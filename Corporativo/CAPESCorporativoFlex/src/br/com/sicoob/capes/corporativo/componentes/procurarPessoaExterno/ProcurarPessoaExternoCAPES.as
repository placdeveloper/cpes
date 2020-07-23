package br.com.sicoob.capes.corporativo.componentes.procurarPessoaExterno {
	
	import flash.display.DisplayObject;
	import flash.events.Event;
	import flash.events.FocusEvent;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.componentes.input.Texto;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.dto.ResultadoValidacaoDTO;
	import br.com.bancoob.sisbr.componentes.pti.ObjetoEvent;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.sisbr.portal.SISBRLoader;
	import br.com.bancoob.sisbr.portal.SISBRModuleEvent;
	import br.com.bancoob.util.IValidavel;
	import br.com.bancoob.util.eventos.EventData;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.util.validadores.CNPJ;
	import br.com.bancoob.util.validadores.CPF;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.corporativo.componentes.procurarPessoaExterno.util.ConstantesProcurarPessoaExternoCAPES;
	import br.com.sicoob.capes.corporativo.componentes.procurarPessoaExterno.vo.ProcurarPessoaExternoVO;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	
	public class ProcurarPessoaExternoCAPES extends ProcurarPessoaExternoCAPESView implements IValidavel {
		
		private static const EVENTO_DESTINO_RECUPERADO:String = "destinoRecuperado";
		private static const DESTINO_CAPES:String = "servicosJavaCapes";
		
		private static const CLASSE_SERVICO:String = "br.com.sicoob.capes.corporativo.fachada.ProcurarPessoaExternoFachada";
		private static const OPERACAO_PESQUISA:String = "procurarPessoaPorCpfCnpJ";
		private static const MENSAGEM_ESPERA:String = "Procurando Pessoas...";
		
		private static const MENSAGEM_CPFCNPJ_INVALIDO:String = "CPF/CNPJ inválido";
		private static const MENSAGEM_PESSOA_INVALIDA:String = "Pessoa inválida.";
		private static const TITULO_JANELA_SELECIONAR_PESSOA:String = "PROCURAR PESSOAS CAPES";
		private static const ICONE_JANELA_SELECIONAR_PESSOA:String = "br/com/bancoob/imagens/icos/srch_16.png";
		private static const TITULO_JANELA_ERRO:String = "ERRO!";
		
		private var servico:ServicoJava = new ServicoJava();
		
		private var _janela:Janela;
		
		private var _valorAtual:String = "";
		private var _valorCooperativa:Number = NaN;
		private var _valorUnidadeInst:Number = NaN;
		private var _registro:ProcurarPessoaExternoVO;
		
		private var _tipoPessoa:String;
		private var _tipoCliente:String;
		private var _compartilhadosBancoob:Boolean = false;
		private var _validarObrigatorio:Boolean = false;
		private var _exibirNome:Boolean = true;
		private var _numeroCooperativa:Number;
		private var _unidadeInstituicao:Number;
		private var _idPessoa:Number;
		
		private var _habilitaCadastroPessoa:Boolean = false;
		private var janela:Janela;
		private var sisbrLoader:SISBRLoader;
		
		private var _janelaAberta:Boolean = false;
		private var destinoConfigurado:Boolean = false;
		
		private var selecionarPessoaExternoCAPES:SelecionarPessoaExternoCAPES = new SelecionarPessoaExternoCAPES();
		
		public static const EVENTO_REGISTRO_PREENCHIDO:String = "registroPreenchido";
		public static const PESQUISA_REALIZADA:String = "pesquisaCAPESRealizada";
		
		public function ProcurarPessoaExternoCAPES() {
			super();
			
			registerClassAlias("br.com.sicoob.capes.comum.negocio.vo.ProcurarPessoaExternoVO", ProcurarPessoaExternoVO);
			
			servico.source = CLASSE_SERVICO;
			servico.mensagemEspera = MENSAGEM_ESPERA;
			servico.addEventListener(ResultEvent.RESULT, obterDados_resultado);
			servico.addEventListener(FaultEvent.FAULT, obterDados_erro);
			
			this.addEventListener(FlexEvent.CREATION_COMPLETE, iniciar);
		}
		
		private function iniciar(evt:FlexEvent):void {
			inicializarServicos();
			
			campoCodigo.restrict = "0123456789";
			campoCodigo.Formato = "";
			campoCodigo.maxChars = 14;
			campoCodigo.tipoEntrada = Texto.TEXTO;
			campoCodigo.validarObrigatorio = _validarObrigatorio;
			
			campoCodigo.addEventListener(FlexEvent.ENTER, acao_botao_enter);
			campoCodigo.addEventListener(FocusEvent.FOCUS_OUT, ao_perder_o_foco);
			campoCodigo.addEventListener(Event.CHANGE, ao_trocar);
			botaoProcurar.addEventListener(MouseEvent.CLICK, procurarPessoas);
			selecionarPessoaExternoCAPES.addEventListener(REGISTRO_SELECIONADO, obterRegistro);
		}
		
		private function procurarPessoaPorCpfCnpJ():void {
			if(campoCodigo.editable) {
				MostraCursor.setBusyCursor(MENSAGEM_ESPERA, Application.application, MostraCursor.CURSOR_PESQUISAR);
				
				_valorAtual = campoCodigo.text;
				_valorCooperativa = !isNaN(_numeroCooperativa) ? _numeroCooperativa : NaN;
				
				if((_valorAtual != null) && (_valorAtual != "")) {
					if(validarCpfCnpj(_valorAtual)) {
						executarConsulta();
					} else {
						Alerta.show(MENSAGEM_CPFCNPJ_INVALIDO, TITULO_JANELA_ERRO, Alerta.ALERTA_ERRO);
						campoNome.text = "";
					}
				}
			}
		}
		
		private function executarConsulta():void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.idPessoa = _idPessoa;
			dto.dados.cpfCnpj = _valorAtual;
			dto.dados.numeroCooperativa = !isNaN(_numeroCooperativa) ? _numeroCooperativa : 0;
			dto.dados.procurarBancoob = _compartilhadosBancoob;
			
			if(_tipoPessoa != null && _tipoPessoa != "") {
				dto.dados.idTipoPessoa = Number(_tipoPessoa);
			}
			
			if(_tipoCliente != null && _tipoCliente != "") {
				if(ConstantesProcurarPessoaExternoCAPES.TIPO_CLIENTE_SOMENTE_CLIENTES == _tipoCliente){
					dto.dados.somenteClientes = true;
				} else if(ConstantesProcurarPessoaExternoCAPES.TIPO_CLIENTE_SOMENTE_PESSOAS == _tipoCliente){
					dto.dados.somenteClientes = false;
				}
			}
			
			if(destinoConfigurado) {
				servico.getOperation(OPERACAO_PESQUISA).send(dto);
			}
		}
		
		private function obterDados_resultado(evento:ResultEvent):void {
			var retorno:ArrayCollection = evento.result.dados.lista;
			if(retorno != null && retorno.length > 0) {
				_registro = retorno[0];
				preencherCampos(_registro);
				_valorAtual = _registro.cpfCnpj;
				_idPessoa = NaN;
			} else if (_habilitaCadastroPessoa) {
				exibirJanelaConfirmacaoAdicionarPessoa();
			}
			
			this.dispatchEvent(new ObjetoEvent(PESQUISA_REALIZADA));
			MostraCursor.removeBusyCursor();
		}
		
		private function obterDados_erro(evento:FaultEvent):void {
			MostraCursor.removeBusyCursor();
			Alerta.show(evento.fault.faultString, TITULO_JANELA_ERRO, Alerta.ALERTA_ERRO);
		}
		
		/**
		 * Exibe a confirmação para inclusão da pessoa, caso o parâmetro esteja ligado.
		 **/
		private function exibirJanelaConfirmacaoAdicionarPessoa():void {
			Alerta.show("A pessoa informada não foi encontrada! Deseja incluí-la?", "Confirmação", Alerta.ALERTA_PERGUNTA, null, confirmaAberturaTelaCadastroPessoa);
		}
		
		/**
		 * Abre a janela com a tela de cadastro da pessoa.
		 */
		private function confirmaAberturaTelaCadastroPessoa(evento:Event):void {
			
			// Faz a configuração da janela.
			janela = new Janela();
			janela.width = 560;
			janela.height = 275;
			janela.title = "CADASTRAR PESSOA";
			
			// Obtém o loader, que carregará o módulo de cadastro.
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
		 * Preenche os dados incluídos pelo módulo de inclusão de pessoa na tela do componente e fecha a janela de cadastro.
		 */
		private function fecharJanelaInclusao(evento:EventData = null):void {
			var vo:PessoaPlataformaVO = evento.data as PessoaPlataformaVO;
			preencherCamposComponenteInclusao(vo);
			_valorAtual = vo.cpfCnpj;
			_idPessoa = NaN;
			
			obterSisbrLoader().unloadModule();
			sisbrLoader = null;
			janela.fecharJanela();
			evento.stopImmediatePropagation();
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
		
		private function preencherCampos(pessoa:ProcurarPessoaExternoVO):void {
			if(pessoa != null) {
				campoCodigo.text = pessoa.cpfCnpj;
				campoNome.text = pessoa.nome;
			} else {
				campoNome.text = "";
			}
			
			campoNome.visible = _exibirNome;
			
			this.dispatchEvent(new ObjetoEvent(EVENTO_REGISTRO_PREENCHIDO));
		}
		
		private function preencherCamposComponenteInclusao(vo:PessoaPlataformaVO):void {
			if(vo != null) {
				campoCodigo.text = vo.cpfCnpj;
				campoNome.text = vo.nomePessoa;
			} else {
				campoNome.text = "";
			}
		}
		
		private function acao_botao_enter(evento:FlexEvent = null):void {
			if(realizaConsulta()){				
				procurarPessoaPorCpfCnpJ();
			}
		}
		
		private function ao_perder_o_foco(evento:Event):void {
			if(!campoCodigo.enabled) {
				return;
			}
			
			if(realizaConsulta()) {
				procurarPessoaPorCpfCnpJ();
			}
		}
		
		private function ao_trocar(evento:Event = null):void {
			campoNome.text = "";
			_registro = null;
			
			if(realizaConsulta()){
				_valorAtual = "";
				_valorCooperativa = 0;
			}
		}
		
		private function realizaConsulta():Boolean {
			var retorno:Boolean = true;
			var _codigo:String = campoCodigo.text;
			
			if(_codigo == null || "" == _codigo){
				retorno = false;
			}else if(_codigo == _valorAtual) {
				if(!isNaN(_valorCooperativa) && !isNaN(_numeroCooperativa)) {
					if(_valorCooperativa == _numeroCooperativa) {
						retorno = false;
					}
				} else {
					retorno = false;
				}
			}
			
			return retorno;
		}
		
		private function procurarPessoas(evento:Event = null):void {
			if(_janela == null){
				_janela = new Janela();
			}
			
			_janela.title = TITULO_JANELA_SELECIONAR_PESSOA;
			_janela.icone = ICONE_JANELA_SELECIONAR_PESSOA;
			
			if(!_janelaAberta) {
				_janelaAberta = true;
				selecionarPessoaExternoCAPES.tipoPessoa = _tipoPessoa;
				selecionarPessoaExternoCAPES.tipoCliente = _tipoCliente;
				selecionarPessoaExternoCAPES.compartilhadosBancoob = _compartilhadosBancoob;
				selecionarPessoaExternoCAPES.numeroCooperativa = _numeroCooperativa;
				selecionarPessoaExternoCAPES.unidadeInstituicao = _unidadeInstituicao;
				selecionarPessoaExternoCAPES.habilitaCadastroPessoa = _habilitaCadastroPessoa;
				
				_janela.removeAllChildren();
				_janela.addChild(DisplayObject(selecionarPessoaExternoCAPES));
				_janela.addEventListener(Janela.FECHAR_JANELA, aoFecharJanela);
				
				selecionarPessoaExternoCAPES.validarCampos();
				
				_janela.abrir(DisplayObject(Application.application), true, true);
			}
		}
		
		private function aoFecharJanela(evento:Event):void {
			_janelaAberta = false;
		}
		
		private function obterRegistro(evento:ObjetoEvent = null): void {
			_registro = evento.objeto as ProcurarPessoaExternoVO;
			preencherCampos(_registro);
		}
		
		//--------------------------------------------------------------------------
		// Métodos de acesso.
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
		
		public function get validarObrigatorio():Boolean {
			return _validarObrigatorio;
		}
		
		public function set validarObrigatorio(valor:Boolean):void {
			_validarObrigatorio = valor;
			if (campoCodigo != null) {
				campoCodigo.validarObrigatorio = valor;
			}
		}
		
		public function get compartilhadosBancoob():Boolean {
			return _compartilhadosBancoob;
		}
		
		public function set compartilhadosBancoob(valor:Boolean):void {
			_compartilhadosBancoob = valor;
		}
		
		public function get exibirNome():Boolean {
			return _exibirNome;
		}
		
		public function set exibirNome(valor:Boolean):void {
			_exibirNome = valor;
			if (campoNome != null) {
				campoNome.visible = valor;
			}
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
		
		/**
		 * Obtém a pessoa pesquisada.
		 **/
		public function obterPessoa():ProcurarPessoaExternoVO {
			return _registro;
		}
		
		/**
		 * Limpa os campos pesquisados.
		 **/
		public function limpar():void {
			campoCodigo.valor = 0;
			campoCodigo.text = "";
			campoNome.text = "";
			
			_valorAtual = "";
			_idPessoa = NaN;
			_registro = null;
			
			if (selecionarPessoaExternoCAPES.initialized) {
				selecionarPessoaExternoCAPES.limpar();
			}
		}
		
		/**
		 * Faz a consulta da pessoa pelo ID e carrega no componente. 
		 **/
		public function carregarPessoa(idPessoa:Number): void {
			if(idPessoa && idPessoa > 0 && _idPessoa != idPessoa){
				this._idPessoa = idPessoa;
				executarConsulta();
			}
		}
		
		//--------------------------------------------------------------------------
		//  Validação.
		//--------------------------------------------------------------------------
		
		private function validarCpfCnpj(valor:String):Boolean {
			if(valor != null && valor.length > 0) {
				return CPF.validarCPF(valor) || CNPJ.validarCNPJ(valor);
			}
			return false;
		}
		
		/**
		 * Método do componente validável.
		 **/
		public function realizarValidacao():ResultadoValidacaoDTO {
			var resultado:ResultadoValidacaoDTO = new ResultadoValidacaoDTO();
			
			if(_validarObrigatorio){
				if (_registro == null || !this.campoCodigo.validar() || !validarCpfCnpj(campoCodigo.text)) {
					var mensagem : String = MENSAGEM_PESSOA_INVALIDA;
					
					if (this.campoCodigo.errorString != "") {
						mensagem = this.campoCodigo.errorString;
					}
					
					resultado.adicionarMensagem(mensagem);
					resultado.valido = false;
				}
			}
			
			return resultado;
		}
		
		//--------------------------------------------------------------------------
		//  Configuração de destino dos serviços.
		//--------------------------------------------------------------------------
		
		private function inicializarServicos():void {
			trace("Inicializando os serviços para o componente ProcurarPessoaExternoCAPES.");
			PortalModel.portal.obterDefinicoesDestino(DESTINO_CAPES, configurarDestino);
		}
		
		public function configurarDestino(destino:DestinoVO = null):void {
			trace("Configurando o destino para o componente ProcurarPessoaExternoCAPES.");
			if (destino != null) {
				trace("Destino recebido para o componente ProcurarPessoaExternoCAPES: " + destino.destino + " endpoint: " + destino.endPoint);
				this.destino = destino;
				dispatchEvent(new ObjetoEvent(EVENTO_DESTINO_RECUPERADO, destino));
				
				servico.configurarDestino(this.destino);
				selecionarPessoaExternoCAPES.configurarDestino(destino);
				
				destinoConfigurado = true;
				botaoProcurar.enabled = true;
			}
		}
		
		public override function set enabled(value:Boolean) : void {
			super.enabled = value;
			if (initialized) {
				this.campoCodigo.enabled = enabled;
				this.botaoProcurar.enabled = enabled;
			}
		}
	}
}