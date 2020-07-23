package br.com.sicoob.capes.frontoffice.negocio.servicos.ejb;

import java.util.ArrayList;
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
import br.com.sicoob.capes.api.negocio.vo.TipoFonteRendaVO;
import br.com.sicoob.capes.frontoffice.negocio.dto.ConsultaTipoFonteRendaDTO;
import br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum;
import br.com.sicoob.capes.frontoffice.negocio.util.ParametroUtil;
import br.com.sicoob.capes.frontoffice.negocio.validacao.Validador;
import br.com.sicoob.capes.frontoffice.negocio.validacao.ValidadorCampoObrigatorio;

@Stateless
@Remote({ Transacao.class })
public class ConsultarTipoFonteRendaServicoEJB extends CAPESTransacaoServicoEJB {

	@Override
	protected List<Validador> getValidadores(Map<String, Parametro> parametros) {
		List<Validador> validadores = new ArrayList<Validador>();
		validadores.add(new ValidadorCampoObrigatorio<Object>(ParametroEnum.TIPO_PESSOA.getRotulo(), parametros.get(ParametroEnum.TIPO_PESSOA.getRotulo()).getValor()));
		return validadores;
	}

	@Override
	protected RetornoTransacaoObjeto executarTransacao(Mensagem mensagem, Map<String, Parametro> parametros) throws BancoobException {
		Integer codigoTipoPessoa = ParametroUtil.recuperarValorParametro(parametros, ParametroEnum.TIPO_PESSOA, Integer.class);
		
		List<TipoFonteRendaVO> listaVO = obterListaTipoFonteRenda(codigoTipoPessoa);
		Resultado<ConsultaTipoFonteRendaDTO> resultado = new Resultado<ConsultaTipoFonteRendaDTO>();
		for (TipoFonteRendaVO vo : listaVO) {
			ConsultaTipoFonteRendaDTO dto = new ConsultaTipoFonteRendaDTO();
			dto.setCodTipoFonteRenda(vo.getCodigo());
			dto.setDescTipoFonteRenda(vo.getDescricao());
			dto.setCodTipoPessoa(vo.getCodigoTipoPessoa());
			dto.setValorObrigatorio(vo.getValorObrigatorio());
			resultado.add(dto);
		}
		RetornoTransacaoObjeto retornoTransacao = new RetornoTransacaoObjeto();
		retornoTransacao.add(resultado);
		return retornoTransacao;
	}

	private List<TipoFonteRendaVO> obterListaTipoFonteRenda(Integer codigoTipoPessoa) throws BancoobException {
		return CAPESApiFabricaDelegates.getInstance().criarTipoFonteRendaDelegate().listarPorTipoPessoa(codigoTipoPessoa.shortValue());
	}

}