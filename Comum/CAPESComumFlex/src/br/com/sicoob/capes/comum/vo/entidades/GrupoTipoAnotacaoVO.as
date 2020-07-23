package br.com.sicoob.capes.comum.vo.entidades{
	import flash.net.registerClassAlias;
	import flash.utils.IDataInput;
	import flash.utils.IDataOutput;
	
	import mx.collections.ListCollectionView;
	
	import org.granite.collections.IPersistentCollection;
	import org.granite.meta;
	
	use namespace meta;
	
	registerClassAlias("br.com.sicoob.capes.negocio.entidades.GrupoTipoAnotacao", GrupoTipoAnotacaoVO);
	[Bindable]
	public class GrupoTipoAnotacaoVO extends CAPESEntidadeVO {
		private var __laziness:String = null;
		
		private var _idGrupoTipoAnotacao:Number;
		private var _nome:String;
		private var _tiposAnotacao:ListCollectionView;
		
		meta function isInitialized(name:String = null):Boolean {
			if (!name){
				return __laziness === null;
			}
			
			var property:* = this[name];
			return ((!(property is GrupoTipoAnotacaoVO) || (property as GrupoTipoAnotacaoVO).meta::isInitialized()) &&
					(!(property is IPersistentCollection) || (property as IPersistentCollection).isInitialized()));
		}

		public function get idGrupoTipoAnotacao():Number {
			return _idGrupoTipoAnotacao;
		}

		public function set idGrupoTipoAnotacao(valor:Number):void {
			_idGrupoTipoAnotacao = valor;
		}

		public function get nome():String {
			return _nome;
		}

		public function set nome(valor:String):void {
			_nome = valor;
		}
		
		public function get tiposAnotacao():ListCollectionView {
			return _tiposAnotacao;
		}
		
		public function set tiposAnotacao(valor:ListCollectionView):void {
			_tiposAnotacao = valor;
		}
		
		public override function readExternal(input:IDataInput):void {
			__laziness = input.readObject() as String;
			if (meta::isInitialized()) {
				super.readExternal(input);
				_idGrupoTipoAnotacao = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
				_nome = input.readObject() as String;
				_tiposAnotacao = input.readObject() as ListCollectionView; 
			} else {
				_idGrupoTipoAnotacao = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			}
		}
		
		public override function writeExternal(output:IDataOutput):void {
			output.writeObject(__laziness);
			if (meta::isInitialized()) {
				super.writeExternal(output);
				output.writeObject(_idGrupoTipoAnotacao);
				output.writeObject(_nome);
				output.writeObject(_tiposAnotacao);
			}
			else {
				output.writeObject(_idGrupoTipoAnotacao);
			}
		}
	}
}