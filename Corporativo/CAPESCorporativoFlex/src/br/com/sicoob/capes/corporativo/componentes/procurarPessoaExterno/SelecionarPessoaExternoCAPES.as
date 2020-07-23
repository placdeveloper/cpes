package br.com.sicoob.capes.corporativo.componentes.procurarPessoaExterno {
	import flash.display.DisplayObject;
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.componentes.eventos.EventNavegacao;
	import br.com.bancoob.componentes.input.InputCPFCNPJ;
	import br.com.bancoob.componentes.paginacao.BarraPaginacao;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.componentes.pti.ObjetoEvent;
	import br.com.bancoob.sisbr.portal.SISBRLoader;
	import br.com.bancoob.sisbr.portal.SISBRModuleEvent;
	import br.com.bancoob.util.StringUtils;
	import br.com.bancoob.util.eventos.EventData;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.enums.TipoPessoaEnum;
	import br.com.sicoob.capes.corporativo.componentes.procurarPessoaExterno.util.ConstantesProcurarPessoaExternoCAPES;
	import br.com.sicoob.capes.corporativo.componentes.procurarPessoaExterno.vo.ProcurarPessoaExternoVO;
	
	public class SelecionarPessoaExternoCAPES extends SelecionarPessoaExternoCAPESView {
		
		private static const EVENTO_DESTINO_RECUPERADO:String = "destinoRecuperado";
		
		private static const CLASSE_SERVICO:String = "br.com.sicoob.capes.corporativo.fachada.ProcurarPessoaExternoFachada";
		private static const OPERACAO_OBTER_DEFINICOES:String = "obterDefinicoesSelecionarPessoa";
		private static const OPERACAO_PROCURAR_PESSOAS:String = "procurarPessoas";
		
		private static const MENSAGEM_OBTER_DEFINICOES:String = "Obtendo definições...";
		private static const MENSAGEM_PROCURAR_PESSOAS:String = "Procurando Pessoas...";
		private static const TITULO_FILTRO_INVALIDO:String = "Filtro inválido";
		private static const MENSAGEM_FILTRO_INVALIDO:String = "Por favor, preencha pelo menos 1 (um) campo para a pesquisa.";
		private static const MENSAGEM_FILTRO_MINIMO:String = "O filtro precisa conter pelo menos 3 (três) caracteres!";
		private static const TAMANHO_FILTRO_MINIMO:Number = 3;
		
		private var servicoDefinicoes:ServicoJava = new ServicoJava();
		private var servicoProcura:ServicoJava = new ServicoJava();
		
		private var _listaResultado:ArrayCollection = new ArrayCollection();
		private var _tipoPessoa:String;
		private var _tipoCliente:String;
		private var _compartilhadosBancoob:Boolean;
		private var _numeroCooperativa:Number;
		private var _unidadeInstituicao:Number;
		private var _habilitaCadastroPessoa:Boolean;
		private var janela:Janela;
		private var sisbrLoader:SISBRLoader
		
		public function SelecionarPessoaExternoCAPES() {
			super();
			
			registerClassAlias("br.com.sicoob.capes.comum.negocio.vo.ProcurarPessoaExternoVO", ProcurarPessoaExternoVO);
			
			servicoDefinicoes.source = CLASSE_SERVICO;
			servicoDefinicoes.mensagemEspera = MENSAGEM_OBTER_DEFINICOES;
			servicoDefinicoes.addEventListener(ResultEvent.RESULT, obterDefinicoes_resultado);
			
			servicoProcura.source = CLASSE_SERVICO;
			servicoProcura.mensagemEspera = MENSAGEM_PROCURAR_PESSOAS;
			servicoProcura.addEventListener(ResultEvent.RESULT, procurarPessoa_resultado);
			
			this.addEventListener(FlexEvent.CREATION_COMPLETE, iniciar);
		}
		
		private function iniciar(evt:FlexEvent):void {
			comboTipo.addEventListener(ListEvent.CHANGE, validarTipoPessoa);
			
			tabela.doubleClickEnabled = true;
			tabela.addEventListener(ListEvent.ITEM_DOUBLE_CLICK, obterRegistro);
			tabela.addEventListener(ListEvent.CHANGE, selecionarRegistro);
			
			botaoProcurar.addEventListener(MouseEvent.CLICK, procurarPessoas);
			botaoLimpar.addEventListener(MouseEvent.CLICK, limpar);
			botaoSelecionar.addEventListener(MouseEvent.CLICK, obterRegistro);
			botaoFechar.addEventListener(MouseEvent.CLICK, fechar);
			barraPaginacao.addEventListener(BarraPaginacao.EVENT_NAVEGACAO, navegar);
			
			campoApelido.addEventListener(FlexEvent.ENTER, acao_botao_enter);
			campoCpfCnpj.addEventListener(FlexEvent.ENTER, acao_botao_enter);
			campoNome.addEventListener(FlexEvent.ENTER, acao_botao_enter);
			
			grupoCliente.selectedValue = ConstantesProcurarPessoaExternoCAPES.PESQUISAR_TODOS;
			
			obterDefinicoes();
		}
		
		private function obterDefinicoes():void {
			MostraCursor.setBusyCursor(MENSAGEM_OBTER_DEFINICOES, Application.application, MostraCursor.CURSOR_PESQUISAR);
			servicoDefinicoes.getOperation(OPERACAO_OBTER_DEFINICOES).send();
		}
		
		private function obterDefinicoes_resultado(evento:ResultEvent):void {
			MostraCursor.removeBusyCursor();
			
			if(evento.result != null && evento.result.dados != null) {
				comboTipo.dataProvider = evento.result.dados.listaTipoPessoa;
			}
			
			validarCampos();
		}
		
		public function limpar(evento:Event = null):void {
			if(campoCpfCnpj.enabled){
				campoCpfCnpj.text = "";
			}
			
			if(!_compartilhadosBancoob) {
				checkBancoob.selected = false;
			}
			
			if(_tipoCliente == null || _tipoCliente == "") {
				grupoCliente.selectedValue = 0;
			}
			
			if(_tipoPessoa == null || _tipoPessoa == "") {
				comboTipo.selectedIndex = 0;
				campoCpfCnpj.enabled = false;
			}
			
			campoApelido.text = "";
			campoNome.text = "";
			
			_listaResultado.removeAll();
			_listaResultado = new ArrayCollection();
			
			selecionarRegistro();
		}
		
		private function procurarPessoas(evento:Event = null): void {
			procurar(1);
		}
		
		private function navegar(evento:EventNavegacao):void {
			procurar(evento.pagina);
		}
		
		private function procurar(pagina:int):void {
			if(validarFiltros()){
				MostraCursor.setBusyCursor(MENSAGEM_PROCURAR_PESSOAS, Application.application, MostraCursor.CURSOR_PESQUISAR);
				
				var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
				
				dto.dados.apelido = campoApelido.text;
				dto.dados.cpfCnpj = StringUtils.trim(campoCpfCnpj.text);
				dto.dados.idTipoPessoa = comboTipo.selectedItem != null ? comboTipo.selectedItem.codigo : null;
				dto.dados.nome = campoNome.text;
				dto.dados.procurarBancoob = checkBancoob.selected;
				dto.dados.numeroCooperativa = _numeroCooperativa;
				dto.dados.pagina = pagina;
				
				if(grupoCliente.selectedValue != ""){
					if(grupoCliente.selectedValue == ConstantesProcurarPessoaExternoCAPES.TIPO_CLIENTE_SOMENTE_CLIENTES){
						dto.dados.somenteClientes = true;
					} else if(grupoCliente.selectedValue == ConstantesProcurarPessoaExternoCAPES.TIPO_CLIENTE_SOMENTE_PESSOAS){
						dto.dados.somenteClientes = false;
					}
				}
				
				servicoProcura.getOperation(OPERACAO_PROCURAR_PESSOAS).send(dto);	
			}
		}
		
		private function procurarPessoa_resultado(evento:ResultEvent):void {
			barraPaginacao.pagina = evento.result.paginaAtual;
			barraPaginacao.totalPaginas = evento.result.qtdPaginas;
			
			_listaResultado = evento.result.dados.lista;
			tabela.dataProvider = _listaResultado;
			
			if(evento.result.dados.lista.length > 0) {
				tabela.setFocus();
				tabela.selectedIndex = 0;
				botaoSelecionar.enabled = true;
			} else if (_habilitaCadastroPessoa) {
				exibirJanelaConfirmacaoAdicionarPessoa();
			}
			
			MostraCursor.removeBusyCursor();
		}
		
		private function exibirJanelaConfirmacaoAdicionarPessoa():void {
			Alerta.show("A pessoa informada não foi encontrada! Deseja incluí-la?", "Confirmação", Alerta.ALERTA_PERGUNTA, null, confirmaAberturaTelaCadastroPessoa);
		}
		
		private function confirmaAberturaTelaCadastroPessoa(evento:Event):void {
			janela = new Janela();
			janela.width = 560;
			janela.height = 275;
			janela.title = "CADASTRAR PESSOA";
			
			sisbrLoader = obterSisbrLoader();
			sisbrLoader.x = -10;
			sisbrLoader.y = -15;
			sisbrLoader.url = "/capes/swf/CadastroNovaPessoa.swf";
			sisbrLoader.destino = destino;
			sisbrLoader.loadModule();
			sisbrLoader.addEventListener(SISBRModuleEvent.CARREGADO, sisbrLoaderCarregado);
			
			// Adiciona o módulo carregado à janela e exibe a mesma.
			janela.addChild(sisbrLoader);
			janela.abrir(Application.application as DisplayObject, true, true);
		}
		
		/**
		 * Adiciona o listener ao módulo de cadastro, para saber quando a pessoa foi incluida.
		 */
		private function sisbrLoaderCarregado(evento:SISBRModuleEvent):void {
			var modulo:DisplayObject = sisbrLoader.child as DisplayObject;
			if(modulo != null) {
				modulo["numeroCooperativa"] = _numeroCooperativa;
				modulo["unidadeInstituicao"] = _unidadeInstituicao;
				modulo.addEventListener("pessoaIncluida", fecharJanelaInclusao);
			}
		}
		
		/**
		 * Obtém a instância do SISBRLoader.
		 */
		private function obterSisbrLoader():SISBRLoader {
			if(sisbrLoader == null) {
				sisbrLoader = new SISBRLoader();
			}
			return sisbrLoader;
		}
		
		private function fecharJanelaInclusao(evento:EventData = null):void {
			janela.fecharJanela();
			evento.stopImmediatePropagation();
		}
		
		private function fechar(evento:Event = null):void {
			super.fecharJanela();
		}
		
		private function validarTipoPessoa(event:ListEvent = null):void {
			if(comboTipo.selectedItem != null) {
				var codigo:Number = comboTipo.selectedItem.codigo;
				if(TipoPessoaEnum.PESSOA_FISICA.codigo == codigo){
					campoCpfCnpj.enabled = true;
					campoCpfCnpj.tipoCampo = InputCPFCNPJ.TIPO_CPF;
				} else if (TipoPessoaEnum.PESSOA_JURIDICA.codigo == codigo) {
					campoCpfCnpj.enabled = true;
					campoCpfCnpj.tipoCampo = InputCPFCNPJ.TIPO_CNPJ;
				}
			} else {
				campoCpfCnpj.text = "";
				campoCpfCnpj.inputMask = "";
				campoCpfCnpj.enabled = false;
			}
		}
		
		private function obterRegistro(evento:Event = null):void {
			this.dispatchEvent(new ObjetoEvent(REGISTRO_SELECIONADO, tabela.selectedItem));
			this.fecharJanela();
		}
		
		private function selecionarRegistro(evento:ListEvent = null):void {
			botaoSelecionar.enabled = (tabela.selectedIndex != -1);
		}
		
		public function validarCampos(): void {
			
			//Valida a exibição do campo tipoPessoa
			if(_tipoPessoa != null && StringUtils.trim(_tipoPessoa)){
				comboTipo.selectedIndex = Number(_tipoPessoa) + 1;
				comboTipo.enabled = false;
				validarTipoPessoa();
			}else {
				comboTipo.selectedIndex = 0;
				comboTipo.enabled = true;
			}
			
			//Valida a exibição do campo tipoCliente
			if(_tipoCliente != null && StringUtils.trim(_tipoCliente)){
				grupoCliente.selectedValue = _tipoCliente;
				grupoCliente.enabled = false;
			}else {
				grupoCliente.selectedValue = ConstantesProcurarPessoaExternoCAPES.PESQUISAR_TODOS;
				grupoCliente.enabled = true;
			}
			
			//Valida a exibição do campo compartilhadosBancoob
			if(_compartilhadosBancoob) {
				checkBancoob.selected = _compartilhadosBancoob;
				checkBancoob.enabled = false;
			}else if(!_compartilhadosBancoob) {
				checkBancoob.selected = _compartilhadosBancoob;
				checkBancoob.enabled = true;
			}
		}
		
		private function acao_botao_enter(evento:FlexEvent = null):void {
			procurarPessoas();
		}
		
		//--------------------------------------------------------------------------
		// Métodos de acesso
		//--------------------------------------------------------------------------
		
		public function get tipoPessoa():String {
			return _tipoPessoa;
		}
		
		public function set tipoPessoa(value:String):void {
			_tipoPessoa = value;
		}
		
		public function get tipoCliente():String {
			return _tipoCliente;
		}
		
		public function set tipoCliente(value:String):void {
			_tipoCliente = value;
		}
		
		public function get compartilhadosBancoob():Boolean {
			return _compartilhadosBancoob;
		}
		
		public function set compartilhadosBancoob(valor:Boolean):void {
			_compartilhadosBancoob = valor;
		}
		
		public function get numeroCooperativa():Number {
			return _numeroCooperativa;
		}
		
		public function set numeroCooperativa(valor:Number):void {
			_numeroCooperativa = valor;
		}
		
		public function set unidadeInstituicao(valor:Number):void {
			_unidadeInstituicao = valor;
		}
		
		public function set habilitaCadastroPessoa(valor:Boolean):void {
			_habilitaCadastroPessoa = valor;
		}
		
		//--------------------------------------------------------------------------
		//  Validação.
		//--------------------------------------------------------------------------
		
		private function validarFiltros():Boolean {
			if(campoApelido.text != null && StringUtils.trim(campoApelido.text) != '' || 
				campoCpfCnpj.text != null && StringUtils.trim(campoCpfCnpj.text) != '' ||
				campoNome.text != null && StringUtils.trim(campoNome.text) != '') {
				
				if(campoApelido.text != null && StringUtils.trim(campoApelido.text) != '' && StringUtils.trim(campoApelido.text).length < TAMANHO_FILTRO_MINIMO ||
					campoNome.text != null && StringUtils.trim(campoNome.text) != '' && StringUtils.trim(campoNome.text).length < TAMANHO_FILTRO_MINIMO){
					Alerta.show(MENSAGEM_FILTRO_MINIMO, TITULO_FILTRO_INVALIDO, Alerta.ALERTA_ERRO);
					return false;
				}
				
				return true;
			}
			
			Alerta.show(MENSAGEM_FILTRO_INVALIDO, TITULO_FILTRO_INVALIDO, Alerta.ALERTA_ERRO);
			return false;
		}
		
	    //--------------------------------------------------------------------------
	    //  Configuração de destino dos serviços.
	    //--------------------------------------------------------------------------
		public function configurarDestino(destino:DestinoVO = null):void {
			if (destino != null) {
				this.destino = destino;
				dispatchEvent(new ObjetoEvent(EVENTO_DESTINO_RECUPERADO, destino));
				servicoDefinicoes.configurarDestino(destino);
				servicoProcura.configurarDestino(destino);
			}
		}
	}
}