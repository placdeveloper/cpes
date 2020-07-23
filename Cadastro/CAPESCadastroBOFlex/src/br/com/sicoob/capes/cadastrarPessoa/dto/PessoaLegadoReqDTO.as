package br.com.sicoob.capes.cadastrarPessoa.dto
{
	import br.com.bancoob.dto.RequisicaoDTO;
	import br.com.sicoob.capes.comum.vo.PessoaLegadoVO;
	
	import flash.net.registerClassAlias;

	registerClassAlias("br.com.bancoob.sisbr.negocio.dto.PessoaLegadoReqDTO",
					    PessoaLegadoReqDTO);

	public class PessoaLegadoReqDTO extends RequisicaoDTO
	{
		private var _dados_pessoa:PessoaLegadoVO; 
		
		public function get DadosPessoa():PessoaLegadoVO { 
		      return _dados_pessoa; 
		} 
		public function set DadosPessoa(vlr:PessoaLegadoVO):void {
		      _dados_pessoa = vlr;
		}		
	}
}