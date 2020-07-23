package br.com.sicoob.capes.comum.vo.entidades{

    import br.com.sicoob.capes.comum.vo.entidades.pk.EnderecoFiscalPK;
    
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    
    import org.granite.collections.IPersistentCollection;
    import org.granite.meta;

    use namespace meta;

    [Bindable]
    public class EnderecoFiscalVOBase extends CAPESEntidadeVO {

        private var __laziness:String = null;

        private var _pk:EnderecoFiscalPK;
        private var _nacionalidade:NacionalidadeVO;
		private var _codigoFiscal:String;
		
		private var _pessoaFisica:PessoaCompartilhamentoVO;

        meta function isInitialized(name:String = null):Boolean {
            if (!name)
                return __laziness === null;

            var property:* = this[name];
            return (
                (!(property is EnderecoFiscalVO) || (property as EnderecoFiscalVO).meta::isInitialized()) &&
                (!(property is IPersistentCollection) || (property as IPersistentCollection).isInitialized())
            );
        }
		
		public function set pessoaFisica(value:PessoaCompartilhamentoVO):void
		{
			_pessoaFisica = value;
		}
		
		public function get pessoaFisica():PessoaCompartilhamentoVO
		{
			return _pessoaFisica;
		}
		
        public function get pk():EnderecoFiscalPK {
            return _pk;
        }
		
        public function set pk(value:EnderecoFiscalPK):void {
            _pk = value;
        }
		
		public function get nacionalidade():NacionalidadeVO {
			return _nacionalidade;
		}
		
		public function set nacionalidade(value:NacionalidadeVO):void {
			_nacionalidade = value;
		}
		
		public function get codigoFiscal():String {
			return _codigoFiscal;
		}
		
		public function set codigoFiscal(value:String):void {
			_codigoFiscal = value;
		}

        override public function readExternal(input:IDataInput):void {
            __laziness = input.readObject() as String;
            if (meta::isInitialized()) {
                super.readExternal(input);
				_codigoFiscal = input.readObject() as String;
                _nacionalidade = input.readObject() as NacionalidadeVO;
				_pessoaFisica = input.readObject() as PessoaCompartilhamentoVO;
                _pk = input.readObject() as EnderecoFiscalPK;
            }
            else {
                _pk = input.readObject() as EnderecoFiscalPK;
            }
        }

        override public function writeExternal(output:IDataOutput):void {
            output.writeObject(__laziness);
            if (meta::isInitialized()) {
                super.writeExternal(output);
	            output.writeObject(_codigoFiscal);
	            output.writeObject(_nacionalidade);
				output.writeObject(_pessoaFisica);
	            output.writeObject(_pk);
            }
            else {
                output.writeObject(_pk);
            }
        }
    }
}