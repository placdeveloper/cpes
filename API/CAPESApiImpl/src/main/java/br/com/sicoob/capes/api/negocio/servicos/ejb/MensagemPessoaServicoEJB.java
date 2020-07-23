package br.com.sicoob.capes.api.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.capes.api.negocio.delegates.CAPESApiFabricaDelegates;
import br.com.sicoob.capes.api.negocio.delegates.PessoaDelegate;
import br.com.sicoob.capes.api.negocio.excecao.ParametroNaoInformadoException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.MensagemPessoaServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.MensagemPessoaServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.MensagemPessoaVO;
import br.com.sicoob.capes.api.negocio.vo.PessoaVO;
import br.com.sicoob.capes.api.persistencia.dao.CAPESApiDao;
import br.com.sicoob.capes.api.persistencia.dao.MensagemPessoaDAO;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.MensagemDelegate;
import br.com.sicoob.capes.cadastro.negocio.excecao.DataAtualException;
import br.com.sicoob.capes.negocio.entidades.Mensagem;

/**
 * The Class MensagemPessoaServicoEJB.
 */
@Stateless
@Local(MensagemPessoaServicoLocal.class)
@Remote(MensagemPessoaServicoRemote.class)
public class MensagemPessoaServicoEJB extends CAPESApiServicoEJB implements MensagemPessoaServicoRemote, MensagemPessoaServicoLocal {

	@Inject
	@Default
	private MensagemPessoaDAO mensagemPessoaDAO;

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<MensagemPessoaVO> obterAtivasByPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		validarObrigatoriedadePessoaInstituicao(idPessoa, idInstituicao);
		return mensagemPessoaDAO.obterMensagensAtivasPorPessoaInstituicao(idPessoa, idInstituicao, null);
	}

	/**
	 * {@inheritDoc}
	 */
	public MensagemPessoaVO incluirMensagem(MensagemPessoaVO mensagemPessoaVO) throws BancoobException {
		try {
			if (mensagemPessoaVO != null) {
				if (mensagemPessoaVO.getIdPessoa() == null) {
					throw new ParametroNaoInformadoException("Pessoa");
				}
				if (mensagemPessoaVO.getIdInstituicao() == null) {
					throw new ParametroNaoInformadoException("Instituição");
				}
				
				validarObrigatoriedade(mensagemPessoaVO);
				Mensagem mensagemIncluida = incluirMensagemCadastro(mensagemPessoaVO);
				return converterMensagemParaVO(mensagemIncluida);
			}
			return null;
		} catch (NegocioException e) {
			getLogger().debug("Erro ao incluir mensagem: ", e.getMessage());
			throw new NegocioException(e.getMessage()); // NOSONAR
		} catch (BancoobException e) {
			getLogger().erro(e, "Erro ao incluir mensagem");
			throw new BancoobException(e.getMessage()); // NOSONAR
		} catch (BancoobRuntimeException e) {
			getLogger().erro(e, "Erro ao incluir mensagem");
			throw new BancoobRuntimeException(e.getMessage()); // NOSONAR
		} catch (Exception e) { // NOPMD
			getLogger().erro(e, "Erro ao incluir mensagem");
			throw new BancoobRuntimeException(e.getMessage()); // NOSONAR
		}
	}

	/**
	 * Converter mensagem para vo.
	 * 
	 * @param mensagemIncluida
	 *            o valor de mensagem incluida
	 * @return MensagemPessoaVO
	 */
	private MensagemPessoaVO converterMensagemParaVO(Mensagem mensagemIncluida) {
		MensagemPessoaVO mensagemPessoaVO = new MensagemPessoaVO();
		mensagemPessoaVO.setCodigoDestinoExibicao(mensagemIncluida.getCodigoTipoDestinoExibicao());
		mensagemPessoaVO.setDataCadastro(mensagemIncluida.getDataHoraInicio());
		mensagemPessoaVO.setDataValidade(mensagemIncluida.getValidade());
		mensagemPessoaVO.setIdInstituicao(mensagemIncluida.getIdInstituicao());
		mensagemPessoaVO.setIdMensagem(mensagemIncluida.getIdMensagem());
		mensagemPessoaVO.setIdPessoa(mensagemIncluida.getPessoa().getId());
		mensagemPessoaVO.setMensagem(mensagemIncluida.getMensagem());
		mensagemPessoaVO.setLoginUsuarioOperacao(mensagemIncluida.getIdUsuarioAprovacao());
		return mensagemPessoaVO;
	}

	/**
	 * {@inheritDoc}
	 */
	public void excluirMensagem(MensagemPessoaVO mensagemVo) throws BancoobException {
		try {
			if (mensagemVo != null) {
				if (mensagemVo.getIdMensagem() == null) {
					throw new ParametroNaoInformadoException("Id Mensagem");
				}
				if (mensagemVo.getLoginUsuarioOperacao() == null) {
					throw new ParametroNaoInformadoException("Login do usuário da operação");
				}
				MensagemDelegate mensagemDelegate = CAPESCadastroFabricaDelegates.getInstance().criarMensagemDelegate();
				Mensagem mensagem = mensagemDelegate.obter(mensagemVo.getIdMensagem());
				
				if (mensagem != null) {
					mensagem.setIdUsuarioExclusao(mensagemVo.getLoginUsuarioOperacao());
					mensagemDelegate.excluirEntidade(mensagem);
				}
			}
		} catch (NegocioException e) {
			getLogger().debug("Erro ao excluir mensagem: ", e.getMessage());
			throw new NegocioException(e.getMessage()); // NOSONAR
		} catch (BancoobException e) {
			getLogger().erro(e, "Erro ao excluir mensagem");
			throw new BancoobException(e.getMessage()); // NOSONAR
		} catch (BancoobRuntimeException e) {
			getLogger().erro(e, "Erro ao excluir mensagem");
			throw new BancoobRuntimeException(e.getMessage()); // NOSONAR
		} catch (Exception e) { // NOPMD
			getLogger().erro(e, "Erro ao excluir mensagem");
			throw new BancoobRuntimeException(e.getMessage()); // NOSONAR
		}
	}

	/**
	 * O método Validar obrigatoriedade.
	 * 
	 * @param mensagem
	 *            o valor de mensagem
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	private void validarObrigatoriedade(MensagemPessoaVO mensagem) throws BancoobException {
		Date validade = mensagem.getDataValidade();

		if (mensagem.getMensagem() == null) {
			throw new ParametroNaoInformadoException("Descrição");
		}
		if (validade == null) {
			throw new ParametroNaoInformadoException("Validade");
		}
		if (mensagem.getCodigoDestinoExibicao() == null) {
			throw new ParametroNaoInformadoException("Exibição");
		}
		if (mensagem.getLoginUsuarioOperacao() == null) {
			throw new ParametroNaoInformadoException("Login do usuário da operação");
		}
		Date data1 = DataUtil.configurarPrimeiraDataIntervalo(validade);
		Date data2 = DataUtil.configurarPrimeiraDataIntervalo(new Date());

		if (data1.before(data2) || data1.equals(data2)) {
			throw new DataAtualException("Validade", "maior");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List<MensagemPessoaVO> incluirMensagensPorCpf(List<MensagemPessoaVO> mensagens) throws BancoobException {
		try {
			for (MensagemPessoaVO mensagemPessoa : mensagens) {
				if (mensagemPessoa.getNumCpf() == null) {
					throw new ParametroNaoInformadoException("CPF");
				}
				if (mensagemPessoa.getIdInstituicao() == null) {
					throw new ParametroNaoInformadoException("Instituição");
				}
				validarObrigatoriedade(mensagemPessoa);
			}
			
			PessoaDelegate pessoaDelegate = CAPESApiFabricaDelegates.getInstance().criarPessoaDelegate();
			List<Mensagem> listaRetorno = new ArrayList<Mensagem>();
			
			for (MensagemPessoaVO vo : mensagens) {
				PessoaVO pessoaVO = pessoaDelegate.obterPorCpfCnpjInstituicao(vo.getNumCpf(), vo.getIdInstituicao());
				
				if (pessoaVO != null) {
					vo.setIdPessoa(pessoaVO.getIdPessoa());
					listaRetorno.add(incluirMensagemCadastro(vo));
				}
			}
			
			return converterListaEntidadeParaListaVO(listaRetorno);
		} catch (NegocioException e) {
			getLogger().debug("Erro ao incluir mensagens por cpf: ", e.getMessage());
			throw new NegocioException(e.getMessage()); // NOSONAR
		} catch (BancoobException e) {
			getLogger().erro(e, "Erro ao incluir mensagens por cpf");
			throw new BancoobException(e.getMessage()); // NOSONAR
		} catch (BancoobRuntimeException e) {
			getLogger().erro(e, "Erro ao incluir mensagens por cpf");
			throw new BancoobRuntimeException(e.getMessage()); // NOSONAR
		} catch (Exception e) { // NOPMD
			getLogger().erro(e, "Erro ao incluir mensagens por cpf");
			throw new BancoobRuntimeException(e.getMessage()); // NOSONAR
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<MensagemPessoaVO> obterMensagensAtivasPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, Short codigoTipoMensagem) throws BancoobException {
		validarObrigatoriedadePessoaInstituicaoTipoMensagem(idPessoa, idInstituicao, codigoTipoMensagem);
		return mensagemPessoaDAO.obterMensagensAtivasPorPessoaInstituicao(idPessoa, idInstituicao, codigoTipoMensagem);
	}

	public List<MensagemPessoaVO> incluirMensagens(List<MensagemPessoaVO> mensagens) throws BancoobException {
		List<MensagemPessoaVO> listaMensagemPessoaVO = new ArrayList<MensagemPessoaVO>();

		for (MensagemPessoaVO mensagemPessoaVO : mensagens) {
			listaMensagemPessoaVO.add(incluirMensagem(mensagemPessoaVO));
		}

		return listaMensagemPessoaVO;
	}

	public void excluirMensagens(List<MensagemPessoaVO> mensagemPessoaVO) throws BancoobException {
		for (MensagemPessoaVO idMensagem : mensagemPessoaVO) {
			excluirMensagem(idMensagem);
		}
	}
	
	private Mensagem incluirMensagemCadastro(MensagemPessoaVO mensagemPessoaVO) throws BancoobException {
		MensagemDelegate mensagemDelegate = CAPESCadastroFabricaDelegates.getInstance().criarMensagemDelegate();
		Mensagem mensagemIncluida = mensagemDelegate.incluir(mensagemPessoaVO.getIdPessoa(), mensagemPessoaVO.getIdInstituicao(),
				mensagemPessoaVO.getMensagem(), mensagemPessoaVO.getDataValidade(), mensagemPessoaVO.getCodigoTipoMensagem(),
				mensagemPessoaVO.getCodigoDestinoExibicao(), mensagemPessoaVO.getLoginUsuarioOperacao());
		return mensagemIncluida;
	}

	/**
	 * Converter mensagem para mensagem pessoa vo.
	 * 
	 * @param listaEntidade
	 *            o valor de lista inclusao
	 * @return List
	 */
	private List<MensagemPessoaVO> converterListaEntidadeParaListaVO(List<Mensagem> listaEntidade) {
		List<MensagemPessoaVO> listaRetorno = new ArrayList<MensagemPessoaVO>();

		for (Mensagem mensagem : listaEntidade) {
			MensagemPessoaVO vo = new MensagemPessoaVO();
			vo.setIdMensagem(mensagem.getIdMensagem());
			vo.setIdPessoa(mensagem.getPessoa().getIdPessoa());
			vo.setIdInstituicao(mensagem.getIdInstituicao());
			vo.setMensagem(mensagem.getMensagem());
			vo.setCodigoDestinoExibicao(mensagem.getCodigoTipoDestinoExibicao());
			vo.setLoginUsuarioOperacao(mensagem.getIdUsuarioAprovacao());
			vo.setCodigoTipoMensagem(mensagem.getCodigoTipoMensagem());

			listaRetorno.add(vo);
		}

		return listaRetorno;
	}

	@Override
	protected CAPESApiDao obterDAO() {
		return mensagemPessoaDAO;
	}

}