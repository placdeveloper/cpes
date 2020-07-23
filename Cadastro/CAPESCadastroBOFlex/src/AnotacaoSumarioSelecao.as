package {

	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.sisbr.componentes.PlataformaAtendimento.ITelaBasePlataformaAtendimento;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastrarAnotacao.BotoesOpcoesAnotacoes;
	import br.com.sicoob.capes.cadastrarAnotacao.dto.ConsultaAnotacaoDTO;
	import br.com.sicoob.capes.cadastrarAnotacao.dto.ConsultaSumarioAnotacaoDTO;
	import br.com.sicoob.capes.cadastrarAnotacao.dto.DefinicaoDTO;
	import br.com.sicoob.capes.cadastrarAnotacao.dto.DetalheAcaoDTO;
	import br.com.sicoob.capes.cadastrarAnotacao.dto.DetalheAcheiDTO;
	import br.com.sicoob.capes.cadastrarAnotacao.dto.DetalheAnotacaoBacenDTO;
	import br.com.sicoob.capes.cadastrarAnotacao.dto.DetalheAnotacaoDTO;
	import br.com.sicoob.capes.cadastrarAnotacao.dto.DetalheAnotacaoInternaDTO;
	import br.com.sicoob.capes.cadastrarAnotacao.dto.DetalheAnotacaoSisbrDTO;
	import br.com.sicoob.capes.cadastrarAnotacao.dto.DetalheCCFDTO;
	import br.com.sicoob.capes.cadastrarAnotacao.dto.DetalheConvemDevedoresDTO;
	import br.com.sicoob.capes.cadastrarAnotacao.dto.DetalheFalenciaDTO;
	import br.com.sicoob.capes.cadastrarAnotacao.dto.DetalheParticipanteFalidaDTO;
	import br.com.sicoob.capes.cadastrarAnotacao.dto.DetalhePefinDTO;
	import br.com.sicoob.capes.cadastrarAnotacao.dto.DetalheProtestoDTO;
	import br.com.sicoob.capes.cadastrarAnotacao.dto.DetalheRefinDTO;
	import br.com.sicoob.capes.cadastrarAnotacao.frmDetalharAnotacao;
	import br.com.sicoob.capes.cadastrarAnotacao.frmEditarAnotacao;
	import br.com.sicoob.capes.cadastrarAnotacao.frmListarAnotacao;
	import br.com.sicoob.capes.cadastrarAnotacao.frmListarSumarioAnotacao;
	import br.com.sicoob.capes.comum.vo.SumarioAnotacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.AnotacaoSisbrVO;
	import br.com.sicoob.capes.comum.vo.entidades.AnotacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.CategoriaAnotacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.CooperativaAnotacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.MapaTipoAnotacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.ObservacaoAnotacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.OrigemInformacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PeriodicidadeAnotacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoAnotacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoBaixaVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoCapturaVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoConsultaOrigemVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoObservacaoAnotacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.pk.CooperativaAnotacaoPK;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCustomizadoCAPES;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.core.Application;
	import mx.core.Container;
	import mx.managers.IFocusManagerContainer;
	
	public class AnotacaoSumarioSelecao extends TelaPlataformaAtendimentoCustomizadoCAPES {

		private var telaListaSumario:frmListarSumarioAnotacao = new frmListarSumarioAnotacao();
		private var telaListaAnotacao:frmListarAnotacao = new frmListarAnotacao();
		private var telaEdicaoAnotacao:frmEditarAnotacao = new frmEditarAnotacao();
		private var telaDetalheAnotacao:frmDetalharAnotacao = new frmDetalharAnotacao(); 
		
		private var botoesOpcoes:BotoesOpcoesAnotacoes = new BotoesOpcoesAnotacoes();
	
		public static const CLASSE_SERVICO: String = 
				"br.com.sicoob.capes.cadastro.fachada.AnotacaoFachada";
		
		public static const CLASSE_SERVICO_RELATORIO:String =
				"br.com.sicoob.capes.relatorio.negocio.fachada.AnotacaoFachada";
		
		public static const CLASSE_SERVICO_RELATORIO_PROVA_VIDA:String =
			"br.com.sicoob.capes.relatorio.negocio.fachada.RelatorioProvaVidaFachada";
		
		private static const TELA_SUMARIO: int  = 0;
		private static const TELA_LISTA: int = 1;
		
	    /**
	     *  Construtor.
	     */	
		public function AnotacaoSumarioSelecao(){
			super();
			
			include 'br/com/sicoob/capes/utils/RegistroClasses.as';
			registerClassAlias("br.com.sicoob.capes.cadastro.negocio.vo.SumarioAnotacaoVO", SumarioAnotacaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Anotacao", AnotacaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.AnotacaoSisbr", AnotacaoSisbrVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.CategoriaAnotacao", CategoriaAnotacaoVO);			
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.OrigemInformacao", OrigemInformacaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.pk.CooperativaAnotacaoPK", CooperativaAnotacaoPK);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.CooperativaAnotacao", CooperativaAnotacaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.ObservacaoAnotacao", ObservacaoAnotacaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoObservacaoAnotacao", TipoObservacaoAnotacaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoAnotacao", TipoAnotacaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoBaixa", TipoBaixaVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoCaptura",	TipoCapturaVO);			
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.PeriodicidadeAnotacao", PeriodicidadeAnotacaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.MapaTipoAnotacao", MapaTipoAnotacaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoConsultaOrigem", TipoConsultaOrigemVO);
			
			registerClassAlias("br.com.sicoob.capes.cadastrarAnotacao.dto.ConsultaSumarioAnotacaoDTO", ConsultaSumarioAnotacaoDTO);
			registerClassAlias("br.com.sicoob.capes.cadastrarAnotacao.dto.ConsultaAnotacaoDTO", ConsultaAnotacaoDTO);
			registerClassAlias("br.com.sicoob.capes.cadastrarAnotacao.dto.DefinicaoDTO", DefinicaoDTO);
			registerClassAlias("br.com.sicoob.capes.cadastrarAnotacao.dto.DetalheAcaoDTO",DetalheAcaoDTO);
			registerClassAlias("br.com.sicoob.capes.cadastrarAnotacao.dto.DetalheAcheiDTO", DetalheAcheiDTO);
			registerClassAlias("br.com.sicoob.capes.cadastrarAnotacao.dto.DetalheAnotacaoDTO", DetalheAnotacaoDTO);
			registerClassAlias("br.com.sicoob.capes.cadastrarAnotacao.dto.DetalheAnotacaoBacenDTO", DetalheAnotacaoBacenDTO);
			registerClassAlias("br.com.sicoob.capes.cadastrarAnotacao.dto.DetalheAnotacaoInternaDTO", DetalheAnotacaoInternaDTO);
			registerClassAlias("br.com.sicoob.capes.cadastrarAnotacao.dto.DetalheAnotacaoSisbrDTO",DetalheAnotacaoSisbrDTO);
			registerClassAlias("br.com.sicoob.capes.cadastrarAnotacao.dto.DetalheCCFDTO",	DetalheCCFDTO);
			registerClassAlias("br.com.sicoob.capes.cadastrarAnotacao.dto.DetalheConvemDevedoresDTO",	DetalheConvemDevedoresDTO);
			registerClassAlias("br.com.sicoob.capes.cadastrarAnotacao.dto.DetalheFalenciaDTO", DetalheFalenciaDTO);
			registerClassAlias("br.com.sicoob.capes.cadastrarAnotacao.dto.DetalheParticipanteFalidaDTO", DetalheParticipanteFalidaDTO);
			registerClassAlias("br.com.sicoob.capes.cadastrarAnotacao.dto.DetalhePefinDTO", DetalhePefinDTO);
			registerClassAlias("br.com.sicoob.capes.cadastrarAnotacao.dto.DetalheProtestoDTO", DetalheProtestoDTO);
			registerClassAlias("br.com.sicoob.capes.cadastrarAnotacao.dto.DetalheRefinDTO", DetalheRefinDTO);	
			
			this.creationPolicy = "all";
			this.addEventListener("carregou", init);
		}
				
		private function init(event:Event):void{
						
			this.textoAcao = "ANOTAÇÕES";
			this.textoModulo = "ANOTAÇÕES";
			this.iconModulo = "br/com/bancoob/imagens/icos/modulos/edit_32.png";
			telaListaAnotacao.addEventListener(Modulo.REGISTRO_SELECIONADO, registroSelecionado);
			telaListaAnotacao.addEventListener(Modulo.REGISTRO_GRAVADO, registroGravado);
			telaEdicaoAnotacao.addEventListener(Modulo.REGISTRO_GRAVADO, registroGravado);
			
			incluirBotoesAdicionais();
			this.adicionaTela(telaListaSumario);		
			this.adicionaTela(telaListaAnotacao);
			this.adicionaTela(telaEdicaoAnotacao);
			this.adicionaTela(telaDetalheAnotacao);
			
			exibirTelaSumarioAnotacao();
		}
				
	    //--------------------------------------------------------------------------
	    //  Configuração de destino dos serviços.
	    //--------------------------------------------------------------------------	
		protected override function configurarDestinosServicos(destinoVO:DestinoVO):void{

			telaListaSumario.servicoListaSumario.configurarDestino(destinoVO);
			
			telaListaAnotacao.servicoConsulta.configurarDestino(destinoVO); 
			telaListaAnotacao.servicoEdicao.configurarDestino(destinoVO);
								
			telaEdicaoAnotacao.configurarDestinos(destinoVO);
			
			telaDetalheAnotacao.servicoDetalhe.configurarDestino(destinoVO);
			telaDetalheAnotacao.servicoRelatorio.configurarDestino(destinoVO);
		}   
		
	    //--------------------------------------------------------------------------
	    //  Listener 
	    //--------------------------------------------------------------------------		
		private function registroSelecionado(event:Event):void {
			var anotacao: AnotacaoVO = telaListaAnotacao.obterAnotacaoSelecionada();
			exibirBotoesAdicionais(anotacao);
		}		
		
		override protected function registroGravado(event:Event):void {
			exibirTelaListagemAnotacao();
		}		
	
		protected function baixaConfirmada(evt:Event):void {
			telaListaAnotacao.baixar();
		}
		
		protected function confirmarBaixaSemValidacao(evt:Event):void {
			telaListaAnotacao.baixarSemValidacao();
		}	
		
		protected function flexibilizacaoConfirmada(evt:Event):void {
			telaListaAnotacao.flexibilizar();
		}	
		
		override protected function voltarClicado(event:Event=null):void	{
			
			var telaExibida: int = vsTelas.selectedIndex;
			
			if(telaExibida == TELA_LISTA) {
				exibirTelaSumarioAnotacao();	
			} else if(telaExibida == 2 || telaExibida == 3) {
				exibirTelaListagemAnotacao(true);
			}
		}
		
	    //--------------------------------------------------------------------------
	    //  Tratamento dos botões.
	    //--------------------------------------------------------------------------		
		private function listarAnotacoesClicado(evt:Event = null): void{
			exibirTelaListagemAnotacao();
		}			
	    
	    protected function baixarAnotacaoClicado(evt:Event = null):void {
	    	Alerta.show("Tem certeza que deseja Baixar a anotação selecionada?", 
					"Confirmação", Alerta.ALERTA_PERGUNTA, null, baixaConfirmada);
	    }
		
	    protected function baixarAnotacaoSemValidar(evt:Event = null):void {
	    	Alerta.show("Tem certeza que deseja Baixar a anotação selecionada?", 
					"Confirmação", Alerta.ALERTA_PERGUNTA, null, confirmarBaixaSemValidacao);
	    }
	    
		protected function flexibilizarAnotacaoClicado(event:MouseEvent=null):void {
			Alerta.show("Tem certeza que deseja Flexibilizar a anotação selecionada?", 
					"Confirmação", Alerta.ALERTA_PERGUNTA, null, flexibilizacaoConfirmada);
		}						

	    protected function detalharAnotacaoClicado(evt:Event = null): void {
			exibirTelaDetalhe();
	    }
	    
	    //--------------------------------------------------------------------------
	    //  Métodos sobrescritos.
	    //--------------------------------------------------------------------------		

		//Listener do botão Adicionar
		override protected function adicionarClicado(event:MouseEvent=null):void	{		
			exibirTelaEdicaoAnotacao();
		}
		
		//Listener do botão Gravar	
		override protected function gravarClicado(event:MouseEvent=null):void {
			telaEdicaoAnotacao.gravarRegistro();
		}		
			
	    //--------------------------------------------------------------------------
	    //  Controle de exibição de telas.
	    //--------------------------------------------------------------------------
		private function exibirTelaSumarioAnotacao():void {

			// Funcionamento comum da Plataforma de atendimento.			
			txtAcao.text = "";
			habilitarControles(telaEdicaoAnotacao as Container);
			systemManager.activate(Application.application as IFocusManagerContainer);	

			telaListaSumario.listarSumario();
			super.selecionaTela(telaListaSumario);
						
			exibirBotoesAdicionais();
			exibirBotoesLista (false, false, false);
		}		    
	    					
		private function exibirTelaListagemAnotacao(voltarClicado:Boolean=false):void {
			
			tratamentoComumPlataforma(telaListaAnotacao);

			telaListaAnotacao.listarAnotacoes();
			super.selecionaTela(telaListaAnotacao);
			
			if(!voltarClicado) {
				telaListaAnotacao.selecionarAbaVigentes()
			}
			
			exibirBotoesAdicionais();
			exibirBotoesLista (true, false, true);		
		}	
		
		private function exibirTelaEdicaoAnotacao():void {					

			var texto:String = "< ADICIONANDO " + textoAcao.toUpperCase() + " >";		
			tratamentoComumPlataforma(telaEdicaoAnotacao, texto);				
			
			telaEdicaoAnotacao.novoRegistro();
			super.selecionaTela(telaEdicaoAnotacao);

			exibirBotoesAdicionais();
			exibirBotoesLista(false, true, true);
		}	
		
		private function exibirTelaDetalhe():void {
			tratamentoComumPlataforma(telaDetalheAnotacao);
			var anotacao: AnotacaoVO = telaListaAnotacao.obterAnotacaoSelecionada();

			if(!isNaN(anotacao.idConsultaExterna) && anotacao.idConsultaExterna != 0){
				telaDetalheAnotacao.obterRelatorioDetalheAnotacao(anotacao.idConsultaExterna);
				return;
			}
			
			telaDetalheAnotacao.detalhar(anotacao);
			super.selecionaTela(telaDetalheAnotacao);
			
			exibirBotoesAdicionais();
			exibirBotoesLista(false, false, true);
		}			

	    //--------------------------------------------------------------------------
	    //  Métodos auxiliares
	    //--------------------------------------------------------------------------
		private function incluirBotoesAdicionais(): void {

			this.setBotoesAdicionais(botoesOpcoes);
			botoesOpcoes.btAnotacoes.addEventListener(MouseEvent.CLICK, listarAnotacoesClicado);
			botoesOpcoes.btBaixar.addEventListener(MouseEvent.CLICK, baixarAnotacaoClicado);
			botoesOpcoes.btDetalhar.addEventListener(MouseEvent.CLICK, detalharAnotacaoClicado);
			//botoesOpcoes.btFlexibilizar.addEventListener(MouseEvent.CLICK, flexibilizarAnotacaoClicado);
			botoesOpcoes.btBaixarSemValidar.addEventListener(MouseEvent.CLICK, baixarAnotacaoSemValidar);
		}	
		
		// Funcionamento comum da Plataforma de atendimento.				
		private function tratamentoComumPlataforma(tela :ITelaBasePlataformaAtendimento, 
				textoAcao:String=""): void {

			txtAcao.text = "";
			habilitarControles(tela as Container);
			systemManager.activate(Application.application as IFocusManagerContainer);				
		}
		
	    //--------------------------------------------------------------------------
	    //  Controle de exibição de botões.
	    //--------------------------------------------------------------------------		
		private function exibirBotoesAdicionais(anotacao: AnotacaoVO=null): void {

			var vigente: Boolean = false;
			var exibeDetalhe: Boolean = false;
			var exibeFlexibilizar: Boolean = false;

			var telaExibida: int = vsTelas.selectedIndex;
			var estaExibindoSumario:Boolean = telaExibida == TELA_SUMARIO;
			var exibirBotao: Boolean = telaListaSumario.isExibirBotaoBaixarAnotacao();
			
			botoesOpcoes.btAnotacoes.visible = estaExibindoSumario;
			botoesOpcoes.btDetalhar.visible = !estaExibindoSumario;		
			botoesOpcoes.btBaixar.visible = !estaExibindoSumario;
			
			botoesOpcoes.btBaixarSemValidar.visible = (!estaExibindoSumario && exibirBotao);	
			
			if(anotacao != null) {
				
				vigente = anotacao.tipoBaixa == null;
				exibeFlexibilizar = vigente && !anotacao.flexibilidade.valor;
				exibeDetalhe = true;
			}
			
			botoesOpcoes.btDetalhar.enabled = exibeDetalhe;		
			botoesOpcoes.btBaixar.enabled = vigente;
			botoesOpcoes.btBaixarSemValidar.enabled = exibeFlexibilizar;			
			
		}

		private function exibirBotoesLista(exibirNovo: Boolean, exibirGravar: Boolean, 
				exibirVoltar: Boolean):void {
					
			listaBotoes.mostraBotao(listaBotoes.botNovo, exibirNovo);
			listaBotoes.mostraBotao(listaBotoes.botOk, exibirGravar);
			listaBotoes.mostraBotao(listaBotoes.botVoltar, exibirVoltar);
		}
		
		/*protected function verificarAlteracoes():Boolean {
			return this.telaEdicaoAnotacao.verificarAlteracoes();
		}*/

	}
}