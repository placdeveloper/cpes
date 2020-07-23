package
{
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.vo.InstituicaoVO;
	import br.com.sicoob.capes.relatorioCadastroCompartilhado.RelatorioCadastroCompartilhadoView;
	
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;

	public class RelatorioCadastroCompartilhado extends RelatorioCadastroCompartilhadoView {
		
		private static const DESTINO_CAPES:String = "destinoCAPES";
		private var listaCoop	: ArrayCollection = new ArrayCollection();
		private var servico		: ServicoJava = new ServicoJava();
		private var _bolHabilitaConsulta:Boolean = false;
		private var _valorAtual:String = "";
		private var _instituicao:InstituicaoVO;
		
		public function RelatorioCadastroCompartilhado(){
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE,init);
		}
		
		/*Método de Definição dos Listeners*/
		private function init(event:FlexEvent):void {
			btOk.addEventListener(MouseEvent.CLICK, okEmitirRelatorio);
			btCancelar.addEventListener(MouseEvent.CLICK, cancelar);
			btFechar.addEventListener(MouseEvent.CLICK, fecharTela);

			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], destino_recuperado);
		}
		
		private function destino_recuperado(destino:DestinoVO):void {
			this.destino = destino;
			servico.configurarDestino(destino);
			componenteCentraisSingulares.configurarDestino(destino);
			campoCpfCnpj.configurarDestino(destino);
			limparCampos();
		}
		
		private function limparCampos():void{	
			campoCpfCnpj.limpar();
		}

		/*Chamada ao relatório quando usuário clica no botão OK*/
		private function okEmitirRelatorio(event:MouseEvent):void{
			var dto:ParametroDTO = new ParametroDTO();
			dto.dados.numCentral = componenteCentraisSingulares.central != null ? componenteCentraisSingulares.central.numeroCooperativa : null;
			dto.dados.numSingular = componenteCentraisSingulares.singular != null ? componenteCentraisSingulares.singular.numeroCooperativa : null;
			dto.dados.idInstituicao = componenteCentraisSingulares.singular != null ? componenteCentraisSingulares.singular.idInstituicao : null;
			dto.dados.cpfCnpj = campoCpfCnpj.txtCodigo != null ? campoCpfCnpj.txtCodigo.text : null;
				
			RelatorioUtil.create().emitirRelatorio("capes/relatorio/RelatorioCadastroCompartilhadoServicoRemote", dto, 
				"RelatorioCadastroCompartilhado", destino);
		}
		
		/*Retorna as informações da tela para default*/
		private function cancelar(event:MouseEvent):void{
			limparCampos();
		}
		
		/*fecha a janela*/
		private function fecharTela(event:MouseEvent):void{
			fecharJanela();
		}
	}
}
