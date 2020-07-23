/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.conversao;

import java.util.Date;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoReplicavelEnum;
import br.com.sicoob.capes.negocio.dto.AtualizacaoCadastralDTO;
import br.com.sicoob.capes.negocio.entidades.Anotacao;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;
import br.com.sicoob.capes.negocio.entidades.legado.PessoaFisica;

/**
 * @author Erico.Junior
 * 
 */
public class ConversorAnotacao extends Conversor<PessoaFisica, Anotacao> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PessoaFisica obterEntidadeReplicacao(AtualizacaoCadastralDTO<Anotacao> dto)
			throws BancoobException {

		Anotacao anotacao = dto.getEntidadeCadastro();
		Integer codTipoAnotacao = anotacao.getTipoAnotacao().getCodTipoAnotacao();

		boolean ehBaixa = false;
		Date dataAtualizacao = null;
		
		if(anotacao.getDataHoraBaixa() != null) {
			ehBaixa = true;
			dataAtualizacao = anotacao.getDataHoraBaixa(); 
		} else {
			ehBaixa = false;
			dataAtualizacao = anotacao.getDataHoraAnotacao();
		}
		
		// Replicação
		Pessoa pessoa = obterPessoa(dto);
		pessoa.setDataUltimaAtualizacao(dataAtualizacao);
		PessoaFisica pessoaFisica = (PessoaFisica) pessoa;
		
		if (isAnotacaoFalecido(codTipoAnotacao)) {
			pessoaFisica.setFalecido(!ehBaixa);
		} else if (isAnotacaoPessoaPoliticamenteExposta(codTipoAnotacao)) {

			if (ehBaixa) {
				pessoaFisica.setPessoaExposta(false);
				pessoaFisica.setTipoExposicao(null);
			} else {
				pessoaFisica.setPessoaExposta(true);
				pessoaFisica.setTipoExposicao(anotacao.getCodigoTipoExposicao());
			}
		}

		return pessoaFisica;
	}

	/**
	 * Verifica se eh anotacao falecido.
	 *
	 * @param codigo o valor de codigo
	 * @return {@code true}, se for anotacao falecido
	 */
	private boolean isAnotacaoFalecido(Integer codigo) {
		return TipoAnotacaoReplicavelEnum.FALECIDO.getCodTipoAnotacao().equals(codigo);
	}

	/**
	 * Verifica se eh anotacao pessoa politicamente exposta.
	 *
	 * @param codigo o valor de codigo
	 * @return {@code true}, se for anotacao pessoa politicamente exposta
	 */
	private boolean isAnotacaoPessoaPoliticamenteExposta(Integer codigo) {
		return TipoAnotacaoReplicavelEnum.PESSOA_POLITICAMENTE_EXPOSTA.getCodTipoAnotacao().equals(
				codigo);
	}

}
