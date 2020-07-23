package br.com.sicoob.capes.api.inclusao.negocio.conversores;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.inclusao.negocio.dto.InformacaoProfissionalDTO;
import br.com.sicoob.capes.negocio.entidades.ConselhoRegional;
import br.com.sicoob.capes.negocio.entidades.TipoAfastamento;
import br.com.sicoob.capes.negocio.entidades.TipoFuncionario;
import br.com.sicoob.capes.negocio.entidades.vigente.InformacaoProfissional;

/**
 * A Classe ConversorInformacaoProfissional.
 * 
 * @author bruno.carneiro
 */
public class ConversorInformacaoProfissional extends ConversorAbstrato<InformacaoProfissional, InformacaoProfissionalDTO> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void definirInformacoesAdicionais(InformacaoProfissional entidade, InformacaoProfissionalDTO dto) throws BancoobException {
		super.definirInformacoesAdicionais(entidade, dto);

		if (dto.getCodigoTipoFuncionario() != null) {
			TipoFuncionario tipoFuncionario = new TipoFuncionario();
			tipoFuncionario.setCodigo(dto.getCodigoTipoFuncionario());
			entidade.setTipoFuncionario(tipoFuncionario);
		}

		if (dto.getCodigoTipoAfastamento() != null) {
			TipoAfastamento tipoAfastamento = new TipoAfastamento();
			tipoAfastamento.setCodigo(dto.getCodigoTipoAfastamento());
			entidade.setTipoAfastamento(tipoAfastamento);
		}

		if (dto.getCodigoConselhoRegional() != null || dto.getNumeroInscricaoConselho() != null || dto.getUfConselho() != null) {
			ConselhoRegional conselhoRegional = new ConselhoRegional();
			conselhoRegional.setCodigo(dto.getCodigoConselhoRegional());
			entidade.setConselhoRegional(conselhoRegional);
			entidade.setUfConselho(dto.getUfConselho());
			entidade.setNumeroInscricaoConselho(dto.getNumeroInscricaoConselho());
		}

		entidade.setDataAdmissao(criarDateTimeDB(dto.getDataAdmissao()));
		entidade.setDataDesligamento(criarDateTimeDB(dto.getDataDesligamento()));

		entidade.setPessoa(obterPessoa(dto.getIdPessoa()));
		entidade.setPessoaEmpregador(obterPessoa(dto.getIdPessoaEmpregador()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void definirInformacoesAdicionais(InformacaoProfissionalDTO dto, InformacaoProfissional entidade) throws BancoobException {
		super.definirInformacoesAdicionais(dto, entidade);
		
		if(entidade.getConselhoRegional() != null) {
			dto.setCodigoConselhoRegional(entidade.getConselhoRegional().getCodigo());
		}
		
		if(entidade.getTipoAfastamento() != null) {
			dto.setCodigoTipoAfastamento(entidade.getTipoAfastamento().getCodigo());
		}
		
		if(entidade.getTipoFuncionario() != null) {
			dto.setCodigoTipoFuncionario(entidade.getTipoFuncionario().getCodigo());
		}
	}

}