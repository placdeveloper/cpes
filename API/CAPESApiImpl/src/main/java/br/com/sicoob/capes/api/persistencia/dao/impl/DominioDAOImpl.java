package br.com.sicoob.capes.api.persistencia.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.api.negocio.vo.DominioVO;
import br.com.sicoob.capes.api.persistencia.dao.DominioDAO;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoDominioEntidadeEnum;

/**
 * A Classe DominioDAOImpl.
 */
public class DominioDAOImpl extends CAPESApiDAO<DominioVO> implements DominioDAO {

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<DominioVO> obterDominioPorTipoDominio(TipoDominioEntidadeEnum tipoDominio) throws BancoobException {
		Comando comando = null;
		try {
			comando = new Comando();
			comando.setSql(criarHQLTipoDominio(tipoDominio));
			comando.configurar();

			return criarQuery(comando).getResultList();
		} catch (NoResultException e) {
			return null;
		} finally {
			fecharComando(comando);
		}
	}
	
	/**
	 * Criar hql tipo dominio.
	 *
	 * @param tipoDominio o valor de tipo dominio
	 * @return String
	 */
	private String criarHQLTipoDominio(TipoDominioEntidadeEnum tipoDominio) {
		if (tipoDominio != null) {
			String hqlRetorno = "SELECT new br.com.sicoob.capes.api.negocio.vo.DominioVO(#PROP_CODIGO# as codigo, #PROP_DESCRICAO# as descricao) FROM #ENTIDADE# ";
			
			String entidade = tipoDominio.getTipo().getCanonicalName();
			String codigo = tipoDominio.getPropriedadeCodigo();
			String descricao = tipoDominio.getPropriedadeDescricao();
			
			hqlRetorno = hqlRetorno.replaceAll("#PROP_CODIGO#", codigo);
			hqlRetorno = hqlRetorno.replaceAll("#PROP_DESCRICAO#", descricao);
			hqlRetorno = hqlRetorno.replaceAll("#ENTIDADE#", entidade);
			
			return hqlRetorno;
		}else {
			return null;
		}
	}

}