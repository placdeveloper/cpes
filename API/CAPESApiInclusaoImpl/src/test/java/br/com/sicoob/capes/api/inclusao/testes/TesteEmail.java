package br.com.sicoob.capes.api.inclusao.testes;

import br.com.sicoob.capes.api.inclusao.negocio.dto.EmailDTO;

/**
 * A Classe TesteEmail.
 *
 * @author bruno.carneiro
 */
public class TesteEmail extends CAPESApiInclusaoTesteAbstrato<EmailDTO> {

	/**
	 * Instancia um novo TesteEmail.
	 */
	public TesteEmail() {
		super("EmailServico");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void preencherInformacoesEspecificasDTO(EmailDTO dto) {
		dto.setCodigoTipoEmail((short) 2);
		dto.setDescricao("teste");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void preencherInformacoesAlteracaoDTO(EmailDTO dto) {
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void preencherInformacoesExclusaoDTO(EmailDTO dto) {
		dto.setIdEmail(798214L);
	}

}