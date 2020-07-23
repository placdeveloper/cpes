package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.capes.cadastro.negocio.dto.FuncionarioDTO;
import br.com.sicoob.capes.cadastro.negocio.enums.FuncaoEnum;
import br.com.sicoob.capes.cadastro.persistencia.dao.FuncionarioDAO;
import br.com.sicoob.capes.negocio.entidades.Funcionario;
import br.com.sicoob.capes.negocio.entidades.Nucleo;

/**
 * Classe que implementa a persistencia de funcionario.
 *
 * @author Juan.Damasceno
 *
 */
public class FuncionarioDAOImpl extends CAPESCadastroCrudDao<Funcionario> implements FuncionarioDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_FUNCIONARIO_POR_INSTITUICAO";
	
	/** A constante NOME_COMANDO_PESQUISAR_ATIVOS_POR_INSTITUICAO. */
	private static final String NOME_COMANDO_PESQUISAR_ATIVOS_POR_INSTITUICAO = "PESQUISA_FUNCIONARIO_ATIVO_POR_INSTITUICAO";
	
	/** A constante NOME_COMANDO_PESQUISAR_POR_NUCLEO. */
	private static final String NOME_COMANDO_PESQUISAR_POR_NUCLEO = "PESQUISA_FUNCIONARIO_POR_NUCLEO";

	/**
	 * Instancia um novo FuncionarioDAOImpl.
	 */
	public FuncionarioDAOImpl() {
		super(Funcionario.class, NOME_COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Funcionario> listarAtivosPorInstituicao(ConsultaDto<Funcionario> consultaDto) {
		return pesquisarListaEntidade(Funcionario.class, consultaDto, NOME_COMANDO_PESQUISAR_ATIVOS_POR_INSTITUICAO);
	}

	/**
	 * {@inheritDoc}
	 */
	public Long obterQuantidadeFuncionariosPorNucleo(Nucleo nucleo) {
		ConsultaDto<Nucleo> criterios = new ConsultaDto<Nucleo>();
		criterios.setFiltro(nucleo);
		return pesquisarNumeroRegistros(criterios, NOME_COMANDO_PESQUISAR_POR_NUCLEO, true);
	}
	
	@SuppressWarnings("unchecked")
	public List<FuncionarioDTO> obterListaFuncionarioGerente(Integer idInstituicao) {
		Comando comando = null;
		try {
			comando = getComando("OBTER_GERENTE_POR_INSTITUICAO");
			comando.adicionarVariavel("idInstituicao", idInstituicao);
			comando.adicionarVariavel("idFuncao", FuncaoEnum.GERENTE.getIdTipoFuncao());
			comando.configurar();
			
			return criarQuery(comando).getResultList();
		} finally {
			fecharComando(comando);
		}
	}

}