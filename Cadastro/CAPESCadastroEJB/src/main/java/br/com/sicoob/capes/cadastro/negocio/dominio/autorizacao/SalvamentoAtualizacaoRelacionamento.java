// 22/02/2013 - 08:40:56
package br.com.sicoob.capes.cadastro.negocio.dominio.autorizacao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;

/**
 * 
 * @author Marcelo.Onofre
 */
public class SalvamentoAtualizacaoRelacionamento extends SalvamentoAtualizacaoGenerico {

	/**
	 * Cria o objeto {@code aprovavel}
	 * 
	 * @param aprovavel
	 *            O objeto a ser copiado
	 * @param instAtualizacao
	 *            a instituição para preenchimento do
	 *            {@link Aprovavel#setIdInstituicaoAtualizacao(Integer)}
	 * @param classeAprovavel
	 *            A classe do objeto {@code aprovavel}
	 * @return o novo objeto
	 */
	@Override
	protected Aprovavel criarNovoRegistro(final Aprovavel aprovavel, final Instituicao instAtualizacao, final Class<Aprovavel> classeAprovavel) {
		RelacionamentoPessoa novoRegistro = (RelacionamentoPessoa) super.criarNovoRegistro(aprovavel, instAtualizacao, classeAprovavel);
		novoRegistro.setIdRelacionamentoAntigo(aprovavel.getId());
		return novoRegistro;
	}

	@Override
	protected Instituicao obterInstituicaoDestino(Aprovavel aprovavel) throws BancoobException {
		RelacionamentoPessoa reg = (RelacionamentoPessoa) aprovavel;
		if (!reg.getTipoRelacionamento().getPermiteCompartilhamento()) {
			// Retorna instituição do usuário logado.
			return obterInstituicaoUsuarioLogado();
		}
		// Retorna responsável pelo cadastro.
		return super.obterInstituicaoDestino(aprovavel);
	}
}