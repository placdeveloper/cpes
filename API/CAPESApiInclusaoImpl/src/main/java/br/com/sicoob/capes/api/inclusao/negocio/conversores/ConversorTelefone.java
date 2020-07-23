package br.com.sicoob.capes.api.inclusao.negocio.conversores;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.inclusao.negocio.dto.TelefoneDTO;
import br.com.sicoob.capes.negocio.entidades.TipoTelefone;
import br.com.sicoob.capes.negocio.entidades.vigente.Telefone;

/**
 * A Classe ConversorTelefone.
 * 
 * @author bruno.carneiro
 */
public class ConversorTelefone extends ConversorAbstrato<Telefone, TelefoneDTO> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void definirInformacoesAdicionais(Telefone entidade, TelefoneDTO dto) throws BancoobException {
		super.definirInformacoesAdicionais(entidade, dto);

		TipoTelefone tipoTelefone = new TipoTelefone();
		tipoTelefone.setId(dto.getCodigoTipoTelefone());

		entidade.setTipoTelefone(tipoTelefone);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void definirInformacoesAdicionais(TelefoneDTO dto, Telefone entidade) throws BancoobException {
		super.definirInformacoesAdicionais(dto, entidade);
		dto.setCodigoTipoTelefone(entidade.getTipoTelefone().getCodigo());
	}

}