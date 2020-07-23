/**
 * 
 */
package br.com.sicoob.capes.processamento.negocio.dominio.inss.builder;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.util.IntegracaoUtil;
import br.com.sicoob.capes.comum.negocio.enums.TipoEnderecoEnum;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.LOCIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.Localidade;
import br.com.sicoob.capes.negocio.entidades.TipoEndereco;
import br.com.sicoob.capes.negocio.entidades.TipoLogradouro;
import br.com.sicoob.capes.negocio.entidades.vigente.Endereco;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;
import br.com.sicoob.capes.processamento.negocio.dto.BeneficiarioDTO;

/**
 * @author Erico.Junior
 *
 */
public class EnderecoBeneficiarioBuilder {

	/** A constante ID_TIPO_LOGRADOURO. */
	private static final Integer ID_TIPO_LOGRADOURO = 99;
	
	/** A constante NAO_INFORMADO. */
	private static final String NAO_INFORMADO = "NÃO INFORMADO";
	
	/** A constante TIPO_LOGRADOURO. */
	private static final TipoLogradouro TIPO_LOGRADOURO = new TipoLogradouro();
	
	static {
		TIPO_LOGRADOURO.setIdTipoLogradouro(ID_TIPO_LOGRADOURO);
	}
	
	/** O atributo endereco. */
	private final transient Endereco endereco;
	
	/**
	 * Instancia um novo EnderecoBeneficiarioBuilder.
	 */
	public EnderecoBeneficiarioBuilder() {
		endereco = new Endereco();
	}		
	
	/**
	 * O método Criar endereco.
	 *
	 * @param dto o valor de dto
	 * @param pessoa o valor de pessoa
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void criarEndereco(BeneficiarioDTO dto, PessoaFisica pessoa) throws BancoobException {

		Localidade localidade = new Localidade();
		localidade.setIdLocalidade(dto.getIdLocalidade());
		
		endereco.setPessoaCompartilhamento(pessoa);
		endereco.setTipoLogradouro(TIPO_LOGRADOURO); 
		endereco.setDescricao(obterLogradouro(dto));
		endereco.setNumero("0");
		endereco.setComplemento(NAO_INFORMADO);
		endereco.setBairro(obterBairro(dto));
		endereco.setLocalidade(localidade);
		endereco.setCep(obterCEP(dto));
		endereco.setGravarHistorico(false);
	}
	
	/**
	 * O método Criar tipo endereco.
	 *
	 * @param tipoEnderecoEnum o valor de tipo endereco enum
	 */
	public void criarTipoEndereco(TipoEnderecoEnum tipoEnderecoEnum) {
		TipoEndereco tipoEndereco = new TipoEndereco();
		tipoEndereco.setCodigo(tipoEnderecoEnum.getCodigo());
		endereco.setTipoEndereco(tipoEndereco);
	}
	
	/**
	 * Obter bairro.
	 *
	 * @param dto o valor de dto
	 * @return String
	 */
	private String obterBairro(BeneficiarioDTO dto) {
		
		String bairro = NAO_INFORMADO;
		
		if(StringUtils.isNotBlank(dto.getBairro())) {
			bairro = dto.getBairro();
		}
		
		return bairro;
	}
	
	/**
	 * Obter logradouro.
	 *
	 * @param dto o valor de dto
	 * @return String
	 */
	private String obterLogradouro(BeneficiarioDTO dto) {
		
		String logradouro = dto.getEndereco();
		
		if(StringUtils.isBlank(logradouro)) {
			logradouro = dto.getEnderecoCooperativa();
		}
		
		return logradouro;
	}
	
	/**
	 * Obter cep.
	 *
	 * @param dto o valor de dto
	 * @return String
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private String obterCEP(BeneficiarioDTO dto) throws BancoobException {
		
		String cep = dto.getCep();
		if(StringUtils.isBlank(cep)) {
			LOCIntegracaoDelegate locDelegate = CAPESIntegracaoFabricaDelegates.getInstance()
					.criarLOCIntegracaoDelegate();
			Localidade localidade = IntegracaoUtil.converterLocalidade(locDelegate.obterLocalidade(dto
					.getIdLocalidade()));
			if(localidade != null) {
				cep = localidade.getNumeroCep();
			}
		}
		return cep;
	}
	
	/**
	 * Recupera o valor de endereco.
	 *
	 * @return o valor de endereco
	 */
	public Endereco getEndereco(){
		return this.endereco;
	}
}
