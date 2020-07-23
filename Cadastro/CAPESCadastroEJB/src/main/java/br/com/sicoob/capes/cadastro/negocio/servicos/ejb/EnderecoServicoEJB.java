/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.apache.commons.beanutils.BeanComparator;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.dominio.validacao.Validacao;
import br.com.sicoob.capes.cadastro.negocio.dominio.validacao.ValidacaoEndereco;
import br.com.sicoob.capes.cadastro.negocio.excecao.EnderecoCorrespondenciaBloqueadoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.EnderecoExclusaoExcpetion;
import br.com.sicoob.capes.cadastro.negocio.excecao.RegistroJaCadastradoException;
import br.com.sicoob.capes.cadastro.negocio.proxies.EnderecoProxy;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.EnderecoCorrespondenciaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.EnderecoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.EnderecoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.EnderecoDAO;
import br.com.sicoob.capes.cadastro.util.IntegracaoUtil;
import br.com.sicoob.capes.comum.negocio.annotation.IntegracaoGedGft;
import br.com.sicoob.capes.comum.negocio.enums.TipoEnderecoEnum;
import br.com.sicoob.capes.comum.negocio.vo.InstituicaoVO;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.LOCIntegracaoDelegate;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.EnderecoCorrespondencia;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.Localidade;
import br.com.sicoob.capes.negocio.entidades.TipoLogradouro;
import br.com.sicoob.capes.negocio.entidades.vigente.Endereco;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Serviço para os endereços
 * 
 * @author erico.junior
 */
@Stateless
@Local( { EnderecoServicoLocal.class })
@Remote( { EnderecoServicoRemote.class })
@IntegracaoGedGft
public class EnderecoServicoEJB extends EntidadeCadastroServicoEJB<Endereco> implements
		EnderecoServicoRemote, EnderecoServicoLocal {

	@Inject
	@Default
	private EnderecoDAO enderecoDAO;

	/** O atributo correspondenciaServico. */
	@EJB(mappedName = "capes/cadastro/EnderecoCorrespondenciaServico")
	private EnderecoCorrespondenciaServicoLocal correspondenciaServico;
	
	/** Objeto de acesso aos dados a PessoaCompartilhamentoDelegate . */
	private PessoaCompartilhamentoDelegate pessoaCompartilhamentoServico = CAPESCadastroFabricaDelegates.getInstance().criarPessoaCompartilhamentoDelegate();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EnderecoDAO getDAO() {
		return enderecoDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarAlterar(Endereco endereco) throws BancoobException {
		validarEndereco(endereco);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarIncluir(Endereco endereco) throws BancoobException {
		validarEndereco(endereco);
	}

	/**
	 * O método Validar endereco.
	 *
	 * @param endereco o valor de endereco
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarEndereco(Endereco endereco) throws BancoobException {
		Validacao<Endereco> validacao = new ValidacaoEndereco();
		validacao.validar(endereco);
		isEnderecoRepetido(endereco);
	}
	
	/**
	 * Verifica se é as duas instâncias do endereço são do mesmo registro.
	 * @param endereco Um dos endereços a serem verificados.
	 * @param outroEndereco O outro endereço a ser verificado.
	 * @return se é as duas instâncias do endereço são do mesmo registro.
	 */
	private boolean isMesmoEndereco(Endereco endereco, Endereco outroEndereco) {
		return ReflexaoUtils.equals(endereco, outroEndereco, "idEndereco");
	}

	/**
	 * Monta o objeto proxy para o endereço.
	 * @param endereco O endereço com os dados para o proxy.
	 * @param correspondencia O endereço de correspondência da pessoa na instituição logada.
	 * @param localidade A localidade do endereço.
	 * @param tiposLogradouro Os tipos de logradouro.
	 * @return o objeto proxy para endereços.
	 * @throws BancoobException 
	 */
	private EnderecoProxy obterEnderecoProxy(Endereco endereco, Endereco correspondencia, 
			Localidade localidade, Map<Integer, String> tiposLogradouro, List<Integer> instituicoes) throws BancoobException{
		
		String nomeCidade = "";
		String siglaUf = "";
		StringBuilder cooperativasCorrespondencia = new StringBuilder();
		if (localidade != null) {
			nomeCidade = localidade.getNome();
			if (localidade.getUf() != null) {
				siglaUf = localidade.getUf().getSiglaUF();
			}
		}
		
		if (instituicoes != null && !instituicoes.isEmpty()) {
			SCIIntegracaoDelegate sciIntegracaoDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();
			for (Integer idInstituicao : instituicoes) {
				InstituicaoVO instituicao = sciIntegracaoDelegate.obterInstituicaoPorId(idInstituicao);
				if (instituicao != null) {
					cooperativasCorrespondencia.append(instituicao.getNumero()).append(", ");
				}
			}
			cooperativasCorrespondencia.deleteCharAt(cooperativasCorrespondencia.length() - 1).deleteCharAt(cooperativasCorrespondencia.length() - 1);
		}
		
		EnderecoProxy proxy = new EnderecoProxy();
		proxy.setIdEndereco(endereco.getIdEndereco());
		proxy.setIdInstituicaoAtualizacao(endereco.getIdInstituicaoAtualizacao());
		proxy.setIdRegistroControlado(endereco.getIdRegistroControlado());
		proxy.setIdUsuarioAprovacao(endereco.getIdUsuarioAprovacao());
		proxy.setCodigoSituacaoAprovacao(endereco.getCodigoSituacaoAprovacao());
		proxy.setBairro(endereco.getBairro());
		proxy.setComplemento(endereco.getComplemento());
		if (endereco.getDataHoraInicio() != null) {
			proxy.setDataHoraInicio(new DateTimeDB(endereco.getDataHoraInicio().getTime()));
		}
		proxy.setDescricaoEndereco(obterDescricaoEndereco(endereco, tiposLogradouro));
		proxy.setDescricaoTipoEndereco(endereco.getTipoEndereco().getDescricao());
		proxy.setNumero(endereco.getNumero());
		proxy.setPadraoCorrespondencia(isMesmoEndereco(endereco, correspondencia));	
		proxy.setNomeCidade(nomeCidade);
		proxy.setSiglaUF(siglaUf);
		proxy.setCooperativasCorrespondencia(cooperativasCorrespondencia.toString());
		return proxy;
	}
	
	/**
	 * Obter endereco correspondencia.
	 *
	 * @param pessoa o valor de pessoa
	 * @param instituicao o valor de instituicao
	 * @return Endereco
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private Endereco obterEnderecoCorrespondencia(PessoaCompartilhamento pessoa, Instituicao instituicao) 
			throws BancoobException {
		
		Endereco endereco = null;
		EnderecoCorrespondencia correspondencia = correspondenciaServico.consultar(
				pessoa, instituicao);
		
		if(correspondencia != null) {
			endereco = correspondencia.getEndereco();
		}
		
		return endereco;
	}
	
	/**
	 * Obter descricao endereco.
	 *
	 * @param endereco o valor de endereco
	 * @param tiposLogradouro o valor de tipos logradouro
	 * @return String
	 */
	private String obterDescricaoEndereco(Endereco endereco, Map<Integer, String> tiposLogradouro) {

		TipoLogradouro tipoLogradouro = endereco.getTipoLogradouro();
		StringBuilder builder = new StringBuilder();
		
		if(tipoLogradouro != null && tipoLogradouro.getIdTipoLogradouro() != null) {
			Integer idTipoLogradouro = tipoLogradouro.getIdTipoLogradouro();
			if (tiposLogradouro.containsKey(idTipoLogradouro)) {
				builder.append(tiposLogradouro.get(idTipoLogradouro));
				builder.append(" ");
			}
		}

		builder.append(endereco.getDescricao());
		return builder.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void tornarPadraoCorrespondencia(Endereco endereco) throws BancoobException {
		tornarPadraoCorrespondencia(endereco, obterInstituicaoUsuarioLogado());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void tornarPadraoCorrespondencia(Endereco endereco,
			Instituicao instituicao) throws BancoobException {

		Endereco enderecoExistente = obter(endereco.getIdEndereco());
		
		if(!isRegistroVigente(enderecoExistente)){
			throw new EnderecoCorrespondenciaBloqueadoException();
		}
		
		getLogger().debug("Endereco para padrao de correspondencia existente: ", String.valueOf(enderecoExistente != null));
		
		if(enderecoExistente != null) {

			PessoaCompartilhamento pessoa = enderecoExistente.getPessoaCompartilhamento();
			EnderecoCorrespondencia correspondencia = correspondenciaServico.consultar(pessoa, instituicao);
			
			if (correspondencia == null) {
				correspondencia = new EnderecoCorrespondencia();
				correspondencia.setEndereco(endereco);
				correspondencia.setPessoaCompartilhamento(pessoa);
				correspondencia.setIdInstituicao(instituicao.getIdInstituicao());
				correspondenciaServico.incluir(correspondencia);
			} else {
				correspondencia.setEndereco(endereco);
				correspondenciaServico.alterar(correspondencia);
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Endereco incluir(Endereco endereco) throws BancoobException {
		return incluir(endereco, obterInstituicaoUsuarioLogado());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Endereco incluir(Endereco endereco, Instituicao instituicao)
			throws BancoobException {
		
		endereco = super.incluir(endereco);
		
		if(isRegistroVigente(endereco)){
			verificarEnderecoCorrespondencia(endereco, instituicao);
		}
		
		return endereco;
	}

	/**
	 * O método Checks if is endereco repetido.
	 *
	 * @param novoEnd o valor de novo end
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void isEnderecoRepetido(Endereco novoEnd) throws BancoobException {
		if (getDAO().isEnderecoRepetido(novoEnd) && novoEnd.getVerificarAutorizacao()) {
			throw new RegistroJaCadastradoException();
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<EnderecoProxy> listarEnderecos(ConsultaDto<Endereco> criterios)
			throws BancoobException {
		
		return listarEnderecos(criterios, obterInstituicaoUsuarioLogado());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<EnderecoProxy> listarEnderecos(ConsultaDto<Endereco> criterios,
			Instituicao instituicao) throws BancoobException {
		
		LOCIntegracaoDelegate locDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarLOCIntegracaoDelegate();
		
		List<EnderecoProxy> retorno = new ArrayList<EnderecoProxy>();
		List<Endereco> enderecos = listar(criterios);
		
		if (enderecos != null && !enderecos.isEmpty()) {
			
			Collections.sort(enderecos, new BeanComparator<Endereco>("idRegistroControlado"));

			Map<Integer, Localidade> localidades = new HashMap<Integer, Localidade>();
			Map<Integer, String> tiposLogradouro = locDelegate.obterMapaTiposLogradouro();

			Localidade localidade = null;
			
			Endereco filtro = (Endereco) criterios.getFiltro();
			Endereco correspondencia = obterEnderecoCorrespondencia(filtro.getPessoaCompartilhamento(), instituicao);

			for (Endereco endereco : enderecos) {

				Integer idLocalidade = endereco.getLocalidade().getIdLocalidade();
				if (!localidades.containsKey(idLocalidade)) {
					localidade = IntegracaoUtil.converterLocalidade(locDelegate.obterLocalidade(idLocalidade));
					localidades.put(idLocalidade, localidade);
				}
				
				localidade = localidades.get(idLocalidade);
				retorno.add(obterEnderecoProxy(
						endereco, correspondencia, localidade, tiposLogradouro, 
						correspondenciaServico.listarInstituicoesVinculadas(endereco)));
			}
		}
		
		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluirEntidade(Endereco objeto) throws BancoobException {
		validarExcluir(objeto);
		super.excluirEntidade(objeto);
		if (isRegistroVigente(objeto)) {
			if (objeto.getIdUsuarioEnvio() != null) {
				pessoaCompartilhamentoServico.renovarCadastroAutomatico(objeto, objeto.getPessoaCompartilhamento().getPessoa().getIdPessoa(), objeto.getIdUsuarioEnvio());
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(Serializable chave) throws BancoobException {
		Endereco endereco = new Endereco();
		if(chave instanceof Long){
			endereco.setId((Long) chave);
		}
		validarExcluir(endereco);
		super.excluir(chave);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(Endereco objeto) throws BancoobException {	
		super.alterar(objeto);
		Endereco endereco = obter(objeto.getIdEndereco());
		if (isRegistroVigente(endereco)) {
			if (objeto.getIdUsuarioEnvio() != null) {
				pessoaCompartilhamentoServico.renovarCadastroAutomatico(objeto, objeto.getPessoaCompartilhamento().getPessoa().getIdPessoa(), objeto.getIdUsuarioEnvio());
			}
		}
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarExcluir(Endereco endereco) throws BancoobException {
		if(correspondenciaServico.isEnderecoCorrespondencia(endereco)) {
			throw new EnderecoExclusaoExcpetion();
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void verificarEnderecoCorrespondencia(Endereco endereco, Instituicao instituicao) throws BancoobException {
		EnderecoCorrespondencia correspondencia = correspondenciaServico.consultar(endereco.getPessoaCompartilhamento(), instituicao);

		if (correspondencia == null) {
			tornarPadraoCorrespondencia(endereco, instituicao);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Endereco obterEnderecoResidencial(PessoaCompartilhamento pessoa) throws BancoobException {
		Endereco retorno = null;
		ConsultaDto<Endereco> criterios = new ConsultaDto<Endereco>();
		Endereco filtro = new Endereco();
		filtro.setPessoaCompartilhamento(pessoa);
		criterios.setFiltro(filtro);
		ConsultaDto<Endereco> resultado = getDAO().pesquisar(criterios );
		for (Endereco item : resultado.getResultado()) {
			if(item.getTipoEndereco().getCodigo().equals(TipoEnderecoEnum.RESIDENCIAL.getCodigo())){
				retorno = item;
			}
		}
		
		return retorno;
	}
}
