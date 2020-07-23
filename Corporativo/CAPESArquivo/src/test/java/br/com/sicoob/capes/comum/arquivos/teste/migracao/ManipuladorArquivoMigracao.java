package br.com.sicoob.capes.comum.arquivos.teste.migracao;

import java.text.MessageFormat;

import br.com.sicoob.capes.comum.arquivos.RegistroArquivo;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.Tratador;
import br.com.sicoob.capes.comum.arquivos.negocio.manipulador.CAPESManipuladorArquivoAbstract;

/**
 * A Classe ManipuladorArquivoMigracao.
 */
public class ManipuladorArquivoMigracao extends CAPESManipuladorArquivoAbstract {

	/** O atributo qtdLinhas. */
	private int qtdLinhas;
	
	/**
	 * Tratar registro arquivo.
	 *
	 * @param vo o valor de vo
	 * @return {@code true}, em caso de sucesso
	 */
	@Tratador
	public boolean tratarRegistroArquivo(CliVO vo) {
		System.out.println(obterNumeroLinha(vo) + " - " + vo);
		qtdLinhas++;
		return true;
	}
	
	/**
	 * Obter numero linha.
	 *
	 * @param <R> o tipo generico
	 * @param registro o valor de registro
	 * @return String
	 */
	public <R extends RegistroArquivo> String obterNumeroLinha(R registro) {
		return MessageFormat.format("{0,number,00000}", registro.getNumeroLinha());
	}

	/**
     * @return the qtdLinhas
     */
    protected int getQtdLinhas() {
    	return qtdLinhas;
    }
	
}
