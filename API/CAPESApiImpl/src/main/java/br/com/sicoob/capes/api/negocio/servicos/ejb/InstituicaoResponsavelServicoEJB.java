/*
 * SICOOB
 * 
 * InstituicaoResponsavelServicoEJB.java(br.com.sicoob.capes.api.negocio.servicos.ejb.InstituicaoResponsavelServicoEJB)
 */
package br.com.sicoob.capes.api.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.apache.commons.lang.math.NumberUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.api.negocio.excecao.CAPESApiNegocioException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.InstituicaoResponsavelServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.InstituicaoResponsavelServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.InstituicaoResponsavelVO;
import br.com.sicoob.capes.api.persistencia.dao.CAPESApiDao;
import br.com.sicoob.capes.api.persistencia.dao.InstituicaoResponsavelDAO;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.negocio.entidades.Pessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaJuridica;
import br.com.sicoob.capes.negocio.entidades.vigente.ResponsavelCadastro;

/**
 * Serviço EJB que implementa a
 * {@link br.com.sicoob.capes.api.negocio.servicos.InstituicaoResponsavelServico}
 * 
 * @author Marcelo.Onofre
 * 
 */
@Stateless
@Local(InstituicaoResponsavelServicoLocal.class)
@Remote(InstituicaoResponsavelServicoRemote.class)
public class InstituicaoResponsavelServicoEJB extends CAPESApiServicoEJB implements InstituicaoResponsavelServicoRemote, InstituicaoResponsavelServicoLocal {

	@Inject
	@Default
	private InstituicaoResponsavelDAO instituicaoResponsavelDAO;
	
	/**
	 * Método que consulta um Responsável pelo CPF/CNPJ juntamente com o código
	 * de compartilhamento do cadastro
	 * 
	 * @param cpfCnpj
	 * 		CPF/CNPJ da pessoa
	 * @param codCompartilhamentoCadastro
	 * 		Código de compartilhamento de cadastro
	 * @return
	 * 		Retorna um {@link InstituicaoResponsavelVO}
	 * @throws BancoobException 
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public InstituicaoResponsavelVO obterByCpfCnpj(String cpfCnpj, Short codCompartilhamentoCadastro) throws BancoobException {
		validarObrigatoriedade(cpfCnpj, codCompartilhamentoCadastro);
		InstituicaoResponsavelVO instituicaoResponsavel = instituicaoResponsavelDAO.obterInstituicaoResponsavelPorCpfCnpj(obterFiltroConsulta(cpfCnpj, codCompartilhamentoCadastro));
		
		if(instituicaoResponsavel == null){
			throw new CAPESApiNegocioException("Responsável não encontrado");
		}
		
		return instituicaoResponsavel;
	}
	
	/**
	 * Validar obrigatoriedade.
	 * 
	 * @param cpfCnpj
	 *            the cpf cnpj
	 * @param codCompartilhamentoCadastro
	 *            the cod compartilhamento cadastro
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	private void validarObrigatoriedade(String cpfCnpj, Short codCompartilhamentoCadastro) throws BancoobException {
		if (cpfCnpj == null || cpfCnpj.trim().equals("")) {
			throw new BancoobException("CPF/CNPJ não informado.");
		}

		if (codCompartilhamentoCadastro == null || codCompartilhamentoCadastro < NumberUtils.INTEGER_ZERO) {
			throw new BancoobException("Código de compartilhamento do cadastro não informado.");
		}
	}

	/**
	 * Obter filtro consulta.
	 * 
	 * @param cpfCnpj
	 *            the cpf cnpj
	 * @param codCompartilhamentoCadastro
	 *            the cod compartilhamento cadastro
	 * @return consulta dto
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	private ConsultaDto<InstituicaoResponsavelVO> obterFiltroConsulta(String cpfCnpj, Short codCompartilhamentoCadastro) {
		InstituicaoResponsavelVO filtro = new InstituicaoResponsavelVO();
		filtro.setCpfCnpj(cpfCnpj);
		filtro.setCodCompartilhamentoCadastro(codCompartilhamentoCadastro);

		ConsultaDto<InstituicaoResponsavelVO> criterios = new ConsultaDto<InstituicaoResponsavelVO>();
		criterios.setFiltro(filtro);
		return criterios;
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public InstituicaoResponsavelVO obterPorIDPessoaCompartilhamento(Long idPessoaCompartilhamento, Short codTipoPessoa) throws BancoobException {
		InstituicaoResponsavelVO vo = null;
		PessoaCompartilhamento pessoaCompartilhamento = null;
		if (TipoPessoaEnum.isPessoaFisica(codTipoPessoa)) {
			pessoaCompartilhamento = new PessoaFisica();
		} else if (TipoPessoaEnum.isPessoaJuridica(codTipoPessoa)) {
			pessoaCompartilhamento = new PessoaJuridica();
		}

		if (pessoaCompartilhamento != null) {
			pessoaCompartilhamento.setId(idPessoaCompartilhamento);
		}

		ResponsavelCadastro responsavel = CAPESCadastroFabricaDelegates.getInstance().criarResponsavelCadastroDelegate().consultar(pessoaCompartilhamento);

		if (responsavel != null) {
			pessoaCompartilhamento = responsavel.getPessoaCompartilhamento();
			Pessoa pessoa = pessoaCompartilhamento.getPessoa();
			vo = new InstituicaoResponsavelVO(pessoa.getIdPessoa(), responsavel.getIdInstituicao());
			vo.setCodCompartilhamentoCadastro(pessoaCompartilhamento.getCodCompartilhamentoCadastro());
			vo.setCpfCnpj(pessoa.getCpfCnpj());
		}
		return vo;
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public InstituicaoResponsavelVO obterByCpfCnpj(String cpfCnpj) throws BancoobException {
		validarObrigatoriedade(cpfCnpj, NumberUtils.SHORT_ONE);
		InstituicaoResponsavelVO instituicaoResponsavel = instituicaoResponsavelDAO.obterInstituicaoResponsavelPorCpfCnpj(cpfCnpj);

		if (instituicaoResponsavel == null) {
			throw new CAPESApiNegocioException("Responsável não encontrado");
		}

		return instituicaoResponsavel;
	}

	@Override
	protected CAPESApiDao obterDAO() {
		return instituicaoResponsavelDAO;
	}

}