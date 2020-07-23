package br.com.sicoob.capes.api.inclusao.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.sicoob.capes.api.inclusao.negocio.dto.ReferenciaDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.interfaces.ReferenciaServicoLocal;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.interfaces.ReferenciaServicoRemote;
import br.com.sicoob.capes.negocio.entidades.vigente.Referencia;

/**
 * EJB contendo servicos relacionados a Referêsncia.
 * 
 * @author bruno.carneiro
 */
@Stateless
@Local(ReferenciaServicoLocal.class)
@Remote(ReferenciaServicoRemote.class)
public class ReferenciaServicoEJB extends CAPESApiInclusaoServicoEJB<ReferenciaDTO, Referencia> implements ReferenciaServicoLocal, ReferenciaServicoRemote {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] obterPropriedadesObrigatoriasInclusao() {
		return new String[] { "codigoTipoReferencia", "observacao" };
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] obterPropriedadesObrigatoriasAlteracao() {
		List<String> lista = new ArrayList<String>(Arrays.asList(obterPropriedadesObrigatoriasInclusao()));
		lista.add("idReferenciaPessoa");
		return lista.toArray(new String[lista.size()]);
	}

}