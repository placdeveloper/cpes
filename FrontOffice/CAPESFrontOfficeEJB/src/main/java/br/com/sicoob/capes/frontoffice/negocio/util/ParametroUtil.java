/*
 * SICOOB
 * 
 * ParametroUtil.java(br.com.sicoob.capes.frontoffice.negocio.util.ParametroUtil)
 */
package br.com.sicoob.capes.frontoffice.negocio.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Map;

import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.srtb.dto.Parametro;
import br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum;

/**
 * The Class ParametroUtil.
 */
public final class ParametroUtil {

	/**
	 * Construtor privado para garantir o Singleton.
	 */
	private ParametroUtil() {

	}

	/**
	 * Recuperar valor parametro.
	 * 
	 * @param <C>
	 *            the generic type
	 * @param parametros
	 *            the parametros
	 * @param metadados
	 *            the metadados
	 * @param tipoDado
	 *            the tipo dado
	 * @return c
	 */
	@SuppressWarnings("unchecked")
	public static <C> C recuperarValorParametro(Map<String, Parametro> parametros, ParametroEnum metadados,	Class<C> tipoDado) {
		C valor = null;
		Parametro parametro = parametros.get(metadados.getRotulo());
		if ((parametro != null) && metadados.getTipoDadoPersistencia().equals(parametro.getTipoJdbc())) {
			valor = (C) obterValor(metadados.getTipoDado(), parametro);
		}
		return valor;
	}

	/**
	 * Obter valor.
	 * 
	 * @param <C>
	 *            the generic type
	 * @param tipo
	 *            the tipo
	 * @param parametro
	 *            the parametro
	 * @return c
	 */
	@SuppressWarnings("unchecked")
	private static <C> C obterValor(Class<C> tipo, Parametro parametro) {
		try {
			Method method = ParametroUtil.class.getDeclaredMethod("obter" + tipo.getSimpleName(), Parametro.class);
			if (!method.isAccessible()) {
				method.setAccessible(true);
			}
			return (C) method.invoke(ParametroUtil.class, parametro);
		} catch (SecurityException e) {
			throw new BancoobRuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new BancoobRuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new BancoobRuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new BancoobRuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new BancoobRuntimeException(e);
		}
	}

	/**
	 * Recupera um java.sql.Date com o valor do parâmetro informado.
	 * 
	 * @param parametro
	 *            O parâmetro da transação.
	 * @return um java.sql.Date a partir do valor informado.
	 */
	@SuppressWarnings("unused")
	private static java.sql.Date obterDate(Parametro parametro) {
		java.sql.Date retorno = null;
		if (parametro != null) {
			Object valor = parametro.getValor();
			if (valor != null) {
				if (valor instanceof java.sql.Date) {
					retorno = (java.sql.Date) valor;
				} else {
					retorno = java.sql.Date.valueOf(valor.toString());
				}
			}
		}
		return retorno;
	}

	/**
	 * Recupera um Short com o valor do parâmetro informado.
	 * 
	 * @param parametro
	 *            O parâmetro da transação.
	 * @return um Short a partir do valor informado.
	 */
	@SuppressWarnings("unused")
	private static Short obterShort(Parametro parametro) {
		Short retorno = null;
		if (parametro != null) {
			Object valor = parametro.getValor();
			if (valor != null) {
				if (valor instanceof Short) {
					retorno = (Short) valor;
				} else {
					retorno = Short.valueOf(valor.toString());
				}
			}
		}
		return retorno;
	}

	/**
	 * Recupera um Short com o valor do parâmetro informado.
	 * 
	 * @param parametro
	 *            O parâmetro da transação.
	 * @return um Short a partir do valor informado.
	 */
	@SuppressWarnings("unused")
	private static Integer obterInteger(Parametro parametro) {
		Integer retorno = null;
		if (parametro != null) {
			Object valor = parametro.getValor();
			if (valor != null) {
				if (valor instanceof Integer) {
					retorno = (Integer) valor;
				} else {
					retorno = Integer.valueOf(valor.toString());
				}
			}
		}
		return retorno;
	}

	/**
	 * Recupera um BigDecimal com o valor do parâmetro informado.
	 * 
	 * @param parametro
	 *            O parâmetro da transação.
	 * @return um BigDecimal a partir do valor informado.
	 */
	@SuppressWarnings("unused")
	private static BigDecimal obterBigDecimal(Parametro parametro) {
		BigDecimal retorno = null;
		if (parametro != null) {
			Object valor = parametro.getValor();
			if (valor != null) {
				if (valor instanceof BigDecimal) {
					retorno = (BigDecimal) valor;
				} else {
					retorno = new BigDecimal(valor.toString());
				}
			}
		}
		return retorno;
	}

	/**
	 * Recupera um Boolean com o valor do parâmetro informado.
	 * 
	 * @param parametro
	 *            O parâmetro da transação.
	 * @return um Boolean a partir do valor informado.
	 */
	@SuppressWarnings("unused")
	private static Boolean obterBoolean(Parametro parametro) {
		Boolean retorno = null;
		if (parametro != null) {
			Object valor = parametro.getValor();
			if (valor != null) {
				if (valor instanceof Boolean) {
					retorno = (Boolean) valor;
				} else {
					retorno = Boolean.valueOf(valor.toString());
				}
			}
		}
		return retorno;
	}

	/**
	 * Recupera um String com o valor do parâmetro informado.
	 * 
	 * @param parametro
	 *            O parâmetro da transação.
	 * @return um String a partir do valor informado.
	 */
	protected static String obterString(Parametro parametro) {
		String retorno = null;
		if (parametro != null) {
			Object valor = parametro.getValor();
			if (valor != null) {
				if (valor instanceof String) {
					retorno = (String) valor;
				} else {
					retorno = String.valueOf(valor);
				}
			}
		}
		return retorno;
	}
}
