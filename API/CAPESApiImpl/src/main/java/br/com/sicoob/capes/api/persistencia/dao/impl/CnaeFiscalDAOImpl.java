package br.com.sicoob.capes.api.persistencia.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.api.negocio.vo.CnaeFiscalVO;
import br.com.sicoob.capes.api.persistencia.dao.CnaeFiscalDAO;

public class CnaeFiscalDAOImpl extends CAPESApiDAO<CnaeFiscalVO> implements CnaeFiscalDAO {

	public CnaeFiscalDAOImpl(){
		super("OBTER_CODIGOS_CNAEFISCAL");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<CnaeFiscalVO> listar() throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando(getNomeComando());
			comando.configurar();
			return criarQuery(comando).getResultList();
		} finally {
			fecharComando(comando);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public CnaeFiscalVO obterCnaeFiscalPorCodigo(String codigoCnae) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando(getNomeComando());
			comando.adicionarVariavel("codigoCnae", codigoCnae);
			comando.configurar();

			return (CnaeFiscalVO) criarQuery(comando).getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			fecharComando(comando);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<CnaeFiscalVO> obterPorDescricao(String descricao) throws BancoobException {
		Comando comando = null;
		try {
			comando = getComando(getNomeComando());
			comando.adicionarVariavel("descricaoCnae", descricao);
			comando.configurar();

			return criarQuery(comando).getResultList();
		} catch (NoResultException e) {
			return null;
		} finally {
			fecharComando(comando);
		}
	}

}