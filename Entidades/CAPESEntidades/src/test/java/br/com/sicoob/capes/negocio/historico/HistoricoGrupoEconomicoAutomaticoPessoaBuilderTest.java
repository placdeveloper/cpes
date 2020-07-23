package br.com.sicoob.capes.negocio.historico;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoAutomaticoPessoa;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoNovo;
import br.com.sicoob.capes.negocio.entidades.historico.HistoricoGrupoEconomicoAutomaticoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;

public class HistoricoGrupoEconomicoAutomaticoPessoaBuilderTest {

	private HistoricoGrupoEconomicoAutomaticoPessoaBuilder builder = new HistoricoGrupoEconomicoAutomaticoPessoaBuilder();

	@Test
	public void verificarAtributrosPreenchidos() {
		GrupoEconomicoAutomaticoPessoa vigente = new GrupoEconomicoAutomaticoPessoa();
		vigente.setId(1);
		RelacionamentoPessoa relacionamento = new RelacionamentoPessoa();
		relacionamento.setIdRelacionamento(2l);
		vigente.setRelacionamentoPessoa(relacionamento);
		PessoaCompartilhamento pessoaCompartilhamento = new PessoaCompartilhamento() {
			private static final long serialVersionUID = 1L;
		};
		pessoaCompartilhamento.setId(3l);
		vigente.setPessoaCompartilhamento(pessoaCompartilhamento);
		vigente.setDataHoraInicio(new DateTimeDB());
		vigente.setBolControlador(true);
		vigente.setPercentualSocio(new BigDecimal("72"));
		GrupoEconomicoNovo grupoEconomico = new GrupoEconomicoNovo();
		grupoEconomico.setId(4);
		vigente.setGrupoEconomico(grupoEconomico);
		vigente.setIdUsuarioCadastro("idUsuarioCadastro");

		HistoricoGrupoEconomicoAutomaticoPessoa historico = builder.criarHistorico(vigente);

		Assert.assertNull(historico.getId());
		Assert.assertEquals(vigente.getDataHoraInicio(), historico.getDataHoraInicio());
		Assert.assertEquals(vigente.getBolControlador(), historico.getBolControlador());
		Assert.assertEquals(vigente.getPercentualSocio(), historico.getPercentualSocio());
		Assert.assertEquals(vigente.getRelacionamentoPessoa().getId(), historico.getIdRelacionamento());
		Assert.assertEquals(vigente.getPessoaCompartilhamento().getId(), historico.getIdPessoaCompartilhamento());
		Assert.assertNotNull(historico.getDataHoraFim());
		// Desc Motivo esclusão
		Assert.assertEquals(vigente.getId(), historico.getIdEntidadeVigente());
		Assert.assertEquals(vigente.getId(), historico.getIdGrupoAutomaticoPessoa());
		Assert.assertEquals(vigente.getIdUsuarioCadastro(), historico.getIdUsuarioCadastro());
		// usuário exclusão
	}

}
