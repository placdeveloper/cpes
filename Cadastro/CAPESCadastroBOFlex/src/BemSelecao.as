package
{
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastrarBem.BotoesAdicionais;
	import br.com.sicoob.capes.cadastrarBem.frmEditarBem;
	import br.com.sicoob.capes.cadastrarBem.frmListarBem;
	import br.com.sicoob.capes.comum.enums.SituacaoRegistroEnum;
	import br.com.sicoob.capes.comum.vo.entidades.UnidadeMedidaVO;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.BemImovelVO;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.BemOnusVO;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.BemPosseVO;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.BemRegistroVO;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.BemVO;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.SituacaoBemVO;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.SubTipoBemVO;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.TipoBemVO;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.TipoPosseBemVO;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCAPESBase;
	
	import flash.events.Event;
	import flash.net.registerClassAlias;
	
	import mx.core.Container;
	import mx.events.ListEvent;
	
	public class BemSelecao extends TelaPlataformaAtendimentoCAPESBase
	{
		
		private var telaLista:frmListarBem = new frmListarBem();
		private var telaEdicao:frmEditarBem = new frmEditarBem();
		private var botoesAdicionais:BotoesAdicionais = new BotoesAdicionais();
		//private var servico:ServicoJava;
		
		public function BemSelecao() {
			super();
			
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bemantigo.Bem", BemVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bemantigo.BemImovel", BemImovelVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bemantigo.BemOnus", BemOnusVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bemantigo.BemPosse", BemPosseVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bemantigo.BemRegistro", BemRegistroVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bemantigo.SituacaoBem",SituacaoBemVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bemantigo.SubTipoBem", SubTipoBemVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bemantigo.TipoBem", TipoBemVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.UnidadeMedida", UnidadeMedidaVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bemantigo.TipoPosseBem", TipoPosseBemVO);
			registerClassAlias("br.com.sicoob.capes.comum.negocio.enums.SituacaoRegistroEnum", SituacaoRegistroEnum);
			
			/*servico = ServicoJavaUtil.getServico("br.com.sicoob.capes.cadastro.fachada.bemantigo.BemFachada", 
				"Criando registro...", ResultEvent.RESULT, onResultSemPatrimonio);*/
			
			this.creationPolicy = "all";
			this.addEventListener("carregou", init);
		}
		
		private function init(evt:Event):void {
			
			this.setTelaLista(telaLista);
			this.setTelaEdicao(telaEdicao);
			
			telaLista.addEventListener(Modulo.LISTA_CARREGADA, onListaCarregada);
			
			this.textoAcao = "BENS";
			this.textoModulo = "BENS";
			this.textoOpcoes = "OPÇÕES";
			this.iconModulo = "br/com/bancoob/imagens/icos/calc_32.png";
			
			configurarBotoesAdicionais();
		}
		
		public override function dispose():void {
			super.dispose();
			this.removeEventListener("carregou", init);
			telaEdicao.dispose();
			telaEdicao = null;
			//servico = null;
		}
		
		private function configurarBotoesAdicionais():void {
			setBotoesAdicionais(this.botoesAdicionais);
			//this.botoesAdicionais.btSemPatrimonio.addEventListener(MouseEvent.CLICK, onClickBtSemPatrimonio);
			
			verificarExibicaoBotoes();
		}
		
		protected function onListaCarregada(event:Event):void {
			//this.botoesAdicionais.btSemPatrimonio.enabled = !existeSemPatrimonio();
			verificarExibicaoBotoes();
		}
		
		/*private function existeSemPatrimonio():Boolean {
			for each (var bem:BemVO in telaLista.listaBem) {
				if (SubTipoBemVO.CODIGO_SUBTIPO_SEM_PATRIMONIO == bem.subTipo.codigo) {
					return true;
				}
			}
			return false;
		}
		
		private function onClickBtSemPatrimonio(event:MouseEvent):void {
			MostraCursor.setBusyCursor("Aguarde...", Application.application, MostraCursor.CURSOR_PROGRESSO);
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.idPessoaCompartilhamento = getPessoaSelecionada().idPessoaCompartilhamento;
			servico.getOperation("criarRegistroSemPatrimonio").send(dto);
		}
		
		private function onResultSemPatrimonio(event:Event):void {
			this.telaLista.obterLista();
			MostraCursor.removeBusyCursor();
		}*/
		
		//--------------------------------------------------------------------------
		//  Configuração de destino dos serviços.
		//--------------------------------------------------------------------------		
		protected override function configurarDestinosServicos(destinoVO:DestinoVO):void{
			
			//servico.configurarDestino(destinoVO);
			telaLista.configurarDestino(destinoVO);	
			telaEdicao.configurarDestinosServicos(destinoVO);
		}
		
		override protected function habilitarControles(cont:Container):void {
			super.habilitarControles(cont);
			telaEdicao.obterAbaCadastro().valorPropriedade.enabled = false;
			verificarExibicaoBotoes();
		}
		
		protected override function registroExcluido(event : Event) : void {
			super.registroExcluido(event);
			this.telaLista.obterGrid().selectedIndex = -1;
			this.telaLista.obterGrid().setFocus();
			this.telaLista.obterLista();
			verificarExibicaoBotoes();
		}
		
		protected override function mostraBotoesMudaGrid(evt:ListEvent=null):void {
			/*mostraBotoesLista();
			if (BemVO(itemLista).subTipo.codigo == SubTipoBemVO.CODIGO_SUBTIPO_SEM_PATRIMONIO) {
				listaBotoes.botAlterar.enabled = false;
				botAlterarOculto = true;
			} else {
				listaBotoes.botAlterar.enabled = true;
				botAlterarOculto = false;
			}
			super.mostraBotoesMudaGrid(evt);*/
			verificarExibicaoBotoes();
		}
		
		/*protected override function mostraBotoesLista():void {
			super.mostraBotoesLista();
			if (botoesAdicionais.initialized) {
				botoesAdicionais.btSemPatrimonio.visible = botoesAdicionais.btSemPatrimonio.includeInLayout = true;
			}
		}*/
		
		protected override function exibirBotaoAlteracao():void {
			/*if (BemVO(itemLista).subTipo.codigo == SubTipoBemVO.CODIGO_SUBTIPO_SEM_PATRIMONIO) {
				listaBotoes.botOk.enabled = false;
				listaBotoes.botCancelar.enabled = false;
			} else {
				super.exibirBotaoAlteracao();
			}
			botoesAdicionais.btSemPatrimonio.visible = botoesAdicionais.btSemPatrimonio.includeInLayout = false;*/
			verificarExibicaoBotoes();
		}
		
		protected override function voltarClicado(event:Event=null):void{
			exibirTelaListar();
			verificarExibicaoBotoes();
		}
		
		private function verificarExibicaoBotoes():void {
			listaBotoes.mostraBotao(listaBotoes.botNovo, false);
			listaBotoes.mostraBotao(listaBotoes.botAlterar, false);
			listaBotoes.mostraBotao(listaBotoes.botExcluir, false);
			listaBotoes.mostraBotao(listaBotoes.botCancelar, false);
			listaBotoes.mostraBotao(listaBotoes.botOk, false);
			listaBotoes.mostraBotao(listaBotoes.botVer, true);
		}
	}
}