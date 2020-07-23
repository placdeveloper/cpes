package br.com.sicoob.capes.cadastrarMensagem{

	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.input.Data;
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.DateUtils;
	import br.com.bancoob.util.FormataData;
	import br.com.bancoob.util.StringUtils;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.FlexUtil;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.VigenteVO;
	import br.com.sicoob.capes.comum.vo.entidades.MensagemVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoDestinoExibicaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoMensagemVO;
	import br.com.sicoob.capes.utils.ConversaoPessoaUtil;
	import br.com.sicoob.capes.utils.plataformaatendimento.IEdicaoPlataformaAtendimentoCAPES;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCAPESBase;
	
	import flash.events.Event;
	import flash.events.FocusEvent;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.ObjectUtil;
	
	public class frmEditarMensagem extends frmEditarMensagemView implements IEdicaoPlataformaAtendimentoCAPES {

		private var mensagemVOBkp:MensagemVO = new MensagemVO();

		[Bindable]
		private var mensagem:MensagemVO = new MensagemVO();

		/**
		 *	Serviços 
		 */
		private var servicoConsulta:ServicoJava;
		private var servicoSalvar:ServicoJava;
		private var servicoExcluir:ServicoJava;
		private var servicoDefinicao:ServicoJava;
		private var servicoTipoMensagem:ServicoJava;
		
		/**
		 *  Operações 
		 */
		static private const OPERACAO_OBTER_DEFINICOES: String = "obterDefinicoes";
		static private const OPERACAO_INCLUIR_DADOS: String = "incluirDados";
		static private const OPERACAO_ALTERAR_DADOS: String = "alterarDados";
		static private const OPERACAO_EXCLUIR_MENSAGEM: String = "excluirDados";
		static private const OPERACAO_OBTER_MENSAGEM: String = "obterDados";
		static private const OPERACAO_OBTER_TIPO_MENSAGEM: String = "obterTipoMensagem";
		static private const OPERACAO_OBTER_TIPO_MENSAGEM_TIPO_DESTINO_EXIBICAO:String = "obterTipoMensagensDestinoExibicao";

		static private const CLASSE_SERVICO: String = "br.com.sicoob.capes.cadastro.fachada.MensagemFachada";
		
		private var _cancelar:Boolean = false;
		
		//--------------------------------------------------------------------------
	    //  Propriedades
	    //--------------------------------------------------------------------------

	    /**
	     *  Construtor.
	     */
		public function frmEditarMensagem(){
			super();

			servicoConsulta = ServicoJavaUtil.getServico(CLASSE_SERVICO);
			servicoConsulta.addEventListener(ResultEvent.RESULT, retornoCarregarRegistro);
			
			servicoDefinicao = ServicoJavaUtil.getServico(CLASSE_SERVICO);
			servicoDefinicao.addEventListener(ResultEvent.RESULT, retornoObterDefinicoes);
			
			servicoTipoMensagem = ServicoJavaUtil.getServico(CLASSE_SERVICO);
			servicoTipoMensagem.addEventListener(ResultEvent.RESULT, retornoObterTipoMensagem);

			servicoSalvar = ServicoJavaUtil.getServico(CLASSE_SERVICO);
			servicoSalvar.addEventListener(ResultEvent.RESULT, retornoSalvar);		

			servicoExcluir = ServicoJavaUtil.getServico(CLASSE_SERVICO);
			servicoExcluir.addEventListener(ResultEvent.RESULT, retornoExcluir);	

			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		public function configurarDestino(destino:DestinoVO):void {
			servicoConsulta.configurarDestino(destino);
			servicoSalvar.configurarDestino(destino);
			servicoExcluir.configurarDestino(destino);
			servicoDefinicao.configurarDestino(destino);
			servicoTipoMensagem.configurarDestino(destino);
		}
		
		//--------------------------------------------------------------------------
	    //  Métodos
	    //--------------------------------------------------------------------------
		
		private function init(evt:FlexEvent):void {
			this.prazo.addEventListener(FocusEvent.FOCUS_OUT, onFocusOutPrazo);
			this.validade.dataMaxima = obterDataMaxima();
			this.validade.dataMinima = obterDataMinima();
			this.validade.addEventListener(FocusEvent.FOCUS_OUT, onFocusOutValidade);
			this.cmbTipoDestinoExibicao.addEventListener(ListEvent.CHANGE, obterTipoMensagem);
			
			obterDefinicoes();
		}
		
		private function obterDataMaxima():Date {
			var data:Date = new Date();
			data.date += this.prazo.valorMaximo;
			data.hours = 23;
			data.minutes = 59;
			data.seconds = 59;
			data.milliseconds = 999;
			return data;
		}
		
		private function obterDataMinima():Date {
			var data:Date = new Date();
			data.date += 1;
			data.hours = 0;
			data.minutes = 0;
			data.seconds = 0;
			data.milliseconds = 0;
			return data;
		}
		
		public function carregarRegistro(mensagem:Object):void {
			MostraCursor.setBusyCursor("Obtendo definições ...", Application.application, MostraCursor.CURSOR_PROGRESSO);

			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.mensagem = mensagem;
			
			servicoConsulta.getOperation(OPERACAO_OBTER_TIPO_MENSAGEM_TIPO_DESTINO_EXIBICAO).send(dto);
		}

		private function retornoCarregarRegistro(evt:ResultEvent):void {
			this.cmbTipoMensagem.dataProvider = evt.result.dados.tiposMensagem;
			this.cmbTipoDestinoExibicao.dataProvider = evt.result.dados.tiposDestinoExibicao;
			mensagem = evt.result.dados.mensagem;
			
			cmbTipoMensagem.validateNow();
			cmbTipoDestinoExibicao.validateNow();
			
			mensagemVOBkp = ObjectUtil.copy(mensagem) as MensagemVO;
			_novo = false;
			habilitaCamposExtras(_novo);
			preencherCampos();
			
		
			this.dispatchEvent(new Event(REGISTRO_CARREGADO));
		}

		public function preencherCampos():void {
			if(mensagem.validade != null){
				this.validade.selectedDate = mensagem.validade.data;
			}
			
			this.descricao.text = mensagem.mensagem;
			this.prazo.valor = calculaPrazo();		
			
			if (mensagem.dataHoraInicio != null){
				this.dataHoraInicio.selectedDate = mensagem.dataHoraInicio.data;
			}
			this.usuarioAlteracao.text = mensagem.idUsuarioAprovacao;
			this.cmbTipoDestinoExibicao.procuraItemPorNome(mensagem.codigoTipoDestinoExibicao, "codTipoDestinoExibicao");
			this.cmbTipoMensagem.procuraItemPorNome(mensagem.codigoTipoMensagem, "codTipoMensagem");	
		}
		
		public function carregarDefinicoes(obj:Object=null):void {
			
		}

		private function retornoSalvar(evt:ResultEvent):void {
			mensagem = evt.result.dados.mensagem;
			MostraCursor.removeBusyCursor();

			this.dispatchEvent(new Event(Modulo.REGISTRO_GRAVADO));	
		}

		public function novoRegistro():void {
			mensagem = new MensagemVO();
			mensagemVOBkp = new MensagemVO();
			_novo = true;
			habilitaCamposExtras(_novo);
			cmbTipoMensagem.enabled = false;
			limparFormIncluir();
		}
		
		private function habilitaCamposExtras(bol: Boolean):void{
			rotuloDataHoraInicio.visible = rotuloDataHoraInicio.includeInLayout = !bol;
			rotuloUsuarioAlteracao.visible = rotuloUsuarioAlteracao.includeInLayout = !bol;
			dataHoraInicio.visible = dataHoraInicio.includeInLayout = !bol;
			usuarioAlteracao.visible = usuarioAlteracao.includeInLayout = !bol;
		}
		
		private function obterDefinicoes():void {
			MostraCursor.setBusyCursor("Obtendo definições ...", Application.application, MostraCursor.CURSOR_PROGRESSO);
			servicoDefinicao.getOperation(OPERACAO_OBTER_DEFINICOES).send();
		}
		
		private function retornoObterDefinicoes(event: ResultEvent): void {
			cmbTipoDestinoExibicao.dataProvider = event.result.dados.tiposDestinoExibicao;
			MostraCursor.removeBusyCursor();
		}
		
		private function obterTipoMensagem(evento:Event): void {
			var tipoDestinoExibicao:TipoDestinoExibicaoVO = TipoDestinoExibicaoVO(cmbTipoDestinoExibicao.selectedItem);
			
			FlexUtil.atualizarCombo(cmbTipoMensagem, null, true);
			
			if(tipoDestinoExibicao != null ) {
				MostraCursor.setBusyCursor("Obtendo definições ...", Application.application, MostraCursor.CURSOR_PESQUISAR);
				var dto: RequisicaoReqDTO = new RequisicaoReqDTO();
				dto.dados.tipoDestinoExibicao = tipoDestinoExibicao;
				cmbTipoMensagem.enabled = true;
				servicoTipoMensagem.getOperation(OPERACAO_OBTER_TIPO_MENSAGEM).send(dto);
			}
		}			
		
		private function retornoObterTipoMensagem(event:ResultEvent): void{
			MostraCursor.removeBusyCursor();
			FlexUtil.atualizarCombo(cmbTipoMensagem, event.result.dados.tiposMensagem, true);
		}
		
		private function isDataValida(input:Data):Boolean {
			input.autoValidacao = false;
			var valido : Boolean = input.validar(montarDataMascara(input));
			input.autoValidacao = true;
			return valido;
		}
				
		protected function montarDataMascara(input : Data):Date {
			var format:String = StringUtils.replace(StringUtils.replace(input.mascara, "/", ""), "-", "");
			return DateUtils.tratarBugData(DateUtils.stringToDate(StringUtils.trim(input.compMask.text), format));
		}

		public function gravarRegistro():void {
			if(!validarCampos()){
				return;
			}

			atualizarCamposRegistro();
			executarSeValido(gravarDados);
		}

		public function atualizarCamposRegistro():void{
			mensagem.mensagem = this.descricao.text;
			mensagem.dataHoraInicio = DateTimeBase.getDateTimeEntity(this.dataHoraInicio.selectedDate);
			mensagem.validade = DateTimeBase.getDateTimeEntity(this.validade.selectedDate);
			mensagem.pessoa = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(TelaPlataformaAtendimentoCAPESBase.getPessoaPlataforma()).pessoa;
			
			if(cmbTipoDestinoExibicao.selectedItem != null) {
				mensagem.codigoTipoDestinoExibicao = cmbTipoDestinoExibicao.selectedItem.codTipoDestinoExibicao;
			}
			
			if(cmbTipoMensagem.selectedItem != null) {
				mensagem.codigoTipoDestinoExibicao = cmbTipoDestinoExibicao.selectedItem.codTipoDestinoExibicao;
				mensagem.codigoTipoMensagem = cmbTipoMensagem.selectedItem.codTipoMensagem
			}
		}

		private function gravarDados():void {
			MostraCursor.setBusyCursor("Gravando dados ...", Application.application,
			MostraCursor.CURSOR_PROGRESSO);

			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.mensagem = mensagem;

			if (_novo){
				servicoSalvar.getOperation(OPERACAO_INCLUIR_DADOS).send(dto);
			}else{
				servicoSalvar.getOperation(OPERACAO_ALTERAR_DADOS).send(dto);
			}
		}

		public function excluirRegistro(mensagem:Object):void {
			MostraCursor.setBusyCursor("Excluindo Registro...", Application.application, MostraCursor.CURSOR_EXCLUIR);

			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.mensagem = mensagem;
			servicoExcluir.getOperation(OPERACAO_EXCLUIR_MENSAGEM).send(dto);
		}
		
		public function retornoExcluir(evt:ResultEvent):void {
			mensagem = evt.result.dados.mensagem;
			MostraCursor.removeBusyCursor();
			
			this.dispatchEvent(new Event(REGISTRO_EXCLUIDO));		
		}
		
		public function restaurarRegistro():void{
			mensagem = (ObjectUtil.copy(mensagemVOBkp) as MensagemVO);
			_cancelar = true;
		}

		public function verificarAlteracoes():Boolean {
			var codDestinoExibicao:Number= cmbTipoDestinoExibicao.selectedIndex as Number;
			codDestinoExibicao = codDestinoExibicao -1;
			if(mensagemVOBkp != null){
				if(mensagemVOBkp.mensagem != descricao.text){
					return false;
				}
				
				if(mensagemVOBkp.codigoTipoDestinoExibicao != codDestinoExibicao){ 
					return false;
				}
				
				if(mensagemVOBkp.dataHoraInicio != null && FormataData.formataData(mensagemVOBkp.dataHoraInicio.data) != FormataData.formataData(dataHoraInicio.selectedDate)){
					return false;
				}
				
				if(mensagemVOBkp.validade != null && FormataData.formataData(mensagemVOBkp.validade.data) != FormataData.formataData(validade.selectedDate)){
					return false;
				}
			}

			return true;
		}

		private function validarCampos():Boolean {
			if (!isDataValida(this.validade)) {
				Alerta.show(this.validade.compMask.errorString, 'ATENÇÃO!', Alerta.ALERTA_ERRO, this.dataHoraInicio.compMask);
				return false;
			}

			return true;
		}

		public function limparFormIncluir(event:FlexEvent=null):void{
			this.descricao.text = "";
			this.dataHoraInicio.selectedDate = new Date();		
			this.validade.selectedDate = null;
			this.prazo.text = "";
			onFocusOutPrazo();
			resetaComboBox();
		}
		
		private function resetaComboBox():void{
			cmbTipoDestinoExibicao.selectedIndex = 0;
			var listaVazia:ArrayCollection = new ArrayCollection();
			cmbTipoMensagem.dataProvider = listaVazia;
			cmbTipoMensagem.selectedIndex = 0;
		}

		private function onFocusOutPrazo(evt:Event=null):void{
			if(!(prazo.text == "")){
				var novaData:Date = new Date(dataHoraInicio.selectedDate);
				novaData["date"] += prazo.valor;
				validade.selectedDate = novaData;
			}else{
				this.validade.selectedDate = null;
			}
		}

		private function onFocusOutValidade(evt:Event=null):void{
			var qtdDias:Number = calculaPrazo();
			if (qtdDias < 0 ) {
				prazo.text = "";
			} else {
				prazo.valor = qtdDias;
			}
		}
		
		private function calculaPrazo() : Number {
			var prazo:Number = -1;
			var millisecondsPerDay:int = 1000 * 60 * 60 * 24; 
			var dataValidade:Date = this.validade.selectedDate;
			if (dataValidade != null) { 
				prazo = (dataValidade.getTime() - dataHoraInicio.selectedDate.getTime()) / millisecondsPerDay;
			}
			return prazo;
		}
		
		[Deprecated]
		public function getEntidadeVigente():VigenteVO {
			return null;
		}
		
		public function isRegistroBloqueadoAlteracao():Boolean {
			return false;
		}
		
	}
}