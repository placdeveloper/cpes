package br.com.sicoob.capes.comum.vo.entidades {
	
	import flash.net.registerClassAlias;
	import flash.utils.IDataInput;
	import flash.utils.IDataOutput;
	
	import mx.collections.ArrayCollection;
	import mx.collections.ListCollectionView;
	
	import br.com.bancoob.tipos.Booleano;
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.tipos.SerializacaoObjetos;
	
	import org.granite.meta;
	import org.granite.collections.IPersistentCollection;
	
	use namespace meta;

	registerClassAlias("br.com.sicoob.capes.negocio.entidades.GrupoEconomicoManualPessoa", GrupoEconomicoNovoManualPessoaVO);
	[Bindable]
    public class GrupoEconomicoNovoManualPessoaVO extends CAPESEntidadeVO {
		private var __laziness:String = null;
		
		private var _id:Number;
		private var _pessoaCompartilhamento:PessoaCompartilhamentoVO;
		private var _dataHoraInicio:IDateTime;
		private var _idUsuarioCadastro:String;
		private var _idUsuarioExclusao:String;
		private var _grupoEconomico:GrupoEconomicoNovoVO;
		private var _grupoEconomicoAutomatico:GrupoEconomicoNovoVO;
		private var _gravarHistorico:Booleano;
		private var _motivoExclusao:String;
		
		meta function isInitialized(name:String = null):Boolean {
			if (!name)
				return __laziness === null;
			
			var property:* = this[name];
			return (
				(!(property is GrupoEconomicoNovoManualPessoaVO) || (property as GrupoEconomicoNovoManualPessoaVO).meta::isInitialized()) &&
				(!(property is IPersistentCollection) || (property as IPersistentCollection).isInitialized())
			);
		}
		
		
		public function get id():Number {
			return _id;
		}
		
		public function set id(value:Number):void {
			_id = value;
		}
		
		public function get dataHoraInicio():IDateTime {
			return _dataHoraInicio;
		}
		
		public function set dataHoraInicio(value:IDateTime):void {
			_dataHoraInicio = value;
		}
		
		public function get idUsuarioCadastro():String {
			return _idUsuarioCadastro;
		}
		
		public function set idUsuarioCadastro(value:String):void {
			_idUsuarioCadastro = value;
		}
		
		public function get grupoEconomico():GrupoEconomicoNovoVO {
			return _grupoEconomico;
		}
		
		public function set grupoEconomico(value:GrupoEconomicoNovoVO):void {
			_grupoEconomico = value;
		}
		
		public function get grupoEconomicoAutomatico():GrupoEconomicoNovoVO {
			return _grupoEconomicoAutomatico;
		}
		
		public function set grupoEconomicoAutomatico(value:GrupoEconomicoNovoVO):void {
			_grupoEconomicoAutomatico = value;
		}
		
		public function get pessoaCompartilhamento():PessoaCompartilhamentoVO {
			return _pessoaCompartilhamento;
		}
		
		public function set pessoaCompartilhamento(value:PessoaCompartilhamentoVO):void {
			_pessoaCompartilhamento = value;
		}
		
		public function set gravarHistorico(value:Booleano):void {
			_gravarHistorico = value;
		}
		
		public function get gravarHistorico():Booleano {
			return _gravarHistorico;
		}
		
		public function get idUsuarioExclusao():String {
			return _idUsuarioExclusao;
		}
		
		public function set idUsuarioExclusao(value:String):void {
			_idUsuarioExclusao = value;
		}
		
		public function get motivoExclusao():String {
			return _motivoExclusao;
		}
		
		public function set motivoExclusao(value:String):void {
			_motivoExclusao = value;
		}
		
		[Transient]
		public function get integrantesAutomatico():ListCollectionView {
			if(grupoEconomicoAutomatico != null) {
				var lista:ArrayCollection = new ArrayCollection;
				var chaveIdPessoaCompartilhamento:Object = new Object;
				chaveIdPessoaCompartilhamento[pessoaCompartilhamento.idPessoaCompartilhamento] = pessoaCompartilhamento;
				for each(var pessoaAutomatico:GrupoEconomicoNovoAutomaticoPessoaVO in grupoEconomicoAutomatico.integrantesAutomatico) {
					if(!chaveIdPessoaCompartilhamento[pessoaAutomatico.pessoaCompartilhamento.idPessoaCompartilhamento]) {
						lista.addItem(pessoaAutomatico);
						chaveIdPessoaCompartilhamento[pessoaAutomatico.pessoaCompartilhamento.idPessoaCompartilhamento] = pessoaAutomatico.pessoaCompartilhamento;
					}
				}
				return lista;
			}
			return null;
		}
		
		override public function readExternal(input:IDataInput):void {
			__laziness = input.readObject() as String;
			if (meta::isInitialized()) {
				super.readExternal(input);
				_dataHoraInicio = input.readObject() as IDateTime;
				_gravarHistorico = SerializacaoObjetos.lerBooleano(input);
				_grupoEconomico = input.readObject() as GrupoEconomicoNovoVO;
				_grupoEconomicoAutomatico = input.readObject() as GrupoEconomicoNovoVO;
				_id = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
				_idUsuarioCadastro = input.readObject() as String;
				_idUsuarioExclusao = input.readObject() as String;
				_motivoExclusao = input.readObject() as String;
				_pessoaCompartilhamento = input.readObject() as PessoaCompartilhamentoVO;
			}
			else {
				_id = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			}
		}
		
		override public function writeExternal(output:IDataOutput):void {
			output.writeObject(__laziness);
			if (meta::isInitialized()) {
				super.writeExternal(output);
				output.writeObject(_dataHoraInicio);
				SerializacaoObjetos.escreverBooleano(_gravarHistorico, output);
				output.writeObject(_grupoEconomico);
				output.writeObject(_grupoEconomicoAutomatico);
				output.writeObject(_id);
				output.writeObject(_idUsuarioCadastro);
				output.writeObject(_idUsuarioExclusao);
				output.writeObject(_motivoExclusao);
				output.writeObject(_pessoaCompartilhamento);
			}
			else {
				output.writeObject(_id);
			}
		}

    }
	
}