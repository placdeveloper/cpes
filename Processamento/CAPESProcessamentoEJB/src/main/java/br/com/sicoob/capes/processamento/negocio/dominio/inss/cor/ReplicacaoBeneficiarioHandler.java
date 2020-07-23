/**
 * 
 */
package br.com.sicoob.capes.processamento.negocio.dominio.inss.cor;

import java.util.Set;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.ReplicacaoCadastroDelegate;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.TransicaoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;
import br.com.sicoob.capes.processamento.negocio.dominio.inss.INSSUtil;
import br.com.sicoob.capes.processamento.negocio.dto.RequisicaoBeneficiarioDTO;

/**
 * @author Erico.Junior
 *
 */
public class ReplicacaoBeneficiarioHandler extends BeneficiarioINSSAbstractHandler {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PessoaFisica processarRequisicao(RequisicaoBeneficiarioDTO requisicao)
			throws BancoobException {

		PessoaFisica pessoaFisica = requisicao.getPessoa();
		Instituicao instituicao = INSSUtil.obterInstituicao(requisicao.getBeneficiario());
		
		if(pessoaFisica != null && !possuiTransicao(instituicao, pessoaFisica)) {
			replicarCadastroBeneficiario(pessoaFisica, instituicao);
		}
		
		if(getProximo() != null) {
			pessoaFisica = getProximo().processarRequisicao(requisicao);
		}
		
		return pessoaFisica;
	}

	/**
	 * Replica o cadastro de uma pessoa f�sica na base da institui��o de destino.
	 * @param pessoa A pessoa que ter� seu cadastro replicado.
	 * @param destino A institui��o na qual o cadastro ser� replicado.
	 * @return a pessoa f�sica que teve seu cadastro replicado.
	 * @throws BancoobException Caso ocorra alguma exce��o.
	 */
	private PessoaFisica replicarCadastroBeneficiario(PessoaFisica pessoa, Instituicao destino) 
			throws BancoobException {
		
		ReplicacaoCadastroDelegate replicacaoDelegate =
				CAPESCadastroFabricaDelegates.getInstance().criarReplicacaoCadastroDelegate();
		return (PessoaFisica) replicacaoDelegate.iniciarRelacionamentoInstituicao(pessoa, destino);
	}
	
	/**
	 * Verifica se a pessoa possui transi��o para a institui��o informada.
	 * @param instituicao A institui��o a ser verificada.
	 * @param pessoa A pessoa 
	 * @return boolean indicando se a pessoa possui transi��o para a institui��o informada ou n�o.
	 */
	private boolean possuiTransicao(Instituicao instituicao, PessoaFisica pessoa) {
		
		Set<TransicaoPessoa> transicoes = pessoa.getTransicoes();
		boolean possui = false;
		
		for (TransicaoPessoa transicao : transicoes) {
			if (instituicao.getIdInstituicao().equals(transicao.getInstituicao().getIdInstituicao())) {
				possui = true;
				break;
			}
		}
		
		return possui;
	}

}
