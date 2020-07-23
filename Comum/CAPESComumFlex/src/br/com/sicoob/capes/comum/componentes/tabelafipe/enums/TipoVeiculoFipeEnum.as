package br.com.sicoob.capes.comum.componentes.tabelafipe.enums {

    import flash.utils.IDataInput;
    
    import org.granite.util.Enum;
	
    [Bindable]
    [RemoteClass(alias="br.com.sicoob.capes.integracao.negocio.enums.TipoVeiculoFipeEnum")]
    public class TipoVeiculoFipeEnum extends Enum {

        public static const CAMINHOES:TipoVeiculoFipeEnum = new TipoVeiculoFipeEnum("CAMINHOES", "Caminhões", "caminhoes", _);
        public static const CARROS:TipoVeiculoFipeEnum = new TipoVeiculoFipeEnum("CARROS", "Carros", "carros", _);
        public static const MOTOS:TipoVeiculoFipeEnum = new TipoVeiculoFipeEnum("MOTOS", "Motos", "motos", _);

		private var _nome:String;
		private var _valor:String;

        function TipoVeiculoFipeEnum(name:String = null, nome:String = null, valor:String = null, restrictor:* = null) {
            super((name || CAMINHOES.name), restrictor);
			if (restrictor != null) {
				this._nome = nome;
				this._valor = valor;
			}
        }
        
		public function get nome():String {
			return this._nome;
		}
		
		public function get valor():String {
			return this._valor;
		}
		
        protected override function getConstants():Array {
            return constants;
        }

        public static function get constants():Array {
            return [CAMINHOES, CARROS, MOTOS];
        }

        public static function valueOf(name:String):TipoVeiculoFipeEnum {
        	return TipoVeiculoFipeEnum(CAMINHOES.constantOf(name));
        }
		
		public override function readExternal(input:IDataInput):void {
			super.readExternal(input);
			var constantObject:TipoVeiculoFipeEnum = valueOf(name);
			_nome = constantObject.nome;
			_valor = constantObject.valor;
		}
    }
}