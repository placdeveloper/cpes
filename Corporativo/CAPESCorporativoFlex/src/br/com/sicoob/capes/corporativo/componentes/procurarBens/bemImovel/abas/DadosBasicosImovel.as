package br.com.sicoob.capes.corporativo.componentes.procurarBens.bemImovel.abas{
	
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.cadastro.FormularioCadastro;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.componentes.pti.ObjetoEvent;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.app.Application;
	import br.com.sicoob.capes.comum.componentes.pesquisaendereco.TelaPesquisaEndereco;
	import br.com.sicoob.capes.comum.enums.TipoClassificacaoBemEnum;
	import br.com.sicoob.capes.comum.util.FlexUtil;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.LocalidadeVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoLocalizacaoBemVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoUsoBemImovelVO;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.IAba;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.vo.BemImovelVO;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.vo.BemVO;
	
	import flash.events.Event;
	
	import mx.collections.ListCollectionView;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	
	/**
	 * Aba de dados básicos do bem imóvel.
	 * 
	 * @author Bruno.Carneiro
	 */
	public class DadosBasicosImovel extends DadosBasicosImovelView implements IAba {
		
		private static const CLASSE_SERVICO:String = "br.com.sicoob.capes.corporativo.fachada.ProcurarBemImovelFachada";
		
		private var _destino:DestinoVO;
		private var servico:ServicoJava;
		
		private var _bemImovelAlteracao:BemImovelVO = null;
		
		/**
		 * @inheritDoc
		 */
		public function inicializar():void {
			servico = ServicoJavaUtil.getServico(CLASSE_SERVICO);
			comboTipoLocalizacao.addEventListener(ListEvent.CHANGE, obterPorTipoLocalizacao);
			comboTipoUso.addEventListener(ListEvent.CHANGE, obterTipoBem);
			checkValorBem.addEventListener(Event.CHANGE, ativarCampoValorBem);
			componentePesquisaEndereco.addEventListener(TelaPesquisaEndereco.EVENTO_REGISTRO_SELECIONADO, preencherRegistroEndereco);
			checkNumero.addEventListener(Event.CHANGE, bloquearCampoNumero);
		}
    	
		/**
		 * @inheritDoc
		 */
    	public function obterDefinicoes(evento:ResultEvent):void {
			FlexUtil.atualizarCombo(comboTipoLocalizacao, evento.result.dados.listaTiposLocalizacao, true, "SELECIONE", true);
			FlexUtil.atualizarCombo(comboTipoLogradouro, evento.result.dados.listaTipoLogradouro, true, "SELECIONE");
			
			FlexUtil.adicionarItemOpcional(comboTipoUso, "SELECIONE");
			FlexUtil.adicionarItemOpcional(comboTipoBem, "SELECIONE");
			FlexUtil.adicionarItemOpcional(comboUnidadeMedida, "SELECIONE");
    	}
		
		/**
		 * @inheritDoc
		 */
		public function atualizarCamposRegistro(bem:BemVO):void {
			var bemImovelBasico:BemImovelVO = BemImovelVO(bem);
			
			bemImovelBasico.codigoTipoLocalizacaoBem = comboTipoLocalizacao.selectedItem.codigo;
			bemImovelBasico.codigoTipoUsoBem = comboTipoUso.selectedItem.codigo;
			bemImovelBasico.codigoTipoBem = comboTipoBem.selectedItem.codigo;
			bemImovelBasico.descricao = FlexUtil.stringVaziaParaNulo(campoDescricao.text);
			bemImovelBasico.informacoesGerais = FlexUtil.stringVaziaParaNulo(campoInformacoesGerais.text);
			bemImovelBasico.denominacao = FlexUtil.stringVaziaParaNulo(campoDenominacao.text);
			bemImovelBasico.valor = campoValorBem.valor;
			bemImovelBasico.valorNaoInformado = checkValorBem.selected;
			bemImovelBasico.areaTotal = FlexUtil.campoNumericoZeroParaNulo(campoAreaTotal.valor);
			bemImovelBasico.codigoUnidadeMedida = comboUnidadeMedida.selectedItem.codigo;
			bemImovelBasico.mesRenovacaoSeguro = FlexUtil.campoNumericoZeroParaNulo(campoMesRenovacao.valor);

			var localidade:LocalidadeVO = componentePesquisaEndereco.obterRegistro();
			if(localidade != null) {
				bemImovelBasico.idLogradouro = localidade.idLogradouro;
			}
			
			bemImovelBasico.idLocalidade = Number(componentePesquisaMunicipio.txtValor.text);
			bemImovelBasico.numero = FlexUtil.stringVaziaParaNulo(campoNumero.text);
			bemImovelBasico.complemento = FlexUtil.stringVaziaParaNulo(campoComplemento.text);
			
			bemImovelBasico.codigoTipoClassificacaoBem = TipoClassificacaoBemEnum.BEM_IMOVEL.codigo;
    	}

		/**
		 * @inheritDoc
		 */
    	public function preencherCampos(bem:BemVO):void {
			var bemImovel:BemImovelVO = bem as BemImovelVO;
			_bemImovelAlteracao = BemImovelVO(bemImovel);
			
			comboTipoLocalizacao.enabled = true;
			
			if(bemImovel != null){
				FlexUtil.selecionarValorCombo(comboTipoLocalizacao, bemImovel.codigoTipoLocalizacaoBem);
				
				campoDescricao.text = bemImovel.descricao;
				campoInformacoesGerais.text = bemImovel.informacoesGerais;
				campoDenominacao.text = bemImovel.denominacao;
				campoValorBem.valor = bemImovel.valor;
				checkValorBem.selected = bemImovel.valorNaoInformado;
				campoAreaTotal.valor = bemImovel.areaTotal;
				FlexUtil.selecionarValorCombo(comboUnidadeMedida, bemImovel.codigoUnidadeMedida);
				campoMesRenovacao.valor = bemImovel.mesRenovacaoSeguro;
				campoNumero.text = bemImovel.numero;
				campoComplemento.text = bemImovel.complemento;				

				campoValorBem.enabled = !checkValorBem.selected;

				componentePesquisaEndereco.carregarEndereco(bemImovel.idLogradouro);
				componentePesquisaMunicipio.txtValor.text = String(bemImovel.idLocalidade);
				componentePesquisaMunicipio.pesquisar();
				
				var modo:int = obterModo();
				if(modo == 2 || modo == 3) { // MODO EDIÇÃO ou VISUALIZAÇÃO
					comboTipoLocalizacao.enabled = false;
				}
			}
			verificarNumero();
    	}

		/**
		 * @inheritDoc
		 */
    	public function limpar():void {
			comboTipoLocalizacao.selectedIndex = 0;
			comboTipoUso.selectedIndex = 0;
			comboTipoBem.selectedIndex = 0;
			campoDescricao.text = "";
			campoDenominacao.text = "";
			checkValorBem.selected = false;
			campoValorBem.text = "";
			campoValorBem.enabled = true;
			campoAreaTotal.text = "";
			comboUnidadeMedida.selectedIndex = 0;
			campoMesRenovacao.text = "";
			componentePesquisaEndereco.limpar();
			
			_bemImovelAlteracao = null;
    	}

		/**
		 * Faz a limpeza dos campos de endereço.
		 */
		private function limparCamposEndereco():void {
			comboTipoLogradouro.selectedIndex = 0;
			campoLogradouro.text = "";
			campoNumero.text = "";
			campoComplemento.text = "";
			campoBairro.text = "";
			componentePesquisaMunicipio.limpar();
		}
		
		/**
		 * @inheritDoc
		 */
		public function abaTrocada():void {
			
		}

		/**
		 * @inheritDoc
		 */
    	public function verificarAlteracoes(bem:BemVO = null):Boolean {
			if(bem == null) {
				if(comboTipoLocalizacao.selectedItem != null
					|| comboTipoUso.selectedItem != null
					|| comboTipoBem.selectedItem != null
					|| campoDescricao.text != ""
					|| campoDenominacao.text != ""
					|| campoValorBem.valor != 0
					|| campoAreaTotal.valor != 0
					|| comboUnidadeMedida.selectedItem != null
					|| campoMesRenovacao.valor != 0
					|| componentePesquisaEndereco.obterRegistro() != null
					|| campoNumero.text != ""
					|| campoComplemento.text != ""){
					return true;
				}
				return false;
			} else {
				var bemImovel:BemImovelVO = bem as BemImovelVO;
				
				if(bemImovel != null) {
					if(FlexUtil.compararValoresNumericos(bemImovel.codigoTipoLocalizacaoBem, comboTipoLocalizacao.selectedItem.codigo)
						&& FlexUtil.compararValoresNumericos(bemImovel.codigoTipoUsoBem, comboTipoUso.selectedItem.codigo)
						&& FlexUtil.compararValoresNumericos(bemImovel.codigoTipoBem, comboTipoBem.selectedItem.codigo)
						&& FlexUtil.compararValoresNumericos(bemImovel.codigoUnidadeMedida, comboUnidadeMedida.selectedItem.codigo)
						
						&& bemImovel.descricao == FlexUtil.stringVaziaParaNulo(campoDescricao.text)
						&& bemImovel.denominacao == FlexUtil.stringVaziaParaNulo(campoDenominacao.text)
						&& FlexUtil.compararValoresNumericos(bemImovel.valor, campoValorBem.valor)
						&& FlexUtil.compararValoresNumericos(bemImovel.areaTotal, campoAreaTotal.valor)
						
						&& FlexUtil.compararValoresNumericos(bemImovel.mesRenovacaoSeguro, campoMesRenovacao.valor)
						&& bemImovel.numero == FlexUtil.stringVaziaParaNulo(campoNumero.text)
						&& bemImovel.complemento == FlexUtil.stringVaziaParaNulo(campoComplemento.text)
						
						&& FlexUtil.compararValoresNumericos(bemImovel.idLogradouro, FlexUtil.obterValorPropriedade(componentePesquisaEndereco.obterRegistro(), "idLogradouro"))) {
						return false;
					}
					return true;
				}
			}
			return false;
    	}
		
		/**
		 *  @inheritDoc
		 */
		public function verificarPreenchimento():Boolean {
			return (comboTipoLocalizacao.selectedItem != null
				|| comboTipoUso.selectedItem != null
				|| comboTipoBem.selectedItem != null
				|| campoDescricao.text != ""
				|| campoDenominacao.text != ""
				|| campoValorBem.valor != 0
				|| campoAreaTotal.valor != 0
				|| comboUnidadeMedida.selectedItem != null
				|| campoMesRenovacao.valor != 0
				|| componentePesquisaEndereco.obterRegistro() != null
				|| campoNumero.text != ""
				|| campoComplemento.text != "");
		}
		
		/**
		 * @inheritDoc
		 */
		public function configurarDestino(destino:DestinoVO):void {
			this._destino = destino;
			servico.configurarDestino(destino);
			componentePesquisaEndereco.configurarDestino(destino);
			//componentePesquisaMunicipio.configurarDestino(destino);
		}
		
		/**
		 * Método que preenche o tipo de unidade de medida e pesquisa os tipos de bem à partir do tipo de localização.
		 */
		private function obterPorTipoLocalizacao(evento:Event):void {
			var tipoLocalizacao:TipoLocalizacaoBemVO = comboTipoLocalizacao.selectedItem as TipoLocalizacaoBemVO;
			
			FlexUtil.atualizarCombo(comboTipoUso, null, true);
			FlexUtil.atualizarCombo(comboTipoBem, null, true);
			atualizarUnidadesMedida();
			
			if(tipoLocalizacao != null){
				atualizarUnidadesMedida(tipoLocalizacao.unidadesMedida);
				MostraCursor.setBusyCursor("Obtendo os tipos de uso do bem...", Application.application, MostraCursor.CURSOR_PROGRESSO);
				var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
				dto.dados.tipoLocalizacaoBem = tipoLocalizacao;
				
				servico.addEventListener(ResultEvent.RESULT, retornoObterTipoUsoBem);
				servico.getOperation("obterTiposUsoBem").send(dto);
			}
		}
		
		/**
		 * Retorno do método de pesquisa de tipos de uso do bem.
		 */
		private function retornoObterTipoUsoBem(evento:ResultEvent):void {
			FlexUtil.atualizarCombo(comboTipoUso, evento.result.dados.listaTiposUsoBem, true, "SELECIONE", true);
			
			if(_bemImovelAlteracao != null) {
				FlexUtil.selecionarValorCombo(comboTipoUso, _bemImovelAlteracao.codigoTipoUsoBem);
			}
			
			MostraCursor.removeBusyCursor();
		}
		
		/**
		 * Obtém os tipos de bem à partir do tipo de uso.
		 */
		private function obterTipoBem(evento:Event):void {
			var tipoUsobemImovel:TipoUsoBemImovelVO = comboTipoUso.selectedItem as TipoUsoBemImovelVO;
			if(tipoUsobemImovel != null){
				FlexUtil.atualizarCombo(comboTipoBem, tipoUsobemImovel.tiposBemImovel, true, "SELECIONE", true);
			} else {
				FlexUtil.atualizarCombo(comboTipoBem, null, true, "SELECIONE", true);
			}
			
			if(_bemImovelAlteracao != null) {
				FlexUtil.selecionarValorCombo(comboTipoBem, _bemImovelAlteracao.codigoTipoBem);
			}
		}
		
		/**
		 * Atualiza a combo de unidades de medida.
		 */
		private function atualizarUnidadesMedida(valor:ListCollectionView = null):void {
			FlexUtil.atualizarCombo(comboUnidadeMedida, valor, true);
		}

		private function preencherRegistroEndereco(evento:ObjetoEvent): void {
			var localidade:LocalidadeVO = evento.objeto as LocalidadeVO;
			
			this.campoNumero.validarObrigatorio = false;
			
			if(localidade != null) {
				//selecionarComboTipoLogradouro(localidade.idTipoLogradouro);
				FlexUtil.selecionarValorCombo(comboTipoLogradouro, localidade.idTipoLogradouro, "idTipoLogradouro");
				
				this.campoLogradouro.text = FlexUtil.nuloParaString(localidade.nomeLogradouro).toUpperCase();
				this.campoBairro.text = FlexUtil.nuloParaString(localidade.nomeBairro).toUpperCase();
				this.campoNumero.validarObrigatorio = true;
				
				if (localidade.idLocalidadePai && localidade.idLocalidadePai != 0) {
					this.componentePesquisaMunicipio.txtValor.text = String(localidade.idLocalidadePai);
				} else {
					this.componentePesquisaMunicipio.txtValor.text = String(localidade.idLocalidade);
				}
				
				this.componentePesquisaMunicipio.pesquisar();
			} else {
				limparCamposEndereco();
			}
		}
		
		/**
		 * Habilita/Desabilita o campo de valor.
		 */
		private function ativarCampoValorBem(evento:Event):void {
			campoValorBem.text = "0";
			campoValorBem.enabled = !this.checkValorBem.selected;
			campoValorBem.validarObrigatorio = !this.checkValorBem.selected;
		}

		/**
		 * Método que seleciona o item da combo à partir do idTipoLogradoudo informado.
		 */
		private function selecionarComboTipoLogradouro(idTipoLogradouro:Number):void {
			for each (var objeto:Object in this.comboTipoLogradouro.dataProvider) {
				if(objeto.idTipoLogradouro == idTipoLogradouro) {
					this.comboTipoLogradouro.selectedItem = objeto;
					break;
				}
			}
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
		 * Verifica se o endereço possui número e ativa/desativa o checkBox
		 */
		private function verificarNumero(evento:Event = null): void {
			if(campoNumero.text == "S/N"){
				checkNumero.selected = true;
			}else {
				checkNumero.selected = false;
			}
			
			bloquearCampoNumero();
		}
		
		/**
		 * Bloqueia o campo número
		 */
		private function bloquearCampoNumero(evento:Event = null):void {
			if(checkNumero.selected) {
				campoNumero.text = "S/N";
				campoNumero.enabled = false;
			} else {
				if(evento != null) {
					campoNumero.text = "";
				}
				campoNumero.enabled = true;
			}
		}
		
		/**
		 * @inheritDoc
		 */
		public function validar():Boolean {
			return true;
		}
	}
}