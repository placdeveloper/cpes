/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.conversao;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;


/**
 * Superclasse para os conversores de pessoa.
 * @author Erico.Junior
 */
public abstract class ConversorPessoa<R extends Pessoa, E extends 
	br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento> extends ConversorAbstract<R, E> {

	/** A constante TAMANHO_OBSERVACAO. */
	private static final int TAMANHO_OBSERVACAO = 200;

	/**
	 * Preencher dados pessoa.
	 *
	 * @param pessoa o valor de pessoa
	 * @param pessoaCUC o valor de pessoa cuc
	 * @return R
	 */
	protected R preencherDadosPessoa(R pessoa, E pessoaCUC) {

		Short idAtividadeEconomica = null;
		String cnae = null;
		String observacao = pessoaCUC.getDescricao();

		if(pessoaCUC.getAtividadeEconomica() != null) {
			idAtividadeEconomica = pessoaCUC.getAtividadeEconomica().getCodigo();
		}
		if(pessoaCUC.getCnae() != null) {
			cnae = pessoaCUC.getCnae().getCodigo();			
		}
		if(StringUtils.isNotEmpty(observacao) && observacao.length() > TAMANHO_OBSERVACAO) {
			observacao = observacao.substring(0, TAMANHO_OBSERVACAO);
		}		
		
		pessoa.setNomeAlterado(houveAlteracaoNome(pessoa, pessoaCUC));
		pessoa.setApelido(pessoaCUC.getNomeApelido());
		pessoa.setDataUltimaAtualizacao(new Date());
		pessoa.setDataCadastramentoPessoa(pessoaCUC.getDataInclusaoSistema());
		pessoa.setDataSFN(pessoaCUC.getDataInclusaoSFN());
		pessoa.setNome(pessoaCUC.getNomePessoa());
		pessoa.setNomeCompleto(pessoaCUC.getNomeCompleto());
		pessoa.setObservacao(observacao);
		pessoa.setIdAtividadeEconomica(idAtividadeEconomica);
		pessoa.setCodigoCnae(cnae);
		pessoa.setAutorizaConsulta(pessoaCUC.getAutorizaConsultaBacen());
			
		return pessoa;
	}
	
	/**
	 * Houve alteracao nome.
	 *
	 * @param pessoa o valor de pessoa
	 * @param pessoaCUC o valor de pessoa cuc
	 * @return {@code true}, em caso de sucesso
	 */
	private boolean houveAlteracaoNome(R pessoa, E pessoaCUC){
		
		String nomeLegado = pessoa.getNome();
		String novoNome = pessoaCUC.getNomePessoa();
		
		return !nomeLegado.equalsIgnoreCase(novoNome);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public R obterEntidadeParaAlteracao(Instituicao instituicao, Integer numPessoa, E entidadeCapes) 
			throws BancoobException {

		R entidade = obterDelegate().obter(numPessoa, instituicao.getIdInstituicao());
		return converter(entidadeCapes, entidade, instituicao, numPessoa);
	}	
}
