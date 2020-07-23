package br.com.sicoob.capes.frontoffice.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.srtb.dto.Mensagem;
import br.com.bancoob.srtb.dto.Parametro;
import br.com.bancoob.srtb.dto.RetornoMensagem;
import br.com.bancoob.srtb.excecao.ExcecaoTransacao;
import br.com.bancoob.srtb.montagemconteudo.objeto.definicao.RetornoTransacaoObjeto;
import br.com.sicoob.capes.api.inclusao.negocio.delegates.CAPESApiInclusaoDelegate;
import br.com.sicoob.capes.api.inclusao.negocio.dto.EncaminharAutorizacaoDTO;
import br.com.sicoob.capes.api.inclusao.negocio.excecao.RegistroPendenteAprovacaoException;
import br.com.sicoob.capes.api.negocio.vo.PessoaVO;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.comum.negocio.vo.TipoDocumentoVO;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.frontoffice.infraestrutura.mensagens.CAPESFrontofficeResourceBundle;
import br.com.sicoob.capes.frontoffice.negocio.excecao.ValidacaoNegocialException;
import br.com.sicoob.capes.frontoffice.negocio.util.MensagemUtil;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.GEDIntegracaoDelegate;
import br.com.sicoob.capes.integracao.negocio.vo.DocumentoGEDVO;
import br.com.sicoob.capes.integracao.negocio.vo.PaginaDocumentoGEDVO;
import br.com.sicoob.capes.integracao.negocio.vo.ValorChaveDocumentoGEDVO;

/**
 * Classe base para os serviços que utilizam documento e precisam de aprovação
 * 
 * @author Bruno.Carneiro
 */
public abstract class ServicosComprovaveisEJB extends CAPESTransacaoServicoEJB {
	
	private static final String CHAVE_MENSAGEM_REGISTRO_APROVACAO = "MN0002";
	
	@Override
	public RetornoMensagem executarTransacao(Mensagem mensagem) throws ExcecaoTransacao {
		RetornoMensagem retornoMensagem = null;
		
		try {
			Map<String, Parametro> parametros = MensagemUtil.recuperarParametros(mensagem);
			validar(parametros);
			RetornoTransacaoObjeto retornoTransacao = executarTransacao(mensagem, parametros);

			getLogger().info("Transacao executada com sucesso");

			retornoMensagem = criarConteudoRetornoSucesso(retornoTransacao, mensagem);

			getLogger().info("[CAPES] RetornoMensagemSucesso: " + ReflectionToStringBuilder.toString(retornoMensagem, ToStringStyle.MULTI_LINE_STYLE));
		} catch (ValidacaoNegocialException e) {
			getLogger().info("[ValidacaoException] - Erro na transacao do CAPES.");
			retornoMensagem = obterRetornoMensagemErro(e.getMessage(), RetornoMensagem.ERRO_VALIDACAO);
			retornoMensagem.setCampoErro(e.getNomeCampo());
			getLogger().info("[ValidacaoException] - " + ReflectionToStringBuilder.toString(retornoMensagem, ToStringStyle.MULTI_LINE_STYLE));
		} catch (RegistroPendenteAprovacaoException e) {
			getLogger().info("[NegocioException] - Erro na transacao do CAPES.");
			retornoMensagem = obterRetornoMensagemErro(CAPESFrontofficeResourceBundle.getInstance().getString(CHAVE_MENSAGEM_REGISTRO_APROVACAO), RetornoMensagem.ERRO_NEGOCIO);
			getLogger().info("[NegocioException] - " + ReflectionToStringBuilder.toString(retornoMensagem, ToStringStyle.MULTI_LINE_STYLE));
		} catch (NegocioException e) {
			getLogger().info("[NegocioException] - Erro na transacao do CAPES.");
			retornoMensagem = obterRetornoMensagemErro(e.getMessage(), RetornoMensagem.ERRO_NEGOCIO);
			getLogger().info("[NegocioException] - " + ReflectionToStringBuilder.toString(retornoMensagem, ToStringStyle.MULTI_LINE_STYLE));
		} catch (BancoobException e) {
			getLogger().erro(e, "[BancoobException] - Erro na transacao do CAPES.");
			retornoMensagem = obterRetornoMensagemErro(e.getMessage(), RetornoMensagem.ERRO_EXECUCAO);
		} catch (BancoobRuntimeException e) {
			getLogger().erro(e, "[BancoobRuntimeException] - Erro na transacao do CAPES.");
			retornoMensagem = obterRetornoMensagemErro(e.getMessage(), RetornoMensagem.ERRO_EXECUCAO);
		}
		return retornoMensagem;
	}

	protected void encaminharFluxo(Integer idPessoa, Integer idInstituicao, Integer idUnidadeInst, Long idRegistro, Integer idInstituicaoRegistro, String idUsuario) throws BancoobException {
		getLogger().debug("Encaminhando o fluxo da pessoa: ", String.valueOf(idPessoa), " para a instituicao: ", String.valueOf(idInstituicao),
				"idUnidadeInst: ", String.valueOf(idUnidadeInst), " idRegistro: ", String.valueOf(idRegistro), 
				"idInstituicaoRegistro: ", String.valueOf(idInstituicaoRegistro), " idUsuario: ", String.valueOf(idUsuario));
		EncaminharAutorizacaoDTO dto = new EncaminharAutorizacaoDTO();
		dto.setIdPessoa(idPessoa);
		dto.setIdInstituicao(idInstituicao);
		dto.setIdUnidadeInst(idUnidadeInst);
		dto.setIdInstituicaoRegistro(idInstituicaoRegistro);
		dto.setIdRegistro(idRegistro);
		dto.setIdUsuarioAprovacao(idUsuario);
		obterDelegateInclusao().encaminharAutorizacao(dto);
	}
	
	protected List<Long> obterDocumentosComprobatorios(byte[] documento, String nomeArquivoDocumentoComprobatorio, String extensaoArquivoDocumentoComprobatorio, 
			List<ValorChaveDocumentoGEDVO> chavesDocumento, PessoaVO pessoa, String idUsuario) throws BancoobException {
		
		TipoPessoaEnum tipoPessoa = TipoPessoaEnum.valueOf(pessoa.getCodTipoPessoa());
		getLogger().debug("Obtendo os documentos comprobatorios para a sigla assunto: ", tipoPessoa.getSiglaAssuntoGED());
		List<Long> idsDocumentos = null;
		List<TipoDocumentoVO> listaTiposDocumento = obterTiposDocumentoPorSiglaAssunto(tipoPessoa.getSiglaAssuntoGED(), obterSiglaClasseDocumento());
		if (listaTiposDocumento != null && !listaTiposDocumento.isEmpty()) {
			TipoDocumentoVO tipoDocumento = listaTiposDocumento.get(0);

			idsDocumentos = enviarDocumentoGED(tipoDocumento.getId(), documento, nomeArquivoDocumentoComprobatorio, extensaoArquivoDocumentoComprobatorio,
					chavesDocumento, pessoa.getIdInstituicao(), pessoa.getIdUnidadeInst(), idUsuario);
		}
		return idsDocumentos;
	}

	protected abstract String obterSiglaClasseDocumento();

	private List<TipoDocumentoVO> obterTiposDocumentoPorSiglaAssunto(String siglaAssunto, String siglaClasseDocumento) throws BancoobException {
		getLogger().debug("Obtendo os tipos de documento pela siglaAssunto: ", siglaAssunto, " e siglaClasseDocumento: ", siglaClasseDocumento);
		List<TipoDocumentoVO> tiposDocumento = CAPESIntegracaoFabricaDelegates.getInstance().criarGEDIntegracaoDelegate().obterTiposDocumentoPorSiglaClasseDocumento(siglaAssunto, siglaClasseDocumento);
		getLogger().debug("Quantidade de tipos de documentos recebidos: ", String.valueOf(tiposDocumento != null ? tiposDocumento.size() : 0));
		return tiposDocumento;
	}

	protected List<Long> enviarDocumentoGED(Integer idDocumento, byte[] documento, String nomeArquivo, String extensaoArquivo,
			List<ValorChaveDocumentoGEDVO> listaValorChave, Integer idInstituicao, Integer idUnidadeInst, String idUsuarioAprovacao) throws BancoobException {
		
		getLogger().debug("Enviando os documentos para o GED. idDocumento: ", String.valueOf(idDocumento), " nomeArquivo: ", nomeArquivo,
				"extensao arquivo: ", extensaoArquivo, " listaValoresChave: ", String.valueOf(listaValorChave), 
				"idInstituicao: ", String.valueOf(idInstituicao), "idUnidadeInst: ", String.valueOf(idUnidadeInst), " idUsuario: ", String.valueOf(idUsuarioAprovacao));
		
		PaginaDocumentoGEDVO paginaDocumento = new PaginaDocumentoGEDVO();
		paginaDocumento.setNomeArquivo(nomeArquivo);
		paginaDocumento.setDescricaoExtensao(extensaoArquivo);
		paginaDocumento.setValorBinario(documento);

		List<PaginaDocumentoGEDVO> paginas = new ArrayList<PaginaDocumentoGEDVO>();
		paginas.add(paginaDocumento);

		DocumentoGEDVO documentoGED = new DocumentoGEDVO();
		documentoGED.setIdTipoDocumento(idDocumento);
		documentoGED.setPaginas(paginas);
		documentoGED.setIdInstituicao(idInstituicao);
		documentoGED.setIdUnidadeInstituicao(idUnidadeInst);
		documentoGED.setIdUsuarioInclusao(idUsuarioAprovacao);
		documentoGED.setIdModulo(Constantes.Negocio.CTA_ID_MODULO);
		documentoGED.setIdSistema(Constantes.Negocio.CTA_ID_SISTEMA.shortValue());
		documentoGED.setListaValorChave(listaValorChave);

		List<DocumentoGEDVO> documentosVO = new ArrayList<DocumentoGEDVO>();
		documentosVO.add(documentoGED);
		GEDIntegracaoDelegate delegate = CAPESIntegracaoFabricaDelegates.getInstance().criarGEDIntegracaoDelegate();
		return delegate.enviarDocumentosGED(documentosVO);
	}
	
	protected abstract CAPESApiInclusaoDelegate<?, ?> obterDelegateInclusao();
	
}