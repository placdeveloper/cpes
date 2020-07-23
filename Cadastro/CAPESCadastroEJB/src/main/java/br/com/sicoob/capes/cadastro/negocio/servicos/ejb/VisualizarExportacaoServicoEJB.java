package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.apache.commons.io.IOUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.ExportacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.excecao.ArquivoExportacaoNaoEncontradoException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.VisualizarExportacaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.VisualizarExportacaoServicoRemote;
import br.com.sicoob.capes.cadastro.negocio.vo.ArquivoExportacaoVO;
import br.com.sicoob.capes.cadastro.util.DadosArquivoExportacaoIterator;
import br.com.sicoob.capes.comum.negocio.enums.CodificacaoArquivoEnum;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.negocio.entidades.DadosArquivoExportacao;
import br.com.sicoob.capes.negocio.entidades.DestinoExportacao;
import br.com.sicoob.capes.negocio.entidades.Exportacao;

/**
 * EJB contendo servicos relacionados a VisualizarExportacao.
 */
@Stateless
@Local({ VisualizarExportacaoServicoLocal.class })
@Remote({ VisualizarExportacaoServicoRemote.class })
public class VisualizarExportacaoServicoEJB extends CAPESCadastroServicoEJB implements VisualizarExportacaoServicoRemote, VisualizarExportacaoServicoLocal {

	/** A constante COMENTARIO_ARQUIVO. */
	private static final String COMENTARIO_ARQUIVO = "Arquivo gerado pela plataforma de clientes no dia {0,date,dd/MM/yyyy} as {0,date,HH}h:{0,date,mm}min.";
	
	/** A constante COMENTARIO_ARQUIVO_FECHAMENTO. */
	private static final String COMENTARIO_ARQUIVO_FECHAMENTO = "Arquivo gerado pelo fechamento agendado no dia {0,date,dd/MM/yyyy} as {0,date,HH}h:{0,date,mm}min.";
	
	/**
	 * {@inheritDoc}
	 */
	public ArquivoExportacaoVO obterArquivoExportacao(DestinoExportacao destino, DateTimeDB dataMovimento) throws BancoobException {
		return obterArquivoExportacao(destino, dataMovimento, false);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public ArquivoExportacaoVO obterArquivoExportacao(DestinoExportacao destino, DateTimeDB dataMovimento, boolean fechamentoAgendado) throws BancoobException {
		ArquivoExportacaoVO retorno = null;

		// Verifica se as informações foram passadas corretamente.
		if (destino != null && dataMovimento != null) {
			ExportacaoDelegate exportacaoDelegate = CAPESCadastroFabricaDelegates.getInstance().criarExportacaoDelegate();

			ConsultaDto<Exportacao> criterios = new ConsultaDto<Exportacao>();
			Exportacao filtro = new Exportacao();
			filtro.setDestino(destino);
			filtro.setDataMovimento(dataMovimento);
			criterios.setFiltro(filtro);
			
			// Obtém a exportação
			ConsultaDto<Exportacao> consultaDto = exportacaoDelegate.pesquisar(criterios);
			List<Exportacao> listaExportacao = consultaDto.getResultado();

			// Verifica a consulta retornou algum registro.
			if (listaExportacao != null && !listaExportacao.isEmpty()) {
				Exportacao exportacao = consultaDto.getResultado().get(0);

				if (exportacao != null) {
					Date dataAtual = new Date();
					ZipOutputStream zos = null;
					ByteArrayOutputStream baos = null;
					String nomeArquivo = MessageFormat.format(Constantes.Negocio.NOME_ARQUIVO_EXPORTACAO_PATTERN, exportacao.getNumeroArquivo(), dataAtual);
					
					// Obtém a codificação salva no destinoArquivo para salvar o arquivo.
					// Caso ela não seja encontrada, será usada a codificação padrão (UTF-8).
					CodificacaoArquivoEnum codificacaoArquivoEnum = CodificacaoArquivoEnum.obterPorCodigo(destino.getCodificacaoArquivo());
					if(codificacaoArquivoEnum == null){
						 codificacaoArquivoEnum = CodificacaoArquivoEnum.UTF_8;
					}
					
					try {
						// Configura o zipoutputstream, configura o nível de compressão para alto e abre a entrada para a gravação do arquivo.
						baos = new ByteArrayOutputStream();
						zos = new ZipOutputStream(baos);
						zos.setLevel(ZipOutputStream.DEFLATED);
						zos.setComment(fechamentoAgendado ? MessageFormat.format(COMENTARIO_ARQUIVO_FECHAMENTO, dataAtual) : MessageFormat.format(COMENTARIO_ARQUIVO, dataAtual));
						zos.putNextEntry(new ZipEntry(nomeArquivo));
						
						// Cria o iterator para fazer a consulta dos dados de arquivo de forma paginada.
						DadosArquivoExportacaoIterator iterator = new DadosArquivoExportacaoIterator(exportacao.getId());
						
						// Percorre a lista de dados
						while (iterator.hasNext()) {
							// Pega a linha dos dadosArquivo e grava no arquivo.
							DadosArquivoExportacao dadosArquivo = iterator.next();
							IOUtils.write(dadosArquivo.getLinha(), zos, codificacaoArquivoEnum.getCodigo());

							// Se não for a última linha do arquivo, escreve a quebra de linha.
							if (iterator.hasNext()) {
								IOUtils.write(IOUtils.LINE_SEPARATOR, zos, codificacaoArquivoEnum.getCodigo());
							}
						}

						// Fecha a entrada do arquivo dentro do zip.
						zos.closeEntry();
					} catch (IOException ioe) {
						getLogger().erro(ioe, "[CAPES] Erro ao gerar o arquivo de exportacao para visualizacao.");
					} finally {
						IOUtils.closeQuietly(baos);
						IOUtils.closeQuietly(zos);
					}

					// Cria o arquivo de retorno com o nome e os bytes gerados.
					retorno = new ArquivoExportacaoVO();
					retorno.setBytes(baos.toByteArray());
					retorno.setNome(nomeArquivo.replace(".txt", ".zip"));
				}

			} else {
				throw new ArquivoExportacaoNaoEncontradoException();
			}
		}
		return retorno;
	}

}