package {
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.relatorioGrupoEconomicoNovo.RelatorioGrupoEconomicoInicialView;
	
	
	public class RelatorioGrupoEconomicoNovo extends RelatorioGrupoEconomicoInicialView {
		
		private static const DESTINO_CAPES:String = "destinoCAPES";
		
		private var servico:ServicoJava = new ServicoJava();
		
		public function RelatorioGrupoEconomicoNovo() {
			addEventListener(FlexEvent.CREATION_COMPLETE,onCreateComplete);
		}
		
		private function onCreateComplete(event:FlexEvent): void {
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], onDestinoRecuperado);
		}
		
		private function onDestinoRecuperado(destino:DestinoVO):void {
			servico.configurarDestino(destino);
			this.destino = destino;
			obterDefinicoes();
		}
		
		private function obterDefinicoes():void {
			servico.source = "br.com.sicoob.capes.cadastro.fachada.RelatorioGrupoEconomicoFachada";
			servico.addEventListener(ResultEvent.RESULT, obterDefinicoesOnResult);
			servico.mensagemEspera = "Obtendo Definições";
			servico.bloquearOperacao = true;
			servico.obterDefinicoes();
		}
		
		private function obterDefinicoesOnResult(event:ResultEvent):void {
			servico.removeEventListener(ResultEvent.RESULT, obterDefinicoesOnResult);
			var destinoTela:String = event.result.dados.destinoTela;
			diasFiltroHistorico = event.result.dados.diasFiltroHistorico as Number;
			//selecionarTela(destinoTela);
			selecionarTela("NOVO");
		}
		
	}
	
}