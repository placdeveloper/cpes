package br.com.sicoob.capes.cadastrarProdutor
{
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.componentes.grid.Grid;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.ParametrosAssistenteAtendimento;
	import br.com.bancoob.sisbr.componentes.pti.ObjetoEvent;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastrarProdutor.janelas.SugerirCategoriaProdutor;
	import br.com.sicoob.capes.utils.autorizacao.DetalhamentoGedGft;
	import br.com.sicoob.capes.comum.enums.SituacaoRegistroEnum;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.CategoriaProdutorVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.ProdutorVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoBeneficiarioSicorVO;
	import br.com.sicoob.capes.corporativo.componentes.plataformaatendimento.ProcuraClientePlataformaCAPES;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	import br.com.sicoob.capes.utils.UploadDocGedUtil;
	import br.com.sicoob.capes.utils.plataformaatendimento.IListaPlataformaAtendimentoCAPES;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCAPESBase;
	
	import flash.display.DisplayObject;
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.events.IndexChangedEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	
	public class frmListarProdutividade extends frmListarProdutividadeView implements IListaPlataformaAtendimentoCAPES {
		
		static public const ABA_ATUAIS : int = 0;
		static public const ABA_ANTERIORES : int = 1;		
		public static const OPERACAO_OBTER_DADOS_SELECAO: String = "obterDadosSelecao";
		
		static public const OPERACAO_OBTER_PRODUTOR: String = "obterProdutor";
		static public const OPERACAO_INCLUIR_PRODUTOR: String = "incluirProdutor";
		static public const OPERACAO_ALTERAR_PRODUTOR: String = "alterarProdutor";
		static public const OPERACAO_EXCLUIR_PRODUTOR: String = "excluirProdutor";
		
		public static const CLICK_ABAS:String = "clickAbas";
		public static const VERIFICAR_BOTOES_AUTORIZACAO:String = "verificarBotoesAutorizacao";
		
		public var servicoListar:ServicoJava = ServicoJavaUtil.getServico(ProdutorSelecao.CLASSE_SERVICO_PRODUTIVIDADE);
		public var servicoConsulta:ServicoJava = ServicoJavaUtil.getServico(ProdutorSelecao.CLASSE_SERVICO_PRODUTOR);
		public var servicoEdicao:ServicoJava = ServicoJavaUtil.getServico(ProdutorSelecao.CLASSE_SERVICO_PRODUTOR);

		private var telaSugerirCategoria:SugerirCategoriaProdutor = new SugerirCategoriaProdutor();
		private var janelaSugerir:Janela = null;
							
		[Bindable]
		public var _novo:Boolean = false;
		private var produtor:ProdutorVO = new ProdutorVO();
		
		private var produtorVigenteBKP:ProdutorVO = new ProdutorVO();
		private var produtorAlteracaoBKP:ProdutorVO = new ProdutorVO();
		
		private var produtorVigente:ProdutorVO = new ProdutorVO();
		private var produtorAlteracao:ProdutorVO = new ProdutorVO();
		
		private var listaCategorias :ArrayCollection = new ArrayCollection();
		private var exploracoesAtuais :ArrayCollection = new ArrayCollection();
		private var exploracoesAnteriores :ArrayCollection = new ArrayCollection();
		
		//Guarda as informações da pessoa selecionada na plataforma
		private var pessoaSelecionada:PessoaPlataformaVO;
		
		//Objeto que guardará as informações para serem usadas no componente GED.
		private var definicoesComponenteGED:ArrayCollection = new ArrayCollection;
		
		private var _isRegistroBloqueado:Boolean = false;
		
		private var _destinoVO:DestinoVO;
		
		private const ABA_DOCUMENTOS:Number = 1;
		
		private static const TITULO_JANELA:String = "DETALHAMENTO GED / GFT";
		public const VERIFICAR_BOTAO_GEDGFT:String = "verificarBotaoGedGft"
		
	    /**
	     *  Construtor.
	     */
		public function frmListarProdutividade()	{
			super();
			
			servicoListar.addEventListener(ResultEvent.RESULT, retornoObterLista);
			servicoConsulta.addEventListener(ResultEvent.RESULT, retornoObterProdutor);
			servicoEdicao.addEventListener(ResultEvent.RESULT, retornoObterProdutor);
			
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(evt:FlexEvent=null):void {
			pessoaSelecionada = ProcuraClientePlataformaCAPES.getPessoaSelecionada();
			
			this.cboCategoriaProdutor.dataProvider = listaCategorias;

			navegacaoTab.addEventListener(Event.CHANGE, clickAbas);			
			abaAnteriores.addEventListener(ListEvent.CHANGE, registroSelecionado);
			abaAtuais.addEventListener(ListEvent.CHANGE, registroSelecionado);
			telaSugerirCategoria.addEventListener(Modulo.REGISTRO_SELECIONADO, aceitarSugestao);
						
			btnAlterarProdutor.addEventListener(MouseEvent.CLICK, verificaAlteracoes);
			btnExcluirProdutor.addEventListener(MouseEvent.CLICK, excluirProdutor);
			btnSugerirCategoria.addEventListener(MouseEvent.CLICK, abrirTelaSugerirCategoria);
			
			componenteProcurarTipoBeneficiarioSicor.configurarDestino(this._destinoVO);
			
			carregarProdutor();
			
			verificarBotaoExcluir();
			
			//Adiciona o evento ao trocar aba.
			this.navegacao.addEventListener(IndexChangedEvent.CHANGE, aoTrocarAba);
			this.abaDocumentos.enabled = pessoaSelecionada.utilizaGedGft;
		}
		
		public function dispose():void {
			abaDocumentos.dispose();
			navegacao.removeEventListener(Event.CHANGE, clickAbas);
			navegacao.removeAllChildren();
			navegacao = null;
		}
		
		public function configurarDestinosServicos(destinoVO:DestinoVO):void{
			servicoListar.configurarDestino(destinoVO);
			servicoEdicao.configurarDestino(destinoVO);
			servicoConsulta.configurarDestino(destinoVO);
			
			_destinoVO = destinoVO;
		}		
				
        private function clickAbas(evento:Event) : void{
        	
    		abaAnteriores.gridDados.selectedIndex = -1;
    		abaAtuais.gridDados.selectedIndex = -1;
    		this.dispatchEvent(new Event(CLICK_ABAS));	
        } 		
		
		public function obterGrid():Grid {
			
			var grid:Grid = null;
			
			if(isAbaAtuaisSelecionada()) {
				grid = abaAtuais.obterGrid()
			} else if(isAbaAnterioresSelecionada()) {
				grid = abaAnteriores.obterGrid()				
			} 
			
			return grid;
		}

		public function obterLista():void{

			MostraCursor.setBusyCursor("Carregando Registros!", 
					Application.application, MostraCursor.CURSOR_PESQUISAR);

			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			preencherRequisicaoReqDTO(dto);
			
			servicoListar.getOperation(OPERACAO_OBTER_DADOS_SELECAO).send(dto);			
		}	
		
		public function carregarProdutor():void {

			MostraCursor.setBusyCursor("Carregando Registros!", 
					Application.application, MostraCursor.CURSOR_PESQUISAR);
					
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			preencherRequisicaoReqDTO(dto);
			
			servicoConsulta.getOperation(OPERACAO_OBTER_PRODUTOR).send(dto);
		}

	    //--------------------------------------------------------------------------
	    //  Métodos auxiliares
	    //--------------------------------------------------------------------------						
		public function isAbaAtuaisSelecionada() : Boolean {
			return navegacaoTab.selectedIndex == ABA_ATUAIS;
		}

		public function isAbaAnterioresSelecionada() : Boolean {
			return navegacaoTab.selectedIndex == ABA_ANTERIORES;
		}
		
		private function calcularRendas(): Number {
			
			var total: Number = 0;
			
			if(exploracoesAtuais != null) {
				var numItens:int = exploracoesAtuais.length;
				
				for(var i:int = 0; i< numItens; i++) {
					total += (exploracoesAtuais[i]).valorRendaBruta;
				}	
			}
			return total;
		}
			
		private function abrirTelaSugerirCategoria(evt:Event):void {			

			// Tela instanciada somente na primeira chamada 			
			if(janelaSugerir == null){
				janelaSugerir = new Janela();
				janelaSugerir.addChild(telaSugerirCategoria);
				janelaSugerir.title = "SUGESTÃO DE CATEGORIA DO PRODUTOR";
				janelaSugerir.icone = "br/com/bancoob/imagens/icos/Import.png";				
			}
			telaSugerirCategoria.carregar(listaCategorias, calcularRendas());
			janelaSugerir.abrir(this,true,true);
		}			
		
	    //--------------------------------------------------------------------------
	    //  Métodos para Produtor.
	    //--------------------------------------------------------------------------
	    private function retornoObterProdutor(event:ResultEvent):void {
			
			if(event.result.dados.categorias != null) {
				listaCategorias = event.result.dados.categorias;
			}
			this.cboCategoriaProdutor.dataProvider = listaCategorias;
			
			produtorVigente = event.result.dados.produtorVigente;
			produtorAlteracao = event.result.dados.produtorAlteracao;
						
			produtor = produtorVigente;
			
			produtorVigenteBKP = produtorVigente;
			produtorAlteracaoBKP = produtorAlteracao;
			
			definicoesComponenteGED = event.result.dados.definicoesComponenteGED;
			preencherCampos();
			MostraCursor.removeBusyCursor();
			obterLista();
			verificarBotaoExcluir();
			
			limparGridDocumentos();
			
			verificarBotoesAutorizacao(produtorAlteracao != null ? produtorAlteracao.situacaoAprovacao : SituacaoRegistroEnum.VIGENTE, exibirBotaoDetalhamentoGedGft());
		}	

		private function verificarBotoesAutorizacao(situacao:SituacaoRegistroEnum, exibirBotaoGedGft:Boolean):void{
			var objeto:Object = new Object();
			objeto.situacao = situacao;
			objeto.exibir = exibirBotaoGedGft;
			
			var evento:Event = new ObjetoEvent(VERIFICAR_BOTOES_AUTORIZACAO, objeto);
			this.dispatchEvent(evento);
		}

		public function preencherCampos():void {
			var categoria: Object = null;
			
			_novo = produtor == null;
			
			txtInscricao.text = "";
			txtMesAnoPermanente.text = "";
			txtMesAnoTemporario.text = "";
			txtEmpregadosPermanentes.text = "";
			txtEmpregadosTemporarios.text = "";

			if(produtor != null) {
				if(produtor.categoria != null) {
					categoria = produtor.categoria.codigo;
				}
				
				txtInscricao.text = produtor.codigoInscricao;
				txtMesAnoPermanente.text = produtor.mesAnoPermanente;
				txtMesAnoTemporario.text = produtor.mesAnoTemporario;
				txtEmpregadosPermanentes.valor = produtor.qtdPermanente;
				txtEmpregadosTemporarios.valor = produtor.qtdTemporario;
				
				var tipobeneficiarioSicor:TipoBeneficiarioSicorVO = produtor.tipoBeneficiarioSicor;
				if(tipobeneficiarioSicor != null) {
					componenteProcurarTipoBeneficiarioSicor.textoCodigo.valor = tipobeneficiarioSicor.codigo;
					componenteProcurarTipoBeneficiarioSicor.pesquisar();
				}
			}			
			
			btnExcluirProdutor.enabled = produtor!= null;	
			cboCategoriaProdutor.procuraItemPorNome(categoria, "codigo");
		}			
			    
	    public function gravarRegistro(evt:Event = null):void {
			
			if(produtorAlteracao != null && produtor != produtorAlteracao){
				Alerta.show("O registro encontra-se em alteração, favor aguardar a finalização do fluxo de autorização.", "Aviso");
				return;
			}
			
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
			
			//Exige confirmação para salvar a certidão sem documentos.
			if(pessoaSelecionada.utilizaGedGft && this.abaDocumentos.obterDocumentosComprobatorios().length <= 0){
				Alerta.show(UploadDocGedUtil.VALIDACAO_DOCUMENTOS_NAO_SELECIONADOS, "ATENÇÃO", Alerta.ALERTA_PERGUNTA, null, onConfirmaGravarRegistro);
			}else{
				onConfirmaGravarRegistro();
			}
			
		}	
		
		private function onConfirmaGravarRegistro(evt:Event = null):void {
			atualizarCamposRegistro();
			executarSeValido(gravarDados);
		}

		private function gravarDados(): void {

			MostraCursor.setBusyCursor("Gravando Registro!", Application.application,
					MostraCursor.CURSOR_GRAVAR);

			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			preencherRequisicaoReqDTO(dto);
			
			if (_novo) {
				servicoEdicao.getOperation(OPERACAO_INCLUIR_PRODUTOR).send(dto);
			} else {
				servicoEdicao.getOperation(OPERACAO_ALTERAR_PRODUTOR).send(dto);
			}
		}

		public function atualizarCamposRegistro():void{
			
			if (_novo) {
				var pessoa:PessoaCompartilhamentoVO = TelaPlataformaAtendimentoCAPESBase.getPessoaSelecionada();
				produtor = new ProdutorVO();
				produtor.pessoaCompartilhamento = pessoa;
				produtor.idPessoaCompartilhamento = pessoa.idPessoaCompartilhamento;
			}
			
			produtor.categoria = cboCategoriaProdutor.selectedItem as CategoriaProdutorVO;
			produtor.codigoInscricao = txtInscricao.text;
			produtor.mesAnoPermanente = txtMesAnoPermanente.text;
			produtor.mesAnoTemporario = txtMesAnoTemporario.text;
			produtor.qtdPermanente = txtEmpregadosPermanentes.valor;
			produtor.qtdTemporario = txtEmpregadosTemporarios.valor;
			produtor.tipoBeneficiarioSicor = componenteProcurarTipoBeneficiarioSicor.obterRegistro() as TipoBeneficiarioSicorVO;
			
			//Adiciona à certidão, os documentos que foram enviados ao GED.
			produtor.documentosComprobatorios = this.abaDocumentos.obterDocumentosComprobatorios();
			
		}

		private function excluirProdutor(evt:Event):void {
			if(!verificarExclusaoProdutor()){
				Alerta.show("Exclusão não permitida. Existe alguma produtividade associada ao produtor.", "Excluir Produtor", Alerta.ALERTA_INFORMACAO);
				return;
			}
			
			Alerta.show("Confirma a operação?", "Confirmação", Alerta.ALERTA_PERGUNTA, null, exluirProdutorConfirmado);
		}
		
		private function verificarExclusaoProdutor():Boolean {
			if(pessoaSelecionada.utilizaGedGft && produtor == produtorAlteracao){
				return true;
			}
			
			return (exploracoesAtuais != null && exploracoesAtuais.length == 0) && (exploracoesAnteriores != null && exploracoesAnteriores.length == 0);
		}
		
		private function exluirProdutorConfirmado(evt:Event):void {
			MostraCursor.setBusyCursor("Excluindo Registro!", Application.application, 
					MostraCursor.CURSOR_EXCLUIR);
					
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			preencherRequisicaoReqDTO(dto);
			
			servicoEdicao.getOperation(OPERACAO_EXCLUIR_PRODUTOR).send(dto);
		}
		
		private function aceitarSugestao(evt:Event):void {

			var categoria: CategoriaProdutorVO = telaSugerirCategoria.categoriaSugerida;
			var codigo: Object = null;
			if(categoria != null) {
				codigo = categoria.codigo;
			}
			cboCategoriaProdutor.procuraItemPorNome(codigo, "codigo")
		}    
	    //--------------------------------------------------------------------------
	    //  Listeners
	    //--------------------------------------------------------------------------
		private function retornoObterLista(event: ResultEvent): void {

			exploracoesAtuais = event.result.dados["atuais"];
			exploracoesAnteriores = event.result.dados["anteriores"];
			abaAnteriores.carregarExploracoes(exploracoesAnteriores);
			abaAtuais.carregarExploracoes(exploracoesAtuais);
			
			selecionarAba(abaAtuais);	
			MostraCursor.removeBusyCursor();
		}		
		
		private function registroSelecionado(evt:Event):void {
			this.dispatchEvent(evt);	
		}				
				
	    public function finalizarExploracao(evt:Event) :void {
			this.dispatchEvent(evt);
	    }		
		
	    //--------------------------------------------------------------------------
	    //  Métodos das interfaces
	    //--------------------------------------------------------------------------
		public function trocarTelaEntrar(params:ParametrosAssistenteAtendimento=null):void{};
		public function trocarTelaSair(params:ParametrosAssistenteAtendimento=null):void{};					
		
		
		//--------------------------------------------------------------------------
		//  Métodos para o componente de upload
		//--------------------------------------------------------------------------
		/**
		 * Método para adicionar os valores às chaves de negócio
		 * ao componente de upload do GED. 
		 */
		private function aoTrocarAba(event:IndexChangedEvent):void {
			if (this.navegacao.selectedIndex == ABA_DOCUMENTOS) {
				
				//Obtém as chaves de negócio
				var listaChavesNegocio:ArrayCollection = criarListaChavesNegocio();
				
				//Adiciona as chaves de negócio à instância do componente.
				this.abaDocumentos.chavesNegocio = listaChavesNegocio;
				
				//Atualiza as chaves de negócio dos arquivos que ainda não foram enviados.
				this.abaDocumentos.atualizarChavesNegocio(listaChavesNegocio);
			}
		}
		
		/**
		 * Método para criar as lista de chave de negócio.
		 */
		private function criarListaChavesNegocio():ArrayCollection {
			var listaValoresChaves:ArrayCollection = new ArrayCollection();
			var objChaveDoc:Object = new Object();
			
			//Adiciona a chave da pessoa (PF/PJ)
			objChaveDoc.siglaTipoDocumento = definicoesComponenteGED[0].siglaTipoDocumento;
			objChaveDoc.siglaChaveDocumento = definicoesComponenteGED[0].obterCodigoTipoPessoaSelecionada;
			objChaveDoc.valorChave = pessoaSelecionada.cpfCnpj;
			listaValoresChaves.addItem(objChaveDoc);
			
			//Adiciona o grupo de compartilhamento
			objChaveDoc = new Object();
			objChaveDoc.siglaTipoDocumento = definicoesComponenteGED[0].siglaTipoDocumento;
			objChaveDoc.siglaChaveDocumento = definicoesComponenteGED[0].chavesNegocio[0];
			objChaveDoc.valorChave = pessoaSelecionada.codCompartilhamentoCadastro;
			listaValoresChaves.addItem(objChaveDoc);
			
			//Adiciona a chave da pessoa (PF/PJ)
			objChaveDoc = new Object();
			objChaveDoc.siglaTipoDocumento = definicoesComponenteGED[1].siglaTipoDocumento;
			objChaveDoc.siglaChaveDocumento = definicoesComponenteGED[1].obterCodigoTipoPessoaSelecionada;
			objChaveDoc.valorChave = pessoaSelecionada.cpfCnpj;
			listaValoresChaves.addItem(objChaveDoc);
			
			//Adiciona o grupo de compartilhamento
			objChaveDoc = new Object();
			objChaveDoc.siglaTipoDocumento = definicoesComponenteGED[1].siglaTipoDocumento;
			objChaveDoc.siglaChaveDocumento = definicoesComponenteGED[1].chavesNegocio[0];
			objChaveDoc.valorChave = pessoaSelecionada.codCompartilhamentoCadastro;
			listaValoresChaves.addItem(objChaveDoc);
			
			return listaValoresChaves;
		}
		
		public function setarProdutorVigente(vigente:Boolean):void{
			produtor = vigente == true ? produtorVigente : produtorAlteracao;
			preencherCampos();
			verificarBotaoExcluir();
			verificarBotoesAutorizacao(SituacaoRegistroEnum.VIGENTE, exibirBotaoDetalhamentoGedGft());
			
			if(produtor != null){
				this.abaDocumentos.carregarDocumentos(UploadDocGedUtil.criarListaDocumentos(produtorAlteracao.documentosComprobatorios));
			}else{
				this.abaDocumentos.carregarDocumentos(UploadDocGedUtil.criarListaDocumentos(null));
			}
		}
		
		private function preencherRequisicaoReqDTO(dto:RequisicaoReqDTO):void {
			dto.dados.pessoa = TelaPlataformaAtendimentoCAPESBase.getPessoaSelecionada();
			dto.dados.produtor = produtor;
			
			//Envia o tipo de pessoa para ser utilizado nas chaves de negócio.
			dto.dados.idTipoPessoa = pessoaSelecionada.codTipoPessoa;
		}
		
		private function verificarBotaoExcluir():void {
			this.btnExcluirProdutor.enabled = produtor != null ? produtor.codigoInscricao : false;
		}
		
		private function limparGridDocumentos():void{
			this.abaDocumentos.limparDocumentos();
			this.navegacao.selectedIndex = 0;
		}
		
		public function getProdutor():ProdutorVO {
			return produtor;
		}
		
		private function exibirBotaoDetalhamentoGedGft():Boolean {
			if(pessoaSelecionada.utilizaGedGft){
				if(produtor != null && (SituacaoRegistroEnum.VIGENTE == produtor.situacaoAprovacao || SituacaoRegistroEnum.BLOQUEADO == produtor.situacaoAprovacao)){
					return true;
				}
			}
			return false;
		}
		
		public function exibirDetalhamentoGedGft():void{
			var popUpDetalhamentoGedGft:DetalhamentoGedGft = new DetalhamentoGedGft();
			popUpDetalhamentoGedGft.objeto = produtor;
			popUpDetalhamentoGedGft.idTipoPessoaSelecionada = pessoaSelecionada.codTipoPessoa;
			
			var janela:Janela = new Janela();
			janela.title = TITULO_JANELA;
			janela.addChild(popUpDetalhamentoGedGft);
			janela.abrir(DisplayObject(Application.application), true);
		}
		
		public function verificarAlteracoes():Boolean {
			var tipoBeneficiarioSicor:TipoBeneficiarioSicorVO = null;
			if(pessoaSelecionada.utilizaGedGft && produtor == produtorAlteracao){
				tipoBeneficiarioSicor = produtorAlteracao.tipoBeneficiarioSicor;
				return txtInscricao.text == produtorAlteracaoBKP.codigoInscricao 
					&& cboCategoriaProdutor.selectedItem.codigo == produtorAlteracaoBKP.categoria.codigo 
					&& txtEmpregadosPermanentes.valor == produtorAlteracaoBKP.qtdPermanente
					&& txtMesAnoPermanente.text == produtorAlteracaoBKP.mesAnoPermanente 
					&& txtEmpregadosTemporarios.valor == produtorAlteracaoBKP.qtdTemporario 
					&& txtMesAnoTemporario.text == produtorAlteracaoBKP.mesAnoTemporario
					&& componenteProcurarTipoBeneficiarioSicor.textoCodigo.valor == (tipoBeneficiarioSicor != null ? tipoBeneficiarioSicor.codigo : 0);
			}
			tipoBeneficiarioSicor = produtorVigenteBKP.tipoBeneficiarioSicor;
			return txtInscricao.text == produtorVigenteBKP.codigoInscricao 
				&& cboCategoriaProdutor.selectedItem.codigo == produtorVigenteBKP.categoria.codigo 
				&& txtEmpregadosPermanentes.valor == produtorVigenteBKP.qtdPermanente
				&& txtMesAnoPermanente.text == produtorVigenteBKP.mesAnoPermanente 
				&& txtEmpregadosTemporarios.valor == produtorVigenteBKP.qtdTemporario 
				&& txtMesAnoTemporario.text == produtorVigenteBKP.mesAnoTemporario
				&& componenteProcurarTipoBeneficiarioSicor.textoCodigo.valor == (tipoBeneficiarioSicor != null ? tipoBeneficiarioSicor.codigo : 0);				
		}
		
		private function verificaAlteracoes(evt:Event = null):void{
//			if (!_novo && verificarAlteracoes()) {
//				Alerta.show(TelaPlataformaAtendimentoCAPESBase.MENSAGEM_ALTERACAO_CORPO, 
//					TelaPlataformaAtendimentoCAPESBase.MENSAGEM_ALTERACAO_CABECALHO, Alerta.ALERTA_INFORMACAO);
//			} else {
				gravarRegistro();
//			}
		}
	}
}