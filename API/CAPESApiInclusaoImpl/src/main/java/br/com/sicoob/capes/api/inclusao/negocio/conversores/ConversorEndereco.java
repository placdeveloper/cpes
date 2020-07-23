package br.com.sicoob.capes.api.inclusao.negocio.conversores;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.inclusao.negocio.dto.EnderecoDTO;
import br.com.sicoob.capes.negocio.entidades.Localidade;
import br.com.sicoob.capes.negocio.entidades.TipoEndereco;
import br.com.sicoob.capes.negocio.entidades.TipoLogradouro;
import br.com.sicoob.capes.negocio.entidades.vigente.Endereco;

/**
 * A Classe ConversorEndereco.
 * 
 * @author bruno.carneiro
 */
public class ConversorEndereco extends ConversorAbstrato<Endereco, EnderecoDTO> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void definirInformacoesAdicionais(Endereco entidade, EnderecoDTO dto) throws BancoobException {
		super.definirInformacoesAdicionais(entidade, dto);

		TipoEndereco tipoEndereco = new TipoEndereco();
		tipoEndereco.setCodigo(dto.getCodigoTipoEndereco());
		entidade.setTipoEndereco(tipoEndereco);

		entidade.setTipoLogradouro(new TipoLogradouro(dto.getCodigoTipoLogradouro(), null));
		Localidade localidade = new Localidade();
		localidade.setIdLocalidade(dto.getCodigoLocalidade());
		entidade.setLocalidade(localidade);
		
		if(dto.getNumero() == null || "".equals(dto.getNumero())) {
			entidade.setNumero("S/N");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void definirInformacoesAdicionais(EnderecoDTO dto, Endereco entidade) throws BancoobException {
		super.definirInformacoesAdicionais(dto, entidade);

		dto.setCodigoTipoEndereco(entidade.getTipoEndereco().getCodigo());
		dto.setCodigoTipoLogradouro(entidade.getTipoLogradouro().getIdTipoLogradouro());
		dto.setCodigoLocalidade(entidade.getLocalidade().getIdLocalidade());
	}

}