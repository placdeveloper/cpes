package br.com.sicoob.capes.relatorio.negocio.relatorios;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.relatorio.negocio.dto.RelatorioCadastroCompartilhadoDTO;
import br.com.sicoob.capes.relatorio.negocio.enums.OrientacaoPaginaEnum;
import br.com.sicoob.capes.relatorio.negocio.vo.RelatorioCadastroCompartilhadoVO;

/**
 * A Classe RelCompartilhamento.
 */
public class RelatorioCadastroCompartilhado extends CAPESRelatorio {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 6902569932526434324L;
	
	/** A constante TITULO_RELATORIO. */
	private static final String TITULO_RELATORIO = "Relatorio de Cadastros Compartilhados";
	
	/** A constante NOME_RELATORIO. */
	private static final String NOME_RELATORIO = "RelatorioCadastroCompartilhado";
	
	/** A constante NOME_RELATORIO_ARQUIVO. */
	private static final String NOME_RELATORIO_ARQUIVO = "RelatorioCadastroCompartilhado.jasper";
	
	/**
	 * Instancia um novo RelatorioCadastroCompartilhado.
	 *
	 * @param dados o valor de dados
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RelatorioCadastroCompartilhado(List<RelatorioCadastroCompartilhadoVO> dados, RelatorioCadastroCompartilhadoDTO filtro)
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