package br.com.sicoob.capes.relatorio.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.comum.negocio.vo.CamposFichaCadastralVO;
import br.com.sicoob.capes.negocio.entidades.EnderecoBase;
import br.com.sicoob.capes.negocio.entidades.RelacionamentoPessoaBase;
import br.com.sicoob.capes.negocio.entidades.bemantigo.Bem;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemImovel;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemOnus;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemPosse;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemRegistro;
import br.com.sicoob.capes.negocio.entidades.bemantigo.TipoBem;
import br.com.sicoob.capes.negocio.entidades.vigente.Certidao;
import br.com.sicoob.capes.negocio.entidades.vigente.Email;
import br.com.sicoob.capes.negocio.entidades.vigente.Endereco;
import br.com.sicoob.capes.negocio.entidades.vigente.FonteRenda;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaJuridica;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtividade;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtor;
import br.com.sicoob.capes.negocio.entidades.vigente.Referencia;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.Telefone;
import br.com.sicoob.capes.negocio.entidades.vigente.Tributacao;
import br.com.sicoob.capes.relatorio.negocio.relatorios.RelatorioFichaCadastral;
import br.com.sicoob.capes.relatorio.negocio.relatorios.RelatorioFichaCadastralPessoaFisica;
import br.com.sicoob.capes.relatorio.negocio.relatorios.RelatorioFichaCadastralPessoaJuridica;
import br.com.sicoob.capes.relatorio.negocio.vo.FichaCadastralBemVO;
import br.com.sicoob.capes.relatorio.negocio.vo.FichaCadastralContatoVO;
import br.com.sicoob.capes.relatorio.negocio.vo.FichaCadastralVO;
import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO;
import br.com.sicoob.relatorio.api.dto.RetornoProcessamentoRelatorioDTO;
import br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico;

/**
 * EJB contendo servicos relacionados a RelatorioFichaCadastralEmBranco.
 */
@Stateless
@Remote({ IProcessamentoRelatorioServico.class })
public class RelatorioFichaCadastralEmBrancoServicoEJB extends CAPESRelatorioServicoEJB implements IProcessamentoRelatorioServico {

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RetornoProcessamentoRelatorioDTO gerarRelatorio(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
		Number qtdBens = (Number) dto.getDados().get("qtdBens");
		Number qtdCertidoes = (Number) dto.getDados().get("qtdCertidoes");
		Number qtdEmails = (Number) dto.getDados().get("qtdEmails");
		Number qtdEnderecos = (Number) dto.getDados().get("qtdEnderecos");
		Number qtdReferencias = (Number) dto.getDados().get("qtdReferencias");
		Number qtdRelacionamentos = (Number) dto.getDados().get("qtdRelacionamentos");
		Number qtdTelefones = (Number) dto.getDados().get("qtdTelefones");
		Number qtdFontesRenda = (Number) dto.getDados().get("qtdFontesRenda");
		Number qtdBensImoveis = (Number) dto.getDados().get("qtdBensImoveis");
		Number qtdProdutividades = (Number) dto.getDados().get("qtdProdutividades");
		Number codigoTipoPessoa = (Number) dto.getDados().get("codigoTipoPessoa");
		
		CamposFichaCadastralVO vo = new CamposFichaCadastralVO();
		vo.setQtdBens(qtdBens.intValue());
		vo.setQtdCertidoes(qtdCertidoes.intValue());
		vo.setQtdEmails(qtdEmails.intValue());
		vo.setQtdEnderecos(qtdEnderecos.intValue());
		vo.setQtdReferencias(qtdReferencias.intValue());
		vo.setQtdRelacionamentos(qtdRelacionamentos.intValue());
		vo.setQtdTelefones(qtdTelefones.intValue());
		vo.setQtdFontesRenda(qtdFontesRenda.intValue());
		vo.setQtdBensImoveis(qtdBensImoveis.intValue());
		vo.setQtdProdutividades(qtdProdutividades.intValue());
		vo.setTipoPessoa(codigoTipoPessoa.intValue());

		FichaCadastralVO fichaCadastralVO = new FichaCadastralVO();
		FichaCadastralContatoVO contatoVO = new FichaCadastralContatoVO();
		PessoaCompartilhamento pessoa = null;

		pessoa = obterInstanciaPessoaCompartilhamento(vo);

		fichaCadastralVO.setPessoa(pessoa);

		ArrayList<PessoaCompartilhamento> pessoasCompartilhamento = new ArrayList<PessoaCompartilhamento>(1);
		pessoasCompartilhamento.add(obterInstanciaPessoaCompartilhamento(vo));

		fichaCadastralVO.setPessoasCompartilhamento(pessoasCompartilhamento);

		fichaCadastralVO.setCertidoes(criarLista(Certidao.class, vo.getQtdCertidoes()));
		fichaCadastralVO.setRelacionamentos((List<RelacionamentoPessoaBase>) (List<?>) criarLista(RelacionamentoPessoa.class, vo.getQtdRelacionamentos()));
		contatoVO.setEmails(criarLista(Email.class, vo.getQtdEmails()));
		contatoVO.setEnderecos(criarLista(EnderecoBase.class, Endereco.class, vo.getQtdEnderecos()));
		contatoVO.setTelefones(criarLista(Telefone.class, vo.getQtdTelefones()));
		fichaCadastralVO.getContatos().add(contatoVO);
		fichaCadastralVO.setFontesDeRenda(criarLista(FonteRenda.class, vo.getQtdFontesRenda()));
		fichaCadastralVO.setReferencias(criarLista(Referencia.class, vo.getQtdReferencias()));

		fichaCadastralVO.setTributacoes(criarLista(Tributacao.class, 1));
		fichaCadastralVO.setPessoaInstituicoes(criarLista(PessoaInstituicao.class, 1));
		fichaCadastralVO.setBensDTO(criarListaBensDTO(criarLista(Bem.class, vo.getQtdBens())));
		fichaCadastralVO.setBemImoveisDTO(criarListaBensDTO(criarLista(BemImovel.class, vo.getQtdBensImoveis())));
		fichaCadastralVO.setBemOutrosDTO(criarListaBensDTO(criarLista(Bem.class, vo.getQtdBens())));

		fichaCadastralVO.setIdInstituicao(obterInstituicaoUsuarioRelatorio().getIdInstituicao());
		fichaCadastralVO.setIdUnidadeInst(obterInstituicaoUsuarioRelatorio().getIdUnidadeInst());

		if (vo.getQtdProdutividades() > 0) {
			fichaCadastralVO.setProdutores(criarLista(Produtor.class, 1));
		}

		fichaCadastralVO.setProdutividades(criarLista(Produtividade.class, vo.getQtdProdutividades()));

		RelatorioFichaCadastral relatorioFichaCadastralPessoaFisica = obterRelatorioFichaCadastral(fichaCadastralVO, vo);

		return relatorioFichaCadastralPessoaFisica.gerarRelatorio(rDto);
	}

	/**
	 * Criar lista.
	 *
	 * @param <R>
	 *            o tipo generico
	 * @param <I>
	 *            o tipo generico
	 * @param classeLista
	 *            o valor de classe lista
	 * @param classeObjetos
	 *            o valor de classe objetos
	 * @param qtd
	 *            o valor de qtd
	 * @return List
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	private <R, I extends R> List<R> criarLista(Class<R> classeLista, Class<I> classeObjetos, Integer qtd) throws BancoobException {

		return new LinkedList<R>(criarLista(classeObjetos, qtd));
	}

	/**
	 * Criar lista.
	 *
	 * @param <E>
	 *            o tipo generico
	 * @param clazz
	 *            o valor de clazz
	 * @param qtd
	 *            o valor de qtd
	 * @return List
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	private <E> List<E> criarLista(Class<E> clazz, Integer qtd) throws BancoobException {
		List<E> lista = new LinkedList<E>();
		for (int i = 0; i < qtd; i++) {
			try {
				lista.add(clazz.newInstance());
			} catch (InstantiationException e) {
				throw new BancoobRuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new BancoobRuntimeException(e);
			}
		}

		return lista;
	}

	/**
	 * Obter instancia pessoa compartilhamento.
	 *
	 * @param vo
	 *            o valor de vo
	 * @return PessoaCompartilhamento
	 */
	private PessoaCompartilhamento obterInstanciaPessoaCompartilhamento(CamposFichaCadastralVO vo) {
		PessoaCompartilhamento pessoa;
		if (isPessoaFisica(vo)) {
			pessoa = new PessoaFisica();
		} else {
			pessoa = new PessoaJuridica();

		}
		return pessoa;
	}

	/**
	 * Obter relatorio ficha cadastral.
	 *
	 * @param fichaCadastralVO
	 *            o valor de ficha cadastral vo
	 * @param vo
	 *            o valor de vo
	 * @return RelatorioFichaCadastral
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	private RelatorioFichaCadastral obterRelatorioFichaCadastral(FichaCadastralVO fichaCadastralVO, CamposFichaCadastralVO vo) throws BancoobException {

		RelatorioFichaCadastral relatorioFichaCadastralPessoaFisica = null;

		if (isPessoaFisica(vo)) {
			relatorioFichaCadastralPessoaFisica = new RelatorioFichaCadastralPessoaFisica(fichaCadastralVO);
		} else {
			relatorioFichaCadastralPessoaFisica = new RelatorioFichaCadastralPessoaJuridica(fichaCadastralVO);
		}

		return relatorioFichaCadastralPessoaFisica;
	}

	/**
	 * Criar lista bens dto.
	 *
	 * @param bens
	 *            o valor de bens
	 * @return List
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<FichaCadastralBemVO> criarListaBensDTO(List bens) throws BancoobException {
		List<FichaCadastralBemVO> bensImoveisDTO = new LinkedList<FichaCadastralBemVO>();

		if (!bens.isEmpty()) {
			FichaCadastralBemVO dto = new FichaCadastralBemVO();
			dto.setBens(bens);
			dto.setBensOnus(criarLista(BemOnus.class, 1));
			dto.setBensRegistro(criarLista(BemRegistro.class, 1));
			dto.setBensPosse(criarLista(BemPosse.class, 1));

			TipoBem tipoBem = new TipoBem();
			tipoBem.setDescricao("");
			dto.setTipoBem(tipoBem);

			bensImoveisDTO.add(dto);
		}

		return bensImoveisDTO;
	}

	/**
	 * Verifica se eh pessoa fisica.
	 *
	 * @param vo
	 *            o valor de vo
	 * @return {@code true}, se for pessoa fisica
	 */
	private boolean isPessoaFisica(CamposFichaCadastralVO vo) {
		return vo.getTipoPessoa().shortValue() == TipoPessoaEnum.PESSOA_FISICA.getCodigo();
	}

}