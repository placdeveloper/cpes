package br.com.sicoob.capes.api.inclusao.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.sicoob.capes.api.inclusao.negocio.dto.MensagemDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.interfaces.MensagemServicoLocal;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.interfaces.MensagemServicoRemote;
import br.com.sicoob.capes.negocio.entidades.Mensagem;

/**
 * Classe com os serviços relacionados à mensagem.
 * 
 * @author Bruno.Carneiro
 */
@Local(MensagemServicoLocal.class)
@Remote(MensagemServicoRemote.class)
@Stateless
public class MensagemServicoEJB extends CAPESApiInclusaoServicoEJB<MensagemDTO, Mensagem> implements MensagemServicoLocal, MensagemServicoRemote {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] obterPropriedadesObrigatoriasInclusao() {
		return new String[] { "codigoTipoMensagem", "codigoTipoDestinoExibicao", "mensagem" };
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] obterPropriedadesObrigatoriasAlteracao() {
		List<String> lista = new ArrayList<String>(Arrays.asList(obterPropriedadesObrigatoriasInclusao()));
		lista.add("idMensagem");
		return lista.toArray(new String[lista.size()]);
	}

}