package br.com.sicoob.capes.api.negocio.delegates.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.api.negocio.vo.ClienteVO;
import br.com.sicoob.capes.comum.negocio.dto.AtualizacaoRiscoClienteDTO;

/**
 * 
 * @author paulo.stoppa
 *
 */
public interface IClienteDelegate extends ICAPESApiDelegate {

	/**
	 * Obter por id pessoa instituicao.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param idInstituicao
	 *            the id instituicao
	 * @return cliente vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	ClienteVO obterPorIdPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obter por id pessoa legado instituicao.
	 * 
	 * @param idPessoaLegado
	 *            the id pessoa legado
	 * @param idInstituicao
	 *            the id instituicao
	 * @return cliente vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	ClienteVO obterPorIdPessoaLegadoInstituicao(Integer idPessoaLegado, Integer idInstituicao) throws BancoobException;

	/**
	 * Obter por cpf cnpj instituicao.
	 * 
	 * @param cpfCnpj
	 *            the cpf cnpj
	 * @param idInstituicao
	 *            the id instituicao
	 * @return cliente vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	ClienteVO obterPorCpfCnpjInstituicao(String cpfCnpj, Integer idInstituicao) throws BancoobException;

	/**
	 * Obter por nome instituicao.
	 * 
	 * @param nome
	 *            the nome
	 * @param idInstituicao
	 *            the id instituicao
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	List<ClienteVO> obterPorNomeInstituicao(String nome, Integer idInstituicao) throws BancoobException;

	/**
	 * Obter por nome apelido instituicao.
	 * 
	 * @param nomeApelido
	 *            the nome apelido
	 * @param idInstituicao
	 *            the id instituicao
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	List<ClienteVO> obterPorNomeApelidoInstituicao(String nomeApelido, Integer idInstituicao) throws BancoobException;

	/**
	 * Obter por nome instituicao paginado.
	 * 
	 * @param nome
	 *            the nome
	 * @param idInstituicao
	 *            the id instituicao
	 * @param pagina
	 *            the pagina
	 * @param tamanhoPagina
	 *            the tamanho pagina
	 * @return consulta dto
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	ConsultaDto<ClienteVO> obterPorNomeInstituicaoPaginado(String nome, Integer idInstituicao, int pagina, int tamanhoPagina) throws BancoobException;

	/**
	 * Obter por nome apelido instituicao paginado.
	 * 
	 * @param nomeApelido
	 *            the nome apelido
	 * @param idInstituicao
	 *            the id instituicao
	 * @param pagina
	 *            the pagina
	 * @param tamanhoPagina
	 *            the tamanho pagina
	 * @return consulta dto
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	ConsultaDto<ClienteVO> obterPorNomeApelidoInstituicaoPaginado(String nomeApelido, Integer idInstituicao, int pagina, int tamanhoPagina)
			throws BancoobException;

	/**
	 * Atualiza a data de inclusao no SFN
	 * 
	 * @param vo
	 *            preenchido com a data do SFN, o id da pessoa e o id da instituicao
	 * @see ClienteVO#getDataInclusaoSFN()
	 * @see ClienteVO#getIdInstituicao()
	 * @see ClienteVO#getIdPessoa()
	 */
	void atualizarDataSFN(ClienteVO vo) throws BancoobException;

	/**
	 * Atualizar risco cliente.
	 * 
	 * @param dto
	 *            the dto
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	void atualizarRiscoCliente(AtualizacaoRiscoClienteDTO dto) throws BancoobException;

}