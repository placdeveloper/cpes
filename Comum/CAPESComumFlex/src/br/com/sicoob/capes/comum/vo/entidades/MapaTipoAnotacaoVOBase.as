package br.com.sicoob.capes.comum.vo.entidades {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    
    import org.granite.collections.IPersistentCollection;
    import org.granite.meta;

    use namespace meta;

    [Bindable]
    public class MapaTipoAnotacaoVOBase extends CAPESEntidadeVO {

        private var __laziness:String = null;
		
		private var _codigoTipoOrigemInformacao:String;
		private var _idMapaTipoAnotacao:Number;
		private var _tipoAnotacao:TipoAnotacaoVO;
		private var _tipoConsultaOrigem:TipoConsultaOrigemVO;
		
		public function get codigoTipoOrigemInformacao():String {
			return _codigoTipoOrigemInformacao;
		}
		
		public function set codigoTipoOrigemInformacao(value:String):void {
			_codigoTipoOrigemInformacao = value;
		}
		
		public function get idMapaTipoAnotacao():Number {
			return _idMapaTipoAnotacao;
		}
		
		public function set idMapaTipoAnotacao(value:Number):void {
			_idMapaTipoAnotacao = value;
		}
		
		public function get tipoAnotacao():TipoAnotacaoVO {
			return _tipoAnotacao;
		}
		
		public function set tipoAnotacao(value:TipoAnotacaoVO):void {
			_tipoAnotacao = value;
		}
		
		public function get tipoConsultaOrigem():TipoConsultaOrigemVO {
			return _tipoConsultaOrigem;
		}
		
		public function set tipoConsultaOrigem(value:TipoConsultaOrigemVO):void {
			_tipoConsultaOrigem = value;
		}
		
        meta function isInitialized(name:String = null):Boolean {
            if (!name){
                return __laziness === null;
			}

            var property:* = this[name];
            return (
                (!(property is MapaTipoAnotacaoVO) || (property as MapaTipoAnotacaoVO).meta::isInitialized()) &&
                (!(property is IPersistentCollection) || (property as IPersistentCollection).isInitialized())
            );
        }

        override public function readExternal(input:IDataInput):void {
            __laziness = input.readObject() as String;
            if (meta::isInitialized()) {
                super.readExternal(input);
				_codigoTipoOrigemInformacao = input.readObject() as String;
				_idMapaTipoAnotacao = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
				_tipoAnotacao = input.readObject() as TipoAnotacaoVO;
				_tipoConsultaOrigem = input.readObject() as TipoConsultaOrigemVO;
            } else {
				_idMapaTipoAnotacao = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
            }
        }

        override public function writeExternal(output:IDataOutput):void {
            output.writeObject(__laziness);
            if (meta::isInitialized()) {
                super.writeExternal(output);
	            output.writeObject(_codigoTipoOrigemInformacao);
	            output.writeObject(_idMapaTipoAnotacao);
	            output.writeObject(_tipoAnotacao);
	            output.writeObject(_tipoConsultaOrigem);
            }else {
                output.writeObject(_idMapaTipoAnotacao);
            }
        }
    }
}