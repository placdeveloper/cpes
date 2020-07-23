package br.com.sicoob.capes.cadastrarContato
{
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.grid.Grid;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.EnderecoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
	import br.com.sicoob.capes.utils.plataformaatendimento.IListaPlataformaAtendimentoCAPES;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCAPESBase;
	
	import flash.events.Event;
	
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	
	public class frmListarContatos extends frmListarContatosView implements IListaPlataformaAtendimentoCAPES {
		public static const ABA_ENDERECO : int = 0;
		public static const ABA_TELEFONE : int = 1;		
		public static const ABA_EMAIL : int = 2;	
		
		private static const CLASSE_SERVICO_ENDERECO:String = "br.com.sicoob.capes.cadastro.fachada.EnderecoFachada";
		private static const CLASSE_SERVICO_CONTATO:String = "br.com.sicoob.capes.cadastro.fachada.ContatoFachada";		
		public static const OPERACAO_OBTER_DADOS_SELECAO: String = "obterDadosSelecao";
		public static const OPERACAO_TORNAR_CORRESPONDENCIA: String = "tornarPadraoCorrespondencia";

		private var servicoEdicaoEndereco:ServicoJava;
		private var servicoListaContatos:ServicoJava;

		public static const CLICK_ABAS:String = "clickAbas";
				
	    /**
	     *  Construtor.
	     */
		public function frmListarContatos()	{
			super();
			
			servicoEdicaoEndereco = ServicoJavaUtil.getServico(CLASSE_SERVICO_ENDERECO);
			servicoListaContatos = ServicoJavaUtil.getServico(CLASSE_SERVICO_CONTATO);
			
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(evt:FlexEvent=null):void {
			navegacaoTab.addEventListener(Event.CHANGE, clickAbas);
			abaEndereco.addEventListener(Modulo.REGISTRO_SELECIONADO, registroSelecionado);
			abaTelefone.addEventListener(Modulo.REGISTRO_SELECIONADO, registroSelecionado);
			abaEmail.addEventListener(Modulo.REGISTRO_SELECIONADO, registroSelecionado);

			abaEndereco.addEventListener(ListEvent.ITEM_DOUBLE_CLICK, duploClique);
			abaTelefone.addEventListener(ListEvent.ITEM_DOUBLE_CLICK, duploClique);
			abaEmail.addEventListener(ListEvent.ITEM_DOUBLE_CLICK, duploClique);
			
			servicoEdicaoEndereco.addEventListener(ResultEvent.RESULT, retornoCorrespondenciaEndereco);
			servicoListaContatos.addEventListener(ResultEvent.RESULT, retornoListarContato);
		}
		
		public function configurarDestinosServicos(destinoVO:DestinoVO):void{
			servicoListaContatos.configurarDestino(destinoVO);
			servicoEdicaoEndereco.configurarDestino(destinoVO);
		}		
				
        private function clickAbas(evento:Event) : void{
    		abaEndereco.gridDados.selectedIndex = -1;
    		abaTelefone.gridDados.selectedIndex = -1;
    		abaEmail.gridDados.selectedIndex = -1;
    		this.dispatchEvent(new Event(CLICK_ABAS));	
        }
		
		public function obterGrid():Grid {
			var grid:Grid = null;
			
			if(isAbaEnderecoSelecionada()) {
				grid = abaEndereco.obterGrid()				
			} else if(isAbaTelefoneSelecionada()) {
				grid = abaTelefone.obterGrid()				
			} else if(isAbaEmailSelecionada()) {
				grid = abaEmail.obterGrid()				
			}
			
			return grid;
		}
		
		private function retornoListarContato(event: ResultEvent): void {
			abaEndereco.carregarEnderecos(event.result.dados["listaEnd"]);
       		abaTelefone.carregarTelefones(event.result.dados["listaTelefone"]);
       		abaEmail.carregarEmails(event.result.dados["listaEmail"]);
		}
		
	    //--------------------------------------------------------------------------
	    //  Métodos auxiliares
	    //--------------------------------------------------------------------------
		public function listar(abaExibicao:int):void {
			MostraCursor.setBusyCursor("Carregando Registros ...", Application.application, MostraCursor.CURSOR_PESQUISAR);
			var pessoa:PessoaCompartilhamentoVO = TelaPlataformaAtendimentoCAPESBase.getPessoaSelecionada();
			
			listarContatos(pessoa);
			
			selecionarAba(abaExibicao);
			navegacaoTab.validateNow();
		}
					
		private function listarContatos(pessoa: PessoaCompartilhamentoVO): void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.pessoa = pessoa;
			servicoListaContatos.getOperation(OPERACAO_OBTER_DADOS_SELECAO).send(dto);			
		}
		
		public function selecionarAba(abaExibicao:int) : void {
			navegacaoTab.selectedIndex = abaExibicao;
		}		
				
		public function isAbaEnderecoSelecionada() : Boolean {
			return navegacaoTab.selectedIndex == ABA_ENDERECO;
		}

		public function isAbaEmailSelecionada() : Boolean {
			return navegacaoTab.selectedIndex == ABA_EMAIL;
		}
		
		public function isAbaTelefoneSelecionada() : Boolean {
			return navegacaoTab.selectedIndex == ABA_TELEFONE;
		}
		
	    //--------------------------------------------------------------------------
	    //  Listeners
	    //--------------------------------------------------------------------------
		private function registroSelecionado(evt:Event):void {
			this.dispatchEvent(evt);	
		}	
		
		private function duploClique(evt:Event):void {
			this.dispatchEvent(evt);	
		}

		public function tornarCorrespondenciaPadrao(evt:Event = null) :void {
			MostraCursor.setBusyCursor("Gravando Registro!", Application.application, MostraCursor.CURSOR_PROGRESSO);
			
			var endereco: EnderecoVO = new EnderecoVO();
			endereco.idEndereco = obterGrid().selectedItem.idEndereco;

			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.endereco = endereco;
			
			servicoEdicaoEndereco.getOperation(OPERACAO_TORNAR_CORRESPONDENCIA).send(dto);
		}	
		
		private function retornoCorrespondenciaEndereco(event: ResultEvent): void {
			MostraCursor.removeBusyCursor();
			this.dispatchEvent(new Event(Modulo.REGISTRO_GRAVADO));
		}
		
		public function obterLista():void {}
	}
}