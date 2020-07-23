package br.com.sicoob.capes.comum.vo.entidades {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    
    import org.granite.collections.IPersistentCollection;
    import org.granite.meta;

    use namespace meta;

    [Bindable]
    public class TipoConsultaOrigemVOBase extends CAPESEntidadeVO {

        private var __laziness:String = null;
		
		private var _descricaoTipoConsultaOrigem:String;
		private var _idTipoConsultaOrigem:Number;
		private var _nomeTipoConsultaOrigem:String;
		private var _origemInformacao:OrigemInformacaoVO;
		
		public function get descricaoTipoConsultaOrigem():String {
			return _descricaoTipoConsultaOrigem;
		}
		
		public function set descricaoTipoConsultaOrigem(value:String):void {
			_descricaoTipoConsultaOrigem = value;
		}
		
		public function get idTipoConsultaOrigem():Number {
			return _idTipoConsultaOrigem;
		}
		
		public function set idTipoConsultaOrigem(value:Number):void {
			_idTipoConsultaOrigem = value;
		}
		
		public function get nomeTipoConsultaOrigem():String {
			return _nomeTipoConsultaOrigem;
		}
		
		public function set nomeTipoConsultaOrigem(value:String):void {
			_nomeTipoConsultaOrigem = value;
		}
		
		public function get origemInformacao():OrigemInformacaoVO {
			return _origemInformacao;
		}
		
		public function set origemInformacao(value:OrigemInformacaoVO):void {
			_origemInformacao = value;
		}
		
		public function get origemTipoMapa():String {
			return origemInformacao.descOrigemInfo + " - " + descricaoTipoConsultaOrigem;
		}
		
        meta function isInitialized(name:String = null):Boolean {
            if (!name){
                return __laziness === null;
			}

            var property:* = this[name];
            return (
                (!(property is TipoConsultaOrigemVO) || (property as TipoConsultaOrigemVO).meta::isInitialized()) &&
                (!(property is IPersistentCollection) || (property as IPersistentCollection).isInitialized())
            );
        }

        override public function readExternal(input:IDataInput):void {
            __laziness = input.readObject() as String;
            if (meta::isInitialized()) {
                super.readExternal(input);
				_descricaoTipoConsultaOrigem = input.readObject() as String;
				_idTipoConsultaOrigem = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
				_nomeTipoConsultaOrigem = input.readObject() as String;
				_origemInformacao = input.readObject() as OrigemInformacaoVO;
            } else {
				_idTipoConsultaOrigem = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
            }
        }

        override public function writeExternal(output:IDataOutput):void {
            output.writeObject(__laziness);
            if (meta::isInitialized()) {
                super.writeExternal(output);
	            output.writeObject(_descricaoTipoConsultaOrigem);
	            output.writeObject(_idTipoConsultaOrigem);
	            output.writeObject(_nomeTipoConsultaOrigem);
	            output.writeObject(_origemInformacao);
            }else {
				output.writeObject(_idTipoConsultaOrigem);
            }
        }
    }
}