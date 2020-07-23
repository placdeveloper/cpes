/*
 * Sicoob
 * AutorizarHelper.java
 * Criado em: 25/10/2010
 */
package br.com.sicoob.capes.cadastro.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.capes.cadastro.negocio.excecao.CAPESCadastroNegocioException;
import br.com.sicoob.capes.cadastro.negocio.vo.CampoTelaListaVO;
import br.com.sicoob.capes.cadastro.negocio.vo.CampoTelaVO;
import br.com.sicoob.capes.cadastro.negocio.vo.CamposTelaVO;
import br.com.sicoob.capes.cadastro.negocio.vo.EncaminharAutorizacaoVO;
import br.com.sicoob.capes.comum.negocio.annotation.CampoAutorizacao;
import br.com.sicoob.capes.comum.negocio.annotation.CamposAutorizacao;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.comum.util.formatacao.Formatador;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * Classe utilitária que auxilia a camada de apresentação de autorização
 * 
 * 25/10/2010
 * 
 * @author Rodrigo.Chaves
 */
public class AutorizarHelper {
	
	/**
	 * Construtor privado para evitar instâncias de classe utilitária
	 */
	private AutorizarHelper() {

	}
	
	/**
	 * Gera os campos da tela de autorização.
	 * 
	 * @param registroAntigo
	 * @param registroNovo
	 * @return
	 * @throws BancoobException
	 */
	public static CamposTelaVO gerarCamposTela(Object registroAntigo, Object registroNovo) throws BancoobException {
		if(registroAntigo == null && registroNovo == null) {
			throw new CAPESCadastroNegocioException("Registro da anotação não encontrado.");
		}
		
		// Obtém a classe do objeto a ter os campos gerados. 
		Class<?> classe = registroAntigo != null ? registroAntigo.getClass() : registroNovo.getClass();

		// Obtém os campos anotados.
		List<CampoAutorizacao> campos = obterCamposAutorizacao(classe);

		List<CampoTelaVO> camposTela = new ArrayList<CampoTelaVO>();
		List<CampoTelaListaVO> camposLista = new ArrayList<CampoTelaListaVO>();
		
		// Para cada campo, obtém as informações e gera os campos para a tela.
		for (CampoAutorizacao campo : campos) {
			SicoobLoggerPadrao.getInstance(AutorizarHelper.class).debug("gerarCamposTela: Gerando o campo: ", campo.label(), " da classe ", classe.getSimpleName());

			// Recupera o valor dos campos.
			Object atributoAntigo = ReflexaoUtils.getValorAtributoAninhadoThreadSafe(registroAntigo, campo.propriedade());
			Object atributoNovo = ReflexaoUtils.getValorAtributoAninhadoThreadSafe(registroNovo, campo.propriedade());

			// Se o atributo não for do tipo lista, gera o campo para a tela.
			if (!campo.isLista()) {
				camposTela.add(criarCampo(campo, atributoAntigo, atributoNovo));
			} else {
				CampoTelaListaVO campoTelaListaVO = criarCamposLista(atributoAntigo, atributoNovo, campo);
				camposLista.add(campoTelaListaVO);
			}
		}
		
		// Ordena os campos principais, pois as heranças também podem ser anotadas.
		Collections.sort(camposTela, new BeanComparator<CampoTelaVO>("ordenacao"));
		
		// Cria o retorno para a fachada.
		CamposTelaVO retorno = new CamposTelaVO();
		retorno.setCampos(camposTela);
		retorno.setListas(camposLista);
		
		// Garbage collector.
		classe = null;
		campos = null;
		camposTela = null;
		camposLista = null;
		
		return retorno;
	}

	/**
	 * Criar campo.
	 *
	 * @param annotation o valor de annotation
	 * @param atributoAntigo o valor de atributo antigo
	 * @param atributoNovo o valor de atributo novo
	 * @return CampoTelaVO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected static CampoTelaVO criarCampo(CampoAutorizacao annotation, Object atributoAntigo, Object atributoNovo) throws BancoobException {
		Formatador formatador = ReflexaoUtils.construirObjetoPorClasse(Formatador.class, annotation.formatador());
		String mascara = StringUtils.isEmpty(annotation.mascara()) ? null : annotation.mascara();

		CampoTelaVO campo = new CampoTelaVO();
		campo.setLabel(annotation.label());
		campo.setValorAntigo(formatador.formatar(atributoAntigo, mascara));
		campo.setValorNovo(formatador.formatar(atributoNovo, mascara));
		campo.setOrdenacao(annotation.ordenacao());
		campo.comparar();
		
		return campo;
	}
	
	/**
	 * Cria os campos para os atributos de lista.
	 * 
	 * @param atributoAntigo
	 * @param atributoNovo
	 * @param campo
	 * @return
	 * @throws BancoobException
	 */
	private static CampoTelaListaVO criarCamposLista(Object atributoAntigo, Object atributoNovo, CampoAutorizacao campo) throws BancoobException {
		Collection<?> listaAntiga = (Collection<?>) atributoAntigo;
		Collection<?> listaNova = (Collection<?>) atributoNovo;
		CampoTelaListaVO retorno = new CampoTelaListaVO();

		// Obtém a classe dos objetos da lista.
		Class<?> classe = obterClasseObjetoLista(listaAntiga, listaNova);

		// Verifica se a lista possui algum objeto.
		if (classe != null) {
			
			// Obtém os campos anotados para essa classe.
			List<CampoAutorizacao> campos = obterCamposAutorizacao(classe);

			// Verifica a classe do tipo da lista foi devidamente anotada.
			if (campos == null || campos.isEmpty()) {
				throw new CAPESCadastroNegocioException("Valores da lista não anotados");
			}

			// Preenche o retorno.
			retorno.setNomeAtributo(campo.label());
			retorno.setContadorSeparador(campos.size());
			retorno.setCampos(criarCamposLista(campos, listaAntiga, listaNova, campo.propriedadesComparacaoLista()));
		}

		return retorno;
	}
	
	/**
	 * Cria os registros para os atributos do tipo lista.
	 * 
	 * @param atributoRegistroAntigo
	 * @param atributoRegistroNovo
	 * @return
	 * @throws BancoobException
	 */
	private static List<CampoTelaVO> criarCamposLista(List<CampoAutorizacao> campos, Collection<?> listaAntiga, Collection<?> listaNova, String... propriedades) throws BancoobException {
		List<CampoTelaVO> retorno = new ArrayList<CampoTelaVO>();

		// Obtém os objetos comuns das listas, para gerar os campos de comparação.
//		Collection<?> listaIntersecao = CollectionUtils.intersection(listaAntiga, listaNova);
		Collection<?> listaIntersecao = ReflexaoUtils.intersecaoColecaoSemTipo(listaAntiga, listaNova, propriedades);
		for (Iterator<?> it = listaIntersecao.iterator(); it.hasNext();) {
			Object objeto = it.next();
			Object objetoEsquerda = obterObjetoLista(listaAntiga, objeto, propriedades);
			Object objetoDireita = obterObjetoLista(listaNova, objeto, propriedades);
			retorno.addAll(gerarRegistroLista(campos, objetoEsquerda, objetoDireita));
		}

		// Obtém os objetos que estão apenas na lista antiga.
//		Collection<?> listaEsquerda = CollectionUtils.subtract(listaAntiga, listaIntersecao);
		Collection<?> listaEsquerda = ReflexaoUtils.subtrairColecaoSemTipo(listaAntiga, listaIntersecao, propriedades);
		for (Iterator<?> it = listaEsquerda.iterator(); it.hasNext();) {
			Object objeto = it.next();
			Object objetoEsquerda = obterObjetoLista(listaAntiga, objeto, propriedades);
			retorno.addAll(gerarRegistroLista(campos, objetoEsquerda, null));
		}

		// Obtém os objetos que estão apenas na lista nova.
//		Collection<?> listaDireita = CollectionUtils.subtract(listaNova, listaIntersecao);
		Collection<?> listaDireita = ReflexaoUtils.subtrairColecaoSemTipo(listaNova, listaIntersecao, propriedades);
		for (Iterator<?> it = listaDireita.iterator(); it.hasNext();) {
			Object objeto = it.next();
			Object objetoDireita = obterObjetoLista(listaNova, objeto, propriedades);
			retorno.addAll(gerarRegistroLista(campos, null, objetoDireita));
		}
		
		// Garbage collector.
		listaIntersecao = null;
		listaEsquerda = null;
		listaDireita = null;

		return retorno;
	}
	
	/**
	 * Obtém os {@code CamposAutorizacao} anotados na classe.
	 * 
	 * @param classe
	 * @return
	 */
	private static List<CampoAutorizacao> obterCamposAutorizacao(Class<?> classe) {
		// Pega as anotações do tipo CamposAutorizacao.
		List<CamposAutorizacao> listaCamposAutorizacao = ReflexaoUtils.getAnnotationsSuperClasses(CamposAutorizacao.class, classe);

		// Para cada registro de anotação da entidade, pega os campos para serem utilizados na geração da tela.
		List<CampoAutorizacao> campos = new ArrayList<CampoAutorizacao>();
		for (CamposAutorizacao camposAutorizacao : listaCamposAutorizacao) {
			campos.addAll(Arrays.asList(camposAutorizacao.camposExibicao()));
		}
		return campos;
	}

	/**
	 * Obtém a classe do objeto passado nas listas.
	 * 
	 * @param listaAntiga
	 * @param listaNova
	 * @return
	 */
	private static Class<?> obterClasseObjetoLista(Collection<?> listaAntiga, Collection<?> listaNova) {
		// Obtém a classe no tipo das listas.
		Class<?> classe = obterClasseLista(listaAntiga);
		if (classe == null) {
			classe = obterClasseLista(listaNova);
		}
		return classe;
	}

	/**
	 * Gera o registro de autorização da lista.
	 * 
	 * @param classe
	 * @param campos
	 * @param objetoAntigo
	 * @param objetoNovo
	 * @return
	 * @throws BancoobException 
	 */
	private static List<CampoTelaVO> gerarRegistroLista(List<CampoAutorizacao> campos, Object objetoAntigo, Object objetoNovo) throws BancoobException {
		List<CampoTelaVO> retorno = new ArrayList<CampoTelaVO>();
		for (CampoAutorizacao campo : campos) {
			Object atributoAntigo = ReflexaoUtils.getValorAtributoAninhadoThreadSafe(objetoAntigo, campo.propriedade());
			Object atributoNovo = ReflexaoUtils.getValorAtributoAninhadoThreadSafe(objetoNovo, campo.propriedade());

			retorno.add(criarCampo(campo, atributoAntigo, atributoNovo));
		}
		return retorno;
	}
	
	/**
	 * Obtém a classe da lista, para obter as anotações de
	 * {@code CamposAutorizacao}
	 * 
	 * @param lista
	 * @return
	 */
	private static Class<?> obterClasseLista(Collection<?> lista) {
		Class<?> classe = null;

		if (lista != null && lista.size() > 0) {
			Object objeto = lista.toArray()[0];
			classe = objeto.getClass();
		}

		return classe;
	}

	/**
	 * Gerar campos tela.
	 *
	 * @param autorizacoes o valor de autorizacoes
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public static List<EncaminharAutorizacaoVO> gerarCamposTela(List<Autorizacao> autorizacoes)
			throws BancoobException {
		
		List<EncaminharAutorizacaoVO> retorno = null;
		EncaminharAutorizacaoVO vo = null;
		
		if(autorizacoes != null
				&& !autorizacoes.isEmpty()){
			retorno = new ArrayList<EncaminharAutorizacaoVO>();
			
			for (Autorizacao aut : autorizacoes) {
				vo = new EncaminharAutorizacaoVO();
				
				vo.setIdAutorizacao(aut.getIdAutorizacao());
				vo.setIdInstituicaoOrigem(aut.getInstituicaoOrigem().getIdInstituicao());
				vo.setTipoAutorizacao(aut.getTipoAutorizacao().getDescricao());
				vo.setTipoAutorizacaoLabel(aut.getTipoAutorizacao().getDescricao());
				vo.setTipoOperacao(aut.getTipoOperacao().getDescricao());
				vo.setDataHoraCadastro(DataUtil.converterDateToString(aut.getDataHoraCadastro(), "dd/MM/yyyy HH:mm:ss"));
				vo.setCooperativaDestino(obterCooperativa(aut.getInstituicaoDestino()));
				vo.setCooperativaOrigem(obterCooperativa(aut.getInstituicaoOrigem()));
				vo.setRegistroControlado(aut.getIdRegistroControlado());
				vo.setPossuiDocumento(aut.getDocumentos() != null && !aut.getDocumentos().isEmpty() ? "SIM" : "-");
				
				retorno.add(vo);
			}
		}
		
		return retorno;
	}

	/**
	 * Obter cooperativa.
	 *
	 * @param instituicao o valor de instituicao
	 * @return String
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private static String obterCooperativa(Instituicao instituicao) throws BancoobException {
		SCIIntegracaoDelegate sciIntegracaoDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();
		Integer numCooperativa = sciIntegracaoDelegate.obterNumeroCooperativa(instituicao.getIdInstituicao());
		Integer unidade = instituicao.getIdUnidadeInst();
		
		return numCooperativa + "-" + unidade;
	}
	
	/**
	 * Obtém um objeto dentro da lista.
	 * 
	 * @param lista
	 * @param objeto
	 * @return
	 */
	private static Object obterObjetoLista(Collection<?> lista, Object objeto, String... propriedades) {
		return ReflexaoUtils.obterObjetoLista(lista, objeto, propriedades);
	}
	
}