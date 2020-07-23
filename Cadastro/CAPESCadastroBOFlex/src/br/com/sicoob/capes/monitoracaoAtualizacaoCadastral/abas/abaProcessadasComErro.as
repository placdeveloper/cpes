package br.com.sicoob.capes.monitoracaoAtualizacaoCadastral.abas {
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.monitoracaoAtualizacaoCadastral.eventos.ExcluirEvent;
	import br.com.sicoob.capes.monitoracaoAtualizacaoCadastral.eventos.ReprocessarEvent;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	
	public class abaProcessadasComErro extends abaProcessadasComErroView 
			implements IAbaMonitoracaoAtualizacaoCadastral {
		
		public static const EVENTO_ATUALIZACAO_EXCLUIDA:String = "atualizacaoExcluida";
		public static const EVENTO_ATUALIZACOES_REPROCESSADAS:String = "atualizacoesReprocessadas";
		private var servico : ServicoJava;
		private var servicoExclusao : ServicoJava;
		private var _mensagens : ArrayCollection;

		public function abaProcessadasComErro() {
			super();
			this.servico = ServicoJavaUtil.getServico(MonitoracaoAtualizacaoCadastral.CLASSE_SERVICO, 
				"Reprocessando atualização cadastral...", ResultEvent.RESULT, onResultReprocessarMensagem);

			this.servicoExclusao = ServicoJavaUtil.getServico(MonitoracaoAtualizacaoCadastral.CLASSE_SERVICO, 
				"Excluindo...", ResultEvent.RESULT, onResultExcluirMensagem);
			
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(event: FlexEvent) : void {
			this.gridDados.addEventListener(ReprocessarEvent.REPROCESSAR, reprocessar);
			this.gridDados.addEventListener(ExcluirEvent.EXCLUIR, onExclusaoSolicitada);
		}
		
		public function configurarDestino(destino:DestinoVO):void {		
			this.servico.configurarDestino(destino);
			this.servicoExclusao.configurarDestino(destino);
		}

		public function set dados(dados:Object):void {
			this.gc.source = this._mensagens = ArrayCollection(dados.resultado);
			this.gc.refresh();
			this.lblQuantidade.text = this.mensagens.length + "/" + dados.totalRegistros;
		}
		public function get mensagens():ArrayCollection {
			return this._mensagens;
		}
		
		public function reprocessar(event : ReprocessarEvent) : void {
			var dto : RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.mensagem = event.mensagem;
			this.servico.getOperation("reprocessarMensagem").send(dto);
		}
		
		private function onResultReprocessarMensagem(event : ResultEvent) : void {
			dispatchEvent(new Event(EVENTO_ATUALIZACOES_REPROCESSADAS));
		}
		
		private function onExclusaoSolicitada(event:ExcluirEvent):void {
			Alerta.show("Tem certeza de que deseja excluir esta atualização cadastral?", 
				"CONFIRMAÇÃO DE EXCLUSÃO", Alerta.ALERTA_PERGUNTA, null, function():void {
					excluirMensagem(event.id);
				});
		}
		
		private function excluirMensagem(id:Number):void {
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.idMensagem = id;
			servicoExclusao.getOperation("excluirMensagem").send(dto);
		}
		
		private function onResultExcluirMensagem(event:ResultEvent):void {
			dispatchEvent(new Event(EVENTO_ATUALIZACAO_EXCLUIDA));
		}
		
	}
}