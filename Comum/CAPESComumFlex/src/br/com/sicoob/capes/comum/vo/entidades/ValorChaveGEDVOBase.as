package br.com.sicoob.capes.comum.vo.entidades
{
	import flash.utils.IDataInput;
	import flash.utils.IDataOutput;
	import flash.utils.IExternalizable;
	
	[Bindable]
	public class ValorChaveGEDVOBase implements IExternalizable
	{
		
		private var _idChave:Number;
		private var _valorChave:String;
		
		public function ValorChaveGEDVOBase(id:Number = NaN, valor:String = null){
			_idChave = id;
			_valorChave = valor;
		}
		
		public function get idChave():Number {
			return _idChave;
		}
		public function set idChave(value:Number):void {
			_idChave = value;
		}
		
		public function get valorChave():String {
			return _valorChave;
		}
		public function set valorChave(value:String):void {
			_valorChave = value;
		}
		
		public function writeExternal(output:IDataOutput):void{
			output.writeObject(_idChave);
			output.writeObject(_valorChave);
		}
		
		public function readExternal(input:IDataInput):void{
			_idChave = input.readObject() as Number;
			_valorChave = input.readObject() as String;
		}
	}
}