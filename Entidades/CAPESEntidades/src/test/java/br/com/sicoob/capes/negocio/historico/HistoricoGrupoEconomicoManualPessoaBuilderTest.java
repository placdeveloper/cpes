package br.com.sicoob.capes.negocio.historico;

import org.junit.Assert;
import org.junit.Test;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoManualPessoa;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoNovo;
import br.com.sicoob.capes.negocio.entidades.historico.HistoricoGrupoEconomicoManualPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

public class HistoricoGrupoEconomicoManualPessoaBuilderTest {

	private HistoricoGrupoEconomicoManualPessoaBuilder builder = new HistoricoGrupoEconomicoManualPessoaBuilder();

	@Test
	public void verificarAtributrosPreenchidos() {
		GrupoEconomicoManualPessoa vigente = new GrupoEconomicoManualPessoa();
		vigente.setId(1);
		PessoaCompartilhamento pessoaCompartilhamento = new PessoaCompartilhamento() {
			private static final long serialVersionUID = 1L;
		};
		pessoaCompartilhamento.setId(2l);
		vigente.setPessoaCompartilhamento(pessoaCompartilhamento);
		vigente.setDataHoraInicio(new DateTimeDB());
		vigente.setIdUsuarioCadastro("idUsuarioCadastro");
		GrupoEconomicoNovo grupoEconomico = new GrupoEconomicoNovo();
		grupoEconomico.setId(3);
		vigente.setGrupoEconomico(grupoEconomico);
		GrupoEconomicoNovo grupoEconomicoAutomatico = new GrupoEconomicoNovo();
		grupoEconomicoAutomatico.setId(4);
		vigente.setGrupoEconomicoAutomatico(grupoEconomicoAutomatico);

		HistoricoGrupoEconomicoManualPessoa historico = builder.criarHistorico(vigente);

		Assert.assertNull(historico.getId());
		Assert.assertEquals(vigente.getDataHoraInicio(), historico.getDataHoraInicio());
		Assert.assertEquals(vigente.getIdUsuarioCadastro(), historico.getIdUsuarioCadastro());
		Assert.assertEquals(vigente.getPessoaCompartilhamento().getId(), historico.getIdPessoaCompartilhamento());
		Assert.assertNotNull(historico.getDataHoraFim());
		// Desc Motivo esclusão
		// ID Hist Grupo Economico Automatico Pessoa
		Assert.assertEquals(vigente.getId(), historico.getIdEntidadeVigente());
		Assert.assertEquals(vigente.getId(), historico.getIdGrupoManualPessoa());
	}

}
