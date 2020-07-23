package br.com.sicoob.capes.comum.vo.entidades {
	import flash.utils.IDataInput;
	import flash.utils.IDataOutput;
	import flash.utils.IExternalizable;

	[Bindable]
	public class TipoDocumentoVOBase implements IExternalizable {
		
		private var _idTipoDocumento:int;
		private var _nomeTipoDoc:String;
		private var _siglaTipoDocumento:String;
		
		public function get idTipoDocumento():int{
			return _idTipoDocumento;
		}
		public function set idTipoDocumento(value:int):void{
			_idTipoDocumento = value;
		}
		
		public function get nomeTipoDoc():String{
			return _nomeTipoDoc;
		}
		public function set nomeTipoDoc(value:String):void{
			_nomeTipoDoc = value;
		}
		
		public function get siglaTipoDocumento():String{
			return _siglaTipoDocumento;
		}
		public function set siglaTipoDocumento(value:String):void{
			_siglaTipoDocumento = value;
		}
		
		public function readExternal(input:IDataInput):void {
			_idTipoDocumento = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_nomeTipoDoc = input.readObject() as String;
			_siglaTipoDocumento = input.readObject() as String;
		}
		
		public function writeExternal(output:IDataOutput):void {
			output.writeObject(isNaN(_idTipoDocumento) ? null : _idTipoDocumento);
			output.writeObject(_nomeTipoDoc);
			output.writeObject(_siglaTipoDocumento);
		}

	}
}