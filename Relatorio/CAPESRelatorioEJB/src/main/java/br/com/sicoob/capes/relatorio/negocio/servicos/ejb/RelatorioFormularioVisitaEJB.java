package br.com.sicoob.capes.relatorio.negocio.servicos.ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.relatorio.negocio.relatorios.RelatorioFormularioVisita;
import br.com.sicoob.capes.relatorio.negocio.vo.FormularioVisitaVO;
import br.com.sicoob.capes.relatorio.persistencia.dao.RelFormularioVisitaDAO;
import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO;
import br.com.sicoob.relatorio.api.dto.RetornoProcessamentoRelatorioDTO;
import br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico;

@Stateless
@Remote({ IProcessamentoRelatorioServico.class })
public class RelatorioFormularioVisitaEJB extends CAPESRelatorioServicoEJB implements IProcessamentoRelatorioServico {

	@Inject
	@Default
	private RelFormularioVisitaDAO dao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RetornoProcessamentoRelatorioDTO gerarRelatorio(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
		Number codigoTipoPessoa = (Number) dto.getDados().get("codigoTipoPessoa");
		Number idPessoaCompartilhamento = (Number) dto.getDados().get("idPessoaCompartilhamento");
		
		FormularioVisitaVO vo = obterDadosFormularioVisita(codigoTipoPessoa.shortValue(), idPessoaCompartilhamento.longValue());

		RelatorioFormularioVisita formulario = new RelatorioFormularioVisita(vo);
		return formulario.gerarRelatorio(rDto);
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	private FormularioVisitaVO obterDadosFormularioVisita(Short codigoTipoPessoa, Long idPessoaCompartilhamento) throws BancoobException {
		FormularioVisitaVO vo = null;

		if (isPessoaJuridica(codigoTipoPessoa)) {
			vo = dao.obterDadosFormularioVisitaPJ(idPessoaCompartilhamento);
		} else {
			vo = dao.obterDadosFormularioVisitaPF(idPessoaCompartilhamento);
		}

		if (vo == null) {
			throw new NegocioException("Não foram encontrados resultados para os filtros selecionados.");
		}

		return vo;
	}

	private boolean isPessoaJuridica(Short codigoTipoPessoa) throws BancoobException {
		return TipoPessoaEnum.isPessoaJuridica(codigoTipoPessoa);
	}

}