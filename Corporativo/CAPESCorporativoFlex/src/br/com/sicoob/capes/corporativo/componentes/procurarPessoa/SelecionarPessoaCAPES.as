package br.com.sicoob.capes.corporativo.componentes.procurarPessoa {
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.componentes.grid.ColunaGrid;
	import br.com.bancoob.componentes.tabelapaginada.TabelaPaginadaUtils;
	import br.com.bancoob.sisbr.componentes.procurarCliente.SelecionarPessoa;
	import br.com.bancoob.sisbr.componentes.procurarCliente.dto.SelPessoaReqDTO;
	import br.com.bancoob.sisbr.componentes.pti.ObjetoEvent;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.sisbr.portal.SISBRLoader;
	import br.com.bancoob.util.FormatUtil;
	import br.com.bancoob.util.StringUtils;
	import br.com.bancoob.util.eventos.EventData;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	
	import flash.display.DisplayObject;
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.controls.dataGridClasses.DataGridColumn;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	public class SelecionarPessoaCAPES extends SelecionarPessoa {
		
		private var servicoPessoa:ServicoJava = new ServicoJava();
		private var _fecharNoDuploClique : Boolean = true;
		private var _possuiRelacionamentoBancoob : Boolean;
		
		private static const MENSAGEM_FILTRO_MINIMO: String = 
				"O filtro precisa conter pelo menos 3 (três) caracteres!"
		
		public static const CLASSE_SERVICO: String = 
				"br.com.sicoob.capes.corporativo.fachada.ProcurarPessoaFachada";
		private static const OPERACAO_PESQUISA: String = "obterDadosSelecao";
				
		private static const ID_PESQUISA_NOME:String = "POR NOME";
		private static const ID_PESQUISA_CPF_CNPJ:String = "POR CPF/CNPJ";
		private static const ID_PESQUISA_APELIDO:String = "POR APELIDO";
		
		private static const LABEL_NOME:String = "Nome/Razão Social";
		private static const LABEL_CPF_CNPJ:String = "CPF/CNPJ";
		private static const LABEL_APELIDO:String = "Apelido/Nome Comercial";
		
		private static const EVENTO_DESTINO_RECUPERADO:String = "destinoRecuperado";
		private static const DESTINO_CAPES:String = "servicosJavaCapes";
		
		private var janela:Janela;
		
		private var _tipo_pessoa:int = 0;
		public override function get tipoPessoa():int {
			return _tipo_pessoa;
		}
		public override function set tipoPessoa(vlr:int):void {
			this._tipo_pessoa = vlr;
		}
		
		private var _habilitaCadastroPessoa:Boolean;
		
		public function SelecionarPessoaCAPES(apenasBancoob:Boolean = false, habilitaCadastroPessoa:Boolean = false) {
			super();
			_possuiRelacionamentoBancoob = apenasBancoob;
			_habilitaCadastroPessoa = habilitaCadastroPessoa;
			servicoPessoa.source = CLASSE_SERVICO;
        	servicoPessoa.mensagemEspera = "Carregando dados...";
			servicoPessoa.addEventListener(ResultEvent.RESULT, obterDados_onResult);
			servicoPessoa.addEventListener(FaultEvent.FAULT, obterDados_erro);
			this.addEventListener(FlexEvent.CREATION_COMPLETE, this.init);
		}
				
		public override function init(evt:FlexEvent):void {
			super.init(evt);
			desabilitarSelecaoApenasClientes=true;
			desabilitarSelecaoTipoPessoa=true;
			cvOpcoes.visible = false;
			definirColunas();
			inicializarServicos();
		}
		
		public function get possuiRelacionamentoBancoob():Boolean {
			return _possuiRelacionamentoBancoob;
		}
		public function set possuiRelacionamentoBancoob(value:Boolean):void {
			this._possuiRelacionamentoBancoob = value;
		}

		protected override function obterArrayTipoProcura():ArrayCollection {
			var arrTipoProc:ArrayCollection = new ArrayCollection();
			arrTipoProc.addItem({label: ID_PESQUISA_NOME});
			arrTipoProc.addItem({label: ID_PESQUISA_CPF_CNPJ});
			arrTipoProc.addItem({label: ID_PESQUISA_APELIDO});
			return arrTipoProc;
		}	
		
		protected override function mudaFoco():void {
			switch(cboTipoProc.selectedIndex) {
				case 0:
					txtNome.setFocus();
					break;
				case 1:
					txtCPF.setFocus();
					break;
				case 2:
					txtApelido.setFocus();
					break;
			}			
		}		
		
		protected override function mudaTipoProcura(evt:ListEvent=null):Boolean {
			
			txtCodigo.visible = false;
			txtCPF.visible = false;
			txtApelido.visible = false;
			txtNome.visible = false;
			
			txtCodigo.text = "";
			txtCPF.text = "";
			txtApelido.text = "";
			txtNome.text = "";
			
			switch(cboTipoProc.selectedIndex) {
				case 0:
					lblTipo.text = LABEL_NOME;
					txtNome.visible = true;
					cvOpcoes.x = txtNome.x + txtNome.width + 10;
					break;
				case 1:
					lblTipo.text = LABEL_CPF_CNPJ;
					txtCPF.visible = true;
					cvOpcoes.x = txtCPF.x + txtCPF.width + 10;
					break;
				case 2:
					lblTipo.text = LABEL_APELIDO;
					txtApelido.visible = true;
					cvOpcoes.x = txtApelido.x + txtApelido.width + 10;

			}
			
			return true;
		}
		
		protected override function validarFiltro():Boolean {
			
			var retorno:Boolean = true;
			
			switch(cboTipoProc.selectedIndex) {
				case 0:
					if(StringUtils.trim(txtNome.text).length < 3){
						Alerta.show(MENSAGEM_FILTRO_MINIMO, "Atenção", Alerta.ALERTA_OK, txtNome);
						retorno = false;
					}
					break;
				case 1:
					if(StringUtils.trim(txtCPF.text).length < 3){
						Alerta.show(MENSAGEM_FILTRO_MINIMO, "Atenção", Alerta.ALERTA_OK, txtCPF);
						retorno = false;
					}
					break;
				case 2:
					if(StringUtils.trim(txtApelido.text).length < 3){
						Alerta.show(MENSAGEM_FILTRO_MINIMO, "Atenção", Alerta.ALERTA_OK, txtApelido);
						retorno = false;
					}
					break;
			}
			
			return retorno;
		}		
		
		protected override function pegarValorFiltro():String {
			var str:String = "";
			
			switch(cboTipoProc.selectedIndex) {
				case 0:
					str = StringUtils.trim(txtNome.text);
					break;
				case 1:
					str = StringUtils.trim(txtCPF.text);
					break;
				case 2:
					str = StringUtils.trim(txtApelido.text);
					break;					
			}
			
			return str;
		}		
		
		protected override function pesquisarPessoas(req:SelPessoaReqDTO):void {
			req.tipoPessoa = this._tipo_pessoa;
			req.possuiRelacionamentoBancoob = possuiRelacionamentoBancoob;
			
			MostraCursor.setBusyCursor(
					"Carregando Registros!", Application.application, MostraCursor.CURSOR_PESQUISAR);
            servicoPessoa.getOperation(OPERACAO_PESQUISA).send(req);
		}
		
		protected override function obterDados_onResult(evt:ResultEvent):void {
			
			MostraCursor.removeBusyCursor();
			
			barraPaginacao.pagina = evt.result.paginaAtual;
			barraPaginacao.totalPaginas = evt.result.qtdPaginas;
			
			grdDados.dataProvider = evt.result.dados.lista;
			
			if(evt.result.dados.lista.length > 0) {
				
				grdDados.setFocus();
				grdDados.selectedIndex = 0;
				
				botOK.enabled = true;
			}else if (_habilitaCadastroPessoa) {
				exibirJanelaConfirmacaoAdicionarPessoa();
			}
				
			this.enabled = true;
		}
		
		private function obterDados_erro(evt:FaultEvent):void {
			cursorManager.removeBusyCursor();
			this.enabled = true;
		}		
		
		protected override function definirColunas(): void {
            
            var coluna1:ColunaGrid = obterColunaGrid(
            		"nomePessoa", LABEL_NOME, TabelaPaginadaUtils.defaultLabelFunction);
            var coluna2:ColunaGrid = obterColunaGrid("", LABEL_CPF_CNPJ, funcaoNumeroDocumento);
			var coluna3:ColunaGrid = obterColunaGrid(
					"nomeApelido", LABEL_APELIDO, TabelaPaginadaUtils.defaultLabelFunction);
			
			var arrCols:Array = new Array();
			arrCols.push(coluna1);
			arrCols.push(coluna2);
			arrCols.push(coluna3);
			
			grdDados.columns = arrCols;
		}
		
		public static function funcaoNumeroDocumento(objeto:Object, coluna:DataGridColumn):String {     
        	
        	var valor: String = "";
        	
        	if(objeto != null) {
        		valor = objeto.cpfCnpj;
        	}
        	
            return FormatUtil.formataCPFCNPJ(String(valor)); 
        } 
		
		private function obterColunaGrid(nomeAtributo:String, tituloCabecalho:String, 
				funcao: Function): ColunaGrid {
					
			var coluna:ColunaGrid = new ColunaGrid();
            coluna.dataField = nomeAtributo;
            coluna.headerText = tituloCabecalho;
            coluna.labelFunction = funcao;
            return coluna;
		}		
				
		protected override function pegarRegistro(evt:MouseEvent=null):void {
			this.dispatchEvent(new EventData(EVENTO_REGISTROSELECIONADO, grdDados.selectedItem));
			if (fecharNoDuploClique || ((evt != null) && (evt.target == this.botOK))) {
				this.fecharJanela();
			}
		}

		public function get fecharNoDuploClique() : Boolean {
			return _fecharNoDuploClique;
		}
		public function set fecharNoDuploClique(value : Boolean) : void {
			_fecharNoDuploClique = value;
		}
		
		private function exibirJanelaConfirmacaoAdicionarPessoa():void {
			Alerta.show("A pessoa informada não foi encontrada! Deseja incluí-la?", "Confirmação", Alerta.ALERTA_PERGUNTA, null, confirmaAberturaTelaCadastroPessoa);
		}
		
		private function confirmaAberturaTelaCadastroPessoa(evento:Event):void {
			janela = new Janela();
			janela.width = 560;
			janela.height = 275;
			janela.title = "CADASTRAR PESSOA";
			
			var sisbrLoader:SISBRLoader = new SISBRLoader();
			sisbrLoader.x = -10;
			sisbrLoader.y = -15;
			sisbrLoader.url = "/capes/swf/CadastroNovaPessoa.swf";
			sisbrLoader.destino = destino;
			sisbrLoader.loadModule();
			janela.addChild(sisbrLoader);
			
			sisbrLoader.addEventListener("pessoaIncluida", fecharJanelaInclusao);
			
			janela.abrir(Application.application as DisplayObject, true, true);
		}
		
		private function fecharJanelaInclusao(evento:EventData = null):void {
			janela.fecharJanela();
			evento.stopImmediatePropagation();
		}
		
		public override function setFocus():void {
			super.setFocus();
			txtApelido.text = "";
			txtCodigo.text = "";
			txtCPF.text = "";
			txtNome.text = "";
			grdDados.dataProvider = new ArrayCollection();
		}

	    //--------------------------------------------------------------------------
	    //  Configuração de destino dos serviços.
	    //--------------------------------------------------------------------------	
		private function inicializarServicos():void {
			trace("Inicializando os serviços do componente ProcurarPessoaCAPES.");
			PortalModel.portal.obterDefinicoesDestino(DESTINO_CAPES, configurarDestino);
		}
		
		private function configurarDestino(destino:DestinoVO = null):void{
			trace("Configurando o destino dos serviços do componente ProcurarPessoaCAPES.");
			if (destino != null) {
				trace("Destino recebido para o componente ProcurarPessoaCAPES: " + destino.destino + " endpoint: " + destino.endPoint);
				this.destino = destino;
				servicoPessoa.configurarDestino(destino);
				dispatchEvent(new ObjetoEvent(EVENTO_DESTINO_RECUPERADO, destino));
			}
		}	
	}
}