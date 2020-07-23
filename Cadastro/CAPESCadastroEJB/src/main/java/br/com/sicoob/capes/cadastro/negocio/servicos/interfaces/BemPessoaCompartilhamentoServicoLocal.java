package br.com.sicoob.capes.cadastro.negocio.servicos.interfaces;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.BemPessoaCompartilhamentoServico;
import br.com.sicoob.capes.negocio.entidades.bem.Bem;
import br.com.sicoob.capes.negocio.entidades.bem.BemPessoaCompartilhamento;

/**
 * A interface para os serviços locais do {@link BemPessoaCompartilhamento}
 * 
 * @author Bruno.Carneiro
 */
public interface BemPessoaCompartilhamentoServicoLocal extends BemPessoaCompartilhamentoServico {
	
	/**
	 * Faz a exclusão dos bens marcados para exclusão.
	 * 
	 * @param bem
	 * @throws BancoobException
	 */
	void excluirCompartilhamentosMarcados(Bem bem) throws BancoobException;
	
	/**
	 * Desmarca os responsáveis pelo bem após a aprovação
	 * @param bem
	 * @throws BancoobException
	 */
	void desmarcarResponsaveisBem(Long idBem) throws BancoobException;
	
	/**
	 * Inclui um BemPessoaCompartilhamento sem autorização.
	 * 
	 * @param objeto
	 * @return
	 * @throws BancoobException
	 */
	BemPessoaCompartilhamento incluirSemAutorizacao(BemPessoaCompartilhamento objeto) throws BancoobException;
	
	/**
	 * Obtém o compartilhamento por idbem e idPessoaCompartilhamento
	 * @param idBem
	 * @param idPessoaCompartilhamento
	 * @return
	 * @throws BancoobException
	 */
	BemPessoaCompartilhamento obterBemPessoaCompartilhamentoInternoIdPessoaCompartilhamento(Long idPessoaCompartilhamento) throws BancoobException;
	
}