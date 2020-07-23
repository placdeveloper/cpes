/*
 * SICOOB
 *
 * ProdutorDelegate.java(br.com.sicoob.capes.api.negocio.delegates.ProdutorDelegate)
 */
package br.com.sicoob.capes.api.negocio.delegates.crossContainer;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.IProdutorDelegate;
import br.com.sicoob.capes.api.negocio.servicos.ProdutorServico;
import br.com.sicoob.capes.api.negocio.vo.ProdutorVO;
import br.com.sicoob.capes.api.negocio.vo.TipoBeneficiarioSicorVO;

/**
 * Classe reponsavel por consultas relacionada com o Produtor
 *
 * @author Marcelo.Onofre
 *
 */
public class ProdutorDelegate extends CAPESApiDelegate<ProdutorServico> implements IProdutorDelegate {
	
	/**
	 * 
	 */
	protected ProdutorDelegate() {
	}

	/**
	 * 
	 * @return
	 */
	public static ProdutorDelegate getInstance() {
		return valueOf(ProdutorDelegate.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ProdutorServico localizarServico() {
		return getLocator()
				.localizarProdutorServico();
	}

	/**
	 * Consulta produtor por pessoa, instituicao
	 *
	 * @param idPessoa
	 *            ID da pessoa. Parametro obrigatorio
	 * @param idInstituicao
	 *            ID da instituicao. Parametro obrigatorio
	 * @return {@link ProdutorVO}
	 * @throws BancoobException
	 */
	public ProdutorVO obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao)
			throws BancoobException {
		return getServico().obterByIdPessoa(idPessoa, idInstituicao);
	}

	/**
	 * Consulta produtor pelo ID pessoa legado, instituicao
	 *
	 * @param idPessoaLegado
	 *            ID da pessoa legado. Parametro obrigatorio
	 * @param idInstituicao
	 *            ID da instituicao. Parametro obrigatorio
	 * @return {@link ProdutorVO}
	 * @throws BancoobException
	 */
	public ProdutorVO obterPorPessoaLegadoInstituicao(Integer idPessoaLegado,
			Integer idInstituicao) throws BancoobException {
		return getServico()
				.obterByIdPessoaLegado(idPessoaLegado, idInstituicao);
	}

	/**
	 * Consulta produtor por CPF/CNPJ, instituicao
	 *
	 * @param cpfCnpj
	 *            CPF/CNPJ. Parametro obrigatorio
	 * @param idInstituicao
	 *            ID da instituicao. Parametro obrigatorio
	 * @return {@link ProdutorVO}
	 * @throws BancoobException
	 */
	public ProdutorVO obterPorCpfCnpjInstituicao(String cpfCnpj, Integer idInstituicao)
			throws BancoobException {
		return getServico().obterByCpfCnpj(cpfCnpj, idInstituicao);
	}

	/**
	 * Obtém o produr que é cliente de acordo com o CPF/CNPJ e ID da instituição.
	 * @param cpfCnpj
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public ProdutorVO obterProdutorCliente(String cpfCnpj, Integer idInstituicao) throws BancoobException{
		return getServico().obterProdutorCliente(cpfCnpj, idInstituicao);
	}

	/**
	 * Consulta produtores por nome, instituicao
	 *
	 * @param nome
	 * 			  Parametro obrigatario e maior do que 3 caracteres
	 * @param idInstituicao
	 *            ID da instituicao. Parametro obrigatorio
	 * @return lista de {@link ProdutorVO}
	 * @throws BancoobException
	 */
	public List<ProdutorVO> obterPorNomeInstituicao(String nome, Integer idInstituicao)
			throws BancoobException {
		return getServico().obterByNome(nome, idInstituicao);
	}

	/**
	 * Consulta produtores por apelido, instituicao
	 *
	 * @param nomeApelido
	 * 			  Parametro obrigatorio e maior do que 3 caracteres
	 * @param idInstituicao
	 *            ID da instituicao. Parametro obrigatorio
	 * @return lista de {@link ProdutorVO}
	 * @throws BancoobException
	 */
	public List<ProdutorVO> obterPorNomeApelidoInstituicao(String nomeApelido,
			Integer idInstituicao) throws BancoobException {
		return getServico().obterByNomeApelido(nomeApelido, idInstituicao);
	}

	/**
	 * Consulta por nome, ID da intituicao de forma paginada
	 *
	 * @param nome
	 * 		Nome da pessoa a ser pesquisado. Parametro obrigatario e maior do que 3 caracteres
	 * @param idInstituicao
	 * 		ID da instituicao. Parametro obrigatorio
	 * @param pagina
	 * 		Numero da pagina, maior ou igual a 0 (zero).
	 * @param tamanhoPagina
	 * 		Tamanho de registros por pagina. Deve ser maior que 1 (um)
	 * @return lista de {@link ProdutorVO}
	 * @throws BancoobException
	 */
	public ConsultaDto<ProdutorVO> obterPorNomeInstituicaoPaginado(String nome,
			Integer idInstituicao, int pagina, int tamanhoPagina)
			throws BancoobException {
		return getServico().obterByNome(nome, idInstituicao, pagina,
				tamanhoPagina);
	}

	/**
	 * Consulta por apelido, ID da intituicao de forma paginada.
	 *
	 * @param nomeApelido
	 * 		Apelido da pessoa a ser pesquisado. Parametro obrigatorio e maior do que 3 caracteres
	 * @param idInstituicao
	 * 		ID da instituicao. Parametro obrigatorio
	 * @param pagina
	 * 		Numero da pagina, maior ou igual a 0 (zero).
	 * @param tamanhoPagina
	 * 		Tamanho de registros por pagina. Deve ser maior que 1 (um)
	 * @return lista de {@link ProdutorVO}
	 * @throws BancoobException
	 */
	public ConsultaDto<ProdutorVO> obterPorNomeApelidoInstituicaoPaginado(String nomeApelido,
			Integer idInstituicao, int pagina, int tamanhoPagina)
			throws BancoobException {
		return getServico().obterByNomeApelido(nomeApelido, idInstituicao,
				pagina, tamanhoPagina);
	}


	/**
	 * Consulta os produtores pelo nome apelido de forma paginada, filtrando por categoria ativa. A pagina 0 corresponde a primeira.
	 * @param nomeApelido
	 * @param idInstituicao
	 * @param pagina
	 * @param tamanhoPagina
	 * @param filtrarCategoriaAtiva
	 * @return
	 * @throws BancoobException
	 */
	public ConsultaDto<ProdutorVO> obterPorNomeApelido(String nomeApelido, Integer idInstituicao, int pagina, int tamanhoPagina, Boolean filtrarCategoriaAtiva) throws BancoobException{
		return getServico().obterByNomeApelido(nomeApelido, idInstituicao, pagina, tamanhoPagina, filtrarCategoriaAtiva);
	}

	/**
	 * Consulta os produtores pelo nome e categoria ativa de forma paginada. A pagina 0 corresponde a primeira.
	 * @param nome
	 * @param idInstituicao
	 * @param pagina
	 * @param tamanhoPagina
	 * @return
	 * @throws BancoobException
	 */
	public ConsultaDto<ProdutorVO> obterPorNome(String nome, Integer idInstituicao, int pagina, int tamanhoPagina, Boolean filtrarCategoriaAtiva) throws BancoobException{
		return getServico().obterByNome(nome, idInstituicao, pagina, tamanhoPagina, filtrarCategoriaAtiva);
	}
	
	/**
	 * Obtém a lista de beneficiários do SICOR
	 * @return
	 * @throws BancoobException
	 */
	public List<TipoBeneficiarioSicorVO> obterListaTipoBeneficiariosSicor() throws BancoobException {
		return getServico().obterListaTipoBeneficiariosSicor();
	}
}
