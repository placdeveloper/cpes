package br.com.sicoob.capes.cadastro.negocio.estrategias;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.FonteRendaDelegate;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;
import br.com.sicoob.capes.negocio.entidades.vigente.FonteRenda;

/**
 * Estratégia para aprovação de endereços.
 */
public class EstrategiaAutorizacaoAprovarFonteRenda extends EstrategiaAutorizacaoAprovar {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void tratarInclusao(Autorizacao autorizacao, Aprovavel aprovavel) throws BancoobException {
		super.tratarInclusao(autorizacao, aprovavel);
		atualizarTipoEmpresaIdeal((FonteRenda) aprovavel);
	}
	
	@Override
	protected void tratarAlteracao(Autorizacao autorizacao, Aprovavel aprovavel) throws BancoobException {
		super.tratarAlteracao(autorizacao, aprovavel);
		atualizarTipoEmpresaIdeal((FonteRenda) aprovavel);
	}
	
	@Override
	protected void tratarExclusao(Autorizacao autorizacao, Aprovavel aprovavel) throws BancoobException {
		super.tratarExclusao(autorizacao, aprovavel);
	}
	
	private void atualizarTipoEmpresaIdeal(FonteRenda renda)  throws BancoobException{
		atualizarTipoEmpresaIdeal(renda, false);
	}
	
	private void atualizarTipoEmpresaIdeal(FonteRenda renda, boolean exclusao) throws BancoobException{
		FonteRendaDelegate delegate = CAPESCadastroFabricaDelegates.getInstance().criarFonteRendaDelegate();
		delegate.atualizarTipoEmpresaIdeal(renda, exclusao);
	}
}