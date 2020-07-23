package br.com.sicoob.capes.relatorio.negocio.servicos.ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroCrudDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.negocio.entidades.TipoRelacionamentoPessoa;
import br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico;

/**
 * 08/08/2011
 * 
 * @author Rodrigo.Chaves
 */
@Stateless
@Remote(IProcessamentoRelatorioServico.class)
public class RelatorioTipoRelacionamentoPessoaServicoEJB extends CAPESRelatorioDominioServicoEJB<TipoRelacionamentoPessoa> {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getCodigoRelatorio() {
		throw new UnsupportedOperationException(
				"Esta funcionalidade não possui geração de relatório");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getTituloRelatorio() {
		throw new UnsupportedOperationException(
				"Esta funcionalidade não possui geração de relatório");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESCadastroCrudDelegate<?, ?> obterDelegate() throws BancoobException {
		return CAPESCadastroFabricaDelegates.getInstance().criarTipoRelacionamentoPessoaDelegate();
	}

}
