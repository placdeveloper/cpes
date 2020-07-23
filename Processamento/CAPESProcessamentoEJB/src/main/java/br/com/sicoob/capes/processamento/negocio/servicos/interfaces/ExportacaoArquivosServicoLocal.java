package br.com.sicoob.capes.processamento.negocio.servicos.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.Arquivo;
import br.com.sicoob.capes.negocio.entidades.DestinoExportacao;
import br.com.sicoob.capes.negocio.entidades.Exportacao;
import br.com.sicoob.capes.processamento.negocio.dominio.exportacaoarquivo.vo.RegistroArquivoBaseVO;
import br.com.sicoob.capes.processamento.negocio.dominio.exportacaoarquivo.vo.RegistroDadosArquivoBaseVO;
import br.com.sicoob.capes.processamento.negocio.dominio.exportacaoarquivo.vo.RegistroTrailerVO;

public interface ExportacaoArquivosServicoLocal {
	
	/**
	 * Realiza os procedimentos de exportacao para o {@code destino} recebido
	 * 
	 * @param destino
	 *            a configuracao da exportacao
	 * @return TODO
	 */
	<C extends RegistroArquivoBaseVO> void exportar(DestinoExportacao destino) throws BancoobException;
	
	/**
	 * Cria o cabeçalho do arquivo.
	 * @param exportacao
	 * @param metadados
	 * @param dataMovimento
	 * @param identificadorArquivo
	 * @param numeroLinha
	 * @return
	 * @throws BancoobException
	 */
	Integer criarCabecalho(Exportacao exportacao, Arquivo metadados, final DateTimeDB dataMovimento, Integer numeroLinha) throws BancoobException;
	
	/**
	 * Cria o rodapé do arquivo.
	 * @param exportacao
	 * @param metadados
	 * @param numeroLinha
	 * @return
	 * @throws BancoobException
	 */
	RegistroTrailerVO criarRodape(Exportacao exportacao, Arquivo metadados, Integer numeroLinha) throws BancoobException;
	
	/**
	 * Gerar dados arquivo.
	 *
	 * @param exportacao o valor de exportacao
	 * @param metadadosArquivo o valor de metadados arquivo
	 * @param numeroLinha o valor de numero linha
	 * @param registros o valor de registros
	 * @return Integer
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Integer gerarDadosArquivo(final Exportacao exportacao, Arquivo metadadosArquivo, Integer numeroLinha, List<RegistroDadosArquivoBaseVO> registros) throws BancoobException;
	
	/**
	 * Incluir exportacao.
	 *
	 * @param destino o valor de destino
	 * @param dataMovimento o valor de data movimento
	 * @param sequencialArquivo o valor de sequencial arquivo
	 * @return Exportacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Exportacao incluirExportacao(DestinoExportacao destino, final DateTimeDB dataMovimento, final Short sequencialArquivo) throws BancoobException;

}