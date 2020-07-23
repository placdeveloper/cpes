package br.com.sicoob.capes.cadastrarFonteRenda
{
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.grid.Grid;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.componentes.PlataformaAtendimento.IListaPlataformaAtendimento;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaVO;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCAPESBase;
	
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	public class frmListarFonteRenda extends frmListarFonteRendaView implements IListaPlataformaAtendimento
	{
		private var listaFonteRenda:ArrayCollection;
		private var pessoa:PessoaVO;
		
		public static const OPERACAO_OBTER_DADOS_SELECAO: String = "obterDadosSelecao";
		public var servicoConsulta:ServicoJava;
		
		
		//--------------------------------------------------------------------------
	    //  Propriedades
	    //--------------------------------------------------------------------------
	    /**
	     *  Construtor.
	     */		
		public function frmListarFonteRenda()
		{
			super();
			
			servicoConsulta = ServicoJavaUtil.getServico(FonteRendaSelecao.CLASSE_SERVICO);
			
			servicoConsulta.addEventListener(ResultEvent.RESULT, retornoObterLista);
		
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);	
			
		}

	    //--------------------------------------------------------------------------
	    //  Métodos
	    //--------------------------------------------------------------------------
		private function init(evt:FlexEvent=null):void {
			
			listaFonteRenda = new ArrayCollection();
			gridDados.dataProvider = listaFonteRenda;
			
			obterLista();
		}
		
		public function obterGrid():Grid {
			this.gridDados.validateNow();
			return this.gridDados;
		}
		
		public function obterLista():void{
			MostraCursor.setBusyCursor("Carregando Registros ...", 
					Application.application, MostraCursor.CURSOR_PESQUISAR);

			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.pessoa = TelaPlataformaAtendimentoCAPESBase.getPessoaSelecionada(); 
			
            servicoConsulta.getOperation(OPERACAO_OBTER_DADOS_SELECAO).send(dto);
					
		}

		public function retornoObterLista(event:ResultEvent):void{
			MostraCursor.removeBusyCursor();
		
			listaFonteRenda.list = event.result.dados["lista"];
			
			this.dispatchEvent(new Event(Modulo.LISTA_CARREGADA));	
		}		
		
		public function returnSizeList():int{
			return listaFonteRenda.length;
		}
	}
}