/**
 * 
 */
package br.com.sicoob.capes.relatorio.negocio.servicos.ejb;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.PessoaCompartilhamentoServicoLocal;
import br.com.sicoob.capes.negocio.entidades.Anotacao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;
import br.com.sicoob.capes.relatorio.negocio.dto.RelatorioProvaVidaDTO;
import br.com.sicoob.capes.relatorio.negocio.relatorios.RelatorioProvaVida;
import br.com.sicoob.capes.relatorio.persistencia.dao.RelatorioProvaVidaDAO;
import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO;
import br.com.sicoob.relatorio.api.dto.RetornoProcessamentoRelatorioDTO;
import br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico;

/**
 * Classe RelatorioProvaVidaServicoEJB
 * 
 * @author Daniel.Domingues
 */
@Stateless
@Remote(IProcessamentoRelatorioServico.class)
public class RelatorioProvaVidaServicoEJB extends CAPESRelatorioServicoEJB implements IProcessamentoRelatorioServico {

	@Inject
	@Default
	private RelatorioProvaVidaDAO dao;
	
	@EJB
	private PessoaCompartilhamentoServicoLocal pessoaCompartilhamentoServico;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RetornoProcessamentoRelatorioDTO gerarRelatorio(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
		Number idAnotacao = (Number) dto.getDados().get("idAnotacao");
		Anotacao anotacao = CAPESCadastroFabricaDelegates.getInstance().criarAnotacaoDelegate().obter(idAnotacao.longValue());

		boolean imprimirRelatorio = CAPESCadastroFabricaDelegates.getInstance().criarTipoAnotacaoDelegate().possuiSaidaTipoAnotacao(anotacao.getTipoAnotacao());

		if (imprimirRelatorio) {
			RelatorioProvaVidaDTO relatorioDTO = dao.obterDadosRelatorio(anotacao.getInstituicao());
			PessoaFisica pessoaFisica = (PessoaFisica) pessoaCompartilhamentoServico.obter(anotacao.getPessoaCompartilhamento().getId());

			relatorioDTO.setDataRelatorio(DataUtil.obterDataAtual());

			// Dados Pessoa Física.
			relatorioDTO.setCpf(pessoaFisica.getPessoa().getCpfCnpj());
			relatorioDTO.setNumeroDocumento(pessoaFisica.getNumeroDocumento());
			relatorioDTO.setNomeBeneficiario(pessoaFisica.getNomeCompleto());
			relatorioDTO.setOrgaoExpeditorDocumento(pessoaFisica.getOrgaoExpedidorDocumento());
			relatorioDTO.setUfOrgaoExpedidorDocumento(pessoaFisica.getUfOrgaoExpedidorDocumento());

			if (pessoaFisica.getDataEmissaoDocumento() != null) {
				relatorioDTO.setDataEmissaoDocumento(DataUtil.converterDateToString(pessoaFisica.getDataEmissaoDocumento(), "dd/MM/yyyy"));
			}

			formatarLocalData(relatorioDTO);
			return new RelatorioProvaVida(relatorioDTO).gerarRelatorio(rDto);
		}

		return null;
	}
	
	/**
	 * Formata a informação de Local e Data do Relatorio.
	 * 
	 * @param relatorioDTO RelatorioProvaVidaDTO
	 */
	private void formatarLocalData(RelatorioProvaVidaDTO relatorioDTO) {
		StringBuilder localDataFormatado = new StringBuilder();
		
		if (StringUtils.isNotEmpty(relatorioDTO.getCidade())) {
			localDataFormatado.append(relatorioDTO.getCidade()).append(", ");
		}
		
		if (relatorioDTO.getDataRelatorio() != null) {
			Locale local = new Locale("pt","BR");
			DateFormat dateFormat = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy",local);
			localDataFormatado.append(dateFormat.format(relatorioDTO.getDataRelatorio()).toLowerCase());
		}
		
		localDataFormatado.append(".");
		
		relatorioDTO.setLocalData(localDataFormatado.toString());
	}

}