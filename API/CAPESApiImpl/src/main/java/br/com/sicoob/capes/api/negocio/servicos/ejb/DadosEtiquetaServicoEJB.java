/*
 * SICOOB
 * 
 * DadosEtiquetaServicoEJB.java(br.com.sicoob.capes.api.negocio.servicos.ejb.DadosEtiquetaServicoEJB)
 */
package br.com.sicoob.capes.api.negocio.servicos.ejb;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.propriedades.RepositorioPropriedades;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.capes.api.negocio.excecao.MaximoPessoasException;
import br.com.sicoob.capes.api.negocio.excecao.PessoaNaoInformadaException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.DadosEtiquetaServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.DadosEtiquetaServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.DadosEtiquetaVO;
import br.com.sicoob.capes.api.persistencia.dao.CAPESApiDao;
import br.com.sicoob.capes.api.persistencia.dao.DadosEtiquetaDAO;

/**
 * The Class DadosEtiquetaServicoEJB.
 */
@Stateless
@Local(DadosEtiquetaServicoLocal.class)
@Remote(DadosEtiquetaServicoRemote.class)
public class DadosEtiquetaServicoEJB extends CAPESApiServicoEJB implements DadosEtiquetaServicoRemote, DadosEtiquetaServicoLocal{
	
	@Inject
	@Default
	private DadosEtiquetaDAO dadosEtiquetaDAO;

	/** O atributo propriedades. */
	private transient Properties propriedades = RepositorioPropriedades.getInstance().recuperarPropriedades("capes.api.propriedades");

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<DadosEtiquetaVO> listar(List<Integer> listaIdPessoaLegado, Integer idInstituicao) throws BancoobException {
		validarObrigatoriedadeInstituicao(idInstituicao);
		validarIdPessoaLegado(listaIdPessoaLegado);
		return dadosEtiquetaDAO.listarDadosEtiqueta(idInstituicao, listaIdPessoaLegado);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<DadosEtiquetaVO> listarPorPessoaLegadoInstituicao(Integer idInstituicao, Short codTipoPessoa, Date dataNascimentoInicio,
			Date dataNascimentoFinal, Character tipoSexo, String mesDiaAniversarioInicio, String mesDiaAniversarioFinal) throws BancoobException {
		return dadosEtiquetaDAO.listarPorPessoaLegadoInstituicao(idInstituicao, codTipoPessoa, dataNascimentoInicio,
				dataNascimentoFinal, tipoSexo, mesDiaAniversarioInicio, mesDiaAniversarioFinal);
	}
	
	/**
	 * Validar id pessoa legado.
	 * 
	 * @param listaIdPessoaLegado
	 *            the lista id pessoa legado
	 * @throws NegocioException
	 *             the negocio exception
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	private void validarIdPessoaLegado(List<Integer> listaIdPessoaLegado) throws NegocioException {
		if (CollectionUtils.isEmpty(listaIdPessoaLegado)) {
			throw new PessoaNaoInformadaException();
		}
		
		String maximo = propriedades.getProperty("maximo.pessoas.etiquetas");
		Integer qtdMaxima = Integer.valueOf(maximo);

		if (listaIdPessoaLegado.size() > qtdMaxima) {
			throw new MaximoPessoasException(maximo);
		}
	}

	@Override
	protected CAPESApiDao obterDAO() {
		return dadosEtiquetaDAO;
	}
}