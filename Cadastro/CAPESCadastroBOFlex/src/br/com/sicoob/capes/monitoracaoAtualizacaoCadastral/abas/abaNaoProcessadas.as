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
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	public class abaNaoProcessadas extends abaNaoProcessadasView 
			implements IAbaMonitoracaoAtualizacaoCadastral {

		public static const EVENTO_MENSAGENS_REENVIADAS : String = "mensagensReenviadas";
		private var servico : ServicoJava;
		private var _mensagens : ArrayCollection;
		
		public function abaNaoProcessadas() {
			super();
			this.servico = ServicoJavaUtil.getServico(MonitoracaoAtualizacaoCadastral.CLASSE_SERVICO, 
				"Reenviando mensagens...", ResultEvent.RESULT, onResultReenviarMensagens);
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(event: FlexEvent) : void {
			this.btReenviar.addEventListener(MouseEvent.CLICK, reenviarMensagens);
			this.gridDados.addEventListener(ExcluirEvent.EXCLUIR, onExclusaoSolicitada);
		}

		public function configurarDestino(destino:DestinoVO):void {
			this.servico.configurarDestino(destino);
		}		
	
		public function get mensagens():ArrayCollection {
			return _mensagens;
		}
	
		public function set dados(dados:Object) : void {
			this.gc.source = this._mensagens = ArrayCollection(dados.resultado);
			this.gc.refresh();
			this.btReenviar.enabled = this.mensagens.length > 0;
			this.lblQuantidade.text = this.mensagens.length + "/" + dados.totalRegistros;
		}	
		
		private function onExclusaoSolicitada(event:ExcluirEvent):void {
			Alerta.show("Não é possível excluir atualizações cadastrais não processadas.", 
				"EXCLUSÃO NÃO PERMITIDA", Alerta.ALERTA_ERRO);
		}
		
		public function reenviarMensagens(event:MouseEvent) : void {
			var parent : MonitoracaoAtualizacaoCadastral = MonitoracaoAtualizacaoCadastral(this.parent.parent);
			parent.pararTempo();
			var dto : RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.mensagens = this.mensagens;
			servico.getOperation("reenviarMensagens").send(dto);
		}
		
		private function onResultReenviarMensagens(envent:ResultEvent):void {
			dispatchEvent(new Event(EVENTO_MENSAGENS_REENVIADAS));
		}
	}
}