package br.com.sicoob.capes.comum.vo.entidades.bem {

    import flash.net.registerClassAlias;
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    
    import org.granite.meta;

    use namespace meta;

	/**
	 * Classe que representa o bem imóvel avaliação rural.
	 * 
	 * @author Bruno.Carneiro
	 */
	registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.BemImovelAvaliacaoRural", BemImovelAvaliacaoRuralVO);
    public class BemImovelAvaliacaoRuralVO extends BemImovelAvaliacaoVO {
		
		private var _benfeitoria:String;
		private var _valorBenfeitoria:Number;
		
		public function get benfeitoria():String {
			return _benfeitoria;
		}
		
		public function set benfeitoria(valor:String):void {
			this._benfeitoria = valor;
		}
		
		public function get valorBenfeitoria():Number {
			return _valorBenfeitoria;
		}
		
		public function set valorBenfeitoria(valor:Number):void {
			this._valorBenfeitoria = valor;
		}

        override public function readExternal(input:IDataInput):void {
            super.readExternal(input);
			_benfeitoria = input.readObject() as String;
			_valorBenfeitoria = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
        }

        override public function writeExternal(output:IDataOutput):void {
            super.writeExternal(output);
			output.writeObject(_benfeitoria);
			output.writeObject(_valorBenfeitoria);
        }
    }
}