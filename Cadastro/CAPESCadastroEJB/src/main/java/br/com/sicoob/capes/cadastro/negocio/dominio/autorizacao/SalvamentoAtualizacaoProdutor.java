package br.com.sicoob.capes.cadastro.negocio.dominio.autorizacao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.util.SerializacaoUtils;
import br.com.sicoob.capes.comum.negocio.enums.TipoOperacaoEnum;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;

/**
 * A Classe SalvamentoAtualizacaoProdutor.
 */
public class SalvamentoAtualizacaoProdutor extends SalvamentoAtualizacaoGenerico {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void salvarAtualizacao(Aprovavel aprovavel, Autorizacao autorizacao, TipoOperacaoEnum tipoOperacao) throws BancoobException {
		Aprovavel registro = null;
		if(TipoOperacaoEnum.A.equals(tipoOperacao)){
			registro = (Aprovavel) ReflexaoUtils.construirObjetoPorClasse(aprovavel.getClass());
			ReflexaoUtils.copiarPropriedades(registro, aprovavel, "dataHoraInicio");
			registro.setIdInstituicaoAtualizacao(autorizacao.getInstituicaoOrigem().getIdInstituicao());
		}else{
			registro = aprovavel;
		}
		autorizacao.setAlteracao(SerializacaoUtils.serilizarAprovavel(registro, tipoOperacao, "dataHoraInicio"));

		super.salvarAtualizacao(aprovavel, autorizacao, tipoOperacao);

		// Adequação para o uso do JSON
		autorizacao.setIdRegistroNovo(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Autorizacao criarAutorizacao(Aprovavel aprovavel, TipoOperacaoEnum tipoOperacao, Instituicao instOrigem) throws BancoobException {
		Autorizacao autorizacao = super.criarAutorizacao(aprovavel, tipoOperacao, instOrigem);

		if(TipoOperacaoEnum.I.equals(tipoOperacao)){
			autorizacao.setTipoOperacao(TipoOperacaoEnum.A);
		}

		return autorizacao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] getPropriedadesIgnoradas() {
		String[] propriedadesBasicas = super.getPropriedadesIgnoradas();
		String[] propriedadesNovas = new String[] {
				"emAlteracao",
				"gravarHistorico",
				"idRegistroControlado",
				"categoria",
				"codigoInscricao",
				"mesAnoTemporario",
				"mesAnoPermanente",
				"qtdTemporario",
				"qtdPermanente",
				"documentosComprobatorios",
				"dataHoraInicio"
		};
		String[] propriedades = new String[propriedadesBasicas.length + propriedadesNovas.length];
		System.arraycopy(propriedadesBasicas, 0, propriedades, 0, propriedadesBasicas.length);
		System.arraycopy(propriedadesNovas, 0, propriedades, propriedadesBasicas.length, propriedadesNovas.length);
		return propriedades;
	}
	
	/**
	 * {@inheritDoc}
	 */
	protected Aprovavel criarNovoRegistro(final Aprovavel aprovavel, final Instituicao instAtualizacao,
			final Class<Aprovavel> classeAprovavel) {
		
		Aprovavel registroCriado = super.criarNovoRegistro(aprovavel, instAtualizacao, classeAprovavel);
		
		return registroCriado;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean copiarID() {
		return true;
	}

}