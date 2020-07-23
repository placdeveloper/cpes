package {
	
	import br.com.bancoob.componentes.Botao;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.painellista.PainelListaBanco;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.dto.RequisicaoDTO;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.app.Application;
	import br.com.sicoob.capes.cadastrarTipoAnotacao.PainelFiltroTipoAnotacaoView;
	import br.com.sicoob.capes.cadastrarTipoAnotacao.TipoAnotacaoSelecaoView;
	import br.com.sicoob.capes.comum.enums.TipoAplicacaoEnum;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.CategoriaAnotacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.CooperativaAnotacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.MapaTipoAnotacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.OrigemInformacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PeriodicidadeAnotacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoAnotacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoCapturaVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoConsultaOrigemVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoObservacaoAnotacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.pk.CooperativaAnotacaoPK;
	
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.containers.HBox;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	public class TipoAnotacaoSelecao extends TipoAnotacaoSelecaoView {
		
		private static const SERVICO:String = "br.com.sicoob.capes.cadastro.fachada.TipoAnotacaoFachada";
		private static const SERVICO_RELATORIO:String = "br.com.sicoob.capes.relatorio.negocio.fachada.TipoAnotacaoFachada";
		private static const OPERACAO_OBTER_DADOS_SELECAO:String = "obterDadosSelecao";
		private static const DESTINO_CAPES:String = "destinoCAPES";
		private static const OBTER_DEFINICOES:String = "obterDefinicoes";
		
		private var servico:ServicoJava;
		
		//**************
		// Construtores:
		//**************
		
		public function TipoAnotacaoSelecao() {
			super();
			include 'br/com/sicoob/capes/utils/RegistroClasses.as';
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.CategoriaAnotacao", CategoriaAnotacaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.OrigemInformacao", OrigemInformacaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.PeriodicidadeAnotacao", PeriodicidadeAnotacaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.pk.CooperativaAnotacaoPK", CooperativaAnotacaoPK);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.CooperativaAnotacao", CooperativaAnotacaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoObservacaoAnotacao", TipoObservacaoAnotacaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoAnotacao", TipoAnotacaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoCaptura", TipoCapturaVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.MapaTipoAnotacao", MapaTipoAnotacaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoConsultaOrigem", TipoConsultaOrigemVO);
		}

		//**************
		// Eventos:
		//**************				

		protected override function init(event: FlexEvent):void {
			super.init(event);
			
			PainelListaBanco(this.painelLista).funcaoCriacaoDto = instanciarDTOConsulta;
			PainelListaBanco(this.painelLista).funcaoConfiguracaoDto = configurarDtoConsulta;

			this.barraBotoesListaCadastro.exibirBotaoAjuda = false;
			
			// Customização do botão de exclusão
			this.barraBotoesListaCadastro.labelBotaoExcluir = "Inativar";
			this.tituloExclusao = "Inativação";
			this.mensagemConfirmacaoExclusao = "Deseja realmente inativar o item?";
			
			// configuração do botão para geração do relatório
			var botaoRelatorio:Botao = new Botao();
			botaoRelatorio.label = "Emitir Relatório";
			botaoRelatorio.addEventListener(MouseEvent.CLICK, botaoGerarRelatorioPressionado);
			botaoRelatorio.height = 22;
			var box:HBox = (this.barraBotoesListaCadastro.getChildAt(0) as HBox);
			box.addChildAt(botaoRelatorio, 0);
			
			servico = ServicoJavaUtil.getServico(SERVICO, "Obtendo definições...", ResultEvent.RESULT, retornoObterDefinicoes);
			
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], destino_recuperado);
		}
		
		private function destino_recuperado(destino:DestinoVO):void {
			PainelListaBanco(this.painelLista).servicoPesquisa.configurarDestino(destino);
			ServicoJava(this.servicoRecuperacaoInformacoes).configurarDestino(destino);
			ServicoJava(this.servicoExclusao).configurarDestino(destino);
			servico.configurarDestino(destino);
			this.destino = destino;
			obterDefinicoes();
		}
		
		private function obterDefinicoes():void {
			MostraCursor.setBusyCursor("Obtendo definições...", Application.application, MostraCursor.CURSOR_PROGRESSO);
			servico.getOperation(OBTER_DEFINICOES).send(new RequisicaoReqDTO());
		}
		
		private function retornoObterDefinicoes(event:ResultEvent): void {
			MostraCursor.removeBusyCursor();
			painelFiltro.comboCategoria.dataProvider = event.result.dados.listaCategoria;
			painelFiltro.comboTipoCaptura.dataProvider = event.result.dados.listaTipoCaptura;
			painelFiltro.comboTipoPessoa.dataProvider = obterListaTipoAplicacao();
			
			PainelListaBanco(this.painelLista).pesquisar(1);
		}

		//***************************************
		// Métodos Herdados:
		//***************************************
		
		override protected function montarDtoExclusao(item:Object):RequisicaoDTO {
			var vo: TipoAnotacaoVO = new TipoAnotacaoVO();
			vo.codTipoAnotacao = item.codTipoAnotacao;
			
			// Monta o DTO, com o VO, para retorno
			var dto: RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.tipoAnotacao = vo;
			return dto;
		}
		
		override protected function montarDtoRecuperacao(item:Object):RequisicaoDTO {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.tipoAnotacao = item as TipoAnotacaoVO;
			return dto;
		}
		
		private function configurarDtoConsulta(dto:ConsultaDto): void {
			var filtro:TipoAnotacaoVO = new TipoAnotacaoVO();
			
			if(painelFiltro.campoCodigo.valor != 0){
				filtro.codTipoAnotacao = painelFiltro.campoCodigo.valor;
			}
			
			if(painelFiltro.campoDescricao.text != ""){
				filtro.descTipoAnotacao = painelFiltro.campoDescricao.text;
			}
			
			if(painelFiltro.comboCategoria.selectedItem != null){
				filtro.categoriaAnotacao = painelFiltro.comboCategoria.selectedItem as CategoriaAnotacaoVO;
			}
			
			if(painelFiltro.comboTipoCaptura.selectedItem != null){
				filtro.tipoCaptura = painelFiltro.comboTipoCaptura.selectedItem as TipoCapturaVO;
			}
			
			if(painelFiltro.comboTipoPessoa.selectedItem != null){
				filtro.idTipoAplicacao = painelFiltro.comboTipoPessoa.selectedItem.codigo;
			}
			
			dto.filtro = filtro;
		}
		
		private function get painelFiltro():PainelFiltroTipoAnotacaoView {
			return this.painelLista.painelFiltro as PainelFiltroTipoAnotacaoView;
		}
		
		private function obterListaTipoAplicacao():ArrayCollection {
			var lista:ArrayCollection = new ArrayCollection();
			
			for each(var tipoAplicacao:TipoAplicacaoEnum in TipoAplicacaoEnum.constants){
				var objeto:Object = new Object();
				objeto.codigo = tipoAplicacao.idTipoAplicacao;
				objeto.descricao = tipoAplicacao.descricao;
				lista.addItem(objeto);
			}
			
			return lista;
		}

		//***************************************
		// Métodos Auxiliares
		//***************************************

		public function instanciarDTOConsulta(): ConsultaDto {
			return new ConsultaDto();
		}
		
		public function botaoGerarRelatorioPressionado(event : MouseEvent):void {
			RelatorioUtil.create().emitirRelatorio("capes/relatorio/RelatorioTipoAnotacaoServicoRemote", new ParametroDTO(), 
				"relatorioTipoAnotacao", destino, "Emitindo relatório...", "PDF", false);
		}
	}
}