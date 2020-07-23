package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoNovo;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * 
 * @author Paulo.Stoppa
 *
 */
public interface GrupoEconomicoNovoServico extends CAPESCadastroCrudServico<GrupoEconomicoNovo> {
	
	/**
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 */
	List<GrupoEconomicoNovo> listarPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao);

	/**
	 * Verifica se a pessoa j� faz parte de algum outro grupo econ�mico na mesma institui��o
	 * 
	 * @param grupoEconomico
	 *            o grupo econ�mico ao qual se deseja vincular a pessoa
	 * @param pessoa
	 *            a pessoa que se deseja validar
	 */
	void validarPessoa(final GrupoEconomicoNovo grupoEconomico, final PessoaCompartilhamento pessoa) throws BancoobException;

}