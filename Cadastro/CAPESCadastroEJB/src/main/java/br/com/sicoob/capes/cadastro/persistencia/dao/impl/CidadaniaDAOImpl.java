package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.util.List;

import javax.persistence.Query;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.cadastro.persistencia.dao.CidadaniaDAO;
import br.com.sicoob.capes.negocio.entidades.Cidadania;

/**
 * A Classe CidadaniaDAOImpl.
 */
public class CidadaniaDAOImpl extends CAPESCadastroCrudDao<Cidadania> implements CidadaniaDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_CIDADANIA";

	/**
	 * Instancia um novo CidadaniaDAOImpl.
	 */
	public CidadaniaDAOImpl() {
		super(Cidadania.class, NOME_COMANDO_PESQUISAR);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public ConsultaDto<Cidadania> pesquisar(ConsultaDto<Cidadania> criterios) throws BancoobException {
		ConsultaDto<Cidadania> retorno = null;
		Comando comando = getComando(NOME_COMANDO_PESQUISAR);
		Cidadania filtro = (Cidadania) criterios.getFiltro();
		comando.adicionarVariavel("idPessoaCompartilhamento", filtro.getPk().getIdPessoaCompartilhamento());
		comando.configurar();

		try {
			Query query = comando.criarQuery(getEntityManager());
			List<Cidadania> resultList = query.getResultList();
			retorno = new ConsultaDto<Cidadania>();
			retorno.setResultado(resultList);
		} finally {
			fecharComando(comando);
		}

		return retorno;
	}

}
