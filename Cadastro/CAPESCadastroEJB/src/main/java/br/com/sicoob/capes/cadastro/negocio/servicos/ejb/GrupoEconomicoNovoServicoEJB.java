/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoRelacionamentoPessoaEnum;
import br.com.sicoob.capes.cadastro.negocio.excecao.CAPESCadastroNegocioException;
import br.com.sicoob.capes.cadastro.negocio.excecao.CampoNaoInformadoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.PessoaEmOutroGrupoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.RegistroMesmoAtributoException;
import br.com.sicoob.capes.cadastro.negocio.excecao.RegistroMesmoAtributoException.TipoMensagem;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoEconomicoAutomaticoPessoaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoEconomicoInstituicaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoEconomicoManualPessoaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoEconomicoNovoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoEconomicoNovoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.GrupoEconomicoNovoDAO;
import br.com.sicoob.capes.comum.negocio.contexto.InformacoesUsuarioCAPES;
import br.com.sicoob.capes.comum.negocio.enums.TipoGrupoEconomicoEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoAutomaticoPessoa;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoInstituicao;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoManualPessoa;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoNovo;
import br.com.sicoob.capes.negocio.entidades.TipoGrupoEconomico;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;

/**
 * 
 * @author Paulo.Stoppa
 *
 */
@Stateless
@Local({ GrupoEconomicoNovoServicoLocal.class })
@Remote({ GrupoEconomicoNovoServicoRemote.class })
public class GrupoEconomicoNovoServicoEJB extends CAPESCadastroCrudServicoEJB<GrupoEconomicoNovo>
		implements GrupoEconomicoNovoServicoLocal, GrupoEconomicoNovoServicoRemote {

	@Inject
	@Default
	private GrupoEconomicoNovoDAO grupoEconomicoNovoDAO;

	@EJB
	private GrupoEconomicoAutomaticoPessoaServicoLocal grupoEconomicoAutomaticoPessoaServico;

	@EJB
	private GrupoEconomicoManualPessoaServicoLocal grupoEconomicoManualPessoaServico;

	@EJB
	private GrupoEconomicoInstituicaoServicoLocal grupoEconomicoInstituicaoServico;

	private static final BigDecimal PERCENTUAL_CONTROLADOR = new BigDecimal(50);

	/**
	 * {@inheritDoc}
	 */
	public void alterar(GrupoEconomicoNovo objeto) throws BancoobException {
		validarAlteracao(objeto);
		final DateTimeDB dataCadastro = new DateTimeDB();
		objeto.setDataHoraInicio(dataCadastro);
		if (CollectionUtils.isNotEmpty(objeto.getIntegrantesManual())) {
			for (GrupoEconomicoManualPessoa grupoPessoa : objeto.getIntegrantesManual()) {
				if (grupoPessoa.getId() == null) {
					grupoPessoa.setDataHoraInicio(dataCadastro);
					grupoPessoa.setIdUsuarioCadastro(InformacoesUsuarioCAPES.getInstance().getLogin());
				}
			}
		}
		if (CollectionUtils.isNotEmpty(objeto.getIntegrantesManualExclusao())) {
			for (GrupoEconomicoManualPessoa grupoPessoa : objeto.getIntegrantesManualExclusao()) {
				grupoPessoa.setIdUsuarioExclusao(InformacoesUsuarioCAPES.getInstance().getLogin());
				grupoEconomicoManualPessoaServico.excluirEntidade(grupoPessoa);
			}
		}
		super.alterar(objeto);
	}

	/**
	 * {@inheritDoc}
	 */
	public void excluir(RelacionamentoPessoa relacionamento) throws BancoobException {
		if (isTipoRelacionamentoGrupoEconomico(relacionamento)) {
			final PessoaCompartilhamento pessoa = relacionamento.getPessoaCompartilhamento();
			final PessoaCompartilhamento pessoaRelacionada = relacionamento.getPessoaRelacionada()
					.getPessoaCompartilhamentoPorGrupo(pessoa.getCodCompartilhamentoCadastro());
			excluirGrupoAutomaticoPessoa(relacionamento, pessoa, pessoaRelacionada);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void excluirEntidade(GrupoEconomicoNovo objeto) throws BancoobException {
		GrupoEconomicoNovo novo = obter(objeto.getId());
		novo.setMotivoExclusao(objeto.getMotivoExclusao());
		/*
		 * Ajustes para exlusão de grupo manual
		 */
		if (novo.getTipo().getId().equals(TipoGrupoEconomicoEnum.MANUAL.getCodigo())) {
			novo.setIdUsuarioExclusao(InformacoesUsuarioCAPES.getInstance().getLogin());
			for (GrupoEconomicoManualPessoa integrante : novo.getIntegrantesManual()) {
				integrante.setIdUsuarioExclusao(novo.getIdUsuarioExclusao());
			}
			final List<GrupoEconomicoInstituicao> instituicoes = listarGrupoEconomicoInstituicao(novo.getId());
			for (GrupoEconomicoInstituicao instituicao : instituicoes) {
				grupoEconomicoInstituicaoServico.excluirEntidade(instituicao);
			}
		}
		super.excluirEntidade(novo);
	}

	/**
	 * {@inheritDoc}
	 */
	public GrupoEconomicoNovo gravar(RelacionamentoPessoa relacionamento) throws BancoobException {
		GrupoEconomicoNovo grupoEconomico = null;
		if (isTipoRelacionamentoGrupoEconomico(relacionamento)) {
			final PessoaCompartilhamento pessoa = relacionamento.getPessoaCompartilhamento();
			final PessoaCompartilhamento pessoaRelacionada = relacionamento.getPessoaRelacionada()
					.getPessoaCompartilhamentoPorGrupo(pessoa.getCodCompartilhamentoCadastro());
			if (isPessoaRelacionadaControladora(relacionamento)) {// Quando o relacionamento possui controlado
				GrupoEconomicoAutomaticoPessoa grupoEconomicoPessoaRelacionada = obterGrupoEconomicoAutomatico(pessoaRelacionada, relacionamento);
				GrupoEconomicoAutomaticoPessoa grupoEconomicoPessoa = obterGrupoEconomicoAutomatico(pessoa, relacionamento);
				if (grupoEconomicoPessoaRelacionada != null
						&& grupoEconomicoPessoa != null) { /* Quando o relacionamento já possui pessoas cadastradas no grupo */
					getLogger().debug("Fluxo de atualização de relacionamento de grupo");
					if (grupoEconomicoPessoaRelacionada.getBolControlador()) { /*
																				 * quando a pessoa cadastrada no grupo é controladora, deve
																				 * atualizar o participação societário
																				 */
						grupoEconomicoPessoaRelacionada.setPercentualSocio(relacionamento.getPercentualCapitalEmpresa());
						grupoEconomicoAutomaticoPessoaServico.alterar(grupoEconomicoPessoaRelacionada);
					} else { // quando a pessoa não é controladora, nenhuma ação será tomada
						grupoEconomico = grupoEconomicoPessoaRelacionada.getGrupoEconomico();
					}
				} else {/* Quando o relacionamento não possui pessoas cadastradas no grupo */
					getLogger().debug("Fluxo de inclusão de relacionamento de grupo");
					grupoEconomico = incluirGrupoEconomicoAutomatico(relacionamento, pessoa, pessoaRelacionada);
				}
			} else {// quando o relacionamento não possui controlador, deve entrar no fluxo de exclusão
				getLogger().debug("Fluxo de exclusão de relacionamento de grupo");
				excluirGrupoAutomaticoPessoa(relacionamento, pessoa, pessoaRelacionada);
			}
		}
		return grupoEconomico;
	}

	/**
	 * {@inheritDoc}
	 */
	public GrupoEconomicoNovo incluir(GrupoEconomicoNovo objeto) throws BancoobException {
		validarInclusao(objeto);
		final DateTimeDB dataCadastro = new DateTimeDB();
		objeto.setDataHoraCadastro(dataCadastro);
		objeto.setDataHoraInicio(dataCadastro);
		if (CollectionUtils.isNotEmpty(objeto.getIntegrantesManual())) {
			for (GrupoEconomicoManualPessoa grupoPessoaManual : objeto.getIntegrantesManual()) {
				grupoPessoaManual.setDataHoraInicio(dataCadastro);
				grupoPessoaManual.setIdUsuarioCadastro(objeto.getIdUsuarioCadastro());
			}
		}
		final GrupoEconomicoNovo retorno = super.incluir(objeto);
		if (objeto.getTipo().getId().equals(TipoGrupoEconomicoEnum.MANUAL.getCodigo())) {
			final GrupoEconomicoInstituicao instituicao = new GrupoEconomicoInstituicao(objeto.getIdInstituicao());
			instituicao.setDataHoraInicio(dataCadastro);
			instituicao.setIdGrupo(retorno.getId());
			grupoEconomicoInstituicaoServico.incluir(instituicao);
		}
		return retorno;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<GrupoEconomicoNovo> listarPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) {
		return getDAO().listarPorPessoaInstituicao(idPessoa, idInstituicao);
	}

	/**
	 * {@inheritDoc}
	 */
	public void validarPessoa(final GrupoEconomicoNovo grupoEconomico, final PessoaCompartilhamento pessoa) throws BancoobException {
		final GrupoEconomicoAutomaticoPessoa grupoAutomaticoPessoa = obterGrupoEconomicoAutomatico(pessoa);
		List<GrupoEconomicoManualPessoa> grupos = listarGrupoEconomicoManual(pessoa, grupoEconomico.getIdInstituicao());

		/*
		 * Limpa a lista de grupos, removendo os grupos de outras instituicoes e o grupo recebido como parametro
		 */
		for (Iterator<GrupoEconomicoManualPessoa> it = grupos.iterator(); it.hasNext();) {
			GrupoEconomicoNovo grupo = it.next().getGrupoEconomico();
			if (grupo.getId().equals(grupoEconomico.getId())) {
				it.remove();
			}
		}

		// para ser valida, apos a limpeza a lista de grupo deve estar vazia
		if (!grupos.isEmpty()) {
			throw new PessoaEmOutroGrupoException("MN217", grupos.get(0).getGrupoEconomico().getNome(), null,
					grupos.get(0).getGrupoEconomico().getTipo());
		} else if (grupoAutomaticoPessoa != null) {
			List<GrupoEconomicoManualPessoa> gruposAuto = listarGrupoEconomicoManual(grupoAutomaticoPessoa.getGrupoEconomico().getId(),
					grupoEconomico.getIdInstituicao());
			if (CollectionUtils.isNotEmpty(gruposAuto)) {
				throw new PessoaEmOutroGrupoException("MN217", gruposAuto.get(0).getGrupoEconomico().getNome(), null,
						gruposAuto.get(0).getGrupoEconomico().getTipo());
			}
			throw new PessoaEmOutroGrupoException("MN218", grupoAutomaticoPessoa.getGrupoEconomico().getNome(),
					grupoAutomaticoPessoa.getGrupoEconomico(), grupoAutomaticoPessoa.getGrupoEconomico().getTipo());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected GrupoEconomicoNovoDAO getDAO() {
		return grupoEconomicoNovoDAO;
	}

	/**
	 * 
	 * @param grupoPessoa
	 * @param index
	 */
	private void ajustarControlador(GrupoEconomicoAutomaticoPessoa grupoPessoa, int index) {
		final Boolean controlador = index < 2 && isControlador(grupoPessoa.getRelacionamentoPessoa(), grupoPessoa.getPessoaCompartilhamento());
		grupoPessoa.setBolControlador(controlador);
		if (controlador) {
			grupoPessoa.setPercentualSocio(grupoPessoa.getRelacionamentoPessoa().getPercentualCapitalEmpresa());
		} else {
			grupoPessoa.setPercentualSocio(null);
		}
	}

	/**
	 * 
	 * @param grupoEconomico
	 * @throws BancoobException
	 */
	private void ajustarGrupo(GrupoEconomicoNovo grupoEconomico) throws BancoobException {
		getLogger().debug("Ajustando nome do Grupo e Elegendo Controlador");
		GrupoEconomicoAutomaticoPessoa filtro = new GrupoEconomicoAutomaticoPessoa();
		filtro.setGrupoEconomico(grupoEconomico);
		ConsultaDto<GrupoEconomicoAutomaticoPessoa> criterios = new ConsultaDto<>();
		criterios.setOrdenacao("elegerControlador");
		criterios.setProcurarPor("pessoaRelacionada");
		criterios.setFiltro(filtro);
		List<GrupoEconomicoAutomaticoPessoa> pessoas = grupoEconomicoAutomaticoPessoaServico.listar(criterios);
		GrupoEconomicoAutomaticoPessoa controlador = pessoas.get(0);
		controlador.setBolControlador(true);
		controlador.setPercentualSocio(controlador.getRelacionamentoPessoa().getPercentualCapitalEmpresa());
		final PessoaCompartilhamento pessoa = controlador.getRelacionamentoPessoa().getPessoaCompartilhamento();
		final PessoaCompartilhamento pessoaRelacionada = controlador.getRelacionamentoPessoa().getPessoaRelacionada()
				.getPessoaCompartilhamentoPorGrupo(pessoa.getCodCompartilhamentoCadastro());
		controlador.getGrupoEconomico().setNome(gerarNome(controlador.getRelacionamentoPessoa(), pessoa, pessoaRelacionada));
		grupoEconomicoAutomaticoPessoaServico.alterar(controlador);
	}

	/**
	 * 
	 * @param relacionamento
	 * @param pessoaRelacionada 
	 * @param pessoa 
	 * @return
	 * @throws BancoobException
	 */
	private GrupoEconomicoNovo criarGrupoEconomicoAutomatico(RelacionamentoPessoa relacionamento, PessoaCompartilhamento pessoa, PessoaCompartilhamento pessoaRelacionada) throws BancoobException {
		final GrupoEconomicoNovo grupoEconomico = new GrupoEconomicoNovo();
		grupoEconomico.setNome(gerarNome(relacionamento, pessoa, pessoaRelacionada));
		TipoGrupoEconomico tipo = new TipoGrupoEconomico();
		tipo.setCodigo(TipoGrupoEconomicoEnum.AUTOMATICO.getCodigo());
		grupoEconomico.setTipo(tipo);
		grupoEconomico.setIdUsuarioCadastro(obterUsuarioRelacionamento(relacionamento));
		return incluir(grupoEconomico);
	}

	/**
	 * 
	 * @param grupoEconomico
	 * @param pessoasExistentes
	 * @param controladorRemovido 
	 * @throws BancoobException
	 */
	private void desmembrarGrupo(GrupoEconomicoNovo grupoEconomico, List<GrupoEconomicoAutomaticoPessoa> pessoasExistentes, boolean controladorRemovido) throws BancoobException {
		final List<GrupoEconomicoAutomaticoPessoa> tupla1 = new ArrayList<>();
		final List<GrupoEconomicoAutomaticoPessoa> tupla2 = new ArrayList<>();
		final Map<String, GrupoEconomicoAutomaticoPessoa> mapPessoas = new LinkedHashMap<>(pessoasExistentes.size());
		// Montar MAP's
		for (GrupoEconomicoAutomaticoPessoa grupoPessoa : pessoasExistentes) {
			StringBuilder builder = new StringBuilder();
			builder.append(grupoPessoa.getRelacionamentoPessoa().getId());
			builder.append("-");
			builder.append(grupoPessoa.getPessoaCompartilhamento().getId());
			mapPessoas.put(builder.toString(), grupoPessoa);
		}

		final Entry<String, GrupoEconomicoAutomaticoPessoa> entry1 = getFirst(mapPessoas);
		gerarTupla(entry1, mapPessoas, tupla1);
		
		final Entry<String, GrupoEconomicoAutomaticoPessoa> entry2 = getFirst(mapPessoas);
		if(entry2 != null) {
			gerarTupla(entry2, mapPessoas, tupla2);

			desmembrarGrupo(tupla1, tupla2);
			grupoEconomico.setMotivoExclusao("Grupo Desmembrado");
			excluirEntidade(grupoEconomico);
		} else if(controladorRemovido) {
			ajustarGrupo(grupoEconomico);
		}
	}

	private <K, V> Entry<K, V> getFirst(Map<K, V> map) {
		if (map.isEmpty())
			return null;
		return map.entrySet().iterator().next();
	}

	private void gerarTupla(final Entry<String, GrupoEconomicoAutomaticoPessoa> entry, final Map<String, GrupoEconomicoAutomaticoPessoa> mapPessoas,
			final List<GrupoEconomicoAutomaticoPessoa> tupla) {
		GrupoEconomicoAutomaticoPessoa grupoPessoa = mapPessoas.remove(entry.getKey());
		if (grupoPessoa != null) {
			tupla.add(grupoPessoa);
			List<Entry<String, GrupoEconomicoAutomaticoPessoa>> gruposPessoaMesmoRelacionamento = mapPessoas.entrySet().stream()
					.filter(e -> e.getKey().startsWith(entry.getKey().substring(0, entry.getKey().indexOf("-") + 1))).collect(Collectors.toList());
			if (!gruposPessoaMesmoRelacionamento.isEmpty()) {
				gerarTupla(gruposPessoaMesmoRelacionamento.get(0), mapPessoas, tupla);
			}
			List<Entry<String, GrupoEconomicoAutomaticoPessoa>> gruposMesmaPessoa = mapPessoas.entrySet().stream()
					.filter(e -> e.getKey().endsWith(entry.getKey().substring(entry.getKey().indexOf("-")))).collect(Collectors.toList());
			for (Entry<String, GrupoEconomicoAutomaticoPessoa> eMesmaPesssoa : gruposMesmaPessoa) {
				gerarTupla(eMesmaPesssoa, mapPessoas, tupla);
			}
		}
	}

	/**
	 * 
	 * @param pessoasGrupo1
	 * @param pessoasGrupo2
	 * @throws BancoobException
	 */
	private void desmembrarGrupo(List<GrupoEconomicoAutomaticoPessoa> pessoasGrupo1, List<GrupoEconomicoAutomaticoPessoa> pessoasGrupo2)
			throws BancoobException {
		final RelacionamentoPessoa relacionamento1 = pessoasGrupo1.get(0).getRelacionamentoPessoa();
		final RelacionamentoPessoa relacionamento2 = pessoasGrupo2.get(0).getRelacionamentoPessoa();
		final PessoaCompartilhamento pessoa1 = relacionamento1.getPessoaCompartilhamento();
		final PessoaCompartilhamento pessoaRelacionada1 = relacionamento1.getPessoaRelacionada()
				.getPessoaCompartilhamentoPorGrupo(pessoa1.getCodCompartilhamentoCadastro());
		final PessoaCompartilhamento pessoa2 = relacionamento2.getPessoaCompartilhamento();
		final PessoaCompartilhamento pessoaRelacionada2 = relacionamento2.getPessoaRelacionada()
				.getPessoaCompartilhamentoPorGrupo(pessoa2.getCodCompartilhamentoCadastro());
		
		final GrupoEconomicoNovo grupo1 = criarGrupoEconomicoAutomatico(relacionamento1, pessoa1, pessoaRelacionada1);
		final GrupoEconomicoNovo grupo2 = criarGrupoEconomicoAutomatico(relacionamento2, pessoa2, pessoaRelacionada2);
		for (int i = 0; i < pessoasGrupo1.size(); i++) {
			GrupoEconomicoAutomaticoPessoa grupoPessoa = pessoasGrupo1.get(i);
			grupoPessoa.setGrupoEconomico(grupo1);
			ajustarControlador(grupoPessoa, i);
			grupoEconomicoAutomaticoPessoaServico.alterar(grupoPessoa);
		}
		for (int i = 0; i < pessoasGrupo2.size(); i++) {
			GrupoEconomicoAutomaticoPessoa grupoPessoa = pessoasGrupo2.get(i);
			grupoPessoa.setGrupoEconomico(grupo2);
			ajustarControlador(grupoPessoa, i);
			grupoEconomicoAutomaticoPessoaServico.alterar(grupoPessoa);
		}
	}

	/**
	 * @param grupoEconomicoPessoaRelacionada
	 * @throws BancoobException
	 */
	private void excluirGrupoAutomaticoPessoa(GrupoEconomicoAutomaticoPessoa grupoEconomicoPessoa) throws BancoobException {
		if (grupoEconomicoPessoa != null) {
			grupoEconomicoPessoa.setIdUsuarioExclusao(grupoEconomicoPessoa.getRelacionamentoPessoa().getIdUsuarioEnvio());
			grupoEconomicoAutomaticoPessoaServico.excluirEntidade(grupoEconomicoPessoa);
		}
	}

	/**
	 * @param relacionamento
	 * @param pessoaRelacionada
	 * @param pessoa
	 * @throws BancoobException
	 */
	private void excluirGrupoAutomaticoPessoa(RelacionamentoPessoa relacionamento, PessoaCompartilhamento pessoa,
			PessoaCompartilhamento pessoaRelacionada) throws BancoobException {
		final GrupoEconomicoAutomaticoPessoa grupoEconomicoPessoaRelacionada = obterGrupoEconomicoAutomatico(pessoaRelacionada, relacionamento);
		final GrupoEconomicoAutomaticoPessoa grupoEconomicoPessoa = obterGrupoEconomicoAutomatico(pessoa, relacionamento);
		excluirGrupoAutomaticoPessoa(grupoEconomicoPessoaRelacionada);
		excluirGrupoAutomaticoPessoa(grupoEconomicoPessoa);
		final boolean existenciaGrupo;
		final GrupoEconomicoNovo grupoEconomico;
		final List<GrupoEconomicoAutomaticoPessoa> pessoasExistentes;
		if (grupoEconomicoPessoaRelacionada != null) {
			grupoEconomico = grupoEconomicoPessoaRelacionada.getGrupoEconomico();
			pessoasExistentes = listarGrupoEconomicoAutomatico(grupoEconomico, null, null, "elegerControlador");
			existenciaGrupo = validarExistenciaGrupoAutomatico(grupoEconomico, pessoasExistentes,
					grupoEconomicoPessoaRelacionada.getRelacionamentoPessoa().getIdUsuarioEnvio());
		} else if (grupoEconomicoPessoa != null) {
			grupoEconomico = grupoEconomicoPessoa.getGrupoEconomico();
			pessoasExistentes = listarGrupoEconomicoAutomatico(grupoEconomico, null, null, "elegerControlador");
			existenciaGrupo = validarExistenciaGrupoAutomatico(grupoEconomico, pessoasExistentes,
					grupoEconomicoPessoa.getRelacionamentoPessoa().getIdUsuarioEnvio());
		} else {
			existenciaGrupo = false;
			grupoEconomico = null;
			pessoasExistentes = null;
		}
		if (existenciaGrupo) {
			desmembrarGrupo(grupoEconomico, pessoasExistentes, (grupoEconomicoPessoaRelacionada != null && grupoEconomicoPessoaRelacionada.getBolControlador()));
		}
	}

	/**
	 * 
	 * @param relacionamento
	 * @param pessoaRelacionada 
	 * @param pessoa 
	 * @return
	 * @throws BancoobException
	 */
	private String gerarNome(RelacionamentoPessoa relacionamento, PessoaCompartilhamento pessoa, PessoaCompartilhamento pessoaRelacionada) throws BancoobException {
		final String nomeBase;
		if (pessoaRelacionada.getPessoa().getTipoPessoa().getId().equals(TipoPessoaEnum.PESSOA_JURIDICA.getCodigo())) {
			nomeBase = pessoaRelacionada.getNomePessoa();
		} else {
			nomeBase = pessoa.getNomePessoa();
		}

		int contador = 1;
		String nome = nomeBase;
		while (obterGrupoPorNome(nome, TipoGrupoEconomicoEnum.AUTOMATICO, "igual") != null) {
			StringBuilder builder = new StringBuilder();
			builder.append(nomeBase);
			builder.append(" ");
			builder.append(contador++);
			nome = builder.toString();
		}
		return nome;
	}

	/**
	 * Insere pessoas em um grupo econômico de forma automática
	 * 
	 * @param relacionamento
	 * @param pessoaRelacionada
	 * @param pessoa
	 * @return
	 * @throws BancoobException
	 */
	private GrupoEconomicoNovo incluirGrupoEconomicoAutomatico(RelacionamentoPessoa relacionamento, PessoaCompartilhamento pessoa,
			PessoaCompartilhamento pessoaRelacionada) throws BancoobException {
		final GrupoEconomicoAutomaticoPessoa grupoEconomicoPessoaRelacionada = obterGrupoEconomicoAutomatico(
				pessoaRelacionada);
		final GrupoEconomicoAutomaticoPessoa grupoEconomicoPessoa = obterGrupoEconomicoAutomatico(pessoa);
		final GrupoEconomicoNovo grupoEconomico;
		boolean controlador = false;
		if (grupoEconomicoPessoaRelacionada == null && grupoEconomicoPessoa == null) {
			getLogger().debug("Cadastramento de grupo");
			grupoEconomico = criarGrupoEconomicoAutomatico(relacionamento, pessoa, pessoaRelacionada);
			controlador = true;
			incluirGrupoEconomicoAutomaticoPessoa(relacionamento, grupoEconomico, controlador, pessoa, pessoaRelacionada);
		} else if (grupoEconomicoPessoaRelacionada != null && grupoEconomicoPessoa != null
				&& !grupoEconomicoPessoaRelacionada.getGrupoEconomico().equals(grupoEconomicoPessoa.getGrupoEconomico())) {
			getLogger().debug("Fluxo de unificação de grupo");
			grupoEconomico = unificarGruposAutomaticos(grupoEconomicoPessoa, grupoEconomicoPessoaRelacionada,
					obterUsuarioRelacionamento(relacionamento));
			incluirGrupoEconomicoAutomaticoPessoa(relacionamento, grupoEconomico, controlador, pessoa, pessoaRelacionada);
			ajustarGrupo(grupoEconomico);
		} else if (grupoEconomicoPessoaRelacionada != null) {
			getLogger().debug("Fluxo de inclusão em grupo de pessoa relacionada");
			grupoEconomico = grupoEconomicoPessoaRelacionada.getGrupoEconomico();
			incluirGrupoEconomicoAutomaticoPessoa(relacionamento, grupoEconomico, controlador, pessoa, pessoaRelacionada);
		} else {
			getLogger().debug("Fluxo de inclusão em grupo de pessoa");
			grupoEconomico = grupoEconomicoPessoa.getGrupoEconomico();
			incluirGrupoEconomicoAutomaticoPessoa(relacionamento, grupoEconomico, controlador, pessoa, pessoaRelacionada);
		}
		return grupoEconomico;
	}

	/**
	 * @param relacionamento
	 * @param grupoEconomico
	 * @param controlador
	 * @param pessoaRelacionada 
	 * @param pessoa 
	 * @throws BancoobException
	 */
	private void incluirGrupoEconomicoAutomaticoPessoa(RelacionamentoPessoa relacionamento, final GrupoEconomicoNovo grupoEconomico,
			boolean controlador, PessoaCompartilhamento pessoa, PessoaCompartilhamento pessoaRelacionada) throws BancoobException {
		GrupoEconomicoAutomaticoPessoa grupoAutomaticoPessoaRelacionada = new GrupoEconomicoAutomaticoPessoa();
		grupoAutomaticoPessoaRelacionada.setRelacionamentoPessoa(relacionamento);
		grupoAutomaticoPessoaRelacionada.setPessoaCompartilhamento(pessoaRelacionada);
		grupoAutomaticoPessoaRelacionada.setDataHoraInicio(new DateTimeDB());
		grupoAutomaticoPessoaRelacionada.setBolControlador(controlador);
		grupoAutomaticoPessoaRelacionada.setPercentualSocio(controlador ? relacionamento.getPercentualCapitalEmpresa() : null);
		grupoAutomaticoPessoaRelacionada.setGrupoEconomico(grupoEconomico);
		grupoAutomaticoPessoaRelacionada.setIdUsuarioCadastro(obterUsuarioRelacionamento(relacionamento));
		grupoEconomicoAutomaticoPessoaServico.incluir(grupoAutomaticoPessoaRelacionada);

		GrupoEconomicoAutomaticoPessoa grupoAutomaticoPessoa = new GrupoEconomicoAutomaticoPessoa();
		grupoAutomaticoPessoa.setRelacionamentoPessoa(relacionamento);
		grupoAutomaticoPessoa.setPessoaCompartilhamento(pessoa);
		grupoAutomaticoPessoa.setDataHoraInicio(new DateTimeDB());
		grupoAutomaticoPessoa.setBolControlador(false);
		grupoAutomaticoPessoa.setPercentualSocio(null);
		grupoAutomaticoPessoa.setGrupoEconomico(grupoEconomico);
		grupoAutomaticoPessoa.setIdUsuarioCadastro(obterUsuarioRelacionamento(relacionamento));
		grupoEconomicoAutomaticoPessoaServico.incluir(grupoAutomaticoPessoa);
	}

	/**
	 * 
	 * @param relacionamento
	 * @param pessoaCompartilhamento
	 * @return
	 */
	private Boolean isControlador(RelacionamentoPessoa relacionamento, PessoaCompartilhamento pessoaCompartilhamento) {
		return relacionamento.getPessoaRelacionada().getPessoaCompartilhamento().getId().equals(pessoaCompartilhamento.getId());
	}

	/**
	 * Verifica se a pessoa realacionada possui capital social maior do que 50%
	 * 
	 * @return
	 */
	private boolean isPessoaRelacionadaControladora(RelacionamentoPessoa relacionamento) {
		return relacionamento.getPercentualCapitalEmpresa().compareTo(PERCENTUAL_CONTROLADOR) > 0;
	}

	/**
	 * verifica se o tipo de relacionamento deve ser tratado.
	 * 
	 * @param relacionamento
	 * @return
	 */
	private boolean isTipoRelacionamentoGrupoEconomico(RelacionamentoPessoa relacionamento) {
		final Short codigoTipoRelacionamento = relacionamento.getTipoRelacionamento().getCodigo();
		return ((codigoTipoRelacionamento.equals(TipoRelacionamentoPessoaEnum.SOCIO.getCodigo())
				|| codigoTipoRelacionamento.equals(TipoRelacionamentoPessoaEnum.SOCIO_ADMINISTRADOR.getCodigo()))
				&& !relacionamento.getPessoa().getIdPessoa().equals(relacionamento.getPessoaRelacionada().getIdPessoa()));
	}

	/**
	 * 
	 * @param grupoEconomico
	 * @param pessoaCompartilhamento
	 * @param relacionamento
	 * @return
	 * @throws BancoobException
	 */
	private List<GrupoEconomicoAutomaticoPessoa> listarGrupoEconomicoAutomatico(GrupoEconomicoNovo grupoEconomico,
			PessoaCompartilhamento pessoaCompartilhamento, RelacionamentoPessoa relacionamento, String ordenacao) throws BancoobException {
		GrupoEconomicoAutomaticoPessoa filtro = new GrupoEconomicoAutomaticoPessoa();
		filtro.setGrupoEconomico(grupoEconomico);
		filtro.setPessoaCompartilhamento(pessoaCompartilhamento);
		filtro.setRelacionamentoPessoa(relacionamento);
		ConsultaDto<GrupoEconomicoAutomaticoPessoa> criterios = new ConsultaDto<>();
		criterios.setFiltro(filtro);
		criterios.setOrdenacao(ordenacao);
		return grupoEconomicoAutomaticoPessoaServico.listar(criterios);
	}

	/**
	 * 
	 * @param idGrupo
	 * @return
	 * @throws BancoobException
	 */
	private List<GrupoEconomicoInstituicao> listarGrupoEconomicoInstituicao(Integer idGrupo) throws BancoobException {
		GrupoEconomicoInstituicao filtro = new GrupoEconomicoInstituicao();
		filtro.setIdGrupo(idGrupo);
		ConsultaDto<GrupoEconomicoInstituicao> criterios = new ConsultaDto<>();
		criterios.setFiltro(filtro);
		return grupoEconomicoInstituicaoServico.listar(criterios);
	}

	/**
	 * 
	 * @param pessoaCompartilhamento
	 * @param integer
	 * @return
	 * @throws BancoobException
	 */
	private List<GrupoEconomicoManualPessoa> listarGrupoEconomicoManual(Integer idGrupoAutomatico, Integer idInstituicao) throws BancoobException {
		return listarGrupoEconomicoManual(idGrupoAutomatico, null, idInstituicao);
	}

	/**
	 * 
	 * @param pessoaCompartilhamento
	 * @param integer
	 * @return
	 * @throws BancoobException
	 */
	private List<GrupoEconomicoManualPessoa> listarGrupoEconomicoManual(PessoaCompartilhamento pessoaCompartilhamento, Integer idInstituicao)
			throws BancoobException {
		return listarGrupoEconomicoManual(null, pessoaCompartilhamento, idInstituicao);
	}

	/**
	 * 
	 * @param idGrupoAutomatico
	 * @param pessoaCompartilhamento
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	private List<GrupoEconomicoManualPessoa> listarGrupoEconomicoManual(Integer idGrupoAutomatico, PessoaCompartilhamento pessoaCompartilhamento,
			Integer idInstituicao) throws BancoobException {
		GrupoEconomicoManualPessoa filtro = new GrupoEconomicoManualPessoa();
		filtro.setGrupoEconomicoAutomatico(new GrupoEconomicoNovo(idGrupoAutomatico));
		filtro.setPessoaCompartilhamento(pessoaCompartilhamento);
		filtro.setGrupoEconomico(new GrupoEconomicoNovo());
		filtro.getGrupoEconomico().setIdInstituicao(idInstituicao);
		ConsultaDto<GrupoEconomicoManualPessoa> criterios = new ConsultaDto<>();
		criterios.setFiltro(filtro);
		return grupoEconomicoManualPessoaServico.listar(criterios);
	}

	/**
	 * 
	 * @param id
	 * @param nome
	 * @return
	 * @throws BancoobException
	 */
	private GrupoEconomicoNovo obterGrupo(Integer id, String nome, TipoGrupoEconomicoEnum tipoGrupo, String tipoProcura, Integer idInstituicao)
			throws BancoobException {
		GrupoEconomicoNovo filtro = new GrupoEconomicoNovo();
		filtro.setId(id);
		filtro.setNome(nome);
		filtro.setIdInstituicao(idInstituicao);
		if (tipoGrupo != null) {
			TipoGrupoEconomico tipo = new TipoGrupoEconomico();
			tipo.setCodigo(tipoGrupo.getCodigo());
			filtro.setTipo(tipo);
		}
		ConsultaDto<GrupoEconomicoNovo> criterios = new ConsultaDto<>();
		criterios.setFiltro(filtro);
		criterios.setTipoProcura(tipoProcura);
		List<GrupoEconomicoNovo> lista = listar(criterios);
		if (CollectionUtils.isEmpty(lista)) {
			return null;
		} else {
			return lista.get(0);
		}
	}

	/**
	 * 
	 * @param pessoaCompartilhamento
	 * @param relacionamento
	 * @return
	 * @throws BancoobException
	 */
	private GrupoEconomicoAutomaticoPessoa obterGrupoEconomicoAutomatico(PessoaCompartilhamento pessoaCompartilhamento,
			RelacionamentoPessoa relacionamento) throws BancoobException {
		List<GrupoEconomicoAutomaticoPessoa> lista = listarGrupoEconomicoAutomatico(null, pessoaCompartilhamento, relacionamento, null);
		final GrupoEconomicoAutomaticoPessoa grupoPessoa;
		if (lista == null || lista.isEmpty()) {
			grupoPessoa = null;
		} else {
			grupoPessoa = lista.get(0);
		}
		return grupoPessoa;
	}

	/**
	 * 
	 * @param pessoaCompartilhamento
	 * @return
	 * @throws BancoobException
	 */
	private GrupoEconomicoAutomaticoPessoa obterGrupoEconomicoAutomatico(PessoaCompartilhamento pessoaCompartilhamento) throws BancoobException {
		return obterGrupoEconomicoAutomatico(pessoaCompartilhamento, null);
	}

	/**
	 * 
	 * @param nome
	 * @return
	 * @throws BancoobException
	 */
	private GrupoEconomicoNovo obterGrupoPorNome(String nome, TipoGrupoEconomicoEnum tipoGrupo, String tipoProcura) throws BancoobException {
		return obterGrupo(null, nome, tipoGrupo, tipoProcura, null);
	}

	/**
	 * 
	 * @param nome
	 * @return
	 * @throws BancoobException
	 */
	private GrupoEconomicoNovo obterGrupoPorNome(String nome, TipoGrupoEconomicoEnum tipoGrupo, String tipoProcura, Integer idInstituicao)
			throws BancoobException {
		return obterGrupo(null, nome, tipoGrupo, tipoProcura, idInstituicao);
	}

	/**
	 * 
	 * @param relacionamento
	 * @return
	 */
	private String obterUsuarioRelacionamento(RelacionamentoPessoa relacionamento) {
		if (relacionamento.getIdUsuarioEnvio() != null) {
			return relacionamento.getIdUsuarioEnvio();
		} else if (relacionamento.getIdUsuarioAprovacao() != null) {
			return relacionamento.getIdUsuarioAprovacao();
		} else {
			return InformacoesUsuarioCAPES.getInstance().getLogin();
		}
	}

	/**
	 * Unifica os grupos automáticos
	 * 
	 * @param grupoEconomicoPessoa
	 * @param grupoEconomicoPessoaRelacionada
	 * @return
	 * @throws BancoobException
	 */
	private GrupoEconomicoNovo unificarGruposAutomaticos(GrupoEconomicoAutomaticoPessoa grupoEconomicoPessoa,
			GrupoEconomicoAutomaticoPessoa grupoEconomicoPessoaRelacionada, String idUsuarioExclusao) throws BancoobException {
		getLogger().debug("Unificando Grupos");
		final GrupoEconomicoNovo grupoEconomicoOrigem;
		final GrupoEconomicoNovo grupoEconomicoDestino;
		if (grupoEconomicoPessoaRelacionada.getGrupoEconomico().getDataHoraCadastro()
				.compareTo(grupoEconomicoPessoa.getGrupoEconomico().getDataHoraCadastro()) <= 0) {// eleger o grupo mais antigo
			getLogger().debug("Pessoa Relacionada Selecionada");
			grupoEconomicoDestino = grupoEconomicoPessoaRelacionada.getGrupoEconomico();
			grupoEconomicoOrigem = grupoEconomicoPessoa.getGrupoEconomico();
		} else {
			getLogger().debug("Pessoa Selecionada");
			grupoEconomicoOrigem = grupoEconomicoPessoaRelacionada.getGrupoEconomico();
			grupoEconomicoDestino = grupoEconomicoPessoa.getGrupoEconomico();
		}
		getLogger().debug("Atualizando dados Pessoa Destino");
		for (GrupoEconomicoAutomaticoPessoa pessoa : grupoEconomicoDestino.getIntegrantesAutomatico()) {
			pessoa.setBolControlador(false);
			pessoa.setPercentualSocio(null);
			grupoEconomicoAutomaticoPessoaServico.alterar(pessoa);
		}
		getLogger().debug("Atualizando dados Pessoa Origem");
		for (GrupoEconomicoAutomaticoPessoa pessoa : grupoEconomicoOrigem.getIntegrantesAutomatico()) {
			pessoa.setGrupoEconomico(grupoEconomicoDestino);
			pessoa.setBolControlador(false);
			pessoa.setPercentualSocio(null);
			grupoEconomicoAutomaticoPessoaServico.alterar(pessoa);
		}
		grupoEconomicoOrigem.setIdUsuarioExclusao(idUsuarioExclusao);
		getLogger().debug("Excluindo grupo de Origem");
		excluirEntidade(grupoEconomicoOrigem);
		getLogger().debug("Retornando grupo de Destino");
		return grupoEconomicoDestino;
	}

	/**
	 * 
	 * @param objeto
	 * @throws BancoobException
	 */
	private void validarAlteracao(GrupoEconomicoNovo objeto) throws BancoobException {
		if (objeto.getId() == null) {
			throw new CampoNaoInformadoException("Id");
		}
		validarDadosBasicosGrupo(objeto);
		validarPorTipo(objeto);
		validarNomeAlteracao(objeto);
	}

	/**
	 * 
	 * @param objeto
	 * @throws BancoobException
	 */
	private void validarDadosBasicosGrupo(GrupoEconomicoNovo objeto) throws BancoobException {
		if (StringUtils.isEmpty(objeto.getIdUsuarioCadastro())) {
			throw new CampoNaoInformadoException("Id Usuário Cadastro");
		}
		if (StringUtils.isEmpty(objeto.getNome())) {
			throw new CampoNaoInformadoException("Nome");
		}
		if (objeto.getTipo() == null) {
			throw new CampoNaoInformadoException("Tipo de Grupo");
		}
	}

	/**
	 * Valida e/ou remove o grupo, caso o grupo não tenha pessoas relacionadas
	 * 
	 * @param grupoEconomico
	 * @throws BancoobException
	 */
	private boolean validarExistenciaGrupoAutomatico(GrupoEconomicoNovo grupoEconomico, List<GrupoEconomicoAutomaticoPessoa> pessoasExistentes,
			String idUsuarioExclusao) throws BancoobException {
		if (CollectionUtils.isEmpty(pessoasExistentes)) {
			grupoEconomico.setIdUsuarioExclusao(idUsuarioExclusao);
			excluirEntidade(grupoEconomico);
			return false;
		}
		return true;

	}

	/**
	 * 
	 * @param objeto
	 * @throws BancoobException
	 */
	private void validarNomeAlteracao(GrupoEconomicoNovo objeto) throws BancoobException {
		final Integer idInstituicao;
		if (objeto.getTipo().getCodigo().equals(TipoGrupoEconomicoEnum.MANUAL.getCodigo())) {
			idInstituicao = objeto.getIdInstituicao();
		} else {
			idInstituicao = null;
		}
		GrupoEconomicoNovo grupo = obterGrupoPorNome(objeto.getNome(), TipoGrupoEconomicoEnum.obterPorCodigo(objeto.getTipo().getCodigo()), "igual",
				idInstituicao);
		if (grupo != null && !grupo.getId().equals(objeto.getId())) {
			throw new RegistroMesmoAtributoException(TipoMensagem.MASCULINO, "Grupo Econômico", "nome");
		}
	}

	/**
	 * 
	 * @param objeto
	 * @throws BancoobException
	 */
	private void validarInclusao(GrupoEconomicoNovo objeto) throws BancoobException {
		validarDadosBasicosGrupo(objeto);
		validarPorTipo(objeto);
		validarNomeInclusao(objeto);
	}

	/**
	 * 
	 * @param objeto
	 * @throws BancoobException
	 */
	private void validarNomeInclusao(GrupoEconomicoNovo objeto) throws BancoobException {
		final Integer idInstituicao;
		if (objeto.getTipo().getCodigo().equals(TipoGrupoEconomicoEnum.MANUAL.getCodigo())) {
			idInstituicao = objeto.getIdInstituicao();
		} else {
			idInstituicao = null;
		}
		GrupoEconomicoNovo grupo = obterGrupoPorNome(objeto.getNome(), TipoGrupoEconomicoEnum.obterPorCodigo(objeto.getTipo().getCodigo()), "igual",
				idInstituicao);
		if (grupo != null) {
			throw new RegistroMesmoAtributoException(TipoMensagem.MASCULINO, "Grupo Econômico", "nome");
		}
	}

	/**
	 * 
	 * @param objeto
	 * @throws BancoobException
	 */
	private void validarPorTipo(GrupoEconomicoNovo objeto) throws BancoobException {
		if (objeto.getTipo().getCodigo().equals(TipoGrupoEconomicoEnum.AUTOMATICO.getCodigo())) {
			if (objeto.getIdInstituicao() != null) {
				throw new CAPESCadastroNegocioException("msg.erro.grupoEconomico.idInstituicaoGrupoAutomatico");
			}
		} else {
			if (objeto.getIdInstituicao() == null) {
				throw new CampoNaoInformadoException("Id Instituição");
			}
			if (CollectionUtils.isEmpty(objeto.getIntegrantesManual())) {
				throw new CAPESCadastroNegocioException("msg.erro.grupoEconomico.quantidadeMinimaDeParticipantes");
			}
		}

	}

}
