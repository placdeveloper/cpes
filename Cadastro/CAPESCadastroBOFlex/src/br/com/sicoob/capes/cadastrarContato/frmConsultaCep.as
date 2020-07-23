package br.com.sicoob.capes.cadastrarContato
{
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.StringUtils;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.UFVO;
	
	import flash.events.Event;
	import flash.events.KeyboardEvent;
	import flash.events.MouseEvent;
	import flash.ui.Keyboard;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;

	public class frmConsultaCep extends frmConsultaCepView {
		
		public static const CLASSE_SERVICO: String = "br.com.sicoob.capes.cadastro.fachada.EnderecoFachada";
		
		private static const SERVICO_JAVA: String = "servicosJavaCapes";
		
		private static const OPERACAO_PESQUISAR: String = "pesquisarEndereco";
		
		private var lista:ArrayCollection = new ArrayCollection();
		
		private var destino:DestinoVO;
		
		private var registro:Object;
		
		private var telaRetorno: frmEditarEndereco;
		
		private var cepTelaAnterior: String;
		
		public var servico: ServicoJava;
		
		public function frmConsultaCep(telaRetorno:frmEditarEndereco, cep:String=null, lista:ArrayCollection=null) {
			super();
			this.telaRetorno = telaRetorno;
			servico = ServicoJavaUtil.getServico(CLASSE_SERVICO);
			servico.addEventListener(ResultEvent.RESULT, retornoPesquisar);
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
			this.cepTelaAnterior = cep;
			this.lista.list = lista;
		}
		
		private function init(evt:FlexEvent=null):void {
			inicializaLista();
			
			btnPesquisar.addEventListener(MouseEvent.CLICK, btnPesquisaClicado);
			btnLimpar.addEventListener(MouseEvent.CLICK, btnLimparClicado);
			
			griConsulta.addEventListener(ListEvent.ITEM_CLICK, registroSelecionado);
			griConsulta.addEventListener(ListEvent.ITEM_DOUBLE_CLICK, preencherCamposComEndereco);
			griConsulta.doubleClickEnabled = true;
			
			numCepLocalidade.addEventListener(KeyboardEvent.KEY_DOWN, btnEntPrecionado);
			cboUF.addEventListener(KeyboardEvent.KEY_DOWN, btnEntPrecionado);
			municipio.addEventListener(KeyboardEvent.KEY_DOWN, btnEntPrecionado);
			bairro.addEventListener(KeyboardEvent.KEY_DOWN, btnEntPrecionado);
			logradouro.addEventListener(KeyboardEvent.KEY_DOWN, btnEntPrecionado);
			
			inicializarServicos();
			
			if (cepTelaAnterior != "") {
				numCepLocalidade.text = "";
				numCepLocalidade.text = cepTelaAnterior;
				//consultar();
			}
		}	 
		
		public function btnEntPrecionado(evt:KeyboardEvent=null):void {
			switch(evt.keyCode){
				case Keyboard.ENTER:
					consultar();	
					break;
			}
		}
		
		public function registroSelecionado(evt:ListEvent=null):void {
			registro = evt.target.selectedItem;
		}
		
		protected function preencherCamposComEndereco(event:ListEvent=null):void {
			telaRetorno.preencherCamposEnderecoConsultado(griConsulta.selectedItem);
			telaRetorno.janelaConsultaEndereco.fecharJanela();
		}
		
		protected function btnPesquisaClicado(event:MouseEvent=null):void {
			consultar();
		}
		
		private function consultar():void {
			MostraCursor.setBusyCursor("Carregando Registros!", Application.application, MostraCursor.CURSOR_PESQUISAR);
			
			// Monta o DTO, com o VO, para retorno
			var dto: RequisicaoReqDTO = new RequisicaoReqDTO();
			
			dto.dados.nomeBairro = bairro.text;
			dto.dados.nomeLocalidade = municipio.text;
			dto.dados.nomeLogradouro = logradouro.text;
			dto.dados.numCep = StringUtils.trim(numCepLocalidade.text);
			var uf:UFVO = cboUF.selectedItem as UFVO;

			if(uf != null) {
				dto.dados.siglaUF = uf.siglaUF;
			}
			
			servico.getOperation(OPERACAO_PESQUISAR).send(dto);
		}
		
		protected function btnLimparClicado(event:MouseEvent=null):void {
			numCepLocalidade.text = "";
			cboUF.selectedIndex = 0;
			municipio.text = "";
			bairro.text = "";
			logradouro.text = "";
		}
		
		private function inicializaLista():void {		
			this.griConsulta.dataProvider = lista;
		}
		
		private function retornoPesquisar(event: ResultEvent): void {
			this.lista.list = event.result.dados.lista;
			this.dispatchEvent(new Event(Modulo.LISTA_CARREGADA));	
			MostraCursor.removeBusyCursor();
		}
		
		//--------------------------------------------------------------------------
		//  Configuração de destino dos serviços.
		//--------------------------------------------------------------------------
		private function inicializarServicos():void {
			if(this.destino == null || destino.tipo == DestinoVO.CANAL_NET) {
				PortalModel.portal.obterDefinicoesDestino(SERVICO_JAVA, onDestinoRecuperado);
			} else {
				configurarDestinos();
			}
		}
		
		private function onDestinoRecuperado(destino:DestinoVO):void {
			this.destino = destino;
			configurarDestinos();
		}
		
		private function configurarDestinos():void{
			servico.configurarDestino(destino);
		}
	}
}