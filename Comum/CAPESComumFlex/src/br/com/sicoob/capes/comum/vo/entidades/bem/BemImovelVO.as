package br.com.sicoob.capes.comum.vo.entidades.bem {

    import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
    import br.com.sicoob.capes.comum.vo.entidades.UnidadeMedidaVO;
    
    import flash.net.registerClassAlias;
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    
    import mx.collections.ListCollectionView;
    
    import org.granite.meta;

    use namespace meta;

	/**
	 * Clase que representa o bem imóvel.
	 * 
	 * @author Bruno.Carneiro
	 */
	registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.BemImovel", BemImovelVO);
    public class BemImovelVO extends BemVO {
		
		private var _areaTotal:Number;
		private var _complemento:String;
		private var _denominacao:String;
		private var _descricaoRoteiro:String;
		private var _idLocalidade:Number;
		private var _idLogradouro:Number;
		private var _idPessoaCompartilhamentoCartorio:Number;
		private var _incra:String;
		private var _informacoesGerais:String;
		private var _latitude:Number;
		private var _longitude:Number;
		private var _matricula:String;
		private var _nirf:String;
		private var _numero:String;
		private var _pessoaCompartilhamentoCartorio:PessoaCompartilhamentoVO;
		private var _relacionamentoPessoas:ListCollectionView;
		private var _tipoBem:TipoBemImovelVO;
		private var _tipoLocalizacaoBem:TipoLocalizacaoBemVO;
		private var _tipoUsoBem:TipoUsoBemImovelVO;
		private var _unidadeMedida:UnidadeMedidaVO;
		
		public function get areaTotal():Number {
			return _areaTotal;
		}
		
		public function set areaTotal(valor:Number):void {
			this._areaTotal = valor;
		}
		
		public function get complemento():String {
			return _complemento;
		}
		
		public function set complemento(valor:String):void {
			this._complemento = valor;
		}
		
		public function get denominacao():String {
			return _denominacao;	
		}
		
		public function set denominacao(valor:String):void {
			this._denominacao = valor;
		}
		
		public function get descricaoRoteiro():String {
			return _descricaoRoteiro;
		}
		
		public function set descricaoRoteiro(valor:String):void {
			this._descricaoRoteiro = valor;
		}
		
		public function get incra():String {
			return _incra;
		}
		
		public function set incra(valor:String):void {
			this._incra = valor;
		}
		
		public function get informacoesGerais():String {
			return _informacoesGerais;
		}
		
		public function set informacoesGerais(valor:String):void {
			this._informacoesGerais = valor;
		}
		
		public function get latitude():Number {
			return _latitude;
		}
		
		public function set latitude(valor:Number):void {
			this._latitude = valor;
		}
		
		public function get idLocalidade():Number {
			return _idLocalidade;
		}
		
		public function set idLocalidade(valor:Number):void {
			this._idLocalidade = valor;
		}
		
		public function get idLogradouro():Number {
			return _idLogradouro;
		}
		
		public function set idLogradouro(valor:Number):void {
			this._idLogradouro = valor;
		}
		
		public function get idPessoaCompartilhamentoCartorio():Number {
			return _idPessoaCompartilhamentoCartorio;
		}
		
		public function set idPessoaCompartilhamentoCartorio(valor:Number):void {
			this._idPessoaCompartilhamentoCartorio = valor;
		}
		
		public function get pessoaCompartilhamentoCartorio():PessoaCompartilhamentoVO {
			return _pessoaCompartilhamentoCartorio;
		}
		
		public function set pessoaCompartilhamentoCartorio(valor:PessoaCompartilhamentoVO):void {
			this._pessoaCompartilhamentoCartorio = valor;
		}
		
		public function get longitude():Number {
			return _longitude;
		}
		
		public function set longitude(valor:Number):void {
			this._longitude = valor;
		}
		
		public function get matricula():String {
			return _matricula;
		}
		
		public function set matricula(valor:String):void {
			this._matricula = valor;
		}
		
		public function get nirf():String {
			return _nirf;
		}
		
		public function set nirf(valor:String):void {
			this._nirf = valor;
		}
		
		public function get numero():String {
			return _numero;
		}
		
		public function set numero(valor:String):void {
			this._numero = valor;
		}
		
		public function get relacionamentoPessoas():ListCollectionView {
			return _relacionamentoPessoas;
		}
		
		public function set relacionamentoPessoas(valor:ListCollectionView):void {
			this._relacionamentoPessoas = valor;
		}
		
		public function get tipoBem():TipoBemImovelVO {
			return _tipoBem;
		}
		
		public function set tipoBem(valor:TipoBemImovelVO):void {
			this._tipoBem = valor;
		}
		
		public function get tipoLocalizacaoBem():TipoLocalizacaoBemVO {
			return _tipoLocalizacaoBem;
		}
		
		public function set tipoLocalizacaoBem(valor:TipoLocalizacaoBemVO):void {
			this._tipoLocalizacaoBem = valor;
		}
		
		public function get tipoUsoBem():TipoUsoBemImovelVO {
			return _tipoUsoBem;
		}
		
		public function set tipoUsoBem(valor:TipoUsoBemImovelVO):void {
			this._tipoUsoBem = valor;
		}
		
		public function get unidadeMedida():UnidadeMedidaVO {
			return _unidadeMedida;
		}
		
		public function set unidadeMedida(valor:UnidadeMedidaVO):void {
			this._unidadeMedida = valor;
		}
		
        override public function readExternal(input:IDataInput):void {
            super.readExternal(input);
			_areaTotal = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_complemento = input.readObject() as String;
			_denominacao = input.readObject() as String;
			_descricaoRoteiro = input.readObject() as String;
			_idLocalidade = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_idLogradouro = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_idPessoaCompartilhamentoCartorio = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_incra = input.readObject() as String;
			_informacoesGerais = input.readObject() as String;
			_latitude = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_longitude = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_matricula = input.readObject() as String;
			_nirf = input.readObject() as String;
			_numero = input.readObject() as String;
			_pessoaCompartilhamentoCartorio = input.readObject() as PessoaCompartilhamentoVO;
			_relacionamentoPessoas = input.readObject() as ListCollectionView;
			_tipoBem = input.readObject() as TipoBemImovelVO;
			_tipoLocalizacaoBem = input.readObject() as TipoLocalizacaoBemVO;
			_tipoUsoBem = input.readObject() as TipoUsoBemImovelVO;
			_unidadeMedida = input.readObject() as UnidadeMedidaVO;
        }

        override public function writeExternal(output:IDataOutput):void {
        	super.writeExternal(output);
			output.writeObject(_areaTotal);
			output.writeObject(_complemento);
			output.writeObject(_denominacao);
			output.writeObject(_descricaoRoteiro);
			output.writeObject(_idLocalidade);
			output.writeObject(_idLogradouro);
			output.writeObject(_idPessoaCompartilhamentoCartorio);
			output.writeObject(_incra);
			output.writeObject(_informacoesGerais);
			output.writeObject(_latitude);
			output.writeObject(_longitude);
			output.writeObject(_matricula);
			output.writeObject(_nirf);
			output.writeObject(_numero);
			output.writeObject(_pessoaCompartilhamentoCartorio);
			output.writeObject(_relacionamentoPessoas);
			output.writeObject(_tipoBem);
			output.writeObject(_tipoLocalizacaoBem);
			output.writeObject(_tipoUsoBem);
			output.writeObject(_unidadeMedida);
        }
    }
}