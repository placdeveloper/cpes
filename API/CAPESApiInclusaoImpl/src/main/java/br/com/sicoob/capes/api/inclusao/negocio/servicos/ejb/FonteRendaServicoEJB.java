package br.com.sicoob.capes.api.inclusao.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.sicoob.capes.api.inclusao.negocio.dto.FonteRendaDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.interfaces.FonteRendaServicoLocal;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.interfaces.FonteRendaServicoRemote;
import br.com.sicoob.capes.negocio.entidades.vigente.FonteRenda;

/**
 * EJB contendo servicos relacionados a FonteRenda.
 * 
 * @author bruno.carneiro
 */
@Stateless
@Local(FonteRendaServicoLocal.class)
@Remote(FonteRendaServicoRemote.class)
public class FonteRendaServicoEJB extends CAPESApiInclusaoServicoEJB<FonteRendaDTO, FonteRenda> implements FonteRendaServicoLocal, FonteRendaServicoRemote {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] obterPropriedadesObrigatoriasInclusao() {
		return new String[] { "codigoTipoFonteRenda" };
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] obterPropriedadesObrigatoriasAlteracao() {
		List<String> lista = new ArrayList<String>(Arrays.asList(obterPropriedadesObrigatoriasInclusao()));
		lista.add("idFonteRenda");
		return lista.toArray(new String[lista.size()]);
	}

}