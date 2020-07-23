package br.com.sicoob.capes.api.inclusao.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.sicoob.capes.api.inclusao.negocio.dto.InformacaoProfissionalDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.interfaces.InformacaoProfissionalServicoLocal;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.interfaces.InformacaoProfissionalServicoRemote;
import br.com.sicoob.capes.negocio.entidades.vigente.InformacaoProfissional;

/**
 * EJB contendo servicos relacionados a Informacao Profissional.
 * 
 * @author bruno.carneiro
 */
@Stateless
@Local(InformacaoProfissionalServicoLocal.class)
@Remote(InformacaoProfissionalServicoRemote.class)
public class InformacaoProfissionalServicoEJB extends CAPESApiInclusaoServicoEJB<InformacaoProfissionalDTO, InformacaoProfissional> implements
		InformacaoProfissionalServicoLocal, InformacaoProfissionalServicoRemote {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] obterPropriedadesObrigatoriasInclusao() {
		return new String[] { "codigoTipoFuncionario", "idPessoaEmpregador", "numMatricula", "dataAdmissao", "codSituacao" };
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] obterPropriedadesObrigatoriasAlteracao() {
		List<String> lista = new ArrayList<String>(Arrays.asList(obterPropriedadesObrigatoriasInclusao()));
		lista.add("idInformacaoProfissional");
		return lista.toArray(new String[lista.size()]);
	}

}