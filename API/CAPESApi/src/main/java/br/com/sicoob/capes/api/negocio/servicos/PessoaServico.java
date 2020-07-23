/*
 * SICOOB
 * 
 * PessoaServico.java(br.com.sicoob.capes.api.negocio.servicos.PessoaServico)
 */
package br.com.sicoob.capes.api.negocio.servicos;

import java.util.List;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.api.negocio.vo.PessoaVO;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.comum.negocio.vo.PessoaBasicaVO;
import br.com.sicoob.capes.comum.negocio.vo.PessoaPlataformaVO;
import br.com.sicoob.capes.comum.negocio.vo.ProcurarPessoaExternoVO;

/**
 * @author Lucas.Borges
 */
public interface PessoaServico extends CAPESApiServico {

	/**
	 * Obter por id pessoa.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param idInstituicao
	 *            the id instituicao
	 * @return pessoa vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	PessoaVO obterByIdPessoa(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obter por id pessoa legado.
	 * 
	 * @param idPessoaLegado
	 *            the id pessoa legado
	 * @param idInstituicao
	 *            the id instituicao
	 * @return pessoa vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	PessoaVO obterByIdPessoaLegado(Integer idPessoaLegado, Integer idInstituicao) throws BancoobException;

	/**
	 * Obter por cpf cnpj.
	 * 
	 * @param cpfCnpj
	 *            the cpf cnpj
	 * @param idInstituicao
	 *            the id instituicao
	 * @return pessoa vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	PessoaVO obterByCpfCnpj(String cpfCnpj, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obter por cpf cnpj.
	 * 
	 * @param cpfCnpj
	 *            the cpf cnpj
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	List<PessoaVO> obterByCpfCnpj(String cpfCnpj) throws BancoobException;
	
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
	List<PessoaVO> obterByNome(String nome, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obter por nome.
	 * 
	 * @param nome
	 *            the nome
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	List<PessoaVO> obterByNome(String nome) throws BancoobException;

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
	List<PessoaVO> obterByNomeApelido(String nomeApelido, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Consulta as pessoas pelo nome de forma paginada. A pagina 0 corresponde a primeira.
	 * @param nome
	 * @param idInstituicao
	 * @param pagina
	 * @param tamanhoPagina
	 * @return
	 * @throws BancoobException
	 */
	ConsultaDto<PessoaVO> obterByNome(String nome, Integer idInstituicao, int pagina, int tamanhoPagina) throws BancoobException;
	
	/**
	 * Consulta as pessoas pelo nome de forma paginada. A pagina 0 corresponde a primeira.
	 * @param nome
	 * @param pagina
	 * @param tamanhoPagina
	 * @return
	 * @throws BancoobException
	 */
	ConsultaDto<PessoaVO> obterByNome(String nome, int pagina, int tamanhoPagina) throws BancoobException;
	
	/**
	 * Consulta as pessoas pelo nome apelido de forma paginada. A pagina 0 corresponde a primeira.
	 * @param nomeApelido
	 * @param idInstituicao
	 * @param pagina
	 * @param tamanhoPagina
	 * @return
	 * @throws BancoobException
	 */
	ConsultaDto<PessoaVO> obterByNomeApelido(String nomeApelido, Integer idInstituicao, int pagina, int tamanhoPagina) throws BancoobException;
	
	/**
	 * Consulta por nome, ID da intituição e tipo de pessoa. Ver {@link TipoPessoaEnum}<br>
	 * 
	 * @param nome
	 * 		Nome da pessoa a ser pesquisado. Parâmetro obrigatório e maior do que 3 caracteres
	 * @param idInstituicao
	 * 		ID da instituição. Parâmetro obrigatório
	 * @param tipoPessoa
	 * 		Tipo da pessoa. Parâmetro obrigatório<br>
	 * 		0 : Pessoa Física<br>
	 *  	1 : Pessoa Jurídica
	 * @return Retorna a lista de pessoas de acordo com os criterios de consulta
	 * @throws BancoobException
	 */
	List<PessoaVO> obterByNomeTipoPessoa(String nome, Integer idInstituicao, Short tipoPessoa)
			throws BancoobException;

	/**
	 * Consulta por apelido, ID da intituição e tipo de pessoa. Ver {@link TipoPessoaEnum}<br>
	 * 
	 * @param nomeApelido
	 * 		Apelido da pessoa a ser pesquisado. Parâmetro obrigatório e maior do que 3 caracteres
	 * @param idInstituicao
	 * 		ID da instituição. Parâmetro obrigatório
	 * @param tipoPessoa
	 * 		Tipo da pessoa. Parâmetro obrigatório<br>
	 * 		0 : Pessoa Física<br>
	 *  	1 : Pessoa Jurídica
	 * @return Retorna a lista de pessoas de acordo com os criterios de consulta
	 * @throws BancoobException
	 */
	List<PessoaVO> obterByNomeApelidoTipoPessoa(String nomeApelido, Integer idInstituicao,
			Short tipoPessoa) throws BancoobException;

	/**
	 * Consulta por nome, ID da intituição e tipo de pessoa, de forma paginada. 
	 * Ver {@link TipoPessoaEnum}<br>
	 * 
	 * @param nome
	 * 		Nome da pessoa a ser pesquisado. Parâmetro obrigatório e maior do que 3 caracteres
	 * @param idInstituicao
	 * 		ID da instituição. Parâmetro obrigatório
	 * @param tipoPessoa
	 * 		Tipo da pessoa. Parâmetro obrigatório<br>
	 * 		0 : Pessoa Física<br>
	 *  	1 : Pessoa Jurídica
	 * @param pagina
	 * 		Número da página, maior ou igual a 0 (zero). 
	 * @param tamanhoPagina
	 * 		Tamanho de registros por página. Deve ser maior que 1 (um)
	 * @return
	 * @throws BancoobException
	 */
	ConsultaDto<PessoaVO> obterByNomeTipoPessoa(String nome, Integer idInstituicao,
			Short tipoPessoa, int pagina, int tamanhoPagina) throws BancoobException;

	/**
	 * Consulta por apelido, ID da intituição e tipo de pessoa, de forma paginada. 
	 * Ver {@link TipoPessoaEnum}<br>
	 * 
	 * @param nomeApelido
	 * 		Apelido da pessoa a ser pesquisado. Parâmetro obrigatório e maior do que 3 caracteres
	 * @param idInstituicao
	 * 		ID da instituição. Parâmetro obrigatório
	 * @param tipoPessoa
	 * 		Tipo da pessoa. Parâmetro obrigatório<br>
	 * 		0 : Pessoa Física<br>
	 *  	1 : Pessoa Jurídica
	 * @param pagina
	 * 		Número da página, maior ou igual a 0 (zero). 
	 * @param tamanhoPagina
	 * 		Tamanho de registros por página. Deve ser maior que 1 (um
	 * @return
	 * @throws BancoobException
	 */
	ConsultaDto<PessoaVO> obterByNomeApelidoTipoPessoa(String nomeApelido,
			Integer idInstituicao, Short tipoPessoa, int pagina, int tamanhoPagina)
			throws BancoobException;

	/**
	 * Pesquisar pessoa plataforma.
	 * 
	 * @param consultaDto
	 *            the consulta dto
	 * @return consulta dto
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	ConsultaDto<PessoaPlataformaVO> pesquisarPessoaPlataforma(ConsultaDto<PessoaPlataformaVO> consultaDto)
			throws BancoobException;
	
	
	/**
	 * Pesquisar pessoa plataforma com as informacoes minimas e com limite de registros.
	 * 
	 * @param consultaDto
	 *            the consulta dto
	 * @return consulta dto
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	ConsultaDto<PessoaPlataformaVO> pesquisarPessoaPlataformaResumido(ConsultaDto<PessoaPlataformaVO> consultaDto)
			throws BancoobException;

	/**
	 * Consultar pessoa plataforma por id pessoa legado.
	 * 
	 * @param idPessoaLegado
	 *            the id pessoa legado
	 * @return pessoa plataforma vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	PessoaPlataformaVO consultarPessoaPlataformaPorIdPessoaLegado(Integer idPessoaLegado) throws BancoobException;
	
	/**
	 * Obter dados pessoa legado.
	 * 
	 * @param cpfCnpj
	 *            the cpf cnpj
	 * @param idInstituicao
	 *            the id instituicao
	 * @return map
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	Map<String, Object> obterDadosPessoaLegado(String cpfCnpj, Integer idInstituicao) throws BancoobException;

	/**
	 * Obter por lista idPessoa instituicao
	 * 
	 * @param idPessoas
	 * @param idInstituicao
	 * @return List<PessoaBasicaVO>
	 * @throws BancoobException
	 */
	List<PessoaBasicaVO> obterPorListaPessoaInstituicao(List<Integer> idPessoas, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Pesquisa do componente Procurar Pessoa Externo CAPES
	 * 
	 * @param criterios
	 * @return ConsultaDto<ProcurarPessoaExternoVO> consulta
	 * @throws BancoobException
	 */
	ConsultaDto<ProcurarPessoaExternoVO> procurarPessoaExterno(ConsultaDto<ProcurarPessoaExternoVO> criterios) throws BancoobException;

	/**
	 * Retorna da lista informada quais são os que possuem autorização para consulta ao BACEN.
	 * 
	 * @param listaCpfCnpj
	 * @param idInstituicao
	 * @return
	 */
	List<String> consultarPessoaPossuemAutorizacaoBacen(List<String> listaCpfCnpj, Integer idInstituicao) throws BancoobException;

	/**
	 * Obter por cpf cnpj e instituicao do grupo compartilhamento.
	 * 
	 * @param cpfCnpj
	 *            the cpf cnpj 
	 * @param idInstituicao
	 *            the id instituicao
	 * @return pessoa vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	PessoaVO obterPorCpfCnpjInstituicaoGrupoCompartilhamento(String cpfCnpj, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obtém o nome da pessoa pelo número CPF/CNPJ
	 * 
	 * <p><b>IMPORTANTE: esse método recupera o nome da pessoa APENAS no compartilhamento do SICOOB </b></p>
	 * 
	 * @param cpfCnpj
	 * @return
	 * @throws BancoobException
	 */
	String obterNomePessoaCompartilhamentoSicoob(String cpfCnpj) throws BancoobException;
	
	/**
	 * Obtém a assinatura da pessoa
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	byte[] obterAssinatura(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obtém a imagem da pessoa
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	byte[] obterImagem(Integer idPessoa, Integer idInstituicao) throws BancoobException;

}