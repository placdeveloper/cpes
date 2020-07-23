package br.com.sicoob.capes.cadastro.negocio.estrategias;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;
import br.com.sicoob.capes.cadastro.util.SerializacaoUtils;

/**
 * Estrat�gia para aprova��o dos casos que n�o seguem a estrutura padr�o onde a
 * altera��o � salva na mesma tabela que os dados v�lidos
 * 
 * @author Rodrigo.Chaves
 */
public class EstrategiaAutorizacaoAprovarEspecifico extends EstrategiaAutorizacaoAprovar {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Aprovavel obterDadosAprovacao(Autorizacao autorizacao) throws BancoobException {
		return SerializacaoUtils.deserializarJson(Aprovavel.class, autorizacao.getAlteracao());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void tratarAlteracao(Autorizacao autorizacao, Aprovavel registroNovo)
			throws BancoobException {
		
		registroNovo.setIdInstituicaoAtualizacao(null);
		registroNovo.setVerificarAutorizacao(false);
		anexarDocumentosDossie(autorizacao);
		alterar(registroNovo);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void tratarInclusao(Autorizacao autorizacao, Aprovavel registroNovo)
			throws BancoobException {
		
		registroNovo.setIdInstituicaoAtualizacao(null);
		registroNovo.setVerificarAutorizacao(false);
		registroNovo.setGravarHistorico(false);
		anexarDocumentosDossie(autorizacao);
		incluir(registroNovo);
	}

}
