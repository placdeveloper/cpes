package br.com.sicoob.capes.relatorio.negocio.servicos.ejb;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.capes.cadastro.negocio.vo.CentralSingularVO;
import br.com.sicoob.capes.negocio.entidades.Funcionario;
import br.com.sicoob.capes.negocio.entidades.Nucleo;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;
import br.com.sicoob.capes.relatorio.negocio.dto.RelatorioTributacaoDTO;
import br.com.sicoob.capes.relatorio.negocio.relatorios.RelatorioTributacao;
import br.com.sicoob.capes.relatorio.negocio.vo.RelatorioTributacaoVO;
import br.com.sicoob.capes.relatorio.persistencia.dao.RelatorioTributacaoDAO;
import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO;
import br.com.sicoob.relatorio.api.dto.RetornoProcessamentoRelatorioDTO;
import br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico;

/**
 * EJB contendo servicos relacionados a RelatorioTributacao.
 */
@Stateless
@Remote(IProcessamentoRelatorioServico.class)
public class RelatorioTributacaoServicoEJB extends CAPESRelatorioServicoEJB implements IProcessamentoRelatorioServico {

	@Inject
	@Default
	private RelatorioTributacaoDAO dao;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RetornoProcessamentoRelatorioDTO gerarRelatorio(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
		RelatorioTributacaoDTO filtro = new RelatorioTributacaoDTO();
		filtro.setCobraIOF((Boolean) dto.getDados().get("iof"));
		filtro.setCobraIR((Boolean) dto.getDados().get("ir"));
		filtro.setIdUnidadeInst((Integer) dto.getDados().get("unidade"));
		
		Number numeroCentral = (Number) dto.getDados().get("central");
		Number numeroSingular = (Number) dto.getDados().get("singular");
		Number idGerente = (Number) dto.getDados().get("gerente");
		Number idNucleo = (Number) dto.getDados().get("nucleo");
		
		String nomeNucleo = (String) dto.getDados().get("nomeNucleo");
		String nomeGerente = (String) dto.getDados().get("nomeGerente");

		if (numeroCentral != null) {
			CentralSingularVO central = new CentralSingularVO();
			central.setNumeroCooperativa(numeroCentral.intValue());
			filtro.setCentral(central);
		}

		if (numeroSingular != null) {
			CentralSingularVO singular = new CentralSingularVO();
			singular.setNumeroCooperativa(numeroSingular.intValue());
			filtro.setSingular(singular);
		}

		if (idGerente != null) {
			Funcionario funcionario = new Funcionario();
			funcionario.setIdFuncionario(idGerente.intValue());

			// Para exibir o nome do funcionario
			PessoaFisica pf = new PessoaFisica();
			pf.setNomePessoa(nomeGerente);
			Set<PessoaCompartilhamento> lista = new HashSet<PessoaCompartilhamento>();
			lista.add(pf);
			Pessoa pessoa = new Pessoa();
			pessoa.setCompartilhamentos(lista);
			funcionario.setPessoa(pessoa);
			
			filtro.setFuncionario(funcionario);
		}

		if (idNucleo != null) {
			Nucleo nucleo = new Nucleo();
			nucleo.setIdNucleo(idNucleo.intValue());
			nucleo.setDescricao(nomeNucleo);
			filtro.setNucleo(nucleo);
		}

		ConsultaDto<RelatorioTributacaoVO> consultaDto = new ConsultaDto<RelatorioTributacaoVO>();
		consultaDto.setFiltro(filtro);
		consultaDto.setOrdenacao((String) dto.getDados().get("ordenacao"));
		List<RelatorioTributacaoVO> lista = dao.pesquisar(consultaDto);
		
		if(lista == null || lista.isEmpty()) {
			throw new NegocioException("Não foram encontrados resultados para os filtros selecionados.");
		}
		
		RelatorioTributacao relatorio = new RelatorioTributacao((RelatorioTributacaoDTO) consultaDto.getFiltro(), lista);
		return relatorio.gerarRelatorio(rDto);
	}

}
