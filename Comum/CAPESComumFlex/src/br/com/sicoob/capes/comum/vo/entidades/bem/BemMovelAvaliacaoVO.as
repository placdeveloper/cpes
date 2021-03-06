package br.com.sicoob.capes.comum.vo.entidades.bem {

    import flash.net.registerClassAlias;
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    
    import mx.collections.ListCollectionView;
    
    import org.granite.meta;

    use namespace meta;

	/**
	 * Classe que representa o Bem Móvel Avaliação.
	 * 
	 * @author Bruno.Carneiro
	 */
	registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.BemMovelAvaliacao", BemMovelAvaliacaoVO);
    public class BemMovelAvaliacaoVO extends BemMovelVO {
		
		private var _dataAvaliacao:Date;
		private var _dataCompraVenda:Date;
		private var _idPessoaCompartilhamentoAvaliador:Number;
		private var _processoAquisicao:Boolean = false;
		private var _tipoEstadoConservacao:TipoEstadoConservacaoBemVO;
		private var _tiposOnus:ListCollectionView;
		private var _valorAvaliacao:Number;
		private var _valorCompraVenda:Number;
		
		public function get valorCompraVenda():Number {
			return this._valorCompraVenda;
		}
		
		public function set valorCompraVenda(valor:Number):void {
			this._valorCompraVenda = valor;
		}
		
		public function get dataCompraVenda():Date {
			return this._dataCompraVenda;
		}
		
		public function set dataCompraVenda(valor:Date):void {
			this._dataCompraVenda = valor;
		}
		
		public function get valorAvaliacao():Number {
			return this._valorAvaliacao;
		}
		
		public function set valorAvaliacao(valor:Number):void {
			this._valorAvaliacao = valor;
		}
		
		public function get dataAvaliacao():Date {
			return this._dataAvaliacao;
		}
		
		public function set dataAvaliacao(valor:Date):void {
			this._dataAvaliacao = valor;
		}
		
		public function get processoAquisicao():Boolean {
			return this._processoAquisicao;
		}
		
		public function set processoAquisicao(valor:Boolean):void {
			this._processoAquisicao = valor;
		}
		
		public function get idPessoaCompartilhamentoAvaliador():Number {
			return this._idPessoaCompartilhamentoAvaliador;
		}
		
		public function set idPessoaCompartilhamentoAvaliador(valor:Number):void {
			this._idPessoaCompartilhamentoAvaliador = valor;
		}
		
		public function get tipoEstadoConservacao():TipoEstadoConservacaoBemVO {
			return this._tipoEstadoConservacao;
		}
		
		public function set tipoEstadoConservacao(valor:TipoEstadoConservacaoBemVO):void {
			this._tipoEstadoConservacao = valor;
		}
		
		public function get tiposOnus():ListCollectionView {
			return this._tiposOnus;
		}
		
		public function set tiposOnus(valor:ListCollectionView):void {
			this._tiposOnus = valor;
		}

        override public function readExternal(input:IDataInput):void {
			super.readExternal(input);
			_dataAvaliacao = input.readObject() as Date;
			_dataCompraVenda = input.readObject() as Date;
			_idPessoaCompartilhamentoAvaliador = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_processoAquisicao = input.readObject() as Boolean;
			_tipoEstadoConservacao = input.readObject() as TipoEstadoConservacaoBemVO;
			_tiposOnus = input.readObject() as ListCollectionView;
			_valorAvaliacao = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_valorCompraVenda = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
        }

        override public function writeExternal(output:IDataOutput):void {
			super.writeExternal(output);
			output.writeObject(_dataAvaliacao);
			output.writeObject(_dataCompraVenda);
			output.writeObject(_idPessoaCompartilhamentoAvaliador);
			output.writeObject(_processoAquisicao);
			output.writeObject(_tipoEstadoConservacao);
			output.writeObject(_tiposOnus);
			output.writeObject(_valorAvaliacao);
			output.writeObject(_valorCompraVenda);
        }
    }
}