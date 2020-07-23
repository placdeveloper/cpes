/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dominio.validacao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.validacao.Validacao;
import br.com.bancoob.validacao.ValidacaoCpf;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.RelacionamentoPessoaDelegate;
import br.com.sicoob.capes.cadastro.negocio.enums.EstadoCivilEnum;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoRelacionamentoPessoaEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoSexoEnum;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.TipoRelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;

/**
 * Classe utilizada na validação dos dados de entrada de pessoa física.
 * 
 * @author juan.damasceno
 */
public class ValidacaoCadastroPessoaFisica extends ValidacaoCadastroPessoa<PessoaFisica> {

	/**
	 * Instancia um novo ValidacaoCadastroPessoaFisica.
	 *
	 * @param instituicao o valor de instituicao
	 */
	public ValidacaoCadastroPessoaFisica(Instituicao instituicao) {
		super(instituicao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getNomeAtributoDocumento() {
		return "CPF";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getNomeAtributoNome() {
		return "Nome";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getNomeAtributoNomeCompleto() {
		return "Nome Completo";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void validarObrigatoriedadeCondicionalEspecifica(PessoaFisica pessoa)
			throws BancoobException {
		
		if (pessoa.getDataNascimento() == null) {
			adicionarFalha("Data de nascimento");
		} else {
			Integer idade = obterIdade(pessoa.getDataNascimento());
			if (idade < 18 && pessoa.getMenorEmancipado() != null && !pessoa.getMenorEmancipado()) {
				List<RelacionamentoPessoa> lista = listarRelacionamento(pessoa,
						TipoRelacionamentoPessoaEnum.RESPONSAVEL_LEGAL.getCodigo());
				if (lista.isEmpty()) {
					adicionarFalha("Responsável legal");
				}
			}
		}
		
		Character sexo = pessoa.getTipoSexo();
		if(!TipoSexoEnum.MASCULINO.getCodigo().equals(sexo) && !TipoSexoEnum.FEMININO.getCodigo().equals(sexo)) {
			adicionarFalha("Sexo");
		}
		
		if (pessoa.getNacionalidade() == null){
			adicionarFalha("Nacionalidade");
		}

		if ((pessoa.getIdNaturalidade() == null || pessoa.getIdNaturalidade() == 0) 
				&& !isDescNaturalidadePreenchido(pessoa)) {
			adicionarFalha("Naturalidade");
		}
		
		if (pessoa.getTipoDocumento() == null) {
			adicionarFalha("Tipo de Documento");
		}
		
		if (StringUtils.isBlank(pessoa.getNumeroDocumento())) {
			adicionarFalha("Nº do Documento");
		}
		
		if (StringUtils.isBlank(pessoa.getOrgaoExpedidorDocumento())) {
			adicionarFalha("Órgão Expedidor");
		}
		
		if (StringUtils.isBlank(pessoa.getUfOrgaoExpedidorDocumento())) {
			adicionarFalha("UF");
		}
		
		if (pessoa.getDataEmissaoDocumento() == null) {
			adicionarFalha("Data emissão do documento");
		}
		
		if (StringUtils.isBlank(pessoa.getNomePai())) {
			adicionarFalha("Nome do pai");
		}
		
		if (StringUtils.isBlank(pessoa.getNomeMae())) {
			adicionarFalha("Nome da mãe");
		}
		
		if (pessoa.getVinculoEmpregaticio() == null) {
			adicionarFalha("Vínculo empregatício");
		}
		
		if (pessoa.getOcupacaoProfissional() == null) {
			adicionarFalha("Profissão");
		}	
		
		if (pessoa.getGrauInstrucao() == null){
			adicionarFalha("Grau de instrução");
		}
		
		if (pessoa.getEstadoCivil() == null) {
			adicionarFalha("Estado civíl");
		} else if (EstadoCivilEnum.CASADO.getCodigo().equals(pessoa.getEstadoCivil().getCodigo())
				|| (EstadoCivilEnum.UNIAO_ESTAVEL.getCodigo().equals(pessoa.getEstadoCivil().getCodigo()))) {
			
			if(pessoa.getConjuge() == null){
				adicionarFalha("Cônjuge");
			}
		}
	}

	/**
	 * Verifica se eh desc naturalidade preenchido.
	 *
	 * @param pessoa o valor de pessoa
	 * @return {@code true}, se for desc naturalidade preenchido
	 */
	private boolean isDescNaturalidadePreenchido(PessoaFisica pessoa) {
		String descNaturalidade = pessoa.getDescNaturalidade();
		return descNaturalidade != null && StringUtils.isNotBlank(descNaturalidade);
	}

	/**
	 * Listar relacionamento.
	 *
	 * @param pessoa o valor de pessoa
	 * @param codTipoRelacionamento o valor de cod tipo relacionamento
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<RelacionamentoPessoa> listarRelacionamento(PessoaFisica pessoa, Short codTipoRelacionamento)
			throws BancoobException {
		RelacionamentoPessoaDelegate relacionamentoPessoaDelegate = 
				obterFabricaDelegate().criarRelacionamentoPessoaDelegate();
		ConsultaDto<RelacionamentoPessoa> criterios = new ConsultaDto<RelacionamentoPessoa>();
		RelacionamentoPessoa filtro = new RelacionamentoPessoa();
		
		filtro.setPessoa(pessoa.getPessoa());
		filtro.setIdInstituicao(getInstituicao().getIdInstituicao());
		TipoRelacionamentoPessoa tipoRelacionamento = new TipoRelacionamentoPessoa();
		tipoRelacionamento.setCodigo(codTipoRelacionamento);
		filtro.setTipoRelacionamento(tipoRelacionamento);
		criterios.setFiltro(filtro);
		
		return relacionamentoPessoaDelegate.listar(criterios);
	}

	/**
	 * Obter fabrica delegate.
	 *
	 * @return CAPESCadastroFabricaDelegates
	 */
	private CAPESCadastroFabricaDelegates obterFabricaDelegate() {
		return CAPESCadastroFabricaDelegates.getInstance();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Validacao obterValidacaoDocumento(String cpfCnpj) {
		return new ValidacaoCpf(cpfCnpj, "", getNomeAtributoDocumento());
	}
	
	/**
	 * Obter idade.
	 *
	 * @param data o valor de data
	 * @return Integer
	 */
	public static Integer obterIdade(Date data) {  
	    Calendar dataNascimento = Calendar.getInstance();  
	    dataNascimento.setTime(data);  
	    Calendar dataAtual = Calendar.getInstance();  
	  
	    Integer diferencaMes = dataAtual.get(Calendar.MONTH) - dataNascimento.get(Calendar.MONTH);  
	    Integer diferencaDia = dataAtual.get(Calendar.DAY_OF_MONTH) - dataNascimento.get(Calendar.DAY_OF_MONTH);  
	    Integer idade = (dataAtual.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR));  
	  
	    if(diferencaMes < 0  || (diferencaMes == 0 && diferencaDia < 0)) {  
	        idade--;  
	    }  
	      
	    return idade;  
	}  
}