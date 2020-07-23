package br.com.sicoob.capes.comum.arquivos.teste;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.sicoob.capes.comum.arquivos.RegistroArquivo;
import br.com.sicoob.capes.comum.arquivos.negocio.manipulador.CAPESManipuladorArquivo;
import br.com.sicoob.capes.comum.arquivos.teste.pocli.CabecalhoPortifolio;
import br.com.sicoob.capes.comum.arquivos.teste.pocli.DadosArquivoPortifolioVO;
import br.com.sicoob.capes.comum.arquivos.teste.pocli.DetalhePortifolio;
import br.com.sicoob.capes.comum.arquivos.teste.pocli.ManipuladorArquivoPortifolio;
import br.com.sicoob.capes.comum.arquivos.teste.pocli.RodapePortifolio;
import br.com.sicoob.capes.comum.arquivos.teste.util.CpfCnpjUtil;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;

/**
 * A Classe ImportacaoExportacaoPortifolioTest.
 */
@Ignore("Apenas para utilização local, pois gera arquivo no disco")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ImportacaoExportacaoPortifolioTest extends ImportacaoExportacaoTest {

	/** O atributo manipuladorArquivo. */
	private ManipuladorArquivoPortifolio manipuladorArquivo;
	
	/** A constante CODIGOS_PORTIFOLIO. */
	private static final int[] CODIGOS_PORTIFOLIO = new int[] { 3, 4, 21, 22 };
	
	/** A constante CODIGOS_TIPO_PORTIFOLIO. */
	private static final Map<Integer, int[]> CODIGOS_TIPO_PORTIFOLIO = new HashMap<Integer, int[]>();

	static {
		CODIGOS_TIPO_PORTIFOLIO.put(3, new int[] { 1, 2, 3, 4, 5, 6, 7, 8 });
		CODIGOS_TIPO_PORTIFOLIO.put(4, new int[] { 1, 2, 3, 4 });
		CODIGOS_TIPO_PORTIFOLIO.put(21, new int[] { 1 });
		CODIGOS_TIPO_PORTIFOLIO.put(22, new int[] { 1, 2, 3, 4 });
	}

	/**
	 * Método de teste para
	 * {@link br.com.sicoob.capes.comum.arquivos.negocio.manipulador.CAPESManipuladorArquivo#importar(InputStream, Class)}
	 */
	@Test(timeout = DateUtils.MILLIS_PER_SECOND)
	public void testImportar() {

		InputStream conteudoArquivo = null;
		try {
			conteudoArquivo = getClass().getResourceAsStream("/portifolio.txt");
			
			Assert.assertNotNull(conteudoArquivo);
			
			long inicio = System.currentTimeMillis();
			System.out.println("Iniciou importação do portifolio...");

			DadosArquivoPortifolioVO dadosArquivo = getManipuladorArquivo().importar(conteudoArquivo,
					DadosArquivoPortifolioVO.class);

			System.out.println("-----------------------------------------");
			System.out.println("Resumo =\n" + dadosArquivo.getResumoImportacao());
			System.out.println("-----------------------------------------");

			double fim = System.currentTimeMillis() - inicio;
			System.out.println("Acabou em " + converterParaSegundos(fim) + " segundos");
			Assert.assertNotNull(dadosArquivo);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		} finally {
			IOUtils.closeQuietly(conteudoArquivo);
		}
	}

	/**
	 * Método de teste para
	 * {@link br.com.sicoob.capes.comum.arquivos.negocio.manipulador.CAPESManipuladorArquivo#exportar(br.com.sicoob.capes.comum.arquivos.negocio.vo.DadosArquivoVO)}
	 */
	@Test(timeout = DateUtils.MILLIS_PER_SECOND)
	public void testExportar() {

		long inicio = System.currentTimeMillis();
		System.out.print("Inicio... ");
		List<RegistroArquivo> registros = new ArrayList<RegistroArquivo>();

		CabecalhoPortifolio cabecalho = new CabecalhoPortifolio(new Date());
		registros.add(cabecalho);

		final Integer qtdeLinhas = 1000;
		for (int i = 0; i < qtdeLinhas; i++) {
			registros.add(gerarDetalhe());
		}
		System.out.println("Entidades geradas em " + (System.currentTimeMillis() - inicio) + " ms");

		RodapePortifolio rodape = new RodapePortifolio(qtdeLinhas);
		registros.add(rodape);

		DadosArquivoPortifolioVO dadosArquivo = new DadosArquivoPortifolioVO();
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

			File caminhoArquivo = new File(System.getProperty("user.dir") + "\\specs\\portifolio.txt");
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
	 * Gerar detalhe.
	 *
	 * @return DetalhePortifolio
	 */
	private DetalhePortifolio gerarDetalhe() {

		DetalhePortifolio d = new DetalhePortifolio();
		d.setCodPortifolio(randomizeCodigo(CODIGOS_PORTIFOLIO));
		d.setCodTipoPessoa(randomizeCodigo(TipoPessoaEnum.values()).getCodigo());
		d.setCodTipoPortifolio(randomizeCodigo(CODIGOS_TIPO_PORTIFOLIO.get(d.getCodPortifolio())));
		d.setCpfCnpj(CpfCnpjUtil.gerarCpfCnpj(d.getCodTipoPessoa()));
		d.setDataFimPortifolio(new Date());
		d.setDataInicoPortifolio(new Date());
		d.setIdEndereco((long) RandomUtils.nextInt());
		d.setIdInstituicao(RandomUtils.nextInt(1000000));
		d.setIdPortifolio(RandomUtils.nextInt(100000));
		d.setSequencialTitular(1);
		return d;
	}

	/**
	 * Randomize codigo.
	 *
	 * @param <I> o tipo generico
	 * @param codigos o valor de codigos
	 * @return I
	 */
	private <I> I randomizeCodigo(I[] codigos) {

		return codigos[RandomUtils.nextInt(codigos.length)];
	}

	/**
	 * Randomize codigo.
	 *
	 * @param codigos o valor de codigos
	 * @return int
	 */
	private int randomizeCodigo(int[] codigos) {

		return codigos[RandomUtils.nextInt(codigos.length)];
	}

	/**
	 * Recupera o valor de manipuladorArquivo.
	 *
	 * @return o valor de manipuladorArquivo
	 */
	private CAPESManipuladorArquivo getManipuladorArquivo() {

		if (manipuladorArquivo == null) {
			manipuladorArquivo = new ManipuladorArquivoPortifolio();
		}
		return manipuladorArquivo;
	}
}
