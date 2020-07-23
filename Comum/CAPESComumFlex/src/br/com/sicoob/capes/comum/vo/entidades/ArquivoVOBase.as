package br.com.sicoob.capes.comum.vo.entidades {
	import flash.utils.IDataInput;
	import flash.utils.IDataOutput;
	import flash.utils.IExternalizable;
	
	[Bindable]
	public class ArquivoVOBase implements IExternalizable {
		
		private var _path:String;
		
		public function ArquivoVOBase(valorNome:String = null, valorExtensao:String = null, valorPath:String = null) {
			_path = valorPath;
		}

		public function get path():String {
			return _path;
		}
		public function set path(value:String):void {
			_path = value;
		}
		
		public function writeExternal(output:IDataOutput):void{
			output.writeObject(_path);
		}
		
		public function readExternal(input:IDataInput):void {
			_path = input.readObject() as String;
		}
	}
}