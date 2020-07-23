package br.com.sicoob.capes.api.persistencia.dao.impl;

import javax.persistence.NoResultException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.api.negocio.vo.InstituicaoResponsavelVO;
import br.com.sicoob.capes.api.persistencia.dao.InstituicaoResponsavelDAO;

/**
 * A Classe InstituicaoResponsavelDAOImpl.
 */
public class InstituicaoResponsavelDAOImpl extends CAPESApiDAO<InstituicaoResponsavelVO> implements InstituicaoResponsavelDAO {

	/**
	 * {@inheritDoc}
	 */
	public InstituicaoResponsavelVO obterInstituicaoResponsavelPorCpfCnpj(ConsultaDto<InstituicaoResponsavelVO> filtroConsulta) {
		Comando comando = null;
		try{
			comando = getComando("OBTER_RESPONSAVEL");
			comando.adicionarVariavel(CRITERIOS, filtroConsulta);
			comando.configurar();
			
			return (InstituicaoResponsavelVO) criarQuery(comando).getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			fecharComando(comando);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public InstituicaoResponsavelVO obterInstituicaoResponsavelPorCpfCnpj(String cpfCnpj) throws BancoobException {
		Comando comando = null;
		try{
			comando = getComando("OBTER_RESPONSAVEL_UNICO");
			comando.adicionarVariavel("cpfCnpj", cpfCnpj);
			comando.configurar();
			
			return (InstituicaoResponsavelVO) criarQuery(comando).getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			fecharComando(comando);
		}
	}

}