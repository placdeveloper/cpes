package br.com.sicoob.capes.cadastro.helper;

import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.ParametroDelegate;
import br.com.sicoob.capes.cadastro.negocio.enums.ParametroEnum;
import br.com.sicoob.capes.cadastro.negocio.vo.ParametroVO;

public class ParametroPiltoHelper {
	
	private static final String ATIVO = "true";

	/**
	 * Verifica se o piloto esta habilitado.
	 * 
	 * @return
	 */
	public static boolean isPilotoHabilitado() {
		ParametroDelegate parametroDelegate = 
				CAPESCadastroFabricaDelegates.getInstance().criarParametroDelegate();
		
		Integer idInstituicao = 
				Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao());
		
		ParametroVO parametro = parametroDelegate
				.obterParametro(ParametroEnum.OBRIGATORIEDADE_DE_CAMPOS.getCodigo(), idInstituicao);
			
		return parametro != null && 
						parametro.getValor() != null && parametro.getValor().equals(ATIVO);
	}

}
