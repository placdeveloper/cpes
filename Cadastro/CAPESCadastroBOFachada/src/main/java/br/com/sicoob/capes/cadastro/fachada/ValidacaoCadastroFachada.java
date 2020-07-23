package br.com.sicoob.capes.cadastro.fachada;

import java.util.List;

import javax.ejb.EJBTransactionRolledbackException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.persistence.QueryTimeoutException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.excecao.ViolacaoIntegridadeException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.ValidacaoCadastralDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.ValidacaoCadastralErroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.ValidacaoCadastralRegraDelegate;
import br.com.sicoob.capes.cadastro.negocio.vo.ValidacaoCadastralVO;
import br.com.sicoob.capes.comum.negocio.enums.TipoRegraValidacaoCadastralEnum;
import br.com.sicoob.capes.negocio.entidades.ValidacaoCadastral;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * A Classe ValidacaoCadastroFachada.
 */
@RemoteService
public class ValidacaoCadastroFachada extends CAPESCadastroBOFachada {
	
	/**
	 * Obtém as definições para exibição no componente de validação cadastral
	 */
	public RetornoDTO obterDefinicoesComponente(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		try {
			
			Integer idInstituicao = (Integer) dto.getDados().get("idInstituicao");
			Number idPessoaCompartilhamento = (Number) dto.getDados().get("idPessoaCompartilhamento");
			String tipoRegra = (String) dto.getDados().get("tipoRegra");
			Boolean considerarOrdemPerfilCadastro = (Boolean) dto.getDados().get("considerarOrdemPerfilCadastro");
			TipoRegraValidacaoCadastralEnum tipoRegraEnum = null;
			
			if(tipoRegra != null) {
				tipoRegraEnum = TipoRegraValidacaoCadastralEnum.valueOf(tipoRegra.charAt(0));
			}
			
			PessoaCompartilhamento pessoaCompartilhamento = CAPESCadastroFabricaDelegates.getInstance().criarPessoaCompartilhamentoDelegate().obter(idPessoaCompartilhamento.longValue());
			
			ValidacaoCadastralDelegate validacaoCadastralDelegate = CAPESCadastroFabricaDelegates.getInstance().criarValidacaoCadastralDelegate();
			ValidacaoCadastral validacaoCadastral = validacaoCadastralDelegate.obter(pessoaCompartilhamento.getId());
			
			ValidacaoCadastralErroDelegate validacaoCadastralErroDelegate = CAPESCadastroFabricaDelegates.getInstance().criarValidacaoCadastralErroDelegate();
			
			List<ValidacaoCadastralVO> listaFalhas = (considerarOrdemPerfilCadastro)
						? validacaoCadastralErroDelegate.listarFalhasRegrasValidacaoCadastralPerfilCadastro(pessoaCompartilhamento.getId(), idInstituicao, tipoRegraEnum, pessoaCompartilhamento.getPerfilCadastro().getId())
						: validacaoCadastralErroDelegate.listarFalhasRegrasValidacaoCadastral(pessoaCompartilhamento.getId(), idInstituicao, tipoRegraEnum);
														 
			
			if(validacaoCadastral != null){
				retorno.getDados().put("dataUltimaAtualizacao", validacaoCadastral.getDataHoraUltimaAtualizacao());
				retorno.getDados().put("dataUltimaValidacao", validacaoCadastral.getDataHoraUltimaValidacao());
			}
			
			retorno.getDados().put("listaFalhas", listaFalhas);
			
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
		return retorno;
	}
	
//	/**
//	 * Obtém as definições para exibição no componente de validação cadastral de acordo com perfil cadastrado
//	 */
//	public RetornoDTO obterDefinicoesComponentePerfilCadastro(RequisicaoReqDTO dto) throws BancoobException {
//		RetornoDTO retorno = new RetornoDTO();
//		
//		Integer idInstituicao = (Integer) dto.getDados().get("idInstituicao");
//		Integer idPessoa = (Integer) dto.getDados().get("idPessoa");
//		String tipoRegra = (String) dto.getDados().get("tipoRegra");
//		TipoRegraValidacaoCadastralEnum tipoRegraEnum = null;
//		
//		if(tipoRegra != null) {
//			tipoRegraEnum = TipoRegraValidacaoCadastralEnum.valueOf(tipoRegra.charAt(0));
//		}
//		
//		PessoaDelegate pessoaDelegate = CAPESCadastroFabricaDelegates.getInstance().criarPessoaDelegate();
//		Pessoa pessoa = pessoaDelegate.obter(idPessoa);
//		
//		ValidacaoCadastralDelegate validacaoCadastralDelegate = CAPESCadastroFabricaDelegates.getInstance().criarValidacaoCadastralDelegate();
//		ValidacaoCadastral validacaoCadastral = validacaoCadastralDelegate.obter(pessoa.getPessoaCompartilhamento().getId());
//		
//		ValidacaoCadastralErroDelegate validacaoCadastralErroDelegate = CAPESCadastroFabricaDelegates.getInstance().criarValidacaoCadastralErroDelegate();
//		List<ValidacaoCadastralVO> listaFalhas = validacaoCadastralErroDelegate.listarFalhasRegrasValidacaoCadastral(idPessoa, idInstituicao, tipoRegraEnum);
//		
//		if(validacaoCadastral != null){
//			retorno.getDados().put("dataUltimaAtualizacao", validacaoCadastral.getDataHoraUltimaAtualizacao());
//			retorno.getDados().put("dataUltimaValidacao", validacaoCadastral.getDataHoraUltimaValidacao());
//		}
//		
//		retorno.getDados().put("listaFalhas", listaFalhas);
//		
//		return retorno;
//	}

	/**
	 * Executa a revalidação do cadastro do cliente
	 * 
	 * @param dto
	 * @throws BancoobException
	 */
	public void revalidarCadastro(RequisicaoReqDTO dto) throws BancoobException {
		Integer idPessoa = (Integer) dto.getDados().get("idPessoa");
		Integer idInstituicao = (Integer) dto.getDados().get("idInstituicao");
		ValidacaoCadastralRegraDelegate delegateRegra = CAPESCadastroFabricaDelegates.getInstance().criarValidacaoCadastralRegraDelegate();
		/*
		 * delegateRegra.revalidarCadastroPerfilCadastro(idPessoa);
		 * 
		 * retirado devido a algumas pendências em outros pontos assim nao sera
		 * feito revalidacao somente de acordo com o perfil, mas sim, sera
		 * aplicadas todas as regras e a exibicao sera de acordo com o perfil do
		 * cadastro
		 */
		try {
			delegateRegra.revalidarCadastro(idPessoa, idInstituicao);
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
	}

}