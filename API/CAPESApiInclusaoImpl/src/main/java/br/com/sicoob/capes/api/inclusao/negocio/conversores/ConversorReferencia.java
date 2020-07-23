package br.com.sicoob.capes.api.inclusao.negocio.conversores;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.inclusao.negocio.dto.ReferenciaDTO;
import br.com.sicoob.capes.negocio.entidades.TipoReferencia;
import br.com.sicoob.capes.negocio.entidades.vigente.Referencia;

/**
 * A Classe ConversorReferencia.
 * 
 * @author bruno.carneiro
 */
public class ConversorReferencia extends ConversorAbstrato<Referencia, ReferenciaDTO> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void definirInformacoesAdicionais(Referencia entidade, ReferenciaDTO dto) throws BancoobException {
		super.definirInformacoesAdicionais(entidade, dto);

		TipoReferencia tipoReferencia = new TipoReferencia();
		tipoReferencia.setCodigo(dto.getCodigoTipoReferencia());
		entidade.setTipoReferencia(tipoReferencia);

		entidade.setPessoa(obterPessoaCompartilhamento(dto.getIdPessoa(), dto.getIdInstituicao()));

		if (dto.getIdPessoaReferencia() != null) {
			entidade.setPessoaReferencia(obterPessoaCompartilhamento(dto.getIdPessoaReferencia(), dto.getIdInstituicao()));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void definirInformacoesAdicionais(ReferenciaDTO dto, Referencia entidade) throws BancoobException {
		super.definirInformacoesAdicionais(dto, entidade);

		dto.setCodigoTipoReferencia(entidade.getTipoReferencia().getCodigo());
	}
}