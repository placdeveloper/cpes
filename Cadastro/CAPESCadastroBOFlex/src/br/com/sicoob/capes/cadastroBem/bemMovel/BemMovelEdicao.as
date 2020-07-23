package br.com.sicoob.capes.cadastroBem.bemMovel {
	
	import flash.events.Event;
	
	import mx.core.Container;
	import mx.events.FlexEvent;
	import mx.events.IndexChangedEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.Botao;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.cadastro.BarraBotoesFormularioCadastroView;
	import br.com.bancoob.componentes.eventos.EventoBarraBotoes;
	import br.com.bancoob.dto.RequisicaoDTO;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.componentes.pti.ObjetoEvent;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.app.Application;
	import br.com.sicoob.capes.cadastroBem.AbaDocumentos;
	import br.com.sicoob.capes.cadastroBem.IAba;
	import br.com.sicoob.capes.cadastroBem.bemMovel.abas.DadosAvaliacaoMovel;
	import br.com.sicoob.capes.cadastroBem.bemMovel.abas.DadosAvancadosMovel;
	import br.com.sicoob.capes.cadastroBem.bemMovel.abas.DadosBasicosMovel;
	import br.com.sicoob.capes.cadastroBem.util.BensUtils;
	import br.com.sicoob.capes.comum.util.FlexUtil;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.bem.BemMovelAvaliacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.BemMovelVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.BemVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoBemMovelVO;
	import br.com.sicoob.capes.corporativo.componentes.plataformaatendimento.ProcuraClientePlataformaCAPES;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	import br.com.sicoob.capes.utils.UploadDocGedUtil;
	
	/**
	 * Tela de edição do bem móvel
	 * 
	 * @author Bruno.Carneiro
	 */
	public class BemMovelEdicao extends BemMovelEdicaoView {
		
		private static const CLASSE_SERVICO:String = "br.com.sicoob.capes.cadastro.fachada.bem.BemMovelFachada";
		private static const OPERACAO_OBTER_DEFINICOES:String = "obterDefinicoes";
		private static const MENSAGEM_OBTER_DEFINICOES:String = "Obtendo definições...";
		
		private static const MENSAGEM_GRAVANDO_DADOS:String = "Gravando dados...";
		private static const OPERACAO_INCLUIR_DADOS:String = "incluirDados";
		private static const OPERACAO_ALTERAR_DADOS:String = "alterarDados";
		
		private static const DESTINO_CAPES:String = "destinoCAPES";
		
		private var pessoaSelecionada:PessoaPlataformaVO;
		
		private var servicoDefinicao:ServicoJava;
		private var servicoGravacao:ServicoJava;
		
		private var _bem:BemVO;
		
		/**
		 * Construtor.
		 */
		public function BemMovelEdicao() {
			super();
			servicoDefinicao = ServicoJavaUtil.getServico(CLASSE_SERVICO, MENSAGEM_OBTER_DEFINICOES, ResultEvent.RESULT, retornoObterDefinicoes);
			servicoGravacao = ServicoJavaUtil.getServico(CLASSE_SERVICO, MENSAGEM_GRAVANDO_DADOS, ResultEvent.RESULT, retornoGravacao);
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		/**
		 * Método chamado após a construção da classe.
		 */
        protected override function init(evento:FlexEvent):void {
            super.init(evento);
			
			var botaoAjuda:Botao = BarraBotoesFormularioCadastroView(this.barraBotoes).btnAjuda;
			botaoAjuda.visible = botaoAjuda.includeInLayout = false;
			
			iterarAbas(function(aba:IAba):void {
				aba.inicializar();
			});
			
			pessoaSelecionada = ProcuraClientePlataformaCAPES.getPessoaSelecionada();
			
			navegacao.addEventListener(IndexChangedEvent.CHANGE, aoTrocarAba);
			obterAbaDadosBasicos().comboTipoBem.addEventListener(ListEvent.CHANGE, eventoAtivarAbas);
			
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], destino_recuperado);
		}
		
		/**
		 * Configura o destino dos serviços.
		 */
		private function destino_recuperado(destino:DestinoVO):void {
			this.destino = destino;
			ServicoJava(servicoInclusao).configurarDestino(destino);
			ServicoJava(servicoEdicao).configurarDestino(destino);
			servicoDefinicao.configurarDestino(destino);
			servicoGravacao.configurarDestino(destino);
			
			iterarAbas(function(aba:IAba):void {
				aba.configurarDestino(destino);
			});
			
			obterDefinicoes();
		}
		
		/**
		 * Obtém as definições da tela.
		 */
		public function obterDefinicoes():void {
			MostraCursor.setBusyCursor(MENSAGEM_OBTER_DEFINICOES, Application.application, MostraCursor.CURSOR_PROGRESSO);
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			//Envia o tipo de pessoa para ser utilizado nas chaves de negócio.
			dto.dados.idTipoPessoa = pessoaSelecionada.codTipoPessoa;
			servicoDefinicao.getOperation(OPERACAO_OBTER_DEFINICOES).send(dto);
		}
		
		/**
		 * Retorno do método obter definições.
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
		 * Verifica se o botão 'VOLTAR' deve ser exibido.
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
		 * Verifica se houveram alterações na tela.
		 */
	  	protected override function houveAlteracoes():Boolean {
			var retorno:Boolean = true;
			
			iterarAbas(function(aba:IAba):void {
				if (!aba.verificarAlteracoes(_bem)) {
					retorno = false;
				}
			});
			
			return retorno;
		}
		
		/**
		 * Limpa o formulário para inclusão.
		 */
		protected override function limparFormIncluir():void {
			iterarAbas(function(aba:IAba):void {
				aba.limpar();
			});
			
			ativarAbas(false);
			verificarExibicaoBotaoVoltar();
			navegacao.selectedIndex = 0;
		}
		
		/**
		 * Preenche os campos para a alteração.
		 */
		protected override function preencherCampos():void {
			ativarAbas(false);
			
			if((_bem == null || _bem != objeto) && objeto != null) {
				_bem = objeto as BemMovelVO;
			}
			
			if(destino != null && _bem != null) {
				BensUtils.verificarCompartilhamentoAssociados(_bem.idBem, destino, preencher);
			}
		}
		
		private function preencher(evento:Event = null):void {
			iterarAbas(function(aba:IAba):void {
				aba.preencherCampos(_bem);
			});
			
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
		 * Método chamado ao clicar no botão 'OK'.
		 * 
		 * Realiza a validação dos campos obrigatórios 
		 * antes de realizar as demais validações.
		 */
		protected override function botaoOkPressionado(evento:EventoBarraBotoes):void {
			validarObrigatoriedadeCampos();
			
			//Exige confirmação para salvar o bem sem documentos.
			if(ProcuraClientePlataformaCAPES.getPessoaSelecionada().utilizaGedGft && obterAbaDocumentos().abaDocumentos.obterDocumentosComprobatorios().length <= 0){
				Alerta.show(UploadDocGedUtil.VALIDACAO_DOCUMENTOS_NAO_SELECIONADOS, "ATENÇÃO", Alerta.ALERTA_PERGUNTA, null, onConfirmaGravarRegistro);
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
		 * Cria o dto para inclusão/alteração 
		 */
		protected override function montarDto():RequisicaoDTO {
			var vo:BemMovelVO = obterVO();
			iterarAbas(function(aba:IAba):void {
				aba.atualizarCamposRegistro(vo);
			});
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.bemMovel = vo;
			return dto;
		}
		
		/**
		 * Obtém o VO correto à partir das informações preenchidas.
		 */
		private function obterVO():BemMovelVO {
			var vo:BemMovelVO;
			var avaliacao:Boolean = obterAbaDadosAvaliacao().verificarPreenchimento();
			
			if(avaliacao) {
				vo = new BemMovelAvaliacaoVO();
			} else {
				vo = new BemMovelVO();
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
				var vo:BemMovelVO = obterVO();
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
		 * Realiza a validação do registro.
		 */
		private function gravarDados(bem:BemMovelVO):void {
			MostraCursor.setBusyCursor(MENSAGEM_GRAVANDO_DADOS, Application.application, MostraCursor.CURSOR_PROGRESSO);
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.bemMovel = bem;
			
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
			var bem:BemMovelVO = evento.result.dados.bemMovel;
			dispatchEvent(new ObjetoEvent(BensUtils.EVENTO_REGISTRO_INCLUIDO, bem));
			
			BensUtils.verificarBemGarantia(bem.id, destino);
		}
		
		/**
		 * Navega entre as abas, chamando a função informada.
		 */
		private function iterarAbas(funcao:Function):void {
			var abas:Array = navegacao.getChildren();
			for each (var aba:IAba in abas){
				funcao(aba);
			}
		}
		
		/**
		 * Informar as abas que uma nova aba foi selecionada.
		 */
		private function aoTrocarAba(evento:Event):void {
			iterarAbas(function(aba:IAba):void {
				aba.abaTrocada();
				
				if(obterAbaDadosAvancados().enabled) {
					aba.marcarCamposObrigatorios();
				}
			});
		}
		
		/**
		 * Ativa/Inativa as abas de acordo com o tipo de bem informado.
		 */
		private function eventoAtivarAbas(evento:Event):void {
			var tipoBemMovel:TipoBemMovelVO = obterAbaDadosBasicos().comboTipoBem.selectedItem as TipoBemMovelVO;
			
			if(tipoBemMovel != null) {
				ativarAbas(true);
				obterAbaDadosAvancados().enabled = tipoBemMovel.possuiDadosAvancados;
			} else {
				ativarAbas(false);
			}
		}
		
		/**
		 * Ativa/Inativa as abas.
		 */
		private function ativarAbas(valor:Boolean):void {
			iterarAbas(function(aba:IAba):void {
				if(Container(aba) != obterAbaDadosBasicos()){
					if(Container(aba) == obterAbaDocumentos()) {
						if(pessoaSelecionada != null) {
							valor = pessoaSelecionada.utilizaGedGft;
						} else {
							valor = false;
						}
					}
					Container(aba).enabled = valor;
				}
			});
		}
		
		/**
		 * Obtém a aba de dados básicos.
		 */
		private function obterAbaDadosBasicos():DadosBasicosMovel {
			return this.navegacao.getChildByName("abaDadosBasicos") as DadosBasicosMovel;
		}
		
		/**
		 * Obtém a aba de dados avançados
		 */
		private function obterAbaDadosAvancados():DadosAvancadosMovel {
			return this.navegacao.getChildByName("abaDadosAvancados") as DadosAvancadosMovel;
		}
		
		/**
		 * Obtém a aba de dados de avaliação.
		 */
		private function obterAbaDadosAvaliacao():DadosAvaliacaoMovel {
			return this.navegacao.getChildByName("abaDadosAvaliacao") as DadosAvaliacaoMovel;
		}
		
		/**
		 * Obtém a aba de documentos.
		 */
		private function obterAbaDocumentos():AbaDocumentos {
			return this.navegacao.getChildByName("abaDocumentos") as AbaDocumentos;
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