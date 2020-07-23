package br.com.sicoob.capes.api.persistencia.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.api.negocio.vo.ProdutorVO;
import br.com.sicoob.capes.api.negocio.vo.TipoBeneficiarioSicorVO;
import br.com.sicoob.capes.api.persistencia.dao.ProdutorDAO;

/**
 * A Classe ProdutorDAOImpl.
 */
public class ProdutorDAOImpl extends CAPESApiDAO<ProdutorVO> implements ProdutorDAO {

	/**
	 * Instancia um novo ProdutorDAOImpl.
	 */
	public ProdutorDAOImpl() {
		super("PESQUISAR_PRODUTOR", "PESQUISAR_QUANTIDADE_PRODUTOR");
	}

	/**
	 * {@inheritDoc}
	 */
	public ProdutorVO obterProdutor(ConsultaDto<ProdutorVO> criterios) throws BancoobException {
		Comando comando = getComando("OBTER_PRODUTOR");

		try {
			comando.adicionarVariavel(CRITERIOS, criterios);
			comando.configurar();

			return (ProdutorVO) criarQuery(comando).getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			fecharComando(comando);
		}
	}

	@SuppressWarnings("unchecked")
	public List<TipoBeneficiarioSicorVO> obterListaTipoBeneficiariosSicor() throws BancoobException {
		Comando comando = getComando("OBTER_TIPO_BENEFICIARIOS_SICOR");
		try {
			comando.configurar();
			return criarQuery(comando).getResultList();
		} catch (NoResultException e) {
			return null;
		} finally {
			fecharComando(comando);
		}
	}
}