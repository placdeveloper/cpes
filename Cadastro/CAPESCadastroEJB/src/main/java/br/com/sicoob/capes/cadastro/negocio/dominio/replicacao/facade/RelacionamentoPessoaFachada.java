package br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade;

import java.util.Collection;
import java.util.Iterator;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.TransicaoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;

/**
 * A Classe RelacionamentoPessoaFachada.
 */
public class RelacionamentoPessoaFachada extends AtualizacaoCadastralFachada<RelacionamentoPessoa> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Collection<TransicaoPessoa> obterTransicoes(RelacionamentoPessoa entidade) throws BancoobException {
		Collection<TransicaoPessoa> transicoes = super.obterTransicoes(entidade);
		Iterator<TransicaoPessoa> it = transicoes.iterator();
		while (it.hasNext()) {
			TransicaoPessoa transicao = it.next();
			if (!entidade.getTipoRelacionamento().getPermiteCompartilhamento()) {
				if (!transicao.getInstituicao().getIdInstituicao().equals(entidade.getIdInstituicao())) {
					it.remove();
				}
			}
		}
		return transicoes;
	}
	
}