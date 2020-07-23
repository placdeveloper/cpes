package br.com.sicoob.capes.api.inclusao.testes;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.inclusao.negocio.dto.EncaminharAutorizacaoDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.ExecutarProcedimentoAutorizacaoDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.RegistroInclusaoDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.CAPESApiInclusaoServico;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;

/**
 * A Classe CAPESApiInclusaoTesteAbstrato.
 * 
 * @param <D>
 *            o dto para uso dos serviços
 *            
 * @author bruno.carneiro
 */
public abstract class CAPESApiInclusaoTesteAbstrato<D extends RegistroInclusaoDTO<?>> {

	/** O atributo nomeServico. */
	private String nomeServico;
	
	/** O atributo servico. */
	private CAPESApiInclusaoServico<D> servico;

	/**
	 * Instancia um novo CAPESApiInclusaoTesteAbstrato.
	 *
	 * @param nomeServico o valor de nome servico
	 */
	public CAPESApiInclusaoTesteAbstrato(String nomeServico) {
		this.nomeServico = nomeServico;
	}

	/**
	 * O método chamado após a construção da classe.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	@Before
	@SuppressWarnings("unchecked")
	public void setUp() throws Exception {
		servico = (CAPESApiInclusaoServico<D>) obterLocator().getObjetoRemoto("capes/api/inclusao/" + nomeServico);
	}
	
	/**
	 * O método Teste incluir.
	 */
	@Test
	public void testeIncluir() {
		try {
			D dto = instanciarDTO();
			preencherInformacoesDTO(dto);
			D retorno = obterServico().incluir(dto);
			verificarRetornoInclusao(retorno);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * O método Teste alterar.
	 */
	@Test
	public void testeAlterar() {
		try {
			D dto = instanciarDTO();
			preencherInformacoesDTO(dto);
			preencherInformacoesAlteracaoDTO(dto);
			obterServico().alterar(dto);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * O método Teste excluir.
	 */
	@Test
	public void testeExcluir() {
		try {
			D dto = instanciarDTO();
			preencherInformacoesDTO(dto);
			preencherInformacoesExclusaoDTO(dto);
			obterServico().excluir(dto);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * O método Teste encaminhar autorização.
	 */
	@Test
	public void testeEncaminharAutorizacao() {
		try {
			EncaminharAutorizacaoDTO dto = new EncaminharAutorizacaoDTO();
			dto.setIdInstituicao(2);
			dto.setIdUnidadeInst(0);
			dto.setIdUsuarioAprovacao("FACA.PARTE");
			dto.setIdRegistro(null);
			dto.setIdInstituicaoRegistro(496);
			dto.setIdPessoa(5015322);
			String idRegistroControlado = obterServico().encaminharAutorizacao(dto);
			Assert.assertNotNull(idRegistroControlado);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * O método teste aprovar autorização
	 */
	@Test
	public void testeExecutarProcedimentoAutorizacao() {
		try {
			ExecutarProcedimentoAutorizacaoDTO dto = new ExecutarProcedimentoAutorizacaoDTO();
			dto.setIdInstituicao(910);
			dto.setIdUnidadeInst(0);
			dto.setIdUsuarioAprovacao("BRUNO.CARNEIRO");
			dto.setIdRegistro(124125L);
			obterServico().executarProcedimentoAutorizacao(dto);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}
	
	/**
	 * Obter servico.
	 *
	 * @return CAPESApiInclusaoServico
	 */
	protected CAPESApiInclusaoServico<D> obterServico() {
		return servico;
	}

	/**
	 * Obter locator.
	 *
	 * @return ServiceLocator
	 * @throws Exception lança a exceção Exception
	 */
	protected ServiceLocator obterLocator() throws Exception {
		return ServiceLocator.getInstance();
	}
	
	/**
	 * O método que preenche as informações do dto.
	 *
	 * @param dto o valor de dto
	 */
	protected void preencherInformacoesDTO(D dto) {
		dto.setIdPessoa(1020095);
		dto.setIdInstituicao(910);
		dto.setIdUnidadeInst(0);
		dto.setIdUsuarioAprovacao("BRUNO.CARNEIRO");
		dto.setVerificarAutorizacao(Boolean.TRUE);
		
		List<Long> listaIdsDocumento = new ArrayList<Long>();
		listaIdsDocumento.add(2147878139L);
		dto.setIdsDocumentos(listaIdsDocumento);
		
		preencherInformacoesEspecificasDTO(dto);
	}

	/**
	 * O método que preenche as informações específicas do dto.
	 *
	 * @param dto o valor de dto
	 */
	protected abstract void preencherInformacoesEspecificasDTO(D dto);
	
	/**
	 * O método que preenche as informações para alteração do DTO.
	 * 
	 * @param dto
	 *            o DTO para ter as informações de alteração preenchidas.
	 */
	protected void preencherInformacoesAlteracaoDTO(D dto) {
		
	}
	
	/**
	 * O método que preenche as informações para a exclusão.
	 * 
	 * @param dto
	 *            O DTO para ter as informações de exclusão preenchidas.
	 */
	protected void preencherInformacoesExclusaoDTO(D dto) {

	}

	/**
	 * Instanciar dto.
	 * 
	 * @return o DTO.
	 */
	@SuppressWarnings("unchecked")
	protected D instanciarDTO() {
		Class<?> classeDTO = ReflexaoUtils.obterParametroGenerico(getClass());
		return (D) ReflexaoUtils.instanciar(classeDTO);
	}
	
	/**
	 * Realiza a verificação do retorno do método de incluir.
	 * 
	 * @param retorno
	 */
	protected void verificarRetornoInclusao(D retorno) {
		Assert.assertTrue(retorno.getId() != null);
	}

}