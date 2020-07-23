package br.com.sicoob.capes.monitoracaoDlqCapes.abas
{
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.grid.Grid;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	public class abaAtualizacaoCadastro extends abaAtualizacaoCadastroView
	{
		private static const OPERACAO_REPROCESSAR: String = "reprocessarDLQAtualizacao";
		private var servicoReprocessar:ServicoJava = new ServicoJava();
				
		public function abaAtualizacaoCadastro()
		{
			super();
			
			servicoReprocessar = ServicoJavaUtil.getServico(MonitoracaoDLQCapes.CLASSE_SERVICO);
			servicoReprocessar.addEventListener(ResultEvent.RESULT, retornoReprocessar);			
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);		
		}

		public function configurarDestino(destino:DestinoVO):void {
			this.servicoReprocessar.configurarDestino(destino);
		}
		
		private function retornoReprocessar(event: ResultEvent): void {

			MostraCursor.removeBusyCursor();
			this.dispatchEvent(new Event(MonitoracaoDLQCapes.FILA_REPROCESSADA));
		}
				
	    //--------------------------------------------------------------------------
	    //  Métodos
	    //--------------------------------------------------------------------------
		private function init(evt:FlexEvent=null):void {
			obterGrid().dataProvider = new ArrayCollection();
			this.btReprocessar.addEventListener(MouseEvent.CLICK, reprocessar);
		}
		
		private function reprocessar(evt:Event = null): void{
			
			MostraCursor.setBusyCursor("Reprocessando Mensagens!", 
					Application.application, MostraCursor.CURSOR_PROGRESSO);
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO;
            servicoReprocessar.getOperation(OPERACAO_REPROCESSAR).send(dto);	
		}
		
		public function obterGrid():Grid {
			this.gridDados.validateNow();
			return this.gridDados;
		}
		
		public function carregar(monitoracao:Object): void {
			var lista: ArrayCollection = monitoracao["mensagens"];
			obterGrid().dataProvider = monitoracao["mensagens"];
			lblExibicao.text = "Estão sendo exibidas " + lista.length + " mensagens do total de " + monitoracao["quantidadeMensagens"];
			lblNomeFila.text = monitoracao["nomeFila"];
			this.gridDados.selectedIndex = -1;
		}	
		
	}
}