package br.com.sicoob.capes.replicacao.negocio.atualizacaocadastral.conversao;

import java.math.BigDecimal;
import java.util.Date;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.dto.AvalFinanceiraDTO;
import br.com.sicoob.capes.negocio.dto.AtualizacaoCadastralDTO;
import br.com.sicoob.capes.negocio.entidades.legado.AvaliacaoFinanceira;
import br.com.sicoob.capes.replicacao.negocio.delegates.CAPESReplicacaoComumFabricaDelegates;

/**
 * A Classe ConversorAvalFinanceira.
 */
public class ConversorAvalFinanceira extends Conversor<AvaliacaoFinanceira, AvalFinanceiraDTO> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AvaliacaoFinanceira obterEntidadeReplicacao(AtualizacaoCadastralDTO<AvalFinanceiraDTO> dto) throws BancoobException {
		AvalFinanceiraDTO entidadeCadastro = dto.getEntidadeCadastro();

		CAPESReplicacaoComumFabricaDelegates fabrica = CAPESReplicacaoComumFabricaDelegates.getInstance();
		AvaliacaoFinanceira avaliacaoFinanceiraBanco = fabrica.criarAvaliacaoFinanceiraDelegate().obter(dto.getIdPessoaLegado(),
				dto.getInstituicao().getIdInstituicao());

		if (avaliacaoFinanceiraBanco != null) {
			avaliacaoFinanceiraBanco.setDataUltimaRenovacao(entidadeCadastro.getPessoaCompartilhamento().getDataRenovacaoCadastral());
		} else {
			avaliacaoFinanceiraBanco = new AvaliacaoFinanceira();
			avaliacaoFinanceiraBanco.setDataUltimaRenovacao(entidadeCadastro.getPessoaCompartilhamento().getDataRenovacaoCadastral());
			avaliacaoFinanceiraBanco.setDataUltimaAtualizacao(new Date());
			avaliacaoFinanceiraBanco.setIdDB2(entidadeCadastro.getId());
			avaliacaoFinanceiraBanco.setIdSQL(dto.getIdPessoaLegado());
			avaliacaoFinanceiraBanco.setValorLimiteCreditoCurtoPrazo(new BigDecimal(0));
			avaliacaoFinanceiraBanco.setValorLimiteCreditoLongoPrazo(new BigDecimal(0));
			avaliacaoFinanceiraBanco.setValorLimiteCreditoRotativo(new BigDecimal(0));
			avaliacaoFinanceiraBanco.setValorTotalGastoFamiliar(new BigDecimal(0));
		}

		return avaliacaoFinanceiraBanco;
	}

}
