package br.com.sicoob.capes.constituicaoVinculadaEsferaAdm.cadastrarConstituicaoVinculadaEsferaAdm
{
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoFormaConstituicaoVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.collections.IList;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;

	public class ConstituicaoVinculadaEsferaAdmEdicao extends ConstituicaoVinculadaEsferaAdmEdicaoView {
		
		private var servico:ServicoJava = new ServicoJava();
		private var listaEsferasAdm:ArrayCollection = new ArrayCollection();
		private var requisicaoDto:RequisicaoReqDTO;
		
		private var _listaConstituicaoDisponivel:ArrayCollection = new ArrayCollection();
		private var _listaConstituicaoVinculadas:ArrayCollection = new ArrayCollection();
		
		private static const DESTINO_CAPES : String = "destinoCAPES";
		
		public function ConstituicaoVinculadaEsferaAdmEdicao() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE,onCreateComplete);
			
		}
		
		private function onCreateComplete(event:FlexEvent): void {
			servico = ServicoJavaUtil.getServico("br.com.sicoob.capes.cadastro.fachada.ConstituicaoVinculadaEsferaAdmFachada");
			servico.obterDefinicoes.addEventListener(ResultEvent.RESULT, obterDefinicoesOnResult);
			servico.consultarConstituicaoVinculadaAlteracao.addEventListener(ResultEvent.RESULT, consultarConstituicaoVinculadaAlteracaoOnResult);
			servico.salvarConstituicaoVinculadaEsferaAdm.addEventListener(ResultEvent.RESULT, salvarConstituicaoVinculadaEsferaAdmOnResult);
			
			btnPesquisar.addEventListener(MouseEvent.CLICK, consultarConstituicaoVinculadaAlteracao);
			btnLimpar.addEventListener(MouseEvent.CLICK, acaoBtnLimpar);
			btnCancelar.addEventListener(MouseEvent.CLICK, fechar);
			btnGravar.addEventListener(MouseEvent.CLICK, acaoBtnGravar);
			
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], onDestinoCAPESRecuperado);
			
			limparCamposTela();
		}
		
		protected function acaoBtnGravar(event:MouseEvent):void {
			var constituicoesSelecionados : ArrayCollection = this.listaConstituicao.recuperarItensSelecionados();
			
			MostraCursor.setBusyCursor("Gravando constituição", this, MostraCursor.CURSOR_PESQUISAR);
			requisicaoDto = new RequisicaoReqDTO();
			requisicaoDto.dados.constituicoesSelecionados = constituicoesSelecionados;
			requisicaoDto.dados.codigo = cmbEsferaAdm.selectedItem.codigo;
			servico.salvarConstituicaoVinculadaEsferaAdm(requisicaoDto);
		}
		
		private function salvarConstituicaoVinculadaEsferaAdmOnResult(event:ResultEvent):void {
			limparCamposTela();
			fecharJanela();
			MostraCursor.removeBusyCursor();
		}
		
		protected function fechar(event:MouseEvent=null):void {
			limparCamposTela();
			fecharJanela();
		}
		
		public function limparCamposTela():void {
			bloquearCampos(false);
			limparCampos();
		}
		
		protected function acaoBtnLimpar(event:MouseEvent):void {
			limparCamposTela();
		}
		
		private function limparCampos():void {
			listaConstituicao.limpar();
			cmbEsferaAdm.selectedItem = null;
		}
		
		private function bloquearCampos(bloquear:Boolean):void {
			btnGravar.enabled = bloquear;
			btnLimpar.enabled = bloquear;
			listaConstituicao.enabled = bloquear;
			btnPesquisar.enabled = !bloquear;
			cmbEsferaAdm.enabled = !bloquear;
		}
		
		protected function consultarConstituicaoVinculadaAlteracao(event:MouseEvent):void {
			if(cmbEsferaAdm.selectedItem != null) {
				MostraCursor.setBusyCursor("Buscando constituição", this, MostraCursor.CURSOR_PESQUISAR);
				requisicaoDto = new RequisicaoReqDTO();
				requisicaoDto.dados.codigo = cmbEsferaAdm.selectedItem.codigo;
				servico.consultarConstituicaoVinculadaAlteracao(requisicaoDto);
			}
		}
		
		private function consultarConstituicaoVinculadaAlteracaoOnResult(event:ResultEvent):void {
			_listaConstituicaoDisponivel = event.result.dados.listaTipoFormaConstituicoesDisponiveis;
			_listaConstituicaoVinculadas = event.result.dados.listaTipoFormaConstituicoesVinculadas;
			
			this.listaConstituicao.carregarListagem(event, false);
			bloquearCampos(true);
			MostraCursor.removeBusyCursor();
		}
		
		public function configurarDestino(destino:DestinoVO):void {
			this.servico.configurarDestino(destino);
		}
		
		private function onDestinoCAPESRecuperado(destino:DestinoVO):void {
			servico.configurarDestino(destino);
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
			
			cmbEsferaAdm.dataProvider = array;
			MostraCursor.removeBusyCursor();
			
		}
	}	
}