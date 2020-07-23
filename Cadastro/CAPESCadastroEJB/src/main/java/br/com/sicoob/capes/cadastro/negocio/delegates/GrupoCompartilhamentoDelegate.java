/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.servicos.GrupoCompartilhamentoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.comum.negocio.vo.GrupoCompartilhamentoVO;
import br.com.sicoob.capes.negocio.entidades.vigente.GrupoCompartilhamento;

/**
 * 
 * @author juan.damasceno
 *
 */
public class GrupoCompartilhamentoDelegate 
		extends CAPESCadastroCrudDelegate<GrupoCompartilhamento, GrupoCompartilhamentoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected GrupoCompartilhamentoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarGrupoCompartilhamentoServico();
	}

	/**
	 * Obter por cooperativa.
	 *
	 * @param numCooperativa o valor de num cooperativa
	 * @return GrupoCompartilhamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public GrupoCompartilhamento obterPorCooperativa(Integer numCooperativa) throws BancoobException {
		return getServico().obterPorCooperativa(numCooperativa);
	}

	/**
	 * Obter por instituicao.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @return GrupoCompartilhamento
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public GrupoCompartilhamento obterPorInstituicao(Integer idInstituicao) throws BancoobException {
		return getServico().obterPorInstituicao(idInstituicao);
	}
		
	/**
	 * Listar instuicoes por compartilhamento.
	 *
	 * @param consultaDto o valor de consulta dto
	 * @return ConsultaDto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public ConsultaDto<GrupoCompartilhamentoVO> listarInstuicoesPorCompartilhamento(ConsultaDto<GrupoCompartilhamento> consultaDto) throws BancoobException {
		return getServico().listarInstuicoesPorCompartilhamento(consultaDto);
	}
	
	/**
	 * Listar instuicoes sem grupo.
	 *
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<GrupoCompartilhamentoVO> listarInstuicoesSemGrupo() throws BancoobException {
		return getServico().listarInstuicoesSemGrupo();
	}
	
	/**
	 * Verificar compartilhamento cadastro.
	 *
	 * @param idCompartilhamentoCadastro o valor de id compartilhamento cadastro
	 * @return {@code true}, em caso de sucesso
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Boolean verificarCompartilhamentoCadastro(Short idCompartilhamentoCadastro) throws BancoobException {
		return getServico().verificarCompartilhamentoCadastro(idCompartilhamentoCadastro);
	}
	
}