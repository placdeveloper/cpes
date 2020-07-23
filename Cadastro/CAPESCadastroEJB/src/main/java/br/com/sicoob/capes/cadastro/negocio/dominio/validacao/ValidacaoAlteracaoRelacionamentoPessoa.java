package br.com.sicoob.capes.cadastro.negocio.dominio.validacao;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;

/**
 * A Classe ValidacaoAlteracaoRelacionamentoPessoa.
 */
public class ValidacaoAlteracaoRelacionamentoPessoa extends ValidacaoRelacionamentoPessoa {

	/**
	 * {@inheritDoc}
	 * 
	 * @throws BancoobException
	 */
	@Override
	protected Calendar obterLimiteInferior(final RelacionamentoPessoa relacionamento)
			throws BancoobException {

		Date dataAtualMovimentoCCS = getDelegate().obterDataMovimentoCCS(relacionamento
				.getIdInstituicao());
		Calendar limite = Calendar.getInstance();
		limite.setTime(DateUtils.truncate(dataAtualMovimentoCCS, Calendar.DATE));
		return limite;
	}

}
