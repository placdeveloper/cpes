package br.com.sicoob.capes.comum.arquivos.teste.pocli;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;

import br.com.sicoob.capes.comum.arquivos.RegistroArquivo;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.Tratador;
import br.com.sicoob.capes.comum.arquivos.negocio.manipulador.CAPESManipuladorArquivo;
import br.com.sicoob.capes.comum.arquivos.negocio.manipulador.CAPESManipuladorArquivoAbstract;

/**
 * A Classe ManipuladorArquivoPortifolio.
 */
public class ManipuladorArquivoPortifolio extends CAPESManipuladorArquivoAbstract implements CAPESManipuladorArquivo {

	/** O atributo qtdLinhas. */
	private int qtdLinhas;
	
	/** O atributo sdf. */
	private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMM/yyyy");

	/**
	 * Tratar registro arquivo.
	 *
	 * @param cabecalho o valor de cabecalho
	 * @return {@code true}, em caso de sucesso
	 */
	@Tratador
	public boolean tratarRegistroArquivo(CabecalhoPortifolio cabecalho) {
		System.out.println("--- Header ---");
		System.out.println(obterNumeroLinha(cabecalho) + " - Data geração arquivo: " + sdf.format(cabecalho.getDataArquivo()));
		return true;
	}
	
	/**
	 * Tratar registro arquivo.
	 *
	 * @param detalhe o valor de detalhe
	 * @return {@code true}, em caso de sucesso
	 */
	@Tratador
	public boolean tratarRegistroArquivo(DetalhePortifolio detalhe) {
		if (qtdLinhas == 0) {
			System.out.println("--- Detalhes ---");
		}
		System.out.println(obterNumeroLinha(detalhe) + " - " + detalhe);
		qtdLinhas++;
		return true;
	}
	
	/**
	 * Tratar registro arquivo.
	 *
	 * @param rodape o valor de rodape
	 * @return {@code true}, em caso de sucesso
	 */
	@Tratador
	public boolean tratarRegistroArquivo(RodapePortifolio rodape) {
		System.out.println("--- Trailer ---");
		System.out.println(obterNumeroLinha(rodape) + " - Total registros: " + rodape.getTotalRegistros());
		return rodape.getTotalRegistros() == qtdLinhas;
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
}
