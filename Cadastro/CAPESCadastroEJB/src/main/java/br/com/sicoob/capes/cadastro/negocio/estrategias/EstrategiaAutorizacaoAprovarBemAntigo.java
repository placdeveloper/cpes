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
 * Estratégia para aprovação de endereços.
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

		// recupera a annotação para acessar o nome do ID
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
	 * O método Atualizar listas alteracao.
	 *
	 * @param entidadeAlteracao o valor de entidade alteracao
	 */
	@SuppressWarnings("unchecked")
	private void atualizarListasAlteracao(Aprovavel entidadeAlteracao) {

		Bem novoRegistro = (Bem)entidadeAlteracao;

		//atualizando a referência do novo registro criado a lsiatas existentes. Tratamento feito
		//quando se utiliza o hibernate para realização de Cascade
		List<BemOnus> bensOnus = (List<BemOnus>)atualizarReferenciaBem(novoRegistro.getBensOnus(), novoRegistro);
		List<BemPosse> bensPosse = (List<BemPosse>)atualizarReferenciaBem(novoRegistro.getBensPosse(), novoRegistro);
		List<BemRegistro> bensResgistro = (List<BemRegistro>)atualizarReferenciaBem(novoRegistro.getBensRegistro() ,
				novoRegistro);

		novoRegistro.setBensOnus(bensOnus);
		novoRegistro.setBensPosse(bensPosse);
		novoRegistro.setBensRegistro(bensResgistro);
	}

	/**
	 * O método Atualizar listas inclusao.
	 *
	 * @param entidadeAlteracao o valor de entidade alteracao
	 */
	private void atualizarListasInclusao(Aprovavel entidadeAlteracao) {

		Bem novoRegistro = (Bem)entidadeAlteracao;

		//atualizando a referência do novo registro criado a lsiatas existentes. Tratamento feito
		//quando se utiliza o hibernate para realização de Cascade
		atualizarDataHoraInicio(novoRegistro.getBensOnus());
		atualizarDataHoraInicio(novoRegistro.getBensPosse());
		atualizarDataHoraInicio(novoRegistro.getBensRegistro());
	}

	/**
	 * O método Atualizar data hora inicio.
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