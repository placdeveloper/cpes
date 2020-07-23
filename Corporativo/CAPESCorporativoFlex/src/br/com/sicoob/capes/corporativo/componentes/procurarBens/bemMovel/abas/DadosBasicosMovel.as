package br.com.sicoob.capes.corporativo.componentes.procurarBens.bemMovel.abas{
	
	import br.com.bancoob.componentes.cadastro.FormularioCadastro;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.enums.TipoClassificacaoBemEnum;
	import br.com.sicoob.capes.comum.util.FlexUtil;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoClassificacaoBemVO;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.IAba;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.vo.BemMovelVO;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.vo.BemVO;
	
	import flash.events.Event;
	
	import mx.rpc.events.ResultEvent;
	
	/**
	 * Aba de dados básicos do bem móvel.
	 * 
	 * @author Bruno.Carneiro
	 */
	public class DadosBasicosMovel extends DadosBasicosMovelView implements IAba {
		
		/**
		 * @inheritDoc
		 */
		public function inicializar():void {
			checkValorBem.addEventListener(Event.CHANGE, ativarCampoValorBem);
		}
    	
		/**
		 * @inheritDoc
		 */
    	public function obterDefinicoes(evento:ResultEvent):void {
			comboTipoBem.dataProvider = evento.result.dados.listaTiposBem;
    	}
		
		/**
		 * @inheritDoc
		 */
		public function atualizarCamposRegistro(bem:BemVO):void {
			var bemMovelBasico:BemMovelVO = BemMovelVO(bem);
			
			bemMovelBasico.codigoTipoBem = comboTipoBem.selectedItem.codigo;
			bemMovelBasico.descricao = FlexUtil.stringVaziaParaNulo(campoDescricao.text);
			bemMovelBasico.valor = campoValorBem.valor;
			bemMovelBasico.valorNaoInformado = checkValorBem.selected;
			bemMovelBasico.mesRenovacaoSeguro = FlexUtil.campoNumericoZeroParaNulo(campoMesRenovacaoSeguro.valor);
			
			var tipoClassificacao:TipoClassificacaoBemVO = new TipoClassificacaoBemVO();
			tipoClassificacao.codigo = TipoClassificacaoBemEnum.BEM_MOVEL.codigo;
			bemMovelBasico.codigoTipoClassificacaoBem = tipoClassificacao.codigo;
    	}

		/**
		 * @inheritDoc
		 */
    	public function preencherCampos(bem:BemVO):void {
			var bemMovel:BemMovelVO = bem as BemMovelVO;
			
			comboTipoBem.enabled = true;
			
			if(bemMovel != null){
				FlexUtil.selecionarValorCombo(comboTipoBem, bemMovel.codigoTipoBem);
				campoDescricao.text = bemMovel.descricao;
				campoValorBem.valor = bemMovel.valor;
				checkValorBem.selected = bemMovel.valorNaoInformado;
				campoMesRenovacaoSeguro.valor = bemMovel.mesRenovacaoSeguro;
				
				campoValorBem.enabled = !checkValorBem.selected;
			}
			
			var modo:int = obterModo();
			if(modo == 2 || modo == 3) { // MODO EDIÇÃO ou VISUALIZAÇÃO
				comboTipoBem.enabled = false;
			}
    	}

		/**
		 * @inheritDoc
		 */
    	public function limpar():void {
			comboTipoBem.selectedIndex = 0;
			campoDescricao.text = "";
			checkValorBem.selected = false;
			campoValorBem.text = "";
			campoValorBem.enabled = true;
			campoMesRenovacaoSeguro.text = "";
    	}
		
		/**
		 * @inheritDoc
		 */
		public function abaTrocada():void {
			
		}
		
		/**
		 * @inheritDoc
		 */
		public function configurarDestino(destino:DestinoVO):void {
			
		}

		/**
		 * @inheritDoc
		 */
    	public function verificarAlteracoes(bem:BemVO = null):Boolean {
			if(bem == null) {
				if(comboTipoBem.selectedItem != null
					|| campoDescricao.text != ""
					|| campoValorBem.valor != 0
					|| campoMesRenovacaoSeguro.text != ""
					|| checkValorBem.selected) {
					return true;
				}
				return false;
			} else {
				var bemMovel:BemMovelVO = bem as BemMovelVO;
				
				if(bemMovel != null) {
					if(!FlexUtil.compararValoresNumericos(bemMovel.codigoTipoBem, comboTipoBem.selectedItem.codigo)
						&& bemMovel.descricao != FlexUtil.stringVaziaParaNulo(campoDescricao.text)
						&& !FlexUtil.compararValoresNumericos(bemMovel.valor, campoValorBem.valor)
						&& !FlexUtil.compararValoresNumericos(bemMovel.mesRenovacaoSeguro, campoMesRenovacaoSeguro.valor)) {
						return true;
					}
					return false;
				}
				return false;
			}
			return false;
    	}
		
		/**
		 *  @inheritDoc
		 */
		public function verificarPreenchimento():Boolean {
			return (comboTipoBem.selectedItem != null
				|| campoDescricao.text != ""
				|| campoValorBem.valor != 0
				|| campoMesRenovacaoSeguro.text != ""
				|| checkValorBem.selected);
		}
		
		/**
		 * Ativa/Desativa o campo de valor de acordo com o check
		 */
		private function ativarCampoValorBem(evento:Event):void {
			campoValorBem.text = "0";
			campoValorBem.enabled = !this.checkValorBem.selected;
			campoValorBem.validarObrigatorio = !this.checkValorBem.selected;
		}
		
		/**
		 * @inheritDoc
		 */
		public function bloquearCampoModoVisualizacao(bloquear:Boolean):void {
			
		}
		
		/**
		 * @inheritDoc
		 */
		public function marcarCamposObrigatorios():void {
			
		}
		
		/**
		 * Obtém o modo atual
		 */
		private function obterModo():int {
			return FormularioCadastro(this.parent.parent).modo;
		}
		
		/**
		 * @inheritDoc
		 */
		public function validar():Boolean {
			return true;
		}
	}
}