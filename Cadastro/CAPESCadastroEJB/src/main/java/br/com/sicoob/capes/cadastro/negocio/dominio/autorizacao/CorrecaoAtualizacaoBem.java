package br.com.sicoob.capes.cadastro.negocio.dominio.autorizacao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizacaoCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.BemDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.BemPessoaCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroCrudDelegate;
import br.com.sicoob.capes.comum.negocio.enums.TipoOperacaoEnum;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.bem.Bem;
import br.com.sicoob.capes.negocio.entidades.bem.BemPessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;

/**
 * Estratégia para a correção das atualizações do Bem
 * 
 * @author Bruno.Carneiro
 */
public class CorrecaoAtualizacaoBem extends CorrecaoAtualizacaoGenerica {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected <A extends CAPESEntidade<Long> & Aprovavel> void corrigirAtualizacao(Autorizacao autorizacao, A aprovavel) throws BancoobException {
		Bem bem = (Bem) aprovavel;
		aprovavel.setVerificarAutorizacao(false);
		aprovavel.setGravarHistorico(false);
		CAPESCadastroCrudDelegate<CAPESEntidade<?>, ?> delegate = criarDelegate(autorizacao.getTipoAutorizacao());
		delegate.alterar(bem);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void excluirAtualizacao(Autorizacao autorizacao, Aprovavel aprovavel) throws BancoobException {
		Bem bem = (Bem) aprovavel;
		BemDelegate delegate = fabricaComum.criarBemDelegate();

		bem.setVerificarAutorizacao(false);
		((Vigente) bem).setGravarHistorico(false);

		// se a autorizacao não foi originada por uma inclusão
		if (!TipoOperacaoEnum.I.equals(autorizacao.getTipoOperacao())) {
			Bem registroOrigem = delegate.obter(autorizacao.getIdRegistroAntigo());
			delegate.marcarEmAlteracao(registroOrigem, null);
			
			BemPessoaCompartilhamentoDelegate bemPessoaCompartilhamentoDelegate = fabricaComum.criarBemPessoaCompartilhamentoDelegate();
			for(BemPessoaCompartilhamento bemPessoaCompartilhamento : registroOrigem.getProprietarios()){
				bemPessoaCompartilhamentoDelegate.marcarEmAlteracao(bemPessoaCompartilhamento, null);
			}
		}

		AutorizacaoCadastroDelegate autorizacaoCadastroDelegate = fabricaComum.criarAutorizacaoCadastroDelegate();
		autorizacaoCadastroDelegate.excluirAutorizacaoRejeitada(autorizacao);
		delegate.excluirEntidade(bem);
	}
	
}