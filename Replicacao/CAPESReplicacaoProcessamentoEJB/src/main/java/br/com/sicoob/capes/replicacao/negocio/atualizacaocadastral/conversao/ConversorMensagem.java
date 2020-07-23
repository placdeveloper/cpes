/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.conversao;


import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.legado.Mensagem;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;
import br.com.sicoob.capes.replicacao.negocio.delegates.EntidadeReplicavelDelegate;

/**
 * Conversor de mensagem do Cadastro único em mensagem da replicação.
 * 
 * @author juan.damasceno
 */
public class ConversorMensagem extends
		ConversorAbstract<Mensagem, br.com.sicoob.capes.negocio.entidades.Mensagem> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Mensagem instanciarEntidade() {
		return new Mensagem();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Mensagem converter(
			br.com.sicoob.capes.negocio.entidades.Mensagem entidade,
			Mensagem mensagem, Instituicao instituicao, Integer numPessoa)
			throws BancoobException {
		
		Pessoa pessoa = obterPessoa(numPessoa, instituicao);

		mensagem.setPessoa(pessoa);
		mensagem.setDataCadastro(entidade.getDataHoraInicio());
		mensagem.setCodigoDestinoExibicao(entidade.getCodigoTipoDestinoExibicao());
		mensagem.setMensagem(entidade.getMensagem());
		mensagem.setValidade(entidade.getValidade());
		mensagem.setIdMensagemDB2(entidade.getId());

		return mensagem;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EntidadeReplicavelDelegate<Mensagem, ?> obterDelegate() {
		return obterFabricaDelegates().criarMensagemDelegate();
	}
}
