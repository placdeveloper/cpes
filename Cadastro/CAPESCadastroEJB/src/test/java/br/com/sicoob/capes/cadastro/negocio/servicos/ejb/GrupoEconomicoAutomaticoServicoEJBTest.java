package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoRelacionamentoPessoaEnum;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoEconomicoAutomaticoPessoaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoEconomicoInstituicaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoEconomicoManualPessoaServicoLocal;
import br.com.sicoob.capes.cadastro.persistencia.dao.GrupoEconomicoNovoDAO;
import br.com.sicoob.capes.comum.negocio.contexto.InformacoesUsuarioCAPES;
import br.com.sicoob.capes.comum.negocio.enums.TipoGrupoEconomicoEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoAutomaticoPessoa;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoNovo;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.TipoGrupoEconomico;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;
import br.com.sicoob.capes.negocio.entidades.TipoRelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaJuridica;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;

public class GrupoEconomicoAutomaticoServicoEJBTest extends Mockito {

	@InjectMocks
	private GrupoEconomicoNovoServicoEJB ejb;

	@Mock
	private GrupoEconomicoNovoDAO grupoEconomicoNovoDAO;

	@Mock
	private GrupoEconomicoAutomaticoPessoaServicoLocal grupoEconomicoAutomaticoPessoaServico;

	@Mock
	private GrupoEconomicoManualPessoaServicoLocal grupoEconomicoManualPessoaServico;

	@Mock
	private GrupoEconomicoInstituicaoServicoLocal grupoEconomicoInstituicaoServico;

	private final TipoPessoa TIPO_PESSOA_JURIDICA = new TipoPessoa(TipoPessoaEnum.PESSOA_JURIDICA.getCodigo());
	private final TipoPessoa TIPO_PESSOA_FISICA = new TipoPessoa(TipoPessoaEnum.PESSOA_FISICA.getCodigo());

	@SuppressWarnings("deprecation")
	@Before
	public void setUp() throws BancoobException {
		MockitoAnnotations.initMocks(this);

		InformacoesUsuario.INSTANCIA.set(new InformacoesUsuario());
		InformacoesUsuario.getInstance().setIdInstituicao("143");
		InformacoesUsuarioCAPES.setInstance(new InformacoesUsuarioCAPES(InformacoesUsuario.getInstance(), new Short("1")));
	}

	@After
	public void preDestroy() {
		InformacoesUsuarioCAPES.removeInstance();
	}

	/**
	 * Tentar gravar um grupo para um relacionamento não sócio ou sócio administrado
	 * 
	 * @throws BancoobException
	 */
	@Test
	public void gravarNaoSocio() throws BancoobException {
		final RelacionamentoPessoa relacionamento = gerarRelacionamentoPessoa(TipoRelacionamentoPessoaEnum.ADMINISTRADOR,
				TipoPessoaEnum.PESSOA_FISICA);
		final TipoRelacionamentoPessoa tipoRelacionamento = relacionamento.getTipoRelacionamento();
		ejb.gravar(relacionamento);
		verify(relacionamento, times(2)).getTipoRelacionamento();// Uma das chamadas é no teste
		verify(tipoRelacionamento, times(1)).getCodigo();
		verifyZeroInteractions(grupoEconomicoNovoDAO, grupoEconomicoAutomaticoPessoaServico, grupoEconomicoManualPessoaServico,
				grupoEconomicoInstituicaoServico);
		verifyNoMoreInteractions(relacionamento, tipoRelacionamento);
	}

	/**
	 * 
	 * @throws BancoobException
	 */
	@Test
	public void gravarSocioMesmaPessoa() throws BancoobException {
		final RelacionamentoPessoa relacionamento = gerarRelacionamentoPessoa(TipoRelacionamentoPessoaEnum.SOCIO, TipoPessoaEnum.PESSOA_FISICA);
		final Pessoa pessoa = relacionamento.getPessoa();
		final Pessoa pessoaRelacionada = relacionamento.getPessoaRelacionada();
		pessoaRelacionada.setIdPessoa(pessoa.getIdPessoa());
		final TipoRelacionamentoPessoa tipoRelacionamento = relacionamento.getTipoRelacionamento();
		ejb.gravar(relacionamento);
		verify(relacionamento, times(2)).getTipoRelacionamento(); // Uma das chamadas é no escopo do teste
		verify(tipoRelacionamento, times(1)).getCodigo();
		verify(relacionamento, times(2)).getPessoa(); // Uma das chamadas é no escopo do teste
		verify(relacionamento, times(2)).getPessoaRelacionada(); // Uma das chamadas é no escopo do teste
		verify(pessoa, times(2)).getIdPessoa(); // Uma das chamadas é no escopo do teste
		verify(pessoaRelacionada, times(1)).getIdPessoa();
		verify(pessoaRelacionada, times(1)).setIdPessoa(1); // Uma das chamadas é no escopo do teste
		verifyZeroInteractions(grupoEconomicoNovoDAO, grupoEconomicoAutomaticoPessoaServico, grupoEconomicoManualPessoaServico,
				grupoEconomicoInstituicaoServico);
		verifyNoMoreInteractions(relacionamento, tipoRelacionamento, pessoa, pessoaRelacionada);
	}

	/**
	 * Tentar gravar um grupo para um sócio não controlador, sem grupo
	 * 
	 * @throws BancoobException
	 */
	@SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void gravarSocioNaoControladorSemGrupo() throws BancoobException {
		final RelacionamentoPessoa relacionamento = gerarRelacionamentoPessoa(TipoRelacionamentoPessoaEnum.SOCIO_ADMINISTRADOR,
				TipoPessoaEnum.PESSOA_FISICA);
		final TipoRelacionamentoPessoa tipoRelacionamento = relacionamento.getTipoRelacionamento();
		relacionamento.setPercentualCapitalEmpresa(new BigDecimal(50));
		when(grupoEconomicoAutomaticoPessoaServico.listar(any(ConsultaDto.class))).thenReturn(null);
		ejb.gravar(relacionamento);
		// Verifica se o sócio é controlador em outro grupo
		verify(relacionamento, times(1)).setPercentualCapitalEmpresa(any(BigDecimal.class));// Uma das chamadas é no escopo do teste
		verify(relacionamento, times(2)).getTipoRelacionamento();// Uma das chamadas é no escopo do teste
		verify(relacionamento, times(1)).getPercentualCapitalEmpresa();
		verify(tipoRelacionamento, times(1)).getCodigo();
		verify(relacionamento, times(1)).getPessoaCompartilhamento();
		verify(relacionamento, times(2)).getPessoa();
		verify(relacionamento, times(2)).getPessoaRelacionada();
		verify(grupoEconomicoAutomaticoPessoaServico, times(2)).listar(any(ConsultaDto.class));
		verifyZeroInteractions(grupoEconomicoNovoDAO, grupoEconomicoManualPessoaServico, grupoEconomicoInstituicaoServico);
		verifyNoMoreInteractions(grupoEconomicoAutomaticoPessoaServico, relacionamento, tipoRelacionamento);
	}

	/**
	 * Tentar gravar um grupo para um sócio não controlador, com grupo, aonde o grupo será mantido e não ajustado
	 * 
	 * @throws BancoobException
	 */
	@SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void gravarSocioNaoControladorComGrupo1() throws BancoobException {
		final RelacionamentoPessoa relacionamento = gerarRelacionamentoPessoa(TipoRelacionamentoPessoaEnum.SOCIO, TipoPessoaEnum.PESSOA_FISICA);
		relacionamento.setPercentualCapitalEmpresa(new BigDecimal(50));
		List<GrupoEconomicoAutomaticoPessoa> pessoas = new ArrayList<>();
		GrupoEconomicoAutomaticoPessoa pessoa = new GrupoEconomicoAutomaticoPessoa();
		pessoa.setRelacionamentoPessoa(relacionamento);
		pessoa.setBolControlador(false);
		pessoa.setGrupoEconomico(new GrupoEconomicoNovo());
		pessoas.add(pessoa);
		when(grupoEconomicoAutomaticoPessoaServico.listar(any(ConsultaDto.class))).thenReturn(pessoas);
		ejb.gravar(relacionamento);
		verify(grupoEconomicoAutomaticoPessoaServico, times(4)).listar(any(ConsultaDto.class));
		verify(grupoEconomicoAutomaticoPessoaServico, times(2)).excluirEntidade(any(GrupoEconomicoAutomaticoPessoa.class));
		verifyNoMoreInteractions(grupoEconomicoAutomaticoPessoaServico);
		verifyZeroInteractions(grupoEconomicoNovoDAO, grupoEconomicoManualPessoaServico, grupoEconomicoInstituicaoServico);
	}

	/**
	 * Tentar gravar um grupo para um sócio não controlador, com grupo, aonde o grupo será removido
	 * 
	 * @throws BancoobException
	 */
	@SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void gravarSocioNaoControladorComGrupo2() throws BancoobException {
		final RelacionamentoPessoa relacionamento = gerarRelacionamentoPessoa(TipoRelacionamentoPessoaEnum.SOCIO_ADMINISTRADOR,
				TipoPessoaEnum.PESSOA_FISICA);
		relacionamento.setPercentualCapitalEmpresa(new BigDecimal(50));
		List<GrupoEconomicoAutomaticoPessoa> pessoas = new ArrayList<>();
		GrupoEconomicoAutomaticoPessoa pessoa = new GrupoEconomicoAutomaticoPessoa();
		pessoa.setRelacionamentoPessoa(relacionamento);
		pessoa.setGrupoEconomico(new GrupoEconomicoNovo(1));
		pessoas.add(pessoa);
		when(grupoEconomicoAutomaticoPessoaServico.listar(any(ConsultaDto.class))).thenReturn(pessoas, pessoas, null);
		final GrupoEconomicoNovo grupo = new GrupoEconomicoNovo();
		grupo.setTipo(new TipoGrupoEconomico(TipoGrupoEconomicoEnum.AUTOMATICO.getCodigo()));
		when(grupoEconomicoNovoDAO.obter(anyInt())).thenReturn(grupo);
		ejb.gravar(relacionamento);
		// Verifica se o sócio é controlador em outro grupo
		verify(grupoEconomicoAutomaticoPessoaServico, times(3)).listar(any(ConsultaDto.class));
		verify(grupoEconomicoAutomaticoPessoaServico, times(2)).excluirEntidade(any(GrupoEconomicoAutomaticoPessoa.class));
		verify(grupoEconomicoInstituicaoServico, times(1)).listar(any(ConsultaDto.class));
		verify(grupoEconomicoNovoDAO, times(1)).obter(anyInt());
		verify(grupoEconomicoNovoDAO, times(1)).excluirEntidade(any(GrupoEconomicoNovo.class));
		verifyNoMoreInteractions(grupoEconomicoAutomaticoPessoaServico, grupoEconomicoNovoDAO, grupoEconomicoInstituicaoServico);
		verifyZeroInteractions(grupoEconomicoManualPessoaServico, grupoEconomicoInstituicaoServico);
	}

	/**
	 * Tentar gravar um grupo para um sócio controlador, sem grupo cadastrado
	 * 
	 * @throws BancoobException
	 */
	@SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void gravarSocioPFControladorSemGrupo() throws BancoobException {
		RelacionamentoPessoa relacionamento = gerarRelacionamentoPessoa(TipoRelacionamentoPessoaEnum.SOCIO, TipoPessoaEnum.PESSOA_FISICA);
		relacionamento.setIdUsuarioEnvio("idUsuarioEnvio");
		relacionamento.setPercentualCapitalEmpresa(new BigDecimal(51));
		List<GrupoEconomicoAutomaticoPessoa> pessoas = new ArrayList<>();
		GrupoEconomicoAutomaticoPessoa pessoa = new GrupoEconomicoAutomaticoPessoa();
		pessoa.setGrupoEconomico(new GrupoEconomicoNovo());
		pessoas.add(pessoa);
		when(grupoEconomicoAutomaticoPessoaServico.listar(any(ConsultaDto.class))).thenReturn(pessoas, null, null, null);
		when(grupoEconomicoNovoDAO.listar(any(ConsultaDto.class))).thenReturn(new ArrayList<>());
		ejb.gravar(relacionamento);
		// Deve incluir duas pessoas no grupo, sendo um controlador e a empresa
		verify(grupoEconomicoAutomaticoPessoaServico, times(2)).incluir(any(GrupoEconomicoAutomaticoPessoa.class));
		// Verificar se o sócio é controlador em outro grupo
		verify(grupoEconomicoAutomaticoPessoaServico, times(4)).listar(any(ConsultaDto.class));
		// Verifica iteração para ajustar nome do grupo
		verify(grupoEconomicoNovoDAO, times(2)).listar(any(ConsultaDto.class));
		// Verifica a inclusão do Grupo
		verify(grupoEconomicoNovoDAO).incluir(any(GrupoEconomicoNovo.class));
		// Não existir novas iterações
		verifyNoMoreInteractions(grupoEconomicoAutomaticoPessoaServico, grupoEconomicoNovoDAO);
	}

	/**
	 * Tentar gravar um grupo para um sócio controlador, sem grupo cadastrado
	 * 
	 * @throws BancoobException
	 */
	@SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void gravarSocioPJControladorSemGrupo() throws BancoobException {
		RelacionamentoPessoa relacionamento = gerarRelacionamentoPessoa(TipoRelacionamentoPessoaEnum.SOCIO, TipoPessoaEnum.PESSOA_JURIDICA);
		relacionamento.setPercentualCapitalEmpresa(new BigDecimal(51));
		relacionamento.setIdUsuarioAprovacao("idUsuarioAprovacao");
		when(grupoEconomicoAutomaticoPessoaServico.listar(any(ConsultaDto.class))).thenReturn(null);
		when(grupoEconomicoNovoDAO.listar(any(ConsultaDto.class))).thenReturn(null);
		ejb.gravar(relacionamento);
		// Deve incluir duas pessoas no grupo, sendo um controlador e a empresa
		verify(grupoEconomicoAutomaticoPessoaServico, times(2)).incluir(any(GrupoEconomicoAutomaticoPessoa.class));
		// Verificar se o sócio é controlador em outro grupo
		verify(grupoEconomicoAutomaticoPessoaServico, times(4)).listar(any(ConsultaDto.class));
		// Verifica iteração para ajustar nome do grupo
		verify(grupoEconomicoNovoDAO, times(2)).listar(any(ConsultaDto.class));
		// Verifica a inclusão do Grupo
		verify(grupoEconomicoNovoDAO).incluir(any(GrupoEconomicoNovo.class));
		// Não existir novas iterações
		verifyNoMoreInteractions(grupoEconomicoAutomaticoPessoaServico, grupoEconomicoNovoDAO);
	}

	/**
	 * Tentar gravar um grupo para um sócio controlador, sem grupo cadastrado
	 * 
	 * @throws BancoobException
	 */
	@SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void gravarSocioPJControladorSemGrupoComNomeRepetido() throws BancoobException {
		InformacoesUsuario.getInstance().setLogin("login");
		RelacionamentoPessoa relacionamento = gerarRelacionamentoPessoa(TipoRelacionamentoPessoaEnum.SOCIO, TipoPessoaEnum.PESSOA_JURIDICA);
		relacionamento.setPercentualCapitalEmpresa(new BigDecimal(51));
		when(grupoEconomicoAutomaticoPessoaServico.listar(any(ConsultaDto.class))).thenReturn(null);
		List<GrupoEconomicoNovo> grupos = new ArrayList<>();
		GrupoEconomicoNovo grupo = new GrupoEconomicoNovo();
		grupos.add(grupo);
		List<GrupoEconomicoNovo> gruposNulo = null;
		when(grupoEconomicoNovoDAO.listar(any(ConsultaDto.class))).thenReturn(grupos, gruposNulo);
		ejb.gravar(relacionamento);
		// Deve incluir duas pessoas no grupo, sendo um controlador e a empresa
		verify(grupoEconomicoAutomaticoPessoaServico, times(2)).incluir(any(GrupoEconomicoAutomaticoPessoa.class));
		// Verificar se o sócio é controlador em outro grupo
		verify(grupoEconomicoAutomaticoPessoaServico, times(4)).listar(any(ConsultaDto.class));
		// Verifica iteração para ajustar nome do grupo
		verify(grupoEconomicoNovoDAO, times(3)).listar(any(ConsultaDto.class));
		// Verifica a inclusão do Grupo
		verify(grupoEconomicoNovoDAO).incluir(any(GrupoEconomicoNovo.class));
		// Não existir novas iterações
		verifyNoMoreInteractions(grupoEconomicoAutomaticoPessoaServico, grupoEconomicoNovoDAO);
	}

	/**
	 * Tentar gravar um grupo para um sócio controlador, com grupo cadastrado
	 * 
	 * @throws BancoobException
	 */
	@Test
	@Ignore
	@SuppressWarnings("unchecked")
	public void gravarSocioPFControladorComGrupo1() throws BancoobException {
		RelacionamentoPessoa relacionamento = gerarRelacionamentoPessoa(TipoRelacionamentoPessoaEnum.SOCIO, TipoPessoaEnum.PESSOA_FISICA);
		relacionamento.setPercentualCapitalEmpresa(new BigDecimal(51));
		List<GrupoEconomicoAutomaticoPessoa> pessoas = new ArrayList<>();
		GrupoEconomicoAutomaticoPessoa pessoa = new GrupoEconomicoAutomaticoPessoa();
		pessoa.setGrupoEconomico(new GrupoEconomicoNovo());
		pessoas.add(pessoa);
		when(grupoEconomicoAutomaticoPessoaServico.listar(any(ConsultaDto.class))).thenReturn(null, null, pessoas, null);
		when(grupoEconomicoNovoDAO.listar(any(ConsultaDto.class))).thenReturn(new ArrayList<>());
		ejb.gravar(relacionamento);
		// Deve incluir duas pessoas no grupo, sendo um controlador e a empresa
		verify(grupoEconomicoAutomaticoPessoaServico, times(2)).incluir(any(GrupoEconomicoAutomaticoPessoa.class));
		// Verificar se o sócio é controlador em outro grupo
		verify(grupoEconomicoAutomaticoPessoaServico, times(4)).listar(any(ConsultaDto.class));
		// Não existir novas iterações
		verifyNoMoreInteractions(grupoEconomicoAutomaticoPessoaServico, grupoEconomicoNovoDAO);
	}

	/**
	 * Tentar gravar um grupo para um sócio controlador, com grupo/pessoa cadastrado
	 * 
	 * @throws BancoobException
	 */
	@SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void gravarSocioControladorComGrupoPessoaControlador() throws BancoobException {
		RelacionamentoPessoa relacionamento = gerarRelacionamentoPessoa(TipoRelacionamentoPessoaEnum.SOCIO, TipoPessoaEnum.PESSOA_FISICA);
		relacionamento.setPercentualCapitalEmpresa(new BigDecimal(51));
		List<GrupoEconomicoAutomaticoPessoa> pessoas = new ArrayList<>();
		GrupoEconomicoAutomaticoPessoa pessoa = new GrupoEconomicoAutomaticoPessoa();
		pessoa.setGrupoEconomico(new GrupoEconomicoNovo());
		pessoa.setBolControlador(true);
		pessoas.add(pessoa);
		when(grupoEconomicoAutomaticoPessoaServico.listar(any(ConsultaDto.class))).thenReturn(pessoas, pessoas);
		when(grupoEconomicoNovoDAO.listar(any(ConsultaDto.class))).thenReturn(new ArrayList<>());
		ejb.gravar(relacionamento);
		// Deve incluir duas pessoas no grupo, sendo um controlador e a empresa
		verify(grupoEconomicoAutomaticoPessoaServico, times(1)).alterar(any(GrupoEconomicoAutomaticoPessoa.class));
		// Verificar se o sócio é controlador em outro grupo
		verify(grupoEconomicoAutomaticoPessoaServico, times(2)).listar(any(ConsultaDto.class));
		// Não existir novas iterações
		verifyNoMoreInteractions(grupoEconomicoAutomaticoPessoaServico, grupoEconomicoNovoDAO);
	}

	/**
	 * Tentar gravar um grupo para um sócio controlador, com grupo/pessoa cadastrado
	 * 
	 * @throws BancoobException
	 */
	@Test
	@Ignore
	@SuppressWarnings("unchecked")
	public void gravarSocioControladorComGrupoPessoa() throws BancoobException {
		RelacionamentoPessoa relacionamento = gerarRelacionamentoPessoa(TipoRelacionamentoPessoaEnum.SOCIO, TipoPessoaEnum.PESSOA_FISICA);
		relacionamento.setPercentualCapitalEmpresa(new BigDecimal(51));
		List<GrupoEconomicoAutomaticoPessoa> pessoas = new ArrayList<>();
		GrupoEconomicoAutomaticoPessoa pessoa = new GrupoEconomicoAutomaticoPessoa();
		pessoa.setGrupoEconomico(new GrupoEconomicoNovo());
		pessoa.setBolControlador(false);
		pessoas.add(pessoa);
		when(grupoEconomicoAutomaticoPessoaServico.listar(any(ConsultaDto.class))).thenReturn(pessoas, pessoas);
		when(grupoEconomicoNovoDAO.listar(any(ConsultaDto.class))).thenReturn(new ArrayList<>());
		ejb.gravar(relacionamento);
		// Verificar se o sócio é controlador em outro grupo
		verify(grupoEconomicoAutomaticoPessoaServico, times(2)).listar(any(ConsultaDto.class));
		// Não existir novas iterações
		verifyNoMoreInteractions(grupoEconomicoAutomaticoPessoaServico, grupoEconomicoNovoDAO);
	}

	/**
	 * Tentar gravar um grupo para um sócio controlador, com grupo cadastrado
	 * 
	 * @throws BancoobException
	 */
	@SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void gravarSocioPFControladorComGrupo2() throws BancoobException {
		RelacionamentoPessoa relacionamento = gerarRelacionamentoPessoa(TipoRelacionamentoPessoaEnum.SOCIO, TipoPessoaEnum.PESSOA_FISICA);
		relacionamento.setPercentualCapitalEmpresa(new BigDecimal(51));
		List<GrupoEconomicoAutomaticoPessoa> pessoas = new ArrayList<>();
		GrupoEconomicoAutomaticoPessoa pessoa = new GrupoEconomicoAutomaticoPessoa();
		pessoa.setGrupoEconomico(new GrupoEconomicoNovo());
		pessoas.add(pessoa);
		when(grupoEconomicoAutomaticoPessoaServico.listar(any(ConsultaDto.class))).thenReturn(null, null, null, pessoas);
		when(grupoEconomicoNovoDAO.listar(any(ConsultaDto.class))).thenReturn(new ArrayList<>());
		ejb.gravar(relacionamento);
		// Deve incluir duas pessoas no grupo, sendo um controlador e a empresa
		verify(grupoEconomicoAutomaticoPessoaServico, times(2)).incluir(any(GrupoEconomicoAutomaticoPessoa.class));
		// Verificar se o sócio é controlador em outro grupo
		verify(grupoEconomicoAutomaticoPessoaServico, times(4)).listar(any(ConsultaDto.class));
		// Não existir novas iterações
		verifyNoMoreInteractions(grupoEconomicoAutomaticoPessoaServico, grupoEconomicoNovoDAO);
	}

	private RelacionamentoPessoa gerarRelacionamentoPessoa(TipoRelacionamentoPessoaEnum tipoRelacionamentoEnum,
			TipoPessoaEnum tipoPessoaRelacionada) {
		Pessoa pessoa = new Pessoa();
		pessoa.setIdPessoa(1);
		pessoa.setTipoPessoa(TIPO_PESSOA_JURIDICA);
		pessoa.setCompartilhamentos(new HashSet<>());
		pessoa.getCompartilhamentos().add(spy(new PessoaJuridica()));
		pessoa.getPessoaCompartilhamento().setNomePessoa(TipoPessoaEnum.PESSOA_JURIDICA.toString());

		Pessoa pessoaRelacionada = new Pessoa();
		pessoaRelacionada.setIdPessoa(2);
		pessoaRelacionada.setCompartilhamentos(new HashSet<>());
		final String nome = tipoPessoaRelacionada.toString() + " Relacionada";
		if (tipoPessoaRelacionada == TipoPessoaEnum.PESSOA_FISICA) {
			pessoaRelacionada.setTipoPessoa(TIPO_PESSOA_FISICA);
			pessoaRelacionada.getCompartilhamentos().add(spy(new PessoaFisica()));
		} else {
			pessoaRelacionada.setTipoPessoa(TIPO_PESSOA_JURIDICA);
			pessoaRelacionada.getCompartilhamentos().add(spy(new PessoaJuridica()));
		}
		pessoaRelacionada.getPessoaCompartilhamento().setNomePessoa(nome);

		TipoRelacionamentoPessoa tipoRelacionamento = new TipoRelacionamentoPessoa();
		tipoRelacionamento.setCodigo(tipoRelacionamentoEnum.getCodigo());
		tipoRelacionamento.setHabilitaCapitalSocial(true);

		RelacionamentoPessoa relacionamento = new RelacionamentoPessoa();
		relacionamento.setPessoa(spy(pessoa));
		relacionamento.setPessoaRelacionada(spy(pessoaRelacionada));
		relacionamento.setTipoRelacionamento(spy(tipoRelacionamento));
		relacionamento.setIdInstituicao(143);

		return spy(relacionamento);
	}

}
