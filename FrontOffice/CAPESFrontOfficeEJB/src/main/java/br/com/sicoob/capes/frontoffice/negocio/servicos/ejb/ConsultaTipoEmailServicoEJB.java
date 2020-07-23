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
import br.com.sicoob.capes.api.negocio.delegates.TipoEmailDelegate;
import br.com.sicoob.capes.api.negocio.vo.TipoEmailVO;
import br.com.sicoob.capes.frontoffice.negocio.dto.ConsultaTipoEmailDTO;
import br.com.sicoob.capes.frontoffice.negocio.validacao.Validador;

@Stateless
@Remote({ Transacao.class })
public class ConsultaTipoEmailServicoEJB extends CAPESTransacaoServicoEJB {

	@Override
	protected List<Validador> getValidadores(Map<String, Parametro> parametros) {
		return null;
	}

	@Override
	protected RetornoTransacaoObjeto executarTransacao(Mensagem mensagem, Map<String, Parametro> parametros) throws BancoobException {
		List<TipoEmailVO> lista = obterListaTipoEmail();
		Resultado<ConsultaTipoEmailDTO> resultado = new Resultado<ConsultaTipoEmailDTO>();
		for (TipoEmailVO tipoEmailVO : lista) {
			ConsultaTipoEmailDTO dto = new ConsultaTipoEmailDTO();
			dto.setCodTipoEmail(tipoEmailVO.getCodTipoEmail());
			dto.setDescTipoEmail(tipoEmailVO.getDescTipoEmail());
			resultado.add(dto);
		}
		RetornoTransacaoObjeto retornoTransacao = new RetornoTransacaoObjeto();
		retornoTransacao.add(resultado);
		return retornoTransacao;
	}

	private List<TipoEmailVO> obterListaTipoEmail() throws BancoobException {
		TipoEmailDelegate delegate = CAPESApiFabricaDelegates.getInstance().criarTipoEmailDelegate();
		return delegate.listar();
	}

}