/*
 * SICOOB
 * 
 * ClienteVO.java(br.com.sicoob.capes.api.negocio.vo.ClienteVO)
 */
package br.com.sicoob.capes.api.negocio.vo;

import java.util.Date;

/**
 * @author Erico.Junior
 * 
 */
public class ClienteVO extends PessoaVO {

	/** Serial UID.*/
	private static final long serialVersionUID = -6079055313517674708L;
	
	/**
	 * Cria uma nova instância de cliente vo.
	 */
	public ClienteVO() {
	}

	/**
	 * Cria uma nova instância de cliente vo.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param cpfCnpj
	 *            the cpf cnpj
	 * @param codTipoPessoa
	 *            the cod tipo pessoa
	 * @param codigoCompartilhamentoCadastro
	 *            the codigo compartilhamento cadastro
	 * @param nomePessoa
	 *            the nome pessoa
	 * @param nomeCompleto
	 *            the nome completo
	 * @param nomeApelido
	 *            the nome apelido
	 * @param descricaoObservacaoPessoa
	 *            the descricao observacao pessoa
	 * @param codigoAtividadeEconomica
	 *            the codigo atividade economica
	 * @param codigoCnaeFiscal
	 *            the codigo cnae fiscal
	 * @param dataInclusaoSistema
	 *            the data inclusao sistema
	 * @param autorizaConsultaBacen
	 *            the autoriza consulta bacen
	 * @param idPessoaLegado
	 *            the id pessoa legado
	 * @param idInstituicao
	 *            the id instituicao
	 * @param dataInclusaoSFN
	 *            the data inclusao sfn
	 */
	public ClienteVO(Integer idPessoa, String cpfCnpj, Short codTipoPessoa,
			Short codigoCompartilhamentoCadastro, String nomePessoa,
			String nomeCompleto, String nomeApelido,
			String descricaoObservacaoPessoa, Short codigoAtividadeEconomica,
			String codigoCnaeFiscal, Date dataInclusaoSistema, Boolean autorizaConsultaBacen, 
			Integer idPessoaLegado, Integer idInstituicao, Integer idUnidadeInst, Date dataInclusaoSFN) {
		super(idPessoa, cpfCnpj, codTipoPessoa, codigoCompartilhamentoCadastro, nomePessoa, nomeCompleto, nomeApelido, 
				descricaoObservacaoPessoa, codigoAtividadeEconomica, codigoCnaeFiscal, dataInclusaoSistema, 
				autorizaConsultaBacen, idPessoaLegado, idInstituicao, dataInclusaoSFN, idUnidadeInst);
	}
	
	/**
	 * Instancia um novo ClienteVO.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param cpfCnpj o valor de cpf cnpj
	 * @param codTipoPessoa o valor de cod tipo pessoa
	 * @param codigoCompartilhamentoCadastro o valor de codigo compartilhamento cadastro
	 * @param nomePessoa o valor de nome pessoa
	 * @param nomeCompleto o valor de nome completo
	 * @param nomeApelido o valor de nome apelido
	 * @param descricaoObservacaoPessoa o valor de descricao observacao pessoa
	 * @param codigoAtividadeEconomica o valor de codigo atividade economica
	 * @param codigoCnaeFiscal o valor de codigo cnae fiscal
	 * @param dataInclusaoSistema o valor de data inclusao sistema
	 * @param autorizaConsultaBacen o valor de autoriza consulta bacen
	 * @param idPessoaLegado o valor de id pessoa legado
	 * @param idInstituicao o valor de id instituicao
	 * @param dataInclusaoSFN o valor de data inclusao sfn
	 */
	public ClienteVO(Integer idPessoa, String cpfCnpj, Short codTipoPessoa,
			Short codigoCompartilhamentoCadastro, String nomePessoa,
			String nomeCompleto, String nomeApelido,
			String descricaoObservacaoPessoa, Short codigoAtividadeEconomica,
			String codigoCnaeFiscal, Date dataInclusaoSistema, Boolean autorizaConsultaBacen, 
			Integer idPessoaLegado, Integer idInstituicao, Date dataInclusaoSFN) {
		super(idPessoa, cpfCnpj, codTipoPessoa, codigoCompartilhamentoCadastro, nomePessoa, nomeCompleto, nomeApelido, 
				descricaoObservacaoPessoa, codigoAtividadeEconomica, codigoCnaeFiscal, dataInclusaoSistema, 
				autorizaConsultaBacen, idPessoaLegado, idInstituicao, dataInclusaoSFN);
	}
	
  

}