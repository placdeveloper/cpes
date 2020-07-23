package br.com.sicoob.capes.cadastro.negocio.dominio.validacao;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.capes.cadastro.negocio.excecao.CampoNaoInformadoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.TamanhoCampoInvalidoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.TelefoneInvalidoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.TipoTelefoneNaoPermitidoException;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoTelefoneEnum;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.TipoTelefone;
import br.com.sicoob.capes.negocio.entidades.vigente.Telefone;

/**
 * Classe para validação do telefone.
 *
 * @author Bruno.Carneiro
 */
public class ValidacaoTelefone extends ValidacaoContatoCooperativa<Telefone> {
	
	private static final int TAMANHO_DDD = 2;
	private static final int TAMANHO_RAMAL = 4;
	private static final int TAMANHO_TELEFONE = 12;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void validarCamposObrigatorios(Telefone entidade) throws BancoobException {
		TipoTelefone tipoTelefone = entidade.getTipoTelefone();
		if (tipoTelefone == null || tipoTelefone.getCodigo() == null) {
			throw new CampoNaoInformadoException("Tipo de telefone");
		}

		if (StringUtils.isBlank(entidade.getTelefone())) {
			throw new CampoNaoInformadoException("Telefone");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void validarTamanhoCampos(Telefone entidade) throws NegocioException {
		if (entidade.getDdd() != null && entidade.getDdd().length() > TAMANHO_DDD) {
			throw new TamanhoCampoInvalidoException("DDD", TAMANHO_DDD);
		}

		if (entidade.getRamal() != null && entidade.getRamal().length() > TAMANHO_RAMAL) {
			throw new TamanhoCampoInvalidoException("Ramal", TAMANHO_RAMAL);
		}

		if (entidade.getTelefone() != null && entidade.getTelefone().length() > TAMANHO_TELEFONE) {
			throw new TamanhoCampoInvalidoException("Telefone", TAMANHO_TELEFONE);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void validarNegocio(Telefone entidade) throws BancoobException {
		validarTipoTelefone(entidade);
		isTelefoneCelularInvalido(entidade);
	}
	
	/**
	 * O método Validar tipo telefone.
	 *
	 * @param telefone o valor de telefone
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarTipoTelefone(Telefone telefone) throws BancoobException {
		Pessoa pessoa = telefone.getPessoaCompartilhamento().getPessoa();
		if (TipoPessoaEnum.isPessoaJuridica(pessoa.getTipoPessoa().getCodTipoPessoa())) {
			if (TipoTelefoneEnum.RESIDENCIAL.getCodigo().equals(telefone.getTipoTelefone().getCodigo())) {
				throw new TipoTelefoneNaoPermitidoException(TipoTelefoneEnum.RESIDENCIAL.getDescricao(), TipoPessoaEnum.PESSOA_JURIDICA.getDescricao());
			}
		}
	}
	
	/**
	 * O método Checks if is telefone inválido.
	 *
	 * @param telefone o valor de novo tel
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void isTelefoneCelularInvalido(Telefone telefone) throws BancoobException {
		if (telefone.getTipoTelefone().getCodigo().equals(TipoTelefoneEnum.CELULAR.getCodigo())) {
			char primeiroNumero = telefone.getTelefone().charAt(0);
			if (!(primeiroNumero == '9' || primeiroNumero == '8' || primeiroNumero == '7' || primeiroNumero == '6')) {
				throw new TelefoneInvalidoException();
			}
		}
	}
    
}