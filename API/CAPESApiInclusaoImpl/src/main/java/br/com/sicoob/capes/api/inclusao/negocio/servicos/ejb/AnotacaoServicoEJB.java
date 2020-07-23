package br.com.sicoob.capes.api.inclusao.negocio.servicos.ejb;

import java.util.Arrays;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.inclusao.negocio.dto.AnotacaoDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.interfaces.AnotacaoServicoLocal;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.interfaces.AnotacaoServicoRemote;
import br.com.sicoob.capes.cadastro.negocio.delegates.AnotacaoDelegate;
import br.com.sicoob.capes.negocio.entidades.Anotacao;

/**
 * EJB contendo servicos relacionados à anotação.
 * 
 * @author bruno.carneiro
 */
@Stateless
@Local(AnotacaoServicoLocal.class)
@Remote(AnotacaoServicoRemote.class)
public class AnotacaoServicoEJB extends CAPESApiInclusaoServicoEJB<AnotacaoDTO, Anotacao> implements AnotacaoServicoLocal, AnotacaoServicoRemote {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] obterPropriedadesObrigatoriasInclusao() {
		return new String[] { "codigoTipoAnotacao", "dataHoraOcorrencia", "valor", "flexibilidade", "quantidade" };
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] obterPropriedadesObrigatoriasAlteracao() {
		List<String> lista = Arrays.asList(obterPropriedadesObrigatoriasInclusao());
		lista.add("idAnotacao");
		return (String[]) lista.toArray();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void realizarExclusao(Anotacao entidade) throws BancoobException {
		AnotacaoDelegate delegate = (AnotacaoDelegate) obterDelegate();
		delegate.baixarAnotacao(entidade);
	}

}