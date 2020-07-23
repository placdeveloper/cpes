package br.com.sicoob.capes.comum.vo.entidades {
    import br.com.bancoob.tipos.IDateTime;
    
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    
    import org.granite.collections.IPersistentCollection;
    import org.granite.meta;
    use namespace meta;

    [Bindable]
	public class MensagemReplicacaoVOBase  extends CAPESEntidadeVO {

        private var __laziness:String = null;
        
		private var _conteudoMensagem:String;
		private var _dataCadastro:IDateTime;
		private var _dataEnvio:IDateTime;
		private var _dataProcessamento:IDateTime;
        private var _entidadeCadastro:String;
		private var _erro:String;
		private var _idInstituicao:Number;
		private var _idMensagemReplicacao:Number;
		private var _idPessoaLegado:Number;
		private var _idRegistro:Number;
		private var _identificadorOperacao:String;
		private var _tipoOperacao:String;

        meta function isInitialized(name:String = null):Boolean {
            if (!name)
                return __laziness === null;

            var property:* = this[name];
            return (
                (!(property is MensagemReplicacaoVO) || (property as MensagemReplicacaoVO).meta::isInitialized()) &&
                (!(property is IPersistentCollection) || (property as IPersistentCollection).isInitialized())
            );
        }
        
		public function set conteudoMensagem(value:String):void {
			_conteudoMensagem = value;
		}
		public function get conteudoMensagem():String {
			return _conteudoMensagem;
		}
		
		public function set dataCadastro(value:IDateTime):void {
			_dataCadastro = value;
		}
		public function get dataCadastro():IDateTime {
			return _dataCadastro;
		}
		
		public function set dataEnvio(value:IDateTime):void {
			_dataEnvio = value;
		}
		public function get dataEnvio():IDateTime {
			return _dataEnvio;
		}
		
		public function set dataProcessamento(value:IDateTime):void {
			_dataProcessamento = value;
		}
		public function get dataProcessamento():IDateTime {
			return _dataProcessamento;
		}
        
        public function set entidadeCadastro(value:String):void {
        	_entidadeCadastro = value;
        }
        public function get entidadeCadastro():String {
        	return _entidadeCadastro;
        }
		
		public function set erro(value:String):void {
			_erro = value;
		}
		public function get erro():String {
			return _erro;
		}
		
		public function set idInstituicao(value:Number):void {
			_idInstituicao = value;
		}
		public function get idInstituicao():Number {
			return _idInstituicao;
		}
		
		public function set idMensagemReplicacao(value:Number):void{
			_idMensagemReplicacao = value;
		}
		public function get idMensagemReplicacao():Number{
			return _idMensagemReplicacao;
		}
		
		public function set idPessoaLegado(value:Number):void{
			_idPessoaLegado = value;
		}
		public function get idPessoaLegado():Number {
			return _idPessoaLegado;
		}
		
		public function set idRegistro(value:Number):void{
			_idRegistro = value;
		}
		public function get idRegistro():Number {
			return _idRegistro;
		}
		
		public function set identificadorOperacao(value:String):void{
			_identificadorOperacao = value;
		}
		public function get identificadorOperacao():String{
			return _identificadorOperacao;
		}
		
		public function set tipoOperacao(value:String):void{
			_tipoOperacao = value;
		}
		public function get tipoOperacao():String {
			return _tipoOperacao;
		}

        override public function readExternal(input:IDataInput):void {
            __laziness = input.readObject() as String;
            if (meta::isInitialized()) {
                super.readExternal(input);
                _conteudoMensagem = input.readObject() as String;
                _dataCadastro = input.readObject() as IDateTime;
                _dataEnvio = input.readObject() as IDateTime;
                _dataProcessamento = input.readObject() as IDateTime;
                _entidadeCadastro = input.readObject() as String;
                _erro = input.readObject() as String;
                _idInstituicao = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
                _idMensagemReplicacao = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
                _idPessoaLegado = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
				_idRegistro = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
                _identificadorOperacao = input.readObject() as String;
                _tipoOperacao = input.readObject() as String;
            } else {
            	_idMensagemReplicacao = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
            }
		}
		
        override public function writeExternal(output:IDataOutput):void {
            output.writeObject(__laziness);
            if (meta::isInitialized()) {
                super.writeExternal(output);
                output.writeObject(_conteudoMensagem);
                output.writeObject(_dataCadastro);
                output.writeObject(_dataEnvio);
                output.writeObject(_dataProcessamento);
                output.writeObject(_entidadeCadastro);
                output.writeObject(_erro);
                output.writeObject(_idInstituicao);
                output.writeObject(_idMensagemReplicacao);
                output.writeObject(_idPessoaLegado);
                output.writeObject(_idRegistro);
                output.writeObject(_identificadorOperacao);
                output.writeObject(_tipoOperacao);
            } else {
                output.writeObject(_idMensagemReplicacao);
            }
        }
	}
}