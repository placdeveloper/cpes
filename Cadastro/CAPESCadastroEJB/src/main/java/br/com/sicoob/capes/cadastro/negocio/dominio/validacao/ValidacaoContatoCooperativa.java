package br.com.sicoob.capes.cadastro.negocio.dominio.validacao;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.capes.cadastro.negocio.delegates.AutorizacaoCadastroDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.excecao.ContatosCooperativaException;
import br.com.sicoob.capes.comum.negocio.contexto.InformacoesUsuarioCAPES;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.EntidadeCadastroBase;
import br.com.sicoob.capes.negocio.entidades.Pessoa;

/**
 * A Classe ValidacaoContatoCooperativa.
 *
 * @param <E> o tipo generico
 */
public abstract class ValidacaoContatoCooperativa<E extends EntidadeCadastroBase> extends ValidacaoEntidadeCadastroAbstract<E> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validar(E entidade) throws BancoobException {
		super.validar(entidade);
		validarTamanhoCampos(entidade);
		validarContatosCooperativa(entidade);
	}

	/**
	 * O método Validar contatos cooperativa.
	 *
	 * @param entidade o valor de entidade
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarContatosCooperativa(E entidade) throws BancoobException {
		Pessoa pessoa = entidade.getPessoaCompartilhamento().getPessoa();
		if (pessoa != null) {
			boolean isPessoaJuridica = TipoPessoaEnum.isPessoaJuridica(pessoa.getTipoPessoa().getCodTipoPessoa());
			if (isPessoaJuridica) {
				SCIIntegracaoDelegate sciIntegracaoDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();
				boolean isCooperativa = sciIntegracaoDelegate.isCooperativa(pessoa.getCpfCnpj());
				if (isCooperativa) {
					AutorizacaoCadastroDelegate autorizacaoCadastroDelegate = CAPESCadastroFabricaDelegates.getInstance().criarAutorizacaoCadastroDelegate();
					boolean isGestorCadastro = autorizacaoCadastroDelegate.isGestorCadastroInstituicao(obterUsuario());
					if (!isGestorCadastro) {
						throw new ContatosCooperativaException();
					}
				}
			}
		}
	}
	
	protected abstract void validarTamanhoCampos(E entidade) throws NegocioException;

	/**
	 * Obter usuario.
	 *
	 * @return UsuarioBancoob
	 */
	private UsuarioBancoob obterUsuario() {
		InformacoesUsuarioCAPES informacoesUsuarioCAPES = InformacoesUsuarioCAPES.getInstance();

		if (informacoesUsuarioCAPES == null) {
			throw new UnsupportedOperationException("O InformacoesUsuarioCAPES não foi instanciado!");
		}

		UsuarioBancoob usuarioBancoob = new UsuarioBancoob();
		usuarioBancoob.setCooperativa(informacoesUsuarioCAPES.getCooperativa());
		usuarioBancoob.setIdInstituicao(informacoesUsuarioCAPES.getIdInstituicao());
		usuarioBancoob.setIdUnidadeInstituicao(informacoesUsuarioCAPES.getIdUnidadeInstituicao());
		usuarioBancoob.setLogin(informacoesUsuarioCAPES.getLogin());
		usuarioBancoob.setPac(informacoesUsuarioCAPES.getPac());

		return usuarioBancoob;
	}

}