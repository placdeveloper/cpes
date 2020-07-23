package br.com.sicoob.capes.comum.vo {

    import flash.net.registerClassAlias;
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import flash.utils.IExternalizable;
    
    import br.com.bancoob.vo.BancoobVO;
    import br.com.sicoob.capes.comum.enums.SituacaoRegistroEnum;
    
	/**
	 * Classe para os dados de listagem de bens.
	 * 
	 * @author Bruno.Carneiro
	 */
	registerClassAlias("br.com.sicoob.capes.cadastro.negocio.vo.DadosListagemBemVO", DadosListagemBemVO);
    public class DadosListagemBemVO extends BancoobVO implements IExternalizable {

		private var _bemInterno:Boolean;
		private var _bloqueadoPor:String;
		private var _codigoSituacaoAprovacao:Number;
		private var _codigoTipoClassificacaoBem:Number;
		private var _dataAvaliacao:Date;
		private var _dataHoraInicio:Date;
		private var _denominacaoBem:String;
		private var _descricaoBem:String;
		private var _descricaoTipoBem:String;
		private var _descricaoTipoClassificacaoBem:String;
		private var _idBem:Number;
		private var _idBemPessoaCompartilhamento:Number;
		private var _idInstituicaoAtualizacao:Number;
		private var _idRegistroControlado:String;
		private var _idUsuarioAprovacao:String;
		private var _percentualProprietario:Number;
		private var _situacaoAprovacao:SituacaoRegistroEnum;
		private var _status:String;
		private var _valor:Number;
		private var _valorAvaliacao:Number;
		
		public function get bemInterno():Boolean {
			return _bemInterno;
		}
		
		public function set bemInterno(valor:Boolean):void {
			_bemInterno = valor;
		}
		
		public function get bloqueadoPor():String {
			return _bloqueadoPor;
		}
		
		public function set bloqueadoPor(valor:String):void {
			_bloqueadoPor = valor;
		}
		
		public function get codigoSituacaoAprovacao():Number {
			return _codigoSituacaoAprovacao;
		}
		
		public function set codigoSituacaoAprovacao(valor:Number):void {
			_codigoSituacaoAprovacao = valor;
		}
		
		public function get codigoTipoClassificacaoBem():Number {
			return _codigoTipoClassificacaoBem;
		}
		
		public function set codigoTipoClassificacaoBem(valor:Number):void {
			_codigoTipoClassificacaoBem = valor;
		}
		
		public function get dataAvaliacao():Date {
			return _dataAvaliacao;
		}
		
		public function set dataAvaliacao(valor:Date):void {
			_dataAvaliacao = valor;
		}
		
		public function get dataHoraInicio():Date {
			return _dataHoraInicio;
		}
		
		public function set dataHoraInicio(valor:Date):void {
			_dataHoraInicio = valor;
		}
		
		public function get denominacaoBem():String {
			return _denominacaoBem;
		}
		
		public function set denominacaoBem(valor:String):void {
			_denominacaoBem = valor;
		}
		
		public function get descricaoBem():String {
			return _descricaoBem;
		}
		
		public function set descricaoBem(valor:String):void {
			_descricaoBem = valor;
		}
		
		public function get descricaoTipoBem():String {
			return _descricaoTipoBem;
		}
		
		public function set descricaoTipoBem(valor:String):void {
			_descricaoTipoBem = valor;
		}
		
		public function get descricaoTipoClassificacaoBem():String {
			return _descricaoTipoClassificacaoBem;
		}
		
		public function set descricaoTipoClassificacaoBem(valor:String):void {
			_descricaoTipoClassificacaoBem = valor;
		}
		
		public function get idBem():Number {
			return _idBem;
		}
		
		public function set idBem(valor:Number):void {
			_idBem = valor;
		}
		
		public function get idBemPessoaCompartilhamento():Number {
			return _idBemPessoaCompartilhamento;
		}
		
		public function set idBemPessoaCompartilhamento(valor:Number):void {
			_idBemPessoaCompartilhamento = valor;
		}
		
		public function get idInstituicaoAtualizacao():Number {
			return _idInstituicaoAtualizacao;
		}
		
		public function set idInstituicaoAtualizacao(valor:Number):void {
			_idInstituicaoAtualizacao = valor;
		}
		
		public function get idRegistroControlado():String {
			return _idRegistroControlado;
		}
		
		public function set idRegistroControlado(valor:String):void {
			_idRegistroControlado = valor;
		}
		
		public function get idUsuarioAprovacao():String {
			return _idUsuarioAprovacao;
		}
		
		public function set idUsuarioAprovacao(valor:String):void {
			_idUsuarioAprovacao = valor;
		}
		
		public function get percentualProprietario():Number {
			return _percentualProprietario;
		}
		
		public function set percentualProprietario(valor:Number):void {
			_percentualProprietario = valor;
		}
		
		public function get situacaoAprovacao():SituacaoRegistroEnum {
			if(_situacaoAprovacao == null){
				_situacaoAprovacao = SituacaoRegistroEnum.valueOff(_codigoSituacaoAprovacao);
			}
			return _situacaoAprovacao;
		}
		
		public function set situacaoAprovacao(valor:SituacaoRegistroEnum):void{
			_situacaoAprovacao = valor;
		}
		
		public function get status():String {
			return _status;
		}
		
		public function set status(valor:String):void {
			_status = valor;
		}
		
		public function get valor():Number {
			return _valor;
		}
		
		public function set valor(valor:Number):void {
			_valor = valor;
		}
		
		public function set valorAvaliacao(value:Number):void{
			_valorAvaliacao = value;
		}
		
		public function get valorAvaliacao():Number {
			return _valorAvaliacao;
		}
		
        public function readExternal(input:IDataInput):void {
			_bemInterno = input.readObject() as Boolean;
			_bloqueadoPor = input.readObject() as String;
			_codigoSituacaoAprovacao = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_codigoTipoClassificacaoBem = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_dataAvaliacao = input.readObject() as Date;
			_dataHoraInicio = input.readObject() as Date;
			_denominacaoBem = input.readObject() as String;
			_descricaoBem = input.readObject() as String;
			_descricaoTipoBem = input.readObject() as String;
			_descricaoTipoClassificacaoBem = input.readObject() as String;
			_idBem = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_idBemPessoaCompartilhamento = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_idInstituicaoAtualizacao = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_idRegistroControlado = input.readObject() as String;
			_idUsuarioAprovacao = input.readObject() as String;
			_percentualProprietario = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_situacaoAprovacao = input.readObject() as SituacaoRegistroEnum;
			_status = input.readObject() as String;
			_valor = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_valorAvaliacao = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
        }

        public function writeExternal(output:IDataOutput):void {
            output.writeObject(_bemInterno);
            output.writeObject(_bloqueadoPor);
            output.writeObject(_codigoSituacaoAprovacao);
            output.writeObject(_codigoTipoClassificacaoBem);
            output.writeObject(_dataAvaliacao);
            output.writeObject(_dataHoraInicio);
            output.writeObject(_denominacaoBem);
            output.writeObject(_descricaoBem);
            output.writeObject(_descricaoTipoBem);
            output.writeObject(_descricaoTipoClassificacaoBem);
            output.writeObject(_idBem);
            output.writeObject(_idBemPessoaCompartilhamento);
            output.writeObject(_idInstituicaoAtualizacao);
            output.writeObject(_idRegistroControlado);
            output.writeObject(_idUsuarioAprovacao);
            output.writeObject(_percentualProprietario);
            output.writeObject(_situacaoAprovacao);
            output.writeObject(_status);
            output.writeObject(_valor);
            output.writeObject(_valorAvaliacao);
        }
    }
}