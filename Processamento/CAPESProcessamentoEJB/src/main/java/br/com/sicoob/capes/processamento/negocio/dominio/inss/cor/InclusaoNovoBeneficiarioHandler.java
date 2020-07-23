/**
 * 
 */
package br.com.sicoob.capes.processamento.negocio.dominio.inss.cor;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;
import br.com.sicoob.capes.processamento.negocio.dominio.inss.INSSUtil;
import br.com.sicoob.capes.processamento.negocio.dto.BeneficiarioDTO;
import br.com.sicoob.capes.processamento.negocio.dto.RequisicaoBeneficiarioDTO;
import br.com.sicoob.capes.processamento.util.BeneficiarioUtil;

/**
 * @author Erico.Junior
 *
 */
public class InclusaoNovoBeneficiarioHandler extends BeneficiarioINSSAbstractHandler {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PessoaFisica processarRequisicao(RequisicaoBeneficiarioDTO requisicao) throws BancoobException {

		PessoaFisica pessoaFisica = requisicao.getPessoa();
		
		if(pessoaFisica == null) {
			pessoaFisica = incluirPessoa(requisicao);
		} else if(getProximo() != null) {
			pessoaFisica = getProximo().processarRequisicao(requisicao);
		}
		
		return pessoaFisica;
	}
	
	/**
	 * Incluir pessoa.
	 *
	 * @param requisicao o valor de requisicao
	 * @return PessoaFisica
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private PessoaFisica incluirPessoa(RequisicaoBeneficiarioDTO requisicao) throws BancoobException {
		
		DateTimeDB dataProduto = requisicao.getDataProduto();
		BeneficiarioDTO dto = requisicao.getBeneficiario();

		PessoaFisica novaPessoa = BeneficiarioUtil.obterPessoaFisica(dto, dataProduto);
		return (PessoaFisica) getDelegate().incluir(novaPessoa, dataProduto, INSSUtil.obterInstituicao(dto));
	}

}
