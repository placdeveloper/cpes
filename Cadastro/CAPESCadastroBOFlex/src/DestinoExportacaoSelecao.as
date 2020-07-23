package {
	
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.painellista.PainelListaBanco;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.dto.RequisicaoDTO;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.tipos.Booleano;
	import br.com.bancoob.util.StringUtils;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.enums.CodificacaoArquivoEnum;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.DestinoExportacaoVO;
	import br.com.sicoob.capes.destinoExportacao.DestinoExportacaoEdicao;
	import br.com.sicoob.capes.destinoExportacao.DestinoExportacaoView;
	import br.com.sicoob.capes.destinoExportacao.PainelFiltroView;
	
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	public class DestinoExportacaoSelecao extends DestinoExportacaoView {
		
		private static const CLASSE_SERVICO: String = "br.com.sicoob.capes.cadastro.fachada.DestinoExportacaoFachada";
		private static const DESTINO_CAPES : String = "destinoCAPES";
		private var servicoDefinicoes:ServicoJava = new ServicoJava();
		
		public function DestinoExportacaoSelecao() {
			super();
			registerClassAlias("br.com.sicoob.capes.comum.negocio.enums.CodificacaoArquivoEnum", CodificacaoArquivoEnum);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.DestinoExportacao", DestinoExportacaoVO);
		}

		protected override function init(event : FlexEvent) : void {
			super.init(event);
			
			servicoDefinicoes = ServicoJavaUtil.getServico(CLASSE_SERVICO, "Obtendo definições...");
			servicoDefinicoes.addEventListener(ResultEvent.RESULT, retorno_obter_definicoes);
			
			servicoExclusao = new ServicoJava();
			servicoExclusao.source = CLASSE_SERVICO;
			ServicoJava(this.servicoExclusao).bloquearOperacao = true;
			ServicoJava(this.servicoExclusao).mensagemEspera = "Excluindo dados...";
			
			var painelLista : PainelListaBanco = PainelListaBanco(this.painelLista)
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
			var filtro:DestinoExportacaoVO = new DestinoExportacaoVO();
			
			if (StringUtils.trim(painelFiltro.campoDescricao.text) != "") {
				filtro.descricao = painelFiltro.campoDescricao.text;
			}
			
			if (painelFiltro.comboAtivo.valorSelecionado != null) {
				filtro.ativo = new Booleano(painelFiltro.comboAtivo.valorSelecionado == "true"); 
			}
			
			dto.filtro = filtro;
		}
		
		protected override function montarDtoExclusao(item:Object):RequisicaoDTO {
			var dto : RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados[DestinoExportacaoEdicao.CHAVE_MAPA] = item;
			return dto;
		}
		
		private function get painelFiltro() : PainelFiltroView {
			return this.painelLista.painelFiltro as PainelFiltroView;
		}
		
		private function get telaEdicao(): DestinoExportacaoEdicao {
			return DestinoExportacaoEdicao(formularioCadastro);
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