package br.com.sicoob.capes.relatorio.negocio.relatorios;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.relatorio.negocio.dto.RelCompartilhamentoDTO;
import br.com.sicoob.capes.relatorio.negocio.enums.OrientacaoPaginaEnum;
import br.com.sicoob.capes.relatorio.negocio.vo.RelCompartilhamentoVO;

/**
 * A Classe RelCompartilhamento.
 */
public class RelCompartilhamento extends CAPESRelatorio {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 6902569932526434324L;
	
	/** A constante TITULO_RELATORIO. */
	private static final String TITULO_RELATORIO = "Relatório de Compartilhamento de Cadastros";
	
	/** A constante NOME_RELATORIO. */
	private static final String NOME_RELATORIO = "RelCompartilhamento";
	
	/** A constante NOME_RELATORIO_ARQUIVO. */
	private static final String NOME_RELATORIO_ARQUIVO = "RelCompartilhamento.jasper";
	
	/**
	 * Instancia um novo RelCompartilhamento.
	 *
	 * @param dados o valor de dados
	 * @param filtro o valor de filtro
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RelCompartilhamento(List<RelCompartilhamentoVO> dados, RelCompartilhamentoDTO filtro)
			throws BancoobException {
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