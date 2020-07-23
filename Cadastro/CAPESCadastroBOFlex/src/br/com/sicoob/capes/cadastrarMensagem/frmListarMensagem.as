package br.com.sicoob.capes.cadastrarMensagem{
	
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.grid.Grid;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaVO;
	import br.com.sicoob.capes.utils.ConversaoPessoaUtil;
	import br.com.sicoob.capes.utils.plataformaatendimento.IListaPlataformaAtendimentoCAPES;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCAPESBase;
	
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	public class frmListarMensagem extends frmListarMensagemView implements IListaPlataformaAtendimentoCAPES {
		
		private static const OPERACAO_OBTER_DADOS_SELECAO: String = "obterDadosSelecao";
		private static const CLASSE_SERVICO: String = "br.com.sicoob.capes.cadastro.fachada.MensagemFachada";
		
		private var servicoConsulta:ServicoJava;
		
		//Informações da Pessoa
		private var pessoa:PessoaVO;
		
		//--------------------------------------------------------------------------
	    //  Propriedades
	    //--------------------------------------------------------------------------
	    /**
	     *  Construtor.
	     */
		public function frmListarMensagem() {
			super();
			
			servicoConsulta = ServicoJavaUtil.getServico(CLASSE_SERVICO);
			servicoConsulta.addEventListener(ResultEvent.RESULT, retornoObterLista);
			
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
	    //--------------------------------------------------------------------------
	    //  Métodos
	    //--------------------------------------------------------------------------
		private function init(evt:FlexEvent=null):void {
			obterLista();
		}

		public function obterGrid():Grid {
			this.gridMensagem.validateNow();
			return this.gridMensagem;
		}

		public function obterLista():void{
			MostraCursor.setBusyCursor("Carregando Registros!", Application.application, MostraCursor.CURSOR_PESQUISAR);

			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.pessoa = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(TelaPlataformaAtendimentoCAPESBase.getPessoaPlataforma()).pessoa;
            servicoConsulta.getOperation(OPERACAO_OBTER_DADOS_SELECAO).send(dto);
		}

		public function retornoObterLista(event:ResultEvent):void{
			gridMensagem.dataProvider = event.result.dados["lista"];
			MostraCursor.removeBusyCursor();
			this.dispatchEvent(new Event(Modulo.LISTA_CARREGADA));	
		}
		
		public function configurarDestino(destino:DestinoVO):void {
			servicoConsulta.configurarDestino(destino);
		}
	}
}