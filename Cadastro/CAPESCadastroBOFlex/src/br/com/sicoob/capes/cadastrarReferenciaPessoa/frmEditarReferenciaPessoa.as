package br.com.sicoob.capes.cadastrarReferenciaPessoa{
	
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.eventos.SelecaoEvent;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastrarContato.frmListarContatos;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.VigenteVO;
	import br.com.sicoob.capes.comum.vo.entidades.ReferenciaBancariaVO;
	import br.com.sicoob.capes.comum.vo.entidades.ReferenciaVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoReferenciaVO;
	import br.com.sicoob.capes.comum.vo.entidades.interfaces.Aprovavel;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	import br.com.sicoob.capes.utils.ConversaoPessoaUtil;
	import br.com.sicoob.capes.utils.plataformaatendimento.IEdicaoPlataformaAtendimentoCAPES;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCAPESBase;
	
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.formatters.DateFormatter;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.ObjectUtil;
	
	public class frmEditarReferenciaPessoa extends frmEditarReferenciaPessoaView implements IEdicaoPlataformaAtendimentoCAPES {
		
		private static var COD_TIPO_REFERENCIA_BANCARIA:Number = 0;
		
		private var referenciaPessoaVOBkp:ReferenciaVO = new ReferenciaVO();
		
		private var isBancoValido:Boolean = true;
		private var isAgenciaValida:Boolean = true;
		
		private var limparAgencia:Boolean = true;
		
		[Bindable]
		private var referencia:ReferenciaVO = new ReferenciaVO();

		/**
		 *	Serviços 
		 */
		public var servicoConsulta:ServicoJava;
		public var servicoSalvar:ServicoJava;
		public var servicoExcluir:ServicoJava;
		public var servicoDefinicao:ServicoJava;
		public var servicoObterBanco:ServicoJava;
		public var servicoObterAgencia:ServicoJava;
		public var servicoListaTelefone:ServicoJava;
		
		/**
		 *  Operações 
		 */
		static private const OPERACAO_OBTER_DEFINICOES: String = "obterDefinicoes";
		static private const OPERACAO_INCLUIR_DADOS: String = "incluirDados";
		static private const OPERACAO_ALTERAR_DADOS: String = "alterarDados";
		static private const OPERACAO_EXCLUIR_REFERENCIA: String = "excluirDados";
		static private const OPERACAO_OBTER_REFERENCIA: String = "obterDados";
		static private const OPERACAO_OBTER_BANCO: String = "obterBanco";
		static private const OPERACAO_OBTER_AGENCIA: String = "obterAgencia";

		static private const CLASSE_SERVICO: String = 
			"br.com.sicoob.capes.cadastro.fachada.ReferenciaFachada";
		
		static private const CLASSE_SERVICO_TELEFONE: String = 
				"br.com.sicoob.capes.cadastro.fachada.TelefoneFachada";

		//--------------------------------------------------------------------------
	    //  Propriedades
	    //--------------------------------------------------------------------------

	    /**
	     *  Construtor.
	     */
		public function frmEditarReferenciaPessoa()
		{
			super();

			servicoConsulta = ServicoJavaUtil.getServico(CLASSE_SERVICO);
			servicoConsulta.addEventListener(ResultEvent.RESULT, retornoCarregarRegistro);

			servicoDefinicao = ServicoJavaUtil.getServico(CLASSE_SERVICO);
			servicoDefinicao.addEventListener(ResultEvent.RESULT, retornoObterDefinicoes);

			servicoSalvar = ServicoJavaUtil.getServico(CLASSE_SERVICO);
			servicoSalvar.addEventListener(ResultEvent.RESULT, retornoSalvar);		

			servicoExcluir = ServicoJavaUtil.getServico(CLASSE_SERVICO);
			servicoExcluir.addEventListener(ResultEvent.RESULT, retornoExcluir);	
			servicoExcluir.addEventListener(FaultEvent.FAULT, retornoExcluirErro);

			servicoListaTelefone = ServicoJavaUtil.getServico(CLASSE_SERVICO_TELEFONE);
			servicoListaTelefone.addEventListener(ResultEvent.RESULT, retornoTelefones);	

			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		//--------------------------------------------------------------------------
	    //  Métodos
	    //--------------------------------------------------------------------------
		private function init(evt:FlexEvent):void 
		{
			carregarDefinicoes();
			inicializarServicos();
			cmbTipoReferencia.addEventListener(ListEvent.CHANGE, alterarObrigatoriedadeCampos);
		}
		
		private function inicializarServicos():void {
			PortalModel.portal.obterDefinicoesDestino("servicosJava", onDestinoPortalRecuperado);
		}  		
		
		private function onDestinoPortalRecuperado(destino:DestinoVO):void {
			procurarBanco.configurarDestino(destino);
			procurarAgencia.configurarDestino(destino);
			
			procurarBanco.addEventListener(SelecaoEvent.OBJETO_SELECIONADO, configurarFiltroAdicionalAgencia); 
			procurarPessoa.addEventListener("itemSelecionado", obterTelefones); 
			procurarPessoa.txtCodigo.addEventListener("change", function():void {
				gridTelefones.dataProvider = null;
				grupoDDDTel.visible = true;
				gridTelefones.visible = false;
			});
		}
		
        private function configurarFiltroAdicionalAgencia(event: SelecaoEvent) : void {
			
			if (event.objeto != null && event.objeto.NUMBANCO != null) {
				
				if(limparAgencia) {
					procurarAgencia.limpar();
				}
				
				procurarAgencia.txtValor.enabled = true;
				procurarAgencia.btnProcurar.enabled = true;
				
				carregarConfiguracaoAgencia(event.objeto.NUMBANCO);
				limparAgencia = true;
   			} else {
				procurarAgencia.txtValor.enabled = false;
				procurarAgencia.btnProcurar.enabled = false;	
				procurarAgencia.limpar();	
   			}
        }
        
        private function carregarConfiguracaoAgencia(numBanco:String): void {
    		var obj: Object = new Object();
			obj.nome = "NUMBANCO";
			obj.valor = numBanco;

			var arr: ArrayCollection = new ArrayCollection();
			arr.addItem(obj);

			this.procurarAgencia.parametros = arr;
            this.procurarAgencia.filtroAdicional = 90041;
        }
        
		public function carregarRegistro(referencia:Object):void {
			
			MostraCursor.setBusyCursor("Obtendo definições ...", 
				Application.application, MostraCursor.CURSOR_PROGRESSO);

			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.idReferencia = referencia.idReferenciaPessoa;

			servicoConsulta.getOperation(OPERACAO_OBTER_REFERENCIA).send(dto);
		}

		private function retornoCarregarRegistro(evt:ResultEvent):void {

			referencia = evt.result.dados.referencia;
			referenciaPessoaVOBkp = ObjectUtil.copy(referencia) as ReferenciaVO;
			
//			preencherCampos();
			_novo = false;
			habilitaCamposExtras(_novo);
			this.dispatchEvent(new Event(REGISTRO_CARREGADO));	
		}
		
		private function convertDataToString(data: Date):String{
			var fmt:DateFormatter = new DateFormatter();
			fmt.formatString = "DD/MM/YYYY";
			return fmt.format(data);
		}
		
		private function habilitaCamposExtras(bol: Boolean):void{
			rotuloDataHoraInicio.visible = rotuloDataHoraInicio.includeInLayout = !bol;
			rotuloUsuarioAlteracao.visible = rotuloUsuarioAlteracao.includeInLayout = !bol;
			dataCadastro.visible = dataCadastro.includeInLayout = !bol;
			usuarioAlteracao.visible = usuarioAlteracao.includeInLayout = !bol;
		}

		public function preencherCampos():void {

			var data: Date = new Date();
			var tipoReferencia: Object = null;
			
			if(referencia.tipoReferencia != null) {
				tipoReferencia = referencia.tipoReferencia.codigo;
			}				
			if (referencia.dataHoraInicio != null) {
				data = referencia.dataHoraInicio.data;	
			}
			
			dataCadastro.selectedDate = data; 
			observacao.text = referencia.observacao;
			cmbTipoReferencia.procuraItemPorNome(tipoReferencia, "codigo");
			usuarioAlteracao.text = referencia.idUsuarioAprovacao;
							
			procurarPessoa.limpar();
			gridTelefones.dataProvider = null;
			grupoDDDTel.visible = true;
			gridTelefones.visible = false;
			if (referencia.pessoaReferencia != null) {
				procurarPessoa.txtCodigo.text = referencia.pessoaReferencia.pessoa.cpfCnpj;
				procurarPessoa.txtNome.text = referencia.pessoaReferencia.nomePessoa;
				procurarPessoa.procurarCodigo();
				ddd.selectedIndex = 0;
				numTelefone.text = "";
			} else {
				ddd.procuraItemPorNome(referencia.ddd.toString(), "label");
				numTelefone.text = referencia.telefone;
			}
			
			if (referencia is ReferenciaBancariaVO) {
							
				limparAgencia = false;
				
				var numBanco:String = ReferenciaBancariaVO(referencia).numeroBanco;
				procurarBanco.txtValor.text = numBanco;
				procurarBanco.pesquisar();
				procurarBanco.validateNow();
								
				procurarAgencia.txtValor.text = ReferenciaBancariaVO(referencia).numeroAgencia;
				carregarConfiguracaoAgencia(numBanco);
				
				procurarAgencia.pesquisar();
				conta.text = ReferenciaBancariaVO(referencia).numeroConta;
				
			} else {
				procurarBanco.limpar();
				procurarAgencia.limpar();
			}
			
			cmbTipoReferencia.enabled = _novo;
			procurarPessoa.enabled = _novo;
						
			alterarObrigatoriedadeCampos();
		}
		
		public function carregarDefinicoes(obj:Object=null):void {
			servicoDefinicao.getOperation(OPERACAO_OBTER_DEFINICOES).send(new RequisicaoReqDTO());
		}

		private function retornoObterDefinicoes(evt:ResultEvent):void {
			cmbTipoReferencia.dataProvider = evt.result.dados.listaTiposReferencia;
		}

		private function retornoSalvar(evt:ResultEvent):void {
			
			referencia = evt.result.dados.referencia;
			
			MostraCursor.removeBusyCursor();

			this.dispatchEvent(new Event(Modulo.REGISTRO_GRAVADO));	
		}

		public function novoRegistro():void
		{
			_novo = true;
			habilitaCamposExtras(_novo);
			referenciaPessoaVOBkp = new ReferenciaVO();
			referenciaPessoaVOBkp.idReferenciaPessoa = 0;
			limparFormIncluir();
		}

		public function gravarRegistro():void {

			atualizarCamposRegistro();
			
			executarSeValido(gravarDados);
		}

		public function atualizarCamposRegistro():void{

			if (_novo) {
				if (cmbTipoReferencia.selectedItem != null 
					&& cmbTipoReferencia.selectedItem.codigo == COD_TIPO_REFERENCIA_BANCARIA) {
					referencia = new ReferenciaBancariaVO();
				} else {
					referencia = new ReferenciaVO();
				}

				referencia.pessoa = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(
						TelaPlataformaAtendimentoCAPESBase.getPessoaPlataforma());
				referencia.pessoaReferencia = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(
						this.procurarPessoa.registro as PessoaPlataformaVO);
			}
			
			if (referencia is ReferenciaBancariaVO) {
				ReferenciaBancariaVO(referencia).numeroBanco = procurarBanco.txtValor.text;
				ReferenciaBancariaVO(referencia).numeroAgencia = procurarAgencia.txtValor.text;
				
				if(conta.text != "") {
					ReferenciaBancariaVO(referencia).numeroConta = conta.text;
				}
			}
			if(referencia.pessoaReferencia == null
					|| this.procurarPessoa.txtCodigo.text == null
					|| this.procurarPessoa.txtCodigo.text == ''){
				if (ddd.selectedItem != null) { 
					referencia.ddd = parseInt(ddd.selectedLabel);
				}
				referencia.telefone = numTelefone.text;
			}
			referencia.observacao = observacao.text;
			referencia.tipoReferencia = cmbTipoReferencia.selectedItem as TipoReferenciaVO;
		}

		private function gravarDados():void {

			MostraCursor.setBusyCursor("Gravando dados ...", Application.application,
			MostraCursor.CURSOR_PROGRESSO);

			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.referencia = referencia;

			if (_novo) {
				servicoSalvar.getOperation(OPERACAO_INCLUIR_DADOS).send(dto);
			} else {
				servicoSalvar.getOperation(OPERACAO_ALTERAR_DADOS).send(dto);
			}
		}

		public function excluirRegistro(referencia:Object):void {
			MostraCursor.setBusyCursor("Excluindo Registro...", Application.application, 
				MostraCursor.CURSOR_EXCLUIR);

			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.referencia = referencia;

			servicoExcluir.getOperation(OPERACAO_EXCLUIR_REFERENCIA).send(dto);
		}
		
		public function retornoExcluir(evt:ResultEvent):void {
			
			//referencia = evt.result.dados.referencia;
			MostraCursor.removeBusyCursor();
			this.dispatchEvent(new Event(REGISTRO_EXCLUIDO));		
		}
		
		public function retornoExcluirErro(evt:FaultEvent):void {
			 
			MostraCursor.removeBusyCursor();
			carregarDefinicoes();
			//this.dispatchEvent(new Event(REGISTRO_EXCLUIDO));							
		}		
		
		public function restaurarRegistro():void{
			referencia = (ObjectUtil.copy(referenciaPessoaVOBkp) as ReferenciaVO);
		}

		public function verificarAlteracoes():Boolean {
			
			var dddSelecionado:Number;
			var tipoReferencia:Number;
			var cpfCnpjReferencia:String = '';
			
			if(ddd.selectedItem != null) {
				dddSelecionado = Number(ddd.selectedItem.label);
			}
			if(cmbTipoReferencia.selectedItem != null) {
				tipoReferencia = Number(cmbTipoReferencia.selectedItem.codigo);
			}
			if(referenciaPessoaVOBkp.pessoaReferencia != null && 
					referenciaPessoaVOBkp.pessoaReferencia.pessoa != null) {
				cpfCnpjReferencia = referenciaPessoaVOBkp.pessoaReferencia.pessoa.cpfCnpj;
			}
			
			var registroIgual:Boolean =	tipoReferencia == referenciaPessoaVOBkp.tipoReferencia.codigo
				&& procurarPessoa.txtCodigo.text == cpfCnpjReferencia
				&& observacao.text == referenciaPessoaVOBkp.observacao
				&& dddSelecionado == referenciaPessoaVOBkp.ddd
				&& numTelefone.text == referenciaPessoaVOBkp.telefone;
				
			if (referenciaPessoaVOBkp is ReferenciaBancariaVO) {
				var refBancaria:ReferenciaBancariaVO = referenciaPessoaVOBkp as ReferenciaBancariaVO;
				
				registroIgual = procurarBanco.txtValor.text == refBancaria.numeroBanco
					&& procurarAgencia.txtValor.text == refBancaria.numeroAgencia
					&& conta.text == refBancaria.numeroConta
			}
			
			return registroIgual;
		}

		private function validarCampos():Boolean {
			return true;
		}

		public function limparFormIncluir(event:FlexEvent=null):void{
			dataCadastro.selectedDate = new Date();
			observacao.text = "";
			ddd.selectedIndex = 0;
			numTelefone.text = "";
			cmbTipoReferencia.selectedIndex = 0;
			cmbTipoReferencia.enabled = true;
			procurarPessoa.limpar();

			procurarBanco.limpar();
			procurarBanco.txtValor.enabled = false;
			procurarBanco.btnProcurar.enabled = false;

			procurarAgencia.limpar();
			procurarAgencia.txtValor.enabled = false;
			procurarAgencia.btnProcurar.enabled = false;
			
			conta.text = "";
			conta.enabled = false;
			obterTelefones();
		}
		
		private function alterarObrigatoriedadeCampos(evt:ListEvent=null):void {

			if (cmbTipoReferencia.selectedItem != null 
					&& cmbTipoReferencia.selectedItem.codigo == COD_TIPO_REFERENCIA_BANCARIA) {

				procurarBanco.txtValor.enabled = true;
				procurarBanco.btnProcurar.enabled = true;
				procurarBanco.validarObrigatorio = true;

				if (!_novo) {
					procurarAgencia.txtValor.enabled = true;
					procurarAgencia.btnProcurar.enabled = true;
				}
				procurarAgencia.validarObrigatorio = true;

				conta.enabled = true;
			} else {
				procurarBanco.txtValor.enabled = false;
				procurarBanco.btnProcurar.enabled = false;
				procurarBanco.validarObrigatorio = false;
				procurarBanco.limpar();

				procurarAgencia.txtValor.enabled = false;
				procurarAgencia.btnProcurar.enabled = false;
				procurarAgencia.validarObrigatorio = false;
				procurarAgencia.limpar();

				conta.enabled = false;		
				conta.text = "";
				
				observacao.validarObrigatorio = true;
			}
		}

		[Deprecated]
		public function getEntidadeVigente():VigenteVO {
			return null;
		}

		public function getEntidadeAprovavel():Aprovavel {
			return null;
		}
		
		private function obterTelefones(event: Event=null) : void {
			if(this.procurarPessoa.registro != null){
				this.grupoDDDTel.visible = this.grupoDDDTel.includeInLayout = false;
				this.gridTelefones.visible = true;
			 	var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			 	if(_novo && observacao.text.length == 0){
			 		observacao.text = this.procurarPessoa.txtNome.text;
			 	}
				dto.dados.pessoa = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(
							this.procurarPessoa.registro as PessoaPlataformaVO);
				servicoListaTelefone.getOperation(frmListarContatos.OPERACAO_OBTER_DADOS_SELECAO).send(dto);
			} else {
				this.grupoDDDTel.visible = true;
				this.procurarPessoa.enabled = true;				
				this.gridTelefones.visible = false;
				this.gridTelefones.dataProvider = null;
			}
		}
		
		public function retornoTelefones(event:ResultEvent):void {
			this.gridTelefones.dataProvider = event.result.dados["lista"];
			MostraCursor.removeBusyCursor();
		}

		public function isRegistroBloqueadoAlteracao():Boolean{
			return false; 
		}
	}
}