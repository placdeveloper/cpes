package br.com.sicoob.capes.comum.componentes.containers {
	import br.com.bancoob.componentes.Rotulo;
	import br.com.bancoob.componentes.containers.CanvasBancoob;
	import br.com.bancoob.componentes.containers.validaveis.CanvasValidavel;
	
	import mx.events.FlexEvent;
	
	public class Fieldset extends CanvasValidavel {
		private var background : CanvasBancoob;
		private var fieldLabel : Rotulo = new Rotulo();
		
		public function Fieldset() {
			this.setStyle("borderStyle", "solid");
			addEventListener(FlexEvent.CREATION_COMPLETE, init);			
		}
		
		private function init(evento : FlexEvent) : void {
			
			if (initialized) {
				this.fieldLabel.setStyle("paddingLeft", 5);
				this.fieldLabel.setStyle("paddingRight", 5);
				this.fieldLabel.text = this.label;
				
				this.background = new CanvasBancoob();
				this.background.setStyle("backgroundColor", 0xF6F7F9);
				this.background.x = this.x + 5;
				this.background.y = this.y - 8;
				this.background.height = 18;
				this.background.addChild(this.fieldLabel);
				this.parent.addChild(this.background);
			}
		}
		
	}
}