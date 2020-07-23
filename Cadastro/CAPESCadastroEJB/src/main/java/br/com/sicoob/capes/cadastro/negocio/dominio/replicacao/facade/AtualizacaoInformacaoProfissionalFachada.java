/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaIntegracaoDelegate;
import br.com.sicoob.capes.comum.negocio.vo.PessoaPlataformaVO;
import br.com.sicoob.capes.negocio.entidades.ConselhoRegional;
import br.com.sicoob.capes.negocio.entidades.TipoAfastamento;
import br.com.sicoob.capes.negocio.entidades.TipoFuncionario;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;
import br.com.sicoob.capes.negocio.entidades.legado.PessoaFisica;
import br.com.sicoob.capes.negocio.entidades.legado.PessoaJuridica;
import br.com.sicoob.capes.negocio.entidades.vigente.InformacaoProfissional;
import br.com.sicoob.capes.replicacao.negocio.delegates.CAPESReplicacaoComumFabricaDelegates;
import br.com.sicoob.capes.replicacao.negocio.delegates.InformacaoProfissionalDelegate;
import br.com.sicoob.capes.replicacao.negocio.delegates.PessoaDelegate;


/**
 * @author Erico.Junior
 *
 */
public class AtualizacaoInformacaoProfissionalFachada {

	private static final int INICIO_DEZENA_ANO = 4;
	private transient PessoaIntegracaoDelegate integracaoDelegate = 
			CAPESCadastroFabricaDelegates.getInstance().criarPessoaIntegracaoDelegate();
	private transient PessoaDelegate pessoaLegadoDelegate = 
			CAPESReplicacaoComumFabricaDelegates.getInstance().criarPessoaDelegate();
	private transient InformacaoProfissionalDelegate infoLegadoDelegate = 
			CAPESReplicacaoComumFabricaDelegates.getInstance().criarInformacaoProfissionalDelegate();
	
	/**
	 * 
	 * @param informacao
	 * @throws BancoobException
	 */
	public void incluirLegado(InformacaoProfissional informacao) throws BancoobException {
		br.com.sicoob.capes.negocio.entidades.legado.InformacaoProfissional infoLegado =
				criarInformacaoLegado(informacao);
		infoLegadoDelegate.incluir(infoLegado, informacao.getIdInstituicao());
	}

	/**
	 * 
	 * @param informacao
	 * @throws BancoobException
	 */
	public void alterarLegado(InformacaoProfissional informacao) throws BancoobException {
		br.com.sicoob.capes.negocio.entidades.legado.InformacaoProfissional infoLegado =
				criarInformacaoLegado(informacao);
		infoLegadoDelegate.alterar(infoLegado, informacao.getIdInstituicao());		
	}
	
	/**
	 * 
	 * @param informacao
	 * @throws BancoobException
	 */
	public void excluirLegado(InformacaoProfissional informacao) throws BancoobException {
		br.com.sicoob.capes.negocio.entidades.legado.InformacaoProfissional infoLegado =
				criarInformacaoLegado(informacao);
		infoLegadoDelegate.excluir(infoLegado, informacao.getIdInstituicao());
	}	
	
	private br.com.sicoob.capes.negocio.entidades.legado.InformacaoProfissional 
			criarInformacaoLegado(InformacaoProfissional informacao) throws BancoobException {
		
		Integer idPessoa = informacao.getPessoa().getIdPessoa();
		Integer idPessoaEmpregador = informacao.getPessoaEmpregador().getIdPessoa();
		Integer idInstituicao = informacao.getIdInstituicao();
		
		br.com.sicoob.capes.negocio.entidades.legado.InformacaoProfissional infoLegado = 
				new br.com.sicoob.capes.negocio.entidades.legado.InformacaoProfissional();
		
		infoLegado.setCargo(informacao.getCargo());
		infoLegado.setDataAdmissao(informacao.getDataAdmissao());
		infoLegado.setDataDesligamento(informacao.getDataDesligamento());
		infoLegado.setIdInformacaoProfissionalDB2(informacao.getIdInformacao());
		infoLegado.setMatricula(informacao.getNumMatricula());
		infoLegado.setMesAnoFerias(obterMesAnoFerias(informacao.getDiaMesFerias()));
		infoLegado.setNumeroInscricaoConselho(informacao.getNumeroInscricaoConselho());
		infoLegado.setEmpregador(obterPessoaJuridica(idPessoaEmpregador, idInstituicao));
		infoLegado.setPessoa(obterPessoaFisica(idPessoa, idInstituicao));		
		infoLegado.setCodigoConselho(obterCodigoConselho(informacao.getConselhoRegional()));
		infoLegado.setTipoAfastamento(obterCodigoTipoAfastamento(informacao.getTipoAfastamento()));
		infoLegado.setTipoFuncionario(obterCodigoTipoFuncionario(informacao.getTipoFuncionario()));
		infoLegado.setUfConselho(informacao.getUfConselho());
		infoLegado.setDescricaoEmpresa(informacao.getPessoaEmpregador().getPessoaCompartilhamento().getNomePessoa());

		tratarSituacaoLegado(informacao, infoLegado);
		
		return infoLegado;
	}

	/**
	 * Tratamento adicionado para compatibilidade com o sistema legado, onde o
	 * código da situação é o contrário do que foi feito no DB2. Quando o
	 * SQLServer deixar de existir, o DB2 continuará correto (seguindo o padrão
	 * do sistema).
	 * 
	 * @param informacao
	 *            br.com.sicoob.capes.negocio.entidades.vigente.InformacaoProfissional entidade do DB2
	 * @param infoLegado
	 *            br.com.sicoob.capes.negocio.entidades.legado.InformacaoProfissional entidade do SQLServer
	 */
	private void tratarSituacaoLegado(InformacaoProfissional informacao, br.com.sicoob.capes.negocio.entidades.legado.InformacaoProfissional infoLegado) {
		Short ativoDB2 = 1;
		Short inativoDB2 = 0;
		if (informacao != null) {
			if (ativoDB2.equals(informacao.getCodSituacao())) {
				infoLegado.setSituacao(inativoDB2);
			} else if (inativoDB2.equals(informacao.getCodSituacao())) {
				infoLegado.setSituacao(ativoDB2);
			}
		}
	}

	/**
	 * Obter pessoa fisica.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @return PessoaFisica
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private PessoaFisica obterPessoaFisica(Integer idPessoa, Integer idInstituicao) 
			throws BancoobException{
		return (PessoaFisica) obterPessoa(idPessoa, idInstituicao);
	}
	
	/**
	 * Obter pessoa juridica.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @return PessoaJuridica
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private PessoaJuridica obterPessoaJuridica(Integer idPessoa, Integer idInstituicao) 
			throws BancoobException{
		return (PessoaJuridica) obterPessoa(idPessoa, idInstituicao);
	}
	
	/**
	 * Obter pessoa.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @return Pessoa
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private Pessoa obterPessoa(Integer idPessoa, Integer idInstituicao) 
			throws BancoobException {
		PessoaPlataformaVO proxy = integracaoDelegate.obterPessoaPlataforma(idPessoa, idInstituicao);
		return pessoaLegadoDelegate.obter(proxy.getIdPessoaLegado(), idInstituicao);
	}
	
	/**
	 * Obter codigo conselho.
	 *
	 * @param conselho o valor de conselho
	 * @return Short
	 */
	private Short obterCodigoConselho(ConselhoRegional conselho) {
		Short codigo = null;
		
		if(conselho != null) {
			codigo = conselho.getCodigo();
		}
		return codigo;
	}
	
	/**
	 * Obter codigo tipo afastamento.
	 *
	 * @param tipo o valor de tipo
	 * @return Short
	 */
	private Short obterCodigoTipoAfastamento(TipoAfastamento tipo) {
		Short codigo = null;
		
		if(tipo != null) {
			codigo = tipo.getCodigo();
		}
		return codigo;
	}

	/**
	 * Obter codigo tipo funcionario.
	 *
	 * @param tipo o valor de tipo
	 * @return Short
	 */
	private Short obterCodigoTipoFuncionario(TipoFuncionario tipo) {
		Short codigo = null;
		
		if(tipo != null) {
			codigo = tipo.getCodigo();
		}
		return codigo;
	}

	/**
	 * Obter mes ano ferias.
	 *
	 * @param mesAno o valor de mes ano
	 * @return String
	 */
	private String obterMesAnoFerias(String mesAno) {
		
		String retorno = null;
		
		if(StringUtils.isNotEmpty(mesAno)) {
			StringBuilder builder = new StringBuilder();
			builder.append(mesAno.substring(0, 2));
			builder.append(mesAno.substring(INICIO_DEZENA_ANO));
			retorno = builder.toString();
		}

		return retorno;
	}

}
