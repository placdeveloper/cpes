/*
 * SICOOB
 *
 * ProdutorServico.java(br.com.sicoob.capes.api.negocio.servicos.ProdutorServico)
 */
package br.com.sicoob.capes.api.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.api.negocio.vo.ProdutorVO;
import br.com.sicoob.capes.api.negocio.vo.TipoBeneficiarioSicorVO;

/**
 * @author Marcelo.Onofre
 *
 */
public interface ProdutorServico extends CAPESApiServico {

	/**
	 * Obtem o produtor de acordo com o ID da pessoa e ID da instituição
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	ProdutorVO obterByIdPessoa(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obtem o produtor de acordo com o ID da pessoa legado e ID da instituição
	 * @param idPessoaLegado
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	ProdutorVO obterByIdPessoaLegado(Integer idPessoaLegado, Integer idInstituicao) throws BancoobException;

	/**
	 * Obtem o produtor de acordo com o CPF/CNPJ e ID da instituição
	 * @param cpfCnpj
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	ProdutorVO obterByCpfCnpj(String cpfCnpj, Integer idInstituicao) throws BancoobException;

	/**
	 * Obtém o produr que é cliente de acordo com o CPF/CNPJ e ID da instituição.
	 * @param cpfCnpj
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	ProdutorVO obterProdutorCliente(String cpfCnpj, Integer idInstituicao) throws BancoobException;

	/**
	 * Obtem os produtores de acordo com o nome da pessoa e ID da instituição
	 * @param nome
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<ProdutorVO> obterByNome(String nome, Integer idInstituicao) throws BancoobException;

	/**
	 * Obtem os produtores de acordo com o apleido da pessoa e ID da instituição
	 * @param nomeApelido
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<ProdutorVO> obterByNomeApelido(String nomeApelido, Integer idInstituicao) throws BancoobException;

	/**
	 * Consulta os produtores pelo nome de forma paginada. A pagina 0 corresponde a primeira.
	 * @param nome
	 * @param idInstituicao
	 * @param pagina
	 * @param tamanhoPagina
	 * @return
	 * @throws BancoobException
	 */
	ConsultaDto<ProdutorVO> obterByNome(String nome, Integer idInstituicao, int pagina, int tamanhoPagina) throws BancoobException;

	/**
	 * Consulta os produtores pelo nome e categoria ativa de forma paginada. A pagina 0 corresponde a primeira.
	 * @param nome
	 * @param idInstituicao
	 * @param pagina
	 * @param tamanhoPagina
	 * @return
	 * @throws BancoobException
	 */
	ConsultaDto<ProdutorVO> obterByNome(String nome, Integer idInstituicao, int pagina, int tamanhoPagina, Boolean filtrarCategoriaAtiva) throws BancoobException;

	/**
	 * Consulta os produtores pelo nome apelido de forma paginada. A pagina 0 corresponde a primeira.
	 * @param nomeApelido
	 * @param idInstituicao
	 * @param pagina
	 * @param tamanhoPagina
	 * @return
	 * @throws BancoobException
	 */
	ConsultaDto<ProdutorVO> obterByNomeApelido(String nomeApelido, Integer idInstituicao, int pagina, int tamanhoPagina) throws BancoobException;

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
	ConsultaDto<ProdutorVO> obterByNomeApelido(String nomeApelido, Integer idInstituicao, int pagina, int tamanhoPagina, Boolean filtrarCategoriaAtiva) throws BancoobException;
	
	/**
	 * Obtém a lista dos Tipos de beneficiários do SICOR.
	 * @return
	 * @throws BancoobException
	 */
	List<TipoBeneficiarioSicorVO> obterListaTipoBeneficiariosSicor() throws BancoobException;
}
