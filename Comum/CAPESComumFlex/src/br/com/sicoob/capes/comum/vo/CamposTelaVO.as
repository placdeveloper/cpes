package br.com.sicoob.capes.comum.vo {

    import flash.net.registerClassAlias;
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import flash.utils.IExternalizable;
    
    import mx.collections.ListCollectionView;

	/**
	 * Classe para ser utilizada na tela de autorização, informando os campos que foram alterados.
	 * 
	 * @author Bruno.Carneiro
	 */
	registerClassAlias("br.com.sicoob.capes.cadastro.negocio.vo.CamposTelaVO", CamposTelaVO);
    public class CamposTelaVO implements IExternalizable {

		private var _campos:ListCollectionView;
		private var _listas:ListCollectionView;
		
		public function get campos():ListCollectionView {
			return _campos;
		}
		
		public function set campos(valor:ListCollectionView):void {
			this._campos = valor;
		}
		
		public function get listas():ListCollectionView {
			return _listas;
		}
		
		public function set listas(valor:ListCollectionView):void {
			this._listas = valor;
		}

        public function readExternal(input:IDataInput):void {
			_campos = input.readObject() as ListCollectionView;
			_listas = input.readObject() as ListCollectionView;
        }

        public function writeExternal(output:IDataOutput):void {
			output.writeObject(_campos);
            output.writeObject(_listas);
        }
    }
}