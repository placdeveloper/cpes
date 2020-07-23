package br.com.sicoob.capes.cadastro.negocio.excecao;

public class BemImovelProprietaroParticipanteProdutividadeException extends CAPESCadastroNegocioException {
	private static final long serialVersionUID = -7737292511351861321L;
	
	private static final String CHAVE_MSG = "MN216";

	public BemImovelProprietaroParticipanteProdutividadeException(String pessoas) {
		super(CHAVE_MSG, pessoas);
	}

}