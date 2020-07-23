/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaIntegracaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.enums.FuncaoEnum;
import br.com.sicoob.capes.comum.negocio.vo.PessoaPlataformaVO;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.legado.Cooperativa;
import br.com.sicoob.capes.negocio.entidades.legado.Funcionario;
import br.com.sicoob.capes.replicacao.negocio.delegates.CAPESReplicacaoComumFabricaDelegates;
import br.com.sicoob.capes.replicacao.negocio.delegates.CooperativaDelegate;
import br.com.sicoob.capes.replicacao.negocio.delegates.FuncionarioDelegate;

/**
 * @author Erico.Junior
 * 
 */
public class AtualizacaoFuncionarioFachada {

	private transient PessoaIntegracaoDelegate pessoaIntegracaoDelegate = 
			CAPESCadastroFabricaDelegates.getInstance().criarPessoaIntegracaoDelegate();
	
	private transient CooperativaDelegate cooperativaDelegate = 
			CAPESReplicacaoComumFabricaDelegates.getInstance().criarCooperativaDelegate();
	
	public void incluirFuncionarioLegado(br.com.sicoob.capes.negocio.entidades.Funcionario objeto) 
			throws BancoobException {
		
		Integer idInstituicao = obterIdInstituicao(objeto);
		Funcionario funcionario = criarReplicacao(objeto, idInstituicao);
		obterFuncionarioDelegate().incluir(funcionario, idInstituicao);
	}

	/**
	 * 
	 * @param objeto
	 * @throws BancoobException
	 */
	public void alterarFuncionarioLegado(br.com.sicoob.capes.negocio.entidades.Funcionario objeto) 
			throws BancoobException {
		
		Integer idInstituicao = obterIdInstituicao(objeto);
		Funcionario funcionarioLegado =	criarReplicacao(objeto, idInstituicao);
		obterFuncionarioDelegate().alterar(funcionarioLegado, idInstituicao);
	}

	/**
	 * 
	 * @param objeto
	 * @throws BancoobException
	 */
	public void excluirFuncionarioLegado(br.com.sicoob.capes.negocio.entidades.Funcionario objeto) 
			throws BancoobException {
		
		Integer idInstituicao = obterIdInstituicao(objeto);
		Integer idPessoaLegado = obterPessoaProxy(objeto.getPessoa(), idInstituicao).getIdPessoaLegado();
	
		Funcionario funcionario = obterFuncionarioDelegate().obter(idPessoaLegado, idInstituicao);
		if(funcionario != null) {
			obterFuncionarioDelegate().excluir(funcionario, idInstituicao);
		}
	}
	
	/**
	 * Cria um {@link Funcionario} para replica??o na base SQL
	 * @param capes
	 * @return
	 * @throws BancoobException
	 */
	private Funcionario criarReplicacao(
			br.com.sicoob.capes.negocio.entidades.Funcionario capes, Integer idInstituicao) 
					throws BancoobException {

		Integer idPessoaLegado = obterPessoaProxy(capes.getPessoa(), idInstituicao).getIdPessoaLegado();
		Funcionario funcionario = obterFuncionarioDelegate().obter(idPessoaLegado, idInstituicao);
		
		if(funcionario == null){
			funcionario = new Funcionario();
			funcionario.setIdFuncionario(idPessoaLegado);
		}
		
		funcionario.setBolGerente(FuncaoEnum.GERENTE.getIdTipoFuncao().equals(capes.getFuncao().getId()));
		funcionario.setDataAdmissao(capes.getDataAdmissao());
		funcionario.setDataDesligamento(capes.getDataDesligamento());
		funcionario.setIdFuncao(capes.getFuncao().getId());
		funcionario.setMatricula(capes.getMatricula());
		funcionario.setNumNucleo(capes.getNucleo().getNumNucleo());
		funcionario.setNumPac(obterNumPacFuncionarioLegado(capes));
		return funcionario;
	}	
	
	/**
	 * @return delegate para replicação de {@link Funcionario}
	 */
	private FuncionarioDelegate obterFuncionarioDelegate() {
		return CAPESReplicacaoComumFabricaDelegates.getInstance().criarFuncionarioDelegate();
	}

	/**
	 * Busca o numero do PAC na base SQL para o funcionario
	 * 
	 * @param objeto
	 * @return
	 * @throws BancoobException
	 */
	private Integer obterNumPacFuncionarioLegado(
			br.com.sicoob.capes.negocio.entidades.Funcionario objeto) throws BancoobException{

		Instituicao instituicao = objeto.getInstituicao();
		
		Cooperativa cooperativa = new Cooperativa();
		cooperativa.setIdInstituicao(instituicao.getIdInstituicao());
		cooperativa.setIdUnidadeInst(instituicao.getIdUnidadeInst());
		
		ConsultaDto<Cooperativa> criterios = new ConsultaDto<Cooperativa>();
		criterios.setFiltro(cooperativa);
		
		List<Cooperativa> cooperativas = cooperativaDelegate.listar(criterios, instituicao.getIdInstituicao()); 
		Integer numPac = null;
		
		if(CollectionUtils.isNotEmpty(cooperativas) ){
			numPac = cooperativas.get(0).getId().getNumPac();
		}
		
		return numPac;

	}

	/**
	 * Obter pessoa proxy.
	 *
	 * @param pessoa o valor de pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @return PessoaPlataformaVO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private PessoaPlataformaVO obterPessoaProxy(Pessoa pessoa, Integer idInstituicao) throws BancoobException {
		return pessoaIntegracaoDelegate.obterPessoaPlataforma(pessoa.getId(), idInstituicao);
	}	
	
	private Integer obterIdInstituicao(br.com.sicoob.capes.negocio.entidades.Funcionario funcionario) {
		return funcionario.getInstituicao().getIdInstituicao();
	}
}