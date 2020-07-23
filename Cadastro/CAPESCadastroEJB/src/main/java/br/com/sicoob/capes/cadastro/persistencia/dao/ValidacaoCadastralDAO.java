package br.com.sicoob.capes.cadastro.persistencia.dao;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.ValidacaoCadastral;

/**
 * A Interface ValidacaoCadastralDAO.
 */
public interface ValidacaoCadastralDAO extends CAPESCadastroCrudDaoIF<ValidacaoCadastral> {

	/**
	 * Atualiza a data da ultima validacao cadastral de uma pessoa.
	 * 
	 * @param idPessoaCompartilhamento
	 *            <b>Opcional</b>. O ID da pessoa cuja data de validacao se
	 *            deseja atualizar
	 * @param data
	 *            <b>Opcional</b>. A nova data de validacao
	 * @throws BancoobException
	 */
	void atualizarDataUltimaValidacao(Long idPessoaCompartilhamento, DateTimeDB data) throws BancoobException;

	/**
	 * Serviço utilizado em fechamento SWS. 
	 * Verifica se a há cadastrados sem renovar o a mais de 36 meses.
	 * E lança uma anotaçao no cadastro do cliente do tipo 519.
	 * @throws BancoobException
	 */
	void verificarPendenciasPrazoRenovacaoCadastro() throws BancoobException;
	
}