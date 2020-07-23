package br.com.sicoob.capes.processamento.persistencia.transformador.exportacaoarquivo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.processamento.negocio.dominio.exportacaoarquivo.vo.RegistroPessoaFisicaVO;
import br.com.sicoob.capes.processamento.negocio.dominio.exportacaoarquivo.vo.RegistroPessoaJuridicaVO;
import br.com.sicoob.capes.processamento.negocio.dominio.exportacaoarquivo.vo.RegistroPessoaVO;
import br.com.sicoob.capes.processamento.persistencia.transformador.TranformadorResultado;

/**
 * A Classe TransformadorRegistroPessoa.
 */
public class TransformadorRegistroPessoa extends TransformadorRegistroAbstrato implements TranformadorResultado<RegistroPessoaVO> {

	/**
	 * {@inheritDoc}
	 */
	public Collection<RegistroPessoaVO> transformar(ResultSet rs) throws BancoobException {
		List<RegistroPessoaVO> resultado = new ArrayList<RegistroPessoaVO>();
		try {
			while (rs.next()) {
				Short codigoTipoPessoa = rs.getShort("CODTIPOPESSOA");
				RegistroPessoaVO vo = null;

				if (TipoPessoaEnum.isPessoaFisica(codigoTipoPessoa)) {
					RegistroPessoaFisicaVO pessoaFisicaVO = new RegistroPessoaFisicaVO();

					pessoaFisicaVO.setDataNascimento(rs.getDate("DATANASCIMENTO"));
					pessoaFisicaVO.setMenorEmancipado(rs.getBoolean("BOLMENOREMANCIPADO"));
					pessoaFisicaVO.setNomePai(rs.getString("NOMEPAI"));
					pessoaFisicaVO.setNomeMae(rs.getString("NOMEMAE"));
					pessoaFisicaVO.setTipoDocumento(rs.getShort("CODTIPODOCUMENTOIDENTIFICACAO"));
					pessoaFisicaVO.setNumeroDocumento(rs.getString("NUMDOCUMENTOIDENTIFICACAO"));
					pessoaFisicaVO.setOrgaoEmissorDocumento(rs.getString("DESCORGAOEXPDOCUMENTOIDENTIFICACAO"));
					pessoaFisicaVO.setUfOrgaoEmissorDocumento(rs.getString("SIGLAUFORGEXPDOCUMENTOIDENTIFICACAO"));
					pessoaFisicaVO.setDataEmissaoDocumento(rs.getDate("DATAEMISSAODOCUMENTOIDENTIFICACAO"));
					pessoaFisicaVO.setEstadoCivil(rs.getShort("CODESTADOCIVIL"));
					pessoaFisicaVO.setSexo(rs.getString("CODTIPOSEXO").charAt(0));
					pessoaFisicaVO.setGrauInstrucao(rs.getShort("CODGRAUINSTRUCAO"));
					pessoaFisicaVO.setDependentes(rs.getShort("QTDDEPENDENTE"));
					pessoaFisicaVO.setRegimeCasamento(rs.getShort("REGIMECASAMENTO"));
					pessoaFisicaVO.setCodigoOcupacaoProfissional(rs.getString("CODOCUPACAO"));

					vo = pessoaFisicaVO;

				} else {
					RegistroPessoaJuridicaVO pessoaJuridicaVO = new RegistroPessoaJuridicaVO();

					pessoaJuridicaVO.setDataConstituicao(rs.getDate("DATACONSTITUICAO"));
					pessoaJuridicaVO.setValorCapitalSocial(rs.getBigDecimal("VALORCAPITALSOCIAL"));
					pessoaJuridicaVO.setInscricaoEstadual(rs.getString("NUMINSCRICAOESTADUAL"));
					pessoaJuridicaVO.setTipoEmpresa(rs.getShort("CODTIPOEMPRESA"));
					pessoaJuridicaVO.setFormaConstituicao(rs.getShort("CODTIPOFORMACONSTITUICAO"));
					pessoaJuridicaVO.setNumeroRegistroJuntaComercial(rs.getString("NUMREGISTROJUNTACOMERCIAL"));
					pessoaJuridicaVO.setDataRegistroJuntaComercial(rs.getDate("DATAREGISTROJUNTACOMERCIAL"));
					pessoaJuridicaVO.setNumeroUltimaAlteracaoContratual(rs.getString("NUMULTIMAALTERACAOCONTRATOSOCIAL"));
					pessoaJuridicaVO.setDataUltimaAlteracaoContratual(rs.getDate("DATAULTIMAALTERACAOCONTRATOSOCIAL"));

					vo = pessoaJuridicaVO;
				}

				vo.setIdPessoaCompartilhamento(rs.getLong("IDPESSOACOMPARTILHAMENTO"));
				vo.setCpfCnpj(rs.getString("NUMCPFCNPJ"));
				vo.setNome(rs.getString("NOMEPESSOA"));
				vo.setApelido(rs.getString("NOMEAPELIDO"));
				vo.setNomeCompleto(rs.getString("NOMECOMPLETO"));
				vo.setCnae(rs.getString("CODCNAE"));
				vo.setDataInclusao(rs.getDate("DATAINCLUSAOSISTEMA"));
				vo.setDataUltimaRenovacao(rs.getDate("DATARENOVACAOCADASTRAL"));
				vo.setDataInclusaoSFN(rs.getDate("DATAINCLUSAOSFN"));
				vo.setAutorizaConsultaBacen(rs.getBoolean("BOLAUTORIZACONSULTABACEN"));

				resultado.add(vo);
			}
		} catch (SQLException excecao) {
			throw new PersistenciaException(excecao);
		} finally {
			fecharResultSet(rs);
		}
		return resultado;
	}

}