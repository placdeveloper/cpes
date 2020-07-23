/**
 * 
 */
package br.com.sicoob.capes.processamento.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.validacao.ResultadoValidacao;
import br.com.bancoob.validacao.Validacao;
import br.com.bancoob.validacao.ValidacaoCpf;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.excecao.DataMaiorException;
import br.com.sicoob.capes.cadastro.negocio.excecao.FormatoInvalidoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.PessoaNaoEncontradaException;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;
import br.com.sicoob.capes.processamento.negocio.dominio.inss.InclusaoBeneficiarioINSS;
import br.com.sicoob.capes.processamento.negocio.dominio.inss.InclusaoClienteBeneficiarioINSS;
import br.com.sicoob.capes.processamento.negocio.dominio.inss.InclusaoEnderecoBeneficiarioINSS;
import br.com.sicoob.capes.processamento.negocio.dto.BeneficiarioDTO;
import br.com.sicoob.capes.processamento.negocio.dto.RequisicaoBeneficiarioDTO;
import br.com.sicoob.capes.processamento.negocio.excecao.BeneficiarioINSSException;
import br.com.sicoob.capes.processamento.negocio.excecao.CpfInvalidoException;
import br.com.sicoob.capes.processamento.negocio.excecao.DataNascimentoException;
import br.com.sicoob.capes.processamento.negocio.excecao.InclusaoPessoaException;
import br.com.sicoob.capes.processamento.negocio.servicos.interfaces.InclusaoBeneficiariosINSSServicoLocal;
import br.com.sicoob.capes.processamento.negocio.servicos.interfaces.InclusaoBeneficiariosINSSServicoRemote;

/**
 * Serviço utilizado na inclusão de beneficiários do INSS no CAPES.
 * @author Erico.Junior
 */
@Stateless
@Local( { InclusaoBeneficiariosINSSServicoLocal.class })
@Remote( { InclusaoBeneficiariosINSSServicoRemote.class })
public class InclusaoBeneficiariosINSSServicoEJB extends CAPESProcessamentoServicoEJB implements
		InclusaoBeneficiariosINSSServicoRemote, InclusaoBeneficiariosINSSServicoLocal {

	/** O atributo delegate. */
	private final transient PessoaCompartilhamentoDelegate delegate = 
			CAPESCadastroFabricaDelegates.getInstance().criarPessoaCompartilhamentoDelegate();

	
	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void atualizarCadastroBeneficiario(BeneficiarioDTO dto, DateTimeDB dataProduto) throws BancoobException {

		PessoaFisica pessoa = incluirBeneficiario(dto, dataProduto, consultarPessoa(dto));
		InclusaoEnderecoBeneficiarioINSS.getInstance().incluir(dto, pessoa);
		InclusaoClienteBeneficiarioINSS.getInstance().incluir(dto, pessoa, dataProduto);
	}

	
	/**
	 * Consulta a pessoa a partir do cpf no compartilhamento de cadastro do qual a instituição pertence.
	 * @param dto DTO com o número do cpf e identificador da instituição.
	 * @return a pessoa a partir do cpf no compartilhamento de cadastro do qual a instituição pertence.
	 * @throws BancoobException caso ocorra alguma exceção.
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public PessoaFisica consultarPessoa(BeneficiarioDTO dto) throws BancoobException {
	
		PessoaFisica pessoa = null;
		
		try  {
			pessoa = (PessoaFisica) delegate.consultarPessoaPorDocumento(
					dto.getIdInstituicao(), dto.getNumCpf());
		} catch (PessoaNaoEncontradaException ex) {
			getLogger().alerta("Pessoa não encontrada.");
		}
		
		return pessoa;
	}	

	/**
	 * Inclui o beneficiário no cadastro de pessoas.
	 * @param dto O dto com os dados do beneficiário.
	 * @param dataProduto A data poduto INSS.
	 * @param pessoaFisica A pessoa física correspondente ao beneficiário se existir ou null caso contrário.
	 * @return A pessoa física incluída que representa o beneficiário. 
	 * @throws BeneficiarioINSSException Caso ocorra alguma exceção.
	 */
	private PessoaFisica incluirBeneficiario(BeneficiarioDTO dto, DateTimeDB dataProduto, PessoaFisica pessoaFisica) 
			throws BeneficiarioINSSException {
		
		PessoaFisica pessoaIncluida = null;
		try {
			
			validarCpf(dto.getNumCpf());

			RequisicaoBeneficiarioDTO requisicao = new RequisicaoBeneficiarioDTO();
			requisicao.setBeneficiario(dto);
			requisicao.setDataProduto(dataProduto);
			requisicao.setPessoa(pessoaFisica);
			
			pessoaIncluida = InclusaoBeneficiarioINSS.getInstance().incluir(requisicao); 
		} catch (FormatoInvalidoException e) {
			throw new CpfInvalidoException(e);	
		} catch (DataMaiorException e) {
			if(e.getMessage().startsWith("A Data de Nascimento")) {
				throw new DataNascimentoException(e);	
			}
			throw new InclusaoPessoaException(e);
		} catch (BancoobException e) {
			throw new InclusaoPessoaException(e);
		}
		
		return pessoaIncluida;
	}
	
	/**
	 * Valida se o cpf é válido.
	 * 
	 * @param cpf
	 *            O cpf para validação
	 * @throws FormatoInvalidoException
	 *             Caso o documento seja inválido.
	 */
	private void validarCpf(String cpf) throws FormatoInvalidoException {

		Validacao validacao = new ValidacaoCpf(cpf, "", "CPF");
		ResultadoValidacao resultado = validacao.executar();
		if (!resultado.isValido()) {
			throw new FormatoInvalidoException(resultado.getParametrosMensagem()[0]);
		}
	}
	
}
