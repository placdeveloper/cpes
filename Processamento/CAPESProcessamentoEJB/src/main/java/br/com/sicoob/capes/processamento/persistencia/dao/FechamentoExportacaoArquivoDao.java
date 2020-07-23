package br.com.sicoob.capes.processamento.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.comum.negocio.enums.TipoInformacaoEnum;
import br.com.sicoob.capes.negocio.entidades.TipoInformacao;
import br.com.sicoob.capes.processamento.negocio.dominio.exportacaoarquivo.vo.RegistroDadosArquivoBaseVO;
import br.com.sicoob.capes.processamento.negocio.vo.CooperativaVO;

/**
 * @since 1.2.1.0
 * @author rodrigo.chaves
 */
public interface FechamentoExportacaoArquivoDao extends CAPESProcessamentoDaoIF {
	
	/**
	 * Realiza a pesquisa para obtencao dos dados para geracao do arquivo
	 * 
	 * @param tipoInformacao
	 *            O tipo de informacao a ser obtido
	 * @param dataMovimento
	 *            a data na qual houve a alteracao cadastral que se deseja exportar
	 * @return uma lista contendo os dados
	 */
	List<RegistroDadosArquivoBaseVO> pesquisarDadosExportacao(TipoInformacaoEnum tipoInformacao, DateTimeDB dataMovimento) throws BancoobException;
	
	/**
	 * Realiza a pesquisa com complementos para obtencao dos dados para geracao do arquivo
	 * 
	 * @param tipoInformacao
	 *            O tipo de informacao a ser obtido
	 * @param dataMovimento
	 *            a data na qual houve a alteracao cadastral que se deseja exportar
	 * @param listaTipos
	 * 			  Os tipos de informação que serão adicionados à consulta.
	 * @return uma lista contendo os dados
	 * @throws BancoobException
	 */
	List<RegistroDadosArquivoBaseVO> pesquisarDadosExportacao(TipoInformacaoEnum tipoInformacao, DateTimeDB dataMovimento, List<TipoInformacao> listaTipos) throws BancoobException;
	
	/**
	 * O método Criar tabela temporaria pac bancoob.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void criarTabelaTemporariaPacBancoob() throws BancoobException;
	
	/**
	 * O método Deletar tabela temporaria pac bancoob.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void deletarTabelaTemporariaPacBancoob() throws BancoobException;
	
	/**
	 * O método Inserir pacs tabela temporaria.
	 *
	 * @param cooperativas o valor de cooperativas
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void inserirPacsTabelaTemporaria(List<CooperativaVO> cooperativas) throws BancoobException;
}