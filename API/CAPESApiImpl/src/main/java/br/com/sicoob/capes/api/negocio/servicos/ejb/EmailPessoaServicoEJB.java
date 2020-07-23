/*
 * SICOOB
 * 
 * EmailPessoaServicoEJB.java(br.com.sicoob.capes.api.negocio.servicos.ejb.EmailPessoaServicoEJB)
 */
package br.com.sicoob.capes.api.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.excecao.ParametroNaoInformadoException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.EmailPessoaServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.EmailPessoaServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.EmailPessoaVO;
import br.com.sicoob.capes.api.persistencia.dao.EmailPessoaDAO;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.negocio.entidades.TipoEmail;
import br.com.sicoob.capes.negocio.entidades.vigente.Email;

/**
 * The Class EmailPessoaServicoEJB.
 */
@Stateless
@Local({ EmailPessoaServicoLocal.class })
@Remote({ EmailPessoaServicoRemote.class })
public class EmailPessoaServicoEJB extends AbstractCAPESApiServicoPessoaIncluirEJB<EmailPessoaVO> implements EmailPessoaServicoRemote, EmailPessoaServicoLocal {

	@Inject
	@Default
	private EmailPessoaDAO emailPessoaDAO;
	
	/** 
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void incluirEmail(EmailPessoaVO dto) throws BancoobException {
		validarObrigatoriedadeEmail(dto);

		Email email = instanciarEmail(dto);
		CAPESCadastroFabricaDelegates.getInstance().criarEmailDelegate().incluir(email);
	}

	/**
	 * Instanciar email.
	 * 
	 * @param dto
	 *            the dto
	 * @return email
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	private Email instanciarEmail(EmailPessoaVO dto) throws BancoobException {
		Email email = new Email();
		email.setPessoaCompartilhamento(obterPessoaCompartilhamento(dto.getIdInstituicao(), dto.getCpfCnpj()));
		email.setTipoEmail(new TipoEmail(dto.getCodigoTipoEmail()));
		email.setDescricao(dto.getDescricaoEmail().toLowerCase());
		
		return email;
	}

	/**
	 * Validar obrigatoriedade email.
	 * 
	 * @param dto
	 *            the dto
	 * @throws ParametroNaoInformadoException
	 *             the parametro nao informado exception
	 */
	private void validarObrigatoriedadeEmail(EmailPessoaVO dto) throws ParametroNaoInformadoException {
		if (dto == null) {
			throw new ParametroNaoInformadoException("E-mail");
		}
		if (dto.getCpfCnpj() == null || dto.getCpfCnpj().equals("")) {
			throw new ParametroNaoInformadoException("CPF/CNPJ");
		}
		if (dto.getDescricaoEmail() == null || dto.getDescricaoEmail().equals("")) {
			throw new ParametroNaoInformadoException("Descrição do e-mail");
		}
		if (dto.getCodigoTipoEmail() == null) {
			throw new ParametroNaoInformadoException("Tipo de e-mail");
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EmailPessoaDAO obterDAO() {
		return emailPessoaDAO;
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<EmailPessoaVO> listarEmaisPessoaPorIdPessoaLegadoIdInstituicao(Integer idPessoaLegado, Integer idInstituicao) throws BancoobException {
		return obterDAO().listarEmaisPessoaPorIdPessoaLegadoIdInstituicao(idPessoaLegado, idInstituicao);
	}
}