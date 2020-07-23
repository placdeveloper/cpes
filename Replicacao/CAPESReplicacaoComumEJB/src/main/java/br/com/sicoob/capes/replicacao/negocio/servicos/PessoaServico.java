/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.Cooperativa;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;
import br.com.sicoob.capes.negocio.entidades.legado.PessoaJuridica;
import br.com.sicoob.capes.negocio.entidades.legado.Telefone;

/**
 * Servi�o utilizado para replica��o de pessoas.
 * 
 * @author Erico.Junior
 */
public interface PessoaServico extends EntidadeReplicavelServico<Pessoa> {

	/**
	 * M�todo para alterar o cpf ou cnpj da pessoa informada.
	 * 
	 * @param pessoa
	 *            a Pessoa a ser alterada.
	 * @param instituicao
	 *            A institui��o.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o na altera��o.
	 */
	void alterarCpfCnpj(Pessoa pessoa, Integer idInstituicao) throws BancoobException;
	
	/**
	 * M�todo para atualizar a foto e assinatura da pessoa informada na base 0001(bancoob)
	 * @param idPessoa
	 * @param idInstituicao
	 * @throws BancoobException
	 */
	void atualizarAssinaturaFotoBancoob(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Realiza a altera��o do e-mail da {@code pessoa}
	 * 
	 * @param pessoa
	 *            A pessoa que ter� seu e-mail alterado
	 * @param idInstituicao
	 *            O ID da institui��o onde a altera��o dever� ser realizada
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o no processo de altera��o
	 */
	void alterarEmail(Pessoa pessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Altera o NumCoopOrigemPessoa e com o numero da cooperativa, e NumPacOrigemPessoa como valor 0, 
	 * na tabela Pessoa da 0001.
	 * @param numPessoa O n�mero da pessoa na base do legado.
	 * @param numCoopOrigem O n�mero da cooperativa de origem
	 * @throws BancoobException
	 */
	void atualizarCooperativaOrigem(Integer numPessoa, Integer numCoopOrigem) throws BancoobException; 

	/**
	 * Atualiza as cooperativas da pessoa informada na base da 0001
	 * @param numPessoaBancoob O numPessoa na base 0001. 
	 * @param cooperativas As cooperativas da pessoa.
	 * @throws BancoobException
	 */
	void atualizarCooperativasPessoa(Integer numPessoaBancoob, List<Cooperativa> cooperativas) throws BancoobException;
	
	/**
	 * Este m�todo visa atualizar o telefone que est� gravado nos atributos da pessoa, por isso est� aqui.
	 * @param pessoa a pessoa que ter� seus dados atualizados.
	 * @param telefone O novo telefone da pessoa.
	 * @param idInstituicao o identificador da institui��o.
	 * @throws BancoobException
	 */
	void atualizarTelefonePessoa(Pessoa pessoa, Telefone telefone, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Atualiza as informa��es de conjuge
	 * @param pessoa
	 * @param idInstituicao
	 * @throws BancoobException
	 */
	void atualizarConjuge(Pessoa pessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Exclui a pessoa sem gerar o hist�rico da exclu��o
	 * @param pessoaLegado
	 * @param idInstituicaoBancoob
	 * @throws BancoobException
	 */
	void excluirSemHistorico(Pessoa pessoaLegado, Integer idInstituicaoBancoob) throws BancoobException;
	

	/**
	 * 
	 * @param pessoa
	 * @throws BancoobException
	 */
	void atualizaCodTipoEmpresa(PessoaJuridica pjTemp) throws BancoobException;
}
