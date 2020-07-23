/*
 * SICOOB
 * 
 * ClienteServico.java(br.com.sicoob.capes.api.negocio.servicos.ClienteServico)
 */
package br.com.sicoob.capes.api.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.api.negocio.vo.ClienteVO;
import br.com.sicoob.capes.comum.negocio.dto.AtualizacaoRiscoClienteDTO;

/**
 * @author Erico.Junior
 *
 */
public interface ClienteServico extends CAPESApiServico {

	/**
	 * Obter por id pessoa.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param idInstituicao
	 *            the id instituicao
	 * @return cliente vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	ClienteVO obterByIdPessoa(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obter por id pessoa legado.
	 * 
	 * @param idPessoaLegado
	 *            the id pessoa legado
	 * @param idInstituicao
	 *            the id instituicao
	 * @return cliente vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	ClienteVO obterByIdPessoaLegado(Integer idPessoaLegado, Integer idInstituicao) throws BancoobException;

	/**
	 * Obter por cpf cnpj.
	 * 
	 * @param cpfCnpj
	 *            the cpf cnpj
	 * @param idInstituicao
	 *            the id instituicao
	 * @return cliente vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	ClienteVO obterByCpfCnpj(String cpfCnpj, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obter por nome.
	 * 
	 * @param nome
	 *            the nome
	 * @param idInstituicao
	 *            the id instituicao
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	List<ClienteVO> obterByNome(String nome, Integer idInstituicao) throws BancoobException;

	/**
	 * Obter por nome apelido.
	 * 
	 * @param nomeApelido
	 *            the nome apelido
	 * @param idInstituicao
	 *            the id instituicao
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	List<ClienteVO> obterByNomeApelido(String nomeApelido, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Consulta as pessoas pelo nome de forma paginada. A pagina 0 corresponde a primeira.
	 * @param nome
	 * @param idInstituicao
	 * @param pagina
	 * @param tamanhoPagina
	 * @return
	 * @throws BancoobException
	 */
	ConsultaDto<ClienteVO> obterByNome(String nome, Integer idInstituicao, int pagina, int tamanhoPagina) 
			throws BancoobException;
	
	/**
	 * Consulta as pessoas pelo nome apelido de forma paginada. A pagina 0 corresponde a primeira.
	 * @param nomeApelido
	 * @param idInstituicao
	 * @param pagina
	 * @param tamanhoPagina
	 * @return
	 * @throws BancoobException
	 */
	ConsultaDto<ClienteVO> obterByNomeApelido(String nomeApelido, Integer idInstituicao, int pagina, int tamanhoPagina) 
			throws BancoobException;

	/**
	 * Atualiza a data de inclusao no SFN
	 * 
	 * @param dto
	 *            preenchido com a data do SFN, o id da pessoa e o id da
	 *            instituicao
	 * @see ClienteVO#getDataInclusaoSFN()
	 * @see ClienteVO#getIdInstituicao()
	 * @see ClienteVO#getIdPessoa()
	 */
	void atualizarDataSFN(ClienteVO dto) throws BancoobException;

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
