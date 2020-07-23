package br.com.sicoob.capes.comum.arquivos.teste;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.JFileChooser;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.com.sicoob.capes.comum.arquivos.negocio.manipulador.CAPESManipuladorArquivo;
import br.com.sicoob.capes.comum.arquivos.teste.migracao.ManipuladorArquivoMigracao;
import br.com.sicoob.capes.comum.arquivos.teste.migracao.MigracaoCliVO;

/**
 * A Classe ImportacaoExportacaoMigracaoTest.
 */
@Ignore
public class ImportacaoExportacaoMigracaoTest extends ImportacaoExportacaoTest {

	/** O atributo manipuladorArquivo. */
	private CAPESManipuladorArquivo manipuladorArquivo;

	/**
	 * O método Test importar.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	@Test
	public void testImportar() throws Exception {

		JFileChooser chooser = new JFileChooser(
		        "C:\\Rodrigo\\Sicoob\\CAPES\\Desenvolvimento\\Migração");
		chooser.setMultiSelectionEnabled(false);
		chooser.setToolTipText("Selecione o diretório que contém os arquivos para importação");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File file = new File(chooser.getSelectedFile(), "Cli.txt");
			InputStream input = new FileInputStream(file);

			System.out.println("Iniciando importação: " + file.getPath());
			long inicio = System.currentTimeMillis();
			long inicioParcial = System.currentTimeMillis();

			Assert.assertNotNull(input);

			MigracaoCliVO cli = getManipuladorArquivo().importar(input, MigracaoCliVO.class);

			System.out.println("-----------------------------------------");
			System.out.println("Acabou em "
					+ converterParaSegundos((double) (System.currentTimeMillis() - inicioParcial))
					+ " segundos: " + file.getName());
			System.out.println("Resumo =\n" + cli.getResumoImportacao());
			System.out.println("-----------------------------------------");

			Assert.assertNotNull(cli);

			// Cliout.txt
//			file = new File(chooser.getSelectedFile(), "Cliout.txt");
//			input = new FileInputStream(file);
//
//			System.out.println("Iniciando importação: " + file.getPath());
//
//			inicioParcial = System.currentTimeMillis();
//
//			Assert.assertNotNull(input);
//
//			MigracaoCliOutVO cliOut = getManipuladorArquivo().importar(input,
//			        MigracaoCliOutVO.class);
//
//			System.out.println("-----------------------------------------");
//			System.out.println("Acabou em "
//					+ converterParaSegundos((double) (System.currentTimeMillis() - inicioParcial))
//					+ " segundos: " + file.getName());
//			System.out.println("Resumo =\n" + cliOut.getResumoImportacao());
//			System.out.println("-----------------------------------------");
//
			System.out.println("Acabou em "
					+ converterParaSegundos((double) (System.currentTimeMillis() - inicio))
					+ " segundos.");
//			Assert.assertNotNull(cliOut);
		}
	}

	/**
	 * Recupera o valor de manipuladorArquivo.
	 *
	 * @return o valor de manipuladorArquivo
	 */
	private CAPESManipuladorArquivo getManipuladorArquivo() {
		if (this.manipuladorArquivo == null) {
			this.manipuladorArquivo = new ManipuladorArquivoMigracao();
		}
		return this.manipuladorArquivo;
	}
}
