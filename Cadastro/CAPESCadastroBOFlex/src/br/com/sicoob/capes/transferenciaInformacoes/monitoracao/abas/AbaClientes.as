package br.com.sicoob.capes.transferenciaInformacoes.monitoracao.abas{
	
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	
	import flash.events.MouseEvent;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	public class AbaClientes extends AbaClientesView {
		
		private static const CLASSE_SERVICO: String = "br.com.sicoob.capes.cadastro.fachada.TransferenciaInformacoesFachada";
		private static  const OPERACAO_OBTER_DEFINICOES: String = "obterStatusClientes";
		private var servico: ServicoJava;
		
		private static const DESTINO_CAPES:String = "destinoCAPES";

		public function AbaClientes(){
			servico = ServicoJavaUtil.getServico(CLASSE_SERVICO, "Obtendo o status da transferência de clientes...");
			servico.addEventListener(ResultEvent.RESULT, retornoObterDefinicoes);
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(event:FlexEvent):void {
			this.btAtualizar.addEventListener(MouseEvent.CLICK, obterStatusClientes);
			
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], destino_recuperado);
		}
		
		private function destino_recuperado(destino:DestinoVO):void {
			servico.configurarDestino(destino);
			
			obterStatusClientes();
		}
		
		private function retornoObterDefinicoes(event: ResultEvent): void {
			this.grid.dataProvider = event.result.dados.statusCliente;
		}
		
		private function obterStatusClientes(event:MouseEvent = null):void{
			servico.getOperation(OPERACAO_OBTER_DEFINICOES).send(new RequisicaoReqDTO());
		}
	}
}