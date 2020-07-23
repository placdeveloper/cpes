/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.TipoAnotacaoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.TipoAnotacao;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;

/**
 * Business delegate para os tipos de anota��o.  
 * @author Erico.Junior
 */
public class TipoAnotacaoDelegate extends
		CAPESCadastroCrudDelegate<TipoAnotacao, TipoAnotacaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoAnotacaoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarTipoAnotacaoServico();
	}

	/**
	 * Consulta os tipos de anota��es ativos com o tipo de captura MANUAL.
	 * @return Lista de tipos de anota��es ativos  com o tipo de captura MANUAL.
	 */
	public List<TipoAnotacao> listarTiposAnotacaoManualAtivos(TipoPessoa tipoPessoa) {
		return getServico().listarTiposAnotacaoManualAtivos(tipoPessoa);
	}
	
	/**
	 * Carregar instituicoes.
	 *
	 * @param tipoAnotacao o valor de tipo anotacao
	 * @return TipoAnotacao
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public TipoAnotacao carregarInstituicoes(TipoAnotacao tipoAnotacao) throws BancoobException {
		return getServico().carregarInstituicoes(tipoAnotacao);
	}

	/**
	 * Obtem a saida do Tipo de Anota��o informada.
	 * 
	 * @param objeto Tipo de Anota��o
	 * @return Texto de saida.
	 * @throws BancoobException
	 */
	public String obterSaidaTipoAnotacao(TipoAnotacao objeto) throws BancoobException {
		return getServico().obterSaidaTipoAnotacao(objeto);
	}
	
	/**
	 * Verifica se o Tipo Anota��o possui texto de saida.
	 * 
	 * @param objeto Tipo de Anota��o
	 * @return <code>true</true> Se possuir
	 * @throws BancoobException
	 */
	public boolean possuiSaidaTipoAnotacao(TipoAnotacao objeto) throws BancoobException {
		return getServico().possuiSaidaTipoAnotacao(objeto);
	}
}
