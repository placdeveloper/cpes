/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.enums.NucleoEnum;
import br.com.sicoob.capes.cadastro.negocio.excecao.NucleoAlteracaoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.NucleoExclusaoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.NucleoExisteInstituicaoException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.FuncionarioServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.NucleoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.NucleoServicoRemote;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.PessoaInstituicaoServicoLocal;
import br.com.sicoob.capes.cadastro.persistencia.dao.NucleoDAO;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Nucleo;
import br.com.sicoob.capes.replicacao.negocio.delegates.CAPESReplicacaoComumFabricaDelegates;
import br.com.sicoob.capes.replicacao.negocio.delegates.NucleoDelegate;

/**
 * Implementa as operações do serviço de NUcleo.
 * 
 * @author juan.damasceno
 */
@Stateless
@Local( { NucleoServicoLocal.class })
@Remote( { NucleoServicoRemote.class })
public class NucleoServicoEJB extends CAPESCadastroCrudServicoEJB<Nucleo>
		implements NucleoServicoRemote, NucleoServicoLocal {

	@Inject
	@Default
	private NucleoDAO nucleoDAO;
	
	/** O atributo pessoaInstituicaoServico. */
	@EJB(mappedName = "capes/cadastro/PessoaInstituicaoServico")
	private PessoaInstituicaoServicoLocal pessoaInstituicaoServico;
	
	/** O atributo funcionarioServico. */
	@EJB(mappedName = "capes/cadastro/FuncionarioServico")
	private FuncionarioServicoLocal funcionarioServico;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected NucleoDAO getDAO() {
		return nucleoDAO;
	}

	/**
	 * Inclui os dados na base DB2 e SQL
	 */
	@Override
	public Nucleo incluir(Nucleo nucleo) throws BancoobException {

		Instituicao instituicao = obterInstituicaoUsuarioLogado();		
		return incluir(nucleo, instituicao);
	}

	/**
	 * O método Verificar existencia mesmo num nucleo.
	 *
	 * @param numNucleo o valor de num nucleo
	 * @param idInstituicao o valor de id instituicao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void verificarExistenciaMesmoNumNucleo(Integer numNucleo,
			Integer idInstituicao) throws BancoobException {
		
		ConsultaDto<Nucleo> criterios = obterFiltro(idInstituicao, numNucleo);
		List<Nucleo> lista = super.listar(criterios);
		if(CollectionUtils.isNotEmpty(lista)) {
			throw new NucleoExisteInstituicaoException();
		}
	}

	/**
	 * O método Verificar existencia mesma descricao.
	 *
	 * @param numNucleo o valor de num nucleo
	 * @param descricao o valor de descricao
	 * @param idInstituicao o valor de id instituicao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void verificarExistenciaMesmaDescricao(Integer numNucleo, String descricao,
			Integer idInstituicao) throws BancoobException {
		
		ConsultaDto<Nucleo> criterios = obterFiltro(idInstituicao, descricao);
		List<Nucleo> lista = super.listar(criterios);
		
		if(CollectionUtils.isNotEmpty(lista)) {
			for (Nucleo nucleo : lista) {
				if(!nucleo.getNumNucleo().equals(numNucleo)) {
					throw new NucleoExisteInstituicaoException();		
				}
			}
		}			
	}
	
	/**
	 * Obter filtro.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @param numNucleo o valor de num nucleo
	 * @return ConsultaDto
	 */
	private ConsultaDto<Nucleo> obterFiltro(Integer idInstituicao, Integer numNucleo) {
		return obterFiltro(idInstituicao, null, numNucleo);
	}
	
	/**
	 * Obter filtro.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @param descricao o valor de descricao
	 * @return ConsultaDto
	 */
	private ConsultaDto<Nucleo> obterFiltro(Integer idInstituicao, String descricao) {
		return obterFiltro(idInstituicao, descricao, null);
	}
	
	/**
	 * Obter filtro.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @param descricao o valor de descricao
	 * @param numNucleo o valor de num nucleo
	 * @return ConsultaDto
	 */
	private ConsultaDto<Nucleo> obterFiltro(
			Integer idInstituicao, String descricao, Integer numNucleo) {
		
		Nucleo nucleo = new Nucleo();
		nucleo.setIdInstituicao(idInstituicao);
		nucleo.setDescricao(descricao);
		nucleo.setNumNucleo(numNucleo);
		
		ConsultaDto<Nucleo> criterios = new ConsultaDto<Nucleo>();
		criterios.setFiltro(nucleo);
		return criterios;
	}

	/**
	 * Altera o {@link Nucleo} na base DB2 e SQL
	 */
	@Override
	public void alterar(Nucleo nucleo) throws BancoobException {
		Integer idInstituicao = nucleo.getIdInstituicao();
		validarAlterarContemVinculos(nucleo);
		verificarExistenciaMesmaDescricao(nucleo.getNumNucleo(), nucleo.getDescricao(), idInstituicao);
		super.alterar(nucleo);
		getReplicacao().alterar(criarReplicacao(nucleo), idInstituicao);
	}

	/**
	 * Excluir o {@link Nucleo} da base DB2 e SQL
	 */
	@Override
	public void excluir(Nucleo nucleo) throws BancoobException {
		validarExcluir(nucleo);
		super.excluir(nucleo.getId());
		getReplicacao().excluir(criarReplicacao(nucleo), nucleo.getIdInstituicao());
	}
	
	private void validarAlterarContemVinculos(Nucleo nucleo) throws BancoobException{
		if(!nucleo.getAtivo()){
			long qtdClientes = pessoaInstituicaoServico.obterQuantidadeClientesPorNucleo(nucleo);
			if(qtdClientes > 0){
				throw new NucleoAlteracaoException("cliente");
			}
			
			long qtdfuncionario = funcionarioServico.obterQuantidadeFuncionariosPorNucleo(nucleo);
			if(qtdfuncionario > 0){
				throw new NucleoAlteracaoException("funcionário");
			}
		}
	}
	
	/**
	 * O método Validar excluir.
	 *
	 * @param nucleo o valor de nucleo
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarExcluir(Nucleo nucleo) throws BancoobException {
		validaContemVinculos(nucleo);
	}
	
	private void validaContemVinculos(Nucleo nucleo) throws BancoobException {
		long qtdClientes = pessoaInstituicaoServico.obterQuantidadeClientesPorNucleo(nucleo);

		if (qtdClientes > 0) {
			throw new NucleoExclusaoException("cliente");
		}
		long qtdfuncionario = funcionarioServico.obterQuantidadeFuncionariosPorNucleo(nucleo);

		if (qtdfuncionario > 0) {
			throw new NucleoExclusaoException("funcionário");
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConsultaDto<Nucleo> pesquisarPorInstituicao(ConsultaDto<Nucleo> criterios)
			throws BancoobException {
		Instituicao instituicao = obterInstituicaoUsuarioLogado();
		Nucleo nucleo = (Nucleo) criterios.getFiltro();
		if (nucleo == null) { 
			nucleo = new Nucleo();
			criterios.setFiltro(nucleo);
		}
		nucleo.setIdInstituicao(instituicao.getIdInstituicao());
		return super.pesquisar(criterios);
	}
	
	/**
	 * Cria o nucleo para replica??o na base SQL
	 * @param objeto
	 * @return
	 */
	private br.com.sicoob.capes.negocio.entidades.legado.Nucleo criarReplicacao(Nucleo objeto) {
		br.com.sicoob.capes.negocio.entidades.legado.Nucleo nucleo = new br.com.sicoob.capes.negocio.entidades.legado.Nucleo();
		nucleo.setNumNucleo(objeto.getNumNucleo());
		nucleo.setDescricao(truncarCampo(objeto.getDescricao(), 40));
		return nucleo;
	}
	
	/**
	 * truca o campo, caso ele seja maior do que o suportado.
	 * 
	 * @param campo
	 * @param tamanhoMaximo
	 * @return
	 */
	private String truncarCampo(String campo, Integer tamanhoMaximo) {
		if (!StringUtils.isEmpty(campo) && campo.length() > tamanhoMaximo) {
			return campo.substring(0, tamanhoMaximo);
		} else {
			return campo;
		}
	}
	
	/**
	 * @return delegate de acesso ao servi?o SQL
	 */
	private NucleoDelegate getReplicacao() {
		return CAPESReplicacaoComumFabricaDelegates.getInstance().criarNucleoDelegate();
	}
	
	@Override
	public ConsultaDto<Nucleo> pesquisarPorInstituicaoAtivos(ConsultaDto<Nucleo> criterios) 
			throws BancoobException {
		Instituicao instituicao = obterInstituicaoUsuarioLogado();
		Nucleo nucleo = (Nucleo) criterios.getFiltro();
		if (nucleo == null) {
			nucleo = new Nucleo();
			criterios.setFiltro(nucleo);
		}
		nucleo.setIdInstituicao(instituicao.getIdInstituicao());
		nucleo.setAtivo(Boolean.TRUE);
		return super.pesquisar(criterios);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer pesquisarProximoCodigo() throws BancoobException {
		return getDAO().pesquisarProximoCodigo(obterInstituicaoUsuarioLogado().getIdInstituicao());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Nucleo consultarNucleoNenhum(Instituicao instituicao) throws BancoobException {
		
		ConsultaDto<Nucleo> criterios = obterFiltro(
				instituicao.getIdInstituicao(), NucleoEnum.NENHUM.getNumNucleo());
		
		List<Nucleo> lista = getDAO().listar(criterios);
		Nucleo nucleo = null;
		if(lista != null && !lista.isEmpty()) {
			nucleo = lista.get(0);
		}
		
		return nucleo;		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Nucleo consultarNucleo(Instituicao instituicao, Integer numNucleo)
			throws BancoobException {
		
		ConsultaDto<Nucleo> criterios = obterFiltro(instituicao.getIdInstituicao(), numNucleo);
		
		List<Nucleo> lista = getDAO().listar(criterios);
		Nucleo nucleo = null;
		if(lista != null && !lista.isEmpty()) {
			nucleo = lista.get(0);
		}
		
		return nucleo;		
	}
	
	/**
	 * Inclui os dados na base DB2 e SQL
	 */
	@Override
	public Nucleo incluir(Nucleo nucleo, Instituicao instituicao) throws BancoobException {
		
		Integer idInstituicao = instituicao.getIdInstituicao();
		
		// verifica se já existe núcleo cadastrado com o mesmo numNumero na mesma instituição
		verificarExistenciaMesmoNumNucleo(nucleo.getNumNucleo(), idInstituicao);
		verificarExistenciaMesmaDescricao(nucleo.getNumNucleo(), nucleo.getDescricao(), idInstituicao);

		nucleo.setIdInstituicao(instituicao.getIdInstituicao());
		Nucleo incluido = super.incluir(nucleo);
		
		for (br.com.sicoob.capes.negocio.entidades.legado.Nucleo nucleoSql : getReplicacao()
				.listar(idInstituicao)) {
			if (nucleoSql.getNumNucleo().equals(incluido.getNumNucleo())) {
				return incluido;
			}
		}
		getReplicacao().incluir(criarReplicacao(incluido), instituicao.getIdInstituicao());
		
		return incluido;
	}

}
