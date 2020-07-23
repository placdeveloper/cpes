package br.com.sicoob.capes.cadastrarPessoa
{
	import flash.display.DisplayObject;
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.ObjectUtil;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.ParametrosAssistenteAtendimento;
	import br.com.bancoob.sisbr.ProcuraClientePlataforma;
	import br.com.bancoob.sisbr.componentes.PlataformaAtendimento.ITelaBasePlataformaAtendimento;
	import br.com.bancoob.sisbr.componentes.plataformas.BarraInferiorPlataformas;
	import br.com.bancoob.sisbr.componentes.pti.ObjetoEvent;
	import br.com.bancoob.sisbr.util.log.SisbrLogger;
	import br.com.bancoob.util.log.ILog;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.util.validadores.CNPJ;
	import br.com.bancoob.util.validadores.CPF;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastrarPessoa.abas.AbaCidadania;
	import br.com.sicoob.capes.cadastrarPessoa.abas.AbaContratoSocial;
	import br.com.sicoob.capes.cadastrarPessoa.abas.AbaPessoaFisica;
	import br.com.sicoob.capes.cadastrarPessoa.abas.AbaPessoaJuridica;
	import br.com.sicoob.capes.cadastrarPessoa.abas.AbaUploadGed;
	import br.com.sicoob.capes.comum.enums.SituacaoRegistroEnum;
	import br.com.sicoob.capes.comum.util.FormatadorUtil;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.VigenteVO;
	import br.com.sicoob.capes.comum.vo.entidades.PerfilCadastroVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaJuridicaVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoDocumentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoPessoaVO;
	import br.com.sicoob.capes.corporativo.componentes.plataformaatendimento.ProcuraClientePlataformaCAPES;
	import br.com.sicoob.capes.corporativo.componentes.validacaoCadastral.ValidacaoCadastral;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	import br.com.sicoob.capes.utils.ConversaoPessoaUtil;
	import br.com.sicoob.capes.utils.UploadDocGedUtil;
	import br.com.sicoob.capes.utils.autorizacao.DetalhamentoGedGft;
	import br.com.sicoob.capes.utils.plataformaatendimento.IEdicaoPlataformaAtendimentoCAPES;
	
	public class frmEditarPessoa extends frmEditarPessoaView 
			implements ITelaBasePlataformaAtendimento, IEdicaoPlataformaAtendimentoCAPES {
	
		public var registro:PessoaCompartilhamentoVO = null;
		public var registroAutorizacao:PessoaCompartilhamentoVO = null;
		private var registroBkp:PessoaCompartilhamentoVO = null;	
			
		private var abaPessoaFisica:AbaPessoaFisica = new AbaPessoaFisica();
		private var abaCidadanias:AbaCidadania = new AbaCidadania();
		private var abaPessoaJuridica:AbaPessoaJuridica = new AbaPessoaJuridica();
		private var abaContratoSocial: AbaContratoSocial = new AbaContratoSocial();
		private var abaUploadGed: AbaUploadGed = null;
//		private var abaPessoaGeral: AbaPessoaGeral = null;
		
		public static const CLASSE_SERVICO: String = 
			"br.com.sicoob.capes.cadastro.fachada.PessoaFachada";
		
		public static const OPERACAO_OBTER_DEFINICOES: String = "obterDefinicoesInclusao";
		public static const OPERACAO_OBTER_DADOS: String = "obterDados";
		public static const OPERACAO_EXCLUIR_DADOS: String = "excluirDados";
		public static const OPERACAO_ALTERAR_DADOS: String = "alterarDados";
		public static const OPERACAO_RENOVACAO: String = "renovarCadastro";
		public static const OPERACAO_VALIDAR_CADASTRO: String = "validarCadastro";
		private static const OPERACAO_ALTERAR_PERFIL_CADASTRO: String = "alterarPerfilCadastro";
		
		public var servicoDefinicoes:ServicoJava = ServicoJavaUtil.getServico(CadastroPessoa.CLASSE_SERVICO);
		public var servicoConsulta:ServicoJava = ServicoJavaUtil.getServico(CadastroPessoa.CLASSE_SERVICO);
		public var servicoConsultaCategoriaCadastro:ServicoJava = ServicoJavaUtil.getServico(CadastroPessoa.CLASSE_SERVICO);
		public var servicoEdicao:ServicoJava = ServicoJavaUtil.getServico(CadastroPessoa.CLASSE_SERVICO);
		public var servicoRenovacao:ServicoJava = ServicoJavaUtil.getServico(CadastroPessoa.CLASSE_SERVICO);
		public var servicoAlteracaoPerfilCadastro:ServicoJava = ServicoJavaUtil.getServico(CadastroPessoa.CLASSE_SERVICO);
		
		private var pessoaProxy:PessoaPlataformaVO = ProcuraClientePlataformaCAPES.getPessoaSelecionada();
		private var _isRegistroBloqueado:Boolean = false;
		private var isExibirVigente:Boolean = true;
		private var definicoesComponenteGED: ArrayCollection = null;

		public static const CLICK_ABAS:String = "clickAbas";
		
		private const ABA_DOCUMENTOS_PF:Number = 3;
		private const ABA_DOCUMENTOS_PJ:Number = 3;
		
		private static const TITULO_JANELA:String = "DETALHAMENTO GED / GFT";
		
		
		private var logger:ILog = SisbrLogger.getLogger("CAPES.frmEditarPessoa");
				
		//EVENTs
		public const VERIFICAR_BOTOES_AUTORIZACAO:String = "verificarBotoes";
		public const VERIFICAR_VALIDACAO_CADASTRAL:String = "verificarValidacaoCadastral";
		private var pilotoHabilitado: Boolean;
		private const DOCUMENTO_IDENTIFICACAO:Number = 163;
		private const DOCUMENTO_CONSTITUICAO:Number = 195;
		private const FISICA: Number = 0;
		private const JURIDICA: Number = 1;
		private var listaDocumetosGed: ArrayCollection = new ArrayCollection();
		
	    /**
	     *  Construtor.
	     */
		public function frmEditarPessoa()	{
			super();

			servicoConsulta.addEventListener(ResultEvent.RESULT, retornoCarregarRegistro);
			servicoConsultaCategoriaCadastro.addEventListener(ResultEvent.RESULT, retornoConsultaCategoriaCadastro);
			servicoDefinicoes.addEventListener(ResultEvent.RESULT, retornoCarregarDefinicoes);
			servicoEdicao.addEventListener(ResultEvent.RESULT, retornoSalvar);
			servicoRenovacao.addEventListener(ResultEvent.RESULT, retornoRenovarCadastro);
			servicoRenovacao.addEventListener(FaultEvent.FAULT, retornoRenovarCadastroErro);
			servicoAlteracaoPerfilCadastro.addEventListener(ResultEvent.RESULT, retornoAlteracaoPerfilCadastro);
			servicoAlteracaoPerfilCadastro.addEventListener(FaultEvent.FAULT, retornoAlteracaoPerfilCadastroErro);
			
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(evt:FlexEvent=null):void {
			navegacaoTab.creationPolicy = "all";
			navegacaoTab.addEventListener(Event.CHANGE, clickAbas);
			
			this.abaGeral.addEventListener(this.abaGeral.EVENTO_VALIDAR, verificarValidacaoCadastral);
			
			if(pessoaProxy.codTipoPessoa ==  FormatadorUtil.TIPO_PESSOA_FISICA) {
				navegacaoTab.addChild(abaPessoaFisica);
				navegacaoTab.addChild(abaCidadanias);
				abaUploadGed = new AbaUploadGed(FormatadorUtil.TIPO_PESSOA_FISICA);
			} else if(pessoaProxy.codTipoPessoa ==  FormatadorUtil.TIPO_PESSOA_JURIDICA) {
				navegacaoTab.addChild(abaPessoaJuridica);
				navegacaoTab.addChild(abaContratoSocial);
				abaUploadGed = new AbaUploadGed(FormatadorUtil.TIPO_PESSOA_JURIDICA);
			}
			abaUploadGed.label = "Documentos";
			navegacaoTab.addChild(abaUploadGed);
			
			for each(var aba: Object in navegacaoTab.getChildren()) {
				logger.info("init (configurando destinos): " + (this.destino == null ? "null" : 
													this.destino.destino + " - " + this.destino.endPoint));
				(aba as IAbaCadastroPessoa).configurarDestinosServicos(this.destino);
			}
			
			carregarDefinicoes();
			
			//Adiciona o evento ao trocar aba.			
			this.abaUploadGed.enabled = pessoaProxy.utilizaGedGft;
		}
		
		public override function dispose():void {
			super.dispose();
			
			if(abaUploadGed != null) {
				abaUploadGed.dispose();
			}
			
			if(navegacaoTab != null) {
				navegacaoTab.removeEventListener(Event.CHANGE, clickAbas);
				navegacaoTab.removeAllChildren();
				navegacaoTab = null;
			}
		}
		
		public function configurarDestinosServicos(destino:DestinoVO):void{
			
			logger.info("configurarDestinosServicos: " + (this.destino == null ? "null" : 
				this.destino.destino + " - " + this.destino.endPoint));
			
			this.destino = destino;
			servicoDefinicoes.configurarDestino(this.destino);
			servicoConsulta.configurarDestino(this.destino);
			servicoConsultaCategoriaCadastro.configurarDestino(this.destino);
			servicoEdicao.configurarDestino(this.destino);
			servicoRenovacao.configurarDestino(this.destino);
			servicoAlteracaoPerfilCadastro.configurarDestino(this.destino);
		}	

	    //--------------------------------------------------------------------------
	    //  Metodos auxiliares
	    //--------------------------------------------------------------------------
        private function clickAbas(evento:Event) : void{
			
			if ((pessoaProxy.codTipoPessoa ==  FormatadorUtil.TIPO_PESSOA_FISICA 
					&& this.navegacaoTab.selectedIndex == ABA_DOCUMENTOS_PF)
					|| (pessoaProxy.codTipoPessoa ==  FormatadorUtil.TIPO_PESSOA_JURIDICA 
						&& this.navegacaoTab.selectedIndex == ABA_DOCUMENTOS_PJ)) {
				
				//Obtem as chaves de negocio
				var listaChavesNegocio:ArrayCollection = criarListaChavesNegocio();
				
				//Adiciona as chaves de negocio a instancia do componente.
				this.abaUploadGed.chavesNegocio = listaChavesNegocio;
				
				//Atualiza as chaves de negocio dos arquivos que ainda nao foram enviados.
				this.abaUploadGed.atualizarChavesNegocio(listaChavesNegocio);
			}
			
    		this.dispatchEvent(new Event(CLICK_ABAS));	
        } 		
			
	    //--------------------------------------------------------------------------
	    //  Metodos das interfaces
	    //--------------------------------------------------------------------------
		public function trocarTelaEntrar(params:ParametrosAssistenteAtendimento=null):void{};
		public function trocarTelaSair(params:ParametrosAssistenteAtendimento=null):void{};
		
		public function gravarRegistro():void {
			atualizarCamposRegistro();
			executarSeValido(gravarDados);
		}
		
		private function gravarDados():void {
			
			//Verifica as chaves de negocio dos documentos do componente GED com  as chaves atuais da tela.
			if(!this.abaUploadGed.validarDocumentosNaoEnviados()) {
				Alerta.show(UploadDocGedUtil.VALIDACAO_DOCUMENTOS_NAO_ENVIADOS, "ERRO!", Alerta.ALERTA_ERRO);
				return;
			}
			
			//Verifica se ha algum documento que ja foi enviado ao ged e esta com as chaves de negocio diferentes das atuais.
			if(!this.abaUploadGed.verificarChavesNegocio(criarListaChavesNegocio())){
				Alerta.show(UploadDocGedUtil.VALIDACAO_DOCUMENTOS_DIVERGENTES, "ERRO!", Alerta.ALERTA_ERRO);
				return;
			}
			
			var pessoaCompartilhamento : PessoaCompartilhamentoVO = 
				ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(pessoaProxy);
			
			var tipoPessoa: Number = pessoaCompartilhamento.pessoa.tipoPessoa.codTipoPessoa;
			
			var existe:Boolean = existeDocumentoIdentificacao(tipoPessoa);
			
			if (!existe) {
				exibirMensagemErro(tipoPessoa);
				return;
			}
			
			if (pessoaProxy.utilizaGedGft && this.abaUploadGed.obterDocumentosComprobatorios().length <= 0) {
				Alerta.show(UploadDocGedUtil.VALIDACAO_DOCUMENTOS_NAO_SELECIONADOS, "ATENÇÃO", Alerta.ALERTA_PERGUNTA, null, verificarMarcacaoConsultaSBC);
			}
			
			else {
				verificarMarcacaoConsultaSBC();
			}
		}
		
		private function verificarMarcacaoConsultaSBC(evt:Event = null):void {
			if(!abaGeral.chkAutorizaBacen.selected){
				Alerta.show("A autorização para consulta no sistema de crédito do Banco Central está desmarcada. Deseja continuar?", "ATENÇÃO", Alerta.ALERTA_PERGUNTA, null, onConfirmaGravarRegistro);
			}else {
				onConfirmaGravarRegistro();
			}
		}
		
		private function onConfirmaGravarRegistro(evt:Event = null):void {
			MostraCursor.setBusyCursor("Processando...", Application.application);
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.pessoa = isExibirVigente ? registro : registroAutorizacao;
			isExibirVigente = true;
			servicoEdicao.getOperation(OPERACAO_ALTERAR_DADOS).send(dto);
		}
		
		public function atualizarCamposRegistro():void {
		
			for each(var aba: Object in navegacaoTab.getChildren()) {
				registro = (aba as IAbaCadastroPessoa).atualizarCamposRegistro(isExibirVigente ? registro : registroAutorizacao);
			}			
		}
		
		public function restaurarRegistro():void {
			logger.info("IDs pessoa - atual: " + registro.id + " bkp: "+ registroBkp.id);
			registro = (ObjectUtil.copy(registroBkp) as PessoaCompartilhamentoVO);
		}
		
		public function verificarAlteracoes():Boolean {

			var retorno:Boolean = true;
			if(registroBkp != null) {
				for each(var aba: Object in navegacaoTab.getChildren()) {
					retorno = (aba as IAbaCadastroPessoa).verificarAlteracoes(registroBkp);
					if(!retorno) {
						break;
					}
				}			
			}
			return retorno;
		}
		
		public function carregarDefinicoes(obj:Object=null):void{
			
			logger.info("carregarDefinicoes: " + (this.destino == null ? "null" : 
				this.destino.destino + " - " + this.destino.endPoint));
			
			MostraCursor.setBusyCursor("Obtendo definições ...", 
				Application.application, MostraCursor.CURSOR_PROGRESSO);
		
			var tipoPessoa: TipoPessoaVO = new TipoPessoaVO();
			tipoPessoa.codTipoPessoa = pessoaProxy.codTipoPessoa; 
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.tipoPessoa = tipoPessoa;
			dto.dados.pessoaCompartilhamento = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(ProcuraClientePlataformaCAPES.getPessoaSelecionada());
			servicoDefinicoes.getOperation(OPERACAO_OBTER_DEFINICOES).send(dto);
		}
		
		public function novoRegistro():void{
		}
		
		public function excluirRegistro(obj:Object):void {
			
			MostraCursor.setBusyCursor("Excluindo Registros!", 
					Application.application, MostraCursor.CURSOR_EXCLUIR);
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.pessoa = obj as PessoaVO;
			servicoDefinicoes.getOperation(OPERACAO_EXCLUIR_DADOS).send(dto);
		}	
		
		public function carregarRegistro(obj:Object):void{
			
			if(obj != null){
				_novo = false;
				MostraCursor.setBusyCursor("Carregando Registro ...", 
						Application.application, MostraCursor.CURSOR_PESQUISAR);
				
				var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
				dto.dados.idPessoaCompartilhamento = pessoaProxy.idCompartilhamento;
				servicoConsulta.getOperation(OPERACAO_OBTER_DADOS).send(dto);
			}
		}
		
		public function preencherCampos():void
		{
			for each(var aba: Object in navegacaoTab.getChildren()) {
				(aba as IAbaCadastroPessoa).preencherCampos(isExibirVigente ? registro : registroAutorizacao);
			}
			
			navegacaoTab.validateNow();
		}	
		
		//--------------------------------------------------------------------------
		// Metodos de callback
		//--------------------------------------------------------------------------
		private function retornoCarregarDefinicoes(event:ResultEvent): void {
			
			for each(var aba: Object in navegacaoTab.getChildren()) {
				if(aba is IAbaCadastroPessoa) {
					(aba as IAbaCadastroPessoa).retornoCarregarDefinicoes(event);
				}
			}
				
			carregarRegistro(pessoaProxy);
			
			definicoesComponenteGED = event.result.dados.definicoesComponenteGED;
			pilotoHabilitado = event.result.dados.pilotoHabilitado;
			
		}
		
		public function setarRegistroAutorizacao():void
		{		
			if(isExibirVigente){				
				isExibirVigente = false;
			}else{				
				isExibirVigente = true;
			}
			
			this.abaUploadGed.carregarDocumentos(UploadDocGedUtil.criarListaDocumentos(isExibirVigente ? 
				registro.documentosComprobatorios : registroAutorizacao.documentosComprobatorios));
		
			verificarBotoesAutorizacao(SituacaoRegistroEnum.VIGENTE, exibirBotaoDetalhamentoGedGft());
			
			preencherCampos();
		}
		
		public function exibirDetalhamentoGedGft():void{
			var popUpDetalhamentoGedGft:DetalhamentoGedGft = new DetalhamentoGedGft();
			popUpDetalhamentoGedGft.objeto = isExibirVigente ? registro : registroAutorizacao;
			popUpDetalhamentoGedGft.idTipoPessoaSelecionada = pessoaProxy.codTipoPessoa;
			
			var janela:Janela = new Janela();
			janela.title = TITULO_JANELA;
			janela.addChild(popUpDetalhamentoGedGft);
			janela.abrir(DisplayObject(Application.application), true);
		}
		
		private function retornoCarregarRegistro(evt:ResultEvent):void {
					
			registro = evt.result.dados["pessoa"];
			registroAutorizacao = evt.result.dados["pessoaAutorizacao"];
			
			registroBkp = ObjectUtil.copy(registro) as PessoaCompartilhamentoVO;
			_novo = false;			
			
			
			//Adiciona os documentos ao componente.
			this.abaUploadGed.carregarDocumentos(UploadDocGedUtil.criarListaDocumentos(isExibirVigente ? 
				registro.documentosComprobatorios : registroAutorizacao.documentosComprobatorios));
			
			//Verifica se o registro esta bloqueado para alteracao.
			_isRegistroBloqueado = evt.result.dados.isRegistroBloqueadoAlteracao;
			
			if(registro is PessoaJuridicaVO) {
				var pj:PessoaJuridicaVO = PessoaJuridicaVO(registro);
				//abaPessoaJuridica.onChangeCmbEsferaAdministrativa(null, pj.codigoEsferaAdministrativa);
			}
			
			this.dispatchEvent(new Event(Modulo.REGISTRO_CARREGADO));

			verificarBotoesAutorizacao(registroAutorizacao != null ? registroAutorizacao.situacaoAprovacao : SituacaoRegistroEnum.VIGENTE, exibirBotaoDetalhamentoGedGft());
			
			carregarDocumentosGed();
		}
		
		private function carregarDocumentosGed(): void {
			this.listaDocumetosGed = new ArrayCollection();
			if (!pilotoHabilitado || this.registro == null || this.registro.idRegistroControlado == null) {
				return;
			}
			
			UploadDocGedUtil.recuperarDocumentosGED(this.destino, this.registro.idRegistroControlado, 
				pessoaProxy.codTipoPessoa, retornoListaGed);
		}
		
		public function retornoListaGed(event:ResultEvent):void {
			this.listaDocumetosGed = event.result.dados.listaDocumentosGED as ArrayCollection;
		}
		private function retornoSalvar(evt:ResultEvent):void {
			
			registro = evt.result.dados["pessoa"];
			registroBkp = ObjectUtil.copy(registro) as PessoaCompartilhamentoVO;
			carregarRegistro(pessoaProxy);
			
			this.dispatchEvent(new Event(Modulo.REGISTRO_GRAVADO));
		}
		
		private function retornoExcluir(evt:ResultEvent):void {
			MostraCursor.removeBusyCursor();
			ProcuraClientePlataforma.reiniciarPesquisa();  
		}
			
		/*=============*/
		/*  GED / GFT  */
		/*=============*/
		
		private function verificarBotoesAutorizacao(situacao:SituacaoRegistroEnum, exibirBotaoGedGft:Boolean):void{
			var objeto:Object = new Object();
			objeto.situacao = situacao;
			objeto.exibir = exibirBotaoGedGft;
			
			var evento:Event = new ObjetoEvent(VERIFICAR_BOTOES_AUTORIZACAO, objeto);
			this.dispatchEvent(evento);
		}
		
		private function exibirBotaoDetalhamentoGedGft():Boolean {
			if(pessoaProxy.utilizaGedGft){
				if(isExibirVigente && registro != null && (SituacaoRegistroEnum.VIGENTE == registro.situacaoAprovacao || SituacaoRegistroEnum.BLOQUEADO == registro.situacaoAprovacao)){
					return true;
				}
			}
			return false;
		}
		
		/**
		 * Metodo para criar as lista de chave de negocio.
		 */
		private function criarListaChavesNegocio():ArrayCollection {
			var listaValoresChaves:ArrayCollection = new ArrayCollection();
			var objChaveDoc:Object = null;
			
			//Criar os valores das chaves na ordem que sao adicionados na fachada.
			var valoresChavesNegocio:ArrayCollection = criarValoresChavesNegocio();
			var definicaoGED:Object = null;

			if(definicoesComponenteGED!=null){
				for(var i:uint = 0; i < definicoesComponenteGED.length; i++){
					definicaoGED = definicoesComponenteGED[i];
					
					//Adiciona a chave da pessoa (PF/PJ)
					objChaveDoc = new Object();
					objChaveDoc.siglaTipoDocumento = definicaoGED.siglaTipoDocumento;
					objChaveDoc.siglaChaveDocumento = definicaoGED.obterCodigoTipoPessoaSelecionada;
					objChaveDoc.valorChave = pessoaProxy.cpfCnpj;
					listaValoresChaves.addItem(objChaveDoc);
					
					//Percorre a lista para preencher as outras chaves.
					for (var x:uint = 0; x < definicaoGED.chavesNegocio.length; x++){
						objChaveDoc = new Object();
						
						objChaveDoc.siglaTipoDocumento = definicaoGED.siglaTipoDocumento;
						objChaveDoc.siglaChaveDocumento = definicaoGED.chavesNegocio[x];
						objChaveDoc.valorChave = valoresChavesNegocio[x];
						listaValoresChaves.addItem(objChaveDoc);
					}
				}
			}
			
			
			return listaValoresChaves;
		}
		
		/**
		 * Metodo para adicionar os valores das chaves de negocio e um array,
		 * de acordo com o esperado pelas chaves de negocio.
		 */
		private function criarValoresChavesNegocio():ArrayCollection {
			var valoresChavesNegocio:ArrayCollection = new ArrayCollection();
			
			valoresChavesNegocio.addItem(pessoaProxy.codCompartilhamentoCadastro);
					
			if(pessoaProxy.codTipoPessoa ==  FormatadorUtil.TIPO_PESSOA_FISICA) {
				
				if(abaPessoaFisica.cboTipoDocumento.selectedItem != null
					&& !isNaN(abaPessoaFisica.cboTipoDocumento.selectedItem.codigo) 
					&& abaPessoaFisica.cboTipoDocumento.selectedItem.codigo != 0){
					valoresChavesNegocio.addItem(abaPessoaFisica.cboTipoDocumento.selectedItem.codigo);
				}else{
					valoresChavesNegocio.addItem(undefined);
				}
				
				if(abaPessoaFisica.txtNumeroDocumento != null && abaPessoaFisica.txtNumeroDocumento.text != ""){
					valoresChavesNegocio.addItem(abaPessoaFisica.txtNumeroDocumento.text);
				}else{
					valoresChavesNegocio.addItem(undefined);
				}
				
			} 			
			
			return valoresChavesNegocio;
		}
		
		public function isRegistroBloqueadoAlteracao():Boolean{
			return _isRegistroBloqueado; 
		}
		
		[Deprecated]
		public function getEntidadeVigente():VigenteVO {
			return null;
		}
		
		public function renovarCadastro():void {
			if (isRegistroBloqueadoAlteracao()) {
				Alerta.show("Não é possível fazer a renovação com registros pendentes de aprovação.","Aviso");
				return;
			}
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			
			dto.dados.pessoa = registro;
			servicoRenovacao.getOperation(OPERACAO_RENOVACAO).send(dto);
		}
		
		public function retornoRenovarCadastro(evt:ResultEvent):void {
			MostraCursor.removeBusyCursor();
			
			registro = evt.result.dados["pessoa"];
			registroBkp = ObjectUtil.copy(registro) as PessoaCompartilhamentoVO;
			_isRegistroBloqueado = evt.result.dados.isRegistroBloqueadoAlteracao;
			
			carregarRegistro(pessoaProxy);
			
			this.dispatchEvent(new Event(Modulo.REGISTRO_GRAVADO));
		}
		
		private function exibirComponenteValidacaoCadastral():void {
			var janelaValidacaoCadastral:Janela = new Janela();
			var validacaoCadastral:ValidacaoCadastral = new ValidacaoCadastral('R');
			
			janelaValidacaoCadastral.title = "VALIDAÇÃO CADASTRAL";
			janelaValidacaoCadastral.icone = "br/com/bancoob/imagens/icos/apply.png";
			janelaValidacaoCadastral.removeAllChildren();
			janelaValidacaoCadastral.addChild(DisplayObject(validacaoCadastral));
			this.parentDocument.addEventListener(BarraInferiorPlataformas.FECHAR_PLATAFORMA, validacaoCadastral.fechar);
			this.parentDocument.addEventListener(BarraInferiorPlataformas.VOLTAR_SISBR, validacaoCadastral.fechar);
			janelaValidacaoCadastral.abrir(DisplayObject(Application.application), false, true);
		}
		
		public function retornoRenovarCadastroErro(evt:FaultEvent):void {
			exibirComponenteValidacaoCadastral();
			MostraCursor.removeBusyCursor();
		}
		
		/**
		 * Retorno do metodo  verificarValidacaoCadastral
		 * @param evt
		 * 
		 */
		public function retornoConsultaCategoriaCadastro(evt:ResultEvent):void {
			var retorno:Boolean = false;
			retorno = evt.result.dados.isPossuiRegraCadastralRestritiva;
			if(retorno){
				Alerta.show(this.abaGeral.MENSAGEM_DE_AVANCO_CBOPERFILCADASTRO,"Aviso",Alerta.ALERTA_OK,null,dispararEventoValidacaoCadastral);
				this.abaGeral.voltarComboPerfilCadastro();
			} else {
				//TODO: gravar mudanca perfil cadastro.
				// Verificar com o isaac.pessoa.
				MostraCursor.setBusyCursor("Alterando o perfil cadastro...", Application.application, MostraCursor.CURSOR_PROGRESSO);
				
				var perfilCadastro:PerfilCadastroVO = abaGeral.cboPerfilCadastro.selectedItem as PerfilCadastroVO;
				var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
				var pessoa:PessoaCompartilhamentoVO = registro as PessoaCompartilhamentoVO;
				pessoa.perfilCadastro = perfilCadastro;
				dto.dados.pessoa = registro as PessoaCompartilhamentoVO;
				servicoAlteracaoPerfilCadastro.getOperation(OPERACAO_ALTERAR_PERFIL_CADASTRO).send(dto);
			}
		}
		
		/**
		 * Verificar se ha pendencias de acordo com as regras
		 *  de validacao e categoria/perfil cadastro
		 * @param evt
		 * 
		 */
		public function verificarValidacaoCadastral (evt:Event) :void {
			// Verificar, pesquisar se pode ser mudado
			MostraCursor.setBusyCursor("Verificando pendências cadastrais ...", 
			Application.application, MostraCursor.CURSOR_PESQUISAR);
			
			var dto:RequisicaoReqDTO 				= new RequisicaoReqDTO();
			dto.dados.idPessoa 						= pessoaProxy.idPessoa;
			dto.dados.ordem 						= (this.abaGeral.cboPerfilCadastro.selectedItem as PerfilCadastroVO).ordem;
			dto.dados.idInstituicao 				= pessoaProxy.idInstituicao;
			dto.dados.idPessoaCompartilhamento 		= pessoaProxy.idCompartilhamento;
			
			servicoConsultaCategoriaCadastro.getOperation(OPERACAO_VALIDAR_CADASTRO).send(dto);
		}
		
		/**
		 * Abre a janela de pendencias de validacao cadastral 
		 * @param evento
		 * 
		 */
		public function dispararEventoValidacaoCadastral(evento:Event = null):void {
			//this.dispatchEvent(new Event(this.VERIFICAR_VALIDACAO_CADASTRAL));
			
			var janelaValidacaoCadastral:Janela = new Janela();
			var validacaoCadastral:ValidacaoCadastral = new ValidacaoCadastral('R', false,false,false);
			
			janelaValidacaoCadastral.title = "VALIDAÇÃO CADASTRAL";
			janelaValidacaoCadastral.icone = "br/com/bancoob/imagens/icos/apply.png";
			janelaValidacaoCadastral.removeAllChildren();
			janelaValidacaoCadastral.addChild(DisplayObject(validacaoCadastral));
			
			this.parentDocument.addEventListener(BarraInferiorPlataformas.FECHAR_PLATAFORMA, validacaoCadastral.fechar);
			this.parentDocument.addEventListener(BarraInferiorPlataformas.VOLTAR_SISBR, validacaoCadastral.fechar);
			janelaValidacaoCadastral.abrir(DisplayObject(Application.application), false, true);
		}
		
		public function retornoAlteracaoPerfilCadastro(evt:ResultEvent):void {
			registro = evt.result.dados["pessoa"];
			registroBkp = ObjectUtil.copy(registro) as PessoaCompartilhamentoVO;
			_isRegistroBloqueado = evt.result.dados.isRegistroBloqueadoAlteracao;
			carregarRegistro(pessoaProxy);
			this.dispatchEvent(new Event(Modulo.REGISTRO_GRAVADO));
			MostraCursor.removeBusyCursor();
		}
		
		public function retornoAlteracaoPerfilCadastroErro(evt:FaultEvent):void {
			MostraCursor.removeBusyCursor();
		}
		
		private function existeDocumentoIdentificacao(tipoPessoa: Number): Boolean {
			
			if(!pilotoHabilitado || pilotoHabilitado && !pessoaProxy.utilizaGedGft) {
				return true;
			}
			
			var existe:Boolean = false; 
			var listaDocGedContemObrigatorio:Boolean = false;
			
			if (this.listaDocumetosGed.length > 0 && abaUploadGed.obterTipoDocumentos().length > 0) {
				var descDocumento: Number = DOCUMENTO_IDENTIFICACAO;
				
				if (tipoPessoa == JURIDICA) {
					descDocumento = DOCUMENTO_CONSTITUICAO;
				}
				
				for (var i:Number = 0; i < this.listaDocumetosGed.length; i++) {
					listaDocGedContemObrigatorio = (descDocumento == this.listaDocumetosGed[i]);
					if (listaDocGedContemObrigatorio) {
						break;
					} 
				}
				
				if (listaDocGedContemObrigatorio) {
					return true;
				} else {
					existe = existeDocumentosObrigatorios(tipoPessoa);
				}
				return existe;
				
			} else {
				if (this.listaDocumetosGed.length > 0) {
					return true;
				}
				existe = existeDocumentosObrigatorios(tipoPessoa);
			}
			return existe;
		}
		private function existeDocumentosObrigatorios(tipoPessoa: Number):Boolean {
			var existe:Boolean = false; 
			
			for each(var tipoDocumento: Object in abaUploadGed.obterTipoDocumentos()) {
				var documento:TipoDocumentoVO = tipoDocumento as TipoDocumentoVO;
				
				if (tipoPessoa == FISICA && documento.idTipoDocumento == DOCUMENTO_IDENTIFICACAO
					|| tipoPessoa == JURIDICA && documento.idTipoDocumento == DOCUMENTO_CONSTITUICAO) {
					existe = true;
					break;
				}
			}
			return existe;
		}
		
		private function exibirMensagemErro(tipoPessoa: Number):void {
			var mensagem: String = " documento de identificação.";
			if (tipoPessoa == JURIDICA) {
				mensagem = " documento de constituição.";
			}
			Alerta.show(UploadDocGedUtil.VALIDACAO_DOCUMENTOS_OBRIGATORIOS + mensagem, "ERRO!", Alerta.ALERTA_ERRO);
		}
	}
}