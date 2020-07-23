package br.com.sicoob.capes.comum.vo.entidades {
	
	import flash.net.registerClassAlias;
	import flash.utils.IDataInput;
	import flash.utils.IDataOutput;
	
	import br.com.bancoob.tipos.IDateTime;
	
	import org.granite.meta;
	import org.granite.collections.IPersistentCollection;
	
	use namespace meta;

	registerClassAlias("br.com.sicoob.capes.negocio.entidades.GrupoEconomicoInstituicao", GrupoEconomicoNovoInstituicaoVO);
	[Bindable]
    public class GrupoEconomicoNovoInstituicaoVO extends CAPESEntidadeVO {
		private var __laziness:String = null;
		
		private var _idInstituicao:Number;
		private var _idGrupo:Number;
		private var _dataHoraInicio:IDateTime;
		
		meta function isInitialized(name:String = null):Boolean {
			if (!name){
				return __laziness === null;
			}
			
			var property:* = this[name];
			return (
				(!(property is GrupoEconomicoNovoInstituicaoVO) || (property as GrupoEconomicoNovoInstituicaoVO).meta::isInitialized()) &&
				(!(property is IPersistentCollection) || (property as IPersistentCollection).isInitialized()));
		}
		
		public function get idInstituicao():Number {
			return _idInstituicao;
		}
		
		public function set idInstituicao(value:Number):void {
			_idInstituicao = value;
		}
		
		public function get idGrupo():Number {
			return _idGrupo;
		}
		
		public function set idGrupo(value:Number):void {
			_idGrupo = value;
		}
		
		public function get dataHoraInicio():IDateTime {
			return _dataHoraInicio;
		}
		
		public function set dataHoraInicio(value:IDateTime):void {
			_dataHoraInicio = value;
		}
		
		override public function readExternal(input:IDataInput):void {
			__laziness = input.readObject() as String;
			if (meta::isInitialized()) {
				super.readExternal(input);
				_dataHoraInicio = input.readObject() as IDateTime;
				_idGrupo = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
				_idInstituicao = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			}
			else {
				_idGrupo = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
				_idInstituicao = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			}
		}
		
		override public function writeExternal(output:IDataOutput):void {
			output.writeObject(__laziness);
			if (meta::isInitialized()) {
				super.writeExternal(output);
				output.writeObject(_dataHoraInicio);
				output.writeObject(_idGrupo);
				output.writeObject(_idInstituicao);
			}
			else {
				output.writeObject(_idGrupo);
				output.writeObject(_idInstituicao);
			}
		}

    }
}
