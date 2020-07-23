/**
 * 
 */
package br.com.sicoob.capes.relatorio.negocio.servicos.ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.relatorio.negocio.relatorios.RelatorioCartaoAssinatura;
import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO;
import br.com.sicoob.relatorio.api.dto.RetornoProcessamentoRelatorioDTO;
import br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico;

/**
 * Classe de relatório da pessoa compartilhamento.
 * 
 * @author Erico.Junior
 */
@Stateless
@Remote({ IProcessamentoRelatorioServico.class })
public class RelatorioPessoaCompartilhamentoServicoEJB extends CAPESRelatorioServicoEJB implements IProcessamentoRelatorioServico {

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RetornoProcessamentoRelatorioDTO gerarRelatorio(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
		Boolean emBranco = (Boolean) dto.getDados().get("cartaoEmBranco");
		String nomeCompleto = (String) dto.getDados().get("nomeCompleto");
		String cpfCnpj = (String) dto.getDados().get("cpfCnpj");
		Number codigoTipoPessoa = (Number) dto.getDados().get("codigoTipoPessoa");

		RelatorioCartaoAssinatura relatorioCartaoAssinatura = new RelatorioCartaoAssinatura(codigoTipoPessoa.shortValue(), nomeCompleto, cpfCnpj, emBranco);
		return relatorioCartaoAssinatura.gerarRelatorio(rDto);
	}

}