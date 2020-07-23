package br.com.sicoob.capes.cadastrarRelacionamentoInstituicao 
{
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.grid.Grid;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.ParametrosAssistenteAtendimento;
	import br.com.bancoob.sisbr.componentes.PlataformaAtendimento.ITelaBasePlataformaAtendimento;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.capes.cadastrarRelacionamentoInstituicao.dto.RelacionamentoInstituicaoDTO;
	import br.com.sicoob.capes.utils.ConversaoPessoaUtil;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.corporativo.componentes.plataformaatendimento.ProcuraClientePlataformaCAPES;
	
	import flash.events.Event;
	
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	
	public class frmListarRelacionamentoInstituicao extends frmListarRelacionamentoInstituicaoView implements ITelaBasePlataformaAtendimento {
		
		public static const OPERACAO_OBTER_DADOS_SELECAO: String = "obterDadosSelecao";	
		public var servicoConsulta:ServicoJava;
		
	    /**
	     *  Construtor.
	     */
		public function frmListarRelacionamentoInstituicao() {
			
			super();
			
			servicoConsulta = ServicoJavaUtil.getServico(RelacionamentoInstituicaoSelecao.CLASSE_SERVICO);
			servicoConsulta.addEventListener(ResultEvent.RESULT, retornoObterLista);
			
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}

		public function trocarTelaEntrar(params:ParametrosAssistenteAtendimento=null):void{};
		public function trocarTelaSair(params:ParametrosAssistenteAtendimento=null):void{};		
		
	    //--------------------------------------------------------------------------
	    //  Métodos auxiliares
	    //--------------------------------------------------------------------------
		private function init(evt:FlexEvent=null):void {
			obterGrid().addEventListener(ListEvent.CHANGE, registroSelecionado);
		}
		
		public function obterGrid():Grid {
			this.grid.validateNow();
			return this.grid;
		}
		
		public function obterRelacionamentoInstituicaoSelecionado(): RelacionamentoInstituicaoDTO {
			var itemLista:RelacionamentoInstituicaoDTO = grid.selectedItem as RelacionamentoInstituicaoDTO;
			return itemLista;
		}

		public function listarRelacionamentos():void {

			MostraCursor.setBusyCursor("Carregando Registros!", 
					Application.application, MostraCursor.CURSOR_PESQUISAR);

			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.pessoa = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(
					ProcuraClientePlataformaCAPES.getPessoaSelecionada());
				
            servicoConsulta.getOperation(OPERACAO_OBTER_DADOS_SELECAO).send(dto);
		}
	
		public function retornoObterLista(event:ResultEvent):void{
			
			var grid:Grid = obterGrid();
			grid.dataProvider = null;
			grid.dataProvider = event.result.dados["lista"];
			grid.selectedIndex = -1;
			
			MostraCursor.removeBusyCursor();
			this.dispatchEvent(new Event(Modulo.LISTA_CARREGADA));	
		}
				
		private function registroSelecionado(evt:ListEvent=null):void {
			this.dispatchEvent(new Event(Modulo.REGISTRO_SELECIONADO));	
		}			
	}
}