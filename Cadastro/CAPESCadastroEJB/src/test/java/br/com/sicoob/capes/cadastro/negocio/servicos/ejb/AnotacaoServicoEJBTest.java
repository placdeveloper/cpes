package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaAnotacaoDTO;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaAnotacaoSisbrDTO;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoBaixaEnum;
import br.com.sicoob.capes.cadastro.negocio.excecao.AnotacaoJaFlexibilizadaException;
import br.com.sicoob.capes.cadastro.negocio.excecao.CampoNaoInformadoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.FlexibilizacaoAnotacaoException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.OrigemInformacaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.PessoaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoAnotacaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.vo.SumarioAnotacaoVO;
import br.com.sicoob.capes.cadastro.persistencia.dao.AnotacaoDAO;
import br.com.sicoob.capes.comum.negocio.contexto.InformacoesUsuarioCAPES;
import br.com.sicoob.capes.negocio.entidades.Anotacao;
import br.com.sicoob.capes.negocio.entidades.AnotacaoSisbr;
import br.com.sicoob.capes.negocio.entidades.CategoriaAnotacao;
import br.com.sicoob.capes.negocio.entidades.OrigemInformacao;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.TipoAnotacao;
import br.com.sicoob.capes.negocio.entidades.TipoCaptura;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;

/**
 * A Classe AnotacaoServicoEJBTest.
 */
public class AnotacaoServicoEJBTest {

	/**
	 * O método Test flexibilizar.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@Test
	public void testFlexibilizar() throws BancoobException {
		AnotacaoServicoEJB ejb = new AnotacaoServicoEJB();

		AnotacaoDAO anotacaoDAO = EasyMock.createMock(AnotacaoDAO.class);
		InformacoesUsuarioCAPES informacoesUsuarioCAPES = criarInformacoesUsuarioCapes();

		EasyMock.expect(anotacaoDAO.obter(EasyMock.anyObject(Serializable.class))).andReturn(criarAnotacao());
		anotacaoDAO.alterar(EasyMock.anyObject(Anotacao.class));

		ejb.setAnotacaoDao(anotacaoDAO);
		
		InformacoesUsuarioCAPES.setInstance(informacoesUsuarioCAPES);

		EasyMock.replay(anotacaoDAO, informacoesUsuarioCAPES);
		ejb.flexibilizar(criarAnotacao());
		EasyMock.verify(anotacaoDAO, informacoesUsuarioCAPES);
	}

	/**
	 * O método Test flexibilizar false.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@Test
	public void testFlexibilizarFalse() throws BancoobException {
		AnotacaoServicoEJB ejb = new AnotacaoServicoEJB();

		AnotacaoDAO anotacaoDAO = EasyMock.createMock(AnotacaoDAO.class);

		Anotacao criarAnotacao = criarAnotacao();
		criarAnotacao.setFlexibilidade(true);
		EasyMock.expect(anotacaoDAO.obter(EasyMock.anyObject(Serializable.class))).andReturn(criarAnotacao);

		ejb.setAnotacaoDao(anotacaoDAO);

		EasyMock.replay(anotacaoDAO);
		boolean exception = true;
		try {
			ejb.flexibilizar(criarAnotacao);
			Assert.fail("Erro");
		} catch (Exception e) {
			exception = false;
			Assert.assertEquals(AnotacaoJaFlexibilizadaException.class, e.getClass());
		}
		Assert.assertFalse(exception);
		EasyMock.verify(anotacaoDAO);
	}

	/**
	 * O método Test flexibilizar categoria relativa.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@Test
	public void testFlexibilizarCategoriaRelativa() throws BancoobException {
		AnotacaoServicoEJB ejb = new AnotacaoServicoEJB();

		AnotacaoDAO anotacaoDAO = EasyMock.createMock(AnotacaoDAO.class);

		Anotacao criarAnotacao = criarAnotacao();
		criarAnotacao.getTipoAnotacao().getCategoriaAnotacao().setIdCategoriaAnotacao(Short.valueOf("1"));
		EasyMock.expect(anotacaoDAO.obter(EasyMock.anyObject(Serializable.class))).andReturn(criarAnotacao);

		ejb.setAnotacaoDao(anotacaoDAO);

		EasyMock.replay(anotacaoDAO);
		boolean exception = true;
		try {
			ejb.flexibilizar(criarAnotacao);
			Assert.fail("Erro");
		} catch (Exception e) {
			exception = false;
			Assert.assertEquals(FlexibilizacaoAnotacaoException.class, e.getClass());
		}
		Assert.assertFalse(exception);
		EasyMock.verify(anotacaoDAO);
	}

	/**
	 * O método Test pesquisar sumario anotacao.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@Test
	public void testPesquisarSumarioAnotacao() throws BancoobException {
		AnotacaoServicoEJB ejb = new AnotacaoServicoEJB();

		OrigemInformacaoServicoLocal origemInformacaoServicoLocal = EasyMock.createMock(OrigemInformacaoServicoLocal.class);
		AnotacaoDAO anotacaoDAO = EasyMock.createMock(AnotacaoDAO.class);

		List<SumarioAnotacaoVO> listaSumarioAnotacao = new ArrayList<SumarioAnotacaoVO>();
		SumarioAnotacaoVO sumarioAnotacaoVO = new SumarioAnotacaoVO();
		listaSumarioAnotacao.add(sumarioAnotacaoVO);
		EasyMock.expect(anotacaoDAO.listarSumarioAnotacoesVigentes(EasyMock.anyObject(PessoaCompartilhamento.class))).andReturn(listaSumarioAnotacao);

		PessoaFisica pessoaFisica = new PessoaFisica();
		pessoaFisica.setIdPessoaCompartilhamento(1L);

		ejb.setAnotacaoDao(anotacaoDAO);

		EasyMock.replay(origemInformacaoServicoLocal, anotacaoDAO);
		ejb.pesquisarSumarioAnotacao(pessoaFisica);
		EasyMock.verify(origemInformacaoServicoLocal, anotacaoDAO);
	}

	/**
	 * O método Test pesquisar sumario anotacao pessoa null.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@Test
	public void testPesquisarSumarioAnotacaoPessoaNull() throws BancoobException {
		AnotacaoServicoEJB ejb = new AnotacaoServicoEJB();
		boolean exception = true;
		try {
			ejb.pesquisarSumarioAnotacao(null);
			Assert.fail("Erro");
		} catch (Exception e) {
			exception = false;
			Assert.assertEquals(CampoNaoInformadoException.class, e.getClass());
		}
		Assert.assertFalse(exception);
	}

	/**
	 * O método Test pesquisar sumario anotacao id pessoa compartilhamento null.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@Test
	public void testPesquisarSumarioAnotacaoIdPessoaCompartilhamentoNull() throws BancoobException {
		AnotacaoServicoEJB ejb = new AnotacaoServicoEJB();
		boolean exception = true;
		try {
			ejb.pesquisarSumarioAnotacao(new PessoaFisica());
			Assert.fail("Erro");
		} catch (Exception e) {
			exception = false;
			Assert.assertEquals(CampoNaoInformadoException.class, e.getClass());
		}
		Assert.assertFalse(exception);
	}

	/**
	 * O método Test listar anotacoes por filtro.
	 */
	@Test
	public void testListarAnotacoesPorFiltro() {
		AnotacaoServicoEJB ejb = new AnotacaoServicoEJB();

		AnotacaoDAO anotacaoDAO = EasyMock.createMock(AnotacaoDAO.class);
		EasyMock.expect(anotacaoDAO.listarAnotacoesPorFiltro(EasyMock.anyObject(ConsultaAnotacaoDTO.class))).andReturn(Arrays.asList(criarAnotacao()));

		ejb.setAnotacaoDao(anotacaoDAO);

		EasyMock.replay(anotacaoDAO);
		ejb.listarAnotacoesPorFiltro(new ConsultaAnotacaoDTO());
		EasyMock.verify(anotacaoDAO);
	}

	/**
	 * O método Test listar anotacoes sisbr.
	 */
	@Test
	public void testListarAnotacoesSisbr() {
		AnotacaoServicoEJB ejb = new AnotacaoServicoEJB();

		AnotacaoDAO anotacaoDAO = EasyMock.createMock(AnotacaoDAO.class);
		EasyMock.expect(anotacaoDAO.listarAnotacoesSisbr(EasyMock.anyObject(ConsultaAnotacaoSisbrDTO.class))).andReturn(Arrays.asList(new AnotacaoSisbr()));

		ejb.setAnotacaoDao(anotacaoDAO);

		EasyMock.replay(anotacaoDAO);
		ejb.listarAnotacoesSisbr(new ConsultaAnotacaoSisbrDTO());
		EasyMock.verify(anotacaoDAO);
	}

	/**
	 * O método Test alterar anotacao.
	 */
	@Test
	public void testAlterarAnotacao() {
		AnotacaoServicoEJB ejb = new AnotacaoServicoEJB();
		boolean exception = true;
		try {
			ejb.alterar(new Anotacao());
			Assert.fail("Erro");
		} catch (Exception e) {
			exception = false;
			Assert.assertEquals(UnsupportedOperationException.class, e.getClass());
		}
		Assert.assertFalse(exception);
	}

	/**
	 * O método Test excluir serializable.
	 */
	@Test
	public void testExcluirSerializable() {
		AnotacaoServicoEJB ejb = new AnotacaoServicoEJB();
		boolean exception = true;
		try {
			ejb.excluir(new Anotacao());
			Assert.fail("Erro");
		} catch (Exception e) {
			exception = false;
			Assert.assertEquals(UnsupportedOperationException.class, e.getClass());
		}
		Assert.assertFalse(exception);
	}

	/**
	 * O método Test incluir anotacao.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@Test
	@Ignore
	public void testIncluirAnotacao() throws BancoobException {
		AnotacaoServicoEJB ejb = new AnotacaoServicoEJB();

		AnotacaoDAO anotacaoDAO = EasyMock.createMock(AnotacaoDAO.class);
		TipoAnotacaoServicoLocal servicoTipoAnotacao = EasyMock.createMock(TipoAnotacaoServicoLocal.class);
		InformacoesUsuarioCAPES informacoesUsuarioCAPES = criarInformacoesUsuarioCapes();
		PessoaServicoLocal servicoPessoa = EasyMock.createMock(PessoaServicoLocal.class);

		EasyMock.expect(anotacaoDAO.obterAnotacoesPorGrupoTipoAnotacao(EasyMock.anyLong(), EasyMock.anyShort())).andReturn(new ArrayList<Anotacao>());
		EasyMock.expect(servicoTipoAnotacao.obter(EasyMock.anyObject(Serializable.class))).andReturn(criarTipoAnotacao());
		EasyMock.expect(servicoPessoa.isPessoaFisica(EasyMock.anyObject(Pessoa.class))).andReturn(true);
		EasyMock.expect(anotacaoDAO.incluir(EasyMock.anyObject(Anotacao.class))).andReturn(null);

		ejb.setAnotacaoDao(anotacaoDAO);
		ejb.setServicoTipoAnotacao(servicoTipoAnotacao);
			
		InformacoesUsuarioCAPES.setInstance(informacoesUsuarioCAPES);
		ejb.setServicoPessoa(servicoPessoa);

		EasyMock.replay(anotacaoDAO, informacoesUsuarioCAPES, servicoTipoAnotacao, servicoPessoa);
		ejb.incluir(criarAnotacao());
		EasyMock.verify(anotacaoDAO, informacoesUsuarioCAPES, servicoTipoAnotacao, servicoPessoa);
	}

	/**
	 * O método Test incluir list of anotacao.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@Test
	@Ignore
	public void testIncluirListOfAnotacao() throws BancoobException {
		AnotacaoServicoEJB ejb = new AnotacaoServicoEJB();

		AnotacaoDAO anotacaoDAO = EasyMock.createMock(AnotacaoDAO.class);
		TipoAnotacaoServicoLocal servicoTipoAnotacao = EasyMock.createMock(TipoAnotacaoServicoLocal.class);
		InformacoesUsuarioCAPES informacoesUsuarioCAPES = criarInformacoesUsuarioCapes();
		PessoaServicoLocal servicoPessoa = EasyMock.createMock(PessoaServicoLocal.class);

		EasyMock.expect(anotacaoDAO.obterAnotacoesPorGrupoTipoAnotacao(EasyMock.anyLong(), EasyMock.anyShort())).andReturn(new ArrayList<Anotacao>());
		EasyMock.expect(servicoTipoAnotacao.obter(EasyMock.anyObject(Serializable.class))).andReturn(criarTipoAnotacao());
		EasyMock.expect(servicoPessoa.isPessoaFisica(EasyMock.anyObject(Pessoa.class))).andReturn(true);
		EasyMock.expect(anotacaoDAO.incluir(EasyMock.anyObject(Anotacao.class))).andReturn(null);

		ejb.setAnotacaoDao(anotacaoDAO);
		ejb.setServicoTipoAnotacao(servicoTipoAnotacao);
			
		InformacoesUsuarioCAPES.setInstance(informacoesUsuarioCAPES);
		ejb.setServicoPessoa(servicoPessoa);

		EasyMock.replay(anotacaoDAO, informacoesUsuarioCAPES, servicoTipoAnotacao, servicoPessoa);
		ejb.incluir(Arrays.asList(criarAnotacao()));
		EasyMock.verify(anotacaoDAO, informacoesUsuarioCAPES, servicoTipoAnotacao, servicoPessoa);
	}

	/**
	 * O método Test baixar anotacao.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@Test
	public void testBaixarAnotacao() throws BancoobException {
		AnotacaoServicoEJB ejb = new AnotacaoServicoEJB();

		InformacoesUsuarioCAPES informacoesUsuarioCAPES = criarInformacoesUsuarioCapes();
		AnotacaoDAO anotacaoDAO = EasyMock.createMock(AnotacaoDAO.class);

		EasyMock.expect(anotacaoDAO.obter(EasyMock.anyObject(Serializable.class))).andReturn(criarAnotacao());
		anotacaoDAO.alterar(EasyMock.anyObject(Anotacao.class));

		ejb.setAnotacaoDao(anotacaoDAO);
		
		InformacoesUsuarioCAPES.setInstance(informacoesUsuarioCAPES);

		EasyMock.replay(anotacaoDAO, informacoesUsuarioCAPES);
		ejb.baixarAnotacao(criarAnotacao());
		EasyMock.verify(anotacaoDAO, informacoesUsuarioCAPES);
	}

	/**
	 * O método Test baixar anotacoes.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@Test
	public void testBaixarAnotacoes() throws BancoobException {
		AnotacaoServicoEJB ejb = new AnotacaoServicoEJB();

		InformacoesUsuarioCAPES informacoesUsuarioCAPES = criarInformacoesUsuarioCapes();
		AnotacaoDAO anotacaoDAO = EasyMock.createMock(AnotacaoDAO.class);

		anotacaoDAO.alterar(EasyMock.anyObject(Anotacao.class));

		ejb.setAnotacaoDao(anotacaoDAO);
			
		
		InformacoesUsuarioCAPES.setInstance(informacoesUsuarioCAPES);

		EasyMock.replay(anotacaoDAO, informacoesUsuarioCAPES);
		ejb.baixarAnotacoes(Arrays.asList(criarAnotacao()));
		EasyMock.verify(anotacaoDAO, informacoesUsuarioCAPES);
	}

	/**
	 * O método Test baixar anotacoes automaticas.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@Test
	public void testBaixarAnotacoesAutomaticas() throws BancoobException {
		AnotacaoServicoEJB ejb = new AnotacaoServicoEJB();

		InformacoesUsuarioCAPES informacoesUsuarioCAPES = criarInformacoesUsuarioCapes();
		AnotacaoDAO anotacaoDAO = EasyMock.createMock(AnotacaoDAO.class);

		anotacaoDAO.alterar(EasyMock.anyObject(Anotacao.class));

		ejb.setAnotacaoDao(anotacaoDAO);
			
		
		InformacoesUsuarioCAPES.setInstance(informacoesUsuarioCAPES);

		EasyMock.replay(anotacaoDAO, informacoesUsuarioCAPES);
		ejb.baixarAnotacoesAutomaticas(Arrays.asList(criarAnotacao()), TipoBaixaEnum.BAIXA_AUTOMATICA_NOVA_CONSULTA);
		EasyMock.verify(anotacaoDAO, informacoesUsuarioCAPES);
	}

	/**
	 * O método Test listar anotacoes para relatorio.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@Test
	public void testListarAnotacoesParaRelatorio() throws BancoobException {
		AnotacaoServicoEJB ejb = new AnotacaoServicoEJB();

		AnotacaoDAO anotacaoDAO = EasyMock.createMock(AnotacaoDAO.class);
		EasyMock.expect(anotacaoDAO.listarAnotacoesParaRelatorio(EasyMock.anyObject(ConsultaAnotacaoDTO.class))).andReturn(null);

		ejb.setAnotacaoDao(anotacaoDAO);

		EasyMock.replay(anotacaoDAO);
		ejb.listarAnotacoesParaRelatorio(new ConsultaAnotacaoDTO());
		EasyMock.verify(anotacaoDAO);
	}

	/**
	 * O método Test get em cadastro unico clientes comum.
	 */
	@Test
	public void testGetEmCadastroUnicoClientesComum() {
	}

	/**
	 * O método Test importar.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@Test
	@Ignore
	public void testImportar() throws BancoobException {
		AnotacaoServicoEJB ejb = new AnotacaoServicoEJB();

		AnotacaoDAO anotacaoDAO = EasyMock.createMock(AnotacaoDAO.class);
		TipoAnotacaoServicoLocal servicoTipoAnotacao = EasyMock.createMock(TipoAnotacaoServicoLocal.class);
		InformacoesUsuarioCAPES informacoesUsuarioCAPES = criarInformacoesUsuarioCapes();
		PessoaServicoLocal servicoPessoa = EasyMock.createMock(PessoaServicoLocal.class);

		EasyMock.expect(anotacaoDAO.obterAnotacoesPorGrupoTipoAnotacao(EasyMock.anyLong(), EasyMock.anyShort())).andReturn(new ArrayList<Anotacao>());
		EasyMock.expect(servicoTipoAnotacao.obter(EasyMock.anyObject(Serializable.class))).andReturn(criarTipoAnotacao());
		EasyMock.expect(servicoPessoa.isPessoaFisica(EasyMock.anyObject(Pessoa.class))).andReturn(true);
		EasyMock.expect(anotacaoDAO.incluir(EasyMock.anyObject(Anotacao.class))).andReturn(null);

		ejb.setAnotacaoDao(anotacaoDAO);
		ejb.setServicoTipoAnotacao(servicoTipoAnotacao);
			
		InformacoesUsuarioCAPES.setInstance(informacoesUsuarioCAPES);
		ejb.setServicoPessoa(servicoPessoa);

		EasyMock.replay(anotacaoDAO, informacoesUsuarioCAPES, servicoTipoAnotacao, servicoPessoa);
		ejb.importar(criarAnotacao());
		EasyMock.verify(anotacaoDAO, informacoesUsuarioCAPES, servicoTipoAnotacao, servicoPessoa);
	}

	/**
	 * O método Test is cadastro receita regular.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@Test
	public void testIsCadastroReceitaRegular() throws BancoobException {
		AnotacaoServicoEJB ejb = new AnotacaoServicoEJB();

		AnotacaoDAO anotacaoDAO = EasyMock.createMock(AnotacaoDAO.class);
		EasyMock.expect(anotacaoDAO.isCadastroReceitaRegular(EasyMock.anyObject(PessoaCompartilhamento.class))).andReturn(true);

		ejb.setAnotacaoDao(anotacaoDAO);

		EasyMock.replay(anotacaoDAO);
		ejb.isCadastroReceitaRegular(new PessoaFisica());
		EasyMock.verify(anotacaoDAO);
	}

	/**
	 * Criar anotacao.
	 *
	 * @return Anotacao
	 */
	private Anotacao criarAnotacao() {
		Anotacao anotacao = new Anotacao();
		anotacao.setDataHoraOcorrencia(new DateTimeDB());
		anotacao.setDataHoraAnotacao(new DateTimeDB());
		anotacao.setFlexibilidade(false);
		anotacao.setPessoaCompartilhamento(new PessoaFisica());
		TipoAnotacao tipoAnotacao = criarTipoAnotacao();
		anotacao.setTipoAnotacao(tipoAnotacao);
		OrigemInformacao origemInformacao = new OrigemInformacao();
		origemInformacao.setIdOrigemInfo(Short.valueOf("2"));
		anotacao.setOrigemInformacao(origemInformacao);
		return anotacao;
	}

	/**
	 * Criar tipo anotacao.
	 *
	 * @return TipoAnotacao
	 */
	private TipoAnotacao criarTipoAnotacao() {
		TipoAnotacao tipoAnotacao = new TipoAnotacao();
		tipoAnotacao.setIdTipoAplicacao(Short.valueOf("1"));
		CategoriaAnotacao categoriaAnotacao = new CategoriaAnotacao();
		categoriaAnotacao.setIdCategoriaAnotacao(Short.valueOf("2"));
		tipoAnotacao.setCategoriaAnotacao(categoriaAnotacao);
		TipoCaptura tipoCaptura = new TipoCaptura();
		tipoCaptura.setIdTipoCaptura(Short.valueOf("3"));
		tipoAnotacao.setTipoCaptura(tipoCaptura);
		tipoAnotacao.setValidaUsoAnotacao(Boolean.FALSE);
		tipoAnotacao.setCompartilhar(Boolean.TRUE);
		return tipoAnotacao;
	}

	/**
	 * Criar informacoes usuario capes.
	 *
	 * @return InformacoesUsuarioCAPES
	 */
	private InformacoesUsuarioCAPES criarInformacoesUsuarioCapes() {
		InformacoesUsuarioCAPES informacoesUsuarioCAPES = EasyMock.createMock(InformacoesUsuarioCAPES.class);
		EasyMock.expect(informacoesUsuarioCAPES.getCooperativa()).andReturn("123").anyTimes();
		EasyMock.expect(informacoesUsuarioCAPES.getIdInstituicao()).andReturn("123").anyTimes();
		EasyMock.expect(informacoesUsuarioCAPES.getIdUnidadeInstituicao()).andReturn("123").anyTimes();
		EasyMock.expect(informacoesUsuarioCAPES.getLogin()).andReturn("diego.rezende").anyTimes();
		EasyMock.expect(informacoesUsuarioCAPES.getPac()).andReturn("123").anyTimes();
		return informacoesUsuarioCAPES;
	}

}
