package {
	
	import flash.display.DisplayObject;
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.cadastro.ListaCadastroView;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.DadosLogin;
	import br.com.bancoob.sisbr.componentes.pti.ObjetoEvent;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastroBem.BotoesAdicionais;
	import br.com.sicoob.capes.cadastroBem.ITelaCrudBem;
	import br.com.sicoob.capes.cadastroBem.frmListarBem;
	import br.com.sicoob.capes.cadastroBem.bemImovel.ListarBemImovel;
	import br.com.sicoob.capes.cadastroBem.bemMovel.ListarBemMovel;
	import br.com.sicoob.capes.cadastroBem.janelas.ConsultarPosse;
	import br.com.sicoob.capes.cadastroBem.janelas.EscolherTipoClassificacaoBem;
	import br.com.sicoob.capes.cadastroBem.util.BensUtils;
	import br.com.sicoob.capes.comum.enums.TipoClassificacaoBemEnum;
	import br.com.sicoob.capes.comum.util.FlexUtil;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.DadosListagemBemVO;
	import br.com.sicoob.capes.comum.vo.DadosListagemParceriasBemVO;
	import br.com.sicoob.capes.comum.vo.entidades.LocalidadeVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoLogradouroVO;
	import br.com.sicoob.capes.comum.vo.entidades.UFVO;
	import br.com.sicoob.capes.comum.vo.entidades.UnidadeMedidaVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.BemImovelAvaliacaoRuralVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.BemImovelAvaliacaoUrbanoVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.BemImovelAvaliacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.BemImovelTipoRelacionamentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.BemImovelVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.BemMovelAvaliacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.BemMovelVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.BemPessoaCompartilhamentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.BemVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoBemImovelVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoBemMovelVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoChassiBemVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoClassificacaoBemVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoCorAutomovelBemVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoEstadoConservacaoBemVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoImplantacaoBemImovelVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoLocalizacaoBemVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoOnusBemVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoPadraoAcabamentoBemImovelVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoRelacionamentoBemImovelVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoServicoCondominialBemImovelVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoUsoBemImovelVO;
	import br.com.sicoob.capes.utils.ConversaoPessoaUtil;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCAPESBase;
	
	/**
	 * Módulo da nova tela de bens.
	 * 
	 * @author Bruno.Carneiro
	 */
	public class CadastroBem extends TelaPlataformaAtendimentoCAPESBase {
		
		private static const CLASSE_SERVICO:String = "br.com.sicoob.capes.cadastro.fachada.bem.BemPessoaCompartilhamentoFachada";
		private static const MENSAGEM_OPERACAO:String = "Executando a operação...";
		private static const MENSAGEM_EXCLUIR_DADOS:String = "Excluindo Registro...";
		private static const TITULO_JANELA_CONSULTAR_POSSE:String = "Consultar posses";
		private static const TITULO_JANELA_CONSULTAR_TIPO_CLASSIFICACAO_BEM:String = "Selecionar tipo de classificação do bem";

		private var telaLista:frmListarBem = new frmListarBem();
		//private var telaEdicao:frmEditarBem = new frmEditarBem();
		private var botoesAdicionais:BotoesAdicionais = new BotoesAdicionais();
		private var servico:ServicoJava;
		private var servicoBem:ServicoJava;
		private var servicoExcluir:ServicoJava;
		private var servicoDesbloquearBem:ServicoJava;
		
		private var janelaConsultarPosse:Janela;
		private var telaConsultarPosse:ConsultarPosse = new ConsultarPosse();
		
		private var _telaPesquisa:ITelaCrudBem;
		private var _janela:Janela;
		private var _tipoClassificacaoBem:TipoClassificacaoBemEnum;
		private var _registro:BemVO;
		private var _modoVisualizacao:Boolean = true;
		
		private var janelaEscolherTipo:Janela;
		private var telaEscolherTipoClassificacaoBem:EscolherTipoClassificacaoBem;

		/**
		 * Construtor.
		 */
		public function CadastroBem() {
			super();
			include 'br/com/sicoob/capes/utils/RegistroClasses.as';
			registerClassAlias("br.com.bancoob.sisbr.negocio.dto.PesquisaDTO", br.com.bancoob.dto.ConsultaDto);
			registerClassAlias("br.com.sicoob.capes.comum.negocio.enums.SituacaoRegistroEnum", SituacaoRegistroEnum);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.UF", UFVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoLogradouro",TipoLogradouroVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Localidade", LocalidadeVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.UnidadeMedida", UnidadeMedidaVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.TipoBemImovel", TipoBemImovelVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.TipoBemMovel", TipoBemMovelVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.TipoClassificacaoBem", TipoClassificacaoBemVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.TipoLocalizacaoBem", TipoLocalizacaoBemVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.TipoOnusBem", TipoOnusBemVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.TipoChassiBem", TipoChassiBemVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.TipoUsoBemImovel", TipoUsoBemImovelVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.TipoCorAutomovelBem", TipoCorAutomovelBemVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.TipoPadraoAcabamentoBemImovel", TipoPadraoAcabamentoBemImovelVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.TipoEstadoConservacaoBem", TipoEstadoConservacaoBemVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.TipoImplantacaoBemImovel", TipoImplantacaoBemImovelVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.TipoServicoCondominialBemImovel", TipoServicoCondominialBemImovelVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.TipoRelacionamentoBemImovel", TipoRelacionamentoBemImovelVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.Bem", BemVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.BemMovel", BemMovelVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.BemMovelAvaliacao", BemMovelAvaliacaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.BemImovel", BemImovelVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.BemImovelTipoRelacionamentoPessoa", BemImovelTipoRelacionamentoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.BemImovelAvaliacao", BemImovelAvaliacaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.BemImovelAvaliacaoRural", BemImovelAvaliacaoRuralVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.BemImovelAvaliacaoUrbano", BemImovelAvaliacaoUrbanoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.bem.BemPessoaCompartilhamento", BemPessoaCompartilhamentoVO);
			registerClassAlias("br.com.sicoob.capes.cadastro.negocio.vo.DadosListagemBemVO", DadosListagemBemVO);
			registerClassAlias("br.com.sicoob.capes.cadastro.negocio.vo.DadosListagemParceriasBemVO", DadosListagemParceriasBemVO);
			
			servico = ServicoJavaUtil.getServico(CLASSE_SERVICO, MENSAGEM_OPERACAO, ResultEvent.RESULT, retornoIncluirBemInterno);
			servicoBem = ServicoJavaUtil.getServico(CLASSE_SERVICO, MENSAGEM_OPERACAO, ResultEvent.RESULT, retornoObterBem);
			servicoExcluir = ServicoJavaUtil.getServico(CLASSE_SERVICO, MENSAGEM_EXCLUIR_DADOS, ResultEvent.RESULT, retornoExcluir);
			servicoDesbloquearBem = ServicoJavaUtil.getServico(CLASSE_SERVICO, MENSAGEM_OPERACAO, ResultEvent.RESULT, retornoDesbloquearBem);
			this.creationPolicy = "all";
			this.addEventListener("carregou", inicializar);
		}
		
		/**
		 * Método chamado após a construção do módulo.
		 */
		private function inicializar(evento:Event):void {
			MostraCursor.setBusyCursor("Iniciando...", Application.application, MostraCursor.CURSOR_PROGRESSO);
			
			this.setTelaLista(telaLista);
			//this.setTelaEdicao(telaEdicao);

			this.textoAcao = "BENS (NOVO)";
			this.textoModulo = "BENS (NOVO)";
			this.textoOpcoes = "OPÇÕES";
			this.iconModulo = "br/com/bancoob/imagens/icos/calc_32.png";
			
			telaLista.addEventListener(Modulo.LISTA_CARREGADA, onListaCarregada);
			telaLista.obterGrid().addEventListener(ListEvent.CHANGE, registroSelecionado);
			
			telaLista.gridParcerias.addEventListener(ListEvent.CHANGE, registroSelecionado);
			telaLista.gridParcerias.addEventListener(ListEvent.ITEM_DOUBLE_CLICK, alterarClicado);
			
			configurarBotoesAdicionais();
		}
		
		/**
		 * Método de dispose, chamado ao remover um módulo e deverá 
		 * remover instâncias e listeners que possam ficar na memória.
		 */
		public override function dispose():void {
			super.dispose();
			this.removeEventListener("carregou", inicializar);
			telaLista = null;
			
			//telaEdicao.dispose();
			//telaEdicao = null;
		}
		
		/**
		 * Configura os métodos a serem chamados pelos botões adicionais da plataforma.
		 */
		private function configurarBotoesAdicionais():void {
			setBotoesAdicionais(this.botoesAdicionais);
			this.botoesAdicionais.botaoSemPatrimonio.addEventListener(MouseEvent.CLICK, aoClicarBotaoSemPatrimonio);
			this.botoesAdicionais.botaoRecusouInformar.addEventListener(MouseEvent.CLICK, aoClicarBotaoRecusouInformar);
			this.botoesAdicionais.botaoConsultarPosse.addEventListener(MouseEvent.CLICK, abrirJanelaConsultarPosse);
			//this.botoesAdicionais.botaoDesbloquearBem.addEventListener(MouseEvent.CLICK, aoClicarBotaoDesbloquearBem);
			
			MostraCursor.removeBusyCursor();
		}
		
		/**
		 * Após o carregamento da lista, verifica quais botões podem ser exibidos.
		 */
		private function onListaCarregada(evento:Event):void {
			verificarExibicaoBotoesLaterais();
			
			/*listaBotoes.mostraBotao(listaBotoes.botVer, true);
			listaBotoes.mostraBotao(listaBotoes.botNovo, !verificarExistenciaBemInterno());*/
			registroSelecionado();
		}
		
		private function verificarExibicaoBotoesLaterais():void {
			this.botoesAdicionais.botaoRecusouInformar.enabled = deveExibirBotoesPatrimonio();
			this.botoesAdicionais.botaoSemPatrimonio.enabled = deveExibirBotoesPatrimonio();
			//this.botoesAdicionais.botaoDesbloquearBem.enabled = deveExibirBotaoDesbloquearBem();
		}
		
		/**
		 * Método chamado ao clicar em um registro da grid, 
		 * para saber quais botões podem ser exibidos para aquele registro.
		 */
		private function registroSelecionado(evento:ListEvent = null):void {
			if(evento != null) {
				itemLista = evento.target.selectedItem;
			} else {
				itemLista = null;
			}
			
			var registro:DadosListagemBemVO = itemLista as DadosListagemBemVO;
			
			if(registro == null) {
				listaBotoes.mostraBotao(listaBotoes.botNovo, true);
				listaBotoes.mostraBotao(listaBotoes.botVer, true);
				listaBotoes.mostraBotao(listaBotoes.botAlterar, false);
				listaBotoes.mostraBotao(listaBotoes.botExcluir, false);
				this.botoesAdicionais.botaoConsultarPosse.enabled = false;
				//this.botoesAdicionais.botaoDesbloquearBem.enabled = false;
				
				if(verificarExistenciaBemInterno()) {
					listaBotoes.mostraBotao(listaBotoes.botNovo, false);
				}
			}else if(registro.bemInterno) {
				listaBotoes.mostraBotao(listaBotoes.botNovo, false);
				listaBotoes.mostraBotao(listaBotoes.botVer, false);
				listaBotoes.mostraBotao(listaBotoes.botAlterar, false);
				listaBotoes.mostraBotao(listaBotoes.botExcluir, true);
				this.botoesAdicionais.botaoConsultarPosse.enabled = false;
				//this.botoesAdicionais.botaoDesbloquearBem.enabled = false;
			}else {
				if(registro.dataHoraInicio == null && !isNaN(registro.idInstituicaoAtualizacao)) {
					listaBotoes.mostraBotao(listaBotoes.botExcluir, true);
				} else {
					listaBotoes.mostraBotao(listaBotoes.botExcluir, false);
				}
				listaBotoes.mostraBotao(listaBotoes.botNovo, true);
				listaBotoes.mostraBotao(listaBotoes.botVer, true);
				listaBotoes.mostraBotao(listaBotoes.botAlterar, true);
				this.botoesAdicionais.botaoConsultarPosse.enabled = true;
				//this.botoesAdicionais.botaoDesbloquearBem.enabled = deveExibirBotaoDesbloquearBem(registro);
			}
		}
		
		/**
		 * Método sobreescrito para que a lista de registros 
		 * seja atualizada após a exclusão de um item.
		 */
		protected override function registroExcluido(evento:Event):void {
			super.registroExcluido(evento);
			this.telaLista.obterGrid().selectedIndex = -1;
			this.telaLista.obterGrid().setFocus();
			this.telaLista.obterLista();
			registroSelecionado();
		}
		
		/**
		 * Chama a função que adiciona os botões adicionais.
		 */
		protected override function mostraBotoesMudaGrid(evento:ListEvent = null):void {
			mostraBotoesLista();
			super.mostraBotoesMudaGrid(evento);
		}
		
		/**
		 * Adiciona os botões adicionais.
		 */
		protected override function mostraBotoesLista():void {
			super.mostraBotoesLista();
			if (botoesAdicionais.initialized) {
				botoesAdicionais.botaoConsultarPosse.visible = botoesAdicionais.botaoConsultarPosse.includeInLayout = true;
				botoesAdicionais.botaoRecusouInformar.visible = botoesAdicionais.botaoRecusouInformar.includeInLayout = true;
				botoesAdicionais.botaoSemPatrimonio.visible = botoesAdicionais.botaoSemPatrimonio.includeInLayout = true;
				//botoesAdicionais.botaoDesbloquearBem.visible = botoesAdicionais.botaoDesbloquearBem.includeInLayout = true;
			}
		}
		
		/**
		 * Exibe o componente de consulta de posses.
		 */
		private function abrirJanelaConsultarPosse(evento:MouseEvent = null):void {
			var objeto:DadosListagemBemVO = itemLista as DadosListagemBemVO;
			if(objeto == null){
				Alerta.show("Por favor, selecione um bem para consultar suas posses.", "Erro", Alerta.ALERTA_ERRO);
				return;
			}
			
			BensUtils.verificarCompartilhamentoAssociados((itemLista as DadosListagemBemVO).idBem, destino, retornoValidarCompartilhamentoConsultarPosse);
		}
		
		/**
		 * Retorno do método de consultar posses.
		 */
		private function retornoValidarCompartilhamentoConsultarPosse(evento:Event = null):void {
			var objeto:DadosListagemBemVO = itemLista as DadosListagemBemVO;
			janelaConsultarPosse = new Janela();
			telaConsultarPosse.configurarDestino(this.destino);
			
			janelaConsultarPosse.title = TITULO_JANELA_CONSULTAR_POSSE;
			janelaConsultarPosse.width = 800;
			
			var tipoClassificacao:TipoClassificacaoBemEnum = TipoClassificacaoBemEnum.obterPorCodigo(objeto.codigoTipoClassificacaoBem);
			if (TipoClassificacaoBemEnum.BEM_IMOVEL == tipoClassificacao){
				janelaConsultarPosse.height = 495;
			} else {
				janelaConsultarPosse.height = 290;
			}
			
			janelaConsultarPosse.addChild(telaConsultarPosse);
			
			telaConsultarPosse.limpar();
			telaConsultarPosse.preencherInformacoes(objeto.idBem, tipoClassificacao);
			telaConsultarPosse.consultar();
			janelaConsultarPosse.abrir(Application.application as DisplayObject, true, true);
		}
		
		/**
		 * Ação do botão adicional 'SEM PATRIMONIO'
		 */
		private function aoClicarBotaoSemPatrimonio(evento:MouseEvent):void {
			MostraCursor.setBusyCursor(MENSAGEM_OPERACAO, Application.application, MostraCursor.CURSOR_PROGRESSO);
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.pessoaCompartilhamento = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(TelaPlataformaAtendimentoCAPESBase.getPessoaPlataforma());
			servico.getOperation("adicionarBemSemPatrimonio").send(dto);
		}
		
		/**
		 * Ação do botão adicional 'RECUSOU-SE A INFORMAR'
		 */
		private function aoClicarBotaoRecusouInformar(evento:MouseEvent):void {
			MostraCursor.setBusyCursor(MENSAGEM_OPERACAO, Application.application, MostraCursor.CURSOR_PROGRESSO);
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.pessoaCompartilhamento = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(TelaPlataformaAtendimentoCAPESBase.getPessoaPlataforma());
			servico.getOperation("adicionarBemRecusouInformar").send(dto);
		}
		
		/**
		 * Ação do botão adicional 'DESBLOQUEAR BEM'
		 */
		private function aoClicarBotaoDesbloquearBem(evento:MouseEvent):void {
			var objeto:DadosListagemBemVO = itemLista as DadosListagemBemVO;
			if(objeto == null){
				Alerta.show("Por favor, selecione um bem para desbloquear", "Erro", Alerta.ALERTA_ERRO);
				return;
			}
			
			if(objeto.status == "Bloqueado" || !FlexUtil.isTextoVazioOuNulo(objeto.bloqueadoPor)) {
				//var mensagemBloqueio:String = "Atenção " + DadosLogin.usr + ", essa operação vai desbloquear um bem que está sendo utilizado pelo(s) produto(s): " + objeto.bloqueadoPor
					//+ ". Isso poderá afetar diretamente nos produtos utilizados pelos sistemas. Tem certeza que deseja desbloquear?";
				var mensagemBloqueio:String = "Atenção " + DadosLogin.usr + ", Sua ação pode impactar em propostas de crédito em andamento. Deseja continuar?";
				Alerta.show(mensagemBloqueio, "Atenção!", Alerta.ALERTA_PERGUNTA, null, confirmarDesbloqueio);
			} else {
				Alerta.show("O bem selecionado não está bloqueado.", "Desbloquear Bem", Alerta.ALERTA_ERRO);
			}
		}
		
		/**
		 * Ao confirmar o desbloqueio do bem.
		 */
		private function confirmarDesbloqueio(evento:Event):void {
			MostraCursor.setBusyCursor(MENSAGEM_OPERACAO, Application.application, MostraCursor.CURSOR_PROGRESSO);
			
			var objeto:DadosListagemBemVO = itemLista as DadosListagemBemVO;
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.idBem = objeto.idBem;
			
			servicoDesbloquearBem.getOperation("desbloquearBem").send(dto);
		}
		
		/**
		 * Retorno do método desbloquear bem.
		 */
		private function retornoDesbloquearBem(evento:ResultEvent):void {
			telaLista.obterLista();
			MostraCursor.removeBusyCursor();
		}
		
		/**
		 * retorno dos métodos de inclusão dos bens internos.
		 */
		private function retornoIncluirBemInterno(evento:Event):void {
			this.telaLista.obterLista();
			MostraCursor.removeBusyCursor();
		}
		
		/**
		 * Ao abrir o formulário de inclusão, remove os botões adicionais da tela.
		 */
		protected override function abrirInclusao():void {
			_modoVisualizacao = false;
			
			abrirJanelaEscolherTipo();
		}
		
		/**
		 * Abre a tela de pesquisa.
		 */
		protected override function consultarClicado(evento:MouseEvent = null):void {
			_modoVisualizacao = true;
			
			abrirJanelaEscolherTipo();
		}
		
		private function abrirJanelaEscolherTipo():void {
			if(janelaEscolherTipo == null || telaEscolherTipoClassificacaoBem == null) {
				telaEscolherTipoClassificacaoBem = new EscolherTipoClassificacaoBem();
				telaEscolherTipoClassificacaoBem.addEventListener(EscolherTipoClassificacaoBem.EVENTO_TIPO_CLASSIFICACAO_SELECIONADO, tipoClassificacaoSelecionado);
				
				janelaEscolherTipo = new Janela();
				janelaEscolherTipo.title = TITULO_JANELA_CONSULTAR_TIPO_CLASSIFICACAO_BEM;
				janelaEscolherTipo.width = 310;
				janelaEscolherTipo.height = 110;
				
				janelaEscolherTipo.addChild(telaEscolherTipoClassificacaoBem);
				
				telaEscolherTipoClassificacaoBem.configurarDestino(destino);
			}
			
			janelaEscolherTipo.abrir(Application.application as DisplayObject, true, true);
			telaEscolherTipoClassificacaoBem.consultar();
		}
		
		/**
		 * Abre a tela de inclusão
		 */
		private function tipoClassificacaoSelecionado(evento:ObjetoEvent):void {
			var tipoClassificacaoBem:TipoClassificacaoBemVO = evento.objeto as TipoClassificacaoBemVO;
			_tipoClassificacaoBem = TipoClassificacaoBemEnum.obterPorCodigo(tipoClassificacaoBem.codigo);
			
			abrirJanela();
		}
		
		/**
		 * Ao abrir o formulário de edição, remove os botões adicionais da tela.
		 */
		protected override function alterarClicado(evento:Event = null):void {
			var registro:DadosListagemBemVO = itemLista as DadosListagemBemVO;
			if(registro != null && !registro.bemInterno) {
				BensUtils.verificarCompartilhamentoAssociados((itemLista as DadosListagemBemVO).idBem, destino, retornoValidarCompartilhamentoAlteracao);
			}
		}
		
		/**
		 * retorno do método de validar compartilhamentos.
		 */
		private function retornoValidarCompartilhamentoAlteracao(evento:Event):void {
			var registro:DadosListagemBemVO = itemLista as DadosListagemBemVO;
			if(!registro.bemInterno) {
				//super.alterarClicado(evento);
				//habilitarBotoesAdicionais(false);
				
				_modoVisualizacao = false;
				
				_tipoClassificacaoBem = TipoClassificacaoBemEnum.obterPorCodigo(registro.codigoTipoClassificacaoBem);
				obterBem(registro.idBem);
			}
		}
		
		/**
		 * Método que obtém as informações do bem para alteração.
		 */
		private function obterBem(idBem:Number):void {
			MostraCursor.setBusyCursor(MENSAGEM_OPERACAO, Application.application, MostraCursor.CURSOR_PROGRESSO);
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.idBem = idBem;
			servicoBem.getOperation("obterBem").send(dto);
		}
		
		/**
		 * Retorno do método que obtém o bem.
		 */
		private function retornoObterBem(evento:ResultEvent):void {
			_registro = evento.result.dados.bem;
			
			if(_registro != null) {
				abrirJanela(_registro);
				MostraCursor.removeBusyCursor();
			}
		}
		
		/**
		 * Ao excluir um registro, remove a visibilidade do botão de consultas de posses
		 * para que ele seja exibido apenas ao selecionar um registro válido.
		 */
		protected override function excluirClicado(evento:MouseEvent = null):void {
			super.excluirClicado(evento);
			this.botoesAdicionais.botaoConsultarPosse.enabled = false;
			//this.botoesAdicionais.botaoDesbloquearBem.enabled = false;
		}
		
		/**
		 * Ao retornar para a tela principal, volta a exibir os botões adicionais.
		 */
		protected override function exibirTelaListar(evento:Event = null):void {
			super.exibirTelaListar(evento);
			habilitarBotoesAdicionais(true);
		}
		
		/**
		 * Habilita/Desabilita a visibilidade dos botões adicionais.
		 */
		private function habilitarBotoesAdicionais(valor:Boolean):void {
			this.botoesAdicionais.visible = this.botoesAdicionais.includeInLayout = valor;
		}
		
		/**
		 * Verifica se os botões de inclusão de bens internos podem ser exibidos, baseado na lista de registros.
		 * 
		 * Obs: os botões só são exibidos caso a pessoa selecionada não possua nenhum bem cadastrado.
		 */
		private function deveExibirBotoesPatrimonio():Boolean {
			var lista:ArrayCollection = telaLista.obterGrid().dataProvider as ArrayCollection;
			if(lista != null && lista.length > 0) {
				return false;
			}
			return true;
		}
		
		/**
		 * Verifica se o botã de desbloqueio de bens pode ser exibido.
		 * 
		 * Obs: O botão só será exibido se o bem estiver bloqueado por algum sistema.
		 */
		private function deveExibirBotaoDesbloquearBem(objeto:DadosListagemBemVO = null):Boolean {
			if(objeto == null) {
				objeto = itemLista as DadosListagemBemVO;
			}
			
			if(objeto != null && (objeto.status == "Bloqueado" || !FlexUtil.isTextoVazioOuNulo(objeto.bloqueadoPor))) {
				return true;
			}
			return false;
		}
		
		/**
		 * Verifica a existêcia de um bem interno na lista de bens cadastrados.
		 */
		private function verificarExistenciaBemInterno():Boolean {
			var lista:ArrayCollection = telaLista.obterGrid().dataProvider as ArrayCollection;
			if(lista != null) {
				for each (var objeto:DadosListagemBemVO in lista) {
					if(objeto.bemInterno) {
						return true;
					}
				}
			}
			return false;
		}
		
		/**
		 * Abre a janela de manter bens.
		 */
		private function abrirJanela(registro:BemVO = null):void {
			this._registro = registro;
			
			_janela = new Janela();
			//_janela.addEventListener("abrir", aoAbrirJanelaModo);
			_janela.addEventListener(Janela.FECHAR_JANELA, aoFecharJanela);
			_janela.width = 815;
			_janela.height = 650;
			
			_telaPesquisa = obterTelaListagem();
			(_telaPesquisa as ListaCadastroView).addEventListener(FlexEvent.CREATION_COMPLETE, aoAbrirJanelaModo);
			
			_janela.title = _tipoClassificacaoBem.descricao;
			_janela.removeAllChildren();
			_janela.addChild(_telaPesquisa as DisplayObject);
			_telaPesquisa.limpar();
			_telaPesquisa.verificarFechamentoTelaEdicao();
			
			_janela.abrir(Application.application as DisplayObject, true, true);
		}
		
		/**
		 * Notifica a janela dependendo do tipo de exibição selecionado.
		 */
		private function aoAbrirJanelaModo(evento:Event):void {
			if(!_modoVisualizacao) {
				if(_registro == null) {
					_telaPesquisa.abrirModoInclusao();
				} else {
					_telaPesquisa.abrirModoEdicao(_registro as BemVO);
				}
			}
		}
		
		private function aoFecharJanela(evento:Event = null):void {
			telaLista.obterLista();
			registroSelecionado();
			telaLista.removerFocoItensSelecionados();
		}
		
		/**
		 * obtém a tela de Pesquisa
		 */
		public function obterTelaListagem():ITelaCrudBem{
			if(TipoClassificacaoBemEnum.BEM_IMOVEL.equals(_tipoClassificacaoBem)) {
				_telaPesquisa = new ListarBemImovel();
			} else {
				_telaPesquisa = new ListarBemMovel();
			}
			
			_telaPesquisa.configurarDestino(destino);
			
			return _telaPesquisa;
		}
		
		protected override function exclusaoConfirmada(evt:Event):void {
			MostraCursor.setBusyCursor(MENSAGEM_EXCLUIR_DADOS, Application.application, MostraCursor.CURSOR_EXCLUIR);
			
			var registro:DadosListagemBemVO = itemLista as DadosListagemBemVO;
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.idBem = registro.idBem;
			dto.dados.bemInterno = registro.bemInterno;
			dto.dados.idBemPessoaCompartilhamento = registro.idBemPessoaCompartilhamento;
			
			servicoExcluir.getOperation("excluirDados").send(dto);
		}
		
		/**
		 * Retorno do método excluir.
		 */
		private function retornoExcluir(evento:ResultEvent):void {
			MostraCursor.removeBusyCursor();
			this.dispatchEvent(new Event(REGISTRO_EXCLUIDO));
			
			if(possuiLista){
				_telaLista.obterGrid().dataProvider.removeItemAt(_telaLista.obterGrid().selectedIndex);
				registroSelecionado();
			}
			
			verificarExibicaoBotoesLaterais();
		}
		
		//------------------------------------------------------------------------------
		// Configuração de destino dos serviços.
		//------------------------------------------------------------------------------
		
		/**
		 * Faz a configuração dos destinos dos serviços.
		 */
		protected override function configurarDestinosServicos(destino:DestinoVO):void {
			this.destino = destino;
			servico.configurarDestino(destino);
			servicoBem.configurarDestino(destino);
			servicoExcluir.configurarDestino(destino);
			servicoDesbloquearBem.configurarDestino(destino);
			telaLista.configurarDestino(destino);
			//telaEdicao.configurarDestinosServicos(destino);
		}
	}
}