package br.com.sicoob.capes.cadastrarGrupoEconomicoNovo {
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.componentes.painellista.PainelListaBanco;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.vo.entidades.DadosRegistroRelacionamentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.FuncaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.FuncionarioVO;
	import br.com.sicoob.capes.comum.vo.entidades.GrupoEconomicoNovoAutomaticoPessoaVO;
	import br.com.sicoob.capes.comum.vo.entidades.GrupoEconomicoNovoInstituicaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.GrupoEconomicoNovoManualPessoaVO;
	import br.com.sicoob.capes.comum.vo.entidades.GrupoEconomicoNovoVO;
	import br.com.sicoob.capes.comum.vo.entidades.NucleoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PerfilTarifarioVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaInstituicaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.RegistroRelacionamentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.RelacionamentoPessoaVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoGrupoEconomicoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoPoderRelacionamentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoRelacionamentoPessoaVO;
	import br.com.sicoob.capes.comum.vo.entidades.pk.PerfilTarifarioPK;

	public class GrupoEconomicoNovo extends GrupoEconomicoNovoSelecaoView {

		private var janelaAdicionarClientes : Janela;
		private static const DESTINO_CAPES:String = "destinoCAPES";
		public var moduloBase:GrupoEconomicoInicialView;
		
		public function GrupoEconomicoNovo(moduloBase:GrupoEconomicoInicialView) {
			this.moduloBase = moduloBase;
			include '../utils/RegistroClasses.as';
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Funcao",FuncaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Funcionario",FuncionarioVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Nucleo",NucleoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.pk.PerfilTarifarioPK", PerfilTarifarioPK);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.PerfilTarifario",PerfilTarifarioVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao",PessoaInstituicaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.GrupoEconomicoInstituicao", GrupoEconomicoNovoInstituicaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.GrupoEconomicoNovo", GrupoEconomicoNovoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.GrupoEconomicoAutomaticoPessoa", GrupoEconomicoNovoAutomaticoPessoaVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.GrupoEconomicoManualPessoa", GrupoEconomicoNovoManualPessoaVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoGrupoEconomico", TipoGrupoEconomicoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoRelacionamentoPessoa", TipoRelacionamentoPessoaVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoPoderRelacionamento", TipoPoderRelacionamentoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.DadosRegistroRelacionamento", DadosRegistroRelacionamentoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Pessoa", PessoaVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa", RelacionamentoPessoaVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.vigente.RegistroRelacionamento", RegistroRelacionamentoVO);
			registerClassAlias("br.com.sicoob.capes.comum.negocio.enums.SituacaoRegistroEnum", SituacaoRegistroEnum);
		}
		
		protected override function init(event:FlexEvent):void {
			super.init(event);
			aplicarPermissaoComponente(barraBotoesListaCadastro);
			barraBotoesListaCadastro.exibirBotaoAjuda = false;
			var painelLista:PainelListaBanco = PainelListaBanco(this.painelLista);
			painelLista.funcaoCriacaoDto = instanciarDtoConsulta;
 			painelLista.funcaoConfiguracaoDto = configurarDtoConsulta;
			
			this.permissoes = moduloBase.permissoes;
			moduloBase.metodoAplicarPermissoes(this);
			this.aplicarPermissoes(this);
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], destino_recuperado);
		}
		
		private function destino_recuperado(destino:DestinoVO):void {
			PainelListaBanco(this.painelLista).servicoPesquisa.configurarDestino(destino);
			
			ServicoJava(this.servicoRecuperacaoInformacoes).configurarDestino(destino);
			ServicoJava(this.servicoExclusao).configurarDestino(destino);
			
			//realiza a pesquisa inicial
			PainelListaBanco(this.painelLista).pesquisar(1);
		}
		
		public function instanciarDtoConsulta(): ConsultaDto {
			return new ConsultaDto();
		}

		public function configurarDtoConsulta(dto:ConsultaDto): void {
			var filtro:GrupoEconomicoNovoVO = new GrupoEconomicoNovoVO();
			if(painelFiltro.nome.text != ""){
					filtro.nome = painelFiltro.nome.text;
			}
			dto.filtro = filtro;
		}
		
		private function get painelFiltro() : PainelFiltroGrupoEconomicoNovoView {
			return this.painelLista.painelFiltro as PainelFiltroGrupoEconomicoNovoView;
		}
		
	}
}