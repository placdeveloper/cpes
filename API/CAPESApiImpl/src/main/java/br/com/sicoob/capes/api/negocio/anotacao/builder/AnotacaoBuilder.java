/*
 * SICOOB
 * 
 * AnotacaoBuilder.java(br.com.sicoob.capes.api.negocio.anotacao.builder.AnotacaoBuilder)
 */
package br.com.sicoob.capes.api.negocio.anotacao.builder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import org.apache.commons.lang.math.NumberUtils;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.Anotacao;
import br.com.sicoob.capes.negocio.entidades.TipoAnotacao;

/**
 * Builder para as anotações.
 * 
 * @author Erico.Junior
 */
public abstract class AnotacaoBuilder {

	/** O atributo data anotacao. */
	private transient DateTimeDB dataAnotacao;
	
	/**
	 * Cria a anotação .
	 * @param dataAnotacao A data da anotação.
	 * @return a Anotação com os dados preenchidos.
	 */
	public Anotacao criarAnotacao(Date dataAnotacao) {
			
		Anotacao anotacao = new Anotacao();
		return popularDadosBasicos(anotacao, dataAnotacao);
	}

	/**
	 * Popular dados basicos.
	 * 
	 * @param anotacao
	 *            the anotacao
	 * @param dataAnotacao
	 *            the data anotacao
	 * @return anotacao
	 */
	protected Anotacao popularDadosBasicos(Anotacao anotacao, Date dataAnotacao) {
		
		this.dataAnotacao = new DateTimeDB(dataAnotacao.getTime());

		Integer quantidade = obterQuantidade();
		if(quantidade == null) {
			quantidade = NumberUtils.INTEGER_ZERO;
		}

		anotacao.setTipoAnotacao(obterTipoAnotacao());
		anotacao.setQuantidade(quantidade.shortValue());
		anotacao.setValor(obterValorAnotacao());
		anotacao.setDataHoraOcorrencia(obterDataHoraOcorrencia());		
		anotacao.setDataHoraAnotacao(getDataAnotacao());
		anotacao.setFlexibilidade(false);
		
		return anotacao;
	}
	
	/**
	 * Recupera o tipo da anotação.
	 * 
	 * @return O tipo da anotação.
	 */
	public TipoAnotacao obterTipoAnotacao() {

		Integer codigo = obterCodigoTipoAnotacao();
		TipoAnotacao tipo = new TipoAnotacao();
		tipo.setCodTipoAnotacao(codigo);
		return tipo;
	}

	/**
	 * Recupera o valor total para a anotação.
	 * 
	 * @return o valor total para a anotação.
	 */
	public abstract BigDecimal obterValorAnotacao();

	/**
	 * Recupera o código do tipo da anotação.
	 * 
	 * @return o código do tipo da anotação.
	 */
	protected abstract Integer obterCodigoTipoAnotacao();

	/**
	 * Obter data hora ocorrencia.
	 * 
	 * @return date time db
	 */
	protected DateTimeDB obterDataHoraOcorrencia() {
		
		Date dataOcorrencia = obterDataOcorrencia();
		DateTimeDB dtOcorrencia = null;
		
		if(dataOcorrencia != null) {
			dtOcorrencia = new DateTimeDB(dataOcorrencia.getTime());
		}
		
		return dtOcorrencia;
	}
	
	/**
	 * Recupera a data da ocorrência.
	 * @return A data da ocorrência.
	 */
	protected abstract Date obterDataOcorrencia();
	
	/**
	 * Recupera a quantidade de registros para a anotação.
	 * 
	 * @return a quantidade de registros para a anotação.
	 */
	protected abstract Integer obterQuantidade();

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return obterCodigoTipoAnotacao();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		Integer tipo = obterCodigoTipoAnotacao();
		Integer tipoParametro = obterCodigoTipoAnotacao();
		return tipo.equals(tipoParametro);
	}

	/**
	 * Calcula a soma entre BigDecimal.
	 * @param primeiro O primeiro termo da adição.
	 * @param segundo O segundo termo da adição.
	 * @return O resultado da soma.
	 */
	protected BigDecimal somar(BigDecimal primeiro, BigDecimal segundo) {
		
		BigDecimal termo1 = BigDecimal.ZERO;
		BigDecimal termo2 = BigDecimal.ZERO;
		
		if(primeiro != null) {
			termo1 = primeiro;
		}

		if(segundo != null) {
			termo2 = segundo;
		}
		
		termo1 = termo1.setScale(2, RoundingMode.HALF_EVEN);
		termo2 = termo2.setScale(2, RoundingMode.HALF_EVEN);
		return termo1.add(termo2);
	}
	
	/**
	 * Calcula a soma entre BigDecimal.
	 * @param primeiro O primeiro termo da adição.
	 * @param segundo O segundo termo da adição.
	 * @return O resultado da soma.
	 */
	protected BigDecimal somar(BigDecimal primeiro, Double segundo) {
		
		BigDecimal termo1 = BigDecimal.ZERO;
		BigDecimal termo2 = BigDecimal.ZERO;
		
		if(primeiro != null) {
			termo1 = primeiro;
		}

		if(segundo != null) {
			termo2 = new BigDecimal(segundo);
		}
		
		termo1 = termo1.setScale(2, RoundingMode.HALF_EVEN);
		termo2 = termo2.setScale(2, RoundingMode.HALF_EVEN);
		return termo1.add(termo2);
	}	
	

	/**
	 * @return the dataAnotacao
	 */
	protected DateTimeDB getDataAnotacao() {
		return dataAnotacao;
	}
	
}
