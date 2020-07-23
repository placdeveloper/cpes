package br.com.sicoob.capes.frontoffice.negocio.servicos.ejb;

import static br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum.CODIGO_TIPO_EMAIL;
import static br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum.DESC_EMAIL;
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
import br.com.bancoob.srtb.montagemconteudo.objeto.definicao.Resultado;
import br.com.bancoob.srtb.montagemconteudo.objeto.definicao.RetornoTransacaoObjeto;
import br.com.bancoob.srtb.servico.Transacao;
import br.com.sicoob.capes.api.inclusao.negocio.delegates.CAPESApiInclusaoFabricaDelegates;
import br.com.sicoob.capes.api.inclusao.negocio.dto.EmailDTO;
import br.com.sicoob.capes.api.negocio.delegates.CAPESApiFabricaDelegates;
import br.com.sicoob.capes.api.negocio.delegates.PessoaDelegate;
import br.com.sicoob.capes.api.negocio.vo.PessoaVO;
import br.com.sicoob.capes.frontoffice.negocio.dto.ConsultaEmailPessoaDTO;
import br.com.sicoob.capes.frontoffice.negocio.util.ParametroUtil;
import br.com.sicoob.capes.frontoffice.negocio.validacao.Validador;
import br.com.sicoob.capes.frontoffice.negocio.validacao.ValidadorCampoObrigatorio;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;

@Stateless
@Remote({ Transacao.class })
public class CriarEmailPessoaServicoEJB extends CAPESTransacaoServicoEJB {

	@Override
	protected List<Validador> getValidadores(Map<String, Parametro> parametros) {
		List<Validador> validadores = new ArrayList<Validador>();
		validadores.add(new ValidadorCampoObrigatorio<Object>(NUMERO_PESSOA.getRotulo(), parametros.get(NUMERO_PESSOA.getRotulo()).getValor()));
		validadores.add(new ValidadorCampoObrigatorio<Object>(NUMERO_COOP_REMETENTE.getRotulo(), parametros.get(NUMERO_COOP_REMETENTE.getRotulo()).getValor()));
		validadores.add(new ValidadorCampoObrigatorio<Object>(CODIGO_TIPO_EMAIL.getRotulo(), parametros.get(CODIGO_TIPO_EMAIL.getRotulo()).getValor()));
		validadores.add(new ValidadorCampoObrigatorio<Object>(DESC_EMAIL.getRotulo(), parametros.get(DESC_EMAIL.getRotulo()).getValor()));
		return validadores;
	}

	@Override
	protected RetornoTransacaoObjeto executarTransacao(Mensagem mensagem, Map<String, Parametro> parametros) throws BancoobException {
		EmailDTO emailNovo = retornaEmailMontado(mensagem, parametros);
		emailNovo = criarEmailComRetorno(emailNovo);// Carrega ID email.

		Resultado<ConsultaEmailPessoaDTO> resultado = new Resultado<ConsultaEmailPessoaDTO>();
		if (emailNovo.getIdEmail() != null) {
			ConsultaEmailPessoaDTO dtoRetorno = new ConsultaEmailPessoaDTO();
			dtoRetorno.setIdEmail(emailNovo.getIdEmail());
			resultado.add(dtoRetorno);
		}
		RetornoTransacaoObjeto retornoTransacao = new RetornoTransacaoObjeto();
		retornoTransacao.add(resultado);
		return retornoTransacao;
	}
	
	private EmailDTO criarEmailComRetorno(EmailDTO email) throws BancoobException {
		return CAPESApiInclusaoFabricaDelegates.obterInstancia().criarEmailDelegate().incluir(email);
	}
	
	private EmailDTO retornaEmailMontado(Mensagem mensagem, Map<String, Parametro> parametros) throws BancoobException {
		Integer idPessoaLegado = ParametroUtil.recuperarValorParametro(parametros, NUMERO_PESSOA, Integer.class);
		Integer numCooperativa = ParametroUtil.recuperarValorParametro(parametros, NUMERO_COOP_REMETENTE, Integer.class);
		Short codTipoEmail = ParametroUtil.recuperarValorParametro(parametros, CODIGO_TIPO_EMAIL, Short.class);
		String descEmail = ParametroUtil.recuperarValorParametro(parametros, DESC_EMAIL, String.class);
		
		String idUsuarioAprov = obterUsuarioCanal(mensagem.getCodigoCanal());
		
		Integer idInstituicao = mensagem.getIdInstituicao();
		if(idInstituicao == null){
			SCIIntegracaoDelegate sciDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();
			idInstituicao = sciDelegate.obterIdInstituicao(numCooperativa);
		}
		
		PessoaDelegate pessoaDelegate = CAPESApiFabricaDelegates.getInstance().criarPessoaDelegate();
		PessoaVO pessoa = pessoaDelegate.obterPorPessoaLegadoInstituicao(idPessoaLegado, idInstituicao);
		
		if (pessoa == null) {
			throw new NegocioException("Pessoa não encontrada");
		}
		
		EmailDTO emailDTO = new EmailDTO();
		emailDTO.setIdPessoa(pessoa.getIdPessoa());
		emailDTO.setIdInstituicao(pessoa.getIdInstituicao());
		emailDTO.setIdUnidadeInst(pessoa.getIdUnidadeInst());
		emailDTO.setCodigoTipoEmail(codTipoEmail);
		emailDTO.setDescricao(descEmail);
		emailDTO.setIdUsuarioAprovacao(idUsuarioAprov);
		
		return emailDTO;
	}

}