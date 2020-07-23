package br.com.sicoob.capes.api.inclusao.testes;

import java.util.Date;

import br.com.sicoob.capes.api.inclusao.negocio.dto.PessoaDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.PessoaJuridicaDTO;
import br.com.sicoob.capes.comum.negocio.vo.DadosCNPJVO;

/**
 * A Classe TestePessoaJuridica.
 *
 * @author bruno.carneiro
 */
public class TestePessoaJuridica extends TestePessoa<PessoaJuridicaDTO> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void preencherInformacoesEspecificasDTO(PessoaDTO dto) {
		dto.setCodigoAtividadeEconomica((short) 54);
		
		DadosCNPJVO cnpjVO = new DadosCNPJVO();
		cnpjVO.setBairro("Teste");
		cnpjVO.setCep(70680550);
		cnpjVO.setCidadeExterior("Teste");
		cnpjVO.setCnaePrincipal("A03");
		cnpjVO.setCodEstabelecimento(1);
		cnpjVO.setCodigoMunicipio(1);
		cnpjVO.setCodigoPais("10");
		cnpjVO.setCodigoSituacaoCadastral(1);
		cnpjVO.setComplemento("Teste");
		cnpjVO.setDataAbertura(new Date());
		cnpjVO.setDataUltimaAtualizacao(new Date());
		cnpjVO.setDdd1("61");
		cnpjVO.setDdd2("61");
		cnpjVO.setEmail("teste@teste.com");
		cnpjVO.setLogradouro("Teste");
		cnpjVO.setNaturezaJuridica("104");
		cnpjVO.setNomeEmpresarial("Teste");
		cnpjVO.setNomeFantasia("Teste");
		cnpjVO.setNomeMunicipio("Teste");
		cnpjVO.setNomePais("Teste");
		cnpjVO.setCnpj("07062372865842");
		cnpjVO.setNumeroLogradouro("Teste");
		cnpjVO.setTelefone1("999999999");
		cnpjVO.setTelefone2("999999999");
		cnpjVO.setTipoLogradouro("Teste");
		cnpjVO.setUf("DF");
		
		dto.setDadosReceita(cnpjVO);
	}

}