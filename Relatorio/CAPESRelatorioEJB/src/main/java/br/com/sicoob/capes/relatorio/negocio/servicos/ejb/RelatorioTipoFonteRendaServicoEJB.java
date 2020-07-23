/**
 * 
 */
package br.com.sicoob.capes.relatorio.negocio.servicos.ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroCrudDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.negocio.entidades.TipoFonteRenda;
import br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico;

/**
 * Implementa as operações do serviço de tipo de fonte de renda.
 * 
 * @author Erico.Junior
 */
@Stateless
@Remote( { IProcessamentoRelatorioServico.class })
public class RelatorioTipoFonteRendaServicoEJB extends CAPESRelatorioDominioServicoEJB<TipoFonteRenda> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getTemplateRelatorio() {
		return "RelatorioTipoFonteRenda.jasper";
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getCodigoRelatorio() {
		return "CLI022";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getTituloRelatorio() {
		return "Relatório de Tipo de Fonte de Renda";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESCadastroCrudDelegate<?, ?> obterDelegate() throws BancoobException {
		return CAPESCadastroFabricaDelegates.getInstance().criarTipoFonteRendaDelegate();
	}

}
