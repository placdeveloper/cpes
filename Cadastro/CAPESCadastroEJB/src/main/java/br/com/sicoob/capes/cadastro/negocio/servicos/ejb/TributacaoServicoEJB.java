/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.dominio.validacao.ValidacaoCadastroPessoa;
import br.com.sicoob.capes.cadastro.negocio.dominio.validacao.ValidacaoCadastroPessoaFisica;
import br.com.sicoob.capes.cadastro.negocio.dominio.validacao.ValidacaoCadastroPessoaJuridica;
import br.com.sicoob.capes.cadastro.negocio.excecao.ClienteNaoCadastradoException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.PessoaCompartilhamentoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.PessoaInstituicaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TributacaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TributacaoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.TributacaoDAO;
import br.com.sicoob.capes.comum.negocio.annotation.IntegracaoGedGft;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.Tributacao;

/**
 * Implementa as opera��es do servi�o de tributacao.
 * 
 * @author juan.damasceno
 */
@Stateless
@Local( { TributacaoServicoLocal.class })
@Remote( { TributacaoServicoRemote.class })
@IntegracaoGedGft
public class TributacaoServicoEJB extends
		EntidadeCadastroServicoEJB<Tributacao> implements TributacaoServicoRemote, TributacaoServicoLocal {

	/** O atributo pessoaInstituicaoServico. */
	@EJB(mappedName = "capes/cadastro/PessoaInstituicaoServico")
	private PessoaInstituicaoServicoLocal pessoaInstituicaoServico;
	
	/** O atributo pessoaCompartilhamentoServico. */
	@EJB(mappedName = "capes/cadastro/PessoaCompartilhamentoServico")
	protected PessoaCompartilhamentoServicoLocal pessoaCompartilhamentoServico;
	
	@Inject
	@Default
	protected TributacaoDAO tributacaoDAO;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TributacaoDAO getDAO() {
		return tributacaoDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	public Tributacao obterPorPessoaComValidacao(PessoaCompartilhamento pessoaCompartilhamento) 
		throws BancoobException {

		PessoaInstituicao pessoaInstituicao = new PessoaInstituicao();
		pessoaInstituicao.setPessoa(pessoaCompartilhamento.getPessoa());
		pessoaInstituicao.setIdInstituicao(obterInstituicaoUsuarioLogado().getIdInstituicao());
		pessoaInstituicao = pessoaInstituicaoServico
				.obterPorPessoaInstituicaoSemValidacao(pessoaInstituicao);
		if (pessoaInstituicao == null) {
			throw new ClienteNaoCadastradoException();
		}
		return obterPorPessoa(pessoaCompartilhamento);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Tributacao obterPorPessoa(PessoaCompartilhamento pessoaCompartilhamento)
			throws BancoobException {
		
		return getDAO().obterPorPessoa(pessoaCompartilhamento);
	}

	/**
	 * <p>
	 * Realiza a inlus�o de uma {@link Tributacao}
	 * </p>
	 * <strong>IMPORTANTE</strong>
	 * <p>
	 * Este m�todo n�o envia mensagem de replica��o devido � imposs�bilidade de inclus�o de
	 * tributa��o via sistema. Desta forma, todos que necessitam fazer esta inclus�o com replica��o,
	 * o fazem de forma s�ncrona.
	 * </p>
	 * 
	 * @param objeto
	 *            a Tributacao a ser inclu�da
	 * @see br.com.sicoob.capes.negocio.entidades.listener.ReplicacaoClienteListener#executarAposInclusao
	 * (br.com.sicoob.capes.negocio.entidades.CAPESEntidade)
	 * 
	 * @return A Tributacao inclu�da
	 */
	@Override
	public Tributacao incluir(Tributacao objeto) throws BancoobException {

		PessoaCompartilhamento pessoaCompartilhamento = objeto.getPessoaCompartilhamento();
		Long idPessoaCompartilhamento = pessoaCompartilhamento.getId();
		
		objeto.setIdPessoaCompartilhamento(idPessoaCompartilhamento);
		
		/*
		 * Bug do hibernate https://hibernate.onjira.com/browse/HHH-2763 S� acontece na inclus�o.
		 * 
		 * {@link br.com.sicoob.capes.negocio.entidades.listener.ReplicacaoListener#obterTransicoes(E)}
		 */
		{
			PessoaCompartilhamento pessoa = pessoaCompartilhamentoServico.obter(idPessoaCompartilhamento);
			pessoa.getTransicoes();
			
			objeto.setPessoaCompartilhamento(pessoaCompartilhamento);
		}
		
		return getDAO().incluir(objeto);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarAlterar(Tributacao objeto) throws BancoobException {
		validarCadastroPessoa(objeto.getPessoaCompartilhamento());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarIncluir(Tributacao objeto) throws BancoobException {
		//n�o faz nada
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void validarCadastroPessoa(PessoaCompartilhamento objeto)
			throws BancoobException {
		validarCadastroPessoa(objeto, obterInstituicaoUsuarioLogado());
	}
	
	/**
	 * O m�todo Validar cadastro pessoa.
	 *
	 * @param objeto o valor de objeto
	 * @param instituicao o valor de instituicao
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	private void validarCadastroPessoa(PessoaCompartilhamento objeto, Instituicao instituicao)
			throws BancoobException {

		PessoaCompartilhamento pessoaCompartilhamentoPersistent = 
				pessoaCompartilhamentoServico.obter(objeto.getId()); 

		ValidacaoCadastroPessoa<?> validacao = 
				obterValidacaoPessoa(pessoaCompartilhamentoPersistent, instituicao); 
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

	/**
	 * {@inheritDoc}
	 */
	public Tributacao incluir(Tributacao tributacao, Instituicao instituicao)
			throws BancoobException {
		
		return incluir(tributacao);
	}

}
