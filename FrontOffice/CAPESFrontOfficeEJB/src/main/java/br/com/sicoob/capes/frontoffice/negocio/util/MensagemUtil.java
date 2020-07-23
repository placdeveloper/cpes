/*
 * SICOOB
 * 
 * MensagemUtil.java(br.com.sicoob.capes.frontoffice.negocio.util.MensagemUtil)
 */
package br.com.sicoob.capes.frontoffice.negocio.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import br.com.bancoob.srtb.dto.Mensagem;
import br.com.bancoob.srtb.dto.Parametro;
import br.com.sicoob.capes.frontoffice.negocio.enums.TipoValorParametroEnum;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * Classe utilit�ria para tratamento das mensagens trocadas nas transa��es banc�rias.
 * @author rodrigo.chaves
 */
public final class MensagemUtil {

	/** A Constante NAO_ENCONTRADO. */
	private static final String NAO_ENCONTRADO = "N�O ENCONTRADO";
	
	/**
	 * Construtor privado. 
	 */
	private MensagemUtil() {
	}
	
	/** 
	 * Logger para a classe utilit�ria.
	 */
	private static final ISicoobLogger LOGGER = SicoobLoggerPadrao.getInstance(MensagemUtil.class);
	
	/**
	 * Recupera um mapa de par�metros onde a chave � o nome do par�metro, ou seja, o r�tulo.
	 * @param mensagem A mensagem da qual se deseja os par�metros.
	 * @return um mapa de par�metros onde a chave � o nome do par�metro, ou seja, o r�tulo.
	 */
	public static Map<String, Parametro> recuperarParametros(Mensagem mensagem) {
		
		Map<String, Parametro> mapa = null;

		if(mensagem != null) {
			mapa = obterMapaParametros(mensagem.getIdTransacao(), mensagem.getParametros());
		} else {
			mapa = new HashMap<String, Parametro>();
		}
		
		return mapa;
	}	
	
	/**
	 * Monta um mapa com os par�metros informados.
	 * @param idTransacao O identificador da transa��o.
	 * @param parametros A lista dos par�metros da transa��o.
	 * @return Um Mapa com os par�metros da transa��o.
	 */
	private static Map<String, Parametro> obterMapaParametros(
			String idTransacao, List<Parametro> parametros) {
		
		Map<String, Parametro> mapa = new HashMap<String, Parametro>();

		StringBuilder builder = new StringBuilder("## PARAMETROS CAPES SICOOB - INICIO ##\n");
		builder.append("TRANSACAO: ").append(idTransacao);
		
		if(CollectionUtils.isNotEmpty(parametros)){
			String nome = null;
			for (Parametro parametro : parametros) {
				if(parametro != null) {
					nome = parametro.getNome();
					builder.append("\n").append(nome).append(" - ");
					builder.append(recuperarNomeTipoJdbc(parametro.getTipoJdbc()));
					builder.append(" - ").append(parametro.getValor());
					mapa.put(nome, parametro);
				}
			}
		}		
		
		builder.append("\n## PARAMETROS CAPES SICOOB - FIM ##");
		LOGGER.info(builder.toString());
		return mapa;
	}
	
	/**
	 * Recupera o tipoJdbc informado mapeado pelo SRTB.
	 * @param tipoJdbc O tipo jdbc em java.sql.Types
	 * @return o tipoJdbc informado mapeado pelo SRTB.
	 */
	private static String recuperarNomeTipoJdbc(int tipoJdbc){
		
		String nomeTipoJdbc = NAO_ENCONTRADO;
		TipoValorParametroEnum tipo = TipoValorParametroEnum.recuperarPorTipoJdbc(tipoJdbc);
		if(tipo != null) {
			nomeTipoJdbc = tipo.getNomeTipoJdbc();
		}
		return nomeTipoJdbc;
	}
}
