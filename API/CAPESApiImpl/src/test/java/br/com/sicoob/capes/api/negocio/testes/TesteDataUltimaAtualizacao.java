package br.com.sicoob.capes.api.negocio.testes;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.DataUltimaAtualizacaoServicoRemote;
import br.com.sicoob.capes.comum.negocio.vo.DataUltimaAtualizacaoVO;

/**
 * A Classe TesteDataUltimaAtualizacao.
 */
public class TesteDataUltimaAtualizacao {
	
	/** O atributo dataUltimaAtualizacaoServico. */
	DataUltimaAtualizacaoServicoRemote dataUltimaAtualizacaoServico;

	/**
	 * O método Sets the up.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	@Before
	public void setUp() throws Exception {
		ServiceLocator locator = ServiceLocator.getInstance();
		dataUltimaAtualizacaoServico = (DataUltimaAtualizacaoServicoRemote) locator.getObjetoRemoto("capes/api/DataUltimaAtualizacaoServico");
	}
	
	/**
	 * O método Teste obter por pessoa instituicao.
	 */
	@Test
	public void testeObterPorPessoaInstituicao() {
		try {
			Date data = dataUltimaAtualizacaoServico.obterByPessoaInstituicao(20609, 910);
			Assert.assertNotNull(data);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	/**
	 * O método Teste.
	 */
	@Test
	public void teste() {
		try {
			List<DataUltimaAtualizacaoVO> retorno = dataUltimaAtualizacaoServico.consultarPorDataReferenciaPessoas(910, new Date(1388591718581L), new Integer[] { 
						652362, 652361, 652360, 652333, 20831, 644553, 20609, 652293, 652253, 644555, 652316, 652315, 652314, 652313, 652273, 652235, 652234, 
						652233, 644557, 644554, 20774, 21496, 21070, 21078,	648258, 644563, 644564, 644562, 644560, 644559, 644558, 644556 });
			Assert.assertNotNull(retorno);
			Assert.assertFalse(retorno.isEmpty());
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}
}