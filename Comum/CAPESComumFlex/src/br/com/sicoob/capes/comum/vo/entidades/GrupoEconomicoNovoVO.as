package br.com.sicoob.capes.comum.vo.entidades {
	
	import flash.net.registerClassAlias;
	import flash.utils.IDataInput;
	import flash.utils.IDataOutput;
	
	import mx.collections.ListCollectionView;
	
	import br.com.bancoob.tipos.Booleano;
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.tipos.SerializacaoObjetos;
	
	import org.granite.meta;
	import org.granite.collections.IPersistentCollection;
	
	use namespace meta;

	registerClassAlias("br.com.sicoob.capes.negocio.entidades.GrupoEconomicoNovo", GrupoEconomicoNovoVO);
	[Bindable]
    public class GrupoEconomicoNovoVO extends CAPESEntidadeVO {
		private var __laziness:String = null;
		
		private var _id:Number;
		private var _nome:String;
		private var _dataHoraCadastro:IDateTime;
		private var _dataHoraInicio:IDateTime;
		private var _idInstituicao:Number;
		private var _idUsuarioCadastro:String;
		private var _idUsuarioExclusao:String;
		private var _tipo:TipoGrupoEconomicoVO;
		private var _integrantesManual:ListCollectionView;
		private var _integrantesAutomatico:ListCollectionView;
		private var _gravarHistorico:Booleano;
		private var _motivoExclusao:String;
		private var _integrantesManualExclusao:ListCollectionView;
		
		meta function isInitialized(name:String = null):Boolean {
			if (!name){
				return __laziness === null;
			}
			
			var property:* = this[name];
			return (
				(!(property is GrupoEconomicoNovoVO) || (property as GrupoEconomicoNovoVO).meta::isInitialized()) &&
				(!(property is IPersistentCollection) || (property as IPersistentCollection).isInitialized()));
		}
		
		public function get id():Number {
			return _id;
		}
		
		public function set id(value:Number):void {
			_id = value;
		}
		
		public function get nome():String {
			return _nome;
		}
		
		public function set nome(value:String):void {
			_nome = value;
		}
		
		public function get dataHoraInicio():IDateTime {
			return _dataHoraInicio;
		}
		
		public function set dataHoraInicio(value:IDateTime):void {
			_dataHoraInicio = value;
		}
		
		public function get dataHoraCadastro():IDateTime {
			return _dataHoraCadastro;
		}
		
		public function set dataHoraCadastro(value:IDateTime):void {
			_dataHoraCadastro = value;
		}
		
		public function get idInstituicao():Number {
			return _idInstituicao;
		}
		
		public function set idInstituicao(value:Number):void {
			_idInstituicao = value;
		}
		
		public function get idUsuarioCadastro():String {
			return _idUsuarioCadastro;
		}
		
		public function set idUsuarioCadastro(value:String):void {
			_idUsuarioCadastro = value;
		}
		
		public function get tipo():TipoGrupoEconomicoVO {
			return _tipo;
		}
		
		public function set tipo(value:TipoGrupoEconomicoVO):void {
			_tipo = value;
		}
		
		public function get integrantesManual():ListCollectionView {
			return _integrantesManual;
		}
		
		public function set integrantesManual(value:ListCollectionView):void {
			_integrantesManual = value;
		}
		
		public function get integrantesAutomatico():ListCollectionView {
			return _integrantesAutomatico;
		}
		
		public function set integrantesAutomatico(value:ListCollectionView):void {
			_integrantesAutomatico = value;
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
		
		public function get integrantesManualExclusao():ListCollectionView {
			return _integrantesManualExclusao;
		}
		
		public function set integrantesManualExclusao(value:ListCollectionView):void {
			_integrantesManualExclusao = value;
		}
		
		override public function readExternal(input:IDataInput):void {
			__laziness = input.readObject() as String;
			if (meta::isInitialized()) {
				super.readExternal(input);
				_dataHoraCadastro = input.readObject() as IDateTime;
				_dataHoraInicio = input.readObject() as IDateTime;
				_gravarHistorico = SerializacaoObjetos.lerBooleano(input);
				_id = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
				_idInstituicao = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
				_idUsuarioCadastro = input.readObject() as String;
				_idUsuarioExclusao = input.readObject() as String;
				_integrantesAutomatico = input.readObject() as ListCollectionView;
				_integrantesManual = input.readObject() as ListCollectionView;
				_integrantesManualExclusao = input.readObject() as ListCollectionView;
				_motivoExclusao = input.readObject() as String;
				_nome = input.readObject() as String;
				_tipo = input.readObject() as TipoGrupoEconomicoVO;
			}
			else {
				_id = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject()());
			}
		}
		
		override public function writeExternal(output:IDataOutput):void {
			output.writeObject(__laziness);
			if (meta::isInitialized()) {
				super.writeExternal(output);
				output.writeObject(_dataHoraCadastro);
				output.writeObject(_dataHoraInicio);
				SerializacaoObjetos.escreverBooleano(_gravarHistorico, output);
				output.writeObject(_id);
				output.writeObject(_idInstituicao);
				output.writeObject(_idUsuarioCadastro);
				output.writeObject(_idUsuarioExclusao);
				output.writeObject(_integrantesAutomatico);
				output.writeObject(_integrantesManual);
				output.writeObject(_integrantesManualExclusao);
				output.writeObject(_motivoExclusao);
				output.writeObject(_nome);
				output.writeObject(_tipo);
			}
			else {
				output.writeObject(_id);
			}
		}

    }
}
