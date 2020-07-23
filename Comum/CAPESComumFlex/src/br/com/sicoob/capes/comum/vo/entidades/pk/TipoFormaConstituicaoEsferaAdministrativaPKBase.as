package br.com.sicoob.capes.comum.vo.entidades.pk{
	
	import br.com.bancoob.negocio.entidades.BancoobChavePrimaria;
	import br.com.sicoob.capes.comum.vo.entidades.TipoFormaConstituicaoEsferaAdministrativaVO;
	
	import flash.utils.IDataInput;
	import flash.utils.IDataOutput;
	
	import org.granite.collections.IPersistentCollection;
	import org.granite.meta;
	
	use namespace meta;
	
	[Bindable]
	public class TipoFormaConstituicaoEsferaAdministrativaPKBase extends BancoobChavePrimaria {
		
		private var __laziness:String = null;
		
		private var _codigoEsferaAdministrativa:Number;
		private var _codigoTipoFormaConstituicao:Number;
		
		meta function isInitialized(name:String = null):Boolean {
			if (!name)
				return __laziness === null;
			
			var property:* = this[name];
			return (
				(!(property is TipoFormaConstituicaoEsferaAdministrativaVO) || (property as TipoFormaConstituicaoEsferaAdministrativaVO).meta::isInitialized()) &&
				(!(property is IPersistentCollection) || (property as IPersistentCollection).isInitialized())
			);
		}
		
		public function set codigoEsferaAdministrativa(value:Number):void {
			_codigoEsferaAdministrativa = value;
		}
		public function get codigoEsferaAdministrativa():Number {
			return _codigoEsferaAdministrativa;
		}
		
		public function set codigoTipoFormaConstituicao(value:Number):void {
			_codigoTipoFormaConstituicao = value;
		}
		public function get codigoTipoFormaConstituicao():Number {
			return _codigoTipoFormaConstituicao;
		}
		
		override public function readExternal(input:IDataInput):void {
			_codigoEsferaAdministrativa = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_codigoTipoFormaConstituicao = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
		}
		
		override public function writeExternal(output:IDataOutput):void {
			output.writeObject(_codigoEsferaAdministrativa);
			output.writeObject(_codigoTipoFormaConstituicao);
		}
	}
}