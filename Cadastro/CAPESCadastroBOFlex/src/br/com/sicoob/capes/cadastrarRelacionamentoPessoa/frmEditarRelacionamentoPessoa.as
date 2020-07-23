package br.com.sicoob.capes.cadastrarRelacionamentoPessoa {
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	import mx.collections.IList;
	import mx.collections.ListCollectionView;
	import mx.events.FlexEvent;
	import mx.events.IndexChangedEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.StringUtil;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.input.Combo;
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.util.DateUtils;
	import br.com.bancoob.util.FormataData;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.VigenteVO;
	import br.com.sicoob.capes.comum.vo.entidades.DadosRegistroRelacionamentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaVO;
	import br.com.sicoob.capes.comum.vo.entidades.RegistroRelacionamentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.RelacionamentoPessoaVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoPoderRelacionamentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoRelacionamentoPessoaVO;
	import br.com.sicoob.capes.comum.vo.entidades.interfaces.Aprovavel;
	import br.com.sicoob.capes.corporativo.componentes.plataformaatendimento.ProcuraClientePlataformaCAPES;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	import br.com.sicoob.capes.utils.ConversaoPessoaUtil;
	import br.com.sicoob.capes.utils.UploadDocGedUtil;
	import br.com.sicoob.capes.utils.plataformaatendimento.IEdicaoPlataformaAtendimentoCAPES;
	
	public class frmEditarRelacionamentoPessoa extends frmEditarRelacionamentoPessoaView 
		implements IEdicaoPlataformaAtendimentoCAPES {
		
		private static const CLASSE_SERVICO : String = 
			"br.com.sicoob.capes.cadastro.fachada.RelacionamentoPessoaFachada";
		private static const OPERACAO_OBTER_DEFINICOES : String = "obterDefinicoes";
		private static const OPERACAO_OBTER_DADOS : String = "obterDados";
		private static const OPERACAO_OBTER_TIPOS_RELACIONAMENTO : String = "obterTiposRelacionamento";
		private static const OPERACAO_VALIDAR_PESSOA_RELACIONADA : String = "validarPessoaRelacionada";
		private static const OPERACAO_INCLUIR_DADOS : String = "incluirDados";
		private static const OPERACAO_ALTERAR_DADOS : String = "alterarDados";
		private static const OPERACAO_EXCLUIR_DADOS : String = "excluirDados";
		
		private const ABA_DOCUMENTOS:Number = 1;
		
		private var _servicoConsulta : ServicoJava;
		private var _servicoDefinicoes : ServicoJava;
		private var _servicoSalvamento : ServicoJava; 
		private var _servicoExclusao : ServicoJava;
		private var _servicoTipoRelacionamento: ServicoJava;
		private var _servicoPendenciaPessoaRelacionada: ServicoJava;
		private var relacionamento : RelacionamentoPessoaVO;
		
		private var dataAtualMovimentoCCS : Date;
		private var tiposRelacionamento : ListCollectionView = new ArrayCollection();
		private var _produtosBancoob : Boolean;
		private var definicoesComponenteGED: Object = null;
		private var _modoConsulta : Boolean = false;
		private var _isRegistroBloqueado:Boolean = false;
		
		private var pessoaProxy : PessoaPlataformaVO = ProcuraClientePlataformaCAPES.getPessoaSelecionada();
		private var CONJUGE:Number = 2;
		private var pilotoHabilitado: Boolean;
		private const PESSOA_JURIDICA: Number = 1;
		private const PROCURADOR: Number = 5;
		private var listaDocumetosGed: ArrayCollection = new ArrayCollection();
		
		public function frmEditarRelacionamentoPessoa() {
			super();
			_servicoConsulta = ServicoJavaUtil.getServico(CLASSE_SERVICO, "Obtendo dados...", 
				ResultEvent.RESULT, onDadosRecuperados);
			_servicoDefinicoes = ServicoJavaUtil.getServico(CLASSE_SERVICO, "Obtendo definições...", 
				ResultEvent.RESULT, onDefinicoesRecuperadas);
			_servicoDefinicoes.addEventListener(FaultEvent.FAULT, onFalhaRecuperarDefinicoes);
			_servicoExclusao = ServicoJavaUtil.getServico(CLASSE_SERVICO, "Excluindo Registro...", 
				ResultEvent.RESULT, onRegistroExcluido);
			_servicoSalvamento = ServicoJavaUtil.getServico(CLASSE_SERVICO, "Gravando dados ...", 
				ResultEvent.RESULT, onRegistroSalvo);
			_servicoTipoRelacionamento = ServicoJavaUtil.getServico(CLASSE_SERVICO, 
				"Atualizando os tipos de relacionamento...", 
				ResultEvent.RESULT, onTiposRelacionamentosRecuperados);
			_servicoPendenciaPessoaRelacionada = ServicoJavaUtil.getServico(CLASSE_SERVICO, 
				"Verificando Pendências ...");
			
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function onChangeTabNavegacao(evento : IndexChangedEvent) : void {
			if (this.navegacao.selectedIndex == ABA_DOCUMENTOS) {
				
				//Obtém as chaves de negócio
				var listaChavesNegocio:ArrayCollection = criarListaChavesNegocio();
				
				//Adiciona as chaves de negócio à instância do componente.
				this.abaDocumentos.chavesNegocio = listaChavesNegocio;
				
				//Atualiza as chaves de negócio dos arquivos que ainda não foram enviados.
				this.abaDocumentos.atualizarChavesNegocio(listaChavesNegocio);
			}
		}
		
		private function setaIsRelacionamentoComp(tipoRelPessoa:TipoRelacionamentoPessoaVO):void{
			if(tipoRelPessoa != null ){
				if(tipoRelPessoa.permiteCompartilhamento.valor){
					txtCompartilhado.text = "Relacionamento compartilhado: Sim";
				}else{
					txtCompartilhado.text = "Relacionamento compartilhado: Não";
				}
			}else{
				txtCompartilhado.text = "";
			}
		}
		
		public function configurarDestinosServicos(destinoVO : DestinoVO):void{
			this.destino = destinoVO;
			servicoConsulta.configurarDestino(destinoVO);
			servicoDefinicoes.configurarDestino(destinoVO);
			servicoExclusao.configurarDestino(destinoVO);
			servicoSalvamento.configurarDestino(destinoVO);
			servicoTipoRelacionamento.configurarDestino(destinoVO);
			servicoPendenciaPessoaRelacionada.configurarDestino(destinoVO);
		}	
		
		public function configurarCampos(evento : ResultEvent = null) : void {
			if (!modoConsulta) {
				this.dtDataCadastro.enabled = false;
				this.procurarPessoa.enabled = _novo;
				this.cmbTipoRelacionamento.enabled = _novo;
				onChangeCmbTipoRelacionamento();
				toggleDataFimMandato();
			}
		}
		
		private function limparFormIncluir(evento : ResultEvent) : void {
			var dataAtual : Date = new Date();
			this.chkIndeterminado.selected = true;
			this.cmbTipoRelacionamento.selectedItem = null;
			this.dtDataCadastro.selectedDate = dataAtual;
			this.dtDataInicioRelacionamento.text = "";
			this.dtDataFimMandato.selectedDate = null;
			this.dtDataInicioMandato.selectedDate = null;
			this.listaPoderes.carregarListagem(evento, true);
			this.nomeCartorio.text = "";
			this.numeroFolha.text = "";
			this.numeroLivro.text = "";
			this.numeroRegistro.text = "";
			this.percentualCapitalEmpresa.text = "";
			this.procurarPessoa.limpar();
			this.abaDocumentos.limparDocumentos();
			this.txtCompartilhado.text = "";
		}
		
		private function criarRelacionamento() : RelacionamentoPessoaVO {	
			var relacionamento : RelacionamentoPessoaVO = new RelacionamentoPessoaVO();
			var tipo : TipoRelacionamentoPessoaVO = TipoRelacionamentoPessoaVO(
				this.cmbTipoRelacionamento.selectedItem);
			if (tipo != null && tipo.habilitaDadosRegistro != null && tipo.habilitaDadosRegistro.valor) {
				var dados : DadosRegistroRelacionamentoVO = new DadosRegistroRelacionamentoVO();
				dados.nomeCartorio = StringUtil.trim(this.nomeCartorio.text);
				dados.numeroFolha = StringUtil.trim(this.numeroFolha.text);
				dados.numeroLivro = StringUtil.trim(this.numeroLivro.text);
				dados.numeroRegistro = StringUtil.trim(this.numeroRegistro.text);
				relacionamento = new RegistroRelacionamentoVO();
				RegistroRelacionamentoVO(relacionamento).dadosRegistro = dados;
				
				relacionamento.dataFimMandato = this.chkIndeterminado.selected ? null : DateTimeBase.getDateTimeEntity(this.dtDataFimMandato.selectedDate);
				relacionamento.dataInicioMandato = DateTimeBase.getDateTimeEntity(this.dtDataInicioMandato.selectedDate);
			}
			relacionamento.id = this.relacionamento.id;
			relacionamento.idInstituicaoAtualizacao = this.relacionamento.idInstituicaoAtualizacao;
			relacionamento.dataHoraInicio = this.relacionamento.dataHoraInicio;
			relacionamento.pessoa = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(
				pessoaProxy).pessoa;
			relacionamento.pessoaCompartilhamento = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(
				pessoaProxy).pessoaCompartilhamento;
			relacionamento.idInstituicao = this.relacionamento.idInstituicao;
			relacionamento.pessoaRelacionada = ConversaoPessoaUtil
				.converterVOParaPessoaCompartilhamento(PessoaPlataformaVO(
					this.procurarPessoa.registro)).pessoa;
			relacionamento.tipoRelacionamento = tipo;
			relacionamento.percentualCapitalEmpresa = this.percentualCapitalEmpresa.valor;
			//relacionamento.codigoSituacaoAprovacao = this.relacionamento.codigoSituacaoAprovacao;
			
			if(tipo.codigo != CONJUGE) {
				relacionamento.dataFimMandato = this.chkIndeterminado.selected ? null :
					DateTimeBase.getDateTimeEntity(this.dtDataFimMandato.selectedDate);
				relacionamento.dataInicioMandato = DateTimeBase.getDateTimeEntity(this.dtDataInicioMandato.selectedDate);
			}
			
			if (!_novo) {
				relacionamento.dataInicioRelacionamento = DateTimeBase.getDateTimeEntity(DateUtils.stringToDate(this.dtDataInicioRelacionamento.text, "DD/MM/YYYY"));
			}
			relacionamento.relacionamentoReverso = this.relacionamento.relacionamentoReverso;
			var poderesSelecionados : ArrayCollection = this.listaPoderes.recuperarItensSelecionados();
			if (tipo.habilitaPoderes.valor && (poderesSelecionados.length > 0)) {
				relacionamento.poderes = poderesSelecionados;
			}
			return relacionamento;
		}
		
		private function obterDTO(relacionamento : RelacionamentoPessoaVO = null) : RequisicaoReqDTO {
			if (relacionamento == null) {
				relacionamento = criarRelacionamento();
			} 
			relacionamento.documentosComprobatorios = this.abaDocumentos.obterDocumentosComprobatorios();
			var dto : RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.relacionamentoPessoa = relacionamento;
			dto.dados.produtosBancoob = this.produtosBancoob;
			
			return dto;
		}
		
		private function salvar() : void {
			var dto : RequisicaoReqDTO = obterDTO();
			
			if (_novo) {
				servicoSalvamento.getOperation(OPERACAO_INCLUIR_DADOS).send(dto);
			} else {
				servicoSalvamento.getOperation(OPERACAO_ALTERAR_DADOS).send(dto);
			}
		}
		
		private function carregarTiposRelacionamento(pessoaRelacionada : PessoaVO = null) : void {
			var dto : RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.pessoa = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(
				pessoaProxy).pessoa;
			dto.dados.pessoaRelacionada = pessoaRelacionada;
			dto.dados.produtosBancoob = _novo != false ? this.produtosBancoob : _novo;
			servicoTipoRelacionamento.getOperation(OPERACAO_OBTER_TIPOS_RELACIONAMENTO).send(dto);
		}
		
		//-----------
		// Listeners
		//-----------
		private function init(evento : FlexEvent) : void {
			
			var pessoaCompartilhamento : PessoaCompartilhamentoVO = ConversaoPessoaUtil
				.converterVOParaPessoaCompartilhamento(pessoaProxy);
			
			this.cmbTipoRelacionamento.addEventListener(ListEvent.CHANGE, onChangeCmbTipoRelacionamento)
			this.chkIndeterminado.addEventListener(Event.CHANGE, onChangeChkIndeterminado);
			this.procurarPessoa.possuiRelacionamentoBancoob = produtosBancoob;
			this.procurarPessoa.addEventListener("itemSelecionado", onPessoaRelacionadaSelecionada);
			this.procurarPessoa.txtCodigo.addEventListener(Event.CHANGE, onChangePessoaRelacionada);
			this.validacoesAdicionais.addItem(new ValidadorTipoRelacionamento(
				this.cmbTipoRelacionamento,	this.procurarPessoa, 
				pessoaCompartilhamento.pessoa.tipoPessoa.codTipoPessoa));
			this.navegacao.addEventListener(IndexChangedEvent.CHANGE, onChangeTabNavegacao);
			this.abaDocumentos.enabled = pessoaProxy.utilizaGedGft;
		}
		
		public override function dispose():void {
			super.dispose();
			abaDocumentos.dispose();
			navegacao.removeEventListener(Event.CHANGE, onChangeTabNavegacao);
			navegacao.removeAllChildren();
			navegacao = null;
		}
		
		private function onDadosRecuperados(evento : ResultEvent) : void {
			
			this.relacionamento = RelacionamentoPessoaVO(evento.result.dados.relacionamentoPessoa);
			
			if(relacionamento != null) {
				//Verifica se o registro está bloqueado para alteração.
				_isRegistroBloqueado = evento.result.dados.isRegistroBloqueadoAlteracao;
				
				//Adiciona os documentos do relacionamento ao componente.
				this.abaDocumentos.carregarDocumentos(UploadDocGedUtil.criarListaDocumentos(relacionamento.documentosComprobatorios));
				
				dispatchEvent(new Event(Modulo.REGISTRO_CARREGADO));
			}
		}
		
		
		
		private function onDefinicoesRecuperadas(evento : ResultEvent) : void {
			this.tiposRelacionamento = evento.result.dados.tiposRelacionamento;
			this.dataAtualMovimentoCCS = IDateTime(evento.result.dados.dataInicioRelacionamento).data;
			pilotoHabilitado = evento.result.dados.pilotoHabilitado;
			
			atualizarCombo(this.cmbTipoRelacionamento, this.tiposRelacionamento);
			if (_novo) {
				limparFormIncluir(evento);
			} else {
				preencherFormEdicao(evento);
				selecionarTipoRelacionamento();
			}
			definicoesComponenteGED = evento.result.dados.definicoesComponenteGED;
			configurarCampos(evento);
			carregarDocumentosGed();
			
		}
		
		private function carregarDocumentosGed(): void {
			this.listaDocumetosGed = new ArrayCollection();
			if (!pilotoHabilitado || this.relacionamento == null || this.relacionamento.idRegistroControlado == null) {
				return;
			}
			UploadDocGedUtil.recuperarDocumentosGED(this.destino, this.relacionamento.idRegistroControlado, 
				pessoaProxy.codTipoPessoa, retornoListaGed);
		}
		
		public function retornoListaGed(event:ResultEvent):void {
			this.listaDocumetosGed = event.result.dados.listaDocumentosGED as ArrayCollection;
		}
		
		private function selecionarTipoRelacionamento() : void {
			var codigoTipoRelacionameto : Number = this.relacionamento.tipoRelacionamento.codigo;
			this.cmbTipoRelacionamento.procuraItemPorNome(codigoTipoRelacionameto, "codigo");
		}
		
		private function onFalhaRecuperarDefinicoes(evento : FaultEvent) : void {
			dispatchEvent(evento);
		}
		
		private function onRegistroExcluido(evento : ResultEvent) : void {
			this.dispatchEvent(new Event(Modulo.REGISTRO_EXCLUIDO));		
		}
		
		private function onRegistroSalvo(evento : ResultEvent) : void {
			this.dispatchEvent(new Event(Modulo.REGISTRO_GRAVADO));	
		}
		
		private function onChangeChkIndeterminado(evento : Event = null) : void {
			this.dtDataFimMandato.selectedDate = null;
			toggleDataFimMandato();
		}
		
		
		private function toggleDataFimMandato() : void {
			
			var habilitar : Boolean = !this.chkIndeterminado.selected;
			this.dtDataFimMandato.validarObrigatorio = habilitar;
			this.dtDataFimMandato.compDate.enabled = habilitar;
			this.dtDataFimMandato.compMask.enabled = habilitar;
			
			var dataMinima : Date = null;
			if (habilitar) {
				var tipoRelacionamento : TipoRelacionamentoPessoaVO = TipoRelacionamentoPessoaVO(
					this.cmbTipoRelacionamento.selectedItem);
				var dataInicio : Date = this.dtDataInicioMandato.selectedDate;
				if (tipoRelacionamento && tipoRelacionamento.habilitaEnvioCCS.valor) {
					dataInicio = this.dataAtualMovimentoCCS;
				} 
				dataMinima = RelacionamentoPessoaUtils.getDataPrimeiraHoraDoDia(dataInicio);
			}
			this.dtDataFimMandato.dataMinima = dataMinima;
			
			this.dtDataFimMandato.validar(this.dtDataInicioMandato.selectedDate);
		}
		
		private function validaRelacionamentoPessoa(tipoRelPessoa:TipoRelacionamentoPessoaVO):void{
			if(tipoRelPessoa != null && this.procurarPessoa.registro != null && tipoRelPessoa.habilitaVerificacaoPendencia.valor){
				validarPessoaRelacionada(ConversaoPessoaUtil
					.converterVOParaPessoaCompartilhamento(PessoaPlataformaVO(
						this.procurarPessoa.registro)).pessoa, tipoRelPessoa);					
			}
		}
		
		private function onChangeCmbTipoRelacionamento(evento : Event = null) : void {
			var tipo : TipoRelacionamentoPessoaVO = TipoRelacionamentoPessoaVO(this.cmbTipoRelacionamento.selectedItem);
			validaRelacionamentoPessoa(tipo);
			setaIsRelacionamentoComp(tipo);
			habilitarPercentualCapitalEmpresa(tipo && tipo.habilitaCapitalSocial.valor);
			habilitarCamposRegistro(tipo && tipo.habilitaDadosRegistro.valor);
			habilitarPoderes(tipo && tipo.habilitaPoderes.valor);
			habilitarDataMandato(tipo && tipo.codigo != CONJUGE);
		}
		
		private function habilitarDataMandato(habilitar:Boolean):void {
			this.dtDataInicioMandato.enabled = habilitar;
			this.chkIndeterminado.enabled = habilitar;
			if(!habilitar) {
				this.dtDataFimMandato.selectedDate = null;
				this.chkIndeterminado.selected = true;
				toggleDataFimMandato();
			}
		}
		
		private function habilitarPoderes(habilitar:Boolean) : void {
			if (!habilitar && _novo) {
				this.listaPoderes.__botRemoverTodos_click(null);
			}
			this.listaPoderes.enabled = habilitar;
		}
		
		private function habilitarCamposRegistro(habilitar:Boolean) : void {
			this.dtDataInicioMandato.enabled = habilitar;
			this.chkIndeterminado.enabled = habilitar;
			this.numeroFolha.enabled = habilitar;
			this.numeroLivro.enabled = habilitar;
			this.numeroRegistro.enabled = habilitar;
			this.nomeCartorio.enabled = habilitar;
			
			if(!habilitar) {
				this.dtDataFimMandato.selectedDate = null;
				this.chkIndeterminado.selected = true;
				toggleDataFimMandato();
			}
		}
		
		private function habilitarPercentualCapitalEmpresa(habilitar:Boolean = false):void {
			this.percentualCapitalEmpresa.enabled = habilitar;
			this.percentualCapitalEmpresa.validarObrigatorio = habilitar;
			if (!habilitar) {
				this.percentualCapitalEmpresa.text = "";
			}
		}
		
		private function onPessoaRelacionadaSelecionada(evento : Event) : void {
			if (_novo && (this.procurarPessoa.registro != null)) {
				carregarTiposRelacionamento(ConversaoPessoaUtil
					.converterVOParaPessoaCompartilhamento(PessoaPlataformaVO(
						this.procurarPessoa.registro)).pessoa);
			}
		}
		
		private function validarPessoaRelacionada(pessoaRel: PessoaVO, tipoRelPessoa:TipoRelacionamentoPessoaVO) : void{
			var dto : RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.pessoaRelacionada = pessoaRel;
			dto.dados.tipoRelacionamentoPessoa = tipoRelPessoa;
			servicoPendenciaPessoaRelacionada.getOperation(OPERACAO_VALIDAR_PESSOA_RELACIONADA).send(dto);
		}
		
		private function onChangePessoaRelacionada(evento : Event) : void {
			if (_novo && StringUtil.trim(this.procurarPessoa.txtCodigo.text).length == 0) {
				this.cmbTipoRelacionamento.dataProvider = this.tiposRelacionamento;
			}
		}
		
		private function onTiposRelacionamentosRecuperados(evento : ResultEvent) : void {
			
			atualizarCombo(this.cmbTipoRelacionamento, evento.result.dados.tiposRelacionamento);					
		}
		
		public function atualizarCombo(combo:Combo, lista:ListCollectionView):void {
			
			if (combo.dataProvider == null)
				combo.dataProvider = new ArrayCollection();
			
			if (lista == null)
				lista = new ArrayCollection();
			
			ListCollectionView(combo.dataProvider).removeAll();
			adicionarItemOpcional(combo);
			cloneArrayCollection(ListCollectionView(combo.dataProvider), lista);
		}
		
		private function adicionarItemOpcional(combo: Combo): void {
			if (combo.dataProvider is IList) {
				var lista: IList = (combo.dataProvider as IList);
				if (lista.length == 0 || (lista.getItemAt(0) != null && 
					!lista.getItemAt(0).hasOwnProperty("idItemOpcionalCombo"))) {
					lista.addItemAt(criarItemOpcional(combo),0);
				}
			}
		}
		
		protected function criarItemOpcional(combo: Combo):Object {
			var objeto:Object = new Object();
			
			objeto[combo.labelField] = "---";
			objeto.idItemOpcionalCombo = -1;
			
			return objeto;
		}
		
		private function cloneArrayCollection(objDestino:ListCollectionView, objOrigem:ListCollectionView):void {
			
			for each (var elemento:Object in objOrigem) {
				objDestino.addItem(elemento);
			}
		} 
		
		//------------------------------------------------
		// Implementação: IEdicaoPlataformaAtendimentoCAPES
		//------------------------------------------------
		[Deprecated]
		public function getEntidadeVigente():VigenteVO {
			return null;
		}
		
		public function getEntidadeAprovavel():Aprovavel {
			return null;
		}
		
		//---------------------------------------------
		// Implementação: IEdicaoPlataformaAtendimento
		//---------------------------------------------
		public function gravarRegistro():void {
			//Verifica as chaves de negócio dos documentos do componente GED com  as chaves atuais da tela.
			if(!this.abaDocumentos.validarDocumentosNaoEnviados()) {
				Alerta.show(UploadDocGedUtil.VALIDACAO_DOCUMENTOS_NAO_ENVIADOS, "ERRO!", Alerta.ALERTA_ERRO);
				return;
			}
			
			//Verifica se há algum documento que já foi enviado ao ged e está com as chaves de negócio diferentes das atuais.
			if(!this.abaDocumentos.verificarChavesNegocio(criarListaChavesNegocio())){
				Alerta.show(UploadDocGedUtil.VALIDACAO_DOCUMENTOS_DIVERGENTES, "ERRO!", Alerta.ALERTA_ERRO);
				return;
			}
			var obrigatorio: Boolean = validarObrigatoriedadeDocumentos();
			
			if (obrigatorio) {
				Alerta.show(UploadDocGedUtil.VALIDACAO_DOCUMENTOS_OBRIGATORIOS + "documento que comprove o relacionamento.", "ERRO!", Alerta.ALERTA_ERRO);
				return;
			}
			
			if (pessoaProxy.utilizaGedGft && this.abaDocumentos.obterDocumentosComprobatorios().length <= 0) {
				Alerta.show(UploadDocGedUtil.VALIDACAO_DOCUMENTOS_NAO_SELECIONADOS, "ATENÇÃO", Alerta.ALERTA_PERGUNTA, null, onConfirmaGravarRegistro);
			} 
			
			else {
				onConfirmaGravarRegistro();
			}
		}
		
		private function onConfirmaGravarRegistro(evt:Event = null):void {
			executarSeValido(salvar);
		}
		
		public function atualizarCamposRegistro():void {
			// não faz nada
		}
		public function restaurarRegistro():void {
			if (_novo) {
				novoRegistro();
			}
		}
		public function verificarAlteracoes():Boolean {
			
			if (this.percentualCapitalEmpresa.valor != this.relacionamento.percentualCapitalEmpresa)
				return false;
			
			if (this.chkIndeterminado.selected != (this.relacionamento.dataInicioRelacionamento != null)) 
				return false;
			
			if (this.relacionamento is RegistroRelacionamentoVO) {
				var registro:DadosRegistroRelacionamentoVO = RegistroRelacionamentoVO(this.relacionamento).dadosRegistro;
				if (this.numeroRegistro.text != registro.numeroRegistro) 
					return false;
				
				if (this.numeroLivro.text != registro.numeroLivro)
					return false;
				
				if (this.numeroFolha.text != registro.numeroFolha)
					return false;
				
				if (this.nomeCartorio.text != registro.nomeCartorio) 
					return false;
			}
			
			if (this.listaPoderes.enabled) {
				for each (var poder:TipoPoderRelacionamentoVO in this.listaPoderes.listaItensSelecionados) {
					var encontrou:Boolean = false;
					for each (var poderBkp:TipoPoderRelacionamentoVO in this.relacionamento.poderes) {
						if (poder.codigo == poderBkp.codigo) {
							encontrou = true;
							break;
						}
					}
					
					if (!encontrou) {
						return false;
					}
				}
			}
			
			return true;
		}
		
		//--------------------------
		// Implementação: ICadastro
		//--------------------------
		public function carregarDefinicoes(obj:Object=null):void {
			
			var dto : RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.pessoa = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(
				pessoaProxy).pessoa;
			dto.dados.produtosBancoob = _novo != false ? this.produtosBancoob : _novo;
			servicoDefinicoes.getOperation(OPERACAO_OBTER_DEFINICOES).send(dto);
		}
		public function novoRegistro():void {
			this.relacionamento = new RelacionamentoPessoaVO();
			this.relacionamento.pessoa = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(
				pessoaProxy).pessoa;
			carregarDefinicoes();
		}
		public function excluirRegistro(obj:Object):void {
			var relacionamento : RelacionamentoPessoaVO = RelacionamentoPessoaVO(obj);
			var dto : RequisicaoReqDTO = obterDTO(relacionamento);
			servicoExclusao.getOperation(OPERACAO_EXCLUIR_DADOS).send(dto);
		}
		public function carregarRegistro(obj:Object):void {
			var dto : RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.relacionamentoPessoa = obj;
			dto.dados.produtosBancoob = this.produtosBancoob;
			servicoConsulta.getOperation(OPERACAO_OBTER_DADOS).send(dto);
		}
		public function preencherCampos() : void {
			
			carregarDefinicoes();
		}
		private function preencherFormEdicao(evento : ResultEvent) : void {
			if (!_novo) {
				var tipoRelacionamento : TipoRelacionamentoPessoaVO = this.relacionamento.tipoRelacionamento;
				setaIsRelacionamentoComp(tipoRelacionamento);
				this.dtDataCadastro.selectedDate = new Date();
				this.procurarPessoa.txtCodigo.text = this.relacionamento.pessoaRelacionada.cpfCnpj;
				this.procurarPessoa.procurarCodigo();
				this.cmbTipoRelacionamento.procuraItemPorNome(tipoRelacionamento.codigo, "codigo");
				if (tipoRelacionamento.habilitaCapitalSocial.valor) {
					this.percentualCapitalEmpresa.valor = this.relacionamento.percentualCapitalEmpresa;
				}
				
				if(relacionamento.dataInicioRelacionamento != null) {
					this.dtDataInicioRelacionamento.text = FormataData.formataData(this.relacionamento.dataInicioRelacionamento.data);
				}
				
				if(this.relacionamento.dataInicioMandato != null){
					this.dtDataInicioMandato.selectedDate = this.relacionamento.dataInicioMandato.data;
				}
				
				this.chkIndeterminado.selected = this.relacionamento.dataFimMandato == null;
				this.dtDataFimMandato.selectedDate = this.chkIndeterminado.selected ? null :
					this.relacionamento.dataFimMandato.data;
				if (tipoRelacionamento != null
					&& tipoRelacionamento.habilitaDadosRegistro != null
					&& tipoRelacionamento.habilitaDadosRegistro.valor) {
					this.dtDataInicioMandato.selectedDate = this.relacionamento.dataInicioMandato.data;
					var registro : RegistroRelacionamentoVO = RegistroRelacionamentoVO(this.relacionamento);
					if(registro != null
						&& registro.dadosRegistro != null){
						this.nomeCartorio.text = registro.dadosRegistro.nomeCartorio;
						this.numeroFolha.text = registro.dadosRegistro.numeroFolha;
						this.numeroLivro.text = registro.dadosRegistro.numeroLivro;
						this.numeroRegistro.text = registro.dadosRegistro.numeroRegistro;						
					}
				} else {
					this.nomeCartorio.text = "";
					this.numeroFolha.text = "";
					this.numeroLivro.text = "";
					this.numeroRegistro.text = "";
				}
				
				// seleciona os poderes
				var poderes : ListCollectionView = this.relacionamento.poderes;
				if (tipoRelacionamento != null
					&&tipoRelacionamento.habilitaPoderes != null
					&&tipoRelacionamento.habilitaPoderes.valor 
					&& poderes && (poderes.length > 0)) {
					
					var poderesSelecionados : ArrayCollection = new ArrayCollection();
					var poderesDisponiveis : IList = IList(evento.result.dados.poderes);
					for each (var poderDisponivel : TipoPoderRelacionamentoVO in poderesDisponiveis) {
						for each (var poder : TipoPoderRelacionamentoVO in poderes) {
							if (poderDisponivel.codigo === poder.codigo) {
								poderesSelecionados.addItem(poder);
								break;
							}
						} 
					}
					evento.result.dados["poderesSelecionados"] = poderesSelecionados;
					this.listaPoderes.carregarListagem(evento, false);
					this.listaPoderes.enabled = !modoConsulta;
				} else {
					this.listaPoderes.limpaListaItensSelecionados();
				}
				
			}
		}
		
		/**
		 * Método para criar as lista de chave de negócio.
		 */
		private function criarListaChavesNegocio():ArrayCollection {
			var listaValoresChaves:ArrayCollection = new ArrayCollection();
			var objChaveDoc:Object = new Object();
			
			//Criar os valores das chaves na ordem que são adicionados na fachada.
			var valoresChavesNegocio:ArrayCollection = criarValoresChavesNegocio();
			
			//Adiciona a chave da pessoa (PF/PJ)
			objChaveDoc.siglaTipoDocumento = definicoesComponenteGED.siglaTipoDocumento;
			objChaveDoc.siglaChaveDocumento = definicoesComponenteGED.obterCodigoTipoPessoaSelecionada;
			objChaveDoc.valorChave = pessoaProxy.cpfCnpj;
			listaValoresChaves.addItem(objChaveDoc);
			
			//Percorre a lista para preencher as outras chaves.
			for (var i:uint = 0; i < definicoesComponenteGED.chavesNegocio.length; i++){
				objChaveDoc = new Object();
				
				objChaveDoc.siglaTipoDocumento = definicoesComponenteGED.siglaTipoDocumento;
				objChaveDoc.siglaChaveDocumento = definicoesComponenteGED.chavesNegocio[i];
				objChaveDoc.valorChave = valoresChavesNegocio[i];
				listaValoresChaves.addItem(objChaveDoc);
			}
			
			return listaValoresChaves;
		}
		
		/**
		 * Método para adicionar os valores das chaves de negócio à um array,
		 * de acordo com o esperado pelas chaves de negócio.
		 */
		private function criarValoresChavesNegocio():ArrayCollection {
			var valoresChavesNegocio:ArrayCollection = new ArrayCollection();
			
			valoresChavesNegocio.addItem(pessoaProxy.codCompartilhamentoCadastro);
			
			if(!isNaN(cmbTipoRelacionamento.selectedIndex) && cmbTipoRelacionamento.selectedIndex != 0){
				valoresChavesNegocio.addItem(cmbTipoRelacionamento.selectedItem.codigo);
			}else{
				valoresChavesNegocio.addItem(undefined);
			}
			
			return valoresChavesNegocio;
		}
		
		private function validarObrigatoriedadeDocumentos(): Boolean {
			if (!pilotoHabilitado || !pessoaProxy.utilizaGedGft) {
				return false;
			}
			return validarListaDocumetos();
			
		}
		private function validarListaDocumetos(): Boolean {
			var pessoaJuridica:Boolean = isPessoaJuridica();
			if (this.listaDocumetosGed.length == 0 && this.abaDocumentos.obterDocumentosComprobatorios().length <= 0) {
				return validarPorTipoPessoa(pessoaJuridica);
			}
			return false;
		}
		
		private function validarPorTipoPessoa(pessoaJuridica: Boolean):Boolean {
			var obrigatorio: Boolean = true;
			if (pessoaJuridica) {
				obrigatorio = validarPessoaJuridica();
			} 
			return obrigatorio;
		}
		
		private function validarPessoaJuridica():Boolean {
			if (this.cmbTipoRelacionamento == null || this.cmbTipoRelacionamento.selectedItem == null) {
				return false;
			}
			var obrigatorio: Boolean = false;
			var tipoRelacionamento : TipoRelacionamentoPessoaVO = TipoRelacionamentoPessoaVO(this.cmbTipoRelacionamento.selectedItem);
			var relacionamento: Number = tipoRelacionamento.codigo;
			
			if (PROCURADOR == relacionamento) {
				obrigatorio = true;
			}
			return obrigatorio;
		}
		
		private function isPessoaJuridica():Boolean {
			return PESSOA_JURIDICA == pessoaProxy.codTipoPessoa;
		}
		
		//---------
		// get/set
		//---------
		public function get produtosBancoob() : Boolean {
			return _produtosBancoob;
		} 
		public function set produtosBancoob(valor:Boolean) : void {
			_produtosBancoob = valor;
		}
		
		public function get modoConsulta() : Boolean {
			return _modoConsulta;
		}
		public function set modoConsulta(value:Boolean):void {
			_modoConsulta = value;
		}
		
		//----------
		// Serviços
		//----------
		public function get servicoConsulta() : ServicoJava {
			return _servicoConsulta;
		}
		
		public function get servicoDefinicoes() : ServicoJava {
			return _servicoDefinicoes;
		}
		
		public function get servicoExclusao() : ServicoJava {
			return _servicoExclusao;
		}
		
		public function get servicoSalvamento() : ServicoJava {
			return _servicoSalvamento;
		}
		
		public function get servicoTipoRelacionamento() : ServicoJava {
			return _servicoTipoRelacionamento;
		}
		
		public function get servicoPendenciaPessoaRelacionada() : ServicoJava {
			return _servicoPendenciaPessoaRelacionada;
		}		
		
		public function isRegistroBloqueadoAlteracao():Boolean{
			return _isRegistroBloqueado; 
		}
	}
}