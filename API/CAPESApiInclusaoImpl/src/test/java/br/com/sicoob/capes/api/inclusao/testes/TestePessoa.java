package br.com.sicoob.capes.api.inclusao.testes;

import java.util.Date;

import br.com.sicoob.capes.api.inclusao.negocio.dto.PessoaDTO;

/**
 * A Classe TestePessoa.
 *
 * @param <D> o tipo generico
 * @author bruno.carneiro
 */
public abstract class TestePessoa<D extends PessoaDTO> extends CAPESApiInclusaoTesteAbstrato<PessoaDTO> {

	/**
	 * Instancia um novo TestePessoa.
	 */
	public TestePessoa() {
		super("PessoaServico");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void preencherInformacoesDTO(PessoaDTO dto) {
		super.preencherInformacoesDTO(dto);
		preencherInformacoesComuns(dto);
	}

	/**
	 * O método Preencher informacoes comuns.
	 *
	 * @param dto o valor de dto
	 */
	private void preencherInformacoesComuns(PessoaDTO dto) {
		dto.setNomePessoa("Teste maria teste");
		dto.setNomeApelido("Teste maria teste");
		dto.setNomeCompleto("Teste maria teste");
		dto.setCpfCnpj("41144181569");
		dto.setDataInclusaoSistema(new Date());
		dto.setDescricao("Teste do teste");
	}
}