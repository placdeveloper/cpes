package br.com.sicoob.capes.api.inclusao.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.sicoob.capes.api.inclusao.negocio.dto.RelacionamentoDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.interfaces.RelacionamentoServicoLocal;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.interfaces.RelacionamentoServicoRemote;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;

/**
 * Classe contendo os serviços relacionados à relacionamento.
 * 
 * @author Bruno.Carneiro
 */
@Stateless
@Local(RelacionamentoServicoLocal.class)
@Remote(RelacionamentoServicoRemote.class)
public class RelacionamentoServicoEJB extends CAPESApiInclusaoServicoEJB<RelacionamentoDTO, RelacionamentoPessoa> implements RelacionamentoServicoLocal, RelacionamentoServicoRemote {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] obterPropriedadesObrigatoriasInclusao() {
		return new String[] { "codigoTipoRelacionamento", "idPessoaRelacionada" };
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] obterPropriedadesObrigatoriasAlteracao() {
		List<String> lista = new ArrayList<String>(Arrays.asList(obterPropriedadesObrigatoriasInclusao()));
		lista.add("idRelacionamento");
		return lista.toArray(new String[lista.size()]);
	}

}