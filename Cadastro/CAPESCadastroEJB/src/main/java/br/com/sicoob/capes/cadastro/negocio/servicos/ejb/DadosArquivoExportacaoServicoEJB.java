package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.DadosArquivoExportacaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.DadosArquivoExportacaoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.DadosArquivoExportacaoDAO;
import br.com.sicoob.capes.negocio.entidades.DadosArquivoExportacao;
import br.com.sicoob.capes.negocio.entidades.Exportacao;
import br.com.sicoob.capes.negocio.entidades.TipoInformacao;

/**
 * @since 1.2.1.0
 * @author rodrigo.chaves
 */
@Stateless
@Local(DadosArquivoExportacaoServicoLocal.class)
@Remote(DadosArquivoExportacaoServicoRemote.class)
public class DadosArquivoExportacaoServicoEJB extends CAPESCadastroCrudServicoEJB<DadosArquivoExportacao> implements 
		DadosArquivoExportacaoServicoLocal,	DadosArquivoExportacaoServicoRemote {

	@Inject
	@Default
	private DadosArquivoExportacaoDAO dao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected DadosArquivoExportacaoDAO getDAO() {
		return this.dao;
	}

	/**
	 * {@inheritDoc}
	 */
	public void incluir(List<DadosArquivoExportacao> listaDados) throws BancoobException {
		getDAO().incluir(listaDados);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<TipoInformacao> obterCodigosTipoInformacaoExportacao(Exportacao exportacao) throws BancoobException {
		List<TipoInformacao> listaCodigos = getDAO().obterCodigosTipoInformacaoExportacao(exportacao);
		return listaCodigos;
	}

	/**
	 * {@inheritDoc}
	 */
	public Integer obterProximoNumeroLinha(Exportacao exportacao) throws BancoobException {
		return getDAO().obterProximoNumeroLinha(exportacao);
	}

}