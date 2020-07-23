package br.com.sicoob.capes.comum.vo
{
	
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.vo.BancoobVO;
	
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.sicoob.capes.cadastro.vo.informacoesprofissionais.InformacaoProfissionalVO",
		InformacaoProfissionalVO);
	
	public class InformacaoProfissionalVO extends BancoobVO
	{
		
		private var _idInformacao:Number;
		private var _idInstituicao:Number;
		private var _idPessoa:Number;
		private var _idPessoaEmpregador:Number;
		private var _idUsuarioAprovacao:String;
		private var _codigoTipoFuncionario:Number;
		private var _codigoTipoAfastamento:Number;
		private var _codigoConselhoRegional:Number;
		private var _codSituacao:Number;		
		
		private var _cnpjPessoaEmpregador:String;
		private var _nomePessoaEmpregador:String;
		private var _numMatricula:String;
		private var _cargo:String;
		private var _diaMesFerias:String;
		private var _ufConselho:String;
		private var _numeroInscricaoConselho:String;		

		private var _dataAdmissao:IDateTime;
		private var _dataHoraInicio:IDateTime;
		private var _dataDesligamento:IDateTime;

		public function set idInformacao(value:Number):void {
			_idInformacao = value;
		}
		public function get idInformacao():Number {
			return _idInformacao;
		}

		public function set idInstituicao(value:Number):void {
			_idInstituicao = value;
		}
		public function get idInstituicao():Number {
			return _idInstituicao;
		}

		public function set idPessoa(value:Number):void {
			_idPessoa = value;
		}
		public function get idPessoa():Number {
			return _idPessoa;
		}

		public function set idPessoaEmpregador(value:Number):void {
			_idPessoaEmpregador = value;
		}
		public function get idPessoaEmpregador():Number {
			return _idPessoaEmpregador;
		}
		
		public function get idUsuarioAprovacao():String{
			return _idUsuarioAprovacao;
		}
		
		public function set idUsuarioAprovacao(valor:String):void{
			this._idUsuarioAprovacao = valor;
		}

		public function set codigoTipoFuncionario(value:Number):void {
			_codigoTipoFuncionario = value;
		}
		public function get codigoTipoFuncionario():Number {
			return _codigoTipoFuncionario;
		}

		public function set codigoTipoAfastamento(value:Number):void {
			_codigoTipoAfastamento = value;
		}
		public function get codigoTipoAfastamento():Number {
			return _codigoTipoAfastamento;
		}
		
		public function set codigoConselhoRegional(value:Number):void {
			_codigoConselhoRegional = value;
		}
		public function get codigoConselhoRegional():Number {
			return _codigoConselhoRegional;
		}	
		
		public function set codSituacao(value:Number):void {
			_codSituacao = value;
		}
		public function get codSituacao():Number {
			return _codSituacao;
		}			

		public function set cnpjPessoaEmpregador(value:String):void {
			_cnpjPessoaEmpregador = value;
		}
		public function get cnpjPessoaEmpregador():String {
			return _cnpjPessoaEmpregador;
		}			

		public function set nomePessoaEmpregador(value:String):void {
			_nomePessoaEmpregador = value;
		}
		public function get nomePessoaEmpregador():String {
			return _nomePessoaEmpregador;
		}
		
		public function set numMatricula(value:String):void {
			_numMatricula = value;
		}
		public function get numMatricula():String {
			return _numMatricula;
		}		

		public function set cargo(value:String):void {
			_cargo = value;
		}
		public function get cargo():String {
			return _cargo;
		}		
		
		public function set diaMesFerias(value:String):void {
			_diaMesFerias = value;
		}
		public function get diaMesFerias():String {
			return _diaMesFerias;
		}		
		
		public function set ufConselho(value:String):void {
			_ufConselho = value;
		}
		public function get ufConselho():String {
			return _ufConselho;
		}	

		public function set numeroInscricaoConselho(value:String):void {
			_numeroInscricaoConselho = value;
		}
		public function get numeroInscricaoConselho():String {
			return _numeroInscricaoConselho;
		}

		public function set dataAdmissao(value:IDateTime):void {
			_dataAdmissao = value;
		}
		public function get dataAdmissao():IDateTime {
			return _dataAdmissao;
		}
		public function set dataHoraInicio(value:IDateTime):void {
			_dataHoraInicio = value;
		}
		public function get dataHoraInicio():IDateTime {
			return _dataHoraInicio;
		}
		public function set dataDesligamento(value:IDateTime):void {
			_dataDesligamento = value;
		}
		public function get dataDesligamento():IDateTime {
			return _dataDesligamento;
		}		

	}
}