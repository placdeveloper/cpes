package br.com.sicoob.capes.comum.vo.entidades
{
	import flash.utils.IDataInput;
	import flash.utils.IDataOutput;
	
	import org.granite.meta;
	import org.granite.collections.IPersistentCollection;
	
	use namespace meta;

	[Bindable]
	public class AutorizacaoDocumentoVOBase extends CAPESEntidadeVO {
		
		private var __laziness:String = null;

		private var _autorizacao:AutorizacaoVO;
		private var _documento:DocumentoComprobatorioVO;
		private var _idAutorizacao:Number;
		private var _idAutorizacaoDocumento:Number;

		meta function isInitialized(name:String = null):Boolean {
			if (!name)
				return __laziness === null;
			
			var property:* = this[name];
			return (
				(!(property is AutorizacaoVO) || (property as AutorizacaoVO).meta::isInitialized()) &&
				(!(property is IPersistentCollection) || (property as IPersistentCollection).isInitialized())
			);
		}

		public function get autorizacao():AutorizacaoVO {
			return _autorizacao;
		}
		
		public function set autorizacao(value:AutorizacaoVO):void{
			_autorizacao = value;
		}
		
		public function get idAutorizacaoDocumento():Number {
			return _idAutorizacaoDocumento;
		}
		public function set idAutorizacaoDocumento(value:Number):void{
			_idAutorizacaoDocumento = value;
		}

		public function get idAutorizacao():Number {
			return _idAutorizacao;
		}
		public function set idAutorizacao(value:Number):void {
			_idAutorizacao = value;
		}
		
		public function get documento():DocumentoComprobatorioVO {
			return _documento;
		}
		public function set documento(value:DocumentoComprobatorioVO):void {
			_documento = value;
		}
		
		public override function readExternal(input:IDataInput):void {
			__laziness = input.readObject() as String;
			if (meta::isInitialized()) {
				super.readExternal(input);
				_autorizacao = input.readObject() as AutorizacaoVO;
				_documento = input.readObject() as DocumentoComprobatorioVO;
				_idAutorizacao = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
				_idAutorizacaoDocumento = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			}else {
				_idAutorizacaoDocumento = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			}
		}
		
		public override function writeExternal(output:IDataOutput):void {
			output.writeObject(__laziness);
			if (meta::isInitialized()) {
				super.writeExternal(output);
				output.writeObject(_autorizacao);
				output.writeObject(_documento);
				output.writeObject(isNaN(_idAutorizacao) ? null : _idAutorizacao);
				output.writeObject(isNaN(_idAutorizacaoDocumento) ? null : _idAutorizacaoDocumento);
			} else {
				output.writeObject(isNaN(_idAutorizacaoDocumento) ? null : _idAutorizacaoDocumento);
			}		
		}
	}
}