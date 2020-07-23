package br.com.sicoob.capes.corporativo.componentes.procurarBens {
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.componentes.eventos.EventoBarraBotoes;
	import br.com.bancoob.componentes.eventos.SelecaoEvent;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.componentes.pti.ObjetoEvent;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.sisbr.portal.SISBRLoader;
	import br.com.bancoob.util.StringUtils;
	import br.com.bancoob.util.reflexao.ReflectionUtils;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.enums.SituacaoRegistroEnum;
	import br.com.sicoob.capes.comum.enums.TipoClassificacaoBemEnum;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.LocalidadeVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoLogradouroVO;
	import br.com.sicoob.capes.comum.vo.entidades.UFVO;
	import br.com.sicoob.capes.comum.vo.entidades.UnidadeMedidaVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.BemImovelVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.BemMovelVO;
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
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.bemImovel.ListarBemImovel;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.bemMovel.ListarBemMovel;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.util.ConstantesBens;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.vo.BemProprietarioVO;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.vo.BemVO;
	
	import flash.display.DisplayObject;
	import flash.events.Event;
	import flash.events.FocusEvent;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.core.Application;
	import mx.core.Container;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.StringUtil;
	
	/**
	 * Componente da tela de associação de bens.
	 * 
	 * Preparado para abrir e pesquisar bens móveis ou imóveis.
	 * 
	 * @author bruno.carneiro
	 */
	public class PesquisarBem extends PesquisarBemView {
		
		public static const EVENTO_OBJETO_INCLUIDO:String = "EVENTO_OBJETO_INCLUIDO_BEM";
		public static const EVENTO_FECHAR_JANELA:String = "EVENTO_FECHAR_JANELA_BEM";
		
		private static const CLASSE_SERVICO:String = "br.com.sicoob.capes.corporativo.fachada.ProcurarBemFachada";
		private static const OPERACAO_OBTER_POR_CODIGO:String = "obterPorCodigo";
		private static const MENSAGEM_REALIZANDO_OPERACAO:String = "Realizando a operação...";
		private static const EVENTO_DESTINO_RECUPERADO:String = "destinoRecuperado";
		private static const DESTINO_CAPES:String = "servicosJavaCapes";
		private static const TITULO_JANELA_CONSULTAR_TIPO_CLASSIFICACAO_BEM:String = "Selecionar tipo de classificação do bem";
		
		private var _janela:Janela;
		private var _destino:DestinoVO;
		
		private var _telaPesquisa:ITelaCrudBem;
		private var _tipoClassificacaoBem:int;
		
		private var _valorAtual:String = "";
		private var _registro:BemVO;
		
		private var _abrirJanelaModoEdicao:Boolean = false;
		private var _abrirJanelaModoInclusao:Boolean = false;
		
		private var sisbrLoader:SISBRLoader;
		
		private var servicoPesquisaCodigo:ServicoJava;
		
		/**
		 * Construtor
		 */
		public function PesquisarBem() {
			super();
			
			servicoPesquisaCodigo = ServicoJavaUtil.getServico(CLASSE_SERVICO, MENSAGEM_REALIZANDO_OPERACAO, ResultEvent.RESULT, retornoPesquisaPorCodigo);
			
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
			registerClassAlias("br.com.sicoob.capes.api.negocio.vo.BemVO", BemVO);
			registerClassAlias("br.com.sicoob.capes.api.negocio.vo.BemImovelVO", BemImovelVO);
			registerClassAlias("br.com.sicoob.capes.api.negocio.vo.BemMovelVO", BemMovelVO);
			registerClassAlias("br.com.sicoob.capes.api.negocio.vo.BemProprietarioVO", BemProprietarioVO);
			
			addEventListener(FlexEvent.CREATION_COMPLETE, iniciar);
		}
		
		/**
		 * Método chamado após a construção da classe
		 */
		private function iniciar(evento:FlexEvent):void {
			inicializarServicos();
			
			textoCodigo.restrict = "0123456789";
			
			textoCodigo.addEventListener(FocusEvent.FOCUS_OUT, aoPerderFocoCampoCodigo);
			botaoPesquisar.addEventListener(MouseEvent.CLICK, abrirJanelaPesquisa);
			
			botaoIncluir.addEventListener(MouseEvent.CLICK, abrirJanelaInclusao);
			botaoEditar.addEventListener(MouseEvent.CLICK, abrirJanelaEdicao);
		}
		
		/**
		 * Executa a consulta quando o campo código perde o foco. 
		 */
		private function aoPerderFocoCampoCodigo(event:FocusEvent):void {
			if (StringUtil.trim(textoCodigo.text).length == 0) {
				limpar();
			} else {
				pesquisar();
			}
		}
		
		/**
		 * Faz a limpeza dos campos.
		 */
		public function limpar():void {
			textoCodigo.enabled = true;
			botaoPesquisar.enabled = true;
			_valorAtual = "";
			_registro = null;
			atualizarCampos();
			notificarSelecao();

			verificarExibicaoBotoes();
		}
		
		/**
		 * Verifica se os os botões adicionais devem ser exibidos.
		 */
		private function verificarExibicaoBotoes():void {
			botaoIncluir.visible = botaoIncluir.includeInLayout = _registro == null;
			botaoEditar.visible = botaoEditar.includeInLayout = _registro != null;
		}
		
		/**
		 * Atualiza as informações dos campos.
		 */
		private function atualizarCampos():void {
			if (_registro != null) {
				this.textoDescricao.text = StringUtils.trim(formatarDescricao(String(ReflectionUtils.recuperarPropriedade(_registro, "descricao"))));
				if(!isNaN(_registro.idBem)) {
					this.textoCodigo.text = StringUtils.trim(String(ReflectionUtils.recuperarPropriedade(_registro, "idBem")));
				}
			} else {
				this.textoDescricao.text = "";
				this.textoCodigo.text = "";
			}
		}
		
		/**
		 * Formata a descrição dos campos de acordo com o espaço disponível para exibição.
		 */
		private function formatarDescricao(descricao:String):String {
			var texto:String = descricao;
			var tamanhoDisponivel:int = this.width - (textoCodigo.width + botaoPesquisar.width + 20); 
			
			if (texto != null) {
				// Se o tamanho do texto for maior que o tamanho na tela, devemos truncá-lo.
				while (textoDescricao.measureText(texto).width >= tamanhoDisponivel && texto.length > 0) {
					texto = texto.substring(0, texto.length - 1);
				}
			}else {
				texto = "";
			}
			return texto;
		}
		
		/**
		 * Notifica que o registro foi selecionado.
		 */
		private function notificarSelecao():void {
			var evento:SelecaoEvent = new SelecaoEvent();
			evento.objeto = this._registro;
			dispatchEvent(evento);
		}
		
		/**
		 * Abre a janela para a pesquisa dos registro.
		 */
		private function abrirJanelaPesquisa(evento:MouseEvent = null):void {
			if(!verificarAberturaJanela()) {
				exibirMensagemTipoBemNaoSelecionado();
				return;
			}
			abrirJanela();
		}
		
		/**
		 * Abre a janela no modo de inclusão.
		 */
		private function abrirJanelaInclusao(evento:MouseEvent = null):void {
			if(!verificarAberturaJanela()) {
				exibirMensagemTipoBemNaoSelecionado();
				return;
			}
			
			abrirJanela(true);
		}
		
		/**
		 * Abre a janela no modo de edição.
		 */
		private function abrirJanelaEdicao(evento:MouseEvent = null):void {
			if(!verificarAberturaJanela()) {
				exibirMensagemTipoBemNaoSelecionado();
				return;
			}
			abrirJanela(false, true);
		}
		
		/**
		 * Abre a janela de manter bens.
		 */
		private function abrirJanela(abrirJanelaModoInclusao:Boolean = false, abrirJanelaModoEdicao:Boolean = false):void {
			_abrirJanelaModoInclusao = abrirJanelaModoInclusao;
			_abrirJanelaModoEdicao = abrirJanelaModoEdicao;
			
			_janela = new Janela();
			_janela.addEventListener("abrir", aoAbrirJanelaModo);
			_janela.width = 815;
			_janela.height = 650;
			
			_janela.title = obterDescricaoBem();
			_janela.removeAllChildren();
			_telaPesquisa.limpar();
			_telaPesquisa.verificarFechamentoTelaEdicao();
			_telaPesquisa.configurarDestino(_destino);
			
			// Adiciona o módulo carregado à janela e exibe a mesma.
			_janela.addChild(_telaPesquisa as DisplayObject);
			_janela.abrir(Application.application as DisplayObject, true, true);
		}
		
		/**
		 * Notifica a janela dependendo do tipo de exibição selecionado.
		 */
		private function aoAbrirJanelaModo(evento:Event):void {
			if(_abrirJanelaModoInclusao) {
				_telaPesquisa.abrirModoInclusao();
			}else if(_abrirJanelaModoEdicao){
				_telaPesquisa.abrirModoEdicao(_registro != null ? _registro as BemVO : null);
			}
		}
		
		/**
		 * Realiza a pesquisa do bem pelo código.
		 */
		public function pesquisar():void {
			if (realizaConsulta()) {
				_valorAtual = textoCodigo.text;
				pesquisarPorCodigo(textoCodigo.valor);
			}
		}
		
		/**
		 * @inheritDoc
		 */
		public function pesquisarPorCodigo(codigo:Number):void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.idBem = codigo;
			servicoPesquisaCodigo.getOperation(OPERACAO_OBTER_POR_CODIGO).send(dto);
		}
		
		/**
		 * Retorno do método de pesquisa por código.
		 */
		private function retornoPesquisaPorCodigo(evento:ResultEvent):void {
			var bem:BemVO = evento.result.dados.bem;
			
			if(bem != null){
				_registro = bem;
			}
			
			atualizarCampos();
		}
		
		/**
		 * Carrega o registro de acordo com o id do bem informado.
		 */
		public function carregarRegistro(idBem:Number):void {
			_valorAtual = String(idBem);
			pesquisarPorCodigo(idBem);
		}
		
		/**
		 * Obtém o registro incluído na janela.
		 */
		private function obterRegistroIncluido(evento:ObjetoEvent = null):void {
			if (evento.objeto != null) {
				_registro = evento.objeto as BemVO;
				_valorAtual = String(_registro.idBem);
			} else {
				limpar();
			}
			
			verificarExibicaoBotoes();
			botaoEditar.visible = botaoEditar.includeInLayout = _registro != null;
			
			atualizarCampos();
		}
		
		/**
		 * Notifica a seleção do registro após a verificação dos compartilhamentos
		 */
		private function retornoVerificacaoCompartilhamento(evento:Event = null):void {
			atualizarCampos();
			notificarSelecao();
		}
		
		/**
		 * Verifica se a consulta pode ser realizada.
		 */
		private function realizaConsulta():Boolean {
			if(_valorAtual == textoCodigo.text){
				return false;
			}
			return true;
		}
		
		/**
		 * Fecha a janela de pesquisa.
		 */
		private function fecharJanelaPesquisa(event:EventoBarraBotoes = null):void {
			fecharJanela();
		}
		
		/**
		 * Fecha a janela.
		 */
		private function fecharJanela(evento:Event = null):void {
			if(_janela != null){
				_janela.fecharJanela();
			}
		}
		
		/**
		 * Método para recuperar o registro selecionado.
		 */
		public function obterRegistro():BemVO {
			return _registro;
		}
		
		/**
		 * Método de acesso para configurar o tipo de bem a ser utilizado pelo componente.
		 */
		public function set tipoClassificacaoBem(valor:int):void {
			this._tipoClassificacaoBem = valor;
			
			if(TipoClassificacaoBemEnum.BEM_IMOVEL.codigo == valor) {
				_telaPesquisa = new ListarBemImovel();
			} else {
				_telaPesquisa = new ListarBemMovel();
			}
			
			(_telaPesquisa as Container).addEventListener(EVENTO_OBJETO_INCLUIDO, obterRegistroIncluido);
			(_telaPesquisa as Container).addEventListener(EVENTO_FECHAR_JANELA, fecharJanelaPesquisa);
		}
		
		/*private function carregarTela():void {
			// Obtém o loader, que carregará o módulo de cadastro.
			sisbrLoader = obterSisbrLoader();
			sisbrLoader.x = -10;
			sisbrLoader.y = -15;
			sisbrLoader.url = "/capes/swf/bemimovel.swf";
			sisbrLoader.destino = _destino;
			sisbrLoader.loadModule();
			sisbrLoader.addEventListener(SISBRModuleEvent.CARREGADO, sisbrLoaderCarregado);
		}*/
		
		/**
		 * Adiciona o listener ao módulo de cadastro, para saber quando a pessoa foi incluida.
		 */
		/*private function sisbrLoaderCarregado(evento:SISBRModuleEvent):void {
			var modulo:DisplayObject = sisbrLoader.child as DisplayObject;
			if(modulo != null) {
				_telaPesquisa = modulo as Object;
				_telaPesquisa.configurarDestino(_destino);
				(_telaPesquisa as Container).addEventListener(EVENTO_OBJETO_INCLUIDO, obterRegistroIncluido);
				(_telaPesquisa as Container).addEventListener(EVENTO_FECHAR_JANELA, fecharJanelaPesquisa);
			}
		}*/
		
		/**
		 * Configura o componente para o modo de edição.
		 */
		public function configurarModoEdicao():void {
			botaoPesquisar.enabled = false;
			textoCodigo.enabled = false;
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
		
		private function verificarAberturaJanela():Boolean {
			return _telaPesquisa != null;
		}
		
		private function exibirMensagemTipoBemNaoSelecionado():void {
			Alerta.show("Por favor, selecione o tipo de bem antes de continuar.", "Selecione um tipo de bem", Alerta.ALERTA_INFORMACAO);
		}
		
		private function obterDescricaoBem():String {
			if(!isNaN(_tipoClassificacaoBem)) {
				if(ConstantesBens.CODIGO_BEM_IMOVEL == _tipoClassificacaoBem) {
					return ConstantesBens.NOME_BEM_IMOVEL;
				} else if (ConstantesBens.CODIGO_BEM_MOVEL == _tipoClassificacaoBem) {
					return ConstantesBens.NOME_BEM_MOVEL;
				}
			}
			return null;
		}
		
		/*private function verificarTipoClassificacaoEscolhido():void {
			if(isNaN(_tipoClassificacaoBem)) {
				var telaEscolherTipoClassificacaoBem:EscolherTipoClassificacaoBem = new EscolherTipoClassificacaoBem();
				telaEscolherTipoClassificacaoBem.addEventListener(EscolherTipoClassificacaoBem.EVENTO_TIPO_CLASSIFICACAO_SELECIONADO, tipoClassificacaoSelecionado);
				telaEscolherTipoClassificacaoBem.configurarDestino(_destino);
				
				var janela:Janela = new Janela();
				janela.title = TITULO_JANELA_CONSULTAR_TIPO_CLASSIFICACAO_BEM;
				janela.width = 310;
				janela.height = 110;
				
				janela.addChild(telaEscolherTipoClassificacaoBem);
				janela.abrir(Application.application as DisplayObject, true, true);
			}
		}*/
		
		/**
		 * Abre a tela de inclusão
		 */
		/*private function tipoClassificacaoSelecionado(evento:ObjetoEvent):void {
			var tipo:TipoClassificacaoBemVO = evento.objeto as TipoClassificacaoBemVO;
			tipoClassificacaoBem = tipo.codigo;
		}*/
		
		//--------------------------------------------------------------------------
		//  Configuração de destino dos serviços.
		//--------------------------------------------------------------------------
		
		private function inicializarServicos():void {
			if(this._destino == null || _destino.tipo == DestinoVO.CANAL_NET) {
				PortalModel.portal.obterDefinicoesDestino(DESTINO_CAPES, configurarDestino);
			} else {
				configurarDestino();
			}
		}
		
		public function configurarDestino(destino:DestinoVO = null):void {
			if (destino != null) {
				this._destino = destino;
				servicoPesquisaCodigo.configurarDestino(destino);
				dispatchEvent(new ObjetoEvent(EVENTO_DESTINO_RECUPERADO, destino));
			}
		}
	}
}