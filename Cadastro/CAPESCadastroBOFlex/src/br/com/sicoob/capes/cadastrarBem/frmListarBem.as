package br.com.sicoob.capes.cadastrarBem{
	import br.com.bancoob.componentes.Modulo;
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
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	public class frmListarBem extends frmListarBemView implements IListaPlataformaAtendimentoCAPES {
		
		private var _listaBem:ArrayCollection = new ArrayCollection();
		public var NUMPESSOA:int;
		
		public static const OPERACAO_OBTER_DADOS_SELECAO: String = "obterDadosSelecao";
		
		public static const CLASSE_SERVICO: String = 
				"br.com.sicoob.capes.cadastro.fachada.bemantigo.BemFachada";
		
		public var servicoConsulta:ServicoJava;
		public var servicoEdicao:ServicoJava;
		
		//Informações da Pessoa
		private var pessoa:PessoaVO;
		
		//--------------------------------------------------------------------------
	    //  Propriedades
	    //--------------------------------------------------------------------------
	    /**
	     *  Construtor.
	     */
		public function frmListarBem()
		{
			super();
			
			servicoConsulta = ServicoJavaUtil.getServico(CLASSE_SERVICO, "Obtendo dados...", 
					ResultEvent.RESULT, retornoObterLista);
			
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
	    //--------------------------------------------------------------------------
	    //  Métodos
	    //--------------------------------------------------------------------------
		private function init(evt:FlexEvent=null):void {
			
			this.gridBem.dataProvider = listaBem;
			obterLista();
		}
		
		public function obterGrid():Grid
		{
			this.gridBem.validateNow();
			return this.gridBem;
		}

		public function obterLista():void{

			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.pessoa = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(
					TelaPlataformaAtendimentoCAPESBase.getPessoaPlataforma());
            servicoConsulta.getOperation(OPERACAO_OBTER_DADOS_SELECAO).send(dto);
		}

		public function retornoObterLista(event:ResultEvent):void{
			
			this.listaBem.list = event.result.dados.lista;
			this.dispatchEvent(new Event(Modulo.LISTA_CARREGADA));	
		}
		
		public function setPessoa(pessoaVO:PessoaVO=null):void {
			this.pessoa = pessoaVO;		
		}
		
		public function configurarDestino(destinoVO:DestinoVO):void {
			this.servicoConsulta.configurarDestino(destinoVO);
		}
		
		public function get listaBem():ArrayCollection {
			return this._listaBem;
		}
	}
}