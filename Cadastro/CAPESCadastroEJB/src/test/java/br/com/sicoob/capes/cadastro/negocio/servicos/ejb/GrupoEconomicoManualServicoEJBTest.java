package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.excecao.CAPESCadastroNegocioException;
import br.com.sicoob.capes.cadastro.negocio.excecao.CampoNaoInformadoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.RegistroMesmoAtributoException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoEconomicoAutomaticoPessoaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoEconomicoInstituicaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoEconomicoManualPessoaServicoLocal;
import br.com.sicoob.capes.cadastro.persistencia.dao.GrupoEconomicoNovoDAO;
import br.com.sicoob.capes.comum.negocio.contexto.InformacoesUsuarioCAPES;
import br.com.sicoob.capes.comum.negocio.enums.TipoGrupoEconomicoEnum;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoManualPessoa;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoNovo;
import br.com.sicoob.capes.negocio.entidades.TipoGrupoEconomico;

public class GrupoEconomicoManualServicoEJBTest extends Mockito {

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

	@SuppressWarnings("deprecation")
	@Before
	public void setUp() throws BancoobException {
		MockitoAnnotations.initMocks(this);

		InformacoesUsuario.INSTANCIA.set(new InformacoesUsuario());
		InformacoesUsuario.getInstance().setIdInstituicao("143");
		InformacoesUsuario.getInstance().setLogin("TEste");
		InformacoesUsuarioCAPES.setInstance(new InformacoesUsuarioCAPES(InformacoesUsuario.getInstance(), new Short("1")));
	}

	@After
	public void preDestroy() {
		InformacoesUsuarioCAPES.removeInstance();
	}

	/**
	 * Tentar gravar um grupo econômico manual sem idUsuario
	 * 
	 * @throws BancoobException
	 */
	@Test
	public void incluirManualSemIdUsuario() throws BancoobException {
		GrupoEconomicoNovo grupo = spy(new GrupoEconomicoNovo());
		try {
			ejb.incluir(grupo);
			Assert.fail();
		} catch (CampoNaoInformadoException e) {
			Assert.assertEquals("MN020", e.getMessage());
		}
		verify(grupo).getIdUsuarioCadastro();
		verifyNoMoreInteractions(grupo);
		verifyZeroInteractions(grupoEconomicoNovoDAO, grupoEconomicoAutomaticoPessoaServico, grupoEconomicoManualPessoaServico);
	}

	/**
	 * Tentar gravar um grupo econômico manual sem nome
	 * 
	 * @throws BancoobException
	 */
	@Test
	public void incluirManualSemNome() throws BancoobException {
		GrupoEconomicoNovo grupo = spy(new GrupoEconomicoNovo());
		try {
			grupo.setIdUsuarioCadastro("idUsuarioCadastro");
			ejb.incluir(grupo);
			Assert.fail();
		} catch (CampoNaoInformadoException e) {
			Assert.assertEquals("MN020", e.getMessage());
		}
		verify(grupo).getIdUsuarioCadastro();
		verify(grupo).setIdUsuarioCadastro(anyString());
		verify(grupo).getNome();
		verifyNoMoreInteractions(grupo);
		verifyZeroInteractions(grupoEconomicoNovoDAO, grupoEconomicoAutomaticoPessoaServico, grupoEconomicoManualPessoaServico);
	}

	/**
	 * Tentar gravar um grupo econômico manual sem tipo
	 * 
	 * @throws BancoobException
	 */
	@Test
	public void incluirManualSemTipo() throws BancoobException {
		GrupoEconomicoNovo grupo = spy(new GrupoEconomicoNovo());
		try {
			grupo.setIdUsuarioCadastro("idUsuarioCadastro");
			grupo.setNome("nome");
			ejb.incluir(grupo);
			Assert.fail();
		} catch (CampoNaoInformadoException e) {
			Assert.assertEquals("MN020", e.getMessage());
		}
		verify(grupo).getIdUsuarioCadastro();
		verify(grupo).setIdUsuarioCadastro(anyString());
		verify(grupo).getNome();
		verify(grupo).setNome(anyString());
		verify(grupo).getTipo();
		verifyNoMoreInteractions(grupo);
		verifyZeroInteractions(grupoEconomicoNovoDAO, grupoEconomicoAutomaticoPessoaServico, grupoEconomicoManualPessoaServico);
	}

	/**
	 * Tentar gravar um grupo econômico manual sem idInstituicao
	 * 
	 * @throws BancoobException
	 */
	@Test
	public void incluirManualValidarPorTipoIdInstituicao() throws BancoobException {
		GrupoEconomicoNovo grupo = spy(new GrupoEconomicoNovo());
		try {
			grupo.setIdUsuarioCadastro("idUsuarioCadastro");
			grupo.setNome("nome");
			grupo.setTipo(new TipoGrupoEconomico(TipoGrupoEconomicoEnum.MANUAL.getCodigo()));
			ejb.incluir(grupo);
			Assert.fail();
		} catch (CampoNaoInformadoException e) {
			Assert.assertEquals("MN020", e.getMessage());
		}
		verify(grupo).getIdUsuarioCadastro();
		verify(grupo).setIdUsuarioCadastro(anyString());
		verify(grupo).getNome();
		verify(grupo).setNome(anyString());
		verify(grupo).setTipo(any(TipoGrupoEconomico.class));
		verify(grupo, times(2)).getTipo();
		verify(grupo).getIdInstituicao();
		verifyNoMoreInteractions(grupo);
		verifyZeroInteractions(grupoEconomicoNovoDAO, grupoEconomicoAutomaticoPessoaServico, grupoEconomicoManualPessoaServico);
	}

	/**
	 * Tentar gravar um grupo econômico manual sem tipo
	 * 
	 * @throws BancoobException
	 */
	@Test
	public void incluirManualValidarPorTipoIntegrantes() throws BancoobException {
		GrupoEconomicoNovo grupo = spy(new GrupoEconomicoNovo());
		try {
			grupo.setIdInstituicao(1);
			grupo.setIdUsuarioCadastro("idUsuarioCadastro");
			grupo.setNome("nome");
			grupo.setTipo(new TipoGrupoEconomico(TipoGrupoEconomicoEnum.MANUAL.getCodigo()));
			ejb.incluir(grupo);
			Assert.fail();
		} catch (CAPESCadastroNegocioException e) {
			Assert.assertEquals("msg.erro.grupoEconomico.quantidadeMinimaDeParticipantes", e.getMessage());
		}
		verify(grupo).getIdUsuarioCadastro();
		verify(grupo).setIdUsuarioCadastro(anyString());
		verify(grupo).getNome();
		verify(grupo).setNome(anyString());
		verify(grupo).setTipo(any(TipoGrupoEconomico.class));
		verify(grupo, times(2)).getTipo();
		verify(grupo).getIntegrantesManual();
		verify(grupo).setIdInstituicao(anyInt());
		verify(grupo, times(1)).getIdInstituicao();
		verifyNoMoreInteractions(grupo);
		verifyZeroInteractions(grupoEconomicoNovoDAO, grupoEconomicoAutomaticoPessoaServico, grupoEconomicoManualPessoaServico);
	}

	/**
	 * Tentar gravar um grupo econômico manual com nome repetido
	 * 
	 * @throws BancoobException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void incluirManualNomeRepetido() throws BancoobException {
		List<GrupoEconomicoNovo> listaGrupos = new ArrayList<>();
		listaGrupos.add(new GrupoEconomicoNovo());
		when(grupoEconomicoNovoDAO.listar(any(ConsultaDto.class))).thenReturn(listaGrupos);
		GrupoEconomicoNovo grupo = spy(new GrupoEconomicoNovo());
		try {
			grupo.setIdInstituicao(1);
			grupo.setIdUsuarioCadastro("idUsuarioCadastro");
			grupo.setNome("nome");
			grupo.setTipo(new TipoGrupoEconomico(TipoGrupoEconomicoEnum.MANUAL.getCodigo()));
			List<GrupoEconomicoManualPessoa> integrantes = new ArrayList<>();
			integrantes.add(new GrupoEconomicoManualPessoa());
			integrantes.add(new GrupoEconomicoManualPessoa());
			grupo.setIntegrantesManual(integrantes);
			ejb.incluir(grupo);
			Assert.fail();
		} catch (RegistroMesmoAtributoException e) {
			Assert.assertEquals("MN219", e.getMessage());
		}
		verify(grupo).getIdUsuarioCadastro();
		verify(grupo).setIdUsuarioCadastro(anyString());
		verify(grupo, times(2)).getNome();
		verify(grupo).setNome(anyString());
		verify(grupo).setTipo(any(TipoGrupoEconomico.class));
		verify(grupo, times(4)).getTipo();
		verify(grupo).getIntegrantesManual();
		verify(grupo).setIntegrantesManual(any(List.class));
		verify(grupo).setIdInstituicao(anyInt());
		verify(grupo, times(2)).getIdInstituicao();
		verify(grupoEconomicoNovoDAO).listar(any(ConsultaDto.class));
		verifyNoMoreInteractions(grupo, grupoEconomicoNovoDAO);
		verifyZeroInteractions(grupoEconomicoAutomaticoPessoaServico, grupoEconomicoManualPessoaServico);
	}

	/**
	 * Gravar um grupo econômico manual
	 * 
	 * @throws BancoobException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void incluirManual() throws BancoobException {
		List<GrupoEconomicoNovo> listaGrupos = new ArrayList<>();
		when(grupoEconomicoNovoDAO.listar(any(ConsultaDto.class))).thenReturn(listaGrupos);
		when(grupoEconomicoNovoDAO.incluir(any(GrupoEconomicoNovo.class))).thenReturn(new GrupoEconomicoNovo(1));
		GrupoEconomicoNovo grupo = spy(new GrupoEconomicoNovo());
		grupo.setIdInstituicao(1);
		grupo.setIdUsuarioCadastro("idUsuarioCadastro");
		grupo.setNome("nome");
		grupo.setTipo(new TipoGrupoEconomico(TipoGrupoEconomicoEnum.MANUAL.getCodigo()));
		List<GrupoEconomicoManualPessoa> integrantes = new ArrayList<>();
		integrantes.add(new GrupoEconomicoManualPessoa());
		integrantes.add(new GrupoEconomicoManualPessoa());
		grupo.setIntegrantesManual(integrantes);
		ejb.incluir(grupo);
		verify(grupo, times(3)).getIdUsuarioCadastro();
		verify(grupo).setIdUsuarioCadastro(anyString());
		verify(grupo, times(2)).getNome();
		verify(grupo).setNome(anyString());
		verify(grupo).setTipo(any(TipoGrupoEconomico.class));
		verify(grupo, times(5)).getTipo();
		verify(grupo, times(3)).getIntegrantesManual();
		verify(grupo).setIntegrantesManual(any(List.class));
		verify(grupo).setDataHoraCadastro(any(DateTimeDB.class));
		verify(grupo).setDataHoraInicio(any(DateTimeDB.class));
		verify(grupoEconomicoNovoDAO).incluir(grupo);
		verify(grupoEconomicoNovoDAO).listar(any(ConsultaDto.class));
		verify(grupo).setIdInstituicao(anyInt());
		verify(grupo, times(3)).getIdInstituicao();
		verifyNoMoreInteractions(grupo, grupoEconomicoNovoDAO);
		verifyZeroInteractions(grupoEconomicoAutomaticoPessoaServico, grupoEconomicoManualPessoaServico);
		Assert.assertNotNull(grupo.getDataHoraCadastro());
		Assert.assertNotNull(grupo.getDataHoraInicio());
		for(GrupoEconomicoManualPessoa integrante : integrantes) {
			Assert.assertNotNull(integrante.getDataHoraInicio());
			Assert.assertNotNull(integrante.getIdUsuarioCadastro());
		}
	}
	
	/**
	 * Tentar alterar um grupo econômico manual sem id
	 * 
	 * @throws BancoobException
	 */
	@Test
	public void alterarManualSemId() throws BancoobException {
		GrupoEconomicoNovo grupo = spy(new GrupoEconomicoNovo());
		try {
			ejb.alterar(grupo);
			Assert.fail();
		} catch (CampoNaoInformadoException e) {
			Assert.assertEquals("MN020", e.getMessage());
		}
		verify(grupo).getId();
		verifyNoMoreInteractions(grupo);
		verifyZeroInteractions(grupoEconomicoNovoDAO, grupoEconomicoAutomaticoPessoaServico, grupoEconomicoManualPessoaServico);
	}
	
	/**
	 * Tentar alterar um grupo econômico manual sem idUsuario
	 * 
	 * @throws BancoobException
	 */
	@Test
	public void alterarManualSemIdUsuario() throws BancoobException {
		GrupoEconomicoNovo grupo = spy(new GrupoEconomicoNovo());
		try {
			grupo.setId(1);
			ejb.alterar(grupo);
			Assert.fail();
		} catch (CampoNaoInformadoException e) {
			Assert.assertEquals("MN020", e.getMessage());
		}
		verify(grupo).getId();
		verify(grupo).setId(anyInt());
		verify(grupo).getIdUsuarioCadastro();
		verifyNoMoreInteractions(grupo);
		verifyZeroInteractions(grupoEconomicoNovoDAO, grupoEconomicoAutomaticoPessoaServico, grupoEconomicoManualPessoaServico);
	}

	/**
	 * Tentar alterar um grupo econômico manual sem nome
	 * 
	 * @throws BancoobException
	 */
	@Test
	public void alterarManualSemNome() throws BancoobException {
		GrupoEconomicoNovo grupo = spy(new GrupoEconomicoNovo());
		try {
			grupo.setId(1);
			grupo.setIdUsuarioCadastro("idUsuarioCadastro");
			ejb.alterar(grupo);
			Assert.fail();
		} catch (CampoNaoInformadoException e) {
			Assert.assertEquals("MN020", e.getMessage());
		}
		verify(grupo).getId();
		verify(grupo).setId(anyInt());
		verify(grupo).getIdUsuarioCadastro();
		verify(grupo).setIdUsuarioCadastro(anyString());
		verify(grupo).getNome();
		verifyNoMoreInteractions(grupo);
		verifyZeroInteractions(grupoEconomicoNovoDAO, grupoEconomicoAutomaticoPessoaServico, grupoEconomicoManualPessoaServico);
	}

	/**
	 * Tentar alterar um grupo econômico manual sem tipo
	 * 
	 * @throws BancoobException
	 */
	@Test
	public void alterarManualSemTipo() throws BancoobException {
		GrupoEconomicoNovo grupo = spy(new GrupoEconomicoNovo());
		try {
			grupo.setId(1);
			grupo.setIdUsuarioCadastro("idUsuarioCadastro");
			grupo.setNome("nome");
			ejb.alterar(grupo);
			Assert.fail();
		} catch (CampoNaoInformadoException e) {
			Assert.assertEquals("MN020", e.getMessage());
		}
		verify(grupo).getId();
		verify(grupo).setId(anyInt());
		verify(grupo).getIdUsuarioCadastro();
		verify(grupo).setIdUsuarioCadastro(anyString());
		verify(grupo).getNome();
		verify(grupo).setNome(anyString());
		verify(grupo).getTipo();
		verifyNoMoreInteractions(grupo);
		verifyZeroInteractions(grupoEconomicoNovoDAO, grupoEconomicoAutomaticoPessoaServico, grupoEconomicoManualPessoaServico);
	}

	/**
	 * Tentar alterar um grupo econômico manual sem idInstituicao
	 * 
	 * @throws BancoobException
	 */
	@Test
	public void alterarManualValidarPorTipoIdInstituicao() throws BancoobException {
		GrupoEconomicoNovo grupo = spy(new GrupoEconomicoNovo());
		try {
			grupo.setId(1);
			grupo.setIdUsuarioCadastro("idUsuarioCadastro");
			grupo.setNome("nome");
			grupo.setTipo(new TipoGrupoEconomico(TipoGrupoEconomicoEnum.MANUAL.getCodigo()));
			ejb.alterar(grupo);
			Assert.fail();
		} catch (CampoNaoInformadoException e) {
			Assert.assertEquals("MN020", e.getMessage());
		}
		verify(grupo).getId();
		verify(grupo).setId(anyInt());
		verify(grupo).getIdUsuarioCadastro();
		verify(grupo).setIdUsuarioCadastro(anyString());
		verify(grupo).getNome();
		verify(grupo).setNome(anyString());
		verify(grupo).setTipo(any(TipoGrupoEconomico.class));
		verify(grupo, times(2)).getTipo();
		verify(grupo).getIdInstituicao();
		verifyNoMoreInteractions(grupo);
		verifyZeroInteractions(grupoEconomicoNovoDAO, grupoEconomicoAutomaticoPessoaServico, grupoEconomicoManualPessoaServico);
	}

	/**
	 * Tentar alterar um grupo econômico manual sem tipo
	 * 
	 * @throws BancoobException
	 */
	@Test
	public void alterarManualValidarPorTipoSemIntegrantes() throws BancoobException {
		GrupoEconomicoNovo grupo = spy(new GrupoEconomicoNovo());
		try {
			grupo.setIdInstituicao(1);
			grupo.setId(1);
			grupo.setIdUsuarioCadastro("idUsuarioCadastro");
			grupo.setNome("nome");
			grupo.setTipo(new TipoGrupoEconomico(TipoGrupoEconomicoEnum.MANUAL.getCodigo()));
			ejb.alterar(grupo);
			Assert.fail();
		} catch (CAPESCadastroNegocioException e) {
			Assert.assertEquals("msg.erro.grupoEconomico.quantidadeMinimaDeParticipantes", e.getMessage());
		}
		verify(grupo).getId();
		verify(grupo).setId(anyInt());
		verify(grupo).getIdUsuarioCadastro();
		verify(grupo).setIdUsuarioCadastro(anyString());
		verify(grupo).getNome();
		verify(grupo).setNome(anyString());
		verify(grupo).setTipo(any(TipoGrupoEconomico.class));
		verify(grupo, times(2)).getTipo();
		verify(grupo).getIntegrantesManual();
		verify(grupo).setIdInstituicao(anyInt());
		verify(grupo, times(1)).getIdInstituicao();
		verifyNoMoreInteractions(grupo);
		verifyZeroInteractions(grupoEconomicoNovoDAO, grupoEconomicoAutomaticoPessoaServico, grupoEconomicoManualPessoaServico);
	}

	/**
	 * Tentar alterar um grupo econômico manual com nome repetido
	 * 
	 * @throws BancoobException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void alterarManualNomeRepetidoGrupoDiferente() throws BancoobException {
		List<GrupoEconomicoNovo> listaGrupos = new ArrayList<>();
		listaGrupos.add(new GrupoEconomicoNovo(2));
		when(grupoEconomicoNovoDAO.listar(any(ConsultaDto.class))).thenReturn(listaGrupos);
		GrupoEconomicoNovo grupo = spy(new GrupoEconomicoNovo());
		try {
			grupo.setIdInstituicao(1);
			grupo.setId(1);
			grupo.setIdUsuarioCadastro("idUsuarioCadastro");
			grupo.setNome("nome");
			grupo.setTipo(new TipoGrupoEconomico(TipoGrupoEconomicoEnum.MANUAL.getCodigo()));
			List<GrupoEconomicoManualPessoa> integrantes = new ArrayList<>();
			integrantes.add(new GrupoEconomicoManualPessoa());
			integrantes.add(new GrupoEconomicoManualPessoa());
			grupo.setIntegrantesManual(integrantes);
			ejb.alterar(grupo);
			Assert.fail();
		} catch (RegistroMesmoAtributoException e) {
			Assert.assertEquals("MN219", e.getMessage());
		}
		verify(grupo, times(2)).getId();
		verify(grupo).setId(anyInt());
		verify(grupo).getIdUsuarioCadastro();
		verify(grupo).setIdUsuarioCadastro(anyString());
		verify(grupo, times(2)).getNome();
		verify(grupo).setNome(anyString());
		verify(grupo).setTipo(any(TipoGrupoEconomico.class));
		verify(grupo, times(4)).getTipo();
		verify(grupo).getIntegrantesManual();
		verify(grupo).setIntegrantesManual(any(List.class));
		verify(grupoEconomicoNovoDAO).listar(any(ConsultaDto.class));
		verify(grupo).setIdInstituicao(anyInt());
		verify(grupo, times(2)).getIdInstituicao();
		verifyNoMoreInteractions(grupo, grupoEconomicoNovoDAO);
		verifyZeroInteractions(grupoEconomicoAutomaticoPessoaServico, grupoEconomicoManualPessoaServico);
	}

	/**
	 * Alterar um grupo econômico manual
	 * 
	 * @throws BancoobException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void alterarManualSemItensExclusao() throws BancoobException {
		List<GrupoEconomicoNovo> listaGrupos = new ArrayList<>();
		listaGrupos.add(new GrupoEconomicoNovo(1));
		when(grupoEconomicoNovoDAO.listar(any(ConsultaDto.class))).thenReturn(listaGrupos);
		GrupoEconomicoNovo grupo = spy(new GrupoEconomicoNovo());
		grupo.setIdInstituicao(1);
		grupo.setId(1);
		grupo.setIdUsuarioCadastro("idUsuarioCadastro");
		grupo.setNome("nome");
		grupo.setTipo(new TipoGrupoEconomico(TipoGrupoEconomicoEnum.MANUAL.getCodigo()));
		List<GrupoEconomicoManualPessoa> integrantes = new ArrayList<>();
		GrupoEconomicoManualPessoa integrante1 = new GrupoEconomicoManualPessoa();
		integrante1.setId(1);
		integrantes.add(integrante1);
		
		GrupoEconomicoManualPessoa integrante2 = new GrupoEconomicoManualPessoa();
		integrantes.add(integrante2);

		grupo.setIntegrantesManual(integrantes);
		ejb.alterar(grupo);
		verify(grupo, times(2)).getId();
		verify(grupo).setId(anyInt());
		verify(grupo).getIdUsuarioCadastro();
		verify(grupo).setIdUsuarioCadastro(anyString());
		verify(grupo, times(2)).getNome();
		verify(grupo).setNome(anyString());
		verify(grupo).setTipo(any(TipoGrupoEconomico.class));
		verify(grupo, times(4)).getTipo();
		verify(grupo, times(3)).getIntegrantesManual();
		verify(grupo).setIntegrantesManual(any(List.class));
		verify(grupo).setDataHoraInicio(any(DateTimeDB.class));
		verify(grupo).getIntegrantesManualExclusao();
		verify(grupoEconomicoNovoDAO).alterar(grupo);
		verify(grupoEconomicoNovoDAO).listar(any(ConsultaDto.class));
		verify(grupo).setIdInstituicao(anyInt());
		verify(grupo, times(2)).getIdInstituicao();
		verifyNoMoreInteractions(grupo, grupoEconomicoNovoDAO);
		verifyZeroInteractions(grupoEconomicoAutomaticoPessoaServico, grupoEconomicoManualPessoaServico);
		Assert.assertNotNull(grupo.getDataHoraInicio());
		Assert.assertNotNull(integrante2.getDataHoraInicio());
		Assert.assertNotNull(integrante2.getIdUsuarioCadastro());
	}

	/**
	 * Alterar um grupo econômico manual
	 * 
	 * @throws BancoobException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void alterarManualComItensExclusao() throws BancoobException {
		when(grupoEconomicoNovoDAO.listar(any(ConsultaDto.class))).thenReturn(null);
		GrupoEconomicoNovo grupo = spy(new GrupoEconomicoNovo());
		grupo.setIdInstituicao(1);
		grupo.setId(1);
		grupo.setIdUsuarioCadastro("idUsuarioCadastro");
		grupo.setNome("nome");
		grupo.setTipo(new TipoGrupoEconomico(TipoGrupoEconomicoEnum.MANUAL.getCodigo()));
		List<GrupoEconomicoManualPessoa> integrantes = new ArrayList<>();
		integrantes.add(new GrupoEconomicoManualPessoa());
		integrantes.add(new GrupoEconomicoManualPessoa());
		grupo.setIntegrantesManual(integrantes);
		List<GrupoEconomicoManualPessoa> integrantesExclusao = new ArrayList<>();
		integrantesExclusao.add(new GrupoEconomicoManualPessoa());
		grupo.setIntegrantesManualExclusao(integrantesExclusao);
		ejb.alterar(grupo);
		verify(grupo).getId();
		verify(grupo).setId(anyInt());
		verify(grupo).getIdUsuarioCadastro();
		verify(grupo).setIdUsuarioCadastro(anyString());
		verify(grupo, times(2)).getNome();
		verify(grupo).setNome(anyString());
		verify(grupo).setTipo(any(TipoGrupoEconomico.class));
		verify(grupo, times(4)).getTipo();
		verify(grupo, times(3)).getIntegrantesManual();
		verify(grupo).setIntegrantesManual(any(List.class));
		verify(grupo).setDataHoraInicio(any(DateTimeDB.class));
		verify(grupo).setIntegrantesManualExclusao(any(List.class));
		verify(grupo, times(2)).getIntegrantesManualExclusao();
		verify(grupoEconomicoNovoDAO).alterar(grupo);
		verify(grupoEconomicoNovoDAO).listar(any(ConsultaDto.class));
		verify(grupo).setIdInstituicao(anyInt());
		verify(grupo, times(2)).getIdInstituicao();
		for(GrupoEconomicoManualPessoa objeto : integrantesExclusao){
			verify(grupoEconomicoManualPessoaServico).excluirEntidade(objeto);
		}
		verifyNoMoreInteractions(grupo, grupoEconomicoNovoDAO, grupoEconomicoManualPessoaServico);
		verifyZeroInteractions(grupoEconomicoAutomaticoPessoaServico);
		Assert.assertNotNull(grupo.getDataHoraInicio());
		for(GrupoEconomicoManualPessoa integrante : integrantes) {
			Assert.assertNotNull(integrante.getDataHoraInicio());
			Assert.assertNotNull(integrante.getIdUsuarioCadastro());
		}
	}
}
