package br.com.sicoob.capes.api.persistencia.dao.impl;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.api.negocio.vo.NucleoVO;
import br.com.sicoob.capes.api.persistencia.dao.NucleoDAO;

/**
 * A Classe NucleoDAOImpl.
 */
public class NucleoDAOImpl extends CAPESApiDAO<NucleoVO> implements NucleoDAO {

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<NucleoVO> listarNucleos(Integer idInstituicao) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando("LISTAR_NUCLEOS");
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.configurar();

			return criarQuery(comando).getResultList();
		} finally {
			fecharComando(comando);
		}
	}
}