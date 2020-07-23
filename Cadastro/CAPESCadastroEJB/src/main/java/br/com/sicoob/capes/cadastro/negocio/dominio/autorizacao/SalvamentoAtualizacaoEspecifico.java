// 22/02/2013 - 08:40:56
package br.com.sicoob.capes.cadastro.negocio.dominio.autorizacao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.util.SerializacaoUtils;
import br.com.sicoob.capes.comum.negocio.enums.TipoOperacaoEnum;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;

/**
 * 
 * @author Rodrigo.Chaves
 */
public class SalvamentoAtualizacaoEspecifico extends SalvamentoAtualizacaoGenerico {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void salvarAtualizacao(Aprovavel aprovavel, Autorizacao autorizacao,
			TipoOperacaoEnum tipoOperacao) throws BancoobException {
		getAutorizacaoCadastroDelegate().removerObjetoSessao(aprovavel);
		aprovavel.setIdInstituicaoAtualizacao(autorizacao.getInstituicaoOrigem().getIdInstituicao());
		autorizacao.setAlteracao(SerializacaoUtils.serilizarAprovavel(aprovavel, tipoOperacao, 
				getPropriedadesIgnoradas()));
	}

	/**
	 * {@inheritDoc}
	 */
	protected String[] getPropriedadesIgnoradas() {
		
		String[] propriedadesPai = super.getPropriedadesIgnoradas();
		String[] propriedades = new String[propriedadesPai.length + 1];
		System.arraycopy(propriedadesPai, 0, propriedades, 0, propriedadesPai.length);
		propriedades[propriedadesPai.length] = "dataHoraInicio";
		return propriedades;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Autorizacao criarAutorizacao(Aprovavel aprovavel,
			TipoOperacaoEnum tipoOperacao, Instituicao instOrigem)
			throws BancoobException {
		Autorizacao autorizacao = super.criarAutorizacao(aprovavel, tipoOperacao, instOrigem);
		if (TipoOperacaoEnum.I.equals(tipoOperacao)) {
			autorizacao.setIdRegistroAntigo(null);
			autorizacao.setIdRegistroNovo(aprovavel.getId());
		}
		return autorizacao;
	}
}
