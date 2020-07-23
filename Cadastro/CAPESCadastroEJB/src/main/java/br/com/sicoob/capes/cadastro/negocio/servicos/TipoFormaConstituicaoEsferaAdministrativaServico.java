package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.vo.EsferaAdministrativaVO;
import br.com.sicoob.capes.negocio.entidades.TipoFormaConstituicao;
import br.com.sicoob.capes.negocio.entidades.TipoFormaConstituicaoEsferaAdministrativa;

/**
 * A Interface TipoFormaConstituicaoEsferaAdministrativaServico.
 */
public interface TipoFormaConstituicaoEsferaAdministrativaServico extends CAPESCadastroCrudServico<TipoFormaConstituicaoEsferaAdministrativa> {

	/**
	 * O m�todo Gravar alteracoes.
	 *
	 * @param codigoEsfera o valor de codigo esfera
	 * @param listaConstituicaoSelecionados o valor de lista constituicao selecionados
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void gravarAlteracoes(Integer codigoEsfera, List<TipoFormaConstituicao> listaConstituicaoSelecionados) throws BancoobException;
	
	/**
	 * Obter esferas administrativas por id forma constituicao.
	 *
	 * @param idFormaConstituicao o valor de id forma constituicao
	 * @return List
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	List<EsferaAdministrativaVO> obterEsferasAdministrativasPorIdFormaConstituicao(Short idFormaConstituicao) throws BancoobException;

}