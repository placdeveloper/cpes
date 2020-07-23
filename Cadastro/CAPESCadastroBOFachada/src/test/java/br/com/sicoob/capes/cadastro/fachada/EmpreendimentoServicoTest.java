package br.com.sicoob.capes.cadastro.fachada;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.SelGeralReqDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.sicoob.capes.cadastro.negocio.delegates.EmpreendimentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.FinalidadeEmpreendimentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.UnidadeMedidaDelegate;
import br.com.sicoob.capes.negocio.entidades.Empreendimento;
import br.com.sicoob.capes.negocio.entidades.FinalidadeEmpreendimento;
import br.com.sicoob.capes.negocio.entidades.UnidadeMedida;

/**
 * A Classe EmpreendimentoServicoTest.
 */
@SuppressWarnings("unchecked")
public class EmpreendimentoServicoTest {

	/**
	 * O método Obter entidade test.
	 */
	@Test
	public void obterEntidadeTest() {
		EmpreendimentoFachada empreendimentoServico = new EmpreendimentoFachada();
		RequisicaoReqDTO dto = new RequisicaoReqDTO();
		Map<String, Object> dados = new HashMap<String, Object>();
		dados.put("empreendimento", new Empreendimento());
		dto.setDados(dados);
		Empreendimento obterEntidade = empreendimentoServico.obterEntidade(dto);
		Assert.assertNotNull(obterEntidade);
	}

	/**
	 * O método Obter dados selecao test.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@Test
	public void obterDadosSelecaoTest() throws BancoobException {
		EmpreendimentoFachada empreendimentoServico = new EmpreendimentoFachada();
		EmpreendimentoDelegate empreendimentoDelegateMock = EasyMock
				.createMock(EmpreendimentoDelegate.class);
		ConsultaDto<Empreendimento> consultaDto = new ConsultaDto<Empreendimento>();
		consultaDto.setPagina(1);
		consultaDto.setTotalRegistros(1);
		
		EasyMock.expect(
				empreendimentoDelegateMock.pesquisar(EasyMock
						.anyObject(ConsultaDto.class)))
				.andReturn(consultaDto).anyTimes();
		empreendimentoServico.setCertidaoDelegate(empreendimentoDelegateMock);
		
		EasyMock.replay(empreendimentoDelegateMock);
		Assert.assertNotNull(empreendimentoServico.obterDadosSelecao(new SelGeralReqDTO()));
		EasyMock.verify(empreendimentoDelegateMock);
	}
	
	/**
	 * O método Obter definicoes test.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@Test
	public void obterDefinicoesTest() throws BancoobException {
		EmpreendimentoDelegate empreendimentoDelegateMock = EasyMock
				.createMock(EmpreendimentoDelegate.class);
		FinalidadeEmpreendimentoDelegate finalidadeEmpreendimentoDelegate = EasyMock
				.createMock(FinalidadeEmpreendimentoDelegate.class);
		UnidadeMedidaDelegate unidadeMedidaDelegate = EasyMock
				.createMock(UnidadeMedidaDelegate.class);
		
		EasyMock.expect(empreendimentoDelegateMock.pesquisarProximoCodigo()).andReturn(1);
		EasyMock.expect(unidadeMedidaDelegate.listar()).andReturn(new ArrayList<UnidadeMedida>());
		EasyMock.expect(finalidadeEmpreendimentoDelegate.listar()).andReturn(new ArrayList<FinalidadeEmpreendimento>());
		
		EmpreendimentoFachada empreendimentoServico = new EmpreendimentoFachada();
		empreendimentoServico.setCertidaoDelegate(empreendimentoDelegateMock);
		empreendimentoServico.setFinalidadeEmpreendimentoDelegate(finalidadeEmpreendimentoDelegate);
		empreendimentoServico.setUnidadeMedidaDelegate(unidadeMedidaDelegate);
		
		EasyMock.replay(empreendimentoDelegateMock, finalidadeEmpreendimentoDelegate, unidadeMedidaDelegate);
		Assert.assertNotNull(empreendimentoServico.obterDefinicoes(new RequisicaoReqDTO()));
		EasyMock.verify(empreendimentoDelegateMock, finalidadeEmpreendimentoDelegate, unidadeMedidaDelegate);
	}

}
