package br.com.sicoob.capes.relatorioDeclaracaoProposito.dto {
	import br.com.bancoob.dto.BancoobDTO;
	
	import flash.net.registerClassAlias;
	import flash.utils.IDataInput;
	import flash.utils.IDataOutput;
	import flash.utils.IExternalizable;

	registerClassAlias("br.com.sicoob.capes.relatorio.negocio.dto.RelatorioDeclaracaoPropositoDTO", RelatorioDeclaracaoPropositoDTO);
    public class RelatorioDeclaracaoPropositoDTO extends BancoobDTO implements IExternalizable {
		
		private var _contaPoupanca:Boolean;
		private var _contaCorrente:Boolean;
		private var _contaSalario:Boolean;
		private var _chequeEspecial:Boolean;
		private var _emprestimoFinanciamento:Boolean;
		private var _investimento:Boolean;
		private var _cartoes:Boolean;
		private var _seguros:Boolean;
		private var _consorcio:Boolean;
		private var _previdenciaPrivada:Boolean;
		
		public function get contaPoupanca():Boolean {
			return _contaPoupanca;
		}
		
		public function set contaPoupanca(valor:Boolean):void {
			this._contaPoupanca = valor;
		}
		
		public function get contaCorrente():Boolean {
			return _contaCorrente;
		}
		
		public function set contaCorrente(valor:Boolean):void {
			this._contaCorrente = valor;
		}
		
		public function get contaSalario():Boolean {
			return _contaSalario;
		}
		
		public function set contaSalario(valor:Boolean):void {
			this._contaSalario = valor;
		}
		
		public function get chequeEspecial():Boolean {
			return _chequeEspecial;
		}
		
		public function set chequeEspecial(valor:Boolean):void {
			this._chequeEspecial = valor;
		}
		
		public function get emprestimoFinanciamento():Boolean {
			return _emprestimoFinanciamento;
		}
		
		public function set emprestimoFinanciamento(valor:Boolean):void {
			this._emprestimoFinanciamento = valor;
		}
		
		public function get investimento():Boolean {
			return _investimento;
		}
		
		public function set investimento(valor:Boolean):void {
			this._investimento = valor;
		}
		
		public function get cartoes():Boolean {
			return _cartoes;
		}
		
		public function set cartoes(valor:Boolean):void {
			this._cartoes = valor;
		}
		
		public function get seguros():Boolean {
			return _seguros;
		}
		
		public function set seguros(valor:Boolean):void {
			this._seguros = valor;
		}
		
		public function get consorcio():Boolean {
			return _consorcio;
		}
		
		public function set consorcio(valor:Boolean):void {
			this._consorcio = valor;
		}
		
		public function get previdenciaPrivada():Boolean {
			return _previdenciaPrivada;
		}
		
		public function set previdenciaPrivada(valor:Boolean):void {
			this._previdenciaPrivada = valor;
		}
		
		public function readExternal(input:IDataInput):void {
			_cartoes = input.readObject() as Boolean;
			_chequeEspecial = input.readObject() as Boolean;
			_consorcio = input.readObject() as Boolean;
			_contaCorrente = input.readObject() as Boolean;
			_contaPoupanca = input.readObject() as Boolean;
			_contaSalario = input.readObject() as Boolean;
			_emprestimoFinanciamento = input.readObject() as Boolean;
			_investimento = input.readObject() as Boolean;
			_previdenciaPrivada = input.readObject() as Boolean;
			_seguros = input.readObject() as Boolean;
		}
		
		public function writeExternal(output:IDataOutput):void {
			output.writeObject(_cartoes);
			output.writeObject(_chequeEspecial);
			output.writeObject(_consorcio);
			output.writeObject(_contaCorrente);
			output.writeObject(_contaPoupanca);
			output.writeObject(_contaSalario);
			output.writeObject(_emprestimoFinanciamento);
			output.writeObject(_investimento);
			output.writeObject(_previdenciaPrivada);
			output.writeObject(_seguros);
		}
	}
}