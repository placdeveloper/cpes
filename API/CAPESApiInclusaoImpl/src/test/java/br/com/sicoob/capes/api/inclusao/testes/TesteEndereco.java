package br.com.sicoob.capes.api.inclusao.testes;

import static org.junit.Assert.fail;

import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.inclusao.negocio.dto.EnderecoDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.interfaces.EnderecoServicoRemote;

/**
 * A Classe TesteEndereco.
 *
 * @author bruno.carneiro
 */
public class TesteEndereco extends CAPESApiInclusaoTesteAbstrato<EnderecoDTO> {

	/**
	 * Instancia um novo TesteEndereco.
	 */
	public TesteEndereco() {
		super("EnderecoServico");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void preencherInformacoesEspecificasDTO(EnderecoDTO dto) {
		dto.setBairro("Springfield");
		dto.setCep("12345678");
		dto.setCodigoLocalidade(3104);
		dto.setCodigoTipoEndereco((short) 2);
		dto.setCodigoTipoLogradouro(99);
		dto.setComplemento("Alameda sempre verde");
		dto.setDescricao("Evergreen Terrace");
		dto.setNumero("7422");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void preencherInformacoesAlteracaoDTO(EnderecoDTO dto) {
		dto.setIdEndereco(124151L);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void preencherInformacoesExclusaoDTO(EnderecoDTO dto) {
		dto.setIdEndereco(124133L);
	}
	
	/**
	 * O método Teste tornar padrao correspondencia.
	 */
	@Test
	public void testeTornarPadraoCorrespondencia() {
		try {
			EnderecoServicoRemote servico = (EnderecoServicoRemote) obterServico();
			servico.tornarPadraoCorrespondencia(16863L, 910);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}
}