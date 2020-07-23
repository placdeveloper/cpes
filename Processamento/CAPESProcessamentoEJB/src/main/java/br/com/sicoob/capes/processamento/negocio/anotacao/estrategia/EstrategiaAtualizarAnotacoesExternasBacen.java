/*
 * SICOOB
 * 
 * EstrategiaAtualizarAnotacoesExternasBacen.java(br.com.sicoob.capes.processamento.negocio.anotacao.estrategia.EstrategiaAtualizarAnotacoesExternasBacen)
 */
package br.com.sicoob.capes.processamento.negocio.anotacao.estrategia;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.comum.negocio.dto.AnotacaoExternaDTO;
import br.com.sicoob.capes.negocio.entidades.Anotacao;
import br.com.sicoob.capes.negocio.entidades.OrigemInformacao;

/**
 * Estratégia de AtualizarAnotacoesExternasBacen.
 */
public class EstrategiaAtualizarAnotacoesExternasBacen extends EstrategiaAtualizarAnotacoesExternasPadrao {

	/** Volume mínimo de processamento necessário para inclusão da anotação. */
	private static final double VOLUME_PROCESSAMENTO_NECESSARIO = 70.0;

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected boolean podeAtualizar(AnotacaoExternaDTO dto, List<Anotacao> anotacoesVigentes) {

		return isDataBaseConsultaMaisRecenteOuIgual(dto, anotacoesVigentes) && isPercentualProcessadoValido(dto);
	}

	/**
	 * O parâmetro para definir a consulta mais recente é o mês e ano base consultado e não a data em que a consulta foi
	 * realizada. Dessa forma, caso exista anotação referente ao mês 10/2010 e o usuário efetue nova consulta ao Bacen,
	 * retroativa ao mês 09/2010, a anotação não deverá ser substituída. De outra forma, caso a nova consulta tenha como
	 * referência o mês 11/2010, a consulta anterior (10/2010) tornar-se-á histórica e novas anotações serão criadas com
	 * base no resultado da consulta mais recente (11/2010). Quando houver anotação para determinada data-base e nova
	 * consulta for realizada na mesma data-base, a consulta mais recente substituirá a anterior, tornando-a histórica.
	 * 
	 * @param resumo
	 *            O resumo da consulta ao BACEN.
	 * @param anotacoesVigentes
	 *            As anotações vigentes da pessoa com origem BACEN.
	 * @return boolean indicando se a data base da consulta informada é maior que a maior data das anotações vigentes.
	 */
	private boolean isDataBaseConsultaMaisRecenteOuIgual(AnotacaoExternaDTO dto, List<Anotacao> anotacoesVigentes) {

		boolean dataBaseMaisRecente = false;
		Date dataBaseConsulta = DateUtils.truncate(dto.getDataBaseBacen(), Calendar.MONTH);
		if (dataBaseConsulta != null) {
			Date maiorDataVigente = obterMaiorDataAnotacaoVigente(anotacoesVigentes);
			dataBaseMaisRecente = (maiorDataVigente == null)
					|| DateUtils.truncate(maiorDataVigente, Calendar.MONTH).before(dataBaseConsulta);
		}
		return dataBaseMaisRecente;
	}

	/**
	 * Recupera a maior data das anotações vigentes para o BACEN.
	 * 
	 * @param anotacoesVigentes
	 *            As anotações vigentes.
	 * @return a maior data das anotações vigentes para o BACEN.
	 */
	private Date obterMaiorDataAnotacaoVigente(List<Anotacao> anotacoesVigentes) {

		Date maiorData = null;
		if (anotacoesVigentes != null && !anotacoesVigentes.isEmpty()) {
			Date dataOcorrencia = null;
			for (Anotacao anotacao : anotacoesVigentes) {
				dataOcorrencia = anotacao.getDataHoraOcorrencia();
				if (maiorData == null || maiorData.before(dataOcorrencia)) {
					maiorData = dataOcorrencia;
				}
			}
		}
		return maiorData;
	}

	/**
	 * Se caso para o ano e mês base da consulta mais recente, o volume de processamento for inferior a 70%, então,
	 * deve-se manter as anotações referentes ao ano e mês base anterior, isto é, nessa situação, a consulta mais
	 * recente não substituirá a mais antiga.
	 * 
	 * @param resumo
	 *            O resumo da consulta ao BACEN.
	 * @return boolean indicando se o percentual processado é válido para gerar anotação.
	 */
	private boolean isPercentualProcessadoValido(AnotacaoExternaDTO dto) {

		return dto.getPercentualProcessadoBacen() >= VOLUME_PROCESSAMENTO_NECESSARIO;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected DateTimeDB obterDataOcorrenciaNadaConsta(OrigemInformacao origem, AnotacaoExternaDTO dto) {

		DateTimeDB data = new DateTimeDB();
		if (dto.getDataBaseBacen() != null) {
			data.setTime(dto.getDataBaseBacen().getTime());
		}
		return data;
	}

}
