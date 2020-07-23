package br.com.sicoob.capes.cadastrarTipoAnotacao {
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.Botao;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.cadastro.BarraBotoesFormularioCadastroView;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.componentes.input.Combo;
	import br.com.bancoob.dto.RequisicaoDTO;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.componentes.pti.ObjetoEvent;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.tipos.Booleano;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastrarTipoAnotacao.componentes.SelecionarCooperativas;
	import br.com.sicoob.capes.cadastrarTipoAnotacao.orgaoExterno.OrgaoExternoEdicao;
	import br.com.sicoob.capes.comum.enums.TipoCapturaEnum;
	import br.com.sicoob.capes.comum.util.FormatadorUtil;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.InstituicaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.CategoriaAnotacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.MapaTipoAnotacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PeriodicidadeAnotacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoAnotacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoCapturaVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoObservacaoAnotacaoVO;
	
	import flash.display.DisplayObject;
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.collections.ListCollectionView;
	import mx.core.Application;
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	/**
	 * Classe para tratamento de eventos da tela de edição de tipo de anotação.
	 * @author 
	 */
	public class TipoAnotacaoEdicao extends TipoAnotacaoEdicaoView {
		
		static public const CLASSE_SERVICO: String = "br.com.sicoob.capes.cadastro.fachada.TipoAnotacaoFachada";
		static private const OPERACAO_OBTER_DEFINICOES: String = "obterDefinicoes";
		public var servico: ServicoJava;
		
		private var orgaoexterno: OrgaoExternoEdicao = new OrgaoExternoEdicao();
		private var listaMapaTipoAnotacao:ListCollectionView = new ArrayCollection();
		
		private var janela:Janela = new Janela();
		private var janelaCooperativas:Janela = new Janela();
		
		private var selecionarCooperativas:SelecionarCooperativas = new SelecionarCooperativas();
		private var _instituicoes:ListCollectionView = new ArrayCollection();
		
		private static const NOME_JANELA:String = "ÓRGÃOS EXTERNOS";
		private static const TITULO_EXCLUSAO:String = "Erro ao excluir um órgão externo";
		private static const TEXTO_EXCLUSAO:String = "Por favor, selecione um registro na lista para a exclusão";
		
		private static const DESTINO_CAPES:String = "destinoCAPES";
		
		public function TipoAnotacaoEdicao() {
			super();
			registerClassAlias("br.com.sicoob.capes.comum.negocio.vo.InstituicaoVO", InstituicaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoAnotacao", TipoAnotacaoVO);
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		//**************
        // Eventos:
        //**************
        protected override function init(event: FlexEvent): void {
            super.init(event);
			servico = ServicoJavaUtil.getServico(CLASSE_SERVICO, "Obtendo definições...", ResultEvent.RESULT, retornoObterDefinicoes);
			
			var botaoAjuda:Botao = BarraBotoesFormularioCadastroView(this.barraBotoes).btnAjuda;
			botaoAjuda.visible = botaoAjuda.includeInLayout = false;
			
			botaoAdicionar.addEventListener(MouseEvent.CLICK, onBotaoAdicionarOrgaoExternoClicado);
			botaoExcluir.addEventListener(MouseEvent.CLICK, onBotaoExcluirOrgaoExternoClicado);
			orgaoexterno.addEventListener(OrgaoExternoEdicao.EVENTO_ADICIONAR_LISTA, onItemAdicionado);
			orgaoexterno.addEventListener(OrgaoExternoEdicao.EVENTO_FECHAR_JANELA, onFecharJanelaAcionado);
			botaoSelecionarCooperativas.addEventListener(MouseEvent.CLICK, selecionarCooperativasClicado);
			
			janelaCooperativas.addEventListener("abrir", abrirJanelaAcionado);
			janelaCooperativas.addEventListener(Janela.FECHAR_JANELA, fecharJanelaAcionado);
			
			rdbTipoAplicacao.addEventListener(Event.CHANGE, verificarCampoAnotarFilial);
			cmbTipoCaptura.addEventListener(Event.CHANGE, verificarTipoCaptura);
			
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], destino_recuperado);
		}
		
		private function destino_recuperado(destino:DestinoVO):void {
			this.destino = destino;
			servico.configurarDestino(destino);
			ServicoJava(servicoInclusao).configurarDestino(destino);
			ServicoJava(servicoEdicao).configurarDestino(destino);
			
			servico.getOperation(OPERACAO_OBTER_DEFINICOES).send(new RequisicaoReqDTO());
		}
	  	
	  	protected override function houveAlteracoes(): Boolean {
			var objetoAntigo: TipoAnotacaoVO = TipoAnotacaoVO(objeto);

			if (MODO_INCLUSAO == modo) {
				return (this.codTipoAnotacao.text != "")
					|| (this.descTipoAnotacao.text != "")
					|| (this.cmbCategoria.selectedItem != null)
					|| (this.cmbTipoCaptura.selectedItem != null)
					|| (this.cmbObservacao.selectedItem != null)
					|| (this.cmbPeriodicidade.selectedItem != null)
					|| (this.rdbTipoAplicacao.selectedValue != FormatadorUtil.TIPO_APLICACAO_PESSOA_FISICA)
					|| (!this.chkRegistraQuantidade.selected)
					|| (!this.chkRegistraValor.selected)
					|| (!this.chkValidaUsoAnotacao.selected)
					|| (!this.chkAnotarFilial.selected)
					|| (!this.chkCompartilhar.selected)
					|| (!new Boolean(this.rdbGrupoAtivo.selectedValue)); 
			} else if (MODO_EDICAO == modo) {
				return (objetoAntigo.codTipoAnotacao !=	this.codTipoAnotacao.valor)
					|| (objetoAntigo.descTipoAnotacao.toUpperCase() != this.descTipoAnotacao.text.toUpperCase())
					|| (isValorComboAlterado(this.cmbCategoria,	objetoAntigo.categoriaAnotacao))
					|| (isValorComboAlterado(this.cmbTipoCaptura, objetoAntigo.tipoCaptura))
					|| (isValorComboAlterado(this.cmbObservacao, objetoAntigo.tipoObservacaoAnotacao))
					|| (isValorComboAlterado(this.cmbPeriodicidade,objetoAntigo.periodicidadeAnotacao))
					|| (objetoAntigo.idTipoAplicacao != Number(this.rdbTipoAplicacao.selectedValue))
					|| (objetoAntigo.registraQuantidade.valor != this.chkRegistraQuantidade.selected)
					|| (objetoAntigo.registraValor.valor != this.chkRegistraValor.selected)
					|| (objetoAntigo.anotarFilial.valor != this.chkAnotarFilial.selected)
					|| (objetoAntigo.validaUsoAnotacao.valor != this.chkValidaUsoAnotacao.selected)
					|| (objetoAntigo.compartilhar.valor != this.chkCompartilhar.selected)
					|| (objetoAntigo.ativo.valor !=	new Boolean(this.rdbGrupoAtivo.selectedValue));
			}
			return false;
		}
		
		override protected function limparFormIncluir(): void {
			this.cmbCategoria.selectedIndex = 0;
			this.cmbPeriodicidade.selectedIndex = 0;
			this.cmbTipoCaptura.selectedIndex = 0;
			this.cmbObservacao.selectedIndex = 0;
			this.codTipoAnotacao.text = "";
			this.descTipoAnotacao.text = ""; 
			this.rdbTipoAplicacao.selectedValue = FormatadorUtil.TIPO_APLICACAO_PESSOA_FISICA;						
			this.chkRegistraQuantidade.selected = false;
			this.chkRegistraValor.selected = false;
			this.rdbGrupoAtivo.selectedValue = true;
			this.gridMapaTipoAnotacao.dataProvider = new ArrayCollection();
			
			this.rotuloAnotarFilial.enabled = false;
			this.chkAnotarFilial.selected = false;
			this.chkAnotarFilial.enabled = false;
			this.rotuloValidarUsoAnotacao.enabled = false;
			this.chkValidaUsoAnotacao.selected = false;
			this.chkValidaUsoAnotacao.enabled = false;
			this.rotuloCompartilhar.selectable = false;
			this.chkCompartilhar.enabled = false;
			this.botaoSelecionarCooperativas.enabled = false;
			this.listaMapaTipoAnotacao = new ArrayCollection();
		}
		
		override protected function preencherCampos():void {
			var vo: TipoAnotacaoVO = TipoAnotacaoVO(objeto);
			this.cmbCategoria.selectedItem = vo.categoriaAnotacao;
			this.cmbPeriodicidade.selectedItem = vo.periodicidadeAnotacao;
			this.cmbTipoCaptura.selectedItem = vo.tipoCaptura;
			this.cmbObservacao.selectedItem = vo.tipoObservacaoAnotacao;
			this.codTipoAnotacao.valor = vo.codTipoAnotacao;
			this.descTipoAnotacao.text = vo.descTipoAnotacao.toUpperCase();
			this.chkRegistraQuantidade.selected = vo.registraQuantidade.valor;
			this.chkRegistraValor.selected = vo.registraValor.valor;
			this.chkValidaUsoAnotacao.selected = vo.validaUsoAnotacao.valor;
			this.rdbTipoAplicacao.selectedValue = vo.idTipoAplicacao;
			this.rdbGrupoAtivo.selectedValue = vo.ativo.valor;
			this.listaMapaTipoAnotacao = vo.mapasTipoAnotacao;
			this.gridMapaTipoAnotacao.dataProvider = listaMapaTipoAnotacao;
			this.chkAnotarFilial.selected = vo.anotarFilial.valor;
			this.chkCompartilhar.selected = vo.compartilhar.valor;
			
			obterInstituicoes();
		}

		override protected function formularioAdicionado(evento:FlexEvent):void {
			super.formularioAdicionado(evento);
						
			if ((initialized)  && (modo != MODO_VISUALIZACAO)) {
				verificarCampoAnotarFilial();
				verificarTipoCaptura();
			}
		}
		
		override protected function montarDto(): RequisicaoDTO {
			var vo: TipoAnotacaoVO;
			
			if(modo == MODO_INCLUSAO){
				vo = new TipoAnotacaoVO();
			}else {
				vo = TipoAnotacaoVO(objeto);
			}
			
			vo.ativo = new Booleano(this.rdbGrupoAtivo.selectedValue);
			vo.categoriaAnotacao = CategoriaAnotacaoVO(this.cmbCategoria.selectedItem);
			vo.codTipoAnotacao = this.codTipoAnotacao.valor;
			vo.descTipoAnotacao = this.descTipoAnotacao.text;
			vo.idTipoAplicacao = Number(this.rdbTipoAplicacao.selectedValue);
			vo.periodicidadeAnotacao = PeriodicidadeAnotacaoVO(this.cmbPeriodicidade.selectedItem);
			vo.registraQuantidade = new Booleano(this.chkRegistraQuantidade.selected);
			vo.registraValor = new Booleano(this.chkRegistraValor.selected);
			vo.validaUsoAnotacao = new Booleano(this.chkValidaUsoAnotacao.selected);
			vo.tipoCaptura = TipoCapturaVO(this.cmbTipoCaptura.selectedItem);
			vo.tipoObservacaoAnotacao = TipoObservacaoAnotacaoVO(this.cmbObservacao.selectedItem);
			vo.compartilhar = new Booleano(this.chkCompartilhar.selected);
			
			vo.mapasTipoAnotacao = listaMapaTipoAnotacao;
			
			if(selecionarCooperativas != null && chkValidaUsoAnotacao.visible && chkValidaUsoAnotacao.selected){
				vo.instituicoes = selecionarCooperativas.instituicoes;
			}
			
			vo.anotarFilial = chkAnotarFilial.visible ? new Booleano(this.chkAnotarFilial.selected) : new Booleano(false);
			
			// Monta o DTO, com o VO, para retorno
			var dto: RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.tipoAnotacao = vo;
			return dto;
		}
		
		/**
		 * Retorno dos dados da definição.
		 *  
		 * @param event O evento com o resultado do método.
		 */
		private function retornoObterDefinicoes(event: ResultEvent): void {
			this.cmbCategoria.dataProvider = event.result.dados.listaCategoria;
			this.cmbPeriodicidade.dataProvider = event.result.dados.listaPeriodicidade;
			this.cmbTipoCaptura.dataProvider = event.result.dados.listaTipoCaptura;
			this.cmbObservacao.dataProvider = event.result.dados.listaObservacao;
			
			if ((initialized)  && (modo != MODO_INCLUSAO)) {
	       		preencherCampos();
	      	} else if ((initialized)  && (modo == MODO_INCLUSAO)) {
				limparFormIncluir();
			}
		}
		
		override protected function configurarEstadoComponente(componente : UIComponent, habilitar : Boolean):void{
			if (componente.name == "codTipoAnotacao") {
				habilitar = (MODO_INCLUSAO == modo);
			}
			super.configurarEstadoComponente(componente, habilitar);
		}
		
		private function isValorComboAlterado(combo : Combo, vo : Object) : Boolean {
			var alterado : Boolean;
			if (combo.isSelecionado()) {
				var itemSelecionado : Object = combo.selectedItem;
				var identificador : String = combo.identificadorItem;
				alterado = vo[identificador] != itemSelecionado[identificador];
			} else {
				alterado = vo != null;
			}
			return alterado;
		}
		
		private function onBotaoAdicionarOrgaoExternoClicado(evento:MouseEvent=null):void{
			janela.title = NOME_JANELA;
			janela.addChild(orgaoexterno);
			janela.abrir(DisplayObject(Application.application), true);
		}
		
		private function onItemAdicionado(evento:ObjetoEvent): void{
			listaMapaTipoAnotacao.addItem(evento.objeto);
			this.gridMapaTipoAnotacao.dataProvider = listaMapaTipoAnotacao;
		}
		
		private function onFecharJanelaAcionado(evento:ObjetoEvent):void {
			janela.fecharJanela();
		}
		
		private function onBotaoExcluirOrgaoExternoClicado(evento:MouseEvent=null):void {
			var objeto:MapaTipoAnotacaoVO = this.gridMapaTipoAnotacao.selectedItem as MapaTipoAnotacaoVO;
			
			if(objeto == null){
				Alerta.show(TEXTO_EXCLUSAO, TITULO_EXCLUSAO, Alerta.ALERTA_ERRO);
			}else{
				listaMapaTipoAnotacao.removeItemAt(this.gridMapaTipoAnotacao.selectedIndex);
				this.gridMapaTipoAnotacao.dataProvider = listaMapaTipoAnotacao;
			}
		}
		
		private function selecionarCooperativasClicado(evento:MouseEvent = null):void {
			selecionarCooperativas.configurar_destino(this.destino);
			janelaCooperativas.title = "Selecionar Cooperativas";
			janelaCooperativas.addChild(selecionarCooperativas);
			janelaCooperativas.abrir(DisplayObject(Application.application), true);
		}
		
		private function abrirJanelaAcionado(evento:Event = null):void {
			selecionarCooperativas.carregarInstituicoes();
		}
		
		private function fecharJanelaAcionado(evento:Event = null):void {

		}
		
		private function verificarCampoAnotarFilial(evento:Event = null):void {
			var isAnotarFilial:Boolean = new Boolean((FormatadorUtil.TIPO_APLICACAO_PESSOA_JURIDICA == rdbTipoAplicacao.selectedValue) || (FormatadorUtil.TIPO_APLICACAO_AMBAS == rdbTipoAplicacao.selectedValue));
			this.rotuloAnotarFilial.enabled = isAnotarFilial;
			this.chkAnotarFilial.enabled = isAnotarFilial;
		}
		
		private function verificarTipoCaptura(evento:Event = null): void {
			var isExibir:Boolean = new Boolean(false);
			
			if(cmbTipoCaptura.selectedItem){
				isExibir = new Boolean(TipoCapturaEnum.MANUAL.codigo == cmbTipoCaptura.selectedItem.idTipoCaptura);
			}
			
			verificarCooperativasAnotacao(isExibir);
			verificarCompartilhar(isExibir);
		}
		
		private function verificarCooperativasAnotacao(isExibir:Boolean):void {
			this.rotuloValidarUsoAnotacao.enabled = isExibir;
			this.chkValidaUsoAnotacao.enabled = isExibir;
			this.botaoSelecionarCooperativas.enabled = isExibir;
			
			var vo:TipoAnotacaoVO = TipoAnotacaoVO(objeto);
			
			this.chkValidaUsoAnotacao.selected = vo != null && vo.cooperativasAnotacao != null && vo.cooperativasAnotacao.length > 0;
			//this.chkValidaUsoAnotacao.selected = false;
			this.botaoSelecionarCooperativas.visible = this.botaoSelecionarCooperativas.includeInLayout = (chkValidaUsoAnotacao.visible && chkValidaUsoAnotacao.selected);
			
			if(selecionarCooperativas != null) {
				selecionarCooperativas.limpar();
			}
		}
		
		private function verificarCompartilhar(isExibir:Boolean):void {
			this.rotuloCompartilhar.enabled = isExibir;
			this.chkCompartilhar.enabled = isExibir;
		}
		
		private function obterInstituicoes():void {
			MostraCursor.setBusyCursor("Obtendo instituições...", Application.application, MostraCursor.CURSOR_PROGRESSO);
			servico = ServicoJavaUtil.getServico(CLASSE_SERVICO, "Obtendo instituições...", ResultEvent.RESULT, retornoObterInstituicoes);
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.tipoAnotacao = this.objeto as TipoAnotacaoVO;
			servico.getOperation("obterInstituicoes").send(dto);
		}
		
		private function retornoObterInstituicoes(evento:ResultEvent):void {
			var tipoAnotacao:TipoAnotacaoVO = evento.result.dados.tipoAnotacao;
			if(tipoAnotacao != null && tipoAnotacao.instituicoes != null){
				adicionarInstituicoes(tipoAnotacao.instituicoes);
			}
			selecionarCooperativas.instituicoes = _instituicoes;
			MostraCursor.removeBusyCursor();
		}
		
		private function adicionarInstituicoes(lista:ListCollectionView):void {
			for each(var instituicao:InstituicaoVO in lista) {
				if(!instEstaNaLista(instituicao)){
					_instituicoes.addItem(instituicao);
				}
			}
		}
		
		private function instEstaNaLista(instituicao:InstituicaoVO):Boolean {
			for each (var inst:InstituicaoVO in _instituicoes){
				if(instituicao.idInstituicao == inst.idInstituicao){
					return true;
				}
			}
			return false;
		}
	}
}