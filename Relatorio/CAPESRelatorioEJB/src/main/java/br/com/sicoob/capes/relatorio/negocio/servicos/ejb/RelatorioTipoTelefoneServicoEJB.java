/* 
 * Sicoob
 * RelatorioTipoTelefoneServicoEJB.java 
 * Criado em: 05/05/2011
 */
package br.com.sicoob.capes.relatorio.negocio.servicos.ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroCrudDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.negocio.entidades.TipoTelefone;
import br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico;

/**
 * Implementa as operações do serviço de tipo de telefone
 * 
 * 05/05/2011
 * 
 * @author Rodrigo.Chaves
 * 
 */
@Stateless
@Remote( { IProcessamentoRelatorioServico.class })
public class RelatorioTipoTelefoneServicoEJB extends CAPESRelatorioDominioServicoEJB<TipoTelefone> {


	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getCodigoRelatorio() {
		return "CLI016";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getTituloRelatorio() {
		return "Relatório de Tipo de Telefone";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESCadastroCrudDelegate<?, ?> obterDelegate() throws BancoobException {
		return CAPESCadastroFabricaDelegates.getInstance().criarTipoTelefoneDelegate();
	}

}
