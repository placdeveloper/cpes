package {
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.constituicaoVinculadaEsferaAdm.ConstituicaoVinculadaEsferaAdmView;
	import br.com.sicoob.capes.constituicaoVinculadaEsferaAdm.cadastrarConstituicaoVinculadaEsferaAdm.ConstituicaoVinculadaEsferaAdmEdicao;
	
	import flash.display.DisplayObject;
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	
	public class ConstituicaoVinculadaEsferaAdm extends ConstituicaoVinculadaEsferaAdmView {
		
		private var servico:ServicoJava = new ServicoJava();
		private var formEdicao:ConstituicaoVinculadaEsferaAdmEdicao = new ConstituicaoVinculadaEsferaAdmEdicao();
		private var listaEsferasAdm:ArrayCollection = new ArrayCollection();
		private var _listaConstituicaoVinculada:ArrayCollection = new ArrayCollection();
		private var registroSelecionado:Object;
		private var requisicaoDto:RequisicaoReqDTO;
		private var janela:Janela = new Janela();
		
		private static const DESTINO_CAPES : String = "destinoCAPES";
		
		
		public function ConstituicaoVinculadaEsferaAdm() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE,onCreateComplete);
			include 'br/com/sicoob/capes/utils/RegistroClasses.as';
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoFormaConstituicao", TipoFormaConstituicaoVO);
			
		}
		
		
		private function onCreateComplete(event:FlexEvent): void {
			servico = ServicoJavaUtil.getServico("br.com.sicoob.capes.cadastro.fachada.ConstituicaoVinculadaEsferaAdmFachada");
			
			servico.consultarConstituicaoVinculada.addEventListener(ResultEvent.RESULT, consultarConstituicaoVinculadaOnResult);
			servico.obterDefinicoes.addEventListener(ResultEvent.RESULT, obterDefinicoesOnResult);
			listaEsferas.addEventListener(ListEvent.ITEM_CLICK,consultarConstituicaoVinculada);
			btnAlterarVinculos.addEventListener(MouseEvent.CLICK, btnAlterarVinculosClicado);
			
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], onDestinoCAPESRecuperado);
			
		}
		
		private function onDestinoCAPESRecuperado(destino:DestinoVO):void {
			servico.configurarDestino(destino);
			formEdicao.configurarDestino(destino);
			obterDefinicoes();
		}
		
		private function obterDefinicoes():void {
			var requisicaoDto:RequisicaoReqDTO = new RequisicaoReqDTO();			
			MostraCursor.setBusyCursor("Buscando Esfera", this, MostraCursor.CURSOR_PESQUISAR);
			servico.obterDefinicoes(requisicaoDto);
		}
		
		
		private function obterDefinicoesOnResult(event:ResultEvent):void {
			listaEsferasAdm = event.result.dados.esferasAdministrativas;
			var array:ArrayCollection = new ArrayCollection();
			array.source = listaEsferasAdm.source;
			
			listaEsferas.dataProvider = array;
			MostraCursor.removeBusyCursor();
			
		}
		
		public function consultarConstituicaoVinculada(event:ListEvent=null):void {
			if(event.target.selectedItem != null) {
				MostraCursor.setBusyCursor("Buscando Constituição vinculada",this,MostraCursor.CURSOR_PESQUISAR);
				registroSelecionado = event.target.selectedItem;
				requisicaoDto = new RequisicaoReqDTO();
				requisicaoDto.dados.codigo = registroSelecionado.codigo;
				servico.consultarConstituicaoVinculada(requisicaoDto);
			}
		}
		
		private function consultarConstituicaoVinculadaOnResult(event:ResultEvent):void {
			_listaConstituicaoVinculada = event.result.dados.listaConstituicaoVinculada;
			var array:ArrayCollection = new ArrayCollection();
			array.source = _listaConstituicaoVinculada.source;
			listaConstituicaoVinculada.dataProvider = array;
			MostraCursor.removeBusyCursor();
		}
		
		protected function btnAlterarVinculosClicado(event:MouseEvent):void {
			janela.title = "Alterar vinculo";
			janela.width = 980;
			janela.height = 554;
			janela.addChild(formEdicao as DisplayObject);
			formEdicao.limparCamposTela();
			janela.abrir(DisplayObject(Application.application), true);
			listaEsferas.selectedItem = null;
			listaConstituicaoVinculada.dataProvider = null;
		}
		
	}
}