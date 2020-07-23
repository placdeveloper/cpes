package br.com.sicoob.capes.cadastro.negocio.delegates.bemantigo;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.delegates.EntidadeCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.servicos.bemantigo.BemAntigoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.bemantigo.Bem;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * 
 * @author Juan.Damasceno
 *
 */
public class BemAntigoDelegate extends EntidadeCadastroDelegate<Bem, BemAntigoServico>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemAntigoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarBemAntigoServico();
	}
	
	/**
	 * Lista os bens usando a pessoa como filtro.
	 * @param pessoa a pessoa que será usada como filtro.
	 * @return os bens usando a pessoa como filtro.
	 * @throws BancoobException 
	 */
	public List<Bem> listarPorPessoa(PessoaCompartilhamento pessoa) throws BancoobException {
		return getServico().listarPorPessoa(pessoa);
	}
	
	/**
	 * Lista os bens imoveis rurais usando a pessoa como filtro.
	 * @param pessoa a pessoa que será usada como filtro.
	 * @return os bens usando a pessoa como filtro.
	 */
	public List<Bem> listarBemImovelRuralPorPessoa(PessoaCompartilhamento pessoa) throws BancoobException {
		return getServico().listarBemImovelRuralPorPessoa(pessoa);
	}
	
	/**
	 * Lista os bens imoveis rurais vigentes usando a pessoa como filtro.
	 * @param pessoa a pessoa que será usada como filtro.
	 * @return os bens usando a pessoa como filtro.
	 */
	public List<Bem> listarBemImovelRuralVigentePorPessoa(PessoaCompartilhamento pessoa) throws BancoobException {
		return getServico().listarBemImovelRuralVigentePorPessoa(pessoa);
	}

	/**
	 * Listar bem imovel por pessoa.
	 *
	 * @param consultaDto o valor de consulta dto
	 * @return List
	 */
	public List<Bem> listarBemImovelPorPessoa(ConsultaDto<?> consultaDto) {
		return getServico().listarBemImovelPorPessoa(consultaDto);
	}

	/**
	 * Listar por bens diferentes de imovel por pessoa.
	 *
	 * @param consultaDto o valor de consulta dto
	 * @return List
	 */
	public List<Bem> listarPorBensDiferentesDeImovelPorPessoa(ConsultaDto<?> consultaDto) {
		return getServico().listarPorBensDiferentesDeImovelPorPessoa(consultaDto);
	}
	
	/**
	 * Importa o bem para pessoa;
	 * @param bem
	 * @return
	 * @throws BancoobException
	 */
	public Bem importar(Bem bem) throws BancoobException{ 
		return getServico().importar(bem);
	}

	/**
	 * Cria um registro com o subtipo de "Nao possui patrimonio"
	 * @param idPessoaCompartilhamento
	 * @throws BancoobException
	 */
	public void criarRegistroSemPatrimonio(Long idPessoaCompartilhamento) throws BancoobException {
		getServico().criarRegistroSemPatrimonio(idPessoaCompartilhamento);
    }
}