/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.validacao;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.EnderecoDelegate;
import br.com.sicoob.capes.cadastro.negocio.excecao.CAPESCadastroNegocioException;
import br.com.sicoob.capes.cadastro.negocio.excecao.CEPInvalidoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.CampoNaoInformadoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.EnderecoMesmoTipoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.TamanhoCampoInvalidoException;
import br.com.sicoob.capes.comum.negocio.enums.TipoEnderecoEnum;
import br.com.sicoob.capes.comum.negocio.vo.LOCIntegracaoTipoLogradouroVO;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.LOCIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.TipoEndereco;
import br.com.sicoob.capes.negocio.entidades.vigente.Endereco;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * @author Erico.Junior
 *
 */
public class ValidacaoEndereco extends ValidacaoContatoCooperativa<Endereco> {

	/** A constante TAMANHO_CEP. */
	private static final int TAMANHO_CEP = 8;
	
	/** A constante TAMANHO_BAIRRO. */
	private static final int TAMANHO_BAIRRO = 30;
	
	/** A constante TAMANHO_COMPLEMENTO. */
	private static final int TAMANHO_COMPLEMENTO = 20;
	
	/** A constante TAMANHO_LOGRADOURO. */
	private static final int TAMANHO_LOGRADOURO = 40;
	
	/** A constante TAMANHO_NUMERO. */
	private static final int TAMANHO_NUMERO = 6;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void validarCamposObrigatorios(Endereco endereco)
			throws BancoobException {
		
		TipoEndereco tipoEndereco = endereco.getTipoEndereco();
		if(tipoEndereco == null || tipoEndereco.getCodigo() == null) {
			throw new CampoNaoInformadoException("Tipo de Endereço");
		}
		
		String cep = endereco.getCep();
		if(StringUtils.isBlank(cep)) {
			throw new CampoNaoInformadoException("CEP");
		}
		
		if(endereco.getTipoLogradouro() == null || endereco.getTipoLogradouro().getIdTipoLogradouro() == null) {
			throw new CampoNaoInformadoException("Tipo Logradouro");
		}
		
		String logradouro = endereco.getDescricao();
		if(StringUtils.isBlank(logradouro)) {
			throw new CampoNaoInformadoException("Logradouro");
		}
		
		String bairro = endereco.getBairro();
		if(StringUtils.isBlank(bairro)) {
			throw new CampoNaoInformadoException("Bairro");
		}
		
		if(endereco.getLocalidade() == null || endereco.getLocalidade().getIdLocalidade() == null) {
			throw new CampoNaoInformadoException("Município");
		}		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void validarNegocio(Endereco novoEndereco) throws BancoobException {
		validarTipoLogradouro(novoEndereco);
		validarDuplicidadeTipoEndereco(novoEndereco);
		validarCEP(novoEndereco);
	}
	
	/**
	 * O método Validar tipo logradouro.
	 *
	 * @param novoEndereco o valor de novo endereco
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarTipoLogradouro(Endereco novoEndereco) throws BancoobException {
		Short idTipoLogradouro = novoEndereco.getTipoLogradouro().getIdTipoLogradouro().shortValue();

		LOCIntegracaoDelegate locDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarLOCIntegracaoDelegate();
		LOCIntegracaoTipoLogradouroVO tipo = locDelegate.obterTipoLogradouro(idTipoLogradouro);

		if (tipo == null) {
			throw new CAPESCadastroNegocioException("Tipo de logradouro inválido.");
		}

		String descricaoTipo = tipo.getNome().toUpperCase();
		String logradouro = novoEndereco.getDescricao().toUpperCase();

		if (logradouro.startsWith(descricaoTipo)) {
			novoEndereco.setDescricao(novoEndereco.getDescricao().replace(descricaoTipo, ""));
			// throw new TipoLogradouroRepetidoException();
		}
	}
	
	/**
	 * Valida a duplicidade de endereço para os tipos residencial e/ou comercial
	 * 
	 * @param novoEndereco
	 *            O endereço que está sendo incluído ou alterado.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	private void validarDuplicidadeTipoEndereco(Endereco novoEndereco) throws BancoobException {

		if (isTipoEnderecoResidencialOuComercial(novoEndereco)
				&& (novoEndereco.getDataHoraInicio() == null)) {

			List<Endereco> lista = listarEnderecos(novoEndereco.getPessoaCompartilhamento());

			if (lista != null && !lista.isEmpty()) {
				for (Endereco endereco : lista) {
					validarTipoEndereco(novoEndereco, endereco);
				}
			}
		}
	}	

	/**
	 * Lista todos os endereços por pessoa.
	 * 
	 * @param pessoa
	 *            A pessoa na qual se desja os endereços.
	 * @return todos os endereços por pessoa.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	private List<Endereco> listarEnderecos(PessoaCompartilhamento pessoa) throws BancoobException {

		Endereco endereco = new Endereco();
		endereco.setPessoaCompartilhamento(pessoa);

		ConsultaDto<Endereco> criterios = new ConsultaDto<Endereco>();
		criterios.setFiltro(endereco);
		criterios.setTipoProcura("VIGENTES");

		EnderecoDelegate delegate = CAPESCadastroFabricaDelegates.getInstance().criarEnderecoDelegate();
		return delegate.listar(criterios);
	}
	
	/**
	 * Valida se os tipos de endereço são diferentes, exceto se o registro
	 * estiver sendo alterado.
	 * 
	 * @param novoEndereco
	 *            O novo endereço
	 * @param endereco
	 *            O endereço existente.
	 * @throws EnderecoMesmoTipoException
	 *             Caso os endereços sejam diferentes, porém com tipo iguais e
	 *             dos tipos comercial ou residencial.
	 */
	private void validarTipoEndereco(Endereco novoEndereco, Endereco endereco)
			throws EnderecoMesmoTipoException {

		Short tipoNovo = novoEndereco.getTipoEndereco().getCodigo();
		Short tipo = endereco.getTipoEndereco().getCodigo();
		
		if (!endereco.getIdRegistroControlado().equals(novoEndereco.getIdRegistroControlado())
				&& isTipoEnderecoResidencialOuComercial(endereco) && !isMesmoEndereco(endereco, novoEndereco)
				&& tipo.equals(tipoNovo)) {
			throw new EnderecoMesmoTipoException(TipoEnderecoEnum.valueOf(tipoNovo).getDescricao());
		}
	}
	
	/**
	 * Verifica se eh tipo endereco residencial ou comercial.
	 *
	 * @param endereco o valor de endereco
	 * @return {@code true}, se for tipo endereco residencial ou comercial
	 */
	private boolean isTipoEnderecoResidencialOuComercial(Endereco endereco) {
		Short codigo = endereco.getTipoEndereco().getCodigo();
		return TipoEnderecoEnum.RESIDENCIAL.getCodigo().equals(codigo)
				|| TipoEnderecoEnum.COMERCIAL.getCodigo().equals(codigo);
	}	
	
	/**
	 * Verifica se é as duas instâncias do endereço são do mesmo registro.
	 * @param endereco Um dos endereços a serem verificados.
	 * @param outroEndereco O outro endereço a ser verificado.
	 * @return se é as duas instâncias do endereço são do mesmo registro.
	 */
	private boolean isMesmoEndereco(Endereco endereco, Endereco outroEndereco) {
		return ReflexaoUtils.equals(endereco, outroEndereco, "idEndereco");
	}
	
	/**
	 * O método Validar cep.
	 *
	 * @param endereco o valor de endereco
	 * @throws NegocioException lança a exceção NegocioException
	 */
	private void validarCEP(Endereco endereco) throws NegocioException {
		String cep = endereco.getCep().trim().replaceAll(" ", "");
		if (TAMANHO_CEP != cep.length()) {
			throw new CEPInvalidoException();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void validarTamanhoCampos(Endereco endereco) throws NegocioException {
		if (endereco.getDescricao() != null && endereco.getDescricao().length() > TAMANHO_LOGRADOURO) {
			throw new TamanhoCampoInvalidoException("Logradouro", TAMANHO_LOGRADOURO);
		}

		if (endereco.getNumero() != null && endereco.getNumero().length() > TAMANHO_NUMERO) {
			throw new TamanhoCampoInvalidoException("Número", TAMANHO_NUMERO);
		}

		if (endereco.getComplemento() != null && endereco.getComplemento().length() > TAMANHO_COMPLEMENTO) {
			throw new TamanhoCampoInvalidoException("Complemento", TAMANHO_COMPLEMENTO);
		}

		if (endereco.getBairro() != null && endereco.getBairro().length() > TAMANHO_BAIRRO) {
			throw new TamanhoCampoInvalidoException("Bairro", TAMANHO_BAIRRO);
		}		
	}
	
}