/*
 * SICOOB
 * 
 * DadosEtiquetaServico.java(br.com.sicoob.capes.api.negocio.servicos.DadosEtiquetaServico)
 */
package br.com.sicoob.capes.api.negocio.servicos;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.DadosEtiquetaVO;

/**
 * @author erico.junior
 *
 */
public interface DadosEtiquetaServico extends CAPESApiServico {

	/**
	 * Listar.
	 * 
	 * @param listaIdPessoaLegado
	 *            the lista id pessoa legado
	 * @param idInstituicao
	 *            the id instituicao
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	List<DadosEtiquetaVO> listar(List<Integer> listaIdPessoaLegado,
			Integer idInstituicao) throws BancoobException;

	/**
	 * @param idInstituicao
	 * @param codTipoPessoa
	 * @param dataNascimentoInicio
	 * @param dataNascimentoFinal
	 * @param tipoSexo
	 * @param mesDiaAniversarioInicio
	 * @param mesDiaAniversarioFinal
	 * @return
	 */
	List<DadosEtiquetaVO> listarPorPessoaLegadoInstituicao(Integer idInstituicao, Short codTipoPessoa, Date dataNascimentoInicio, Date dataNascimentoFinal,
			Character tipoSexo, String mesDiaAniversarioInicio, String mesDiaAniversarioFinal) throws BancoobException;

}
