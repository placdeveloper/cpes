package br.com.sicoob.capes.fichaCadastral.abas{
	
	import flash.events.MouseEvent;
	
	import mx.events.FlexEvent;
	import mx.formatters.DateFormatter;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
	import br.com.sicoob.capes.utils.ConversaoPessoaUtil;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCustomizadoCAPES;
	
	public class imprimeCadastroNova extends imprimeCadastroViewNova  {
		
		private var destinoVO:DestinoVO = null;
		
		/**
		 *  Construtor.
		 */
		public function imprimeCadastroNova(){
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(evt:FlexEvent):void{
			inicializarServicos();
			btImprimir.addEventListener(MouseEvent.CLICK, imprimir);
			rdAtual.selected = true;
			dtData.dataMinima = new Date("01/01/1960");
			dtData.dataMaxima = new Date();
			dtDataFim.dataMinima = new Date("01/01/1960");
			dtDataFim.dataMaxima = new Date();
		}
		
		private function imprimir(evt:MouseEvent):void {
			if(!dtData.validar(new Date(dtData.compMask.text))){
				Alerta.show("A data de inicio é inválida!", "Erro", Alerta.ALERTA_ERRO);
				return ;
			} else if(!dtDataFim.validar(new Date(dtDataFim.compMask.text))){
				Alerta.show("A data fim é inválida!", "Erro", Alerta.ALERTA_ERRO);
				return ;
			}
			
			if(dtData.compDate.selectedDate > new Date()){
				Alerta.show("Data inválida! Data máxima para a data início é: " + convertDataToString(new Date()) + ".", "Erro", Alerta.ALERTA_ERRO);
				dtData.setFocus();
				return;
			}
			
			if(dtDataFim.compDate.selectedDate > new Date()){
				Alerta.show("Data inválida! Data máxima para a data fim é: " + convertDataToString(new Date()) + ".", "Erro", Alerta.ALERTA_ERRO);
				dtDataFim.setFocus();
				return;
			}
			
			if(dtData.selectedDate && dtDataFim.selectedDate) {
				if(dtDataFim.selectedDate.getTime() < dtData.selectedDate.getTime()){
					Alerta.show("A data final não pode ser anterior à data inicial!", "Erro", Alerta.ALERTA_ERRO);
					dtData.setFocus();
					return;
				}
			}
			
			var pessoaCompartilhamento:PessoaCompartilhamentoVO = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(TelaPlataformaAtendimentoCustomizadoCAPES.getPessoaPlataforma());

			var dto:ParametroDTO = new ParametroDTO();
			dto.dados.idPessoaCompartilhamento = pessoaCompartilhamento.idPessoaCompartilhamento;
			dto.dados.idPessoa = pessoaCompartilhamento.pessoa.idPessoa;
			dto.dados.codigoTipoPessoa = pessoaCompartilhamento.pessoa.tipoPessoa.codTipoPessoa;
			dto.dados.atual = rdAtual.selected;
			dto.dados.historicoEm = rdPosicaoHistorico.selected;
			dto.dados.data = dtData.selectedDate;
			dto.dados.dataFim = dtDataFim.selectedDate;
			dto.dados.emBranco = rdEmBranco.selected;
			
			if(rdEmBranco.selected) {
				RelatorioUtil.create().emitirRelatorio("capes/relatorio/RelatorioFichaCadastralNovaEmBrancoServicoRemote", dto, "relatorioFichaCadastralNova", destinoVO, "Emitindo Ficha Cadastral...", "PDF", false);
			} else {
				RelatorioUtil.create().emitirRelatorio("capes/relatorio/RelatorioFichaCadastralNovaServicoRemote", dto, "relatorioFichaCadastralNova", destinoVO, "Emitindo Ficha Cadastral...", "PDF", false);
			}
		}
		
		private function convertDataToString(data: Date):String{
			var fmt:DateFormatter = new DateFormatter();
			fmt.formatString = "DD/MM/YYYY";
			return fmt.format(data);
		}
		
		public static function parse(stringValue:String, pattern:String = 'DD/MM/YYYY'):Date
		{
			var dateCommonFormatter : DateFormatter = new DateFormatter();
			dateCommonFormatter.formatString = pattern;
			
			var formattedStringValue : String = dateCommonFormatter.format(stringValue);                                
			return new Date(Date.parse(formattedStringValue));
		}
		
		private function inicializarServicos():void {
			PortalModel.portal.obterDefinicoesDestino("servicosJavaCapes", onDestinoRecuperado);
		}  		
		
		private function onDestinoRecuperado(destino:DestinoVO):void {
			destinoVO = destino;
		}
		
	}
}
