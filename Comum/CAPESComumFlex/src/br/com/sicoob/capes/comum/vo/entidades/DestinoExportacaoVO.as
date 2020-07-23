package br.com.sicoob.capes.comum.vo.entidades{
	import br.com.bancoob.tipos.Booleano;
	import br.com.bancoob.tipos.SerializacaoObjetos;
	
	import flash.net.registerClassAlias;
	import flash.utils.IDataInput;
	import flash.utils.IDataOutput;
	
	import mx.collections.ListCollectionView;
	
	import org.granite.collections.IPersistentCollection;
	import org.granite.meta;
	
	use namespace meta;
	
	registerClassAlias("br.com.sicoob.capes.negocio.entidades.DestinoExportacao", DestinoExportacaoVO);
	[Bindable]
	public class DestinoExportacaoVO extends CAPESEntidadeVO {
		private var __laziness:String = null;
		
		private var _ativo:Booleano;
		private var _codificacaoArquivo:String;
		private var _codigo:Number;
		private var _descricao:String;
		private var _diretorio:String;
		private var _tiposInformacao:ListCollectionView;
		
		meta function isInitialized(name:String = null):Boolean {
			if (!name){
				return __laziness === null;
			}
			
			var property:* = this[name];
			return ((!(property is DestinoExportacaoVO) || (property as DestinoExportacaoVO).meta::isInitialized()) &&
					(!(property is IPersistentCollection) || (property as IPersistentCollection).isInitialized()));
		}

		public function get ativo():Booleano {
			return _ativo;
		}

		public function set ativo(value:Booleano):void {
			_ativo = value;
		}

		public function get codificacaoArquivo():String {
			return _codificacaoArquivo;
		}

		public function set codificacaoArquivo(value:String):void {
			_codificacaoArquivo = value;
		}

		public function get codigo():Number {
			return _codigo;
		}

		public function set codigo(value:Number):void {
			_codigo = value;
		}

		public function get descricao():String {
			return _descricao;
		}

		public function set descricao(value:String):void {
			_descricao = value;
		}
		
		public function get diretorio():String {
			return _diretorio;
		}
		
		public function set diretorio(value:String):void {
			_diretorio = value;
		}
		
		public function set tiposInformacao(value:ListCollectionView):void {
			_tiposInformacao = value;
		}
		
		public function get tiposInformacao():ListCollectionView {
			return _tiposInformacao;
		}
		
		override public function readExternal(input:IDataInput):void {
			__laziness = input.readObject() as String;
			if (meta::isInitialized()) {
				super.readExternal(input);
				_ativo = SerializacaoObjetos.lerBooleano(input);
				_codificacaoArquivo = input.readObject() as String;
				_codigo = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
				_descricao = input.readObject() as String;
				_diretorio = input.readObject() as String;
				_tiposInformacao = input.readObject() as ListCollectionView; 
			} else {
				_codigo = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			}
		}
		
		override public function writeExternal(output:IDataOutput):void {
			output.writeObject(__laziness);
			if (meta::isInitialized()) {
				super.writeExternal(output);
				SerializacaoObjetos.escreverBooleano(_ativo, output);
				output.writeObject(_codificacaoArquivo);
				output.writeObject(_codigo);
				output.writeObject(_descricao);
				output.writeObject(_diretorio);
				output.writeObject(_tiposInformacao);
			}
			else {
				output.writeObject(_codigo);
			}
		}
	}
}