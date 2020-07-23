/*
 * SICOOB
 * 
 * InclusaoEnderecoBeneficiarioINSS.java(br.com.sicoob.capes.processamento.negocio.dominio.inss.InclusaoEnderecoBeneficiarioINSS)
 */
package br.com.sicoob.capes.processamento.negocio.dominio.inss;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.EnderecoCorrespondenciaDelegate;
import br.com.sicoob.capes.cadastro.negocio.delegates.EnderecoDelegate;
import br.com.sicoob.capes.comum.negocio.enums.TipoEnderecoEnum;
import br.com.sicoob.capes.negocio.entidades.EnderecoCorrespondencia;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.vigente.Endereco;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;
import br.com.sicoob.capes.processamento.negocio.dominio.inss.builder.EnderecoBeneficiarioBuilder;
import br.com.sicoob.capes.processamento.negocio.dto.BeneficiarioDTO;
import br.com.sicoob.capes.processamento.negocio.excecao.EnderecoException;

/**
 * Inclui o endereço para o beneficiário, caso não exista nenhum.
 * @author Erico.Junior
 */
public final class InclusaoEnderecoBeneficiarioINSS {
	
	/** O atributo instance. */
	private static InclusaoEnderecoBeneficiarioINSS instance = new InclusaoEnderecoBeneficiarioINSS();
		
	/** O atributo endereco delegate. */
	private final transient EnderecoDelegate enderecoDelegate = 
			CAPESCadastroFabricaDelegates.getInstance().criarEnderecoDelegate();

	/** O atributo correspondencia delegate. */
	private final transient EnderecoCorrespondenciaDelegate correspondenciaDelegate = 
			CAPESCadastroFabricaDelegates.getInstance().criarEnderecoCorrespondenciaDelegate();
	
	/**
	 * Recupera uma instância única de InclusaoEnderecoBeneficiarioINSS.
	 * 
	 * @return uma intância única InclusaoEnderecoBeneficiarioINSS
	 */
	public static InclusaoEnderecoBeneficiarioINSS getInstance() {
		return instance;
	}

	/**
	 * Cria uma nova instância de inclusao endereco beneficiario inss.
	 */
	private InclusaoEnderecoBeneficiarioINSS() {
	}
	
	/**
	 * Inclui o endereço para o beneficiário caso não exista nenhum cadastrado.
	 * @param dto A dto com os dados do beneficiário.
	 * @param pessoa A pessoa cadastrada no capes.
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	public void incluir(BeneficiarioDTO dto, PessoaFisica pessoa)throws EnderecoException {

		try {
			Instituicao instituicao = INSSUtil.obterInstituicao(dto);
			if(existeEnderecos(pessoa)) {
				if(!existeEnderecoCorrespondencia(pessoa, instituicao)) {
					incluirEndereco(dto, pessoa, instituicao, TipoEnderecoEnum.OUTROS);
				}
			} else {
				incluirEndereco(dto, pessoa, instituicao, TipoEnderecoEnum.RESIDENCIAL);
			}
		} catch (BancoobException e) {
			throw new EnderecoException(e);
		}
	}
	
	/**
	 * Incluir endereco.
	 * 
	 * @param dto
	 *            the dto
	 * @param pessoa
	 *            the pessoa
	 * @param instituicao
	 *            the instituicao
	 * @param tipoEnderecoEnum
	 *            the tipo endereco enum
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	private void incluirEndereco(BeneficiarioDTO dto, PessoaFisica pessoa, Instituicao instituicao, 
			TipoEnderecoEnum tipoEnderecoEnum) throws BancoobException {
		
		EnderecoBeneficiarioBuilder enderecoBuilder = new EnderecoBeneficiarioBuilder();
		enderecoBuilder.criarEndereco(dto, pessoa);
		enderecoBuilder.criarTipoEndereco(tipoEnderecoEnum);
		Endereco endereco = enderecoBuilder.getEndereco();
		endereco.setVerificarAutorizacao(false);// o endereco ja entra aprovado
		enderecoDelegate.incluir(endereco, instituicao);		
	}
	
	/**
	 * Existe endereco correspondencia.
	 * 
	 * @param pessoa
	 *            the pessoa
	 * @param instituicao
	 *            the instituicao
	 * @return true, em caso de sucesso
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	private boolean existeEnderecoCorrespondencia(PessoaCompartilhamento pessoa, Instituicao instituicao) 
			throws BancoobException {
		EnderecoCorrespondencia correspondencia = correspondenciaDelegate.consultar(pessoa, instituicao);
		return correspondencia != null;

	}
	
	/**
	 * Existe enderecos.
	 * 
	 * @param pessoa
	 *            the pessoa
	 * @return true, em caso de sucesso
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	private boolean existeEnderecos(PessoaFisica pessoa) throws BancoobException{
		
		Endereco endereco = new Endereco();
		endereco.setPessoaCompartilhamento(pessoa);
		
		ConsultaDto<Endereco> criterios = new ConsultaDto<Endereco>();
		criterios.setFiltro(endereco);
		
		List<Endereco> enderecos = enderecoDelegate.listar(criterios);
		return enderecos != null && !enderecos.isEmpty();
	}	
	
}
