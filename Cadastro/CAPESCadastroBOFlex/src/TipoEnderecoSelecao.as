package {
	import br.com.bancoob.componentes.Botao;
	import br.com.bancoob.componentes.painellista.PainelListaBanco;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastrarTipoEndereco.PainelFiltroTipoEnderecoView;
	import br.com.sicoob.capes.cadastrarTipoEndereco.TipoEnderecoEdicao;
	import br.com.sicoob.capes.cadastrarTipoEndereco.TipoEnderecoView;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.TipoEnderecoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoPessoaContatoVO;
	
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.containers.HBox;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;

	public class TipoEnderecoSelecao extends TipoEnderecoView {
		
		private static const DESTINO_CAPES:String = "destinoCAPES";
		private static const OPERACAO_OBTER_DEFINICOES: String = "obterDefinicoes";
		
		private var servico : ServicoJava;
		private var classeServico : String = "br.com.sicoob.capes.cadastro.fachada.TipoEnderecoFachada";
		
		public function TipoEnderecoSelecao() {
			super();
			include 'br/com/sicoob/capes/utils/RegistroClasses.as';
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoPessoaContato", TipoPessoaContatoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoEndereco", TipoEnderecoVO);
		}

		protected override function init(evento:FlexEvent) : void {
			super.init(evento);
			
			this.servico = ServicoJavaUtil.getServico(classeServico, "Obtendo tipos pessoas contato...", ResultEvent.RESULT, retornoObterDefinicoes);
			this.barraBotoesListaCadastro.exibirBotaoExcluir = false;
			PainelListaBanco(this.painelLista).funcaoConfiguracaoDto = configurarDtoConsulta;
			PainelListaBanco(this.painelLista).funcaoCriacaoDto = instanciarDTOConsulta;
			
			// configuração do botão para geração do relatório
			var botaoRelatorio : Botao = new Botao();
			botaoRelatorio.label = "Gerar Relatório";
			botaoRelatorio.addEventListener(MouseEvent.CLICK, botaoGerarRelatorioPressionado);
			botaoRelatorio.height = 22;
			var box : HBox = (this.barraBotoesListaCadastro.getChildAt(0) as HBox);
			box.addChildAt(botaoRelatorio, 0);
			
			// adiciona a tela de edição
			this.formularioCadastro = new TipoEnderecoEdicao();
			
			// configura o serviço para pesquisa
			PainelListaBanco(this.painelLista).servicoPesquisa = ServicoJavaUtil.getServico(classeServico, "Recuperando dados...");
			
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], destino_recuperado);
		}
		
		private function destino_recuperado(destino:DestinoVO):void {
			servico.configurarDestino(destino);
			PainelListaBanco(this.painelLista).servicoPesquisa.configurarDestino(destino);
			this.destino = destino;
			
			servico.getOperation(obterOperacaoObterDefinicoes()).send(new RequisicaoReqDTO());
		}
		
		private function configurarDtoConsulta(dto:ConsultaDto): void {
			var filtro:TipoEnderecoVO = new TipoEnderecoVO();
			
			if(painelFiltro.campoCodigo.text != ""){
				filtro.codigo = painelFiltro.campoCodigo.valor;
			}
			
			if(painelFiltro.campoDescricao.text != ""){
				filtro.descricao = painelFiltro.campoDescricao.text;
			}
			
			if(painelFiltro.comboTipoPessoa.selectedItem != null){
				filtro.tipoPessoaContato = painelFiltro.comboTipoPessoa.selectedItem as TipoPessoaContatoVO;
			} else {
				filtro.tipoPessoaContato = null;
			}
			
			dto.filtro = filtro;
		}
		
		public function instanciarDTOConsulta(): ConsultaDto {
			return new ConsultaDto();
		}
		
		public function botaoGerarRelatorioPressionado(event : MouseEvent) : void {
			RelatorioUtil.create().emitirRelatorio("capes/relatorio/RelatorioTipoEnderecoServicoRemote", new ParametroDTO(), 
				"relatorioTipoEndereco", destino);
		}
		
		protected override function botaoIncluirPressionado():void {
			this.formularioCadastro = new TipoEnderecoEdicao();
			super.botaoIncluirPressionado();
		}
		
		private function get painelFiltro():PainelFiltroTipoEnderecoView {
			return this.painelLista.painelFiltro as PainelFiltroTipoEnderecoView;
		}
		
		protected function obterOperacaoObterDefinicoes() : String {
			return OPERACAO_OBTER_DEFINICOES;
		}
		
		private function retornoObterDefinicoes(event: ResultEvent): void {
			painelFiltro.comboTipoPessoa.dataProvider = event.result.dados.listaPessoaContato;
			
			// Realiza a pesquisa inicial
			PainelListaBanco(this.painelLista).pesquisar(1);
		}
	}
}