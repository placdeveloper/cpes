package br.com.sicoob.capes.corporativo.componentes.procurarBens.vo {

    import flash.net.registerClassAlias;
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    
	/**
	 * Clase que representa o bem imóvel.
	 * 
	 * @author Bruno.Carneiro
	 */
	registerClassAlias("br.com.sicoob.capes.api.negocio.vo.BemImovelVO", BemImovelVO);
    public class BemImovelVO extends BemVO {
		
		private var _areaPrivativa:Number;
		private var _areaTerreno:Number;
		private var _areaTestada:Number;
		private var _areaTotal:Number;
		private var _benfeitoria:String;
		private var _codigoTipoBem:Number;
		private var _codigoTipoEstadoConservacao:Number;
		private var _codigoTipoImplantacaoBemImovel:Number;
		private var _codigoTipoLocalizacaoBem:Number;
		private var _codigoTipoPadraoAcabamentoBemImovel:Number;
		private var _codigoTipoServicoCondominialBemImovel:Number;
		private var _codigoTipoUsoBem:Number;
		private var _codigoUnidadeMedida:Number;
		private var _complemento:String;
		private var _dataAvaliacao:Date;
		private var _dataCompraVenda:Date;
		private var _denominacao:String;
		private var _descricaoRoteiro:String;
		private var _descricaoTipoBem:String;
		private var _descricaoTipoEstadoConservacao:String;
		private var _descricaoTipoImplantacaoBemImovel:String;
		private var _descricaoTipoLocalizacaoBem:String;
		private var _descricaoTipoPadraoAcabamentoBemImovel:String;
		private var _descricaoTipoServicoCondominialBemImovel:String;
		private var _descricaoTipoUsoBem:String;
		private var _descricaoUnidadeMedida:String;
		private var _idLocalidade:Number;
		private var _idLogradouro:Number;
		private var _idPessoaAvaliador:Number;
		private var _idPessoaCartorio:Number;
		private var _incra:String;
		private var _informacoesGerais:String;
		private var _latitude:Number;
		private var _longitude:Number;
		private var _matricula:String;
		private var _nirf:String;
		private var _numero:String;
		private var _processoAquisicao:Boolean;
		private var _quantidadeDormitorios:Number;
		private var _quantidadeVagasGaragem:Number;
		private var _valorAvaliacao:Number;
		private var _valorBenfeitoria:Number;
		private var _valorCompraVenda:Number;
		
		public function get areaPrivativa():Number {
			return this._areaPrivativa;
		}
		
		public function set areaPrivativa(valor:Number):void {
			this._areaPrivativa = valor;
		}
		
		public function get areaTerreno():Number {
			return this._areaTerreno;
		}
		
		public function set areaTerreno(valor:Number):void {
			this._areaTerreno = valor;
		}
		
		public function get areaTestada():Number {
			return this._areaTestada;
		}
		
		public function set areaTestada(valor:Number):void {
			this._areaTestada = valor;
		}
		
		public function get areaTotal():Number {
			return this._areaTotal;
		}
		
		public function set areaTotal(valor:Number):void {
			this._areaTotal = valor;
		}
		
		public function get benfeitoria():String {
			return this._benfeitoria;
		}
		
		public function set benfeitoria(valor:String):void {
			this._benfeitoria = valor;
		}
		
		public function get codigoTipoBem():Number {
			return this._codigoTipoBem;
		}
		
		public function set codigoTipoBem(valor:Number):void {
			this._codigoTipoBem = valor;
		}
		
		public function get codigoTipoEstadoConservacao():Number {
			return this._codigoTipoEstadoConservacao;
		}
		
		public function set codigoTipoEstadoConservacao(valor:Number):void {
			this._codigoTipoEstadoConservacao = valor;
		}
		
		public function get codigoTipoImplantacaoBemImovel():Number {
			return this._codigoTipoImplantacaoBemImovel;
		}
		
		public function set codigoTipoImplantacaoBemImovel(valor:Number):void {
			this._codigoTipoImplantacaoBemImovel = valor;
		}
		
		public function get codigoTipoLocalizacaoBem():Number {
			return this._codigoTipoLocalizacaoBem;
		}
		
		public function set codigoTipoLocalizacaoBem(valor:Number):void {
			this._codigoTipoLocalizacaoBem = valor;
		}
		
		public function get codigoTipoPadraoAcabamentoBemImovel():Number {
			return this._codigoTipoPadraoAcabamentoBemImovel;
		}
		
		public function set codigoTipoPadraoAcabamentoBemImovel(valor:Number):void {
			this._codigoTipoPadraoAcabamentoBemImovel = valor;
		}
		
		public function get codigoTipoServicoCondominialBemImovel():Number {
			return this._codigoTipoServicoCondominialBemImovel;
		}
		
		public function set codigoTipoServicoCondominialBemImovel(valor:Number):void {
			this._codigoTipoServicoCondominialBemImovel = valor;
		}
		
		public function get codigoTipoUsoBem():Number {
			return this._codigoTipoUsoBem;
		}
		
		public function set codigoTipoUsoBem(valor:Number):void {
			this._codigoTipoUsoBem = valor;
		}
		
		public function get codigoUnidadeMedida():Number {
			return this._codigoUnidadeMedida;
		}
		
		public function set codigoUnidadeMedida(valor:Number):void {
			this._codigoUnidadeMedida = valor;
		}
		
		public function get complemento():String {
			return this._complemento;
		}
		
		public function set complemento(valor:String):void {
			this._complemento = valor;
		}
		
		public function get dataAvaliacao():Date {
			return this._dataAvaliacao;
		}
		
		public function set dataAvaliacao(valor:Date):void {
			this._dataAvaliacao = valor;
		}
		
		public function get dataCompraVenda():Date {
			return this._dataCompraVenda;
		}
		
		public function set dataCompraVenda(valor:Date):void {
			this._dataCompraVenda = valor;
		}
		
		public function get denominacao():String {
			return this._denominacao;
		}
		
		public function set denominacao(valor:String):void {
			this._denominacao = valor;
		}
		
		public function get descricaoRoteiro():String {
			return this._descricaoRoteiro;
		}
		
		public function set descricaoRoteiro(valor:String):void {
			this._descricaoRoteiro = valor;
		}
		
		public function get descricaoTipoBem():String {
			return this._descricaoTipoBem;
		}
		
		public function set descricaoTipoBem(valor:String):void {
			this._descricaoTipoBem = valor;
		}
		
		public function get descricaoTipoEstadoConservacao():String {
			return this._descricaoTipoEstadoConservacao;
		}
		
		public function set descricaoTipoEstadoConservacao(valor:String):void {
			this._descricaoTipoEstadoConservacao = valor;
		}
		
		public function get descricaoTipoImplantacaoBemImovel():String {
			return this._descricaoTipoImplantacaoBemImovel;
		}
		
		public function set descricaoTipoImplantacaoBemImovel(valor:String):void {
			this._descricaoTipoImplantacaoBemImovel = valor;
		}
		
		public function get descricaoTipoLocalizacaoBem():String {
			return this._descricaoTipoLocalizacaoBem;
		}
		
		public function set descricaoTipoLocalizacaoBem(valor:String):void {
			this._descricaoTipoLocalizacaoBem = valor;
		}
		
		public function get descricaoTipoPadraoAcabamentoBemImovel():String {
			return this._descricaoTipoPadraoAcabamentoBemImovel;
		}
		
		public function set descricaoTipoPadraoAcabamentoBemImovel(valor:String):void {
			this._descricaoTipoPadraoAcabamentoBemImovel = valor;
		}
		
		public function get descricaoTipoServicoCondominialBemImovel():String {
			return this._descricaoTipoServicoCondominialBemImovel;
		}
		
		public function set descricaoTipoServicoCondominialBemImovel(valor:String):void {
			this._descricaoTipoServicoCondominialBemImovel = valor;
		}
		
		public function get descricaoTipoUsoBem():String {
			return this._descricaoTipoUsoBem;
		}
		
		public function set descricaoTipoUsoBem(valor:String):void {
			this._descricaoTipoUsoBem = valor;
		}
		
		public function get descricaoUnidadeMedida():String {
			return this._descricaoUnidadeMedida;
		}
		
		public function set descricaoUnidadeMedida(valor:String):void {
			this._descricaoUnidadeMedida = valor;
		}
		
		public function get idLocalidade():Number {
			return this._idLocalidade;
		}
		
		public function set idLocalidade(valor:Number):void {
			this._idLocalidade = valor;
		}
		
		public function get idLogradouro():Number {
			return this._idLogradouro;
		}
		
		public function set idLogradouro(valor:Number):void {
			this._idLogradouro = valor;
		}
		
		public function get idPessoaAvaliador():Number {
			return this._idPessoaAvaliador;
		}
		
		public function set idPessoaAvaliador(valor:Number):void {
			this._idPessoaAvaliador = valor;
		}
		
		public function get idPessoaCartorio():Number {
			return this._idPessoaCartorio;
		}
		
		public function set idPessoaCartorio(valor:Number):void {
			this._idPessoaCartorio = valor;
		}
		
		public function get incra():String {
			return this._incra;
		}
		
		public function set incra(valor:String):void {
			this._incra = valor;
		}
		
		public function get informacoesGerais():String {
			return this._informacoesGerais;
		}
		
		public function set informacoesGerais(valor:String):void {
			this._informacoesGerais = valor;
		}
		
		public function get latitude():Number {
			return this._latitude;
		}
		
		public function set latitude(valor:Number):void {
			this._latitude = valor;
		}
		
		public function get longitude():Number {
			return this._longitude;
		}
		
		public function set longitude(valor:Number):void {
			this._longitude = valor;
		}
		
		public function get matricula():String {
			return this._matricula;
		}
		
		public function set matricula(valor:String):void {
			this._matricula = valor;
		}
		
		public function get nirf():String {
			return this._nirf;
		}
		
		public function set nirf(valor:String):void {
			this._nirf = valor;
		}
		
		public function get numero():String {
			return this._numero;
		}
		
		public function set numero(valor:String):void {
			this._numero = valor;
		}
		
		public function get processoAquisicao():Boolean {
			return this._processoAquisicao;
		}
		
		public function set processoAquisicao(valor:Boolean):void {
			this._processoAquisicao = valor;
		}
		
		public function get quantidadeDormitorios():Number {
			return this._quantidadeDormitorios;
		}
		
		public function set quantidadeDormitorios(valor:Number):void {
			this._quantidadeDormitorios = valor;
		}
		
		public function get quantidadeVagasGaragem():Number {
			return this._quantidadeVagasGaragem;
		}
		
		public function set quantidadeVagasGaragem(valor:Number):void {
			this._quantidadeVagasGaragem = valor;
		}
		
		public function get valorAvaliacao():Number {
			return this._valorAvaliacao;
		}
		
		public function set valorAvaliacao(valor:Number):void {
			this._valorAvaliacao = valor;
		}
		
		public function get valorBenfeitoria():Number {
			return this._valorBenfeitoria;
		}
		
		public function set valorBenfeitoria(valor:Number):void {
			this._valorBenfeitoria = valor;
		}
		
		public function get valorCompraVenda():Number {
			return this._valorCompraVenda;
		}
		
		public function set valorCompraVenda(valor:Number):void {
			this._valorCompraVenda = valor;
		}
		
		public override function readExternal(input:IDataInput):void {
			super.readExternal(input);
			_areaPrivativa = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_areaTerreno = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_areaTestada = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_areaTotal = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_benfeitoria = input.readObject() as String;
			_codigoTipoBem = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_codigoTipoEstadoConservacao = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_codigoTipoImplantacaoBemImovel = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_codigoTipoLocalizacaoBem = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_codigoTipoPadraoAcabamentoBemImovel = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_codigoTipoServicoCondominialBemImovel = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_codigoTipoUsoBem = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_codigoUnidadeMedida = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_complemento = input.readObject() as String;
			_dataAvaliacao = input.readObject() as Date;
			_dataCompraVenda = input.readObject() as Date;
			_denominacao = input.readObject() as String;
			_descricaoRoteiro = input.readObject() as String;
			_descricaoTipoBem = input.readObject() as String;
			_descricaoTipoEstadoConservacao = input.readObject() as String;
			_descricaoTipoImplantacaoBemImovel = input.readObject() as String;
			_descricaoTipoLocalizacaoBem = input.readObject() as String;
			_descricaoTipoPadraoAcabamentoBemImovel = input.readObject() as String;
			_descricaoTipoServicoCondominialBemImovel = input.readObject() as String;
			_descricaoTipoUsoBem = input.readObject() as String;
			_descricaoUnidadeMedida = input.readObject() as String;
			_idLocalidade = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_idLogradouro = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_idPessoaAvaliador = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_idPessoaCartorio = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_incra = input.readObject() as String;
			_informacoesGerais = input.readObject() as String;
			_latitude = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_longitude = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_matricula = input.readObject() as String;
			_nirf = input.readObject() as String;
			_numero = input.readObject() as String;
			_processoAquisicao = input.readObject() as Boolean;
			_quantidadeDormitorios = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_quantidadeVagasGaragem = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_valorAvaliacao = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_valorBenfeitoria = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_valorCompraVenda = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
		}
		
		public override function writeExternal(output:IDataOutput):void {
			super.writeExternal(output);
			output.writeObject(_areaPrivativa);
			output.writeObject(_areaTerreno);
			output.writeObject(_areaTestada);
			output.writeObject(_areaTotal);
			output.writeObject(_benfeitoria);
			output.writeObject(_codigoTipoBem);
			output.writeObject(_codigoTipoEstadoConservacao);
			output.writeObject(_codigoTipoImplantacaoBemImovel);
			output.writeObject(_codigoTipoLocalizacaoBem);
			output.writeObject(_codigoTipoPadraoAcabamentoBemImovel);
			output.writeObject(_codigoTipoServicoCondominialBemImovel);
			output.writeObject(_codigoTipoUsoBem);
			output.writeObject(_codigoUnidadeMedida);
			output.writeObject(_complemento);
			output.writeObject(_dataAvaliacao);
			output.writeObject(_dataCompraVenda);
			output.writeObject(_denominacao);
			output.writeObject(_descricaoRoteiro);
			output.writeObject(_descricaoTipoBem);
			output.writeObject(_descricaoTipoEstadoConservacao);
			output.writeObject(_descricaoTipoImplantacaoBemImovel);
			output.writeObject(_descricaoTipoLocalizacaoBem);
			output.writeObject(_descricaoTipoPadraoAcabamentoBemImovel);
			output.writeObject(_descricaoTipoServicoCondominialBemImovel);
			output.writeObject(_descricaoTipoUsoBem);
			output.writeObject(_descricaoUnidadeMedida);
			output.writeObject(_idLocalidade);
			output.writeObject(_idLogradouro);
			output.writeObject(_idPessoaAvaliador);
			output.writeObject(_idPessoaCartorio);
			output.writeObject(_incra);
			output.writeObject(_informacoesGerais);
			output.writeObject(_latitude);
			output.writeObject(_longitude);
			output.writeObject(_matricula);
			output.writeObject(_nirf);
			output.writeObject(_numero);
			output.writeObject(_processoAquisicao);
			output.writeObject(_quantidadeDormitorios);
			output.writeObject(_quantidadeVagasGaragem);
			output.writeObject(_valorAvaliacao);
			output.writeObject(_valorBenfeitoria);
			output.writeObject(_valorCompraVenda);
		}

    }
}