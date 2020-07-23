package br.com.sicoob.capes.corporativo.componentes.procurarBens.vo {

    import flash.net.registerClassAlias;
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    
	/**
	 * Classe que representa o Bem Móvel.
	 * 
	 * @author Bruno.Carneiro
	 */
	registerClassAlias("br.com.sicoob.capes.api.negocio.vo.BemMovelVO", BemMovelVO);
    public class BemMovelVO extends BemVO {
		
		private var _anoConstrucaoAeronave:Number;
		private var _anoConstrucaoEmbarcacao:Number;
		private var _anoFabricacaoAutomovel:Number;
		private var _anoModeloAutomovel:Number;
		private var _codigoTipoBem:Number;
		private var _codigoTipoChassi:Number;
		private var _codigoTipoCorAutomovel:Number;
		private var _codigoTipoEstadoConservacao:Number;
		private var _dataAvaliacao:Date;
		private var _dataCompraVenda:Date;
		private var _idPessoaAvaliador:Number;
		private var _inscricaoEmbarcacao:String;
		private var _matriculaAeronave:String;
		private var _numeroChassi:String;
		private var _placa:String;
		private var _processoAquisicao:Boolean = false;
		private var _renavam:String;
		private var _valorAvaliacao:Number;
		private var _valorCompraVenda:Number;
		private var _uf:String;
		
		public function get anoConstrucaoAeronave():Number {
			return this._anoConstrucaoAeronave;
		}
		
		public function set anoConstrucaoAeronave(valor:Number):void {
			this._anoConstrucaoAeronave = valor;
		}
		
		public function get anoConstrucaoEmbarcacao():Number {
			return this._anoConstrucaoEmbarcacao;
		}
		
		public function set anoConstrucaoEmbarcacao(valor:Number):void {
			this._anoConstrucaoEmbarcacao = valor;
		}
		
		public function get anoFabricacaoAutomovel():Number {
			return this._anoFabricacaoAutomovel;
		}
		
		public function set anoFabricacaoAutomovel(valor:Number):void {
			this._anoFabricacaoAutomovel = valor;
		}
		
		public function get anoModeloAutomovel():Number {
			return this._anoModeloAutomovel;
		}
		
		public function set anoModeloAutomovel(valor:Number):void {
			this._anoModeloAutomovel = valor;
		}
		
		public function get codigoTipoBem():Number {
			return this._codigoTipoBem;
		}
		
		public function set codigoTipoBem(valor:Number):void {
			this._codigoTipoBem = valor;
		}
		
		public function get codigoTipoChassi():Number {
			return this._codigoTipoChassi;
		}
		
		public function set codigoTipoChassi(valor:Number):void {
			this._codigoTipoChassi = valor;
		}
		
		public function get codigoTipoCorAutomovel():Number {
			return this._codigoTipoCorAutomovel;
		}
		
		public function set codigoTipoCorAutomovel(valor:Number):void {
			this._codigoTipoCorAutomovel = valor;
		}
		
		public function get codigoTipoEstadoConservacao():Number {
			return this._codigoTipoEstadoConservacao;
		}
		
		public function set codigoTipoEstadoConservacao(valor:Number):void {
			this._codigoTipoEstadoConservacao = valor;
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
		
		public function get idPessoaAvaliador():Number {
			return this._idPessoaAvaliador;
		}
		
		public function set idPessoaAvaliador(valor:Number):void {
			this._idPessoaAvaliador = valor;
		}
		
		public function get inscricaoEmbarcacao():String {
			return this._inscricaoEmbarcacao;
		}
		
		public function set inscricaoEmbarcacao(valor:String):void {
			this._inscricaoEmbarcacao = valor;
		}
		
		public function get matriculaAeronave():String {
			return this._matriculaAeronave;
		}
		
		public function set matriculaAeronave(valor:String):void {
			this._matriculaAeronave = valor;
		}
		
		public function get numeroChassi():String {
			return this._numeroChassi;
		}
		
		public function set numeroChassi(valor:String):void {
			this._numeroChassi = valor;
		}
		
		public function get placa():String {
			return this._placa;
		}
		
		public function set placa(valor:String):void {
			this._placa = valor;
		}
		
		public function get processoAquisicao():Boolean {
			return this._processoAquisicao;
		}
			
		public function set processoAquisicao(valor:Boolean):void {
			this._processoAquisicao = valor;
		}
		
		public function get renavam():String {
			return this._renavam;
		}
		
		public function set renavam(valor:String):void {
			this._renavam = valor;
		}
		
		public function get valorAvaliacao():Number {
			return this._valorAvaliacao;
		}
		
		public function set valorAvaliacao(valor:Number):void {
			this._valorAvaliacao = valor;
		}
		
		public function get valorCompraVenda():Number {
			return this._valorCompraVenda;
		}
		
		public function set valorCompraVenda(valor:Number):void {
			this._valorCompraVenda = valor;
		}
		
		public function get uf():String {
			return this._uf;
		}
		
		public function set uf(valor:String):void {
			this._uf = valor;
		}
			
		public override function readExternal(input:IDataInput):void {
			super.readExternal(input);
			_anoConstrucaoAeronave = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_anoConstrucaoEmbarcacao = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_anoFabricacaoAutomovel = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_anoModeloAutomovel = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_codigoTipoBem = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_codigoTipoChassi = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_codigoTipoCorAutomovel = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_codigoTipoEstadoConservacao = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_dataAvaliacao = input.readObject() as Date;
			_dataCompraVenda = input.readObject() as Date;
			_idPessoaAvaliador = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_inscricaoEmbarcacao = input.readObject() as String;
			_matriculaAeronave = input.readObject() as String;
			_numeroChassi = input.readObject() as String;
			_placa = input.readObject() as String;
			_processoAquisicao = input.readObject() as Boolean;
			_renavam = input.readObject() as String;
			_valorAvaliacao = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_valorCompraVenda = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_uf = input.readObject() as String;
		}
		
		public override function writeExternal(output:IDataOutput):void {
			super.writeExternal(output);
			output.writeObject(_anoConstrucaoAeronave);
			output.writeObject(_anoConstrucaoEmbarcacao);
			output.writeObject(_anoFabricacaoAutomovel);
			output.writeObject(_anoModeloAutomovel);
			output.writeObject(_codigoTipoBem);
			output.writeObject(_codigoTipoChassi);
			output.writeObject(_codigoTipoCorAutomovel);
			output.writeObject(_codigoTipoEstadoConservacao);
			output.writeObject(_dataAvaliacao);
			output.writeObject(_dataCompraVenda);
			output.writeObject(_idPessoaAvaliador);
			output.writeObject(_inscricaoEmbarcacao);
			output.writeObject(_matriculaAeronave);
			output.writeObject(_numeroChassi);
			output.writeObject(_placa);
			output.writeObject(_processoAquisicao);
			output.writeObject(_renavam);
			output.writeObject(_valorAvaliacao);
			output.writeObject(_valorCompraVenda);
			output.writeObject(_uf);
		}
		
    }
}