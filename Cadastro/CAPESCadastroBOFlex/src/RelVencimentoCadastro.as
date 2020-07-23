package
{
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.relatorioVencimentoCadastro.RelVencimentoCadastroView;
	
	import flash.events.MouseEvent;
	
	import mx.events.FlexEvent;

	public class RelVencimentoCadastro extends RelVencimentoCadastroView
	{
		
		private static const CLASSE_SERVICO_RELATORIO:String = "br.com.sicoob.capes.relatorio.negocio.fachada.RelVencimentoCadastroFachada";
		private static const DESTINO_CAPES:String = "destinoCAPES";
		
		public function RelVencimentoCadastro()
		{
			super();
			include 'br/com/sicoob/capes/utils/RegistroClasses.as';
			this.addEventListener(FlexEvent.CREATION_COMPLETE,init);
		}
		
		/*Método de Definição dos Listeners*/
		private function init(event:FlexEvent):void{
			btOk.addEventListener(MouseEvent.CLICK, okEmitirRelatorio);
			btCancelar.addEventListener(MouseEvent.CLICK, cancelar);
			btFechar.addEventListener(MouseEvent.CLICK, fecharTela);
			
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], destino_recuperado);
			dataInicio.selectedDate = null;
			dataFim.selectedDate = null;
		}
		
		private function destino_recuperado(destino:DestinoVO):void {
			this.destino = destino;
			componenteCentraisSingulares.configurarDestino(destino);
		}
		
		/*Método para validar que todos os campos necessários estão preenchidos*/
		private function validarCampos():Boolean{
			if (componenteCentraisSingulares.central == null || isNaN(componenteCentraisSingulares.central.numeroCooperativa)){
				Alerta.show("Preencher o campo 'Central'.", "Atenção!");
				return false;
			} else if (rdGrupoOrdem.selectedValue == null){
				Alerta.show("Preencher o tipo de ordenação.", "Atenção!");
				return false;
			}
			return true;
		}
		
		private function okEmitirRelatorio(event:MouseEvent):void {
			if (validarCampos()){
				var dto:ParametroDTO = new ParametroDTO();
				dto.dados.numCentral = componenteCentraisSingulares.central != null ? componenteCentraisSingulares.central.numeroCooperativa : null;
				if (!isNaN(componenteCentraisSingulares.singular.numeroCooperativa)){
					dto.dados.numSingular = componenteCentraisSingulares.singular.numeroCooperativa;
					dto.dados.idInstituicao = componenteCentraisSingulares.singular.idInstituicao;
				}
				else{
					dto.dados.numSingular = null;
				}
				
				dto.dados.unidade = componenteCentraisSingulares.unidade != null ? componenteCentraisSingulares.unidade.codigo : null;
				dto.dados.gerente = componenteCentraisSingulares.gerente != null ? componenteCentraisSingulares.gerente.idFuncionario : null;
				dto.dados.nomeGerente = componenteCentraisSingulares.gerente != null ? componenteCentraisSingulares.gerente.pessoa.pessoaCompartilhamento.nomePessoa : null;
				dto.dados.nucleo = componenteCentraisSingulares.nucleo != null ? componenteCentraisSingulares.nucleo.idNucleo : null;
				dto.dados.nomeNucleo = componenteCentraisSingulares.nucleo != null ? componenteCentraisSingulares.nucleo.descricao : null;
				
				dto.dados.dataInicio = dataInicio.selectedDate;
				dto.dados.dataFim = dataFim.selectedDate;
				dto.dados.ordenacao = rdGrupoOrdem.selectedValue;
				dto.dados.somenteResponsavel = checkSomenteResponsavel.selected;
				
				RelatorioUtil.create().emitirRelatorio("capes/relatorio/RelatorioVencimentoCadastroServicoRemote", dto, 
					"RelVencimentoCadastro", destino);
			}
		}
		
		private function cancelar(evento:MouseEvent = null):void {
			componenteCentraisSingulares.limpar();
			rdGrupoOrdem.selectedValue = "0";
			dataInicio.selectedDate = null;
			dataFim.selectedDate = null;
			checkSomenteResponsavel.selected = false;
			rdGrupoOrdem.selectedValue = "rdOrdemData";
		}
		
		private function fecharTela(evento:MouseEvent = null):void {
			pegaJanela().fecharJanela();
		}
	}
}