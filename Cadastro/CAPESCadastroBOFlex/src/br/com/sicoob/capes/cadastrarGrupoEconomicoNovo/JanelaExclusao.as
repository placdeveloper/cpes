package br.com.sicoob.capes.cadastrarGrupoEconomicoNovo {
	import flash.events.MouseEvent;
	
	import mx.events.FlexEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.componentes.eventos.EventJanela;
	
	public class JanelaExclusao extends JanelaExclusaoView {
		
		private var _callbackConfirmacao:Function;
		private var _callbackCancelar:Function;
		
		public function JanelaExclusao() {
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
			addEventListener(Janela.FECHAR_JANELA,limparDados);
		}
		
		private function init(event:FlexEvent):void {
			btnConfirmar.addEventListener(MouseEvent.CLICK, confirmarClicado);
			btnCancelar.addEventListener(MouseEvent.CLICK, cancelarClicado);
		}
		
		private function confirmarClicado(event:MouseEvent = null):void {
			if(txtCampoJustificativa.validar()){
				if(callbackConfirmacao != null) {
					callbackConfirmacao(motivoExclusao);
				} else {
					fecharJanela();
				}
			} else {
				Alerta.show(txtCampoJustificativa.validarMensagem, "Atenção", Alerta.ALERTA_OK, txtCampoJustificativa);
			}
		}
		
		private function cancelarClicado(event:MouseEvent = null):void {
			if(callbackCancelar != null) {
				callbackCancelar();
			} else {
				fecharJanela();
			}
		}
		
		private function limparDados(event:EventJanela):void {
			callbackCancelar = null;
			callbackConfirmacao = null;
			motivoExclusao = "";
			textoTitulo = "";
			if(txtCampoJustificativa != null) {
				txtCampoJustificativa.errorString = "";
			}
		}
		
		public function get callbackConfirmacao():Function {
			return _callbackConfirmacao;
		}

		/**
		 * modelo do callback: callbackConfirmacao(motivoExclusão:String);
		 */
		public function set callbackConfirmacao(value:Function):void {
			_callbackConfirmacao = value;
		}

		public function get callbackCancelar():Function {
			return _callbackCancelar;
		}

		/**
		 * modelo do callback: callbackCancelar();
		 */
		public function set callbackCancelar(value:Function):void {
			_callbackCancelar = value;
		}

		
	}
}