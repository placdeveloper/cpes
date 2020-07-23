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
	 * Replica o cadastro de uma pessoa física na base da instituição de destino.
	 * @param pessoa A pessoa que terá seu cadastro replicado.
	 * @param destino A instituição na qual o cadastro será replicado.
	 * @return a pessoa física que teve seu cadastro replicado.
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	private PessoaFisica replicarCadastroBeneficiario(PessoaFisica pessoa, Instituicao destino) 
			throws BancoobException {
		
		ReplicacaoCadastroDelegate replicacaoDelegate =
				CAPESCadastroFabricaDelegates.getInstance().criarReplicacaoCadastroDelegate();
		return (PessoaFisica) replicacaoDelegate.iniciarRelacionamentoInstituicao(pessoa, destino);
	}
	
	/**
	 * Verifica se a pessoa possui transição para a instituição informada.
	 * @param instituicao A instituição a ser verificada.
	 * @param pessoa A pessoa 
	 * @return boolean indicando se a pessoa possui transição para a instituição informada ou não.
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
