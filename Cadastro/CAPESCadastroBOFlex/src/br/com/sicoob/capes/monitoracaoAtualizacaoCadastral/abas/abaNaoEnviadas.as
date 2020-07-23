package br.com.sicoob.capes.monitoracaoAtualizacaoCadastral.abas 
{
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.monitoracaoAtualizacaoCadastral.eventos.ExcluirEvent;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.events.AdvancedDataGridEvent;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	public class abaNaoEnviadas extends abaNaoEnviadasView 
			implements IAbaMonitoracaoAtualizacaoCadastral {

		public static const EVENTO_MENSAGENS_ENVIADAS : String = "mensagensEnviadas";
		private var servico : ServicoJava;
		private var _mensagens : ArrayCollection;
		
		public function abaNaoEnviadas() {
			super();
			this.servico = ServicoJavaUtil.getServico(MonitoracaoAtualizacaoCadastral.CLASSE_SERVICO, 
				"Enviando mensagens...", ResultEvent.RESULT, onResultEnviarMensagens);
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(event: FlexEvent) : void {
			this.btEnviar.addEventListener(MouseEvent.CLICK, enviarMensagens);
			this.gridDados.addEventListener(ExcluirEvent.EXCLUIR, onExclusaoSolicitada);
		}
		
		public function configurarDestino(destino:DestinoVO):void {
			this.servico.configurarDestino(destino);
		}		
	
		public function set dados(dados:Object) : void {
			this.gc.source = this._mensagens = ArrayCollection(dados.resultado);
			this.gc.refresh();
			this.btEnviar.enabled = this.mensagens.length > 0;
			this.lblQuantidade.text = this.mensagens.length + "/" + dados.totalRegistros;
		}	
		public function get mensagens() : ArrayCollection {
			return this._mensagens;
		}
		
		private function onExclusaoSolicitada(event:ExcluirEvent):void {
			Alerta.show("Não é possível excluir atualizações cadastrais não enviadas.", 
				"EXCLUSÃO NÃO PERMITIDA", Alerta.ALERTA_ERRO);
		}
		
		public function enviarMensagens(event:MouseEvent) : void {
			var parent : MonitoracaoAtualizacaoCadastral = MonitoracaoAtualizacaoCadastral(this.parent.parent);
			parent.pararTempo();
			var dto : RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.mensagens = this.mensagens;
			servico.getOperation("enviarMensagens").send(dto);
		}
		
		private function onResultEnviarMensagens(envent:ResultEvent):void {
			dispatchEvent(new Event(EVENTO_MENSAGENS_ENVIADAS));
		}
	}
}