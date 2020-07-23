package br.com.sicoob.capes.api.negocio.delegates.interfaces;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.DadosEtiquetaVO;

/**
 * 
 * @author paulo.stoppa
 *
 */
public interface IDadosEtiquetaDelegate extends ICAPESApiDelegate {

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
	List<DadosEtiquetaVO> listarPorPessoaLegadoInstituicao(List<Integer> listaIdPessoaLegado, Integer idInstituicao) throws BancoobException;

	/**
	 * @param idInstituicao
	 * @param codTipoPessoa
	 *            0-Pessoa Física; 1-Pessoa Jurídica
	 * @param dataNascimentoInicio
	 * @param dataNascimentoFinal
	 * @param tipoSexo
	 *            M; F
	 * @param mesDiaAniversarioInicio
	 *            MMDD
	 * @param mesDiaAniversarioFinal
	 *            MMDD
	 * @return
	 * @throws BancoobException
	 */
	List<DadosEtiquetaVO> listarPorPessoaLegadoInstituicao(Integer idInstituicao, Short codTipoPessoa, Date dataNascimentoInicio,
			Date dataNascimentoFinal, Character tipoSexo, String mesDiaAniversarioInicio, String mesDiaAniversarioFinal) throws BancoobException;

}
