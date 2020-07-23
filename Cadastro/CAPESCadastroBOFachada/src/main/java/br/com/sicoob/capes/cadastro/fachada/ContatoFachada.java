package br.com.sicoob.capes.cadastro.fachada;

import javax.ejb.EJBTransactionRolledbackException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.persistence.QueryTimeoutException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.excecao.ViolacaoIntegridadeException;
import br.com.bancoob.sisbr.componentes.selecaoGeral.dto.DadosSelGeralDTO;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;

/**
 * A Classe ContatoFachada.
 */
@RemoteService
public class ContatoFachada extends CAPESCadastroBOFachada {
	
	/**
	 * Obter dados selecao.
	 *
	 * @param dto o valor de dto
	 * @return DadosSelGeralDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@SuppressWarnings("unchecked")
	public DadosSelGeralDTO obterDadosSelecao(RequisicaoReqDTO dto) throws BancoobException {
		
		DadosSelGeralDTO resultadoDto = new DadosSelGeralDTO();

		try {
			EnderecoFachada enderecoFachada = new EnderecoFachada();
			TelefoneFachada telefoneFachada = new TelefoneFachada();
			EmailFachada emailFachada = new EmailFachada();
			
			DadosSelGeralDTO dadosSelecaoEnd = enderecoFachada.obterDadosSelecao(dto);
			DadosSelGeralDTO dadosSelecaoEmail = emailFachada.obterDadosSelecao(dto);
			DadosSelGeralDTO dadosSelecaoTelefone = telefoneFachada.obterDadosSelecao(dto);
			
			Object listaEnd = dadosSelecaoEnd.getDados().get(NOME_PADRAO_LISTA);
			Object listaEmail = dadosSelecaoEmail.getDados().get(NOME_PADRAO_LISTA);
			Object listaTelefone = dadosSelecaoTelefone.getDados().get(NOME_PADRAO_LISTA);
			
			resultadoDto.getDados().put("listaEnd", listaEnd);
			resultadoDto.getDados().put("listaEmail", listaEmail);
			resultadoDto.getDados().put("listaTelefone", listaTelefone);
		
		} catch (QueryTimeoutException e) {
			registrarLogQueryTimeOutException(e, dto);
		} catch (NullPointerException e) {
			registrarLogNullPointerException(e, dto);
		} catch (ViolacaoIntegridadeException e) {
			registrarLogViolacaoIntegridadeException(e, dto);
		} catch (EJBTransactionRolledbackException e) {
			registrarLogTransactionRolledbackException(e, dto);
		} catch (EntityNotFoundException e) {
			registrarLogEntityNotFoundException(e, dto);
		} catch (PersistenceException e) {
			registrarLogPersistenceException(e, dto);
		} catch (NegocioException e) {
			throw(e);
		} catch (Exception e) {//nosonar
			registrarLogException(e, dto);
		}
		return resultadoDto;
	}

}
