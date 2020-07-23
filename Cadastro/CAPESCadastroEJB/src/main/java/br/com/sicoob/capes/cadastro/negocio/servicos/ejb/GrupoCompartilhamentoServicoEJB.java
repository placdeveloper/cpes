/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.apache.commons.lang.math.NumberUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.TransicaoPessoaDelegate;
import br.com.sicoob.capes.cadastro.negocio.excecao.GrupoCompartilhamentoNaoPodeExcluirException;
import br.com.sicoob.capes.cadastro.negocio.excecao.GrupoCompartilhamentoNaoPodeSerAlteradoException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoCompartilhamentoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoCompartilhamentoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.GrupoCompartilhamentoDAO;
import br.com.sicoob.capes.comum.negocio.vo.GrupoCompartilhamentoVO;
import br.com.sicoob.capes.comum.negocio.vo.InstituicaoVO;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.vigente.GrupoCompartilhamento;

/**
 * Implementa as operações do serviço de GrupoCompartilhamento.
 * 
 * @author juan.damasceno
 */
@Stateless
@Local( { GrupoCompartilhamentoServicoLocal.class })
@Remote( { GrupoCompartilhamentoServicoRemote.class })
public class GrupoCompartilhamentoServicoEJB extends
		CAPESCadastroCrudServicoEJB<GrupoCompartilhamento> implements GrupoCompartilhamentoServicoRemote, GrupoCompartilhamentoServicoLocal {

	@Inject
	@Default
	private GrupoCompartilhamentoDAO grupoCompartilhamentoDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected GrupoCompartilhamentoDAO getDAO() {
		return grupoCompartilhamentoDAO;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public GrupoCompartilhamento obterPorCooperativa(Integer numCooperativa) throws BancoobException {

		InstituicaoVO instituicao = obterInstituicao(numCooperativa);
		GrupoCompartilhamento grupoCompartilhamento = null;

		if (instituicao != null) {
			Integer idInstituicao = instituicao.getIdInstituicao();
			grupoCompartilhamento = obterPorInstituicao(idInstituicao);
		}

		return grupoCompartilhamento;
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public GrupoCompartilhamento obterPorInstituicao(Integer idInstituicao) throws BancoobException {

		ConsultaDto<GrupoCompartilhamento> criterios = new ConsultaDto<GrupoCompartilhamento>();

		GrupoCompartilhamento filtro = new GrupoCompartilhamento();
		filtro.setIdInstituicao(idInstituicao);
		criterios.setFiltro(filtro);

		List<GrupoCompartilhamento> resultado = listar(criterios);
		GrupoCompartilhamento grupoCompartilhamento = null;
		
		if (!resultado.isEmpty()){
			grupoCompartilhamento = resultado.get(0);
		}

		return grupoCompartilhamento;
	}

	/**
	 * Obter instituicao.
	 *
	 * @param numCooperativa o valor de num cooperativa
	 * @return InstituicaoVO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private InstituicaoVO obterInstituicao(Integer numCooperativa) throws BancoobException {
		SCIIntegracaoDelegate sciIntegracaoDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();
		return sciIntegracaoDelegate.obterInstituicaoPorNumeroCooperativa(numCooperativa);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(Serializable chave) throws BancoobException {
		TransicaoPessoaDelegate transicaoPessoaDelegate = CAPESCadastroFabricaDelegates.getInstance().criarTransicaoPessoaDelegate();
				
		GrupoCompartilhamento grupoCompartilhamento = obter(chave);
		
		if(transicaoPessoaDelegate.verificarInstituicao(grupoCompartilhamento.getIdInstituicao())){
			throw new GrupoCompartilhamentoNaoPodeExcluirException();
		}else{
			getDAO().excluir(chave);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public ConsultaDto<GrupoCompartilhamentoVO> listarInstuicoesPorCompartilhamento(ConsultaDto<GrupoCompartilhamento> consultaDto) throws BancoobException {
		return transformarDTO(getDAO().listarInstuicoesPorCompartilhamento(consultaDto));
	}
	
	/**
	 * Transformar dto.
	 *
	 * @param resultado o valor de resultado
	 * @return ConsultaDto
	 */
	private ConsultaDto<GrupoCompartilhamentoVO> transformarDTO(ConsultaDto<GrupoCompartilhamentoVO> resultado) {
		ConsultaDto<GrupoCompartilhamentoVO> dto = new ConsultaDto<GrupoCompartilhamentoVO>();
		dto.setFiltro(resultado.getFiltro());
		dto.setOrdemCrescente(resultado.isOrdemCrescente());
		dto.setOrdenacao(resultado.getOrdenacao());
		dto.setPagina(resultado.getPagina());
		dto.setProcurarPor(resultado.getProcurarPor());
		List<GrupoCompartilhamentoVO> lista = resultado.getResultado();
		dto.setResultado(lista == null ? new ArrayList<GrupoCompartilhamentoVO>() : lista);
		dto.setTamanhoPagina(resultado.getTamanhoPagina());
		dto.setTipoProcura(resultado.getTipoProcura());
		dto.setTotalRegistros(lista == null ? NumberUtils.INTEGER_ZERO : resultado.getTotalRegistros());
		return dto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<GrupoCompartilhamentoVO> listarInstuicoesSemGrupo()
			throws BancoobException {
		return getDAO().listarInstuicoesSemGrupo();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Boolean verificarCompartilhamentoCadastro(Short idCompartilhamentoCadastro) throws BancoobException {
		return getDAO().verificarCompartilhamentoCadastro(idCompartilhamentoCadastro);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(GrupoCompartilhamento objeto) throws BancoobException {
		validarAlteracaoGrupoCompartilhamento(objeto);
		super.alterar(objeto);
	}

	/**
	 * O método Validar alteracao grupo compartilhamento.
	 *
	 * @param grupo o valor de grupo
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarAlteracaoGrupoCompartilhamento(GrupoCompartilhamento grupo) throws BancoobException {
		if (grupo != null) {
			GrupoCompartilhamento grupoComp = grupoCompartilhamentoDAO.obter(grupo.getId());

			if (grupoComp != null && !grupo.getCompartilhamentoCadastro().getId().equals(grupoComp.getCompartilhamentoCadastro().getId())) {
				TransicaoPessoaDelegate transicaoDelegate = CAPESCadastroFabricaDelegates.getInstance().criarTransicaoPessoaDelegate();
				Integer quantidadePessoaInstituicao = transicaoDelegate.buscarQuantidadePessoasInstituicao(grupo.getIdInstituicao());

				if (quantidadePessoaInstituicao != null && quantidadePessoaInstituicao > 0) {
					throw new GrupoCompartilhamentoNaoPodeSerAlteradoException();
				}
			}
		}
	}

}