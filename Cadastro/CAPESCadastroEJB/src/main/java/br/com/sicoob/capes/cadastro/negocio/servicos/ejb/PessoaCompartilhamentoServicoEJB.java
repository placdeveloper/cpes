/*
 * SICOOB
 * 
 * PessoaCompartilhamentoServicoEJB.java(br.com.sicoob.capes.cadastro.negocio.servicos.ejb.PessoaCompartilhamentoServicoEJB)
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.hibernate.exception.ConstraintViolationException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.excecao.ViolacaoChavePrimariaException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.PerfilCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaInstituicaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.ValidacaoCadastralRegraDelegate;
import br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade.AtualizacaoCadastralFachada;
import br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade.AtualizacaoPessoaCompartilhamentoFachada;
import br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade.InclusaoPessoaLegadoFachada;
import br.com.sicoob.capes.cadastro.negocio.dominio.validacao.ValidacaoPessoa;
import br.com.sicoob.capes.cadastro.negocio.dominio.validacao.ValidacaoPessoaCompartilhamento;
import br.com.sicoob.capes.cadastro.negocio.dominio.validacao.ValidacaoPessoaFisica;
import br.com.sicoob.capes.cadastro.negocio.dominio.validacao.ValidacaoPessoaJuridica;
import br.com.sicoob.capes.cadastro.negocio.dto.AtualizacaoDataSFNDTO;
import br.com.sicoob.capes.cadastro.negocio.dto.AvalFinanceiraDTO;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaPessoaDTO;
import br.com.sicoob.capes.cadastro.negocio.enums.AtividadeEconomicaEnum;
import br.com.sicoob.capes.cadastro.negocio.enums.NacionalidadeEnum;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoAtualizacaoRenovacaoEnum;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoRelacionamentoPessoaEnum;
import br.com.sicoob.capes.cadastro.negocio.excecao.CAPESCadastroNegocioException;
import br.com.sicoob.capes.cadastro.negocio.excecao.ClienteNaoEncontradoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.ClienteNaoMigradoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.ExistePessoaComMesmoCpfCnpjException;
import br.com.sicoob.capes.cadastro.negocio.excecao.FiltroTamanhoMaximoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.FiltroTamanhoMinimoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.FormatoInvalidoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.GrupoCompartilhamentoInexistenteException;
import br.com.sicoob.capes.cadastro.negocio.excecao.PessoaComErrosCadastraisRestritivosException;
import br.com.sicoob.capes.cadastro.negocio.excecao.PessoaNaoEncontradaException;
import br.com.sicoob.capes.cadastro.negocio.excecao.PessoaPendenteAprovacaoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.RegistroNaoEncontradoNegocioException;
import br.com.sicoob.capes.cadastro.negocio.excecao.RenovacaoSomenteClientesException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.AnotacaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.AutorizacaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.CnaeFiscalServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.EmailServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.EnderecoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoCompartilhamentoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.LogCompartilhamentoCadastroServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.PessoaCompartilhamentoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.PessoaCompartilhamentoServicoRemote;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.PessoaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.RelacionamentoPessoaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ResponsavelCadastroServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TelefoneServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoAnotacaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoAtualizacaoRenovacaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoFormaConstituicaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoRelacionamentoPessoaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TransicaoPessoaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ValidacaoCadastralErroServicoLocal;
import br.com.sicoob.capes.cadastro.persistencia.dao.PessoaCompartilhamentoDAO;
import br.com.sicoob.capes.cadastro.util.IntegracaoUtil;
import br.com.sicoob.capes.comum.negocio.annotation.IgnorarAutorizar;
import br.com.sicoob.capes.comum.negocio.annotation.IntegracaoGedGft;
import br.com.sicoob.capes.comum.negocio.contexto.InformacoesUsuarioCAPES;
import br.com.sicoob.capes.comum.negocio.enums.OrigemInformacaoEnum;
import br.com.sicoob.capes.comum.negocio.enums.SituacaoCadastralRFBEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoAutomaticaEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoAtualizacaoCadastralEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoEmailEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoEnderecoEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoPerfilCadastroEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoSexoEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoTelefoneEnum;
import br.com.sicoob.capes.comum.negocio.vo.DadosCNPJVO;
import br.com.sicoob.capes.comum.negocio.vo.DadosCPFVO;
import br.com.sicoob.capes.comum.negocio.vo.DadosReceitaFederalVO;
import br.com.sicoob.capes.comum.negocio.vo.InstituicaoVO;
import br.com.sicoob.capes.comum.negocio.vo.PessoaPlataformaVO;
import br.com.sicoob.capes.comum.negocio.vo.ProcurarPessoaExternoVO;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.LOCIntegracaoDelegate;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;
import br.com.sicoob.capes.negocio.dominio.replicacao.facade.IAtualizacaoCadastralFachada;
import br.com.sicoob.capes.negocio.entidades.Anotacao;
import br.com.sicoob.capes.negocio.entidades.AtividadeEconomica;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.Cidadania;
import br.com.sicoob.capes.negocio.entidades.CnaeFiscal;
import br.com.sicoob.capes.negocio.entidades.CompartilhamentoCadastro;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Nacionalidade;
import br.com.sicoob.capes.negocio.entidades.OrigemInformacao;
import br.com.sicoob.capes.negocio.entidades.PerfilCadastro;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.TipoAnotacao;
import br.com.sicoob.capes.negocio.entidades.TipoConsultaOrigem;
import br.com.sicoob.capes.negocio.entidades.TipoEmail;
import br.com.sicoob.capes.negocio.entidades.TipoEndereco;
import br.com.sicoob.capes.negocio.entidades.TipoFormaConstituicao;
import br.com.sicoob.capes.negocio.entidades.TipoLogradouro;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;
import br.com.sicoob.capes.negocio.entidades.TipoRelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.TipoTelefone;
import br.com.sicoob.capes.negocio.entidades.TransicaoPessoa;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;
import br.com.sicoob.capes.negocio.entidades.legado.Lista;
import br.com.sicoob.capes.negocio.entidades.legado.ListaItem;
import br.com.sicoob.capes.negocio.entidades.legado.pk.ListaItemPK;
import br.com.sicoob.capes.negocio.entidades.pk.CidadaniaPK;
import br.com.sicoob.capes.negocio.entidades.vigente.Email;
import br.com.sicoob.capes.negocio.entidades.vigente.Endereco;
import br.com.sicoob.capes.negocio.entidades.vigente.GrupoCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaJuridica;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.ResponsavelCadastro;
import br.com.sicoob.capes.negocio.entidades.vigente.Telefone;
import br.com.sicoob.capes.replicacao.negocio.delegates.CAPESReplicacaoComumFabricaDelegates;

/**
 * Implementa as operações do serviço de pessoas.
 * 
 * @author Erico.Junior
 */
@Stateless
@Local({ PessoaCompartilhamentoServicoLocal.class })
@Remote({ PessoaCompartilhamentoServicoRemote.class })
@IntegracaoGedGft
public class PessoaCompartilhamentoServicoEJB extends EntidadeCadastroServicoEJB<PessoaCompartilhamento> implements PessoaCompartilhamentoServicoRemote,
		PessoaCompartilhamentoServicoLocal {

	/** A constante ID_TIPO_ANOTACAO_36_MESES_ATRASO. */
	private static final int ID_TIPO_ANOTACAO_36_MESES_ATRASO = 519;

	/** A constante NAO_INFORMADO. */
	private static final String NAO_INFORMADO = "NÃO INFORMADO";

	/** A constante PESQUISA_POR_CPF_CNPJ. */
	private static final String PESQUISA_POR_CPF_CNPJ = "POR CPF/CNPJ";
	
	private static final String PESQUISA_POR_CODIGO_COMPARTILHAMENTO = "POR CODIGO COMPARTILHAMENTO";
	
	@Inject
	@Default
	protected PessoaCompartilhamentoDAO dao;

	/** O atributo autorizacaoServicoLocal. */
	@EJB(mappedName = "capes/cadastro/AutorizacaoServico")
	private AutorizacaoServicoLocal autorizacaoServicoLocal;

	/** O atributo servicoAnotacao. */
	@EJB(mappedName = "capes/cadastro/AnotacaoServico")
	private AnotacaoServicoLocal servicoAnotacao;

	/** O atributo grupoCompartilhamentoServico. */
	@EJB(mappedName = "capes/cadastro/GrupoCompartilhamentoServico")
	private GrupoCompartilhamentoServicoLocal grupoCompartilhamentoServico;

	/** O atributo responsavelCadastroServico. */
	@EJB(mappedName = "capes/cadastro/ResponsavelCadastroServico")
	private ResponsavelCadastroServicoLocal responsavelCadastroServico;

	/** O atributo transicaoPessoaServico. */
	@EJB(mappedName = "capes/cadastro/TransicaoPessoaServico")
	private TransicaoPessoaServicoLocal transicaoPessoaServico;

	/** O atributo logCompartilhamentoServico. */
	@EJB(mappedName = "capes/cadastro/LogCompartilhamentoCadastroServico")
	private LogCompartilhamentoCadastroServicoLocal logCompartilhamentoServico;

	/** O atributo pessoaServico. */
	@EJB(mappedName = "capes/cadastro/PessoaServico")
	private PessoaServicoLocal pessoaServico;

	/** O atributo enderecoServico. */
	@EJB(mappedName = "capes/cadastro/EnderecoServico")
	private EnderecoServicoLocal enderecoServico;

	/** O atributo telefoneServico. */
	@EJB(mappedName = "capes/cadastro/TelefoneServico")
	private TelefoneServicoLocal telefoneServico;

	/** O atributo emailServico. */
	@EJB(mappedName = "capes/cadastro/EmailServico")
	private EmailServicoLocal emailServico;

	/** O atributo cnaeServico. */
	@EJB(mappedName = "capes/cadastro/CnaeFiscalServico")
	private CnaeFiscalServicoLocal cnaeServico;

	/** O atributo formaConstituicaoServico. */
	@EJB(mappedName = "capes/cadastro/TipoFormaConstituicaoServico")
	private TipoFormaConstituicaoServicoLocal formaConstituicaoServico;

	/** O atributo validacaoCadastralErroServicoLocal. */
	@EJB(mappedName = "capes/cadastro/ValidacaoCadastralErroServico")
	private ValidacaoCadastralErroServicoLocal validacaoCadastralErroServicoLocal;
	
	/** O atributo transicaoPessoaServico. */
	@EJB(mappedName = "capes/cadastro/RelacionamentoPessoaServico")
	private RelacionamentoPessoaServicoLocal relacionamentoPessoaServico;
	
	/** O atributo transicaoPessoaServico. */
	@EJB(mappedName = "capes/cadastro/TipoRelacionamentoPessoaServico")
	private TipoRelacionamentoPessoaServicoLocal tipoRelacionamentoPessoaServico;
	
	/** O atributo pessoaLegadoServico. */
	@EJB(mappedName = "capes/replicacao/PessoaServico")
	private br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.PessoaServicoLocal pessoaLegadoServico;
	
	/** O atributo anotacaoServico. */
	@EJB(mappedName = "capes/cadastro/AnotacaoServico")
	private AnotacaoServicoLocal anotacaoServico;
	
	/** O atributo tipoAnotacaoServico. */
	@EJB(mappedName = "capes/cadastro/TipoAnotacaoServico")
	private TipoAnotacaoServicoLocal tipoAnotacaoServico;
	
	/** O atributo TipoAtualizacaoRenovacaoServico. */
	@EJB(mappedName = "capes/cadastro/TipoAtualizacaoRenovacaoServico")
	private TipoAtualizacaoRenovacaoServicoLocal tipoAtualizacaoRenovacaoServico;
	
	@EJB
	private PessoaCompartilhamentoServicoLocal pessoaCompartilhamentoServico;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected PessoaCompartilhamentoDAO getDAO() {
		return dao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PessoaCompartilhamento consultarPessoaPorIdPessoaLegado(Integer idPessoaLegado) throws BancoobException {

		Instituicao instituicao = obterInstituicaoUsuarioLogado();
		return consultarPessoaPorIdPessoaLegado(idPessoaLegado, instituicao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PessoaCompartilhamento consultarPessoaPorIdPessoaLegado(Integer idPessoaLegado, Instituicao instituicao) throws BancoobException {

		getLogger().info("Consultando pessoa por idPessoaLegado: " + idPessoaLegado + ", idInstituicao: " + instituicao.getIdInstituicao());

		TransicaoPessoa transicao = new TransicaoPessoa();
		transicao.setIdPessoaLegado(idPessoaLegado);
		transicao.setInstituicao(instituicao);

		return consultarPessoaPorTransicaoPessoa(transicao);
	}

	/**
	 * Consulta {@link PessoaCompartilhamento} por documento CPF/CNPJ e ID da
	 * Instituição
	 * 
	 * @param idInstituicao
	 *            ID da instiuição
	 * @param cpfCnpj
	 *            CPF/CNPJ da pessoa
	 * @return {@link PessoaCompartilhamento}
	 * @throws BancoobException
	 */
	@Override
	public PessoaCompartilhamento consultarPessoaPorDocumento(Integer idInstituicao, String cpfCnpj) throws BancoobException {

		getLogger().debug("Consultando pessoa por documento: " + cpfCnpj + ", idInstituicao: " + idInstituicao);
		PessoaCompartilhamento pessoa = null;

		try {

			if (cpfCnpj != null && !cpfCnpj.trim().equals("") && idInstituicao != null) {
				CompartilhamentoCadastro compartilhamentoCadastro = obterCompartilhamentoCadastro(idInstituicao);
				pessoa = consultarPessoaPorDocumentoCodCompartilhamento(compartilhamentoCadastro.getCodigo(), cpfCnpj);
			}

		} catch (ClienteNaoEncontradoException ex) {
			getLogger().info(cpfCnpj + " não cadastrado.");
			throw new PessoaNaoEncontradaException(ex);
		}

		return pessoa;
	}

	/**
	 * Consulta {@link PessoaCompartilhamento} por documento CPF/CNPJ e o código
	 * do compartilhamento cadastro
	 * 
	 * @param codCompartilhamentoCadastro
	 *            Código do compartilhamento {@link CompartilhamentoCadastro}
	 * @param documento
	 *            CPF/CNPJ da pessoa
	 * @return {@link PessoaCompartilhamento}
	 * @throws BancoobException
	 */
	@Override
	public PessoaCompartilhamento consultarPessoaPorDocumentoCodCompartilhamento(Short codCompartilhamentoCadastro, String cpfCnpj) throws BancoobException {

		PessoaCompartilhamento pessoa = null;

		try {

			if (cpfCnpj != null && !cpfCnpj.trim().equals("") && codCompartilhamentoCadastro != null) {
				pessoa = getDAO().consultarPessoaPorDocumento(codCompartilhamentoCadastro, cpfCnpj);
			}

		} catch (ClienteNaoEncontradoException ex) {
			getLogger().info(cpfCnpj + " não cadastrado.");
			throw new PessoaNaoEncontradaException(ex);
		}
		return pessoa;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PessoaCompartilhamento consultarPessoaPorTransicaoPessoa(TransicaoPessoa transicaoPessoa) throws BancoobException {

		PessoaCompartilhamento pessoa = null;

		try {

			pessoa = getDAO().consultarPessoa(transicaoPessoa);

		} catch (ClienteNaoEncontradoException ex) {
			throw new ClienteNaoMigradoException(ex);
		}

		return pessoa;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterarSemReplicacao(PessoaCompartilhamento pessoa) throws BancoobException {
		getDAO().alterar(pessoa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void incluirSemReplicacao(PessoaCompartilhamento pessoa) throws BancoobException {
		atribuirDadosIniciais(pessoa);
		getDAO().incluir(pessoa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@IgnorarAutorizar
	public PessoaCompartilhamento incluir(PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException {
		CompartilhamentoCadastro compartilhamento = obterCompartilhamentoUsuario();
		pessoaCompartilhamento.setCompartilhamento(compartilhamento);
		return incluir(pessoaCompartilhamento, new DateTimeDB(), obterInstituicaoUsuarioLogado());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@IgnorarAutorizar
	public PessoaCompartilhamento incluir(PessoaCompartilhamento pessoaCompartilhamento, DadosReceitaFederalVO dadosReceita) throws BancoobException {
		preencherDadosReceita(pessoaCompartilhamento, dadosReceita);
		PessoaCompartilhamento pessoaIncluida = pessoaCompartilhamentoServico.incluirComDadosReceita(pessoaCompartilhamento);
		pessoaCompartilhamentoServico.incluirContatosReceitaPJ(pessoaIncluida, dadosReceita);
		pessoaCompartilhamentoServico.incluirAnotacaoReceita(pessoaIncluida, dadosReceita);
		return pessoaIncluida;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@IgnorarAutorizar
	public PessoaCompartilhamento incluir(PessoaCompartilhamento pessoaCompartilhamento, Integer numeroCooperativa, Integer unidadeInstituicao) throws BancoobException {
		CompartilhamentoCadastro compartilhamentoCadastro = null;
		Instituicao instituicao = null;
		if (numeroCooperativa != null && unidadeInstituicao != null) {
			instituicao = obterInstituicao(numeroCooperativa, unidadeInstituicao);
			compartilhamentoCadastro = obterCompartilhamentoCadastro(instituicao.getIdInstituicao());
		} else {
			instituicao = obterInstituicaoUsuarioLogado();
			compartilhamentoCadastro = obterCompartilhamentoUsuario();
		}
		pessoaCompartilhamento.setCompartilhamento(compartilhamentoCadastro);
		pessoaCompartilhamento.setPerfilCadastro(obterPerfilCadastroPadrao());
		
		consultarCpfCnpjParaInclusao(pessoaCompartilhamento.getPessoa().getCpfCnpj(), pessoaCompartilhamento.getPessoa().getTipoPessoa());
		try {
			return incluir(pessoaCompartilhamento, new DateTimeDB(), instituicao);
		} catch (BancoobException e) {
			throw new ExistePessoaComMesmoCpfCnpjException(e);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@IgnorarAutorizar
	public PessoaCompartilhamento incluir(PessoaCompartilhamento pessoaCompartilhamento, DadosReceitaFederalVO dadosReceita, Integer numeroCooperativa, Integer unidadeInstituicao) throws BancoobException {
		preencherDadosReceita(pessoaCompartilhamento, dadosReceita);
		PessoaCompartilhamento pessoaIncluida = pessoaCompartilhamentoServico.incluirComDadosReceita(pessoaCompartilhamento, numeroCooperativa, unidadeInstituicao);
		pessoaCompartilhamentoServico.incluirContatosReceitaPJ(pessoaIncluida, dadosReceita);
		pessoaCompartilhamentoServico.incluirAnotacaoReceita(pessoaIncluida, dadosReceita);
		return pessoaIncluida;
	}
	
	/**
	 * Obter tipo anotacao.
	 * 
	 * @param situacaoCadastral
	 *            the situacao cadastral
	 * @param tipoPessoa
	 *            the tipo pessoa
	 * @return tipo anotacao
	 */
	private TipoAnotacao obterTipoAnotacao(SituacaoCadastralRFBEnum situacaoCadastral, TipoPessoaEnum tipoPessoa) {
		Integer codigoTipoAnotacao = null;
		if (SituacaoCadastralRFBEnum.REGULAR.equals(situacaoCadastral) || SituacaoCadastralRFBEnum.ATIVA.equals(situacaoCadastral)) {
			codigoTipoAnotacao = TipoAnotacaoAutomaticaEnum.RECEITA_NADA_CONSTA.getCodTipoAnotacao();
		} else {
			if (TipoPessoaEnum.PESSOA_FISICA.equals(tipoPessoa)) {
				if (SituacaoCadastralRFBEnum.CANCELADA_MULTIPLICIDADE.equals(situacaoCadastral)) {
					codigoTipoAnotacao = TipoAnotacaoAutomaticaEnum.CPF_IRREGULAR_CANCELADO_POR_MULTIPLICIDADE.getCodTipoAnotacao();
				} else if (SituacaoCadastralRFBEnum.NULA.equals(situacaoCadastral)) {
					codigoTipoAnotacao = TipoAnotacaoAutomaticaEnum.CPF_IRREGULAR_NULO.getCodTipoAnotacao();
				} else if (SituacaoCadastralRFBEnum.CANCELADA_OFICIO.equals(situacaoCadastral)) {
					codigoTipoAnotacao = TipoAnotacaoAutomaticaEnum.CPF_IRREGULAR_CANCELADO_POR_OFICIO.getCodTipoAnotacao();
				} else if (SituacaoCadastralRFBEnum.SUSPENSA.equals(situacaoCadastral)) {
					codigoTipoAnotacao = TipoAnotacaoAutomaticaEnum.CPF_IRREGULAR_SUSPENSA.getCodTipoAnotacao();
				} else if (SituacaoCadastralRFBEnum.CANCELADA_OBITO_SEM_ESPOLIO.equals(situacaoCadastral)) {
					codigoTipoAnotacao = TipoAnotacaoAutomaticaEnum.CPF_IRREGULAR_TITULAR_FALECIDO.getCodTipoAnotacao();
				} else if (SituacaoCadastralRFBEnum.PENDENTE_REGULARIZACAO.equals(situacaoCadastral)) {
					codigoTipoAnotacao = TipoAnotacaoAutomaticaEnum.CPF_IRREGULAR_PENDENTE_REGULARIZACAO.getCodTipoAnotacao();
				}
			} else if (TipoPessoaEnum.PESSOA_JURIDICA.equals(tipoPessoa)) {
				codigoTipoAnotacao = TipoAnotacaoAutomaticaEnum.RECEITA_CNPJ_IRREGULAR.getCodTipoAnotacao();
			}
		}
		return new TipoAnotacao(codigoTipoAnotacao);
	}

	/**
	 * Obter tipo consulta origem p.
	 * 
	 * @param tipoPessoa
	 *            the tipo pessoa
	 * @return tipo consulta origem
	 */
	private TipoConsultaOrigem obterTipoConsultaOrigem(TipoPessoaEnum tipoPessoa) {

		TipoConsultaOrigem tipoConsulta = null;
		if (TipoPessoaEnum.PESSOA_FISICA.equals(tipoPessoa)) {
			tipoConsulta = new TipoConsultaOrigem(TipoConsultaOrigem.RFB_FISICA);
		} else if (TipoPessoaEnum.PESSOA_JURIDICA.equals(tipoPessoa)) {
			tipoConsulta = new TipoConsultaOrigem(TipoConsultaOrigem.RFB_JURIDICA);
		}
		return tipoConsulta;
	}

	/**
	 * O método Preencher dados receita.
	 *
	 * @param pessoaCompartilhamento o valor de pessoa compartilhamento
	 * @param dadosReceita o valor de dados receita
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void preencherDadosReceita(PessoaCompartilhamento pessoaCompartilhamento, DadosReceitaFederalVO dadosReceita) throws BancoobException {

		Pessoa pessoa = pessoaCompartilhamento.getPessoa();
		SituacaoCadastralRFBEnum situacaoCadastral = SituacaoCadastralRFBEnum.valueOf(dadosReceita.getCodigoSituacaoCadastral(), pessoa.getTipoPessoa()
				.getCodTipoPessoa());

		pessoa.setSituacaoCadastralRFB(situacaoCadastral);
		pessoa.setNomeRFB(dadosReceita.getNome());
		if (pessoaCompartilhamento instanceof PessoaFisica) {
			DadosCPFVO vo = (DadosCPFVO) dadosReceita;
			PessoaFisica pf = (PessoaFisica) pessoaCompartilhamento;
			pf.setNomeMae(StringUtils.left(vo.getNomeMae(), 50));
			pf.setDataNascimento(vo.getDataNascimento() == null ? null : new DateTimeDB(vo.getDataNascimento().getTime()));
			pf.setTipoSexo(vo.getTipoSexo().getCodigo());
		} else if (pessoaCompartilhamento instanceof PessoaJuridica) {
			DadosCNPJVO vo = (DadosCNPJVO) dadosReceita;
			PessoaJuridica pj = (PessoaJuridica) pessoaCompartilhamento;
			pj.setDataConstituicao(new DateTimeDB(vo.getDataAbertura().getTime()));
			pj.setCnae(obterCnae(vo.getCnaePrincipal()));
			pj.setFormaConstituicao(obterFormaConstituicao(vo));
		}
	}

	/**
	 * Obter forma constituicao.
	 *
	 * @param vo o valor de vo
	 * @return TipoFormaConstituicao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private TipoFormaConstituicao obterFormaConstituicao(DadosCNPJVO vo) throws BancoobException {

		return formaConstituicaoServico.obter(vo.getCodigoFormaConstituicao());
	}

	/**
	 * Obter cnae.
	 *
	 * @param codigoCnae o valor de codigo cnae
	 * @return CnaeFiscal
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected CnaeFiscal obterCnae(String codigoCnae) throws BancoobException {

		CnaeFiscal cnae = null;
		CnaeFiscal filtro = new CnaeFiscal();
		filtro.setCodigo(codigoCnae);

		ConsultaDto<CnaeFiscal> dto = new ConsultaDto<CnaeFiscal>();
		dto.setFiltro(filtro);
		List<CnaeFiscal> resultado = cnaeServico.pesquisar(dto).getResultado();

		if ((resultado != null) && !resultado.isEmpty()) {
			cnae = resultado.get(0);
		}
		return cnae;
	}

	/**
	 * Realiza a inclusao de {@link Endereco} com dados oriundos da Receita
	 * Federal
	 * 
	 * @param pj
	 *            a pessoa para a qual serão incluidos os contatos
	 * @param vo
	 *            os dados recebidos da Receita Federal
	 * @throws BancoobException
	 */
	private void incluirEndereco(PessoaJuridica pj, DadosCNPJVO vo) throws BancoobException {
		LOCIntegracaoDelegate locDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarLOCIntegracaoDelegate();
		TipoLogradouro tipoLogradouro = IntegracaoUtil.converterTipoLogradouro(locDelegate.obterTipoLogradouroRFB(vo.getTipoLogradouro()));

		String logradouro = StringUtils.strip(vo.getLogradouro());
		Pattern pattern = Pattern.compile("^" + tipoLogradouro.getNome().toUpperCase() + "\\s+");

		if (logradouro != null) {
			Matcher matcher = pattern.matcher(logradouro.toUpperCase());
			if (matcher.find()) {
				logradouro = logradouro.substring(matcher.end());
			}
		}

		try {

			Endereco endereco = new Endereco();
			endereco.setBairro(StringUtils.isBlank(vo.getBairro()) ? NAO_INFORMADO : StringUtils.left(vo.getBairro(), 30));
			endereco.setCep(StringUtils.isBlank(vo.getCep().toString()) ? "00000000" : vo.getCep().toString());
			endereco.setComplemento(StringUtils.left(vo.getComplemento(), 20));
			endereco.setDescricao(StringUtils.isBlank(logradouro) ? NAO_INFORMADO : StringUtils.left(logradouro, 40));
			endereco.setLocalidade(IntegracaoUtil.converterLocalidade(locDelegate.obterLocalidadeRFB(vo.getCodigoMunicipio())));
			endereco.setNumero(StringUtils.right(vo.getNumeroLogradouro(), 6));
			endereco.setTipoLogradouro(tipoLogradouro);
			endereco.setTipoEndereco(new TipoEndereco());
			endereco.getTipoEndereco().setCodigo(TipoEnderecoEnum.COMERCIAL.getCodigo());
			endereco.setVerificarAutorizacao(false);
			endereco.setPessoaCompartilhamento(pj);
			enderecoServico.incluir(endereco);
		} catch (NegocioException e) {
			getLogger().info("O endereco vindo da receita nao foi incluido devido a validacao:" + e.getMessage());
		} catch (ViolacaoChavePrimariaException e) {
			getLogger().erro(
					e,
					"O endereco recebido da Refeita Federal para a pessoa juridica \"" + pj.getIdPessoaCompartilhamento()
							+ "\" esta incompleto e nao sera cadastrado: " + vo);
		} catch (ConstraintViolationException e) {
			getLogger().erro(
					e,
					"O endereco recebido da Refeita Federal para a pessoa juridica \"" + pj.getIdPessoaCompartilhamento()
							+ "\" esta incompleto e nao sera cadastrado: " + vo);
		} catch (BancoobException e) {
			getLogger().erro(e, "Erro ao incluir o endereco da receita federal");
		}
	}

	/**
	 * Realiza a inclusao de {@link Telefone} com dados oriundos da Receita
	 * Federal
	 * 
	 * @param pj
	 *            a pessoa para a qual serão incluidos os contatos
	 * @param ddd
	 *            o codigo DDD recebido da Receita Federal
	 * @param numero
	 *            o numero do telefone recebido da Receita Federal
	 * @throws BancoobException
	 */
	private void incluirTelefone(PessoaJuridica pj, String ddd, String numero) throws BancoobException {
		if (StringUtils.isNotBlank(numero) && StringUtils.isNumeric(numero)) {
			try {
				Telefone telefone = new Telefone();
				telefone.setDdd(StringUtils.isBlank(ddd) ? null : StringUtils.right(ddd, 2));
				telefone.setPessoaCompartilhamento(pj);
				telefone.setTelefone(StringUtils.right(numero, 12));
				telefone.setTipoTelefone(new TipoTelefone());
				telefone.getTipoTelefone().setCodigo(TipoTelefoneEnum.COMERCIAL.getCodigo());
				telefoneServico.incluir(telefone);
			} catch (NegocioException e) {
				getLogger().info("O telefone vindo da receita nao foi incluido devido a validacao: " + e.getMessage());
			} catch (BancoobException e) {
				getLogger().erro(e, "Erro ao incluir o telefone vindo da receita.");
			}
		}
	}

	/**
	 * Realiza a inclusao de {@link Email} com dados oriundos da Receita Federal
	 * 
	 * @param pj
	 *            a pessoa para a qual serão incluidos os contatos
	 * @param endereco
	 *            o endereco de e-mail recebido da Receita Federal
	 * @throws BancoobException
	 */
	private void incluirEmail(PessoaJuridica pj, String endereco) throws BancoobException {
		if (StringUtils.isNotBlank(endereco)) {
			try {
				Email email = new Email();
				email.setDescricao(endereco);
				email.setTipoEmail(new TipoEmail());
				email.getTipoEmail().setCodigo(TipoEmailEnum.COMERCIAL.getCodigo());
				email.setPessoaCompartilhamento(pj);
				emailServico.incluir(email);
			} catch (NegocioException e) {
				getLogger().info("O email da receita nao foi incluido devido a validacao: " + e.getMessage());
			} catch (BancoobException e) {
				getLogger().erro(e, "Erro ao incluir o email vindo da receita.");
			}
		}
	}

	/**
	 * Obter compartilhamento usuario.
	 *
	 * @return CompartilhamentoCadastro
	 */
	private CompartilhamentoCadastro obterCompartilhamentoUsuario() {
		InformacoesUsuarioCAPES informacoes = InformacoesUsuarioCAPES.getInstance();
		CompartilhamentoCadastro compartilhamento = null;
		if (informacoes != null && informacoes.getCodigoCompartilhamento() != null) {
			compartilhamento = new CompartilhamentoCadastro();
			compartilhamento.setCodigo(informacoes.getCodigoCompartilhamento());
		}
		return compartilhamento;
	}

	/**
	 * Incluir pessoa.
	 *
	 * @param pessoa o valor de pessoa
	 * @return Pessoa
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private Pessoa incluirPessoa(Pessoa pessoa) throws BancoobException {

		Pessoa pessoaPersistente = null;
		try {
			pessoaPersistente = pessoaServico.consultarPessoaPorDocumento(pessoa.getCpfCnpj());
		} catch (PessoaNaoEncontradaException e) {
			pessoaPersistente = pessoaServico.incluir(pessoa);
		}

		return pessoaPersistente;
	}

	/**
	 * Valida antes de incluir se o cpf ou cnpj é válido, se o mesmo já
	 * existe cadastrado para outra pessoa e se a atividade economica pode ser
	 * atribuída.
	 * 
	 * @param pessoaCompartilhamento
	 *            A pessoa a ser validada.
	 * @throws BancoobException
	 *             Caso ocorra problemas na validação.
	 */
	@Override
	public void validarIncluir(PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException {
		validarDadosPessoa(pessoaCompartilhamento);
		consultarCpfCnpjParaInclusao(pessoaCompartilhamento.getPessoa().getCpfCnpj(), pessoaCompartilhamento.getPessoa().getTipoPessoa());
	}

	/**
	 * Inclui uma pessoa no legado.
	 * 
	 * @param pessoa
	 *            A pessoa que será incluída no legado.
	 * @param idInstituicao
	 *            O identificador da instituição na qual a pessoa será
	 *            incluída.
	 * @return o identificador da pessoa incluída no legado.
	 * @throws BancoobException
	 */
	private Integer incluirPessoaLegado(PessoaCompartilhamento pessoa, Integer idInstituicao) throws BancoobException {
		InclusaoPessoaLegadoFachada<PessoaCompartilhamento> fachada = new InclusaoPessoaLegadoFachada<PessoaCompartilhamento>();
		br.com.sicoob.capes.negocio.entidades.legado.Pessoa pessoaLegado = fachada.criarPessoaLegado(pessoa);
		return pessoaLegadoServico.incluir(pessoaLegado, idInstituicao).getId();
	}

	/**
	 * Inclui uma transição para a pessoa informada na instituição do
	 * usuário logado.
	 * 
	 * @param pessoa
	 *            A pessoa que terá uma transição com a instituição.
	 * @param dataIntegracao
	 *            A data de integração.
	 * @param idPessoaLegado
	 *            O identificador da pessoa no sistema legado.
	 * @param instituicao
	 *            A instituição para a transição.
	 * @param idPessoaLegadoBancoob 
	 * @param pessoaIncluidaBancoob 
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	private void incluirTransicao(PessoaCompartilhamento pessoa, Date dataIntegracao, Integer idPessoaLegado, Instituicao instituicao) throws BancoobException {

		TransicaoPessoa transicao = new TransicaoPessoa();
		transicao.setDataHoraIntegracao(dataIntegracao);
		transicao.setIdPessoaLegado(idPessoaLegado);
		transicao.setInstituicao(instituicao);
		transicao.setNomePessoaLegado(pessoa.getNomePessoa());
		transicao.setPessoaCompartilhamento(pessoa);
		transicaoPessoaServico.incluir(transicao);
	}

	/**
	 * Inclui a instituição como responsável pelo cadastro da pessoa
	 * informada.
	 * 
	 * @param pessoa
	 *            A pessoa a ser incluída.
	 * @param idInstituicaoResponsavel
	 *            O identificador da instituição responsável pelo
	 *            cadastro.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	private void incluirResponsavelCadastro(PessoaCompartilhamento pessoa, Integer idInstituicaoResponsavel) throws BancoobException {

		ResponsavelCadastro responsavel = new ResponsavelCadastro();
		responsavel.setIdInstituicao(idInstituicaoResponsavel);
		responsavel.setPessoa(pessoa);
		responsavelCadastroServico.incluir(responsavel);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(PessoaCompartilhamento objeto) throws BancoobException {
		alterarPessoaCompartilhamento(objeto);
		PessoaCompartilhamento pessoa = obter(objeto.getId());
		if(isRegistroVigente(pessoa)){
			if(objeto.getIdUsuarioEnvio() != null){
				this.renovarCadastroAutomatico(objeto, objeto.getPessoaCompartilhamento().getPessoa().getIdPessoa(), objeto.getIdUsuarioEnvio());
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarAlterar(PessoaCompartilhamento pessoa) throws BancoobException {
		validarDadosPessoa(pessoa);
	}

	/**
	 * Validação de obrigatoriedade para a entidade Pessoa.
	 * 
	 * @param pessoa
	 *            A pessoa a ser validada.
	 * @throws BancoobException
	 *             Caso alguma validação falhe.
	 */
	private void validarDadosPessoa(PessoaCompartilhamento pessoa) throws BancoobException {

		ValidacaoPessoaCompartilhamento<?> validacao = null;

		if (pessoa instanceof PessoaFisica) {
			validacao = new ValidacaoPessoaFisica();
		} else {
			validacao = new ValidacaoPessoaJuridica();
		}

		validacao.validar(pessoa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConsultaDto<PessoaCompartilhamento> pesquisar(ConsultaDto<PessoaCompartilhamento> criterios) throws BancoobException {

		validarPesquisarPessoa(criterios.getTipoProcura(), criterios.getProcurarPor());
		return super.pesquisar(criterios);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ConsultaDto<PessoaPlataformaVO> pesquisarPessoaProxy(ConsultaDto<PessoaPlataformaVO> criterios) throws BancoobException {

		validarPesquisarPessoa(criterios.getTipoProcura(), criterios.getProcurarPor());

		Instituicao instituicao = obterInstituicaoUsuarioLogado();
		CompartilhamentoCadastro compartilhamentoCadastro = obterCompartilhamentoCadastro(instituicao.getIdInstituicao());

		PessoaPlataformaVO pessoaProxy = (PessoaPlataformaVO) criterios.getFiltro();
		if (pessoaProxy == null) {
			pessoaProxy = new PessoaPlataformaVO();
		}
		pessoaProxy.setIdInstituicao(instituicao.getIdInstituicao());
		pessoaProxy.setCodCompartilhamentoCadastro(compartilhamentoCadastro.getCodigo());

		criterios.setFiltro(pessoaProxy);

		return getDAO().pesquisarPessoaProxy(criterios);
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ConsultaDto<PessoaPlataformaVO> pesquisarPessoaProxyResumido(ConsultaDto<PessoaPlataformaVO> criterios) throws BancoobException {
		
		if(criterios.getProcurarPor().indexOf("'")>= 0){
			criterios.setProcurarPor(criterios.getProcurarPor().replace("'", "''"));
		}
		validarPesquisarPessoa(criterios.getTipoProcura(), criterios.getProcurarPor());

		Instituicao instituicao = obterInstituicaoUsuarioLogado();
		CompartilhamentoCadastro compartilhamentoCadastro = obterCompartilhamentoCadastro(instituicao.getIdInstituicao());

		PessoaPlataformaVO pessoaProxy = (PessoaPlataformaVO) criterios.getFiltro();
		if (pessoaProxy == null) {
			pessoaProxy = new PessoaPlataformaVO();
		}
		pessoaProxy.setIdInstituicao(instituicao.getIdInstituicao());
		pessoaProxy.setCodCompartilhamentoCadastro(compartilhamentoCadastro.getCodigo());

		criterios.setFiltro(pessoaProxy);

		return getDAO().pesquisarPessoaProxyResumido(criterios);
	}

	/**
	 * Validações feitas na listagem de pessoa para garantir que exista um
	 * filtro válido.
	 * 
	 * @param nome
	 *            O nome utilizado na pesquisa.
	 * @throws FiltroTamanhoMinimoException
	 *             Caso o número mínimo de caracteres exigido para o filtro
	 *             não seja informado.
	 * @throws FiltroTamanhoMaximoException
	 *             Caso o número máximo de caracteres exigido para o filtro
	 *             seja excedido.
	 * @throws FormatoInvalidoException
	 *             Caso o formato do filtro seja inválido. Ex: "An%"
	 */
	private void validarPesquisarPessoa(String tipoProcura, String procurarPor) throws FiltroTamanhoMinimoException, FormatoInvalidoException, FiltroTamanhoMaximoException {

		if (!PESQUISA_POR_CODIGO_COMPARTILHAMENTO.equals(tipoProcura)) {
			if (StringUtils.isEmpty(procurarPor) || procurarPor.length() < 3) {
				throw new FiltroTamanhoMinimoException("3 (três)");
			}
		}

		if (PESQUISA_POR_CPF_CNPJ.equals(tipoProcura) && procurarPor.length() > 14) {
			throw new FiltroTamanhoMaximoException("14 (quatorze)");
		}

		if (procurarPor.indexOf('%') >= 0) {
			throw new FormatoInvalidoException("Filtro");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PessoaCompartilhamento consultar(Pessoa pessoa, CompartilhamentoCadastro compartilhamentoCadastro) throws BancoobException {
		return getDAO().consultar(pessoa, compartilhamentoCadastro);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer pesquisarCodigoCompartilhamento(Instituicao instituicao) throws BancoobException {
		return getDAO().pesquisarCodigoCompartilhamento(instituicao);
	}

	/**
	 * Recupera o compartilhamento cadastro usando a instiuição como filtro.
	 * 
	 * @param idInstituicao
	 *            a instiuição que será usada como filtro.
	 * @return o compartilhamento cadastro.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção
	 */
	private CompartilhamentoCadastro obterCompartilhamentoCadastro(Integer idInstituicao) throws BancoobException {

		GrupoCompartilhamento grupoCompartilhamento = grupoCompartilhamentoServico.obterPorInstituicao(idInstituicao);

		if (grupoCompartilhamento == null) {
			throw new GrupoCompartilhamentoInexistenteException();
		}

		return grupoCompartilhamento.getCompartilhamentoCadastro();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PessoaCompartilhamento consultarCpfCnpjParaInclusao(String cpfCnpj, TipoPessoa tipoPessoa) throws BancoobException {
		return consultarCpfCnpjParaInclusao(cpfCnpj, tipoPessoa, obterInstituicaoUsuarioLogado());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public PessoaCompartilhamento consultarCpfCnpjParaInclusao(String cpfCnpj, TipoPessoa tipoPessoa, Integer numeroCooperativa) throws BancoobException {
		Instituicao instituicao = null;
		if(numeroCooperativa != null) {
			instituicao = obterInstituicao(numeroCooperativa, null);
		}else {
			instituicao = obterInstituicaoUsuarioLogado();
		}
		return consultarCpfCnpjParaInclusao(cpfCnpj, tipoPessoa, instituicao);
	}

	/**
	 * Obtém a instituição à partir do número da cooperativa.
	 * 
	 * @param numeroCooperativa
	 * @return
	 * @throws BancoobException
	 */
	private Instituicao obterInstituicao(Integer numeroCooperativa, Integer unidadeInstituicao) throws BancoobException {
		Instituicao instituicao;
		SCIIntegracaoDelegate sciDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();
		InstituicaoVO instituicaoInformada = sciDelegate.obterInstituicaoPorNumeroCooperativa(numeroCooperativa);

		instituicao = new Instituicao();
		instituicao.setIdInstituicao(instituicaoInformada.getIdInstituicao());
		instituicao.setNumero(instituicaoInformada.getNumero().toString());
		instituicao.setIdUnidadeInst(unidadeInstituicao);
		return instituicao;
	}
	
	/**
	 * 
	 * @param cpfCnpj
	 * @param tipoPessoa
	 * @param instituicao
	 * @return
	 * @throws BancoobException
	 */
	private PessoaCompartilhamento consultarCpfCnpjParaInclusao(String cpfCnpj, TipoPessoa tipoPessoa, Instituicao instituicao) throws BancoobException {
		validarDocumento(cpfCnpj, tipoPessoa);
		PessoaCompartilhamento pessoa = recuperarPessoa(cpfCnpj, instituicao);
		if (pessoa != null) {
			TransicaoPessoa transicao = recuperarTransicao(pessoa, instituicao);
			if (transicao != null) {
				ExistePessoaComMesmoCpfCnpjException e = new ExistePessoaComMesmoCpfCnpjException();
				getLogger().erro(e, cpfCnpj + " ja existe na cooperativa " + instituicao.getNumero() + "(" + instituicao.getIdInstituicao() + ")");
				throw e;
			}
			logCompartilhamentoServico.incluir(pessoa);
		}

		return pessoa;
	}
	
	/**
	 * Metodo para recuperar a pessoa por tipo, cooperativa e cpf/cnpj
	 * 
	 * @param numCooperativa
	 * @param tipoPessoa
	 * @param cpfCnpj
	 * @return
	 * @throws BancoobException
	 */
	@Override
	public PessoaCompartilhamento consultarPessoa(String cpfCnpj, TipoPessoa tipoPessoa, Integer numeroCooperativa) throws BancoobException {
		validarDocumento(cpfCnpj, tipoPessoa);
		Instituicao instituicao = null;
		instituicao = obterInstituicao(numeroCooperativa, null);
		PessoaCompartilhamento pessoa = recuperarPessoa(cpfCnpj, instituicao);
		
		return pessoa;
	}

	/**
	 * Recupera a transição da pessoa para a instituição informada.
	 * 
	 * @param pessoa
	 *            A pessoa a ser verificada.
	 * @param instituicao
	 *            A instituição a ser verificada.
	 * @return a transição da pessoa para a instituição informada ou
	 *         null caso não exista transição com a instituição.
	 */
	private TransicaoPessoa recuperarTransicao(PessoaCompartilhamento pessoa, Instituicao instituicao) {

		TransicaoPessoa transicaoEncontrada = null;
		Set<TransicaoPessoa> transicoes = pessoa.getTransicoes();

		if (transicoes != null && !transicoes.isEmpty()) {

			for (TransicaoPessoa transicao : transicoes) {
				if (instituicao.equals(transicao.getInstituicao())) {
					transicaoEncontrada = transicao;
					break;
				}
			}
		}

		return transicaoEncontrada;
	}

	/**
	 * O método Validar documento.
	 *
	 * @param cpfCnpj o valor de cpf cnpj
	 * @param tipoPessoa o valor de tipo pessoa
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarDocumento(String cpfCnpj, TipoPessoa tipoPessoa) throws BancoobException {

		Pessoa pessoa = new Pessoa();
		pessoa.setCpfCnpj(cpfCnpj);
		pessoa.setTipoPessoa(tipoPessoa);

		ValidacaoPessoa validacao = new ValidacaoPessoa();
		validacao.validar(pessoa);
	}

	/**
	 * Recuperar pessoa.
	 * 
	 * @param cpfCnpj
	 *            o valor de cpf cnpj
	 * @param instituicao
	 * @return
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	private PessoaCompartilhamento recuperarPessoa(String cpfCnpj, Instituicao instituicao) throws BancoobException {
		PessoaCompartilhamento pessoa = null;
		try {
			if (cpfCnpj != null) {
				pessoa = consultarPessoaPorDocumento(instituicao.getIdInstituicao(), cpfCnpj);
			}
		} catch (PessoaNaoEncontradaException e) {
			getLogger().erro(e, cpfCnpj + " não existe na base.");
		}
		return pessoa;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PessoaCompartilhamento incluir(PessoaCompartilhamento pessoaCompartilhamento, DateTimeDB dataInclusao, Instituicao instituicao)
			throws BancoobException {

		validarDadosPessoa(pessoaCompartilhamento);

		Pessoa pessoa = null;
		Long idPessoa = pessoaServico.consultarPessoaPorCpfCnpjUR(pessoaCompartilhamento.getPessoa().getCpfCnpj());
		if (idPessoa == null) {
			pessoa = incluirPessoa(pessoaCompartilhamento.getPessoa());
		} else {
			pessoa = CAPESCadastroFabricaDelegates.getInstance().criarPessoaDelegate().obter(idPessoa.intValue());
		}

		Integer idInstituicao = instituicao.getIdInstituicao();
		pessoaCompartilhamento.setCompartilhamento(obterCompartilhamentoCadastro(idInstituicao));
		pessoaCompartilhamento.setPessoa(pessoa);
		pessoaCompartilhamento.setDataInclusaoSFN(null);
		pessoaCompartilhamento.setDataInclusaoSistema(dataInclusao);
		pessoaCompartilhamento = atribuirDadosIniciais(pessoaCompartilhamento);

		PessoaCompartilhamento pessoaIncluida = getDAO().incluir(pessoaCompartilhamento);
		Integer idPessoaLegado = incluirPessoaLegado(pessoaIncluida, idInstituicao);
		incluirTransicao(pessoaIncluida, dataInclusao, idPessoaLegado, instituicao);
		incluirResponsavelCadastro(pessoaIncluida, idInstituicao);

		return pessoaIncluida;
	}
	
	/**
	 * Atribuir dados iniciais.
	 *
	 * @param pessoaCompartilhamento o valor de pessoa compartilhamento
	 * @return PessoaCompartilhamento
	 */
	private PessoaCompartilhamento atribuirDadosIniciais(PessoaCompartilhamento pessoaCompartilhamento) {

		if (pessoaCompartilhamento instanceof PessoaFisica) {
			pessoaCompartilhamento = atribuirDadosIniciaisPF(pessoaCompartilhamento);
		} else if (pessoaCompartilhamento instanceof PessoaJuridica) {
			pessoaCompartilhamento = atribuirDadosIniciaisPJ(pessoaCompartilhamento);
		}

		return pessoaCompartilhamento;
	}

	/**
	 * Obter atividade economica default.
	 *
	 * @param tipoPessoa o valor de tipo pessoa
	 * @return AtividadeEconomica
	 */
	private AtividadeEconomica obterAtividadeEconomicaDefault(TipoPessoaEnum tipoPessoa) {

		AtividadeEconomica atividade = new AtividadeEconomica();
		Short codigoAtividade = null;
		if (TipoPessoaEnum.PESSOA_FISICA.equals(tipoPessoa)) {
			codigoAtividade = AtividadeEconomicaEnum.PESSOA_FISICA.getCodigo();
		} else {
			codigoAtividade = AtividadeEconomicaEnum.PESSOA_JURIDICA.getCodigo();
		}
		atividade.setCodigo(codigoAtividade);

		return atividade;
	}

	/**
	 * Atribuir dados iniciais pf.
	 *
	 * @param pessoaCompartilhamento o valor de pessoa compartilhamento
	 * @return PessoaFisica
	 */
	private PessoaFisica atribuirDadosIniciaisPF(PessoaCompartilhamento pessoaCompartilhamento) {

		PessoaFisica pf = (PessoaFisica) pessoaCompartilhamento;
		pf.setAtividadeEconomica(obterAtividadeEconomicaDefault(TipoPessoaEnum.PESSOA_FISICA));

		Character tipoSexo = pf.getTipoSexo();

		if (pf.getMenorEmancipado() == null) {
			pf.setMenorEmancipado(false);
		}
		if (pf.getUniaoEstavel() == null) {
			pf.setUniaoEstavel(false);
		}
		if (pf.getQuantidadeDependentes() == null) {
			pf.setQuantidadeDependentes((short) 0);
		}
		if (tipoSexo == null || TipoSexoEnum.recuperarTipoSexoEnumPorCodigo(tipoSexo) == null) {
			pf.setTipoSexo(TipoSexoEnum.NAO_INFORMADO.getCodigo());
		}
		return pf;
	}

	/**
	 * Atribuir dados iniciais pj.
	 *
	 * @param pessoaCompartilhamento o valor de pessoa compartilhamento
	 * @return PessoaJuridica
	 */
	private PessoaJuridica atribuirDadosIniciaisPJ(PessoaCompartilhamento pessoaCompartilhamento) {

		PessoaJuridica pj = (PessoaJuridica) pessoaCompartilhamento;
		pj.setAtividadeEconomica(obterAtividadeEconomicaDefault(TipoPessoaEnum.PESSOA_JURIDICA));

		if (pj.getValorCapitalSocial() == null) {
			pj.setValorCapitalSocial(BigDecimal.ZERO);
		}

		return pj;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterarNomeMae(PessoaFisica pessoa, String nomeMae) throws BancoobException {

		PessoaFisica pessoaAlteracao = (PessoaFisica) obter(pessoa.getIdPessoaCompartilhamento());
		pessoaAlteracao.setNomeMae(nomeMae);

		alterarPessoaCompartilhamento(pessoaAlteracao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void alterarDataCadastramentoSFN(Integer idPessoaLegado, Instituicao instituicao, DateTimeDB dataInclusaoSFN) throws BancoobException {

		PessoaCompartilhamento pessoaCompartilhamento = consultarPessoaPorIdPessoaLegado(idPessoaLegado, instituicao);
		DateTimeDB dataSFNAtual = pessoaCompartilhamento.getDataInclusaoSFN();
		if ((dataSFNAtual == null) || (dataInclusaoSFN.compareTo(dataSFNAtual) < 0)) {
			pessoaCompartilhamento.setDataInclusaoSFN(dataInclusaoSFN);
			dao.alterar(pessoaCompartilhamento);

			AtualizacaoDataSFNDTO dto = new AtualizacaoDataSFNDTO(pessoaCompartilhamento);
			IAtualizacaoCadastralFachada<AtualizacaoDataSFNDTO> fachada = new AtualizacaoCadastralFachada<AtualizacaoDataSFNDTO>();
			fachada.executarAtualizacao(dto, TipoAtualizacaoCadastralEnum.OPERACAO_ALTERACAO);
		}
	}

	/**
	 * Altera o registro da pessoacompartilhamento.
	 * 
	 * @param pessoa
	 *            A pessoa compartilhaento a ser alterada.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	private void alterarPessoaCompartilhamento(PessoaCompartilhamento pessoa) throws BancoobException {
		PessoaFisica oldConjuge = null;
		if (pessoa instanceof PessoaJuridica) {
			// Tratamento de Bug do DB2
			tratarValorCapitalSocial(pessoa);
		} else {
			PessoaFisica pessoaF = (PessoaFisica) pessoa;
			oldConjuge = retornaOldConjuge(pessoaF);
			tratarPessoaFisica(pessoa);
		}

		super.alterar(pessoa);
		if (pessoa instanceof PessoaFisica) {
			alterarConjugeReverso((PessoaFisica) pessoa, oldConjuge);
		}
		IAtualizacaoCadastralFachada<PessoaCompartilhamento> fachada = new AtualizacaoCadastralFachada<PessoaCompartilhamento>();
		fachada.executarAtualizacao(obter(pessoa.getId()), TipoAtualizacaoCadastralEnum.OPERACAO_ALTERACAO);
	}

	/**
	 * O método Tratar pessoa fisica.
	 *
	 * @param pessoa o valor de pessoa
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void tratarPessoaFisica(PessoaCompartilhamento pessoa) throws BancoobException {

		PessoaFisica pf = (PessoaFisica) pessoa;
		pf.setAtividadeEconomica(obterAtividadeEconomicaDefault(TipoPessoaEnum.PESSOA_FISICA));

		Nacionalidade nacionalidade = pf.getNacionalidade();

		if (nacionalidade != null) {
			Short codigoNacionalidade = nacionalidade.getCodigo();
			if (codigoNacionalidade != null && NacionalidadeEnum.isBrasileira(codigoNacionalidade)) {
				pf.setDescNaturalidade(null);
			}
		}

		// Tratamento para a cidadania
		if (CollectionUtils.isEmpty(pf.getListaCidadania()) && pf.getNacionalidade() != null) {
			CidadaniaPK cidadaniaPK = new CidadaniaPK();
			cidadaniaPK.setCodigoNacionalidade(pf.getNacionalidade().getCodigo());
			cidadaniaPK.setIdPessoaCompartilhamento(pf.getIdPessoaCompartilhamento());

			Cidadania cidadania = new Cidadania();
			cidadania.setPk(cidadaniaPK);
			cidadania.setNacionalidade(pf.getNacionalidade());
			cidadania.setPessoaFisica(pf);
			
			if(pf.getListaCidadania() == null){
				pf.setListaCidadania(new HashSet<Cidadania>());
			}
			
			pf.getListaCidadania().add(cidadania);
		}
	}

	/**
	 * O método Tratar valor capital social.
	 *
	 * @param pessoa o valor de pessoa
	 */
	private void tratarValorCapitalSocial(PessoaCompartilhamento pessoa) {
		PessoaJuridica pj = (PessoaJuridica) pessoa;
		pj.setValorCapitalSocial(tratarValor(pj.getValorCapitalSocial()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConsultaDto<PessoaCompartilhamento> pesquisarParaIncorporacao(ConsultaDto<PessoaCompartilhamento> criterios, Instituicao instituicao)
			throws BancoobException {

		criterios.setFiltro(instituicao);
		return dao.pesquisarParaIncorporacao(criterios);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date obterDataUltimaAtualizacao(Long idPessoaCompartilhamento) throws BancoobException {
		return dao.obterDataUltimaAtualizacao(idPessoaCompartilhamento);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterarBeneficiarioINSS(PessoaFisica beneficiario) throws BancoobException {
		alterarPessoaCompartilhamento(beneficiario);
	}

	/**
	 * Lista as pessoas de um compartilhamento.
	 * 
	 * @param criterios
	 *            Os critérios da pesquisa.
	 * @return
	 * @throws BancoobException
	 */
	@Override
	public List<PessoaCompartilhamento> listarCompartilhamento(ConsultaPessoaDTO criterios) throws BancoobException {
		return getDAO().listarCompartilhamento(criterios);
	}

	/**
	 * @param compartilhamento
	 * @return
	 * @throws BancoobException
	 */
	@Override
	public Long obterMaiorIdPessoasPorCompartilhamento(CompartilhamentoCadastro compartilhamento) throws BancoobException {
		return getDAO().obterMaiorIdPessoasPorCompartilhamento(compartilhamento);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterarGrupoPessoasInexistentesUnicas(Integer idInstituicao, Short codigoCompartilhamento) throws BancoobException {
		getDAO().alterarGrupoPessoasInexistentesUnicas(idInstituicao, codigoCompartilhamento);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConsultaDto<PessoaCompartilhamento> buscarPessoasInexistentesCompartilhadas(ConsultaDto<PessoaCompartilhamento> criterios) throws BancoobException {
		return getDAO().buscarPessoasInexistentesCompartilhadas(criterios);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Map<String, Object> obterDadosPessoaLegado(String cpfCnpj, Integer idInstituicao) throws BancoobException {
		return getDAO().obterDadosPessoaLegado(cpfCnpj, idInstituicao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@IgnorarAutorizar
	public void renovarCadastro(PessoaCompartilhamento pessoa) throws BancoobException {
		validarRenovacaoCadastro(null, pessoa, Boolean.TRUE);
		pessoa.setCodigoTipoAtualizacaoRenovacao(TipoAtualizacaoRenovacaoEnum.MANUAL.getIdTipoAtualizacaoRenovacao());
		pessoa.setIdUsuarioEnvio(obterUsuario().getLogin());
		pessoa.setIdUsuarioRenovacao(obterUsuario().getLogin());	
		InformacoesUsuarioCAPES informacoesUsuarioCAPES = InformacoesUsuarioCAPES.getInstance();
		Calendar calendar = DateUtils.truncate(Calendar.getInstance(), Calendar.HOUR_OF_DAY);
		pessoa.setDataRenovacaoCadastral(new DateTimeDB(calendar.getTimeInMillis()));
		pessoa.setIdInstituicaoRenovacao(Short.valueOf(informacoesUsuarioCAPES.getIdInstituicao()));
		getDAO().alterar(pessoa);
		AtualizacaoPessoaCompartilhamentoFachada fachada = new AtualizacaoPessoaCompartilhamentoFachada();
		AvalFinanceiraDTO dto = new AvalFinanceiraDTO();
		dto.setPessoaCompartilhamento(pessoa);
		this.anotacaoServico.baixarAnotacaoPessoaPorTipo(tipoAnotacaoServico.obter(ID_TIPO_ANOTACAO_36_MESES_ATRASO), pessoa);
		fachada.executarAtualizacao(dto, TipoAtualizacaoCadastralEnum.OPERACAO_ALTERACAO_AVAL_FINANCEIRA);
	}

	/**
	 * O método Revalidar cadastro.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void revalidarCadastro(Long idPessoaCompartilhamento) throws BancoobException {
		ValidacaoCadastralRegraDelegate delegate = CAPESCadastroFabricaDelegates.getInstance().criarValidacaoCadastralRegraDelegate();
		delegate.revalidarCadastroPerfilCadastro(idPessoaCompartilhamento);
	}

	/**
	 * O método Validar renovacao cadastro.
	 *
	 * @param pessoa o valor de pessoa
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarRenovacaoCadastro(Aprovavel aprovavel, PessoaCompartilhamento pessoa, Boolean revalidarCadastro) throws BancoobException {
		try {
			consultarPessoaInstituicao(pessoa);
		} catch (RegistroNaoEncontradoNegocioException e) {
			throw new RenovacaoSomenteClientesException(e);
		}
		
		Autorizacao  autorizacao = null;
		if(aprovavel != null){
			 autorizacao = autorizacaoServicoLocal.obterPorEntidade(aprovavel);
		}	
		
		if(autorizacao != null){
			if(autorizacaoServicoLocal.isPessoaPendenteAprovacaoAutorizacao(pessoa, autorizacao)){
				throw new PessoaPendenteAprovacaoException();
			}
		}else{
			if (autorizacaoServicoLocal.isPessoaPendenteAprovacao(pessoa)) {
				throw new PessoaPendenteAprovacaoException();
			}
		}
		
		//Caso a atualizacao seja manual, a revalidacao sera feita. Essa decisao e de performance, para nao revalidar o tempo inteiro.
		if(revalidarCadastro && pessoa.getCodigoTipoAtualizacaoRenovacao() != null){
			revalidarCadastro(pessoa.getId());
		}
		
		if (validacaoCadastralErroServicoLocal.isPossuiRegraCadastralRestritiva(pessoa.getIdPessoaCompartilhamento(), obterInstituicaoUsuarioLogado()
				.getIdInstituicao(), pessoa.getPerfilCadastro().getCodigo())) {
			throw new PessoaComErrosCadastraisRestritivosException();
		}

	}

	/**
	 * Consultar pessoa instituicao.
	 *
	 * @param pessoa o valor de pessoa
	 * @return PessoaInstituicao
	 * @throws RegistroNaoEncontradoNegocioException lança a exceção RegistroNaoEncontradoNegocioException
	 */
	private PessoaInstituicao consultarPessoaInstituicao(PessoaCompartilhamento pessoa) throws RegistroNaoEncontradoNegocioException {
		PessoaInstituicao pessoaInstituicaoFiltro = new PessoaInstituicao();
		pessoaInstituicaoFiltro.setPessoa(pessoa.getPessoa());
		Instituicao instituicao = obterInstituicaoUsuarioLogado();
		pessoaInstituicaoFiltro.setIdInstituicao(instituicao.getIdInstituicao());
		PessoaInstituicaoDelegate pessoaInstituicaoDelegate = CAPESCadastroFabricaDelegates.getInstance().criarPessoaInstituicaoDelegate();
		return pessoaInstituicaoDelegate.obterPorPessoaInstituicao(pessoaInstituicaoFiltro);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ConsultaDto<ProcurarPessoaExternoVO> procurarPessoaExterno(ConsultaDto<ProcurarPessoaExternoVO> criterios) throws BancoobException {
		Instituicao instituicaoLogada = obterInstituicaoUsuarioLogado();

		getLogger().debug("[CAPES - Componente procurar pessoa externo] cooperativa logada: ", instituicaoLogada.getNumero(), "(",
				String.valueOf(instituicaoLogada.getIdInstituicao()), ")");

		ProcurarPessoaExternoVO procurarPessoaExternoVO = (ProcurarPessoaExternoVO) criterios.getFiltro();
		if (procurarPessoaExternoVO == null) {
			procurarPessoaExternoVO = new ProcurarPessoaExternoVO();
		}

		validarProcurarPessoaExterno(procurarPessoaExternoVO);

		getLogger().debug("[CAPES - Componente procurar pessoa externo] Número de cooperativa informada: ",
				String.valueOf(procurarPessoaExternoVO.getNumeroCooperativa()));

		Integer idInstituicao = null;
		// Se o usuário informar o número da cooperativa, o sistema permite que
		// o mesmo seja usado na consulta.
		if (procurarPessoaExternoVO.getNumeroCooperativa() != null && procurarPessoaExternoVO.getNumeroCooperativa() != 0) {
			SCIIntegracaoDelegate sciDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();
			InstituicaoVO instituicaoInformada = sciDelegate.obterInstituicaoPorNumeroCooperativa(procurarPessoaExternoVO.getNumeroCooperativa());

			if (instituicaoInformada != null) {
				getLogger().debug("[CAPES - Componente procurar pessoa externo] Está logado na ", instituicaoLogada.getNumero(), "(",
						String.valueOf(instituicaoLogada.getIdInstituicao()), ") e informou a cooperativa: ", String.valueOf(instituicaoInformada.getNumero()),
						"(", String.valueOf(instituicaoInformada.getIdInstituicao()), ")");
				idInstituicao = instituicaoInformada.getIdInstituicao();
			} else {
				idInstituicao = instituicaoLogada.getIdInstituicao();
			}
		}
		// Se o usuário não informar o número da cooperativa, o sistema coloca
		// como padrão o id da instituição logada.
		else {
			idInstituicao = instituicaoLogada.getIdInstituicao();
		}

		getLogger().debug("[CAPES - Componente procurar pessoa externo] ID da instituição usada na consulta: ", String.valueOf(idInstituicao));
		procurarPessoaExternoVO.setIdInstituicao(idInstituicao);
		criterios.setFiltro(procurarPessoaExternoVO);

		return getDAO().procurarPessoaExterno(criterios);
	}

	/**
	 * O método Validar procurar pessoa externo.
	 *
	 * @param procurarPessoaExternoVO o valor de procurar pessoa externo vo
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarProcurarPessoaExterno(ProcurarPessoaExternoVO procurarPessoaExternoVO) throws BancoobException {
		if (procurarPessoaExternoVO != null) {
			if (!StringUtils.isEmpty(procurarPessoaExternoVO.getApelido())) {
				validarCampoTexto(procurarPessoaExternoVO.getApelido());
			}

			if (!StringUtils.isEmpty(procurarPessoaExternoVO.getNome())) {
				validarCampoTexto(procurarPessoaExternoVO.getNome());
			}

			if (!StringUtils.isEmpty(procurarPessoaExternoVO.getCpfCnpj()) && procurarPessoaExternoVO.getCpfCnpj().length() > 14) {
				throw new FiltroTamanhoMaximoException("14 (quatorze)");
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PessoaCompartilhamento> listarPessoasMesmoDocumento(String cpfCnpj) throws BancoobException {
		return getDAO().listarPessoasMesmoDocumento(cpfCnpj);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PessoaCompartilhamento> consultarFiliais(Short codCompartilhamentoCadastro, String cnpj) throws BancoobException {
		return getDAO().consultarFiliais(codCompartilhamentoCadastro, cnpj.substring(0, 8));
	}

	/**
	 * O método Validar campo texto.
	 *
	 * @param texto o valor de texto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarCampoTexto(String texto) throws BancoobException {
		if (texto.length() < 3) {
			throw new FiltroTamanhoMinimoException("3 (três)");
		}

		if (texto.indexOf('%') >= 0) {
			throw new FormatoInvalidoException("Filtro");
		}
	}

	@Override
	public void atualizaCodTipoEmpresa(PessoaJuridica pessoa) throws BancoobException {
		getDAO().atualizaCodTipoEmpresa(pessoa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PessoaCompartilhamento consultarPorIdPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		getLogger().debug("Consultando pessoa por idPessoa: " + idPessoa + ", idInstituicao: " + idInstituicao);
		PessoaCompartilhamento retorno = null;
		try {
			if (idPessoa != null && idInstituicao != null) {
				Pessoa pessoa = new Pessoa();
				pessoa.setId(idPessoa);

				CompartilhamentoCadastro compartilhamentoCadastro = obterCompartilhamentoCadastro(idInstituicao);
				retorno = consultar(pessoa, compartilhamentoCadastro);
			}
		} catch (ClienteNaoEncontradoException e) {
			throw new PessoaNaoEncontradaException(e);
		}

		return retorno;
	}

	private PerfilCadastro obterPerfilCadastroPadrao() throws BancoobException {
		PerfilCadastroDelegate perfilCadastroDelegate = CAPESCadastroFabricaDelegates.getInstance().criarPerfilCadastroDelegate();
		PerfilCadastro retorno = perfilCadastroDelegate.obter(TipoPerfilCadastroEnum.SIMPLES.getCodigo());
		return retorno;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void renovarCadastroAutomatico(Aprovavel aprovavel, Integer idPessoa, String usuarioRenovacao) throws BancoobException {
		if(tipoAtualizacaoRenovacaoServico.obter(TipoAtualizacaoRenovacaoEnum.AUTOMATICO.getIdTipoAtualizacaoRenovacao()).getAtivo()){
			try{
				PessoaCompartilhamento pessoa = pessoaServico.obter(idPessoa).getPessoaCompartilhamento();
				validarRenovacaoCadastro(aprovavel, pessoa, Boolean.FALSE);
				pessoa.setCodigoTipoAtualizacaoRenovacao(TipoAtualizacaoRenovacaoEnum.AUTOMATICO.getIdTipoAtualizacaoRenovacao());
				pessoa.setIdUsuarioRenovacao(obterIdUsuarioEnvio(usuarioRenovacao));
				pessoa.setIdUsuarioEnvio(obterIdUsuarioEnvio(usuarioRenovacao));
				InformacoesUsuarioCAPES informacoesUsuarioCAPES = InformacoesUsuarioCAPES.getInstance();
				Calendar calendar = DateUtils.truncate(Calendar.getInstance(), Calendar.HOUR_OF_DAY);
				pessoa.setDataRenovacaoCadastral(new DateTimeDB(calendar.getTimeInMillis()));
				pessoa.setIdInstituicaoRenovacao(Short.valueOf(informacoesUsuarioCAPES.getIdInstituicao()));
				getDAO().alterar(pessoa);
				AtualizacaoPessoaCompartilhamentoFachada fachada = new AtualizacaoPessoaCompartilhamentoFachada();
				AvalFinanceiraDTO dto = new AvalFinanceiraDTO();
				dto.setPessoaCompartilhamento(pessoa);
				fachada.executarAtualizacao(dto, TipoAtualizacaoCadastralEnum.OPERACAO_ALTERACAO_AVAL_FINANCEIRA);
				this.anotacaoServico.baixarAnotacaoPessoaPorTipo(tipoAnotacaoServico.obter(ID_TIPO_ANOTACAO_36_MESES_ATRASO), pessoa);
			}catch(CAPESCadastroNegocioException e){
				getLogger().info(e.getMessage());
			}
		}
	}

	/**
	 * Obtem o idUsuarioEnvio das entidades, caso o fluxo GED/GFT esteja desligado ele seta o 
	 * usuário logado.
	 * @param usuarioRenovacao
	 * @return
	 */
	private String obterIdUsuarioEnvio(String usuarioRenovacao) {
		String idUsuarioEnvio;
		if(usuarioRenovacao == null){
			idUsuarioEnvio = obterUsuario().getLogin();
		}else{
			idUsuarioEnvio = usuarioRenovacao;
		}
		return idUsuarioEnvio;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@IgnorarAutorizar
	public void alterarPerfilCadastro(PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException {
		// FIXME bruno.carneiro - Descomentar o método incluirHistoricoPessoaCompartilhamento após subida do WAS
		// verificar o nome da coluna e alterar o método alterarPerfilCadastro para atualizar o campo IDUSUARIOPERFILCADASTRO.
		//getDAO().incluirHistoricoPessoaCompartilhamento(pessoaCompartilhamento);
		getDAO().alterarPerfilCadastro(pessoaCompartilhamento, InformacoesUsuarioCAPES.getInstance().getLogin());
	}
	
	//Remover após adaptação dos produtos.
	private boolean houveMudancaConjuge(PessoaFisica pessoa, PessoaFisica oldConjuge) throws BancoobException {
		if (pessoa != null) {
			if (pessoa.getConjuge() != null && oldConjuge != null) {
				if (pessoa.getConjuge().getId() != null && oldConjuge.getId() != null) {
					return !pessoa.getConjuge().getId().equals(oldConjuge.getId());
				}
				return false;
			} else if (pessoa.getConjuge() != null && oldConjuge == null) {
				return true;
			} else if (pessoa.getConjuge() == null && oldConjuge != null) {
				return true;
			}
		}
		return false;
	}

	private PessoaFisica retornaOldConjuge(PessoaFisica pessoaF) throws BancoobException {
		PessoaFisica pessoaOld = (PessoaFisica) obter(pessoaF.getId());
		return pessoaOld.getConjuge() != null ? (PessoaFisica) pessoaOld.getConjuge() : null;
	}

	private void alterarConjugeReverso(PessoaFisica pessoaF, PessoaFisica oldConjuge) throws BancoobException {
		if (pessoaF.getConjuge() != null) {
			PessoaFisica conjuge = (PessoaFisica) obter(pessoaF.getConjuge().getId());
			conjuge.setConjuge(pessoaF.getPessoaCompartilhamento());
			conjuge.setRegimeCasamento(pessoaF.getRegimeCasamento());
			conjuge.setEstadoCivil(pessoaF.getEstadoCivil());
			if (houveMudancaConjuge(pessoaF, oldConjuge)) {
				criaRelacionamentoConjuge(pessoaF);
			}
			getDAO().alterar(conjuge);
		} else {
			removeConjugeReverso(pessoaF, oldConjuge != null ? oldConjuge.getId() : null);
		}
	}

	private void removeConjugeReverso(PessoaFisica pessoaF, Long idConjuge) throws BancoobException {
		if (idConjuge != null) {
			PessoaFisica conjuge = (PessoaFisica) obter(idConjuge);
			conjuge.setConjuge(null);
			conjuge.setRegimeCasamento(null);
			conjuge.setEstadoCivil(pessoaF.getEstadoCivil());
			removeRelacionamentoConjuge(pessoaF, conjuge);
			getDAO().alterar(conjuge);
		}
	}

	private void removeRelacionamentoConjuge(PessoaFisica pessoaF, PessoaFisica conjuge) throws BancoobException {
		RelacionamentoPessoa filtro = new RelacionamentoPessoa();
		filtro.setPessoa(pessoaF.getPessoa());
		filtro.setPessoaRelacionada(conjuge.getPessoa());
		filtro.setIdInstituicao(obterInstituicaoUsuarioLogado().getIdInstituicao());

		TipoRelacionamentoPessoa tipoRelacionamento = tipoRelacionamentoPessoaServico.obter(TipoRelacionamentoPessoaEnum.CONJUGE.getCodigo());
		filtro.setTipoRelacionamento(tipoRelacionamento);

		List<RelacionamentoPessoa> listaRel = relacionamentoPessoaServico.pesquisarRelacionamentosVigentesPorFiltro(filtro);

		if (!listaRel.isEmpty()) {
			for (RelacionamentoPessoa relPessoa : listaRel) {
				relPessoa.setVerificarAutorizacao(false);
				relacionamentoPessoaServico.excluirEntidade(relPessoa);
			}
		}
	}

	private void criaRelacionamentoConjuge(PessoaFisica pessoaF) throws BancoobException {
		RelacionamentoPessoa relacionamento = new RelacionamentoPessoa();
		relacionamento.setPessoa(pessoaF.getPessoa());
		relacionamento.setPessoaRelacionada(pessoaF.getConjuge().getPessoa());
		relacionamento.setIdInstituicao(obterInstituicaoUsuarioLogado().getIdInstituicao());
		relacionamento.setPessoaCompartilhamento(pessoaF.getPessoaCompartilhamento());
		relacionamento.setVerificarAutorizacao(false);
		relacionamento.setDataInicioRelacionamento(new DateTimeDB());

		TipoRelacionamentoPessoa tipoRelacionamento = tipoRelacionamentoPessoaServico.obter(TipoRelacionamentoPessoaEnum.CONJUGE.getCodigo());
		relacionamento.setTipoRelacionamento(tipoRelacionamento);

		relacionamentoPessoaServico.incluir(relacionamento, true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void incluirContatosReceitaPJ(PessoaCompartilhamento pessoaCompartilhamento, DadosReceitaFederalVO dadosReceita) throws BancoobException {
		if (pessoaCompartilhamento instanceof PessoaJuridica) {
			PessoaJuridica pj = (PessoaJuridica) pessoaCompartilhamento;
			DadosCNPJVO vo = (DadosCNPJVO) dadosReceita;
			incluirEndereco(pj, vo);
			incluirTelefone(pj, vo.getDdd1(), vo.getTelefone1());
			incluirTelefone(pj, vo.getDdd2(), vo.getTelefone2());
			incluirEmail(pj, vo.getEmail());
		}
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void incluirAnotacaoReceita(PessoaCompartilhamento pessoaIncluida, DadosReceitaFederalVO dadosReceita) throws BancoobException {
		try {
			Pessoa pessoa = pessoaIncluida.getPessoa();
			TipoPessoaEnum tipoPessoa = TipoPessoaEnum.valueOf(pessoa.getTipoPessoa().getCodTipoPessoa());
			SituacaoCadastralRFBEnum situacaoCadastral = SituacaoCadastralRFBEnum.valueOf(dadosReceita.getCodigoSituacaoCadastral(), tipoPessoa);

			Anotacao anotacao = new Anotacao();
			anotacao.setDataHoraAnotacao(new DateTimeDB());
			anotacao.setDataHoraOcorrencia(pessoaIncluida.getDataInclusaoSistema());
			anotacao.setFlexibilidade(false);
			anotacao.setIdConsultaExterna(dadosReceita.getIdConsulta());
			anotacao.setInstituicao(new Instituicao(dadosReceita.getIdInstituicao(), dadosReceita.getIdUnidadeInst()));
			anotacao.setOrigemInformacao(new OrigemInformacao(OrigemInformacaoEnum.RECEITA.getIdOrigemInformacao()));
			anotacao.setPessoaCompartilhamento(pessoaIncluida);
			anotacao.setQuantidade(NumberUtils.SHORT_ZERO);
			anotacao.setTipoAnotacao(obterTipoAnotacao(situacaoCadastral, tipoPessoa));
			anotacao.setTipoConsultaOrigem(obterTipoConsultaOrigem(tipoPessoa));
			anotacao.setUsuarioInclusao(null);
			anotacao.setValor(BigDecimal.ZERO);
			servicoAnotacao.incluirAnotacao(anotacao, Boolean.FALSE);
		} catch (NegocioException e) {
			getLogger().info("A anotacao da receita nao foi incluida devido a validacao: " + e.getMessage());
		} catch (BancoobException e) {
			getLogger().erro(e, "Erro ao incluir anotacao da receita.");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public PessoaCompartilhamento incluirComDadosReceita(PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException {
		return incluir(pessoaCompartilhamento);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public PessoaCompartilhamento incluirComDadosReceita(PessoaCompartilhamento pessoaCompartilhamento, Integer numeroCooperativa, Integer unidadeInstituicao) throws BancoobException {
		return incluir(pessoaCompartilhamento, numeroCooperativa, unidadeInstituicao);
	}
	
	/**
	 * Verifica se o parâmetro de inclusão pelo faça parte está ligado;
	 * 
	 * @return boolean se o cadastro deve ou não ser feito pelo faça-parte.
	 * @throws BancoobException
	 */
	@Override
	public boolean obterParametroInclusaoFacaParte(Short codigoTipoPessoa) throws BancoobException {
		if (TipoPessoaEnum.isPessoaFisica(codigoTipoPessoa)) {
			Lista lista = new Lista();
			lista.setIdLista(195);
			ListaItemPK chave = new ListaItemPK();
			chave.setLista(lista);
			chave.setCodigoItem("100");
			ListaItem parametro = CAPESReplicacaoComumFabricaDelegates.getInstance().criarListaItemDelegate().obter(chave, obterInstituicaoUsuarioLogado().getIdInstituicao());

			if (parametro != null) {
				return "1".equalsIgnoreCase(parametro.getDescricao());
			}
		}

		return false;
	}

}