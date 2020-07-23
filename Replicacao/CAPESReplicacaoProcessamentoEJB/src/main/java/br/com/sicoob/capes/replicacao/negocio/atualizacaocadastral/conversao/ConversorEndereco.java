/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.conversao;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.EnderecoCorrespondenciaDelegate;
import br.com.sicoob.capes.negocio.entidades.EnderecoCorrespondencia;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Localidade;
import br.com.sicoob.capes.negocio.entidades.TipoLogradouro;
import br.com.sicoob.capes.negocio.entidades.legado.Endereco;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.replicacao.negocio.delegates.EntidadeReplicavelDelegate;

/**
 * Converte o endereço do cadastro único no endereço da replicação. 
 * @author Erico.Junior
 */
public class ConversorEndereco extends
		ConversorAbstract<Endereco, br.com.sicoob.capes.negocio.entidades.vigente.Endereco> {

	/** O atributo correspondenciaDelegate. */
	private transient EnderecoCorrespondenciaDelegate correspondenciaDelegate = 
			CAPESCadastroFabricaDelegates.getInstance().criarEnderecoCorrespondenciaDelegate();

	/** O atributo ORIGEM_MUNICIPIO. */
	private static Short ORIGEM_MUNICIPIO = (short)2;
	
	/** A constante TAMANHO_BAIRRO. */
	private static final int TAMANHO_BAIRRO = 30;
	
	/** A constante TAMANHO_COMPLEMENTO. */
	private static final int TAMANHO_COMPLEMENTO = 20;
	
	/** A constante TAMANHO_LOGRADOURO. */
	private static final int TAMANHO_LOGRADOURO = 40;
	
	/** A constante TAMANHO_NUMERO. */
	private static final int TAMANHO_NUMERO = 6;
	
	/**
	 * Obter id municipio ibge.
	 *
	 * @param localidade o valor de localidade
	 * @return Integer
	 */
	private Integer obterIdMunicipioIBGE(Localidade localidade) {

		Integer idMunicipioIBGE = null;
		try {
			String codIBGE = localidade.getCodigoIBGE();
			if(codIBGE != null) {
				idMunicipioIBGE = Integer.parseInt(codIBGE);	
			}
		} catch (NumberFormatException e){
		}
		
		return idMunicipioIBGE;	
	}
	
	/**
	 * Verifica se eh endereco correspondencia.
	 *
	 * @param pessoa o valor de pessoa
	 * @param instituicao o valor de instituicao
	 * @param idEndereco o valor de id endereco
	 * @return {@code true}, se for endereco correspondencia
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private boolean isEnderecoCorrespondencia(PessoaCompartilhamento pessoa, 
			Instituicao instituicao, Long idEndereco) throws BancoobException {
		
		boolean isCorrespondencia = false;
		EnderecoCorrespondencia correspondencia = correspondenciaDelegate.consultar(pessoa, instituicao);

		if(correspondencia != null && correspondencia.getEndereco() != null) {
			Long idEnderecoCorrespondencia = correspondencia.getEndereco().getIdEndereco();
			isCorrespondencia = idEndereco.equals(idEnderecoCorrespondencia);
		}
		
		return isCorrespondencia;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Endereco instanciarEntidade() {
		return new Endereco();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Endereco converter(br.com.sicoob.capes.negocio.entidades.vigente.Endereco entidade,
			Endereco endereco, Instituicao instituicao, Integer numPessoa)
			throws BancoobException {
		
		// Replicação
		Pessoa pessoa = obterPessoa(numPessoa, instituicao);
		Localidade localidade = obterLocalidade(entidade.getLocalidade());
		TipoLogradouro tipoLogradouro = entidade.getTipoLogradouro();
		Long idEnderecoDB2 = entidade.getIdEndereco();
		boolean isCorrespondencia = isEnderecoCorrespondencia(
				entidade.getPessoaCompartilhamento(), instituicao, idEnderecoDB2);
		
		endereco.setBairro(truncarValorCampo(entidade.getBairro(), TAMANHO_BAIRRO));
		endereco.setCep(entidade.getCep());
		endereco.setCodTipoEndereco(entidade.getTipoEndereco().getCodigo());
		endereco.setComplemento(truncarValorCampo(entidade.getComplemento(), TAMANHO_COMPLEMENTO));
		endereco.setDataUltimaAtualizacao(new Date());
		endereco.setDescricao(truncarValorCampo(entidade.getDescricao(), TAMANHO_LOGRADOURO));
		endereco.setIdEnderecoPessoaDB2(idEnderecoDB2);
		endereco.setNumero(truncarValorCampo(entidade.getNumero(), TAMANHO_NUMERO));
		endereco.setPessoa(pessoa);
		endereco.setEnderecoCorrespondencia(isCorrespondencia);

		if(tipoLogradouro != null) {
			endereco.setIdTipoLogradouro(tipoLogradouro.getIdTipoLogradouro().shortValue());
		}
		if(localidade != null) {
			endereco.setCidade(localidade.getNome());
			endereco.setUf(localidade.getUf().getSiglaUF());
			Integer idMunicipioIBGE = obterIdMunicipioIBGE(localidade);
			if(idMunicipioIBGE != null) {
				endereco.setIdMunicipioIbge(idMunicipioIBGE);
				endereco.setOrigemMunicipio(ORIGEM_MUNICIPIO);
			}
		}
		return endereco;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EntidadeReplicavelDelegate<Endereco, ?> obterDelegate() {
		return obterFabricaDelegates().criarEnderecoDelegate();
	}
	
	/**
	 * Truncar valor campo.
	 *
	 * @param campoEndereco o valor de campo endereco
	 * @param tamanhoMaximoCampo o valor de tamanho maximo campo
	 * @return String
	 */
	private String truncarValorCampo(String campoEndereco, Integer tamanhoMaximoCampo) {
		if (!StringUtils.isEmpty(campoEndereco) && campoEndereco.length() > tamanhoMaximoCampo) {
			return campoEndereco.substring(0, tamanhoMaximoCampo);
		} else {
			return campoEndereco;
		}
	}
}