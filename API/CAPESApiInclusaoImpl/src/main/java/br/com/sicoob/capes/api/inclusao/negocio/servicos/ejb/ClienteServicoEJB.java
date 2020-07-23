package br.com.sicoob.capes.api.inclusao.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.api.inclusao.negocio.dto.ClienteDTO;
import br.com.sicoob.capes.api.inclusao.negocio.excecao.CAPESApiInclusaoException;
import br.com.sicoob.capes.api.inclusao.negocio.excecao.CAPESApiInclusaoNegocioException;
import br.com.sicoob.capes.api.inclusao.negocio.excecao.CAPESApiInclusaoRuntimeException;
import br.com.sicoob.capes.api.inclusao.negocio.excecao.RegistroPendenteAprovacaoException;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.interfaces.ClienteServicoLocal;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.interfaces.ClienteServicoRemote;
import br.com.sicoob.capes.cadastro.negocio.delegates.FuncionarioDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaInstituicaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.enums.FuncaoEnum;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.Funcionario;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao;

/**
 * Classe contendo os servi�os de Cliente.
 * 
 * @author bruno.carneiro
 */
@Stateless
@Local(ClienteServicoLocal.class)
@Remote(ClienteServicoRemote.class)
public class ClienteServicoEJB extends CAPESApiInclusaoServicoEJB<ClienteDTO, PessoaInstituicao> implements ClienteServicoLocal, ClienteServicoRemote {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(ClienteDTO dto) throws BancoobException {
		throw new UnsupportedOperationException("Opera��o n�o suportada.");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] obterPropriedadesObrigatoriasInclusao() {
		return new String[] { "codigoFuncionario", "codigoPerfilTarifario", "codigoNucleo" };
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] obterPropriedadesObrigatoriasAlteracao() {
		return obterPropriedadesObrigatoriasInclusao();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected PessoaInstituicao realizarInclusao(PessoaInstituicao entidade, ClienteDTO dto) throws BancoobException {
		entidade.setDataCadastro(new DateTimeDB());
		return super.realizarInclusao(entidade, dto);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void copiarPropriedadesAlteracao(PessoaInstituicao entidadeDestino, PessoaInstituicao entidadeFonte) {
		ReflexaoUtils.copiarPropriedades(entidadeDestino, entidadeFonte, "id", "idPessoaInstituicao", "dataCadastro", "idInstituicaoAtualizacao", "idRegistroControlado", "codigoSituacaoAprovacao", "situacaoAprovacao");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void alterarGerente(ClienteDTO dto) throws BancoobException {
		getLogger().debug("Iniciando a alteracao do gerente: ", dto.getClass().getName());
		getLogger().debug("Parametros informados: ", obterInformacoesObjeto(dto));
		try {
			// Valida se o DTO cont�m as informa��es necess�rias para a altera��o do registro.
			validarAlteracaoGerente(dto);
			
			// Preenche as informa��es do usu�rio logado para ser usado durante a sess�o.
			preencherInformacoesUsuario(dto);
			
			// Cria o filtro para a consulta ao cliente.
			PessoaInstituicao filtroPesquisa = obterFiltroPesquisa(dto);
			
			// Obt�m a entidade do banco para a altera��o.
			PessoaInstituicao entidadeBanco = obterFabricaCadastro().criarPessoaInstituicaoDelegate().obterPorPessoaInstituicaoSemValidacao(filtroPesquisa);
			if (entidadeBanco == null) {
				throw new CAPESApiInclusaoNegocioException("Cliente n�o encontrado com os par�mentros informados.");
			}
			
			// Remove o objeto da sess�o. (Caso o fluxo de dupla conferencia esteja ligado, n�o altera a refer�ncia vinculada � sess�o).
			removerObjetoSessao(entidadeBanco);
			
			// Altera o funcion�rio.
			Funcionario funcionario = obterFuncionario(dto.getCodigoFuncionario());
			if (funcionario == null) {
				throw new CAPESApiInclusaoNegocioException("Funcion�rio n�o encontrado com os par�metros informados.");
			} else if (!FuncaoEnum.GERENTE.getIdTipoFuncao().equals(funcionario.getFuncao().getId())) {
				throw new CAPESApiInclusaoNegocioException("O funcion�rio informado n�o possui a fun��o de gerente.");
			}
			
			entidadeBanco.setFuncionario(funcionario);
			
			// Realiza a altera��o.
			PessoaInstituicaoDelegate delegate = (PessoaInstituicaoDelegate) obterDelegate();
			delegate.alterar(entidadeBanco);
			
			// Remove as informa��es do usu�rio logado.
			removerInformacoesUsuario();
		} catch (br.com.sicoob.capes.cadastro.negocio.excecao.RegistroPendenteAprovacaoException e) {
			getLogger().debug("[CAPES Api Inclusao] Erro ao alterar o gerente: ", e.getMessage());
			RegistroPendenteAprovacaoException.lancarExcecao(e.getMessage());
		} catch (NegocioException e) {
			getLogger().debug("[CAPES Api Inclusao] Erro ao alterar o gerente: ", e.getMessage());
			CAPESApiInclusaoNegocioException.lancarExcecao(e.getMessage());
		} catch (BancoobException e) {
			getLogger().erro(e, "[CAPES Api Inclusao] Erro ao alterar o gerente");
			CAPESApiInclusaoException.lancarExcecao(e.getMessage());
		} catch (BancoobRuntimeException e) {
			getLogger().erro(e, "[CAPES Api Inclusao] Erro ao alterar o gerente");
			CAPESApiInclusaoRuntimeException.lancarExcecao(e.getMessage());
		} catch (Exception e) { //NOPMD
			getLogger().erro(e, "[CAPES Api Inclusao] Erro ao alterar o gerente");
			CAPESApiInclusaoRuntimeException.lancarExcecao(e.getMessage());
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void alterarGerente(ClienteDTO dto, boolean notificarCTZ) throws BancoobException {
		getLogger().info("Integra��o com o CTZ est� desativada.");
	}

	/**
	 * Verifica se o DTO tem as informa��es necess�rias para altera��o do
	 * gerente.
	 * 
	 * @param dto
	 * @throws BancoobException
	 */
	private void validarAlteracaoGerente(ClienteDTO dto) throws BancoobException {
		validarPropriedadesBasicas(dto);
		validarPropriedades(dto, new String[] { "codigoFuncionario" });
	}

	/**
	 * Cria uma PessoaInstituicao para a pesquisa.
	 * 
	 * @param dto
	 * @return
	 */
	private PessoaInstituicao obterFiltroPesquisa(ClienteDTO dto) {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(dto.getIdPessoa());
		PessoaInstituicao pessoaInstituicao = new PessoaInstituicao();
		pessoaInstituicao.setPessoa(pessoa);
		pessoaInstituicao.setIdInstituicao(dto.getIdInstituicao());
		return pessoaInstituicao;
	}
	
	/**
	 * Obt�m o funcion�rio � partir dos par�metros informados.
	 * 
	 * @param idFuncionario
	 * @return
	 * @throws BancoobException
	 */
	private Funcionario obterFuncionario(Integer idFuncionario) throws BancoobException {
		FuncionarioDelegate funcionarioDelegate = obterFabricaCadastro().criarFuncionarioDelegate();
		return funcionarioDelegate.obter(idFuncionario);
	}
	
}