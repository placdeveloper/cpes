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
import br.com.sicoob.capes.api.negocio.vo.TipoEnderecoVO;
import br.com.sicoob.capes.frontoffice.negocio.dto.ConsultaTipoEnderecoDTO;
import br.com.sicoob.capes.frontoffice.negocio.validacao.Validador;

@Stateless
@Remote({ Transacao.class })
public class ConsultarTipoEnderecoPessoaServicoEJB extends CAPESTransacaoServicoEJB {

	@Override
	protected List<Validador> getValidadores(Map<String, Parametro> parametros) {
		return null;
	}

	@Override
	protected RetornoTransacaoObjeto executarTransacao(Mensagem mensagem, Map<String, Parametro> parametros) throws BancoobException {
		List<TipoEnderecoVO> listaVO = obterListaTipoEndereco();
		Resultado<ConsultaTipoEnderecoDTO> resultado = new Resultado<ConsultaTipoEnderecoDTO>();
		for (TipoEnderecoVO vo : listaVO) {
			ConsultaTipoEnderecoDTO dto = new ConsultaTipoEnderecoDTO();
			dto.setCodTipoEndereco(vo.getCodTipoEndereco());
			dto.setDescTipoEndereco(vo.getDescTipoEndereco());
			dto.setIdTipoPessoaContato(vo.getIdTipoPessoaContato());
			resultado.add(dto);
		}
		RetornoTransacaoObjeto retornoTransacao = new RetornoTransacaoObjeto();
		retornoTransacao.add(resultado);
		return retornoTransacao;
	}

	private List<TipoEnderecoVO> obterListaTipoEndereco() throws BancoobException {
		return CAPESApiFabricaDelegates.getInstance().criarTipoEnderecoDelegate().listar();
	}
}