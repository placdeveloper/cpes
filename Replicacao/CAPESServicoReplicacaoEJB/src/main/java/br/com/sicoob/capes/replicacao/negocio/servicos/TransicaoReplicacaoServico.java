package br.com.sicoob.capes.replicacao.negocio.servicos;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.BancoServidor;
import br.com.sicoob.capes.negocio.entidades.legado.TransicaoReplicacao;

/**
 * 
 * Interface que define os m�todos de servi�o da entidade TransicaoReplicacao.  
 * 
 * @author Juan.Damasceno
 *
 */
public interface TransicaoReplicacaoServico extends
		CAPESServicoReplicacaoCrudServico<TransicaoReplicacao> {

	/**
	 * Lista as TransicaoReplicacao que ainda n�o foram replicadas.
	 * @return as TransicaoReplicacao que ainda n�o foram replicadas.
	 */
	List<TransicaoReplicacao> listarNaoReplicados();

	/**
	 * Remove as transi��es que j� foram replicadas antes da data passada como par�metro.
	 * @param data ser� utilizada para filtrar os registros.
	 */
	void removerReplicados(Date data) throws BancoobException;
	
	/**
	 * Centraliza as transicoes que foram marcadas.
	 * @param bancoServidor instancia de BancoServidor que contem as informa��es sobre o banco de dados.
	 * @throws BancoobException 
	 */
	void centralizaTransicoesReplicacao(BancoServidor bancoServidor) throws BancoobException;

	/**
	 * Remove as transicoes que foram replicadas.
	 * @param bancoServidor instancia de BancoServidor que contem as informa��es sobre o banco de dados.
	 */
	void removeTransicoesReplicacaoCentralizadas(BancoServidor bancoServidor);

	/**
	 * Marca as transicoes que ser�o replicadas.
	 * @param bancoServidor instancia de BancoServidor que contem as informa��es sobre o banco de dados.
	 * @return a quantidade de registros afetados.
	 */
	int marcaTransicoesReplicacaoParaCentralizacao(BancoServidor bancoServidor);

	/**
	 * Centraliza transicoes replicacao sem sistema cooperativo.
	 *
	 * @param bancoServidor o valor de banco servidor
	 * @return int
	 */
	int centralizaTransicoesReplicacaoSemSistemaCooperativo(
			BancoServidor bancoServidor);
}