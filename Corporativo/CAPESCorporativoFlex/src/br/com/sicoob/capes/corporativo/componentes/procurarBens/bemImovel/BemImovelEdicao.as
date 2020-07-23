package br.com.sicoob.capes.corporativo.componentes.procurarBens.bemImovel {
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.Botao;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.cadastro.BarraBotoesFormularioCadastroView;
	import br.com.bancoob.componentes.eventos.EventoBarraBotoes;
	import br.com.bancoob.dto.RequisicaoDTO;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.app.Application;
	import br.com.sicoob.capes.comum.enums.TipoLocalizacaoBemImovelEnum;
	import br.com.sicoob.capes.comum.util.FlexUtil;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoLocalizacaoBemVO;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.IAba;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.bemImovel.abas.DadosAvaliacaoImovel;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.bemImovel.abas.DadosAvancadosImovel;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.bemImovel.abas.DadosBasicosImovel;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.janelas.AbaDocumentos;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.vo.BemImovelVO;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.vo.BemVO;
	
	import flash.events.Event;
	
	import mx.core.Container;
	import mx.events.FlexEvent;
	import mx.events.IndexChangedEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	
	/**
	 * Tela de cadastro do bem imóvel.
	 * 
	 * @author Bruno.Carneiro
	 */
	public class BemImovelEdicao extends BemImovelEdicaoView {
		
		private static const CLASSE_SERVICO:String = "br.com.sicoob.capes.corporativo.fachada.ProcurarBemImovelFachada";
		
		private static const OPERACAO_OBTER_DEFINICOES:String = "obterDefinicoes";
		private static const MENSAGEM_OBTER_DEFINICOES:String = "Obtendo definições ...";
		
		private static const MENSAGEM_GRAVANDO_DADOS:String = "Gravando dados...";
		private static const OPERACAO_INCLUIR_DADOS:String = "incluirDados";
		private static const OPERACAO_ALTERAR_DADOS:String = "alterarDados";
		
		private static const DESTINO_CAPES:String = "destinoCAPES";
		
		private var servicoDefinicao:ServicoJava;
		private var servicoGravacao:ServicoJava;
		
		private var _iniciado:Boolean = false;
		
		private var _bem:BemVO;
		
		/**
		 * Construtor.
		 */
		public function BemImovelEdicao() {
			super();
			servicoDefinicao = ServicoJavaUtil.getServico(CLASSE_SERVICO, MENSAGEM_OBTER_DEFINICOES, ResultEvent.RESULT, retornoObterDefinicoes);
			servicoGravacao = ServicoJavaUtil.getServico(CLASSE_SERVICO, MENSAGEM_GRAVANDO_DADOS, ResultEvent.RESULT, retornoGravacao);
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		/**
		 * Método chamado após a construção da classe.
		 */
        protected override function init(evento:FlexEvent): void {
            super.init(evento);
			
			_iniciado = true;
			
			var botaoAjuda:Botao = BarraBotoesFormularioCadastroView(this.barraBotoes).btnAjuda;
			botaoAjuda.visible = botaoAjuda.includeInLayout = false;
			
			iterarAbas(function(aba:IAba):void {
				aba.inicializar();
			});
			
			navegacao.addEventListener(IndexChangedEvent.CHANGE, aoTrocarAba);
			obterAbaDadosBasicos().comboTipoLocalizacao.addEventListener(ListEvent.CHANGE, eventoAtivarAbas);
			
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], destino_recuperado);
		}
		
		/**
		 * Realiza a configuração do destino dos serviço.
		 */
		private function destino_recuperado(destino:DestinoVO):void {
			this.destino = destino;
			ServicoJava(servicoInclusao).configurarDestino(destino);
			ServicoJava(servicoEdicao).configurarDestino(destino);
			servicoDefinicao.configurarDestino(destino);
			servicoGravacao.configurarDestino(destino);
			
			iterarAbas(function(aba:IAba):void{
				aba.configurarDestino(destino);
			});
			
			obterDefinicoes();
		}
		
		/**
		 * Obtém as definições da tela.
		 */
		private function obterDefinicoes():void {
			MostraCursor.setBusyCursor(MENSAGEM_OBTER_DEFINICOES, Application.application, MostraCursor.CURSOR_PROGRESSO);
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			servicoDefinicao.getOperation(OPERACAO_OBTER_DEFINICOES).send(dto);
		}
		
		/**
		 * Retorno do método de obter definições.
		 */
		private function retornoObterDefinicoes(evento:ResultEvent):void {
			iterarAbas(function(aba:IAba):void{
				aba.obterDefinicoes(evento);
			});
			
			if ((initialized) && (modo != MODO_INCLUSAO)) {
				preencherCampos();
			} else if ((initialized) && (modo == MODO_INCLUSAO)) {
				limparFormIncluir();
			}
			
			MostraCursor.removeBusyCursor();
		}
		
		/**
		 * Verifica se o botão voltar deve ser exibido.
		 */
		private function verificarExibicaoBotaoVoltar():void {
			if(modo == MODO_EDICAO || modo == MODO_INCLUSAO) {
				barraBotoes.btnFechar.enabled = 
					barraBotoes.btnFechar.includeInLayout = false;
			} else {
				barraBotoes.btnFechar.enabled = 
					barraBotoes.btnFechar.includeInLayout = true;
			}
		}
		
		/**
		 * Verifica se houve alteração na tela.
		 */
	  	protected override function houveAlteracoes():Boolean {
			var objetoAntigo:BemImovelVO = BemImovelVO(_bem);
			var retorno:Boolean = false;
			
			iterarAbas(function(aba:IAba):void {
				if (aba.verificarAlteracoes(objetoAntigo)) {
					retorno = true;
				}
			});
			
			return retorno;
		}
		
		/**
		 * Limpa o formulário para inclusão.
		 */
		protected override function limparFormIncluir(): void {
			if(_iniciado){
				iterarAbas(function(aba:IAba):void{
					aba.limpar();
				});
			}
			ativarAbas(false);
			verificarExibicaoBotaoVoltar();
			navegacao.selectedIndex = 0;
		}
		
		/**
		 * Preenche os campos para alteração.
		 */
		protected override function preencherCampos():void {
			ativarAbas(false);
			
			if((_bem == null || _bem != objeto) && objeto != null) {
				_bem = objeto as BemImovelVO;
			}
			
			if(_iniciado){
				iterarAbas(function(aba:IAba):void {
					aba.preencherCampos(_bem as BemImovelVO);
				});
			}
			
			// Adicionado por causa da ordem de execução que não bloqueava o campo, 
			// pois a validação de bloqueio executa antes do preencher campos, que faz a 
			// validação se o componente pode ou não ser habilitado de acordo com as validações de combo.
			if(MODO_VISUALIZACAO == modo) {
				obterAbaDadosAvaliacao().bloquearCampoModoVisualizacao(true);
			} else {
				obterAbaDadosAvaliacao().bloquearCampoModoVisualizacao(false);
			}
			
			verificarExibicaoBotaoVoltar();
			
			navegacao.selectedIndex = 0;
		}
		
		/**
		 * Método chamado ao clicar no botão de salvar.
		 * 
		 * Verifica se os campos foram devidamente preenchidos antes de executar as demais validações.
		 */
		protected override function botaoOkPressionado(evento:EventoBarraBotoes):void {
			validarObrigatoriedadeCampos();
			
			//Exige confirmação para salvar o bem sem documentos.
			if(obterAbaDocumentos().obterDocumentosComprobatorios().length <= 0){
				Alerta.show(AbaDocumentos.VALIDACAO_DOCUMENTOS_NAO_SELECIONADOS, "ATENÇÃO", Alerta.ALERTA_PERGUNTA, null, onConfirmaGravarRegistro);
			} else {
				onConfirmaGravarRegistro();
			}
		}
		
		/**
		 * Executa a validação da tela e se estiver válida, realiza a gravação dos dados.
		 **/
		private function onConfirmaGravarRegistro(evento:Event = null):void {
			executarSeValido(gravar);
		}
		
		/**
		 * Preenche o DTO para a gravação do registro.
		 */
		protected override function montarDto():RequisicaoDTO {
			var vo:BemImovelVO = obterVO();
			
			iterarAbas(function(aba:IAba):void {
				aba.atualizarCamposRegistro(vo);
			});
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.bemImovel = vo;
			return dto;
		}
		
		/**
		 * Obtém o VO correto para ser usado na tela à partir das informações preenchidas.
		 */
		private function obterVO():BemImovelVO {
			var vo:BemImovelVO = null;
			var avaliacao:Boolean = obterAbaDadosAvaliacao().verificarPreenchimento();
			
			if(avaliacao) {
				if(isImovelUrbano()) {
					vo = new BemImovelVO();
				} else {
					vo = new BemImovelVO();
				}
			} else {
				vo = new BemImovelVO();
			}
			
			if(modo == MODO_EDICAO) {
				FlexUtil.copiarPropriedades(_bem, vo, "id", "tipoAutorizacao");
			}
			
			return vo;
		}
		
		/**
		 * Função que grava o registro. 
		 */
		protected override function gravar():void {
			if(validarAbas()) {
				var vo:BemImovelVO = obterVO();
				iterarAbas(function(aba:IAba):void {
					aba.atualizarCamposRegistro(vo);
				});
				
				gravarDados(vo);
			}
		}
		
		/**
		 * Verifica se todas as abas passaram pela validação negocial.
		 */
		private function validarAbas():Boolean {
			var valido:Boolean = true;
			iterarAbas(function(aba:IAba):void {
				if(!aba.validar()) {
					valido = false;
				}
			});
			return valido;
		}
		
		/**
		 * Faz as validações necessárias para a inclusão de um bem.
		 */
		private function gravarDados(bem:BemImovelVO):void {
			MostraCursor.setBusyCursor(MENSAGEM_GRAVANDO_DADOS, Application.application, MostraCursor.CURSOR_PROGRESSO);
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.bemImovel = bem;
			
			if(MODO_INCLUSAO == modo) {
				servicoGravacao.getOperation(OPERACAO_INCLUIR_DADOS).send(dto);
			}else if(MODO_EDICAO == modo) {
				servicoGravacao.getOperation(OPERACAO_ALTERAR_DADOS).send(dto);			
			}
		}
		
		/**
		 * Retorno do método de validação.
		 */
		private function retornoGravacao(evento:ResultEvent):void {
			var bem:BemImovelVO = evento.result.dados.bemImovel;
			//dispatchEvent(new ObjetoEvent(BensUtils.EVENTO_REGISTRO_INCLUIDO, bem));
		}
		
		/**
		 * Percorre as abas chamando a função desejada.
		 */
		private function iterarAbas(funcao:Function):void {
			var abas:Array = navegacao.getChildren();
			for each (var aba:IAba in abas){
				funcao(aba);
			}
		}
		
		/**
		 * Informa as abas que houve mudança de abas.
		 */
		private function aoTrocarAba(evento:Event):void {
			iterarAbas(function(aba:IAba):void {
				aba.abaTrocada();
			});
		}
		
		/**
		 * Ativa ou não as abas à partir do tipo de localização selecionado na aba de dados básicos.
		 */
		private function eventoAtivarAbas(evento:Event):void {
			var tipoLocalizacaoBem:TipoLocalizacaoBemVO = obterAbaDadosBasicos().comboTipoLocalizacao.selectedItem as TipoLocalizacaoBemVO;
			
			if(tipoLocalizacaoBem != null){
				var tipolocalizacaoBemEnum:TipoLocalizacaoBemImovelEnum = TipoLocalizacaoBemImovelEnum.obterPorCodigo(tipoLocalizacaoBem.codigo);
				ativarAbas(true);
			} else {
				ativarAbas(false);
			}
		}
		
		/**
		 * Ativa/Desativa as abas.
		 */
		private function ativarAbas(valor:Boolean):void {
			iterarAbas(function(aba:IAba):void {
				if(Container(aba) != obterAbaDadosBasicos()){
					Container(aba).enabled = valor;
				}
			});
		}
		
		/**
		 * Obtém a aba de dados básicos.
		 */
		private function obterAbaDadosBasicos():DadosBasicosImovel {
			return this.navegacao.getChildByName("abaDadosBasicos") as DadosBasicosImovel;
		}
		
		/**
		 * Obtém a aba de dados avançados.
		 */
		private function obterAbaDadosAvancados():DadosAvancadosImovel {
			return this.navegacao.getChildByName("abaDadosAvancados") as DadosAvancadosImovel;
		}
		
		/**
		 * Obtém a aba de dados de avaliação.
		 */
		private function obterAbaDadosAvaliacao():DadosAvaliacaoImovel {
			return this.navegacao.getChildByName("abaDadosAvaliacao") as DadosAvaliacaoImovel;
		}
		
		/**
		 * Obtém a aba de documentos.
		 */
		private function obterAbaDocumentos():AbaDocumentos {
			return this.navegacao.getChildByName("abaDocumentos") as AbaDocumentos;
		}
		
		/**
		 * Verifica se o imóvel preenchido é do tipo 'URBANO'
		 */
		private function isImovelUrbano():Boolean {
			var tipoLocalizacao:TipoLocalizacaoBemImovelEnum = obterTipoLocalizacaoBem();
			return TipoLocalizacaoBemImovelEnum.URBANO.equals(tipoLocalizacao);
		}
		
		/**
		 * Obtém o tipo de localização informado.
		 */
		private function obterTipoLocalizacaoBem():TipoLocalizacaoBemImovelEnum {
			var tipoLocalizacao:TipoLocalizacaoBemVO = obterAbaDadosBasicos().comboTipoLocalizacao.selectedItem as TipoLocalizacaoBemVO;
			
			if(tipoLocalizacao != null){
				return TipoLocalizacaoBemImovelEnum.obterPorCodigo(tipoLocalizacao.codigo);
			}
			return null;
		}
		
		/**
		 * Valida a obrigatoriedade dos campos. 
		 */
		private function validarObrigatoriedadeCampos():void {
			iterarAbas(function(aba:IAba):void {
				if((Container(aba).enabled)) {
					aba.marcarCamposObrigatorios();
				}
			});
		}
		
		/**
		 * @inheritDoc
		 */
		protected override function habilitarFormulario():void {
			super.habilitarFormulario();
			iterarAbas(function(aba:IAba):void {
				aba.bloquearCampoModoVisualizacao(false);
			});
		}
		
		/**
		 * @inheritDoc
		 */
		protected override function desabilitarFormulario():void {
			super.desabilitarFormulario();
			iterarAbas(function(aba:IAba):void {
				aba.bloquearCampoModoVisualizacao(true);
			});
		}
		
	}
}