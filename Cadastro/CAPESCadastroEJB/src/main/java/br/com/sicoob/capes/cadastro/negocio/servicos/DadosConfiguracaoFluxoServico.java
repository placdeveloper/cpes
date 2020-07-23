package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.DadosConfiguracaoFluxo;

/**
 * @author rodrigo.chaves
 */
public interface DadosConfiguracaoFluxoServico extends
		CAPESCadastroCrudServico<DadosConfiguracaoFluxo> {

	/**
	 * Obter.
	 *
	 * @param isResponsavel o valor de is responsavel
	 * @param isGestor o valor de is gestor
	 * @param possuiDocumento o valor de possui documento
	 * @return DadosConfiguracaoFluxo
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	DadosConfiguracaoFluxo obter(Boolean isResponsavel, Boolean isGestor, Boolean possuiDocumento)
			throws BancoobException;
	
	/**
	 * Obter siglas processo.
	 *
	 * @return List
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	List<String> obterSiglasProcesso() throws BancoobException;

}