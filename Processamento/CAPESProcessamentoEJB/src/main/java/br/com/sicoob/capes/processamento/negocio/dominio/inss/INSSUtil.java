/**
 * 
 */
package br.com.sicoob.capes.processamento.negocio.dominio.inss;

import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.processamento.negocio.dto.BeneficiarioDTO;

/**
 * @author erico.junior
 *
 */
public final class INSSUtil {
	
	/**
	 * Instancia um novo INSSUtil.
	 */
	private INSSUtil(){
	}
	
	/**
	 * Cria a instituição a partir do dto.
	 * @param dto O dto com os dados do beneficiário.
	 * @return a instituição a partir do dto.
	 */
	public static Instituicao obterInstituicao(BeneficiarioDTO dto) {
		Instituicao instituicao = new Instituicao();
		instituicao.setIdInstituicao(dto.getIdInstituicao());
		instituicao.setIdUnidadeInst(dto.getIdUnidadeInst());
		return instituicao;
	}		
}
