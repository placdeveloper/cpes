package br.com.sicoob.capes.relatorio.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.capes.comum.negocio.enums.FuncionalidadeValidacaoCadastralEnum;
import br.com.sicoob.capes.relatorio.negocio.dto.RelatorioValidacaoCadastralDTO;
import br.com.sicoob.capes.relatorio.negocio.enums.TipoRelatorioEnum;
import br.com.sicoob.capes.relatorio.negocio.relatorios.RelatorioValidacaoCadastral;
import br.com.sicoob.capes.relatorio.negocio.relatorios.RelatorioValidacaoCadastralAnalitico;
import br.com.sicoob.capes.relatorio.negocio.relatorios.RelatorioValidacaoCadastralSintetico;
import br.com.sicoob.capes.relatorio.negocio.vo.RelatorioValidacaoCadastralAnaliticoVO;
import br.com.sicoob.capes.relatorio.negocio.vo.RelatorioValidacaoCadastralSinteticoVO;
import br.com.sicoob.capes.relatorio.persistencia.dao.RelatorioValidacaoCadastralDAO;
import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO;
import br.com.sicoob.relatorio.api.dto.RetornoProcessamentoRelatorioDTO;
import br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico;

/**
 * EJB contendo servicos relacionados a RelatorioValidacaoCadastral.
 */
@Stateless
@Remote({ IProcessamentoRelatorioServico.class })
public class RelatorioValidacaoCadastralServicoEJB extends CAPESRelatorioServicoEJB implements IProcessamentoRelatorioServico {
	
	@Inject
	@Default
	private RelatorioValidacaoCadastralDAO dao;
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	private List<RelatorioValidacaoCadastralAnaliticoVO> obterDadosRelatorioAnalitico(RelatorioValidacaoCadastralDTO dto) throws BancoobException {
		List<RelatorioValidacaoCadastralAnaliticoVO> lista = dao.obterDadosRelatorioAnalitico(dto);
		
		if (lista.isEmpty()) {
			throw new NegocioException("Não foram encontrados resultados para os filtros selecionados.");
		}
		
		return lista;
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	private List<RelatorioValidacaoCadastralSinteticoVO> obterDadosRelatorioSintetico(RelatorioValidacaoCadastralDTO dto) throws BancoobException {
		List<RelatorioValidacaoCadastralSinteticoVO> lista = dao.obterDadosRelatorioSintetico(dto);
		
		if (lista.isEmpty()) {
			throw new NegocioException("Não foram encontrados resultados para os filtros selecionados.");
		}
		
		return lista;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RetornoProcessamentoRelatorioDTO gerarRelatorio(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
		Integer tipoRelatorio = (Integer) dto.getDados().get("tipo");
		RelatorioValidacaoCadastralDTO dtoValidacao = obterDTOValidacao(dto);
		
		RelatorioValidacaoCadastral relatorio = null;
		if (tipoRelatorio != null && TipoRelatorioEnum.isAnalitico(tipoRelatorio)) {
			relatorio = gerarRelatorioAnalitico(dtoValidacao);
		} else if (tipoRelatorio != null && TipoRelatorioEnum.isSintetico(tipoRelatorio)) {
			relatorio = gerarRelatorioSintetico(dtoValidacao);
		}
		
		if(relatorio != null) {
			return relatorio.gerarRelatorio(rDto);
		}
		
		return null;
	}
	
	/**
	 * O método Gerar relatorio analitico.
	 *
	 * @param retorno o valor de retorno
	 * @param dtoValidacao o valor de dto validacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private RelatorioValidacaoCadastral gerarRelatorioAnalitico(RelatorioValidacaoCadastralDTO dtoValidacao) throws BancoobException {
		List<RelatorioValidacaoCadastralAnaliticoVO> lista = obterDadosRelatorioAnalitico(dtoValidacao);
		RelatorioValidacaoCadastral relatorio = new RelatorioValidacaoCadastralAnalitico(dtoValidacao, lista);
		return relatorio;
	}
	
	/**
	 * O método Gerar relatorio sintetico.
	 *
	 * @param retorno o valor de retorno
	 * @param dtoValidacao o valor de dto validacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private RelatorioValidacaoCadastral gerarRelatorioSintetico(RelatorioValidacaoCadastralDTO dtoValidacao) throws BancoobException {
		List<RelatorioValidacaoCadastralSinteticoVO> lista = obterDadosRelatorioSintetico(dtoValidacao);
		RelatorioValidacaoCadastral relatorio = new RelatorioValidacaoCadastralSintetico(dtoValidacao, lista);
		return relatorio;
	}
	
	/**
	 * Obter dto validacao.
	 *
	 * @param dto o valor de dto
	 * @return RelatorioValidacaoCadastralDTO
	 */
	private RelatorioValidacaoCadastralDTO obterDTOValidacao(ParametroDTO dto) {
		RelatorioValidacaoCadastralDTO retorno = null;
		if (dto != null) {
			retorno = new RelatorioValidacaoCadastralDTO();
			retorno.setIdInstituicao((Integer) dto.getDados().get("idInstituicao"));
			retorno.setCentral((Integer) dto.getDados().get("central"));
			retorno.setSingular((Integer) dto.getDados().get("singular"));
			retorno.setIdSingular((Integer) dto.getDados().get("idInstituicao"));
			retorno.setUnidade((Integer) dto.getDados().get("unidade"));
			retorno.setNucleo((Integer) dto.getDados().get("nucleo"));
			retorno.setNomeNucleo((String) dto.getDados().get("nomeNucleo"));
			retorno.setGerente((Integer) dto.getDados().get("gerente"));
			retorno.setNomeGerente((String) dto.getDados().get("nomeGerente"));
			String funcionalidade = (String) dto.getDados().get("funcionalidade");
			retorno.setFuncionalidade(funcionalidade != null ? FuncionalidadeValidacaoCadastralEnum.valueOf(funcionalidade) : null);
			retorno.setCpfCnpj((String) dto.getDados().get("cpfCnpj"));
			retorno.setIdTipoErro((Integer) dto.getDados().get("tipoErro"));
			retorno.setDescricaoTipoErro((String) dto.getDados().get("descricaoErro"));
			retorno.setCodigoTipoRegra((String) dto.getDados().get("restricao"));
		}
		return retorno;
	}

}