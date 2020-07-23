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
import br.com.sicoob.capes.api.negocio.vo.TipoEmpresaVO;
import br.com.sicoob.capes.frontoffice.negocio.dto.ConsultarTipoEmpresaDTO;
import br.com.sicoob.capes.frontoffice.negocio.validacao.Validador;

@Stateless
@Remote({ Transacao.class })
public class ConsultarTipoEmpresaServicoEJB extends CAPESTransacaoServicoEJB {

	@Override
	protected List<Validador> getValidadores(Map<String, Parametro> parametros) {
		return null;
	}

	@Override
	protected RetornoTransacaoObjeto executarTransacao(Mensagem mensagem, Map<String, Parametro> parametros) throws BancoobException {
		List<TipoEmpresaVO> listaVO = obterListaTipoEmpresa();
		Resultado<ConsultarTipoEmpresaDTO> resultado = new Resultado<ConsultarTipoEmpresaDTO>();
		for (TipoEmpresaVO vo : listaVO) {
			ConsultarTipoEmpresaDTO dto = new ConsultarTipoEmpresaDTO();
			dto.setCodTipoEmpresa(vo.getCodTipoEmpresa());
			dto.setDescricao(vo.getDescricao());
			dto.setPossuiAtivoSuperior(vo.getPossuiAtivoSuperior());
			dto.setIsSimplesNacional(vo.getIsSimplesNacional());
			dto.setValorLimiteInferior(vo.getValorLimiteInferior());
			dto.setValorLimiteSuperior(vo.getValorLimiteSuperior());
			resultado.add(dto);
		}
		RetornoTransacaoObjeto retornoTransacao = new RetornoTransacaoObjeto();
		retornoTransacao.add(resultado);
		return retornoTransacao;
	}

	private List<TipoEmpresaVO> obterListaTipoEmpresa() throws BancoobException {
		return CAPESApiFabricaDelegates.getInstance().criarTipoEmpresaDelegate().listar();
	}

}