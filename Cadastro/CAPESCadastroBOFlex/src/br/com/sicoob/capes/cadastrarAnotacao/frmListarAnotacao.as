package br.com.sicoob.capes.cadastrarAnotacao
{
	import flash.events.Event;
	
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.ParametrosAssistenteAtendimento;
	import br.com.bancoob.sisbr.componentes.PlataformaAtendimento.ITelaBasePlataformaAtendimento;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.AnotacaoVO;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCustomizadoCAPES;
	
	public class frmListarAnotacao extends frmListarAnotacaoView implements ITelaBasePlataformaAtendimento {
		
		public static const OPERACAO_OBTER_DADOS_ANOTACOES_SELECAO: String = "obterDadosAnotacoesSelecao";
		public static const OPERACAO_FLEXIBILIZAR: String = "flexibilizarAnotacao";
		public static const OPERACAO_BAIXAR: String = "baixarAnotacao";
		public static const OPERACAO_BAIXAR_SEM_VALIDAR: String = "baixarAnotacaoSemValidacao";
		
		public var servicoConsulta:ServicoJava;
		public var servicoEdicao:ServicoJava;
		
		static private const ABA_VIGENTES : int = 0;
		static private const ABA_BAIXADAS : int = 1;
		
	    /**
	     *  Construtor.
	     */
		public function frmListarAnotacao()	{
			super();
			
			servicoConsulta = ServicoJavaUtil.getServico(AnotacaoSumarioSelecao.CLASSE_SERVICO);
			servicoConsulta.addEventListener(ResultEvent.RESULT, retornoObterLista);

			servicoEdicao = ServicoJavaUtil.getServico(AnotacaoSumarioSelecao.CLASSE_SERVICO);
			servicoEdicao.addEventListener(ResultEvent.RESULT, retornoEdicao);
			
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(evt:FlexEvent=null):void {
			navegacaoTab.addEventListener(Event.CHANGE, clickAbas);
			abaAnotacoesVigentes.addEventListener(Modulo.REGISTRO_SELECIONADO, registroSelecionado);
			abaAnotacoesBaixadas.addEventListener(Modulo.REGISTRO_SELECIONADO, registroSelecionado);
		}
		
        private function clickAbas(evento:Event) : void{
        	
    		abaAnotacoesBaixadas.listaAnotacoes.selectedIndex = -1;
    		abaAnotacoesVigentes.listaAnotacoes.selectedIndex = -1;
    		this.dispatchEvent(new Event(Modulo.REGISTRO_SELECIONADO));	
        } 		
		
		private function retornoObterLista(event: ResultEvent): void {
			
			abaAnotacoesVigentes.carregarAnotacoes(event.result.dados["listaVigentes"]);
			abaAnotacoesBaixadas.carregarAnotacoes(event.result.dados["listaBaixadas"]);
			
			MostraCursor.removeBusyCursor();
		}
		
	    //--------------------------------------------------------------------------
	    //  Métodos auxiliares
	    //--------------------------------------------------------------------------
		public function listarAnotacoes(): void {

			MostraCursor.setBusyCursor("Carregando Registros!", 
					Application.application, MostraCursor.CURSOR_PESQUISAR);
			var dto: RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.pessoa = TelaPlataformaAtendimentoCustomizadoCAPES.getPessoaSelecionada();
            servicoConsulta.getOperation(OPERACAO_OBTER_DADOS_ANOTACOES_SELECAO).send(dto);			
		}		
		
				
		public function obterAnotacaoSelecionada(): AnotacaoVO {
			
        	var aba : int =  navegacaoTab.selectedIndex;
			var itemLista:Object = null;
			         	      	
        	if(aba == ABA_VIGENTES){
				itemLista = abaAnotacoesVigentes.obterAnotacaoSelecionada();
        	} else {
        		itemLista = abaAnotacoesBaixadas.obterAnotacaoSelecionada();
        	}
			return AnotacaoVO(itemLista);
		}
	
		public function selecionarAbaVigentes() : void {
			navegacaoTab.selectedIndex = ABA_VIGENTES;
		}
	
	    //--------------------------------------------------------------------------
	    //  Listeners
	    //--------------------------------------------------------------------------
		public function flexibilizar(evt:Event = null) :void {
			
			MostraCursor.setBusyCursor("Flexiblizando Anotação...", Application.application, 
					MostraCursor.CURSOR_PROGRESSO);
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.anotacao = obterAnotacaoSelecionada();
			servicoEdicao.getOperation(OPERACAO_FLEXIBILIZAR).send(dto);
		}		

		public function baixar(evt:Event = null) :void {
			
			MostraCursor.setBusyCursor("Baixando Anotação...", Application.application, 
					MostraCursor.CURSOR_PROGRESSO);
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.anotacao = obterAnotacaoSelecionada(); 
			servicoEdicao.getOperation(OPERACAO_BAIXAR).send(dto);
		}
		
		public function baixarSemValidacao(evt:Event = null) :void {
			
			MostraCursor.setBusyCursor("Baixando Anotação...", Application.application, 
				MostraCursor.CURSOR_PROGRESSO);
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.anotacao = obterAnotacaoSelecionada(); 
			servicoEdicao.getOperation(OPERACAO_BAIXAR_SEM_VALIDAR).send(dto);
		}
		
		private function retornoEdicao(event: ResultEvent): void {

			MostraCursor.removeBusyCursor();
			this.dispatchEvent(new Event(Modulo.REGISTRO_GRAVADO));
		}

		private function registroSelecionado(evt:Event):void {
			this.dispatchEvent(evt);	
		}				

	    //--------------------------------------------------------------------------
	    //  Métodos das interfaces
	    //--------------------------------------------------------------------------
		public function trocarTelaEntrar(params:ParametrosAssistenteAtendimento=null):void{};
		public function trocarTelaSair(params:ParametrosAssistenteAtendimento=null):void{};					
	}
}