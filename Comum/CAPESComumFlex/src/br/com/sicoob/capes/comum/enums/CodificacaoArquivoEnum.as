package br.com.sicoob.capes.comum.enums {

    import flash.utils.IDataInput;
    
    import org.granite.util.Enum;
	
    [Bindable]
    [RemoteClass(alias="br.com.sicoob.capes.comum.negocio.enums.CodificacaoArquivoEnum")]
    public class CodificacaoArquivoEnum extends Enum {

        public static const ASCII:CodificacaoArquivoEnum = new CodificacaoArquivoEnum("ASCII", "US-ASCII", _);
        public static const CP1252:CodificacaoArquivoEnum = new CodificacaoArquivoEnum("CP1252", "Cp1252", _);
        public static const ISO_8859_1:CodificacaoArquivoEnum = new CodificacaoArquivoEnum("ISO_8859_1", "ISO-8859-1", _);
        public static const UTF_8:CodificacaoArquivoEnum = new CodificacaoArquivoEnum("UTF_8", "UTF-8", _);
		
		private var _codigo:String;

        function CodificacaoArquivoEnum(name:String = null, codigo:String = null, restrictor:* = null) {
            super((name || ASCII.name), restrictor);
			if (restrictor != null) {
				this._codigo = codigo;
			}
        }
        
		public function get codigo():String {
			return this._codigo;
		}
		
        protected override function getConstants():Array {
            return constants;
        }

        public static function get constants():Array {
            return [ASCII, CP1252 , ISO_8859_1, UTF_8];
        }

		public override function readExternal(input:IDataInput):void {
			super.readExternal(input);
			var constantObject:CodificacaoArquivoEnum = valueOf(name);
			_codigo = constantObject.codigo;
		}
		
		public static function valueOf(name:String):CodificacaoArquivoEnum {
			for each (var valor:* in constants){
				if (valor.name == name) {
					return valor;
				}
			}
			return null;
		}
		
		public static function obterPorCodigo(codigo:String):CodificacaoArquivoEnum {
			for each (var codificacao:CodificacaoArquivoEnum in constants){
				if (codificacao.codigo == codigo) {
					return codificacao;
				}
			}
			return null;
		}
    }
}