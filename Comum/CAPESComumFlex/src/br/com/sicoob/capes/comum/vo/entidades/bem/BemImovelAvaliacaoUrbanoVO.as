package br.com.sicoob.capes.comum.vo.entidades.bem {

    import flash.net.registerClassAlias;
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    
    import org.granite.meta;

    use namespace meta;

	/**
	 * Classe que represenva o bem imóvel avaliação urbano.
	 * 
	 * @author Bruno.Carneiro
	 */
	registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.BemImovelAvaliacaoUrbano", BemImovelAvaliacaoUrbanoVO);
    public class BemImovelAvaliacaoUrbanoVO extends BemImovelAvaliacaoVO {
		
		private var _areaPrivativa:Number;
		private var _areaTerreno:Number;
		private var _areaTestada:Number;
		private var _quantidadeDormitorios:Number;
		private var _quantidadeVagasGaragem:Number;
		private var _tipoEstadoConservacao:TipoEstadoConservacaoBemVO;
		private var _tipoImplantacaoBemImovel:TipoImplantacaoBemImovelVO;
		private var _tipoPadraoAcabamentoBemImovel:TipoPadraoAcabamentoBemImovelVO;
		private var _tipoServicoCondominialBemImovel:TipoServicoCondominialBemImovelVO;
		
		public function get areaPrivativa():Number {
			return _areaPrivativa;
		}
		
		public function set areaPrivativa(valor:Number):void {
			this._areaPrivativa = valor;
		}
		
		public function get areaTerreno():Number {
			return _areaTerreno;
		}
		
		public function set areaTerreno(valor:Number):void {
			this._areaTerreno = valor;
		}
		
		public function get areaTestada():Number {
			return _areaTestada;
		}
		
		public function set areaTestada(valor:Number):void {
			this._areaTestada = valor;
		}
		
		public function get quantidadeDormitorios():Number {
			return _quantidadeDormitorios;
		}
		
		public function set quantidadeDormitorios(valor:Number):void {
			this._quantidadeDormitorios = valor;
		}
		
		public function get quantidadeVagasGaragem():Number {
			return _quantidadeVagasGaragem;
		}
		
		public function set quantidadeVagasGaragem(valor:Number):void {
			this._quantidadeVagasGaragem = valor;
		}
		
		public function get tipoEstadoConservacao():TipoEstadoConservacaoBemVO {
			return _tipoEstadoConservacao;
		}
		
		public function set tipoEstadoConservacao(valor:TipoEstadoConservacaoBemVO):void {
			this._tipoEstadoConservacao = valor;
		}
		
		public function get tipoImplantacaoBemImovel():TipoImplantacaoBemImovelVO {
			return _tipoImplantacaoBemImovel;
		}
		
		public function set tipoImplantacaoBemImovel(valor:TipoImplantacaoBemImovelVO):void {
			this._tipoImplantacaoBemImovel = valor;
		}
		
		public function get tipoPadraoAcabamentoBemImovel():TipoPadraoAcabamentoBemImovelVO {
			return _tipoPadraoAcabamentoBemImovel;
		}
		
		public function set tipoPadraoAcabamentoBemImovel(valor:TipoPadraoAcabamentoBemImovelVO):void {
			this._tipoPadraoAcabamentoBemImovel = valor;
		}
		
		public function get tipoServicoCondominialBemImovel():TipoServicoCondominialBemImovelVO {
			return _tipoServicoCondominialBemImovel;
		}
		
		public function set tipoServicoCondominialBemImovel(valor:TipoServicoCondominialBemImovelVO):void {
			this._tipoServicoCondominialBemImovel = valor;
		}

        override public function readExternal(input:IDataInput):void {
            super.readExternal(input);
			_areaPrivativa = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_areaTerreno = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_areaTestada = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_quantidadeDormitorios = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_quantidadeVagasGaragem = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_tipoEstadoConservacao = input.readObject() as TipoEstadoConservacaoBemVO;
			_tipoImplantacaoBemImovel = input.readObject() as TipoImplantacaoBemImovelVO;
			_tipoPadraoAcabamentoBemImovel = input.readObject() as TipoPadraoAcabamentoBemImovelVO;
			_tipoServicoCondominialBemImovel = input.readObject() as TipoServicoCondominialBemImovelVO;
        }

        override public function writeExternal(output:IDataOutput):void {
            super.writeExternal(output);
			output.writeObject(_areaPrivativa);
			output.writeObject(_areaTerreno);
			output.writeObject(_areaTestada);
			output.writeObject(_quantidadeDormitorios);
			output.writeObject(_quantidadeVagasGaragem);
			output.writeObject(_tipoEstadoConservacao);
			output.writeObject(_tipoImplantacaoBemImovel);
			output.writeObject(_tipoPadraoAcabamentoBemImovel);
			output.writeObject(_tipoServicoCondominialBemImovel);
        }
    }
}