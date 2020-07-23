package br.com.sicoob.capes.comum.componentes.tabelafipe {
	
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.componentes.pti.ObjetoEvent;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.app.Application;
	import br.com.sicoob.capes.comum.componentes.tabelafipe.enums.TipoVeiculoFipeEnum;
	import br.com.sicoob.capes.comum.componentes.tabelafipe.vo.DetalheFipeVO;
	import br.com.sicoob.capes.comum.componentes.tabelafipe.vo.FipeVO;
	import br.com.sicoob.capes.comum.componentes.tabelafipe.vo.MarcaFipeVO;
	import br.com.sicoob.capes.comum.componentes.tabelafipe.vo.ModeloFipeVO;
	import br.com.sicoob.capes.comum.componentes.tabelafipe.vo.VeiculoFipeVO;
	import br.com.sicoob.capes.comum.util.FlexUtil;
	
	import flash.events.Event;
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	/**
	 * Componente para obter as informações de veículos diretamente da FIPE.
	 * 
	 * @author Bruno.Carneiro
	 **/
	public class TabelaFipe extends TabelaFipeView {
		
		private static const CLASSE_SERVICO: String = "br.com.sicoob.capes.cadastro.fachada.TabelaFipeFachada";
		private static const INDICADOR_OBRIGATORIEDADE:String = " *";
		
		private var servico:ServicoJava = new ServicoJava();
		
		private var _tamanhoCombos:Number;
		private var _posicaoRotulos:Number;
		private var _posicaoCombos:Number;
		private var _tipoVeiculoObrigatorio:Boolean = false;
		private var _marcaObrigatoria:Boolean = false;
		private var _veiculoObrigatorio:Boolean = false;
		private var _modeloObrigatorio:Boolean = false;
		
		private var _detalhe:DetalheFipeVO;
		
		public static const EVENTO_DETALHE_VEICULO:String = "DetalheVeiculo";
		
		private static const MENSAGEM_TIPO_VEICULO:String = "Obtendo os tipos de veículos...";
		private static const MENSAGEM_MARCA:String = "Obtendo as marcas de veículos...";
		private static const MENSAGEM_VEICULO:String = "Obtendo os veículos...";
		private static const MENSAGEM_MODELO:String = "Obtendo os modelos de veículos...";
		private static const MENSAGEM_DETALHE:String = "Obtendo os detalhes do veículo...";
				
		/**
		 * Método Construtor
		 **/
		public function TabelaFipe(){
			super();
			servico.source = CLASSE_SERVICO;
			
			registerClassAlias("br.com.sicoob.capes.integracao.negocio.enums.TipoVeiculoFipeEnum", TipoVeiculoFipeEnum);
			registerClassAlias("br.com.sicoob.capes.integracao.negocio.vo.FipeVO", FipeVO);
			registerClassAlias("br.com.sicoob.capes.integracao.negocio.vo.MarcaFipeVO", MarcaFipeVO);
			registerClassAlias("br.com.sicoob.capes.integracao.negocio.vo.ModeloFipeVO", ModeloFipeVO);
			registerClassAlias("br.com.sicoob.capes.integracao.negocio.vo.DetalheFipeVO", DetalheFipeVO);
			
			this.addEventListener(FlexEvent.CREATION_COMPLETE, iniciar);
		}
		
		/**
		 * Método executado após a construção do componente.
		 **/
		private function iniciar(evt:FlexEvent = null):void {
			comboTipoVeiculo.addEventListener(Event.CHANGE, obterMarcas);
			comboTipoVeiculo.validarObrigatorio = _tipoVeiculoObrigatorio;
			rotuloTipoVeiculo.text = rotuloTipoVeiculo.text + (_tipoVeiculoObrigatorio ? INDICADOR_OBRIGATORIEDADE : "") + ":";

			comboMarca.addEventListener(Event.CHANGE, obterVeiculos);
			comboMarca.validarObrigatorio = _marcaObrigatoria;
			rotuloMarca.text = rotuloMarca.text + (_marcaObrigatoria ? INDICADOR_OBRIGATORIEDADE : "") + ":";
			
			comboVeiculo.addEventListener(Event.CHANGE, obterModelos);
			comboVeiculo.validarObrigatorio = _veiculoObrigatorio;
			rotuloVeiculo.text = rotuloVeiculo.text + (_veiculoObrigatorio ? INDICADOR_OBRIGATORIEDADE : "") + ":";
			
			comboModelo.addEventListener(Event.CHANGE, obterDetalhes);
			comboModelo.validarObrigatorio = _modeloObrigatorio;
			rotuloModelo.text = rotuloModelo.text + (_modeloObrigatorio ? INDICADOR_OBRIGATORIEDADE : "") + ":";
			
			configurarTamanhoCombos();
			configurarPosicaoCampos();
		}
		
		/**
		 * Obtém os tipos de veículos
		 **/
		private function obterTiposVeiculo():void {
			MostraCursor.setBusyCursor(MENSAGEM_TIPO_VEICULO, Application.application, MostraCursor.CURSOR_PROGRESSO);
			servico.obterTiposVeiculo.addEventListener(ResultEvent.RESULT, retornoObterTiposVeiculos);
			servico.mensagemEspera = MENSAGEM_TIPO_VEICULO;
			servico.bloquearOperacao = true;
			servico.getOperation("obterTiposVeiculo").send(new RequisicaoReqDTO());
		}
		
		/**
		 * Obtém as marcas dos veículos.
		 **/
		private function obterMarcas(evento:Event = null):void {
			if(comboTipoVeiculo.selectedItem != null) {
				MostraCursor.setBusyCursor(MENSAGEM_MARCA, Application.application, MostraCursor.CURSOR_PROGRESSO);
				servico.obterMarcas.addEventListener(ResultEvent.RESULT, retornoObterMarcas);
				servico.mensagemEspera = MENSAGEM_MARCA;
				servico.bloquearOperacao = true;
				
				var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
				dto.dados.tipoVeiculo = comboTipoVeiculo.selectedItem as TipoVeiculoFipeEnum;
				
				servico.getOperation("obterMarcas").send(dto);
			}
		}
		
		/**
		 * Obtém os veículos
		 **/
		private function obterVeiculos(evento:Event = null):void {
			if(comboMarca.selectedItem != null) {
				MostraCursor.setBusyCursor(MENSAGEM_VEICULO, Application.application, MostraCursor.CURSOR_PROGRESSO);
				servico.obterVeiculos.addEventListener(ResultEvent.RESULT, retornoObterVeiculos);
				servico.mensagemEspera = MENSAGEM_VEICULO;
				servico.bloquearOperacao = true;
				
				var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
				dto.dados.tipoVeiculo = comboTipoVeiculo.selectedItem as TipoVeiculoFipeEnum;
				dto.dados.idMarca = comboMarca.selectedItem.id;
					
				servico.getOperation("obterVeiculos").send(dto);
			}
		}
		
		/**
		 * Obtém o modelo dos veículos
		 **/
		private function obterModelos(evento:Event = null):void {
			if(comboVeiculo.selectedItem != null) {
				MostraCursor.setBusyCursor(MENSAGEM_MODELO, Application.application, MostraCursor.CURSOR_PROGRESSO);
				servico.obterModelos.addEventListener(ResultEvent.RESULT, retornoObterModelos);
				servico.mensagemEspera = MENSAGEM_MODELO;
				servico.bloquearOperacao = true;
				
				var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
				dto.dados.tipoVeiculo = comboTipoVeiculo.selectedItem as TipoVeiculoFipeEnum;
				dto.dados.idMarca = comboMarca.selectedItem.id;
				dto.dados.idVeiculo = comboVeiculo.selectedItem.id;
				
				servico.getOperation("obterModelos").send(dto);
			}
		}
		
		/**
		 * Obtém os detalhes do modelo selecionado.
		 **/
		private function obterDetalhes(evento:Event = null):void {
			if(comboModelo.selectedItem != null) {
				MostraCursor.setBusyCursor(MENSAGEM_DETALHE, Application.application, MostraCursor.CURSOR_PROGRESSO);
				servico.obterDetalhes.addEventListener(ResultEvent.RESULT, retornoObterDetalhes);
				servico.mensagemEspera = MENSAGEM_DETALHE;
				servico.bloquearOperacao = true;
				
				var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
				dto.dados.tipoVeiculo = comboTipoVeiculo.selectedItem as TipoVeiculoFipeEnum;
				dto.dados.idMarca = comboMarca.selectedItem.id;
				dto.dados.idVeiculo = comboVeiculo.selectedItem.id;
				dto.dados.idModelo = comboModelo.selectedItem.id;
				
				servico.getOperation("obterDetalhes").send(dto);
			}
		}
		
		/**
		 * Método de retorno do obter tipos de veículos
		 **/
		private function retornoObterTiposVeiculos(evento:ResultEvent = null):void {
			FlexUtil.atualizarCombo(comboTipoVeiculo, evento.result.dados.listaTiposVeiculo, true);
			MostraCursor.removeBusyCursor();
		}
		
		/**
		 * Método de retorno do obter marcas de veículos
		 **/
		private function retornoObterMarcas(evento:ResultEvent = null):void {
			FlexUtil.atualizarCombo(comboMarca, evento.result.dados.listaMarcas, true);
			MostraCursor.removeBusyCursor();
		}
		
		/**
		 * Método de retorno do obter veículos
		 **/
		private function retornoObterVeiculos(evento:ResultEvent = null):void {
			FlexUtil.atualizarCombo(comboVeiculo, evento.result.dados.listaVeiculos, true);
			MostraCursor.removeBusyCursor();
		}
		
		/**
		 * Método de retorno do obter modelos de veículos
		 **/
		private function retornoObterModelos(evento:ResultEvent = null):void {
			FlexUtil.atualizarCombo(comboModelo, evento.result.dados.listaModelos, true);
			MostraCursor.removeBusyCursor();
		}
		
		/**
		 * Método de retorno do obter detalhes do modelo do veículo
		 **/
		private function retornoObterDetalhes(evento:ResultEvent = null):void {
			_detalhe = evento.result.dados.detalhes;
			
			if(_detalhe != null) {
				dispatchEvent(new ObjetoEvent(EVENTO_DETALHE_VEICULO, _detalhe));
			}
			MostraCursor.removeBusyCursor();
		}
		
		/**
		 * Configura o tamanho das combos
		 **/
		private function configurarTamanhoCombos():void {
			if(!isNaN(_tamanhoCombos) && _tamanhoCombos > 0) {
				comboTipoVeiculo.width = _tamanhoCombos;
				comboMarca.width = _tamanhoCombos;
				comboVeiculo.width = _tamanhoCombos;
				comboModelo.width = _tamanhoCombos;
			}
		}
		
		/**
		 * Configura a posição dos campos na tela.
		 **/
		private function configurarPosicaoCampos():void {
			if(!isNaN(_posicaoRotulos)) {
				rotuloTipoVeiculo.x = _posicaoRotulos;
				rotuloMarca.x = _posicaoRotulos;
				rotuloVeiculo.x = _posicaoRotulos;
				rotuloModelo.x = _posicaoRotulos;
			}
			
			if(!isNaN(_posicaoCombos)) {
				comboTipoVeiculo.x = _posicaoCombos;
				comboMarca.x = _posicaoCombos;
				comboVeiculo.x = _posicaoCombos;
				comboModelo.x = _posicaoCombos;
			}
		}
		
		/**
		 * Método que faz a limpeza do componente, voltando os valores iniciais.
		 **/
		public function limpar():void {
			comboTipoVeiculo.selectedIndex = 0;
			comboMarca.selectedIndex = 0;
			comboVeiculo.selectedIndex = 0;
			comboModelo.selectedIndex = 0;
		}
		
		/**
		 * Método para configuração do destino dos serviços.
		 **/
		public function configurarDestino(destino:DestinoVO = null):void {
			if (destino != null) {
				servico.configurarDestino(destino);
				obterTiposVeiculo();
			}
		}
		
		//Métodos de acesso
		
		public function get tipoVeiculo():TipoVeiculoFipeEnum {
			var retorno:TipoVeiculoFipeEnum = null;
			if(comboTipoVeiculo.selectedItem != null){
				retorno = comboTipoVeiculo.selectedItem as TipoVeiculoFipeEnum;
			}
			return retorno;
		}
		
		public function get marca():MarcaFipeVO {
			var retorno:MarcaFipeVO = null;
			if(comboMarca.selectedItem != null){
				retorno = comboMarca.selectedItem as MarcaFipeVO;
			}
			return retorno;
		}
		
		public function get veiculo():VeiculoFipeVO {
			var retorno:VeiculoFipeVO = null;
			if(comboVeiculo.selectedItem != null){
				retorno = comboVeiculo.selectedItem as VeiculoFipeVO;
			}
			return retorno;
		}
		
		public function get modelo():ModeloFipeVO {
			var retorno:ModeloFipeVO = null;
			if(comboModelo.selectedItem != null){
				retorno = comboModelo.selectedItem as ModeloFipeVO;
			}
			return retorno;
		}
		
		public function get detalhe():DetalheFipeVO {
			return _detalhe;
		}
		
		/**
		 * Define o valor do tamanho das combos
		 **/
		public function set tamanhoCombos(valor:Number):void {
			_tamanhoCombos = valor;
		}
		
		/**
		 * Define o valor da posição dos rótulos 
		 **/
		public function set posicaoRotulos(valor:Number):void {
			_posicaoRotulos = valor;
		}
		
		/**
		 * Define o valor da posição das combos. 
		 **/
		public function set posicaoCombos(valor:Number):void {
			_posicaoCombos = valor;
		}
		
		/**
		 * Obtém o valor da obrigatoriedade do tipo de veículo
		 **/
		public function get tipoVeiculoObrigatorio():Boolean{
			return _tipoVeiculoObrigatorio;
		}

		/**
		 * Define o valor da obrigatoriedade do tipo de veículo
		 **/
		public function set tipoVeiculoObrigatorio(value:Boolean):void{
			_tipoVeiculoObrigatorio = value;
		}

		/**
		 * Obtém o valor da obrigatoriedade da marca de veículo
		 **/
		public function get marcaObrigatoria():Boolean{
			return _marcaObrigatoria;
		}

		/**
		 * Define o valor da obrigatoriedade da marca de veículo
		 **/
		public function set marcaObrigatoria(value:Boolean):void{
			_marcaObrigatoria = value;
		}
		
		/**
		 * Obtém o valor da obrigatoriedade do veículo
		 **/
		public function get veiculoObrigatorio():Boolean{
			return _veiculoObrigatorio;
		}

		/**
		 * Define o valor da obrigatoriedade do veículo
		 **/
		public function set veiculoObrigatorio(value:Boolean):void{
			_veiculoObrigatorio = value;
		}

		/**
		 * Obtém o valor da obrigatoriedade do modelo do veículo
		 **/
		public function get modeloObrigatorio():Boolean{
			return _modeloObrigatorio;
		}

		/**
		 * Define o valor da obrigatoriedade do modelo do veículo
		 **/
		public function set modeloObrigatorio(value:Boolean):void{
			_modeloObrigatorio = value;
		}

	}
}