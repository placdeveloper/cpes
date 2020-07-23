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
 * Estrat�gia de AtualizarAnotacoesExternasBacen.
 */
public class EstrategiaAtualizarAnotacoesExternasBacen extends EstrategiaAtualizarAnotacoesExternasPadrao {

	/** Volume m�nimo de processamento necess�rio para inclus�o da anota��o. */
	private static final double VOLUME_PROCESSAMENTO_NECESSARIO = 70.0;

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected boolean podeAtualizar(AnotacaoExternaDTO dto, List<Anotacao> anotacoesVigentes) {

		return isDataBaseConsultaMaisRecenteOuIgual(dto, anotacoesVigentes) && isPercentualProcessadoValido(dto);
	}

	/**
	 * O par�metro para definir a consulta mais recente � o m�s e ano base consultado e n�o a data em que a consulta foi
	 * realizada. Dessa forma, caso exista anota��o referente ao m�s 10/2010 e o usu�rio efetue nova consulta ao Bacen,
	 * retroativa ao m�s 09/2010, a anota��o n�o dever� ser substitu�da. De outra forma, caso a nova consulta tenha como
	 * refer�ncia o m�s 11/2010, a consulta anterior (10/2010) tornar-se-� hist�rica e novas anota��es ser�o criadas com
	 * base no resultado da consulta mais recente (11/2010). Quando houver anota��o para determinada data-base e nova
	 * consulta for realizada na mesma data-base, a consulta mais recente substituir� a anterior, tornando-a hist�rica.
	 * 
	 * @param resumo
	 *            O resumo da consulta ao BACEN.
	 * @param anotacoesVigentes
	 *            As anota��es vigentes da pessoa com origem BACEN.
	 * @return boolean indicando se a data base da consulta informada � maior que a maior data das anota��es vigentes.
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
	 * Recupera a maior data das anota��es vigentes para o BACEN.
	 * 
	 * @param anotacoesVigentes
	 *            As anota��es vigentes.
	 * @return a maior data das anota��es vigentes para o BACEN.
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
	 * Se caso para o ano e m�s base da consulta mais recente, o volume de processamento for inferior a 70%, ent�o,
	 * deve-se manter as anota��es referentes ao ano e m�s base anterior, isto �, nessa situa��o, a consulta mais
	 * recente n�o substituir� a mais antiga.
	 * 
	 * @param resumo
	 *            O resumo da consulta ao BACEN.
	 * @return boolean indicando se o percentual processado � v�lido para gerar anota��o.
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
