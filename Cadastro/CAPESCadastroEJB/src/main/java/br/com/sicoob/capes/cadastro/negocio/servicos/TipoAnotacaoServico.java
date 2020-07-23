/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.TipoAnotacao;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;

/**
 * Define as opera��es do servi�o de manipula��o de tipo de anota��es.
 * 
 * @author Erico.Junior
 */
public interface TipoAnotacaoServico extends
		CAPESCadastroCrudServico<TipoAnotacao> {

	/**
	 * Consulta os tipos de anota��es ativos com o tipo de captura MANUAL.
	 * @return Lista de tipos de anota��es ativos com o tipo de captura MANUAL.
	 */
	List<TipoAnotacao> listarTiposAnotacaoManualAtivos(TipoPessoa tipoPessoa);
	
	/**
	 * Carregar instituicoes.
	 *
	 * @param tipoAnotacao o valor de tipo anotacao
	 * @return TipoAnotacao
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	TipoAnotacao carregarInstituicoes(TipoAnotacao tipoAnotacao) throws BancoobException;
	
	/**
	 * Obtem a saida do Tipo de Anota��o informada.
	 * 
	 * @param objeto Tipo de Anota��o
	 * @return Texto de saida.
	 * @throws BancoobException
	 */
	String obterSaidaTipoAnotacao(TipoAnotacao objeto) throws BancoobException;
	
	/**
	 * Verifica se o Tipo Anota��o possui texto de saida.
	 * 
	 * @param objeto Tipo de Anota��o
	 * @return <code>true</true> Se possuir
	 * @throws BancoobException
	 */
	boolean possuiSaidaTipoAnotacao(TipoAnotacao objeto) throws BancoobException;	
}
