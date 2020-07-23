/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.bancoob.persistencia.excecao.TratamentoExcecaoPersistencia;
import br.com.sicoob.capes.cadastro.negocio.excecao.ClienteNaoEncontradoException;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoAnotacaoDAO;
import br.com.sicoob.capes.negocio.entidades.TipoAnotacao;

/**
 * Dao para os tipos de anotação.
 * 
 * @author Erico.Junior
 */
public class TipoAnotacaoDAOImpl extends CAPESCadastroCrudDao<TipoAnotacao> implements TipoAnotacaoDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISAR_TIPO_ANOTACAO";

	/**
	 * Construtor do DAO.
	 */
	public TipoAnotacaoDAOImpl() {
		super(TipoAnotacao.class, NOME_COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TipoAnotacao> listarTiposAnotacaoAtivos(ConsultaDto<TipoAnotacao> consultaDto) {
		return pesquisarListaEntidade(TipoAnotacao.class, consultaDto, "PESQUISA_TIPO_ANOTACAO_ATIVO_POR_CAPTURA");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoAnotacao incluir(TipoAnotacao objeto) throws BancoobException {
		TipoAnotacao tipoAnotacao = null;
		try {
			tipoAnotacao = super.incluir(objeto);
		} catch (PersistenceException e) {
			TratamentoExcecaoPersistencia.getInstance().tratarExcecaoPersistenciaInclusao(e);
		}
		return tipoAnotacao;
	}

	/**
	 * {@inheritDoc}
	 */
	public String obterSaidaTipoAnotacao(TipoAnotacao objeto) throws BancoobException {
		String saidaTipoAnotacao = "";
		Comando comando = getComando("OBTER_SAIDA_TIPO_ANOTACAO");
		comando.adicionarVariavel("criterio", objeto);
		comando.configurar();

		try {
			Query query = comando.criarQuery(getEntityManager());
			saidaTipoAnotacao = (String) query.getSingleResult();
		} catch (NoResultException e) {
			throw new ClienteNaoEncontradoException(e);
		} catch (PersistenceException e) {
			throw new PersistenciaException(e);
		} finally {
			fecharComando(comando);
		}

		return saidaTipoAnotacao;
	}
	
}