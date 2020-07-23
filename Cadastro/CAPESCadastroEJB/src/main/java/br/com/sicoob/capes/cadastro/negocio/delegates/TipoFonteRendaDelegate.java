/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.TipoFonteRendaServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.TipoFonteRenda;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;

/**
 * Business delegate para os tipos de fonte de renda. 
 * @author Erico.Junior
 */
public class TipoFonteRendaDelegate extends
		CAPESCadastroCrudDominioDelegate<TipoFonteRenda, TipoFonteRendaServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoFonteRendaServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarTipoFonteRendaServico();
	}

	/**
	 * Lista os tipos de fonte de renda associados ao tipo da pessoa.
	 * @param tipoPessoa O tipo da pessoa utilizado na consulta.
	 * @return Lista dos tipos de fontes de renda associados ao tipo da pessoa
	 */
	public List<TipoFonteRenda> listarPorTipoPessoa(TipoPessoa tipoPessoa) throws BancoobException {
		return localizarServico().listarPorTipoPessoa(tipoPessoa);
	}
}
