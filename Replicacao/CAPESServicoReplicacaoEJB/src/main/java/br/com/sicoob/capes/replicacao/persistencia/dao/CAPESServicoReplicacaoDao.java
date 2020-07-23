package br.com.sicoob.capes.replicacao.persistencia.dao;

import br.com.bancoob.persistencia.dao.BancoobDao;

/**
 * Classe padrao com a infraestrutura de acesso a base de dados do sistema ServicoReplicacaoCadastroUnicoClientes
 * 
 * @author Stefanini IT Solutions
 */
public abstract class CAPESServicoReplicacaoDao extends BancoobDao {

	/**
	 * @param nomeComandoListar
	 * @param nomeComandoConsultar
	 */
	public CAPESServicoReplicacaoDao(String nomeDatasource, String arquivoQueries, String nomeColecaoComandos) {

		super(nomeDatasource, arquivoQueries, nomeColecaoComandos);
	}
}