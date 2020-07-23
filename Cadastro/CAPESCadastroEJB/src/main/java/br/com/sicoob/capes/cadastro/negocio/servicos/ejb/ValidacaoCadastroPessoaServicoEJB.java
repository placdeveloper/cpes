package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.EJB;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.dominio.validacao.ValidacaoCadastroPessoa;
import br.com.sicoob.capes.cadastro.negocio.dominio.validacao.ValidacaoCadastroPessoaFisica;
import br.com.sicoob.capes.cadastro.negocio.dominio.validacao.ValidacaoCadastroPessoaJuridica;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.PessoaCompartilhamentoServicoLocal;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.negocio.entidades.EntidadeCadastroBase;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Super classe das entidades que necessitam validar o cadastro da pessoa.
 * @author Juan.Damasceno
 *
 * @param <E>
 */
public abstract class ValidacaoCadastroPessoaServicoEJB<E extends EntidadeCadastroBase & Vigente & Replicavel> extends
		EntidadeCadastroServicoEJB<E> {
	
	/** O atributo pessoaCompartilhamentoServico. */
	@EJB(mappedName = "capes/cadastro/PessoaCompartilhamentoServico")
	protected PessoaCompartilhamentoServicoLocal pessoaCompartilhamentoServico;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarAlterar(E objeto) throws BancoobException {
		validarCadastroPessoa(objeto.getPessoaCompartilhamento());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarIncluir(E objeto) throws BancoobException {
		validarCadastroPessoa(objeto.getPessoaCompartilhamento());
	}
	
	/**
	 * O método Validar cadastro pessoa.
	 *
	 * @param objeto o valor de objeto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void validarCadastroPessoa(PessoaCompartilhamento objeto)
			throws BancoobException {

		PessoaCompartilhamento pessoaCompartilhamentoPersistent = 
				pessoaCompartilhamentoServico.obter(objeto.getId()); 

		ValidacaoCadastroPessoa<?> validacao = 
				obterValidacaoPessoa(pessoaCompartilhamentoPersistent, obterInstituicaoUsuarioLogado()); 
		validacao.validar(pessoaCompartilhamentoPersistent);
	}

	/**
	 * Obter validacao pessoa.
	 *
	 * @param objeto o valor de objeto
	 * @param instituicao o valor de instituicao
	 * @return ValidacaoCadastroPessoa
	 */
	private ValidacaoCadastroPessoa<?> obterValidacaoPessoa(PessoaCompartilhamento objeto, Instituicao instituicao) {

		Short codTipoPessoa = objeto.getPessoa().getTipoPessoa().getCodTipoPessoa();
		ValidacaoCadastroPessoa<?> validacao = null;

		if (TipoPessoaEnum.isPessoaFisica(codTipoPessoa)) {
			validacao = new ValidacaoCadastroPessoaFisica(instituicao);
		} else  {
			validacao = new ValidacaoCadastroPessoaJuridica(instituicao);
		}

		return validacao;
	}	

}