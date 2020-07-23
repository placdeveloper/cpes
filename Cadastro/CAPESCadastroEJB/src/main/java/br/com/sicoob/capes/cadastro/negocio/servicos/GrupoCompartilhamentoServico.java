/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.comum.negocio.vo.GrupoCompartilhamentoVO;
import br.com.sicoob.capes.negocio.entidades.vigente.GrupoCompartilhamento;

/**
 * Define as opera��es do servi�o de manipula��o de GrupoCompartilhamento.
 * 
 * @author Juan.Damasceno
 */
public interface GrupoCompartilhamentoServico extends
		CAPESCadastroCrudServico<GrupoCompartilhamento> {

	/**
	 * Obter por cooperativa.
	 *
	 * @param numCooperativa o valor de num cooperativa
	 * @return GrupoCompartilhamento
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	GrupoCompartilhamento obterPorCooperativa(Integer numCooperativa)
			throws BancoobException;

	/**
	 * Obter por instituicao.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @return GrupoCompartilhamento
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	GrupoCompartilhamento obterPorInstituicao(Integer idInstituicao)
			throws BancoobException;
	
	/**
	 * Listar instuicoes por compartilhamento.
	 *
	 * @param consultaDto o valor de consulta dto
	 * @return ConsultaDto
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	ConsultaDto<GrupoCompartilhamentoVO> listarInstuicoesPorCompartilhamento(ConsultaDto<GrupoCompartilhamento> consultaDto)
			throws BancoobException;
	
	/**
	 * Listar instuicoes sem grupo.
	 *
	 * @return List
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	List<GrupoCompartilhamentoVO> listarInstuicoesSemGrupo()
			throws BancoobException;
	
	/**
	 * Verificar compartilhamento cadastro.
	 *
	 * @param idCompartilhamentoCadastro o valor de id compartilhamento cadastro
	 * @return {@code true}, em caso de sucesso
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	Boolean verificarCompartilhamentoCadastro(Short idCompartilhamentoCadastro) 
			throws BancoobException;
}