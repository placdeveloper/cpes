package br.com.sicoob.capes.cadastrarReferenciaPessoa{
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.grid.Grid;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.utils.plataformaatendimento.IListaPlataformaAtendimentoCAPES;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCAPESBase;
	
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	public class frmListarReferenciaPessoa extends frmListarReferenciaPessoaView implements IListaPlataformaAtendimentoCAPES
	{
		private var listaReferenciaPessoa:ArrayCollection;
		
		public static const OPERACAO_OBTER_DADOS_SELECAO: String = "obterDadosSelecao";
		
		public static const CLASSE_SERVICO: String = 
				"br.com.sicoob.capes.cadastro.fachada.ReferenciaFachada";
		
		public var servicoConsulta:ServicoJava;
		public var servicoEdicao:ServicoJava;
		
		//--------------------------------------------------------------------------
	    //  Propriedades
	    //--------------------------------------------------------------------------
	    /**
	     *  Construtor.
	     */
		public function frmListarReferenciaPessoa()
		{
			super();
			
			servicoConsulta = ServicoJavaUtil.getServico(CLASSE_SERVICO);
			servicoConsulta.addEventListener(ResultEvent.RESULT, retornoObterLista);
			
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
	    //--------------------------------------------------------------------------
	    //  Métodos
	    //--------------------------------------------------------------------------

		private function init(evt:FlexEvent=null):void {
			
			listaReferenciaPessoa = new ArrayCollection();
			
			gridReferenciaPessoa.dataProvider = listaReferenciaPessoa;
			
			obterLista();
		}
		
		public function obterGrid():Grid
		{
			this.gridReferenciaPessoa.validateNow();
			return this.gridReferenciaPessoa;
		}

		public function obterLista():void{

			MostraCursor.setBusyCursor("Carregando Registros!", Application.application, MostraCursor.CURSOR_PESQUISAR);

			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.carregarTelPessoaRef = true;
			dto.dados.pessoa = TelaPlataformaAtendimentoCAPESBase.getPessoaSelecionada();
            servicoConsulta.getOperation(OPERACAO_OBTER_DADOS_SELECAO).send(dto);
		}

		public function retornoObterLista(event:ResultEvent):void{
			
			listaReferenciaPessoa.list = event.result.dados["lista"];
			MostraCursor.removeBusyCursor();
			
			this.dispatchEvent(new Event(Modulo.LISTA_CARREGADA));	
		}
		 	  	
	}
}