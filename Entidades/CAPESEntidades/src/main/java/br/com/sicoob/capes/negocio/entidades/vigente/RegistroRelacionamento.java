/*
 * SICOOB
 * 
 * RegistroRelacionamento.java(br.com.sicoob.capes.negocio.entidades.vigente.RegistroRelacionamento)
 */
package br.com.sicoob.capes.negocio.entidades.vigente;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.sicoob.capes.comum.negocio.annotation.CampoAutorizacao;
import br.com.sicoob.capes.comum.negocio.annotation.CamposAutorizacao;
import br.com.sicoob.capes.comum.negocio.annotation.NaoVerificarGestorResponsavel;
import br.com.sicoob.capes.negocio.entidades.DadosRegistroRelacionamento;

/**
 * Armazena os dados de registro de um relacionamento em cart�rio
 *
 * 24/08/2011
 * @author Rodrigo.Chaves
 */
@Entity
@Table(schema = "CLI", name = "REGISTRORELACIONAMENTO")
@CamposAutorizacao(id = "idRelacionamento", camposExibicao = {
		@CampoAutorizacao(propriedade = "dataHoraInicio", label = "DATA DO CADASTRO", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorData", mascara = "dd/MM/yyyy"),
		@CampoAutorizacao(propriedade = "pessoaRelacionada.cpfCnpj", label = "PESSOA RELACIONADA", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorCpfCnpj"),
		@CampoAutorizacao(propriedade = "tipoRelacionamento.descricao", label = "TIPO DE RELACIONAMENTO"),
		@CampoAutorizacao(propriedade = "percentualCapitalEmpresa", label = "PERCENTUAL DO CAPITAL SOCIAL", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorDecimal"),
		@CampoAutorizacao(propriedade = "dataInicioMandato", label = "DATA DE IN�CIO DO MANDATO", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorData", mascara = "dd/MM/yyyy"),
		@CampoAutorizacao(propriedade = "dataFimMandato", label = "DATA DE FIM DO MANDATO", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorData", mascara = "dd/MM/yyyy"),
		@CampoAutorizacao(propriedade = "dadosRegistro.numeroRegistro", label = "N�MERO DO REGISTRO"),
		@CampoAutorizacao(propriedade = "dadosRegistro.numeroLivro", label = "N�MERO DO LIVRO"),
		@CampoAutorizacao(propriedade = "dadosRegistro.numeroFolha", label = "N�MERO DA FOLHA"),
		@CampoAutorizacao(propriedade = "dadosRegistro.nomeCartorio", label = "NOME DO CART�RIO") })
@NaoVerificarGestorResponsavel
public class RegistroRelacionamento extends RelacionamentoPessoa {

	/** Serial UID */
	private static final long serialVersionUID = -6049248402761799256L;
	
	/** O atributo dados registro. */
	@Embedded
	private DadosRegistroRelacionamento dadosRegistro;

	/**
	 * Recupera dados registro.
	 * 
	 * @return dados registro
	 */
	public DadosRegistroRelacionamento getDadosRegistro() {
		return dadosRegistro;
	}

	/**
	 * Preenche dados registro.
	 * 
	 * @param dadosRegistro
	 *            o novo dados registro
	 */
	public void setDadosRegistro(DadosRegistroRelacionamento dadosRegistro) {
		this.dadosRegistro = dadosRegistro;
	}
		
}
