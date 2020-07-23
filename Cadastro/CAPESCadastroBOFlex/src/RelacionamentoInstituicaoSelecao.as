package
{
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.grid.Grid;
	import br.com.bancoob.sisbr.componentes.PlataformaAtendimento.ITelaBasePlataformaAtendimento;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastrarRelacionamentoInstituicao.BotoesOpcoesRelacionamentos;
	import br.com.sicoob.capes.cadastrarRelacionamentoInstituicao.dto.RelacionamentoInstituicaoDTO;
	import br.com.sicoob.capes.cadastrarRelacionamentoInstituicao.frmEditarRelacionamentoInstituicao;
	import br.com.sicoob.capes.cadastrarRelacionamentoInstituicao.frmListarRelacionamentoInstituicao;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCustomizadoCAPES;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.core.Application;
	import mx.core.Container;
	import mx.managers.IFocusManagerContainer;
	
	public class RelacionamentoInstituicaoSelecao extends TelaPlataformaAtendimentoCustomizadoCAPES {

		private var telaLista:frmListarRelacionamentoInstituicao = new frmListarRelacionamentoInstituicao();
		private var telaEdicao:frmEditarRelacionamentoInstituicao = new frmEditarRelacionamentoInstituicao();
		
		private var botoesOpcoes:BotoesOpcoesRelacionamentos = new BotoesOpcoesRelacionamentos();
		
		public static const CLASSE_SERVICO: String = 
				"br.com.sicoob.capes.cadastro.fachada.RelacionamentoInstituicaoFachada";
		private static const TELA_LISTA: int  = 0;
		private static const TELA_EDICAO: int = 1;
				
		public function RelacionamentoInstituicaoSelecao() {
			super();
			include 'br/com/sicoob/capes/utils/RegistroClasses.as';
			
			this.creationPolicy = "all";
			this.addEventListener("carregou", init);
		}

		private function init(evt:Event):void {

			this.textoAcao = "INSTITUIÇÕES DE RELACIONAMENTO";
			this.textoModulo = "INSTITUIÇÕES DE RELACIONAMENTO";
			this.iconModulo = "br/com/bancoob/imagens/icos/relacionamento.png";
			
			telaLista.addEventListener(Modulo.LISTA_CARREGADA, listaCarregada);
			telaLista.addEventListener(Modulo.REGISTRO_SELECIONADO, registroSelecionado);
		
			telaEdicao.addEventListener(Modulo.REGISTRO_GRAVADO, registroGravado);
		
			incluirBotoesAdicionais();
			this.adicionaTela(telaLista);	
			this.adicionaTela(telaEdicao);
			
			exibirTelaListagem();
		}

	    //--------------------------------------------------------------------------
	    //  Configuração de destino dos serviços.
	    //--------------------------------------------------------------------------		
		protected override function configurarDestinosServicos(destinoVO:DestinoVO):void{
			telaLista.servicoConsulta.configurarDestino(destinoVO);
			telaEdicao.servicoSalvar.configurarDestino(destinoVO);
		}
					
	    //--------------------------------------------------------------------------
	    //  Métodos auxiliares
	    //--------------------------------------------------------------------------
		private function incluirBotoesAdicionais(): void {
			this.setBotoesAdicionais(botoesOpcoes);
			botoesOpcoes.btAlterarGestor.addEventListener(MouseEvent.CLICK, alterarGestorClicado);
			botoesOpcoes.btEnviarBancoob.addEventListener(MouseEvent.CLICK, enviarBancoobClicado);
			botoesOpcoes.btAtualizarAssFotoBancoob.addEventListener(MouseEvent.CLICK, atualizarAssFotosBancoobClicado);
		}	
		
		// Funcionamento comum da Plataforma de atendimento.				
		private function tratamentoComumPlataforma(tela :ITelaBasePlataformaAtendimento, 
				textoAcao:String=""): void {

			txtAcao.text = textoAcao;
			habilitarControles(tela as Container);
			systemManager.activate(Application.application as IFocusManagerContainer);				
		}		

		private function exibirTelaListagem():void {
			
			telaLista.listarRelacionamentos();
			super.selecionaTela(telaLista);
						
			exibirBotoesAdicionais();
			exibirBotoesLista(false, false);
		}
						
	    //--------------------------------------------------------------------------
	    //  Listener 
	    //--------------------------------------------------------------------------					
		protected function registroSelecionado(event:Event):void {
			var relacionamento: RelacionamentoInstituicaoDTO = 
				telaLista.obterRelacionamentoInstituicaoSelecionado();
			exibirBotoesAdicionais(relacionamento);
		}		

		override protected function registroGravado(event:Event):void {
			exibirTelaListagem();
		}		
	
		protected function listaCarregada(event:Event):void{
			
			MostraCursor.removeBusyCursor();
			
			var grid:Grid = telaLista.obterGrid();
			if(grid.dataProvider.length > 0) {
				grid.selectedIndex = -1;
			} 

			exibirBotoesAdicionais();
		}		
		
	    //--------------------------------------------------------------------------
	    //  Tratamento dos botões.
	    //--------------------------------------------------------------------------		
		private function alterarGestorClicado(evt:MouseEvent = null): void{

// 			TODO Quando existir a integração com o GFT descomentar abaixo.
//			var texto: String = txtAcao.text = "< EDITANDO " + textoAcao.toUpperCase() + " >";
//			tratamentoComumPlataforma(telaEdicao, texto);
//						
//			telaEdicao.carregarRegistro(
//					telaLista.obterRelacionamentoInstituicaoSelecionado(), getPessoa());
//			super.selecionaTela(telaEdicao);
//
//			exibirBotoesAdicionais();
//			exibirBotoesLista(true, true);
			gravarClicado(evt);
		}					
		
		override protected function gravarClicado(event:MouseEvent=null):void {
			Alerta.show("Tem certeza que deseja alterar o responsável pelo cadastro?", 
					"Confirmação", Alerta.ALERTA_PERGUNTA, null, alteracaoConfirmada);
		}				
		
		override protected function voltarClicado(event:Event=null):void	{
			exibirTelaListagem();
		}		
		
		protected function alteracaoConfirmada(evt:Event):void {
			telaEdicao.carregarRegistro(telaLista.obterRelacionamentoInstituicaoSelecionado(), getPessoa());
			telaEdicao.alterarGestor();
		}
		
		private function enviarBancoobClicado(evt: Event = null): void {
			telaEdicao.replicarCadastroBancoob(getPessoa());
		}

		private function atualizarAssFotosBancoobClicado(evt: Event = null): void {
			telaEdicao.atualizarAssinaturaFotosBancoob(getPessoa());
		}
		
		
		private function getPessoa():PessoaCompartilhamentoVO {
			return TelaPlataformaAtendimentoCustomizadoCAPES.getPessoaSelecionada();
		}		
		
	    //--------------------------------------------------------------------------
	    //  Controle de exibição de botões.
	    //--------------------------------------------------------------------------	
		private function exibirBotoesAdicionais(relacionamento: RelacionamentoInstituicaoDTO=null): void {
			
			var exibeAlterarGestor: Boolean = false;
			if(relacionamento != null) {
				exibeAlterarGestor = !relacionamento.responsavelCadastro;
			}
			botoesOpcoes.btAlterarGestor.enabled = exibeAlterarGestor;
		}
		
		private function exibirBotoesLista(exibirGravar: Boolean, exibirVoltar: Boolean):void {
			
			listaBotoes.mostraBotao(listaBotoes.botOk, exibirGravar);
			listaBotoes.mostraBotao(listaBotoes.botVoltar, exibirVoltar);
		}
		
		/*protected function verificarAlteracoes():Boolean {
			return this.telaEdicao.verificarAlteracoes();
		}*/
	}
}