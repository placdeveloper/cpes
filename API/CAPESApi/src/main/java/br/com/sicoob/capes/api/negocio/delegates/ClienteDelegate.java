/*
 * SICOOB
 * 
 * ClienteDelegate.java(br.com.sicoob.capes.api.negocio.delegates.ClienteDelegate)
 */
package br.com.sicoob.capes.api.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.IClienteDelegate;
import br.com.sicoob.capes.api.negocio.servicos.ClienteServico;
import br.com.sicoob.capes.api.negocio.servicos.locator.CAPESApiServiceLocator;
import br.com.sicoob.capes.api.negocio.vo.ClienteVO;
import br.com.sicoob.capes.comum.negocio.dto.AtualizacaoRiscoClienteDTO;

/**
 * @author Erico.Junior
 * 
 */
public class ClienteDelegate extends CAPESApiDelegate<ClienteServico> implements IClienteDelegate {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected ClienteServico localizarServico() {
		return CAPESApiServiceLocator.getInstance().localizarClienteServico();
	}

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
	public ClienteVO obterPorIdPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return getServico().obterByIdPessoa(idPessoa, idInstituicao);
	}

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
	public ClienteVO obterPorIdPessoaLegadoInstituicao(Integer idPessoaLegado,
			Integer idInstituicao) throws BancoobException {
		return getServico().obterByIdPessoaLegado(idPessoaLegado, idInstituicao);
	}

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
	public ClienteVO obterPorCpfCnpjInstituicao(String cpfCnpj, Integer idInstituicao) throws BancoobException {
		return getServico().obterByCpfCnpj(cpfCnpj, idInstituicao);
	}

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
	public List<ClienteVO> obterPorNomeInstituicao(String nome, Integer idInstituicao) throws BancoobException {
		return getServico().obterByNome(nome, idInstituicao);
	}

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
	public List<ClienteVO> obterPorNomeApelidoInstituicao(String nomeApelido, Integer idInstituicao) throws BancoobException {
		return getServico().obterByNomeApelido(nomeApelido, idInstituicao);
	}

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
	public ConsultaDto<ClienteVO> obterPorNomeInstituicaoPaginado(String nome, Integer idInstituicao, int pagina, int tamanhoPagina) throws BancoobException {
		return getServico().obterByNome(nome, idInstituicao, pagina, tamanhoPagina);
	}

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
	public ConsultaDto<ClienteVO> obterPorNomeApelidoInstituicaoPaginado(String nomeApelido, Integer idInstituicao, int pagina, int tamanhoPagina) throws BancoobException {
		return getServico().obterByNomeApelido(nomeApelido, idInstituicao, pagina, tamanhoPagina);
	}
	
	/**
	 * Atualiza a data de inclusao no SFN
	 * 
	 * @param vo
	 *            preenchido com a data do SFN, o id da pessoa e o id da instituicao
	 * @see ClienteVO#getDataInclusaoSFN()
	 * @see ClienteVO#getIdInstituicao()
	 * @see ClienteVO#getIdPessoa()
	 */
	public void atualizarDataSFN(ClienteVO vo) throws BancoobException {
		getServico().atualizarDataSFN(vo);
	}
	
	/**
	 * Atualizar risco cliente.
	 * 
	 * @param dto
	 *            the dto
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public void atualizarRiscoCliente(AtualizacaoRiscoClienteDTO dto) throws BancoobException {
		getServico().atualizarRiscoCliente(dto);
	}
}