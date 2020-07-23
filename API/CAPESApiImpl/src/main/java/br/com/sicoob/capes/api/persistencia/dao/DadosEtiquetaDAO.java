package br.com.sicoob.capes.api.persistencia.dao;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.DadosEtiquetaVO;

/**
 * A Interface DadosEtiquetaDAO.
 */
public interface DadosEtiquetaDAO extends CAPESApiDaoIF<DadosEtiquetaVO> {
	
	/**
	 * Listar dados etiqueta.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @param listaIdPessoaLegado o valor de lista id pessoa legado
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<DadosEtiquetaVO> listarDadosEtiqueta(Integer idInstituicao, List<Integer> listaIdPessoaLegado) throws BancoobException;
	
	/**
	 * Listar por pessoa legado instituicao.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @param codTipoPessoa o valor de cod tipo pessoa
	 * @param dataNascimentoInicio o valor de data nascimento inicio
	 * @param dataNascimentoFinal o valor de data nascimento final
	 * @param tipoSexo o valor de tipo sexo
	 * @param mesDiaAniversarioInicio o valor de mes dia aniversario inicio
	 * @param mesDiaAniversarioFinal o valor de mes dia aniversario final
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<DadosEtiquetaVO> listarPorPessoaLegadoInstituicao(Integer idInstituicao, Short codTipoPessoa, Date dataNascimentoInicio, Date dataNascimentoFinal,
			Character tipoSexo, String mesDiaAniversarioInicio, String mesDiaAniversarioFinal) throws BancoobException;

}