package br.com.sicoob.capes.relatorioDeclaracaoProposito{

	import br.com.bancoob.componentes.input.Check;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	import br.com.sicoob.capes.relatorioDeclaracaoProposito.dto.RelatorioDeclaracaoPropositoDTO;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCustomizadoCAPES;
	
	import flash.events.MouseEvent;
	
	import mx.events.FlexEvent;
	
	public class RelatorioDelacracaoProposito extends RelatorioDelacracaoPropositoView  {
		
		private var destinoVO:DestinoVO = null;
		
	    /**
	     * Construtor.
	     */
		public function RelatorioDelacracaoProposito(){
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(evt:FlexEvent):void {
			inicializarServicos();
			btImprimir.addEventListener(MouseEvent.CLICK, imprimir);
			btCancelar.addEventListener(MouseEvent.CLICK, cancelar);
			checkTodos.addEventListener(MouseEvent.CLICK, marcarTodos);
		}
		
		public function cancelar(event:MouseEvent):void {
			limparForm();
		}
		
		public function limparForm():void {
			
			for each (var checkbox1:Check in boxCampos.getChildren()){
			    checkbox1.selected = false;
			}
			
			for each (var checkbox2:Check in boxCampos.getChildren()){
			    checkbox2.enabled = true;
			}
		}
		
		private function marcarTodos(event:MouseEvent):void {
			if (checkTodos.selected) {
				
				//Actionscript não possui variavel com escopo de bloco
				for each (var checkbox1:Check in boxCampos.getChildren()){
				    checkbox1.selected = true;
				}
				
				for each (var checkbox2:Check in boxCampos.getChildren()){
				    checkbox2.enabled = false;
				}
				
				checkTodos.enabled = true;
			} else {
				
				for each (var checkbox3:Check in boxCampos.getChildren()){
				   checkbox3.selected = false;
				}
				
				for each (var checkbox4:Check in boxCampos.getChildren()){
				    checkbox4.enabled = true;
				}
			} 	
		}
		
		private function imprimir(evt:MouseEvent):void {	
			var relatorioDTO:RelatorioDeclaracaoPropositoDTO = new RelatorioDeclaracaoPropositoDTO();

			relatorioDTO.contaPoupanca = checkContaPoupanca.selected;
			relatorioDTO.contaCorrente = checkContaCorrente.selected;
			relatorioDTO.contaSalario = checkContaSalario.selected;
			relatorioDTO.chequeEspecial = checkChequeEspecial.selected;
			relatorioDTO.emprestimoFinanciamento = checkEmprestimos.selected;
			relatorioDTO.investimento = checkInvestimentos.selected;
			relatorioDTO.cartoes = checkCartoes.selected;
			relatorioDTO.seguros = checkSeguros.selected;
			relatorioDTO.consorcio = checkConsorcio.selected;
			relatorioDTO.previdenciaPrivada = checkPrevidenciaPrivada.selected;
			
			var dto:ParametroDTO = new ParametroDTO();
			var pessoa:PessoaPlataformaVO = TelaPlataformaAtendimentoCustomizadoCAPES.getPessoaPlataforma();
			
			dto.dados.idPessoa = pessoa.idPessoa; 
			dto.dados.idInstituicao = pessoa.idInstituicao;
			dto.dados.dto = relatorioDTO;
			
			RelatorioUtil.create().emitirRelatorio("capes/relatorio/RelatorioDeclaracaoPropositoServicoRemote", dto, 
				"RelatorioDeclaracaoProposito", destinoVO, "Emitindo relatório...", "PDF", false);
		}
		
		private function inicializarServicos():void {
			PortalModel.portal.obterDefinicoesDestino("servicosJavaCapes", onDestinoRecuperado);
		}  		
		
		private function onDestinoRecuperado(destino:DestinoVO):void {
			destinoVO = destino;
		}
	}
}