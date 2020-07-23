package br.com.sicoob.capes.comum.vo {

    import flash.net.registerClassAlias;
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import flash.utils.IExternalizable;
    
    import mx.collections.ListCollectionView;

	/**
	 * Classe que representa a lista de campos para utilização na tela de autorização.
	 * 
	 * @author Bruno.Carneiro
	 */
	registerClassAlias("br.com.sicoob.capes.cadastro.negocio.vo.CampoTelaListaVO", CampoTelaListaVO);
    public class CampoTelaListaVO implements IExternalizable {

		private var _campos:ListCollectionView;
		private var _contadorSeparador:Number;
		private var _nomeAtributo:String;
		
		public function get campos():ListCollectionView {
			return _campos;
		}
		
		public function set campos(valor:ListCollectionView):void {
			_campos = valor;
		}
		
		public function get contadorSeparador():Number {
			return _contadorSeparador;
		}
		
		public function set contadorSeparador(valor:Number):void {
			_contadorSeparador = valor;
		}
		
		public function get nomeAtributo():String {
			return _nomeAtributo;
		}
		
		public function set nomeAtributo(valor:String):void {
			_nomeAtributo = valor;
		}

        public function readExternal(input:IDataInput):void {
			_campos = input.readObject() as ListCollectionView;
			_contadorSeparador = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_nomeAtributo = input.readObject() as String;
        }

        public function writeExternal(output:IDataOutput):void {
            output.writeObject(_campos);
            output.writeObject(_contadorSeparador);
            output.writeObject(_nomeAtributo);
        }
    }
}