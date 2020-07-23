package br.com.sicoob.capes.relatorio.negocio.servicos.ejb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.comum.negocio.vo.CamposFichaCadastralVO;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaJuridica;
import br.com.sicoob.capes.relatorio.negocio.relatorios.RelatorioFichaCadastralEmBranco;
import br.com.sicoob.capes.relatorio.negocio.vo.FichaCadastralVO;
import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO;
import br.com.sicoob.relatorio.api.dto.RetornoProcessamentoRelatorioDTO;
import br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico;

@Stateless
@Remote({ IProcessamentoRelatorioServico.class })
public class RelatorioFichaCadastralNovaEmBrancoServicoEJB extends CAPESRelatorioServicoEJB implements IProcessamentoRelatorioServico {

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RetornoProcessamentoRelatorioDTO gerarRelatorio(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
		Number codigoTipoPessoa = (Number) dto.getDados().get("codigoTipoPessoa");

		Boolean atual = (Boolean) dto.getDados().get("atual");
		Boolean historicoEm = (Boolean) dto.getDados().get("historicoEm");
		Boolean posicao = (Boolean) dto.getDados().get("posicao");
		Boolean emBranco = (Boolean) dto.getDados().get("emBranco");

		Date data = (Date) dto.getDados().get("data");
		Date dataFim = (Date) dto.getDados().get("dataFim");
		
		CamposFichaCadastralVO vo = new CamposFichaCadastralVO();
		vo.setTipoPessoa(codigoTipoPessoa != null ? codigoTipoPessoa.intValue() : null);
		vo.setAtual(atual);
		vo.setEmBranco(emBranco);
		vo.setHistoricoEm(historicoEm);
		vo.setPosicao(posicao);
		vo.setData(data);
		vo.setDataFim(dataFim);

		FichaCadastralVO fichaCadastralVO = new FichaCadastralVO();
		PessoaCompartilhamento pessoa = null;

		pessoa = obterInstanciaPessoaCompartilhamento(vo);

		fichaCadastralVO.setPessoa(pessoa);

		ArrayList<PessoaCompartilhamento> pessoasCompartilhamento = new ArrayList<PessoaCompartilhamento>(1);
		pessoasCompartilhamento.add(obterInstanciaPessoaCompartilhamento(vo));

		fichaCadastralVO.setPessoasCompartilhamento(pessoasCompartilhamento);
		fichaCadastralVO.setIdInstituicao(obterInstituicaoUsuarioRelatorio().getIdInstituicao());
		fichaCadastralVO.setIdUnidadeInst(obterInstituicaoUsuarioRelatorio().getIdUnidadeInst());
		fichaCadastralVO.setLabelFiltroData(obterLabelFiltro(vo));

		RelatorioFichaCadastralEmBranco fichaEmBranco = new RelatorioFichaCadastralEmBranco(fichaCadastralVO);

		return fichaEmBranco.gerarRelatorio(rDto);
	}

	/**
	 * Obter instancia pessoa compartilhamento.
	 *
	 * @param vo
	 *            o valor de vo
	 * @return PessoaCompartilhamento
	 */
	private PessoaCompartilhamento obterInstanciaPessoaCompartilhamento(CamposFichaCadastralVO vo) {
		PessoaCompartilhamento pessoa;
		if (isPessoaFisica(vo)) {
			pessoa = new PessoaFisica();
		} else {
			pessoa = new PessoaJuridica();

		}
		return pessoa;
	}

	/**
	 * Verifica se eh pessoa fisica.
	 *
	 * @param vo
	 *            o valor de vo
	 * @return {@code true}, se for pessoa fisica
	 */
	private boolean isPessoaFisica(CamposFichaCadastralVO vo) {
		return vo.getTipoPessoa().shortValue() == TipoPessoaEnum.PESSOA_FISICA.getCodigo();
	}

	private String obterLabelFiltro(CamposFichaCadastralVO vo) {
		String dataFormatada = "EMISSÃO: ".concat(formatarData(DataUtil.obterDataAtual()));

		return dataFormatada;
	}

	private String formatarData(Date data) {
		return new SimpleDateFormat("dd/MM/yyyy").format(data);
	}

}
