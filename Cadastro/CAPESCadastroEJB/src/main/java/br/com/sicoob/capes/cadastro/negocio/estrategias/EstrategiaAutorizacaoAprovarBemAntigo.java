package br.com.sicoob.capes.cadastro.negocio.estrategias;

import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum;
import br.com.sicoob.capes.comum.negocio.annotation.CamposAutorizacao;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.bemantigo.Bem;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemOnus;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemPosse;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemRegistro;
import br.com.sicoob.capes.negocio.entidades.bemantigo.Ibem;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;

/**
 * Estrat�gia para aprova��o de endere�os.
 */
public class EstrategiaAutorizacaoAprovarBemAntigo extends EstrategiaAutorizacaoAprovar {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void tratarAlteracao(Autorizacao autorizacao, Aprovavel aprovavel)
			throws BancoobException {

		TipoAutorizacaoEntidadeEnum tipoAutorizacao = TipoAutorizacaoEntidadeEnum.valueOf(autorizacao
				.getTipoAutorizacao());

		// recupera a annota��o para acessar o nome do ID
		CamposAutorizacao camposAutorizacao =
				tipoAutorizacao.getTipo().getAnnotation(CamposAutorizacao.class);

		// prepara o objeto para ser persistido
		Aprovavel entidadeAlteracao = obterEntidadeAlteracao(tipoAutorizacao, aprovavel);
		ReflexaoUtils.copiarPropriedades(entidadeAlteracao, aprovavel, "id", camposAutorizacao.id());
		ReflexaoUtils.setPropriedade(entidadeAlteracao, camposAutorizacao.id(),
				autorizacao.getIdRegistroAntigo());
		entidadeAlteracao.setIdInstituicaoAtualizacao(null);
		entidadeAlteracao.setVerificarAutorizacao(false);

		atualizarListasAlteracao(entidadeAlteracao);

		super.tratarAlteracao(autorizacao, aprovavel, entidadeAlteracao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void tratarInclusao(Autorizacao autorizacao, Aprovavel aprovavel)
			throws BancoobException {

		aprovavel.setIdInstituicaoAtualizacao(null);
		aprovavel.setVerificarAutorizacao(false);
		tratarHistorico(aprovavel, false);
		anexarDocumentosDossie(autorizacao);
		atualizarListasInclusao(aprovavel);
		alterar(aprovavel);
	}


	/**
	 * O m�todo Atualizar listas alteracao.
	 *
	 * @param entidadeAlteracao o valor de entidade alteracao
	 */
	@SuppressWarnings("unchecked")
	private void atualizarListasAlteracao(Aprovavel entidadeAlteracao) {

		Bem novoRegistro = (Bem)entidadeAlteracao;

		//atualizando a refer�ncia do novo registro criado a lsiatas existentes. Tratamento feito
		//quando se utiliza o hibernate para realiza��o de Cascade
		List<BemOnus> bensOnus = (List<BemOnus>)atualizarReferenciaBem(novoRegistro.getBensOnus(), novoRegistro);
		List<BemPosse> bensPosse = (List<BemPosse>)atualizarReferenciaBem(novoRegistro.getBensPosse(), novoRegistro);
		List<BemRegistro> bensResgistro = (List<BemRegistro>)atualizarReferenciaBem(novoRegistro.getBensRegistro() ,
				novoRegistro);

		novoRegistro.setBensOnus(bensOnus);
		novoRegistro.setBensPosse(bensPosse);
		novoRegistro.setBensRegistro(bensResgistro);
	}

	/**
	 * O m�todo Atualizar listas inclusao.
	 *
	 * @param entidadeAlteracao o valor de entidade alteracao
	 */
	private void atualizarListasInclusao(Aprovavel entidadeAlteracao) {

		Bem novoRegistro = (Bem)entidadeAlteracao;

		//atualizando a refer�ncia do novo registro criado a lsiatas existentes. Tratamento feito
		//quando se utiliza o hibernate para realiza��o de Cascade
		atualizarDataHoraInicio(novoRegistro.getBensOnus());
		atualizarDataHoraInicio(novoRegistro.getBensPosse());
		atualizarDataHoraInicio(novoRegistro.getBensRegistro());
	}

	/**
	 * O m�todo Atualizar data hora inicio.
	 *
	 * @param lista o valor de lista
	 */
	private void atualizarDataHoraInicio(List<? extends Ibem> lista) {

		if (lista != null
				&& !lista.isEmpty()) {
			for (Ibem bem : lista) {
				bem.setDataHoraInicio(new DateTimeDB());
			}
		}
	}

	/**
	 * Atualizar referencia bem.
	 *
	 * @param lista o valor de lista
	 * @param novoRegistro o valor de novo registro
	 * @return the list<? extends ibem>
	 */
	private List<? extends Ibem> atualizarReferenciaBem(List<? extends Ibem> lista, Bem novoRegistro) {

		 List<Ibem> retorno = new ArrayList<Ibem>();

		if (lista != null
				&& !lista.isEmpty()) {
			for (Ibem bem : lista) {
				Ibem novoBem = ReflexaoUtils.construirObjetoPorClasse(bem.getClass());
						ReflexaoUtils.copiarPropriedades(novoBem, bem, "id", "idBemOnus",
								"idBemPosse", "idBemRegistro");
				novoBem.setBem(novoRegistro);
				retorno.add(novoBem);
			}
		}
		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Aprovavel obterEntidadeAlteracao(TipoAutorizacaoEntidadeEnum tipoAutorizacao, Aprovavel aprovavel){
		return ReflexaoUtils.construirObjetoPorClasse(aprovavel.getClass());
	}

}