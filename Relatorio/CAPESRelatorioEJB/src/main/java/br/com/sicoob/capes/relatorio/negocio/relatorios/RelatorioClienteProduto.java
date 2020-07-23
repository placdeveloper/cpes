package br.com.sicoob.capes.relatorio.negocio.relatorios;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.relatorio.negocio.dto.RelClienteProdutoProxy;
import br.com.sicoob.capes.relatorio.negocio.dto.RelClienteProdutoVO;
import br.com.sicoob.capes.relatorio.negocio.enums.OrientacaoPaginaEnum;

public class RelatorioClienteProduto extends CAPESRelatorio {
	

	private static final long serialVersionUID = -5623111245068693858L;
	
	private static final String NOME_RELATORIO = "RelClienteProduto";
	private static final String TITULO_RELATORIO = "Relatório de Cliente Produto";
	private static final String CODIGO_RELATORIO = "CLI019";
	private static final String NOME_RELATORIO_ARQUIVO = "RelClienteProduto.jasper";
	
	public RelatorioClienteProduto(RelClienteProdutoVO filtro, List<RelClienteProdutoProxy> dados) throws BancoobException {
		super(OrientacaoPaginaEnum.PAISAGEM, dados);
		getParametros().put("FILTRO", filtro);
	}

	@Override
	protected String getNomeTemplateDados() {
		return NOME_RELATORIO_ARQUIVO;
	}

	@Override
	protected String getIdRelatorio() {
		return CODIGO_RELATORIO;
	}

	public String getNomeRelatorio() {
		return NOME_RELATORIO;
	}

	@Override
	protected String getTituloRelatorio() {
		return TITULO_RELATORIO;
	}

}
