package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Hibernate;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.cadastro.negocio.vo.ProprietarioBemVO;
import br.com.sicoob.capes.cadastro.negocio.vo.ValoresBensVO;
import br.com.sicoob.capes.cadastro.persistencia.dao.BemDAO;
import br.com.sicoob.capes.comum.negocio.enums.TipoClassificacaoBemEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoLocalizacaoBemImovelEnum;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.bem.Bem;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovel;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovelAvaliacao;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovelAvaliacaoRural;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovelAvaliacaoUrbano;
import br.com.sicoob.capes.negocio.entidades.bem.BemImovelTipoRelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.bem.BemMovel;
import br.com.sicoob.capes.negocio.entidades.bem.BemMovelAvaliacao;
import br.com.sicoob.capes.negocio.entidades.bem.BemPessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.bem.TipoOnusBem;

/**
 * Dao base para as classes que herdam de {@link Bem}.
 * 
 * @param <T>
 *            A classe que herda de {@link Bem}
 * 
 * @author bruno.carneiro
 */
public class BemDAOImpl extends EntidadeCadastroDao<Bem> implements BemDAO {

	/**
	 * O Construtor padr�o da DAO.
	 * 
	 * @param classe
	 * @param nomeComandoPesquisar
	 */
	public BemDAOImpl() {
		super(Bem.class, "");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Bem obter(Serializable chave) throws BancoobException {
		Bem bem = super.obter(chave);
		
		if(bem != null) {
			Hibernate.initialize(bem.getProprietarios());
			
			if(bem instanceof BemImovel) {
				BemImovel bemImovel = (BemImovel) bem;
				Hibernate.initialize(bemImovel.getPessoaCompartilhamentoCartorio());
				Hibernate.initialize(bemImovel.getRelacionamentoPessoas());
			}
		}
		
		return bem;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(Bem objeto) throws BancoobException {
		boolean mudouTipo = verificarMudancaTipo(objeto);

		if (mudouTipo) {
			alterarTipo(objeto);
		} else {
			realizarAlteracao(objeto);
		}
	}
	
	private void alterarTipo(Bem objeto) throws BancoobException {
		// Remove o objeto da sess�o
		removerObjetoSessao(objeto);
		
		// Obt�m o objeto do banco
		Bem bem = obter(objeto.getId());
		
		// Remove o objeto da sess�o
		removerObjetoSessao(bem);

		// Adiciona ou remove o registro de avalia��o.
		tratarAvaliacao(objeto, bem);
		
		// Obt�m o bem com o tipo certo.
		Bem bemAvaliacao = obter(objeto.getId());
		
		// Remove o objeto da sess�o
		removerObjetoSessao(bemAvaliacao);

		// Copia as informa��es para o novo objeto.
		ReflexaoUtils.copiarPropriedades(bemAvaliacao, objeto);

		// Realiza as altera��es
		realizarAlteracao(bemAvaliacao);
	}
	
	private boolean verificarMudancaTipo(Bem objeto) throws BancoobException {
		// Obt�m o objeto do banco
		Bem bem = obter(objeto.getId());

		// Se o tipo mudou, de Im�vel/m�vel para avalia��o devemos inserir o registro na tabela de avalia��o
		// ap�s a altera��o, pois o hibernate identifica como uma nova entidade, caso a mesma tenha sido preenchida.
		return !objeto.getClass().equals(bem.getClass());
	}
	
	private void realizarAlteracao(Bem objeto) throws BancoobException {
		// Realiza a altera��o do objeto
		super.alterar(objeto);

		// Envia as altera��es para o banco.
		getEntityManager().flush();
	}

	/**
	 * Substitui a cole��o de propriet�rios do bem, independente se a lista � a mesma. 
	 * <br/>
	 * O m�todo foi sobreescrito retirando a compara��o das listas, pois nesse
	 * caso, n�o saber�amos a hora de desmarcar um
	 * {@link BemPessoaCompartilhamento}.
	 * 
	 * @param destino
	 * @param origem
	 * @param colecoes
	 */
	@SuppressWarnings("unchecked")
	public void substituirColecoesPersistentesBemPessoaCompartilhamento(Bem destino, Bem origem, String... colecoes) {
		Collection<CAPESEntidade<?>> colecaoDestino, colecaoOrigem = null;
		for (String lista : colecoes) {
			colecaoDestino = (Collection<CAPESEntidade<?>>) ReflexaoUtils.getValorAtributo(destino, lista);
			colecaoOrigem = (Collection<CAPESEntidade<?>>) ReflexaoUtils.getValorAtributo(origem, lista);
			if (CollectionUtils.isNotEmpty(colecaoDestino) && CollectionUtils.isEmpty(colecaoOrigem)) {
				substituirColecaoPersistente(colecaoDestino, CollectionUtils.EMPTY_COLLECTION);
			} else if (CollectionUtils.isNotEmpty(colecaoOrigem)) {
				substituirColecaoPersistente(colecaoDestino, colecaoOrigem);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void marcarEmAlteracao(Bem objeto, Integer idInstituicaoAtualizacao) throws BancoobException {
		marcarEmAlteracao("CLI.BEM", "IDBEM", objeto.getId(), idInstituicaoAtualizacao);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ProprietarioBemVO> obterProprietariosBem(Long idBem) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando("OBTER_PROPRIETARIOS_BEM");
			comando.adicionarVariavel("idBem", idBem);
			comando.configurar();

			return criarQuery(comando).getResultList();
		} finally {
			fecharComando(comando);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ProprietarioBemVO> obterParticipantesBemImovel(Long idBem) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando("OBTER_PARTICIPANTES_BEM_IMOVEL");
			comando.adicionarVariavel("idBem", idBem);
			comando.configurar();

			return criarQuery(comando).getResultList();
		} finally {
			fecharComando(comando);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ValoresBensVO obterValoresBensPessoa(Long idPessoaCompartilhamento) throws BancoobException {
		Connection conx = null;
		ResultSet rset = null;
		Comando comando = null;

		ValoresBensVO retorno = null;

		try {
			conx = estabelecerConexao();
			comando = getComando("OBTER_VALORES_BEM_PESSOA");
			comando.adicionarVariavel("idPessoaCompartilhamento", idPessoaCompartilhamento);
			comando.configurar();

			rset = comando.executarConsulta(conx);

			if (rset.next()) {
				retorno = new ValoresBensVO();
				retorno.setValorTotalAvaliado(rset.getBigDecimal("TOTAL_AVALIADO"));
				retorno.setValorTotalDeclarado(rset.getBigDecimal("TOTAL_DECLARADO"));
				retorno.setValorTotalImovelAvaliado(rset.getBigDecimal("TOTAL_IMOVEL_AVALIADO"));
				retorno.setValorTotalImovelDeclarado(rset.getBigDecimal("TOTAL_IMOVEL_DECLARADO"));
				retorno.setValorTotalMovelAvaliado(rset.getBigDecimal("TOTAL_MOVEL_AVALIADO"));
				retorno.setValorTotalMovelDeclarado(rset.getBigDecimal("TOTAL_MOVEL_DECLARADO"));
			}
		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		} finally {
			fecharResultSet(rset);
			fecharConexao(conx);
			fecharComando(comando);
		}

		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long obterIdBemSemPatrimonio(Short codigoCompartilhamentoCadastro) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando("OBTER_ID_BEM_SEM_PATRIMONIO");
			comando.adicionarVariavel("codigoCompartilhamentoCadastro", codigoCompartilhamentoCadastro);
			comando.configurar();

			return (Long) criarQuery(comando).getSingleResult();
		} finally {
			fecharComando(comando);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long obterIdBemRecusouSeInformar(Short codigoCompartilhamentoCadastro) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando("OBTER_ID_BEM_RECUSOU_SE_INFORMAR");
			comando.adicionarVariavel("codigoCompartilhamentoCadastro", codigoCompartilhamentoCadastro);
			comando.configurar();

			return (Long) criarQuery(comando).getSingleResult();
		} finally {
			fecharComando(comando);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Short obterTipoClassificacaoBem(Long idBem) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando("OBTER_TIPO_CLASSIFICACAO_BEM");
			comando.adicionarVariavel("idBem", idBem);
			comando.configurar();

			return (Short) criarQuery(comando).getSingleResult();
		} finally {
			fecharComando(comando);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<BemImovelTipoRelacionamentoPessoa> obterRelacionamentosBemImovel(Long idBem) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando("OBTER_RELACIONAMENTOS_BEM_IMOVEL");
			comando.adicionarVariavel("idBem", idBem);
			comando.configurar();

			return criarQuery(comando).getResultList();
		} finally {
			fecharComando(comando);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConsultaDto<Bem> pesquisar(ConsultaDto<Bem> criterios) throws BancoobException {
		String nomePesquisa = null;
		String nomePesquisaContador = null;
		Bem filtro = (Bem) criterios.getFiltro();
		if (filtro instanceof BemImovel) {
			nomePesquisa = "PESQUISA_BEM_IMOVEL_POR_FILTRO";
			nomePesquisaContador = "PESQUISA_CONTADOR_BEM_IMOVEL_POR_FILTRO";
		} else if (filtro instanceof BemMovel) {
			nomePesquisa = "PESQUISA_BEM_MOVEL_POR_FILTRO";
			nomePesquisaContador = "PESQUISA_CONTADOR_BEM_MOVEL_POR_FILTRO";
		}
		return pesquisar(Bem.class, criterios, nomePesquisa, nomePesquisaContador);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<BemImovel> obterImoveisRurais(Long idPessoaCompartilhamento) throws BancoobException {
		Comando comando = null;
		Connection conx = null;
		ResultSet rset = null;

		List<BemImovel> retorno = new ArrayList<BemImovel>();
		try {
			comando = getComando("OBTER_BENS_IMOVEIS_RURAIS");
			comando.adicionarVariavel("idPessoaCompartilhamento", idPessoaCompartilhamento);
			comando.configurar();

			conx = estabelecerConexao();
			rset = comando.executarConsulta(conx);

			while (rset.next()) {
				BemImovel bemImovel = new BemImovel();
				bemImovel.setId(rset.getLong("IDBEM"));
				bemImovel.setDescricao(rset.getString("DESCBEM"));
				bemImovel.setDenominacao(rset.getString("DESCDENOMINACAO"));
				retorno.add(bemImovel);
			}

			return retorno;
		} catch (SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			fecharResultSet(rset);
			fecharConexao(conx);
			fecharComando(comando);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean verificarExistenciaBemInterno(Long idBem, Long idPessoaCompartilhamento) throws BancoobException {
		Comando comando = null;
		Connection conx = null;
		ResultSet rset = null;

		Long retorno = null;

		try {
			comando = getComando("VERIFICAR_EXISTENCIA_BEM_INTERNO");
			comando.adicionarVariavel("idBem", idBem);
			comando.adicionarVariavel("idPessoaCompartilhamento", idPessoaCompartilhamento);
			comando.configurar();

			conx = estabelecerConexao();
			rset = comando.executarConsulta(conx);

			if (rset.next()) {
				retorno = rset.getLong("IDBEM");
			}

			return retorno != null ? true : false;
		} catch (SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			fecharResultSet(rset);
			fecharConexao(conx);
			fecharComando(comando);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long obterIdBemInterno(Long idPessoaCompartilhamento) throws BancoobException {
		Comando comando = null;
		Connection conx = null;
		ResultSet rset = null;

		Long retorno = null;

		try {
			comando = getComando("VERIFICAR_EXISTENCIA_BEM_INTERNO");
			comando.adicionarVariavel("idPessoaCompartilhamento", idPessoaCompartilhamento);
			comando.configurar();

			conx = estabelecerConexao();
			rset = comando.executarConsulta(conx);

			if (rset.next()) {
				retorno = rset.getLong("IDBEM");
			}

			return retorno;
		} catch (SQLException e) {
			throw new PersistenciaException(e);
		} finally {
			fecharResultSet(rset);
			fecharConexao(conx);
			fecharComando(comando);
		}
	}
	
	/**
	 * Verifica se dever� remover ou adicionar o registro na tabela de
	 * avalia��o.
	 * 
	 * @param objeto
	 * @param bem
	 * @throws BancoobException
	 */
	private void tratarAvaliacao(Bem objeto, Bem bem) throws BancoobException {
		TipoClassificacaoBemEnum tipoClassificacaoBemEnum = TipoClassificacaoBemEnum.obterPorCodigo(bem.getTipoClassificacaoBem().getCodigo());

		boolean removerAvaliacao = false;
		if (TipoClassificacaoBemEnum.BEM_IMOVEL.equals(tipoClassificacaoBemEnum)) {
			if (bem instanceof BemImovelAvaliacao && objeto instanceof BemImovel) {
				removerAvaliacao = true;
			}
		} else if (TipoClassificacaoBemEnum.BEM_MOVEL.equals(tipoClassificacaoBemEnum)) {
			if (bem instanceof BemMovelAvaliacao && objeto instanceof BemMovel) {
				removerAvaliacao = true;
			}
		}

		if (removerAvaliacao) {
			removerAvaliacao(objeto);
		} else {
			inserirAvaliacao(objeto);
		}
	}
	
	/**
	 * M�todo que faz a inclus�o da avalia��o.
	 * 
	 * O m�todo foi feito por uma limita��o do JPA onde, ao trocar o tipo da
	 * entidade, ele n�o mant�m o id, achando que � uma nova entidade. Por isso,
	 * � feito o merge da entidade anterior e inserindo o novo tipo.
	 * 
	 * @param bem
	 * @throws BancoobException
	 */
	private void inserirAvaliacao(Bem bem) throws BancoobException {
		TipoClassificacaoBemEnum tipoClassificacao = TipoClassificacaoBemEnum.obterPorCodigo(bem.getTipoClassificacaoBem().getCodigo());
		
		String nomeComando = null;
		Set<TipoOnusBem> tiposOnus = null;

		if (TipoClassificacaoBemEnum.BEM_IMOVEL.equals(tipoClassificacao)) {
			BemImovel bemImovel = (BemImovel) bem;
			inserirBemImovelAvaliacao((BemImovelAvaliacao) bem);

			nomeComando = "INCLUIR_TIPO_ONUS_BEM_IMOVEL_AVALIACAO";
			tiposOnus = ((BemImovelAvaliacao) bem).getTiposOnus();

			TipoLocalizacaoBemImovelEnum tipoLocalizacao = TipoLocalizacaoBemImovelEnum.obterPorCodigo(bemImovel.getTipoLocalizacaoBem().getCodigo());
			if (TipoLocalizacaoBemImovelEnum.RURAL.equals(tipoLocalizacao)) {
				inserirBemImovelAvaliacaoRural((BemImovelAvaliacaoRural) bem);
			} else if (TipoLocalizacaoBemImovelEnum.URBANO.equals(tipoLocalizacao)) {
				inserirBemImovelAvaliacaoUrbano((BemImovelAvaliacaoUrbano) bem);
			}
		} else if (TipoClassificacaoBemEnum.BEM_MOVEL.equals(tipoClassificacao)) {
			inserirBemMovelAvaliacao((BemMovelAvaliacao) bem);

			nomeComando = "INCLUIR_TIPO_ONUS_BEM_MOVEL_AVALIACAO";
			tiposOnus = ((BemMovelAvaliacao) bem).getTiposOnus();
		}

		if (tiposOnus != null) {
			for (TipoOnusBem tipo : tiposOnus) {
				inserirTipoOnusAvaliacao(nomeComando, tipo.getCodigo(), bem.getId());
			}
		}
		
		// Envia as altera��es para o banco.
		getEntityManager().flush();
	}
	
	/**
	 * Remove o registro da tabela de avalia��o
	 * @param objeto
	 * @throws BancoobException
	 */
	private void removerAvaliacao(Bem bem) throws BancoobException {
		TipoClassificacaoBemEnum tipoClassificacaoBemEnum = TipoClassificacaoBemEnum.obterPorCodigo(bem.getTipoClassificacaoBem().getCodigo());
		if (TipoClassificacaoBemEnum.BEM_IMOVEL.equals(tipoClassificacaoBemEnum)) {
			removerTipoOnusBemImovelAvaliacao(bem);
			BemImovel bemImovel = (BemImovel) bem;
			TipoLocalizacaoBemImovelEnum tipoLocalizacao = TipoLocalizacaoBemImovelEnum.obterPorCodigo(bemImovel.getTipoLocalizacaoBem().getCodigo());
			if (TipoLocalizacaoBemImovelEnum.RURAL.equals(tipoLocalizacao)) {
				removerBemImovelAvaliacaoRural(bem);
			} else if (TipoLocalizacaoBemImovelEnum.URBANO.equals(tipoLocalizacao)) {
				removerBemImovelAvaliacaoUrbano(bem);
			}
			removerBemImovelAvaliacao(bem);
		} else if (TipoClassificacaoBemEnum.BEM_MOVEL.equals(tipoClassificacaoBemEnum)) {
			removerTipoOnusBemMovelAvaliacao(bem);
			removerBemMovelAvaliacao(bem);
		}
		
		// Envia as altera��es para o banco.
		getEntityManager().flush();
	}

	/**
	 * Inclui o bem m�vel avalia��o
	 * 
	 * @param bemAvaliacao
	 * @throws BancoobException
	 */
	private void inserirBemMovelAvaliacao(BemMovelAvaliacao bemAvaliacao) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando("INCLUIR_BEM_MOVEL_AVALIACAO");
			comando.configurar();

			Query query = getEntityManager().createNativeQuery(comando.getSqlEmUso());
			query.setParameter(1, bemAvaliacao.getId());
			query.setParameter(2, bemAvaliacao.getValorCompraVenda());
			query.setParameter(3, bemAvaliacao.getDataCompraVenda());
			query.setParameter(4, bemAvaliacao.getValorAvaliacao());
			query.setParameter(5, bemAvaliacao.getDataAvaliacao());
			query.setParameter(6, bemAvaliacao.getIdPessoaCompartilhamentoAvaliador());
			query.setParameter(7, bemAvaliacao.getProcessoAquisicao());
			query.setParameter(8, bemAvaliacao.getTipoEstadoConservacao() != null ? bemAvaliacao.getTipoEstadoConservacao().getCodigo() : null);
			query.executeUpdate();
		} finally {
			fecharComando(comando);
		}
	}

	/**
	 * Inclui o bem im�vel avalia��o.
	 * 
	 * @param bem
	 * @throws BancoobException
	 */
	private void inserirBemImovelAvaliacao(BemImovelAvaliacao bem) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando("INCLUIR_BEM_IMOVEL_AVALIACAO");
			comando.configurar();

			Query query = getEntityManager().createNativeQuery(comando.getSqlEmUso());
			query.setParameter(1, bem.getId());
			query.setParameter(2, bem.getValorCompraVenda());
			query.setParameter(3, bem.getDataCompraVenda());
			query.setParameter(4, bem.getValorAvaliacao());
			query.setParameter(5, bem.getDataAvaliacao());
			query.setParameter(6, bem.getIdPessoaCompartilhamentoAvaliador());
			query.setParameter(7, bem.getProcessoAquisicao());
			query.executeUpdate();
		} finally {
			fecharComando(comando);
		}
	}

	/**
	 * Inclui o bem im�vel avalia��o rural
	 * 
	 * @param bem
	 * @throws BancoobException
	 */
	private void inserirBemImovelAvaliacaoRural(BemImovelAvaliacaoRural bem) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando("INCLUIR_BEM_IMOVEL_AVALIACAO_RURAL");
			comando.configurar();

			Query query = getEntityManager().createNativeQuery(comando.getSqlEmUso());
			query.setParameter(1, bem.getId());
			query.setParameter(2, bem.getBenfeitoria());
			query.setParameter(3, bem.getValorBenfeitoria());
			query.executeUpdate();
		} finally {
			fecharComando(comando);
		}
	}

	/**
	 * Incluir o bem im�vel avalia��o urbano.
	 * 
	 * @param bem
	 * @throws BancoobException
	 */
	private void inserirBemImovelAvaliacaoUrbano(BemImovelAvaliacaoUrbano bem) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando("INCLUIR_BEM_IMOVEL_AVALIACAO_URBANO");
			comando.configurar();

			Query query = getEntityManager().createNativeQuery(comando.getSqlEmUso());
			query.setParameter(1, bem.getId());
			query.setParameter(2, bem.getTipoImplantacaoBemImovel() != null ? bem.getTipoImplantacaoBemImovel() : null);
			query.setParameter(3, bem.getTipoEstadoConservacao() != null ? bem.getTipoEstadoConservacao() : null);
			query.setParameter(4, bem.getTipoPadraoAcabamentoBemImovel() != null ? bem.getTipoPadraoAcabamentoBemImovel() : null);
			query.setParameter(5, bem.getTipoServicoCondominialBemImovel() != null ? bem.getTipoServicoCondominialBemImovel() : null);
			query.setParameter(6, bem.getAreaPrivativa());
			query.setParameter(7, bem.getQuantidadeDormitorios());
			query.setParameter(8, bem.getQuantidadeVagasGaragem());
			query.setParameter(9, bem.getAreaTerreno());
			query.setParameter(10, bem.getAreaTestada());
			query.executeUpdate();
		} finally {
			fecharComando(comando);
		}
	}
	
	/**
	 * Incluir o tipo de �nus do bem im�vel.
	 * 
	 * @param bem
	 * @throws BancoobException
	 */
	private void inserirTipoOnusAvaliacao(String nomeComando, Short codigoTipoOnus, Long idBem) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando(nomeComando);
			comando.configurar();

			Query query = getEntityManager().createNativeQuery(comando.getSqlEmUso());
			query.setParameter(1, codigoTipoOnus);
			query.setParameter(2, idBem);
			query.executeUpdate();
		} finally {
			fecharComando(comando);
		}
	}
	
	private void removerBemImovelAvaliacao(Bem bem) {
		Comando comando = null;
		try {
			comando = getComando("REMOVER_BEM_IMOVEL_AVALIACAO");
			comando.configurar();

			Query query = getEntityManager().createNativeQuery(comando.getSqlEmUso());
			query.setParameter(1, bem.getId());
			query.executeUpdate();
		} finally {
			fecharComando(comando);
		}
	}
	
	/**
	 * Remove o bem im�vel avalia��o rural
	 * 
	 * @param bem
	 */
	private void removerBemImovelAvaliacaoRural(Bem bem) {
		Comando comando = null;
		try {
			comando = getComando("REMOVER_BEM_IMOVEL_AVALIACAO_RURAL");
			comando.configurar();

			Query query = getEntityManager().createNativeQuery(comando.getSqlEmUso());
			query.setParameter(1, bem.getId());
			query.executeUpdate();
		} finally {
			fecharComando(comando);
		}
	}

	/**
	 * Remove o bem m�vel avalia��o urbano
	 * 
	 * @param bem
	 */
	private void removerBemImovelAvaliacaoUrbano(Bem bem) {
		Comando comando = null;
		try {
			comando = getComando("REMOVER_BEM_IMOVEL_AVALIACAO_URBANO");
			comando.configurar();

			Query query = getEntityManager().createNativeQuery(comando.getSqlEmUso());
			query.setParameter(1, bem.getId());
			query.executeUpdate();
		} finally {
			fecharComando(comando);
		}
	}
	
	/**
	 * Remove o bem m�vel avalia��o
	 * 
	 * @param bem
	 */
	private void removerBemMovelAvaliacao(Bem bem) {
		Comando comando = null;
		try {
			comando = getComando("REMOVER_BEM_MOVEL_AVALIACAO");
			comando.configurar();

			Query query = getEntityManager().createNativeQuery(comando.getSqlEmUso());
			query.setParameter(1, bem.getId());
			query.executeUpdate();
		} finally {
			fecharComando(comando);
		}
	}
	
	/**
	 * Remove os tipos de �nus do bem im�vel
	 * 
	 * @param bem
	 */
	private void removerTipoOnusBemImovelAvaliacao(Bem bem) {
		Comando comando = null;
		try {
			comando = getComando("REMOVER_TIPO_ONUS_BEM_IMOVEL_AVALIACAO");
			comando.configurar();

			Query query = getEntityManager().createNativeQuery(comando.getSqlEmUso());
			query.setParameter(1, bem.getId());
			query.executeUpdate();
		} finally {
			fecharComando(comando);
		}
	}
	
	/**
	 * Remove os tipos de �nus do bem m�vel
	 * 
	 * @param bem
	 */
	private void removerTipoOnusBemMovelAvaliacao(Bem bem) {
		Comando comando = null;
		try {
			comando = getComando("REMOVER_TIPO_ONUS_BEM_MOVEL_AVALIACAO");
			comando.configurar();

			Query query = getEntityManager().createNativeQuery(comando.getSqlEmUso());
			query.setParameter(1, bem.getId());
			query.executeUpdate();
		} finally {
			fecharComando(comando);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Bem> obterBensPorPessoaCompartilhamento(Long idPessoaCompartilhamento, Short tipoClassificacao) throws BancoobException {
		Comando comando = null;

		try {
			comando = getComando("PESQUISA_BENS_POR_ID_PESSOA_COMPARTILHAMENTO");
			comando.adicionarVariavel("idPessoaCompartilhamento", idPessoaCompartilhamento);
			comando.adicionarVariavel("tipoClassificacao", tipoClassificacao);
			comando.configurar();

			return criarQuery(comando).getResultList();

		} finally {
			fecharComando(comando);
		}

	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Bem> obterBensImoveisPorPessoaCompartilhamento(Long idPessoaCompartilhamento) throws BancoobException {
		Comando comando = null;

		try {
			comando = getComando("PESQUISA_BENS_IMOVEL_POR_ID_PESSOA_COMPARTILHAMENTO");
			comando.adicionarVariavel("idPessoaCompartilhamento", idPessoaCompartilhamento);
			comando.configurar();

			return criarQuery(comando).getResultList();

		} finally {
			fecharComando(comando);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean verificarBemEmGarantia(Long idBem) throws BancoobException {
		Connection conx = null;
		ResultSet rset = null;
		Comando comando = null;

		Integer retorno = 0;
		try {
			conx = estabelecerConexao();
			comando = getComando("VERIFICAR_BEM_EM_GARANTIA");
			comando.adicionarVariavel("idBem", idBem);
			comando.configurar();

			rset = comando.executarConsulta(conx);
			if (rset.next()) {
				retorno = rset.getInt("CONTADOR");
			}

		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		} finally {
			fecharResultSet(rset);
			fecharConexao(conx);
			fecharComando(comando);
		}

		return retorno > 0;
	}
	
	@Override
	public void inserirHistoricoDesbloqueioBem(Long idBem, String idUsuario) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando("INSERIR_HISTORICO_DESBLOQUEIO_BEM");
			comando.configurar();

			Query query = getEntityManager().createNativeQuery(comando.getSqlEmUso());
			query.setParameter(1, idUsuario);
			query.setParameter(2, idBem);
			query.executeUpdate();
		} finally {
			fecharComando(comando);
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> obterPessoaSemBensVinculados() throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando("PESQUISAR_PESSOAS_SEM_BEM");
			comando.configurar();
			
			Query query = getEntityManager().createNativeQuery(comando.getSqlEmUso());
			return query.getResultList();
			
		} finally {
			fecharComando(comando);
		}
	}
	@Override
	public Number quantidadePessoasSemBensVinculados() throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando("PESQUISAR_QUANTIDADE_PESSOAS_SEM_BEM");
			comando.configurar();
			
			Query query = getEntityManager().createNativeQuery(comando.getSqlEmUso());
			return (Number) query.getSingleResult();
			
		} finally {
			fecharComando(comando);
		}
	}

}