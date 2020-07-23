/**
 * 
 */
package br.com.sicoob.capes.relatorio.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.commons.lang.math.NumberUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroCrudDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.GrupoCompartilhamentoDelegate;
import br.com.sicoob.capes.comum.negocio.vo.GrupoCompartilhamentoVO;
import br.com.sicoob.capes.comum.negocio.vo.InstituicaoVO;
import br.com.sicoob.capes.negocio.entidades.CompartilhamentoCadastro;
import br.com.sicoob.capes.negocio.entidades.vigente.GrupoCompartilhamento;
import br.com.sicoob.capes.relatorio.negocio.relatorios.RelatorioGrupoCompartilhamento;
import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO;
import br.com.sicoob.relatorio.api.dto.RetornoProcessamentoRelatorioDTO;
import br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico;

/**
 * Implementa as operações do serviço de GrupoCompartilhamento.
 * 
 * @author juan.damasceno
 */
@Stateless
@Remote( { IProcessamentoRelatorioServico.class })
public class RelatorioGrupoCompartilhamentoServicoEJB extends CAPESRelatorioDominioServicoEJB<GrupoCompartilhamento> {

	/** O atributo delegate. */
	private GrupoCompartilhamentoDelegate delegate = CAPESCadastroFabricaDelegates.getInstance().criarGrupoCompartilhamentoDelegate();

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RetornoProcessamentoRelatorioDTO gerarRelatorio(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
		Short codCompartilhamentoCadastro = Short.valueOf(dto.getDados().get("codCompartilhamentoCadastro").toString());
		
		UsuarioBancoob usuario = obterUsuario();
		Integer idInstituicao = Integer.valueOf(usuario.getIdInstituicao());
		InstituicaoVO cooperativa = obterInstituicaoPorId(idInstituicao);
		String siglaCooperativa = cooperativa.getSiglaInstituicao();
		Integer numeroCooperativa = cooperativa.getNumero();
		
		GrupoCompartilhamento filtro = new GrupoCompartilhamento();
		CompartilhamentoCadastro compCadastro = new CompartilhamentoCadastro();
		compCadastro.setCodigo(codCompartilhamentoCadastro);
		filtro.setCompartilhamentoCadastro(compCadastro);
		ConsultaDto<GrupoCompartilhamento> consultaDto = new ConsultaDto<GrupoCompartilhamento>();
		consultaDto.setFiltro(filtro);
		
		List<GrupoCompartilhamentoVO> lista = listarInstuicoesPorCompartilhamento(consultaDto).getResultado();
		if(lista == null || lista.isEmpty()) {
			throw new NegocioException("Não foram encontrados resultados para os filtros selecionados.");
		}
		
		RelatorioGrupoCompartilhamento relatorio = new RelatorioGrupoCompartilhamento(lista, numeroCooperativa, siglaCooperativa, "CLI023", "Relatório de Grupo Compartilhamento", "GrupoCompartilhamento.jasper");
		
		return relatorio.gerarRelatorio(rDto);
	}
	
	/**
	 * Listar instuicoes por compartilhamento.
	 *
	 * @param consultaDto o valor de consulta dto
	 * @return ConsultaDto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ConsultaDto<GrupoCompartilhamentoVO> listarInstuicoesPorCompartilhamento(ConsultaDto<GrupoCompartilhamento> consultaDto) throws BancoobException {
		return transformarDTO(delegate.listarInstuicoesPorCompartilhamento(consultaDto));
	}
	
	/**
	 * Transformar dto.
	 *
	 * @param resultado o valor de resultado
	 * @return ConsultaDto
	 */
	private ConsultaDto<GrupoCompartilhamentoVO> transformarDTO(ConsultaDto<GrupoCompartilhamentoVO> resultado) {
		ConsultaDto<GrupoCompartilhamentoVO> dto = new ConsultaDto<GrupoCompartilhamentoVO>();
		dto.setFiltro(resultado.getFiltro());
		dto.setOrdemCrescente(resultado.isOrdemCrescente());
		dto.setOrdenacao(resultado.getOrdenacao());
		dto.setPagina(resultado.getPagina());
		dto.setProcurarPor(resultado.getProcurarPor());
		List<GrupoCompartilhamentoVO> lista = resultado.getResultado();
		dto.setResultado(lista == null ? new ArrayList<GrupoCompartilhamentoVO>() : lista);
		dto.setTamanhoPagina(resultado.getTamanhoPagina());
		dto.setTipoProcura(resultado.getTipoProcura());
		dto.setTotalRegistros(lista == null ? NumberUtils.INTEGER_ZERO : resultado.getTotalRegistros());
		return dto;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getCodigoRelatorio() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getTituloRelatorio() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESCadastroCrudDelegate<?, ?> obterDelegate() throws BancoobException {
		return CAPESCadastroFabricaDelegates.getInstance().criarGrupoCompartilhamentoDelegate();
	}

}