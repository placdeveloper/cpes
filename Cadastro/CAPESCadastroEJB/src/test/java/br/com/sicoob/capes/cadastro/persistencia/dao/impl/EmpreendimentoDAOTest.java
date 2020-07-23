package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.sql.Connection;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.persistencia.dao.CapesTestDAO;
import br.com.sicoob.capes.comum.negocio.contexto.InformacoesUsuarioCAPES;
import br.com.sicoob.capes.negocio.entidades.Empreendimento;

/**
 * A Classe EmpreendimentoDAOTest.
 */
@Ignore
public class EmpreendimentoDAOTest extends CapesTestDAO {

	/** A constante TESTE_NOVA_DESCRICAO. */
	private static final String TESTE_NOVA_DESCRICAO = "teste nova descricao";
	
	/** O atributo dao. */
	private EmpreendimentoDAOImpl dao;
 
	/**
	 * O método Test pesquisar proximo codigo.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@Test
	public void testPesquisarProximoCodigo() throws BancoobException {
		InformacoesUsuarioCAPES informacoesUsuarioCAPES = criarInformacoesUsuarioCapes();
		InformacoesUsuarioCAPES.setInstance(informacoesUsuarioCAPES);
		EasyMock.replay(informacoesUsuarioCAPES);
		
		begin();
		Empreendimento empreendimento = criarEmpreendimento();
		empreendimento.setCodigo(dao.pesquisarProximoCodigo().intValue());
		dao.incluir(empreendimento);
		commit();
		
		EasyMock.verify(informacoesUsuarioCAPES);
		Assert.assertEquals(2, dao.pesquisarProximoCodigo().intValue());
	}

	/**
	 * O método Test incluir.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@Test
	public void testIncluir() throws BancoobException {
		InformacoesUsuarioCAPES informacoesUsuarioCAPES = criarInformacoesUsuarioCapes();
		InformacoesUsuarioCAPES.setInstance(informacoesUsuarioCAPES);
		EasyMock.replay(informacoesUsuarioCAPES);
		
		begin();
		Empreendimento empreendimento = criarEmpreendimento();
		empreendimento.setCodigo(dao.pesquisarProximoCodigo().intValue());
		dao.incluir(empreendimento);
		commit();
		
		EasyMock.verify(informacoesUsuarioCAPES);
		Assert.assertNotNull(dao.obter(empreendimento.getCodigo()));
	}

	/**
	 * O método Test excluir.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@Test
	public void testExcluir() throws BancoobException {
		InformacoesUsuarioCAPES informacoesUsuarioCAPES = criarInformacoesUsuarioCapes();
		InformacoesUsuarioCAPES.setInstance(informacoesUsuarioCAPES);
		EasyMock.replay(informacoesUsuarioCAPES);
		
		begin();
		Empreendimento empreendimento = criarEmpreendimento();
		empreendimento.setCodigo(dao.pesquisarProximoCodigo().intValue());
		dao.incluir(empreendimento);
		dao.excluir(empreendimento.getCodigo());
		commit();
		
		EasyMock.verify(informacoesUsuarioCAPES);
		Assert.assertNull(dao.obter(empreendimento.getCodigo()));
		
	}

	/**
	 * O método Test alterar.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@Test
	public void testAlterar() throws BancoobException {
		InformacoesUsuarioCAPES informacoesUsuarioCAPES = criarInformacoesUsuarioCapes();
		InformacoesUsuarioCAPES.setInstance(informacoesUsuarioCAPES);
		EasyMock.replay(informacoesUsuarioCAPES);
		
		begin();
		Empreendimento empreendimento = criarEmpreendimento();
		empreendimento.setCodigo(dao.pesquisarProximoCodigo().intValue());
		dao.incluir(empreendimento);
		commit();
		
		empreendimento.setDescricao(TESTE_NOVA_DESCRICAO);
		dao.alterar(empreendimento);
		commit();
		
		EasyMock.verify(informacoesUsuarioCAPES);
		Assert.assertEquals(dao.obter(empreendimento.getCodigo()).getDescricao(), TESTE_NOVA_DESCRICAO);
	}

	/**
	 * O método Test listar.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@Test
	public void testListar() throws BancoobException {
		InformacoesUsuarioCAPES informacoesUsuarioCAPES = criarInformacoesUsuarioCapes();
		InformacoesUsuarioCAPES.setInstance(informacoesUsuarioCAPES);
		EasyMock.replay(informacoesUsuarioCAPES);
		
		begin();
		Empreendimento empreendimento = criarEmpreendimento();
		empreendimento.setCodigo(dao.pesquisarProximoCodigo().intValue());
		dao.incluir(empreendimento);
		commit();
		
		EasyMock.verify(informacoesUsuarioCAPES);
		Assert.assertEquals(dao.listar().size(), 1);
	}

	/**
	 * O método Test listar consulta dto of t.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@Test
	public void testListarConsultaDtoOfT() throws BancoobException {
		InformacoesUsuarioCAPES informacoesUsuarioCAPES = criarInformacoesUsuarioCapes();
		InformacoesUsuarioCAPES.setInstance(informacoesUsuarioCAPES);
		EasyMock.replay(informacoesUsuarioCAPES);
		
		begin();
		Empreendimento empreendimento = criarEmpreendimento();
		empreendimento.setCodigo(dao.pesquisarProximoCodigo().intValue());
		dao.incluir(empreendimento);
		commit();
		
		ConsultaDto<Empreendimento> consultaDto = new ConsultaDto<Empreendimento>();
		consultaDto.setFiltro(empreendimento);
		
		EasyMock.verify(informacoesUsuarioCAPES);
		Assert.assertEquals(dao.listar(consultaDto).size(), 1);
		
	}

	/**
	 * O método Test pesquisar.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@Test
	public void testPesquisar() throws BancoobException {
		InformacoesUsuarioCAPES informacoesUsuarioCAPES = criarInformacoesUsuarioCapes();
		InformacoesUsuarioCAPES.setInstance(informacoesUsuarioCAPES);
		EasyMock.replay(informacoesUsuarioCAPES);
		
		begin();
		Empreendimento empreendimento = criarEmpreendimento();
		empreendimento.setCodigo(dao.pesquisarProximoCodigo().intValue());
		dao.incluir(empreendimento);
		commit();
		
		ConsultaDto<Empreendimento> consultaDto = new ConsultaDto<Empreendimento>();
		consultaDto.setFiltro(empreendimento);
		
		EasyMock.verify(informacoesUsuarioCAPES);
		Assert.assertEquals(dao.pesquisar(consultaDto).getResultado().size(), 1);
	}

	/**
	 * O método Test obter.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@Test
	public void testObter() throws BancoobException {
		InformacoesUsuarioCAPES informacoesUsuarioCAPES = criarInformacoesUsuarioCapes();
		InformacoesUsuarioCAPES.setInstance(informacoesUsuarioCAPES);
		EasyMock.replay(informacoesUsuarioCAPES);
		
		begin();
		Empreendimento empreendimento = criarEmpreendimento();
		empreendimento.setCodigo(dao.pesquisarProximoCodigo().intValue());
		dao.incluir(empreendimento);
		commit();
		
		EasyMock.verify(informacoesUsuarioCAPES);
		Assert.assertNotNull(dao.obter(empreendimento.getCodigo()));
	}

	/**
	 * Criar empreendimento.
	 *
	 * @return Empreendimento
	 */
	private Empreendimento criarEmpreendimento() {
		Empreendimento empreendimento = new Empreendimento();
		empreendimento.setAtivo(true);
		empreendimento.setCodigoBacen("123");
		empreendimento.setDescricao("teste");
		empreendimento.setExigeArea(true);
		empreendimento.setExigeImovel(true);
		return empreendimento;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void setUp() {
		dao = new EmpreendimentoDAOImpl() {
			@Override
			protected Connection estabelecerConexao() {
				return getConnection();
			}
		};
		dao.setEntityManager(getEntityManager());
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
		EasyMock.expect(informacoesUsuarioCAPES.getCodigoCompartilhamento()).andReturn(null).anyTimes();
		return informacoesUsuarioCAPES;
	}

}
