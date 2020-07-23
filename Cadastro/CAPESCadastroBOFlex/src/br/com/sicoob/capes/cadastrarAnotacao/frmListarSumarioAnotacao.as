package br.com.sicoob.capes.cadastrarAnotacao
{
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.grid.Grid;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.ParametrosAssistenteAtendimento;
	import br.com.bancoob.sisbr.componentes.PlataformaAtendimento.ITelaBasePlataformaAtendimento;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCustomizadoCAPES;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	public class frmListarSumarioAnotacao extends frmListarSumarioAnotacaoView implements ITelaBasePlataformaAtendimento {

		static private const OPERACAO_OBTER_DADOS_SELECAO: String = "obterDadosSelecao";

		public var servicoListaSumario:ServicoJava;
		
		private var listaSumario:ArrayCollection = new ArrayCollection();
		private var exibirBotao: Boolean = false;
		
		//--------------------------------------------------------------------------
	    //  Propriedades
	    //--------------------------------------------------------------------------
	    /**
	     *  Construtor.
	     */
		public function frmListarSumarioAnotacao() {
			super();
			servicoListaSumario = ServicoJavaUtil.getServico(AnotacaoSumarioSelecao.CLASSE_SERVICO);
			servicoListaSumario.addEventListener(ResultEvent.RESULT, retornoObterLista);
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
	    //--------------------------------------------------------------------------
	    //  Métodos
	    //--------------------------------------------------------------------------
	    
		private function init(evt:FlexEvent=null):void {
			gridSumarioAnotacoes.dataProvider = listaSumario;
		}
        
		public function obterGrid():Grid {
			this.gridSumarioAnotacoes.validateNow();
			return this.gridSumarioAnotacoes;
		}	
		
		public function listarSumario():void {
			
			MostraCursor.setBusyCursor(
					"Carregando Registros!", Application.application, MostraCursor.CURSOR_PESQUISAR);
			
			var pessoa: PessoaPlataformaVO = TelaPlataformaAtendimentoCustomizadoCAPES.getPessoaPlataforma();
			
			var dto: RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.pessoa = TelaPlataformaAtendimentoCustomizadoCAPES.getPessoaSelecionada();
			
            servicoListaSumario.getOperation(OPERACAO_OBTER_DADOS_SELECAO).send(dto);
		}

		/**
		 * Retorno dos dados da definição.
		 *  
		 * @param event O evento com o resultado do método.
		 */
		private function retornoObterLista(event: ResultEvent): void {

			listaSumario.list = event.result.dados["lista"];
			
			exibirBotao = event.result.dados.exibirBotaoBaixaAnotacao;
			
			gridSumarioAnotacoes.selectedIndex = -1;
			gridSumarioAnotacoes.selectable = false;					
			MostraCursor.removeBusyCursor();
		}
		
		public function isExibirBotaoBaixarAnotacao(): Boolean {
			return exibirBotao;
		} 
		
		public function trocarTelaEntrar(params:ParametrosAssistenteAtendimento=null):void{};
		public function trocarTelaSair(params:ParametrosAssistenteAtendimento=null):void{};
				       
	}
}