package br.com.sicoob.capes.comum.arquivos.negocio.vo;

import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.negocio.vo.BancoobVo;
import br.com.sicoob.capes.comum.arquivos.RegistroArquivo;
import br.com.sicoob.capes.comum.arquivos.excecao.UsoIncorretoApiException;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.Arquivo;

/**
 * Classe que representa os dados do arquivo ap�s importa��o ou antes da exporta��o. <strong>Aten��o!</strong> Essa
 * classe deve obrigatoriamente definir a annotation: {@link br.com.sicoob.capes.comum.arquivos.negocio.annotation.Arquivo}.
 * 
 * Criado em 21/7/2014.
 * 
 * @author rodrigo.chaves
 */
public abstract class DadosArquivoVO extends BancoobVo {

    /** A constante serialVersionUID. */
    private static final long serialVersionUID = 110947383707212814L;

	/**
	 * Constante usada para indicar que o tamanho da linha do arquivo n�o � fixo ou que a valida��o do tamanho da linha
	 * deve ser ignorada.
	 */
	public static final int TAMANHO_LINHA_INDEFINIDO = -1;

	/** O atributo registros. */
	protected List<RegistroArquivo> registros;
	
	/** O atributo resumoImportacao. */
	protected ResumoImportacaoVo resumoImportacao;

	/**
	 * Classe abstrata. Essa classe n�o deve/pode ser instanciada diretamente, ela deve ser herdada.
	 */
	protected DadosArquivoVO() {

	}

	/**
	 * Obt�m o valor do atributo <code>resumoImportacao</code>.
	 * 
	 * @return ResumoImportacaoVo - O atributo resumoImportacao.
	 */
	public ResumoImportacaoVo getResumoImportacao() {

		return resumoImportacao;
	}

	/**
	 * Atribui o valor do atributo <code>resumo</code>.
	 * 
	 * @param resumo
	 *            - O valor a ser atribu�do.
	 */
	public void setResumoImportacao(ResumoImportacaoVo resumo) {

		this.resumoImportacao = resumo;
	}

	/**
	 * Obt�m o valor do atributo <code>registros</code>.
	 * 
	 * @return List<RegistroArquivo> - O atributo registros.
	 */
	public List<RegistroArquivo> getRegistros() {

		if (registros == null) {
	        registros = new ArrayList<RegistroArquivo>();
        }
        return registros;
	}

	/**
	 * Atribui o valor do atributo <code>registros</code>.
	 * 
	 * @param registros
	 *            - O valor a ser atribu�do.
	 */
	public void setRegistros(List<RegistroArquivo> registros) {

		this.registros = registros;
	}

	/**
	 * Obt�m os metadados ({@link Arquivo}).
	 * 
	 * @return Arquivo - metadados (annotation).
	 */
	public Arquivo getMetadados() {

		Arquivo metadados = getClass().getAnnotation(Arquivo.class);
		if (metadados == null) {
			throw new UsoIncorretoApiException("exception.dadosarquivo.annotation",
			        Arquivo.class.getName(), getClass().getName());
		}
		return metadados;
	}

}