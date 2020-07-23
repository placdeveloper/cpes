/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.servicos.SolicitacaoTransferenciaInformacoesServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.Funcionario;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomico;
import br.com.sicoob.capes.negocio.entidades.Nucleo;
import br.com.sicoob.capes.negocio.entidades.vigente.GrupoEconomicoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao;

/**
 * Transferência de informações via Thread
 * @author Lucas.Borges
 */
public class SolicitacaoTransferenciaInformacoesDelegate extends
		CAPESCadastroDelegate<SolicitacaoTransferenciaInformacoesServico>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected SolicitacaoTransferenciaInformacoesServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarSolicitacaoTransferenciaInformacoesServico();
	}
	
	/**
	 * Mudança de gerente pessoa via Thread
	 * @param criterios
	 * @param gerenteDestino
	 * @throws BancoobException
	 */
	public void transferirInformacoesGerente(
			ConsultaDto<PessoaInstituicao> criterios, Funcionario gerenteDestino)
			throws BancoobException {
		getServico().transferirInformacoesGerente(criterios, gerenteDestino);
	}
	/**
	 * Mudança de unidade e gerente pessoa via Thread
	 * @param criterios
	 * @param idUnidadeInstDestino
	 * @param gerenteDestino
	 * @throws BancoobException
	 */
	public void transferirInformacoesUnidade(
			ConsultaDto<PessoaInstituicao> criterios,
			Integer idUnidadeInstDestino, Funcionario gerenteDestino)
			throws BancoobException {
		getServico().transferirInformacoesUnidade(criterios, idUnidadeInstDestino, gerenteDestino);
	}
	
	/**
	 * Mudança de unidade e gerente pessoa via Thread
	 * @param listaCpfCnpj
	 * @param idUnidadeInstDestino
	 * @param gerenteDestino
	 * @param nucleoDestino
	 * @throws BancoobException
	 */
	public void transferirInformacoesCpfCnpj(String listaCpfCnpj, Integer idUnidadeInstDestino, Funcionario gerenteDestino, Nucleo nucleoDestino) throws BancoobException {
		getServico().transferirInformacoesCpfCnpj(listaCpfCnpj, idUnidadeInstDestino, gerenteDestino, nucleoDestino);
	}
	
	/**
	 * Mudança de grupo econômico pessoa via Thread
	 * @param criterios
	 * @param grupoDestino
	 * @throws BancoobException
	 */
	public void transferirInformacoesGrupo(
			ConsultaDto<GrupoEconomicoPessoa> criterios,
			GrupoEconomico grupoDestino) throws BancoobException {
		getServico().transferirInformacoesGrupo(criterios, grupoDestino);
	}
	/**
	 * Mudança de núcleo pessoa via Thread
	 * @param criterios
	 * @param nucleoDestino
	 * @throws BancoobException
	 */
	public void transferirInformacoesNucleo(
			ConsultaDto<PessoaInstituicao> criterios, Nucleo nucleoDestino)
			throws BancoobException {
		getServico().transferirInformacoesNucleo(criterios, nucleoDestino);
	}
}
