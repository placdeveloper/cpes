package br.com.sicoob.capes.comum.componentes.tabelafipe.vo {
	import br.com.bancoob.vo.BancoobVO;
	
	import flash.net.registerClassAlias;
	import flash.utils.IDataInput;
	import flash.utils.IDataOutput;
	import flash.utils.IExternalizable;

	registerClassAlias("br.com.sicoob.capes.integracao.negocio.vo.itx.fipe.MarcaFipeVO", MarcaFipeVO);
	public class MarcaFipeVO extends FipeVO {
		
		private var _ordem:Number;
		
		public function get ordem():Number {
			return _ordem;
		}
		
		public function set ordem(valor:Number):void {
			this._ordem = valor;
		}
		
		public override function readExternal(input:IDataInput):void {
			super.readExternal(input);
			_ordem = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
		}
		
		public override function writeExternal(output:IDataOutput):void {
			super.writeExternal(output);
			output.writeObject(_ordem);
		}
	}
}