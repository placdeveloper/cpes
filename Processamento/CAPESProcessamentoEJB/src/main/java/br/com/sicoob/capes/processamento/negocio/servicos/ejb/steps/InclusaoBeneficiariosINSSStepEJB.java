package br.com.sicoob.capes.processamento.negocio.servicos.ejb.steps;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.legado.Produto;
import br.com.sicoob.capes.processamento.negocio.dto.BeneficiarioDTO;
import br.com.sicoob.capes.processamento.negocio.dto.InconsistenciaContaDTO;
import br.com.sicoob.capes.processamento.negocio.enums.ProdutoEnum;
import br.com.sicoob.capes.processamento.negocio.excecao.BeneficiarioINSSException;
import br.com.sicoob.capes.processamento.negocio.servicos.interfaces.InclusaoBeneficiariosINSSServicoLocal;
import br.com.sicoob.capes.processamento.negocio.servicos.interfaces.ProdutoServicoLocal;
import br.com.sicoob.capes.processamento.persistencia.dao.FechamentoBeneficiariosINSSDao;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.execucao.RetornoExecucao;
import br.com.sicoob.sws.api.parametro.Parametro;
import br.com.sicoob.sws.api.servico.StepServico;

/**
 * Classe com o fluxo inclusão de beneficiários do INSS, antes executado pelo fechamento agendado.
 * 
 * <b>NOTA: Por dependência do grupo de fechamento da GESIN 1, esse fluxo não está sendo chamado.</b> 
 *
 * @author Bruno.Carneiro
 */
@Stateless
@Remote(StepServico.class)
public class InclusaoBeneficiariosINSSStepEJB extends CAPESStepBase {

	/** A constante LOTE_ORIGEM. */
	private static final int LOTE_ORIGEM = 20;

	@Inject
	@Default
	private transient FechamentoBeneficiariosINSSDao dao;

	/** O atributo servicoProduto. */
	@EJB
	private transient ProdutoServicoLocal servicoProduto;

	/** O atributo servicoBeneficiario. */
	@EJB
	private transient InclusaoBeneficiariosINSSServicoLocal servicoBeneficiario;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected RetornoExecucao executarStep(ContextoExecucao contexto) throws BancoobException {
		Integer numCooperativa = null;

		try {
			Parametro parametro = contexto.getParametrosStep().get("NUMERO_COOPERATIVA");

			numCooperativa = parametro.getValor();
			DateTimeDB dataProduto = recuperarDataProdutoINSS(numCooperativa);

			getLogger().info("FechamentoBeneficiariosINSSServicoEJB na cooperativa: " + numCooperativa + ", dataProduto: " + dataProduto);

			List<BeneficiarioDTO> beneficiarios = dao.listarBeneficiariosImportacao(numCooperativa, dataProduto);

			if (beneficiarios != null && !beneficiarios.isEmpty()) {
				for (BeneficiarioDTO dto : beneficiarios) {
					incluirCadastroBeneficiario(dto, dataProduto, numCooperativa);
				}
			}

			return sucesso();
		} catch (Exception e) { // NOPMD
			getLogger().erro(e, "Erro na inclusao de beneficiarios do INSS na cooperativa: " + numCooperativa);
			String mensagem = e.getMessage();
			if (mensagem == null) {
				mensagem = e.getCause().getMessage();
			}

			return erro(mensagem);
		}
	}

	/**
	 * Recupera a data do produto INSS.
	 * 
	 * @param numCooperativa
	 *            O número da cooperativa para conexão.
	 * @return a data do produto INSS.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	private DateTimeDB recuperarDataProdutoINSS(Integer numCooperativa) throws BancoobException {
		Produto produto = servicoProduto.obter(ProdutoEnum.PAGAMENTO_INSS.getCodigo(), numCooperativa);
		return produto.getDataAtualMovimento();
	}

	/**
	 * Inclui ou atualiza o cadastro do beneficiário na cooperativa. Se ocorrer alguma exceção de negócio, a aplicação grava o log de inconsistência.
	 * 
	 * @param dto
	 *            O dto com os dados do beneficiário.
	 * @param dataProduto
	 *            A data do produto pagamento de INSS.
	 * @param numCooperativa
	 *            O número da cooperativa.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção de sistema.
	 */
	private void incluirCadastroBeneficiario(BeneficiarioDTO dto, DateTimeDB dataProduto, Integer numCooperativa) throws BancoobException {
		try {
			getLogger().info("Incluindo beneficiario do inss: " + dto.getNumCpf() + ", cooperativa: " + numCooperativa);
			servicoBeneficiario.atualizarCadastroBeneficiario(dto, dataProduto);
		} catch (BeneficiarioINSSException e) {
			getLogger().erro(e, "Erro ao incluir o beneficiario: " + dto.getNumCpf() + " na instituicao:" + dto.getIdInstituicao());
			gravarLogInconsistencia(numCooperativa, dataProduto, e.getCodErro(), dto);
		}
	}

	/**
	 * Grava o log de inconsistência para erros no cadastro de beneficiários.
	 * 
	 * @param numCooperativa
	 *            O número da cooperativa para conexão.
	 * @param dataProduto
	 *            A data do produto INSS.
	 * @param numErro
	 *            O número do erro.
	 * @param dto
	 *            o dto com as informações do registro que deu erro.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção;
	 */
	public void gravarLogInconsistencia(Integer numCooperativa, DateTimeDB dataProduto, Integer numErro, BeneficiarioDTO dto) throws BancoobException {
		InconsistenciaContaDTO inconsistencia = new InconsistenciaContaDTO();
		inconsistencia.setCodLoteOrigem(LOTE_ORIGEM);
		inconsistencia.setDataProcessamento(dataProduto);
		inconsistencia.setNumBeneficiario(dto.getNumBeneficiario());
		inconsistencia.setNumErro(numErro);
		inconsistencia.setNumTrabalhador(dto.getNumTrabalhador());
		dao.gravarLogInconsistenciaBeneficiario(numCooperativa, inconsistencia);
	}

}