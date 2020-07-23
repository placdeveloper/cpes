package {
	import br.com.bancoob.componentes.Botao;
	import br.com.bancoob.componentes.painellista.PainelListaBanco;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.tipos.Booleano;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastrarTipoTelefone.PainelFiltroTipoTelefoneView;
	import br.com.sicoob.capes.cadastrarTipoTelefone.TipoTelefoneEdicao;
	import br.com.sicoob.capes.cadastrarTipoTelefone.TipoTelefoneView;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.TipoPessoaContatoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoTelefoneVO;
	
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.containers.HBox;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;

	public class TipoTelefoneSelecao extends TipoTelefoneView {
		
		private static const DESTINO_CAPES:String = "destinoCAPES";
		private static const OPERACAO_OBTER_DEFINICOES: String = "obterDefinicoes";
		
		private var servico : ServicoJava;
		private var classeServico : String = "br.com.sicoob.capes.cadastro.fachada.TipoTelefoneFachada";
		
		public function TipoTelefoneSelecao() {
			super();
			include 'br/com/sicoob/capes/utils/RegistroClasses.as';
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoPessoaContato", TipoPessoaContatoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoTelefone", TipoTelefoneVO);
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
			this.formularioCadastro = new TipoTelefoneEdicao();
			
			// configura o serviço para pesquisa
			PainelListaBanco(this.painelLista).servicoPesquisa = ServicoJavaUtil.getServico(classeServico, "Recuperando dados...");
			
			painelFiltro.comboSimNao.dataProvider = obterListaComboSimNao();
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], destino_recuperado);
		}
		
		private function destino_recuperado(destino:DestinoVO):void {
			servico.configurarDestino(destino);
			PainelListaBanco(this.painelLista).servicoPesquisa.configurarDestino(destino);
			this.destino = destino;
			
			servico.getOperation(obterOperacaoObterDefinicoes()).send(new RequisicaoReqDTO());
		}
		
		protected function obterOperacaoObterDefinicoes() : String {
			return OPERACAO_OBTER_DEFINICOES;
		}
		
		private function configurarDtoConsulta(dto:ConsultaDto): void {
			var filtro:TipoTelefoneVO = new TipoTelefoneVO();
			
			filtro.dddObrigatorio = null;
			
			if(painelFiltro.campoCodigo.text != ""){
				filtro.codigo = painelFiltro.campoCodigo.valor;
			}
			
			if(painelFiltro.campoDescricao.text != ""){
				filtro.descricao = painelFiltro.campoDescricao.text;
			}
			
//			if(painelFiltro.campoDigitos.text != ""){
//				filtro.numMinDigitos = painelFiltro.campoDigitos.valor;
//			}
			
			if(painelFiltro.comboTipoPessoa.selectedItem != null){
				filtro.tipoPessoaContato = painelFiltro.comboTipoPessoa.selectedItem as TipoPessoaContatoVO;
			} else {
				filtro.tipoPessoaContato = null;
			}
			
			if(painelFiltro.comboSimNao.selectedItem != null) {
				filtro.ativo = new Booleano(painelFiltro.comboSimNao.selectedItem.codigo as Boolean);
			} else {
				filtro.ativo = null;
			}
			
			
			dto.filtro = filtro;
		}
		
		private function obterListaComboSimNao():ArrayCollection {
			var lista:ArrayCollection = new ArrayCollection();
			var objetoSim:Object = new Object();
			objetoSim.codigo = true;
			objetoSim.descricao = "SIM";
			lista.addItem(objetoSim);
			
			var objetoNao:Object = new Object();
			objetoNao.codigo = false;
			objetoNao.descricao = "NÃO";
			lista.addItem(objetoNao);
			return lista;
		}
		
		private function get painelFiltro():PainelFiltroTipoTelefoneView {
			return this.painelLista.painelFiltro as PainelFiltroTipoTelefoneView;
		}
		
		public function instanciarDTOConsulta(): ConsultaDto {
			return new ConsultaDto();
		}
		
		public function botaoGerarRelatorioPressionado(event : MouseEvent) : void {
			RelatorioUtil.create().emitirRelatorio("capes/relatorio/RelatorioTipoTelefoneServicoRemote", new ParametroDTO(), 
				"relatorioTipoTelefone", destino);
		}
		
		protected override function botaoIncluirPressionado():void {
			this.formularioCadastro = new TipoTelefoneEdicao();
			super.botaoIncluirPressionado();
		}
		
		private function retornoObterDefinicoes(event: ResultEvent): void {
			painelFiltro.comboTipoPessoa.dataProvider = event.result.dados.listaPessoaContato;
			
			// Realiza a pesquisa inicial
			PainelListaBanco(this.painelLista).pesquisar(1);
		}
	}
}