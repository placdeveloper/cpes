/*
 * SICOOB
 * 
 * DataUltimaAtualizacaoServicoEJB.java(br.com.sicoob.capes.api.negocio.servicos.ejb.DataUltimaAtualizacaoServicoEJB)
 */
package br.com.sicoob.capes.api.negocio.servicos.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.excecao.ParametroNaoInformadoException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.DataUltimaAtualizacaoServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.DataUltimaAtualizacaoServicoRemote;
import br.com.sicoob.capes.api.persistencia.dao.CAPESApiDao;
import br.com.sicoob.capes.api.persistencia.dao.DataUltimaAtualizacaoDAO;
import br.com.sicoob.capes.comum.negocio.vo.DataUltimaAtualizacaoVO;

/**
 * Serviço EJB que implementa a {@link br.com.sicoob.capes.api.negocio.servicos.DataUltimaAtualizacaoServico}
 * 
 * @author Marcelo.Onofre
 */
@Stateless
@Local(DataUltimaAtualizacaoServicoLocal.class)
@Remote(DataUltimaAtualizacaoServicoRemote.class)
public class DataUltimaAtualizacaoServicoEJB extends CAPESApiServicoEJB implements DataUltimaAtualizacaoServicoRemote, DataUltimaAtualizacaoServicoLocal{

	@Inject
	@Default
	private DataUltimaAtualizacaoDAO dataUltimaAtualizacaoDAO;

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Date obterByPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		validarObrigatoriedadePessoaInstituicao(idPessoa, idInstituicao);
		return dataUltimaAtualizacaoDAO.obterDataUltimaAtualizacaoPorPessoaInstituicao(idPessoa, idInstituicao);
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<DataUltimaAtualizacaoVO> consultarPorDataReferenciaPessoas(Integer idInstituicao, Date dataReferencia, Integer... idsPessoas) throws BancoobException {
		validarObrigatoriedadeInstituicao(idInstituicao);
		if (dataReferencia == null) {
			throw new ParametroNaoInformadoException("dataReferencia");
		}
		if ((idsPessoas == null) || (idsPessoas.length == 0)) {
			throw new ParametroNaoInformadoException("idsPessoas");
		}
		return dataUltimaAtualizacaoDAO.consultarDatasUltimaAtualizacaoPorDataReferenciaPessoas(idInstituicao, dataReferencia, idsPessoas);
	}

	@Override
	protected CAPESApiDao obterDAO() {
		return dataUltimaAtualizacaoDAO;
	}

}