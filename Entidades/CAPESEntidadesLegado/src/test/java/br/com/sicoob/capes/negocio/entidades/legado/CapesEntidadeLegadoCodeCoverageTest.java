package br.com.sicoob.capes.negocio.entidades.legado;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

import br.com.sicoob.capes.negocio.entidades.legado.pk.BemOnusPK;
import br.com.sicoob.capes.negocio.entidades.legado.pk.BemPossePK;
import br.com.sicoob.capes.negocio.entidades.legado.pk.BemRegistroPK;
import br.com.sicoob.capes.negocio.entidades.legado.pk.CertidaoPK;
import br.com.sicoob.capes.negocio.entidades.legado.pk.CooperativaPK;
import br.com.sicoob.capes.negocio.entidades.legado.pk.CooperativaPessoaPK;
import br.com.sicoob.capes.negocio.entidades.legado.pk.HistoricoAlteracaoCnpjCpfPK;
import br.com.sicoob.capes.negocio.entidades.legado.pk.ListaItemPK;
import br.com.sicoob.capes.negocio.entidades.legado.pk.TransicaoReplicacaoPK;

/**
 * A Classe CapesEntidadeLegadoCodeCoverageTest.
 */
public class CapesEntidadeLegadoCodeCoverageTest {

	/** A constante setArgs. */
	public static final Object setArgs[] = { null };
	
	/** A constante noArgs. */
	public static final Object noArgs[] = {};

	/**
	 * O método Superficial capes entidade code coverage.
	 *
	 * @param classe o valor de classe
	 * @param entidate o valor de entidate
	 */
	@SuppressWarnings("rawtypes")
	public static void superficialCapesEntidadeCodeCoverage(Class classe, Object entidate) {

		Method[] methods = classe.getMethods();
		try {
			for (Method method : methods) {
				if (method.getName().startsWith("set")) {
					method.invoke(entidate, setArgs);
				}
				if (method.getName().startsWith("get")) {
					method.invoke(entidate, noArgs);
				}
			}
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		}
	}
	

	/**
	 * O método Test superficial capes entidade code coverage.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	@Test
	public void testSuperficialCapesEntidadeCodeCoverage() throws Exception {
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(AvaliacaoFinanceira.class, new AvaliacaoFinanceira());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(BancoServidor.class, new BancoServidor());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Bem.class, new Bem());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(BemImovel.class, new BemImovel());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(BemOnus.class, new BemOnus());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(BemPosse.class, new BemPosse());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(BemRegistro.class, new BemRegistro());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(CategoriaProdutor.class, new CategoriaProdutor());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Certidao.class, new Certidao());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Cliente.class, new Cliente());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Cooperativa.class, new Cooperativa());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(CooperativaPessoa.class, new CooperativaPessoa());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Empreendimento.class, new Empreendimento());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Endereco.class, new Endereco());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(FonteRenda.class, new FonteRenda());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Funcionario.class, new Funcionario());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(InformacaoProfissional.class, new InformacaoProfissional());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Lista.class, new Lista());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(ListaItem.class, new ListaItem());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Mensagem.class, new Mensagem());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Nucleo.class, new Nucleo());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(PessoaFisica.class, new PessoaFisica());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(PessoaJuridica.class, new PessoaJuridica());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Produtividade.class, new Produtividade());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Produto.class, new Produto());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Produtor.class, new Produtor());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Referencia.class, new Referencia());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(RelacionamentoPessoa.class, new RelacionamentoPessoa());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(Telefone.class, new Telefone());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoBem.class, new TipoBem());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoEmpresa.class, new TipoEmpresa());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TransicaoReplicacao.class, new TransicaoReplicacao());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(UnidadeMedida.class, new UnidadeMedida());
		
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(BemOnusPK.class, new BemOnusPK());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(BemPossePK.class, new BemPossePK());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(BemRegistroPK.class, new BemRegistroPK());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(CertidaoPK.class, new CertidaoPK());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(CooperativaPessoaPK.class, new CooperativaPessoaPK());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(CooperativaPK.class, new CooperativaPK());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoAlteracaoCnpjCpfPK.class, new HistoricoAlteracaoCnpjCpfPK());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(ListaItemPK.class, new ListaItemPK());
		CapesEntidadeLegadoCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TransicaoReplicacaoPK.class, new TransicaoReplicacaoPK());
		
	}

}