/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade.AtualizacaoCooperativaPessoaFachada;
import br.com.sicoob.capes.cadastro.negocio.excecao.PessoaNaoSeRelacionaComBancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TransicaoPessoaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TransicaoPessoaServicoRemote;
import br.com.sicoob.capes.cadastro.negocio.vo.DadosAlteracaoGrupoVO;
import br.com.sicoob.capes.cadastro.negocio.vo.TransicaoPessoaVO;
import br.com.sicoob.capes.cadastro.persistencia.dao.TransicaoPessoaDAO;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.TransicaoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Implementa as operações do serviço de TransicaoPessoa.
 * 
 * @author Juan.Damasceno
 */
@Stateless
@Local( { TransicaoPessoaServicoLocal.class })
@Remote( { TransicaoPessoaServicoRemote.class })
public class TransicaoPessoaServicoEJB extends CAPESCadastroCrudServicoEJB<TransicaoPessoa>
		implements TransicaoPessoaServicoRemote, TransicaoPessoaServicoLocal {

	@Inject
	@Default
	private TransicaoPessoaDAO transicaoPessoaDAO;
	
	/**
	 *	{@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<TransicaoPessoa> listar(TransicaoPessoa transicao) {
		return getDAO().listar(transicao);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TransicaoPessoaDAO getDAO() {
		return transicaoPessoaDAO;
	}

	
	/**
	 * Só deve ser utilizado na alteração do CPF/CNPJ.
	 */
	@Override
	public List<TransicaoPessoaVO> listarTodasTransicoes(Pessoa pessoa) {
		return getDAO().listarTodasTransicoes(pessoa);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean verificarInstituicao(Integer idInstituicao) {
		return getDAO().verificarInstituicao(idInstituicao);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void validarPessoaPossuiRelacionamentoBancoob(PessoaCompartilhamento pessoa)
			throws BancoobException {
		
		boolean temRelacionamentoBancoob = possuiRelacionamentoBancoob(pessoa);
	
		if (!temRelacionamentoBancoob) {
			throw new PessoaNaoSeRelacionaComBancoobException();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TransicaoPessoa obterTransicaoPorPessoaInstituicao(Pessoa pessoa, Integer idInstituicao)
			throws BancoobException {
		return getDAO().obterTransicaoPorPessoaInstituicao(pessoa.getIdPessoa(), idInstituicao);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TransicaoPessoa obterTransicaoPorPessoaCompartilhamentoInstituicao(Long idPessoaCompartilhamento, Integer idInstituicao) throws BancoobException {
		return getDAO().obterTransicaoPorPessoaCompartilhamentoInstituicao(idPessoaCompartilhamento, idInstituicao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<TransicaoPessoa> listarTransicoesParaReplicacao(
			PessoaCompartilhamento pessoaCompartilhamento) {
		return getDAO().listarTransicoesParaReplicacao(pessoaCompartilhamento);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean possuiRelacionamentoBancoob(PessoaCompartilhamento pessoa)
			throws BancoobException {
		return obterTransicaoBancoob(pessoa) != null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TransicaoPessoa> listar(PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException {
		TransicaoPessoa transicao = new TransicaoPessoa();
		transicao.setPessoaCompartilhamento(pessoaCompartilhamento);
		return listar(transicao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TransicaoPessoa obterTransicaoBancoob(PessoaCompartilhamento pessoaCompartilhamento)
			throws BancoobException {
		
		Instituicao bancoob = new Instituicao();
		bancoob.setIdInstituicao(Constantes.Comum.ID_INSTITUICAO_BANCOOB);
		
		TransicaoPessoa transicao = new TransicaoPessoa();
		transicao.setPessoaCompartilhamento(pessoaCompartilhamento);
		transicao.setInstituicao(bancoob);

		TransicaoPessoa retorno = null;
		List<TransicaoPessoa> transicoes = listar(transicao);
		
		if(transicoes != null && !transicoes.isEmpty()) {
			retorno = transicoes.get(0);
		}
		
		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TransicaoPessoa incluir(TransicaoPessoa transicao)
			throws BancoobException {

		Integer idInstituicao = transicao.getInstituicao().getIdInstituicao();
		PessoaCompartilhamento pessoaCompartilhamento = transicao.getPessoaCompartilhamento();
		
		TransicaoPessoa transicaoBancoob = null;
		List<TransicaoPessoa> transicoes = null;
		
		if(Constantes.Comum.ID_INSTITUICAO_BANCOOB.equals(idInstituicao)) {
			transicaoBancoob = transicao;
			transicoes = listar(pessoaCompartilhamento);
		} else {
			transicaoBancoob = obterTransicaoBancoob(pessoaCompartilhamento);
			transicoes = new ArrayList<TransicaoPessoa>();
			transicoes.add(transicao);
		}
		
		getLogger().info("Possui transicao com o Bancoob: "  + (transicaoBancoob != null));
		if(transicaoBancoob != null) {
			AtualizacaoCooperativaPessoaFachada fachada = new AtualizacaoCooperativaPessoaFachada();
			fachada.atualizarCooperativaPessoa(transicaoBancoob, transicoes);
		}
		
		return super.incluir(transicao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean isIncorporacaoFinalizada(Integer idInsituicaoIncorporadora,
			Integer idInsituicaoIncorporada) throws BancoobException {
	
		return getDAO().isIncorporacaoFinalizada(idInsituicaoIncorporadora, idInsituicaoIncorporada);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer buscarQuantidadePessoasInstituicao(Integer idInstituicao)
			throws BancoobException {
		return getDAO().buscarQuantidadePessoasInstituicao(idInstituicao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TransicaoPessoa> buscarPessoasExistentesGrupo(Integer idInstituicao,
			Short codigoCompartilhamento) throws BancoobException {
		return getDAO().buscarPessoasExistentesGrupo(idInstituicao, codigoCompartilhamento);
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, Integer> buscarQuantidadesPessoasAlteracaoGrupo(
			Integer idInstituicao, Short codigoCompartilhamento)
					throws BancoobException {
		return getDAO().buscarQuantidadesPessoasAlteracaoGrupo(idInstituicao, codigoCompartilhamento);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConsultaDto<DadosAlteracaoGrupoVO> buscarDadosAlteracaoGrupoPessoasNoDestino(
			ConsultaDto<DadosAlteracaoGrupoVO> criterios) throws BancoobException {
		return getDAO().buscarDadosAlteracaoGrupoPessoasNoDestino(criterios);
	}

}
