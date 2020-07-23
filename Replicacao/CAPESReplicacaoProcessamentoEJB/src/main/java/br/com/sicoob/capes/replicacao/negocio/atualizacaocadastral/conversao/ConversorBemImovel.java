/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.conversao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Localidade;
import br.com.sicoob.capes.negocio.entidades.legado.BemImovel;
import br.com.sicoob.capes.negocio.entidades.legado.UnidadeMedida;
import br.com.sicoob.capes.replicacao.negocio.delegates.BemImovelDelegate;

/**
 * Conversor do Bem.
 * @author erico.junior
 */
public class ConversorBemImovel extends
		ConversorAbstract<BemImovel, br.com.sicoob.capes.negocio.entidades.bemantigo.BemImovel> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemImovel instanciarEntidade() {
		return new BemImovel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemImovel converter(br.com.sicoob.capes.negocio.entidades.bemantigo.BemImovel entidade,
			BemImovel novaEntidade, Instituicao instituicao, Integer numPessoa)
			throws BancoobException {

		ConversorBem conversorBemImovel = new ConversorBem();

		BemImovel bem  = (BemImovel)conversorBemImovel.converter(entidade, novaEntidade, instituicao, numPessoa);
		bem.setArea(obterValor(entidade.getArea()));
		bem.setBenfeitorias(entidade.getBenfeitoria());
		bem.setDenominacao(entidade.getDenominacao());
		bem.setValorBenfeitorias(obterValor(entidade.getValorBenfeitoria()));
		bem.setIdTipoLocalizacao(obterTipoLocalizacao(entidade.getCodLocalizacao()));
	
		Localidade comarca = obterLocalidade(entidade.getIdLocalidadeComarca());
		if(comarca != null) {
			bem.setComarca(comarca.getNome());
			bem.setUfComarca(comarca.getUf().getSiglaUF());
		}
		
		Localidade localidade = obterLocalidade(entidade.getIdLocalidadeImovel());
		if(localidade != null) {
			bem.setMunicipio(localidade.getNome());
			bem.setUf(localidade.getUf().getSiglaUF());
		}
		
		if (entidade.getUnidadeMedida() != null) {
			UnidadeMedida unidadeMedida = new UnidadeMedida();
			unidadeMedida.setId(entidade.getUnidadeMedida().getCodigo());
			bem.setUnidadeMedida(unidadeMedida);
		}
		return bem;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemImovelDelegate obterDelegate() {
		return obterFabricaDelegates().criarBemImovelDelegate();
	}

	/**
	 * Recupera o tipo de localização
	 * 1 = Urbano
	 * 2 = Rural
	 * @param codLocalizacao
	 * @return
	 */
	private Short obterTipoLocalizacao(String codLocalizacao) {
		
		Short tipoLocalizacao = null;
		if("U".equalsIgnoreCase(codLocalizacao)){
			tipoLocalizacao = Short.valueOf("1");
		} else if("R".equalsIgnoreCase(codLocalizacao)){
			tipoLocalizacao = Short.valueOf("2");
		}
		return tipoLocalizacao;
	}	
	
	/**
	 * Obter entidade para alteracao.
	 *
	 * @param instituicao o valor de instituicao
	 * @param numPessoa o valor de num pessoa
	 * @param entidadeCapes o valor de entidade capes
	 * @return R
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public BemImovel obterEntidadeParaAlteracao(Instituicao instituicao, Integer numPessoa, br.com.sicoob.capes.negocio.entidades.bemantigo.BemImovel entidadeCapes)
		    throws BancoobException {
		BemImovel entidadeConsulta  = instanciarEntidade();
		entidadeConsulta.setIdDB2(entidadeCapes.getId());
		List<BemImovel> listEntidade = obterDelegate().obterPorIdsDB2(entidadeConsulta, instituicao.getIdInstituicao());
		BemImovel entidade = null;
		if(listEntidade != null && !listEntidade.isEmpty()) {
			entidade = obterDelegate().obter(listEntidade.get(0).getId());
		}
		return converter(entidadeCapes, entidade, instituicao, numPessoa);
	}
	
	/**
	 * Obter entidade para exclusao.
	 *
	 * @param instituicao o valor de instituicao
	 * @param numPessoa o valor de num pessoa
	 * @param entidadeCapes o valor de entidade capes
	 * @return R
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public BemImovel obterEntidadeParaExclusao(Instituicao instituicao, Integer numPessoa, br.com.sicoob.capes.negocio.entidades.bemantigo.BemImovel entidadeCapes) 
			throws BancoobException {
		
		BemImovel entidadeConsulta = instanciarEntidade();
		entidadeConsulta.setIdDB2(entidadeCapes.getId());
		
		List<BemImovel> listEntidade = obterDelegate().obterPorIdsDB2(entidadeConsulta, instituicao.getIdInstituicao());
		
		BemImovel entidade = null;
		if(listEntidade != null && !listEntidade.isEmpty()) {
			entidade = listEntidade.get(0);
		}
		
		return entidade;
	}

}
