package br.com.sicoob.capes.comum.vo.entidades.bem {

    import flash.net.registerClassAlias;
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    
    import br.com.bancoob.tipos.Booleano;
    import br.com.bancoob.tipos.IDateTime;
    import br.com.bancoob.tipos.SerializacaoObjetos;
    import br.com.sicoob.capes.comum.enums.SituacaoRegistroEnum;
    import br.com.sicoob.capes.comum.enums.TipoAutorizacaoEnum;
    import br.com.sicoob.capes.comum.vo.entidades.EntidadeCadastroBaseVO;
    import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
    import br.com.sicoob.capes.comum.vo.entidades.interfaces.Aprovavel;
    
    import org.granite.meta;
    import org.granite.collections.IPersistentCollection;

    use namespace meta;

	/**
	 * Classe que representa o BemPessoaCompartilhamento.
	 * 
	 * @author Bruno.Carneiro
	 */
	registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.BemPessoaCompartilhamento", BemPessoaCompartilhamentoVO);
    public class BemPessoaCompartilhamentoVO extends EntidadeCadastroBaseVO implements Aprovavel {
		private var __laziness:String = null;
		
		private var _areaPosse:Number;
		private var _bem:BemVO;
		private var _codigoSituacaoAprovacao:Number;
		private var _dataHoraInicio:IDateTime;
		private var _gravarHistorico:Booleano = new Booleano(true);
		private var _idBem:Number;
		private var _idBemPessoaCompartilhamento:Number;
		private var _idInstituicaoAtualizacao:Number;
		private var _idPessoaCompartilhamento:Number;
		private var _marcadoExclusao:Boolean = false;
		private var _percentualProprietario:Number;
		private var _pessoaCompartilhamento:PessoaCompartilhamentoVO;
		private var _pessoaResponsavel:Boolean = false;
		private var _situacaoAprovacao:SituacaoRegistroEnum;
		private var _verificarAutorizacao:Booleano = new Booleano(true);
		
		meta function isInitialized(name:String = null):Boolean {
			if (!name){
				return __laziness === null;
			}
			
			var property:* = this[name];
			return ((!(property is BemPessoaCompartilhamentoVO) || (property as BemPessoaCompartilhamentoVO).meta::isInitialized()) &&
					(!(property is IPersistentCollection) || (property as IPersistentCollection).isInitialized()));
		}
		
		public function get areaPosse():Number {
			return _areaPosse;
		}
		
		public function set areaPosse(valor:Number):void {
			this._areaPosse = valor;
		}
		
		public function get bem():BemVO {
			return _bem;
		}
		
		public function set bem(valor:BemVO):void {
			this._bem = valor;
		}
		
		public function get codigoSituacaoAprovacao():Number {
			return _codigoSituacaoAprovacao;
		}
		
		public function set codigoSituacaoAprovacao(valor:Number):void {
			this._codigoSituacaoAprovacao = valor;
		}
		
		public function get dataHoraInicio():IDateTime {
			return _dataHoraInicio;
		}
		
		public function set dataHoraInicio(valor:IDateTime):void {
			this._dataHoraInicio = valor;
		}
		
		public function get gravarHistorico():Booleano {
			return _gravarHistorico;
		}
		
		public function set gravarHistorico(valor:Booleano):void {
			this._gravarHistorico = valor;
		}
		
		public function get idBem():Number {
			return _idBem;
		}
		
		public function set idBem(valor:Number):void {
			this._idBem = valor;
		}
		
		public function get idBemPessoaCompartilhamento():Number {
			return _idBemPessoaCompartilhamento;
		}
		
		public function set idBemPessoaCompartilhamento(valor:Number):void {
			this._idBemPessoaCompartilhamento = valor;
		}
		
		public function get idInstituicaoAtualizacao():Number {
			return _idInstituicaoAtualizacao;
		}
		
		public function set idInstituicaoAtualizacao(valor:Number):void {
			this._idInstituicaoAtualizacao = valor;
		}
		
		public function get idPessoaCompartilhamento():Number {
			return _idPessoaCompartilhamento;
		}
		
		public function set idPessoaCompartilhamento(valor:Number):void {
			this._idPessoaCompartilhamento = valor;
		}
		
		public function get idRegistroControlado():String {
			return bem.idRegistroControlado;
		}
		
		public function get marcadoExclusao():Boolean {
			return _marcadoExclusao;
		}
		
		public function set marcadoExclusao(valor:Boolean):void {
			this._marcadoExclusao = valor;
		}
		
		public function get pessoaCompartilhamento():PessoaCompartilhamentoVO {
			return _pessoaCompartilhamento;
		}
		
		public function set pessoaCompartilhamento(valor:PessoaCompartilhamentoVO):void {
			this._pessoaCompartilhamento = valor;
		}
		
		public function get pessoaResponsavel():Boolean {
			return _pessoaResponsavel;
		}
		
		public function set pessoaResponsavel(valor:Boolean):void {
			this._pessoaResponsavel = valor;
		}
		
		public function get percentualProprietario():Number {
			return _percentualProprietario;
		}
		
		public function set percentualProprietario(valor:Number):void {
			this._percentualProprietario = valor;
		}
		
		public function set situacaoAprovacao(valor:SituacaoRegistroEnum):void{
			_situacaoAprovacao = null;
		}
		
		public function get situacaoAprovacao():SituacaoRegistroEnum {
			if(_situacaoAprovacao == null){
				_situacaoAprovacao = SituacaoRegistroEnum.valueOff(_codigoSituacaoAprovacao);
			}
			return _situacaoAprovacao;
		}
		
		public function get verificarAutorizacao():Booleano {
			return _verificarAutorizacao;
		}
		
		public function set verificarAutorizacao(valor:Booleano):void {
			this._verificarAutorizacao = valor;
		}
		
		public function get id():Number{
			return _idBemPessoaCompartilhamento;
		}
		
		public function get tipoAutorizacao():TipoAutorizacaoEnum {
			return TipoAutorizacaoEnum.BEM_NOVO;
		}
		
		/**
		 * {@inheritDoc}
		 * 
		 * Implementado devido a interface Aprovavel,
		 * portanto retorna null por  a entidade não 
		 * conter o campo IdUsuarioEnvio.  
		 *
		 */
		public function get idUsuarioEnvio():String
		{
			return null;
		}
		
        override public function readExternal(input:IDataInput):void {
			__laziness = input.readObject() as String;
			if (meta::isInitialized()) {
	            super.readExternal(input);
				_areaPosse = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
				_bem = input.readObject() as BemVO;
				_codigoSituacaoAprovacao = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
				_dataHoraInicio = input.readObject() as IDateTime;
				_gravarHistorico = SerializacaoObjetos.lerBooleano(input);
				_idBem = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
				_idBemPessoaCompartilhamento = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
				_idInstituicaoAtualizacao = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
				_idPessoaCompartilhamento = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
				_marcadoExclusao = input.readObject() as Boolean;
				_percentualProprietario = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
				_pessoaCompartilhamento = input.readObject() as PessoaCompartilhamentoVO;
				_pessoaResponsavel = input.readObject() as Boolean;
				_situacaoAprovacao = input.readObject() as SituacaoRegistroEnum;
				_verificarAutorizacao = SerializacaoObjetos.lerBooleano(input);
			}
        }
		
        override public function writeExternal(output:IDataOutput):void {
			output.writeObject(__laziness);
			if (meta::isInitialized()) {
	            super.writeExternal(output);
				output.writeObject(_areaPosse);
				output.writeObject(_bem);
				output.writeObject(_codigoSituacaoAprovacao);
				output.writeObject(_dataHoraInicio);
				SerializacaoObjetos.escreverBooleano(_gravarHistorico, output);
				output.writeObject(_idBem);
				output.writeObject(_idBemPessoaCompartilhamento);
				output.writeObject(_idInstituicaoAtualizacao);
				output.writeObject(_idPessoaCompartilhamento);
				output.writeObject(_marcadoExclusao);
				output.writeObject(_percentualProprietario);
				output.writeObject(_pessoaCompartilhamento);
				output.writeObject(_pessoaResponsavel);
				output.writeObject(_situacaoAprovacao);
				SerializacaoObjetos.escreverBooleano(_verificarAutorizacao, output);
			}
        }
    }
}