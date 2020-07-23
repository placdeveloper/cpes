package br.com.sicoob.capes.comum.vo.entidades.bem {

    import br.com.sicoob.capes.comum.vo.entidades.CAPESEntidadeVO;
    import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
    
    import flash.net.registerClassAlias;
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    
    import org.granite.collections.IPersistentCollection;
    import org.granite.meta;

    use namespace meta;

	/**
	 * Classe que representa o tipo de relacionamento do bem imóvel.
	 * 
	 * @author Bruno.Carneiro
	 */
	registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.BemImovelTipoRelacionamentoPessoa", BemImovelTipoRelacionamentoVO);
    public class BemImovelTipoRelacionamentoVO extends CAPESEntidadeVO {
		private var __laziness:String = null;

		private var _areaPosse:Number;
		private var _bemImovel:BemImovelVO;
		private var _dataInicioRelacionamento:Date;
		private var _dataFimRelacionamento:Date;
		private var _idBemImovelRelacionamento:Number;
		private var _idPessoaCompartilhamento:Number;
		private var _pessoaCompartilhamento:PessoaCompartilhamentoVO;
		private var _tipoRelacionamento:TipoRelacionamentoBemImovelVO;
		
		meta function isInitialized(name:String = null):Boolean {
			if (!name) {
				return __laziness === null;
			}
			
			var property:* = this[name];
			return ((!(property is BemImovelTipoRelacionamentoVO) || (property as BemImovelTipoRelacionamentoVO).meta::isInitialized()) &&
					(!(property is IPersistentCollection) || (property as IPersistentCollection).isInitialized()));
		}
		
		public function get areaPosse():Number {
			return _areaPosse;
		}
		
		public function set areaPosse(valor:Number):void {
			this._areaPosse = valor;
		}
		
		public function get bemImovel():BemImovelVO {
			return _bemImovel;
		}
		
		public function set bemImovel(valor:BemImovelVO):void {
			this._bemImovel = valor;
		}
		
		public function get dataInicioRelacionamento():Date {
			return _dataInicioRelacionamento;
		}
		
		public function set dataInicioRelacionamento(valor:Date):void {
			this._dataInicioRelacionamento = valor;
		}
		
		public function get dataFimRelacionamento():Date {
			return _dataFimRelacionamento;
		}
		
		public function set dataFimRelacionamento(valor:Date):void {
			this._dataFimRelacionamento = valor;
		}
		
		public function get idBemImovelRelacionamento():Number {
			return _idBemImovelRelacionamento;
		}
		
		public function set idBemImovelRelacionamento(valor:Number):void {
			this._idBemImovelRelacionamento = valor;
		}
		
		public function get idPessoaCompartilhamento():Number {
			return _idPessoaCompartilhamento;
		}
		
		public function set idPessoaCompartilhamento(valor:Number):void {
			this._idPessoaCompartilhamento = valor;
		}
		
		public function get pessoaCompartilhamento():PessoaCompartilhamentoVO {
			return _pessoaCompartilhamento;
		}
		
		public function set pessoaCompartilhamento(valor:PessoaCompartilhamentoVO):void {
			this._pessoaCompartilhamento = valor;
		}
		
		public function get tipoRelacionamento():TipoRelacionamentoBemImovelVO {
			return _tipoRelacionamento;
		}
		
		public function set tipoRelacionamento(valor:TipoRelacionamentoBemImovelVO):void {
			this._tipoRelacionamento = valor;
		}
		
		override public function readExternal(input:IDataInput):void {
			__laziness = input.readObject() as String;
			if (meta::isInitialized()) {
				super.readExternal(input);
				_areaPosse = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
				_bemImovel = input.readObject() as BemImovelVO;
				_dataFimRelacionamento = input.readObject() as Date;
				_dataInicioRelacionamento = input.readObject() as Date;
				_idBemImovelRelacionamento = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
				_idPessoaCompartilhamento = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
				_pessoaCompartilhamento = input.readObject() as PessoaCompartilhamentoVO;
				_tipoRelacionamento = input.readObject() as TipoRelacionamentoBemImovelVO;
			}else {
				_idBemImovelRelacionamento = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			}
		}
		
		override public function writeExternal(output:IDataOutput):void {
			output.writeObject(__laziness);
			if (meta::isInitialized()) {
				super.writeExternal(output);
				output.writeObject(_areaPosse);
				output.writeObject(_bemImovel);
				output.writeObject(_dataFimRelacionamento);
				output.writeObject(_dataInicioRelacionamento);
				output.writeObject(_idBemImovelRelacionamento);
				output.writeObject(_idPessoaCompartilhamento);
				output.writeObject(_pessoaCompartilhamento);
				output.writeObject(_tipoRelacionamento);
			}else {
				output.writeObject(_idBemImovelRelacionamento);
			}
		}
    }
}