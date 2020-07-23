package br.com.sicoob.capes.corporativo.fachada;

import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.bancoob.persistencia.excecao.ViolacaoIntegridadeException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.fachada.BancoobFachada;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.sicoob.capes.cadastro.negocio.excecao.CAPESCadastroNegocioException;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao; 
/**
 * Classe que deve ser herdada pelas Fachadas do sistema.
 * @author Juan.Damasceno
 *
 */
public abstract class CAPESCorporativoFachada extends BancoobFachada {
	
	/**
	 * Obter usuario logado.
	 *
	 * @return UsuarioBancoob
	 */
	protected UsuarioBancoob obterUsuarioLogado() {
		return (UsuarioBancoob) ContextoHttp.getInstance().getUserPrincipal();
	}

	// ************************************************************************************
	//
	// Validar Entidade NullPointerException
	//
	// ************************************************************************************
	
	protected void validarEntidade(Object entidade) throws BancoobException {
		validarEntidade(entidade, "");
	}

	protected void validarEntidade(Object entidade, String msgEntidade) throws BancoobException {
		if (entidade == null) {
			lancarExcessaoValidarEntidade(msgEntidade);
		}
	
		if (entidade instanceof CAPESEntidade) {
			if(((CAPESEntidade<?>) entidade).getId() == null){
				lancarExcessaoValidarEntidade(msgEntidade);
			}
		}
	}
	
	protected void validarEntidade(List<?> listaEntidade, String msgEntidade) throws BancoobException {
		if (listaEntidade == null) {
			lancarExcessaoValidarEntidade(msgEntidade);
		}
	}
	
	protected void lancarExcessaoValidarEntidade(String msgEntidade) throws BancoobException {
		//ThreadContext.put("tipoException", "NullPointerException");

		String msg = String.format(Constantes.Negocio.MENSAGEM_FACHADA_VALIDACAO_DADOS_NULL, " "+ msgEntidade);
		throw new CAPESCadastroNegocioException(msg);
	}
	
	// ************************************************************************************
	//
	// REGISTRAR LOGS EXCEPTIONS
	//
	// ************************************************************************************

	protected void registrarLogNullPointerException(Exception e, Object... parametros) throws BancoobException {
		adicionarLogIndexadores(e, Constantes.Negocio.MENSAGEM_FACHADA_VALIDACAO_DADOS_NULL, "", parametros);
	}

	protected void registrarLogQueryTimeOutException(Exception e, Object... parametros) throws BancoobException {
		adicionarLogIndexadores(e, Constantes.Negocio.MENSAGEM_FACHADA_QUERY_TIMEOUT, " ", parametros);
	}

	protected void registrarLogPersistenceException(Exception e, Object... parametros) throws BancoobException {
		adicionarLogIndexadores(e, Constantes.Negocio.MENSAGEM_FACHADA_COULD_NOT_EXECUTE_QUERY, " ", parametros);
	}

	protected void registrarLogSQLServerException(Exception e, Object... parametros) throws BancoobException {
		adicionarLogIndexadores(e, Constantes.Negocio.MENSAGEM_FACHADA_SQLSERVER, " ", parametros);
	}

	protected void registrarLogException(Exception e, Object... parametros) throws BancoobException {
		adicionarLogIndexadores(e, Constantes.Negocio.MENSAGEM_FACHADA_GENERICA, ", " + e.getMessage(), parametros);
	}
	
	protected void registrarLogViolacaoIntegridadeException(ViolacaoIntegridadeException e, Object... parametros)throws BancoobException {
		adicionarLogIndexadores(e, Constantes.Negocio.MENSAGEM_FACHADA_VIOLACAO_INTEGRIDADE, ", " + e.getMessage(), parametros);
	}
	
	protected void registrarLogTransactionRolledbackException(Exception e, Object... parametros)throws BancoobException {
		adicionarLogIndexadores(e, Constantes.Negocio.MENSAGEM_FACHADA_TRANSACTION_ROLLEDBACK, ", " + e.getMessage(), parametros);
	}
	
	protected void registrarLogEntityNotFoundException(Exception e, Object... parametros)throws BancoobException {
		adicionarLogIndexadores(e, Constantes.Negocio.MENSAGEM_FACHADA_ENTITY_NOTFOUND, ", " + e.getMessage(), parametros);
	}
	
	
	
	private void adicionarLogIndexadores(final Exception exception, final String msg, final String msgComplemento, final Object... parametros) throws BancoobException {
		//ThreadContext.put("capesTipoException", exception.getClass().getSimpleName());
		
		if(parametros!=null){
			for (Object parametro : parametros) {
				if (parametro instanceof RequisicaoReqDTO){
					adicionarLogIndexRequisicaoReqDTO((RequisicaoReqDTO) parametro);
				}else if (parametro instanceof CAPESEntidade) {
					adicionarLogIndexCAPESEntidade((CAPESEntidade<?>) parametro);
				}
			}
		}
		
		String logErro = "ERRO FACHADA classe: %s - Parametros: %s" ;
		
//		Logger LOGGER  = LogManager.getLogger(this.getClass().getSimpleName());
//		
//		LOGGER.error(String.format(logErro,
//						this.getClass().getSimpleName() ,
//						ToStringBuilder.reflectionToString(parametros)),
//					exception);
		
		SicoobLoggerPadrao.getInstance(this.getClass())
			.erro(exception, String.format(logErro,
										this.getClass().getSimpleName() ,
										ToStringBuilder.reflectionToString(parametros)));
		
		String msgEx = String.format(msg, msgComplemento);
		throw new CAPESCadastroNegocioException(msgEx);
	}
	
	private void adicionarLogIndexRequisicaoReqDTO(final RequisicaoReqDTO requisicaoReqDTO){
		if (requisicaoReqDTO != null 
			&& requisicaoReqDTO.getDados()!=null
			&& !requisicaoReqDTO.getDados().isEmpty()) {
			for (Entry<String, Object> item : requisicaoReqDTO.getDados().entrySet()) {
				if(item.getValue()!=null){
					if (item.getValue() instanceof CAPESEntidade) {
						adicionarLogIndexCAPESEntidade((CAPESEntidade<?>) item.getValue());
					}
				}
			}
		}
	}
	
	private void adicionarLogIndexCAPESEntidade(final CAPESEntidade<?> entidade){
		//ThreadContext.put("capesEntidade", entidade.getClass().getSimpleName());
		if (entidade != null) {
			if (entidade instanceof Pessoa) {
				//ThreadContext.put("idPessoa", String.valueOf(entidade.getId()));
			} else if (entidade instanceof PessoaCompartilhamento) {
				//ThreadContext.put("idPessoaCompartilhamento", String.valueOf(entidade.getId()));
			} else {
				//ThreadContext.put("capesEntidadeId", String.valueOf(entidade.getId()));
			}
		}
	}
}