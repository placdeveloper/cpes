package br.com.sicoob.capes.comum.vo.entidades.bem {

    import br.com.sicoob.capes.comum.vo.entidades.CAPESEntidadeVO;
    
    import flash.net.registerClassAlias;
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    
    import mx.collections.ListCollectionView;
    
    import org.granite.collections.IPersistentCollection;
    import org.granite.meta;

    use namespace meta;

	/**
	 * Classe que representa o tipo de localização do bem.
	 * 
	 * @author Bruno.Carneiro
	 */
	registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.TipoLocalizacaoBem", TipoLocalizacaoBemVO);
    public class TipoLocalizacaoBemVO extends CAPESEntidadeVO {
		private var __laziness:String = null;
		
		private var _codigo:Number;
		private var _descricao:String;
		private var _tipoClassificacaoBem:TipoClassificacaoBemVO;
		private var _unidadesMedida:ListCollectionView;
		
		meta function isInitialized(name:String = null):Boolean {
			if (!name){
				return __laziness === null;
			}
			
			var property:* = this[name];
			return ((!(property is TipoLocalizacaoBemVO) || (property as TipoLocalizacaoBemVO).meta::isInitialized()) &&
				(!(property is IPersistentCollection) || (property as IPersistentCollection).isInitialized()));
		}
		
		public function get codigo():Number {
			return _codigo;
		}
		
		public function set codigo(valor:Number):void {
			this._codigo = valor;
		}
		
		public function get descricao():String {
			return _descricao;
		}
		
		public function set descricao(valor:String):void {
			this._descricao = valor;
		}
		
		public function get tipoClassificacaoBem():TipoClassificacaoBemVO {
			return _tipoClassificacaoBem;
		}
		
		public function set tipoClassificacaoBem(valor:TipoClassificacaoBemVO):void {
			this._tipoClassificacaoBem = valor;
		}
		
		public function get unidadesMedida():ListCollectionView {
			return _unidadesMedida;
		}
		
		public function set unidadesMedida(valor:ListCollectionView):void {
			this._unidadesMedida = valor;
		}
		
		override public function readExternal(input:IDataInput):void {
			__laziness = input.readObject() as String;
			if (meta::isInitialized()) {
				super.readExternal(input);
				_codigo = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
				_descricao = input.readObject() as String;
				_tipoClassificacaoBem = input.readObject() as TipoClassificacaoBemVO;
				_unidadesMedida = input.readObject() as ListCollectionView;
			}else {
				_codigo = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			}
		}
		
		override public function writeExternal(output:IDataOutput):void {
			output.writeObject(__laziness);
			if (meta::isInitialized()) {
				super.writeExternal(output);
				output.writeObject(_codigo);
				output.writeObject(_descricao);
				output.writeObject(_tipoClassificacaoBem);
				output.writeObject(_unidadesMedida);
			}else {
				output.writeObject(_codigo);
			}
		}
    }
}