/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ExportacaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ExportacaoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.ExportacaoDAO;
import br.com.sicoob.capes.negocio.entidades.DestinoExportacao;
import br.com.sicoob.capes.negocio.entidades.Exportacao;

/**
 * @since 1.2.1.0
 * @author rodrigo.chaves
 */
@Stateless
@Local(ExportacaoServicoLocal.class)
@Remote(ExportacaoServicoRemote.class)
public class ExportacaoServicoEJB extends CAPESCadastroCrudServicoEJB<Exportacao> implements
        ExportacaoServicoLocal, ExportacaoServicoRemote {

	@Inject
	@Default
	private ExportacaoDAO dao;
	
    /**
	 * {@inheritDoc}
	 */
    @Override
    protected ExportacaoDAO getDAO() {
    	return this.dao;
    }

	/**
	 * {@inheritDoc}
	 */
	public Short obterNumeroSequencial(DestinoExportacao destino) throws BancoobException {
		return getDAO().obterNumeroSequencial(destino);
	}

}
