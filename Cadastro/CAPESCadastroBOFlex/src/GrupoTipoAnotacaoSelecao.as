package {
	
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.painellista.PainelListaBanco;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.dto.RequisicaoDTO;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.StringUtils;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.CategoriaAnotacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.CooperativaAnotacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.GrupoTipoAnotacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.MapaTipoAnotacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.OrigemInformacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PeriodicidadeAnotacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoAnotacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoCapturaVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoConsultaOrigemVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoObservacaoAnotacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.pk.CooperativaAnotacaoPK;
	import br.com.sicoob.capes.grupoTipoAnotacao.GrupoTipoAnotacaoEdicao;
	import br.com.sicoob.capes.grupoTipoAnotacao.GrupoTipoAnotacaoView;
	import br.com.sicoob.capes.grupoTipoAnotacao.PainelFiltroView;
	
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	public class GrupoTipoAnotacaoSelecao extends GrupoTipoAnotacaoView {
		
		private static const CLASSE_SERVICO: String = "br.com.sicoob.capes.cadastro.fachada.GrupoTipoAnotacaoFachada";
		private static const DESTINO_CAPES : String = "destinoCAPES";
		private var servicoDefinicoes:ServicoJava = new ServicoJava();
		
		public function GrupoTipoAnotacaoSelecao() {
			super();
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
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.GrupoTipoAnotacao", GrupoTipoAnotacaoVO);
		}

		protected override function init(event : FlexEvent) : void {
			super.init(event);
			
			servicoDefinicoes = ServicoJavaUtil.getServico(CLASSE_SERVICO, "Obtendo definições...");
			servicoDefinicoes.addEventListener(ResultEvent.RESULT, retorno_obter_definicoes);
			
			servicoExclusao = new ServicoJava();
			servicoExclusao.source = CLASSE_SERVICO;
			ServicoJava(this.servicoExclusao).bloquearOperacao = true;
			ServicoJava(this.servicoExclusao).mensagemEspera = "Excluindo dados...";
			
			var painelLista:PainelListaBanco = PainelListaBanco(this.painelLista)
			painelLista.funcaoCriacaoDto = instanciarDtoConsulta;
			painelLista.funcaoConfiguracaoDto = configurarDtoConsulta;
			
			this.barraBotoesListaCadastro.exibirBotaoAjuda = false;
			
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], destino_recuperado);
		}
		
		public function instanciarDtoConsulta(): ConsultaDto {
			return new ConsultaDto();
		}
		
		public function configurarDtoConsulta(dto:ConsultaDto): void {
			var filtro:GrupoTipoAnotacaoVO = new GrupoTipoAnotacaoVO();
			
			if (StringUtils.trim(painelFiltro.campoNome.text) != "") {
				filtro.nome = painelFiltro.campoNome.text;
			}
			
			dto.filtro = filtro;
		}
		
		protected override function montarDtoExclusao(item:Object):RequisicaoDTO {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados[GrupoTipoAnotacaoEdicao.CHAVE_MAPA] = item;
			return dto;
		}
		
		private function get painelFiltro():PainelFiltroView {
			return this.painelLista.painelFiltro as PainelFiltroView;
		}
		
		private function get telaEdicao():GrupoTipoAnotacaoEdicao {
			return GrupoTipoAnotacaoEdicao(formularioCadastro);
		}
		
		private function destino_recuperado(destino:DestinoVO):void {
			PainelListaBanco(this.painelLista).servicoPesquisa.configurarDestino(destino);
			ServicoJava(this.servicoExclusao).configurarDestino(destino);
			servicoDefinicoes.configurarDestino(destino);
			obterDefinicoes();
		}
		
		private function obterDefinicoes():void {
			var requisicaoDto:RequisicaoReqDTO = new RequisicaoReqDTO();			
			MostraCursor.setBusyCursor("Obtendo definições...", this, MostraCursor.CURSOR_PESQUISAR);
			servicoDefinicoes.obterDefinicoes(requisicaoDto);
		}
		
		private function retorno_obter_definicoes(event:ResultEvent) : void {
			PainelListaBanco(this.painelLista).pesquisar(1);
		}
	}
}