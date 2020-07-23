package br.com.sicoob.capes.api.inclusao.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.capes.api.inclusao.negocio.dto.BemDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.BemProprietarioDTO;
import br.com.sicoob.capes.api.inclusao.negocio.excecao.CAPESApiInclusaoException;
import br.com.sicoob.capes.api.inclusao.negocio.excecao.CAPESApiInclusaoNegocioException;
import br.com.sicoob.capes.api.inclusao.negocio.excecao.CAPESApiInclusaoRuntimeException;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.interfaces.BemServicoLocal;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.interfaces.BemServicoRemote;
import br.com.sicoob.capes.cadastro.negocio.delegates.BemDelegate;
import br.com.sicoob.capes.comum.negocio.enums.TipoClassificacaoBemEnum;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.bem.Bem;
import br.com.sicoob.capes.negocio.entidades.bem.BemPessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * EJB com os serviços relacionados ao Bem.
 * 
 * @author Bruno.Carneiro
 */
@Stateless
@Local(BemServicoLocal.class)
@Remote(BemServicoRemote.class)
public class BemServicoEJB extends CAPESApiInclusaoServicoEJB<BemDTO, Bem> implements BemServicoLocal, BemServicoRemote {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(BemDTO dto) throws BancoobException {
		throw new UnsupportedOperationException("Operação não suportada.");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void copiarPropriedadesAlteracao(Bem entidadeDestino, Bem entidadeFonte) {
		ReflexaoUtils.copiarPropriedades(entidadeDestino, entidadeFonte, "idInstituicaoAtualizacao", "idRegistroControlado", "codigoSituacaoAprovacao", "situacaoAprovacao", "compartilhamentoCadastro", "codCompartilhamentoCadastro");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void validarAlteracao(BemDTO dto) throws BancoobException {
		validarPropriedades(dto, new String[] { "idInstituicao", "idUnidadeInst", "idUsuarioAprovacao" });
		validarPropriedadesObrigatoriasAlteracao(dto);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] obterPropriedadesObrigatoriasInclusao() {
		return new String[] { "codigoTipoClassificacaoBem", "descricao", "valor" };
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] obterPropriedadesObrigatoriasAlteracao() {
		List<String> lista = new ArrayList<String>(Arrays.asList(obterPropriedadesObrigatoriasInclusao()));
		lista.add("idBem");
		return lista.toArray(new String[lista.size()]);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void validarPropriedadesObrigatoriasInclusao(BemDTO dto) throws BancoobException {
		super.validarPropriedadesObrigatoriasInclusao(dto);
		validarPropriedadesEspecificas(dto);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void validarPropriedadesObrigatoriasAlteracao(BemDTO dto) throws BancoobException {
		super.validarPropriedadesObrigatoriasAlteracao(dto);
		validarPropriedadesEspecificas(dto);
	}
	
	/**
	 * Realiza a validação das propriedades específicas de cada bem.
	 * 
	 * @param dto
	 *            o dto para ser validado.
	 * @throws BancoobException
	 */
	private void validarPropriedadesEspecificas(BemDTO dto) throws BancoobException {
		if (TipoClassificacaoBemEnum.isImovel(dto.getCodigoTipoClassificacaoBem())) {
			validarPropriedades(dto, obterPropriedadesBemImovel());
		} else {
			validarPropriedades(dto, obterPropriedadesBemMovel());
		}
	}

	/**
	 * Obtém o nome das propriedades específicas do bem imóvel.
	 * 
	 * @return {@code String[]} com as propriedades.
	 */
	private String[] obterPropriedadesBemImovel() {
		return new String[] { "codigoTipoLocalizacaoBem", "codigoTipoUsoBem", "codigoTipoBem", "denominacao", "areaTotal", "codigoUnidadeMedida", "idLocalidade" };
	}

	/**
	 * Obtém o nome das propriedades específicas do bem móvel.
	 * 
	 * @return {@code String[]} com as propriedades.
	 */
	private String[] obterPropriedadesBemMovel() {
		return new String[] { "codigoTipoBem" };
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void incluirSemPatrimonio(BemDTO dto) throws BancoobException {
		getLogger().debug("Incluindo bem sem patrimonio.");
		getLogger().debug("Parametros informados:", obterInformacoesObjeto(dto));
		try {
			validarPropriedadesBasicas(dto);
			preencherInformacoesUsuario(dto);

			PessoaCompartilhamento pessoaCompartilhamento = obterPessoaCompartilhamento(dto.getIdPessoa(), dto.getIdInstituicao());
			getLogger().debug("PessoaCompartilhamento encontrada para inclusao de bem sem patrimonio: ", String.valueOf(pessoaCompartilhamento != null));
			if (pessoaCompartilhamento != null) {
				BemDelegate delegate = (BemDelegate) obterDelegate();
				delegate.adicionarBemSemPatrimonio(pessoaCompartilhamento);
			}

			removerInformacoesUsuario();
		} catch (NegocioException e) {
			getLogger().debug("[CAPES Api Inclusao] Erro ao incluir bem sem patrimonio: ", e.getMessage());
			CAPESApiInclusaoNegocioException.lancarExcecao(e.getMessage());
		} catch (BancoobException e) {
			getLogger().erro(e, "[CAPES Api Inclusao] Erro ao incluir bem sem patrimonio");
			CAPESApiInclusaoException.lancarExcecao(e.getMessage());
		} catch (BancoobRuntimeException e) {
			getLogger().erro(e, "[CAPES Api Inclusao] Erro ao incluir bem sem patrimonio");
			CAPESApiInclusaoRuntimeException.lancarExcecao(e.getMessage());
		} catch (Exception e) { //NOPMD 
			getLogger().erro(e, "[CAPES Api Inclusao] Erro ao incluir bem sem patrimonio");
			CAPESApiInclusaoRuntimeException.lancarExcecao(e.getMessage());
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void incluirRecusouInformar(BemDTO dto) throws BancoobException {
		getLogger().debug("Incluindo bem sem patrimonio.");
		getLogger().debug("Parametros informados:", obterInformacoesObjeto(dto));
		try {
			validarPropriedadesBasicas(dto);
			preencherInformacoesUsuario(dto);

			PessoaCompartilhamento pessoaCompartilhamento = obterPessoaCompartilhamento(dto.getIdPessoa(), dto.getIdInstituicao());
			getLogger().debug("PessoaCompartilhamento encontrada para inclusao de bem sem patrimonio: ", String.valueOf(pessoaCompartilhamento != null));
			if (pessoaCompartilhamento != null) {
				BemDelegate delegate = (BemDelegate) obterDelegate();
				delegate.adicionarBemRecusouInformar(pessoaCompartilhamento);
			}
			
			removerInformacoesUsuario();
		} catch (NegocioException e) {
			getLogger().debug("[CAPES Api Inclusao] Erro ao incluir bem sem patrimonio: ", e.getMessage());
			CAPESApiInclusaoNegocioException.lancarExcecao(e.getMessage());
		} catch (BancoobException e) {
			getLogger().erro(e, "[CAPES Api Inclusao] Erro ao incluir bem sem patrimonio");
			CAPESApiInclusaoException.lancarExcecao(e.getMessage());
		} catch (BancoobRuntimeException e) {
			getLogger().erro(e, "[CAPES Api Inclusao] Erro ao incluir bem sem patrimonio");
			CAPESApiInclusaoRuntimeException.lancarExcecao(e.getMessage());
		} catch (Exception e) { //NOPMD 
			getLogger().erro(e, "[CAPES Api Inclusao] Erro ao incluir bem sem patrimonio");
			CAPESApiInclusaoRuntimeException.lancarExcecao(e.getMessage());
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void alterarProprietarios(BemDTO dto, List<BemProprietarioDTO> proprietarios) throws BancoobException {
		getLogger().debug("Alterando os proprietarios do bem");
		getLogger().debug("Parametros informados:", obterInformacoesObjeto(dto));

		try {
			validarPropriedadesBasicas(dto);
			validarPropriedades(dto, new String[] { "id" });
			preencherInformacoesUsuario(dto);
			Bem bem = obterEntidadeBanco(dto.getId());

			getLogger().debug("Bem encontrado: ", String.valueOf(bem != null));

			if (bem != null) {
				removerObjetoSessao(bem);
				List<BemPessoaCompartilhamento> listaProprietarios = null;
				if (proprietarios != null && proprietarios.size() > 0) {
					getLogger().debug("A lista informada possui proprietarios.");
					listaProprietarios = obterProprietarios(bem, proprietarios);
				} else {
					getLogger().debug("Nao foram informados proprietarios.");
					listaProprietarios = obterProprietariosExclusao(dto, bem.getProprietarios());
				}
				bem.setProprietarios(listaProprietarios);
				obterDelegate().alterar(bem);
			}

			removerInformacoesUsuario();
		} catch (NegocioException e) {
			getLogger().debug("[CAPES Api Inclusao] Erro ao alterar os proprietarios do bem: ", e.getMessage());
			CAPESApiInclusaoNegocioException.lancarExcecao(e.getMessage());
		} catch (BancoobException e) {
			getLogger().erro(e, "[CAPES Api Inclusao] Erro ao alterar os proprietarios do bem");
			CAPESApiInclusaoException.lancarExcecao(e.getMessage());
		} catch (BancoobRuntimeException e) {
			getLogger().erro(e, "[CAPES Api Inclusao] Erro ao alterar os proprietarios do bem");
			CAPESApiInclusaoRuntimeException.lancarExcecao(e.getMessage());
		} catch (Exception e) { // NOPMD
			getLogger().erro(e, "[CAPES Api Inclusao] Erro ao alterar os proprietarios do bem");
			CAPESApiInclusaoRuntimeException.lancarExcecao(e.getMessage());
		}
	}
	
	/**
	 * Obtém a lista dos proprietários.
	 * 
	 * @param bem
	 * @param proprietarios
	 * @return
	 * @throws BancoobException
	 */
	private List<BemPessoaCompartilhamento> obterProprietarios(Bem bem, List<BemProprietarioDTO> proprietarios) throws BancoobException {
		List<BemPessoaCompartilhamento> retorno = new ArrayList<BemPessoaCompartilhamento>();
		for (BemProprietarioDTO prop : proprietarios) {
			PessoaCompartilhamento pessoa = obterPessoaCompartilhamento(prop.getIdPessoa(), prop.getIdInstituicao());

			if (pessoa != null) {
				BemPessoaCompartilhamento bpc = new BemPessoaCompartilhamento();
				bpc.setBem(bem);
				bpc.setIdPessoaCompartilhamento(pessoa.getId());
				bpc.setPessoaCompartilhamento(pessoa);
				bpc.setPessoaResponsavel(prop.getPessoaResponsavel());
				bpc.setPercentualProprietario(prop.getPercentualProprietario());

				retorno.add(bpc);
			}
		}
		return retorno;
	}

	/**
	 * Obter os proprietários para exclusão
	 * @param dto
	 * @param proprietarios
	 * @return
	 * @throws BancoobException
	 */
	private List<BemPessoaCompartilhamento> obterProprietariosExclusao(BemDTO dto, List<BemPessoaCompartilhamento> proprietarios) throws BancoobException {
		for (BemPessoaCompartilhamento prop : proprietarios) {
			prop.setPessoaResponsavel(dto.getIdPessoa().equals(prop.getPessoaCompartilhamento().getPessoa().getIdPessoa()));
			prop.setPercentualProprietario(BigDecimal.ZERO);
			prop.setMarcadoExclusao(Boolean.TRUE);
		}
		return proprietarios;
	}

}