package {
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.painellista.PainelListaBanco;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.dto.RequisicaoDTO;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.app.Application;
	import br.com.sicoob.capes.cadastrarFuncionario.FuncionarioEdicao;
	import br.com.sicoob.capes.cadastrarFuncionario.FuncionarioView;
	import br.com.sicoob.capes.cadastrarFuncionario.paineis.PainelFiltroFuncionarioView;
	import br.com.sicoob.capes.utils.ConversaoPessoaUtil;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.FuncaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.FuncionarioVO;
	import br.com.sicoob.capes.comum.vo.entidades.InstituicaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.NucleoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;

	public class FuncionarioSelecao extends FuncionarioView {
		
		private static const OBTER_DEFINICOES:String = "obterDefinicoes";
		private static const DESTINO_CAPES:String = "destinoCAPES";
		
		private var servico:ServicoJava;
		
		public function FuncionarioSelecao() {
			super();
			include 'br/com/sicoob/capes/utils/RegistroClasses.as';
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Funcionario", FuncionarioVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Instituicao", InstituicaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Funcao", FuncaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Nucleo", NucleoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento", PessoaCompartilhamentoVO);
		}
		
		protected override function init(evento:FlexEvent) : void {
			super.init(evento);
			
			PainelListaBanco(this.painelLista).funcaoCriacaoDto = instanciarDTOConsulta;
			PainelListaBanco(this.painelLista).funcaoConfiguracaoDto = configurarDtoConsulta;
			
			this.barraBotoesListaCadastro.exibirBotaoAjuda = false;
			
			// adiciona a tela de edição
			this.formularioCadastro = new FuncionarioEdicao();
			
			// configura o serviço para pesquisa
			PainelListaBanco(this.painelLista).servicoPesquisa = ServicoJavaUtil.getServico(obterClasseServico(), "Recuperando dados...");
			
			servicoExclusao = ServicoJavaUtil.getServico(obterClasseServico(), "Excluindo dados...");
			servico = ServicoJavaUtil.getServico(obterClasseServico(), "Obtendo definições...", ResultEvent.RESULT, retornoObterDefinicoes);
			
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], destino_recuperado);
		}
		
		private function destino_recuperado(destino:DestinoVO):void {
			PainelListaBanco(this.painelLista).servicoPesquisa.configurarDestino(destino);
			servicoExclusao.configurarDestino(destino);
			servico.configurarDestino(destino);
			
			obterDefinicoes();
		}
		
		private function obterDefinicoes():void {
			MostraCursor.setBusyCursor("Obtendo definições...", Application.application, MostraCursor.CURSOR_PROGRESSO);
			servico.getOperation(OBTER_DEFINICOES).send(new RequisicaoReqDTO());
		}
		
		private function retornoObterDefinicoes(event:ResultEvent): void {
			MostraCursor.removeBusyCursor();
			painelFiltro.comboFuncao.dataProvider = event.result.dados.funcoes;
			painelFiltro.comboNucleo.dataProvider = event.result.dados.nucleos;
			painelFiltro.comboPA.dataProvider = event.result.dados.pacs;
			
			// Realiza a pesquisa inicial
			PainelListaBanco(this.painelLista).pesquisar(1);
		}
		
		private function get painelFiltro():PainelFiltroFuncionarioView {
			return this.painelLista.painelFiltro as PainelFiltroFuncionarioView;
		}
		
		protected override function montarDtoExclusao(item:Object):RequisicaoDTO {
			var dto : RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados["funcionario"] = item;
			return dto;
		}
		
		protected function obterClasseServico() : String {
			return "br.com.sicoob.capes.cadastro.fachada.FuncionarioFachada";
		}
		
		public function instanciarDTOConsulta(): ConsultaDto {
			return new ConsultaDto();
		}
		
		private function configurarDtoConsulta(dto:ConsultaDto): void {
			var filtro:FuncionarioVO = new FuncionarioVO();
			
			if(painelFiltro.componenteProcurarPessoa.registro != null){
				var pessoaPlataforma:PessoaPlataformaVO = painelFiltro.componenteProcurarPessoa.registro as PessoaPlataformaVO;
				var pessoaCompartilhamento:PessoaCompartilhamentoVO = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(pessoaPlataforma);
				filtro.pessoa = pessoaCompartilhamento.pessoa;
			}
			
			if(painelFiltro.campoMatricula.text != ""){
				filtro.matricula = painelFiltro.campoMatricula.text;
			}
			
			if(painelFiltro.comboFuncao.selectedItem != null){
				filtro.funcao = painelFiltro.comboFuncao.selectedItem as FuncaoVO;
			}
			
			if(painelFiltro.comboNucleo.selectedItem != null){
				filtro.nucleo = painelFiltro.comboNucleo.selectedItem as NucleoVO;
			}
			
			if(painelFiltro.comboPA.selectedItem != null){
				filtro.instituicao = painelFiltro.comboPA.selectedItem as InstituicaoVO;
			}
			
			dto.filtro = filtro;
		}
	}
}