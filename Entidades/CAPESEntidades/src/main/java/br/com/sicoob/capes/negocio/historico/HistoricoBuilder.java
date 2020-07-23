/*
 * SICOOB
 * 
 * HistoricoBuilder.java(br.com.sicoob.capes.negocio.historico.HistoricoBuilder)
 */
package br.com.sicoob.capes.negocio.historico;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;

import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.sicoob.capes.negocio.entidades.interfaces.Historico;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * Superclasse para os Builders dos históricos
 * 
 * @author Erico.Junior
 */
public abstract class HistoricoBuilder<H extends Historico, V extends Vigente> {

	/**
	 * Cria o histórico para a entidade informada.
	 * @param entidadeVigente A entidade que será gravada como histórico.
	 * @return O histórico.
	 */
	public H criarHistorico(V entidadeVigente) {

		H historico = null;

		try {
			historico = novaInstanciaHistorico();
			historico.setDataHoraFim(new Date());
			historico.setDataHoraInicio(entidadeVigente.getDataHoraInicio());
			ConvertUtils.register(new BigDecimalConverter(BigDecimal.ZERO), BigDecimal.class);
			BeanUtils.copyProperties(historico, entidadeVigente);

		} catch (IllegalAccessException e) {
			SicoobLoggerPadrao.getInstance(this.getClass()).erro(
					e, "Não foi possível criar o Histórico para a entidade");
			throw new BancoobRuntimeException(e);
		} catch (InvocationTargetException e) {
			SicoobLoggerPadrao.getInstance(this.getClass()).erro(
					e, "Não foi possível criar o Histórico para a entidade");
			throw new BancoobRuntimeException(e);
		}

		return preencherDadosHistorico(historico, entidadeVigente);
	}
	
	/**
	 * O método deve ser escrito em cada Builder concreto para retorna uma instância da classe
	 * de histórico
	 * @return Uma instância da classe de histórico.
	 */
	protected abstract H novaInstanciaHistorico();

	/**
	 * Preenche os dados do histórico.
	 * @param historico O histórico para preenchimento dos dados.
	 * @param entidadeVigente A entidade com os dados utilizados para preenchimento.
	 * @return O histórico com os dados preenchidos.
	 */
	protected abstract H preencherDadosHistorico(H historico, V entidadeVigente);
}
