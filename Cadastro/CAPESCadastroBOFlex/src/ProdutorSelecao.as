package
{
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.Botao;
	import br.com.bancoob.componentes.botoes.BotaoPlataforma;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.sisbr.componentes.pti.ObjetoEvent;
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastrarProdutor.BotoesOpcoesProdutor;
	import br.com.sicoob.capes.cadastrarProdutor.frmEditarProdutividade;
	import br.com.sicoob.capes.cadastrarProdutor.frmListarProdutividade;
	import br.com.sicoob.capes.cadastrarProdutor.janelas.FinalizarExploracao;
	import br.com.sicoob.capes.comum.enums.SituacaoRegistroEnum;
	import br.com.sicoob.capes.comum.vo.entidades.CategoriaProdutorVO;
	import br.com.sicoob.capes.comum.vo.entidades.EmpreendimentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.FinalidadeEmpreendimentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.ProdutividadeBaseVO;
	import br.com.sicoob.capes.comum.vo.entidades.ProdutividadeVO;
	import br.com.sicoob.capes.comum.vo.entidades.ProdutorBaseVO;
	import br.com.sicoob.capes.comum.vo.entidades.ProdutorVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoBeneficiarioSicorVO;
	import br.com.sicoob.capes.comum.vo.entidades.UnidadeMedidaVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.BemImovelAvaliacaoRuralVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.BemImovelAvaliacaoUrbanoVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.BemImovelAvaliacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.BemImovelTipoRelacionamentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.BemImovelVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.BemPessoaCompartilhamentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.BemVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoBemImovelVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoClassificacaoBemVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoEstadoConservacaoBemVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoImplantacaoBemImovelVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoLocalizacaoBemVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoOnusBemVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoPadraoAcabamentoBemImovelVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoRelacionamentoBemImovelVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoServicoCondominialBemImovelVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoUsoBemImovelVO;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.BemImovelVO;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.BemOnusVO;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.BemPosseVO;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.BemRegistroVO;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.BemVO;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.SituacaoBemVO;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.SubTipoBemVO;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.TipoBemVO;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.TipoPosseBemVO;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCAPESBase;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.events.ListEvent;
	
	public class ProdutorSelecao extends TelaPlataformaAtendimentoCAPESBase {
		
		private var telaLista:frmListarProdutividade = new frmListarProdutividade();
		private var telaEdicao:frmEditarProdutividade = new frmEditarProdutividade();
		private var botoesOpcoes:BotoesOpcoesProdutor = new BotoesOpcoesProdutor();
		
		public static const CLASSE_SERVICO_PRODUTOR: String = 
			"br.com.sicoob.capes.cadastro.fachada.ProdutorFachada";
		public static const CLASSE_SERVICO_PRODUTIVIDADE: String = 
			"br.com.sicoob.capes.cadastro.fachada.ProdutividadeFachada";
		
		private var telaFinalizarExploracao:FinalizarExploracao = new FinalizarExploracao();
		private var janelaFinalizar:Janela = null;
		public var renovacaoExploracaoClicado: Boolean = false;
		
		public function ProdutorSelecao() {
			super();
			registrarClasses();
			
			this.creationPolicy = "all";
			this.addEventListener("carregou", init);
		}
		
		private function registrarClasses(): void {
			include 'br/com/sicoob/capes/utils/RegistroClasses.as';
			
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bemantigo.Bem", br.com.sicoob.capes.comum.vo.entidades.bemantigo.BemVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bemantigo.BemImovel", br.com.sicoob.capes.comum.vo.entidades.bemantigo.BemImovelVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bemantigo.BemOnus", BemOnusVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bemantigo.BemPosse", BemPosseVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bemantigo.BemRegistro", BemRegistroVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bemantigo.SituacaoBem",SituacaoBemVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bemantigo.SubTipoBem", SubTipoBemVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bemantigo.TipoBem", TipoBemVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bemantigo.TipoPosseBem", TipoPosseBemVO);
			
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.TipoBemImovel", TipoBemImovelVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.TipoClassificacaoBem", TipoClassificacaoBemVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.TipoLocalizacaoBem", TipoLocalizacaoBemVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.TipoOnusBem", TipoOnusBemVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.TipoUsoBemImovel", TipoUsoBemImovelVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.TipoPadraoAcabamentoBemImovel", TipoPadraoAcabamentoBemImovelVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.TipoEstadoConservacaoBem", TipoEstadoConservacaoBemVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.TipoImplantacaoBemImovel", TipoImplantacaoBemImovelVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.TipoServicoCondominialBemImovel", TipoServicoCondominialBemImovelVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.TipoRelacionamentoBemImovel", TipoRelacionamentoBemImovelVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.Bem", br.com.sicoob.capes.comum.vo.entidades.bem.BemVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.BemImovel", br.com.sicoob.capes.comum.vo.entidades.bem.BemImovelVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.BemImovelTipoRelacionamentoPessoa", BemImovelTipoRelacionamentoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.BemImovelAvaliacao", BemImovelAvaliacaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.BemImovelAvaliacaoRural", BemImovelAvaliacaoRuralVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.BemImovelAvaliacaoUrbano", BemImovelAvaliacaoUrbanoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.BemPessoaCompartilhamento", BemPessoaCompartilhamentoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.CategoriaProdutor", CategoriaProdutorVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Empreendimento", EmpreendimentoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.FinalidadeEmpreendimento", FinalidadeEmpreendimentoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.ProdutividadeBase", ProdutividadeBaseVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.vigente.Produtividade", ProdutividadeVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.ProdutorBase", ProdutorBaseVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.vigente.Produtor", ProdutorVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.UnidadeMedida",UnidadeMedidaVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoBeneficiarioSicor", TipoBeneficiarioSicorVO);
		}
		
		private function init(evt:Event):void {
			
			this.textoAcao = "PRODUÇÃO";
			this.textoModulo = "PRODUÇÕES";
			this.iconModulo = "br/com/bancoob/imagens/icos/produtor.png";
			
			telaLista.addEventListener(ListEvent.CHANGE, registroSelecionado);
			telaLista.addEventListener(frmListarProdutividade.CLICK_ABAS, clickAbas);
			telaLista.addEventListener(frmListarProdutividade.VERIFICAR_BOTOES_AUTORIZACAO, verificarBotoesAutorizacao);
			
			telaLista.addEventListener(telaLista.VERIFICAR_BOTAO_GEDGFT, verificarExibirBotaoGedGft);
			
			telaEdicao.addEventListener(REGISTRO_GRAVADO, registroGravado);
			telaEdicao.addEventListener(REGISTRO_CARREGADO, registroCarregado);
			telaFinalizarExploracao.addEventListener(FinalizarExploracao.FINALIZAR_EXPLORACAO, finalizarExploracaoConfirmada); 
			
			this.listaBotoes.botNovo.addEventListener(MouseEvent.CLICK, clickBotoes);
			this.listaBotoes.botVoltar.addEventListener(MouseEvent.CLICK, clickBotoes);
			
			incluirBotoesAdicionais();
			this.setTelaLista(telaLista);	
			this.setTelaEdicao(telaEdicao);
			exibirBotoesAdicionais();
		}
		
		public override function dispose():void {
			super.dispose();
			this.removeEventListener("carregou", init);
			telaLista.dispose();
			telaEdicao.dispose();
			botoesOpcoes = null;
			telaEdicao = null;
		}
		
		//--------------------------------------------------------------------------
		//  Configuração de destino dos serviços.
		//--------------------------------------------------------------------------		
		protected override function configurarDestinosServicos(destinoVO:DestinoVO):void{
			telaLista.configurarDestinosServicos(destinoVO);
			telaEdicao.configurarDestinosServicos(destinoVO);
		}
		
		//--------------------------------------------------------------------------
		//  Métodos auxiliares
		//--------------------------------------------------------------------------
		private function incluirBotoesAdicionais(): void {
			this.setBotoesAdicionais(botoesOpcoes);
			botoesOpcoes.btFinalizar.addEventListener(MouseEvent.CLICK, finalizarExploracaoClicado);
			botoesOpcoes.btRenovar.addEventListener(MouseEvent.CLICK, renovarExploracaoClicado);
			
			botoesOpcoes.botoesAutorizacao.bt_GED_GFT.addEventListener(MouseEvent.CLICK, btVisualizarDocumentoClicado);
			
			botoesOpcoes.botoesAutorizacao.bt_A_ENCAMINHAR.addEventListener(MouseEvent.CLICK, onBotaoAprovacaoClicado);
			botoesOpcoes.botoesAutorizacao.bt_BLOQUEADO.addEventListener(MouseEvent.CLICK, onBotaoAprovacaoClicado);
			botoesOpcoes.botoesAutorizacao.bt_DEVOLVIDO.addEventListener(MouseEvent.CLICK, onBotaoAprovacaoClicado);
			botoesOpcoes.botoesAutorizacao.bt_EM_AUTORIZACAO.addEventListener(MouseEvent.CLICK, onBotaoAprovacaoClicado);
		}	
		
		protected override function mostraBotoesLista():void	{
			super.mostraBotoesLista();
			itemLista = null;
			exibirBotoesAdicionais();
		}
		
		protected override function mostraBotoesEdicao(consulta:Boolean=false):void {
			super.mostraBotoesEdicao(consulta);
			exibirBotoesAdicionais();
		}
		
		protected override function mostraBotoesMudaGrid(evt:ListEvent = null):void{
			super.mostraBotoesMudaGrid(evt);
			exibirBotoesAdicionais();
		}
		
		//--------------------------------------------------------------------------
		//  Listener 
		//--------------------------------------------------------------------------		
		private function registroSelecionado(evt:ListEvent):void {
			itemLista = (evt.target as frmListarProdutividade).obterGrid().selectedItem;
			mostraBotoesMudaGrid();
		}	    
		
		private function finalizarExploracaoConfirmada(evt:Event=null): void{
			
			var percentual: Number = telaFinalizarExploracao.txtPercentual.valor
			var houveFrustracao: Boolean = telaFinalizarExploracao.rdbSimNao.selectedValue;
			var dataOcorrencia: IDateTime = DateTimeBase.getDateTime(telaFinalizarExploracao.txtDataOcorrencia.selectedDate);
			telaEdicao.finalizarExploracao(itemLista.idProdutividade, percentual, houveFrustracao, dataOcorrencia);
		}
		
		protected override function registroCarregado(evt:Event):void {
			
			super.registroCarregado(evt);
			if(renovacaoExploracaoClicado) {
				telaEdicao.setProdutor(telaLista.getProdutor());
				telaEdicao.txtAnoInicio.text = '';
				telaEdicao.txtAnoFim.text = '';
				renovacaoExploracaoClicado = false;
				telaEdicao.isRegistroNovo(true);
			}
		}
		
		private function clickAbas(evento:Event) : void{
			mostraBotoesLista();
		}
		
		//--------------------------------------------------------------------------
		//  Tratamento dos botões.
		//--------------------------------------------------------------------------			
		private function renovarExploracaoClicado(evt:MouseEvent = null): void{
			telaEdicao.carregarRegistro(itemLista);
			telaEdicao.isRegistroNovo(true);
			renovacaoExploracaoClicado = true;
		}		    
		
		private function finalizarExploracaoClicado(evt:MouseEvent = null): void{
			if(janelaFinalizar == null){
				janelaFinalizar = new Janela();
				janelaFinalizar.addChild(telaFinalizarExploracao);
				janelaFinalizar.title = "FINALIZAR EXPLORAÇÃO";
				janelaFinalizar.icone = "br/com/bancoob/imagens/icos/Import.png";				
			}
			telaFinalizarExploracao.iniciar();
			janelaFinalizar.abrir(this,true,true);
		}	
		
		private function onBotaoAprovacaoClicado(evt:MouseEvent):void{
			var botao:BotaoPlataforma = BotaoPlataforma(evt.currentTarget);
			if(botoesOpcoes.botoesAutorizacao.bt_BLOQUEADO == botao){
				telaLista.setarProdutorVigente(true);
			}else{ 
				telaLista.setarProdutorVigente(false);
			}
		}
		
		private function verificarExibirBotaoGedGft(evento:ObjetoEvent):void{
			botoesOpcoes.botoesAutorizacao.bt_GED_GFT.visible = 
				botoesOpcoes.botoesAutorizacao.bt_GED_GFT.includeInLayout = evento.objeto as Boolean;
		}
		
		//--------------------------------------------------------------------------
		//  Controle de exibição de botões.
		//--------------------------------------------------------------------------	
		private function exibirBotoesAdicionais(): void {
			var exibirRenovar: Boolean = false;
			var exibirFinalizar: Boolean = false;
			
			if(vsTelas.selectedIndex == 0) {
				exibirRenovar = itemLista != null && itemLista.situacaoAprovacao.name == SituacaoRegistroEnum.VIGENTE.name;
				exibirFinalizar = itemLista != null && itemLista.codSituacao == 1 && itemLista.situacaoAprovacao.name == SituacaoRegistroEnum.VIGENTE.name;
			}
			
			botoesOpcoes.btRenovar.enabled = exibirRenovar;
			botoesOpcoes.btFinalizar.enabled = exibirFinalizar;
		}
		
		private function verificarBotoesAutorizacao(evento:ObjetoEvent):void{
			botoesOpcoes.botoesAutorizacao.situacao = evento.objeto.situacao as SituacaoRegistroEnum;
			botoesOpcoes.botoesAutorizacao.bt_GED_GFT.visible = 
				botoesOpcoes.botoesAutorizacao.bt_GED_GFT.includeInLayout = evento.objeto.exibir as Boolean;
		}
		
		override protected function abrirInclusao():void {
			var produtor:ProdutorVO = telaLista.getProdutor();
			
			if(produtor == null || isNaN(produtor.id) || produtor.dataHoraInicio == null){
				Alerta.show("É necessário cadastrar um produtor antes de inserir uma produtividade.", "Produtor não cadastrado");
				exibirBotoesAutorizacao(true);
				return;
			}
			
			telaEdicao.setProdutor(produtor);
			telaEdicao.limparLabelEmpreendimento();
			
			super.abrirInclusao();
		}
		
		override protected function alterarClicado(event:Event=null):void {
			telaEdicao.setProdutor(telaLista.getProdutor());
			super.alterarClicado(event);
		}
		
		protected function btVisualizarDocumentoClicado(event:MouseEvent=null):void {
			this.telaLista.exibirDetalhamentoGedGft();
		}
		
		private function clickBotoes(evt:MouseEvent):void{
			var botao:Botao = Botao(evt.target);
			
			if(this.listaBotoes.botNovo == botao){
				exibirBotoesAutorizacao(false);
			}else if(this.listaBotoes.botVoltar == botao){ 
				exibirBotoesAutorizacao(true);
			}
		}
		
		private function exibirBotoesAutorizacao(valor:Boolean):void{
			botoesOpcoes.botoesAutorizacao.visible = 
				botoesOpcoes.botoesAutorizacao.includeInLayout = valor;
		}
		
		override protected function registroGravado(event:Event):void {
			super.registroGravado(event);
			
			verificarOperacaoPendente();
			
			exibirBotoesAutorizacao(true);
		}
		
		protected override function registroExcluido(event : Event) : void {
			super.registroExcluido(event);
			this.telaLista.obterGrid().selectedIndex = -1;
			this.telaLista.obterGrid().setFocus();
			this.telaLista.obterLista();
		}
		
	}
}