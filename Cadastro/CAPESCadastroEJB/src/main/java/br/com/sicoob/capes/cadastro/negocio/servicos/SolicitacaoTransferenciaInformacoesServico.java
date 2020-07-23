/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.negocio.entidades.Funcionario;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomico;
import br.com.sicoob.capes.negocio.entidades.Nucleo;
import br.com.sicoob.capes.negocio.entidades.vigente.GrupoEconomicoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao;

/**
 * Serviço para transferência de informações
 * @author Lucas.Borges
 */
public interface SolicitacaoTransferenciaInformacoesServico extends CAPESCadastroServico {

	/**
	 * Mudança de gerente pessoa via Thread
	 * @param criterios
	 * @param gerenteDestino
	 * @throws BancoobException
	 */
	void transferirInformacoesGerente(ConsultaDto<PessoaInstituicao> criterios, Funcionario gerenteDestino) throws BancoobException;	
	/**
	 * Mudança de unidade e gerente pessoa via Thread
	 * @param criterios
	 * @param idUnidadeInstDestino
	 * @param gerenteDestino
	 * @throws BancoobException
	 */
	void transferirInformacoesUnidade(ConsultaDto<PessoaInstituicao> criterios, Integer idUnidadeInstDestino, Funcionario gerenteDestino) throws BancoobException;	
	/**
	 * Mudança de grupo econômico pessoa via Thread
	 * @param criterios
	 * @param grupoDestino
	 * @throws BancoobException
	 */
	void transferirInformacoesGrupo(ConsultaDto<GrupoEconomicoPessoa> criterios, GrupoEconomico grupoDestino) throws BancoobException;	
	/**
	 * Mudança de núcleo pessoa via Thread
	 * @param criterios
	 * @param nucleoDestino
	 * @throws BancoobException
	 */
	void transferirInformacoesNucleo(ConsultaDto<PessoaInstituicao> criterios, Nucleo nucleoDestino) throws BancoobException;
	/**
	 * Mudança de unidade e gerente pessoa via Thread
	 * @param listaCpfCnpj
	 * @param idUnidadeInstDestino
	 * @param gerenteDestino
	 * @throws BancoobException
	 */
	void transferirInformacoesCpfCnpj(String listaCpfCnpj, Integer idUnidadeInstDestino, Funcionario gerenteDestino, Nucleo nucleoDestino) throws BancoobException;
}
