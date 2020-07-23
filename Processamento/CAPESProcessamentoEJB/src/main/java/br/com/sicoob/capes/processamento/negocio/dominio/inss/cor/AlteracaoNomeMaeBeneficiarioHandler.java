/**
 * 
 */
package br.com.sicoob.capes.processamento.negocio.dominio.inss.cor;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;
import br.com.sicoob.capes.processamento.negocio.dto.BeneficiarioDTO;
import br.com.sicoob.capes.processamento.negocio.dto.RequisicaoBeneficiarioDTO;

/**
 * Atualiza o nome da mãe se ele não estiver preenchido no cadastro.
 * @author Erico.Junior
 */
public class AlteracaoNomeMaeBeneficiarioHandler extends BeneficiarioINSSAbstractHandler {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PessoaFisica processarRequisicao(RequisicaoBeneficiarioDTO requisicao)
			throws BancoobException {
		
		PessoaFisica pessoaFisica = requisicao.getPessoa();
		
		if(pessoaFisica != null && !isNomeMaePreenchido(pessoaFisica)) {
			BeneficiarioDTO dto = requisicao.getBeneficiario();
			getDelegate().alterarNomeMae(pessoaFisica, dto.getNomeMae());
		}
		
		if(getProximo() != null) {
			pessoaFisica = getProximo().processarRequisicao(requisicao);
		}
		
		return pessoaFisica;
	}
	
	/**
	 * Verifica se eh nome mae preenchido.
	 *
	 * @param pessoaFisica o valor de pessoa fisica
	 * @return {@code true}, se for nome mae preenchido
	 */
	private boolean isNomeMaePreenchido(PessoaFisica pessoaFisica) {

		String nomeMae = pessoaFisica.getNomeMae();
		return nomeMae != null && StringUtils.isNotBlank(nomeMae.trim());
	}
	
}
