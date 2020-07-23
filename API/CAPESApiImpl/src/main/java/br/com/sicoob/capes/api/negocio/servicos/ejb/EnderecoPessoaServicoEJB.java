/*
 * SICOOB
 * 
 * EnderecoPessoaServicoEJB.java(br.com.sicoob.capes.api.negocio.servicos.ejb.EnderecoPessoaServicoEJB)
 */
package br.com.sicoob.capes.api.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.dto.EnderecoPessoaDTO;
import br.com.sicoob.capes.api.negocio.excecao.ParametroNaoInformadoException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.EnderecoPessoaServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.EnderecoPessoaServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.EnderecoPessoaVO;
import br.com.sicoob.capes.api.persistencia.dao.EnderecoPessoaDAO;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.negocio.entidades.Localidade;
import br.com.sicoob.capes.negocio.entidades.TipoEndereco;
import br.com.sicoob.capes.negocio.entidades.TipoLogradouro;
import br.com.sicoob.capes.negocio.entidades.vigente.Endereco;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * The Class EnderecoPessoaServicoEJB.
 */
@Stateless
@Local({ EnderecoPessoaServicoLocal.class })
@Remote({ EnderecoPessoaServicoRemote.class })
public class EnderecoPessoaServicoEJB extends AbstractCAPESApiServicoPessoaEJB<EnderecoPessoaVO> 
	implements EnderecoPessoaServicoRemote, EnderecoPessoaServicoLocal{
	
	@Inject
	@Default
	private EnderecoPessoaDAO enderecoPessoaDAO;
	
	/** 
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public EnderecoPessoaVO obterEnderecoCorrespondenciaByPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		validarObrigatoriedadePessoaInstituicao(idPessoa, idInstituicao);
		return enderecoPessoaDAO.obterEnderecoCorrespondenciaPorPessoaInstituicao(idPessoa, idInstituicao);
	}
	
	/** 
	 * {@inheritDoc}
	 */
	public void incluirEndereco(EnderecoPessoaDTO dto) throws BancoobException {
		validarObrigatoriedadeEndereco(dto);
		
		Endereco endereco = new Endereco();
		endereco.setBairro(dto.getBairro());
		endereco.setCep(dto.getCep());
		endereco.setDescricao(dto.getDescricao());
		endereco.setLocalidade(new Localidade());
		endereco.getLocalidade().setIdLocalidade(dto.getIdLocalidade());
		endereco.setTipoEndereco(new TipoEndereco());
		endereco.getTipoEndereco().setCodigo(dto.getCodigoTipoEndereco());
		endereco.setTipoLogradouro(criarTipoLogradouro(dto));
		endereco.setPessoaCompartilhamento(obterPessoaCompartilhamento(dto.getIdPessoaCompartilhamento()));
		endereco.setNumero(dto.getNumero());
		endereco.setComplemento(dto.getComplemento());
		CAPESCadastroFabricaDelegates.getInstance().criarEnderecoDelegate().incluir(endereco);
	}

	/**
	 * Criar tipo logradouro.
	 * 
	 * @param dto
	 *            the dto
	 * @return tipo logradouro
	 */
	private TipoLogradouro criarTipoLogradouro(EnderecoPessoaDTO dto) {
		TipoLogradouro tipoLogradouro = null;
		if (dto.getIdTipoLogradouro() != null) {
			tipoLogradouro = new TipoLogradouro(dto.getIdTipoLogradouro().intValue(), null);
		}
		return tipoLogradouro;
	}
	
	/**
	 * @param idPessoaCompartilhamento
	 * @return
	 * @throws BancoobException 
	 */
	private PessoaCompartilhamento obterPessoaCompartilhamento(Long idPessoaCompartilhamento) throws BancoobException {
		return CAPESCadastroFabricaDelegates.getInstance().criarPessoaCompartilhamentoDelegate().obter(idPessoaCompartilhamento);
	}

	/**
	 * Validar obrigatoriedade endereco.
	 * 
	 * @param dto
	 *            the dto
	 * @throws ParametroNaoInformadoException
	 *             the parametro nao informado exception
	 */
	private void validarObrigatoriedadeEndereco(EnderecoPessoaDTO dto) throws ParametroNaoInformadoException {
		if (dto == null) {
			throw new ParametroNaoInformadoException("Endereço");
		}
		if (StringUtils.isBlank(dto.getDescricao())) {
			throw new ParametroNaoInformadoException("Descrição");
		}
		if (dto.getCodigoTipoEndereco() == null) {
			throw new ParametroNaoInformadoException("Tipo de Endereço");
		}
		if (dto.getIdTipoLogradouro() == null) {
			throw new ParametroNaoInformadoException("Tipo de Logradouro");
		}
		if (dto.getIdPessoaCompartilhamento() == null) {
			throw new ParametroNaoInformadoException("Pessoa Compartilhamento");
		}
		if (StringUtils.isBlank(dto.getBairro())) {
			throw new ParametroNaoInformadoException("Bairro");
		}
		if (StringUtils.isBlank(dto.getCep())) {
			throw new ParametroNaoInformadoException("Cep");
		}
		if (dto.getIdLocalidade() == null) {
			throw new ParametroNaoInformadoException("Localidade");
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EnderecoPessoaDAO obterDAO() {
		return enderecoPessoaDAO;
	}

}