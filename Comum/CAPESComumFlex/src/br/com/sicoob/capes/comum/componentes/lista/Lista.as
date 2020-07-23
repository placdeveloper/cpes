package br.com.sicoob.capes.comum.componentes.lista {
	
	import br.com.bancoob.componentes.Lista;
	
	import flash.events.Event;
	
	import mx.core.ScrollPolicy;
	import mx.core.mx_internal;
	
	/**
	 * Componente de lista.
	 * 
	 * @author Bruno.Carneiro
	 **/
	public class Lista extends br.com.bancoob.componentes.Lista {
		
		/**
		 * Método construtor.
		 */
		public function Lista():void {
			horizontalScrollPolicy = ScrollPolicy.AUTO;
		}
		
		/**
		 * {@inheritDoc}
		 */
		public override function get maxHorizontalScrollPosition():Number {
			if (isNaN(mx_internal::_maxHorizontalScrollPosition)) {
				return 0;
			}
			return mx_internal::_maxHorizontalScrollPosition;
		}
		
		/**
		 * {@inheritDoc}
		 */
		public override function set maxHorizontalScrollPosition(valor:Number):void {
			mx_internal::_maxHorizontalScrollPosition = valor;
			dispatchEvent(new Event("maxHorizontalScrollPositionChanged"));
			
			scrollAreaChanged = true;
			invalidateDisplayList();
		}
		
		/**
		 * {@inheritDoc}
		 */
		protected override function updateDisplayList(larguraSemEscala:Number, alturaSemEscala:Number):void {
			var diferencaLargura:Number = measureWidthOfItems(0,0) - (larguraSemEscala - viewMetrics.left - viewMetrics.right);
			
			if (diferencaLargura <= 0) {
				maxHorizontalScrollPosition = NaN;
			} else {
				maxHorizontalScrollPosition = diferencaLargura;
			}
			
			super.updateDisplayList(larguraSemEscala, alturaSemEscala);
		}
		
	}
}