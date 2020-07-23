package br.com.sicoob.capes.comum.vo.entidades{
	import br.com.bancoob.tipos.IDateTime;
	import br.com.sicoob.capes.comum.vo.entidades.CAPESEntidadeVO;
	
	import flash.utils.IDataInput;
	import flash.utils.IDataOutput;
	
	import org.granite.meta;
	
    use namespace meta;

    [Bindable]
	public class GrupoEconomicoPessoaBaseVOBase extends CAPESEntidadeVO {

		private var _dataHoraInicio : IDateTime;
		private var _grupoEconomico : GrupoEconomicoVO;
		private var _idUsuarioAprovacao:String;
		private var _pessoaInstituicao : PessoaInstituicaoVO;
		
		public function set dataHoraInicio(value:IDateTime):void {
			_dataHoraInicio = value;
		}
		public function get dataHoraInicio():IDateTime {
			return _dataHoraInicio;
		}
		
		public function set grupoEconomico(value:GrupoEconomicoVO):void {
			_grupoEconomico = value;
		}
		public function get grupoEconomico():GrupoEconomicoVO {
			return _grupoEconomico;
		}
		
		public function set pessoaInstituicao(value : PessoaInstituicaoVO):void {
			_pessoaInstituicao = value;
		}
		public function get pessoaInstituicao():PessoaInstituicaoVO {
			return _pessoaInstituicao;
		}
		
		public function get idUsuarioAprovacao():String{
			return _idUsuarioAprovacao;
		}
		
		public function set idUsuarioAprovacao(valor:String):void{
			this._idUsuarioAprovacao = valor;
		}
		
		override public function readExternal(input:IDataInput):void {
			super.readExternal(input);
			_dataHoraInicio = input.readObject() as IDateTime;
			_grupoEconomico = input.readObject() as GrupoEconomicoVO;
			_idUsuarioAprovacao = input.readObject() as String;
			_pessoaInstituicao = input.readObject() as PessoaInstituicaoVO;
		}
		
		override public function writeExternal(output:IDataOutput):void {
			super.writeExternal(output);
			output.writeObject(_dataHoraInicio);
			output.writeObject(_grupoEconomico);
			output.writeObject(_idUsuarioAprovacao);
			output.writeObject(_pessoaInstituicao);
		}
	}
}