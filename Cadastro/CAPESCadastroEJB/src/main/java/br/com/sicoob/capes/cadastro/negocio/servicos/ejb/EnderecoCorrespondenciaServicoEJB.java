/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.EnderecoCorrespondenciaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.EnderecoCorrespondenciaServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.EnderecoCorrespondenciaDAO;
import br.com.sicoob.capes.negocio.entidades.EnderecoCorrespondencia;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.Endereco;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * @author Erico.Junior
 * 
 */
@Stateless
@Local( { EnderecoCorrespondenciaServicoLocal.class })
@Remote( { EnderecoCorrespondenciaServicoRemote.class })
public class EnderecoCorrespondenciaServicoEJB extends
		CAPESCadastroCrudServicoEJB<EnderecoCorrespondencia> implements
		EnderecoCorrespondenciaServicoRemote, EnderecoCorrespondenciaServicoLocal {

	@Inject
	@Default
	protected EnderecoCorrespondenciaDAO dao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EnderecoCorrespondenciaDAO getDAO() {
		return dao;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public EnderecoCorrespondencia consultar(PessoaCompartilhamento pessoa,
			Instituicao instituicao) throws BancoobException {
		return dao.consultar(pessoa, instituicao);
	}

	/**
	 * {@inheritDoc}
	 */
	public EnderecoCorrespondencia consultar(PessoaCompartilhamento pessoa)
			throws BancoobException {
		return consultar(pessoa, obterInstituicaoUsuarioLogado());
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isEnderecoCorrespondencia(Endereco endereco)
			throws BancoobException {
		
		EnderecoCorrespondencia correspondencia = new EnderecoCorrespondencia();
		correspondencia.setEndereco(endereco);
		
		ConsultaDto<EnderecoCorrespondencia> criterios = new ConsultaDto<EnderecoCorrespondencia>();
		criterios.setFiltro(correspondencia);
		
		List<EnderecoCorrespondencia> correspondencias = getDAO().listar(criterios);
		return correspondencias != null && correspondencias.size() > 0;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Integer> listarInstituicoesVinculadas(Endereco endereco)
			throws BancoobException{
		return dao.listarInstituicoesVinculadas(endereco);
	}

}
