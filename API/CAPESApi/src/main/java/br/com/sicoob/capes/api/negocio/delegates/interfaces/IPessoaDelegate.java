package br.com.sicoob.capes.api.negocio.delegates.interfaces;

import java.util.List;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.api.negocio.vo.PessoaVO;
import br.com.sicoob.capes.comum.negocio.vo.PessoaBasicaVO;
import br.com.sicoob.capes.comum.negocio.vo.PessoaPlataformaVO;
import br.com.sicoob.capes.comum.negocio.vo.ProcurarPessoaExternoVO;

/**
 * A interfacee pessoaDelegate.
 * 
 * @author Lucas.Borges
 */
public interface IPessoaDelegate extends ICAPESApiDelegate {

	/**
	 * <ul>
	 * <li>Operação disponível em <strong>Backoffice</strong>, <strong>Frontoffice</strong> e <strong>Processamento</strong></li>
	 * </ul>
	 * 
	 * @param idPessoa
	 *            o ID da pessoa
	 * @param idInstituicao
	 *            o ID da instituição
	 * @return a pessoa solicitada
	 */
	PessoaVO obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obter por pessoa legado instituicao.
	 * 
	 * @param idPessoaLegado
	 *            the id pessoa legado
	 * @param idInstituicao
	 *            the id instituicao
	 * @return pessoa vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	PessoaVO obterPorPessoaLegadoInstituicao(Integer idPessoaLegado, Integer idInstituicao) throws BancoobException;

	/**
	 * Obter por cpf cnpj instituicao.
	 * 
	 * @param cpfCnpj
	 *            the cpf cnpj
	 * @param idInstituicao
	 *            the id instituicao
	 * @return pessoa vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	PessoaVO obterPorCpfCnpjInstituicao(String cpfCnpj, Integer idInstituicao) throws BancoobException;

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
	 * Obter por cpf cnpj.
	 * 
	 * @param cpfCnpj
	 *            the cpf cnpj
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	List<PessoaVO> obterPorCpfCnpj(String cpfCnpj) throws BancoobException;

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
	List<PessoaVO> obterPorNomeInstituicao(String nome, Integer idInstituicao) throws BancoobException;

	/**
	 * Obter por nome.
	 * 
	 * @param nome
	 *            the nome
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	List<PessoaVO> obterPorNome(String nome) throws BancoobException;

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
	List<PessoaVO> obterPorNomeApelidoInstituicao(String nomeApelido, Integer idInstituicao) throws BancoobException;

	/**
	 * Consulta as pessoas pelo nome de forma paginada. A pagina 0 corresponde a primeira.
	 * 
	 * @param nome
	 * @param idInstituicao
	 * @param pagina
	 * @param tamanhoPagina
	 * @return
	 * @throws BancoobException
	 */
	ConsultaDto<PessoaVO> obterPorNomeInstituicaoPaginado(String nome, Integer idInstituicao, int pagina, int tamanhoPagina) throws BancoobException;

	/**
	 * Consulta as pessoas pelo nome de forma paginada. A pagina 0 corresponde a primeira.
	 * 
	 * @param nome
	 * @param pagina
	 * @param tamanhoPagina
	 * @return
	 * @throws BancoobException
	 */
	ConsultaDto<PessoaVO> obterPorNomeInstituicaoPaginado(String nome, int pagina, int tamanhoPagina) throws BancoobException;

	/**
	 * Consulta as pessoas pelo nome apelido de forma paginada. A pagina 0 corresponde a primeira.
	 * 
	 * @param nomeApelido
	 * @param idInstituicao
	 * @param pagina
	 * @param tamanhoPagina
	 * @return
	 * @throws BancoobException
	 */
	ConsultaDto<PessoaVO> obterPorNomeApelidoInstituicaoPaginado(String nomeApelido, Integer idInstituicao, int pagina, int tamanhoPagina)
			throws BancoobException;

	/**
	 * Consulta por nome, ID da intituicao e tipo de pessoa. Ver ;@link TipoPessoaEnum}<br>
	 * 
	 * @param nome
	 *            Nome da pessoa a ser pesquisado. Parametro obrigatorio e maior do que 3 caracteres
	 * @param idInstituicao
	 *            ID da instituicao. Parametro obrigatorio
	 * @param tipoPessoa
	 *            Tipo da pessoa. Parametro obrigatorio<br>
	 *            0 : Pessoa Fisica<br>
	 *            1 : Pessoa Juridica
	 * @return Retorna a lista de pessoas de acordo com os criterios de consulta
	 * @throws BancoobException
	 */
	List<PessoaVO> obterPorNomeInstituicaoTipoPessoa(String nome, Integer idInstituicao, Short tipoPessoa) throws BancoobException;

	/**
	 * Consulta por apelido, ID da intituicao e tipo de pessoa. Ver ;@link TipoPessoaEnum}<br>
	 * 
	 * @param nomeApelido
	 *            Apelido da pessoa a ser pesquisado. Parametro obrigatorio e maior do que 3 caracteres
	 * @param idInstituicao
	 *            ID da instituicao. Parametro obrigatorio
	 * @param tipoPessoa
	 *            Tipo da pessoa. Parametro obrigatorio<br>
	 *            0 : Pessoa Fisica<br>
	 *            1 : Pessoa Juridica
	 * @return Retorna a lista de pessoas de acordo com os criterios de consulta
	 * @throws BancoobException
	 */
	List<PessoaVO> obterPorNomeApelidoInstituicaoTipoPessoa(String nomeApelido, Integer idInstituicao, Short tipoPessoa) throws BancoobException;

	/**
	 * Consulta por nome, ID da intituicao e tipo de pessoa, de forma paginada. Ver;@link TipoPessoaEnum}<br>
	 * 
	 * @param nome
	 *            Nome da pessoa a ser pesquisado. Parametro obrigatorio e maior do que 3 caracteres
	 * @param idInstituicao
	 *            ID da instituicao. Parametro obrigatorio
	 * @param tipoPessoa
	 *            Tipo da pessoa. Parametro obrigatorio<br>
	 *            0 : Pessoa Fisica<br>
	 *            1 : Pessoa Juridica
	 * @param pagina
	 *            Numero da pagina, maior ou igual a 0 (zero).
	 * @param tamanhoPagina
	 *            Tamanho de registros por pagina. Deve ser maior que 1 (um)
	 * @return
	 * @throws BancoobException
	 */
	ConsultaDto<PessoaVO> obterPorNomeInstituicaoTipoPessoaPaginado(String nome, Integer idInstituicao, Short tipoPessoa, int pagina,
			int tamanhoPagina) throws BancoobException;

	/**
	 * Consulta por apelido, ID da intituicao e tipo de pessoa, de forma paginada. Ver;@link TipoPessoaEnum}<br>
	 * 
	 * @param nomeApelido
	 *            Apelido da pessoa a ser pesquisado. Parametro obrigatorio e maior do que 3 caracteres
	 * @param idInstituicao
	 *            ID da instituicao. Parametro obrigatorio
	 * @param tipoPessoa
	 *            Tipo da pessoa. Parametro obrigatorio<br>
	 *            0 : Pessoa Fisica<br>
	 *            1 : Pessoa Juridica
	 * @param pagina
	 *            Numero da pagina, maior ou igual a 0 (zero).
	 * @param tamanhoPagina
	 *            Tamanho de registros por pagina. Deve ser maior que 1 (um
	 * @return
	 * @throws BancoobException
	 */
	ConsultaDto<PessoaVO> obterPorNomeApelidoInstituicaoTipoPessoaPaginado(String nomeApelido, Integer idInstituicao, Short tipoPessoa, int pagina,
			int tamanhoPagina) throws BancoobException;

	/**
	 * Pesquisar pessoa plataforma.
	 * 
	 * @param consultaDto
	 *            the consulta dto
	 * @return consulta dto
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	ConsultaDto<PessoaPlataformaVO> pesquisarPessoaPlataforma(ConsultaDto<PessoaPlataformaVO> consultaDto) throws BancoobException;

	/**
	 * Pesquisar pessoa plataforma com as informacoes minimas e com limite de registros.
	 * 
	 * @param consultaDto
	 *            the consulta dto
	 * @return consulta dto
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	ConsultaDto<PessoaPlataformaVO> pesquisarPessoaPlataformaResumido(ConsultaDto<PessoaPlataformaVO> consultaDto) throws BancoobException;

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
	 * @return List<PessoaInstituicaoVO>
	 * @throws BancoobException
	 */
	List<PessoaBasicaVO> obterPorListaPessoaInstituicao(List<Integer> idPessoas, Integer idInstituicao) throws BancoobException;

	/**
	 * Procurar pessoa externo.
	 *
	 * @param criterios
	 *            o valor de criterios
	 * @return ConsultaDto
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	ConsultaDto<ProcurarPessoaExternoVO> procurarPessoaExterno(ConsultaDto<ProcurarPessoaExternoVO> criterios) throws BancoobException;

	/**
	 * Retorna da lista informada quais são os que possuem autorização para consulta ao BACEN.
	 * 
	 * @param cpfCnpj
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<String> consultarPessoaPossuemAutorizacaoBacen(List<String> listaCpfCnpj, Integer idInstituicao) throws BancoobException;

	/**
	 * Obtém o nome da pessoa pelo número CPF/CNPJ
	 * 
	 * <p>
	 * <b>IMPORTANTE: esse método recupera o nome da pessoa APENAS no compartilhamento do SICOOB </b>
	 * </p>
	 * 
	 * @param cpfCnpj
	 * @return
	 * @throws BancoobException
	 */
	String obterNomePessoaCompartilhamentoSicoob(String cpfCnpj) throws BancoobException;

	/**
	 * Obtém a assinatura da pessoa
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	byte[] obterAssinatura(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obtém a imagem da pessoa
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	byte[] obterImagem(Integer idPessoa, Integer idInstituicao) throws BancoobException;

}