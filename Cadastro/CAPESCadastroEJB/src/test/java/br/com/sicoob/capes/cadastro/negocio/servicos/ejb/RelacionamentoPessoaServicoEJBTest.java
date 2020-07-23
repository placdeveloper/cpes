package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.CompartilhamentoCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.RelacionamentoPessoaDelegate;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoRelacionamentoPessoaEnum;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.AutorizacaoCadastroServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoEconomicoNovoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.PessoaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TransicaoPessoaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ValidacaoCadastralErroServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ValidacaoCadastralRegraServicoLocal;
import br.com.sicoob.capes.cadastro.persistencia.dao.RelacionamentoPessoaDAO;
import br.com.sicoob.capes.comum.negocio.contexto.InformacoesUsuarioCAPES;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.negocio.entidades.CompartilhamentoCadastro;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;
import br.com.sicoob.capes.negocio.entidades.TipoRelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ CAPESCadastroFabricaDelegates.class })
public class RelacionamentoPessoaServicoEJBTest extends Mockito {

	@InjectMocks
	private RelacionamentoPessoaServicoEJB ejb;

	@Mock
	private RelacionamentoPessoaDAO relacionamentoPessoaDAO;

	@Mock
	private GrupoEconomicoNovoServicoLocal grupoEconomicoAutomaticoServico;

	@Mock
	private PessoaServicoLocal pessoaServico;

	@Mock
	private AutorizacaoCadastroServicoLocal autorizacaoCadastroServico;

	@Mock
	private CAPESCadastroFabricaDelegates cadastroFabricaDelegates;

	@Mock
	private RelacionamentoPessoaDelegate relacionamentoPessoaDelegate;

	@Mock
	private PessoaCompartilhamentoDelegate pessoaCompartilhamentoDelegate;

	@Mock
	private CompartilhamentoCadastroDelegate compartilhamentoCadastroDelegate;

	@Mock
	private ValidacaoCadastralErroServicoLocal validacaoCadastralErroServico;

	@Mock
	private TransicaoPessoaServicoLocal transicaoPessoaServico;

	@Mock
	private ValidacaoCadastralRegraServicoLocal validacaoCadastralRegraServico;

	@Before
	public void setUp() throws BancoobException {
		MockitoAnnotations.initMocks(this);
		Whitebox.setInternalState(CAPESCadastroFabricaDelegates.class, "fabrica", cadastroFabricaDelegates);
		when(cadastroFabricaDelegates.criarRelacionamentoPessoaDelegate()).thenReturn(relacionamentoPessoaDelegate);
		when(cadastroFabricaDelegates.criarPessoaCompartilhamentoDelegate()).thenReturn(pessoaCompartilhamentoDelegate);
		when(cadastroFabricaDelegates.criarCompartilhamentoCadastroDelegate()).thenReturn(compartilhamentoCadastroDelegate);

		InformacoesUsuario.INSTANCIA.set(new InformacoesUsuario());
		InformacoesUsuario.getInstance().setIdInstituicao("143");
		InformacoesUsuarioCAPES.setInstance(new InformacoesUsuarioCAPES(InformacoesUsuario.getInstance(), new Short("1")));
	}

	@After
	public void preDestroy() {
		InformacoesUsuarioCAPES.removeInstance();
	}

	/**
	 * <ul>
	 * <li>Inclusão de sócio</li>
	 * <li>Sem Compartilhamento GED</li>
	 * <li>Sem Vigencia</li>
	 * <li>Sem Reverso</li>
	 * <li>Sem outros sócios</li>
	 * <li>Sem Envio CCS</li>
	 * </ul>
	 * 
	 * @throws BancoobException
	 */
	@SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void teste1() throws BancoobException {

		TipoPessoa tipoPessoaJuridica = new TipoPessoa(TipoPessoaEnum.PESSOA_JURIDICA.getCodigo());
		TipoPessoa tipoPessoaFisica = new TipoPessoa(TipoPessoaEnum.PESSOA_FISICA.getCodigo());

		Pessoa pessoa = new Pessoa();
		pessoa.setId(1);
		pessoa.setTipoPessoa(tipoPessoaJuridica);

		Pessoa pessoaRelacionada = new Pessoa();
		pessoaRelacionada.setId(2);
		pessoaRelacionada.setTipoPessoa(tipoPessoaFisica);

		Set<TipoPessoa> tiposPessoaRelacionamento = new HashSet<>();
		tiposPessoaRelacionamento.add(tipoPessoaJuridica);

		Set<TipoPessoa> tiposPessoaRelacionada = new HashSet<>();
		tiposPessoaRelacionada.add(tipoPessoaFisica);

		TipoRelacionamentoPessoa tipoRelacionamento = new TipoRelacionamentoPessoa();
		tipoRelacionamento.setCodigo(TipoRelacionamentoPessoaEnum.SOCIO.getCodigo());
		tipoRelacionamento.setHabilitaDadosRegistro(false);
		tipoRelacionamento.setTiposPessoaRelacionamento(tiposPessoaRelacionamento);
		tipoRelacionamento.setTiposPessoaRelacionada(tiposPessoaRelacionada);
		tipoRelacionamento.setHabilitaCapitalSocial(true);
		tipoRelacionamento.setHabilitaEnvioCCS(false);

		RelacionamentoPessoa relacionamento = spy(new RelacionamentoPessoa());
		relacionamento.setPessoa(pessoa);
		relacionamento.setPessoaRelacionada(pessoaRelacionada);
		relacionamento.setTipoRelacionamento(tipoRelacionamento);
		relacionamento.setIdInstituicao(143);

		CompartilhamentoCadastro compartilhamentoCadastro = new CompartilhamentoCadastro();
		compartilhamentoCadastro.setUtilizaGedGft(null);
		when(compartilhamentoCadastroDelegate.obter(anyShort())).thenReturn(compartilhamentoCadastro);

		ConsultaDto<RelacionamentoPessoa> retornoPesquisarRelacionamentos = new ConsultaDto<>();
		retornoPesquisarRelacionamentos.setResultado(new ArrayList<RelacionamentoPessoa>());
		when(relacionamentoPessoaDelegate.pesquisar(any(ConsultaDto.class))).thenReturn(retornoPesquisarRelacionamentos);

		ejb.incluir(relacionamento);

		verify(relacionamentoPessoaDAO).incluir(relacionamento);

		verify(cadastroFabricaDelegates, times(1)).criarCompartilhamentoCadastroDelegate();
		verify(cadastroFabricaDelegates, times(1)).criarRelacionamentoPessoaDelegate();

		verify(relacionamentoPessoaDelegate, times(1)).pesquisar(any(ConsultaDto.class));
		verify(relacionamentoPessoaDelegate, times(1)).pesquisarRelacionamentosSemelhantes(eq(pessoa), eq(pessoaRelacionada),
				any(TipoRelacionamentoPessoa.class), anyInt());
		verify(relacionamentoPessoaDelegate, times(1)).pesquisarRelacionamentosVigentesPorFiltro(any(RelacionamentoPessoa.class));
		verify(relacionamentoPessoaDelegate, times(1)).verificaPendenciasPessoaRelacionada(relacionamento);

		verify(compartilhamentoCadastroDelegate, times(1)).obter(anyShort());
		
		verify(relacionamento, times(1)).setDataInicioRelacionamento(any(DateTimeDB.class));

		verifyNoMoreInteractions(relacionamentoPessoaDAO, cadastroFabricaDelegates, relacionamentoPessoaDelegate, compartilhamentoCadastroDelegate);
		verifyZeroInteractions(grupoEconomicoAutomaticoServico, pessoaServico, autorizacaoCadastroServico, pessoaCompartilhamentoDelegate,
				validacaoCadastralErroServico, transicaoPessoaServico, validacaoCadastralRegraServico);

	}
	
	/**
	 * <ul>
	 * <li>Inclusão de Conjuge</li>
	 * <li>Compartilhamento GED - true</li>
	 * <li>Sem Vigencia</li>
	 * <li>Com relacionamento Reverso</li>
	 * </ul>
	 * 
	 * @throws BancoobException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void teste2() throws BancoobException {

		TipoPessoa tipoPessoaFisica = new TipoPessoa(TipoPessoaEnum.PESSOA_FISICA.getCodigo());

		Pessoa pessoa = new Pessoa();
		pessoa.setId(1);
		pessoa.setIdPessoa(1);
		pessoa.setTipoPessoa(tipoPessoaFisica);

		Pessoa pessoaRelacionada = new Pessoa();
		pessoaRelacionada.setId(2);
		pessoaRelacionada.setIdPessoa(2);
		pessoaRelacionada.setTipoPessoa(tipoPessoaFisica);

		Set<TipoPessoa> tiposPessoaRelacionamento = new HashSet<>();
		tiposPessoaRelacionamento.add(tipoPessoaFisica);

		Set<TipoPessoa> tiposPessoaRelacionada = new HashSet<>();
		tiposPessoaRelacionada.add(tipoPessoaFisica);

		TipoRelacionamentoPessoa tipoRelacionamento = new TipoRelacionamentoPessoa();
		tipoRelacionamento.setCodigo(TipoRelacionamentoPessoaEnum.CONJUGE.getCodigo());
		tipoRelacionamento.setHabilitaDadosRegistro(false);
		tipoRelacionamento.setHabilitaCapitalSocial(false);
		tipoRelacionamento.setTiposPessoaRelacionamento(tiposPessoaRelacionamento);
		tipoRelacionamento.setTiposPessoaRelacionada(tiposPessoaRelacionada);
		tipoRelacionamento.setHabilitaEnvioCCS(false);
		tipoRelacionamento.setRelacionamentoReverso(tipoRelacionamento);

		RelacionamentoPessoa relacionamento = spy(new RelacionamentoPessoa());
		relacionamento.setPessoa(pessoa);
		relacionamento.setPessoaRelacionada(pessoaRelacionada);
		relacionamento.setTipoRelacionamento(tipoRelacionamento);
		relacionamento.setIdInstituicao(143);

		CompartilhamentoCadastro compartilhamentoCadastro = new CompartilhamentoCadastro();
		compartilhamentoCadastro.setUtilizaGedGft(true);
		when(compartilhamentoCadastroDelegate.obter(anyShort())).thenReturn(compartilhamentoCadastro);

		ConsultaDto<RelacionamentoPessoa> retornoPesquisarRelacionamentos = new ConsultaDto<>();
		retornoPesquisarRelacionamentos.setResultado(new ArrayList<RelacionamentoPessoa>());
		when(relacionamentoPessoaDelegate.pesquisar(any(ConsultaDto.class))).thenReturn(retornoPesquisarRelacionamentos);

		when(relacionamentoPessoaDAO.incluir(any(RelacionamentoPessoa.class))).thenAnswer(new Answer<RelacionamentoPessoa>(){
            @Override
            public RelacionamentoPessoa answer(InvocationOnMock invocation){
            	RelacionamentoPessoa relacionamento = invocation.getArgument(0);
            	relacionamento.setIdRelacionamento(1l);
                return relacionamento;
            }});
		
		
		
		when(pessoaServico.obter(pessoaRelacionada.getIdPessoa())).thenReturn(pessoaRelacionada);
		
		ejb.incluir(relacionamento, true);

		verify(relacionamentoPessoaDAO, times(2)).incluir(any(RelacionamentoPessoa.class));
		verify(relacionamentoPessoaDAO, times(1)).atualizarRelacionamentoReverso(anyLong(), anyLong());

		verify(cadastroFabricaDelegates, times(1)).criarCompartilhamentoCadastroDelegate();
		verify(cadastroFabricaDelegates, times(1)).criarRelacionamentoPessoaDelegate();

		verify(relacionamentoPessoaDelegate, times(1)).pesquisarRelacionamentosSemelhantes(eq(pessoa), eq(pessoaRelacionada),
				any(TipoRelacionamentoPessoa.class), anyInt());
		verify(relacionamentoPessoaDelegate, times(1)).pesquisarRelacionamentosVigentesPorFiltro(any(RelacionamentoPessoa.class));
		verify(relacionamentoPessoaDelegate, times(1)).verificaPendenciasPessoaRelacionada(relacionamento);

		verify(compartilhamentoCadastroDelegate, times(1)).obter(anyShort());
		
		verify(relacionamento, never()).setDataInicioRelacionamento(any(DateTimeDB.class));
		
		verify(pessoaServico, times(1)).obter(pessoaRelacionada.getIdPessoa());

		verifyNoMoreInteractions(relacionamentoPessoaDAO, cadastroFabricaDelegates, relacionamentoPessoaDelegate, compartilhamentoCadastroDelegate, pessoaServico);
		verifyZeroInteractions(grupoEconomicoAutomaticoServico, autorizacaoCadastroServico, pessoaCompartilhamentoDelegate,
				validacaoCadastralErroServico, transicaoPessoaServico, validacaoCadastralRegraServico);

	}
	
	/**
	 * <ul>
	 * <li>Inclusão de sócio</li>
	 * <li>Compartilhamento GED - false</li>
	 * <li>Com relacionamento Reverso</li>
	 * <li>Sem outros sócios</li>
	 * <li>Envio CCS</li>
	 * </ul>
	 * 
	 * @throws BancoobException
	 */
	@SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void teste3() throws BancoobException {

		TipoPessoa tipoPessoaJuridica = new TipoPessoa(TipoPessoaEnum.PESSOA_JURIDICA.getCodigo());
		TipoPessoa tipoPessoaFisica = new TipoPessoa(TipoPessoaEnum.PESSOA_FISICA.getCodigo());

		Pessoa pessoa = new Pessoa();
		pessoa.setId(1);
		pessoa.setTipoPessoa(tipoPessoaJuridica);

		Pessoa pessoaRelacionada = new Pessoa();
		pessoaRelacionada.setId(2);
		pessoaRelacionada.setTipoPessoa(tipoPessoaFisica);

		Set<TipoPessoa> tiposPessoaRelacionamento = new HashSet<>();
		tiposPessoaRelacionamento.add(tipoPessoaJuridica);

		Set<TipoPessoa> tiposPessoaRelacionada = new HashSet<>();
		tiposPessoaRelacionada.add(tipoPessoaFisica);

		TipoRelacionamentoPessoa tipoRelacionamento = new TipoRelacionamentoPessoa();
		tipoRelacionamento.setCodigo(TipoRelacionamentoPessoaEnum.SOCIO.getCodigo());
		tipoRelacionamento.setHabilitaDadosRegistro(false);
		tipoRelacionamento.setTiposPessoaRelacionamento(tiposPessoaRelacionamento);
		tipoRelacionamento.setTiposPessoaRelacionada(tiposPessoaRelacionada);
		tipoRelacionamento.setHabilitaCapitalSocial(true);
		tipoRelacionamento.setHabilitaEnvioCCS(true);

		RelacionamentoPessoa relacionamento = new RelacionamentoPessoa();
		relacionamento.setPessoa(pessoa);
		relacionamento.setPessoaRelacionada(pessoaRelacionada);
		relacionamento.setTipoRelacionamento(tipoRelacionamento);
		relacionamento.setIdInstituicao(143);
		RelacionamentoPessoa relacionamentoSpy = spy(relacionamento);

		CompartilhamentoCadastro compartilhamentoCadastro = new CompartilhamentoCadastro();
		compartilhamentoCadastro.setUtilizaGedGft(false);
		when(compartilhamentoCadastroDelegate.obter(anyShort())).thenReturn(compartilhamentoCadastro);

		ConsultaDto<RelacionamentoPessoa> retornoPesquisarRelacionamentos = new ConsultaDto<>();
		retornoPesquisarRelacionamentos.setResultado(new ArrayList<RelacionamentoPessoa>());
		when(relacionamentoPessoaDelegate.pesquisar(any(ConsultaDto.class))).thenReturn(retornoPesquisarRelacionamentos);
		
		when(relacionamentoPessoaDAO.incluir(relacionamentoSpy)).thenReturn(relacionamentoSpy);
		when(relacionamentoPessoaDelegate.obterDataMovimentoCCS(anyInt())).thenReturn(new DateTimeDB());
		
		ejb.incluir(relacionamentoSpy, true);
		
		verify(relacionamentoPessoaDAO).incluir(relacionamentoSpy);

		verify(cadastroFabricaDelegates, times(1)).criarCompartilhamentoCadastroDelegate();
		verify(cadastroFabricaDelegates, times(1)).criarRelacionamentoPessoaDelegate();

		verify(relacionamentoPessoaDelegate, times(1)).pesquisar(any(ConsultaDto.class));
		verify(relacionamentoPessoaDelegate, times(1)).pesquisarRelacionamentosSemelhantes(eq(pessoa), eq(pessoaRelacionada),
				any(TipoRelacionamentoPessoa.class), anyInt());
		verify(relacionamentoPessoaDelegate, times(1)).pesquisarRelacionamentosVigentesPorFiltro(any(RelacionamentoPessoa.class));
		verify(relacionamentoPessoaDelegate, times(1)).verificaPendenciasPessoaRelacionada(relacionamentoSpy);
		verify(relacionamentoPessoaDelegate, times(1)).obterDataMovimentoCCS(anyInt());

		verify(compartilhamentoCadastroDelegate, times(1)).obter(anyShort());
		
		verify(relacionamentoSpy, times(1)).setDataInicioRelacionamento(any(DateTimeDB.class));

		verifyNoMoreInteractions(relacionamentoPessoaDAO, cadastroFabricaDelegates, relacionamentoPessoaDelegate, compartilhamentoCadastroDelegate);
		verifyZeroInteractions(grupoEconomicoAutomaticoServico, pessoaServico, autorizacaoCadastroServico, pessoaCompartilhamentoDelegate,
				validacaoCadastralErroServico, transicaoPessoaServico, validacaoCadastralRegraServico);

	}

}
