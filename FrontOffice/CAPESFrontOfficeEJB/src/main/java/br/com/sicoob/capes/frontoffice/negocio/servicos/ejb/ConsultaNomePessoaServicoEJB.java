package br.com.sicoob.capes.frontoffice.negocio.servicos.ejb;

import static br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum.CPF_CNPJ;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.srtb.dto.Mensagem;
import br.com.bancoob.srtb.dto.Parametro;
import br.com.bancoob.srtb.dto.RetornoMensagem;
import br.com.bancoob.srtb.excecao.ExcecaoTransacao;
import br.com.bancoob.srtb.montagemconteudo.objeto.definicao.Resultado;
import br.com.bancoob.srtb.montagemconteudo.objeto.definicao.RetornoTransacaoObjeto;
import br.com.bancoob.srtb.servico.Transacao;
import br.com.sicoob.capes.api.negocio.delegates.CAPESApiFabricaDelegates;
import br.com.sicoob.capes.api.negocio.delegates.PessoaDelegate;
import br.com.sicoob.capes.frontoffice.negocio.dto.ConsultaNomePessoaDTO;
import br.com.sicoob.capes.frontoffice.negocio.util.ParametroUtil;
import br.com.sicoob.capes.frontoffice.negocio.validacao.Validador;
import br.com.sicoob.capes.frontoffice.negocio.validacao.ValidadorCampoObrigatorio;

@Stateless
@Remote({ Transacao.class })
public class ConsultaNomePessoaServicoEJB extends CAPESTransacaoServicoEJB {

	@Override
	protected RetornoTransacaoObjeto executarTransacao(Mensagem mensagem, Map<String, Parametro> parametros) throws BancoobException {
		String cpfCnpj = ParametroUtil.recuperarValorParametro(parametros, CPF_CNPJ, String.class);

		// realiza a consulta
		String nomePessoa = obterNomePessoa(cpfCnpj);

		// cria o resultado
		Resultado<ConsultaNomePessoaDTO> resultado = new Resultado<ConsultaNomePessoaDTO>();
		resultado.add(new ConsultaNomePessoaDTO(nomePessoa));

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

	private String obterNomePessoa(String cpfCnpj) throws BancoobException {
		PessoaDelegate delegate = CAPESApiFabricaDelegates.getInstance().criarPessoaDelegate();
		return delegate.obterNomePessoaCompartilhamentoSicoob(cpfCnpj);
	}

	@Override
	protected List<Validador> getValidadores(Map<String, Parametro> parametros) {
		List<Validador> validadores = new ArrayList<Validador>();
		validadores.add(new ValidadorCampoObrigatorio<Object>(CPF_CNPJ.getRotulo(), parametros.get(CPF_CNPJ.getRotulo()).getValor()));
		return validadores;
	}

}