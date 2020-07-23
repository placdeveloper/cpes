package br.com.sicoob.capes.processamento.negocio.servicos.ejb.steps;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.time.DateUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.DadosArquivoExportacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.DestinoExportacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.ExportacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.VisualizarExportacaoDelegate;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.Arquivo;
import br.com.sicoob.capes.comum.arquivos.negocio.manipulador.CAPESManipuladorArquivoAbstract;
import br.com.sicoob.capes.comum.negocio.enums.TipoInformacaoEnum;
import br.com.sicoob.capes.comum.negocio.vo.InstituicaoVO;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.DadosArquivoExportacao;
import br.com.sicoob.capes.negocio.entidades.DestinoExportacao;
import br.com.sicoob.capes.negocio.entidades.Exportacao;
import br.com.sicoob.capes.negocio.entidades.TipoInformacao;
import br.com.sicoob.capes.processamento.negocio.dominio.exportacaoarquivo.vo.ArquivoExportacaoVO;
import br.com.sicoob.capes.processamento.negocio.dominio.exportacaoarquivo.vo.RegistroArquivoBaseVO;
import br.com.sicoob.capes.processamento.negocio.dominio.exportacaoarquivo.vo.RegistroDadosArquivoBaseVO;
import br.com.sicoob.capes.processamento.negocio.dominio.exportacaoarquivo.vo.RegistroHeaderVO;
import br.com.sicoob.capes.processamento.negocio.dominio.exportacaoarquivo.vo.RegistroTrailerVO;
import br.com.sicoob.capes.processamento.negocio.servicos.interfaces.ExportacaoArquivosServicoLocal;
import br.com.sicoob.capes.processamento.negocio.vo.CooperativaVO;
import br.com.sicoob.capes.processamento.persistencia.dao.CooperativaDao;
import br.com.sicoob.capes.processamento.persistencia.dao.FechamentoExportacaoArquivoDao;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.servico.StepServico;

/**
 * Fluxo de exportação de arquivos, gera um arquivo com as alterações diárias (D-1), antes executado pelo Fechamento Agendado.
 *
 * @author Bruno.Carneiro
 */
@Stateless
@Remote(StepServico.class)
@Local(ExportacaoArquivosServicoLocal.class)
public class ExportacaoArquivosStepEJB extends CAPESStepBase implements ExportacaoArquivosServicoLocal {

	private static final int QUANTIDADE_INSERCAO_EM_LOTE = 5000;

	@Inject
	@Default
	private FechamentoExportacaoArquivoDao dao;

	@Inject
	@Default
	private CooperativaDao cooperativaDao;

	@EJB
	private ExportacaoArquivosServicoLocal servicoLocal;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RetornoExecucao executarStep(ContextoExecucao contexto) throws BancoobException {
		DestinoExportacao destino = null;
		getLogger().info("Iniciando a execucao do step de exportacao de arquivos.");

		try {
			List<DestinoExportacao> destinos = recuperarDestinosAtivos();

			// faz a exportacao para cada destino ativo.
			if (destinos != null && !destinos.isEmpty()) {
				Iterator<DestinoExportacao> it = destinos.iterator();
				while (it.hasNext()) {
					destino = it.next();
					servicoLocal.exportar(destino);
				}
			}

			return sucesso();
		} catch (BancoobException e) {// NOPMD
			Short idDestino = null;
			String descricao = null;
			if (destino != null) {
				idDestino = destino.getId();
				descricao = destino.getDescricao();
			}
			getLogger().erro(e, "execucao do step de exportacao de arquivos: " + idDestino + ": " + descricao);
			return erro("Erro na execucao do step de exportacao de arquivos: " + e.getMessage());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public <C extends RegistroArquivoBaseVO> void exportar(DestinoExportacao destino) throws BancoobException {
		try {
			criarTabelaTemporariaPacsBancoob();

			final DateTimeDB dataMovimento = gerarDataMovimento();
			final Short sequencialArquivo = obterSequencialArquivo(destino);

			ArquivoExportacaoVO arquivoVO = new ArquivoExportacaoVO();
			Arquivo metadados = arquivoVO.getMetadados();

			// Consulta a exportacao para o destino e data movimento.
			Exportacao exportacao = consultarExportacao(destino, dataMovimento);

			// Se ainda nao foi gerada inclui a exportacao.
			if (exportacao == null) {
				exportacao = servicoLocal.incluirExportacao(destino, dataMovimento, sequencialArquivo);
			}

			// obtem os tipos que foram gerados na tabela de dados arquivo
			DadosArquivoExportacaoDelegate dadosArquivoExportacaoDelegate = CAPESCadastroFabricaDelegates.getInstance().criarDadosArquivoExportacaoDelegate();
			List<TipoInformacao> listaTiposExecutados = dadosArquivoExportacaoDelegate.obterCodigosTipoInformacaoExportacao(exportacao);

			// verifica os tipos que nao foram executados.
			@SuppressWarnings("unchecked")
			List<TipoInformacao> listaTiposNaoExecutados = (List<TipoInformacao>) CollectionUtils.disjunction(destino.getTiposInformacao(), listaTiposExecutados);

			// Ordena a lista de acordo com o codigo do tipo da informacao.
			Collections.sort(listaTiposNaoExecutados, new Comparator<TipoInformacao>() {
				@Override
				public int compare(TipoInformacao o1, TipoInformacao o2) {
					return o1.getCodigo().compareTo(o2.getCodigo());
				}
			});

			// obtem o numero da proxima linha para continuar gerando os dados.
			Integer numeroLinha = obterProximoNumeroLinha(exportacao);

			// Verifica se ja foi gerado o cabecalho
			if (!contemNaLista(Short.valueOf(RegistroHeaderVO.CODIGO_REGISTRO), listaTiposNaoExecutados)) {
				// gera o cabecalho
				numeroLinha = servicoLocal.criarCabecalho(exportacao, metadados, dataMovimento, numeroLinha);
			}

			// verifica se o tipo PESSOA esta na lista dos nao executados.
			if (contemNaLista(TipoInformacaoEnum.PESSOA.getCodigo(), listaTiposNaoExecutados)) {
				// se nao estiver na lista, gera os dados de pessoa.
				List<RegistroDadosArquivoBaseVO> dadosPessoa = dao.pesquisarDadosExportacao(TipoInformacaoEnum.PESSOA, dataMovimento, destino.getTiposInformacao());
				numeroLinha = servicoLocal.gerarDadosArquivo(exportacao, metadados, numeroLinha, dadosPessoa);
			}

			// gera os registros para cada tipo de informacao que ainda nao foi gerado.
			numeroLinha = gerarRegistrosPorTipo(listaTiposNaoExecutados, dataMovimento, exportacao, metadados, numeroLinha);

			// verifica se ja foi gerado o rodape.
			if (!contemNaLista(Short.valueOf(RegistroTrailerVO.CODIGO_REGISTRO), listaTiposNaoExecutados)) {
				// se na lista ainda nao contem o rodape, gera o mesmo.
				servicoLocal.criarRodape(exportacao, metadados, numeroLinha);
			}

			// gerando o arquivo
			gerarArquivo(destino, dataMovimento);

			// Finaliza a exportacao
			exportacao.setFinalizado(Boolean.TRUE);
			alterarExportacao(exportacao);

		} catch (BancoobException e) {
			getLogger().erro(e, "Erro ao executar o fechamento de exportacao de arquivos");
			throw e;
		} finally {
			dao.deletarTabelaTemporariaPacBancoob();
		}
	}

	/**
	 * O método Criar tabela temporaria pacs bancoob.
	 *
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	private void criarTabelaTemporariaPacsBancoob() throws BancoobException {
		List<CooperativaVO> listaPacsBancoob = cooperativaDao.obterPacsBancoob();
		dao.criarTabelaTemporariaPacBancoob();
		dao.inserirPacsTabelaTemporaria(listaPacsBancoob);
	}

	/**
	 * Verifica se o tipo da informacao esta na lista de tipos.
	 * 
	 * @param tipo
	 * @param tipos
	 * @return {@code boolean} se esta ou nao na lista
	 */
	private boolean contemNaLista(Short codigo, List<TipoInformacao> tipos) {
		for (TipoInformacao tipoInf : tipos) {
			if (tipoInf.getCodigo().equals(codigo)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gera o arquivo e copia para o diretorio do destino
	 * 
	 * @param destino
	 * @param exportacao
	 * @param arquivoVO
	 * @throws BancoobException
	 */
	private void gerarArquivo(DestinoExportacao destino, DateTimeDB dataMovimento) throws BancoobException {
		FileOutputStream out = null;
		try {
			VisualizarExportacaoDelegate visualizarExportacaoDelegate = CAPESCadastroFabricaDelegates.getInstance().criarVisualizarExportacaoDelegate();
			br.com.sicoob.capes.cadastro.negocio.vo.ArquivoExportacaoVO arquivoExportacaoVO = visualizarExportacaoDelegate.obterArquivoExportacao(destino, dataMovimento, Boolean.TRUE);

			File arquivo = new File(new File(destino.getDiretorio()), arquivoExportacaoVO.getNome());
			out = new FileOutputStream(arquivo);
			IOUtils.write(arquivoExportacaoVO.getBytes(), out);
		} catch (FileNotFoundException e) {
			throw new BancoobRuntimeException("Falha ao exportar arquivo do destino: " + destino.getDescricao() + " na data: " + dataMovimento.toString(), e);
		} catch (IOException e) {
			throw new BancoobRuntimeException("Falha ao exportar arquivo do destino: " + destino.getDescricao() + " na data: " + dataMovimento.toString(), e);
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	/**
	 * Realiza a consulta da exportacao por data movimento e destino.
	 * 
	 * @param destino
	 * @param dataMovimento
	 * @return
	 * @throws BancoobException
	 */
	private Exportacao consultarExportacao(DestinoExportacao destino, final DateTimeDB dataMovimento) throws BancoobException {
		Exportacao retorno = null;
		ExportacaoDelegate delegateExportacao = CAPESCadastroFabricaDelegates.getInstance().criarExportacaoDelegate();

		ConsultaDto<Exportacao> criterios = new ConsultaDto<Exportacao>();
		Exportacao filtro = new Exportacao();
		filtro.setDataMovimento(dataMovimento);
		filtro.setDestino(destino);
		criterios.setFiltro(filtro);

		ConsultaDto<Exportacao> retornoConsulta = delegateExportacao.pesquisar(criterios);
		if (retornoConsulta.getResultado() != null && !retornoConsulta.getResultado().isEmpty()) {
			retorno = retornoConsulta.getResultado().get(0);
		}
		return retorno;
	}

	/**
	 * Realiza a inclusao do registro de exportacao.
	 * 
	 * @param destino
	 * @param dataMovimento
	 * @param sequencialArquivo
	 * @return
	 * @throws BancoobException
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Exportacao incluirExportacao(DestinoExportacao destino, final DateTimeDB dataMovimento, final Short sequencialArquivo) throws BancoobException {
		ExportacaoDelegate delegateExportacao = CAPESCadastroFabricaDelegates.getInstance().criarExportacaoDelegate();
		Exportacao exportacao = new Exportacao();
		exportacao.setDataMovimento(dataMovimento);
		exportacao.setDestino(destino);
		exportacao.setNumeroArquivo(sequencialArquivo);
		delegateExportacao.incluir(exportacao);
		return exportacao;
	}

	/**
	 * Altera a exportacao
	 * 
	 * @param exportacao
	 * @throws BancoobException
	 */
	public void alterarExportacao(Exportacao exportacao) throws BancoobException {
		ExportacaoDelegate delegateExportacao = CAPESCadastroFabricaDelegates.getInstance().criarExportacaoDelegate();
		delegateExportacao.alterar(exportacao);
	}

	/**
	 * Obtem o proximo numero de linha para os dados de arquivos
	 * 
	 * @param exportacao
	 * @return
	 * @throws BancoobException
	 */
	public Integer obterProximoNumeroLinha(Exportacao exportacao) throws BancoobException {
		DadosArquivoExportacaoDelegate dadosArquivoExportacaoDelegate = CAPESCadastroFabricaDelegates.getInstance().criarDadosArquivoExportacaoDelegate();
		return dadosArquivoExportacaoDelegate.obterProximoNumeroLinha(exportacao);
	}

	/**
	 * Cria o registro de cabecalho do arquivo
	 * 
	 * @param exportacao
	 *            o registro de exportacao
	 * @param metadados
	 *            os metadados do arquivo
	 * @param dataMovimento
	 *            a data do movimento
	 * 
	 * @return o registro de cabecalho
	 * @see RegistroHeaderVO
	 * @see Exportacao
	 * @see Arquivo
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Integer criarCabecalho(Exportacao exportacao, Arquivo metadados, final DateTimeDB dataMovimento, Integer numeroLinha) throws BancoobException {
		SCIIntegracaoDelegate sci = CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();
		InstituicaoVO sicoob = sci.obterInformacoesSicoob();
		RegistroHeaderVO cabecalho = new RegistroHeaderVO(sicoob, dataMovimento, "SICOOB - CAPES - ATUALIZAÇÃO CADASTRAL", exportacao.getNumeroArquivo().toString(), 'E');
		cabecalho.setNumeroLinha(numeroLinha);

		DadosArquivoExportacaoDelegate delegate = CAPESCadastroFabricaDelegates.getInstance().criarDadosArquivoExportacaoDelegate();
		String linha = CAPESManipuladorArquivoAbstract.gerarLinha(cabecalho, metadados);
		delegate.incluir(new DadosArquivoExportacao(exportacao, numeroLinha++, linha));

		return numeroLinha;
	}

	/**
	 * Cria o registro de rodape do arquivo
	 * 
	 * @param exportacao
	 *            o registro de exportacao
	 * @param metadados
	 *            os metadados do arquivo
	 * @param numeroLinha
	 *            o numero da linha
	 * 
	 * @return o registro de rodape
	 * @see RegistroTrailerVO
	 * @see Exportacao
	 * @see Arquivo
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public RegistroTrailerVO criarRodape(Exportacao exportacao, Arquivo metadados, Integer numeroLinha) throws BancoobException {
		RegistroTrailerVO trailer = new RegistroTrailerVO();
		trailer.setNumeroLinha(numeroLinha);

		CAPESCadastroFabricaDelegates fabrica = CAPESCadastroFabricaDelegates.getInstance();
		DadosArquivoExportacaoDelegate delegate = fabrica.criarDadosArquivoExportacaoDelegate();
		String linha = CAPESManipuladorArquivoAbstract.gerarLinha(trailer, metadados);
		delegate.incluir(new DadosArquivoExportacao(exportacao, numeroLinha, linha));
		return trailer;
	}

	/**
	 * Gera os registros por tipo de informacao.
	 * 
	 * @param destino
	 * @param dataMovimento
	 * @param exportacao
	 * @param metadados
	 * @param numeroLinha
	 * @return
	 * @throws BancoobException
	 */
	private Integer gerarRegistrosPorTipo(List<TipoInformacao> tiposInformacao, final DateTimeDB dataMovimento, Exportacao exportacao, Arquivo metadados, Integer numeroLinha) throws BancoobException {
		// gera os dados da exportacao para cada tipo de informacao selecionado para o destino.
		for (TipoInformacao tipoInformacao : tiposInformacao) {
			TipoInformacaoEnum tipoEnum = TipoInformacaoEnum.valueOf(tipoInformacao.getCodigo());

			// verifica se existe um tipoInformacao para aquele codigo e ignora o tipo de pessoa, porque o mesmo ja foi gerado acima.
			if (tipoEnum != null && !TipoInformacaoEnum.PESSOA.equals(tipoEnum)) {
				List<RegistroDadosArquivoBaseVO> dados = dao.pesquisarDadosExportacao(tipoEnum, dataMovimento);
				numeroLinha = servicoLocal.gerarDadosArquivo(exportacao, metadados, numeroLinha, dados);
			}
		}
		return numeroLinha;
	}

	/**
	 * Gera a data do movimento (D - 1)s
	 * 
	 * @return a data do movimento
	 */
	private DateTimeDB gerarDataMovimento() {
		Calendar temp = DateUtils.truncate(Calendar.getInstance(), Calendar.DATE);
		temp.add(Calendar.DATE, -1);
		return new DateTimeDB(temp.getTimeInMillis());
	}

	/**
	 * Obtém o próximo número disponível para o sequencial do arquivo.
	 * 
	 * @return Short o número sequencial.
	 */
	private Short obterSequencialArquivo(DestinoExportacao destino) throws BancoobException {
		ExportacaoDelegate exportacaoDelegate = CAPESCadastroFabricaDelegates.getInstance().criarExportacaoDelegate();
		return exportacaoDelegate.obterNumeroSequencial(destino);
	}

	/**
	 * Grava os registros na tabela de dados arquivo.
	 * 
	 * @param exportacao
	 * @param metadadosArquivo
	 * @param numeroLinha
	 * @param registros
	 * @throws BancoobException
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Integer gerarDadosArquivo(final Exportacao exportacao, Arquivo metadadosArquivo, Integer numeroLinha, List<RegistroDadosArquivoBaseVO> registros) throws BancoobException {
		CAPESCadastroFabricaDelegates fabrica = CAPESCadastroFabricaDelegates.getInstance();
		DadosArquivoExportacaoDelegate delegate = fabrica.criarDadosArquivoExportacaoDelegate();
		List<DadosArquivoExportacao> listaDadosArquivo = new ArrayList<DadosArquivoExportacao>();

		// percorre a lista de registros
		for (int i = 0; i < registros.size(); i++) {
			RegistroArquivoBaseVO registro = registros.get(i);
			registro.setNumeroLinha(numeroLinha);
			String linha = CAPESManipuladorArquivoAbstract.gerarLinha(registro, metadadosArquivo);
			listaDadosArquivo.add(new DadosArquivoExportacao(exportacao, numeroLinha++, linha));

			// para cada 5 mil linhas geradas, faz a inclusao em batch
			if (listaDadosArquivo.size() == QUANTIDADE_INSERCAO_EM_LOTE) {
				delegate.incluir(listaDadosArquivo);
				listaDadosArquivo.clear();
			}
		}

		// inclui os dados restantes.
		delegate.incluir(listaDadosArquivo);
		return numeroLinha;
	}

	/**
	 * Recupera lista de {@code DestinoExportacao} que estejam ativos ( {@link DestinoExportacao#getAtivo()} == {@code true})
	 * 
	 * @return lista com os destinos ativos ou uma lista vazia, caso não haja nenhum destino ativo
	 * @see DestinoExportacao
	 */
	private List<DestinoExportacao> recuperarDestinosAtivos() throws BancoobException {
		CAPESCadastroFabricaDelegates fabrica = CAPESCadastroFabricaDelegates.getInstance();
		DestinoExportacaoDelegate delegate = fabrica.criarDestinoExportacaoDelegate();

		DestinoExportacao filtro = new DestinoExportacao();
		filtro.setAtivo(Boolean.TRUE);

		ConsultaDto<DestinoExportacao> criterios = new ConsultaDto<DestinoExportacao>();
		criterios.setFiltro(filtro);
		return delegate.pesquisar(criterios).getResultado();
	}

}