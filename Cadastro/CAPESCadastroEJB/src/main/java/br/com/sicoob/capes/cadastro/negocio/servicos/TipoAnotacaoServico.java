/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.TipoAnotacao;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;

/**
 * Define as operações do serviço de manipulação de tipo de anotações.
 * 
 * @author Erico.Junior
 */
public interface TipoAnotacaoServico extends
		CAPESCadastroCrudServico<TipoAnotacao> {

	/**
	 * Consulta os tipos de anotações ativos com o tipo de captura MANUAL.
	 * @return Lista de tipos de anotações ativos com o tipo de captura MANUAL.
	 */
	List<TipoAnotacao> listarTiposAnotacaoManualAtivos(TipoPessoa tipoPessoa);
	
	/**
	 * Carregar instituicoes.
	 *
	 * @param tipoAnotacao o valor de tipo anotacao
	 * @return TipoAnotacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	TipoAnotacao carregarInstituicoes(TipoAnotacao tipoAnotacao) throws BancoobException;
	
	/**
	 * Obtem a saida do Tipo de Anotação informada.
	 * 
	 * @param objeto Tipo de Anotação
	 * @return Texto de saida.
	 * @throws BancoobException
	 */
	String obterSaidaTipoAnotacao(TipoAnotacao objeto) throws BancoobException;
	
	/**
	 * Verifica se o Tipo Anotação possui texto de saida.
	 * 
	 * @param objeto Tipo de Anotação
	 * @return <code>true</true> Se possuir
	 * @throws BancoobException
	 */
	boolean possuiSaidaTipoAnotacao(TipoAnotacao objeto) throws BancoobException;	
}
