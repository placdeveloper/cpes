/*
 * SICOOB
 *
 * ProdutorDelegate.java(br.com.sicoob.capes.api.negocio.delegates.ProdutorDelegate)
 */
package br.com.sicoob.capes.api.negocio.delegates.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.api.negocio.vo.ProdutorVO;
import br.com.sicoob.capes.api.negocio.vo.TipoBeneficiarioSicorVO;

/**
 * interfacee reponsavel por consultas relacionada com o Produtor
 *
 * @author Marcelo.Onofre
 *
 */
public interface IProdutorDelegate extends ICAPESApiDelegate {

	/**
	 * Consulta produtor por pessoa, instituicao
	 *
	 * @param idPessoa
	 *            ID da pessoa. Parametro obrigatorio
	 * @param idInstituicao
	 *            ID da instituicao. Parametro obrigatorio
	 * @return;@link ProdutorVO}
	 * @throws BancoobException
	 */
	ProdutorVO obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Consulta produtor pelo ID pessoa legado, instituicao
	 *
	 * @param idPessoaLegado
	 *            ID da pessoa legado. Parametro obrigatorio
	 * @param idInstituicao
	 *            ID da instituicao. Parametro obrigatorio
	 * @return;@link ProdutorVO}
	 * @throws BancoobException
	 */
	ProdutorVO obterPorPessoaLegadoInstituicao(Integer idPessoaLegado, Integer idInstituicao) throws BancoobException;

	/**
	 * Consulta produtor por CPF/CNPJ, instituicao
	 *
	 * @param cpfCnpj
	 *            CPF/CNPJ. Parametro obrigatorio
	 * @param idInstituicao
	 *            ID da instituicao. Parametro obrigatorio
	 * @return;@link ProdutorVO}
	 * @throws BancoobException
	 */
	ProdutorVO obterPorCpfCnpjInstituicao(String cpfCnpj, Integer idInstituicao) throws BancoobException;

	/**
	 * Obtém o produr que é cliente de acordo com o CPF/CNPJ e ID da instituição.
	 * 
	 * @param cpfCnpj
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	ProdutorVO obterProdutorCliente(String cpfCnpj, Integer idInstituicao) throws BancoobException;

	/**
	 * Consulta produtores por nome, instituicao
	 *
	 * @param nome
	 *            Parametro obrigatario e maior do que 3 caracteres
	 * @param idInstituicao
	 *            ID da instituicao. Parametro obrigatorio
	 * @return lista de;@link ProdutorVO}
	 * @throws BancoobException
	 */
	List<ProdutorVO> obterPorNomeInstituicao(String nome, Integer idInstituicao) throws BancoobException;

	/**
	 * Consulta produtores por apelido, instituicao
	 *
	 * @param nomeApelido
	 *            Parametro obrigatorio e maior do que 3 caracteres
	 * @param idInstituicao
	 *            ID da instituicao. Parametro obrigatorio
	 * @return lista de;@link ProdutorVO}
	 * @throws BancoobException
	 */
	List<ProdutorVO> obterPorNomeApelidoInstituicao(String nomeApelido, Integer idInstituicao) throws BancoobException;

	/**
	 * Consulta por nome, ID da intituicao de forma paginada
	 *
	 * @param nome
	 *            Nome da pessoa a ser pesquisado. Parametro obrigatario e maior do que 3 caracteres
	 * @param idInstituicao
	 *            ID da instituicao. Parametro obrigatorio
	 * @param pagina
	 *            Numero da pagina, maior ou igual a 0 (zero).
	 * @param tamanhoPagina
	 *            Tamanho de registros por pagina. Deve ser maior que 1 (um)
	 * @return lista de;@link ProdutorVO}
	 * @throws BancoobException
	 */
	ConsultaDto<ProdutorVO> obterPorNomeInstituicaoPaginado(String nome, Integer idInstituicao, int pagina, int tamanhoPagina)
			throws BancoobException;

	/**
	 * Consulta por apelido, ID da intituicao de forma paginada.
	 *
	 * @param nomeApelido
	 *            Apelido da pessoa a ser pesquisado. Parametro obrigatorio e maior do que 3 caracteres
	 * @param idInstituicao
	 *            ID da instituicao. Parametro obrigatorio
	 * @param pagina
	 *            Numero da pagina, maior ou igual a 0 (zero).
	 * @param tamanhoPagina
	 *            Tamanho de registros por pagina. Deve ser maior que 1 (um)
	 * @return lista de;@link ProdutorVO}
	 * @throws BancoobException
	 */
	ConsultaDto<ProdutorVO> obterPorNomeApelidoInstituicaoPaginado(String nomeApelido, Integer idInstituicao, int pagina, int tamanhoPagina)
			throws BancoobException;

	/**
	 * Consulta os produtores pelo nome apelido de forma paginada, filtrando por categoria ativa. A pagina 0 corresponde a primeira.
	 * 
	 * @param nomeApelido
	 * @param idInstituicao
	 * @param pagina
	 * @param tamanhoPagina
	 * @param filtrarCategoriaAtiva
	 * @return
	 * @throws BancoobException
	 */
	ConsultaDto<ProdutorVO> obterPorNomeApelido(String nomeApelido, Integer idInstituicao, int pagina, int tamanhoPagina,
			Boolean filtrarCategoriaAtiva) throws BancoobException;

	/**
	 * Consulta os produtores pelo nome e categoria ativa de forma paginada. A pagina 0 corresponde a primeira.
	 * 
	 * @param nome
	 * @param idInstituicao
	 * @param pagina
	 * @param tamanhoPagina
	 * @return
	 * @throws BancoobException
	 */
	ConsultaDto<ProdutorVO> obterPorNome(String nome, Integer idInstituicao, int pagina, int tamanhoPagina, Boolean filtrarCategoriaAtiva)
			throws BancoobException;

	/**
	 * Obtém a lista de beneficiários do SICOR
	 * 
	 * @return
	 * @throws BancoobException
	 */
	List<TipoBeneficiarioSicorVO> obterListaTipoBeneficiariosSicor() throws BancoobException;

}
