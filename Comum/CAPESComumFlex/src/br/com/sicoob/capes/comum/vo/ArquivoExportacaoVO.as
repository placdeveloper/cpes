package br.com.sicoob.capes.comum.vo {
	import br.com.bancoob.vo.BancoobVO;
	
	import flash.net.registerClassAlias;
	import flash.utils.ByteArray;
	import flash.utils.IDataInput;
	import flash.utils.IDataOutput;
	import flash.utils.IExternalizable;
	
	import org.granite.meta;
	
	use namespace meta;
	
	registerClassAlias("br.com.sicoob.capes.cadastro.negocio.vo.ArquivoExportacaoVO", ArquivoExportacaoVO);
	[Bindable]
	public class ArquivoExportacaoVO extends BancoobVO implements IExternalizable {
		
		private var _nome:String;
		private var _bytes:ByteArray;

		public function get nome():String {
			return _nome;
		}

		public function set nome(value:String):void {
			_nome = value;
		}
		
		public function get bytes():ByteArray {
			return _bytes;
		}
		
		public function set bytes(value:ByteArray):void {
			_bytes = value;
		}
		
		public function readExternal(input:IDataInput):void {
			_bytes = input.readByte() as ByteArray;
			_nome = input.readObject() as String;
		}
		
		public function writeExternal(output:IDataOutput):void {
			output.writeBytes(_bytes, 0, _bytes.length);
			output.writeObject(_nome);
		}
	
	}
}