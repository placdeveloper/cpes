package br.com.sicoob.capes.corporativo.componentes.plataformaatendimento {
	import flash.display.DisplayObject;
	import flash.events.Event;
	import flash.events.KeyboardEvent;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.controls.dataGridClasses.DataGridColumn;
	import mx.core.Application;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.componentes.input.Texto;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.ProcuraClientePlataforma;
	import br.com.bancoob.sisbr.componentes.plataformas.BarraInferiorPlataformas;
	import br.com.bancoob.sisbr.componentes.procurarCliente.dto.SelPessoaReqDTO;
	import br.com.bancoob.sisbr.componentes.selecaoGeral.EventoProcurar;
	import br.com.bancoob.sisbr.componentes.selecaoGeral.SelecaoGeralConstantes;
	import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelecaoGeralDTO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.FormatUtil;
	import br.com.bancoob.util.StringUtils;
	import br.com.bancoob.util.eventos.EventData;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.corporativo.componentes.validacaoCadastral.ValidacaoCadastral;
	import br.com.sicoob.capes.corporativo.componentes.validacaoCadastral.vo.ValidacaoCadastralVO;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	import br.com.sicoob.sisbr.componentes.cadastrounicoclientes.plataformaatendimento.ProcuraClientePlataformaCAPS;
	
	public class ProcuraClientePlataformaCAPES extends ProcuraClientePlataforma
	{
		
		private static const EVENTO_BLOQUEAR_MENU:String = "bloquearMenu"
		private static const SERVICO_PROCURAR_PESSOA_CUC:String = "br.com.sicoob.capes.corporativo.fachada.ProcurarPessoaFachada";
		
		private static const CAMPO_NOME_PESSOA:String = "nomePessoa";
		private static const CAMPO_CPF_CNPJ:String = "cpfCnpj";
		private static const CAMPO_APELIDO:String = "nomeApelido";
		
		private static const ID_PESQUISA_NOME:String = "POR NOME";
		private static const ID_PESQUISA_CPF_CNPJ:String = "POR CPF/CNPJ";
		private static const ID_PESQUISA_APELIDO:String = "POR APELIDO";
		
		private static const QTD_MAXIMA_REGISTRO:int = 50;
		private static const MSG_QTD_MAXIMA_REGISTRO:String ="Esta consulta retornou muitos registos, o resultado foi limitado a "+QTD_MAXIMA_REGISTRO+" registros.  "
			+ "Recomendamos que seja feito uma busca mais detalhada.";
		
		public static const EVENTO_PESSOA_SELECIONADA:String = "PESSOA_SELECIONADA_PLATAFORMA";
		
		private var destinoCUC:DestinoVO;
		private var servicoCUCPesquisa:ServicoJava;
		private var servicoCUCBusca:ServicoJava;
		private var servicoCUCResponsavel:ServicoJava;
		private var servicoCUCPessoaLegado:ServicoJava;
		
		private var procuraClienteLegado:ProcuraClientePlataformaCAPS;
		
		private static var isPessoaSelecionada:Boolean = false;
		private static var pessoaSelecionada:PessoaPlataformaVO;
		
		private var gridCacheDataProvider:ArrayCollection = new ArrayCollection();
		private var gridCacheDataProviderTotalPag:int = 0;
		private var isBarraPaginacao:Boolean = false;
		
		private function liberarGridCacheDataProvider():void{
			gridCacheDataProviderTotalPag = 0;
			if(gridCacheDataProvider!=null){
				gridCacheDataProvider.removeAll();
			}
			gridCacheDataProvider = null; 
			gridCacheDataProvider = new ArrayCollection();
		}
		
		private function getGridCacheDataProvider():ArrayCollection {
			return gridCacheDataProvider;
		}
		
		private function setGridCacheDataProvider(data:ArrayCollection):void {
			liberarGridCacheDataProvider();
			gridCacheDataProvider = data == null ? new ArrayCollection() : data;
		}
		
		private function getGridCacheDataProviderTotalPag():int {
			return gridCacheDataProviderTotalPag;
		}
		
		private function setGridCacheDataProviderTotalPag(pag:int):void {
			gridCacheDataProviderTotalPag = pag;
		}
		
		private function mouseDownBotaoAnterior(evt:MouseEvent=null):void {
			isBarraPaginacao = true;
		}
		
		private function keyDownBotaoAnterior(evt:KeyboardEvent=null):void {
			isBarraPaginacao = true;
		}
		
		private function retornaListaPorPagina(pag:int):ArrayCollection{
			var listaPaginada:ArrayCollection = new ArrayCollection();			
			for(var i:int = (pag*10) - 10; ((i <= (pag*10) - 1)&&(i <gridCacheDataProvider.length)); i++){
				listaPaginada.addItem(gridCacheDataProvider.getItemAt(i));
			}
			
			return listaPaginada;
		}
		
		public static function getPessoaSelecionada():PessoaPlataformaVO {
			if(!isPessoaSelecionada){
				isPessoaSelecionada = true;
				if( plataformaSingleton ){
					(plataformaSingleton as ProcuraClientePlataformaCAPES).reSelecionarPessoa();
				}
			}
			return pessoaSelecionada;
		}
		
		public function reSelecionarPessoa():void{
			if(pessoaSelecionada != null && pessoaSelecionada.idPessoa != 0){
				lbFiltro.visible = lbFiltro.includeInLayout = false;
				cboCampoProcurar.visible = cboCampoProcurar.includeInLayout = false;
				lbParceiro.visible = lbParceiro.includeInLayout = false;
				campoFiltro.visible = campoFiltro.includeInLayout = false;
				lbNomeParceiro.alpha = .5;
				atualizarLabelParceiro();
				campoFiltro.text = "";
			}
		}
		
		override public function ProcuraClientePlataformaCAPES()
		{
			super();
			
			registerClassAlias("br.com.sicoob.capes.comum.negocio.vo.PessoaPlataformaVO", PessoaPlataformaVO);
			registerClassAlias("br.com.sicoob.capes.cadastro.negocio.vo.ValidacaoCadastralVO", ValidacaoCadastralVO);
			
			procuraClienteLegado = new ProcuraClientePlataformaCAPS();
		}
		
		override public function init():void {
			super.init();
			inicializarServicosCUC();
		}
		
		private function inicializarServicosCUC():void {
			destinoCUC = ConfiguracoesCAPES.destinoCAPES;
			configurarServicoPesquisa();
			configurarServicoResponsavel();
			configurarServicoPessoaLegado();
			MostraCursor.removeBusyCursor();
			liberarGridCacheDataProvider();
			
			super.carregarModuloInclusao();
		}
		
		private function configurarServicoPesquisa():void {
			servicoCUCPesquisa = new ServicoJava();
			servicoCUCPesquisa.source = SERVICO_PROCURAR_PESSOA_CUC;
			servicoCUCPesquisa.bloquearOperacao = false;
			servicoCUCPesquisa.configurarDestino(destinoCUC);
			servicoCUCPesquisa.addEventListener(ResultEvent.RESULT, obterDadosCUC_onResult);
			servicoCUCPesquisa.addEventListener(FaultEvent.FAULT, obterDadosCUC_onError);
			
			barraPaginacao.botAnterior.addEventListener(MouseEvent.MOUSE_DOWN,mouseDownBotaoAnterior);
			barraPaginacao.botPrimeiro.addEventListener(MouseEvent.MOUSE_DOWN,mouseDownBotaoAnterior);
			barraPaginacao.botIrPara.addEventListener(MouseEvent.MOUSE_DOWN,mouseDownBotaoAnterior);
			barraPaginacao.botProximo.addEventListener(MouseEvent.MOUSE_DOWN,mouseDownBotaoAnterior);
			barraPaginacao.botUltimo.addEventListener(MouseEvent.MOUSE_DOWN,mouseDownBotaoAnterior);
			
			barraPaginacao.botAnterior.addEventListener(KeyboardEvent.KEY_DOWN,keyDownBotaoAnterior);
			barraPaginacao.botPrimeiro.addEventListener(KeyboardEvent.KEY_DOWN,keyDownBotaoAnterior);
			barraPaginacao.botIrPara.addEventListener(KeyboardEvent.KEY_DOWN,keyDownBotaoAnterior);
			barraPaginacao.botProximo.addEventListener(KeyboardEvent.KEY_DOWN,keyDownBotaoAnterior);
			barraPaginacao.botUltimo.addEventListener(KeyboardEvent.KEY_DOWN,keyDownBotaoAnterior);
		}
		
		private function configurarServicoBusca():void {
			servicoCUCBusca = new ServicoJava();
			servicoCUCBusca.source = SERVICO_PROCURAR_PESSOA_CUC;
			servicoCUCBusca.bloquearOperacao = false;
			servicoCUCBusca.configurarDestino(destinoCUC);
			servicoCUCBusca.addEventListener(ResultEvent.RESULT, obterPessoaCUC_onResult);
		}
		
		private function configurarServicoResponsavel() : void {
			servicoCUCResponsavel = new ServicoJava();
			servicoCUCResponsavel.source = SERVICO_PROCURAR_PESSOA_CUC;
			servicoCUCResponsavel.bloquearOperacao = false;
			servicoCUCResponsavel.configurarDestino(destinoCUC);
			servicoCUCResponsavel.addEventListener(ResultEvent.RESULT, obterResponsavel_onResult);
		}
		
		private function configurarServicoPessoaLegado() : void {
			servicoCUCPessoaLegado = new ServicoJava();
			servicoCUCPessoaLegado.source = SERVICO_PROCURAR_PESSOA_CUC;
			servicoCUCPessoaLegado.bloquearOperacao = false;
			servicoCUCPessoaLegado.configurarDestino(destinoCUC);
			servicoCUCPessoaLegado.addEventListener(ResultEvent.RESULT, obterPessoaLegado_onResult);
		}
		
		private function obterDadosCUC_onResult(event:ResultEvent):void {
			
			if(event.result.dados.lista.length>(QTD_MAXIMA_REGISTRO-1)){
				Alerta.show(MSG_QTD_MAXIMA_REGISTRO, 
					"Alerta", Alerta.ALERTA_INFORMACAO, null, null);
				event.result.dados.lista.removeItemAt(QTD_MAXIMA_REGISTRO);
				event.result.qtdPaginas = event.result.qtdPaginas-1;
			}
			setGridCacheDataProvider(event.result.dados.lista);
			setGridCacheDataProviderTotalPag(event.result.qtdPaginas);
			this.carregaGridCacheDataProvider(event.result.paginaAtual);
		}
		
		private function carregaGridCacheDataProvider(paginaAtual:int):void {
			barraPaginacao.pagina = paginaAtual;
			barraPaginacao.totalPaginas = getGridCacheDataProviderTotalPag();
			
			vsTelas.selectedIndex = 0;
			
			grdDados.dataProvider = retornaListaPorPagina(paginaAtual);
			
			dispatchEvent(new Event(EVENTO_BLOQUEAR_MENU));
			
			this.cursor.removeBusyCursor();				
			mcBloqueio.visible = false;
			
			focusManager.setFocus(grdDados);
			
			if(getGridCacheDataProvider().length == 1)
			{
				MostraCursor.setBusyCursor("Carregando os dados!", Application.application);
				grdDados.selectedIndex = 0; 
				selecionarItem();
			}			
		}
		
		private function obterDadosCUC_onError(event:FaultEvent):void {
			this.cursor.removeBusyCursor();				
			mcBloqueio.visible = false;
		}
		
		override protected function notificarTrocaDeParceiroViaAcessoRapido(parceiro:Object):void {
			
			ProcuraClientePlataforma.setObjCliente(parceiro);
			
			obterPessoaCUC(parceiro["NUMCGCCPFNUM"]);
		}
		
		private function obterPessoaCUC(cpfcnpj:String):void {
			
			if(servicoCUCBusca == null) {
				configurarServicoBusca();
			}
			
			var req:SelPessoaReqDTO = new SelPessoaReqDTO();
			req.pagina = 1;
			req.listaApenasClientes = false;
			req.tipoPessoa = -1;
			req.tipoProcura = ID_PESQUISA_CPF_CNPJ;
			req.valor = cpfcnpj;
			
			servicoCUCBusca.getOperation("obterDadosSelecaoPlataforma").send(req);
		}
		
		private function obterPessoaCUC_onResult(event:ResultEvent):void {
			
			if(event == null || event.result == null || 
				!event.result.hasOwnProperty("dados") ||
				event.result["dados"] == null ||
				!event.result["dados"].hasOwnProperty("lista") ||
				event.result["dados"]["lista"] == null){
				
				return;
			}
			
			var array:ArrayCollection = event.result["dados"]["lista"] as ArrayCollection;
			
			if(array.length > 0) {
				pessoaSelecionada = array.getItemAt(0) as PessoaPlataformaVO;
				procuraClienteLegado.compatibilizarCAPESReestruturado(pessoaSelecionada);
				
				carregarMenus();
			}
		}
		
		override protected function validarFiltro():Boolean {
			if(cboCampoProcurar.selectedItem.tipoSort != SelecaoGeralConstantes.TIPOSORT_DATE) {
				if(StringUtils.trim(campoFiltro.text).length < 7 &&
					cboCampoProcurar.selectedItem.tipoSort != SelecaoGeralConstantes.TIPOSORT_NUMERIC) {
					Alerta.show("O Filtro precisa conter pelo menos 7 (sete) letras", "Atenção");
					return false;
				}
				if(cboCampoProcurar.selectedItem.tipoSort == SelecaoGeralConstantes.TIPOSORT_NUMERIC &&
					StringUtils.trim(campoFiltro.text).length == 0) {
					Alerta.show("O Filtro precisa conter um valor", "Atenção");
					return false;
				}
			}else{
				if(campoFiltro.selectedDate == null) {
					Alerta.show("O Filtro precisa conter um valor", "Atenção");
					return false;
				}
			}
			
			return true;
		}
		
		override protected function pessoaIncluida(evt:EventData):void {
			
			pessoaSelecionada = evt.data as PessoaPlataformaVO;
			procuraClienteLegado.compatibilizarCAPESReestruturado(pessoaSelecionada);
			
			var event:EventData = new EventData("", pessoaSelecionada.cpfCnpj);
			
			super.pessoaIncluida(event);
		}
		
		override protected function carregarMenus():void {
			MostraCursor.setBusyCursor("Montando Menu!", Application.application);
			
			var arr:ArrayCollection = new ArrayCollection();
			var pfPj:int = pessoaSelecionada != null ? pessoaSelecionada.codTipoPessoa : 0;
			arr.addItem(pfPj == 0 ? "tipoPF" : "tipoPJ");
			
			var app:AplicacaoVO = PortalModel.portal.obterAplicacaoPorId("MDIPLATAFORMACUC");
			
			PortalModel.portal.obterMenusAplicativo(app, montarItemsMenu, arr);
			
			// Notificando os outros aplicativos que a pessoa foi trocada.
			PortalModel.obterAplicativoSelecionado().dispatchEvent(new Event(EVENTO_PESSOA_SELECIONADA));
		}
		
		/**
		 * Método sobreescrito para exibição das informações da validação cadastral.
		 **/
		override protected function montarItemsMenu(evt:ResultEvent):void {
			super.montarItemsMenu(evt);
			
			var pessoa:PessoaPlataformaVO = getPessoaSelecionada();
			
			//Verifica se a pessoa é cliente.
			if(pessoa != null && !isNaN(pessoa.idPessoaInstituicao) && pessoa.idPessoaInstituicao != 0) {
				exibirComponenteValidacaoCadastral();
			}
		}
		
		private function exibirComponenteValidacaoCadastral():void {
			var janelaValidacaoCadastral:Janela = new Janela();
			var validacaoCadastral:ValidacaoCadastral = new ValidacaoCadastral();
			
			janelaValidacaoCadastral.title = "VALIDAÇÃO CADASTRAL";
			janelaValidacaoCadastral.icone = "br/com/bancoob/imagens/icos/apply.png";
			janelaValidacaoCadastral.removeAllChildren();
			janelaValidacaoCadastral.addChild(DisplayObject(validacaoCadastral));
			this.parentDocument.addEventListener(BarraInferiorPlataformas.FECHAR_PLATAFORMA, validacaoCadastral.fechar);
			this.parentDocument.addEventListener(BarraInferiorPlataformas.VOLTAR_SISBR, validacaoCadastral.fechar);
			janelaValidacaoCadastral.abrir(DisplayObject(Application.application), false, true);
		}
		
		override protected function atualizarLabelParceiro():void {
			
			lbNomeParceiro.text = pessoaSelecionada.nomePessoa;
			
			lbNomeParceiro.validateNow();
			lbNomeParceiro.width = lbNomeParceiro.textWidth + 10;
			lbNomeParceiro.visible = lbNomeParceiro.includeInLayout = true;
		}
		
		override protected function carregarDefGrid():void {
			
			super.carregarDefGrid();
			
			(grdDados.columns[2] as DataGridColumn).labelFunction = formatarCPFCNPJ;
		}
		
		private function formatarCPFCNPJ(registro:Object, coluna:DataGridColumn):String {
			return FormatUtil.formataCPFCNPJ(registro[coluna.dataField]);
		}
		
		override protected function exibirCampoFiltroPadrao():void {
			if((cboCampoProcurar.dataProvider as ArrayCollection).length > 0){
				cboCampoProcurar.selectedIndex = 2;
				definirCampoFiltro(cboCampoProcurar.selectedItem);
			}
		}
		
		override protected function recuperarDefinicoesGrid():SelecaoGeralDTO
		{
			var arrColunas:ArrayCollection = new ArrayCollection();
			
			arrColunas.addItem({campo: CAMPO_NOME_PESSOA,
				campoOrdenacao:null,
				colunaCombo:-1,
				formato:null,
				incremental:true,
				index:1,
				laguraColuna:4600,
				naoVisivelProcura:false,
				pesquisarComMascara:false,
				procuraColunaRetorno:0,
				procurarPor:"",
				tipoAlinhamento:0,
				tipoAlinhamentoCabecalho:0,
				tipoColunaGrid:0,
				tipoFormatoTexto:2,
				tipoSort:1,
				titulo:"Nome/Razão Social",
				visivel:true});
			
			arrColunas.addItem({campo: CAMPO_APELIDO,
				campoOrdenacao:null,
				colunaCombo:-1,
				formato:null,
				incremental:true,
				index:2,
				laguraColuna:4600,
				naoVisivelProcura:false,
				pesquisarComMascara:false,
				procuraColunaRetorno:0,
				procurarPor:"",
				tipoAlinhamento:0,
				tipoAlinhamentoCabecalho:0,
				tipoColunaGrid:0,
				tipoFormatoTexto:1,
				tipoSort:1,
				titulo:"Nome de Tratamento/Nome Fantasia",
				visivel:true});
			
			arrColunas.addItem({campo: CAMPO_CPF_CNPJ,
				campoOrdenacao:null,
				colunaCombo:-1,
				formato:null,
				incremental:true,
				index:3,
				laguraColuna:2500,
				naoVisivelProcura:false,
				pesquisarComMascara:false,
				procuraColunaRetorno:0,
				procurarPor:"",
				tipoAlinhamento:0,
				tipoAlinhamentoCabecalho:0,
				tipoColunaGrid:0,
				tipoFormatoTexto:1,
				tipoSort:2,
				titulo:"CPF/CNPJ",
				visivel:true});
			
			var selDef:SelecaoGeralDTO = new SelecaoGeralDTO();
			selDef.colunaAtual = 0;
			selDef.colunaPadrao = 0;
			selDef.desabilitarFiltro = false;
			selDef.numeroColunas = 4;
			selDef.numeroLista = 0;
			selDef.colunas = arrColunas;
			
			return selDef;
		}
		
		override public function botProcurar_click(evt:MouseEvent=null):void {
			
			//if(pessoaSelecionada != null && pessoaSelecionada.idPessoa != 0){
			if(isPessoaSelecionada){
				aparecerPesquisa();
			}
			else
			{
				if(!validarFiltro())
					return;
				
				paginaPesquisa = 1;
				abrirPesquisa();
				
				this.dispatchEvent(new EventoProcurar(pegarValorFiltro(), cboCampoProcurar.selectedItem.index, 1));
			}
		}
		
		override protected function aparecerPesquisa():void{
			isPessoaSelecionada = false;
//			pessoaSelecionada = null;
//			procuraClienteLegado.compatibilizarCAPESReestruturado(pessoaSelecionada);
			super.aparecerPesquisa();
		}
		
		override protected function procurarDados(pag:int):void {
			if(!validarFiltro())
				return;
			
			if(isBarraPaginacao){
				isBarraPaginacao = false;
				carregaGridCacheDataProvider(pag);
			}else{
				pesquisarPessoasCUC(pag);
			}
		}
		
		private function pesquisarPessoasCUC(pagina:int):void {
			
			this.cursor.setBusyCursor();				
			mcBloqueio.visible = true;
			
			var req:SelPessoaReqDTO = montarDTOConsulta(pagina);
			
			pesquisarPessoaInstituicao(req);
		}
		
		private function pesquisarPessoaInstituicao(dto:SelPessoaReqDTO):void {
			servicoCUCPesquisa.getOperation("obterDadosSelecaoPlataforma").send(dto);
		}
		
		private function montarDTOConsulta(pagina:int):SelPessoaReqDTO {
			var req:SelPessoaReqDTO = new SelPessoaReqDTO();
			req.pagina = pagina;
			req.listaApenasClientes = false;
			req.tipoPessoa = -1;
			req.tipoProcura = obterTipoProcuraCUC();
			req.valor = pegarValorFiltro();
			
			return req;
		}
		
		private function obterTipoProcuraCUC():String {
			
			var labelCUC:String;
			
			switch(cboCampoProcurar.selectedItem["campo"]) {
				
				case CAMPO_NOME_PESSOA:
					labelCUC = ID_PESQUISA_NOME;
					break;
				
				case CAMPO_CPF_CNPJ:
					labelCUC = ID_PESQUISA_CPF_CNPJ;
					break;
				
				case CAMPO_APELIDO:
					labelCUC = ID_PESQUISA_APELIDO;
					break;
			}
			
			return labelCUC;
			
		}
		
		override protected function selecionarItem():void {
			if(grdDados.selectedItem)
			{
				pessoaSelecionada = grdDados.selectedItem as PessoaPlataformaVO;
				isPessoaSelecionada = true;
				procuraClienteLegado.compatibilizarCAPESReestruturado(pessoaSelecionada);
				
				var req:RequisicaoReqDTO = new RequisicaoReqDTO();
				req.dados.idPessoaCompartilhamento = pessoaSelecionada.idCompartilhamento;
				req.dados.codTipoPessoa = pessoaSelecionada.codTipoPessoa;
				servicoCUCResponsavel.getOperation("obterInstituicaoResponsavelCadastro").send(req);
			}
		}
		
		private function obterResponsavel_onResult(event:ResultEvent):void {
			
			var isResponsavel: Boolean = (event.result.dados["responsavel"]) as Boolean;
			
			if(!isResponsavel && !pessoaSelecionada.utilizaGedGft) {
				Alerta.show("Esta instituição não é a responsável por este cadastro, você terá "
					+ "direito apenas de visualização. Para alterar procure a instituição responsável.", 
					"Alerta", Alerta.ALERTA_INFORMACAO, null, null);
			}
			
			obterPessoaLegado();
		}
		
		private function obterPessoaLegado():void {
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["cpfCnpj"] = pessoaSelecionada.cpfCnpj;
			dto.dados["idInstituicao"] = pessoaSelecionada.idInstituicao;
			servicoCUCPessoaLegado.getOperation("obterDadosPessoaLegado").send(dto);
		}
		
		private function obterPessoaLegado_onResult(evt:ResultEvent):void{
			
			parceiroSelecionado = evt.result.dados["pessoaLegado"];
			carregarMenus();
		}
		
		override protected function carregarModuloInclusao():void {
			
		}
		
		override protected function configurarCampoFiltro():void {
			super.configurarCampoFiltro();
			
			if(cboCampoProcurar.selectedItem != null &&
				cboCampoProcurar.selectedItem["campo"] == CAMPO_CPF_CNPJ){
				(campoFiltro as Texto).maxChars = 14;
			} else if(cboCampoProcurar.selectedItem != null &&
				cboCampoProcurar.selectedItem["campo"] == CAMPO_APELIDO){
				(campoFiltro as Texto).maxChars = 30;
			}else if(cboCampoProcurar.selectedItem != null &&
				cboCampoProcurar.selectedItem["campo"] == CAMPO_NOME_PESSOA){
				(campoFiltro as Texto).maxChars = 50;
			}
		}
	}
}