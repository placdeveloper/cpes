package br.com.sicoob.capes.api.inclusao.testes;

import java.util.Date;

import br.com.sicoob.capes.api.inclusao.negocio.dto.PessoaDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.PessoaFisicaDTO;
import br.com.sicoob.capes.comum.negocio.vo.DadosCPFVO;

/**
 * A Classe TestePessoaFisica.
 *
 * @author bruno.carneiro
 */
public class TestePessoaFisica extends TestePessoa<PessoaFisicaDTO> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void preencherInformacoesEspecificasDTO(PessoaDTO dto) {
		PessoaFisicaDTO dtoPessoaFisica = (PessoaFisicaDTO) dto;

		dtoPessoaFisica.setCodigoAtividadeEconomica((short) 55);
		dtoPessoaFisica.setDataNascimento(new Date());
		dtoPessoaFisica.setNomePai("Pai teste maria teste");
		dtoPessoaFisica.setNomeMae("Mãe teste maria teste");
		dtoPessoaFisica.setCodigoTipoDocumento((short) 1);
		dtoPessoaFisica.setNumeroDocumento("123456");
		dtoPessoaFisica.setOrgaoExpedidorDocumento("SSP");
		dtoPessoaFisica.setUfOrgaoExpedidorDocumento("DF");
		dtoPessoaFisica.setDataEmissaoDocumento(new Date());
		dtoPessoaFisica.setTipoSexo('F');
		dtoPessoaFisica.setCodigoOcupacaoProfissional(1000);
		dtoPessoaFisica.setCodigoEstadoCivil((short) 1);
		dtoPessoaFisica.setCodigoRegimeCasamento((short) 1);
		dtoPessoaFisica.setQuantidadeDependentes((short) 2);
		dtoPessoaFisica.setCodigoGrauInstrucao((short) 1);
		dtoPessoaFisica.setDescNaturalidade("Teste");
		dtoPessoaFisica.setIdNaturalidade(null);
		dtoPessoaFisica.setCodigoVinculoEmpregaticio((short) 1);
		dtoPessoaFisica.setCodigoNacionalidade((short) 10);
		dtoPessoaFisica.setDescricao("segundoteste");
		
		DadosCPFVO cpfVO = new DadosCPFVO();
		cpfVO.setIdConsulta(1);
		cpfVO.setIdInstituicao(910);
		cpfVO.setIdUnidadeInst(0);
		cpfVO.setAnoObito(2016);
		cpfVO.setCodigoSituacaoCadastral(0);
		cpfVO.setCodResidenteExterior(2);
		cpfVO.setCodSexo(1);
		cpfVO.setDataNascimento(new Date());
		cpfVO.setDataUltimaAtualizacao(new Date());
		cpfVO.setDescResidenteExterior("Teste");
		cpfVO.setDescSexo("F");
		cpfVO.setDescSituacaoCadastral("Teste");
		cpfVO.setNome("Teste maria teste");
		cpfVO.setNomeMae("Mãe teste maria teste");
		cpfVO.setCpf("41144181569");
		cpfVO.setNumTituloEleitor("123445");

		dtoPessoaFisica.setDadosReceita(cpfVO);
	}

}