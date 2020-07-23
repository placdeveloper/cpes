package br.com.sicoob.capes.api.inclusao.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.negocio.servicos.ejb.BancoobServicoEJB;
import br.com.sicoob.capes.api.inclusao.negocio.dto.BemMovelDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.FonteRendaDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.PlataformaContabilDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.interfaces.BemServicoLocal;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.interfaces.FonteRendaServicoLocal;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.interfaces.PlataformaContabilServicoLocal;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.interfaces.PlataformaContabilServicoRemote;
import br.com.sicoob.capes.api.negocio.delegates.BemMovelDelegate;
import br.com.sicoob.capes.api.negocio.delegates.CAPESApiFabricaDelegates;
import br.com.sicoob.capes.api.negocio.delegates.FonteRendaPessoaDelegate;
import br.com.sicoob.capes.api.negocio.delegates.PessoaDelegate;
import br.com.sicoob.capes.api.negocio.vo.BemMovelVO;
import br.com.sicoob.capes.api.negocio.vo.FonteRendaPessoaVO;
import br.com.sicoob.capes.api.negocio.vo.PessoaVO;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoBemMovelEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoClassificacaoBemEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoFonteRendaEnum;
import br.com.sicoob.capes.comum.negocio.vo.InstituicaoVO;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;

/**
 * EJB contendo servicos relacionados a Fonte de Renda e Bem patrimonio, voltado para a plataforma contabil.
 * 
 * @author valdemar.xavier
 */
@Stateless
@Local(PlataformaContabilServicoLocal.class)
@Remote(PlataformaContabilServicoRemote.class)
public class PlataformaContabilServicoEJB extends BancoobServicoEJB implements PlataformaContabilServicoLocal, PlataformaContabilServicoRemote {

	
	/** O atributo SCI. delegateSCI Capes. */
	private SCIIntegracaoDelegate delegateSCI = CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();
	
	
	/** O atributo FonteRenda. API Inclusão. */
	@EJB(mappedName = "capes/api/inclusao/FonteRendaServico")
	private FonteRendaServicoLocal fonteRendaInclusao;
	
	/** O atributo Bem.  API Inclusão. */
	@EJB(mappedName = "capes/api/inclusao/BemServico")
	private BemServicoLocal bemInclusao;
	

	/** O atributo servicoPessoa API. **/
	private PessoaDelegate servicoPessoa = CAPESApiFabricaDelegates.getInstance().criarPessoaDelegate();
	
	/** O atributo servicoFonteRenda API. **/
	private FonteRendaPessoaDelegate servicoFonteRenda = CAPESApiFabricaDelegates.getInstance().criarFonteRendaPessoaDelegate();
	
	/** O atributo servicoBemPessoa API. **/
	private BemMovelDelegate servicoBemPessoa = CAPESApiFabricaDelegates.getInstance().criarBemMovelDelegate();
	
	/** Constante quantidade de meses ano **/
	final BigDecimal ANO = new BigDecimal(12);
	
	/** Constante id Unidade Instituicao **/
	final Integer idUnidadeInst = Integer.valueOf(0);
	
	
	/**
	 * Atualiza os valores de patrimonio e faturamento da instituicao.
	 **/
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public boolean atualizarDadosContabil(PlataformaContabilDTO dto) throws BancoobException { 
		getLogger().debug("IdInstituição de atualização de Patrimonio e Faturamento: " + dto.getIdInstituicao());
		getLogger().debug("Valor de atualização de Patrimônio: " + dto.getValorPatrimonio());
		getLogger().debug("Valor de atualização de Faturamento: " + dto.getValorFaturamento());
		
		InstituicaoVO instSci = delegateSCI.obterInformcoesGeraisInstituicaoSCI(dto.getIdInstituicao(), idUnidadeInst);
		
		if(instSci == null) {
			getLogger().debug("O idInstituição informada  " + dto.getIdInstituicao() + " não foi encontrado no SCI.");
			throw new NegocioException("O idInstituição informada  " + dto.getIdInstituicao() + " não foi encontrado no SCI.");
		}
		
		if(instSci.getCnpj() == null) {
			getLogger().debug("O idInstituição informada: " + dto.getIdInstituicao() + " não possui cnpj na unidade zero.");
			throw new NegocioException("O idInstituição informada: " + dto.getIdInstituicao() + " não possui cnpj na unidade zero.");
		}
		
		PessoaVO pessoa = servicoPessoa.obterPorCpfCnpjInstituicao(instSci.getCnpj(), dto.getIdInstituicao());
		
		if(pessoa == null) {
			getLogger().debug("A instituição informada não foi encontrada no sistema Capes. IdInstituição: " + dto.getIdInstituicao() + " CNPJ: " + instSci.getCnpj());
			throw new NegocioException("A instituição informada não foi encontrada no sistema Capes. IdInstituição: " + dto.getIdInstituicao() + " CNPJ: " + instSci.getCnpj());
		}
		
		try {
			
			atualizarFaturamento(dto, pessoa.getIdPessoa());
			atualizarPatrimonio(dto, pessoa.getIdPessoa());
		
		}catch (BancoobException e) {
			getLogger().debug(e.getMessage() + ": Com idInstituição: " + dto.getIdInstituicao() + " e CNPJ: " + pessoa.getCpfCnpj());
			throw new NegocioException(e.getMessage());
		}
		
		getLogger().debug("Atualizado com sucesso valorPatrimonio e valorFaturamento da cooperativa com idInstituição: " + dto.getIdInstituicao() + " e CNPJ: " + pessoa.getCpfCnpj());
		
		return true;
	}

	/**
	 * Atualiza o faturamento da instituição
	 * @param dto
	 * @param pessoCompartilhamento 
	 * @throws BancoobException
	 */
	private void atualizarFaturamento(PlataformaContabilDTO dto, Integer idPessoa) throws BancoobException {
		FonteRendaDTO fonteRenda = new FonteRendaDTO();
		fonteRenda.setIdPessoa(idPessoa); 
		fonteRenda.setIdInstituicao(dto.getIdInstituicao());
		fonteRenda.setIdUnidadeInst(0);
		fonteRenda.setIdUsuarioAprovacao("CAPESINCLUI0300_00");
		fonteRenda.setCodigoTipoFonteRenda(TipoFonteRendaEnum.FATURAMENTO.getCodigo());
		fonteRenda.setDescFonteRenda("FATURAMENTO MÉDIO MENSAL");
		fonteRenda.setVerificarAutorizacao(false);
		fonteRenda.setValorReceitaBrutaMensal(dto.getValorFaturamento().divide(ANO,RoundingMode.HALF_EVEN));
		
		FonteRendaPessoaVO fonteFaturamento = obterFonteRendaFaturamento(idPessoa, dto.getIdInstituicao());

		if(fonteFaturamento == null){ 
			fonteRendaInclusao.incluir(fonteRenda);	
		}else {
			fonteRenda.setId(fonteFaturamento.getIdFonteRenda());
			fonteRendaInclusao.alterar(fonteRenda);
		}
	}
	
	/**
	 * Verifica se atualiza ou inclui a fonte de renda.
	 * @throws BancoobException 
	 */
	private FonteRendaPessoaVO obterFonteRendaFaturamento(Integer idPessoa, Integer idInstituicao) throws BancoobException{
		FonteRendaPessoaVO retorno = null;
		
		List<FonteRendaPessoaVO> listResult = servicoFonteRenda.obterPorPessoaInstituicao(idPessoa, idInstituicao);

		if(!listResult.isEmpty()) {
			for (FonteRendaPessoaVO renda : listResult) {
				if(renda.getCodigoTipoFonteRenda().equals(TipoFonteRendaEnum.FATURAMENTO.getCodigo())) {
					return renda;
				}
			}
		}
	
		return retorno;
	}
	
	/**
	 * Atualiza o patrimonio da instituição
	 * @param dto
	 * @param pessoCompartilhamento2 
	 * @throws BancoobException
	 */
	private void atualizarPatrimonio(PlataformaContabilDTO dto, Integer idPessoa) throws BancoobException {

		BemMovelDTO bemPessoa = new BemMovelDTO();
		
		bemPessoa.setIdPessoa(idPessoa); 
		bemPessoa.setIdInstituicao(dto.getIdInstituicao());
		bemPessoa.setIdUnidadeInst(0);
		bemPessoa.setIdUsuarioAprovacao("CAPESINCLUI0300_00");
		bemPessoa.setCodigoTipoClassificacaoBem(TipoClassificacaoBemEnum.BEM_MOVEL.getCodigo());
		bemPessoa.setDescricao("IMÓVEIS/MÓVEIS E EQUIPAMENTOS DE USO");
		bemPessoa.setValor(BigDecimal.valueOf(0));
		bemPessoa.setCodigoTipoBem(TipoBemMovelEnum.MAQUINA_EQUIPAMENTO.getCodigo());
		bemPessoa.setValor(dto.getValorPatrimonio());
		bemPessoa.setVerificarAutorizacao(false);
		
		BemMovelVO bemImovelEquipamentosUso = isAtualizarPatrimonio(idPessoa, dto.getIdInstituicao());
		if(bemImovelEquipamentosUso == null) {
			bemInclusao.incluir(bemPessoa);
		}else {
			bemPessoa.setId(bemImovelEquipamentosUso.getIdBem());
			bemInclusao.alterar(bemPessoa);
		}
	}

	/**
	 * Verifica se atualiza ou inclui o bem.
	 * @throws BancoobException 
	 */
	private BemMovelVO isAtualizarPatrimonio(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		BemMovelVO retorno = null;
		
		List<BemMovelVO> resultList = servicoBemPessoa.obterPorPessoaInstituicao(idPessoa, idInstituicao);
		
		if(!resultList.isEmpty()) {
			for (BemMovelVO bemPessoa : resultList) {
				if(bemPessoa.getDescricao().equals("IMÓVEIS/MÓVEIS E EQUIPAMENTOS DE USO")) {
					return bemPessoa;
				}
			}
		}
		
		return retorno;
	}

}