import br.com.bancoob.dto.RequisicaoReqDTO;
import br.com.sicoob.capes.cadastrarRelacionamentoInstituicao.dto.RelacionamentoInstituicaoDTO;
import br.com.sicoob.capes.comum.enums.SituacaoCadastralRFBEnum;
import br.com.sicoob.capes.comum.enums.SituacaoRegistroEnum;
import br.com.sicoob.capes.comum.enums.TipoAutorizacaoEntidadeEnum;
import br.com.sicoob.capes.comum.vo.entidades.AtividadeEconomicaVO;
import br.com.sicoob.capes.comum.vo.entidades.CAPESEntidadeVO;
import br.com.sicoob.capes.comum.vo.entidades.CidadaniaVO;
import br.com.sicoob.capes.comum.vo.entidades.CnaeFiscalVO;
import br.com.sicoob.capes.comum.vo.entidades.CompartilhamentoCadastroVO;
import br.com.sicoob.capes.comum.vo.entidades.DocumentoComprobatorioVO;
import br.com.sicoob.capes.comum.vo.entidades.EnderecoFiscalVO;
import br.com.sicoob.capes.comum.vo.entidades.EntidadeCadastroBaseVO;
import br.com.sicoob.capes.comum.vo.entidades.EstadoCivilVO;
import br.com.sicoob.capes.comum.vo.entidades.FuncaoVO;
import br.com.sicoob.capes.comum.vo.entidades.GrauInstrucaoVO;
import br.com.sicoob.capes.comum.vo.entidades.InstituicaoVO;
import br.com.sicoob.capes.comum.vo.entidades.NacionalidadeVO;
import br.com.sicoob.capes.comum.vo.entidades.NucleoVO;
import br.com.sicoob.capes.comum.vo.entidades.OcupacaoProfissionalVO;
import br.com.sicoob.capes.comum.vo.entidades.PerfilCadastroVO;
import br.com.sicoob.capes.comum.vo.entidades.PerfilTarifarioVO;
import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
import br.com.sicoob.capes.comum.vo.entidades.PessoaFisicaVO;
import br.com.sicoob.capes.comum.vo.entidades.PessoaJuridicaVO;
import br.com.sicoob.capes.comum.vo.entidades.PessoaVO;
import br.com.sicoob.capes.comum.vo.entidades.RegimeCasamentoVO;
import br.com.sicoob.capes.comum.vo.entidades.TipoDocumentoIdentificacaoVO;
import br.com.sicoob.capes.comum.vo.entidades.TipoEmpresaVO;
import br.com.sicoob.capes.comum.vo.entidades.TipoFormaConstituicaoEsferaAdministrativaVO;
import br.com.sicoob.capes.comum.vo.entidades.TipoFormaConstituicaoVO;
import br.com.sicoob.capes.comum.vo.entidades.TipoPessoaVO;
import br.com.sicoob.capes.comum.vo.entidades.TransicaoPessoaVO;
import br.com.sicoob.capes.comum.vo.entidades.VinculoEmpregaticioVO;
import br.com.sicoob.capes.comum.vo.entidades.pk.CidadaniaPK;
import br.com.sicoob.capes.comum.vo.entidades.pk.EnderecoFiscalPK;
import br.com.sicoob.capes.comum.vo.entidades.pk.PerfilTarifarioPK;
import br.com.sicoob.capes.comum.vo.entidades.pk.TipoFormaConstituicaoEsferaAdministrativaPK;
import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;

import flash.net.registerClassAlias;

import org.granite.hibernate.HibernatePersistentBag;
import org.granite.hibernate.HibernatePersistentList;
import org.granite.hibernate.HibernatePersistentMap;
import org.granite.hibernate.HibernatePersistentSet;

new HibernatePersistentBag();
new HibernatePersistentList();
new HibernatePersistentMap();
new HibernatePersistentSet();

/*
* registra mapas e coleções do hibernate
*/
registerClassAlias("org.granite.hibernate.HibernatePersistentBag", HibernatePersistentBag);
registerClassAlias("org.granite.hibernate.HibernatePersistentList", HibernatePersistentList);
registerClassAlias("org.granite.hibernate.HibernatePersistentMap", HibernatePersistentMap);
registerClassAlias("org.granite.hibernate.HibernatePersistentSet", HibernatePersistentSet);
registerClassAlias("br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO", RequisicaoReqDTO);

/*
* Enums
*/
registerClassAlias("br.com.sicoob.capes.comum.negocio.enums.SituacaoCadastralRFBEnum", SituacaoCadastralRFBEnum);
registerClassAlias("br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum", TipoAutorizacaoEntidadeEnum);

/*
* DTOs 
*/
registerClassAlias("br.com.sicoob.capes.cadastro.negocio.dto.RelacionamentoInstituicaoDTO", RelacionamentoInstituicaoDTO);

/*
* Entidades do Corporativo.
*/
registerClassAlias("br.com.sicoob.capes.negocio.entidades.AtividadeEconomica", AtividadeEconomicaVO);
registerClassAlias("br.com.sicoob.capes.negocio.entidades.CAPESEntidade", CAPESEntidadeVO);
registerClassAlias("br.com.sicoob.capes.negocio.entidades.CnaeFiscal", CnaeFiscalVO);
registerClassAlias("br.com.sicoob.capes.negocio.entidades.EstadoCivil",	EstadoCivilVO);
registerClassAlias("br.com.sicoob.capes.negocio.entidades.GrauInstrucao", GrauInstrucaoVO);
registerClassAlias("br.com.sicoob.capes.negocio.entidades.Instituicao", InstituicaoVO);
registerClassAlias("br.com.sicoob.capes.negocio.entidades.Nacionalidade", NacionalidadeVO);
registerClassAlias("br.com.sicoob.capes.negocio.entidades.OcupacaoProfissional", OcupacaoProfissionalVO);
registerClassAlias("br.com.sicoob.capes.negocio.entidades.PerfilCadastro", PerfilCadastroVO);
registerClassAlias("br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica", PessoaFisicaVO);
registerClassAlias("br.com.sicoob.capes.negocio.entidades.vigente.PessoaJuridica", PessoaJuridicaVO);
registerClassAlias("br.com.sicoob.capes.negocio.entidades.Pessoa", PessoaVO);
registerClassAlias("br.com.sicoob.capes.negocio.entidades.RegimeCasamento", RegimeCasamentoVO);
registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoPessoa", TipoPessoaVO);
registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoDocumentoIdentificacao", TipoDocumentoIdentificacaoVO);
registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoEmpresa",	TipoEmpresaVO);
registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoFormaConstituicao",TipoFormaConstituicaoVO);
registerClassAlias("br.com.sicoob.capes.negocio.entidades.TransicaoPessoa", TransicaoPessoaVO);
registerClassAlias("br.com.sicoob.capes.negocio.entidades.VinculoEmpregaticio",	VinculoEmpregaticioVO);
registerClassAlias("br.com.sicoob.capes.negocio.entidades.DocumentoComprobatorio", DocumentoComprobatorioVO);
registerClassAlias("br.com.sicoob.capes.negocio.entidades.EntidadeCadastroBase", EntidadeCadastroBaseVO);
registerClassAlias("br.com.sicoob.capes.negocio.entidades.CompartilhamentoCadastro", CompartilhamentoCadastroVO);
registerClassAlias("br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento", PessoaCompartilhamentoVO);
registerClassAlias("br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO", PessoaPlataformaVO);
registerClassAlias("br.com.sicoob.capes.negocio.entidades.pk.PerfilTarifarioPK", PerfilTarifarioPK);
registerClassAlias("br.com.sicoob.capes.negocio.entidades.PerfilTarifario", PerfilTarifarioVO);

registerClassAlias("br.com.sicoob.capes.negocio.entidades.pk.TipoFormaConstituicaoEsferaAdministrativaPK", TipoFormaConstituicaoEsferaAdministrativaPK);
registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoFormaConstituicaoEsferaAdministrativa", TipoFormaConstituicaoEsferaAdministrativaVO);

registerClassAlias("br.com.sicoob.capes.negocio.entidades.pk.CidadaniaPK", CidadaniaPK);
registerClassAlias("br.com.sicoob.capes.negocio.entidades.Cidadania", CidadaniaVO);

registerClassAlias("br.com.sicoob.capes.negocio.entidades.pk.EnderecoFiscalPK", EnderecoFiscalPK);
registerClassAlias("br.com.sicoob.capes.negocio.entidades.EnderecoFiscal", EnderecoFiscalVO);

registerClassAlias("br.com.sicoob.capes.negocio.entidades.Funcao", FuncaoVO);
registerClassAlias("br.com.sicoob.capes.negocio.entidades.Nucleo", NucleoVO);

/**
 * Enum da situação do registro 
 **/
registerClassAlias("br.com.sicoob.capes.comum.negocio.enums.SituacaoRegistroEnum", SituacaoRegistroEnum);
