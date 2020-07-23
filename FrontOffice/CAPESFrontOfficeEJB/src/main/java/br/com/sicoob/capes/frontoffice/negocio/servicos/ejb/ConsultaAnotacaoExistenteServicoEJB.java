/*
 * SICOOB
 * 
 * ConsultaAnotacaoExistenteServicoEJB.java(br.com.sicoob.capes.frontoffice.negocio.servicos.ejb.ConsultaAnotacaoExistenteServicoEJB)
 */
package br.com.sicoob.capes.frontoffice.negocio.servicos.ejb;

import static br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum.CODIGO_TIPO_ANOTACAO;
import static br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum.CPF_CNPJ;
import static br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum.INSTITUICAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.apache.commons.collections.CollectionUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.srtb.dto.Mensagem;
import br.com.bancoob.srtb.dto.Parametro;
import br.com.bancoob.srtb.dto.RetornoMensagem;
import br.com.bancoob.srtb.excecao.ExcecaoTransacao;
import br.com.bancoob.srtb.montagemconteudo.objeto.definicao.Resultado;
import br.com.bancoob.srtb.montagemconteudo.objeto.definicao.RetornoTransacaoObjeto;
import br.com.bancoob.srtb.servico.Transacao;
import br.com.sicoob.capes.api.negocio.delegates.AnotacaoPessoaDelegate;
import br.com.sicoob.capes.api.negocio.delegates.CAPESApiFabricaDelegates;
import br.com.sicoob.capes.api.negocio.vo.AnotacaoPessoaVO;
import br.com.sicoob.capes.frontoffice.negocio.dto.ConsultaAnotacaoExistenteDTO;
import br.com.sicoob.capes.frontoffice.negocio.util.ParametroUtil;
import br.com.sicoob.capes.frontoffice.negocio.validacao.Validador;
import br.com.sicoob.capes.frontoffice.negocio.validacao.ValidadorCampoObrigatorio;

/**
 * Session Bean implementation class ConsultaAnotacaoExistenteServicoEJB
 */
@Stateless
@Remote({ Transacao.class })
public class ConsultaAnotacaoExistenteServicoEJB extends CAPESTransacaoServicoEJB {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RetornoTransacaoObjeto executarTransacao(Mensagem mensagem, Map<String, Parametro> parametros) throws BancoobException {
		String cpfCnpj = ParametroUtil.recuperarValorParametro(parametros, CPF_CNPJ, String.class);
		Integer idInstituicao = ParametroUtil.recuperarValorParametro(parametros, INSTITUICAO, Integer.class);
		Integer codTipoAnotacao = ParametroUtil.recuperarValorParametro(parametros, CODIGO_TIPO_ANOTACAO, Integer.class);

		// realiza a consulta
		List<AnotacaoPessoaVO> anotacoes = obterAnotacoesVigentes(cpfCnpj, idInstituicao, codTipoAnotacao);

		// cria o resultado
		Resultado<ConsultaAnotacaoExistenteDTO> resultado = new Resultado<ConsultaAnotacaoExistenteDTO>();
		resultado.add(new ConsultaAnotacaoExistenteDTO(CollectionUtils.isNotEmpty(anotacoes)));

		// monta o objeto de retorno
		RetornoTransacaoObjeto retornoTransacao = new RetornoTransacaoObjeto();
		retornoTransacao.add(resultado);
		return retornoTransacao;
	}
	
	@Override
	@SuppressWarnings("deprecation")
	protected RetornoMensagem criarConteudoRetornoSucesso(RetornoTransacaoObjeto retornoTransacao, Mensagem mensagem) throws ExcecaoTransacao {
		return obterRetornoMensagemSucesso(retornoTransacao, mensagem);
	}

	/**
	 * Obter anotacoes vigentes.
	 * 
	 * @param cpfCnpj
	 *            the cpf cnpj
	 * @param idInstituicao
	 *            the id instituicao
	 * @param codTipoAnotacao
	 *            the cod tipo anotacao
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	private List<AnotacaoPessoaVO> obterAnotacoesVigentes(String cpfCnpj, Integer idInstituicao, Integer codTipoAnotacao) throws BancoobException {
		AnotacaoPessoaDelegate delegate = CAPESApiFabricaDelegates.getInstance().criarAnotacaoPessoaDelegate();
		List<AnotacaoPessoaVO> anotacoes = delegate.obterVigentesPorPessoaInstituicaoTipo(cpfCnpj, idInstituicao, codTipoAnotacao);
		return anotacoes;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<Validador> getValidadores(Map<String, Parametro> parametros) {
		List<Validador> validadores = new ArrayList<Validador>();
		validadores.add(new ValidadorCampoObrigatorio<Object>(CPF_CNPJ.getRotulo(), parametros.get(CPF_CNPJ.getRotulo()).getValor()));
		validadores.add(new ValidadorCampoObrigatorio<Object>(INSTITUICAO.getRotulo(), parametros.get(INSTITUICAO.getRotulo()).getValor()));
		validadores.add(new ValidadorCampoObrigatorio<Object>(CODIGO_TIPO_ANOTACAO.getRotulo(), parametros.get(CODIGO_TIPO_ANOTACAO.getRotulo()).getValor()));
		return validadores;
	}

}