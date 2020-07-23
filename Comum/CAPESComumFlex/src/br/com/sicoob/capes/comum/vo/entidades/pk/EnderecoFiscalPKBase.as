package br.com.sicoob.capes.comum.vo.entidades.pk {

    import br.com.bancoob.negocio.entidades.BancoobChavePrimaria;
    import br.com.sicoob.capes.comum.vo.entidades.EnderecoFiscalVO;
    
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    
    import org.granite.collections.IPersistentCollection;
    import org.granite.meta;

    use namespace meta;

    [Bindable]
    public class EnderecoFiscalPKBase extends BancoobChavePrimaria {

        private var __laziness:String = null;

        private var _idPessoaCompartilhamento:Number;
        private var _codigoNacionalidade:Number;

        meta function isInitialized(name:String = null):Boolean {
            if (!name)
                return __laziness === null;

            var property:* = this[name];
            return (
                (!(property is EnderecoFiscalVO) || (property as EnderecoFiscalVO).meta::isInitialized()) &&
                (!(property is IPersistentCollection) || (property as IPersistentCollection).isInitialized())
            );
        }
		
		public function set codigoNacionalidade(value:Number):void {
			_codigoNacionalidade = value;
		}
		
		public function get codigoNacionalidade():Number {
			return _codigoNacionalidade;
		}

        public function set idPessoaCompartilhamento(value:Number):void {
			_idPessoaCompartilhamento = value;
        }
		
        public function get idPessoaCompartilhamento():Number {
            return _idPessoaCompartilhamento;
        }

        override public function readExternal(input:IDataInput):void {
			_codigoNacionalidade = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_idPessoaCompartilhamento = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
        }

        override public function writeExternal(output:IDataOutput):void {
            output.writeObject(_codigoNacionalidade);
            output.writeObject(_idPessoaCompartilhamento);
        }
    }
}