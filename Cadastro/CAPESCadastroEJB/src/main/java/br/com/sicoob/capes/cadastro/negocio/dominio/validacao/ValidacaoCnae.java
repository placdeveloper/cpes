/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.validacao;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.excecao.CnaeNaoInformadoException;
import br.com.sicoob.capes.negocio.entidades.CnaeFiscal;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;


/**
 * @author erico.junior
 *
 */
public class ValidacaoCnae implements Validacao<PessoaCompartilhamento> {

	/** O atributo delegate. */
	private transient PessoaCompartilhamentoDelegate delegate = 
			CAPESCadastroFabricaDelegates.getInstance().criarPessoaCompartilhamentoDelegate();

	
	/**
	 * {@inheritDoc}
	 */
	public void validar(PessoaCompartilhamento pessoa)
			throws BancoobException {

		PessoaCompartilhamento pessoaCompartilhamento = 
				delegate.obter(pessoa.getIdPessoaCompartilhamento());
		
		CnaeFiscal cnae = pessoaCompartilhamento.getCnae();
		if(cnae == null || StringUtils.isEmpty(cnae.getCodigo())) {
			throw new CnaeNaoInformadoException();
		}		
	}

}
