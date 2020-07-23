package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.Iterator;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.excecao.CAPESCadastroNegocioException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoTipoAnotacaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoTipoAnotacaoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.GrupoTipoAnotacaoDAO;
import br.com.sicoob.capes.negocio.entidades.GrupoTipoAnotacao;
import br.com.sicoob.capes.negocio.entidades.TipoAnotacao;

/**
 * Classe com os serviços de grupo de tipo anotação.
 * 
 * @author Bruno.Carneiro
 */
@Stateless
@Local({ GrupoTipoAnotacaoServicoLocal.class })
@Remote({ GrupoTipoAnotacaoServicoRemote.class })
public class GrupoTipoAnotacaoServicoEJB extends CAPESCadastroCrudServicoEJB<GrupoTipoAnotacao> implements GrupoTipoAnotacaoServicoLocal, GrupoTipoAnotacaoServicoRemote {

	@Inject
	@Default
	protected GrupoTipoAnotacaoDAO grupoTipoAnotacaoDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected GrupoTipoAnotacaoDAO getDAO() {
		return grupoTipoAnotacaoDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GrupoTipoAnotacao incluir(GrupoTipoAnotacao objeto) throws BancoobException {
		validarGrupoTipoAnotacao(objeto);
		return super.incluir(objeto);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(GrupoTipoAnotacao objeto) throws BancoobException {
		validarGrupoTipoAnotacao(objeto);
		super.alterar(objeto);
	}
	
	/**
	 * Validar o grupo tipo de anotação
	 * 
	 * @param objeto
	 */
	private void validarGrupoTipoAnotacao(GrupoTipoAnotacao objeto) throws BancoobException{
		List<GrupoTipoAnotacao> lista = obterGruposPorTiposAnotacao(objeto.getTiposAnotacao());
		for (Iterator<GrupoTipoAnotacao> iterator = lista.iterator(); iterator.hasNext();) {
			GrupoTipoAnotacao grupo = iterator.next();
		    if (grupo.getId().equals(objeto.getId())) {
		        iterator.remove();
		    }
		}

		// Verifica se a lista ainda possui registros.
		if (CollectionUtils.isNotEmpty(lista)) {
			throw new CAPESCadastroNegocioException("O tipo de anotação já pertence a outro grupo.");
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<GrupoTipoAnotacao> obterGruposPorTipoAnotacao(Integer idTipoAnotacao) throws BancoobException {
		return getDAO().obterGruposPorTipoAnotacao(idTipoAnotacao);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<GrupoTipoAnotacao> obterGruposPorTiposAnotacao(List<TipoAnotacao> tiposAnotacao) throws BancoobException {
		return getDAO().obterGruposPorTiposAnotacao(tiposAnotacao);
	}

}