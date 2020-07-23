/**
 * 
 */
package br.com.sicoob.capes.cadastro.anotacao.builder;

import java.util.ArrayList;
import java.util.List;

import br.com.sicoob.capes.cadastro.negocio.dto.DefinicaoDTO;

/**
 * Builder para as defini��es dos detalhes de anota��o.
 * 
 * @author erico.junior
 */
public abstract class DefinicoesDetalheAnotacaoBuilder {

	/** A constante TIPO_DATA. */
	private static final int TIPO_DATA = 1;
	
	/** A constante TIPO_CNPJ. */
	private static final int TIPO_CNPJ = 2;
	
	/** A constante TIPO_VALOR. */
	private static final int TIPO_VALOR = 3;
	
	/** A constante TIPO_INTEIRO. */
	private static final int TIPO_INTEIRO = 4;
	
	/**
	 * Cria a lista de defini��es para o detalhe.
	 * 
	 * @return lista de defini��es para o detalhe.
	 */
	public List<DefinicaoDTO> obterListaDefinicoes() {
		List<DefinicaoDTO> lista = new ArrayList<DefinicaoDTO>();
		adicionarDefinicoes(lista);
		return lista;
	}

	/**
	 * Adiciona as defini��es a lista.
	 * 
	 * @param lista
	 *            A lista onde ser� adicionada a defini��o.
	 */
	protected abstract void adicionarDefinicoes(List<DefinicaoDTO> lista);

	/**
	 * Recupera uma defini��o para uma coluna de texto.
	 * 
	 * @param cabecalho
	 *            O cabe�alho da coluna.
	 * @param nomeAtributo
	 *            O nome do atributo da coluna
	 * @param largura
	 *            A largura da coluna.
	 * @return A defini��o para uma coluna de texto.
	 */
	protected DefinicaoDTO obterDefinicaoTextoDTO(String cabecalho, String nomeAtributo, 
			int largura) {

		DefinicaoDTO dto = new DefinicaoDTO();
		dto.setCabecalho(cabecalho);
		dto.setLargura(largura);
		dto.setNomeAtributo(nomeAtributo);

		return dto;
	}
	
	/**
	 * Recupera uma defini��o para uma coluna de n�mero inteiro.
	 * 
	 * @param cabecalho
	 *            O cabe�alho da coluna.
	 * @param nomeAtributo
	 *            O nome do atributo da coluna
	 * @param largura
	 *            A largura da coluna.
	 * @return A defini��o para uma coluna de n�mero inteiro.
	 */
	protected DefinicaoDTO obterDefinicaoInteiroDTO(String cabecalho, String nomeAtributo, 
			int largura) {

		DefinicaoDTO dto = obterDefinicaoTextoDTO(cabecalho, nomeAtributo, largura);
		dto.setTipo(TIPO_INTEIRO);
		return dto;
	}	

	/**
	 * Recupera uma defini��o para uma coluna de data.
	 * 
	 * @param cabecalho
	 *            O cabe�alho da coluna.
	 * @param nomeAtributo
	 *            O nome do atributo da coluna
	 * @param largura
	 *            A largura da coluna.
	 * @return A defini��o para uma coluna de data.
	 */
	protected DefinicaoDTO obterDefinicaoDataDTO(String cabecalho, String nomeAtributo, 
			int largura) {

		DefinicaoDTO dto = obterDefinicaoTextoDTO(cabecalho, nomeAtributo, largura);
		dto.setTipo(TIPO_DATA);
		return dto;
	}

	/**
	 * Recupera uma defini��o para uma coluna de CNPJ.
	 * 
	 * @param cabecalho
	 *            O cabe�alho da coluna.
	 * @param nomeAtributo
	 *            O nome do atributo da coluna
	 * @param largura
	 *            A largura da coluna.
	 * @return A defini��o para uma coluna de CNPJ.
	 */
	protected DefinicaoDTO obterDefinicaoCnpjDTO(String cabecalho, String nomeAtributo, 
			int largura) {

		DefinicaoDTO dto = obterDefinicaoTextoDTO(cabecalho, nomeAtributo, largura);
		dto.setTipo(TIPO_CNPJ);
		return dto;
	}
	
	/**
	 * Recupera uma defini��o para uma coluna de Valor.
	 * 
	 * @param cabecalho
	 *            O cabe�alho da coluna.
	 * @param nomeAtributo
	 *            O nome do atributo da coluna
	 * @param largura
	 *            A largura da coluna.
	 * @return A defini��o para uma coluna de Valor.
	 */
	protected DefinicaoDTO obterDefinicaoValorDTO(String cabecalho, String nomeAtributo, 
			int largura) {

		DefinicaoDTO dto = obterDefinicaoTextoDTO(cabecalho, nomeAtributo, largura);
		dto.setTipo(TIPO_VALOR);
		return dto;
	}

}
