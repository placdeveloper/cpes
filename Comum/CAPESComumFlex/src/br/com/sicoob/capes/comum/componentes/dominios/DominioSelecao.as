package br.com.sicoob.capes.comum.componentes.dominios {
	import br.com.bancoob.componentes.Botao;
	import br.com.bancoob.componentes.cadastro.FormularioCadastro;
	import br.com.bancoob.componentes.painellista.PainelListaBanco;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	
	import flash.events.MouseEvent;
	
	import mx.containers.HBox;
	import mx.events.FlexEvent;
	
	public class DominioSelecao extends DominioSelecaoView {
		
		private static const DESTINO_CAPES:String = "destinoCAPES";
		
		public var botaoRelatorio : Botao = new Botao();
		
		protected override function init(evento:FlexEvent) : void {
			super.init(evento);
			
			this.barraBotoesListaCadastro.exibirBotaoExcluir = false;
			PainelListaBanco(this.painelLista).funcaoCriacaoDto = instanciarDTOConsulta;
			
			// configuração do botão para geração do relatório
			botaoRelatorio.label = "Emitir Relatório";
			botaoRelatorio.addEventListener(MouseEvent.CLICK, botaoGerarRelatorioPressionado);
			botaoRelatorio.height = 22;
			var box : HBox = (this.barraBotoesListaCadastro.getChildAt(0) as HBox);
			box.addChildAt(botaoRelatorio, 0);
			
			// adiciona a tela de edição
			this.formularioCadastro = obterFormularioCadastro();
			
			// configura o serviço para pesquisa
			PainelListaBanco(this.painelLista).servicoPesquisa = ServicoJavaUtil
				.getServico(obterClasseServico(), "Recuperando dados...");
			
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], destino_recuperado);
		}
		
		private function destino_recuperado(destino:DestinoVO):void {
			(PainelListaBanco(this.painelLista).servicoPesquisa as ServicoJava).configurarDestino(destino);
			this.destino = destino;
			
			// Realiza a pesquisa inicial
			PainelListaBanco(this.painelLista).pesquisar(1);
		}
		
		protected function obterFormularioCadastro():FormularioCadastro {
			return null;
		}
		
		protected function obterClasseServico() : String {
			return null;
		}
		
		protected function obterClasseServicoRelatorio() : String {
			return null;
		}
		
		public function instanciarDTOConsulta(): ConsultaDto {
			return new ConsultaDto();
		}
		
		public function botaoGerarRelatorioPressionado(event : MouseEvent) : void {
			RelatorioUtil.create().emitirRelatorio(obterClasseServicoRelatorio(),
				instanciarDTO(), obterNomeRelatorio(), destino, "Emitindo relatório...", 
				"PDF", false);
		}
		
		protected function obterNomeRelatorio() : String {
			return "relatorio";
		}
		
		protected function instanciarDTO() : ParametroDTO {
			return new ParametroDTO();
		}
		
		protected override function botaoIncluirPressionado():void {
			this.formularioCadastro = obterFormularioCadastro();
			super.botaoIncluirPressionado();
		}
	}
}