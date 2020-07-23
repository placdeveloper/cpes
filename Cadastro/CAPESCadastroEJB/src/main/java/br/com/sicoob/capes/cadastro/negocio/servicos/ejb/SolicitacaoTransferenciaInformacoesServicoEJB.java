/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.dto.TransfInformacoesDTO;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ExecutorTransferenciaInformacaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.SolicitacaoTransferenciaInformacoesServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.SolicitacaoTransferenciaInformacoesServicoRemote;
import br.com.sicoob.capes.negocio.entidades.Funcionario;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomico;
import br.com.sicoob.capes.negocio.entidades.Nucleo;
import br.com.sicoob.capes.negocio.entidades.vigente.GrupoEconomicoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao;

/**
 * @author Lucas.Borges
 */
@Stateless
@Local(SolicitacaoTransferenciaInformacoesServicoLocal.class)
@Remote(SolicitacaoTransferenciaInformacoesServicoRemote.class)
public class SolicitacaoTransferenciaInformacoesServicoEJB extends CAPESCadastroServicoEJB implements SolicitacaoTransferenciaInformacoesServicoRemote,
		SolicitacaoTransferenciaInformacoesServicoLocal {

	@EJB
	private ExecutorTransferenciaInformacaoServicoLocal executorTransferencia;

	/** A constante DELIMITADOR. */
	private static final String DELIMITADOR = ",";
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void transferirInformacoesGerente(ConsultaDto<PessoaInstituicao> criterios, Funcionario gerenteDestino) throws BancoobException {
		TransfInformacoesDTO dto = new TransfInformacoesDTO(criterios, null, gerenteDestino, null);
		executarTransferencia(dto);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void transferirInformacoesUnidade(ConsultaDto<PessoaInstituicao> criterios, Integer idUnidadeInstDestino, Funcionario gerenteDestino)
			throws BancoobException {
		TransfInformacoesDTO dto = new TransfInformacoesDTO(criterios, idUnidadeInstDestino, gerenteDestino, null);
		executarTransferencia(dto);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void transferirInformacoesGrupo(ConsultaDto<GrupoEconomicoPessoa> criterios, GrupoEconomico grupoDestino) throws BancoobException {
		TransfInformacoesDTO dto = new TransfInformacoesDTO(criterios, grupoDestino);
		executarTransferencia(dto);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void transferirInformacoesNucleo(ConsultaDto<PessoaInstituicao> criterios, Nucleo nucleoDestino) throws BancoobException {
		TransfInformacoesDTO dto = new TransfInformacoesDTO(criterios, null, null, nucleoDestino);
		executarTransferencia(dto);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void transferirInformacoesCpfCnpj(String listaCpfCnpj, Integer idUnidadeInstDestino, Funcionario gerenteDestino, Nucleo nucleoDestino) throws BancoobException {
		TransfInformacoesDTO dto = new TransfInformacoesDTO(converteListaCpfCnpj(listaCpfCnpj), idUnidadeInstDestino, gerenteDestino, obterInstituicaoUsuarioLogado().getIdInstituicao(), nucleoDestino);
		executarTransferencia(dto);
	}

	/**
	 * Executa a transferencia via Thread
	 * 
	 * @param dto
	 * @throws BancoobException
	 * @throws SicoobThreadsException
	 */
	private void executarTransferencia(TransfInformacoesDTO dto) throws BancoobException {
		dto.setIdUsuarioLogado(obterUsuario().getLogin());
		executorTransferencia.processar(dto);
	}

    /**
     * @return List<String> 
     */
    private List<String> converteListaCpfCnpj(String s){
		return Arrays.asList(s.trim().replaceAll("\r", "").split(DELIMITADOR));
	}

}
