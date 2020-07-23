package br.com.sicoob.capes.api.inclusao.negocio.conversores;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.inclusao.negocio.dto.AnotacaoDTO;
import br.com.sicoob.capes.negocio.entidades.Anotacao;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.ObservacaoAnotacao;
import br.com.sicoob.capes.negocio.entidades.OrigemInformacao;
import br.com.sicoob.capes.negocio.entidades.TipoAnotacao;
import br.com.sicoob.capes.negocio.entidades.TipoBaixa;
import br.com.sicoob.capes.negocio.entidades.TipoConsultaOrigem;

/**
 * A Classe ConversorAnotacao.
 * 
 * @author bruno.carneiro
 */
public class ConversorAnotacao extends ConversorAbstrato<Anotacao, AnotacaoDTO> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void definirInformacoesAdicionais(Anotacao entidade, AnotacaoDTO dto) throws BancoobException {
		super.definirInformacoesAdicionais(entidade, dto);

		TipoAnotacao tipoAnotacao = obterFabricaCadastro().criarTipoAnotacaoDelegate().obter(dto.getCodigoTipoAnotacao());
		entidade.setTipoAnotacao(tipoAnotacao);

		Instituicao instituicao = new Instituicao();
		instituicao.setIdInstituicao(dto.getIdInstituicao());
		instituicao.setIdUnidadeInst(dto.getIdUnidadeInst());
		entidade.setInstituicao(instituicao);

		if (dto.getCodigoObservacaoAnotacao() != null) {
			ObservacaoAnotacao observacaoAnotacao = new ObservacaoAnotacao();
			observacaoAnotacao.setCodigo(dto.getCodigoObservacaoAnotacao());
			entidade.setObservacaoAnotacao(observacaoAnotacao);
		}

		if (dto.getCodigoOrigemInformacao() != null) {
			OrigemInformacao origemInformacao = new OrigemInformacao();
			origemInformacao.setIdOrigemInfo(dto.getCodigoOrigemInformacao());
			entidade.setOrigemInformacao(origemInformacao);
		}

		if (dto.getCodigoTipoBaixa() != null) {
			TipoBaixa tipoBaixa = new TipoBaixa();
			tipoBaixa.setIdTipoBaixa(dto.getCodigoTipoBaixa());
			entidade.setTipoBaixa(tipoBaixa);
		}

		if (dto.getCodigoTipoConsultaOrigem() != null) {
			TipoConsultaOrigem tipoConsultaOrigem = new TipoConsultaOrigem();
			tipoConsultaOrigem.setIdTipoConsultaOrigem(dto.getCodigoTipoConsultaOrigem());
			entidade.setTipoConsultaOrigem(tipoConsultaOrigem);
		}

		entidade.setDataHoraAlteracao(criarDateTimeDB(dto.getDataHoraAlteracao()));
		entidade.setDataHoraBaixa(criarDateTimeDB(dto.getDataHoraBaixa()));
		entidade.setDataHoraOcorrencia(criarDateTimeDB(dto.getDataHoraOcorrencia()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void definirInformacoesAdicionais(AnotacaoDTO dto, Anotacao entidade) throws BancoobException {
		super.definirInformacoesAdicionais(dto, entidade);

		dto.setCodigoTipoAnotacao(entidade.getTipoAnotacao().getCodTipoAnotacao());
		dto.setIdInstituicao(entidade.getInstituicao().getIdInstituicao());
		dto.setIdUnidadeInst(entidade.getInstituicao().getIdUnidadeInst());

		if (entidade.getObservacaoAnotacao() != null) {
			dto.setCodigoObservacaoAnotacao(entidade.getObservacaoAnotacao().getCodigo());
		}

		if (entidade.getOrigemInformacao() != null) {
			dto.setCodigoOrigemInformacao(entidade.getOrigemInformacao().getIdOrigemInfo());
		}

		if (entidade.getTipoBaixa() != null) {
			dto.setCodigoTipoBaixa(entidade.getTipoBaixa().getIdTipoBaixa());
		}

		if (entidade.getTipoConsultaOrigem() != null) {
			dto.setCodigoTipoConsultaOrigem(entidade.getTipoConsultaOrigem().getIdTipoConsultaOrigem());
		}
	}

}