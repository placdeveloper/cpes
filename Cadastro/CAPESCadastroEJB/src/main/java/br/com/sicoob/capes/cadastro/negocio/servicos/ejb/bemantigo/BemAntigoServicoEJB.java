/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb.bemantigo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.excecao.InformacaoExistenteException;
import br.com.sicoob.capes.cadastro.negocio.servicos.bemantigo.BemAntigoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.bemantigo.BemAntigoServicoRemote;
import br.com.sicoob.capes.cadastro.negocio.servicos.ejb.EntidadeCadastroServicoEJB;
import br.com.sicoob.capes.cadastro.persistencia.dao.bemantigo.BemAntigoDAO;
import br.com.sicoob.capes.comum.negocio.annotation.IgnorarAutorizar;
import br.com.sicoob.capes.negocio.entidades.bemantigo.Bem;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemImovel;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemOnus;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemPosse;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemRegistro;
import br.com.sicoob.capes.negocio.entidades.bemantigo.SituacaoBem;
import br.com.sicoob.capes.negocio.entidades.bemantigo.SubTipoBem;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Implementa as operacoes do servico de BemImovel.
 * 
 * @author Erico.Junior
 */
@Stateless
@Local( { BemAntigoServicoLocal.class })
@Remote( { BemAntigoServicoRemote.class })
public class BemAntigoServicoEJB extends EntidadeCadastroServicoEJB<Bem> implements BemAntigoServicoRemote, BemAntigoServicoLocal {

	@Inject
	@Default
	protected BemAntigoDAO bemDAO;

//	/** O atributo servicoProdutividade. */
//	@EJB(mappedName = "capes/cadastro/ProdutividadeServico")
//	private ProdutividadeServicoLocal servicoProdutividade;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemAntigoDAO getDAO() {
		return bemDAO;
	}
	
	/**
	 * Lista os bens usando a pessoa como filtro.
	 * 
	 * @param pessoa
	 *            a pessoa que sera usada como filtro.
	 * @return os bens usando a pessoa como filtro.
	 */
	public List<Bem> listarPorPessoa(PessoaCompartilhamento pessoa) throws BancoobException{
		return getDAO().listarPorPessoa(pessoa);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarIncluir(Bem bem) throws BancoobException {
//		validarDataVencimento(bem);
//		validarRegistro(bem);
//		validarPosse(bem);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarAlterar(Bem bem) throws BancoobException {
//		validarDataVencimento(bem);
//		validarRegistro(bem);
//		validarPosse(bem);
	}
	
	/**
	 * Lista os bens usando a pessoa como filtro.
	 * 
	 * @param pessoa
	 *            a pessoa que sera usada como filtro.
	 * @return os bens usando a pessoa como filtro.
	 */
	@SuppressWarnings("rawtypes")
	public List<Bem> listarPorBensDiferentesDeImovelPorPessoa(ConsultaDto consultaDto) {
		return getDAO().listarPorBensDiferentesDeImovelPorPessoa(consultaDto);
	}

	/**
	 * Lista os bens usando a pessoa como filtro.
	 * 
	 * @param pessoa
	 *            a pessoa que sera usada como filtro.
	 * @return os bens usando a pessoa como filtro.
	 */
	@SuppressWarnings("rawtypes")
	public List<Bem> listarBemImovelPorPessoa(ConsultaDto consultaDto) {
		return getDAO().listarBemImovelPorPessoa(consultaDto);
	}

	/**
	 * Lista os bens imoveis rurais usando a pessoa como filtro.
	 * 
	 * @param pessoa
	 *            a pessoa que sera usada como filtro.
	 * @return os bens usando a pessoa como filtro.
	 */
	public List<Bem> listarBemImovelRuralPorPessoa(PessoaCompartilhamento pessoa) {
		return getDAO().listarBemImovelRuralPorPessoa(pessoa);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<Bem> listarBemImovelRuralVigentePorPessoa(PessoaCompartilhamento pessoa) throws BancoobException {
		return getDAO().listarBemImovelRuralVigentePorPessoa(pessoa);
	}

//	/**
//	 * O método Validar data vencimento.
//	 *
//	 * @param bem o valor de bem
//	 * @throws DataAtualException lança a exceção DataAtualException
//	 */
//	private void validarDataVencimento(Bem bem) throws DataAtualException {
//		if (bem.getDataVencimentoSeguro() != null) {
//			validarDataAtualMaior("Vencimento do Seguro", bem.getDataVencimentoSeguro());
//		}
//	}

//	/**
//	 * O método Validar registro.
//	 *
//	 * @param bem o valor de bem
//	 * @throws BancoobException lança a exceção DataAtualException
//	 */
//	private void validarRegistro(Bem bem) throws BancoobException {
//		for (BemRegistro bemRegistro : bem.getBensRegistro()) {
//			if (bemRegistro.getDataUltimaMatricula() != null) {
//				validarDataAtualMenor("Data posse do registro", bemRegistro.getDataUltimaMatricula());
//			}
//			if (bemRegistro.getAreaMatricula() != null) {
//				validarCampoAreaMatricula(bemRegistro);
//			}
//		}
//	}
	
//	/**
//	 * O método Validar possa.
//	 *
//	 * @param bem o valor de bem
//	 * @throws BancoobException lança a exceção DataAtualException
//	 */
//	private void validarPosse(Bem bem) throws BancoobException {
//		for (BemPosse bemPosse: bem.getBensPosse()) {
//			if (bemPosse.getArea() != null){
//				validarCampoAreaPosse(bemPosse);
//			}
//		}
//	}

//	private void validarCampoAreaMatricula(BemRegistro bemRegistro) throws BancoobException{
//		if(bemRegistro.getAreaMatricula().precision() > 13) {
//			throw new ValorForaLimiteException("Bem Registro (Qtd. Área)", "0.000001", "9999999.999999");
//		}
//	}
	
//	private void validarCampoAreaPosse(BemPosse bemPosse) throws BancoobException {
//		if(bemPosse.getArea().precision() > 13) {
//			throw new ValorForaLimiteException("Bem Posse (Área Posse)", "0.000001", "9999999.999999");
//		}
//	}

//	/**
//	 * O método Validar data atual maior.
//	 *
//	 * @param campo o valor de campo
//	 * @param date o valor de date
//	 * @throws DataAtualException lança a exceção DataAtualException
//	 */
//	private void validarDataAtualMaior(String campo, Date date) throws DataAtualException {
//		
//		Date dataAtual = DataUtil.configurarPrimeiraDataIntervalo(DataUtil.obterDataAtual());
//		Date data = DataUtil.configurarPrimeiraDataIntervalo(date);
//		if (data.compareTo(dataAtual) <= 0){
//			throw new DataAtualException(campo, "maior");
//		}
//	}

//	/**
//	 * O método Validar data atual menor.
//	 *
//	 * @param campo o valor de campo
//	 * @param date o valor de date
//	 * @throws DataAtualException lança a exceção DataAtualException
//	 */
//	private void validarDataAtualMenor(String campo, Date date) throws DataAtualException {
//		
//		Date dataAtual = DataUtil.configurarPrimeiraDataIntervalo(DataUtil.obterDataAtual());
//		Date data = DataUtil.configurarPrimeiraDataIntervalo(date);
//		if (data.compareTo(dataAtual) >= 0){
//			throw new DataAtualException(campo, "menor");
//		}
//	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Bem incluir(Bem objeto) throws BancoobException {
		tratarValores(objeto);
		return super.incluir(objeto);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(Bem objeto) throws BancoobException {
		tratarValores(objeto);
		super.alterar(objeto);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(Serializable chave) throws BancoobException {
		validarExcluirSerializable(chave);
		super.excluir(chave);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarExcluir(Bem bem) throws BancoobException {
		validarExcluirSerializable(bem.getId());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarExcluir(Serializable objeto) throws BancoobException {
		validarExcluirSerializable(objeto);	
	}
	
	/**
	 * O método Validar excluir serializable.
	 *
	 * @param objeto o valor de objeto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void validarExcluirSerializable(Serializable objeto) throws BancoobException{
//		Bem bem = obterBem(objeto);
//		isBemUtilizado(bem);
//		if(bem instanceof BemImovel) {
//			List<Produtividade> produtividades = servicoProdutividade.listarProdutividadesAssociadas((BemImovel)bem);
//			if(produtividades != null && produtividades.size() >0) {
//				throw new BemExclusaoException();
//			}
//		}
	}	
	
//	/**
//	 * Obtem o Bem 
//	 * @param objeto
//	 * @return
//	 * @throws BancoobException
//	 */
//	private Bem obterBem(Serializable objeto) throws BancoobException {
//		Bem bem = null;
//		
//		if(objeto instanceof Bem
//				|| objeto instanceof BemImovel){
//			bem = obter(((Bem)objeto).getIdBem());	
//		}else {			
//			bem = obter(objeto);
//		}
//		return bem;
//	}

	/**
	 * @see #tratarValor(BigDecimal)
	 * @param bem
	 */
	private void tratarValores(Bem bem) {
		
		bem.setValorAtualMercado(tratarValor(bem.getValorAtualMercado()));
		bem.setValorSeguro(tratarValor(bem.getValorSeguro()));
		bem.setPercentual(tratarValor(bem.getPercentual()));
		if (bem instanceof BemImovel) {
			BemImovel imovel = (BemImovel) bem;
			imovel.setValorBenfeitoria(tratarValor(imovel.getValorBenfeitoria()));
			if(imovel.getArea() != null) {
				imovel.setArea(imovel.getArea().divide(BigDecimal.ONE, 6, RoundingMode.CEILING));
			}
		}
		
		if (!CollectionUtils.isEmpty(bem.getBensOnus())) {
			for (BemOnus onus : bem.getBensOnus()) {
				onus.setValor(tratarValor(onus.getValor()));
			}
		}

		if (!CollectionUtils.isEmpty(bem.getBensRegistro())) {
			for (BemRegistro registro : bem.getBensRegistro()) {
				if(registro.getAreaMatricula() != null) {
					registro.setAreaMatricula(registro.getAreaMatricula().divide(BigDecimal.ONE, 6, RoundingMode.CEILING));
				}
			}
		}

		if (!CollectionUtils.isEmpty(bem.getBensPosse())) {
			for (BemPosse posse : bem.getBensPosse()) {
				posse.setArea(posse.getArea().divide(BigDecimal.ONE, 6, RoundingMode.CEILING));
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Bem importar(Bem bem) throws BancoobException {
		tratarValores(bem);
		validarIncluir(bem);
		return getDAO().incluir(bem);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void criarRegistroSemPatrimonio(Long idPessoaCompartilhamento) throws BancoobException {
		criarRegistroSemPatrimonio(idPessoaCompartilhamento, null);
    }
	
	public void criarRegistroSemPatrimonio(Long idPessoaCompartilhamento, Long idBemNovo) throws BancoobException {
		SubTipoBem subTipoBem = null;
		CAPESCadastroFabricaDelegates fabrica = CAPESCadastroFabricaDelegates.getInstance();
		
		// recupera a pessoa
		PessoaCompartilhamento pessoa = fabrica.criarPessoaCompartilhamentoDelegate().obter(idPessoaCompartilhamento);
		
		//Verifica se já existe um bem desse tipo
		for(Bem bem : listarPorPessoa(pessoa)){
			if(bem.getSubTipo().getCodigo().equals(SubTipoBem.CODIGO_SUBTIPO_SEM_PATRIMONIO)){
				throw new InformacaoExistenteException();
			}
		}

		// recupera a situacao
		SituacaoBem situacaoBem = fabrica.criarSituacaoBemAntigoDelegate().obter(SituacaoBem.CODIGO_SITUACAO_LIVRE);
		
		// recupera o subtipo
		Iterator<SubTipoBem> it = situacaoBem.getSubtipos().iterator();
		for (; it.hasNext() && (subTipoBem == null);) {
	        SubTipoBem temp = it.next();
	        if (SubTipoBem.CODIGO_SUBTIPO_SEM_PATRIMONIO.equals(temp.getCodigo())) {
	        	subTipoBem = temp;
	        }
        }
		
		// cria o bem
		Bem bem = new Bem();
		bem.setPercentual(new BigDecimal(100.0));
		bem.setPessoaCompartilhamento(pessoa);
		bem.setSubTipo(subTipoBem);
		bem.setSituacao(situacaoBem);
		bem.setValorAtualMercado(BigDecimal.ZERO);
		bem.setVerificarAutorizacao(false);
		bem.setIdBemNovo(idBemNovo);
		
		getDAO().incluir(bem);
    }

//    /**
//   	 * Valida se a informação está sendo utilizada por outro sistema.
//   	 * @param bem
//   	 * @throws BancoobException
//   	 */
//   	private void isBemUtilizado(Bem bem) throws BancoobException {
//   		InformacaoUtilizadaDelegate informacaoUtilizadaDelegate = CAPESCadastroFabricaDelegates.getInstance().criarInformacaoUtilizadaDelegate();
//   		List<String> sistemasUtilizandoInformacao = informacaoUtilizadaDelegate.listarSistemasUsandoInformacao(
//   				TipoInformacaoEnum.BEM.getCodigo(), bem.getId());
//   		
//   		if (sistemasUtilizandoInformacao != null && sistemasUtilizandoInformacao.size() > 0) {
//   			throw new InformacaoUtilizadaException(sistemasUtilizandoInformacao.toArray());
//   		}
//   	}
   	
   	/**
   	 * {@inheritDoc}
   	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Bem> obterPorIdBemNovo(PessoaCompartilhamento pessoaCompartilhamento, Long id) throws BancoobException {
		Bem filtro = new Bem();
		filtro.setIdBemNovo(id);
		filtro.setPessoaCompartilhamento(pessoaCompartilhamento);
		ConsultaDto<Bem> criterios = new ConsultaDto<Bem>();
		criterios.setFiltro(filtro);
		return getDAO().listarBensPorIdBemNovo(criterios);
	}
   	
   	/**
   	 * {@inheritDoc}
   	 */
   	@IgnorarAutorizar
   	public void excluirEntidadeSemAutorizacao(Bem objeto) throws BancoobException {
   		getDAO().excluirEntidade(objeto);
   	}

}