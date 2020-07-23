package br.com.sicoob.capes.frontoffice.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.srtb.dto.Mensagem;
import br.com.bancoob.srtb.dto.Parametro;
import br.com.bancoob.srtb.montagemconteudo.objeto.definicao.RetornoTransacaoObjeto;
import br.com.bancoob.srtb.servico.Transacao;
import br.com.sicoob.capes.api.inclusao.negocio.delegates.CAPESApiInclusaoFabricaDelegates;
import br.com.sicoob.capes.api.inclusao.negocio.dto.TelefoneDTO;
import br.com.sicoob.capes.api.negocio.delegates.CAPESApiFabricaDelegates;
import br.com.sicoob.capes.api.negocio.delegates.PessoaDelegate;
import br.com.sicoob.capes.api.negocio.vo.PessoaVO;
import br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum;
import br.com.sicoob.capes.frontoffice.negocio.util.ParametroUtil;
import br.com.sicoob.capes.frontoffice.negocio.validacao.Validador;
import br.com.sicoob.capes.frontoffice.negocio.validacao.ValidadorCampoObrigatorio;

@Stateless
@Remote({ Transacao.class })
public class ExcluirTelefonePessoaServicoEJB extends CAPESTransacaoServicoEJB {

	@Override
	protected List<Validador> getValidadores(Map<String, Parametro> parametros) {
		List<Validador> validadores = new ArrayList<Validador>();
		validadores.add(new ValidadorCampoObrigatorio<Object>(ParametroEnum.ID_TELEFONE.getRotulo(), parametros.get(ParametroEnum.ID_TELEFONE.getRotulo()).getValor()));
		validadores.add(new ValidadorCampoObrigatorio<Object>(ParametroEnum.NUMERO_PESSOA.getRotulo(), parametros.get(ParametroEnum.NUMERO_PESSOA.getRotulo()).getValor()));
		validadores.add(new ValidadorCampoObrigatorio<Object>(ParametroEnum.NUMERO_COOP_REMETENTE.getRotulo(), parametros.get(ParametroEnum.NUMERO_COOP_REMETENTE.getRotulo()).getValor()));
		return validadores;
	}

	@Override
	protected RetornoTransacaoObjeto executarTransacao(Mensagem mensagem, Map<String, Parametro> parametros) throws BancoobException {
		//TODO: Verificar o pq foi criado como integer.
 		Integer idTelefonePessoa = ParametroUtil.recuperarValorParametro(parametros, ParametroEnum.ID_TELEFONE, Integer.class);
		Integer idPessoaLegado = ParametroUtil.recuperarValorParametro(parametros, ParametroEnum.NUMERO_PESSOA, Integer.class);
		Integer numCooperativa = ParametroUtil.recuperarValorParametro(parametros, ParametroEnum.NUMERO_COOP_REMETENTE, Integer.class);

		String idUsuario = obterUsuarioCanal(mensagem.getCodigoCanal());
		Integer idInstituicao = obterIdInstituicao(mensagem.getIdInstituicao(), numCooperativa);

		PessoaDelegate pessoaDelegate = CAPESApiFabricaDelegates.getInstance().criarPessoaDelegate();
		PessoaVO pessoa = pessoaDelegate.obterPorPessoaLegadoInstituicao(idPessoaLegado, idInstituicao);

		if (pessoa == null) {
			throw new NegocioException("Pessoa não encontrada");
		}

		TelefoneDTO dto = new TelefoneDTO();
		dto.setId(idTelefonePessoa.longValue());
		dto.setIdInstituicao(pessoa.getIdInstituicao());
		dto.setIdUnidadeInst(pessoa.getIdUnidadeInst());
		dto.setIdUsuarioAprovacao(idUsuario);
		CAPESApiInclusaoFabricaDelegates.obterInstancia().criarTelefoneDelegate().excluir(dto);

		return new RetornoTransacaoObjeto();
	}

}