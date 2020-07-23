/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.util.List;

import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.negocio.entidades.vigente.InformacaoProfissional;
import br.com.sicoob.capes.cadastro.persistencia.dao.InformacaoProfissionalDAO;

/**
 * @author Erico.Junior
 * 
 */
public class InformacaoProfissionalDAOImpl extends
		CAPESCadastroCrudDao<InformacaoProfissional> implements
		InformacaoProfissionalDAO {

	/** A constante NOME_COMANDO_CONSULTAR_POR_MATRICULA_EMPREGADOR. */
	private static final String NOME_COMANDO_CONSULTAR_POR_MATRICULA_EMPREGADOR = 
			"CONSULTAR_INFORMACOES_PROFISSIONAIS_POR_MATRICULA_EMPREGADOR";
	
	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_INFORMACOES_PROFISSIONAIS";

	/**
	 * Instancia um novo InformacaoProfissionalDAOImpl.
	 */
	public InformacaoProfissionalDAOImpl() {
		super(InformacaoProfissional.class, NOME_COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<InformacaoProfissional> listarPorMatriculaEmpregador(
			InformacaoProfissional informacao) {
		ConsultaDto<InformacaoProfissional> criterios = new ConsultaDto<InformacaoProfissional>();
		criterios.setFiltro(informacao);
		return pesquisarLista(InformacaoProfissional.class, criterios, 
				NOME_COMANDO_CONSULTAR_POR_MATRICULA_EMPREGADOR);
	}

}
