/*
 * SICOOB
 * 
 * EmailPessoaDelegate.java(br.com.sicoob.capes.api.negocio.delegates.EmailPessoaDelegate)
 */
package br.com.sicoob.capes.api.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.IEmailPessoaDelegate;
import br.com.sicoob.capes.api.negocio.servicos.EmailPessoaServico;
import br.com.sicoob.capes.api.negocio.servicos.locator.CAPESApiServiceLocator;
import br.com.sicoob.capes.api.negocio.vo.EmailPessoaVO;

/**
 * @author Lucas.Borges
 */
public class EmailPessoaDelegate extends
		AbstractCAPESApiPessoaDelegate<EmailPessoaVO,EmailPessoaServico> implements IEmailPessoaDelegate {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected EmailPessoaServico localizarServico() {
		return CAPESApiServiceLocator.getInstance()
				.localizarEmailPessoaServico();
	}
	
	/**
	 * Metodo que lista todos os e-mails VIGENTES e NAO-VIGENTES por pessoa, instituicao
	 * 
	 * @param idPessoa ID pessoa 
	 * @param idInstituicao ID instituicao
	 * @return Lista de {@link EmailPessoaVO}
	 * @throws BancoobException
	 */
	public List<EmailPessoaVO> obterNaoVigentePorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) 
			throws BancoobException{
		return getServico().obterNaoVigenteByPessoaInstituicao(idPessoa, idInstituicao);
	}
	
	/**
	 * Metodo que inclui um e-mail NAO-VIGENTE, para que este passe 
	 * por todo fluxo de autorizacao do GED/GFT.
	 *  
	 * @param email VO com os dados obrigatorios para inclusao de um e-mail
	 * 	<br>
	 * 	Os dados obrigatorios sao:
	 *  <ul>
	 *  	<li>Descricao do e-mail {@link EmailPessoaVO#setDescricaoEmail(String)}</li>
	 *  	<li>Tipo de email {@link EmailPessoaVO#setCodigoTipoEmail(Short)}
	 *  		<br> @see TipoEmailEnum
	 *  	</li>
	 *  	<li>ID da intituicao</li>
	 *  	<li>CPF/CNPJ da pessoa responsavel</li>
	 *  <ul>
	 * @throws BancoobException
	 */
	public void incluir(EmailPessoaVO email)throws BancoobException{
		getServico().incluirEmail(email);
	}
	
	public List<EmailPessoaVO> listarEmaisPessoaPorIdPessoaLegadoIdInstituicao(Integer idPessoaLegado, Integer idInstituicao) throws BancoobException {
		return getServico().listarEmaisPessoaPorIdPessoaLegadoIdInstituicao(idPessoaLegado, idInstituicao);
	}
}
