package br.com.sicoob.capes.negocio.historico;

import org.junit.Assert;
import org.junit.Test;

import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoAutomaticoPessoa;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoManualPessoa;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoNovo;
import br.com.sicoob.capes.negocio.entidades.interfaces.Historico;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;

public class FabricaHistoricoBuilderTest {

	private final FabricaHistoricoBuilder<Historico, Vigente> fabrica = FabricaHistoricoBuilder.getInstance();

	@Test
	public void grupoEconomicoNovo() {
		HistoricoBuilder<?, ?> historico = fabrica.obterBuilder(new GrupoEconomicoNovo());
		Assert.assertTrue(historico instanceof HistoricoGrupoEconomicoNovoBuilder);
	}

	@Test
	public void grupoEconomicoAutomaticoPessoa() {
		HistoricoBuilder<?, ?> historico = fabrica.obterBuilder(new GrupoEconomicoAutomaticoPessoa());
		Assert.assertTrue(historico instanceof HistoricoGrupoEconomicoAutomaticoPessoaBuilder);
	}

	@Test
	public void grupoEconomicoManualPessoa() {
		HistoricoBuilder<?, ?> historico = fabrica.obterBuilder(new GrupoEconomicoManualPessoa());
		Assert.assertTrue(historico instanceof HistoricoGrupoEconomicoManualPessoaBuilder);
	}

}