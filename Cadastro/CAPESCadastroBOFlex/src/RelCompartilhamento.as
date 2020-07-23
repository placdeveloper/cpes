package
{
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.relatorioCompartilhamento.RelCompartilhamentoView;
	
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;

	public class RelCompartilhamento extends RelCompartilhamentoView
	{
		private static const CLASSE_SERVICO_RELATORIO : String = "br.com.sicoob.capes.relatorio.negocio.fachada.RelCompartilhamentoFachada";
		private static const DESTINO_CAPES:String = "destinoCAPES";
		
		private var listaCoop:ArrayCollection = new ArrayCollection();
		
		public function RelCompartilhamento()
		{
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE,init);
		}
		
		/*Método de Definição dos Listeners*/
		private function init(event:FlexEvent):void{
			btOk.addEventListener(MouseEvent.CLICK, okEmitirRelatorio);
			btCancelar.addEventListener(MouseEvent.CLICK, cancelar);
			btFechar.addEventListener(MouseEvent.CLICK, fecharTela);
			
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], destino_recuperado);
		}
		
		private function destino_recuperado(destino:DestinoVO):void {
			componenteCentraisSingulares.configurarDestino(destino);
			this.destino = destino;
			obterDefinicoes();
		}
		
		/*Método que define algumas caracteristicas default da tela e chama serviço que irá retornar o GRAU DA COOPERATIVA LOGADA*/
		private function obterDefinicoes():void{	
			rdSimGrupo.selected = true;
			dataInicio.selectedDate = null;
			dataFim.selectedDate = null;
		}
		
		/*Método para validar que todos os campos necessários estão preenchidos*/
		private function validarCampos():Boolean{
			
			if (dataFim.selectedDate < dataInicio.selectedDate) {
				Alerta.show("A Data Fim não pode ser menor que a Data Início", "Atenção!");
				return false;
			}
			
			if (componenteCentraisSingulares.central == null || isNaN(componenteCentraisSingulares.central.numeroCooperativa)){
				Alerta.show("Preencher o campo 'Central'.", "Atenção!");
				return false;
			}
			else{
				return true;
			}
		}
		
		/*Chamada ao relatório quando usuário clica no botão OK*/
		private function okEmitirRelatorio(event:MouseEvent):void{
			if (validarCampos()){
				var dto:ParametroDTO = new ParametroDTO();
				dto.dados.numCentral = componenteCentraisSingulares.central != null ? componenteCentraisSingulares.central.numeroCooperativa : null;
				dto.dados.numSingular = componenteCentraisSingulares.singular != null ? componenteCentraisSingulares.singular.numeroCooperativa : null;
				dto.dados.compartilhamento = radioGrupoCompart.selectedValue;
				dto.dados.dataInicio = DateTimeBase.getDateTime(dataInicio.selectedDate);;
				dto.dados.dataFim = DateTimeBase.getDateTime(dataFim.selectedDate);
				
				RelatorioUtil.create().emitirRelatorio("capes/relatorio/RelatorioCompartilhamentoServicoRemote", dto, 
					"RelCompartilhamento", destino, "Emitindo formulário", "PDF", false);
			}
		}
		
		/*Retorna as informações da tela para default*/
		private function cancelar(event:MouseEvent):void{
			obterDefinicoes();
		}
		
		/*fecha a janela*/
		private function fecharTela(event:MouseEvent):void{
			fecharJanela();
		}
	}
}
