package br.com.sicoob.capes.comum.arquivos.util.conversao;

import java.util.HashMap;
import java.util.Map;

import br.com.sicoob.capes.comum.arquivos.excecao.CampoArquivoInvalidoException;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo;
import br.com.sicoob.capes.comum.arquivos.util.StringUtils;

/**
 * A Classe ConversorBoolean.
 */
public class ConversorBoolean extends ConversorAbstrato<Boolean> {

	/** O atributo valores. */
	protected Map<String, Boolean> valores;

	/**
	 * Instancia um novo ConversorBoolean.
	 */
	public ConversorBoolean() {
		valores = new HashMap<String, Boolean>();
		valores.put("S", Boolean.TRUE);
		valores.put("N", Boolean.FALSE);
		valores.put("Sim", Boolean.TRUE);
		valores.put("N\u00E3o", Boolean.FALSE);
		valores.put("1", Boolean.TRUE);
		valores.put("0", Boolean.FALSE);
		valores.put("true", Boolean.TRUE);
		valores.put("false", Boolean.FALSE);
    }
	
	/**
	 * {@inheritDoc}
	 */
	public Boolean converterDeString(String valor, CampoArquivo metadados) throws CampoArquivoInvalidoException {
		verificarObrigatoriedade(valor, metadados);
		
		if(!valores.containsKey(valor)){
			throw new CampoArquivoInvalidoException("exception.conversao.boolean", valor, getTipoDado());
		}
		
		return valores.get(valor);
	}

	/**
	 * {@inheritDoc}
	 */
	public String converterParaString(Boolean valor, CampoArquivo metadados) {
		String result = null;
		if (valor == null) {
			result = StringUtils.EMPTY;
		} else {
			result = (valor ? metadados.descBooleanTrue() : metadados.descBooleanFalse());
		}
		return result;
	}

}