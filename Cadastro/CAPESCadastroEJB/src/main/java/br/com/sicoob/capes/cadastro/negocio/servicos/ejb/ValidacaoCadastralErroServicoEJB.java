package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ValidacaoCadastralErroServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ValidacaoCadastralErroServicoRemote;
import br.com.sicoob.capes.cadastro.negocio.vo.ValidacaoCadastralVO;
import br.com.sicoob.capes.cadastro.persistencia.dao.ValidacaoCadastralErroDAO;
import br.com.sicoob.capes.comum.negocio.enums.TipoRegraValidacaoCadastralEnum;
import br.com.sicoob.capes.negocio.entidades.ValidacaoCadastralErro;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * EJB contendo servicos relacionados a ValidacaoCadastralErro.
 */
@Stateless
@Local(ValidacaoCadastralErroServicoLocal.class) 
@Remote(ValidacaoCadastralErroServicoRemote.class)
public class ValidacaoCadastralErroServicoEJB extends CAPESCadastroCrudServicoEJB<ValidacaoCadastralErro> implements
		ValidacaoCadastralErroServicoLocal, ValidacaoCadastralErroServicoRemote {

	@Inject
	@Default
	private ValidacaoCadastralErroDAO dao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ValidacaoCadastralErroDAO getDAO() {
		return dao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deletarErrosPessoa(Long idPessoaCompartilhamento) throws BancoobException {
		getDAO().deletarErrosPessoa(idPessoaCompartilhamento);
	}
	
	/**
	 * {@inheritDoc}
	 */
    @Override
	public void deletarErrosRegra(Short codigoRegra) throws BancoobException {
		getDAO().deletarErrosRegra(codigoRegra);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ValidacaoCadastralVO> listarFalhasRegrasValidacaoCadastral(Long idPessoaCompartilhamento, Integer idInstituicao, TipoRegraValidacaoCadastralEnum tipoRegra) throws BancoobException {
		return getDAO().listarFalhasRegrasValidacaoCadastral(idPessoaCompartilhamento, idInstituicao, tipoRegra);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ValidacaoCadastralVO> listarFalhasRegrasValidacaoCadastral(Long idPessoaCompartilhamento, Integer idInstituicao, TipoRegraValidacaoCadastralEnum tipoRegra, Short codPerfilCadastro) throws BancoobException {
		return getDAO().listarFalhasRegrasValidacaoCadastral(idPessoaCompartilhamento, idInstituicao, tipoRegra, codPerfilCadastro);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isPossuiRegraCadastralRestritiva(Long idPessoaCompartilhamento, Integer idInstituicao) throws BancoobException {
		return getDAO().isPossuiRegraCadastralRestritiva(idPessoaCompartilhamento, idInstituicao, null);
	}
	
	@Override
	public boolean isPossuiRegraCadastralRestritiva(Long idPessoaCompartilhamento, Integer idInstituicao, Short codigoPerfilCadastro) throws BancoobException {
		return getDAO().isPossuiRegraCadastralRestritiva(idPessoaCompartilhamento, idInstituicao, codigoPerfilCadastro);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deletarErros(Short codigoRegra, DateTimeDB dataValidacao) throws BancoobException {
	    getDAO().deletarErros(codigoRegra, dataValidacao);
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isExisteErro(Short codigoRegra) throws BancoobException {
	    return getDAO().isExisteErro(codigoRegra);
	}
	
	@Override
	public List<String> listarPendenciasPessoaRelacionada(PessoaCompartilhamento pessoa, Integer idInstituicao) throws BancoobException {
		return getDAO().listarPendenciasPessoaRelacionada(pessoa, idInstituicao);
	}

}