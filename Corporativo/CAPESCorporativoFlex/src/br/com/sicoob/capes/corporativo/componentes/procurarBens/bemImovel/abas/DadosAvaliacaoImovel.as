package br.com.sicoob.capes.corporativo.componentes.procurarBens.bemImovel.abas{
	
	import flash.events.Event;
	import flash.events.FocusEvent;
	
	import mx.collections.ArrayCollection;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.cadastro.FormularioCadastro;
	import br.com.bancoob.componentes.input.Combo;
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.enums.TipoLocalizacaoBemImovelEnum;
	import br.com.sicoob.capes.comum.util.FlexUtil;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoBemImovelVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoImplantacaoBemImovelVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoLocalizacaoBemVO;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.IAba;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.vo.BemImovelVO;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.vo.BemVO;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	
	/**
	 * Aba de dados avaliação do bem imóvel.
	 * 
	 * @author Bruno.Carneiro
	 */
	public class DadosAvaliacaoImovel extends DadosAvaliacaoImovelView implements IAba {
		
		/**
		 * @inheritDoc
		 */
		public function inicializar():void {
			obterAbaDadosBasicos().comboTipoBem.addEventListener(ListEvent.CHANGE, aoTrocarTipoBem);
			comboTipoImplantacao.addEventListener(ListEvent.CHANGE, aoTrocarTipoImplantacao);
			obterAbaDadosBasicos().campoAreaTotal.addEventListener(FocusEvent.FOCUS_OUT, aoTrocarAreaTotal);
		}
    	
		/**
		 * @inheritDoc
		 */
    	public function obterDefinicoes(evento:ResultEvent):void {
			comboTipoImplantacao.dataProvider = evento.result.dados.listaTiposImplantacao;
			comboEstadoConservacao.dataProvider = evento.result.dados.listaTiposEstadoConservacao;
			comboPadraoAcabamento.dataProvider = evento.result.dados.listaTiposPadraoAcabamento;
			comboServicosCondominiais.dataProvider = evento.result.dados.listaTiposServicosCondominiais;
			componenteSelecaoTipoOnus.adicionarListaRegistrosDisponiveis(evento.result.dados.listaTiposOnus);
    	}
		
		/**
		 * @inheritDoc
		 */
		public function atualizarCamposRegistro(bem:BemVO):void {
			if(bem is BemImovelVO){
				var bemImovelAvaliacao:BemImovelVO = BemImovelVO(bem);
				if(isImovelUrbano()) {
					var bemImovelAvaliacaoUrbano:BemImovelVO = BemImovelVO(bemImovelAvaliacao);
					
					bemImovelAvaliacaoUrbano.codigoTipoImplantacaoBemImovel = comboTipoImplantacao.selectedItem.codigo;
					bemImovelAvaliacaoUrbano.codigoTipoEstadoConservacao = comboEstadoConservacao.selectedItem.codigo;
					bemImovelAvaliacaoUrbano.codigoTipoPadraoAcabamentoBemImovel = comboPadraoAcabamento.selectedItem.codigo;
					bemImovelAvaliacaoUrbano.codigoTipoServicoCondominialBemImovel = comboServicosCondominiais.selectedItem.codigo;
					bemImovelAvaliacaoUrbano.areaPrivativa = FlexUtil.campoNumericoZeroParaNulo(campoAreaPrivativa.valor);
					bemImovelAvaliacaoUrbano.quantidadeDormitorios = FlexUtil.campoNumericoZeroParaNulo(campoQuantidadeDormitorios.valor);
					bemImovelAvaliacaoUrbano.quantidadeVagasGaragem = FlexUtil.campoNumericoZeroParaNulo(campoQuantidadeVagasGaragem.valor);
					bemImovelAvaliacaoUrbano.areaTerreno = FlexUtil.campoNumericoZeroParaNulo(campoAreaTerreno.valor);
					bemImovelAvaliacaoUrbano.areaTestada = FlexUtil.campoNumericoZeroParaNulo(campoAreaTestada.valor);
					bemImovelAvaliacaoUrbano.processoAquisicao = checkProcessoAquisicao.selected;
				} else {
					var bemImovelAvaliacaoRural:BemImovelVO = BemImovelVO(bemImovelAvaliacao);
					
					bemImovelAvaliacaoRural.benfeitoria = FlexUtil.stringVaziaParaNulo(campoBenfeitorias.text);
					bemImovelAvaliacaoRural.valorBenfeitoria = FlexUtil.campoNumericoZeroParaNulo(campoValorAvaliacaoBenfeitorias.valor);
					bemImovelAvaliacaoRural.processoAquisicao = checkProcessoAquisicao2.selected;
				}
				
				bemImovelAvaliacao.valorCompraVenda = FlexUtil.campoNumericoZeroParaNulo(campoValorCompraVenda.valor);
				bemImovelAvaliacao.dataCompraVenda = campoDataCompraVenda.selectedDate;
				bemImovelAvaliacao.valorAvaliacao = FlexUtil.campoNumericoZeroParaNulo(campoValorAvaliacao.valor);
				bemImovelAvaliacao.dataAvaliacao = campoDataAvaliacao.selectedDate;
				
				var pessoa:PessoaPlataformaVO = componenteProcurarPessoa.registro as PessoaPlataformaVO;
				bemImovelAvaliacao.idPessoaAvaliador = pessoa != null ? pessoa.idCompartilhamento : NaN;
				//bemImovelAvaliacao.tiposOnus = ListCollectionView(componenteSelecaoTipoOnus.obterItensSelecionados());
			}
    	}

		/**
		 * @inheritDoc
		 */
    	public function preencherCampos(bem:BemVO):void {
			limpar();
			if(bem is BemImovelVO){
				var bemImovelAvaliacaoUrbano:BemImovelVO = BemImovelVO(bem);
				
				FlexUtil.selecionarValorCombo(comboTipoImplantacao, bemImovelAvaliacaoUrbano.codigoTipoImplantacaoBemImovel);
				FlexUtil.selecionarValorCombo(comboEstadoConservacao, bemImovelAvaliacaoUrbano.codigoTipoEstadoConservacao);
				FlexUtil.selecionarValorCombo(comboPadraoAcabamento, bemImovelAvaliacaoUrbano.codigoTipoPadraoAcabamentoBemImovel);
				FlexUtil.selecionarValorCombo(comboServicosCondominiais, bemImovelAvaliacaoUrbano.codigoTipoServicoCondominialBemImovel);
				campoAreaPrivativa.valor = bemImovelAvaliacaoUrbano.areaPrivativa;
				campoQuantidadeDormitorios.valor = bemImovelAvaliacaoUrbano.quantidadeDormitorios;
				campoQuantidadeVagasGaragem.valor = bemImovelAvaliacaoUrbano.quantidadeVagasGaragem;
				campoAreaTerreno.valor = bemImovelAvaliacaoUrbano.areaTerreno;
				campoAreaTestada.valor = bemImovelAvaliacaoUrbano.areaTestada;
				checkProcessoAquisicao.selected = bemImovelAvaliacaoUrbano.processoAquisicao;
			} else if(bem is BemImovelVO) {
				var bemImovelAvaliacaoRural:BemImovelVO = BemImovelVO(bem);
				
				campoBenfeitorias.text = bemImovelAvaliacaoRural.benfeitoria;
				campoValorAvaliacaoBenfeitorias.valor = bemImovelAvaliacaoRural.valorBenfeitoria;
				checkProcessoAquisicao2.selected = bemImovelAvaliacaoRural.processoAquisicao;
			}
			
			if(bem is BemImovelVO){
				var bemImovelAvaliacao:BemImovelVO = BemImovelVO(bem);
				
				campoValorCompraVenda.valor = bemImovelAvaliacao.valorCompraVenda;
				campoDataCompraVenda.selectedDate = bemImovelAvaliacao.dataCompraVenda != null ? bemImovelAvaliacao.dataCompraVenda : null;
				campoValorAvaliacao.valor = bemImovelAvaliacao.valorAvaliacao;
				campoDataAvaliacao.selectedDate = bemImovelAvaliacao.dataAvaliacao != null ? bemImovelAvaliacao.dataAvaliacao : null;
				componenteProcurarPessoa.procurarPorCodigoCompartilhamento(bemImovelAvaliacao.idPessoaAvaliador);
				//componenteSelecaoTipoOnus.adicionarListaRegistrosSelecionados(bemImovelAvaliacao.tiposOnus);
			}
    	}

		/**
		 * @inheritDoc
		 */
    	public function limpar():void {
			comboTipoImplantacao.selectedIndex = 0;
			comboEstadoConservacao.selectedIndex = 0;
			comboPadraoAcabamento.selectedIndex = 0;
			comboServicosCondominiais.selectedIndex = 0;
			campoAreaPrivativa.text = "";
			campoQuantidadeDormitorios.text = "";
			campoQuantidadeVagasGaragem.text = "";
			campoAreaTerreno.text = "";
			campoAreaTestada.text = "";
			checkProcessoAquisicao.selected = false;
			campoBenfeitorias.text = "";
			campoValorAvaliacaoBenfeitorias.text = "";
			checkProcessoAquisicao2.selected = false;
			campoValorCompraVenda.text = "";
			campoDataCompraVenda.selectedDate = null;
			campoValorAvaliacao.text = "";
			campoDataAvaliacao.selectedDate = null;
			componenteProcurarPessoa.limpar()
			componenteSelecaoTipoOnus.limpar();
    	}
		
		/**
		 * @inheritDoc
		 */
		public function abaTrocada():void {
			marcarCamposObrigatorios();
			var tipoLocalizacao:TipoLocalizacaoBemVO = obterAbaDadosBasicos().comboTipoLocalizacao.selectedItem as TipoLocalizacaoBemVO;
			
			if(tipoLocalizacao != null){
				var tipoLocalizacaoBemEnum:TipoLocalizacaoBemImovelEnum = TipoLocalizacaoBemImovelEnum.obterPorCodigo(tipoLocalizacao.codigo);
				
				if(TipoLocalizacaoBemImovelEnum.RURAL == tipoLocalizacaoBemEnum){
					canvasAvaliacaoRural.visible = canvasAvaliacaoRural.includeInLayout = true;
					canvasAvaliacaoUrbano.visible = canvasAvaliacaoUrbano.includeInLayout = false;
					canvasCompraVenda.y = 125;
					canvasAvaliador.y = 200;
					canvasTipoOnus.y = 300;
				} else {
					canvasAvaliacaoRural.visible = canvasAvaliacaoRural.includeInLayout = false;
					canvasAvaliacaoUrbano.visible = canvasAvaliacaoUrbano.includeInLayout = true;
					canvasCompraVenda.y = 275;
					canvasAvaliador.y = 350;
					canvasTipoOnus.y = 450;
				}
				
				this.verticalScrollPolicy = "auto";
			}
		}

		/**
		 * @inheritDoc
		 */
    	public function verificarAlteracoes(bem:BemVO = null):Boolean {
			if(bem == null) {
				var onus:ArrayCollection = componenteSelecaoTipoOnus.obterItensSelecionados();
				
				if(verificarModificacaoCombo(comboTipoImplantacao)
					|| verificarModificacaoCombo(comboEstadoConservacao)
					|| verificarModificacaoCombo(comboPadraoAcabamento)
					|| verificarModificacaoCombo(comboServicosCondominiais)
					|| campoAreaPrivativa.valor != 0
					|| campoQuantidadeDormitorios.valor != 0
					|| campoQuantidadeVagasGaragem.valor != 0
					|| campoAreaTerreno.valor != 0
					|| campoAreaTestada.valor != 0
					|| checkProcessoAquisicao.selected
					
					|| campoBenfeitorias.text != ""
					|| campoValorAvaliacaoBenfeitorias.valor != 0
					|| checkProcessoAquisicao2.selected
					
					|| campoValorCompraVenda.valor != 0
					|| campoDataCompraVenda.selectedDate != null
					|| campoValorAvaliacao.valor != 0
					|| campoDataAvaliacao.selectedDate != null
					|| componenteProcurarPessoa.registro != null
				
					|| (onus != null && onus.length > 0)) {
					return true;
				}
					return false;
				} else if(!(bem is BemImovelVO)) {
					if(verificarModificacaoCombo(comboTipoImplantacao)
						|| verificarModificacaoCombo(comboEstadoConservacao)
						|| verificarModificacaoCombo(comboPadraoAcabamento)
						|| verificarModificacaoCombo(comboServicosCondominiais)
						|| campoAreaPrivativa.valor != 0
						|| campoQuantidadeDormitorios.valor != 0
						|| campoQuantidadeVagasGaragem.valor != 0
						|| campoAreaTerreno.valor != 0
						|| campoAreaTestada.valor != 0
						|| checkProcessoAquisicao.selected
						
						|| campoBenfeitorias.text != ""
						|| campoValorAvaliacaoBenfeitorias.valor != 0
						|| checkProcessoAquisicao2.selected
						
						|| (onus != null && onus.length > 0)) {
						return true;
					}
				} else if (bem is BemImovelVO) {
				var bemImovelAvaliacao:BemImovelVO = bem as BemImovelVO;
				
				if((isImovelUrbano() && bemImovelAvaliacao is BemImovelVO)) {
					return true;
				} else if ((!isImovelUrbano() && bemImovelAvaliacao is BemImovelVO)) {
					return true;
				}
				
				if(isImovelUrbano()) {
					var bemImovelAvaliacaoUrbano:BemImovelVO = BemImovelVO(bemImovelAvaliacao);
					if(FlexUtil.compararValoresNumericos(bemImovelAvaliacaoUrbano.codigoTipoImplantacaoBemImovel, comboTipoImplantacao.selectedItem.codigo)
						&& FlexUtil.compararValoresNumericos(bemImovelAvaliacaoUrbano.codigoTipoEstadoConservacao, comboEstadoConservacao.selectedItem.codigo)
						&& FlexUtil.compararValoresNumericos(bemImovelAvaliacaoUrbano.codigoTipoPadraoAcabamentoBemImovel, comboPadraoAcabamento.selectedItem.codigo)
						&& FlexUtil.compararValoresNumericos(bemImovelAvaliacaoUrbano.codigoTipoServicoCondominialBemImovel, comboServicosCondominiais.selectedItem.codigo)
						
						&& FlexUtil.compararValoresNumericos(bemImovelAvaliacaoUrbano.areaPrivativa, campoAreaPrivativa.valor)
						&& FlexUtil.compararValoresNumericos(bemImovelAvaliacaoUrbano.quantidadeDormitorios, campoQuantidadeDormitorios.valor)
						&& FlexUtil.compararValoresNumericos(bemImovelAvaliacaoUrbano.quantidadeVagasGaragem, campoQuantidadeVagasGaragem.valor)
						
						&& FlexUtil.compararValoresNumericos(bemImovelAvaliacaoUrbano.areaTerreno, campoAreaTerreno.valor)
						&& FlexUtil.compararValoresNumericos(bemImovelAvaliacaoUrbano.areaTestada, campoAreaTestada.valor)) {
						return false;
					}
					return true;
				} else {
					var bemImovelAvaliacaoRural:BemImovelVO = BemImovelVO(bemImovelAvaliacao);
					if(bemImovelAvaliacaoRural.benfeitoria == FlexUtil.stringVaziaParaNulo(campoBenfeitorias.text)
						&& FlexUtil.compararValoresNumericos(bemImovelAvaliacaoRural.valorBenfeitoria, campoValorAvaliacaoBenfeitorias.valor)) {
						return false;
					}
				}
				
				if(FlexUtil.compararValoresNumericos(bemImovelAvaliacao.valorCompraVenda, campoValorCompraVenda.valor)
					&& bemImovelAvaliacao.dataCompraVenda == DateTimeBase.getDateTime(campoDataCompraVenda.selectedDate)
					&& FlexUtil.compararValoresNumericos(bemImovelAvaliacao.valorAvaliacao, campoValorAvaliacao.valor)
					&& bemImovelAvaliacao.dataAvaliacao == DateTimeBase.getDateTime(campoDataAvaliacao.selectedDate)
					&& FlexUtil.compararValoresNumericos(FlexUtil.obterValorPropriedade(bemImovelAvaliacao, "pessoaCompartilhamentoAvaliador.id"), 
						FlexUtil.obterValorPropriedade(componenteProcurarPessoa.registro, "idCompartilhamento"))){
					return false;
				}
				return true;
			}
			return false;
		}
		
		/**
		 *  @inheritDoc
		 */
		public function verificarPreenchimento():Boolean {
			return (verificarModificacaoCombo(comboTipoImplantacao)
				|| verificarModificacaoCombo(comboEstadoConservacao)
				|| verificarModificacaoCombo(comboPadraoAcabamento)
				|| verificarModificacaoCombo(comboServicosCondominiais)
				|| campoAreaPrivativa.valor != 0
				|| campoQuantidadeDormitorios.valor != 0
				|| campoQuantidadeVagasGaragem.valor != 0
				|| campoAreaTerreno.valor != 0
				|| campoAreaTestada.valor != 0
				|| checkProcessoAquisicao.selected
				
				|| campoBenfeitorias.text != ""
				|| campoValorAvaliacaoBenfeitorias.valor != 0
				|| checkProcessoAquisicao2.selected
				
				|| campoValorCompraVenda.valor != 0
				|| campoDataCompraVenda.selectedDate != null
				|| campoValorAvaliacao.valor != 0
				|| campoDataAvaliacao.selectedDate != null
				|| componenteProcurarPessoa.registro != null);
		}
		
		private function verificarModificacaoCombo(combo:Combo, item:Object = null):Boolean {
			if(combo != null && combo.initialized && combo.enabled) {
				if(combo.selectedItem != item) {
					return true;
				}
			}
			return false;
		}

		/**
		 * @inheritDoc
		 */
		public function configurarDestino(destino:DestinoVO):void {
			
		}
		
		/**
		 * Verifica quando o tipo de implantação foi alterada, para atualização dos campos.
		 */
		private function aoTrocarTipoImplantacao(evento:Event):void {
			var tipoImplantacaoBem:TipoImplantacaoBemImovelVO = comboTipoImplantacao.selectedItem as TipoImplantacaoBemImovelVO;
			var tipoBem:TipoBemImovelVO = obterAbaDadosBasicos().comboTipoBem.selectedItem as TipoBemImovelVO;
			
			campoAreaTerreno.validarObrigatorio = false;
			campoAreaTestada.validarObrigatorio = false;
			
			if(tipoImplantacaoBem != null){
				if(tipoBem != null && tipoBem.habilitaAreaTerreno) {
					campoAreaTerreno.validarObrigatorio = tipoImplantacaoBem.areaObrigatoria;
				}
				
				if(tipoBem != null && tipoBem.habilitaAreaTerreno) {
					campoAreaTestada.validarObrigatorio = tipoImplantacaoBem.areaObrigatoria;
				}
			}
		}
		
		/**
		 * Verifica quando o tipo de bem é alterado, para atualização dos campos dependentes.
		 */
		private function aoTrocarTipoBem(evento:Event):void {
			var tipoBem:TipoBemImovelVO = obterAbaDadosBasicos().comboTipoBem.selectedItem as TipoBemImovelVO;
			
			if(tipoBem != null && obterModo() != 3) {
				comboEstadoConservacao.enabled = tipoBem.habilitaTipoEstadoConservacao;
				if(!tipoBem.habilitaTipoEstadoConservacao) {
					comboEstadoConservacao.selectedIndex = 0;
				}
				
				comboPadraoAcabamento.enabled = tipoBem.habilitaTipoPadraoAcabamento;
				if(!tipoBem.habilitaTipoPadraoAcabamento) {
					comboPadraoAcabamento.selectedIndex = 0;
				}
				
				comboServicosCondominiais.enabled = tipoBem.habilitaTipoServicoCondominial;
				if(!tipoBem.habilitaTipoServicoCondominial) {
					comboServicosCondominiais.selectedIndex = 0;
				}
				
				campoAreaPrivativa.enabled = tipoBem.habilitaAreaPrivativa;
				if(!tipoBem.habilitaAreaPrivativa) {
					campoAreaPrivativa.text = "";
				}
				
				campoQuantidadeDormitorios.enabled = tipoBem.habilitaQuantidadeDormitorio;
				if(!tipoBem.habilitaQuantidadeDormitorio) {
					campoQuantidadeDormitorios.text = "";
				}
				
				campoQuantidadeVagasGaragem.enabled = tipoBem.habilitaQuantidadeVagasGaragem;
				if(!tipoBem.habilitaQuantidadeVagasGaragem) {
					campoQuantidadeVagasGaragem.text = "";
				}
				
				campoAreaTerreno.enabled = tipoBem.habilitaAreaTerreno;
				if(!tipoBem.habilitaAreaTerreno) {
					campoAreaTerreno.text = "";
				}
				
				campoAreaTestada.enabled = tipoBem.habilitaAreaTerreno;
				if(!tipoBem.habilitaAreaTerreno) {
					campoAreaTestada.text = "";
				}
			} else {
				habilitarCamposTipoBem(false);
			}
		}
		
		/**
		 * Habilita/Desabilita os campos dependentes do tipo do bem.
		 */
		private function habilitarCamposTipoBem(valor:Boolean):void {
			comboEstadoConservacao.enabled = valor;
			comboPadraoAcabamento.enabled = valor;
			comboServicosCondominiais.enabled = valor;
			campoAreaPrivativa.enabled = valor;
			campoQuantidadeDormitorios.enabled = valor;
			campoQuantidadeVagasGaragem.enabled = valor;
			campoAreaTerreno.enabled = valor;
			campoAreaTestada.enabled = valor;
		}
		
		/**
		 * Ao atualizar a área total na aba de dados básicos, atualizamos também a capacidade máxima dos campos dependentes da área.
		 */
		private function aoTrocarAreaTotal(evento:Event):void {
			var valor:Number = obterAbaDadosBasicos().campoAreaTotal.valor;
			
			campoAreaTerreno.valorMaximo = valor;
			campoAreaTestada.valorMaximo = valor;
		}
		
		/**
		 * Obtém a aba de dados básicos.
		 */
		private function obterAbaDadosBasicos():DadosBasicosImovel {
			return this.parent.getChildByName("abaDadosBasicos") as DadosBasicosImovel;
		}
		
		/**
		 * Verifica se o imóvel selecionado é do tipo 'URBANO'.
		 */
		private function isImovelUrbano():Boolean {
			var tipoLocalizacao:TipoLocalizacaoBemImovelEnum = obterTipoLocalizacaoBem();
			return TipoLocalizacaoBemImovelEnum.URBANO.equals(tipoLocalizacao);
		}
		
		/**
		 * Obtém o tipo de localização selecionada na aba de dados básicos.
		 */
		private function obterTipoLocalizacaoBem():TipoLocalizacaoBemImovelEnum {
			var tipoLocalizacao:TipoLocalizacaoBemVO = obterAbaDadosBasicos().comboTipoLocalizacao.selectedItem as TipoLocalizacaoBemVO;
			
			if(tipoLocalizacao != null){
				return TipoLocalizacaoBemImovelEnum.obterPorCodigo(tipoLocalizacao.codigo);
			}
			
			return null;
		}
		
		/**
		 * @inheritDoc
		 */
		public function bloquearCampoModoVisualizacao(bloquear:Boolean):void {
			comboTipoImplantacao.enabled = !bloquear;
			comboEstadoConservacao.enabled = !bloquear;
			comboPadraoAcabamento.enabled = !bloquear;
			comboServicosCondominiais.enabled = !bloquear;
			componenteSelecaoTipoOnus.enabled = !bloquear;
		}
		
		/**
		 * @inheritDoc
		 */
		public function marcarCamposObrigatorios():void {
			desmarcarTodosCampos();
			if(verificarAlteracoes() && obterModo() != 3) { //MODO VISUALIZACAO
				var tipoLocalizacao:TipoLocalizacaoBemVO = obterAbaDadosBasicos().comboTipoLocalizacao.selectedItem as TipoLocalizacaoBemVO;
				var tipoLocalizacaoBemEnum:TipoLocalizacaoBemImovelEnum = TipoLocalizacaoBemImovelEnum.obterPorCodigo(tipoLocalizacao.codigo);
				
				campoValorAvaliacao.validarObrigatorio = true;
				campoDataAvaliacao.validarObrigatorio = true;
				componenteProcurarPessoa.validarObrigatorio = true;
				
				if(TipoLocalizacaoBemImovelEnum.RURAL == tipoLocalizacaoBemEnum) {
					if(checkProcessoAquisicao2.selected) {
						campoValorCompraVenda.validarObrigatorio = true;
						campoDataCompraVenda.validarObrigatorio = true;
					}
					
				} else if (TipoLocalizacaoBemImovelEnum.URBANO == tipoLocalizacaoBemEnum) {
					comboTipoImplantacao.validarObrigatorio = true;
					
					if(comboEstadoConservacao.enabled) {
						comboEstadoConservacao.validarObrigatorio = true;
					}
					
					if(comboPadraoAcabamento.enabled) {
						comboPadraoAcabamento.validarObrigatorio = true;
					}
					
					if(comboServicosCondominiais.enabled) {
						comboServicosCondominiais.validarObrigatorio = true;
					}
					
					if(campoAreaPrivativa.enabled) {
						campoAreaPrivativa.validarObrigatorio = true;
					}
					
					if(campoQuantidadeDormitorios.enabled) {
						campoQuantidadeDormitorios.validarObrigatorio = true;
					}
					
					if(campoQuantidadeVagasGaragem.enabled) {
						campoQuantidadeVagasGaragem.validarObrigatorio = true;
					}
					
					if(checkProcessoAquisicao.selected) {
						campoValorCompraVenda.validarObrigatorio = true;
						campoDataCompraVenda.validarObrigatorio = true;
					}
				}
			}
		}
		
		/**
		 * Desmarca a obrigatoriedade de todos os campos.
		 */
		private function desmarcarTodosCampos():void {
			comboTipoImplantacao.validarObrigatorio = false;
			comboEstadoConservacao.validarObrigatorio = false;
			comboPadraoAcabamento.validarObrigatorio = false;
			comboServicosCondominiais.validarObrigatorio = false;
			campoAreaPrivativa.validarObrigatorio = false;
			campoQuantidadeDormitorios.validarObrigatorio = false;
			campoQuantidadeVagasGaragem.validarObrigatorio = false;
			campoBenfeitorias.validarObrigatorio = false;
			campoValorAvaliacaoBenfeitorias.validarObrigatorio = false;
			campoValorCompraVenda.validarObrigatorio = false;
			campoDataCompraVenda.validarObrigatorio = false;
			campoValorAvaliacao.validarObrigatorio = false;
			campoDataAvaliacao.validarObrigatorio = false;
			componenteProcurarPessoa.validarObrigatorio = false;
			componenteSelecaoTipoOnus.validarObrigatorio = false;
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