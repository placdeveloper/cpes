package br.com.sicoob.capes.api.inclusao.testes;

import static org.junit.Assert.fail;

import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.inclusao.negocio.dto.ClienteDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.interfaces.ClienteServicoRemote;

public class TesteCliente extends CAPESApiInclusaoTesteAbstrato<ClienteDTO> {

	public TesteCliente() {
		super("ClienteServico");
	}

	@Override
	protected void preencherInformacoesEspecificasDTO(ClienteDTO dto) {
		dto.setCodigoFuncionario(281);
		dto.setCodigoNucleo(922);
		dto.setCodigoPerfilTarifario(10);
	}
	
	@Test
	public void testeAlterarGerente() {
		try {
			ClienteDTO dto = instanciarDTO();
			preencherInformacoesDTO(dto);
			ClienteServicoRemote servico = (ClienteServicoRemote) obterServico();
			servico.alterarGerente(dto);
		} catch (BancoobException e) {
			fail(e.getMessage());
		}
	}

}