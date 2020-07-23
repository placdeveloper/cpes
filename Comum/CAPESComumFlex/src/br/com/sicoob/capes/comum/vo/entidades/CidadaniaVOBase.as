package br.com.sicoob.capes.comum.vo.entidades{

    import br.com.sicoob.capes.comum.vo.entidades.pk.CidadaniaPK;
    
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    
    import org.granite.collections.IPersistentCollection;
    import org.granite.meta;

    use namespace meta;

    [Bindable]
    public class CidadaniaVOBase extends CAPESEntidadeVO {

        private var __laziness:String = null;

        private var _pk:CidadaniaPK;
        private var _nacionalidade:NacionalidadeVO;
		private var _pessoaFisica:PessoaCompartilhamentoVO;

        meta function isInitialized(name:String = null):Boolean {
            if (!name)
                return __laziness === null;

            var property:* = this[name];
            return (
                (!(property is CidadaniaVO) || (property as CidadaniaVO).meta::isInitialized()) &&
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
		
        public function get pk():CidadaniaPK {
            return _pk;
        }
		
        public function set pk(value:CidadaniaPK):void {
            _pk = value;
        }
		
		public function get nacionalidade():NacionalidadeVO {
			return _nacionalidade;
		}
		
		public function set nacionalidade(value:NacionalidadeVO):void {
			_nacionalidade = value;
		}

        override public function readExternal(input:IDataInput):void {
            __laziness = input.readObject() as String;
            if (meta::isInitialized()) {
                super.readExternal(input);
                _nacionalidade = input.readObject() as NacionalidadeVO;
				_pessoaFisica = input.readObject() as PessoaCompartilhamentoVO;
                _pk = input.readObject() as CidadaniaPK;
            }
            else {
                _pk = input.readObject() as CidadaniaPK;
            }
        }

        override public function writeExternal(output:IDataOutput):void {
            output.writeObject(__laziness);
            if (meta::isInitialized()) {
                super.writeExternal(output);
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