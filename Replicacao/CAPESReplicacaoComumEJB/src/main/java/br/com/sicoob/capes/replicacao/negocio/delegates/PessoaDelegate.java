/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.Cooperativa;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;
import br.com.sicoob.capes.negocio.entidades.legado.PessoaJuridica;
import br.com.sicoob.capes.replicacao.negocio.servicos.PessoaServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.locator.CAPESReplicacaoComumServiceLocator;

/**
 * Delegate para os serviços de pessoas no projeto de replicação.
 * 
 * @author Erico.Junior
 */
public class PessoaDelegate extends EntidadeReplicavelDelegate<Pessoa, PessoaServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected PessoaServico localizarServico() {
		return CAPESReplicacaoComumServiceLocator.getInstance().localizarPessoaServico();
	}

	/**
	 * Método para alterar o cpf ou cnpj da pessoa informada.
	 * 
	 * @param pessoa
	 *            a Pessoa a ser alterada.
	 * @param instituicao
	 *            A instituição.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção na alteração.
	 */
	public void alterarCpfCnpj(Pessoa pessoa, Integer idInstituicao) throws BancoobException {
		getServico().alterarCpfCnpj(pessoa, idInstituicao);
	}	
	
	/**
	 * Método para atualizar a foto e assinatura da pessoa informada na base 0001(bancoob)
	 * @param idPessoa
	 * @param idInstituicao
	 * @throws BancoobException
	 */
	public void atualizarAssinaturaFotoBancoob(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		getServico().atualizarAssinaturaFotoBancoob(idPessoa, idInstituicao);
	}	
	
	/**
	 * Realiza a alteração do e-mail da {@code pessoa}
	 * 
	 * @param pessoa
	 *            A pessoa que terá seu e-mail alterado
	 * @param idInstituicao
	 *            O ID da instituição onde a alteração deverá ser realizada
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção no processo de alteração
	 */
	public void alterarEmail(Pessoa pessoa, Integer idInstituicao) throws BancoobException {
		getServico().alterarEmail(pessoa, idInstituicao);
	}
	
	/**
	 * Altera o NumCoopOrigemPessoa e com o numero da cooperativa, e NumPacOrigemPessoa como valor 0, 
	 * na tabela Pessoa da 0001.
	 * @param numPessoa O número da pessoa na base do legado.
	 * @param numCoopOrigem O número da cooperativa de origem
	 * @throws BancoobException
	 */
	public void atualizarCooperativaOrigem(Integer numPessoa, Integer numCoopOrigem) throws BancoobException {
		getServico().atualizarCooperativaOrigem(numPessoa, numCoopOrigem);
	}
	
	/**
	 * Atualiza as cooperativas da pessoa informada na base da 0001
	 * @param numPessoaBancoob O numPessoa na base 0001. 
	 * @param cooperativas As cooperativas da pessoa.
	 * @throws BancoobException
	 */
	public void atualizarCooperativasPessoa(Integer numPessoaBancoob, List<Cooperativa> cooperativas) 
			throws BancoobException {
		getServico().atualizarCooperativasPessoa(numPessoaBancoob, cooperativas);
	}

	/**
	 * Exclui a pessoa sem gerar o histórico da exclução
	 * @param pessoaLegado
	 * @param idInstituicaoBancoob
	 * @throws BancoobException 
	 */
	public void excluirSemHistorico(br.com.sicoob.capes.negocio.entidades.legado.Pessoa pessoaLegado, Integer idInstituicaoBancoob) throws BancoobException {
		getServico().excluirSemHistorico(pessoaLegado, idInstituicaoBancoob);
	}
	
	/**
	 * Atualiza o Código do Tipo de Empresa pertencente ao uma Pessoa Juridica
	 * @param pessoaLegado
	 * @throws BancoobException
	 */
	public void atualizaCodTipoEmpresa(PessoaJuridica pjTemp) throws BancoobException {
		getServico().atualizaCodTipoEmpresa(pjTemp);
	}
}
