package br.com.sicoob.capes.cadastrarContato {
	
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.ParametrosAssistenteAtendimento;
	import br.com.bancoob.sisbr.eventos.EventAssistenteAtendimento;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.VigenteVO;
	import br.com.sicoob.capes.comum.vo.entidades.EmailVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoEmailVO;
	import br.com.sicoob.capes.corporativo.componentes.plataformaatendimento.ProcuraClientePlataformaCAPES;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCAPESBase;
	
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.formatters.DateFormatter;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.ObjectUtil;
	
	public class frmEditarEmail extends frmEditarEmailView implements IEdicaoContato {

		public static const CLASSE_SERVICO: String = "br.com.sicoob.capes.cadastro.fachada.EmailFachada";
		
		private var servicoEdicao:ServicoJava = ServicoJavaUtil.getServico(CLASSE_SERVICO);
		private var servicoConsulta:ServicoJava = ServicoJavaUtil.getServico(CLASSE_SERVICO);
		private var servicoDefinicao:ServicoJava = ServicoJavaUtil.getServico(CLASSE_SERVICO);
		private var servicoExcluir:ServicoJava = ServicoJavaUtil.getServico(CLASSE_SERVICO);
		
		[Bindable]
		private var registro:EmailVO = new EmailVO();
		private var registroBkp:EmailVO = new EmailVO();
		private var pessoaSelecionada:PessoaPlataformaVO;
		
		/**
		 * Construtor.
		 */
		public function frmEditarEmail(){
			super();
			servicoDefinicao.addEventListener(ResultEvent.RESULT, retornoObterDefinicoes);
			servicoConsulta.addEventListener(ResultEvent.RESULT, retornoCarregarRegistro);
			servicoEdicao.addEventListener(ResultEvent.RESULT, retornoEdicao);
			servicoExcluir.addEventListener(ResultEvent.RESULT, retornoExcluir);
			servicoExcluir.addEventListener(FaultEvent.FAULT, retornoExcluirErro);
			
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		public function init(evt:FlexEvent):void {
			pessoaSelecionada = ProcuraClientePlataformaCAPES.getPessoaSelecionada();
			this.cmbTipoEmail.dataProvider = new ArrayCollection();
			carregarDefinicoes();
		}
		
		public function configurarDestinosServicos(destinoVO:DestinoVO):void{
			servicoDefinicao.configurarDestino(destinoVO);
			servicoEdicao.configurarDestino(destinoVO);
			servicoConsulta.configurarDestino(destinoVO);
			servicoExcluir.configurarDestino(destinoVO);
		}
		
		public function carregarDefinicoes(obj:Object=null):void {
			MostraCursor.setBusyCursor("Obtendo definições ...", Application.application, MostraCursor.CURSOR_PROGRESSO);
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.idTipoPessoa = pessoaSelecionada.codTipoPessoa;
			
			servicoDefinicao.getOperation(ContatoPessoaSelecao.OPERACAO_OBTER_DEFINICOES).send(dto);
		}

		public function carregarRegistro(obj:Object): void {
			MostraCursor.setBusyCursor("Obtendo definições ...", Application.application, MostraCursor.CURSOR_PROGRESSO);

			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.email = obj;
			servicoConsulta.getOperation(ContatoPessoaSelecao.OPERACAO_OBTER).send(dto);
		}
		
		public function excluirRegistro(email:Object):void {
			MostraCursor.setBusyCursor("Excluindo Registros!", Application.application, MostraCursor.CURSOR_EXCLUIR);
			
			var vo:EmailVO = new EmailVO();
			vo.idEmail = email["idEmail"];
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.email = email;
			servicoExcluir.getOperation(ContatoPessoaSelecao.OPERACAO_EXCLUIR).send(dto);
		}
		
		public function preencherCampos():void {
			var itemTipoEmail: Object = null;
			if(registroBkp != null && registroBkp.tipoEmail != null) {
				itemTipoEmail = registroBkp.tipoEmail.codigo;
			}
			cmbTipoEmail.procuraItemPorNome(itemTipoEmail, "codigo");
			email.text = registroBkp.descricao;
			dataHoraInicio.text = registroBkp.dataHoraInicio != null ? convertDataToString(registroBkp.dataHoraInicio.data) : null;
			usuarioAlteracao.text = registroBkp.idUsuarioAprovacao;
		}

		public function novoRegistro():void {
			_novo = true;
			registro = new EmailVO();
			registroBkp = new EmailVO();
			
			habilitaCamposExtras(_novo);
			preencherCampos();
		}
		
		public function gravarRegistro():void {
			atualizarCamposRegistro();
			executarSeValido(gravarDados);
		}
		
		private function convertDataToString(data: Date):String{
			var fmt:DateFormatter = new DateFormatter();
			fmt.formatString = "DD/MM/YYYY";
			return fmt.format(data);
		}
		
		private function habilitaCamposExtras(bol: Boolean):void{
			rotuloDataHoraInicio.visible = rotuloDataHoraInicio.includeInLayout = !bol;
			rotuloUsuarioAlteracao.visible = rotuloUsuarioAlteracao.includeInLayout = !bol;
			dataHoraInicio.visible = dataHoraInicio.includeInLayout = !bol;
			usuarioAlteracao.visible = usuarioAlteracao.includeInLayout = !bol;
		}
		
		public function atualizarCamposRegistro():void {
			if (_novo) {
				registro = new EmailVO();
			}
			
			registro.tipoEmail = cmbTipoEmail.selectedItem as TipoEmailVO;
			registro.descricao = email.text;
			registro.pessoaCompartilhamento = TelaPlataformaAtendimentoCAPESBase.getPessoaSelecionada();	
		}
		
		//--------------------------------------------------------------------------
		//  Métodos de auxiliares.
		//--------------------------------------------------------------------------	
		private function gravarDados(): void {
			MostraCursor.setBusyCursor("Gravando Registro!", Application.application, MostraCursor.CURSOR_GRAVAR);

			var dto: RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.email = registro;

			if (_novo) {
				servicoEdicao.getOperation(ContatoPessoaSelecao.OPERACAO_INCLUIR).send(dto);
			} else {
				servicoEdicao.getOperation(ContatoPessoaSelecao.OPERACAO_ALTERAR).send(dto);
			}
		}
		
		public function restaurarRegistro():void{
			registro = (ObjectUtil.copy(registroBkp) as EmailVO);
		}

		public function verificarAlteracoes():Boolean {
			return cmbTipoEmail.selectedItem.codigo == registroBkp.tipoEmail.codigo	&& email.text == registroBkp.descricao;
		}
		
	    //--------------------------------------------------------------------------
	    //  Métodos de callback.
	    //--------------------------------------------------------------------------
		private function retornoObterDefinicoes(event: ResultEvent): void {
			MostraCursor.removeBusyCursor();
			if(event.result.dados.tiposEmail != null) {
				cmbTipoEmail.dataProvider = event.result.dados.tiposEmail;
				cmbTipoEmail.validateNow();
			}
			preencherCampos();
		}
		
		private function retornoCarregarRegistro(evt:ResultEvent):void {
			registro = evt.result.dados.email;
			registroBkp = ObjectUtil.copy(registro) as EmailVO;
			
			_novo = false;	
			habilitaCamposExtras(_novo);
			preencherCampos();
			this.dispatchEvent(new Event(REGISTRO_CARREGADO));
		}
		
		private function retornoEdicao(evt: ResultEvent): void {
			registro = evt.result.dados.endereco; 
			MostraCursor.removeBusyCursor();
	  		this.dispatchEvent(new EventAssistenteAtendimento(EventAssistenteAtendimento.EVENTO_CONFIRMAR_MUDANCA_TELA));
			this.dispatchEvent(new Event(Modulo.REGISTRO_GRAVADO));
			//work around para remover o foco do botão salvar, 
			//fazia com que ao apertar enter depois de salvar o registro ele era incluido novamente.
			cmbTipoEmail.setFocus();
		}

		public function retornoExcluir(evt:ResultEvent):void {
			registro = evt.result.dados.endereco;
			MostraCursor.removeBusyCursor();
			this.dispatchEvent(new Event(REGISTRO_EXCLUIDO));
		}

		public function retornoExcluirErro(evt:FaultEvent):void {
			MostraCursor.removeBusyCursor();
			this.dispatchEvent(new Event(REGISTRO_EXCLUIDO));
		}
		
		[Deprecated]
		public function getEntidadeVigente():VigenteVO {
			return registro;
		}

		public function isRegistroBloqueadoAlteracao():Boolean{
			return false;
		}
		
		/** Interface ITelaBasePlataformaAtendimento*/
		public function trocarTelaEntrar(params:ParametrosAssistenteAtendimento=null):void{};
		public function trocarTelaSair(params:ParametrosAssistenteAtendimento=null):void{};
	}

}