package br.com.sicoob.capes.alterarCpfCnpj
{
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaVO;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	public class PainelPesquisaPessoaCpfCnpj extends PainelPesquisaPessoaCpfCnpjView {
		
		static private const OPERACAO_OBTER_DADOS: String = "obterDados";
		public var servicoPesquisa:ServicoJava;

		private var listaVinculos:ArrayCollection = new ArrayCollection();
		private var pessoa: PessoaVO; 
		
		//**************
		// Construtores:
		//**************
		
		public function PainelPesquisaPessoaCpfCnpj() {
			servicoPesquisa = ServicoJavaUtil.getServico(AlteracaoDocumentoPessoa.CLASSE_SERVICO);
			servicoPesquisa.addEventListener(ResultEvent.RESULT, retornoConsultar);
			servicoPesquisa.addEventListener(FaultEvent.FAULT, retornoConsultarErro);
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}

		//**************
		// Eventos:
		//**************				
		protected function init(event: FlexEvent): void {
			gridVinculos.dataProvider = listaVinculos;
			
			btConsultar.addEventListener(MouseEvent.CLICK, consultarClicado);
			btCancelar.addEventListener(MouseEvent.CLICK, cancelarClicado);
		}
		
		private function consultarClicado(evt:Event = null): void{
			executarSeValido(consultar);
		}			

		public function cancelarClicado(evt:Event):void {
			limpar();	
			this.dispatchEvent(new Event(AlteracaoDocumentoPessoa.LIMPAR_TELA));		
		}
		
		private function consultar():void {
			
			MostraCursor.setBusyCursor("Carregando Registros!", 
					Application.application, MostraCursor.CURSOR_PESQUISAR);
			
			var dto: RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.cpfCnpjAtual = cpfCnpjAtual.text;
            servicoPesquisa.getOperation(OPERACAO_OBTER_DADOS).send(dto);			
		}		
		
		public function retornoConsultar(evt:ResultEvent):void{
			carregarRetornoConsultar(evt.result.dados["lista"], evt.result.dados["pessoa"]);
		}		
		
		public function retornoConsultarErro(evt:FaultEvent):void{
			carregarRetornoConsultar(new ArrayCollection(), null);
		}

		private function carregarRetornoConsultar(vinculos: ArrayCollection, pessoa:PessoaVO):void {
			this.listaVinculos.list = vinculos;
			this.pessoa = pessoa;
			this.gridVinculos.selectedIndex = -1;
			this.gridVinculos.selectable = false;					
			MostraCursor.removeBusyCursor();
			
			this.dispatchEvent(new Event(Modulo.REGISTRO_CARREGADO));		
		}
			
		public function limpar():void {
			cpfCnpjAtual.text = "";
			listaVinculos.list = new ArrayCollection();
			pessoa = null;
		}
		
		public function obterPessoa(): PessoaVO {
			return this.pessoa;
		}
		
		public function configurarDestinos(destino:DestinoVO):void {
			servicoPesquisa.configurarDestino(destino);
		}
	}
}