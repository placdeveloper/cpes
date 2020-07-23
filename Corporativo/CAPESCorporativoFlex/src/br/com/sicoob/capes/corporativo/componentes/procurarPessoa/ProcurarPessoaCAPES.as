package br.com.sicoob.capes.corporativo.componentes.procurarPessoa {
	import flash.display.DisplayObject;
	import flash.events.Event;
	
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.componentes.input.Texto;
	import br.com.bancoob.dto.ResultadoValidacaoDTO;
	import br.com.bancoob.sisbr.componentes.procurarCliente.SelecionarPessoa;
	import br.com.bancoob.sisbr.componentes.procurarCliente.procurarPessoa;
	import br.com.bancoob.sisbr.componentes.procurarCliente.dto.SelPessoaReqDTO;
	import br.com.bancoob.sisbr.componentes.pti.ObjetoEvent;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.sisbr.portal.SISBRLoader;
	import br.com.bancoob.sisbr.portal.SISBRModuleEvent;
	import br.com.bancoob.util.IValidavel;
	import br.com.bancoob.util.eventos.EventData;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.util.validadores.CNPJ;
	import br.com.bancoob.util.validadores.CPF;
	import br.com.bancoob.vo.DestinoVO;
	
	public class ProcurarPessoaCAPES extends procurarPessoa implements IValidavel {
		
		private var servicoPessoa:ServicoJava = new ServicoJava();
		private var _validarObrigatorio : Boolean = false;
		private var _possuiRelacionamentoBancoob : Boolean = false;
		private var _selecaoAutomaticaDestino:Boolean = true;
		private var _habilitaCadastroPessoa:Boolean = false;
		private var _exibeMensagemErroRetorno:Boolean = true;

		public static const EVENTO_DESTINO_RECUPERADO:String = "destinoRecuperado";
		public static const CLASSE_SERVICO: String = 
				"br.com.sicoob.capes.corporativo.fachada.ProcurarPessoaFachada";
		private static const OPERACAO_PESQUISA: String = "obterDadosSelecao";
		private static const DESTINO_CAPES:String = "servicosJavaCapes";
		
		private var janela:Janela;
		
		private var sisbrLoader:SISBRLoader;

		public function ProcurarPessoaCAPES() {
			super();
			servicoPessoa.source = CLASSE_SERVICO;
        	servicoPessoa.mensagemEspera = "Carregando dados...";
			servicoPessoa.addEventListener(ResultEvent.RESULT, obterDados_onResult);
			servicoPessoa.addEventListener(FaultEvent.FAULT, obterDados_erro);
			tipoPessoa = -1;
			this.addEventListener(FlexEvent.CREATION_COMPLETE, this.iniciar);
		}

		protected function iniciar(evt:FlexEvent):void {
			txtCodigo.restrict = "0123456789";
			txtCodigo.Formato = "";
			txtCodigo.maxChars = 14;
			txtCodigo.tipoEntrada = Texto.TEXTO;
			txtCodigo.validarObrigatorio = validarObrigatorio;
			if (selecaoAutomaticaDestino) {
				inicializarServicos();
			}
			
			// correção do tamanho do campo texto para caber o CPF/CNPJ
			txtCodigo.width += 45;
			botProcurar.x += 45;
			txtNome.x += 45;
		}
		
		public function get possuiRelacionamentoBancoob():Boolean {
			return _possuiRelacionamentoBancoob;
		}
		public function set possuiRelacionamentoBancoob(value:Boolean):void {
			this._possuiRelacionamentoBancoob = value;
		}

		public function get validarObrigatorio() : Boolean {
			return _validarObrigatorio;
		}
		public function set validarObrigatorio(valor : Boolean ) : void {
			_validarObrigatorio = valor;
			if (txtCodigo) {
				txtCodigo.validarObrigatorio = valor;
			}
		}
		
		protected override function obterSelecionarPessoa():SelecionarPessoa {
			return new SelecionarPessoaCAPES(possuiRelacionamentoBancoob, _habilitaCadastroPessoa);
		}

		protected override function pegarDados(evt:EventData):void {
			
			preencherDadosApresentacao(evt.data);
		}
		
		public override function obterDados_onResult(evt:ResultEvent):void {
			
			MostraCursor.removeBusyCursor();
			
			if(evt.result.dados.lista != null && evt.result.dados.lista.length > 0) {
				preencherDadosApresentacao(evt.result.dados.lista[0]);
				
			// Se o parâmetro estiver marcado, exibe uma confirmação para inclusão da pessoa.
			} else if (_habilitaCadastroPessoa) {
				exibirJanelaConfirmacaoAdicionarPessoa();
			} else {
				limpar();
				if(exibeMensagemErroRetorno){
					Alerta.show("CPF/CNPJ não encontrado na base da cooperativa.", "ATENÇÃO", Alerta.ALERTA_INFORMACAO);
				}
			}
		}		
		
		private function obterDados_erro(evt:FaultEvent):void {
			MostraCursor.removeBusyCursor();
			Alerta.show(evt.fault.faultString, "Erro!", Alerta.ALERTA_ERRO);
		}		
		
		public override function procurarCodigo():void {
			
			var cpfCnpj:String = txtCodigo.text;

			if(CPF.validarCPF(cpfCnpj) || CNPJ.validarCNPJ(cpfCnpj)) {
				
				MostraCursor.setBusyCursor("Procurando dados ...", 
						Application.application, MostraCursor.CURSOR_PESQUISAR);

				var req : SelPessoaReqDTO = new SelPessoaReqDTO();
					
				req.tipoProcura = "POR CPF/CNPJ";
				req.tipoPessoa = tipoPessoa;
				req.valor = cpfCnpj;
				req.pagina = 1;
				req.possuiRelacionamentoBancoob = possuiRelacionamentoBancoob;

				servicoPessoa.getOperation(OPERACAO_PESQUISA).send(req);
			} else{
				txtNome.text = "";
				_codigo = 0;
			}
		}
		
		public function procurarPorCodigoCompartilhamento(idCompartilhamento:Number):void {
			if(!isNaN(idCompartilhamento) && idCompartilhamento != 0){
				MostraCursor.setBusyCursor("Procurando dados ...", Application.application, MostraCursor.CURSOR_PESQUISAR);
				
				var req:SelPessoaReqDTO = new SelPessoaReqDTO();
				
				req.tipoProcura = "POR CODIGO COMPARTILHAMENTO";
				req.tipoPessoa = tipoPessoa;
				req.valor = String(idCompartilhamento);
				req.pagina = 1;
				req.possuiRelacionamentoBancoob = possuiRelacionamentoBancoob;
				
				servicoPessoa.getOperation(OPERACAO_PESQUISA).send(req);
			}
		}
		
		private function preencherDadosApresentacao(pessoa:Object): void {
			if(pessoa != null) {
				txtCodigo.text = obterNumeroDocumento(pessoa);
				txtNome.text = pessoa.nomePessoa;
				_registro = pessoa;
				_codigo = pessoa.idPessoa; 
			} else {
				txtNome.text = "";
				_registro = null;
				_codigo = 0;
			}
			this.dispatchEvent(new Event(ITEM_SELECIONADO));
		} 

		public static function obterNumeroDocumento(pessoa:Object): String {     
        	
        	var valor: String = "";
        	
        	if(pessoa != null) {
        		valor = pessoa.cpfCnpj;
        	}
        	
            return valor; 
        } 
        
		public override function limpar():void {
			super.limpar();
			_codigo = 0;
			txtCodigo.valor = 0;
			txtCodigo.text = "";
			txtNome.text = "";
			_registro = null;
		}
			        
	    //--------------------------------------------------------------------------
	    //  Configuração de destino dos serviços.
	    //--------------------------------------------------------------------------	
		private function inicializarServicos():void {
			trace("Inicializando os serviços do componente ProcurarPessoaCAPES.");
			PortalModel.portal.obterDefinicoesDestino(DESTINO_CAPES, configurarDestino, erroAoConfigurarDestino);
		}
		
		public function configurarDestino(destino:DestinoVO = null):void{
			trace("Configurando o destino dos serviços do componente ProcurarPessoaCAPES.");
			if (destino != null) {
				trace("Destino recebido para o componente ProcurarPessoaCAPES: " + destino.destino + " endpoint: " + destino.endPoint);
			    this.destino = destino;
				servicoPessoa.configurarDestino(destino);
				dispatchEvent(new ObjetoEvent(EVENTO_DESTINO_RECUPERADO, destino));
			}
		}
		
		private function erroAoConfigurarDestino(evento:FaultEvent):void {
			trace("Erro ao acionar o método para obter destino do componente ProcurarPessoaCAPES. (" + evento.message +" )");
			Alerta.show("Erro ao obter o destino do componente ProcurarPessoaCAPES: " + evento.message + "( " + evento.fault ? evento.fault.faultDetail : "" + " )", "Erro", Alerta.ALERTA_ERRO);
		}
		
	    //--------------------------------------------------------------------------
	    //  Validação.
	    //--------------------------------------------------------------------------
	    public function realizarValidacao():ResultadoValidacaoDTO {
			var resultado:ResultadoValidacaoDTO = new ResultadoValidacaoDTO();
			
			var msg:String = null;
			if(!validarCampoObrigatorio()) {
				msg = "Campo de preenchimento obrigatório!";
			}
			
			if(!validar()) {
				msg = "Pessoa inválida";
			}
			
			if(msg != null && msg != "") {
				if (this.txtCodigo.errorString != "") {
					msg = this.txtCodigo.errorString;
				}
				
				resultado.adicionarMensagem(msg);
				resultado.valido = false;
				resultado.campoFoco = this.txtCodigo;
			}

			return resultado;
		}
		
		public function validar():Boolean{
 			if(this.txtCodigo.valor > 0){
				return CPF.validarCPF(txtCodigo.text) || CNPJ.validarCNPJ(txtCodigo.text);
	 		}
	 		return true;
		}
		
		public function validarCampoObrigatorio():Boolean {
			if (validarObrigatorio) {
				if(this._registro == null || !this.txtCodigo.validar()) {
					return false;
				}
			}
			return true;
		}

		public function get selecaoAutomaticaDestino():Boolean {
			return _selecaoAutomaticaDestino;
		}

		public function set selecaoAutomaticaDestino(value:Boolean):void {
			_selecaoAutomaticaDestino = value;
		}
		
		public function get exibeMensagemErroRetorno():Boolean {
			return _exibeMensagemErroRetorno;
		}
		
		public function set exibeMensagemErroRetorno(value:Boolean):void {
			_exibeMensagemErroRetorno = value;
		}
		
		public function set habilitaCadastroPessoa(valor:Boolean):void {
			_habilitaCadastroPessoa = valor;
		}
		
		/**
		 * Exibe a confirmação para inclusão da pessoa, caso o parâmetro esteja ligado.
		 **/
		private function exibirJanelaConfirmacaoAdicionarPessoa():void {
			Alerta.show("A pessoa informada não foi encontrada! Deseja incluí-la?", "Confirmação", Alerta.ALERTA_PERGUNTA, null, confirmaAberturaTelaCadastroPessoa);
		}
		
		/**
		 * Abre a janela com a tela de cadastro da pessoa.
		 */
		private function confirmaAberturaTelaCadastroPessoa(evento:Event):void {
			
			// Faz a configuração da janela.
			janela = new Janela();
			janela.width = 560;
			janela.height = 275;
			janela.title = "CADASTRAR PESSOA";
			
			// Obtém o loader, que carregará o módulo de cadastro.
			sisbrLoader = obterSisbrLoader();
			sisbrLoader.x = -10;
			sisbrLoader.y = -15;
			sisbrLoader.url = "/capes/swf/CadastroNovaPessoa.swf";
			sisbrLoader.destino = destino;
			sisbrLoader.loadModule();
			sisbrLoader.addEventListener(SISBRModuleEvent.CARREGADO, sisbrLoaderCarregado);
			
			// Adiciona o módulo carregado à janela e exibe a mesma.
			janela.addChild(sisbrLoader);
			janela.abrir(Application.application as DisplayObject, true, true);
		}
		
		/**
		 * Adiciona o listener ao módulo de cadastro, para saber quando a pessoa foi incluida.
		 */
		private function sisbrLoaderCarregado(evento:SISBRModuleEvent):void {
			var modulo:DisplayObject = sisbrLoader.child as DisplayObject;
			if(modulo != null) {
				modulo.addEventListener("pessoaIncluida", fecharJanela);
			}
		}
		
		/**
		 * Preenche os dados incluídos pelo módulo de inclusão de pessoa na tela do componente e fecha a janela de cadastro.
		 */
		private function fecharJanela(evento:EventData = null):void {
			preencherDadosApresentacao(evento.data);
			obterSisbrLoader().unloadModule();
			sisbrLoader = null;
			janela.fecharJanela();
			evento.stopImmediatePropagation();
		}
		
		/**
		 * Obtém a instância do SISBRLoader.
		 */
		private function obterSisbrLoader():SISBRLoader {
			if(sisbrLoader == null) {
				sisbrLoader = new SISBRLoader();
			}
			return sisbrLoader;
		}
	}
}