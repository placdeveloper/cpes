package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.io.Serializable;

import javax.persistence.EntityExistsException;

import org.hibernate.exception.ConstraintViolationException;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.excecao.ExclusaoDestinoException;
import br.com.sicoob.capes.cadastro.persistencia.dao.DestinoExportacaoDAO;
import br.com.sicoob.capes.negocio.entidades.DestinoExportacao;

/**
 * A Classe DestinoExportacaoDAOImpl.
 */
public class DestinoExportacaoDAOImpl extends CAPESCadastroCrudDao<DestinoExportacao> implements DestinoExportacaoDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISAR_DESTINO_EXPORTACAO";

	/**
	 * Instancia um novo DestinoExportacaoDAOImpl.
	 */
	public DestinoExportacaoDAOImpl() {
		super(DestinoExportacao.class, NOME_COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(Serializable chave) throws BancoobException {
		try {
			super.excluir(chave);
			getEntityManager().flush();
		} catch (ConstraintViolationException e) {
			throw new ExclusaoDestinoException(e);
		} catch (EntityExistsException e) {
			throw new ExclusaoDestinoException(e);
		}
	}

}