package br.com.sicoob.capes.replicacao.persistencia.dao;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.BancoServidor;
import br.com.sicoob.capes.negocio.entidades.legado.TransicaoReplicacao;

/**
 * 
 * Interface que define os metodos de persistencia da entidade TransicaoReplicacao.
 * 
 * @author Juan.Damasceno
 *
 */
public interface TransicaoReplicacaoDao extends CAPESServicoReplicacaoCrudDaoIF<TransicaoReplicacao>{

	/**
	 * Lista as TransicaoReplicacao que ainda não foram replicadas.
	 * @return as TransicaoReplicacao que ainda não foram replicadas.
	 */
	List<TransicaoReplicacao> listarNaoReplicados();

	/**
	 * Remove as transições que já foram replicadas antes da data passada como parâmetro.
	 * @param data será utilizada para filtrar os registros.
	 */
	void removerReplicados(Date data) throws BancoobException;

	/**
	 * Centraliza as transicoes marcadas.
	 * @param bancoServidor instancia de BancoServidor que contem as informações sobre o banco de dados.
	 * @return a quantidade de registros afetados.
	 */
	int centralizaTransicoesReplicacao(BancoServidor bancoServidor);

	/**
	 * Remove as transicoes que foram replicadas.
	 * @param bancoServidor instancia de BancoServidor que contem as informações sobre o banco de dados.
	 * @return a quantidade de registros afetados.
	 */
	int removeTransicoesReplicacaoCentralizadas(BancoServidor bancoServidor);

	/**
	 * Marca as transicoes que serão replicadas.
	 * @param bancoServidor instancia de BancoServidor que contem as informações sobre o banco de dados.
	 * @return a quantidade de registros afetados.
	 */
	int marcaTransicoesReplicacaoParaCentralizacao(BancoServidor bancoServidor);

	/**
	 * Centraliza transicoes replicacao sem sistema cooperativo.
	 *
	 * @param bancoServidor o valor de banco servidor
	 * @return int
	 */
	int centralizaTransicoesReplicacaoSemSistemaCooperativo(BancoServidor bancoServidor);
}
