package br.com.sicoob.capes.relatorio.persistencia.dao.impl;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;
import br.com.sicoob.capes.relatorio.persistencia.dao.RelatorioRelacionamentoDAO;

public class RelatorioRelacionamentoDAOImpl extends CAPESRelatorioDao implements RelatorioRelacionamentoDAO {

	/** A constante NOME_COMANDO_PESQUISAR_VIGENTE. */
	private static final String NOME_COMANDO_PESQUISAR_VIGENTE = "PESQUISAR_RELACIONAMENTOS_VIGENTES_FILTRO_SEM_CONJUGE";

	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<RelacionamentoPessoa> pesquisarRelacionamentosVigentesPorFiltro(RelacionamentoPessoa relacionamentoPessoa) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando(NOME_COMANDO_PESQUISAR_VIGENTE);
			comando.adicionarVariavel("idPessoa", relacionamentoPessoa.getPessoa() != null ? relacionamentoPessoa.getPessoa().getIdPessoa() : null);
			comando.adicionarVariavel("idInstituicao", relacionamentoPessoa.getIdInstituicao() != null ? relacionamentoPessoa.getIdInstituicao() : null);
			comando.adicionarVariavel("idTipoRelacionamento", relacionamentoPessoa.getTipoRelacionamento() != null ? relacionamentoPessoa.getTipoRelacionamento().getId() : null);
			comando.adicionarVariavel("idPessoaRelacionada", relacionamentoPessoa.getPessoaRelacionada() != null ? relacionamentoPessoa.getPessoaRelacionada().getId() : null);
			comando.configurar();
			return criarQuery(comando).getResultList();
		} finally {
			fecharComando(comando);
		}
	}
}
