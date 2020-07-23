package br.com.sicoob.capes.negocio.historico;

import org.junit.Assert;
import org.junit.Test;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.comum.negocio.enums.TipoGrupoEconomicoEnum;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoNovo;
import br.com.sicoob.capes.negocio.entidades.TipoGrupoEconomico;
import br.com.sicoob.capes.negocio.entidades.historico.HistoricoGrupoEconomicoNovo;

public class HistoricoGrupoEconomicoNovoBuilderTest {

	private HistoricoGrupoEconomicoNovoBuilder builder = new HistoricoGrupoEconomicoNovoBuilder();

	@Test
	public void verificarAtributrosPreenchidos() {
		GrupoEconomicoNovo vigente = new GrupoEconomicoNovo();
		vigente.setId(1);
		vigente.setDataHoraInicio(new DateTimeDB());
		vigente.setIdUsuarioCadastro("usuario.teste");
		vigente.setNome("NOME Grupo");
		TipoGrupoEconomico tipoGrupoEconomico = new TipoGrupoEconomico();
		tipoGrupoEconomico.setCodigo(TipoGrupoEconomicoEnum.AUTOMATICO.getCodigo());
		vigente.setTipo(tipoGrupoEconomico);
		
		HistoricoGrupoEconomicoNovo historico = builder.criarHistorico(vigente);
		
		// Desc Motivo esclusão
		// usuário exclusão
		Assert.assertNull(historico.getId());
		Assert.assertNotNull(historico.getDataHoraFim());
		Assert.assertEquals(vigente.getId(), historico.getIdEntidadeVigente());
		Assert.assertEquals(vigente.getId(), historico.getIdGrupo());
		Assert.assertEquals(vigente.getDataHoraInicio(), historico.getDataHoraInicio());
		Assert.assertEquals(vigente.getDataHoraInicio(), historico.getDataHoraInicio());
		Assert.assertEquals(vigente.getIdUsuarioCadastro(), historico.getIdUsuarioCadastro());
		Assert.assertEquals(vigente.getNome(), historico.getNome());
		Assert.assertEquals(vigente.getTipo().getId(), historico.getCodTipoGrupoEconomico());
	}

}
