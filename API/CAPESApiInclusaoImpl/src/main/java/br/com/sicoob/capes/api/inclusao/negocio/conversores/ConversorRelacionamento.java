package br.com.sicoob.capes.api.inclusao.negocio.conversores;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.inclusao.negocio.dto.RelacionamentoDTO;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoRelacionamentoPessoaDelegate;
import br.com.sicoob.capes.negocio.entidades.TipoRelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;

/**
 * O conversor da entidade/DTO de relacionamento.
 * 
 * @author Bruno.Carneiro
 */
public class ConversorRelacionamento extends ConversorAbstrato<RelacionamentoPessoa, RelacionamentoDTO> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void definirInformacoesAdicionais(RelacionamentoPessoa entidade, RelacionamentoDTO dto) throws BancoobException {
		super.definirInformacoesAdicionais(entidade, dto);
		
		entidade.setPessoa(obterPessoa(dto.getIdPessoa()));
		entidade.setPessoaCompartilhamento(obterPessoaCompartilhamento(dto.getIdPessoa(), dto.getIdInstituicao()));
		entidade.setPessoaRelacionada(obterPessoa(dto.getIdPessoaRelacionada()));
		entidade.setTipoRelacionamento(obterTipoRelacionamento(dto.getCodigoTipoRelacionamento()));
		
		entidade.setDataFimMandato(criarDateTimeDB(dto.getDataFimMandato()));
		entidade.setDataInicioMandato(criarDateTimeDB(dto.getDataInicioMandato()));
		entidade.setDataInicioRelacionamento(criarDateTimeDB(dto.getDataInicioRelacionamento()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void definirInformacoesAdicionais(RelacionamentoDTO dto, RelacionamentoPessoa entidade) throws BancoobException {
		super.definirInformacoesAdicionais(dto, entidade);
		
		dto.setId(entidade.getId());
		dto.setCodigoTipoRelacionamento(entidade.getTipoRelacionamento().getId());
		dto.setIdPessoaRelacionada(entidade.getPessoaRelacionada().getId());
		dto.setIdPessoa(entidade.getPessoa().getId());
	}
	
	/**
	 * Obtém o tipo de relacionamento
	 * 
	 * @param codigoTipoRelacionamento
	 * @return
	 * @throws BancoobException
	 */
	private TipoRelacionamentoPessoa obterTipoRelacionamento(Short codigoTipoRelacionamento) throws BancoobException {
		TipoRelacionamentoPessoaDelegate delegate = new TipoRelacionamentoPessoaDelegate();
		return delegate.obter(codigoTipoRelacionamento);
	}
}