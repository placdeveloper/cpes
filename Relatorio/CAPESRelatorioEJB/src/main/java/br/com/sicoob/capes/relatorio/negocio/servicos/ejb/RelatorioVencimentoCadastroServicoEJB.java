package br.com.sicoob.capes.relatorio.negocio.servicos.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.capes.relatorio.negocio.dto.RelVencimentoCadastroDTO;
import br.com.sicoob.capes.relatorio.negocio.relatorios.RelVencimentoCadastro;
import br.com.sicoob.capes.relatorio.negocio.vo.RelVencimentoCadastroVO;
import br.com.sicoob.capes.relatorio.persistencia.dao.RelVencimentoCadastroDAO;
import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO;
import br.com.sicoob.relatorio.api.dto.RetornoProcessamentoRelatorioDTO;
import br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico;

/**
 * EJB contendo servicos relacionados a RelatorioVencimentoCadastro.
 */
@Stateless
@Remote( { IProcessamentoRelatorioServico.class })
public class RelatorioVencimentoCadastroServicoEJB extends CAPESRelatorioServicoEJB implements IProcessamentoRelatorioServico {
	
	@Inject
	@Default
	private RelVencimentoCadastroDAO dao;
	
	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	private List<RelVencimentoCadastroVO> obterDadosRelatorio(RelVencimentoCadastroDTO proxy) throws BancoobException {
		List<RelVencimentoCadastroVO> lista = dao.obterDadosRelatorio(proxy);
		if (lista.isEmpty()) {
			throw new NegocioException("Não foram encontrados resultados para os filtros selecionados.");
		}
		return lista;		
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RetornoProcessamentoRelatorioDTO gerarRelatorio(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
		RelVencimentoCadastroDTO proxy = new RelVencimentoCadastroDTO();

		Integer singular = null;
		Integer central = Integer.valueOf(dto.getDados().get("numCentral").toString());
		if (dto.getDados().get("numSingular") != null) {
			singular = Integer.valueOf(dto.getDados().get("numSingular").toString());
		}
		Integer ordenacao = (Integer) dto.getDados().get("ordenacao");

		proxy.setCentral(central);
		proxy.setSingular(singular);
		proxy.setIdInstituicao((Integer) dto.getDados().get("idInstituicao"));
		proxy.setUnidade((Integer) dto.getDados().get("unidade"));
		proxy.setGerente((Integer) dto.getDados().get("gerente"));
		proxy.setNomeGerente((String) dto.getDados().get("nomeGerente"));
		proxy.setNucleo((Integer) dto.getDados().get("nucleo"));
		proxy.setNomeNucleo((String) dto.getDados().get("nomeNucleo"));
		proxy.setDataInicio((Date) dto.getDados().get("dataInicio"));
		proxy.setDataFim((Date) dto.getDados().get("dataFim"));
		proxy.setSomenteResponsavel((Boolean) dto.getDados().get("somenteResponsavel"));
		proxy.setOrdenacao(ordenacao);

		List<RelVencimentoCadastroVO> listaRetorno = obterDadosRelatorio(proxy);
		RelVencimentoCadastro relatorio = new RelVencimentoCadastro(proxy, listaRetorno);
		return relatorio.gerarRelatorio(rDto);
	}

}