package br.com.sicoob.capes.comum.vo.entidades {
	
	import flash.net.registerClassAlias;
	import flash.utils.IDataInput;
	import flash.utils.IDataOutput;
	
	import br.com.bancoob.tipos.Booleano;
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.tipos.SerializacaoObjetos;
	
	import org.granite.meta;
	import org.granite.collections.IPersistentCollection;
	
	use namespace meta;

	registerClassAlias("br.com.sicoob.capes.negocio.entidades.GrupoEconomicoAutomaticoPessoa", GrupoEconomicoNovoAutomaticoPessoaVO);
	[Bindable]
    public class GrupoEconomicoNovoAutomaticoPessoaVO extends CAPESEntidadeVO {
		private var __laziness:String = null;
		
		private var _id:Number;
		private var _relacionamentoPessoa:RelacionamentoPessoaVO;
		private var _pessoaCompartilhamento:PessoaCompartilhamentoVO;
		private var _dataHoraInicio:IDateTime;
		private var _bolControlador:Booleano;
		private var _percentualSocio:Number;
		private var _grupoEconomico:GrupoEconomicoNovoVO;
		private var _idUsuarioCadastro:String;
		private var _idUsuarioExclusao:String;
		
		private var _gravarHistorico:Booleano;
		
		meta function isInitialized(name:String = null):Boolean {
			if (!name)
				return __laziness === null;
			
			var property:* = this[name];
			return (
				(!(property is GrupoEconomicoNovoAutomaticoPessoaVO) || (property as GrupoEconomicoNovoAutomaticoPessoaVO).meta::isInitialized()) &&
				(!(property is IPersistentCollection) || (property as IPersistentCollection).isInitialized())
			);
		}
		
		override public function readExternal(input:IDataInput):void {
			__laziness = input.readObject() as String;
			if (meta::isInitialized()) {
				super.readExternal(input);
				_bolControlador = SerializacaoObjetos.lerBooleano(input);
				_dataHoraInicio = input.readObject() as IDateTime;
				_gravarHistorico = SerializacaoObjetos.lerBooleano(input);
				_grupoEconomico = input.readObject() as GrupoEconomicoNovoVO;
				_id = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
				_idUsuarioCadastro = input.readObject() as String;
				_idUsuarioExclusao = input.readObject() as String;
				_percentualSocio = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
				_pessoaCompartilhamento = input.readObject() as PessoaCompartilhamentoVO;
				_relacionamentoPessoa = input.readObject() as RelacionamentoPessoaVO;
			}
			else {
				_id = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			}
		}
		
		override public function writeExternal(output:IDataOutput):void {
			output.writeObject(__laziness);
			if (meta::isInitialized()) {
				super.writeExternal(output);
				SerializacaoObjetos.escreverBooleano(_bolControlador, output);
				output.writeObject(_dataHoraInicio);
				SerializacaoObjetos.escreverBooleano(_gravarHistorico, output);
				output.writeObject(_grupoEconomico);
				output.writeObject(_id);
				output.writeObject(_idUsuarioCadastro);
				output.writeObject(_idUsuarioExclusao);
				output.writeObject(_percentualSocio);
				output.writeObject(_pessoaCompartilhamento);
				output.writeObject(_relacionamentoPessoa);
			}
			else {
				output.writeObject(_id);
			}
		}

		public function get id():Number
		{
			return _id;
		}

		public function set id(value:Number):void
		{
			_id = value;
		}

		public function get relacionamentoPessoa():RelacionamentoPessoaVO
		{
			return _relacionamentoPessoa;
		}

		public function set relacionamentoPessoa(value:RelacionamentoPessoaVO):void
		{
			_relacionamentoPessoa = value;
		}

		public function get pessoaCompartilhamento():PessoaCompartilhamentoVO
		{
			return _pessoaCompartilhamento;
		}

		public function set pessoaCompartilhamento(value:PessoaCompartilhamentoVO):void
		{
			_pessoaCompartilhamento = value;
		}

		public function get dataHoraInicio():IDateTime
		{
			return _dataHoraInicio;
		}

		public function set dataHoraInicio(value:IDateTime):void
		{
			_dataHoraInicio = value;
		}

		public function get bolControlador():Booleano
		{
			return _bolControlador;
		}

		public function set bolControlador(value:Booleano):void
		{
			_bolControlador = value;
		}

		public function get percentualSocio():Number
		{
			return _percentualSocio;
		}

		public function set percentualSocio(value:Number):void
		{
			_percentualSocio = value;
		}

		public function get grupoEconomico():GrupoEconomicoNovoVO
		{
			return _grupoEconomico;
		}

		public function set grupoEconomico(value:GrupoEconomicoNovoVO):void
		{
			_grupoEconomico = value;
		}

		public function get idUsuarioCadastro():String
		{
			return _idUsuarioCadastro;
		}

		public function set idUsuarioCadastro(value:String):void
		{
			_idUsuarioCadastro = value;
		}
		
		public function get idUsuarioExclusao():String
		{
			return _idUsuarioExclusao;
		}
		
		public function set idUsuarioExclusao(value:String):void
		{
			_idUsuarioExclusao = value;
		}

		public function get gravarHistorico():Booleano
		{
			return _gravarHistorico;
		}

		public function set gravarHistorico(value:Booleano):void
		{
			_gravarHistorico = value;
		}

		
    }
	
}