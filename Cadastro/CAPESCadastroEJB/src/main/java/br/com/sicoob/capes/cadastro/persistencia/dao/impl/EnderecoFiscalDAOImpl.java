package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.util.List;

import javax.persistence.Query;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.cadastro.persistencia.dao.EnderecoFiscalDAO;
import br.com.sicoob.capes.negocio.entidades.EnderecoFiscal;

/**
 * A Classe EnderecoFiscalDAOImpl.
 */
public class EnderecoFiscalDAOImpl extends CAPESCadastroCrudDao<EnderecoFiscal> implements EnderecoFiscalDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_ENDERECO_FISCAL";

	/**
	 * Instancia um novo EnderecoFiscalDAOImpl.
	 */
	public EnderecoFiscalDAOImpl() {
		super(EnderecoFiscal.class, NOME_COMANDO_PESQUISAR);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public ConsultaDto<EnderecoFiscal> pesquisar(ConsultaDto<EnderecoFiscal> criterios) throws BancoobException {
		ConsultaDto<EnderecoFiscal> retorno = null;
		Comando comando = getComando(NOME_COMANDO_PESQUISAR);
		EnderecoFiscal filtro = (EnderecoFiscal) criterios.getFiltro();
		comando.adicionarVariavel("idPessoaCompartilhamento", filtro.getPk().getIdPessoaCompartilhamento());
		comando.configurar();

		try {
			Query query = comando.criarQuery(getEntityManager());
			List<EnderecoFiscal> resultList = query.getResultList();
			retorno = new ConsultaDto<EnderecoFiscal>();
			retorno.setResultado(resultList);
		} finally {
			fecharComando(comando);
		}

		return retorno;
	}

}
