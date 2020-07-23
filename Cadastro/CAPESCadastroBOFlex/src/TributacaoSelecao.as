package {

	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.botoes.BotaoPlataforma;
	import br.com.bancoob.sisbr.componentes.PlataformaAtendimento.ITelaBasePlataformaAtendimento;
	import br.com.bancoob.sisbr.componentes.pti.ObjetoEvent;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.alterarTributacao.BotoesOpcoesTributacao;
	import br.com.sicoob.capes.alterarTributacao.frmEditarTributacao;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCustomizadoCAPES;
	import br.com.sicoob.capes.comum.enums.SituacaoRegistroEnum;
	import br.com.sicoob.capes.comum.vo.TributacaoVO;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.core.Application;
	import mx.core.Container;
	import mx.managers.IFocusManagerContainer;
	
	public class TributacaoSelecao extends TelaPlataformaAtendimentoCustomizadoCAPES {

		private var telaEdicao:frmEditarTributacao = new frmEditarTributacao();
		private var botoesOpcoes:BotoesOpcoesTributacao = new BotoesOpcoesTributacao();
	
		private var destinoVO:DestinoVO;
		
	    /**
	     *  Construtor.
	     */	
		public function TributacaoSelecao(){
			super();
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.vigente.Tributacao", TributacaoVO);
			
			this.creationPolicy = "all";
			this.addEventListener("carregou", init);
		}
				
		private function init(event:Event):void{
			
			include 'br/com/sicoob/capes/utils/RegistroClasses.as';
			
			this.textoAcao = "PERFIL TRIBUTÁRIO";
			this.textoModulo = "PERFIL TRIBUTÁRIO";
			this.iconModulo = "br/com/bancoob/imagens/icos/perfilTributario.png";
			
			listaBotoes.botCancelar.addEventListener(MouseEvent.CLICK, cancelarClicado);
			listaBotoes.botOk.addEventListener(MouseEvent.CLICK, gravarClicado);	
			//listaBotoes.botExcluir.addEventListener(MouseEvent.CLICK, excluirClicado);
			
			telaEdicao.addEventListener(frmEditarTributacao.VERIFICAR_BOTOES_AUTORIZACAO, verificarBotoesAutorizacao);
			
			incluirBotoesAdicionais();
			
			this.adicionaTela(telaEdicao);		
			
			telaEdicao.addEventListener(Modulo.REGISTRO_CARREGADO, registroCarregado);
			
			telaEdicao.carregarRegistro();
		}
		
		public override function dispose():void {
			super.dispose();
			this.removeEventListener("carregou", init);
			telaEdicao.dispose();
			telaEdicao = null;
		}
		
		protected function registroCarregado(evt:Event):void
		{
			exibirTelaEdicao();		
		}

		override protected function cancelarClicado(event:MouseEvent=null):void
		{
			telaEdicao.restaurarRegistro();
			telaEdicao.preencherCampos();
		}
		
		override protected function gravarClicado(event:MouseEvent=null):void
		{
			telaEdicao.gravarRegistro()
		}
		
		/*override protected function excluirClicado(event:MouseEvent=null):void{
			telaEdicao.excluirRegistro();
		}*/
		
	    //--------------------------------------------------------------------------
	    //  Configuração de destino dos serviços.
	    //--------------------------------------------------------------------------	
		protected override function configurarDestinosServicos(destinoVO:DestinoVO):void{
			telaEdicao.servicoConsulta.configurarDestino(destinoVO);
			telaEdicao.servicoSalvar.configurarDestino(destinoVO);
			telaEdicao.servicoValidarCadastro.configurarDestino(destinoVO);
		}   
		
	    //--------------------------------------------------------------------------
	    //  Listener 
	    //--------------------------------------------------------------------------		
		
		override protected function registroGravado(event:Event):void {
		}		

		override protected function voltarClicado(event:Event=null):void	{
			
		}
	    
	    //--------------------------------------------------------------------------
	    //  Métodos sobrescritos.
	    //--------------------------------------------------------------------------		
		private function exibirTelaEdicao():void {					

			var texto:String = "< EDITANDO " + textoAcao.toUpperCase() + " >";		
			tratamentoComumPlataforma(telaEdicao, texto);				

			super.selecionaTela(telaEdicao);

			exibirBotoesLista(false, true, true);
		}	
		
	    //--------------------------------------------------------------------------
	    //  Métodos auxiliares
	    //--------------------------------------------------------------------------
		// Funcionamento comum da Plataforma de atendimento.				
		private function tratamentoComumPlataforma(tela :ITelaBasePlataformaAtendimento, 
				textoAcao:String=""): void {

			txtAcao.text = "";
			habilitarControles(tela as Container);
			systemManager.activate(Application.application as IFocusManagerContainer);				
		}

		private function exibirBotoesLista(exibirNovo: Boolean, exibirGravar: Boolean, 
				exibirVoltar: Boolean):void {

			listaBotoes.mostraBotao(listaBotoes.botOk, exibirGravar);
			listaBotoes.mostraBotao(listaBotoes.botCancelar, exibirVoltar);
		}
		
		override protected function habilitarControles(cont:Container):void {
			super.habilitarControles(cont);
			
			telaEdicao.dataCadastro.enabled = false;
		}
		
		private function incluirBotoesAdicionais(): void {
			this.setBotoesAdicionais(botoesOpcoes);
			
			botoesOpcoes.botoesAutorizacao.bt_GED_GFT.addEventListener(MouseEvent.CLICK, btVisualizarDocumentoClicado);
			
			botoesOpcoes.botoesAutorizacao.bt_A_ENCAMINHAR.addEventListener(MouseEvent.CLICK, onBotaoAprovacaoClicado);
			botoesOpcoes.botoesAutorizacao.bt_BLOQUEADO.addEventListener(MouseEvent.CLICK, onBotaoAprovacaoClicado);
			botoesOpcoes.botoesAutorizacao.bt_DEVOLVIDO.addEventListener(MouseEvent.CLICK, onBotaoAprovacaoClicado);
			botoesOpcoes.botoesAutorizacao.bt_EM_AUTORIZACAO.addEventListener(MouseEvent.CLICK, onBotaoAprovacaoClicado);
		}
		
		private function onBotaoAprovacaoClicado(evt:MouseEvent):void{
			var botao:BotaoPlataforma = BotaoPlataforma(evt.currentTarget);
			if(botoesOpcoes.botoesAutorizacao.bt_BLOQUEADO == botao){
				telaEdicao.setarTributacaoVigente(true);
			}else{
				telaEdicao.setarTributacaoVigente(false);
			}
		}
		
		private function verificarBotoesAutorizacao(evento:ObjetoEvent):void{
			botoesOpcoes.botoesAutorizacao.situacao = evento.objeto.situacao as SituacaoRegistroEnum;
			botoesOpcoes.botoesAutorizacao.bt_GED_GFT.visible = 
				botoesOpcoes.botoesAutorizacao.bt_GED_GFT.includeInLayout = evento.objeto.exibir as Boolean;
		}
		
		protected function btVisualizarDocumentoClicado(event:MouseEvent=null):void {
			this.telaEdicao.exibirDetalhamentoGedGft();
		}
		
		/*protected function verificarAlteracoes():Boolean {
			return this.telaEdicao.verificarAlteracoes();
		}*/
	}
}