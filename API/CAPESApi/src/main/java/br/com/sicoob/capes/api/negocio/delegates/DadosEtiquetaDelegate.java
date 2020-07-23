/*
 * SICOOB
 * 
 * DadosEtiquetaDelegate.java(br.com.sicoob.capes.api.negocio.delegates.DadosEtiquetaDelegate)
 */
package br.com.sicoob.capes.api.negocio.delegates;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.IDadosEtiquetaDelegate;
import br.com.sicoob.capes.api.negocio.servicos.DadosEtiquetaServico;
import br.com.sicoob.capes.api.negocio.servicos.locator.CAPESApiServiceLocator;
import br.com.sicoob.capes.api.negocio.vo.DadosEtiquetaVO;

/**
 * @author erico.junior
 * 
 */
public class DadosEtiquetaDelegate extends CAPESApiDelegate<DadosEtiquetaServico> implements IDadosEtiquetaDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected DadosEtiquetaServico localizarServico() {
		return CAPESApiServiceLocator.getInstance().localizarDadosEtiquetaServico();
	}

	/**
	 * Listar por pessoa legado instituicao.
	 * 
	 * @param listaIdPessoaLegado
	 *            the lista id pessoa legado
	 * @param idInstituicao
	 *            the id instituicao
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	@Deprecated
	public List<DadosEtiquetaVO> listarPorPessoaLegadoInstituicao(List<Integer> listaIdPessoaLegado, Integer idInstituicao) throws BancoobException {
		return getServico().listar(listaIdPessoaLegado, idInstituicao);
	}

	/**
	 * @param idInstituicao
	 * @param codTipoPessoa 0-Pessoa Física; 1-Pessoa Jurídica
	 * @param dataNascimentoInicio
	 * @param dataNascimentoFinal
	 * @param tipoSexo M; F
	 * @param mesDiaAniversarioInicio MMDD
	 * @param mesDiaAniversarioFinal MMDD
	 * @return
	 * @throws BancoobException 
	 */
	public List<DadosEtiquetaVO> listarPorPessoaLegadoInstituicao(Integer idInstituicao, Short codTipoPessoa, Date dataNascimentoInicio,
			Date dataNascimentoFinal, Character tipoSexo, String mesDiaAniversarioInicio, String mesDiaAniversarioFinal) throws BancoobException {
		return getServico().listarPorPessoaLegadoInstituicao(idInstituicao, codTipoPessoa, dataNascimentoInicio,
				dataNascimentoFinal, tipoSexo, mesDiaAniversarioInicio, mesDiaAniversarioFinal);
	}

}
