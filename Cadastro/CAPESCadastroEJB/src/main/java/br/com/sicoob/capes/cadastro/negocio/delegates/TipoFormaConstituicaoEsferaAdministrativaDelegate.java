package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.TipoFormaConstituicaoEsferaAdministrativaServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.comum.negocio.vo.EsferaAdministrativaVO;
import br.com.sicoob.capes.negocio.entidades.TipoFormaConstituicao;
import br.com.sicoob.capes.negocio.entidades.TipoFormaConstituicaoEsferaAdministrativa;

/**
 * A Classe TipoFormaConstituicaoEsferaAdministrativaDelegate.
 */
public class TipoFormaConstituicaoEsferaAdministrativaDelegate extends
		CAPESCadastroCrudDelegate<TipoFormaConstituicaoEsferaAdministrativa, TipoFormaConstituicaoEsferaAdministrativaServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoFormaConstituicaoEsferaAdministrativaServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarTipoFormaConstituicaoEsferaAdministrativaServico();
	}

	/**
	 * O método Gravar alteracoes.
	 *
	 * @param codigoEsfera o valor de codigo esfera
	 * @param listaConstituicaoSelecionados o valor de lista constituicao selecionados
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void gravarAlteracoes(Integer codigoEsfera, List<TipoFormaConstituicao> listaConstituicaoSelecionados) throws BancoobException {
		getServico().gravarAlteracoes(codigoEsfera, listaConstituicaoSelecionados);
	}
	
	/**
	 * Obter esferas administrativas por id forma constituicao.
	 *
	 * @param idFormaConstituicao o valor de id forma constituicao
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<EsferaAdministrativaVO> obterEsferasAdministrativasPorIdFormaConstituicao(Short idFormaConstituicao) throws BancoobException {
		return getServico().obterEsferasAdministrativasPorIdFormaConstituicao(idFormaConstituicao);
	}

}