package br.com.sicoob.capes.comum.vo.entidades.bem {

    import br.com.sicoob.capes.comum.vo.entidades.CAPESEntidadeVO;
    
    import flash.net.registerClassAlias;
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    
    import org.granite.collections.IPersistentCollection;
    import org.granite.meta;

    use namespace meta;

	/**
	 * Classe que representa o tipo do bem imóvel.
	 * 
	 * @author Bruno.Carneiro
	 */
	registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.TipoBemImovel", TipoBemImovelVO);
    public class TipoBemImovelVO extends CAPESEntidadeVO {
		private var __laziness:String = null;

		private var _codigo:Number;
		private var _descricao:String;
		private var _habilitaTipoEstadoConservacao:Boolean = false;
		private var _habilitaTipoPadraoAcabamento:Boolean = false;
		private var _habilitaTipoServicoCondominial:Boolean = false;
		private var _habilitaAreaPrivativa:Boolean = false;
		private var _habilitaQuantidadeDormitorio:Boolean = false;
		private var _habilitaQuantidadeVagasGaragem:Boolean = false;
		private var _habilitaAreaTerreno:Boolean = false;
		
		meta function isInitialized(name:String = null):Boolean {
			if (!name){
				return __laziness === null;
			}
			
			var property:* = this[name];
			return ((!(property is TipoBemImovelVO) || (property as TipoBemImovelVO).meta::isInitialized()) &&
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
		
		public function get habilitaTipoPadraoAcabamento():Boolean {
			return _habilitaTipoPadraoAcabamento;
		}
		
		public function set habilitaTipoPadraoAcabamento(valor:Boolean):void {
			this._habilitaTipoPadraoAcabamento = valor;
		}
		
		public function get habilitaTipoServicoCondominial():Boolean {
			return _habilitaTipoServicoCondominial;
		}
		
		public function set habilitaTipoServicoCondominial(valor:Boolean):void {
			this._habilitaTipoServicoCondominial = valor;
		}
		
		public function get habilitaAreaPrivativa():Boolean {
			return _habilitaAreaPrivativa;
		}
		
		public function set habilitaAreaPrivativa(valor:Boolean):void {
			this._habilitaAreaPrivativa = valor;
		}
		
		public function get habilitaQuantidadeDormitorio():Boolean {
			return _habilitaQuantidadeDormitorio;
		}
		
		public function set habilitaQuantidadeDormitorio(valor:Boolean):void {
			this._habilitaQuantidadeDormitorio = valor;
		}
		
		public function get habilitaQuantidadeVagasGaragem():Boolean {
			return _habilitaQuantidadeVagasGaragem;
		}
		
		public function set habilitaQuantidadeVagasGaragem(valor:Boolean):void {
			this._habilitaQuantidadeVagasGaragem = valor;
		}
		
		public function get habilitaAreaTerreno():Boolean {
			return _habilitaAreaTerreno;
		}
		
		public function set habilitaAreaTerreno(valor:Boolean):void {
			this._habilitaAreaTerreno = valor;
		}
		
		override public function readExternal(input:IDataInput):void {
			__laziness = input.readObject() as String;
			if (meta::isInitialized()) {
				super.readExternal(input);
				_codigo = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
				_descricao = input.readObject() as String;
				_habilitaAreaPrivativa = input.readObject() as Boolean;
				_habilitaAreaTerreno = input.readObject() as Boolean;
				_habilitaQuantidadeDormitorio = input.readObject() as Boolean;
				_habilitaQuantidadeVagasGaragem = input.readObject() as Boolean;
				_habilitaTipoEstadoConservacao = input.readObject() as Boolean;
				_habilitaTipoPadraoAcabamento = input.readObject() as Boolean;
				_habilitaTipoServicoCondominial = input.readObject() as Boolean;
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
				output.writeObject(_habilitaAreaPrivativa);
				output.writeObject(_habilitaAreaTerreno);
				output.writeObject(_habilitaQuantidadeDormitorio);
				output.writeObject(_habilitaQuantidadeVagasGaragem);
				output.writeObject(_habilitaTipoEstadoConservacao);
				output.writeObject(_habilitaTipoPadraoAcabamento);
				output.writeObject(_habilitaTipoServicoCondominial);
			}else {
				output.writeObject(_codigo);
			}
		}
    }
}