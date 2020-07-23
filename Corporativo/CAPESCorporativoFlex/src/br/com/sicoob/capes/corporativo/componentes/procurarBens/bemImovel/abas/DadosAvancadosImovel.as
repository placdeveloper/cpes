package br.com.sicoob.capes.corporativo.componentes.procurarBens.bemImovel.abas{
	
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.enums.TipoLocalizacaoBemImovelEnum;
	import br.com.sicoob.capes.comum.util.FlexUtil;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoLocalizacaoBemVO;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.IAba;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.vo.BemImovelVO;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.vo.BemVO;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	
	import mx.rpc.events.ResultEvent;
	
	/**
	 * Aba de dados avançados do bem imóvel.
	 * 
	 * @author Bruno.Carneiro
	 */
	public class DadosAvancadosImovel extends DadosAvancadosImovelView implements IAba {
		
		/**
		 * @inheritDoc
		 */
		public function inicializar():void {
			
		}
    	
		/**
		 * @inheritDoc
		 */
    	public function obterDefinicoes(evento:ResultEvent):void {
			
    	}
		
		/**
		 * @inheritDoc
		 */
		public function atualizarCamposRegistro(bem:BemVO):void {
			var bemAvancado:BemImovelVO = BemImovelVO(bem);
			
			var pessoa:PessoaPlataformaVO = componenteProcurarPessoa.registro as PessoaPlataformaVO;
			bemAvancado.idPessoaCartorio = pessoa != null ? pessoa.idCompartilhamento : NaN;
			
			bemAvancado.matricula = FlexUtil.stringVaziaParaNulo(campoMatricula.text);
			bemAvancado.nirf = FlexUtil.stringVaziaParaNulo(campoNirf.text);
			bemAvancado.incra = FlexUtil.stringVaziaParaNulo(campoIncra.text);
			bemAvancado.descricaoRoteiro = FlexUtil.stringVaziaParaNulo(campoRoteiroAcesso.text);
			bemAvancado.latitude = FlexUtil.campoNumericoZeroParaNulo(campoLatitude.valor, false);
			bemAvancado.longitude = FlexUtil.campoNumericoZeroParaNulo(campoLongitude.valor, false);
    	}

		/**
		 * @inheritDoc
		 */
    	public function preencherCampos(bem:BemVO):void {
			var bemImovelAvancado:BemImovelVO = bem as BemImovelVO;
			
			limpar();
			
			if(bemImovelAvancado != null) {
				componenteProcurarPessoa.procurarPorCodigoCompartilhamento(bemImovelAvancado.idPessoaCartorio);
				campoMatricula.text = bemImovelAvancado.matricula;
				campoNirf.text = bemImovelAvancado.nirf;
				campoIncra.text = bemImovelAvancado.incra;
				campoRoteiroAcesso.text = bemImovelAvancado.descricaoRoteiro;
				campoLatitude.valor = bemImovelAvancado.latitude;
				campoLongitude.valor = bemImovelAvancado.longitude;
			}
    	}

		/**
		 * @inheritDoc
		 */
    	public function limpar():void {
			componenteProcurarPessoa.limpar();
			campoMatricula.text = "";
			campoNirf.text = "";
			campoIncra.text = "";
			campoRoteiroAcesso.text = "";
			campoLatitude.text = "";
			campoLongitude.text = "";
    	}
		
		/**
		 * @inheritDoc
		 */
		public function abaTrocada():void {
			marcarCamposObrigatorios();
			var tipoLocalizacao:TipoLocalizacaoBemVO = obterAbaDadosBasicos().comboTipoLocalizacao.selectedItem as TipoLocalizacaoBemVO;
			
			if(tipoLocalizacao != null) {
				var tipoLocalizacaoBemEnum:TipoLocalizacaoBemImovelEnum = TipoLocalizacaoBemImovelEnum.obterPorCodigo(tipoLocalizacao.codigo);
				
				rotuloTitulo.texto = "Avançado - " + tipoLocalizacaoBemEnum.descricao;
				
				canvasAvancadoRural.visible = canvasAvancadoRural.includeInLayout = TipoLocalizacaoBemImovelEnum.RURAL == tipoLocalizacaoBemEnum;
			}
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
				if(componenteProcurarPessoa.registro != null
					|| campoMatricula.text != ""
					|| campoNirf.text != ""
					|| campoIncra.text != ""
					|| campoRoteiroAcesso.text != ""
					|| campoLatitude.valor != 0
					|| campoLongitude.valor != 0) {
					return true;
				}
				return false;
			}else {
				var bemAvancado:BemImovelVO = BemImovelVO(bem);
				if(bemAvancado != null){
					
					var tipoLocalizacao:TipoLocalizacaoBemVO = obterAbaDadosBasicos().comboTipoLocalizacao.selectedItem as TipoLocalizacaoBemVO;
					var tipoLocalizacaoBemEnum:TipoLocalizacaoBemImovelEnum = TipoLocalizacaoBemImovelEnum.obterPorCodigo(tipoLocalizacao.codigo);
					
					if(FlexUtil.compararValoresNumericos(FlexUtil.obterValorPropriedade(bemAvancado, "pessoaCompartilhamentoCartorio.id"), 
						FlexUtil.obterValorPropriedade(componenteProcurarPessoa.registro, "idCompartilhamento"))
						&& bemAvancado.matricula == FlexUtil.stringVaziaParaNulo(campoMatricula.text)){
						return false;
					}
					
					if(TipoLocalizacaoBemImovelEnum.RURAL == tipoLocalizacaoBemEnum){
						if(bemAvancado.nirf == FlexUtil.stringVaziaParaNulo(campoNirf.text)
							&& bemAvancado.incra == FlexUtil.stringVaziaParaNulo(campoIncra.text)
							&& bemAvancado.descricaoRoteiro == FlexUtil.stringVaziaParaNulo(campoRoteiroAcesso.text)
							&& FlexUtil.compararValoresNumericos(bemAvancado.latitude, campoLatitude.valor)
							&& FlexUtil.compararValoresNumericos(bemAvancado.longitude, campoLongitude.valor)) {
							return false;
						}
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
			return (componenteProcurarPessoa.registro != null
				|| campoMatricula.text != ""
				|| campoNirf.text != ""
				|| campoIncra.text != ""
				|| campoRoteiroAcesso.text != ""
				|| campoLatitude.valor != 0
				|| campoLongitude.valor != 0);
		}
		
		/**
		 * Obtém a aba de dados básicos.
		 */
		private function obterAbaDadosBasicos():DadosBasicosImovel {
			return this.parent.getChildByName("abaDadosBasicos") as DadosBasicosImovel;
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
				var tipoLocalizacao:TipoLocalizacaoBemVO = obterAbaDadosBasicos().comboTipoLocalizacao.selectedItem as TipoLocalizacaoBemVO;
				var tipoLocalizacaoBemEnum:TipoLocalizacaoBemImovelEnum = TipoLocalizacaoBemImovelEnum.obterPorCodigo(tipoLocalizacao.codigo);
				
				componenteProcurarPessoa.validarObrigatorio = true;
				campoMatricula.validarObrigatorio = true;
				
				if(TipoLocalizacaoBemImovelEnum.RURAL == tipoLocalizacaoBemEnum) {
					campoNirf.validarObrigatorio = true;
					campoIncra.validarObrigatorio = true;
					
					if(FlexUtil.stringVaziaParaNulo(campoRoteiroAcesso.text) == null
						&& FlexUtil.stringVaziaParaNulo(campoLatitude.text) == null
						&& FlexUtil.stringVaziaParaNulo(campoLongitude.text) == null) {
						campoRoteiroAcesso.validarObrigatorio = true;
						campoLatitude.validarObrigatorio = true;
						campoLongitude.validarObrigatorio = true;
					} else if(FlexUtil.stringVaziaParaNulo(campoRoteiroAcesso.text) != null) {
						campoLatitude.validarObrigatorio = false;
						campoLongitude.validarObrigatorio = false;
					} else if (FlexUtil.stringVaziaParaNulo(campoLatitude.text) == null
						|| FlexUtil.stringVaziaParaNulo(campoLongitude.text) == null) {
						campoLatitude.validarObrigatorio = true;
						campoLongitude.validarObrigatorio = true;
					} else if (FlexUtil.stringVaziaParaNulo(campoLatitude.text) != null
						&& FlexUtil.stringVaziaParaNulo(campoLongitude.text) != null) {
						campoRoteiroAcesso.validarObrigatorio = false;
					}
					
				} else if (TipoLocalizacaoBemImovelEnum.URBANO == tipoLocalizacaoBemEnum) {
					
				}
			}
		}
		
		/**
		 * Desmarca a obrigatoriedade de todos os campos.
		 */
		private function desmarcarTodosCampos():void {
			componenteProcurarPessoa.validarObrigatorio = false;
			campoMatricula.validarObrigatorio = false;
			campoNirf.validarObrigatorio = false;
			campoIncra.validarObrigatorio = false;
			campoRoteiroAcesso.validarObrigatorio = false;
			campoLatitude.validarObrigatorio = false;
			campoLongitude.validarObrigatorio = false;
		}
		
		/**
		 * @inheritDoc
		 */
		public function validar():Boolean {
			return true;
		}
	}
}