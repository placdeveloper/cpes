/**
 * 
 */
package br.com.sicoob.capes.cadastro.conversao.informacoesprofissionais;

import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.PessoaDelegate;
import br.com.sicoob.capes.cadastro.vo.informacoesprofissionais.InformacaoProfissionalVO;
import br.com.sicoob.capes.negocio.entidades.ConselhoRegional;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.TipoAfastamento;
import br.com.sicoob.capes.negocio.entidades.TipoFuncionario;
import br.com.sicoob.capes.negocio.entidades.vigente.InformacaoProfissional;
import br.com.sicoob.tipos.DateTime;

/**
 * @author Erico.Junior
 *
 */
public final class ConversorInformacaoProfissional {

	/**
	 * Instancia um novo ConversorInformacaoProfissional.
	 */
	private ConversorInformacaoProfissional(){
	}
	
	/**
	 * Converter.
	 *
	 * @param lista o valor de lista
	 * @return List
	 */
	public static List<InformacaoProfissionalVO> converter(List<InformacaoProfissional> lista) {
		
		List<InformacaoProfissionalVO> retorno = new ArrayList<InformacaoProfissionalVO>();
		
		if(lista != null) {
			for (InformacaoProfissional informacao : lista) {
				retorno.add(converter(informacao));
			}
		}
		
		return retorno;
	}

	/**
	 * Converter.
	 *
	 * @param informacao o valor de informacao
	 * @return InformacaoProfissionalVO
	 */
	public static InformacaoProfissionalVO converter(InformacaoProfissional informacao) {
		
		Pessoa empregador = informacao.getPessoaEmpregador();
		ConselhoRegional conselhoRegional = informacao.getConselhoRegional();
		TipoAfastamento tipoAfastamento = informacao.getTipoAfastamento();
		TipoFuncionario tipoFuncionario = informacao.getTipoFuncionario();
		
		InformacaoProfissionalVO vo = new InformacaoProfissionalVO();
		vo.setCargo(informacao.getCargo());
		vo.setCodSituacao(informacao.getCodSituacao());
		vo.setDataAdmissao(informacao.getDataAdmissao());
		vo.setDataDesligamento(informacao.getDataDesligamento());
		vo.setDataHoraInicio(informacao.getDataHoraInicio());
		vo.setIdInformacao(informacao.getIdInformacao());
		vo.setIdInstituicao(informacao.getIdInstituicao());
		vo.setIdPessoa(informacao.getPessoa().getIdPessoa());
		vo.setDiaMesFerias(informacao.getDiaMesFerias());
		vo.setNumeroInscricaoConselho(informacao.getNumeroInscricaoConselho());
		vo.setNumMatricula(informacao.getNumMatricula());
		vo.setUfConselho(informacao.getUfConselho());
		vo.setIdUsuarioAprovacao(informacao.getIdUsuarioAprovacao());

		if(tipoAfastamento != null) {
			vo.setCodigoTipoAfastamento(tipoAfastamento.getCodigo());
		}
		if(tipoFuncionario != null) {
			vo.setCodigoTipoFuncionario(tipoFuncionario.getCodigo());	
		}
		if(conselhoRegional != null) {
			vo.setCodigoConselhoRegional(conselhoRegional.getCodigo());
		}
		if(empregador != null) {
			vo.setCnpjPessoaEmpregador(empregador.getCpfCnpj());
			vo.setIdPessoaEmpregador(empregador.getId());
			vo.setNomePessoaEmpregador(empregador.getPessoaCompartilhamento().getNomePessoa());
		}
		
		return vo;
	}
	
	/**
	 * Obter id informacao.
	 *
	 * @param vo o valor de vo
	 * @return Integer
	 */
	public static Integer obterIdInformacao(InformacaoProfissionalVO vo) {
		return vo.getIdInformacao();
	}
	
	/**
	 * Converter.
	 *
	 * @param vo o valor de vo
	 * @return InformacaoProfissional
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public static InformacaoProfissional converter(InformacaoProfissionalVO vo)
	        throws BancoobException {
		
		InformacaoProfissional informacao = new InformacaoProfissional();
		
		informacao.setCargo(vo.getCargo());
		informacao.setCodSituacao(vo.getCodSituacao());
		informacao.setDataAdmissao(obterDateTimeDB(vo.getDataAdmissao()));
		informacao.setDataDesligamento(obterDateTimeDB(vo.getDataDesligamento()));
		informacao.setDataHoraInicio(obterDateTimeDB(vo.getDataHoraInicio()));
		informacao.setIdInformacao(vo.getIdInformacao());
		informacao.setIdInstituicao(vo.getIdInstituicao());
		informacao.setDiaMesFerias(vo.getDiaMesFerias());
		informacao.setNumeroInscricaoConselho(vo.getNumeroInscricaoConselho());
		informacao.setNumMatricula(vo.getNumMatricula());
		
		informacao.setConselhoRegional(
				ConversorConselho.obterConselho(vo.getCodigoConselhoRegional()));
		informacao.setUfConselho(vo.getUfConselho());
		
		informacao.setPessoa(obterPessoa(vo.getIdPessoa()));
		informacao.setPessoaEmpregador(obterPessoa(vo.getIdPessoaEmpregador()));
		informacao.setTipoAfastamento(
				ConversorTipoAfastamento.obterTipoAfastamento(vo.getCodigoTipoAfastamento()));
		informacao.setTipoFuncionario(
				ConversorTipoFuncionario.obterTipoFuncionario(vo.getCodigoTipoFuncionario()));
		return informacao;
	}	
	
	/**
	 * Obter pessoa.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @return Pessoa
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private static Pessoa obterPessoa(Integer idPessoa) throws BancoobException {
		Pessoa pessoa = null;
		if(idPessoa != null) {
			CAPESCadastroFabricaDelegates fabrica = CAPESCadastroFabricaDelegates.getInstance();
			PessoaDelegate pessoaDelegate = fabrica.criarPessoaDelegate();
			pessoa = pessoaDelegate.obter(idPessoa);
		}
		return pessoa;
	}
	
	/**
	 * Obter date time db.
	 *
	 * @param data o valor de data
	 * @return DateTimeDB
	 */
	private static DateTimeDB obterDateTimeDB(DateTime data) {
		
		DateTimeDB dataEntidade = null;
		
		if(data != null){
			dataEntidade = new DateTimeDB(data.getTime());
		}
		
		return dataEntidade;
	}
}
