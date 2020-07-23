/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;

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
import br.com.bancoob.util.MensagemUtil;
import br.com.sicoob.capes.cadastro.infraestrutura.mensagens.CAPESCadastroResourceBundle;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.TipoEmpresaDelegate;
import br.com.sicoob.capes.cadastro.negocio.dominio.validacao.Validacao;
import br.com.sicoob.capes.cadastro.negocio.dominio.validacao.ValidacaoFonteRenda;
import br.com.sicoob.capes.cadastro.negocio.excecao.QuantidadeFonteRendaPermitidaException;
import br.com.sicoob.capes.cadastro.negocio.proxies.FonteRendaProxy;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.FonteRendaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.FonteRendaServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.FonteRendaDAO;
import br.com.sicoob.capes.comum.negocio.annotation.IgnorarAutorizar;
import br.com.sicoob.capes.comum.negocio.annotation.IntegracaoGedGft;
import br.com.sicoob.capes.comum.negocio.enums.TipoEmpresaEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.GEDIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.TipoEmpresa;
import br.com.sicoob.capes.negocio.entidades.TipoFonteRenda;
import br.com.sicoob.capes.negocio.entidades.vigente.FonteRenda;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaJuridica;

/**
 * Implementa as operaïcoes do servico de fontes de renda.
 * 
 * @author Erico.Junior
 */
@Stateless
@Local( { FonteRendaServicoLocal.class })
@Remote( { FonteRendaServicoRemote.class })
@IntegracaoGedGft
public class FonteRendaServicoEJB extends EntidadeCadastroServicoEJB<FonteRenda> implements
		FonteRendaServicoRemote, FonteRendaServicoLocal {
	
	@Inject
	@Default
	private FonteRendaDAO fonteRendaDao;
	
	/** QTD_PERMITIDA_FONTE_RENDA_PJ */
	private static final Integer QTD_PERMITIDA_FONTE_RENDA_PJ = 1;
	
	/** Objeto de acesso aos dados a PessoaCompartilhamentoDelegate . */
	private PessoaCompartilhamentoDelegate pessoaCompartilhamentoServico = CAPESCadastroFabricaDelegates.getInstance().criarPessoaCompartilhamentoDelegate();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public FonteRenda incluir(FonteRenda renda) throws BancoobException {
		renda.setValorReceitaBrutaMensal(tratarValor(renda.getValorReceitaBrutaMensal()));
		renda = super.incluir(renda);
		if(isRegistroVigente(renda)){
			atualizarTipoEmpresaIdeal(renda);
		}
		return renda;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(FonteRenda renda) throws BancoobException {
		renda.setValorReceitaBrutaMensal(tratarValor(renda.getValorReceitaBrutaMensal()));
		super.alterar(renda);
		FonteRenda fonteRenda = obter(renda.getIdFonteRenda());
		if (isRegistroVigente(fonteRenda)) {
			if (renda.getIdUsuarioEnvio() != null) {
				pessoaCompartilhamentoServico.renovarCadastroAutomatico(renda, renda.getPessoaCompartilhamento().getPessoa().getIdPessoa(), renda.getIdUsuarioEnvio());
			}
		}
	}
	
	@Override
	public void excluirEntidade(FonteRenda renda) throws BancoobException {
		super.excluirEntidade(renda);
		if(isRegistroVigente(renda)){
			atualizarTipoEmpresaIdeal(renda, true);
			if(renda.getIdUsuarioEnvio() != null){
				pessoaCompartilhamentoServico.renovarCadastroAutomatico(renda, renda.getPessoaCompartilhamento().getPessoa().getIdPessoa(), renda.getIdUsuarioEnvio());
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected FonteRendaDAO getDAO() {
		return fonteRendaDao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarIncluir(FonteRenda fonte) throws BancoobException {
		validaQuantidadeFonteRendaPJ(fonte);
		validarFonteRenda(fonte);
	}

	private void validaQuantidadeFonteRendaPJ(FonteRenda fonte) throws BancoobException {
		if (isRendaPessoaJuridica(fonte) && isRegistroVigente(fonte)) {
			List<FonteRendaProxy> listaF = listarRendas(fonte.getPessoaCompartilhamento());
			if (listaF.size() >= QTD_PERMITIDA_FONTE_RENDA_PJ) {
				throw new QuantidadeFonteRendaPermitidaException();
			}
		}	
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarAlterar(FonteRenda fonte) throws BancoobException {
		validarFonteRenda(fonte);
	}


	/**
	 * O método Validar fonte renda.
	 *
	 * @param fonte o valor de fonte
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarFonteRenda(FonteRenda fonte) throws BancoobException {

		Validacao<FonteRenda> validacao = new ValidacaoFonteRenda();
		validacao.validar(fonte);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<FonteRendaProxy> listarRendas(PessoaCompartilhamento pessoa) throws BancoobException {
		
		FonteRenda filtro = new FonteRenda();
		filtro.setPessoaCompartilhamento(pessoa);

		ConsultaDto<FonteRenda> criterios = new ConsultaDto<FonteRenda>();
		criterios.setFiltro(filtro);
		List<FonteRenda> lista = listar(criterios);
		
		List<FonteRendaProxy> retorno = new ArrayList<FonteRendaProxy>();
		FonteRendaProxy proxy = null;
		
		for (FonteRenda fonte : lista) {
			
			PessoaCompartilhamento empregador = fonte.getPessoaEmpregador();
			TipoFonteRenda tipoFonteRenda = fonte.getTipoFonteRenda();
			
			proxy = new FonteRendaProxy();
			proxy.setBolRendaFixa(fonte.getBolRendaFixa());
			proxy.setBolSimplesNacional(fonte.getBolSimplesNacional());
			proxy.setCodigoTipoFonteRenda(tipoFonteRenda.getCodigo());
			proxy.setDataHoraInicio(fonte.getDataHoraInicio());
			proxy.setDataValidadeRenda(fonte.getDataValidadeRenda());
			proxy.setDescFonteRenda(fonte.getDescFonteRenda());
			proxy.setDescricaoTipoFonteRenda(tipoFonteRenda.getDescricao());
			proxy.setIdFonteRenda(fonte.getIdFonteRenda());
			proxy.setIdPessoa(fonte.getPessoaCompartilhamento().getIdPessoaCompartilhamento());
			proxy.setIdInstituicaoAtualizacao(fonte.getIdInstituicaoAtualizacao());
			proxy.setCodigoSituacaoAprovacao(fonte.getCodigoSituacaoAprovacao());
			proxy.setIdUsuarioAprovacao(fonte.getIdUsuarioAprovacao());
			
			if(empregador != null) {
				proxy.setIdPessoaEmpregador(empregador.getIdPessoaCompartilhamento());
			}
			proxy.setValorReceitaBrutaMensal(fonte.getValorReceitaBrutaMensal());
			retorno.add(proxy);
		}
		
		return retorno;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void excluirRendas(List<FonteRenda> rendas) throws BancoobException {
		
		if(rendas != null) {
			for (FonteRenda fonteRenda : rendas) {
				getDAO().excluir(fonteRenda.getId());
				GEDIntegracaoDelegate gedIntegracaoDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarGEDIntegracaoDelegate();
				gedIntegracaoDelegate.desassociarDocumentosDossie(fonteRenda.getIdRegistroControlado(), fonteRenda.getPessoaCompartilhamento().getPessoa().getTipoPessoa().getCodTipoPessoa());
			}
		}
		
	}

	/**
	 * {@inheritDoc}
	 */	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<FonteRenda> listarVencidas(ConsultaDto<FonteRenda> criterios)
			throws BancoobException {
		
		FonteRenda filtro = new FonteRenda();
		filtro.setDataValidadeRenda(new DateTimeDB());
		
		criterios.setOrdenacao("padrao");
		criterios.setFiltro(filtro);
		return getDAO().listarVencidas(criterios);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@IgnorarAutorizar
	public FonteRenda importar(FonteRenda renda) throws BancoobException {
		validarIncluir(renda);
		return getDAO().incluir(renda);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String obterMensagensValidacao(FonteRenda renda) throws BancoobException {
		String mensagem = null;
		if (renda != null) {
			String mensagemValorPF = "MN184";			
			//Verifica se é pessoa física.
			if (!isRendaPessoaJuridica(renda)) {
				PessoaFisica pessoa = (PessoaFisica) consultarPessoaPorID(renda.getPessoaCompartilhamento());
				if (pessoa != null) {
					//Se o vínculo empregatício estiver nulo ou o valor da renda for 
					//menor que a renda mínima informada no vínculo a mensagem será exibida.
					if (pessoa.getVinculoEmpregaticio() == null
					        || renda.getValorReceitaBrutaMensal().compareTo(
					                pessoa.getVinculoEmpregaticio()
					                        .getValorRendaMinimaObrigatoria()) < 0) {
						mensagem = obterMensagem(mensagemValorPF);
					}
				}
			}
		}
		return mensagem;
	}

	/**
	 * Obter mensagem.
	 *
	 * @param codigoMensagem o valor de codigo mensagem
	 * @return String
	 */
	private String obterMensagem(String codigoMensagem) {
		return MensagemUtil.getString(codigoMensagem, CAPESCadastroResourceBundle.getInstance());
	}

    private boolean isRendaPessoaJuridica(FonteRenda renda) throws BancoobException{
    	return TipoPessoaEnum.isPessoaJuridica(renda.getPessoaCompartilhamento().getPessoa().getTipoPessoa().getCodTipoPessoa());
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    private void atualizarTipoEmpresaIdeal(FonteRenda fonteRenda) throws BancoobException {
    	atualizarTipoEmpresaIdeal(fonteRenda, false);
    }
	
    @Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void atualizarTipoEmpresaIdeal(FonteRenda fonteRenda, boolean exclusao) throws BancoobException {
		if (isRendaPessoaJuridica(fonteRenda)) {
			PessoaJuridica pessoaJuridica = (PessoaJuridica) fonteRenda.getPessoaCompartilhamento();
			if (exclusao) {
				pessoaJuridica.setTipoEmpresa(null);
			} else {
				if (fonteRenda.getBolPossuiAtivo()) {
					TipoEmpresa tipoEmpresa = new TipoEmpresa();
					tipoEmpresa.setId(TipoEmpresaEnum.GRANDE_EMPRESA.getCodigo());
					pessoaJuridica.setTipoEmpresa(tipoEmpresa);
				} else {
					TipoEmpresa tipoEmpresa = obterTipoEmpresaPorFaixaDeRendaAnual(fonteRenda);
					pessoaJuridica.setTipoEmpresa(tipoEmpresa);
				}
			}
			CAPESCadastroFabricaDelegates.getInstance().criarPessoaCompartilhamentoDelegate().atualizaCodTipoEmpresa(pessoaJuridica);
		}
	}
    
	private PessoaCompartilhamento consultarPessoaPorID(PessoaCompartilhamento pessoa) throws BancoobException {
		return CAPESCadastroFabricaDelegates.getInstance().criarPessoaCompartilhamentoDelegate().obter(pessoa.getId());
	}
    
	private TipoEmpresa obterTipoEmpresaPorFaixaDeRendaAnual(FonteRenda fonteRenda) throws BancoobException {
		TipoEmpresaDelegate empresaDelegate = CAPESCadastroFabricaDelegates.getInstance().criarTipoEmpresaDelegate();
		TipoEmpresa tipoEmpresaRetorno = empresaDelegate.obterTipoEmpresaPorFaixaRendaAnual(fonteRenda);
		return tipoEmpresaRetorno;
	}
	
}