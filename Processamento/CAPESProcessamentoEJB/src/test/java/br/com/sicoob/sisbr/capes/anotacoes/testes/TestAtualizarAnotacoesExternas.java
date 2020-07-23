package br.com.sicoob.sisbr.capes.anotacoes.testes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.sicoob.capes.comum.negocio.dto.AnotacaoExternaDTO;
import br.com.sicoob.capes.comum.negocio.dto.AnotacaoInformacaoDTO;

/**
 * A Classe TestAtualizarAnotacoesExternas.
 */
public class TestAtualizarAnotacoesExternas extends AbstractTestAtualizarAnotacoes{
	
	/**
	 * método executado no início dos testes e que
	 * estabelece uma conexão com o serviço JMS
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		estabelecerConexao();
	}
	
	/**
	 * método executado no final dos testes e que fecha as conexões
	 * do publicador, da sessão e da conexão
	 *  
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		finalizarConexao();		
	}
	
	/**
	 * Teste de envio de mensagem para uma PF com o CPF que nada consta
	 */
	@Test
	public void testAtualizarAnotacaoExterna() {
		AnotacaoExternaDTO dto = new AnotacaoExternaDTO();
		
		dto.setDataBaseBacen(new Date());
		dto.setIdConsulta(null);
		dto.setIdInstituicao(910);
		dto.setIdUnidadeInst(0);
		dto.setNomeOrgaoOrigem("BACEN");
		dto.setNomeReceita("");
		dto.setNomeTipoConsulta("SCR");
		dto.setNumCpfCnpj("90123185998");
		dto.setObservacao("");
		dto.setPercentualProcessadoBacen((double) 80);
		
		AnotacaoInformacaoDTO dtoInformacao = new AnotacaoInformacaoDTO();
		dtoInformacao.setCodigoTipoOrigemInformacao("210");
		dtoInformacao.setDataOcorrencia(new Date());
		dtoInformacao.setQuantidade(0);
		dtoInformacao.setValor(BigDecimal.ZERO);
		
		AnotacaoInformacaoDTO dtoInformacao2 = new AnotacaoInformacaoDTO();
		dtoInformacao2.setCodigoTipoOrigemInformacao("260");
		dtoInformacao2.setDataOcorrencia(new Date());
		dtoInformacao2.setQuantidade(0);
		dtoInformacao2.setValor(BigDecimal.ZERO);
		
		AnotacaoInformacaoDTO dtoInformacao3 = new AnotacaoInformacaoDTO();
		dtoInformacao3.setCodigoTipoOrigemInformacao("310");
		dtoInformacao3.setDataOcorrencia(new Date());
		dtoInformacao3.setQuantidade(0);
		dtoInformacao3.setValor(BigDecimal.ZERO);
		
		List<AnotacaoInformacaoDTO> listaInformacao = new ArrayList<AnotacaoInformacaoDTO>();
		listaInformacao.add(dtoInformacao);
		listaInformacao.add(dtoInformacao2);
		listaInformacao.add(dtoInformacao3);
		
		dto.setListaAnotacoes(listaInformacao);
		
		send(dto);
	}
	
	/**
	 * Teste de envio de mensagem para uma PF com o CPF que nada consta
	 */
	@Test
	public void testImportarArquivoReceitaFederal() {
		AnotacaoExternaDTO dto = new AnotacaoExternaDTO();
		
		dto.setDataBaseBacen(null);
		dto.setIdConsulta(null);
		dto.setIdInstituicao(null);// na importacao do arquivo este valor nao eh passado
		dto.setIdUnidadeInst(null);// na importacao do arquivo este valor nao eh passado
		dto.setNomeOrgaoOrigem("RFB");
		dto.setNomeReceita("XCANDHFADNÇA ADNÇAFDA NÇAFRB");
		dto.setNomeTipoConsulta("RFBFISICA");
		dto.setNumCpfCnpj("28356194881");
//		dto.setNumCpfCnpj("00146187105");
		dto.setObservacao(null);
		dto.setPercentualProcessadoBacen(null);
		dto.setListaAnotacoes(new ArrayList<AnotacaoInformacaoDTO>());

		send(dto);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String obterNomeDestino() {
		return "QL.CONT.EXEC.CONS.EXT";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String obterNomeFabrica() {
		return "/XAConnectionFactory";
	}

}