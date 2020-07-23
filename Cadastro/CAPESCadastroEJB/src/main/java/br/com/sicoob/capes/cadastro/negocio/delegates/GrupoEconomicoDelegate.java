/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.GrupoEconomicoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.cadastro.negocio.vo.StatusTransferenciaGrupoEconomicoVO;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomico;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao;

/**
 * Business delegate para grupo economico.  
 * @author juan.damasceno
 */
public class GrupoEconomicoDelegate extends
	CAPESCadastroCrudDelegate<GrupoEconomico, GrupoEconomicoServico> {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected GrupoEconomicoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarGrupoEconomicoServico();
	}
	
	/**
	 * Listar por pessoa.
	 *
	 * @param pessoa o valor de pessoa
	 * @return List
	 */
	public List<GrupoEconomico> listarPorPessoa(Pessoa pessoa) {
		return getServico().listarPorPessoa(pessoa);
	}

	/**
	 * O método Validarpessoa.
	 *
	 * @param grupoEconomico o valor de grupo economico
	 * @param pessoa o valor de pessoa
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void validarpessoa(GrupoEconomico grupoEconomico, PessoaInstituicao pessoa)
			throws BancoobException {
		getServico().validarPessoa(grupoEconomico, pessoa);
	}
	
	/**
	 * Obter status transferencia grupo economico.
	 *
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<StatusTransferenciaGrupoEconomicoVO> obterStatusTransferenciaGrupoEconomico() throws BancoobException{
		return getServico().obterStatusTransferenciaGrupoEconomico();
	}

	public List<GrupoEconomico> obterListaGrupoEconomico(Integer idInstituicao) throws BancoobException {
		return getServico().obterListaGrupoEconomico(idInstituicao);
	}

}