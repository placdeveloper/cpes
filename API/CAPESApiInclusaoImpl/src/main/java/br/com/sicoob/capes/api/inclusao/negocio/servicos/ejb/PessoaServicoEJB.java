package br.com.sicoob.capes.api.inclusao.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.inclusao.negocio.dto.EncaminharAutorizacaoDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.ExecutarProcedimentoAutorizacaoDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.PessoaDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.interfaces.PessoaServicoLocal;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.interfaces.PessoaServicoRemote;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.ReplicacaoCadastroDelegate;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;

/**
 * EJB contendo servicos relacionados a Pessoa.
 * 
 * @author bruno.carneiro
 */
@Stateless
@Local(PessoaServicoLocal.class)
@Remote(PessoaServicoRemote.class)
public class PessoaServicoEJB extends CAPESApiInclusaoServicoEJB<PessoaDTO, PessoaCompartilhamento> implements PessoaServicoLocal, PessoaServicoRemote {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(PessoaDTO dto) throws BancoobException {
		throw new UnsupportedOperationException("Operação não suportada.");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void validarInclusao(PessoaDTO dto) throws BancoobException {
		validarPropriedades(dto, new String[] { "idInstituicao", "idUnidadeInst", "idUsuarioAprovacao" });
		validarPropriedadesObrigatoriasInclusao(dto);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] obterPropriedadesObrigatoriasInclusao() {
		return new String[] { "cpfCnpj", "nomePessoa", "nomeCompleto" };
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] obterPropriedadesObrigatoriasAlteracao() {
		return obterPropriedadesObrigatoriasInclusao();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected PessoaCompartilhamento realizarInclusao(PessoaCompartilhamento entidade, PessoaDTO dto) throws BancoobException {
		PessoaCompartilhamentoDelegate delegate = (PessoaCompartilhamentoDelegate) obterDelegate();
		PessoaCompartilhamento pessoaCompartilhamento = delegate.consultarCpfCnpjParaInclusao(dto.getCpfCnpj(), entidade.getPessoa().getTipoPessoa());

		if (pessoaCompartilhamento != null && pessoaCompartilhamento.getTransicoes().size() > 0) {
			ReplicacaoCadastroDelegate delegateReplicacaoCadastro = obterFabricaCadastro().criarReplicacaoCadastroDelegate();
			return delegateReplicacaoCadastro.iniciarRelacionamentoInstituicao(pessoaCompartilhamento);
		}

		if (dto.getDadosReceita() != null) {
			return delegate.incluir(entidade, dto.getDadosReceita());
		} else {
			return delegate.incluir(entidade);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected PessoaCompartilhamento instanciarEntidadeAprovacao() {
		return new PessoaFisica();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void copiarPropriedadesAlteracao(PessoaCompartilhamento entidadeDestino, PessoaCompartilhamento entidadeFonte) {
		ReflexaoUtils.copiarPropriedades(entidadeDestino, entidadeFonte, "compartilhamento", "pessoa", "transicoes", "perfilCadastro", "listaCidadania", "listaEnderecoFiscal", "grauInstrucao");
		if(entidadeFonte instanceof PessoaFisica && ((PessoaFisica)entidadeFonte).getGrauInstrucao() != null) {
			((PessoaFisica)entidadeDestino).setGrauInstrucao(((PessoaFisica)entidadeFonte).getGrauInstrucao());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String encaminharAutorizacao(EncaminharAutorizacaoDTO dto) throws BancoobException {
		if (dto.getIdRegistro() == null) {
			PessoaCompartilhamento pessoaCompartilhamento = obterPessoaCompartilhamento(dto.getIdPessoa(), dto.getIdInstituicaoRegistro());
			dto.setIdRegistro(pessoaCompartilhamento.getId());
		}
		return super.encaminharAutorizacao(dto);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void executarProcedimentoAutorizacao(ExecutarProcedimentoAutorizacaoDTO dto) throws BancoobException {
		if (dto.getIdRegistro() == null) {
			PessoaCompartilhamento pessoaCompartilhamento = obterPessoaCompartilhamento(dto.getIdPessoa(), dto.getIdInstituicaoRegistro());
			dto.setIdRegistro(pessoaCompartilhamento.getId());
		}
		super.executarProcedimentoAutorizacao(dto);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void cancelarFluxoAutorizacao(PessoaDTO dto, String justificativa) throws BancoobException {
		if (dto.getId() == null) {
			PessoaCompartilhamento pessoaCompartilhamento = obterPessoaCompartilhamento(dto.getIdPessoa(), dto.getIdInstituicao());
			dto.setId(pessoaCompartilhamento.getId());
		}
		super.cancelarFluxoAutorizacao(dto, justificativa);
	}
	
}