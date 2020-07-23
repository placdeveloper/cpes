package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.HistoricoAlteracaoCpfCnpjDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaDelegate;
import br.com.sicoob.capes.cadastro.negocio.dto.AlteracaoDocumentoPessoaDTO;
import br.com.sicoob.capes.cadastro.negocio.excecao.CampoNaoInformadoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.PessoaNaoEncontradaException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.AlterarDocumentoPessoaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.AlterarDocumentoPessoaServicoRemote;
import br.com.sicoob.capes.cadastro.negocio.vo.ConsultaAlteracaoDocumentoVO;
import br.com.sicoob.capes.cadastro.persistencia.dao.AlterarDocumentoPessoaDAO;
import br.com.sicoob.capes.comum.negocio.vo.InstituicaoVO;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.HistoricoAlteracaoCpfCnpj;
import br.com.sicoob.capes.negocio.entidades.Pessoa;

/**
 * @author Erico.Junior
 * 
 */
@Stateless
@Local( { AlterarDocumentoPessoaServicoLocal.class })
@Remote( { AlterarDocumentoPessoaServicoRemote.class })
public class AlterarDocumentoPessoaServicoEJB extends CAPESCadastroServicoEJB implements
		AlterarDocumentoPessoaServicoRemote, AlterarDocumentoPessoaServicoLocal {

	@Inject
	@Default
	private AlterarDocumentoPessoaDAO dao;
	
	/**
	 * {@inheritDoc}
	 * 
	 * @throws BancoobException
	 */
	public void alterarDocumentoPessoa(AlteracaoDocumentoPessoaDTO dto) throws BancoobException {

		CAPESCadastroFabricaDelegates fabrica = 
				CAPESCadastroFabricaDelegates.getInstance();
		
		PessoaDelegate pessoaDelegate = fabrica.criarPessoaDelegate();
		HistoricoAlteracaoCpfCnpjDelegate historicoDelegate = 
				fabrica.criarHistoricoAlteracaoCpfCnpjDelegate();
		
		Pessoa pessoa = pessoaDelegate.obter(dto.getPessoa().getId());
		HistoricoAlteracaoCpfCnpj historico = obterHistoricoAlteracaoCpfCnpj(dto, pessoa.getCpfCnpj());
		
		pessoaDelegate.alterarCpfCnpj(pessoa, dto.getCpfCnpjNovo());		
		historicoDelegate.incluir(historico);
	}

	/**
	 * Obter historico alteracao cpf cnpj.
	 *
	 * @param dto o valor de dto
	 * @param cpfCnpjAnterior o valor de cpf cnpj anterior
	 * @return HistoricoAlteracaoCpfCnpj
	 */
	private HistoricoAlteracaoCpfCnpj obterHistoricoAlteracaoCpfCnpj(
			AlteracaoDocumentoPessoaDTO dto, String cpfCnpjAnterior) {
		

		HistoricoAlteracaoCpfCnpj historico = new HistoricoAlteracaoCpfCnpj();
		historico.setCpfCnpjNovo(dto.getCpfCnpjNovo());
		historico.setMotivo(dto.getMotivo());
		historico.setNomeSolicitante(dto.getSolicitante());
		historico.setCpfCnpjAnterior(cpfCnpjAnterior);
		historico.setPessoa(dto.getPessoa()); 
		historico.setDataHoraAlteracao(new DateTimeDB());
		return historico;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ConsultaAlteracaoDocumentoVO> consultarVinculosDocumento(
			String cpfCnpj) throws BancoobException {
		
		if(cpfCnpj == null || StringUtils.isEmpty(cpfCnpj.trim())) {
			throw new CampoNaoInformadoException("CPF/CNPJ");
		}
		
		List<ConsultaAlteracaoDocumentoVO> lista = dao.consultarVinculosDocumento(cpfCnpj);
		if(lista == null || lista.isEmpty()) {
			throw new PessoaNaoEncontradaException();
		}

		return preencherDadosInstituicao(lista);
	}
	
	/**
	 * Preenche o nome das instituições das lista de transições.
	 * 
	 * @param lista
	 *            A lista com as transições da pessoa.
	 * @return A lista com o nome das instituições preenchidos.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	private List<ConsultaAlteracaoDocumentoVO> preencherDadosInstituicao(List<ConsultaAlteracaoDocumentoVO> lista)
			throws BancoobException {

		Map<Integer, InstituicaoVO> mapa = new HashMap<Integer, InstituicaoVO>();
		
		SCIIntegracaoDelegate delegate = CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();
		InstituicaoVO instituicao = null;

		for (ConsultaAlteracaoDocumentoVO dto : lista) {

			Integer idInstituicao = dto.getIdInstituicao();
			
			if (!mapa.containsKey(idInstituicao)) {
				instituicao = delegate.obterInstituicaoPorId(idInstituicao);
				mapa.put(idInstituicao, instituicao);
			}	

			instituicao = mapa.get(idInstituicao);
			if (instituicao != null) {
				dto.setNomeInstituicao(instituicao.getNome());
				dto.setNumeroInstituicao(String.valueOf(instituicao.getNumero()));
			}
		}
		
		return lista;
	}

}
