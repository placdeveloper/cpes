package br.com.sicoob.capes.corporativo.componentes.procurarBens.vo {

    import br.com.bancoob.vo.BancoobVO;
    
    import flash.net.registerClassAlias;
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    
	/**
	 * Classe que representa o Bem.
	 * 
	 * @author Bruno.Carneiro
	 */
	registerClassAlias("br.com.sicoob.capes.api.negocio.vo.BemVO", BemVO);
    public class BemVO extends BancoobVO {
		
		private var _codigoTipoClassificacaoBem:Number;
		private var _descricao:String;
		private var _descricaoTipoClassificacaoBem:String;
		private var _emGarantia:Boolean;
		private var _idBem:Number;
		private var _idUsuarioAprovacao:String;
		private var _interno:Boolean;
		private var _mesRenovacaoSeguro:Number;
		private var _percentualProprietario:Number;
		private var _valor:Number;
		private var _valorNaoInformado:Boolean;
		
		public function get codigoTipoClassificacaoBem():Number {
			return this._codigoTipoClassificacaoBem;
		}
		
		public function set codigoTipoClassificacaoBem(valor:Number):void {
			this._codigoTipoClassificacaoBem = valor;
		}
		
		public function get descricao():String {
			return this._descricao;
		}
		
		public function set descricao(valor:String):void {
			this._descricao = valor;
		}
		
		public function get descricaoTipoClassificacaoBem():String {
			return this._descricaoTipoClassificacaoBem;
		}
		
		public function set descricaoTipoClassificacaoBem(valor:String):void {
			this._descricaoTipoClassificacaoBem = valor;
		}
		
		public function get emGarantia():Boolean {
			return this._emGarantia;
		}
		
		public function set emGarantia(valor:Boolean):void {
			this._emGarantia = valor;
		}
		
		public function get idBem():Number {
			return this._idBem;
		}
		
		public function set idBem(valor:Number):void {
			this._idBem = valor;
		}
		
		public function get idUsuarioAprovacao():String {
			return this._idUsuarioAprovacao;
		}
		
		public function set idUsuarioAprovacao(valor:String):void {
			this._idUsuarioAprovacao = valor;
		}
		
		public function get interno():Boolean {
			return this._interno;
		}
		
		public function set interno(valor:Boolean):void {
			this._interno = valor;
		}
		
		public function get mesRenovacaoSeguro():Number {
			return this._mesRenovacaoSeguro;
		}
		
		public function set mesRenovacaoSeguro(valor:Number):void {
			this._mesRenovacaoSeguro = valor;
		}
		
		public function get percentualProprietario():Number {
			return this._percentualProprietario;
		}
		
		public function set percentualProprietario(valor:Number):void {
			this._percentualProprietario = valor;
		}
		
		public function get valor():Number {
			return this._valor;
		}
		
		public function set valor(valor:Number):void {
			this._valor = valor;
		}
		
		public function get valorNaoInformado():Boolean {
			return this._valorNaoInformado;
		}
		
		public function set valorNaoInformado(valor:Boolean):void {
			this._valorNaoInformado = valor;
		}
		
		public function readExternal(input:IDataInput):void {
			_codigoTipoClassificacaoBem = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_descricao = input.readObject() as String;
			_descricaoTipoClassificacaoBem = input.readObject() as String;
			_emGarantia = input.readObject() as Boolean;
			_idBem = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_idUsuarioAprovacao = input.readObject() as String;
			_interno = input.readObject() as Boolean;
			_mesRenovacaoSeguro = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_percentualProprietario = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_valor = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_valorNaoInformado = input.readObject() as Boolean;
		}
		
		public function writeExternal(output:IDataOutput):void {
			output.writeObject(_codigoTipoClassificacaoBem);
			output.writeObject(_descricao);
			output.writeObject(_descricaoTipoClassificacaoBem);
			output.writeObject(_emGarantia);
			output.writeObject(_idBem);
			output.writeObject(_idUsuarioAprovacao);
			output.writeObject(_interno);
			output.writeObject(_mesRenovacaoSeguro);
			output.writeObject(_percentualProprietario);
			output.writeObject(_valor);
			output.writeObject(_valorNaoInformado);
		}
		
    }
}