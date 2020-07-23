package {
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.grid.Grid;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastrarRelacionamentoPessoa.RelacionamentoPessoaUtils;
	import br.com.sicoob.capes.cadastrarRelacionamentoPessoa.frmEditarRelacionamentoPessoa;
	import br.com.sicoob.capes.cadastrarRelacionamentoPessoa.frmListarRelacionamentoPessoa;
	import br.com.sicoob.capes.comum.enums.SituacaoRegistroEnum;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.DadosRegistroRelacionamentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaVO;
	import br.com.sicoob.capes.comum.vo.entidades.RegistroRelacionamentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.RelacionamentoPessoaVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoPoderRelacionamentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoRelacionamentoPessoaVO;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCAPESBase;
	
	import flash.events.Event;
	import flash.net.registerClassAlias;
	
	import mx.events.ListEvent;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;

	public class RelacionamentoPessoaSelecao extends TelaPlataformaAtendimentoCAPESBase {
		
		private static const PRODUTOS_BANCOOB : String = "produtosBancoob";
		private static const CLASSE_SERVICO : String = "br.com.sicoob.capes.cadastro.fachada.RelacionamentoPessoaFachada";
		private static const OPERACAO_VALIDAR_TRANSICAO_PESSOA_RELACIONADA : String = "validarTransicaoPessoaRelacionada";
		private static const OPERACAO_COMPARTILHAR_PESSOA_RELACIONADA : String = "compartilharPessoaRelacionada";
		
		private var telaLista : frmListarRelacionamentoPessoa = new frmListarRelacionamentoPessoa();
		private var telaEdicao : frmEditarRelacionamentoPessoa = new frmEditarRelacionamentoPessoa();
		private var idInstituicao : Number;
		private var telaConsulta:Boolean;
		
		private var servicoValidarTransicaoPessoaRelacionada:ServicoJava;
		private var servicoCompartilharPessoaRelacionada:ServicoJava;
		
		public function RelacionamentoPessoaSelecao() {
			
			super();
			
			include 'br/com/sicoob/capes/utils/RegistroClasses.as';
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoRelacionamentoPessoa", TipoRelacionamentoPessoaVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoPoderRelacionamento", TipoPoderRelacionamentoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.DadosRegistroRelacionamento", DadosRegistroRelacionamentoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Pessoa", PessoaVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa", RelacionamentoPessoaVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.vigente.RegistroRelacionamento", RegistroRelacionamentoVO);
			registerClassAlias("br.com.sicoob.capes.comum.negocio.enums.SituacaoRegistroEnum", SituacaoRegistroEnum);
			
			this.creationPolicy = "all";
			this.addEventListener("carregou", init);
			
			telaLista = new frmListarRelacionamentoPessoa();
			telaEdicao = new frmEditarRelacionamentoPessoa();
			
			servicoValidarTransicaoPessoaRelacionada = ServicoJavaUtil.getServico(CLASSE_SERVICO, 
				"Verificando transição pessoa relacionada ...", ResultEvent.RESULT, onResultTransicaoPessoaRelacionada);
			servicoCompartilharPessoaRelacionada = ServicoJavaUtil.getServico(CLASSE_SERVICO, 
				"Compartilhando pessoa relacionada na base da cooperativa ...", ResultEvent.RESULT, onResultCompartilhamentoPessoaRelacionada);
		}
		
		private function init(evento : Event) : void {
			
			setTelaLista(this.telaLista);
			this.telaLista.produtosBancoob = this.produtosBancoob;
			this.telaLista.addEventListener(frmListarRelacionamentoPessoa.TAB_CHANGED, onTabChanged);

			setTelaEdicao(this.telaEdicao);
			this.telaEdicao.produtosBancoob = this.produtosBancoob;
			this.telaEdicao.addEventListener(FaultEvent.FAULT, onFalhaAberturaEdicao);
			
			textoAcao = "RELACIONAMENTO";
			textoModulo = this.produtosBancoob ? "PRODUTOS BANCOOB - RELACIONAMENTO" :
					"RELACIONAMENTO";
			iconModulo = "br/com/bancoob/imagens/icos/parceria.png";
		}
		
		public override function dispose():void {
			super.dispose();
			this.removeEventListener("carregou", init);
			telaEdicao.dispose();
			telaEdicao = null;
		}
		
		protected override function mostraBotoesMudaGrid(evt:ListEvent = null):void{
			this.listaBotoes.botAlterar.enabled = isItemListaEditavel();
			super.mostraBotoesMudaGrid(evt);
		}

		
		private function onFalhaAberturaEdicao(evento : FaultEvent) : void {
			exibirTelaListar();	
		}
		
		private function onTabChanged(evento : Event) : void {
			
			var grid : Grid = this.telaLista.obterGrid();
			if(grid) {
				grid.selectedIndex = -1;
				grid.setFocus();
			}

			if (this.telaLista.isAbaCedidosSelecionada()) {	
				this.mostraBotoesLista();
			} else if (this.telaLista.isAbaExercidosSelecionada()) {
				removerBotoes();
			}
		}
		
		protected function removerBotoes(mostrar : Boolean = false):void	{
			this.listaBotoes.mostraBotao(this.listaBotoes.botAlterar, mostrar);
			this.listaBotoes.mostraBotao(this.listaBotoes.botExcluir, mostrar);
			this.listaBotoes.mostraBotao(this.listaBotoes.botOk, mostrar);
			this.listaBotoes.mostraBotao(this.listaBotoes.botCancelar, mostrar);
			this.listaBotoes.mostraBotao(this.listaBotoes.botVoltar, mostrar);
			this.listaBotoes.mostraBotao(this.listaBotoes.botVer, mostrar);
			this.listaBotoes.mostraBotao(this.listaBotoes.botNovo, mostrar);
		}		

		protected override function exibirBotaoAlteracao():void {
			if (isItemListaEditavel()) {
				super.exibirBotaoAlteracao();
			}
		}

		private function isItemListaEditavel():Boolean {
			var relacionamento : RelacionamentoPessoaVO = RelacionamentoPessoaVO(this.itemLista);
			return relacionamento && (!relacionamento.dataFimMandato 
					|| (relacionamento.dataFimMandato.data >= RelacionamentoPessoaUtils
							.getDataUltimaHoraDoDia(new Date())));
		}

		protected override function configurarDestinosServicos(destinoVO:DestinoVO):void {
			this.telaLista.servicoConsulta.configurarDestino(destinoVO);
			this.telaEdicao.configurarDestinosServicos(destinoVO);
			this.servicoValidarTransicaoPessoaRelacionada.configurarDestino(destinoVO);
			this.servicoCompartilharPessoaRelacionada.configurarDestino(destinoVO);
		}
		
		protected override function abrirInclusao():void {
			this.telaEdicao._novo = true;
			this.telaEdicao.modoConsulta = false;
			super.abrirInclusao();			
		}
		
		protected override function exibirTelaEdicao(consulta:Boolean=false):void {
			validarTransicaoPessoaRelacionada(consulta);
		}
		
		private function mostrarTelaEdicao():void {
			this.telaEdicao._novo = false;
			this.telaEdicao.modoConsulta = false;
			super.exibirTelaEdicao(false);
		}
		
		private function mostrarTelaConsulta():void {
			this.telaEdicao._novo = false;
			this.telaEdicao.modoConsulta = true;
			super.exibirTelaConsulta(false);
		}
		
		protected override function exibirTelaConsulta(consulta:Boolean=true):void {
			if(!isRegistroEmAprovacao()){
				mostrarTelaConsulta();
			}else{
				validarTransicaoPessoaRelacionada(consulta);
			}			
		}
		
		protected override function registroExcluido(event : Event) : void {
			super.registroExcluido(event);
			this.telaLista.obterGrid().selectedIndex = -1;
			this.telaLista.obterGrid().setFocus();
			this.telaLista.obterLista();
		}
		
		private function validarTransicaoPessoaRelacionada(consulta:Boolean):void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.pessoaRelacionada = returnPessoaRelacionadaSelecionada();
			dto.dados.produtosBancoob = this.produtosBancoob;
			dto.dados.telaConsulta = consulta;
			servicoValidarTransicaoPessoaRelacionada.getOperation(OPERACAO_VALIDAR_TRANSICAO_PESSOA_RELACIONADA).send(dto);
		}
		
		private function onResultTransicaoPessoaRelacionada(evento : ResultEvent) : void {
			var existeTransicao:Boolean = evento.result.dados.existeTransicao as Boolean;
			this.telaConsulta = evento.result.dados.telaConsulta as Boolean;
			if(!existeTransicao){
				compartilhaPessoaRelacionada();
			} else {
				telaConsulta == true ? mostrarTelaConsulta() : mostrarTelaEdicao();  				
			}
		}
		
		private function compartilhaPessoaRelacionada():void{
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.pessoaRelacionada = returnPessoaRelacionadaSelecionada();
			dto.dados.produtosBancoob = this.produtosBancoob;
			dto.dados.telaConsulta = this.telaConsulta;
			servicoCompartilharPessoaRelacionada.getOperation(OPERACAO_COMPARTILHAR_PESSOA_RELACIONADA).send(dto);
		}
		
		private function onResultCompartilhamentoPessoaRelacionada(evento:ResultEvent):void {
			var isCompSucesso:Boolean = evento.result.dados.isCompSucesso as Boolean;
			var telaConsulta:Boolean = evento.result.dados.telaConsulta as Boolean;
			if(isCompSucesso){
				telaConsulta == true ? mostrarTelaConsulta() : mostrarTelaEdicao();
			}
		}
		
		private function returnPessoaRelacionadaSelecionada():PessoaVO{
			return (telaLista.obterGrid().selectedItem as RelacionamentoPessoaVO).pessoaRelacionada;
		}
	}
}