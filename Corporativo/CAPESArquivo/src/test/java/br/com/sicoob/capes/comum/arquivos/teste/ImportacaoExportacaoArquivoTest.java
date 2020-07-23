package br.com.sicoob.capes.comum.arquivos.teste;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.com.sicoob.capes.comum.arquivos.RegistroArquivo;
import br.com.sicoob.capes.comum.arquivos.teste.apoio.CabecalhoEntidadeTeste;
import br.com.sicoob.capes.comum.arquivos.teste.apoio.DadosArquivoTesteVo;
import br.com.sicoob.capes.comum.arquivos.teste.apoio.EntidadeTeste;
import br.com.sicoob.capes.comum.arquivos.teste.apoio.ManipuladorArquivoTeste;
import br.com.sicoob.capes.comum.arquivos.teste.apoio.ManipuladorArquivoTesteImpl;
import br.com.sicoob.capes.comum.arquivos.teste.apoio.RodapeEntidadeTeste;

/**
 * Teste unitário utilizado para testar a importação de exportação de arquivos texto de troca de dados.
 * 
 * Criado em 22/7/2014
 * 
 * @author rodrigo.chaves
 */
@Ignore
public class ImportacaoExportacaoArquivoTest extends ImportacaoExportacaoTest {

	/** O atributo manipuladorArquivo. */
	private ManipuladorArquivoTeste manipuladorArquivo;

	/**
	 * Método de teste para
	 * {@link br.com.sicoob.capes.comum.arquivos.negocio.manipulador.CAPESManipuladorArquivo#exportar(br.com.sicoob.capes.comum.arquivos.negocio.vo.DadosArquivoVO)}
	 */
	@Test()
	public void testExportar() {

		long inicio = System.currentTimeMillis();
		System.out.print("Inicio... ");
		List<RegistroArquivo> registros = new ArrayList<RegistroArquivo>();

		CabecalhoEntidadeTeste cabecalho = new CabecalhoEntidadeTeste(new Date(), "ARQUIVO_01_aaaa", "RODRIGO CHAVES");
		registros.add(cabecalho);

		final Integer qtdeLinhas = 10000;
		for (int i = 0; i < qtdeLinhas; i++) {
			registros.add(gerarEntidadeTeste());
		}
		System.out.println("Entidades geradas em " + (System.currentTimeMillis() - inicio) + " ms");

		RodapeEntidadeTeste rodape = new RodapeEntidadeTeste(qtdeLinhas.longValue());
		registros.add(rodape);

		DadosArquivoTesteVo dadosArquivo = new DadosArquivoTesteVo();
		dadosArquivo.setRegistros(registros);

		// Escrevendo no arquivo de saída
		FileWriter writer = null;
		InputStream arquivo = null;
		try {
			arquivo = getManipuladorArquivo().exportar(dadosArquivo);
			long fim = System.currentTimeMillis();

			double tempoTotal = fim - inicio;
			System.out.println("Acabou em " + converterParaSegundos(tempoTotal) + " segundos");
			double segundosPorLinha = qtdeLinhas / tempoTotal;
			System.out.println("Média de segundos: ");
			System.out.println("\t1 linha: " + converterParaSegundos(segundosPorLinha));
			System.out.println("\t1000 linhas: " + converterParaSegundos(segundosPorLinha * 1000));

			File caminhoArquivo = new File(System.getProperty("user.dir") + "\\specs\\arquivo-EntidadeTeste-exportacao.txt");
			if (!caminhoArquivo.exists()) {
				caminhoArquivo.createNewFile();
			}
			writer = new FileWriter(caminhoArquivo, false);
			IOUtils.copy(arquivo, writer);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		} finally {
			IOUtils.closeQuietly(arquivo);
			IOUtils.closeQuietly(writer);
		}
	}

	/**
	 * Método de teste para
	 * {@link br.com.sicoob.capes.comum.arquivos.negocio.manipulador.CAPESManipuladorArquivo#importar(InputStream, Class)}
	 */
	@Test(timeout = DateUtils.MILLIS_PER_SECOND * 3)
	public void testImportar() {

		try {
			InputStream conteudoArquivo = getClass().getResourceAsStream("/arquivo-EntidadeTeste-exportacao.txt");
			long inicio = System.currentTimeMillis();
			System.out.println("Iniciou importação...");

			DadosArquivoTesteVo dadosArquivo = getManipuladorArquivo().importar(conteudoArquivo, DadosArquivoTesteVo.class);

			System.out.println("-----------------------------------------");
			System.out.println("Resumo =\n" + dadosArquivo.getResumoImportacao());
			System.out.println("-----------------------------------------");

			double fim = System.currentTimeMillis() - inicio;
			System.out.println("Acabou em " + converterParaSegundos(fim) + " segundos");
			Assert.assertNotNull(dadosArquivo);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Gera as entidades de teste para serem usadas na expotação de objetos para arquivo texto.
	 * 
	 * @return EntidadeTeste - mock preenchido com valores aleatórios.
	 */
	private EntidadeTeste gerarEntidadeTeste() {

		EntidadeTeste e = new EntidadeTeste();
		e.setCodigo(RandomUtils.nextInt(10000));
		e.setDataNascimento(new Date());
		e.setDescricao(RandomStringUtils.randomAlphabetic(RandomUtils.nextInt(150)) + 1);
		e.setNome(RandomStringUtils.randomAlphabetic(RandomUtils.nextInt(30)) + 1);
		e.setValor(new BigDecimal(RandomUtils.nextDouble() * RandomUtils.nextInt(500)));
		return e;
	}

	/**
	 * Obtém o valor do atributo <code>manipuladorArquivo</code>.
	 * 
	 * @return O atributo manipuladorArquivo
	 */
	private ManipuladorArquivoTeste getManipuladorArquivo() {

		if (manipuladorArquivo == null) {
			manipuladorArquivo = new ManipuladorArquivoTesteImpl();
		}
		return manipuladorArquivo;
	}

}
