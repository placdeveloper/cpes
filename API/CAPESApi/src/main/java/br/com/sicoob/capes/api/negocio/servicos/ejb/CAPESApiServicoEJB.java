/*
 * SICOOB
 * 
 * CAPESApiServicoEJB.java(br.com.sicoob.capes.api.negocio.servicos.ejb.CAPESApiServicoEJB)
 */
package br.com.sicoob.capes.api.negocio.servicos.ejb;


import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.FloatConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.ShortConverter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.collections.functors.NotNullPredicate;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.negocio.servicos.ejb.BancoobServicoEJB;
import br.com.bancoob.negocio.vo.BancoobVo;
import br.com.sicoob.capes.api.negocio.excecao.InstituicaoNaoInformadaException;
import br.com.sicoob.capes.api.negocio.excecao.ParametroInvalidoException;
import br.com.sicoob.capes.api.negocio.excecao.PessoaNaoInformadaException;
import br.com.sicoob.capes.api.negocio.excecao.TipoMensagemNaoInformadaException;
import br.com.sicoob.capes.api.negocio.filtros.FiltroConsultaAPIAbstrato;
import br.com.sicoob.capes.api.negocio.servicos.CAPESApiServico;
import br.com.sicoob.capes.api.persistencia.dao.CAPESApiDao;

/**
 * @author Erico.Junior
 *
 */
public abstract class CAPESApiServicoEJB extends BancoobServicoEJB implements CAPESApiServico {
	
	/** A Constante TAMANHO_MINIMO_FILTRO. */
	private static final int TAMANHO_MINIMO_FILTRO = 3;
	
	/**
	 * Validar obrigatoriedade instituicao.
	 * 
	 * @param idInstituicao
	 *            the id instituicao
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	protected void validarObrigatoriedadeInstituicao(Integer idInstituicao) throws BancoobException {
		if (idInstituicao == null) {
			throw new InstituicaoNaoInformadaException();
		}
	}
	
	/**
	 * Verifica se idPessoa e idInstituicao são diferentes de null
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @throws BancoobException
	 */
	protected void validarObrigatoriedadePessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		if (idPessoa == null) {
			throw new PessoaNaoInformadaException();
		}
		if (idInstituicao == null) {
			throw new InstituicaoNaoInformadaException();
		}
	}
	
	/**
	 * O método Validar obrigatoriedade pessoa instituicao tipo mensagem.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @param codigoMensagem o valor de codigo mensagem
	 * @throws BancoobException lança a exceção BancoobException
	 */
	protected void validarObrigatoriedadePessoaInstituicaoTipoMensagem(Integer idPessoa, Integer idInstituicao,
			Short codigoMensagem) throws BancoobException {
		if (idPessoa == null) {
			throw new PessoaNaoInformadaException();
		}
		if (idInstituicao == null) {
			throw new InstituicaoNaoInformadaException();
		}
		if(codigoMensagem == null){
			throw new TipoMensagemNaoInformadaException();
		}
	}
	
	/**
	 * Validar paginacao.
	 * 
	 * @param pagina
	 *            the pagina
	 * @param tamanhoPagina
	 *            the tamanho pagina
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	protected void validarPaginacao(int pagina, int tamanhoPagina) throws BancoobException {
		if (pagina < NumberUtils.INTEGER_ZERO) {
			throw new NegocioException("O número da página deve ser maior ou igual a zero.");
		} else if (tamanhoPagina < NumberUtils.INTEGER_ONE) {
			throw new NegocioException("O tamanho da página deve ser maior que zero.");
		}
	}
	
	/**
	 * Validações feitas na listagem de pessoa para garantir que exista um filtro válido.
	 * 
	 * @param procurarPor
	 *            A procura utilizada na pesquisa.
	 * @throws NegocioException 
	 */
	protected void validarPesquisarPessoa(String procurarPor) throws NegocioException {
		if (StringUtils.isEmpty(procurarPor) || procurarPor.length() < TAMANHO_MINIMO_FILTRO) {
			throw new NegocioException("O Filtro deve ter no mínimo 3 (três) posições.");
		}

		if (procurarPor.indexOf('%') >= NumberUtils.INTEGER_ZERO) {
			throw new NegocioException("Filtro inválido.");
		}
	}
	
	/**
	 * Obter objeto.
	 * 
	 * OBS: Os Conversores são instanciados com o parâmetro null padrão, para
	 * que sejam retornados caso ocorra algum erro durante a conversão.
	 * 
	 * @param <C>
	 *            the generic type
	 * @param classe
	 *            the classe
	 * @param propriedades
	 *            the propriedades
	 * @return c
	 */
	protected <C> C obterObjeto(Class<C> classe, Map<String, Object> propriedades) {
		C objeto = null;
		if (propriedades != null) {
			try {
				if (CollectionUtils.countMatches(propriedades.values(), NotNullPredicate.getInstance()) > NumberUtils.INTEGER_ZERO) {
					objeto = classe.newInstance();
					ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
					ConvertUtils.register(new ShortConverter(null), Short.class);
					ConvertUtils.register(new IntegerConverter(null), Integer.class);
					ConvertUtils.register(new LongConverter(null), Long.class);
					ConvertUtils.register(new DoubleConverter(null), Double.class);
					ConvertUtils.register(new FloatConverter(null), Float.class);
					BeanUtils.populate(objeto, propriedades);
				}
				return objeto;
			} catch (IllegalAccessException e) {
				throw new BancoobRuntimeException(e);
			} catch (InvocationTargetException e) {
				throw new BancoobRuntimeException(e);
			} catch (InstantiationException e) {
				throw new BancoobRuntimeException(e);
			} catch (IllegalArgumentException e){
				throw new BancoobRuntimeException(e);
			}
		}
		return objeto;
	}
	
	/**
	 * Obter lista objetos.
	 * 
	 * @param <C>
	 *            the generic type
	 * @param classe
	 *            the classe
	 * @param listaPropriedades
	 *            the lista propriedades
	 * @return list
	 */
	protected <C> List<C> obterListaObjetos(Class<C> classe, List<Map<String, Object>> listaPropriedades) {
		List<C> listaRetorno = new ArrayList<C>();
		if ((listaPropriedades != null) && !listaPropriedades.isEmpty()) {
			listaRetorno = new ArrayList<C>();
			for (Map<String, Object> mapa : listaPropriedades) {
				CollectionUtils.addIgnoreNull(listaRetorno, obterObjeto(classe, mapa));
			}
		}
		return listaRetorno;
	}
	
	/**
	 * Obter consulta dto.
	 * 
	 * @param <C>
	 *            the generic type
	 * @param classe
	 *            the classe
	 * @param consultaDTO
	 *            the consulta dto
	 * @return consulta dto
	 */
	@SuppressWarnings("unchecked")
	protected <C> ConsultaDto<C> obterConsultaDTO(Class<C> classe, ConsultaDto<Map<String, Object>> consultaDTO) {
		ConsultaDto<C> retorno = null;
		if (consultaDTO != null) {
			retorno = copiarDTO(classe, consultaDTO);
			List<C> listaResultado = obterListaObjetos(classe, consultaDTO.getResultado());
			retorno.setResultado(listaResultado);
		} else {
			retorno = new ConsultaDto<C>();
			retorno.setResultado(ListUtils.EMPTY_LIST);
		}
		return retorno;
	}

	/**
	 * Copiar dto.
	 * 
	 * @param <C>
	 *            the generic type
	 * @param classe
	 *            the classe
	 * @param consultaDTO
	 *            the consulta dto
	 * @return consulta dto
	 */
	private <C> ConsultaDto<C> copiarDTO(Class<C> classe, ConsultaDto<Map<String, Object>> consultaDTO) {
		ConsultaDto<C> retorno = new ConsultaDto<C>();
		retorno.setTotalRegistros(consultaDTO.getTotalRegistros());
		retorno.setFiltro(consultaDTO.getFiltro());
		retorno.setTipoProcura(consultaDTO.getTipoProcura());
		retorno.setProcurarPor(consultaDTO.getProcurarPor());
		retorno.setPagina(consultaDTO.getPagina());
		retorno.setTamanhoPagina(consultaDTO.getTamanhoPagina());
		retorno.setOrdenacao(consultaDTO.getOrdenacao());
		retorno.setOrdemCrescente(consultaDTO.isOrdemCrescente());
		return retorno;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public <K extends BancoobVo> List<K> pesquisar(FiltroConsultaAPIAbstrato filtro) throws BancoobException {
		validarFiltroPesquisar(filtro);
		ConsultaDto<? extends FiltroConsultaAPIAbstrato> consultaDto = obterConsultaDTO(filtro);
		return obterDAO().pesquisar(consultaDto);
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public <K extends BancoobVo> ConsultaDto<K> pesquisarPaginado(FiltroConsultaAPIAbstrato filtro, int pagina, int tamanhoPagina) throws BancoobException {
		validarFiltroPesquisar(filtro);
		ConsultaDto<? extends FiltroConsultaAPIAbstrato> consultaDto = obterConsultaDTO(filtro, pagina, tamanhoPagina);
		return obterDAO().pesquisarPaginado(consultaDto);
	}

	/**
	 * Obtém o dao do serviço.
	 * 
	 * @return o dao solicitado
	 */
	protected abstract CAPESApiDao obterDAO();
	
	/**
	 * Realiza a validação do filtro da consulta.
	 * 
	 * @param criterios
	 * @throws ParametroInvalidoException
	 */
	protected <V extends FiltroConsultaAPIAbstrato> void validarFiltroPesquisar(FiltroConsultaAPIAbstrato filtro) throws BancoobException {
		if (filtro == null || filtro.getIdInstituicao() == null || filtro.getIdPessoa() == null) {
			throw new ParametroInvalidoException();
		}
	}

	/**
	 * Obtém o consultaDto
	 * 
	 * @param filtro
	 * @return
	 * @throws BancoobException
	 */
	protected <V extends FiltroConsultaAPIAbstrato> ConsultaDto<V> obterConsultaDTO(V filtro) throws BancoobException {
		return obterConsultaDTO(filtro, NumberUtils.INTEGER_ZERO, NumberUtils.INTEGER_ZERO);
	}

	/**
	 * Obtém o consultaDto
	 * 
	 * @param filtro
	 * @return
	 * @throws BancoobException
	 */
	protected <V extends FiltroConsultaAPIAbstrato> ConsultaDto<V> obterConsultaDTO(V filtro, int pagina, int tamanhoPagina) throws BancoobException {
		ConsultaDto<V> consultaDto = new ConsultaDto<V>();
		consultaDto.setFiltro(filtro);
		consultaDto.setPagina(pagina);
		consultaDto.setTamanhoPagina(tamanhoPagina);
		return consultaDto;
	}
	
}