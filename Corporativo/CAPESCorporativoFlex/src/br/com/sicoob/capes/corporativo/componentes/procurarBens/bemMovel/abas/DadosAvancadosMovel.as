package br.com.sicoob.capes.corporativo.componentes.procurarBens.bemMovel.abas{
	
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.enums.TipoBemMovelEnum;
	import br.com.sicoob.capes.comum.util.FlexUtil;
	import br.com.sicoob.capes.comum.vo.entidades.UFVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoBemMovelVO;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.IAba;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.vo.BemMovelVO;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.vo.BemVO;
	
	import flash.events.Event;
	
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	
	/**
	 * Aba de dados avançados do bem móvel.
	 * 
	 * @author Bruno.Carneiro
	 */
	public class DadosAvancadosMovel extends DadosAvancadosMovelView implements IAba {
		
		/**
		 * @inheritDoc
		 */
		public function inicializar():void {
			obterAbaDadosBasicos().comboTipoBem.addEventListener(ListEvent.CHANGE, aoTrocarTipoBem);
		}
    	
		/**
		 * @inheritDoc
		 */
    	public function obterDefinicoes(evento:ResultEvent):void {
    		comboCor.dataProvider = evento.result.dados.listaTiposCores;
			comboTipoChassi.dataProvider = evento.result.dados.listaTiposChassi;
			comboUF.dataProvider = evento.result.dados.ufs;
    	}
		
		/**
		 * @inheritDoc
		 */
		public function atualizarCamposRegistro(bem:BemVO):void {
			var bemMovelAvancado:BemMovelVO = BemMovelVO(bem);
			
			bemMovelAvancado.anoConstrucaoAeronave = FlexUtil.campoNumericoZeroParaNulo(campoAnoConstrucaoAeronave.valor);
			bemMovelAvancado.anoConstrucaoEmbarcacao = FlexUtil.campoNumericoZeroParaNulo(campoAnoConstrucaoEmbarcacao.valor);
			bemMovelAvancado.anoFabricacaoAutomovel = FlexUtil.campoNumericoZeroParaNulo(campoAnoFabricacaoVeiculo.valor);
			bemMovelAvancado.anoModeloAutomovel = FlexUtil.campoNumericoZeroParaNulo(campoanoModeloVeiculo.valor);
			bemMovelAvancado.inscricaoEmbarcacao = FlexUtil.stringVaziaParaNulo(campoInscricaoEmbarcacao.text);
			bemMovelAvancado.matriculaAeronave = FlexUtil.stringVaziaParaNulo(campoMatriculaAeronave.text);
			bemMovelAvancado.numeroChassi = FlexUtil.stringVaziaParaNulo(campoNumeroChassi.text);
			bemMovelAvancado.placa = FlexUtil.stringVaziaParaNulo(campoPlaca.text);
			bemMovelAvancado.renavam = FlexUtil.stringVaziaParaNulo(campoRenavam.text);
			bemMovelAvancado.codigoTipoChassi = comboTipoChassi.selectedItem.codigo;
			bemMovelAvancado.codigoTipoCorAutomovel = comboCor.selectedItem.codigo;
			bemMovelAvancado.uf = comboUF.selectedItem != null ?(comboUF.selectedItem as UFVO).siglaUF : null;
    	}

		/**
		 * @inheritDoc
		 */
    	public function preencherCampos(bem:BemVO):void {
			var bemMovel:BemMovelVO = bem as BemMovelVO;
			
			limpar();
			
			if(bemMovel != null) {
				campoAnoConstrucaoAeronave.valor = bemMovel.anoConstrucaoAeronave;
				campoAnoConstrucaoEmbarcacao.valor = bemMovel.anoConstrucaoEmbarcacao;
				campoAnoFabricacaoVeiculo.valor = bemMovel.anoFabricacaoAutomovel;
				campoanoModeloVeiculo.valor = bemMovel.anoModeloAutomovel;
				campoInscricaoEmbarcacao.text = bemMovel.inscricaoEmbarcacao;
				campoMatriculaAeronave.text = bemMovel.matriculaAeronave;
				campoNumeroChassi.text = bemMovel.numeroChassi;
				campoPlaca.text = bemMovel.placa;
				campoRenavam.text = bemMovel.renavam;
				
				FlexUtil.selecionarValorCombo(comboTipoChassi, bemMovel.codigoTipoChassi);
				FlexUtil.selecionarValorCombo(comboCor, bemMovel.codigoTipoCorAutomovel);
				FlexUtil.selecionarValorCombo(comboUF, bemMovel.uf, "siglaUF"); 
			}
    	}
		
		/**
		 * @inheritDoc
		 */
    	public function limpar():void {
			comboTipoChassi.selectedIndex = 0;
			campoNumeroChassi.text = "";
			campoRenavam.text = "";
			campoAnoFabricacaoVeiculo.text = "";
			campoanoModeloVeiculo.text = "";
			campoPlaca.text = "";
			comboUF.selectedIndex = 0;
			comboCor.selectedIndex = 0;
			campoInscricaoEmbarcacao.text = "";
			campoAnoConstrucaoEmbarcacao.text = "";
			campoMatriculaAeronave.text = "";
			campoAnoConstrucaoAeronave.text = "";
    	}
		
		/**
		 * @inheritDoc
		 */
		public function abaTrocada():void {
			marcarCamposObrigatorios();
			var tipoBem:TipoBemMovelVO = obterAbaDadosBasicos().comboTipoBem.selectedItem as TipoBemMovelVO;
			
			if(tipoBem != null){
				var tipoBemMovelEnum:TipoBemMovelEnum = TipoBemMovelEnum.obterPorCodigo(tipoBem.codigo);
				rotuloTitulo.texto = "Avançado - " + tipoBemMovelEnum.descricao;
				
				desativarCanvas();
				
				if(TipoBemMovelEnum.VEICULO == tipoBemMovelEnum){
					canvasAvancadoVeiculo.visible = canvasAvancadoVeiculo.includeInLayout = true;
				}else if(TipoBemMovelEnum.AERONAVE == tipoBemMovelEnum){
					canvasAvancadoAeronave.visible = canvasAvancadoAeronave.includeInLayout = true;
				}else if(TipoBemMovelEnum.EMBARCACAO == tipoBemMovelEnum){
					canvasAvancadoEmbarcacao.visible = canvasAvancadoEmbarcacao.includeInLayout = true;
				}
			}
		}
		
		/**
		 * @inheritDoc
		 */
		public function configurarDestino(destino:DestinoVO):void {
		}

		/**
		 * {@inheritDoc}
		 */
    	public function verificarAlteracoes(bem:BemVO = null):Boolean {
			if(bem == null) {
				if(comboTipoChassi.selectedItem != null
					|| campoNumeroChassi.text != ""
					|| campoRenavam.text != ""
					|| campoAnoFabricacaoVeiculo.valor != 0
					|| campoanoModeloVeiculo.valor != 0
					|| campoPlaca.text != ""
					|| comboUF.selectedItem != null
					|| comboCor.selectedItem != null
					|| campoInscricaoEmbarcacao.text != ""
					|| campoAnoConstrucaoEmbarcacao.valor != 0
					|| campoMatriculaAeronave.text != ""
					|| campoAnoConstrucaoAeronave.valor != 0) {
					return true;
				}
				return false;
			} else {
				var bemAvancado:BemMovelVO = BemMovelVO(bem);
				
				if(bemAvancado != null) {
					var tipoBem:TipoBemMovelVO = obterAbaDadosBasicos().comboTipoBem.selectedItem as TipoBemMovelVO;
					var tipoBemMovelEnum:TipoBemMovelEnum = TipoBemMovelEnum.obterPorCodigo(tipoBem.codigo);
					
					if(TipoBemMovelEnum.VEICULO == tipoBemMovelEnum){
						if(bemAvancado.uf == (comboUF.selectedItem != null ? (comboUF.selectedItem as UFVO).siglaUF : null)
							&& FlexUtil.compararValoresNumericos(bemAvancado.codigoTipoCorAutomovel, comboCor.selectedItem.codigo)
							
							&& bemAvancado.numeroChassi == FlexUtil.stringVaziaParaNulo(campoNumeroChassi.text)
							&& bemAvancado.renavam == FlexUtil.stringVaziaParaNulo(campoRenavam.text)
							
							&& FlexUtil.compararValoresNumericos(bemAvancado.anoFabricacaoAutomovel, campoAnoFabricacaoVeiculo.valor)
							&& FlexUtil.compararValoresNumericos(bemAvancado.anoModeloAutomovel, campoanoModeloVeiculo.valor)
							&& bemAvancado.placa == FlexUtil.stringVaziaParaNulo(campoPlaca.text)) {
							
							return false;
						}
					}else if(TipoBemMovelEnum.AERONAVE == tipoBemMovelEnum){
						if(bemAvancado.matriculaAeronave == FlexUtil.stringVaziaParaNulo(campoMatriculaAeronave.text)
							&& FlexUtil.compararValoresNumericos(bemAvancado.anoConstrucaoAeronave, campoAnoConstrucaoAeronave.valor)) {
							return false;
						}
					}else if(TipoBemMovelEnum.EMBARCACAO == tipoBemMovelEnum){
						if(bemAvancado.inscricaoEmbarcacao == FlexUtil.stringVaziaParaNulo(campoInscricaoEmbarcacao.text)
							&& FlexUtil.compararValoresNumericos(bemAvancado.anoConstrucaoEmbarcacao, campoAnoConstrucaoEmbarcacao.valor)) {
							return false;
						}
					}
					return true;
				}
				return false;
			}
			return false;
    	}
		
		/**
		 *  @inheritDoc
		 */
		public function verificarPreenchimento():Boolean {
			return (comboTipoChassi.selectedItem != null
				|| campoNumeroChassi.text != ""
				|| campoRenavam.text != ""
				|| campoAnoFabricacaoVeiculo.valor != 0
				|| campoanoModeloVeiculo.valor != 0
				|| campoPlaca.text != ""
				|| comboUF.selectedItem != null
				|| comboCor.selectedItem != null
				|| campoInscricaoEmbarcacao.text != ""
				|| campoAnoConstrucaoEmbarcacao.valor != 0
				|| campoMatriculaAeronave.text != ""
				|| campoAnoConstrucaoAeronave.valor != 0);
		}
		
		/**
		 * Desativa os canvas ao trocar o tipo de bem móvel.
		 */
		private function desativarCanvas():void {
			canvasAvancadoVeiculo.visible = canvasAvancadoVeiculo.includeInLayout = false;
			canvasAvancadoAeronave.visible = canvasAvancadoAeronave.includeInLayout = false;
			canvasAvancadoEmbarcacao.visible = canvasAvancadoEmbarcacao.includeInLayout = false;
		}
		
		/**
		 * Método chamado ao trocar o bem.
		 */
		private function aoTrocarTipoBem(evento:Event):void {
			limpar();
		}
		
		/**
		 * Obtém a aba de dados básicos.
		 */
		private function obterAbaDadosBasicos():DadosBasicosMovel {
			return this.parent.getChildByName("abaDadosBasicos") as DadosBasicosMovel;
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
			desmarcarTodosCampos();
			if(verificarAlteracoes()) {
				var tipoBem:TipoBemMovelVO = obterAbaDadosBasicos().comboTipoBem.selectedItem as TipoBemMovelVO;
				var tipoBemMovelEnum:TipoBemMovelEnum = TipoBemMovelEnum.obterPorCodigo(tipoBem.codigo);
				
				if(TipoBemMovelEnum.VEICULO == tipoBemMovelEnum){
					comboTipoChassi.validarObrigatorio = true;
					campoNumeroChassi.validarObrigatorio = true;
					campoAnoFabricacaoVeiculo.validarObrigatorio = true;
					campoanoModeloVeiculo.validarObrigatorio = true;
					//campoRenavam.validarObrigatorio = true;
					//campoPlaca.validarObrigatorio = true;
					//comboUF.validarObrigatorio = true;
					//comboCor.validarObrigatorio = true;
				}else if(TipoBemMovelEnum.AERONAVE == tipoBemMovelEnum){
					campoMatriculaAeronave.validarObrigatorio = true;
					campoAnoConstrucaoAeronave.validarObrigatorio = true;
				}else if(TipoBemMovelEnum.EMBARCACAO == tipoBemMovelEnum){
					campoInscricaoEmbarcacao.validarObrigatorio = true;
					campoAnoConstrucaoEmbarcacao.validarObrigatorio = true;
				}
			}
		}
		
		/**
		 * Desmarca a obrigatoriedade de todos os campos.
		 */
		private function desmarcarTodosCampos():void {
			comboTipoChassi.validarObrigatorio = false;
			campoNumeroChassi.validarObrigatorio = false;
			campoRenavam.validarObrigatorio = false;
			campoAnoFabricacaoVeiculo.validarObrigatorio = false;
			campoanoModeloVeiculo.validarObrigatorio = false;
			campoPlaca.validarObrigatorio = false;
			comboUF.validarObrigatorio = false;
			comboCor.validarObrigatorio = false;
			campoInscricaoEmbarcacao.validarObrigatorio = false;
			campoAnoConstrucaoEmbarcacao.validarObrigatorio = false;
			campoMatriculaAeronave.validarObrigatorio = false;
			campoAnoConstrucaoAeronave.validarObrigatorio = false;
		}
		
		/**
		 * @inheritDoc
		 */
		public function validar():Boolean {
			return true;
		}
	}
}