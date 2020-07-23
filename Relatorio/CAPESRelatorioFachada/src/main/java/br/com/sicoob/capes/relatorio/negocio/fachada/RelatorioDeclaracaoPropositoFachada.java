package br.com.sicoob.capes.relatorio.negocio.fachada;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.sicoob.capes.relatorio.negocio.relatorios.RelatorioDeclaracaoProposito;

/**
 * A Classe RelatorioDeclaracaoPropositoFachada.
 */
@RemoteService
public class RelatorioDeclaracaoPropositoFachada extends CAPESRelatorioComumFachada {

	/** A constante RELATORIO_DECLARACAO_PROPOSITO. */
	private static final String RELATORIO_DECLARACAO_PROPOSITO = "relatorioDeclaracaoProposito";

	/**
	 * Instancia um novo RelatorioDeclaracaoPropositoFachada.
	 */
	public RelatorioDeclaracaoPropositoFachada() {
		super(RELATORIO_DECLARACAO_PROPOSITO);
	}

	/**
	 * Gerar relatorio.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO gerarRelatorio(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();

		ContextoHttp.getInstance().getCaminhoContextoWeb();
		retorno.getDados().put("nomeRelatorio", RelatorioDeclaracaoProposito.NOME_RELATORIO_SESSAO);

		return retorno;
	}
}