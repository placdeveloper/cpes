package br.com.sicoob.capes.cadastrarGrupoEconomico {
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.componentes.painellista.PainelListaBanco;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.dto.RequisicaoDTO;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastrarGrupoEconomico.GrupoEconomicoSelecaoView;
	import br.com.sicoob.capes.cadastrarGrupoEconomico.PainelFiltroGrupoEconomicoView;
	import br.com.sicoob.capes.comum.vo.entidades.FuncaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.FuncionarioVO;
	import br.com.sicoob.capes.comum.vo.entidades.GrupoEconomicoPessoaVO;
	import br.com.sicoob.capes.comum.vo.entidades.GrupoEconomicoVO;
	import br.com.sicoob.capes.comum.vo.entidades.NucleoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PerfilTarifarioVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaInstituicaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.pk.PerfilTarifarioPK;
	
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;

	public class GrupoEconomicoAntigo extends GrupoEconomicoSelecaoView {

		private var janelaAdicionarClientes : Janela;
		private static const DESTINO_CAPES:String = "destinoCAPES";

		public function GrupoEconomicoAntigo() {
			super();
			include '../utils/RegistroClasses.as';
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Funcao",FuncaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Funcionario",FuncionarioVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Nucleo",NucleoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.pk.PerfilTarifarioPK", PerfilTarifarioPK);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.PerfilTarifario",PerfilTarifarioVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao",PessoaInstituicaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.vigente.GrupoEconomicoPessoa", GrupoEconomicoPessoaVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.GrupoEconomico",GrupoEconomicoVO);
		}
		
		protected override function init(event : FlexEvent) : void {
			
			super.init(event);
			var painelLista : PainelListaBanco = PainelListaBanco(this.painelLista)
			painelLista.funcaoCriacaoDto = instanciarDtoConsulta;
 			painelLista.funcaoConfiguracaoDto = configurarDtoConsulta;
			
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
			
			var filtro:GrupoEconomicoVO = new GrupoEconomicoVO();
			if(painelFiltro.nome.text != ""){
					filtro.descricao = painelFiltro.nome.text;
			}
			dto.filtro = filtro;
		}
		
		private function get painelFiltro() : PainelFiltroGrupoEconomicoView {
			return this.painelLista.painelFiltro as PainelFiltroGrupoEconomicoView;
		}
		
		protected override function montarDtoExclusao(item:Object):RequisicaoDTO {
			
			var dto : RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.grupoEconomico = item;
			return dto;
		}
	}
}