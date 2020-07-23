package br.com.sicoob.capes.frontoffice.negocio.servicos.ejb;

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
import br.com.bancoob.srtb.montagemconteudo.objeto.definicao.RetornoTransacaoObjeto;
import br.com.bancoob.srtb.servico.Transacao;
import br.com.sicoob.capes.api.inclusao.negocio.delegates.CAPESApiInclusaoFabricaDelegates;
import br.com.sicoob.capes.api.inclusao.negocio.delegates.EnderecoDelegate;
import br.com.sicoob.capes.api.inclusao.negocio.dto.EnderecoDTO;
import br.com.sicoob.capes.api.negocio.delegates.CAPESApiFabricaDelegates;
import br.com.sicoob.capes.api.negocio.delegates.PessoaDelegate;
import br.com.sicoob.capes.api.negocio.vo.PessoaVO;
import br.com.sicoob.capes.comum.negocio.enums.TipoEnderecoEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum;
import br.com.sicoob.capes.frontoffice.negocio.excecao.CampoObrigatorioException;
import br.com.sicoob.capes.frontoffice.negocio.util.ParametroUtil;
import br.com.sicoob.capes.frontoffice.negocio.validacao.Validador;
import br.com.sicoob.capes.frontoffice.negocio.validacao.ValidadorCampoObrigatorio;
import br.com.sicoob.capes.integracao.negocio.vo.ValorChaveDocumentoGEDVO;

@Stateless
@Remote({ Transacao.class })
public class AlterarEnderecoPessoaServicoEJB extends ServicosComprovaveisEJB {
	
	/** A constante TAMANHO_BAIRRO. */
	private static final int TAMANHO_BAIRRO = 30;
	
	/** A constante TAMANHO_COMPLEMENTO. */
	private static final int TAMANHO_COMPLEMENTO = 20;
	
	/** A constante TAMANHO_LOGRADOURO. */
	private static final int TAMANHO_LOGRADOURO = 40;
	
	/** A constante TAMANHO_NUMERO. */
	private static final int TAMANHO_NUMERO = 6;

	@Override
	protected List<Validador> getValidadores(Map<String, Parametro> parametros) {
		List<Validador> validadores = new ArrayList<Validador>();
		validadores.add(new ValidadorCampoObrigatorio<Object>(ParametroEnum.NUMERO_PESSOA.getRotulo(), parametros.get(ParametroEnum.NUMERO_PESSOA.getRotulo()).getValor()));
		validadores.add(new ValidadorCampoObrigatorio<Object>(ParametroEnum.NUMERO_COOP_REMETENTE.getRotulo(), parametros.get(ParametroEnum.NUMERO_COOP_REMETENTE.getRotulo()).getValor()));
		validadores.add(new ValidadorCampoObrigatorio<Object>(ParametroEnum.ID_ENDERECO.getRotulo(), parametros.get(ParametroEnum.ID_ENDERECO.getRotulo()).getValor()));
		validadores.add(new ValidadorCampoObrigatorio<Object>(ParametroEnum.COD_TIPO_ENDERECO.getRotulo(), parametros.get(ParametroEnum.COD_TIPO_ENDERECO.getRotulo()).getValor()));
		validadores.add(new ValidadorCampoObrigatorio<Object>(ParametroEnum.COD_TIPO_LOGRADOURO.getRotulo(), parametros.get(ParametroEnum.COD_TIPO_LOGRADOURO.getRotulo()).getValor()));
		validadores.add(new ValidadorCampoObrigatorio<Object>(ParametroEnum.DESC_CEP.getRotulo(), parametros.get(ParametroEnum.DESC_CEP.getRotulo()).getValor()));		
		validadores.add(new ValidadorCampoObrigatorio<Object>(ParametroEnum.DESC_ENDERECO.getRotulo(), parametros.get(ParametroEnum.DESC_ENDERECO.getRotulo()).getValor()));		
		validadores.add(new ValidadorCampoObrigatorio<Object>(ParametroEnum.DESC_SEU_NUMERO.getRotulo(), parametros.get(ParametroEnum.DESC_SEU_NUMERO.getRotulo()).getValor()));		
		validadores.add(new ValidadorCampoObrigatorio<Object>(ParametroEnum.DESC_NOME_BAIRRO.getRotulo(), parametros.get(ParametroEnum.DESC_NOME_BAIRRO.getRotulo()).getValor()));
		validadores.add(new ValidadorCampoObrigatorio<Object>(ParametroEnum.ID_LOCALIDADE.getRotulo(), parametros.get(ParametroEnum.ID_LOCALIDADE.getRotulo()).getValor()));		
		return validadores;
	}
	
	@Override
	protected RetornoTransacaoObjeto executarTransacao(Mensagem mensagem, Map<String, Parametro> parametros) throws BancoobException {
		Integer idPessoaLegado = ParametroUtil.recuperarValorParametro(parametros, ParametroEnum.NUMERO_PESSOA, Integer.class);
		Integer numCooperativa = ParametroUtil.recuperarValorParametro(parametros, ParametroEnum.NUMERO_COOP_REMETENTE, Integer.class);
		
		String idUsuario = obterUsuarioCanal(mensagem.getCodigoCanal());
		
		Integer idEndereco = ParametroUtil.recuperarValorParametro(parametros, ParametroEnum.ID_ENDERECO, Integer.class);
		Short idTipoEndereco = ParametroUtil.recuperarValorParametro(parametros, ParametroEnum.COD_TIPO_ENDERECO, Short.class);
		Integer idTipoLogradouro = ParametroUtil.recuperarValorParametro(parametros, ParametroEnum.COD_TIPO_LOGRADOURO, Integer.class);
		Integer idLocalidade = ParametroUtil.recuperarValorParametro(parametros, ParametroEnum.ID_LOCALIDADE, Integer.class);
		String cep = ParametroUtil.recuperarValorParametro(parametros, ParametroEnum.DESC_CEP, String.class);
		String descEndereco = ParametroUtil.recuperarValorParametro(parametros, ParametroEnum.DESC_ENDERECO, String.class);
		String descNumero = ParametroUtil.recuperarValorParametro(parametros, ParametroEnum.DESC_SEU_NUMERO, String.class);
		String descComplemento = ParametroUtil.recuperarValorParametro(parametros, ParametroEnum.DESC_COMPLEMENTO, String.class);
		String descNomeBairro = ParametroUtil.recuperarValorParametro(parametros, ParametroEnum.DESC_NOME_BAIRRO, String.class);
		
		String documentoComprobatorio = ParametroUtil.recuperarValorParametro(parametros, ParametroEnum.DOCUMENTO_COMPROBATORIO, String.class);
		String nomeArquivoDocumentoComprobatorio = ParametroUtil.recuperarValorParametro(parametros, ParametroEnum.NOME_ARQUIVO_DOCUMENTO_COMPROBATORIO, String.class);
		String extensaoArquivoDocumentoComprobatorio = ParametroUtil.recuperarValorParametro(parametros, ParametroEnum.EXTENSAO_ARQUIVO_DOCUMENTO_COMPROBATORIO, String.class);
		
		if(TipoEnderecoEnum.RESIDENCIAL.getCodigo().equals(idTipoEndereco) && (documentoComprobatorio == null || "".equals(documentoComprobatorio))){
			throw new CampoObrigatorioException("Documento comprobatório");
		}
		
		Integer idInstituicao = obterIdInstituicao(mensagem.getIdInstituicao(), numCooperativa);
		
		PessoaDelegate pessoaDelegate = CAPESApiFabricaDelegates.getInstance().criarPessoaDelegate();
		PessoaVO pessoa = pessoaDelegate.obterPorPessoaLegadoInstituicao(idPessoaLegado, idInstituicao);
		
		if (pessoa == null) {
			throw new NegocioException("Pessoa não encontrada");
		}
		
		getLogger().debug("Arquivo recebido: ", String.valueOf(StringUtils.isNotBlank(documentoComprobatorio)), " nome do arquivo: ",
				nomeArquivoDocumentoComprobatorio, " extensao do arquivo: ", extensaoArquivoDocumentoComprobatorio);
		
		List<Long> idsDocumentos = null;
		if(documentoComprobatorio != null && !"".equals(documentoComprobatorio)) {
			getLogger().debug("Documento comprobatorio recebido: ", documentoComprobatorio);
			byte[] documento = Base64.decodeBase64(documentoComprobatorio.getBytes());
			getLogger().debug("Documento comprobatorio convertido para bytes: ", String.valueOf(documento));
			
			// Cria as chaves de negócio
			TipoPessoaEnum tipoPessoa = TipoPessoaEnum.valueOf(pessoa.getCodTipoPessoa());
			List<ValorChaveDocumentoGEDVO> chavesDocumento = new ArrayList<ValorChaveDocumentoGEDVO>();
			chavesDocumento.add(new ValorChaveDocumentoGEDVO(TipoPessoaEnum.isPessoaFisica(tipoPessoa.getCodigo()) ? Constantes.Negocio.GED_SIGLA_CHAVE_DOCUMENTO_PF : Constantes.Negocio.GED_SIGLA_CHAVE_DOCUMENTO_PJ, pessoa.getCpfCnpj()));
			chavesDocumento.add(new ValorChaveDocumentoGEDVO(Constantes.Negocio.GED_SIGLA_CHAVE_DOCUMENTO_GRUPO_COMPARTILHAMENTO, String.valueOf(pessoa.getCodigoCompartilhamentoCadastro())));
			chavesDocumento.add(new ValorChaveDocumentoGEDVO(Constantes.Negocio.GED_SIGLA_CHAVE_DOCUMENTO_TIPO_ENDERECO, String.valueOf(idTipoEndereco)));
			chavesDocumento.add(new ValorChaveDocumentoGEDVO(Constantes.Negocio.GED_SIGLA_CHAVE_DOCUMENTO_CEP, String.valueOf(cep)));
			
			// Envia os documentos ao GED e obtém o id do documento.
			idsDocumentos = obterDocumentosComprobatorios(documento, nomeArquivoDocumentoComprobatorio, extensaoArquivoDocumentoComprobatorio, chavesDocumento, pessoa, idUsuario);
			
			getLogger().debug("Quantidade de ids de documentos recebidos: ", String.valueOf(idsDocumentos != null ? idsDocumentos.size() : 0));
		}
		
		EnderecoDTO dto = new EnderecoDTO();
		dto.setIdPessoa(pessoa.getIdPessoa());
		dto.setIdInstituicao(pessoa.getIdInstituicao());
		dto.setIdUnidadeInst(pessoa.getIdUnidadeInst());
		dto.setIdEndereco(idEndereco.longValue());
		dto.setCodigoTipoEndereco(idTipoEndereco);
		dto.setCodigoTipoLogradouro(idTipoLogradouro);
		dto.setCodigoLocalidade(idLocalidade);
		dto.setNumero(truncarCampo(descNumero, TAMANHO_NUMERO));
		dto.setCep(cep);
		dto.setDescricao(truncarCampo(descEndereco, TAMANHO_LOGRADOURO));
		dto.setComplemento(truncarCampo(descComplemento, TAMANHO_COMPLEMENTO));
		dto.setBairro(truncarCampo(descNomeBairro, TAMANHO_BAIRRO));
		dto.setIdUsuarioAprovacao(idUsuario);
		dto.setIdsDocumentos(idsDocumentos);
		
		alterarEndereco(dto);
		encaminharFluxo(pessoa.getIdPessoa(), pessoa.getIdInstituicao(), pessoa.getIdUnidadeInst(), dto.getIdEndereco(), pessoa.getIdInstituicao(), idUsuario);
		RetornoTransacaoObjeto retornoTransacao = new RetornoTransacaoObjeto();
		return retornoTransacao;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void alterarEndereco(EnderecoDTO dto) throws BancoobException {
		obterDelegateInclusao().alterar(dto);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String obterSiglaClasseDocumento() {
		return "ENDERECO";
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EnderecoDelegate obterDelegateInclusao() {
		return CAPESApiInclusaoFabricaDelegates.obterInstancia().criarEnderecoDelegate();
	}
	
	private String truncarCampo(String campo, Integer tamanhoMaximo) {
		if (!StringUtils.isEmpty(campo) && campo.length() > tamanhoMaximo) {
			return campo.substring(0, tamanhoMaximo);
		} else {
			return campo;
		}
	}

}