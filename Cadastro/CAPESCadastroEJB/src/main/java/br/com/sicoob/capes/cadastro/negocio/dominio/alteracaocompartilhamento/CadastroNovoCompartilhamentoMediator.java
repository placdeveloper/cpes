/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.alteracaocompartilhamento;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Mediator utilizado na cópia do cadastro de uma pessoa para outra em outro grupo de compartilhamento.
 * @author erico.junior
 */
public class CadastroNovoCompartilhamentoMediator {

	/** O atributo replicacaoEnderecos. */
	private transient ReplicacaoEnderecos replicacaoEnderecos = new ReplicacaoEnderecos();
	
	/** O atributo replicacaoTelefones. */
	private transient ReplicacaoTelefones replicacaoTelefones = new ReplicacaoTelefones();
	
	/** O atributo replicacaoEmails. */
	private transient ReplicacaoEmails replicacaoEmails = new ReplicacaoEmails();
	
	/** O atributo replicacaoCertidoes. */
	private transient ReplicacaoCertidao replicacaoCertidoes = new ReplicacaoCertidao();
	
	/** O atributo replicacaoReferencias. */
	private transient ReplicacaoReferencias replicacaoReferencias = new ReplicacaoReferencias(); 
	
	/** O atributo replicacaoBens. */
	private transient ReplicacaoBens replicacaoBens = new ReplicacaoBens();
	
	/** O atributo replicacaoRendas. */
	private transient ReplicacaoFonteRenda replicacaoRendas = new ReplicacaoFonteRenda(); 
	
	/** O atributo replicacaoProdutor. */
	private transient ReplicacaoProdutor replicacaoProdutor = new ReplicacaoProdutor();
	
	/** O atributo replicacaoProdutividade. */
	private transient ReplicacaoProdutividade replicacaoProdutividade = new ReplicacaoProdutividade();
	
	/** O atributo pessoaCompartilhamento. */
	private PessoaCompartilhamento pessoaCompartilhamento;
	
	/** O atributo pessoaNovoCompartilhamento. */
	private PessoaCompartilhamento pessoaNovoCompartilhamento;
	
	/** O atributo instituicaoDestino. */
	private Instituicao instituicaoDestino;
	
	/**
	 * Instancia um novo CadastroNovoCompartilhamentoMediator.
	 *
	 * @param pessoaCompartilhamento o valor de pessoa compartilhamento
	 * @param pessoaNovoCompartilhamento o valor de pessoa novo compartilhamento
	 * @param instituicao o valor de instituicao
	 */
	public CadastroNovoCompartilhamentoMediator(PessoaCompartilhamento pessoaCompartilhamento, 
			PessoaCompartilhamento pessoaNovoCompartilhamento, Instituicao instituicao) {
		
		this.pessoaCompartilhamento = pessoaCompartilhamento;
		this.pessoaNovoCompartilhamento = pessoaNovoCompartilhamento;
		this.instituicaoDestino = instituicao;
	}
	
	/**
	 * O método Copiar cadastro.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void copiarCadastro() throws BancoobException {
		
		replicacaoEnderecos.executar(pessoaCompartilhamento, pessoaNovoCompartilhamento, instituicaoDestino);
		replicacaoTelefones.executar(pessoaCompartilhamento, pessoaNovoCompartilhamento);
		replicacaoEmails.executar(pessoaCompartilhamento, pessoaNovoCompartilhamento);
		replicacaoCertidoes.executar(pessoaCompartilhamento, pessoaNovoCompartilhamento);
		replicacaoReferencias.executar(pessoaCompartilhamento, pessoaNovoCompartilhamento);
		replicacaoBens.executar(pessoaCompartilhamento, pessoaNovoCompartilhamento);
		replicacaoRendas.executar(pessoaCompartilhamento, pessoaNovoCompartilhamento);
		replicacaoProdutor.executar(pessoaCompartilhamento, pessoaNovoCompartilhamento);
		replicacaoProdutividade.executar(pessoaCompartilhamento, pessoaNovoCompartilhamento);
	}
	
}
