/*
 * SICOOB
 * 
 * GrupoEconomicoServicoEJB.java(br.com.sicoob.capes.api.negocio.servicos.ejb.GrupoEconomicoServicoEJB)
 */
package br.com.sicoob.capes.api.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.excecao.ParametroNaoInformadoException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.GrupoEconomicoServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.GrupoEconomicoServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.GrupoEconomicoVO;
import br.com.sicoob.capes.api.negocio.vo.PessoaGrupoEconomicoVO;
import br.com.sicoob.capes.api.persistencia.dao.CAPESApiDao;
import br.com.sicoob.capes.api.persistencia.dao.GrupoEconomicoDAO;
import br.com.sicoob.capes.api.persistencia.dao.GrupoEconomicoNovoDAO;
import br.com.sicoob.capes.cadastro.negocio.enums.ParametroEnum;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ParametroServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.vo.ParametroVO;

/**
 * @author Marcelo.Onofre
 * 
 */
@Stateless
@Local(GrupoEconomicoServicoLocal.class)
@Remote(GrupoEconomicoServicoRemote.class)
public class GrupoEconomicoServicoEJB extends CAPESApiServicoEJB implements GrupoEconomicoServicoRemote, GrupoEconomicoServicoLocal {

	private static final String DESTINO_NOVO = "NOVO";

	@Inject
	@Default
	private GrupoEconomicoDAO grupoEconomicoDAO;

	@Inject
	@Default
	private GrupoEconomicoNovoDAO grupoEconomicoNovoDAO;

	@EJB
	private ParametroServicoLocal parametroServico;

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public GrupoEconomicoVO obterByPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		validarObrigatoriedadePessoaInstituicao(idPessoa, idInstituicao);
		ParametroVO parametroVO = parametroServico.obterParametro(ParametroEnum.SERVICOS_GRUPO_ECONOMICO.getCodigo(), idInstituicao);
		if (parametroVO.getValor().equals(DESTINO_NOVO)) {
			return grupoEconomicoNovoDAO.obterGrupoPorPessoaInstituicao(idPessoa, idInstituicao);
		} else {
			return grupoEconomicoDAO.obterPorPessoaInstituicaoUnico(idPessoa, idInstituicao);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PessoaGrupoEconomicoVO> obterPessoasPorGrupoInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		validarObrigatoriedadePessoaInstituicao(idPessoa, idInstituicao);
		ParametroVO parametroVO = parametroServico.obterParametro(ParametroEnum.SERVICOS_GRUPO_ECONOMICO.getCodigo(), idInstituicao);
		if (parametroVO.getValor().equals(DESTINO_NOVO)) {
			return grupoEconomicoNovoDAO.obterPessoasPorPessoaInstituicao(idPessoa, idInstituicao);
		} else {
			return grupoEconomicoDAO.obterPessoasPorGrupoInstituicao(idPessoa, idInstituicao);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PessoaGrupoEconomicoVO> obterGruposPorCpfCnpj(List<String> listaCpfCnpj, Integer idInstituicao) throws BancoobException {
		validarObrigatoriedadeInstituicao(idInstituicao);
		if (listaCpfCnpj == null || listaCpfCnpj.isEmpty()) {
			throw new ParametroNaoInformadoException("listaCpfCnpj");
		}
		ParametroVO parametroVO = parametroServico.obterParametro(ParametroEnum.SERVICOS_GRUPO_ECONOMICO.getCodigo(), idInstituicao);
		if (parametroVO.getValor().equals(DESTINO_NOVO)) {
			return grupoEconomicoNovoDAO.obterPessoasGruposPorCpfCnpj(listaCpfCnpj, idInstituicao);
		} else {
			return grupoEconomicoDAO.obterGruposPorCpfCnpj(listaCpfCnpj, idInstituicao);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PessoaGrupoEconomicoVO> obterPessoasPorGrupo(Integer idGrupoEconomico) {
		return obterPessoasPorGrupo(idGrupoEconomico, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PessoaGrupoEconomicoVO> obterPessoasPorGrupo(Integer idGrupoEconomico, Integer idInstituicao) {
		ParametroVO parametroVO = parametroServico.obterParametro(ParametroEnum.SERVICOS_GRUPO_ECONOMICO.getCodigo(), idInstituicao);
		if (parametroVO.getValor().equals(DESTINO_NOVO)) {
			return grupoEconomicoNovoDAO.obterPessoasPorGrupo(idGrupoEconomico);
		} else {
			return grupoEconomicoDAO.obterPessoasPorGrupo(idGrupoEconomico);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PessoaGrupoEconomicoVO> obterGruposPorCpfCnpj(String cpfCnpj, Integer idInstituicao) throws BancoobException {
		validarObrigatoriedadeInstituicao(idInstituicao);
		if (StringUtils.isBlank(cpfCnpj)) {
			throw new ParametroNaoInformadoException("cpfCnpj");
		}
		return grupoEconomicoNovoDAO.obterPessoasPorCpfCnpj(cpfCnpj, idInstituicao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESApiDao obterDAO() {
		return grupoEconomicoDAO;
	}

}