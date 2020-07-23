/**
 * 
 */
package br.com.sicoob.capes.processamento.util;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.comum.negocio.enums.TipoPerfilCadastroEnum;
import br.com.sicoob.capes.comum.util.IdadeUtil;
import br.com.sicoob.capes.negocio.entidades.PerfilCadastro;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;
import br.com.sicoob.capes.processamento.negocio.dominio.inss.builder.PessoaFisicaINSSBuilder;
import br.com.sicoob.capes.processamento.negocio.dto.BeneficiarioDTO;

/**
 * @author erico.junior
 *
 */
public final class BeneficiarioUtil {

	/**
	 * Instancia um novo BeneficiarioUtil.
	 */
	private BeneficiarioUtil() {
	}
	
	/**
	 * Obter pessoa fisica.
	 *
	 * @param dto o valor de dto
	 * @param dataProduto o valor de data produto
	 * @return PessoaFisica
	 */
	public static PessoaFisica obterPessoaFisica(BeneficiarioDTO dto, DateTimeDB dataProduto) {
		
		DateTimeDB dataNascimento = dto.getDataNascimento();

		PessoaFisica pessoaFisica = criarPessoaFisica();
		pessoaFisica.getPessoa().setCpfCnpj(dto.getNumCpf());
		pessoaFisica.setNomePessoa(dto.getNome());
		pessoaFisica.setNomeCompleto(dto.getNome());
		pessoaFisica.setDataInclusaoSistema(dataProduto);
		pessoaFisica.setDataInclusaoSFN(dataProduto);
		pessoaFisica.setDataNascimento(dataNascimento);
		pessoaFisica.setNomeMae(dto.getNomeMae());
		pessoaFisica.setUfOrgaoExpedidorDocumento(dto.getUf());
		pessoaFisica.setDataEmissaoDocumento(new DateTimeDB(dataNascimento.getTime()));
		pessoaFisica.setIdNaturalidade(dto.getIdLocalidade());
		pessoaFisica.setGravarHistorico(false);
		
		PerfilCadastro perfilCadastro = new PerfilCadastro();
		perfilCadastro.setCodigo(TipoPerfilCadastroEnum.SIMPLES.getCodigo());
		pessoaFisica.setPerfilCadastro(perfilCadastro);

		// Conforme solicitação em 26/09/2012
		// Se for menor de idade, deve ser incluído como emancipado.
		pessoaFisica.setMenorEmancipado(
				IdadeUtil.isMenorIdade(pessoaFisica.getDataNascimento()));
		
		return pessoaFisica;
	}
	
	/**
	 * Criar pessoa fisica.
	 *
	 * @return PessoaFisica
	 */
	private static PessoaFisica criarPessoaFisica() {
		
		PessoaFisicaINSSBuilder builder = new PessoaFisicaINSSBuilder();
		builder.criarApelido();
		builder.criarObservacaoPessoa();
		builder.criarAtividadeEconomica();
		builder.criarDadosEspecificos();
		return builder.getPessoaCompartilhamento();
	}
	
}
