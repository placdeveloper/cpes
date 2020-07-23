package br.com.sicoob.capes.api.inclusao.negocio.conversores;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.inclusao.negocio.dto.EmailDTO;
import br.com.sicoob.capes.negocio.entidades.TipoEmail;
import br.com.sicoob.capes.negocio.entidades.vigente.Email;

/**
 * A Classe ConversorEmail.
 * 
 * @author bruno.carneiro
 */
public class ConversorEmail extends ConversorAbstrato<Email, EmailDTO> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void definirInformacoesAdicionais(Email entidade, EmailDTO dto) throws BancoobException {
		super.definirInformacoesAdicionais(entidade, dto);

		TipoEmail tipoEmail = new TipoEmail();
		tipoEmail.setCodigo(dto.getCodigoTipoEmail());
		entidade.setTipoEmail(tipoEmail);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void definirInformacoesAdicionais(EmailDTO dto, Email entidade) throws BancoobException {
		super.definirInformacoesAdicionais(dto, entidade);

		dto.setCodigoTipoEmail(entidade.getTipoEmail().getCodigo());
	}
}