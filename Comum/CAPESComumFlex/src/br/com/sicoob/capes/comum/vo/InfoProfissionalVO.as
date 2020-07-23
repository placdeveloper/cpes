package br.com.sicoob.capes.comum.vo
{
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.vo.BancoobVO;
	
	import flash.net.registerClassAlias;

	registerClassAlias("br.com.sicoob.sisbr.clientes.fachada.vo.InfoProfissionalVO",InfoProfissionalVO);

	public class InfoProfissionalVO extends BancoobVO
	{
		private var _descMatriculaFunc:String;
		private var _descEmpresaTrabalha:String;
		private var _descOcupacaoProfissional:String;
		private var _dataAdmissao:IDateTime;
		private var _numDDD:String;
		private var _numTelefone:String;
		private var _numRamal:String;
		private var _numPessoaFisica:int;
		private var _numPessoaJuridica:int;
		private var _codOrgao:int;
		private var _codSubOrgao:int;
		private var _codTipoTomador:int;
		private var _codTipoVerba:int;
		private var _codTipoSindicalizacao:int;
		private var _uidTrabalha:String;
        //private var _numPessoaJuridicaAnterior:int;
        //private var _descMatriculaFuncAnterior:String;        
        private var _codTpAfastFuncionario:int;
        private var _codTipoFuncionario:int;
        private var _descPerAquisitivo:String;
        private var _codSitFuncionario:int;        
        private var _descSitFuncionario:String;
        private var _dataDesligamento:IDateTime;        
        private var _numInscConsReg:String;
        private var _codSiglaConsReg:int;
        private var _siglaUFConsReg:String;
        private var _numCpfCnpj:String;

		//CPF
		public function get numCpfCnpj():String
		{ return _numCpfCnpj; }
		public function set numCpfCnpj(vlr:String):void
		{ _numCpfCnpj = vlr; }
    	
		// DescMatriculaFunc
		public function get descMatriculaFunc():String
		{ return _descMatriculaFunc; }
		public function set descMatriculaFunc(vlr:String):void
		{ _descMatriculaFunc = vlr; }
		
		// DescEmpresaTrabalha
		public function get descEmpresaTrabalha():String
		{ return _descEmpresaTrabalha; }
		public function set descEmpresaTrabalha(vlr:String):void
		{ _descEmpresaTrabalha = vlr; }
		
		// DescOcupacaoProfissional
		public function get descOcupacaoProfissional():String
		{ return _descOcupacaoProfissional; }
		public function set descOcupacaoProfissional(vlr:String):void
		{ _descOcupacaoProfissional = vlr; }
		
		// DataAdmissao
		public function get dataAdmissao():IDateTime
		{ return _dataAdmissao; }
		public function set dataAdmissao(vlr:IDateTime):void
		{ _dataAdmissao = vlr; }
				
		// NumDDD
		public function get numDDD():String
		{ return _numDDD; }
		public function set numDDD(vlr:String):void
		{ _numDDD = vlr; }
		
		// NumTelefone
		public function get numTelefone():String
		{ return _numTelefone; }
		public function set numTelefone(vlr:String):void
		{ _numTelefone = vlr; }
		
		// NumRamal
		public function get numRamal():String
		{ return _numRamal; }
		public function set numRamal(vlr:String):void
		{ _numRamal = vlr; }
		
		// NumPessoaFisica
		public function get numPessoaFisica():int
		{ return _numPessoaFisica; }
		public function set numPessoaFisica(vlr:int):void
		{ _numPessoaFisica = vlr; }
		
		// NumPessoaJuridica
		public function get numPessoaJuridica():int
		{ return _numPessoaJuridica; }
		public function set numPessoaJuridica(vlr:int):void
		{ _numPessoaJuridica = vlr; }
		
		// CodOrgao
		public function get codOrgao():int
		{ return _codOrgao; }
		public function set codOrgao(vlr:int):void
		{ _codOrgao = vlr; }

		// CodSubOrgao
		public function get codSubOrgao():int
		{ return _codSubOrgao; }
		public function set codSubOrgao(vlr:int):void
		{ _codSubOrgao = vlr; }
		
		// CodTipoTomador
		public function get codTipoTomador():int
		{ return _codTipoTomador; }
		public function set codTipoTomador(vlr:int):void
		{ _codTipoTomador = vlr; }
		
		// CodTipoVerba
		public function get codTipoVerba():int
		{ return _codTipoVerba; }
		public function set codTipoVerba(vlr:int):void
		{ _codTipoVerba = vlr; }
		
		// CodTipoSindicalizacao
		public function get codTipoSindicalizacao():int
		{ return _codTipoSindicalizacao; }
		public function set codTipoSindicalizacao(vlr:int):void
		{ _codTipoSindicalizacao = vlr; }
		
		// UIDTrabalha
		public function get uidTrabalha():String
		{ return _uidTrabalha; }
		public function set uidTrabalha(vlr:String):void
		{ _uidTrabalha = vlr; }

 		/* DescPessoaJuridicaAnterior
		public function get numPessoaJuridicaAnterior():int
		{ return _numPessoaJuridicaAnterior; }
		public function set numPessoaJuridicaAnterior(vlr:int):void
		{ _numPessoaJuridicaAnterior = vlr; }		

		// DescPessoaJuridicaAnterior
		public function get descMatriculaFuncAnterior():String
		{ return _descMatriculaFuncAnterior; }
		public function set descMatriculaFuncAnterior(vlr:String):void
		{ _descMatriculaFuncAnterior = vlr; }
		*/
		
		// DescPerAquisitivo
		public function get descPerAquisitivo():String
		{ return _descPerAquisitivo; }
		public function set descPerAquisitivo(vlr:String):void
		{ _descPerAquisitivo = vlr; }

		// CodTipoFuncionario
		public function get codTipoFuncionario():int
		{return _codTipoFuncionario;}
		public function set codTipoFuncionario(vlr:int):void
		{ _codTipoFuncionario = vlr;}
			
		//CodSitFuncionario
		public function get codSitFuncionario():int
		{return _codSitFuncionario;}
		public function set codSitFuncionario(vlr:int):void
		{ _codSitFuncionario = vlr; }
		
		//CodTpAfastFuncionario
		public function get codTpAfastFuncionario():int
		{ return _codTpAfastFuncionario; }
		public function set codTpAfastFuncionario(vlr:int):void
		{ _codTpAfastFuncionario = vlr; }
		
		//DescSitFuncionario
		public function get descSitFuncionario():String
		{ return _descSitFuncionario; }
		public function set descSitFuncionario(vlr:String):void
		{ _descSitFuncionario = vlr; }

		// dataDesligamento
		public function get dataDesligamento():IDateTime
		{ return _dataDesligamento; }
		public function set dataDesligamento(vlr:IDateTime):void
		{ _dataDesligamento = vlr; }
				    
        // NumInscConsReg
		public function get numInscConsReg():String
		{ return _numInscConsReg; }
		public function set numInscConsReg(vlr:String):void
		{ _numInscConsReg = vlr; }
		
		// CodSiglaConsReg
		public function get codSiglaConsReg():int
		{ return _codSiglaConsReg; }
		public function set codSiglaConsReg(vlr:int):void
		{ _codSiglaConsReg = vlr; }
		
		// SiglaUFConsReg
		public function get siglaUFConsReg():String
		{ return _siglaUFConsReg; }
		public function set siglaUFConsReg(vlr:String):void
		{ _siglaUFConsReg = vlr; }



	}
}