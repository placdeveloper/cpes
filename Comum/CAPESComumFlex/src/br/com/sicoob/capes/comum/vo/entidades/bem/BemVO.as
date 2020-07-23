package br.com.sicoob.capes.comum.vo.entidades.bem {

    import flash.net.registerClassAlias;
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    
    import mx.collections.ListCollectionView;
    
    import br.com.bancoob.tipos.Booleano;
    import br.com.bancoob.tipos.IDateTime;
    import br.com.bancoob.tipos.SerializacaoObjetos;
    import br.com.sicoob.capes.comum.enums.SituacaoRegistroEnum;
    import br.com.sicoob.capes.comum.enums.TipoAutorizacaoEnum;
    import br.com.sicoob.capes.comum.vo.entidades.CompartilhamentoCadastroVO;
    import br.com.sicoob.capes.comum.vo.entidades.EntidadeCadastroBaseVO;
    import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
    import br.com.sicoob.capes.comum.vo.entidades.interfaces.Comprovavel;
    
    import org.granite.meta;
    import org.granite.collections.IPersistentCollection;

    use namespace meta;

	/**
	 * Classe que representa o Bem.
	 * 
	 * @author Bruno.Carneiro
	 */
	registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.Bem", BemVO);
    public class BemVO extends EntidadeCadastroBaseVO implements Comprovavel {
		private var __laziness:String = null;
		
		private var _codCompartilhamentoCadastro:Number;
		private var _codigoSituacaoAprovacao:Number;
		private var _compartilhamento:CompartilhamentoCadastroVO;
		private var _dataHoraInicio:IDateTime;
		private var _descricao:String;
		private var _documentosComprobatorios:ListCollectionView;
		private var _gravarHistorico:Booleano = new Booleano(true);
		private var _idBem:Number;
		private var _idInstituicaoAtualizacao:Number;
		private var _idRegistroControlado:String;
		private var _idUsuarioEnvio:String;
		private var _interno:Boolean = false;
		private var _mesRenovacaoSeguro:Number;
		private var _proprietarios:ListCollectionView;
		private var _situacaoAprovacao:SituacaoRegistroEnum;
		private var _status:String;
		private var _tipoClassificacaoBem:TipoClassificacaoBemVO;
		private var _valor:Number;
		private var _valorNaoInformado:Boolean = false;
		private var _verificarAutorizacao:Booleano = new Booleano(true);
		
		meta function isInitialized(name:String = null):Boolean {
			if (!name){
				return __laziness === null;
			}
			
			var property:* = this[name];
			return ((!(property is BemVO) || (property as BemVO).meta::isInitialized()) &&
				(!(property is IPersistentCollection) || (property as IPersistentCollection).isInitialized()));
		}
		
		public function get codCompartilhamentoCadastro():Number {
			return _codCompartilhamentoCadastro;	
		}
		
		public function set codCompartilhamentoCadastro(valor:Number):void {
			this._codCompartilhamentoCadastro = valor;
		}
		
		public function get codigoSituacaoAprovacao():Number {
			return _codigoSituacaoAprovacao;	
		}
		
		public function set codigoSituacaoAprovacao(valor:Number):void {
			this._codigoSituacaoAprovacao = valor;
		}
		
		public function get compartilhamento():CompartilhamentoCadastroVO {
			return _compartilhamento;	
		}
		
		public function set compartilhamento(valor:CompartilhamentoCadastroVO):void {
			this._compartilhamento = valor;
		}
		
		public function get descricao():String {
			return _descricao;
		}
		
		public function set descricao(valor:String):void {
			this._descricao = valor;
		}
		
		public function get idBem():Number {
			return _idBem;	
		}
		
		public function set idBem(valor:Number):void {
			this._idBem = valor;
		}
		
		public function get idRegistroControlado():String {
			if(_idRegistroControlado == null) {
				_idRegistroControlado = tipoAutorizacao.name + "#" + id;	
			}
			return _idRegistroControlado;	
		}
		
		public function set idRegistroControlado(valor:String):void {
			this._idRegistroControlado = valor;
		}
		
		public function get interno():Boolean {
			return _interno;
		}
		
		public function set interno(valor:Boolean):void {
			this._interno = valor;
		}
		
		public function get mesRenovacaoSeguro():Number {
			return _mesRenovacaoSeguro;	
		}
		
		public function set mesRenovacaoSeguro(valor:Number):void {
			this._mesRenovacaoSeguro = valor;
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
		
		public function set status(valor:String):void{
			_status = null;
		}
		
		public function get status():String {
			return _status;
		}
		
		public function get tipoClassificacaoBem():TipoClassificacaoBemVO {
			return _tipoClassificacaoBem;	
		}
		
		public function set tipoClassificacaoBem(valor:TipoClassificacaoBemVO):void {
			this._tipoClassificacaoBem = valor;
		}
		
		public function get valor():Number {
			return _valor;	
		}
		
		public function set valor(valor:Number):void {
			this._valor = valor;
		}
		
		public function get valorNaoInformado():Boolean {
			return _valorNaoInformado;	
		}
		
		public function set valorNaoInformado(valor:Boolean):void {
			this._valorNaoInformado = valor;
		}
		
		public function get documentosComprobatorios():ListCollectionView {
			return _documentosComprobatorios;
		}
		
		public function set documentosComprobatorios(valor:ListCollectionView):void {
			this._documentosComprobatorios = valor;
		}
		
		public function get id():Number{
			return _idBem;
		}
		
		public function get pessoaCompartilhamento():PessoaCompartilhamentoVO {
			return null;
		}
		
		public function get tipoAutorizacao():TipoAutorizacaoEnum {
			return TipoAutorizacaoEnum.BEM_NOVO;
		}
		
		public function set idInstituicaoAtualizacao(valor:Number):void {
			this._idInstituicaoAtualizacao = valor;
		}
		
		public function get idInstituicaoAtualizacao():Number {
			return this._idInstituicaoAtualizacao;
		}
		
		public function set verificarAutorizacao(verificarAutorizacao:Booleano):void {
			this._verificarAutorizacao = verificarAutorizacao;	
		}
		
		public function get verificarAutorizacao():Booleano {
			return this._verificarAutorizacao;	
		}
		
		public function set dataHoraInicio(valor:IDateTime):void {
			this._dataHoraInicio = valor;
		}
		
		public function get dataHoraInicio():IDateTime {
			return this._dataHoraInicio;
		}
		
		public function set gravarHistorico(gravarHistorico:Booleano):void {
			this._gravarHistorico = gravarHistorico;
		}
		
		public function get gravarHistorico():Booleano {
			return this._gravarHistorico;
		}
		
		public function get proprietarios():ListCollectionView {
			return this._proprietarios;
		}
		
		public function set proprietarios(valor:ListCollectionView):void {
			this._proprietarios = valor;
		}
		
		public function get idUsuarioEnvio():String
		{
			return _idUsuarioEnvio;
		}
		
		public function set idUsuarioEnvio(value:String):void
		{
			_idUsuarioEnvio = value; 	
		}
		
        override public function readExternal(input:IDataInput):void {
			__laziness = input.readObject() as String;
			if (meta::isInitialized()) {
				super.readExternal(input);
				_codCompartilhamentoCadastro = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
				_codigoSituacaoAprovacao = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
				_compartilhamento = input.readObject() as CompartilhamentoCadastroVO;
				_dataHoraInicio = input.readObject() as IDateTime;
				_descricao = input.readObject() as String;
				_documentosComprobatorios = input.readObject() as ListCollectionView;
				_gravarHistorico = SerializacaoObjetos.lerBooleano(input);
				_idBem = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
				_idInstituicaoAtualizacao = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
				_idRegistroControlado = input.readObject() as String;
				_idUsuarioEnvio  = input.readObject() as String;
				_interno = input.readObject() as Boolean;
				_mesRenovacaoSeguro = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
				_proprietarios = input.readObject() as ListCollectionView;
				_situacaoAprovacao = input.readObject() as SituacaoRegistroEnum;
				_status = input.readObject() as String;
				_tipoClassificacaoBem = input.readObject() as TipoClassificacaoBemVO;
				_valor = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
				_valorNaoInformado = input.readObject() as Boolean;
				_verificarAutorizacao = SerializacaoObjetos.lerBooleano(input);
			}
        }
		
        override public function writeExternal(output:IDataOutput):void {
			output.writeObject(__laziness);
			if (meta::isInitialized()) {
				super.writeExternal(output);
				output.writeObject(_codCompartilhamentoCadastro);
				output.writeObject(_codigoSituacaoAprovacao);
				output.writeObject(_compartilhamento);
				output.writeObject(_dataHoraInicio);
				output.writeObject(_descricao);
				output.writeObject(_documentosComprobatorios);
				SerializacaoObjetos.escreverBooleano(_gravarHistorico, output);
				output.writeObject(_idBem);
				output.writeObject(_idInstituicaoAtualizacao);
				output.writeObject(_idRegistroControlado);
				output.writeObject(_idUsuarioEnvio);
				output.writeObject(_interno);
				output.writeObject(_mesRenovacaoSeguro);
				output.writeObject(_proprietarios);
				output.writeObject(_situacaoAprovacao);
				output.writeObject(_status);
				output.writeObject(_tipoClassificacaoBem);
				output.writeObject(_valor);
				output.writeObject(_valorNaoInformado);
				SerializacaoObjetos.escreverBooleano(_verificarAutorizacao, output);
			}
        }
    }
}