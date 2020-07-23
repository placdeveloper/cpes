package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.negocio.entidades.vigente.Certidao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.cadastro.persistencia.dao.CertidaoDAO;

/**
 * Classe que implementa a persistencia de Certidao.
 *
 * @author Juan.Damasceno
 *
 */
public class CertidaoDAOImpl extends EntidadeCadastroDao<Certidao> implements CertidaoDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_CERTIDAO_POR_PESSOA";
	
	/** A constante LISTAR_VENCIDAS. */
	private static final String LISTAR_VENCIDAS = "LISTAR_CERTIDOES_VENCIDAS";
	
	/**
	 * Instancia um novo CertidaoDAOImpl.
	 */
	public CertidaoDAOImpl() {
		super(Certidao.class, NOME_COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Certidao> listarPorPessoa(PessoaCompartilhamento pessoa) throws BancoobException {
		
		Certidao certidao = new Certidao();
		certidao.setPessoa(pessoa);
		
		ConsultaDto<Certidao> consultaDto = new ConsultaDto<Certidao>();
		consultaDto.setFiltro(certidao);
		
		return listar(consultaDto);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Certidao> listarVencidas(ConsultaDto<Certidao> criterios)
			throws BancoobException {
		return pesquisarLista(getClasse(), criterios, LISTAR_VENCIDAS);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(Certidao objeto) throws BancoobException {
		try {
			getEntityManager().merge(objeto);
			getEntityManager().flush();
		} catch (IllegalArgumentException e) {
			throw new BancoobException("msg.erro.alterar.nao.existe", e);
		}
	}
}