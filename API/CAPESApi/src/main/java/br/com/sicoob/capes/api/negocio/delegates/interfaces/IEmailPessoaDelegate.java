package br.com.sicoob.capes.api.negocio.delegates.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.EmailPessoaVO;

/**
 * 
 * @author paulo.stoppa
 *
 */
public interface IEmailPessoaDelegate extends IAbstractCAPESApiPessoaDelegate<EmailPessoaVO> {

	/**
	 * Metodo que lista todos os e-mails VIGENTES e NAO-VIGENTES por pessoa, instituicao
	 * 
	 * @param idPessoa
	 *            ID pessoa
	 * @param idInstituicao
	 *            ID instituicao
	 * @return Lista de {@link EmailPessoaVO}
	 * @throws BancoobException
	 */
	List<EmailPessoaVO> obterNaoVigentePorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Metodo que inclui um e-mail NAO-VIGENTE, para que este passe por todo fluxo de autorizacao do GED/GFT.
	 * 
	 * @param email
	 *            VO com os dados obrigatorios para inclusao de um e-mail <br>
	 *            Os dados obrigatorios sao:
	 *            <ul>
	 *            <li>Descricao do e-mail {@link EmailPessoaVO#setDescricaoEmail(String)}</li>
	 *            <li>Tipo de email {@link EmailPessoaVO#setCodigoTipoEmail(Short)} <br>
	 * @see TipoEmailEnum</li>
	 *      <li>ID da intituicao</li>
	 *      <li>CPF/CNPJ da pessoa responsavel</li>
	 *      <ul>
	 * @throws BancoobException
	 */
	void incluir(EmailPessoaVO email) throws BancoobException;

	List<EmailPessoaVO> listarEmaisPessoaPorIdPessoaLegadoIdInstituicao(Integer idPessoaLegado, Integer idInstituicao) throws BancoobException;

}