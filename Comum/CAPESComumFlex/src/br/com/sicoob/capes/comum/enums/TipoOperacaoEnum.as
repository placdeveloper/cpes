package br.com.sicoob.capes.comum.enums {

    import flash.utils.IDataInput;
    
    import org.granite.util.Enum;
	
    [Bindable]
    [RemoteClass(alias="br.com.sicoob.capes.comum.negocio.enums.TipoOperacaoEnum")]
    public class TipoOperacaoEnum extends Enum {

        public static const A:TipoOperacaoEnum = new TipoOperacaoEnum("A", "Alteração", _);
        public static const E:TipoOperacaoEnum = new TipoOperacaoEnum("E", "Exclusão", _);
        public static const I:TipoOperacaoEnum = new TipoOperacaoEnum("I", "Inclusão", _);

		private var _descricao:String;

        function TipoOperacaoEnum(name:String = null, descricao:String = null, restrictor:* = null) {
            super((name || A.name), restrictor);
			if (restrictor != null) {
				this._descricao = descricao;
			}
        }
        
		public function get descricao():String {
			return this._descricao;
		}
		
        protected override function getConstants():Array {
            return constants;
        }

        public static function get constants():Array {
            return [A, E , I];
        }

        public static function valueOf(name:String):TipoOperacaoEnum {
        	return TipoOperacaoEnum(A.constantOf(name));
        }
		
		public override function readExternal(input:IDataInput):void {
			super.readExternal(input);
			var constantObject:TipoOperacaoEnum = valueOf(name);
			_descricao = constantObject.descricao;
		}
    }
}