package br.com.sicoob.capes.integracao.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;

/**
 * A interface CTAIntegracaoServico.
 * 
 * @author Bruno.Carneiro
 */
public interface CTAIntegracaoServico extends CAPESIntegracaoServico {

	/**
	 * Verifica se é usuario no grupo autorizador.
	 * 
	 * @param usuarioBancoob
	 *            the usuario bancoob
	 * @param nomeGrupo
	 *            the nome grupo
	 * @return true, se for usuario no grupo autorizador
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	boolean isUsuarioNoGrupoAutorizador(UsuarioBancoob usuarioBancoob, String nomeGrupo) throws BancoobException;

}