package br.com.sicoob.capes.comum.vo.entidades.bem {

    import br.com.sicoob.capes.comum.vo.entidades.CAPESEntidadeVO;
    
    import flash.net.registerClassAlias;
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    
    import org.granite.collections.IPersistentCollection;
    import org.granite.meta;

    use namespace meta;

	/**
	 * Classe que representa o tipo do bem móvel.
	 * 
	 * @author Bruno.Carneiro
	 */
	registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.TipoBemMovel", TipoBemMovelVO);
    public class TipoBemMovelVO extends CAPESEntidadeVO {
		private var __laziness:String = null;
		
		private var _codigo:Number;
		private var _descricao:String;
		private var _habilitaTipoEstadoConservacao:Boolean = false;
		private var _possuiDadosAvancados:Boolean = false;
		
		meta function isInitialized(name:String = null):Boolean {
			if (!name){
				return __laziness === null;
			}
			
			var property:* = this[name];
			return ((!(property is TipoBemMovelVO) || (property as TipoBemMovelVO).meta::isInitialized()) &&
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
		
		public function get habilitaTipoEstadoConservacao():Boolean {
			return _habilitaTipoEstadoConservacao;
		}
		
		public function set habilitaTipoEstadoConservacao(valor:Boolean):void {
			this._habilitaTipoEstadoConservacao = valor;
		}
		
		public function get possuiDadosAvancados():Boolean {
			return _possuiDadosAvancados;
		}
		
		public function set possuiDadosAvancados(valor:Boolean):void {
			this._possuiDadosAvancados = valor;
		}
		
		override public function readExternal(input:IDataInput):void {
			__laziness = input.readObject() as String;
			if (meta::isInitialized()) {
				super.readExternal(input);
				_codigo = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
				_descricao = input.readObject() as String;
				_habilitaTipoEstadoConservacao = input.readObject() as Boolean;
				_possuiDadosAvancados = input.readObject() as Boolean;
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
				output.writeObject(_habilitaTipoEstadoConservacao);
				output.writeObject(_possuiDadosAvancados);
			}else {
				output.writeObject(_codigo);
			}
		}
		
    }
}