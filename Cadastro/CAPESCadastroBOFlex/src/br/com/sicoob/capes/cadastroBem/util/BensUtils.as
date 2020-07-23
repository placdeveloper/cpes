package br.com.sicoob.capes.cadastroBem.util {
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.app.Application;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	/**
	 * Classe com métodos úteis para as telas de bens.
	 */
	public class BensUtils {
		
		private static const CLASSE_SERVICO:String = "br.com.sicoob.capes.cadastro.fachada.bem.BemPessoaCompartilhamentoFachada";
		private static const MENSAGEM_COMPARTILHAMENTO:String = "Verificando o cadastro dos proprietários e associados. Por favor, aguarde...";
		private static const MENSAGEM_BEM_GARANTIA:String = "O bem encontra-se em garantia, somente as informações da aba avaliação foram consideradas.";
		private static const METODO_COMPARTILHAMENTO:String = "verificarCompartilhamentoAssociados";
		public static const EVENTO_REGISTRO_INCLUIDO:String = "EVENTO_REGISTRO_INCLUIDO";
		
		/**
		 * Método construtor.
		 **/
		public function BensUtils() {
			
		}
		
		public static function verificarCompartilhamentoAssociados(idBem:Number, destino:DestinoVO, listener:Function = null, faultListener:Function = null):void {
			if(!isNaN(idBem)) {
				MostraCursor.setBusyCursor(MENSAGEM_COMPARTILHAMENTO, Application.application, MostraCursor.CURSOR_PROGRESSO);
				var servicoCompartilhamento:ServicoJava = ServicoJavaUtil.getServico(CLASSE_SERVICO, MENSAGEM_COMPARTILHAMENTO);
				servicoCompartilhamento.configurarDestino(destino);
				
				if(listener != null) {
					servicoCompartilhamento.addEventListener(ResultEvent.RESULT, listener);
				}
				
				if(faultListener != null) {
					servicoCompartilhamento.addEventListener(FaultEvent.FAULT, faultListener);
				}
				
				var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
				dto.dados.idBem = idBem;
				servicoCompartilhamento.getOperation(METODO_COMPARTILHAMENTO).send(dto);
			} else if(listener != null) {
				listener.call();
			}
		}
		
		public static function verificarBemGarantia(idBem:Number, destino:DestinoVO):void {
			var servico:ServicoJava = ServicoJavaUtil.getServico(CLASSE_SERVICO, "Verificando o bem em garantia.", ResultEvent.RESULT, retornoVerificarBemGarantia);
			servico.configurarDestino(destino);
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.idBem = idBem;
			servico.getOperation("verificarBemEmGarantia").send(dto);
		}
		
		private static function retornoVerificarBemGarantia(evento:ResultEvent):void {
			var bemEmGarantia:Boolean = evento.result.dados.bemEmGarantia as Boolean;
			
			if(bemEmGarantia) {
				Alerta.show(MENSAGEM_BEM_GARANTIA, "Informação", Alerta.ALERTA_INFORMACAO);
			}
		}
		
	}
}