package br.com.sicoob.capes.api.inclusao.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.sicoob.capes.api.inclusao.negocio.dto.EmailDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.interfaces.EmailServicoLocal;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.interfaces.EmailServicoRemote;
import br.com.sicoob.capes.negocio.entidades.vigente.Email;

/**
 * EJB contendo servicos relacionados a Email.
 * 
 * @author bruno.carneiro
 */
@Stateless
@Local(EmailServicoLocal.class)
@Remote(EmailServicoRemote.class)
public class EmailServicoEJB extends CAPESApiInclusaoServicoEJB<EmailDTO, Email> implements EmailServicoLocal, EmailServicoRemote {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] obterPropriedadesObrigatoriasInclusao() {
		return new String[] {"codigoTipoEmail", "descricao" };
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] obterPropriedadesObrigatoriasAlteracao() {
		List<String> lista = new ArrayList<String>(Arrays.asList(obterPropriedadesObrigatoriasInclusao()));
		lista.add("idEmail");
		return lista.toArray(new String[lista.size()]);
	}

}