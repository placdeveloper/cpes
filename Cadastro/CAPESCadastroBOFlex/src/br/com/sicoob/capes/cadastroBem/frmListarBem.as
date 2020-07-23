package br.com.sicoob.capes.cadastroBem {
	
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.events.IndexChangedEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.grid.Grid;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.FormataNumero;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.app.Application;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.corporativo.componentes.plataformaatendimento.ProcuraClientePlataformaCAPES;
	import br.com.sicoob.capes.utils.plataformaatendimento.IListaPlataformaAtendimentoCAPES;
	
	/**
	 * Tela de listagem dos bens.
	 * 
	 * @author Bruno.Carneiro
	 */
	public class frmListarBem extends frmListarBemView implements IListaPlataformaAtendimentoCAPES {
		
		private static const CLASSE_SERVICO:String = "br.com.sicoob.capes.cadastro.fachada.bem.BemPessoaCompartilhamentoFachada";
		private static const OPERACAO_OBTER_DADOS_SELECAO:String = "obterDadosListagem";
		private static const OPERACAO_OBTER_DADOS_SELECAO_PARCERIAS:String = "obterDadosListagemParcerias";
		private static const MENSAGEM_OBTENDO_DADOS:String = "Obtendo dados...";
		
		private static const ABA_PROPRIEDADES:int = 0;
		private static const ABA_PARTICIPACOES:int = 1;
		
		private var servicoConsulta:ServicoJava;
		private var servicoConsultaParcerias:ServicoJava;

		private var consultaParceriasRealizada:Boolean = false;
		
		/**
		 * Construtor.
		 */
		public function frmListarBem(){
			super();
			servicoConsulta = ServicoJavaUtil.getServico(CLASSE_SERVICO, MENSAGEM_OBTENDO_DADOS, ResultEvent.RESULT, retornoObterLista);
			servicoConsultaParcerias = ServicoJavaUtil.getServico(CLASSE_SERVICO, MENSAGEM_OBTENDO_DADOS, ResultEvent.RESULT, retornoObterListaParcerias);
			this.addEventListener(FlexEvent.CREATION_COMPLETE, inicializar);
		}
		
		/**
		 * Método chamado após a construção da classe.
		 */
		private function inicializar(evt:FlexEvent=null):void {
			navegacao.addEventListener(IndexChangedEvent.CHANGE, aoTrocarAba);
			obterLista();
		}
		
		/**
		 * Realiza a configuração dos destinos dos serviços.
		 */
		public function configurarDestino(destinoVO:DestinoVO):void {
			servicoConsulta.configurarDestino(destinoVO);
			servicoConsultaParcerias.configurarDestino(destinoVO);
		}
		
		/**
		 * Obtém a grid de dados para uso no módulo principal.
		 */
		public function obterGrid():Grid {
			gridPropriedades.validateNow();
			return gridPropriedades;
		}
		
		/**
		 * Obtém a lista de registros cadastrados para a pessoa selecionada.
		 */
		public function obterLista():void {
			MostraCursor.setBusyCursor(MENSAGEM_OBTENDO_DADOS, Application.application, MostraCursor.CURSOR_PROGRESSO);
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.idPessoaCompartilhamento = ProcuraClientePlataformaCAPES.getPessoaSelecionada().idCompartilhamento;
			servicoConsulta.getOperation(OPERACAO_OBTER_DADOS_SELECAO).send(dto);
		}
		
		/**
		 * Obtém a lista de bem cadastrados em que a pessoa selecionada é parceira.
		 **/
		private function obterListaParcerias():void {
			MostraCursor.setBusyCursor(MENSAGEM_OBTENDO_DADOS, Application.application, MostraCursor.CURSOR_PROGRESSO);
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.idPessoaCompartilhamento = ProcuraClientePlataformaCAPES.getPessoaSelecionada().idCompartilhamento;
			servicoConsultaParcerias.getOperation(OPERACAO_OBTER_DADOS_SELECAO_PARCERIAS).send(dto);
		}
		
		/**
		 * Retorno da consulta de registros.
		 */
		private function retornoObterLista(event:ResultEvent):void{
			this.gridPropriedades.dataProvider = event.result.dados.lista;
			
			var objeto:Object = event.result.dados.valores;
			if(objeto != null){
				this.valorTotalAvaliado.text = "R$ " + FormataNumero.formata(objeto.valorTotalAvaliado);
				this.valorTotalDeclarado.text = "R$ " + FormataNumero.formata(objeto.valorTotalDeclarado);
				this.valorTotalImovelAvaliado.text = "R$ " + FormataNumero.formata(objeto.valorTotalImovelAvaliado);
				this.valorTotalImovelDeclarado.text = "R$ " + FormataNumero.formata(objeto.valorTotalImovelDeclarado);
				this.valorTotalMovelAvaliado.text = "R$ " + FormataNumero.formata(objeto.valorTotalMovelAvaliado);
				this.valorTotalMovelDeclarado.text = "R$ " + FormataNumero.formata(objeto.valorTotalMovelDeclarado);
			}
			
			this.dispatchEvent(new Event(Modulo.LISTA_CARREGADA));
			MostraCursor.removeBusyCursor();
		}
		
		/**
		 * Retorno da consulta de registros de parcerias.
		 */
		private function retornoObterListaParcerias(event:ResultEvent):void{
			this.gridParcerias.dataProvider = event.result.dados.lista;
			this.dispatchEvent(new Event(Modulo.LISTA_CARREGADA));
			MostraCursor.removeBusyCursor();
		}
		
		/**
		 * Método chamado ao trocar as abas de navegação
		 */
		private function aoTrocarAba(evento:Event):void {
			if (initialized) {
				var valoresVisiveis:Boolean = true;
				
				removerFocoItensSelecionados();
	
				if(navegacao.selectedIndex == ABA_PARTICIPACOES) {
					valoresVisiveis = false;
					
					var lista:ArrayCollection = gridParcerias.dataProvider as ArrayCollection;
					
					if((lista == null || lista.length == 0) && !consultaParceriasRealizada) {
						consultaParceriasRealizada = true;
						obterListaParcerias();
					}
				}
				
				this.rotuloTotalAvaliado.visible = valoresVisiveis;
				this.rotuloTotalDeclarado.visible = valoresVisiveis;
				this.rotuloTotalImovelAvaliado.visible = valoresVisiveis;
				this.rotuloTotalImovelDeclarado.visible = valoresVisiveis;
				this.rotuloTotalMovelAvaliado.visible = valoresVisiveis;
				this.rotuloTotalMovelDeclarado.visible = valoresVisiveis;
				
				this.valorTotalAvaliado.visible = valoresVisiveis;
				this.valorTotalDeclarado.visible = valoresVisiveis;
				this.valorTotalImovelAvaliado.visible = valoresVisiveis;
				this.valorTotalImovelDeclarado.visible = valoresVisiveis;
				this.valorTotalMovelAvaliado.visible = valoresVisiveis;
				this.valorTotalMovelDeclarado.visible = valoresVisiveis;
			} else {
				evento.stopImmediatePropagation();
			}
		}
		
		public function removerFocoItensSelecionados():void {
			gridParcerias.selectedIndex = -1;
			gridPropriedades.selectedIndex = -1;
		}
	}
}