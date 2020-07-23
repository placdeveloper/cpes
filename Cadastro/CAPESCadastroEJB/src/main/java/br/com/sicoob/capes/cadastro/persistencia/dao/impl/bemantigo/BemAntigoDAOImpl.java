package br.com.sicoob.capes.cadastro.persistencia.dao.impl.bemantigo;

import java.io.Serializable;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.persistencia.dao.bemantigo.BemAntigoDAO;
import br.com.sicoob.capes.cadastro.persistencia.dao.impl.EntidadeCadastroDao;
import br.com.sicoob.capes.negocio.entidades.bemantigo.Bem;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemImovel;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Classe que implementa a persistência de Bem.
 * @author Juan.Damasceno
 *
 */
public class BemAntigoDAOImpl extends EntidadeCadastroDao<Bem> implements BemAntigoDAO {
	
	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_BEM_POR_PESSOA";
	
	/** A constante PESQUISA_BEM_DIFERENTE_DE_IMOVEL_POR_PESSOA. */
	private static final String PESQUISA_BEM_DIFERENTE_DE_IMOVEL_POR_PESSOA = "PESQUISA_BEM_DIFERENTE_DE_IMOVEL_POR_PESSOA";
	
	/** A constante PESQUISA_BEM_IMOVEL_VIGENTE_POR_PESSOA. */
	private static final String PESQUISA_BEM_IMOVEL_VIGENTE_POR_PESSOA = "PESQUISA_BEM_IMOVEL_VIGENTE_POR_PESSOA";
	
	/**
	 * Instancia um novo BemDAOImpl.
	 */
	public BemAntigoDAOImpl() {
		super(Bem.class, null);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Bem obter(Serializable chave) throws BancoobException {
		Bem retorno = super.obter(chave);
		
		if(retorno != null) {
			retorno.getBensOnus().size();
			retorno.getBensPosse().size();
			retorno.getBensRegistro().size();
		}
		
		return retorno;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(Bem objeto) throws BancoobException {
		//removerObjetoSessao(objeto);
		//Bem bemPersistent = obter(objeto.getId());
		//ReflexaoUtils.copiarPropriedades(bemPersistent, objeto, "bensOnus", "bensPosse", "bensRegistro");
		//copiarListas(bemPersistent, objeto);
		super.alterar(objeto);
	}
	
//	/**
//	 * O método Copiar listas.
//	 *
//	 * @param destino o valor de destino
//	 * @param origem o valor de origem
//	 * @throws BancoobException lança a exceção BancoobException
//	 */
//	private void copiarListas(Bem destino, Bem origem)
//			throws BancoobException {
//		copiarLista(destino, origem, destino.getBensOnus(), "bensOnus");
//		copiarLista(destino, origem, destino.getBensPosse(), "bensPosse");
//		copiarLista(destino, origem, destino.getBensRegistro(), "bensRegistro");
//	}
	
//	/**
//	 * O método Copiar lista.
//	 *
//	 * @param destino o valor de destino
//	 * @param origem o valor de origem
//	 * @param lista o valor de lista
//	 * @param propriedade o valor de propriedade
//	 * @throws BancoobException lança a exceção BancoobException
//	 */
//	private void copiarLista(Bem destino, Bem origem, List<?> lista, String propriedade) throws BancoobException {
//		if (lista != null) {
//			super.substituirColecoesPersistentes(destino, origem, propriedade);
//		} 
//	}
	
	/**
	 * Lista os bens usando a pessoa como filtro.
	 * @param pessoa a pessoa que será usada como filtro.
	 * @return os bens usando a pessoa como filtro.
	 */
	@Override
	public List<Bem> listarPorPessoa(PessoaCompartilhamento pessoa) {
		
		Bem bem = new Bem();
		bem.setPessoaCompartilhamento(pessoa);
		
		ConsultaDto<Bem> consultaDto = new ConsultaDto<Bem>();
		consultaDto.setFiltro(bem);
		consultaDto.setProcurarPor("POR BEM");
		
		ConsultaDto<Bem> resultado = pesquisar(Bem.class, consultaDto, NOME_COMANDO_PESQUISAR);
		
		return resultado.getResultado();
	}
	
	/**
	 * Lista os bens usando a pessoa como filtro.
	 * @param pessoa a pessoa que será usada como filtro.
	 * @return os bens usando a pessoa como filtro.
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List<Bem> listarPorBensDiferentesDeImovelPorPessoa(ConsultaDto dto) {
		return pesquisarLista(Bem.class, dto, PESQUISA_BEM_DIFERENTE_DE_IMOVEL_POR_PESSOA);
	}
	
	/**
	 * Lista os bens usando a pessoa como filtro.
	 * @param pessoa a pessoa que será usada como filtro.
	 * @return os bens usando a pessoa como filtro.
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List<Bem> listarBemImovelPorPessoa(ConsultaDto dto) {
		dto.setProcurarPor("POR BEM IMOVEL");
		return pesquisarLista(Bem.class, dto, NOME_COMANDO_PESQUISAR);
	}
	
	/**
	 * Lista os bens usando a pessoa como filtro.
	 * @param pessoa a pessoa que será usada como filtro.
	 * @return os bens usando a pessoa como filtro.
	 */
	@Override
	public List<Bem> listarBemImovelRuralPorPessoa(PessoaCompartilhamento pessoa) {
		
		BemImovel bemImovel = new BemImovel();
		bemImovel.setCodLocalizacao("R");
		bemImovel.setPessoaCompartilhamento(pessoa);
		
		ConsultaDto<Bem> consultaDto = new ConsultaDto<Bem>();
		consultaDto.setFiltro(bemImovel);
		consultaDto.setProcurarPor("POR BEM IMOVEL");

		ConsultaDto<Bem> resultado = pesquisar(Bem.class, consultaDto, NOME_COMANDO_PESQUISAR);
		
		return resultado.getResultado();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Bem> listarBemImovelRuralVigentePorPessoa(PessoaCompartilhamento pessoa) {
		BemImovel bemImovel = new BemImovel();
		bemImovel.setCodLocalizacao("R");
		bemImovel.setPessoaCompartilhamento(pessoa);
		
		ConsultaDto<Bem> consultaDto = new ConsultaDto<Bem>();
		consultaDto.setFiltro(bemImovel);

		ConsultaDto<Bem> resultado = pesquisar(Bem.class, consultaDto, PESQUISA_BEM_IMOVEL_VIGENTE_POR_PESSOA);
		
		return resultado.getResultado();
	}
	
	@Override
	public List<Bem> listarBensPorIdBemNovo(ConsultaDto<Bem> criterios) throws BancoobException {
		return pesquisarLista(Bem.class, criterios, "PESQUISA_BEM_POR_ID_BEM_NOVO");
	}
}