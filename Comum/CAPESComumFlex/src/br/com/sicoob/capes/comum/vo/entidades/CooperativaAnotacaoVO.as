package br.com.sicoob.capes.comum.vo.entidades {
	import br.com.sicoob.capes.comum.vo.entidades.pk.CooperativaAnotacaoPK;
	
	import flash.net.registerClassAlias;
	import flash.utils.IDataInput;
	import flash.utils.IDataOutput;
	
	import org.granite.collections.IPersistentCollection;
	import org.granite.meta;
	
	use namespace meta;

	registerClassAlias("br.com.sicoob.capes.negocio.entidades.CooperativaAnotacao", CooperativaAnotacaoVO);
	public class CooperativaAnotacaoVO extends CAPESEntidadeVO {
		
		private var __laziness:String = null;
		
		private var _pk:CooperativaAnotacaoPK;
		private var _tipoAnotacao:TipoAnotacaoVO;
		
		meta function isInitialized(name:String = null):Boolean {
			if (!name)
				return __laziness === null;
			
			var property:* = this[name];
			return (
				(!(property is CooperativaAnotacaoVO) || (property as CooperativaAnotacaoVO).meta::isInitialized()) &&
				(!(property is IPersistentCollection) || (property as IPersistentCollection).isInitialized())
			);
		}
		
		public function get pk():CooperativaAnotacaoPK {
			return _pk;
		}
		
		public function set pk(valor:CooperativaAnotacaoPK):void {
			_pk = valor;
		}
		
		public function get tipoAnotacao():TipoAnotacaoVO {
			return _tipoAnotacao;
		}
		
		public function set tipoAnotacao(valor:TipoAnotacaoVO):void {
			_tipoAnotacao = valor;
		}
		
		override public function readExternal(input:IDataInput):void {
			__laziness = input.readObject() as String;
			if (meta::isInitialized()) {
				super.readExternal(input);
				_pk = input.readObject() as CooperativaAnotacaoPK;
				_tipoAnotacao = input.readObject() as TipoAnotacaoVO;
			} else {
				_pk = input.readObject() as CooperativaAnotacaoPK;
			}
		}
		
		override public function writeExternal(output:IDataOutput):void {
			output.writeObject(__laziness);
			if (meta::isInitialized()) {
				super.writeExternal(output);
				output.writeObject(_pk);
				output.writeObject(_tipoAnotacao);
			} else {
				output.writeObject(_pk);
			}
		}
	}
}