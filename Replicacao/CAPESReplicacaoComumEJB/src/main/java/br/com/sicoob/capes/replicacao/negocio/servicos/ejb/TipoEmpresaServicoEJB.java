package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.FonteRenda;
import br.com.sicoob.capes.negocio.entidades.legado.TipoEmpresa;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.TipoEmpresaServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.TipoEmpresaServicoRemote;
import br.com.sicoob.capes.replicacao.persistencia.dao.TipoEmpresaDAO;

@Stateless
@Local({ TipoEmpresaServicoLocal.class })
@Remote({ TipoEmpresaServicoRemote.class })
public class TipoEmpresaServicoEJB extends EntidadeDominioReplicavelServicoEJB<TipoEmpresa> implements
		TipoEmpresaServicoLocal, TipoEmpresaServicoRemote {

	@Inject
	@Default
	private transient TipoEmpresaDAO tipoEmpresaDAO;

	@Override
	protected TipoEmpresaDAO getDAO() {
		return tipoEmpresaDAO;
	}
	
	public TipoEmpresa obterTipoEmpresaPorFaixaRendaAnual(FonteRenda renda) throws BancoobException {
		return getDAO().obterTipoEmpresaPorFaixaRendaAnual(renda);
	}	
}
