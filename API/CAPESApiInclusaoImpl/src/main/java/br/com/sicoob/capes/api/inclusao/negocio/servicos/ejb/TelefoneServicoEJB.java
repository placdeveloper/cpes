package br.com.sicoob.capes.api.inclusao.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.sicoob.capes.api.inclusao.negocio.dto.TelefoneDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.interfaces.TelefoneServicoLocal;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.interfaces.TelefoneServicoRemote;
import br.com.sicoob.capes.negocio.entidades.vigente.Telefone;

/**
 * EJB contendo servicos relacionados a Telefone.
 * 
 * @author bruno.carneiro
 */
@Stateless
@Local(TelefoneServicoLocal.class)
@Remote(TelefoneServicoRemote.class)
public class TelefoneServicoEJB extends CAPESApiInclusaoServicoEJB<TelefoneDTO, Telefone> implements TelefoneServicoLocal, TelefoneServicoRemote {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] obterPropriedadesObrigatoriasInclusao() {
		return new String[] { "codigoTipoTelefone", "telefone" };
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] obterPropriedadesObrigatoriasAlteracao() {
		List<String> lista = new ArrayList<String>(Arrays.asList(obterPropriedadesObrigatoriasInclusao()));
		lista.add("idTelefonePessoa");
		return lista.toArray(new String[lista.size()]);
	}

}