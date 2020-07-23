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
 * Serviço utilizado para replicação de pessoas.
 * 
 * @author Erico.Junior
 */
public interface PessoaServico extends EntidadeReplicavelServico<Pessoa> {

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
	void alterarCpfCnpj(Pessoa pessoa, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Método para atualizar a foto e assinatura da pessoa informada na base 0001(bancoob)
	 * @param idPessoa
	 * @param idInstituicao
	 * @throws BancoobException
	 */
	void atualizarAssinaturaFotoBancoob(Integer idPessoa, Integer idInstituicao) throws BancoobException;

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
	void alterarEmail(Pessoa pessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Altera o NumCoopOrigemPessoa e com o numero da cooperativa, e NumPacOrigemPessoa como valor 0, 
	 * na tabela Pessoa da 0001.
	 * @param numPessoa O número da pessoa na base do legado.
	 * @param numCoopOrigem O número da cooperativa de origem
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
	 * Este método visa atualizar o telefone que está gravado nos atributos da pessoa, por isso está aqui.
	 * @param pessoa a pessoa que terá seus dados atualizados.
	 * @param telefone O novo telefone da pessoa.
	 * @param idInstituicao o identificador da instituição.
	 * @throws BancoobException
	 */
	void atualizarTelefonePessoa(Pessoa pessoa, Telefone telefone, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Atualiza as informações de conjuge
	 * @param pessoa
	 * @param idInstituicao
	 * @throws BancoobException
	 */
	void atualizarConjuge(Pessoa pessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Exclui a pessoa sem gerar o histórico da exclução
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
