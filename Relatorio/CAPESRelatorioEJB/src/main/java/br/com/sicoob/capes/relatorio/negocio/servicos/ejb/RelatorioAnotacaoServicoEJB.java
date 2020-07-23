/* 
 * Sicoob
 * RelatorioAnotacaoServicoEJB.java 
 * Criado em: 30/06/2011
 */
package br.com.sicoob.capes.relatorio.negocio.servicos.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.capes.cadastro.negocio.delegates.AnotacaoDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.dominio.GrupoCompartilhamentoCache;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaAnotacaoDTO;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.comum.negocio.vo.InstituicaoVO;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.Anotacao;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.TipoAnotacao;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaJuridica;
import br.com.sicoob.capes.relatorio.negocio.relatorios.RelatorioAnotacao;
import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO;
import br.com.sicoob.relatorio.api.dto.RetornoProcessamentoRelatorioDTO;
import br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico;

/**
 * Implementa as operações do serviço de relatório de anotações
 *
 * 30/06/2011
 * @author Rodrigo.Chaves
 * 
 */
@Stateless
@Remote( { IProcessamentoRelatorioServico.class })
public class RelatorioAnotacaoServicoEJB extends CAPESRelatorioServicoEJB implements IProcessamentoRelatorioServico{

	/**
	 * Recupera a instituição a partir do ID informado.
	 * 
	 * @param idInstituicao
	 *            O ID da instituição.
	 * 
	 * @return A instituição
	 */
	private InstituicaoVO obterInstituicao(Integer idInstituicao) throws BancoobException {
		SCIIntegracaoDelegate sciIntegracaoDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();
		InstituicaoVO instituicao = sciIntegracaoDelegate.obterInstituicaoPorId(idInstituicao);
		return instituicao;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RetornoProcessamentoRelatorioDTO gerarRelatorio(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
		Number idTipoPessoa = (Number) dto.getDados().get("idTipoPessoa");
		Number idTipoAnotacao = (Number) dto.getDados().get("idTipoAnotacao");
		String nomeTipoPessoa = (String) dto.getDados().get("nomeTipoPessoa");
		String nomeTipoanotacao = (String) dto.getDados().get("nomeTipoAnotacao");
		String cpfCnpj = (String) dto.getDados().get("cpfCnpj");

		PessoaCompartilhamento pessoaCompartilhamento = null;
		if (idTipoPessoa != null) {
			if (TipoPessoaEnum.PESSOA_FISICA.getCodigo().equals(idTipoPessoa.shortValue())) {
				pessoaCompartilhamento = new PessoaFisica();
			} else if (TipoPessoaEnum.PESSOA_JURIDICA.getCodigo().equals(idTipoPessoa.shortValue())) {
				pessoaCompartilhamento = new PessoaJuridica();
			}

			TipoPessoa tipoPessoa = new TipoPessoa();
			tipoPessoa.setCodTipoPessoa(idTipoPessoa.shortValue());
			tipoPessoa.setDescTipoPessoa(nomeTipoPessoa);
			Pessoa pessoa = new Pessoa();
			pessoa.setCpfCnpj(cpfCnpj);
			pessoa.setTipoPessoa(tipoPessoa);
			
			if(pessoaCompartilhamento != null) {
				pessoaCompartilhamento.setPessoa(pessoa);
			}
		}

		TipoAnotacao tipoAnotacao = null;
		if (idTipoAnotacao != null) {
			tipoAnotacao = new TipoAnotacao();
			tipoAnotacao.setCodTipoAnotacao(idTipoAnotacao.intValue());
			tipoAnotacao.setDescTipoAnotacao(nomeTipoanotacao);
		}

		Anotacao anotacao = new Anotacao();
		anotacao.setPessoaCompartilhamento(pessoaCompartilhamento);
		anotacao.setTipoAnotacao(tipoAnotacao);

		ConsultaAnotacaoDTO consulta = new ConsultaAnotacaoDTO();
		consulta.setAnotacoesBaixadas((Boolean) dto.getDados().get("baixadas"));
		Date dataInicio = (Date) dto.getDados().get("dataInicio");
		if (dataInicio != null) {
			consulta.setDataInicio(DataUtil.configurarPrimeiraDataIntervalo(dataInicio));
		}
		Date dataFim = (Date) dto.getDados().get("dataFim");
		if (dataFim != null) {
			consulta.setDataFim(DataUtil.configurarSegundaDataIntervalo(dataFim));
		}

		UsuarioBancoob usuario = obterUsuario();
		Integer idInstituicao = Integer.valueOf(usuario.getIdInstituicao());
		consulta.setCodigoCompartilhamento(GrupoCompartilhamentoCache.getInstance().recuperarCodigoCompartilhamento(idInstituicao));

		if ((anotacao.getPessoaCompartilhamento() != null) && StringUtils.isNotEmpty(anotacao.getPessoaCompartilhamento().getPessoa().getCpfCnpj())) {
			anotacao.setTipoAnotacao(null);
		} else {
			consulta.setIdInstituicaoPessoa(idInstituicao);
			consulta.setAnotacoesBaixadas(false);
		}
		
		consulta.setFiltro(anotacao);

		CAPESCadastroFabricaDelegates fabrica = CAPESCadastroFabricaDelegates.getInstance();
		AnotacaoDelegate delegate = fabrica.criarAnotacaoDelegate();
		List<Anotacao> anotacoes = delegate.listarAnotacoesParaRelatorio(consulta);

		if (anotacoes == null || anotacoes.isEmpty()) {
			throw new NegocioException("Não há dados para a geração do relatório.");
		}

		InstituicaoVO instituicao = obterInstituicao(idInstituicao);
		Integer numeroCooperativa = instituicao.getNumero();
		String siglaCooperativa = instituicao.getSiglaInstituicao();
		RelatorioAnotacao relatorioAnotacao = new RelatorioAnotacao(consulta, anotacoes, numeroCooperativa, siglaCooperativa);
		
		return relatorioAnotacao.gerarRelatorio(rDto);
	}

}