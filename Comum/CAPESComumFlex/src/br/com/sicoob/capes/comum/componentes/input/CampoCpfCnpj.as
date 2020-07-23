package br.com.sicoob.capes.comum.componentes.input {
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.input.Texto;
	import br.com.bancoob.util.FormatUtil;
	import br.com.bancoob.util.StringUtils;
	import br.com.bancoob.util.validadores.CNPJ;
	import br.com.bancoob.util.validadores.CPF;
	
	import flash.events.Event;
	import flash.events.FocusEvent;
	import flash.events.KeyboardEvent;
	
	import mx.events.FlexEvent;
	
	/**
	 * Componente para os CPF/CPNJ das telas de relatório.
	 **/
	public class CampoCpfCnpj extends Texto {
		
		/**
		 * Método Construtor
		 **/
		public function CampoCpfCnpj(){
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, iniciar);
		}
		
		private function iniciar(evt:FlexEvent=null):void {
			this.restrict = "0123456789\\";
			this.setStyle("textAlign", "left");
			this.agruparDigitos = false;
			this.permitirValoreNegativos = false;
			this.tipoEntrada = TEXTO;
			this.casasDecimais = 0;
			this.maxChars = 14;
			
			this.addEventListener(FocusEvent.FOCUS_OUT, verificarCpfCnpj);
		}
		
		private function verificarCpfCnpj(evento:Event = null):void {
			adicionarMascara();
			validarCpfCnpj();
		}
		
		private function adicionarMascara(evento:Event = null):void {
			var cpfCnpj:String = this.text; 
			cpfCnpj = retirarCaracteres(cpfCnpj);
			
			if(cpfCnpj.length == 11){
				this.text = FormatUtil.formataValor(cpfCnpj, "###.###.###-##", 1);
			}else if(cpfCnpj.length > 11 && cpfCnpj.length == 14){
				this.text = FormatUtil.formataValor(cpfCnpj, "##.###.###/####-##", 1);
			}
			
			this.validateDisplayList();
			this.validateNow();
		}
		
		private function validarCpfCnpj(evento:FocusEvent = null):void {
			var texto:String = "";
			
			var cpfCnpj:String = this.text; 
			cpfCnpj = retirarCaracteres(cpfCnpj);
			
			if(cpfCnpj != null && cpfCnpj != ""){
				if(cpfCnpj.length == 11 && !CPF.validarCPF(cpfCnpj)) {
					texto = "CPF";
				} else if (cpfCnpj.length == 14 && !CNPJ.validarCNPJ(cpfCnpj)) {
					texto = "CNPJ";
				} else if(cpfCnpj.length < 11 || (cpfCnpj.length > 11 && cpfCnpj.length < 14)){
					texto = "CPF/CNPJ";
				}
				
				if(texto != ""){
					Alerta.show(texto + " inválido.", "Erro", Alerta.ALERTA_ERRO);
				}
			}
		}
		
		private function retirarCaracteres(valor:String):String {
			valor = StringUtils.replace(valor, ".", "");
			valor = StringUtils.replace(valor, "-", "");
			valor = StringUtils.replace(valor, "/", "");
			
			valor = StringUtils.trim(valor);
			
			return valor;
		}
		
		/**
		 * recupera o CPF/CNPJ sem formatacao
		 */ 
		public function get cpfCnpj():String {
			return retirarCaracteres(text);
		}
	}
}