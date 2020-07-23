package br.com.sicoob.capes.api.inclusao.negocio.conversores;

import java.math.BigDecimal;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.inclusao.negocio.dto.FonteRendaDTO;
import br.com.sicoob.capes.negocio.entidades.TipoFonteRenda;
import br.com.sicoob.capes.negocio.entidades.vigente.FonteRenda;

/**
 * A Classe ConversorFonteRenda.
 * 
 * @author bruno.carneiro
 */
public class ConversorFonteRenda extends ConversorAbstrato<FonteRenda, FonteRendaDTO> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void definirInformacoesAdicionais(FonteRenda entidade, FonteRendaDTO dto) throws BancoobException {
		super.definirInformacoesAdicionais(entidade, dto);

		TipoFonteRenda tipoFonteRenda = new TipoFonteRenda();
		tipoFonteRenda.setCodigo(dto.getCodigoTipoFonteRenda());
		entidade.setTipoFonteRenda(tipoFonteRenda);

		if (dto.getIdPessoaEmpregador() != null) {
			entidade.setPessoaEmpregador(obterPessoaCompartilhamento(dto.getIdPessoaEmpregador(), dto.getIdInstituicao()));
		}

		entidade.setDataValidadeRenda(criarDateTimeDB(dto.getDataValidadeRenda()));
		
		if(dto.getValorReceitaBrutaMensal() == null) {
			entidade.setValorReceitaBrutaMensal(BigDecimal.ZERO);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void definirInformacoesAdicionais(FonteRendaDTO dto, FonteRenda entidade) throws BancoobException {
		super.definirInformacoesAdicionais(dto, entidade);
		
		dto.setCodigoTipoFonteRenda(entidade.getTipoFonteRenda().getCodigo());
	}

}