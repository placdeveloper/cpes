package br.com.sicoob.capes.cadastro.negocio.dominio.validacao;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.capes.cadastro.negocio.excecao.CampoNaoInformadoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.FormatoInvalidoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.TamanhoCampoInvalidoException;
import br.com.sicoob.capes.negocio.entidades.TipoEmail;
import br.com.sicoob.capes.negocio.entidades.vigente.Email;

/**
 * Classe para validação do E-mail.
 *
 * @author Bruno.Carneiro
 */
public class ValidacaoEmail extends ValidacaoContatoCooperativa<Email> {
	
	private static final int TAMANHO_DESCRICAO = 100; 

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void validarTamanhoCampos(Email entidade) throws NegocioException {
		if (entidade.getDescricao() != null && entidade.getDescricao().length() > TAMANHO_DESCRICAO) {
			throw new TamanhoCampoInvalidoException("Descrição", TAMANHO_DESCRICAO);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void validarCamposObrigatorios(Email entidade) throws BancoobException {
		TipoEmail tipoEmail = entidade.getTipoEmail();
		if (tipoEmail == null || tipoEmail.getCodigo() == null) {
			throw new CampoNaoInformadoException("Tipo de e-mail");
		}

		if (StringUtils.isBlank(entidade.getDescricao())) {
			throw new CampoNaoInformadoException("Descrição");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void validarNegocio(Email entidade) throws BancoobException {
		validarPadraoEmail(entidade);
	}
	
	/**
	 * Valida o formato do e-mail
	 * 
	 * @param entidade
	 * @throws BancoobException
	 */
	private void validarPadraoEmail(Email entidade) throws BancoobException {
		String nomeCampo = "E-mail";
		String email = entidade.getDescricao();
		if (email != null) {
			if (!email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
				throw new FormatoInvalidoException(nomeCampo);
			}
		} else {
			throw new CampoNaoInformadoException(nomeCampo);
		}
	}
	
}