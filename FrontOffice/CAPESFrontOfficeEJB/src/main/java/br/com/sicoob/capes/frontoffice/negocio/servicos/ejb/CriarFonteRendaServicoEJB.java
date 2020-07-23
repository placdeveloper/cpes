package br.com.sicoob.capes.frontoffice.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.srtb.dto.Mensagem;
import br.com.bancoob.srtb.dto.Parametro;
import br.com.bancoob.srtb.montagemconteudo.objeto.definicao.Resultado;
import br.com.bancoob.srtb.montagemconteudo.objeto.definicao.RetornoTransacaoObjeto;
import br.com.bancoob.srtb.servico.Transacao;
import br.com.sicoob.capes.api.inclusao.negocio.delegates.CAPESApiInclusaoFabricaDelegates;
import br.com.sicoob.capes.api.inclusao.negocio.delegates.FonteRendaDelegate;
import br.com.sicoob.capes.api.inclusao.negocio.dto.EncaminharAutorizacaoDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.FonteRendaDTO;
import br.com.sicoob.capes.api.negocio.delegates.CAPESApiFabricaDelegates;
import br.com.sicoob.capes.api.negocio.delegates.PessoaDelegate;
import br.com.sicoob.capes.api.negocio.vo.PessoaVO;
import br.com.sicoob.capes.api.negocio.vo.TipoFonteRendaVO;
import br.com.sicoob.capes.comum.negocio.enums.TipoFonteRendaEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.frontoffice.negocio.dto.ConsultaFonteRendaDTO;
import br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum;
import br.com.sicoob.capes.frontoffice.negocio.util.ParametroUtil;
import br.com.sicoob.capes.frontoffice.negocio.validacao.Validador;
import br.com.sicoob.capes.frontoffice.negocio.validacao.ValidadorCampoObrigatorio;
import br.com.sicoob.capes.integracao.negocio.vo.ValorChaveDocumentoGEDVO;

@Stateless
@Remote({ Transacao.class })
public class CriarFonteRendaServicoEJB extends ServicosComprovaveisEJB {
	
	@Override
	protected List<Validador> getValidadores(Map<String, Parametro> parametros) {
		List<Validador> validadores = new ArrayList<Validador>();
		validadores.add(new ValidadorCampoObrigatorio<Object>(ParametroEnum.NUMERO_PESSOA.getRotulo(), parametros.get(ParametroEnum.NUMERO_PESSOA.getRotulo()).getValor()));
		validadores.add(new ValidadorCampoObrigatorio<Object>(ParametroEnum.NUMERO_COOP_REMETENTE.getRotulo(), parametros.get(ParametroEnum.NUMERO_COOP_REMETENTE.getRotulo()).getValor()));
		validadores.add(new ValidadorCampoObrigatorio<Object>(ParametroEnum.COD_TIPO_RENDA.getRotulo(), parametros.get(ParametroEnum.COD_TIPO_RENDA.getRotulo()).getValor()));
		return validadores;
	}

	@Override
	protected RetornoTransacaoObjeto executarTransacao(Mensagem mensagem, Map<String, Parametro> parametros) throws BancoobException {
		Integer idPessoaLegado = ParametroUtil.recuperarValorParametro(parametros, ParametroEnum.NUMERO_PESSOA, Integer.class);
		Integer numCooperativa = ParametroUtil.recuperarValorParametro(parametros, ParametroEnum.NUMERO_COOP_REMETENTE, Integer.class);
		
		String idUsuario = obterUsuarioCanal(mensagem.getCodigoCanal());

		Short codTipoFonteRenda = ParametroUtil.recuperarValorParametro(parametros, ParametroEnum.COD_TIPO_RENDA, Short.class);
		BigDecimal valorReceitaMensal = ParametroUtil.recuperarValorParametro(parametros, ParametroEnum.VALOR_TRANSACAO, BigDecimal.class);
		
		String documentoComprobatorio = ParametroUtil.recuperarValorParametro(parametros, ParametroEnum.DOCUMENTO_COMPROBATORIO, String.class);
		String nomeArquivoDocumentoComprobatorio = ParametroUtil.recuperarValorParametro(parametros, ParametroEnum.NOME_ARQUIVO_DOCUMENTO_COMPROBATORIO, String.class);
		String extensaoArquivoDocumentoComprobatorio = ParametroUtil.recuperarValorParametro(parametros, ParametroEnum.EXTENSAO_ARQUIVO_DOCUMENTO_COMPROBATORIO, String.class);

		Integer idInstituicao = obterIdInstituicao(mensagem.getIdInstituicao(), numCooperativa);
		PessoaDelegate pessoaDelegate = CAPESApiFabricaDelegates.getInstance().criarPessoaDelegate();
		PessoaVO pessoa = pessoaDelegate.obterPorPessoaLegadoInstituicao(idPessoaLegado, idInstituicao);
		
		if (pessoa == null) {
			throw new NegocioException("Pessoa não encontrada");
		}
		
		TipoFonteRendaVO tipoRenda = CAPESApiFabricaDelegates.getInstance().criarTipoFonteRendaDelegate().obterTipoFonteRenda(codTipoFonteRenda);
		if (tipoRenda != null && tipoRenda.getValorObrigatorio()) {
			if (valorReceitaMensal == null) {
				throw new NegocioException("O valor é obrigatório para o tipo de renda informado.");
			}

			if (tipoRendaObrigaDocumento(tipoRenda.getCodigo()) && (StringUtils.isEmpty(documentoComprobatorio) || StringUtils.isEmpty(nomeArquivoDocumentoComprobatorio) || StringUtils.isEmpty(extensaoArquivoDocumentoComprobatorio))) {
				throw new NegocioException("O documento é obrigatório para o tipo de renda informado.");
			}
		}
		
		getLogger().debug("Arquivo recebido: ", String.valueOf(StringUtils.isNotBlank(documentoComprobatorio)), " nome do arquivo: ",
				nomeArquivoDocumentoComprobatorio, " extensao do arquivo: ", extensaoArquivoDocumentoComprobatorio);
		
		List<Long> idsDocumentos = null;
		if(!StringUtils.isEmpty(documentoComprobatorio)) {
			getLogger().debug("Documento comprobatorio recebido: ", documentoComprobatorio);
			byte[] documento = Base64.decodeBase64(documentoComprobatorio.getBytes());
			getLogger().debug("Documento comprobatorio convertido para bytes: ", String.valueOf(documento));
			
			// Cria as chaves de negócio
			TipoPessoaEnum tipoPessoa = TipoPessoaEnum.valueOf(pessoa.getCodTipoPessoa());
			List<ValorChaveDocumentoGEDVO> chavesDocumento = new ArrayList<ValorChaveDocumentoGEDVO>();
			chavesDocumento.add(new ValorChaveDocumentoGEDVO(Constantes.Negocio.GED_SIGLA_CHAVE_DOCUMENTO_GRUPO_COMPARTILHAMENTO, String.valueOf(pessoa.getCodigoCompartilhamentoCadastro())));
			chavesDocumento.add(new ValorChaveDocumentoGEDVO(Constantes.Negocio.GED_SIGLA_CHAVE_DOCUMENTO_TIPO_RENDIMENTO, String.valueOf(codTipoFonteRenda)));
			chavesDocumento.add(new ValorChaveDocumentoGEDVO(TipoPessoaEnum.isPessoaFisica(tipoPessoa.getCodigo()) ? Constantes.Negocio.GED_SIGLA_CHAVE_DOCUMENTO_PF : Constantes.Negocio.GED_SIGLA_CHAVE_DOCUMENTO_PJ, pessoa.getCpfCnpj()));
			
			// Envia os documentos ao GED e obtém o id do documento.
			idsDocumentos = obterDocumentosComprobatorios(documento, nomeArquivoDocumentoComprobatorio, extensaoArquivoDocumentoComprobatorio, chavesDocumento, pessoa, idUsuario);
			
			getLogger().debug("Quantidade de ids de documentos recebidos: ", String.valueOf(idsDocumentos != null ? idsDocumentos.size() : 0));
		}
		
		FonteRendaDTO dto = new FonteRendaDTO();
		dto.setIdPessoa(pessoa.getIdPessoa());
		dto.setIdInstituicao(pessoa.getIdInstituicao());
		dto.setIdUnidadeInst(pessoa.getIdUnidadeInst());
		dto.setCodigoTipoFonteRenda(codTipoFonteRenda);
		dto.setValorReceitaBrutaMensal(valorReceitaMensal);
		dto.setIdUsuarioAprovacao(idUsuario);
		dto.setIdsDocumentos(idsDocumentos);

		FonteRendaDTO retorno = incluirFonteRenda(dto);
		Resultado<ConsultaFonteRendaDTO> resultado = new Resultado<ConsultaFonteRendaDTO>();
		if (retorno.getIdFonteRenda() != null) {
			encaminharFluxo(pessoa.getIdPessoa(), pessoa.getIdInstituicao(), pessoa.getIdUnidadeInst(), retorno.getIdFonteRenda(), pessoa.getIdInstituicao(), mensagem.getCodigoCanal());
			ConsultaFonteRendaDTO dtoRetorno = new ConsultaFonteRendaDTO();
			dtoRetorno.setIdFonteRenda(retorno.getIdFonteRenda());
			resultado.add(dtoRetorno);
		}
		
		RetornoTransacaoObjeto retornoTransacao = new RetornoTransacaoObjeto();
		retornoTransacao.add(resultado);
		return retornoTransacao;
	}
	
	private boolean tipoRendaObrigaDocumento(Short codigoTipoRenda) {
		boolean retorno = true;
		TipoFonteRendaEnum tipoFonteRenda = TipoFonteRendaEnum.obterPorCodigo(codigoTipoRenda);

		if (TipoFonteRendaEnum.NAO_POSSUI_RENDA.equals(tipoFonteRenda) 
				|| TipoFonteRendaEnum.RENDIMENTO_NAO_COMPROVADO.equals(tipoFonteRenda)
				|| TipoFonteRendaEnum.MESADA.equals(tipoFonteRenda)) {
			retorno = false;
		}
		
		getLogger().debug("Tipo de renda: ", tipoFonteRenda.getDescricao(), ", documento obrigatorio: ", String.valueOf(retorno));
		
		return retorno;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public FonteRendaDTO incluirFonteRenda(FonteRendaDTO dto) throws BancoobException {
		return CAPESApiInclusaoFabricaDelegates.obterInstancia().criarFonteRendaDelegate().incluir(dto);
	}

	private void encaminharFluxo(Integer idPessoa, Integer idInstituicao, Integer idUnidadeInst, Long idRegistro, Integer idInstituicaoRegistro, Short codigoCanal) throws BancoobException {
		EncaminharAutorizacaoDTO dto = new EncaminharAutorizacaoDTO();
		dto.setIdPessoa(idPessoa);
		dto.setIdInstituicao(idInstituicao);
		dto.setIdUnidadeInst(idUnidadeInst);
		dto.setIdInstituicaoRegistro(idInstituicaoRegistro);
		dto.setIdRegistro(idRegistro);
		dto.setIdUsuarioAprovacao(obterUsuarioCanal(codigoCanal));
		obterDelegateInclusao().encaminharAutorizacao(dto);
	}

	@Override
	protected String obterSiglaClasseDocumento() {
		return "RENDA";
	}

	@Override
	protected FonteRendaDelegate obterDelegateInclusao() {
		return CAPESApiInclusaoFabricaDelegates.obterInstancia().criarFonteRendaDelegate();
	}

}