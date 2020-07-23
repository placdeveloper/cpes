package br.com.sicoob.capes.relatorio.negocio.relatorios;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.relatorio.negocio.dto.RelVencimentoCadastroDTO;
import br.com.sicoob.capes.relatorio.negocio.enums.OrientacaoPaginaEnum;
import br.com.sicoob.capes.relatorio.negocio.vo.RelVencimentoCadastroVO;

/**
 * A Classe RelVencimentoCadastro.
 */
public class RelVencimentoCadastro extends CAPESRelatorio {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 4052700586875640747L;
	
	/** A constante NOME_RELATORIO. */
	private static final String NOME_RELATORIO = "RelVencimentoCadastro";
	
	/** A constante NOME_RELATORIO_ARQUIVO. */
	private static final String NOME_RELATORIO_ARQUIVO = "RelVencimentoCadastro.jasper";
	
	/** A constante TITULO_RELATORIO. */
	private static final String TITULO_RELATORIO = "Relatório de Vencimento de Cadastros";

	/**
	 * Instancia um novo RelVencimentoCadastro.
	 *
	 * @param filtro o valor de filtro
	 * @param dados o valor de dados
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RelVencimentoCadastro(RelVencimentoCadastroDTO filtro,
			List<RelVencimentoCadastroVO> dados) throws BancoobException {
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
		return "CLI003";
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
