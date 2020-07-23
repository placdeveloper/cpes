/*
 * SICOOB
 * 
 * TelefonePessoaServicoEJB.java(br.com.sicoob.capes.api.negocio.servicos.ejb.TelefonePessoaServicoEJB)
 */
package br.com.sicoob.capes.api.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.excecao.ParametroNaoInformadoException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.TelefonePessoaServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.TelefonePessoaServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.TelefonePessoaVO;
import br.com.sicoob.capes.api.persistencia.dao.TelefonePessoaDAO;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.negocio.entidades.TipoTelefone;
import br.com.sicoob.capes.negocio.entidades.vigente.Telefone;

/**
 * The Class TelefonePessoaServicoEJB.
 */
@Stateless
@Local({ TelefonePessoaServicoLocal.class })
@Remote({ TelefonePessoaServicoRemote.class })
public class TelefonePessoaServicoEJB extends AbstractCAPESApiServicoPessoaIncluirEJB<TelefonePessoaVO> 
	implements TelefonePessoaServicoRemote, TelefonePessoaServicoLocal{
	
	@Inject
	@Default
	private TelefonePessoaDAO telefonePessoaDAO;
	
	/** 
	 * {@inheritDoc}
	 */
	public void incluirTelefone(TelefonePessoaVO dto)throws BancoobException {
		validarObrigatoriedadeTelefone(dto);
		
		Telefone telefone = instanciarTelefone(dto);
		CAPESCadastroFabricaDelegates.getInstance().criarTelefoneDelegate().incluir(telefone);
	}

	/**
	 * Instanciar telefone.
	 * 
	 * @param dto
	 *            the dto
	 * @return telefone
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	private Telefone instanciarTelefone(TelefonePessoaVO dto) throws BancoobException {
		Telefone telefone = new Telefone();
		telefone.setTipoTelefone(new TipoTelefone(dto.getCodigoTipoTelefone()));
		telefone.setDdd(dto.getDdd());
		telefone.setRamal(dto.getRamal());
		telefone.setTelefone(dto.getTelefone());
		telefone.setPessoaCompartilhamento(obterPessoaCompartilhamento(dto.getIdInstituicao(), dto.getCpfCnpj()));

		return telefone;
	}

	/**
	 * Validar obrigatoriedade telefone.
	 * 
	 * @param dto
	 *            the dto
	 * @throws ParametroNaoInformadoException
	 *             the parametro nao informado exception
	 */
	private void validarObrigatoriedadeTelefone(TelefonePessoaVO dto) throws ParametroNaoInformadoException {
		if (dto == null) {
			throw new ParametroNaoInformadoException("Telefone");
		}
		if (dto.getTelefone() == null || dto.getTelefone().equals("")) {
			throw new ParametroNaoInformadoException("Número Telefone");
		}
		if (dto.getCodigoTipoTelefone() == null) {
			throw new ParametroNaoInformadoException("Tipo Telefone");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TelefonePessoaDAO obterDAO() {
		return telefonePessoaDAO;
	}

	public List<TelefonePessoaVO> listarTelefonePessoaPorIdPessoaLegadoIdInstituicao(Integer idPessoaLegado, Integer idInstituicao) throws BancoobException {
		return obterDAO().listarTelefonePessoaPorIdPessoaLegadoIdInstituicao(idPessoaLegado, idInstituicao);
	}
}