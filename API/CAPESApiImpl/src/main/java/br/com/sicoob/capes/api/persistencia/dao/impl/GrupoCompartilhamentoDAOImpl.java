package br.com.sicoob.capes.api.persistencia.dao.impl;

import javax.persistence.NoResultException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.api.negocio.vo.GrupoCompartilhamentoVO;
import br.com.sicoob.capes.api.persistencia.dao.GrupoCompartilhamentoDAO;

public class GrupoCompartilhamentoDAOImpl extends CAPESApiDAO<GrupoCompartilhamentoVO> implements GrupoCompartilhamentoDAO {

	public GrupoCompartilhamentoDAOImpl() {
		super("PESQUISAR_GRUPO_COMPARTILHAMENTO");
	}

	public GrupoCompartilhamentoVO obterGrupoCompartilhamentoInstituicao(Integer idInstituicao) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando(getNomeComando());
			comando.adicionarVariavel(ID_INSTITUICAO, idInstituicao);
			comando.configurar();

			return (GrupoCompartilhamentoVO) criarQuery(comando).getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			fecharComando(comando);
		}
	}
}