package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.excecao.GrupoCompartilhamentoInexistenteException;
import br.com.sicoob.capes.cadastro.negocio.excecao.PessoaNaoEncontradaException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoCompartilhamentoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.PessoaCompartilhamentoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.PessoaIntegracaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.PessoaIntegracaoServicoRemote;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.PessoaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TransicaoPessoaServicoLocal;
import br.com.sicoob.capes.comum.negocio.vo.PessoaPlataformaVO;
import br.com.sicoob.capes.comum.negocio.vo.ProcurarPessoaExternoVO;
import br.com.sicoob.capes.negocio.entidades.CompartilhamentoCadastro;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;
import br.com.sicoob.capes.negocio.entidades.TransicaoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.GrupoCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * EJB que implementa os serviços de integração de pessoa
 * com as outras aplicações do SICOOB.
 * 
 * @author juan.damasceno
 *
 */
@Stateless
@Local( { PessoaIntegracaoServicoLocal.class })
@Remote( { PessoaIntegracaoServicoRemote.class })
public class PessoaIntegracaoServicoEJB extends CAPESCadastroServicoEJB implements PessoaIntegracaoServicoRemote, PessoaIntegracaoServicoLocal {
	
	/** O atributo pessoaCompartilhamentoServico. */
	@EJB(mappedName = "capes/cadastro/PessoaCompartilhamentoServico")
	private PessoaCompartilhamentoServicoLocal pessoaCompartilhamentoServico;
	
	/** O atributo pessoaServico. */
	@EJB(mappedName = "capes/cadastro/PessoaServico")
	private PessoaServicoLocal pessoaServico;
	
	/** O atributo transicaoPessoaServico. */
	@EJB(mappedName = "capes/cadastro/TransicaoPessoaServico")
	private TransicaoPessoaServicoLocal transicaoPessoaServico;
	
	/** O atributo grupoCompartilhamentoServico. */
	@EJB(mappedName = "capes/cadastro/GrupoCompartilhamentoServico")
	private GrupoCompartilhamentoServicoLocal grupoCompartilhamentoServico;
	
	/**
	 * Consulta a pessoa utilizando o identificador da pessoa no sistema legado.
	 * @param idInstituicao a instituição que será usada para consultar a pessoa.
	 * @param idPessoaLegado o identificador da pessoa no sistema legado.
	 * @return uma instancia de PessoaPlataformaVO contendo os dados da pessoa.
	 * @throws BancoobException caso ocorra alguma exceção.
	 */
	public PessoaPlataformaVO consultarPessoaPlataformaPorIdPessoaLegado(Integer idInstituicao, Integer idPessoaLegado)
			throws BancoobException {

		PessoaCompartilhamento pessoaCompartilhamento = 
				obterPessoaPorIdPessoaLegado(idInstituicao, idPessoaLegado);

		return converterPessoa(pessoaCompartilhamento, idInstituicao);
	}

	/**
	 * {@inheritDoc}
	 */
	public PessoaPlataformaVO consultarPessoaPlataformaPorIdPessoaLegado(Integer idPessoaLegado)
			throws BancoobException {
		Instituicao instituicao = obterInstituicaoUsuarioLogado();
		return consultarPessoaPlataformaPorIdPessoaLegado(instituicao.getIdInstituicao(), idPessoaLegado);
	}
	
	/**
	 * Consulta a pessoa utilizando o documento como filtro.
	 * @param idInstituicao a instituição que será usada para consultar a pessoa.
	 * @param cpfCnpj o CPF ou CNPJ da pessoa que será pesquisada.
	 * @return uma instancia de PessoaPlataformaVO contendo os dados da pessoa pesquisada.
	 * @throws GrupoCompartilhamentoInexistenteException caso não exista um 
	 * GrupoCompartilhamento para a instituição usada como filtro da pesquisa. 
	 */
	public PessoaPlataformaVO consultarPorDocumento(Integer idInstituicao, String cpfCnpj) throws BancoobException {

		PessoaCompartilhamento pessoaCompartilhamento = 
				pessoaCompartilhamentoServico.consultarPessoaPorDocumento(idInstituicao, cpfCnpj);

		return converterPessoa(pessoaCompartilhamento, idInstituicao);
	}

	/**
	 * Consulta a transição da pessoa na instituição passada como parametro.
	 * @param pessoaCompartilhamento a instância de PessoaCompartilhamento.
	 * @param idInstituicao a instituição que será usada como filtro para a 
	 * consulta dos dados das transições.
	 * @return a transição da pessoa
	 * @throws BancoobException 
	 */
	private TransicaoPessoa obterTransicao(Pessoa pessoa, Integer idInstituicao) throws BancoobException {

		Instituicao instituicao = new Instituicao();
		instituicao.setIdInstituicao(idInstituicao);
		return transicaoPessoaServico.obterTransicaoPorPessoaInstituicao(pessoa, idInstituicao);
	}

	/**
	 * Pesquisa pessoas com relacionamento na instituição do usuário logado, utilizando os 
	 * critérios informados.
	 * 
	 * @param criterios Os critérios utilizados na consulta.
	 * @return As pessoas encontradas.
	 * @throws GrupoCompartilhamentoInexistenteException caso não exista um 
	 * GrupoCompartilhamento para a instituição usada como filtro da pesquisa. 
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ConsultaDto<PessoaPlataformaVO> pesquisarPessoaPlataforma(ConsultaDto<PessoaPlataformaVO> criterios) throws BancoobException {
		return pessoaCompartilhamentoServico.pesquisarPessoaProxy(criterios);
	}
	
	
	/**
	 * Pesquisa pessoas com relacionamento na instituição do usuário logado, utilizando os 
	 * critérios informados.
	 * 
	 * Retorna as informacoes minimas e com limite de registros.
	 * 
	 * @param criterios Os critérios utilizados na consulta.
	 * @return As pessoas encontradas.
	 * @throws GrupoCompartilhamentoInexistenteException caso não exista um 
	 * GrupoCompartilhamento para a instituição usada como filtro da pesquisa. 
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ConsultaDto<PessoaPlataformaVO> pesquisarPessoaPlataformaResumido(ConsultaDto<PessoaPlataformaVO> criterios) throws BancoobException {
		return pessoaCompartilhamentoServico.pesquisarPessoaProxyResumido(criterios);
	}
	
	/**
	 * Consulta os dados da pessa da pessoa.
	 * @param idInstituicao a instituição que será usada como filtro para a 
	 * consulta dos dados pessoa.
	 * @param idPessoa o identificador da pessoa que será consultada
	 */
	public PessoaCompartilhamento obter(Integer idInstituicao, Integer idPessoa) throws BancoobException {
		
		Pessoa pessoa = new Pessoa();
		pessoa.setId(idPessoa);
		
		CompartilhamentoCadastro compartilhamentoCadastro = obterCompartilhamentoCadastro(idInstituicao);
		PessoaCompartilhamento pessoaCompartilhamento = 
				pessoaCompartilhamentoServico.consultar(pessoa, compartilhamentoCadastro);
		
		if(pessoaCompartilhamento == null) {
			throw new PessoaNaoEncontradaException();
		}
		
		return pessoaCompartilhamento;
	}
	
	/**
	 * Consulta os dados da pessoa na instituição do usuário logado.
	 * @param idPessoa o identificador da pessoa que será consultada
	 * @return proxy para a pessoa consultada na instituição do usuário logado.
	 */
	public PessoaPlataformaVO obterPessoaPlataforma(Integer idPessoa) throws BancoobException {
		
		Integer idInstituicao = obterInstituicaoUsuarioLogado().getIdInstituicao();
		return obterPessoaPlataforma(idPessoa, idInstituicao);
	}	
	
	/**
	 * Consulta os dados da pessoa na instituição do usuário logado.
	 * @param idPessoa o identificador da pessoa que será consultada
	 * @param idInstituicao a instituição que será usada para consultar a pessoa. 
	 * @return proxy para a pessoa consultada na instituição do usuário logado.
	 */	
	public PessoaPlataformaVO obterPessoaPlataforma(Integer idPessoa, Integer idInstituicao)
			throws BancoobException {
		PessoaCompartilhamento pessoaCompartilhamento = obter(idInstituicao, idPessoa);
		return converterPessoa(pessoaCompartilhamento, idInstituicao);
	}	
	
	/**
	 * Converte uma instância de PessoaCompartilhamento para PessoaPlataformaVO, 
	 * também consulta os dados da transição usando o idInstituição como filtro.
	 * @param pessoaCompartilhamento a instância de PessoaCompartilhamento que 
	 * será convertida.
	 * @param idInstituicao a instituição que será usada como filtro para a 
	 * consulta dos dados das transições.
	 * @return uma instância de PessoaPlataformaVO.
	 * @throws BancoobException 
	 */
	private PessoaPlataformaVO converterPessoa(
			PessoaCompartilhamento pessoaCompartilhamento, Integer idInstituicao) throws BancoobException {

		Pessoa pessoa = pessoaCompartilhamento.getPessoa();
		TipoPessoa tipoPessoa = pessoa.getTipoPessoa();

		PessoaPlataformaVO pessoaProxy = new PessoaPlataformaVO();
		pessoaProxy.setCodTipoPessoa(tipoPessoa.getCodTipoPessoa());
		pessoaProxy.setDescTipoPessoa(tipoPessoa.getDescTipoPessoa());
		pessoaProxy.setCpfCnpj(pessoa.getCpfCnpj());
		pessoaProxy.setIdCompartilhamento(pessoaCompartilhamento.getIdPessoaCompartilhamento());
		pessoaProxy.setIdPessoa(pessoa.getIdPessoa());
		pessoaProxy.setNomeApelido(pessoaCompartilhamento.getNomeApelido());
		pessoaProxy.setNomeCompleto(pessoaCompartilhamento.getNomeCompleto());
		pessoaProxy.setNomePessoa(pessoaCompartilhamento.getNomePessoa());
		pessoaProxy.setAutorizaConsultaBacen(pessoaCompartilhamento.getAutorizaConsultaBacen());
		pessoaProxy.setDataInclusaoSFN(pessoaCompartilhamento.getDataInclusaoSFN());
		pessoaProxy.setUtilizaGedGft(pessoaCompartilhamento.getCompartilhamento().getUtilizaGedGft());
		pessoaProxy.setCodCompartilhamentoCadastro(pessoaCompartilhamento.getCodCompartilhamentoCadastro());
		pessoaProxy.setCodigoPerfilCadastro(pessoaCompartilhamento.getPerfilCadastro().getCodigo());
		
		TransicaoPessoa transicaoPessoaRetorno = obterTransicao(pessoa, idInstituicao);
		
		if (transicaoPessoaRetorno != null) {
			Instituicao instituicao = transicaoPessoaRetorno.getInstituicao();
			pessoaProxy.setIdUnidadeInst(instituicao.getIdUnidadeInst());
			pessoaProxy.setIdInstituicao(instituicao.getIdInstituicao());
			pessoaProxy.setIdPessoaLegado(transicaoPessoaRetorno.getIdPessoaLegado());
		}
		
		return pessoaProxy;
	}
	
	/**
	 * Recupera o compartilhamento cadastro usando a instiuição como filtro. 
	 * @param idInstituicao a instiuição que será usada como filtro.
	 * @return o compartilhamento cadastro.
	 * @throws BancoobException Caso ocorra alguma exceção
	 */
	private CompartilhamentoCadastro obterCompartilhamentoCadastro(
			Integer idInstituicao) throws BancoobException {
		GrupoCompartilhamento grupoCompartilhamento = 
				grupoCompartilhamentoServico.obterPorInstituicao(idInstituicao);
		
		if (grupoCompartilhamento == null) {
			throw new GrupoCompartilhamentoInexistenteException();
		}
		
		return grupoCompartilhamento.getCompartilhamentoCadastro();
	}
	
	/**
	 * Consulta a pessoa utilizando o documento como filtro.
	 * @param idInstituicao a instituição que será usada para consultar a pessoa.
	 * @param cpfCnpj o CPF ou CNPJ da pessoa que será pesquisada.
	 * @return uma instancia de PessoaPlataformaVO contendo os dados da pessoa pesquisada.
	 * @throws GrupoCompartilhamentoInexistenteException caso não exista um 
	 * GrupoCompartilhamento para a instituição usada como filtro da pesquisa. 
	 */
	public Integer consultarIdPessoaPorDocumento(String cpfCnpj) throws BancoobException {
		Pessoa pessoa = pessoaServico.consultarPessoaPorDocumento(cpfCnpj);
		return pessoa.getId();
	}

	/**
	 * {@inheritDoc}
	 */
	public PessoaCompartilhamento obterPessoaPorIdPessoaLegado(Integer idInstituicao, Integer idPessoaLegado)
			throws BancoobException {
		
		Instituicao instituicao = new Instituicao();
		instituicao.setIdInstituicao(idInstituicao);

		return pessoaCompartilhamentoServico.consultarPessoaPorIdPessoaLegado(idPessoaLegado, instituicao);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Map<String, Object> obterDadosPessoaLegado(String cpfCnpj, Integer idInstituicao)
			throws BancoobException {
		return pessoaCompartilhamentoServico.obterDadosPessoaLegado(cpfCnpj, idInstituicao);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ConsultaDto<ProcurarPessoaExternoVO> procurarPessoaExterno(ConsultaDto<ProcurarPessoaExternoVO> criterios) throws BancoobException {
		return pessoaCompartilhamentoServico.procurarPessoaExterno(criterios);
	}

}