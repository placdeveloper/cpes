package {

	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.core.Application;
	import mx.core.Container;
	import mx.managers.IFocusManagerContainer;
	
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.sisbr.componentes.PlataformaAtendimento.ITelaBasePlataformaAtendimento;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.alterarPessoaInstituicao.frmEditarPessoaInstituicao;
	import br.com.sicoob.capes.comum.vo.TributacaoVO;
	import br.com.sicoob.capes.comum.vo.UnidadeInstituicaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.FuncaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.FuncionarioVO;
	import br.com.sicoob.capes.comum.vo.entidades.GrupoEconomicoNovoAutomaticoPessoaVO;
	import br.com.sicoob.capes.comum.vo.entidades.GrupoEconomicoNovoInstituicaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.GrupoEconomicoNovoManualPessoaVO;
	import br.com.sicoob.capes.comum.vo.entidades.GrupoEconomicoNovoVO;
	import br.com.sicoob.capes.comum.vo.entidades.GrupoEconomicoPessoaVO;
	import br.com.sicoob.capes.comum.vo.entidades.GrupoEconomicoVO;
	import br.com.sicoob.capes.comum.vo.entidades.NucleoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PerfilTarifarioVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaInstituicaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoGrupoEconomicoVO;
	import br.com.sicoob.capes.comum.vo.entidades.pk.PerfilTarifarioPK;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCustomizadoCAPES;
	
	public class PessoaInstituicaoSelecao extends TelaPlataformaAtendimentoCustomizadoCAPES {

		public static const CLASSE_SERVICO: String = 
				"br.com.sicoob.capes.cadastro.fachada.ProdutosBancoobFachada";
	
		private var telaEdicao:frmEditarPessoaInstituicao = new frmEditarPessoaInstituicao();
		private var destinoVO:DestinoVO;
		
	    /**
	     *  Construtor.
	     */	
		public function PessoaInstituicaoSelecao(){
			super();

			registerClassAlias("br.com.sicoob.capes.negocio.entidades.PerfilTarifarioPK", PerfilTarifarioPK);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.PerfilTarifario", PerfilTarifarioVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.vigente.PessoaInstituicao", PessoaInstituicaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Nucleo", NucleoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.GrupoEconomico", GrupoEconomicoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.vigente.GrupoEconomicoPessoa", GrupoEconomicoPessoaVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Funcionario", FuncionarioVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Funcao", FuncaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.vigente.Tributacao", TributacaoVO);
			registerClassAlias("br.com.sicoob.capes.cadastro.negocio.vo.UnidadeInstituicaoVO", UnidadeInstituicaoVO);
			
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.GrupoEconomicoInstituicao", GrupoEconomicoNovoInstituicaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.GrupoEconomicoNovo", GrupoEconomicoNovoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.GrupoEconomicoAutomaticoPessoa", GrupoEconomicoNovoAutomaticoPessoaVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.GrupoEconomicoManualPessoa", GrupoEconomicoNovoManualPessoaVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoGrupoEconomico", TipoGrupoEconomicoVO);
			
			this.creationPolicy = "all";
			this.addEventListener("carregou", init);
		}
				
		private function init(event:Event):void {
			
			include 'br/com/sicoob/capes/utils/RegistroClasses.as';
			this.textoAcao = "DADOS DO CLIENTE";
			this.textoModulo = this.produtosBancoob ? "PRODUTOS BANCOOB - DADOS DO CLIENTE" :
					"DADOS DO CLIENTE";
			this.iconModulo = "br/com/bancoob/imagens/icos/Accountant.png";
			
			listaBotoes.botCancelar.addEventListener(MouseEvent.CLICK, cancelarClicado);
			listaBotoes.botOk.addEventListener(MouseEvent.CLICK, gravarClicado);	
			
			this.adicionaTela(telaEdicao);
			this.telaEdicao.produtosBancoob = this.produtosBancoob;
			telaEdicao.addEventListener(Modulo.REGISTRO_CARREGADO, registroCarregado);
			
			telaEdicao.carregarRegistro();
		}
		
		protected function registroCarregado(evt:Event):void {
			exibirTelaEdicao();		
		}

		override protected function cancelarClicado(event:MouseEvent=null):void {
			telaEdicao.limparForm()
		}
		
		override protected function gravarClicado(event:MouseEvent=null):void {
			telaEdicao.gravarRegistro()
		}
		
	    //--------------------------------------------------------------------------
	    //  Configuração de destino dos serviços.
	    //--------------------------------------------------------------------------	
		protected override function configurarDestinosServicos(destinoVO:DestinoVO):void{
			telaEdicao.configurarDestino(destinoVO);
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
			telaEdicao.dataHoraInicio.enabled = false;
		}
		
		/*protected function verificarAlteracoes():Boolean {
			return this.telaEdicao.verificarAlteracoes();
		}*/
	}
}