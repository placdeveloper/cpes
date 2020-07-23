/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.sicoob.capes.cadastro.negocio.dominio.GrupoCompartilhamentoCache;
import br.com.sicoob.capes.cadastro.negocio.excecao.GrupoCompartilhamentoInexistenteException;
import br.com.sicoob.capes.cadastro.negocio.servicos.GrupoCompartilhamentoServico;
import br.com.sicoob.capes.comum.negocio.contexto.InformacoesUsuarioCAPES;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * @author erico.junior
 *
 */
public class GrupoCompartilhamentoInterceptor {
	
	/** O atributo logger. */
	private transient ISicoobLogger logger = SicoobLoggerPadrao.getInstance(this.getClass());

	/**
	 * Intercepta a chamada ao Serviço EJB.
	 * 
	 * @param contexto
	 *            o contexto de execução do EJB.
	 * @return o retorno do Serviço EJB.
	 * @throws BancoobException
	 *             caso alguma exceção ocorra.
	 */
	@AroundInvoke
	public Object intercept(InvocationContext contexto) throws Exception {

		logger.debug("GrupoCompartilhamentoInterceptor.intercept: inicio");

		InformacoesUsuario informacoes = InformacoesUsuario.getInstance();	
		InformacoesUsuarioCAPES informacoesCapes = InformacoesUsuarioCAPES.getInstance();
		if ((informacoesCapes != null) && !informacoesCapes.equals(informacoes)) {
			InformacoesUsuarioCAPES.removeInstance();
		}
		
		if (!(contexto.getTarget() instanceof GrupoCompartilhamentoServico)
				&& (informacoes != null) && (informacoes.getIdInstituicao() != null)) {
			Integer idInstituicao = Integer.valueOf(informacoes.getIdInstituicao());
			Short codigo = recuperarCodigoCompartilhamento(idInstituicao);

			logger.debug("GrupoCompartilhamentoInterceptor.intercept: compartilhamento = ",
					codigo + ", instituicao = " + idInstituicao);

			informacoesCapes = new InformacoesUsuarioCAPES(informacoes, codigo);
			InformacoesUsuarioCAPES.setInstance(informacoesCapes);
		}
		logger.debug("GrupoCompartilhamentoInterceptor.intercept: fim");
		
		return contexto.proceed();
	}

	/**
	 * Recuperar codigo compartilhamento.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @return Short
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private Short recuperarCodigoCompartilhamento(Integer idInstituicao) throws BancoobException {
		
		GrupoCompartilhamentoCache cache = GrupoCompartilhamentoCache.getInstance();
		Short codigo = cache.recuperarCodigoCompartilhamento(idInstituicao);

		if(codigo == null) {
			throw new GrupoCompartilhamentoInexistenteException();
		}
		
		return codigo; 
	}
}