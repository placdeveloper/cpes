package br.com.sicoob.capes.api.inclusao.testes;

import java.text.SimpleDateFormat;

import br.com.sicoob.capes.api.inclusao.negocio.dto.InformacaoProfissionalDTO;

/**
 * Classe com os teste de informação profissional.
 * 
 * @author Bruno.Carneiro
 */
public class TesteInformacaoProfissional extends CAPESApiInclusaoTesteAbstrato<InformacaoProfissionalDTO> {

	/**
	 * Construtor.
	 */
	public TesteInformacaoProfissional() {
		super("InformacaoProfissionalServico");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void preencherInformacoesEspecificasDTO(InformacaoProfissionalDTO dto) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			dto.setCargo("Teste");
			dto.setCodigoConselhoRegional((short) 14);
			dto.setCodigoTipoAfastamento((short) 1);
			dto.setCodigoTipoFuncionario((short) 1);
			dto.setCodSituacao((short) 1);
			dto.setDataAdmissao(sdf.parse("22/10/2018"));
			dto.setDataDesligamento(null);
			dto.setDiaMesFerias("0112");
			dto.setUfConselho("DF");
			dto.setNumeroInscricaoConselho("515615");
			dto.setNumMatricula("1668866");
			dto.setIdPessoa(5665372);
			dto.setIdPessoaEmpregador(1020105);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}