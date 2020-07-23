package br.com.sicoob.capes.frontoffice.negocio.servicos.ejb;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.srtb.dto.Mensagem;
import br.com.bancoob.srtb.dto.Parametro;
import br.com.bancoob.srtb.montagemconteudo.objeto.definicao.Resultado;
import br.com.bancoob.srtb.montagemconteudo.objeto.definicao.RetornoTransacaoObjeto;
import br.com.bancoob.srtb.servico.Transacao;
import br.com.sicoob.capes.api.negocio.delegates.CAPESApiFabricaDelegates;
import br.com.sicoob.capes.api.negocio.vo.TipoTelefoneVO;
import br.com.sicoob.capes.frontoffice.negocio.dto.ConsultaTipoTelefoneDTO;
import br.com.sicoob.capes.frontoffice.negocio.validacao.Validador;

@Stateless
@Remote({ Transacao.class })
public class ConsultaTipoTelefoneServicoEJB extends CAPESTransacaoServicoEJB {

	@Override
	protected List<Validador> getValidadores(Map<String, Parametro> parametros) {
		return null;
	}

	@Override
	protected RetornoTransacaoObjeto executarTransacao(Mensagem mensagem, Map<String, Parametro> parametros) throws BancoobException {
		List<TipoTelefoneVO> lista = obterListaTipoEmail();
		Resultado<ConsultaTipoTelefoneDTO> resultado = new Resultado<ConsultaTipoTelefoneDTO>();
		for (TipoTelefoneVO vo : lista) {
			ConsultaTipoTelefoneDTO dto = new ConsultaTipoTelefoneDTO();
			dto.setCodTipoTelefone(vo.getCodTipoTelefone());
			dto.setDescTipoTelefone(vo.getDescTipoTelefone());
			resultado.add(dto);
		}
		RetornoTransacaoObjeto retornoTransacao = new RetornoTransacaoObjeto();
		retornoTransacao.add(resultado);
		return retornoTransacao;
	}

	private List<TipoTelefoneVO> obterListaTipoEmail() throws BancoobException {
		return CAPESApiFabricaDelegates.getInstance().criarTipoTelefoneDelegate().listar();
	}
}