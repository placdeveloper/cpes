/*
 * SICOOB
 * 
 * RelacionamentoPessoaServicoEJB.java(br.com.sicoob.capes.api.negocio.servicos.ejb.RelacionamentoPessoaServicoEJB)
 */
package br.com.sicoob.capes.api.negocio.servicos.ejb;

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
import br.com.sicoob.capes.api.negocio.filtros.FiltroConsultaAPIAbstrato;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.RelacionamentoPessoaServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.RelacionamentoPessoaServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.DadosRegistroVO;
import br.com.sicoob.capes.api.negocio.vo.RelacionamentoPessoaPoderVO;
import br.com.sicoob.capes.api.negocio.vo.RelacionamentoPessoaVO;
import br.com.sicoob.capes.api.persistencia.dao.RelacionamentoPessoaDAO;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoRelacionamentoPessoaEnum;

/**
 * The Class RelacionamentoPessoaServicoEJB.
 */
@Stateless
@Local(RelacionamentoPessoaServicoLocal.class)
@Remote(RelacionamentoPessoaServicoRemote.class)
public class RelacionamentoPessoaServicoEJB extends AbstractCAPESApiServicoPessoaEJB<RelacionamentoPessoaVO> 
		implements RelacionamentoPessoaServicoRemote, RelacionamentoPessoaServicoLocal{
	
	@Inject
	@Default
	private RelacionamentoPessoaDAO relacionamentoPessoaDAO;

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RelacionamentoPessoaVO> obterConjugesByPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		validarObrigatoriedadePessoaInstituicao(idPessoa, idInstituicao);
		List<RelacionamentoPessoaVO> listaRelacionamento = obterByPessoaTipoCompartilhado(idPessoa, TipoRelacionamentoPessoaEnum.CONJUGE.getCodigo(), idInstituicao);
		return obterDadosRegistroPoderes(listaRelacionamento);
	}

	/** 
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RelacionamentoPessoaVO> obterQuadroSocietarioByPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		validarObrigatoriedadePessoaInstituicao(idPessoa, idInstituicao);
		List<RelacionamentoPessoaVO> listaRelacionamento = new ArrayList<RelacionamentoPessoaVO>();
		listaRelacionamento.addAll(obterByPessoaTipoCompartilhado(idPessoa, TipoRelacionamentoPessoaEnum.SOCIO.getCodigo(), idInstituicao));
		listaRelacionamento.addAll(obterByPessoaTipoCompartilhado(idPessoa, TipoRelacionamentoPessoaEnum.SOCIO_ADMINISTRADOR.getCodigo(), idInstituicao));		
		return obterDadosRegistroPoderes(listaRelacionamento);
	}

	/** 
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RelacionamentoPessoaVO> obterByPessoaInstituicaoTipo(Integer idPessoa, Integer idInstituicao, Short codigoTipoRelacionamento) throws BancoobException {
		validarObrigatoriedadePessoaInstituicao(idPessoa, idInstituicao);
		if (codigoTipoRelacionamento == null) {
			throw new BancoobException("ID tipo relacionamento não informado.");
		}		
		return obterDadosRegistroPoderes(obterByPessoaTipoCompartilhado(idPessoa, codigoTipoRelacionamento, idInstituicao));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RelacionamentoPessoaVO obterByID(Serializable id) throws BancoobException {
		RelacionamentoPessoaVO dto = super.obterByID(id);
		obterPoderes(dto);
		obterDadosRegistro(dto);
		return dto;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RelacionamentoPessoaVO> obterByPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		validarObrigatoriedadePessoaInstituicao(idPessoa, idInstituicao);
		List<RelacionamentoPessoaVO> listaRelacionamento = obterByPessoaTipoCompartilhado(idPessoa, null, idInstituicao);
		return obterDadosRegistroPoderes(listaRelacionamento);
	}
	
	@Override
	@SuppressWarnings("unchecked")	
	public List<RelacionamentoPessoaVO> pesquisar(FiltroConsultaAPIAbstrato filtro) throws BancoobException {
		validarFiltroPesquisar(filtro);
		return obterDAO().pesquisarEspecifico(filtro);
	}

	/**
	 * Obter dados registro poderes.
	 * 
	 * @param listaRelacionamento
	 *            the lista relacionamento
	 * @return list
	 */
	private List<RelacionamentoPessoaVO> obterDadosRegistroPoderes(List<RelacionamentoPessoaVO> listaRelacionamento) throws BancoobException {
		if (listaRelacionamento != null) {
			for (RelacionamentoPessoaVO dto : listaRelacionamento) {
				obterPoderes(dto);
				obterDadosRegistro(dto);
			}
		}
		return listaRelacionamento;
	}

	/**
	 * Obter dados registro.
	 * 
	 * @param dto
	 *            the dto
	 */
	private void obterDadosRegistro(RelacionamentoPessoaVO dto) throws BancoobException {
		if (dto != null && dto.getIdRelacionamento() != null && dto.getHabilitaDadosRegistro() != null && dto.getHabilitaDadosRegistro()) {
			DadosRegistroVO dadosRegistro = relacionamentoPessoaDAO.obterDadosRegistro(dto.getIdRelacionamento());
			dto.setDadosRegistro(dadosRegistro);
		}
	}

	/**
	 * Obter poderes.
	 * 
	 * @param dto
	 *            the dto
	 */
	private void obterPoderes(RelacionamentoPessoaVO dto) throws BancoobException {
		if (dto != null && dto.getIdRelacionamento() != null && dto.getHabilitaPoderes() != null && dto.getHabilitaPoderes()) {
			List<RelacionamentoPessoaPoderVO> poderes = relacionamentoPessoaDAO.obterPoderes(dto.getIdRelacionamento());
			if (poderes != null && poderes.size() > NumberUtils.INTEGER_ZERO) {
				dto.setPoderes(poderes);
			}
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public ConsultaDto<RelacionamentoPessoaVO> pesquisarPaginado(
			FiltroConsultaAPIAbstrato filtro, int pagina, int tamanhoPagina)
			throws BancoobException {
		validarFiltroPesquisar(filtro);		
		return obterDAO().pesquisarPaginadoEspecifico((obterConsultaDTO(filtro, pagina, tamanhoPagina)));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RelacionamentoPessoaDAO obterDAO() {
		return relacionamentoPessoaDAO;
	}

	private List<RelacionamentoPessoaVO> obterByPessoaTipoCompartilhado(
			Integer idPessoa, Short codigoTipoRelacionamento,
			Integer idInstituicao) throws BancoobException {
		return obterDAO().obterByPessoaTipoCompartilhado(idPessoa,
				codigoTipoRelacionamento, idInstituicao);
	}

}