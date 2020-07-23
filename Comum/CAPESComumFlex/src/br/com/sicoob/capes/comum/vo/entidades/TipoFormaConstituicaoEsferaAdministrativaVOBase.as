package br.com.sicoob.capes.comum.vo.entidades{
	
	import br.com.bancoob.tipos.Booleano;
	import br.com.bancoob.tipos.SerializacaoObjetos;
	import br.com.sicoob.capes.comum.vo.entidades.pk.TipoFormaConstituicaoEsferaAdministrativaPK;
	
	import flash.utils.IDataInput;
	import flash.utils.IDataOutput;
	
	import org.granite.collections.IPersistentCollection;
	import org.granite.meta;
	
	use namespace meta;
	
	[Bindable]
	public class TipoFormaConstituicaoEsferaAdministrativaVOBase extends CAPESEntidadeVO {
		
		private var __laziness:String = null;
		
		private var _pk:TipoFormaConstituicaoEsferaAdministrativaPK;
		private var _tipoFormaConstituicao:TipoFormaConstituicaoVO;
		
		public function get tipoFormaConstituicao():TipoFormaConstituicaoVO
		{
			return _tipoFormaConstituicao;
		}

		public function set tipoFormaConstituicao(value:TipoFormaConstituicaoVO):void
		{
			_tipoFormaConstituicao = value;
		}
		
		meta function isInitialized(name:String = null):Boolean {
			if (!name)
				return __laziness === null;
			
			var property:* = this[name];
			return (
				(!(property is PerfilTarifarioVO) || (property as PerfilTarifarioVO).meta::isInitialized()) &&
				(!(property is IPersistentCollection) || (property as IPersistentCollection).isInitialized())
			);
		}
		
		public function get descricaoTipoForma():String {
			return _tipoFormaConstituicao.descricao;
		}
		
		public function set pk(value:TipoFormaConstituicaoEsferaAdministrativaPK):void {
			_pk = value;
		}
		public function get pk():TipoFormaConstituicaoEsferaAdministrativaPK {
			return _pk;
		}
		
		override public function readExternal(input:IDataInput):void {
			__laziness = input.readObject() as String;
			if (meta::isInitialized()) {
				super.readExternal(input);
				_pk = input.readObject() as TipoFormaConstituicaoEsferaAdministrativaPK;
				_tipoFormaConstituicao = input.readObject() as TipoFormaConstituicaoVO;
			}
			else {
				_pk = input.readObject() as TipoFormaConstituicaoEsferaAdministrativaPK;
			}
		}
		
		override public function writeExternal(output:IDataOutput):void {
			output.writeObject(__laziness);
			if (meta::isInitialized()) {
				super.writeExternal(output);
				output.writeObject(_pk);
				output.writeObject(_tipoFormaConstituicao);
			}
			else {
				output.writeObject(_pk);
			}
		}
	}
}