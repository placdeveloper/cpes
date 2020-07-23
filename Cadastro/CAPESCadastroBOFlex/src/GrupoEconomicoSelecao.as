package {
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastrarGrupoEconomicoNovo.GrupoEconomicoInicialView;

	public class GrupoEconomicoSelecao extends GrupoEconomicoInicialView {

		private var servico:ServicoJava = new ServicoJava();
		private static const DESTINO_CAPES:String = "destinoCAPES";

		public function GrupoEconomicoSelecao() {
			addEventListener(FlexEvent.CREATION_COMPLETE,onCreateComplete);
		}
		
		private function onCreateComplete(event:FlexEvent): void {
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], destino_recuperado);
		}
		
		private function destino_recuperado(destino:DestinoVO):void {
			servico.configurarDestino(destino);
			this.destino = destino;
			//obterDefinicoes();
			selecionarTela("Antigo");
		}
		
		private function obterDefinicoes():void {
			servico.source = "br.com.sicoob.capes.cadastro.fachada.GrupoEconomicoNovoFachada";
			servico.addEventListener(ResultEvent.RESULT, obterDefinicoesOnResult);
			servico.mensagemEspera = "Obtendo Definições";
			servico.bloquearOperacao = true;
			servico.obterDefinicoes();
		}
		
		private function obterDefinicoesOnResult(event:ResultEvent):void {
			servico.removeEventListener(ResultEvent.RESULT, obterDefinicoesOnResult);
			var destinoTela:String = event.result.dados.destinoTela;
			selecionarTela(destinoTela);
		}
	}
}