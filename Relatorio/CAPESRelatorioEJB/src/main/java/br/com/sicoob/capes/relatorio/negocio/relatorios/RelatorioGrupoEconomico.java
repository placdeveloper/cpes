package br.com.sicoob.capes.relatorio.negocio.relatorios;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.relatorio.negocio.dto.RelatorioGrupoEconomicoDTO;
import br.com.sicoob.capes.relatorio.negocio.enums.OrientacaoPaginaEnum;
import br.com.sicoob.capes.relatorio.negocio.vo.RelatorioGrupoEconomicoVO;

/**
 * A Classe RelatorioGrupoEconomico.
 */
public class RelatorioGrupoEconomico extends CAPESRelatorio {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 2835524561592181550L;
	
	/** A constante NOME_RELATORIO. */
	private static final String NOME_RELATORIO = "RelatorioGrupoEconomico";
	
	/** A constante NOME_RELATORIO_ARQUIVO. */
	private static final String NOME_RELATORIO_ARQUIVO = "RelatorioGrupoEconomico.jasper";
	
	/** A constante TITULO_RELATORIO. */
	private static final String TITULO_RELATORIO = "Relatório de Grupo Econômico";
	
	/**
	 * Instancia um novo RelatorioGrupoEconomico.
	 *
	 * @param filtro o valor de filtro
	 * @param dados o valor de dados
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RelatorioGrupoEconomico(RelatorioGrupoEconomicoDTO filtro,
			List<RelatorioGrupoEconomicoVO> dados) throws BancoobException {
		super(OrientacaoPaginaEnum.PAISAGEM, dados);
		getParametros().put("FILTRO", filtro);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getNomeTemplateDados() {
		return NOME_RELATORIO_ARQUIVO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getIdRelatorio() {
		return "CLI001";
	}

	/**
	 * Recupera o valor de nomeRelatorio.
	 *
	 * @return o valor de nomeRelatorio
	 */
	public String getNomeRelatorio() {
		return NOME_RELATORIO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getTituloRelatorio() {
		return TITULO_RELATORIO;
	}

}
