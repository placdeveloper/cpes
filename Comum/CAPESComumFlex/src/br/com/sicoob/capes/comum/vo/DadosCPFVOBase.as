package br.com.sicoob.capes.comum.vo
{
	import br.com.bancoob.tipos.SerializacaoObjetos;
	
	import flash.utils.IDataInput;
	import flash.utils.IDataOutput;

	public class DadosCPFVOBase extends DadosReceitaFederalVO
	{
		private var _anoObito:int;
		private var _codResidenteExterior:int;
		private var _codSexo:int;
		private var _dataNascimento:Date;
		private var _cpf:String;
		private var _descResidenteExterior:String;
		private var _descSituacaoCadastral:String;
		private var _descSexo:String;
		private var _nome:String;
		private var _nomeMae:String;
		private var _numTituloEleitor:String;
		
		public function get codResidenteExterior():int {
			return _codResidenteExterior;
		}
		public function set codResidenteExterior(codResidenteExterior:int):void {
			this._codResidenteExterior = codResidenteExterior;
		}
		
		public function get nomeMae():String {
			return _nomeMae;
		}
		public function set nomeMae(nomeMae:String):void {
			this._nomeMae = nomeMae;
		}
		
		public function get dataNascimento():Date {
			return _dataNascimento;
		}
		public function set dataNascimento(dataNascimento:Date):void {
			this._dataNascimento = dataNascimento;
		}
		
		public function get codSexo():int {
			return _codSexo;
		}
		public function set codSexo(codSexo:int):void {
			this._codSexo = codSexo;
		}
		
		public function get numTituloEleitor():String {
			return _numTituloEleitor;
		}
		public function set numTituloEleitor(numTituloEleitor:String):void {
			this._numTituloEleitor = numTituloEleitor;
		}
		
		public function get anoObito():int {
			return _anoObito;
		}
		public function set anoObito(anoObito:int):void {
			this._anoObito = anoObito;
		}
		
		public function get cpf():String {
			return _cpf;
		}
		public function set cpf(cpf:String):void {
			this._cpf = cpf;
		}
		
		public function get descSituacaoCadastral():String {
			return _descSituacaoCadastral;
		}
		public function set descSituacaoCadastral(descSituacaoCadastral:String):void {
			this._descSituacaoCadastral = descSituacaoCadastral;
		}
		
		public function get descResidenteExterior():String {
			return _descResidenteExterior;
		}
		public function set descResidenteExterior(descResidenteExterior:String):void {
			this._descResidenteExterior = descResidenteExterior;
		}
		
		public function get descSexo():String {
			return _descSexo;
		}
		public function set descSexo(descSexo:String):void {
			this._descSexo = descSexo;
		}
		
		public override function get nome():String {
			return _nome;
		}
		public function set nome(nome:String):void {
			this._nome = nome;
		}
		
		public override function readExternal(input:IDataInput):void {
			super.readExternal(input);
			_anoObito = input.readInt();                
			_codResidenteExterior = input.readInt();    
			_codSexo = input.readInt();                 
			_cpf = input.readObject() as String;        
			_dataNascimento = SerializacaoObjetos.lerDate(input);         
			_descResidenteExterior = input.readObject() as String;
			_descSexo = input.readObject() as String;             
			_descSituacaoCadastral = input.readObject() as String;
			_nome = input.readObject() as String;                 
			_nomeMae = input.readObject() as String;              
			_numTituloEleitor = input.readObject() as String;     
		}
		
		public override function writeExternal(output:IDataOutput):void {
			super.writeExternal(output);
			output.writeInt(_anoObito);
			output.writeInt(_codResidenteExterior);
			output.writeInt(_codSexo);
			output.writeObject(_cpf);
			SerializacaoObjetos.escreverDate(_dataNascimento, output);         
			output.writeObject(_descResidenteExterior);
			output.writeObject(_descSexo);
			output.writeObject(_descSituacaoCadastral);
			output.writeObject(_nome);
			output.writeObject(_nomeMae);
			output.writeObject(_numTituloEleitor);
		}
	}
}