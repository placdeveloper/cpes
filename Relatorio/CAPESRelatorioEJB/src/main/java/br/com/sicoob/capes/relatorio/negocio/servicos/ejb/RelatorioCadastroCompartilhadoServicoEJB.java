package br.com.sicoob.capes.relatorio.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.capes.relatorio.negocio.dto.RelatorioCadastroCompartilhadoDTO;
import br.com.sicoob.capes.relatorio.negocio.relatorios.RelatorioCadastroCompartilhado;
import br.com.sicoob.capes.relatorio.negocio.vo.RelatorioCadastroCompartilhadoVO;
import br.com.sicoob.capes.relatorio.persistencia.dao.RelatorioCadastroCompartilhadoDAO;
import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO;
import br.com.sicoob.relatorio.api.dto.RetornoProcessamentoRelatorioDTO;
import br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico;

/**
 * EJB contendo servicos relacionados a RelatorioCadastroCompartilhadoServico.
 */
@Stateless
@Remote({ IProcessamentoRelatorioServico.class })
public class RelatorioCadastroCompartilhadoServicoEJB extends CAPESRelatorioServicoEJB implements IProcessamentoRelatorioServico {

	@Inject
	@Default
	private RelatorioCadastroCompartilhadoDAO dao;

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	private List<RelatorioCadastroCompartilhadoVO> obterDadosRelatorio(RelatorioCadastroCompartilhadoDTO proxy) throws BancoobException {
		List<RelatorioCadastroCompartilhadoVO> lista = dao.obterDadosRelatorio(proxy);
		if (lista == null || lista.isEmpty()) {
			throw new NegocioException("Não foram encontrados resultados para os filtros selecionados.");
		}
		return lista;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RetornoProcessamentoRelatorioDTO gerarRelatorio(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
		RelatorioCadastroCompartilhadoDTO proxy = new RelatorioCadastroCompartilhadoDTO();

		Integer numSingular = null;
		Integer numCentral = Integer.valueOf(dto.getDados().get("numCentral").toString());
		if (dto.getDados().get("numSingular") != null) {
			numSingular = Integer.valueOf(dto.getDados().get("numSingular").toString());
		}

		proxy.setNumCentral(numCentral);
		proxy.setNumSingular(numSingular);
		proxy.setIdInstituicao((Integer) dto.getDados().get("idInstituicao"));
		proxy.setCpfCnpj(dto.getDados().get("cpfCnpj").toString());

		List<RelatorioCadastroCompartilhadoVO> listaRetorno = obterDadosRelatorio(proxy);
		RelatorioCadastroCompartilhado relatorio = new RelatorioCadastroCompartilhado(listaRetorno, proxy);
		return relatorio.gerarRelatorio(rDto);
	}

}