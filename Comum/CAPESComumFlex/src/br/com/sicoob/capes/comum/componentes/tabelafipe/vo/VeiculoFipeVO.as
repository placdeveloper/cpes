package br.com.sicoob.capes.comum.componentes.tabelafipe.vo {
	import flash.net.registerClassAlias;
	import flash.utils.IDataInput;
	import flash.utils.IDataOutput;

	registerClassAlias("br.com.sicoob.capes.integracao.negocio.vo.itx.fipe.VeiculoFipeVO", VeiculoFipeVO);
	public class VeiculoFipeVO extends FipeVO {
		
		private var _marca:String;
		private var _marcaFipe:String;
		private var _ordem:Number;
		
		public function get marca():String {
			return _marca;
		}
		
		public function set marca(valor:String):void {
			this._marca = valor;
		}
		
		public function get marcaFipe():String {
			return _marcaFipe;
		}
		
		public function set marcaFipe(valor:String):void {
			this._marcaFipe = valor;
		}
		
		public function get ordem():Number {
			return _ordem;
		}
		
		public function set ordem(valor:Number):void {
			this._ordem = valor;
		}
		
		public override function readExternal(input:IDataInput):void {
			super.readExternal(input);
			_marca = input.readObject() as String;
			_marcaFipe = input.readObject() as String;
			_ordem = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
		}
		
		public override function writeExternal(output:IDataOutput):void {
			super.writeExternal(output);
			output.writeObject(_marca);
			output.writeObject(_marcaFipe);
			output.writeObject(_ordem);
		}
		
	}
}