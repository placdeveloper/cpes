package br.com.sicoob.capes.comum.enums {
	
	import flash.utils.IDataInput;
	
	import org.granite.util.Enum;
	
	/**
	 * Enum que representa os tipos de bens móveis.
	 * 
	 * @author bruno.carneiro
	 */
	[Bindable]
	[RemoteClass(alias = "br.com.sicoob.capes.cadastro.negocio.enums.TipoBemMovelEnum")]
	public class TipoBemMovelEnum extends Enum {
		
		public static const VEICULO:TipoBemMovelEnum 				= new TipoBemMovelEnum("VEICULO", 1, "VEÍCULO", true, _);
		public static const EMBARCACAO:TipoBemMovelEnum 			= new TipoBemMovelEnum("EMBARCACAO", 2, "EMBARCAÇÃO", true, _);
		public static const AERONAVE:TipoBemMovelEnum 				= new TipoBemMovelEnum("AERONAVE", 3, "AERONAVE", true, _);
		public static const MAQUINA_EQUIPAMENTO:TipoBemMovelEnum 	= new TipoBemMovelEnum("MAQUINA_EQUIPAMENTO", 4, "MÁQUINA/EQUIPAMENTO", false, _);
		public static const SEMOVENTE:TipoBemMovelEnum 				= new TipoBemMovelEnum("SEMOVENTE", 5, "SEMOVENTE", false, _);
		
		private var _codigo:Number;
		private var _descricao:String;
		private var _possuiDadosAvancados:Boolean;
		
		function TipoBemMovelEnum(name:String = null, codigo:Number = 0, descricao:String = null, possuiDadosAvancados:Boolean = true, restrictor:* = null) {
			super((name || VEICULO.name), restrictor);
			if (restrictor != null) {
				this._codigo = codigo;
				this._descricao = descricao;
				this._possuiDadosAvancados = possuiDadosAvancados;
			}
		}
		
		/**
		 * Obtém o valor do código.
		 */
		public function get codigo():Number {
			return _codigo;
		}
		
		/**
		 * Obtém o valor da descrição.
		 */
		public function get descricao():String {
			return _descricao;
		}
		
		public static function get constantes():Array {
			return [VEICULO, EMBARCACAO, AERONAVE, MAQUINA_EQUIPAMENTO, SEMOVENTE];
		}
		
		protected override function getConstants():Array {
			return constantes;
		}
		
		public override function readExternal(input:IDataInput):void {
			super.readExternal(input);
			var objeto:TipoBemMovelEnum = valueOf(name);
			_codigo = objeto.codigo;
			_descricao = objeto.descricao;
		}
		
		public static function valueOf(name:String):TipoBemMovelEnum {
			return TipoBemMovelEnum(VEICULO.constantOf(name));
		}
		
		/**
		 * Obtém o enum à partir do código informado.
		 */
		public static function obterPorCodigo(codigo:Number):TipoBemMovelEnum {
			var retorno:TipoBemMovelEnum = null;
			
			for each(var tipoBemMovel:TipoBemMovelEnum in constantes){
				if(tipoBemMovel._codigo == codigo){
					retorno = tipoBemMovel;
				}
			}
			return retorno;
		}
	}
}