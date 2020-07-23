package
{
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.componentes.painellista.PainelListaBanco;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastroEmpreendimento.EmpreendimentoEdicao;
	import br.com.sicoob.capes.cadastroEmpreendimento.EmpreendimentoView;
	import br.com.sicoob.capes.cadastroEmpreendimento.PainelFiltroEmpreendimentoView;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.EmpreendimentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.FinalidadeEmpreendimentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.UnidadeMedidaVO;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.SubTipoBemVO;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.TipoBemVO;
	
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	public class Empreendimento extends EmpreendimentoView {
		
		private var janelaAdicionarClientes : Janela;
		
		public var servico: ServicoJava;
		
		static public const CLASSE_SERVICO: String = 
			"br.com.sicoob.capes.cadastro.fachada.EmpreendimentoFachada";
		static private const OPERACAO_OBTER_DEFINICOES: String = "obterDefinicoes";
		private static const DESTINO_CAPES:String = "destinoCAPES";
		
		public function Empreendimento() {
			super();
			include 'br/com/sicoob/capes/utils/RegistroClasses.as';
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Empreendimento", EmpreendimentoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.UnidadeMedida",UnidadeMedidaVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.FinalidadeEmpreendimento", FinalidadeEmpreendimentoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bemantigo.SubTipoBem", SubTipoBemVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bemantigo.TipoBem", TipoBemVO);
		} 
		
		protected override function init(event: FlexEvent): void {
			super.init(event);
			
			servico = ServicoJavaUtil.getServico(CLASSE_SERVICO, "Obtendo definições...", 
				ResultEvent.RESULT, retornoObterDefinicoes);
			
			var painelLista:PainelListaBanco = PainelListaBanco(this.painelLista)
			painelLista.funcaoCriacaoDto = instanciarDTOConsulta;
			painelLista.funcaoConfiguracaoDto = configurarDtoConsulta;
			
			// remoção do botão de exclusao
			this.barraBotoesListaCadastro.exibirBotaoExcluir = false;
			 
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], destino_recuperado);
		}
		
		private function destino_recuperado(destino:DestinoVO):void {
			servico.configurarDestino(destino);
			PainelListaBanco(this.painelLista).servicoPesquisa.configurarDestino(destino);
			//ServicoJava(this.servicoRecuperacaoInformacoes).configurarDestino(destino);
			//ServicoJava(this.servicoExclusao).configurarDestino(destino);
			
			servico.getOperation(OPERACAO_OBTER_DEFINICOES).send(new RequisicaoReqDTO());
		}
		
		public function configurarDtoConsulta(dto:ConsultaDto): void {
			
			var filtro:EmpreendimentoVO = new EmpreendimentoVO();
			if(painelFiltro.codigo.text != ""){
				filtro.codigo = painelFiltro.codigo.valor;
			}
			
			if(painelFiltro.descricao.text != ""){
				filtro.descricao = painelFiltro.descricao.text;
			}
			
			if(painelFiltro.finalidade.selectedIndex != 0){
				filtro.finalidade = painelFiltro.finalidade.selectedItem as FinalidadeEmpreendimentoVO;
			}
			dto.filtro = filtro;
		}
		
		private function get painelFiltro() : PainelFiltroEmpreendimentoView {
			return this.painelLista.painelFiltro as PainelFiltroEmpreendimentoView;
		}
		
		public function instanciarDTOConsulta(): ConsultaDto {
			return new ConsultaDto();
		}
		
		private function retornoObterDefinicoes(event: ResultEvent): void {
			painelFiltro.finalidade.dataProvider = event.result.dados.listaFinalidades;
			
			// Realiza a pesquisa inicial
			PainelListaBanco(this.painelLista).pesquisar(1);
		}
		
		protected override function botaoIncluirPressionado():void {
			this.formularioCadastro = new EmpreendimentoEdicao();
			super.botaoIncluirPressionado();
		}

	}
}