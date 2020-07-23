package br.com.sicoob.capes.cadastrarContato
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.formatters.DateFormatter;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.ObjectUtil;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.ParametrosAssistenteAtendimento;
	import br.com.bancoob.sisbr.eventos.EventAssistenteAtendimento;
	import br.com.bancoob.util.StringUtils;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.VigenteVO;
	import br.com.sicoob.capes.comum.vo.entidades.TelefoneVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoTelefoneVO;
	import br.com.sicoob.capes.corporativo.componentes.plataformaatendimento.ProcuraClientePlataformaCAPES;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	import br.com.sicoob.capes.utils.ConversaoPessoaUtil;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCAPESBase;
	
	
	public class frmEditarTelefone extends frmEditarTelefoneView implements IEdicaoContato {
		
		static public const CLASSE_SERVICO: String = 
			"br.com.sicoob.capes.cadastro.fachada.TelefoneFachada";
		
		public var servicoEdicao:ServicoJava = ServicoJavaUtil.getServico(CLASSE_SERVICO);
		public var servicoConsulta:ServicoJava = ServicoJavaUtil.getServico(CLASSE_SERVICO);
		public var servicoDefinicao:ServicoJava = ServicoJavaUtil.getServico(CLASSE_SERVICO);
		public var servicoExcluir:ServicoJava = ServicoJavaUtil.getServico(CLASSE_SERVICO);
		
		[Bindable]
		private var registro:TelefoneVO = new TelefoneVO();
		private var registroBkp:TelefoneVO = new TelefoneVO();
		private var pessoaSelecionada:PessoaPlataformaVO;
		
		/**
		 *  Construtor.
		 */
		public function frmEditarTelefone(){
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
			this.cmbTipoTelefone.dataProvider = new ArrayCollection();	
			
			cmbTipoTelefone.addEventListener(Event.CHANGE, verificarDDD);
			
			carregarDefinicoes();		
		}
		
		public function configurarDestinosServicos(destinoVO:DestinoVO):void{
			servicoDefinicao.configurarDestino(destinoVO);
			servicoEdicao.configurarDestino(destinoVO);
			servicoConsulta.configurarDestino(destinoVO);
			servicoExcluir.configurarDestino(destinoVO);
		}	
		
		public function carregarDefinicoes(obj:Object=null):void {
			MostraCursor.setBusyCursor("Obtendo definições ...", 
				Application.application, MostraCursor.CURSOR_PROGRESSO);
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.idTipoPessoa = pessoaSelecionada.codTipoPessoa;
			servicoDefinicao.getOperation(ContatoPessoaSelecao.OPERACAO_OBTER_DEFINICOES).send(dto);
		}
		
		public function carregarRegistro(obj:Object): void {
			
			MostraCursor.setBusyCursor("Obtendo definições ...", 
				Application.application, MostraCursor.CURSOR_PROGRESSO);
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.telefone = obj;
			servicoConsulta.getOperation(ContatoPessoaSelecao.OPERACAO_OBTER).send(dto);
		}
		
		public function excluirRegistro(telefone:Object):void {
			MostraCursor.setBusyCursor("Excluindo Registros!", Application.application, 
				MostraCursor.CURSOR_EXCLUIR);
			
			var vo:TelefoneVO = new TelefoneVO();
			vo.idTelefonePessoa = telefone["idTelefonePessoa"];
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.telefone = telefone;
			dto.dados.pessoa = TelaPlataformaAtendimentoCAPESBase.getPessoaSelecionada();
			
			servicoExcluir.getOperation(ContatoPessoaSelecao.OPERACAO_EXCLUIR).send(dto);
		}
		
		private function convertDataToString(data: Date):String{
			var fmt:DateFormatter = new DateFormatter();
			fmt.formatString = "DD/MM/YYYY";
			return fmt.format(data);
		}
		
		public function preencherCampos():void {
			
			var itemDdd: Object = null;
			if(registroBkp != null && registroBkp.ddd != null) {
				itemDdd = registroBkp.ddd;
			}
			
			var itemTipoTelefone: Object = null;
			if(registroBkp != null && registroBkp.tipoTelefone != null) {
				itemTipoTelefone = registroBkp.tipoTelefone.codigo;
				alteraObrigatoreadadeCmbDDD(registroBkp.tipoTelefone.dddObrigatorio.valor);
			}
			
			cmbTipoTelefone.procuraItemPorNome(itemTipoTelefone, "codigo");
			cmbDdd.procuraItemPorNome(itemDdd, "label");
			dataHoraInicio.text = registroBkp.dataHoraInicio != null ? convertDataToString(registroBkp.dataHoraInicio.data) : null;
			usuarioAlteracao.text = registroBkp.idUsuarioAprovacao;
			
			ramal.text = registroBkp.ramal;
			numero.text = registroBkp.telefone;
			observacao.text = registroBkp.observacao;
		}
		
		private function habilitaCamposExtras(bol: Boolean):void{
			rotuloDataHoraInicio.visible = rotuloDataHoraInicio.includeInLayout = !bol;
			rotuloUsuarioAlteracao.visible = rotuloUsuarioAlteracao.includeInLayout = !bol;
			dataHoraInicio.visible = dataHoraInicio.includeInLayout = !bol;
			usuarioAlteracao.visible = usuarioAlteracao.includeInLayout = !bol;
		}
		
		public function novoRegistro():void {
			_novo = true;
			registro = new TelefoneVO();
			registroBkp = new TelefoneVO();
			
			habilitaCamposExtras(_novo);
			preencherCampos();
		}
		
		public function gravarRegistro():void {
			
			atualizarCamposRegistro();
			if(verificarQuantidadeDigitos()){
				executarSeValido(verificaNumeroRepetido);
			}
		}
		
		public function atualizarCamposRegistro():void{
			
			if (_novo) {
				registro = new TelefoneVO();
			}
			
			registro.tipoTelefone = cmbTipoTelefone.selectedItem as TipoTelefoneVO;
			
			var ddd:String = null;
			if(cmbDdd.enabled){
				if (cmbDdd.selectedItem != null) { 
					ddd = cmbDdd.selectedLabel;
				}
			}
			
			registro.ddd = ddd;
			registro.telefone = numero.text;
			registro.ramal = ramal.text; 
			registro.observacao = StringUtils.trim(observacao.text);
			registro.pessoaCompartilhamento = TelaPlataformaAtendimentoCAPESBase.getPessoaSelecionada();
		}
		
		//--------------------------------------------------------------------------
		//  Método utilizado na gravação do registro, verifica números do tipo
		//  99999999/88888888... Caso se enquadre nessa regra, ele pergunta ao
		//  usuário se ele deseja inseri-lo mesmo assim.
		//--------------------------------------------------------------------------
		private function verificaNumeroRepetido(): void{
			var arrayChars:Array = registro.telefone.split("");
			var i:Number;
			for(i = 0; i < arrayChars.length; i++){
				if(arrayChars[i] != arrayChars[i+1]){
					break;
				}
			}
			if(i == arrayChars.length -1){
				Alerta.show("O telefone pode ser inválido. Deseja cadastrá-lo?", "Confirmação", Alerta.ALERTA_PERGUNTA, 
					null, gravarDados);	
			}else{
				executarSeValido(gravarDados);
			}
		}
		
		private function verificarQuantidadeDigitos(): Boolean {
			if (registro == null || registro.tipoTelefone == null) {
				return true;
			}
			
			if(registro.telefone.length < registro.tipoTelefone.numMinDigitos){
				Alerta.show("O tipo de telefone "+registro.tipoTelefone.descricao+" deve conter no mínimo "+registro.tipoTelefone.numMinDigitos.toString()+" dígitos.", "Aviso", Alerta.ALERTA_INFORMACAO);
				return false;
			}
			return true;
		}
		
		//--------------------------------------------------------------------------
		//  Métodos de auxiliares.
		//--------------------------------------------------------------------------	
		private function gravarDados(event:MouseEvent = null): void {
			
			var dto: RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.telefone = registro;
			dto.dados.pessoa = TelaPlataformaAtendimentoCAPESBase.getPessoaSelecionada();

			MostraCursor.setBusyCursor("Gravando Registro!", Application.application, MostraCursor.CURSOR_GRAVAR);
			if (_novo) {
				servicoEdicao.getOperation(ContatoPessoaSelecao.OPERACAO_INCLUIR).send(dto);	
			} else {
				servicoEdicao.getOperation(ContatoPessoaSelecao.OPERACAO_ALTERAR).send(dto);	
			}
		}
		
		public function restaurarRegistro():void{
			registro = (ObjectUtil.copy(registroBkp) as TelefoneVO);
		}
		
		public function verificarAlteracoes():Boolean {
			return cmbTipoTelefone.selectedItem.codigo == registroBkp.tipoTelefone.codigo
				&& (cmbDdd.selectedItem != null ? cmbDdd.selectedItem.label : null)  == registroBkp.ddd 
				&& ramal.text == registroBkp.ramal
				&& numero.text == registroBkp.telefone
				&& observacao.text == registroBkp.observacao; 
		}
		
		//--------------------------------------------------------------------------
		//  Métodos de callback.
		//--------------------------------------------------------------------------		    			
		private function retornoObterDefinicoes(event: ResultEvent): void {
			MostraCursor.removeBusyCursor();
			if(event.result.dados.tiposTelefone != null) {
				cmbTipoTelefone.dataProvider = event.result.dados.tiposTelefone;	
				cmbTipoTelefone.validateNow();
			}
			preencherCampos();
		}
		
		private function retornoCarregarRegistro(evt:ResultEvent):void {
			registro = evt.result.dados.telefone;
			registroBkp = ObjectUtil.copy(registro) as TelefoneVO;		
			
			_novo = false;
			
			habilitaCamposExtras(_novo);
			preencherCampos();
			this.dispatchEvent(new Event(REGISTRO_CARREGADO));	
		}
		
		private function retornoEdicao(evt: ResultEvent): void {
			
			registro = evt.result.dados.telefone; 
			MostraCursor.removeBusyCursor();
			this.dispatchEvent(new EventAssistenteAtendimento(EventAssistenteAtendimento.EVENTO_CONFIRMAR_MUDANCA_TELA));
			this.dispatchEvent(new Event(Modulo.REGISTRO_GRAVADO));	
			//work around para remover o foco do botão salvar, 
			//fazia com que ao apertar enter depois de salvar o registro ele era incluido novamente.
			numero.setFocus();
		}				
		
		public function retornoExcluir(evt:ResultEvent):void {
			
			registro = evt.result.dados.telefone;
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
		
		private function verificarDDD(evento:Event = null): void {
			if(cmbTipoTelefone.selectedItem != null){
				var tipoTelefone:TipoTelefoneVO = cmbTipoTelefone.selectedItem as TipoTelefoneVO;
				alteraObrigatoreadadeCmbDDD(tipoTelefone.dddObrigatorio.valor);
			}else {
				alteraObrigatoreadadeCmbDDD(true);
			}
		}
		
		private function alteraObrigatoreadadeCmbDDD(valor:Boolean):void{
			cmbDdd.validarObrigatorio = valor;
			cmbDdd.validateNow();
		}
		
	}
	
}