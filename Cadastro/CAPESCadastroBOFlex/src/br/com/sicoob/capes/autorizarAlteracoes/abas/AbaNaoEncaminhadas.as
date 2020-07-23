package br.com.sicoob.capes.autorizarAlteracoes.abas{
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.Botao;
	import br.com.bancoob.componentes.painellista.PainelListaBanco;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.componentes.pti.ObjetoEvent;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.autorizarAlteracoes.renderer.AcoesRenderer;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.AutorizacaoVO;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.containers.HBox;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;

	public class AbaNaoEncaminhadas extends AbaNaoEncaminhadasView implements IAbaAutorizacao {
		
		private var _pesquisaRealizada:Boolean = false;
		private var servico:ServicoJava;

		public function AbaNaoEncaminhadas(){
			super();
			servico = ServicoJavaUtil.getServico(
				"br.com.sicoob.capes.cadastro.fachada.AutorizarFachada", 
				"Encaminhando autorizações...", ResultEvent.RESULT, onResultEncaminhar);
		}
		
		protected override function init(evento:FlexEvent):void {
			
			super.init(evento);
			
			this.pegaJanela().centralizarJanela();
			
			var painelListaBanco : PainelListaBanco = PainelListaBanco(this.painelLista);
			painelListaBanco.funcaoCriacaoDto = instanciarDtoConsulta;
			painelListaBanco.funcaoConfiguracaoDto = configurarDtoConsulta;
			painelListaBanco.metodoPesquisa = "obterDadosSelecaoNaoEncaminhadas";
			painelListaBanco.tabelaPaginada.grdDados.doubleClickEnabled = false;
			painelListaBanco.tabelaPaginada.addEventListener(AcoesRenderer.EVENTO_ENCAMINHAR, 
				onClickBtEncaminhar);
			
			this.barraBotoesListaCadastro.exibirBotaoAlterar = false;
			this.barraBotoesListaCadastro.exibirBotaoExcluir = false;
			this.barraBotoesListaCadastro.exibirBotaoIncluir = false;
			this.barraBotoesListaCadastro.exibirBotaoLimpar = false;
			this.barraBotoesListaCadastro.exibirBotaoVisualizar = false;
			
			var botaoAtualizar:Botao = new Botao();
			botaoAtualizar.label = "Atualizar";
			botaoAtualizar.addEventListener(MouseEvent.CLICK, onClickBotaoAtualizar);
			botaoAtualizar.height = 22;
			var box : HBox = (this.barraBotoesListaCadastro.getChildAt(0) as HBox);
			box.addChildAt(botaoAtualizar, 0);
			
			this.addEventListener(Event.CHANGE, onAbaSelecionada);
		}
		
		public function pesquisar(pagina:int = 1):void {
			this.pesquisaRealizada = true;
			PainelListaBanco(this.painelLista).pesquisar(pagina);			
		}
		
		private function onAbaSelecionada(event:Event):void {
			if (!this.pesquisaRealizada) {
				pesquisar();
			}
		}
		
		private function onClickBtEncaminhar(event:ObjetoEvent):void{
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.pessoa = event.objeto;
			servico.getOperation("encaminharAutorizacoes").send(dto);
		}
	
		private function onResultEncaminhar(event:ResultEvent):void{
			
			var autorizacoesEncaminhadas:Boolean = event.result.dados.autorizacoesEncaminhadas;
			if(autorizacoesEncaminhadas){				
				PainelListaBanco(this.painelLista).pesquisar(1);
			}else{
				Alerta.show("As autorizações não foram encaminhas pois houve alteração do Responsável," +
					" favor encaminhar novamente.", "Confirmação", Alerta.ALERTA_INFORMACAO, null);
			}
			
		}
		
		private function configurarDtoConsulta(dto: ConsultaDto):void{
			dto.filtro = new AutorizacaoVO();
		}
		
		private function instanciarDtoConsulta():ConsultaDto{
			return new ConsultaDto();
		}
		
		private function onClickBotaoAtualizar(event:MouseEvent = null):void {
			PainelListaBanco(this.painelLista).pesquisar(1);
		}
		
		public function configurarDestino(destino:DestinoVO):void {
			this.servico.configurarDestino(destino);
			ServicoJava(this.servicoRecuperacaoInformacoes).configurarDestino(destino);
			
			var lista:PainelListaBanco = PainelListaBanco(this.painelLista);
			ServicoJava(lista.servicoPesquisa).configurarDestino(destino);
		}
		
		public function get pesquisaRealizada():Boolean {
			return _pesquisaRealizada;
		}
		public function set pesquisaRealizada(value:Boolean):void {
			this._pesquisaRealizada = value;
		}
	}
}