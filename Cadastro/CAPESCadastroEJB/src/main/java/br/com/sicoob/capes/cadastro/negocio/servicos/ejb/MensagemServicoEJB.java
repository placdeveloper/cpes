/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.capes.cadastro.negocio.excecao.CampoNaoInformadoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.DataAtualException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.MensagemServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.MensagemServicoRemote;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.PessoaServicoLocal;
import br.com.sicoob.capes.cadastro.persistencia.dao.MensagemDAO;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Mensagem;
import br.com.sicoob.capes.negocio.entidades.Pessoa;

/**
 * Implementa as operacoes do servico de TipoReferencia.
 * 
 * @author juan.damasceno
 */
@Stateless
@Local({ MensagemServicoLocal.class })
@Remote({ MensagemServicoRemote.class })
public class MensagemServicoEJB extends CAPESCadastroCrudServicoEJB<Mensagem> implements MensagemServicoRemote, MensagemServicoLocal {

	/** A constante VENCIDA_30_DIAS. */
	private static final int VENCIDA_30_DIAS = -30;

	@Inject
	@Default
	private MensagemDAO mensagemDAO;
	
	/** O atributo servicoPessoa. */
	@EJB(mappedName = "capes/cadastro/PessoaServico")
	private PessoaServicoLocal servicoPessoa;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Mensagem incluir(Mensagem mensagem) throws BancoobException {

		Instituicao instituicao = obterInstituicaoUsuarioLogado();
		mensagem.setDataHoraInicio(new DateTimeDB());
		mensagem.setIdUsuarioAprovacao(obterUsuario().getLogin());

		return incluirMensagem(mensagem, instituicao.getIdInstituicao());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected MensagemDAO getDAO() {
		return mensagemDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void excluirMensagens(List<Mensagem> mensagens) throws BancoobException {
		if (mensagens != null) {
			for (Mensagem mensagem : mensagens) {
				getDAO().excluir(mensagem.getId());
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Mensagem> listarVencidas(ConsultaDto<Mensagem> criterios) throws BancoobException {

		Date dataValidade = DataUtil.incrementarData(new Date(), Calendar.DAY_OF_MONTH, VENCIDA_30_DIAS);

		Mensagem mensagem = new Mensagem();
		mensagem.setValidade(new DateTimeDB(dataValidade.getTime()));

		criterios.setFiltro(mensagem);
		criterios.setOrdenacao("padrao");

		return getDAO().listarVencidas(criterios);
	}

	/**
	 * Incluir mensagem.
	 * 
	 * @param mensagem
	 *            o valor de mensagem
	 * @param idInstituicao
	 *            o valor de id instituicao
	 * @return Mensagem
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	private Mensagem incluirMensagem(Mensagem mensagem, Integer idInstituicao) throws BancoobException {

		mensagem.setIdInstituicao(idInstituicao);
		mensagem.setDataHoraInicio(new DateTimeDB());

		validarIncluir(mensagem);

		return super.incluir(mensagem);
	}

	/**
	 * O método Validar incluir.
	 * 
	 * @param mensagem
	 *            o valor de mensagem
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	private void validarIncluir(Mensagem mensagem) throws BancoobException {

		DateTimeDB validade = mensagem.getValidade();
		if (mensagem.getPessoa() == null) {
			throw new CampoNaoInformadoException("Pessoa");
		}
		if (mensagem.getMensagem() == null) {
			throw new CampoNaoInformadoException("Descrição");
		}
		if (validade == null) {
			throw new CampoNaoInformadoException("Validade");
		}
		if (mensagem.getCodigoTipoDestinoExibicao() == null) {
			throw new CampoNaoInformadoException("Exibição");
		}
		if (mensagem.getIdInstituicao() == null) {
			throw new CampoNaoInformadoException("Instituição");
		}

		Date data1 = DataUtil.configurarPrimeiraDataIntervalo(validade);
		Date data2 = DataUtil.configurarPrimeiraDataIntervalo(mensagem.getDataHoraInicio());

		if (data1.before(data2) || data1.equals(data2)) {
			throw new DataAtualException("Validade", "maior");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Mensagem incluir(Integer idPessoa, Integer idInstituicao, String descMensagem, Date dataValidade, Short codTipoMensagem, Short codTipoDestino, String loginUsuarioOperacao) throws BancoobException {
		Pessoa pessoa = servicoPessoa.obter(idPessoa);

		Mensagem mensagem = new Mensagem();
		mensagem.setCodigoTipoDestinoExibicao(codTipoDestino);
		mensagem.setCodigoTipoMensagem(codTipoMensagem);
		mensagem.setMensagem(descMensagem);
		mensagem.setPessoa(pessoa);
		mensagem.setIdUsuarioAprovacao(loginUsuarioOperacao);
		if (dataValidade != null) {
			mensagem.setValidade(new DateTimeDB(dataValidade.getTime()));
		}
		return incluirMensagem(mensagem, idInstituicao);
	}

}