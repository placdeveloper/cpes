package br.com.sicoob.capes.api.inclusao.testes;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.inclusao.negocio.dto.BemDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.BemProprietarioDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.interfaces.BemServicoRemote;

/**
 * Classe para os testes de bens.
 * 
 * @author Bruno.Carneiro
 */
public class TesteBem extends CAPESApiInclusaoTesteAbstrato<BemDTO> {

	/**
	 * Construtor
	 */
	public TesteBem() {
		super("BemServico");
	}

	/**
	 * Método para inclusão do bem do tipo 'SEM PATRIMÔNIO'.
	 */
	@Test
	public void testeIncluirSemPatrimonio() {
		try {
			BemDTO dto = instanciarDTO();
			preencherInformacoesDTO(dto);

			BemServicoRemote servico = (BemServicoRemote) obterServico();
			servico.incluirSemPatrimonio(dto);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Método para inclusão do bem do tipo 'RECUSOU-SE A INFORMAR'.
	 */
	@Test
	public void testeIncluirRecusouInformar() {
		try {
			BemDTO dto = instanciarDTO();
			preencherInformacoesDTO(dto);

			BemServicoRemote servico = (BemServicoRemote) obterServico();
			servico.incluirRecusouInformar(dto);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void preencherInformacoesEspecificasDTO(BemDTO dto) {
		dto.setIdPessoa(20772);
		dto.setIdInstituicao(910);
		dto.setIdUnidadeInst(0);
		dto.setIdUsuarioAprovacao("BRUNO.CARNEIRO");
		dto.setDescricao("Teste");
		dto.setMesRenovacaoSeguro((short) 12);
		dto.setValor(new BigDecimal(12019));
	}
	
	@Test
	public void testeAlterarProprietarios() {
		try {
			BemDTO dto = instanciarDTO();
			preencherInformacoesEspecificasDTO(dto);
			dto.setId(1223L);
			
			List<BemProprietarioDTO> proprietarios = new ArrayList<BemProprietarioDTO>();
			BemProprietarioDTO prop = new BemProprietarioDTO();
			prop.setIdInstituicao(910);
			prop.setIdPessoa(5665392);
			prop.setPercentualProprietario(new BigDecimal(100));
			prop.setPessoaResponsavel(Boolean.TRUE);
			proprietarios.add(prop);
			
			BemServicoRemote servico = (BemServicoRemote) obterServico();
			servico.alterarProprietarios(dto, proprietarios);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testeExcluirProprietarios() {
		try {
			BemDTO dto = instanciarDTO();
			preencherInformacoesEspecificasDTO(dto);
			dto.setId(1042L);

			BemServicoRemote servico = (BemServicoRemote) obterServico();
			servico.alterarProprietarios(dto, null);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}
	
}