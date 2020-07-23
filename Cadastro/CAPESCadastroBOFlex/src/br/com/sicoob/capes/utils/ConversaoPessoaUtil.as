package br.com.sicoob.capes.utils
{
	import br.com.sicoob.capes.comum.vo.entidades.InstituicaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PerfilCadastroVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaFisicaVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaJuridicaVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoPessoaVO;
	import br.com.sicoob.capes.comum.vo.entidades.TransicaoPessoaVO;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	
	import mx.collections.ArrayCollection;
	
	public class ConversaoPessoaUtil
	{
		
		public static const TIPO_PESSOA_FISICA: int = 0;
		public static const TIPO_PESSOA_JURIDICA: int = 1;		
		
 		public static function converterVOParaPessoaCompartilhamento(proxy : PessoaPlataformaVO) : PessoaCompartilhamentoVO {
			var pessoaCompartilhamento : PessoaCompartilhamentoVO = null;
			
			if (proxy) {
				if (TIPO_PESSOA_FISICA == proxy.codTipoPessoa) {
					pessoaCompartilhamento = new PessoaFisicaVO();
				} else if (TIPO_PESSOA_JURIDICA == proxy.codTipoPessoa) {
					pessoaCompartilhamento = new PessoaJuridicaVO();
				}
				
				if (pessoaCompartilhamento) {
					
					var perfilCadastro:PerfilCadastroVO = new PerfilCadastroVO();
					perfilCadastro.codigo = proxy.codigoPerfilCadastro;

					var transicao : TransicaoPessoaVO = new TransicaoPessoaVO();
					transicao.idPessoaLegado = proxy.idPessoaLegado;
					transicao.instituicao = new InstituicaoVO();
					transicao.instituicao.idInstituicao = proxy.idInstituicao;
					transicao.instituicao.idUnidadeInst = proxy.idUnidadeInst;

					var pessoa: PessoaVO = new PessoaVO();
					pessoa.idPessoa = proxy.idPessoa;
					pessoa.cpfCnpj = proxy.cpfCnpj;
					pessoa.tipoPessoa = new TipoPessoaVO();
					pessoa.tipoPessoa.codTipoPessoa = proxy.codTipoPessoa;
					pessoa.tipoPessoa.descTipoPessoa = proxy.descTipoPessoa;
					pessoa.compartilhamentos = new ArrayCollection();
					pessoa.compartilhamentos.addItem(pessoaCompartilhamento);

					pessoaCompartilhamento.idPessoaCompartilhamento = proxy.idCompartilhamento;
					pessoaCompartilhamento.nomePessoa = proxy.nomePessoa;
					pessoaCompartilhamento.nomeApelido = proxy.nomeApelido;
					pessoaCompartilhamento.nomeCompleto = proxy.nomeCompleto;
					pessoaCompartilhamento.transicoes = new ArrayCollection();
					pessoaCompartilhamento.transicoes.addItem(transicao);
					pessoaCompartilhamento.pessoa = pessoa;
					pessoaCompartilhamento.perfilCadastro = perfilCadastro;
					
				}
			}
			
			return pessoaCompartilhamento;
		}

	}
}