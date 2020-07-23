/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.InformacaoUtilizadaDelegate;
import br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade.AtualizacaoInformacaoProfissionalFachada;
import br.com.sicoob.capes.cadastro.negocio.dominio.validacao.ValidacaoInformacaoProfissional;
import br.com.sicoob.capes.cadastro.negocio.excecao.InformacaoUtilizadaException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.InformacaoProfissionalServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.InformacaoProfissionalServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.InformacaoProfissionalDAO;
import br.com.sicoob.capes.comum.negocio.annotation.IgnorarAutorizar;
import br.com.sicoob.capes.comum.negocio.enums.TipoInformacaoEnum;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.InformacaoProfissional;

/**
 * @author Erico.Junior
 * 
 */
@Stateless
@Local({ InformacaoProfissionalServicoLocal.class })
@Remote({ InformacaoProfissionalServicoRemote.class })
public class InformacaoProfissionalServicoEJB extends
		CAPESCadastroCrudServicoEJB<InformacaoProfissional>
		implements InformacaoProfissionalServicoRemote, InformacaoProfissionalServicoLocal {

	@Inject
	@Default
	private InformacaoProfissionalDAO dao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected InformacaoProfissionalDAO getDAO() {
		return dao;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<InformacaoProfissional> listar(Pessoa pessoa) throws BancoobException {
		return listar(pessoa, obterInstituicaoUsuarioLogado().getIdInstituicao());
	}

	/**
	 * {@inheritDoc}
	 */
	public List<InformacaoProfissional> listar(Pessoa pessoa, Integer idInstituicao) 
			throws BancoobException {

		InformacaoProfissional informacao = new InformacaoProfissional();
		informacao.setPessoa(pessoa);
		informacao.setIdInstituicao(idInstituicao);
		
		ConsultaDto<InformacaoProfissional> criterios = new ConsultaDto<InformacaoProfissional>();
		criterios.setFiltro(informacao);
		
		return listar(criterios);
	}

	/**
	 * {@inheritDoc}
	 */
	public InformacaoProfissional incluirBancoob(InformacaoProfissional informacao) 
			throws BancoobException {
		
		return incluir(informacao, Constantes.Comum.ID_INSTITUICAO_BANCOOB);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<InformacaoProfissional> listarPorMatriculaEmpregador(
			InformacaoProfissional informacao) {
		return getDAO().listarPorMatriculaEmpregador(informacao);
	}	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(InformacaoProfissional informacao) throws BancoobException {
		validarAlterar(informacao);
		super.alterar(informacao);
		obterAtualizacaoFachada().alterarLegado(informacao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InformacaoProfissional incluir(InformacaoProfissional informacao) 
			throws BancoobException {
		
		Instituicao instituicao = obterInstituicaoUsuarioLogado();
		return incluir(informacao, instituicao.getIdInstituicao());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(Serializable chave) throws BancoobException {
		InformacaoProfissional informacao = obter(chave);
		validarExclusao(informacao);
		super.excluir(informacao.getIdInformacao());
		obterAtualizacaoFachada().excluirLegado(informacao);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluirEntidade(InformacaoProfissional objeto) throws BancoobException {
		validarExclusao(objeto);
		super.excluirEntidade(objeto);
	}

	/**
	 * {@inheritDoc}
	 */
	@IgnorarAutorizar
	public InformacaoProfissional incluir(InformacaoProfissional informacao,
			Integer idInstituicao) throws BancoobException {
		
		informacao.setIdInstituicao(idInstituicao);
		validarIncluir(informacao);
		InformacaoProfissional incluida =  super.incluir(informacao);
		obterAtualizacaoFachada().incluirLegado(incluida);
		return incluida;
	}
	
	/**
	 * O método Validar incluir.
	 *
	 * @param informacao o valor de informacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarIncluir(InformacaoProfissional informacao) throws BancoobException {
		ValidacaoInformacaoProfissional validacao = new ValidacaoInformacaoProfissional();
		validacao.validar(informacao);
	}
	
	/**
	 * O método Validar alterar.
	 *
	 * @param informacao o valor de informacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarAlterar(InformacaoProfissional informacao) throws BancoobException {
		ValidacaoInformacaoProfissional validacao = new ValidacaoInformacaoProfissional();
		validacao.validar(informacao);
	}
	
	/**
	 * Obter atualizacao fachada.
	 *
	 * @param entidadeDb2 o valor de entidade db2
	 * @param idInstituicao o valor de id instituicao
	 * @param tipoOperacaoEnum o valor de tipo operacao enum
	 * @return AtualizacaoInformacaoProfissionalFachada
	 */
	private AtualizacaoInformacaoProfissionalFachada obterAtualizacaoFachada() {
		return new AtualizacaoInformacaoProfissionalFachada();
	}
	
	/**
	 * Valida se a informação está sendo utilizada por outro sistema.
	 * @param informacaoProfissional
	 * @throws BancoobException
	 */
	private void validarExclusao(InformacaoProfissional informacaoProfissional) throws BancoobException {
		if(informacaoProfissional != null){
			InformacaoUtilizadaDelegate informacaoUtilizadaDelegate = CAPESCadastroFabricaDelegates.getInstance().criarInformacaoUtilizadaDelegate();
			List<String> sistemasUtilizandoInformacao = informacaoUtilizadaDelegate.listarSistemasUsandoInformacao(
					TipoInformacaoEnum.INFORMACAO_PROFISSIONAL.getCodigo(), informacaoProfissional.getId().longValue());

			if (sistemasUtilizandoInformacao != null && sistemasUtilizandoInformacao.size() > 0) {
				throw new InformacaoUtilizadaException(sistemasUtilizandoInformacao.toArray());
			}
		}
	}
	
}