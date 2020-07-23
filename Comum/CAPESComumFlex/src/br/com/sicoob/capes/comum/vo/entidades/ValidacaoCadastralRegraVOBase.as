package br.com.sicoob.capes.comum.vo.entidades{
	import br.com.bancoob.tipos.Booleano;
	import br.com.bancoob.tipos.SerializacaoObjetos;
	import br.com.sicoob.capes.comum.enums.FuncionalidadeValidacaoCadastralEnum;
	
	import flash.utils.IDataInput;
	import flash.utils.IDataOutput;
	
	import org.granite.collections.IPersistentCollection;
	import org.granite.meta;
	
	use namespace meta;
	
	[Bindable]
	public class ValidacaoCadastralRegraVOBase extends CAPESEntidadeVO{
		private var __laziness:String = null;
		
		private var _ativo:Booleano;
		private var _codigoRegra:Number;
		private var _descricao:String;
		private var _executarRegra:Booleano;
		private var _funcionalidade:FuncionalidadeValidacaoCadastralEnum;
		private var _mensagemErro:String;
		private var _perfilCadastro:PerfilCadastroVO;
		private var _query:String;
		private var _tipoRegra:TipoRegraValidacaoCadastralVO;

		meta function isInitialized(name:String = null):Boolean {
			if (!name)
				return __laziness === null;
			
			var property:* = this[name];
			return (
				(!(property is ValidacaoCadastralRegraVOBase) || (property as ValidacaoCadastralRegraVOBase).meta::isInitialized()) &&
				(!(property is IPersistentCollection) || (property as IPersistentCollection).isInitialized())
			);
		}

		public function get ativo():Booleano
		{
			return _ativo;
		}

		public function set ativo(value:Booleano):void
		{
			_ativo = value;
		}

		public function get codigoRegra():Number
		{
			return _codigoRegra;
		}

		public function set codigoRegra(value:Number):void
		{
			_codigoRegra = value;
		}

		public function get descricao():String
		{
			return _descricao;
		}

		public function set descricao(value:String):void
		{
			_descricao = value;
		}
		
		public function get executarRegra():Booleano
		{
			return _executarRegra;
		}
		
		public function set executarRegra(value:Booleano):void
		{
			_executarRegra = value;
		}

		public function get funcionalidade():FuncionalidadeValidacaoCadastralEnum
		{
			return _funcionalidade;
		}

		public function set funcionalidade(value:FuncionalidadeValidacaoCadastralEnum):void
		{
			_funcionalidade = value;
		}

		public function get mensagemErro():String
		{
			return _mensagemErro;
		}

		public function set mensagemErro(value:String):void
		{
			_mensagemErro = value;
		}
		
		public function get perfilCadastro():PerfilCadastroVO
		{
			return _perfilCadastro;
		}
		
		public function set perfilCadastro(value:PerfilCadastroVO):void
		{
			_perfilCadastro = value;
		}

		public function get query():String
		{
			return _query;
		}

		public function set query(value:String):void
		{
			_query = value;
		}
		
		public function get tipoRegra():TipoRegraValidacaoCadastralVO
		{
			return _tipoRegra;
		}
		
		public function set tipoRegra(value:TipoRegraValidacaoCadastralVO):void
		{
			_tipoRegra = value;
		}

		override public function readExternal(input:IDataInput):void {
			__laziness = input.readObject() as String;
			if (meta::isInitialized()) {
				super.readExternal(input);
				_ativo = SerializacaoObjetos.lerBooleano(input);
				_codigoRegra = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
				_descricao = input.readObject() as String;
				_executarRegra = SerializacaoObjetos.lerBooleano(input);
				_funcionalidade = input.readObject() as FuncionalidadeValidacaoCadastralEnum;
				_mensagemErro = input.readObject() as String;
				_perfilCadastro = input.readObject() as PerfilCadastroVO;
				_query = input.readObject() as String;
				_tipoRegra = input.readObject() as TipoRegraValidacaoCadastralVO;
			} else {
				_codigoRegra = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			}
		}
		
		override public function writeExternal(output:IDataOutput):void {
			output.writeObject(__laziness);
			if (meta::isInitialized()) {
				super.writeExternal(output);
				SerializacaoObjetos.escreverBooleano(_ativo, output);
				output.writeObject(_codigoRegra);
				output.writeObject(_descricao);
				SerializacaoObjetos.escreverBooleano(_executarRegra, output);
				output.writeObject(_funcionalidade);
				output.writeObject(_mensagemErro);
				output.writeObject(_perfilCadastro);
				output.writeObject(_query);
				output.writeObject(_tipoRegra);
			}
			else {
				output.writeObject(_codigoRegra);
			}
		}

	}
}