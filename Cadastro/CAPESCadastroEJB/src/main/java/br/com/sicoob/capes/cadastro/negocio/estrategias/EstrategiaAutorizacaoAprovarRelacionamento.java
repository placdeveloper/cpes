package br.com.sicoob.capes.cadastro.negocio.estrategias;

import java.util.Calendar;
import java.util.Date;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.RelacionamentoPessoaDelegate;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;

/**
 * Estratégia para aprovação de relacionamentos.
 */
public class EstrategiaAutorizacaoAprovarRelacionamento extends EstrategiaAutorizacaoAprovar {
	
	/** O atributo relacionamentoPessoaDelegate. */
	private RelacionamentoPessoaDelegate relacionamentoPessoaDelegate = 
			CAPESCadastroFabricaDelegates.getInstance().criarRelacionamentoPessoaDelegate();

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Aprovavel obterEntidadeAlteracao(TipoAutorizacaoEntidadeEnum tipoAutorizacao, Aprovavel aprovavel) {
		return (Aprovavel) ReflexaoUtils.construirObjetoPorClasse(aprovavel.getClass());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void tratarInclusao(Autorizacao autorizacao, Aprovavel aprovavel) throws BancoobException {
		tratarDataCCS((RelacionamentoPessoa)aprovavel);
		super.tratarInclusao(autorizacao, aprovavel);
		relacionamentoPessoaDelegate.gerarRelacionamentoReverso((RelacionamentoPessoa) aprovavel);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void tratarAlteracao(Autorizacao autorizacao, Aprovavel aprovavel) throws BancoobException {
		super.tratarAlteracao(autorizacao, aprovavel);		
		RelacionamentoPessoa relacionamento = relacionamentoPessoaDelegate.obter(autorizacao.getIdRegistroAntigo());
		relacionamentoPessoaDelegate.alterarRelacionamentoReverso(relacionamento);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void tratarExclusao(Autorizacao autorizacao, Aprovavel aprovavel) throws BancoobException {
		super.tratarExclusao(autorizacao, aprovavel);
	}
	
	/**
	 * O método Tratar data ccs.
	 *
	 * @param rel o valor de rel
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void tratarDataCCS(RelacionamentoPessoa rel) throws BancoobException {
		if (rel.getTipoRelacionamento().getHabilitaEnvioCCS()) {
			Date dataCCS = relacionamentoPessoaDelegate.obterDataMovimentoCCS(rel.getIdInstituicao());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dataCCS);
			rel.setDataInicioRelacionamento(new DateTimeDB(calendar.getTimeInMillis()));
		} else {
			rel.setDataInicioRelacionamento(new DateTimeDB());
		}
	}
}