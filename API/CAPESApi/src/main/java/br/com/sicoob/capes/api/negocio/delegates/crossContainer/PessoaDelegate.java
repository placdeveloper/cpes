/*
 * SICOOB
 * 
 * PessoaDelegate.java(br.com.sicoob.capes.api.negocio.delegates.PessoaDelegate)
 */
package br.com.sicoob.capes.api.negocio.delegates.crossContainer;

import java.util.List;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.IPessoaDelegate;
import br.com.sicoob.capes.api.negocio.servicos.PessoaServico;
import br.com.sicoob.capes.api.negocio.vo.PessoaVO;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.comum.negocio.vo.PessoaBasicaVO;
import br.com.sicoob.capes.comum.negocio.vo.PessoaPlataformaVO;
import br.com.sicoob.capes.comum.negocio.vo.ProcurarPessoaExternoVO;

/**
 * A classe pessoaDelegate.
 * @author Lucas.Borges
 */
public class PessoaDelegate extends CAPESApiDelegate<PessoaServico> implements IPessoaDelegate {
	
	/**
	 * 
	 */
	protected PessoaDelegate() {
	}

	/**
	 * 
	 * @return
	 */
	public static PessoaDelegate getInstance() {
		return valueOf(PessoaDelegate.class);
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected PessoaServico localizarServico() {
		return getLocator().localizarPessoaServico();
	}

	/**
	 * <ul>
	 * <li>Operação disponível em <strong>Backoffice</strong>,
	 * <strong>Frontoffice</strong> e <strong>Processamento</strong></li>
	 * </ul>
	 * 
	 * @param idPessoa 
	 *            o ID da pessoa
	 * @param idInstituicao
	 *            o ID da instituição
	 * @return a pessoa solicitada
	 */
	public PessoaVO obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return getServico().obterByIdPessoa(idPessoa, idInstituicao);
	}

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
	public PessoaVO obterPorPessoaLegadoInstituicao(Integer idPessoaLegado, Integer idInstituicao) throws BancoobException {
		return getServico().obterByIdPessoaLegado(idPessoaLegado, idInstituicao);
	}

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
	public PessoaVO obterPorCpfCnpjInstituicao(String cpfCnpj, Integer idInstituicao) throws BancoobException {
		return getServico().obterByCpfCnpj(cpfCnpj, idInstituicao);
	}
	
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
	public PessoaVO obterPorCpfCnpjInstituicaoGrupoCompartilhamento(String cpfCnpj, Integer idInstituicao) throws BancoobException {
		return getServico().obterPorCpfCnpjInstituicaoGrupoCompartilhamento(cpfCnpj, idInstituicao);
	}

	/**
	 * Obter por cpf cnpj.
	 * 
	 * @param cpfCnpj
	 *            the cpf cnpj
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public List<PessoaVO> obterPorCpfCnpj(String cpfCnpj) throws BancoobException {
		return getServico().obterByCpfCnpj(cpfCnpj);
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
	public List<PessoaVO> obterPorNomeInstituicao(String nome, Integer idInstituicao) throws BancoobException {
		return getServico().obterByNome(nome, idInstituicao);
	}

	/**
	 * Obter por nome.
	 * 
	 * @param nome
	 *            the nome
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public List<PessoaVO> obterPorNome(String nome) throws BancoobException {
		return getServico().obterByNome(nome);
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
	public List<PessoaVO> obterPorNomeApelidoInstituicao(String nomeApelido, Integer idInstituicao) throws BancoobException {
		return getServico().obterByNomeApelido(nomeApelido, idInstituicao);
	}

	/**
	 * Consulta as pessoas pelo nome de forma paginada. A pagina 0 corresponde a
	 * primeira.
	 * 
	 * @param nome
	 * @param idInstituicao
	 * @param pagina
	 * @param tamanhoPagina
	 * @return
	 * @throws BancoobException
	 */
	public ConsultaDto<PessoaVO> obterPorNomeInstituicaoPaginado(String nome, Integer idInstituicao, int pagina, int tamanhoPagina) throws BancoobException {
		return getServico().obterByNome(nome, idInstituicao, pagina, tamanhoPagina);
	}

	/**
	 * Consulta as pessoas pelo nome de forma paginada. A pagina 0 corresponde a
	 * primeira.
	 * 
	 * @param nome
	 * @param pagina
	 * @param tamanhoPagina
	 * @return
	 * @throws BancoobException
	 */
	public ConsultaDto<PessoaVO> obterPorNomeInstituicaoPaginado(String nome, int pagina, int tamanhoPagina) throws BancoobException {
		return getServico().obterByNome(nome, pagina, tamanhoPagina);
	}

	/**
	 * Consulta as pessoas pelo nome apelido de forma paginada. A pagina 0
	 * corresponde a primeira.
	 * 
	 * @param nomeApelido
	 * @param idInstituicao
	 * @param pagina
	 * @param tamanhoPagina
	 * @return
	 * @throws BancoobException
	 */
	public ConsultaDto<PessoaVO> obterPorNomeApelidoInstituicaoPaginado(String nomeApelido, Integer idInstituicao, int pagina, int tamanhoPagina)
			throws BancoobException {
		return getServico().obterByNomeApelido(nomeApelido, idInstituicao, pagina, tamanhoPagina);
	}

	/**
	 * Consulta por nome, ID da intituicao e tipo de pessoa. Ver
	 * {@link TipoPessoaEnum}<br>
	 * 
	 * @param nome
	 *            Nome da pessoa a ser pesquisado. Parametro obrigatorio e
	 *            maior do que 3 caracteres
	 * @param idInstituicao
	 *            ID da instituicao. Parametro obrigatorio
	 * @param tipoPessoa
	 *            Tipo da pessoa. Parametro obrigatorio<br>
	 *            0 : Pessoa Fisica<br>
	 *            1 : Pessoa Juridica
	 * @return Retorna a lista de pessoas de acordo com os criterios de consulta
	 * @throws BancoobException
	 */
	public List<PessoaVO> obterPorNomeInstituicaoTipoPessoa(String nome, Integer idInstituicao, Short tipoPessoa) throws BancoobException {
		return getServico().obterByNomeTipoPessoa(nome, idInstituicao, tipoPessoa);
	}

	/**
	 * Consulta por apelido, ID da intituicao e tipo de pessoa. Ver
	 * {@link TipoPessoaEnum}<br>
	 * 
	 * @param nomeApelido
	 *            Apelido da pessoa a ser pesquisado. Parametro obrigatorio
	 *            e maior do que 3 caracteres
	 * @param idInstituicao
	 *            ID da instituicao. Parametro obrigatorio
	 * @param tipoPessoa
	 *            Tipo da pessoa. Parametro obrigatorio<br>
	 *            0 : Pessoa Fisica<br>
	 *            1 : Pessoa Juridica
	 * @return Retorna a lista de pessoas de acordo com os criterios de consulta
	 * @throws BancoobException
	 */
	public List<PessoaVO> obterPorNomeApelidoInstituicaoTipoPessoa(String nomeApelido, Integer idInstituicao, Short tipoPessoa) throws BancoobException {
		return getServico().obterByNomeApelidoTipoPessoa(nomeApelido, idInstituicao, tipoPessoa);
	}

	/**
	 * Consulta por nome, ID da intituicao e tipo de pessoa, de forma
	 * paginada. Ver {@link TipoPessoaEnum}<br>
	 * 
	 * @param nome
	 *            Nome da pessoa a ser pesquisado. Parametro obrigatorio e
	 *            maior do que 3 caracteres
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
	public ConsultaDto<PessoaVO> obterPorNomeInstituicaoTipoPessoaPaginado(String nome, Integer idInstituicao, Short tipoPessoa, int pagina, int tamanhoPagina)
			throws BancoobException {
		return getServico().obterByNomeTipoPessoa(nome, idInstituicao, tipoPessoa, pagina, tamanhoPagina);
	}

	/**
	 * Consulta por apelido, ID da intituicao e tipo de pessoa, de forma
	 * paginada. Ver {@link TipoPessoaEnum}<br>
	 * 
	 * @param nomeApelido
	 *            Apelido da pessoa a ser pesquisado. Parametro obrigatorio
	 *            e maior do que 3 caracteres
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
	public ConsultaDto<PessoaVO> obterPorNomeApelidoInstituicaoTipoPessoaPaginado(String nomeApelido, Integer idInstituicao, Short tipoPessoa, int pagina,
			int tamanhoPagina) throws BancoobException {
		return getServico().obterByNomeApelidoTipoPessoa(nomeApelido, idInstituicao, tipoPessoa, pagina, tamanhoPagina);
	}

	/**
	 * Pesquisar pessoa plataforma.
	 * 
	 * @param consultaDto
	 *            the consulta dto
	 * @return consulta dto
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public ConsultaDto<PessoaPlataformaVO> pesquisarPessoaPlataforma(ConsultaDto<PessoaPlataformaVO> consultaDto) throws BancoobException {

		return getServico().pesquisarPessoaPlataforma(consultaDto);
	}
	
	
	/**
	 * Pesquisar pessoa plataforma com as informacoes minimas e com limite de registros.
	 * 
	 * @param consultaDto
	 *            the consulta dto
	 * @return consulta dto
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public ConsultaDto<PessoaPlataformaVO> pesquisarPessoaPlataformaResumido(ConsultaDto<PessoaPlataformaVO> consultaDto) throws BancoobException {

		return getServico().pesquisarPessoaPlataformaResumido(consultaDto);
	}

	/**
	 * Consultar pessoa plataforma por id pessoa legado.
	 * 
	 * @param idPessoaLegado
	 *            the id pessoa legado
	 * @return pessoa plataforma vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public PessoaPlataformaVO consultarPessoaPlataformaPorIdPessoaLegado(Integer idPessoaLegado) throws BancoobException {

		return getServico().consultarPessoaPlataformaPorIdPessoaLegado(idPessoaLegado);
	}
	
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
	public Map<String, Object> obterDadosPessoaLegado(String cpfCnpj, Integer idInstituicao) throws BancoobException {

		return getServico().obterDadosPessoaLegado(cpfCnpj, idInstituicao);
	}

	/**
	 * Obter por lista idPessoa instituicao
	 * 
	 * @param idPessoas
	 * @param idInstituicao
	 * @return List<PessoaInstituicaoVO>
	 * @throws BancoobException
	 */
	public List<PessoaBasicaVO> obterPorListaPessoaInstituicao(List<Integer> idPessoas, Integer idInstituicao) throws BancoobException {
		return getServico().obterPorListaPessoaInstituicao(idPessoas, idInstituicao);
	}
	
	/**
	 * Procurar pessoa externo.
	 *
	 * @param criterios o valor de criterios
	 * @return ConsultaDto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public ConsultaDto<ProcurarPessoaExternoVO> procurarPessoaExterno(ConsultaDto<ProcurarPessoaExternoVO> criterios) throws BancoobException {
		return getServico().procurarPessoaExterno(criterios);
	}
	
	/**
	 * Retorna da lista informada quais são os que possuem autorização para consulta ao BACEN.
	 * 
	 * @param cpfCnpj
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public List<String> consultarPessoaPossuemAutorizacaoBacen(List<String> listaCpfCnpj, Integer idInstituicao) throws BancoobException {
		return getServico().consultarPessoaPossuemAutorizacaoBacen(listaCpfCnpj, idInstituicao);
	}
	
	/**
	 * Obtém o nome da pessoa pelo número CPF/CNPJ
	 * 
	 * <p><b>IMPORTANTE: esse método recupera o nome da pessoa APENAS no compartilhamento do SICOOB </b></p>
	 * 
	 * @param cpfCnpj
	 * @return
	 * @throws BancoobException
	 */
	public String obterNomePessoaCompartilhamentoSicoob(String cpfCnpj) throws BancoobException{
		return getServico().obterNomePessoaCompartilhamentoSicoob(cpfCnpj);
	}
	
	/**
	 * Obtém a assinatura da pessoa
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public byte[] obterAssinatura(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return getServico().obterAssinatura(idPessoa, idInstituicao);
	}

	/**
	 * Obtém a imagem da pessoa
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public byte[] obterImagem(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return getServico().obterImagem(idPessoa, idInstituicao);
	}

}