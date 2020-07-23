package br.com.sicoob.capes.alterarEnderecoBancoob
{
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.componentes.PlataformaAtendimento.IListaPlataformaAtendimento;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastrarContato.abas.abaListarEndereco;
	import br.com.sicoob.capes.utils.ConversaoPessoaUtil;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.EnderecoVO;
	import br.com.sicoob.capes.corporativo.componentes.plataformaatendimento.ProcuraClientePlataformaCAPES;
	
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	public class frmListarEnderecosBancoob extends abaListarEndereco implements IListaPlataformaAtendimento
	{
	
		private static const CLASSE_SERVICO_ENDERECO: String = 
				"br.com.sicoob.capes.cadastro.fachada.EnderecoFachada";
		public static const OPERACAO_OBTER_DADOS_SELECAO: String = "obterDadosSelecao";
		public static const OPERACAO_TORNAR_CORRESPONDENCIA: String = "tornarPadraoCorrespondencia";
		
		public var servicoListaEndereco:ServicoJava = ServicoJavaUtil.getServico(CLASSE_SERVICO_ENDERECO);
		public var servicoEdicaoEndereco:ServicoJava = ServicoJavaUtil.getServico(CLASSE_SERVICO_ENDERECO);

		private var listaEnderecos:ArrayCollection;
						
		public function frmListarEnderecosBancoob() {
			
			servicoListaEndereco.addEventListener(ResultEvent.RESULT, retornoListarEndereco);
			servicoEdicaoEndereco.addEventListener(ResultEvent.RESULT, retornoCorrespondenciaEndereco);
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		//--------------------------------------------------------------------------
	    //  Métodos
	    //--------------------------------------------------------------------------
		private function init(evt:FlexEvent=null):void {

			labelSelecione.visible = false;
			imagemSelecione.visible = false;
			
			listaEnderecos = new ArrayCollection();
			gridDados.dataProvider = listaEnderecos;
			
			obterLista();
		}
		
		public function configurarDestinosServicos(destinoVO:DestinoVO):void{
			servicoListaEndereco.configurarDestino(destinoVO);
			servicoEdicaoEndereco.configurarDestino(destinoVO);
		}
		
		public function obterLista():void{

			MostraCursor.setBusyCursor("Carregando Registros ...", 
					Application.application, MostraCursor.CURSOR_PESQUISAR);
					
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.pessoa = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(ProcuraClientePlataformaCAPES.getPessoaSelecionada());
			dto.dados.produtosBancoob = true;
			servicoListaEndereco.getOperation(OPERACAO_OBTER_DADOS_SELECAO).send(dto);	
		}		

		public function tornarCorrespondenciaPadrao(evt:Event = null) :void {
			
			MostraCursor.setBusyCursor("Gravando Registro!", Application.application, 
					MostraCursor.CURSOR_PROGRESSO);
			
			var endereco: EnderecoVO = new EnderecoVO();
			endereco.idEndereco = obterGrid().selectedItem.idEndereco;

			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.endereco = endereco;
			dto.dados.produtosBancoob = true;
			servicoEdicaoEndereco.getOperation(OPERACAO_TORNAR_CORRESPONDENCIA).send(dto);
		}	
		
		private function retornoCorrespondenciaEndereco(event: ResultEvent): void {
			MostraCursor.removeBusyCursor();
			this.dispatchEvent(new Event(Modulo.REGISTRO_GRAVADO));
		}	
		
		private function retornoListarEndereco(event: ResultEvent): void {
			this.carregarEnderecos(event.result.dados["lista"]);
			MostraCursor.removeBusyCursor();
		}		
	}
}