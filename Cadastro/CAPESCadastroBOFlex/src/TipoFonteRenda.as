package {
	import br.com.bancoob.componentes.Botao;
	import br.com.bancoob.componentes.painellista.PainelListaBanco;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastrarTipoFonteRenda.TipoFonteRendaEdicao;
	import br.com.sicoob.capes.cadastrarTipoFonteRenda.TipoFonteRendaView;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.TipoFonteRendaVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoPessoaVO;
	
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.containers.HBox;
	import mx.events.FlexEvent;

	public class TipoFonteRenda extends TipoFonteRendaView {
		
		private static const DESTINO_CAPES:String = "destinoCAPES";
		
		private var classeServico : String = "br.com.sicoob.capes.cadastro.fachada.TipoFonteRendaFachada";
		
		public function TipoFonteRenda() {
			super();
			include 'br/com/sicoob/capes/utils/RegistroClasses.as';
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoFonteRenda", TipoFonteRendaVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoPessoa", TipoPessoaVO);
		}

		protected override function init(evento:FlexEvent) : void {
			super.init(evento);
			
			this.barraBotoesListaCadastro.exibirBotaoExcluir = false;
			PainelListaBanco(this.painelLista).funcaoCriacaoDto = instanciarDTOConsulta;
			
			// configuração do botão para geração do relatório
			var botaoRelatorio : Botao = new Botao();
			botaoRelatorio.label = "Gerar Relatório";
			botaoRelatorio.addEventListener(MouseEvent.CLICK, botaoGerarRelatorioPressionado);
			botaoRelatorio.height = 22;
			var box : HBox = (this.barraBotoesListaCadastro.getChildAt(0) as HBox);
			box.addChildAt(botaoRelatorio, 0);
			
			// adiciona a tela de edição
			this.formularioCadastro = new TipoFonteRendaEdicao();
			
			// configura o serviço para pesquisa
			PainelListaBanco(this.painelLista).servicoPesquisa = ServicoJavaUtil.getServico(classeServico, "Recuperando dados...");
			
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], destino_recuperado);
		}
		
		private function destino_recuperado(destino:DestinoVO):void {
			PainelListaBanco(this.painelLista).servicoPesquisa.configurarDestino(destino);
			this.destino = destino;
			
			// Realiza a pesquisa inicial
			PainelListaBanco(this.painelLista).pesquisar(1);
		}

		public function instanciarDTOConsulta(): ConsultaDto {
			return new ConsultaDto();
		}
		
		public function botaoGerarRelatorioPressionado(event : MouseEvent) : void {
			RelatorioUtil.create().emitirRelatorio("capes/relatorio/RelatorioTipoFonteRendaServicoRemote", new ParametroDTO(), 
				"relatorioTipoFonteRenda", destino);
		}
		
		protected override function botaoIncluirPressionado():void {
			this.formularioCadastro = new TipoFonteRendaEdicao();
			super.botaoIncluirPressionado();
		}
		
	}
}