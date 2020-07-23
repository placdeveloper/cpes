package br.com.sicoob.capes.corporativo.componentes.listarAnotacoes {
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.grid.Grid;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.corporativo.componentes.listarAnotacoes.dto.AnotacaoPessoaDTO;
	
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	public class PainelListaAnotacoes extends PainelListaAnotacoesView {
		private static const OPERACAO_OBTER_DADOS_SELECAO: String = "obterDadosSelecao";
		
		public static const CLASSE_SERVICO: String = "br.com.sicoob.capes.corporativo.fachada.AnotacaoFachada";
				
		public var servicoConsulta:ServicoJava = new ServicoJava();
		
		public function PainelListaAnotacoes() {
			super();
			
			registerClassAlias("br.com.sicoob.sisbr.capes.integracao.negocio.dtos.AnotacaoPessoaDTO", AnotacaoPessoaDTO);
			
			servicoConsulta.source = CLASSE_SERVICO;
        	servicoConsulta.mensagemEspera = "Carregando dados...";
			servicoConsulta.addEventListener(ResultEvent.RESULT, retornoObterLista);
			
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}

		private function init(evt:FlexEvent=null):void {
			exibirTelaLista();
		}
		
	    //--------------------------------------------------------------------------
	    //  Listeners
	    //--------------------------------------------------------------------------
		private function listarClicado(evt:MouseEvent=null): void {
			vsTelas.selectedIndex = 0;	
		}
		
		private function retornoObterLista(event: ResultEvent): void {
			carregarAnotacoes(event.result.dados["listaAnotacoes"]);
			MostraCursor.removeBusyCursor();
		}		
		
	    //--------------------------------------------------------------------------
	    //  Métodos auxiliares
	    //--------------------------------------------------------------------------
		public function carregarAnotacoes(listaAnotacoes:Object = null): void {
			this.listaAnotacoes.dataProvider = null;
			this.listaAnotacoes.dataProvider = listaAnotacoes;
			this.listaAnotacoes.selectedIndex = -1;
		}		
		
		public function obterGrid():Grid {
			this.listaAnotacoes.validateNow();
			return this.listaAnotacoes;
		}

		public function listar(idPessoa:int, idInstituicao:int): void {
			MostraCursor.setBusyCursor("Carregando Registros!", Application.application, MostraCursor.CURSOR_PESQUISAR);
			var dto: RequisicaoReqDTO = new RequisicaoReqDTO;
			dto.dados.idPessoa = idPessoa;
			dto.dados.idInstituicao = idInstituicao;
			dto.dados.baixadas = baixadas;
            servicoConsulta.getOperation(OPERACAO_OBTER_DADOS_SELECAO).send(dto);
		}

		private function exibirTelaLista(evt:MouseEvent=null): void {
			vsTelas.selectedIndex = 0;	
		}

		public function configurarDestino(destino:DestinoVO) : void {
			this.destino = destino;
			servicoConsulta.configurarDestino(destino);
		}
	}
}