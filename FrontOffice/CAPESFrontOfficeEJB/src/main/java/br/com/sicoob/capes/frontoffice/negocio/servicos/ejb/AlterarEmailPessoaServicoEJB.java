package br.com.sicoob.capes.frontoffice.negocio.servicos.ejb;

import static br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum.NUMERO_COOP_REMETENTE;
import static br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum.NUMERO_PESSOA;

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
import br.com.sicoob.capes.api.inclusao.negocio.delegates.EmailDelegate;
import br.com.sicoob.capes.api.inclusao.negocio.dto.EmailDTO;
import br.com.sicoob.capes.api.negocio.delegates.CAPESApiFabricaDelegates;
import br.com.sicoob.capes.api.negocio.delegates.PessoaDelegate;
import br.com.sicoob.capes.api.negocio.vo.PessoaVO;
import br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum;
import br.com.sicoob.capes.frontoffice.negocio.util.ParametroUtil;
import br.com.sicoob.capes.frontoffice.negocio.validacao.Validador;
import br.com.sicoob.capes.frontoffice.negocio.validacao.ValidadorCampoObrigatorio;

@Stateless
@Remote({ Transacao.class })
public class AlterarEmailPessoaServicoEJB extends CAPESTransacaoServicoEJB {

	@Override
	protected List<Validador> getValidadores(Map<String, Parametro> parametros) {
		List<Validador> validadores = new ArrayList<Validador>();
		validadores.add(new ValidadorCampoObrigatorio<Object>(ParametroEnum.NUMERO_PESSOA.getRotulo(), parametros.get(ParametroEnum.NUMERO_PESSOA.getRotulo()).getValor()));
		validadores.add(new ValidadorCampoObrigatorio<Object>(ParametroEnum.NUMERO_COOP_REMETENTE.getRotulo(), parametros.get(ParametroEnum.NUMERO_COOP_REMETENTE.getRotulo()).getValor()));		
		validadores.add(new ValidadorCampoObrigatorio<Object>(ParametroEnum.CODIGO_TIPO_EMAIL.getRotulo(), parametros.get(ParametroEnum.CODIGO_TIPO_EMAIL.getRotulo()).getValor()));
		validadores.add(new ValidadorCampoObrigatorio<Object>(ParametroEnum.DESC_EMAIL.getRotulo(), parametros.get(ParametroEnum.DESC_EMAIL.getRotulo()).getValor()));
		validadores.add(new ValidadorCampoObrigatorio<Object>(ParametroEnum.ID_EMAIL.getRotulo(), parametros.get(ParametroEnum.ID_EMAIL.getRotulo()).getValor()));
		return validadores;
	}
	
	@Override
	protected RetornoTransacaoObjeto executarTransacao(Mensagem mensagem, Map<String, Parametro> parametros) throws BancoobException {
		EmailDTO email = retornaEmailMontado(mensagem, parametros);
		alterarEmailPessoa(email);
		RetornoTransacaoObjeto retornoTransacao = new RetornoTransacaoObjeto();
		return retornoTransacao;
	}
	
	private void alterarEmailPessoa(EmailDTO email) throws BancoobException {
		EmailDelegate emailDelegate = CAPESApiInclusaoFabricaDelegates.obterInstancia().criarEmailDelegate();
		emailDelegate.alterar(email);
	}
	
	private EmailDTO retornaEmailMontado(Mensagem mensagem, Map<String, Parametro> parametros) throws BancoobException {
		Integer idPessoaLegado = ParametroUtil.recuperarValorParametro(parametros, NUMERO_PESSOA, Integer.class);
		Integer numCooperativa = ParametroUtil.recuperarValorParametro(parametros, NUMERO_COOP_REMETENTE, Integer.class);
		
		Short codTipoEmail = ParametroUtil.recuperarValorParametro(parametros, ParametroEnum.CODIGO_TIPO_EMAIL, Short.class);
		String descEmail = ParametroUtil.recuperarValorParametro(parametros, ParametroEnum.DESC_EMAIL, String.class);
		Integer idEmail = ParametroUtil.recuperarValorParametro(parametros, ParametroEnum.ID_EMAIL, Integer.class);
		
		String idUsuarioAprov = obterUsuarioCanal(mensagem.getCodigoCanal());
		Integer idInstituicao = obterIdInstituicao(mensagem.getIdInstituicao(), numCooperativa);
		
		PessoaDelegate pessoaDelegate = CAPESApiFabricaDelegates.getInstance().criarPessoaDelegate();
		PessoaVO pessoa = pessoaDelegate.obterPorPessoaLegadoInstituicao(idPessoaLegado, idInstituicao);
		
		if (pessoa == null) {
			throw new NegocioException("Pessoa não encontrada");
		}
		
		EmailDTO emailDTO = new EmailDTO();
		emailDTO.setIdPessoa(pessoa.getIdPessoa());
		emailDTO.setIdInstituicao(pessoa.getIdInstituicao());
		emailDTO.setIdUnidadeInst(pessoa.getIdUnidadeInst());
		emailDTO.setIdEmail(idEmail.longValue());
		emailDTO.setCodigoTipoEmail(codTipoEmail);
		emailDTO.setDescricao(descEmail);
		emailDTO.setIdUsuarioAprovacao(idUsuarioAprov);
		
		return emailDTO;		
	}

}