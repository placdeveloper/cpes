package br.com.sicoob.capes.relatorio.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;

public interface RelatorioRelacionamentoDAO {
	
	/**
	 * Pesquisa os relacionamentos vigentes por pessoa, instituição e tipo de relacionamento sem conjuge.
	 * @param relacionamentoPessoa com os filtros
	 * @return lista de relacionamentos vigentes
	 * @throws BancoobException
	 */
	List<RelacionamentoPessoa> pesquisarRelacionamentosVigentesPorFiltro(RelacionamentoPessoa relacionamentoPessoa) throws BancoobException;
	
}
