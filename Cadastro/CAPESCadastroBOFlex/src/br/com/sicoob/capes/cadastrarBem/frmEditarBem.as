package br.com.sicoob.capes.cadastrarBem{
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.input.Combo;
	import br.com.bancoob.componentes.validadores.ColecaoValidaveis;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.dto.ResultadoValidacaoDTO;
	import br.com.bancoob.util.IValidavel;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastrarBem.abas.*;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.VigenteVO;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.BemImovelVO;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.BemVO;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.SubTipoBemVO;
	import br.com.sicoob.capes.corporativo.componentes.plataformaatendimento.ProcuraClientePlataformaCAPES;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	import br.com.sicoob.capes.utils.ConversaoPessoaUtil;
	import br.com.sicoob.capes.utils.UploadDocGedUtil;
	import br.com.sicoob.capes.utils.plataformaatendimento.IEdicaoPlataformaAtendimentoCAPES;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCAPESBase;
	
	import flash.display.DisplayObject;
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	import mx.collections.IList;
	import mx.collections.ListCollectionView;
	import mx.core.Application;
	import mx.core.Container;
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	import mx.events.IndexChangedEvent;
	import mx.events.ListEvent;
	import mx.events.ValidationResultEvent;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.ObjectUtil;
	import mx.validators.Validator;
	
	public class frmEditarBem extends frmEditarBemView implements IEdicaoPlataformaAtendimentoCAPES {
		
		private static var CODIGO_TIPO_BEM_IMOVEL:Number = 1;
		
		[Bindable]
		private var bem:BemVO;
		private var registroBkp:BemVO = null;	
		private var _isRegistroBloqueado:Boolean = false;
		private var definicoesComponenteGED: ArrayCollection = null;
		
		private var pessoaProxy : PessoaPlataformaVO = ProcuraClientePlataformaCAPES.getPessoaSelecionada();
		private const ABA_DOCUMENTOS:Number = 5;
		
		/**
		 *	Serviços 
		 */
		public var servicoConsulta:ServicoJava;
		public var servicoSalvar:ServicoJava;
		public var servicoExcluir:ServicoJava;
		public var servicoDefinicao:ServicoJava;
		
		public var servicoSubTiposBem:ServicoJava;
		public var servicoDependenciasSubTipoBem:ServicoJava;
		
		/**
		 *  Operações 
		 */
		static private const OPERACAO_OBTER_DEFINICOES: String = "obterDefinicoes";
		static private const OPERACAO_INCLUIR_DADOS: String = "incluirDados";
		static private const OPERACAO_ALTERAR_DADOS: String = "alterarDados";
		static private const OPERACAO_EXCLUIR_BEM: String = "excluirDados";
		static private const OPERACAO_OBTER_BEM: String = "obterDados";
		
		static private const CLASSE_SERVICO: String = 
			"br.com.sicoob.capes.cadastro.fachada.bemantigo.BemFachada";
		
		//--------------------------------------------------------------------------
		//  Propriedades
		//--------------------------------------------------------------------------
		
		/**
		 *  Construtor.
		 */
		public function frmEditarBem()
		{
			super();
			
			servicoConsulta = ServicoJavaUtil.getServico(CLASSE_SERVICO);
			servicoConsulta.addEventListener(ResultEvent.RESULT, retornoCarregarRegistro);
			
			servicoDefinicao = ServicoJavaUtil.getServico(CLASSE_SERVICO);
			servicoDefinicao.addEventListener(ResultEvent.RESULT, retornoObterDefinicoes);
			
			servicoSalvar = ServicoJavaUtil.getServico(CLASSE_SERVICO);
			servicoSalvar.addEventListener(ResultEvent.RESULT, retornoSalvar);		
			
			servicoExcluir = ServicoJavaUtil.getServico(CLASSE_SERVICO);
			servicoExcluir.addEventListener(ResultEvent.RESULT, retornoExcluir);	
			servicoExcluir.addEventListener(FaultEvent.FAULT, retornoExcluirErro);
			
			servicoSubTiposBem = ServicoJavaUtil.getServico(CLASSE_SERVICO);
			servicoDependenciasSubTipoBem = ServicoJavaUtil.getServico(CLASSE_SERVICO);
			
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		//--------------------------------------------------------------------------
		//  Métodos
		//--------------------------------------------------------------------------
		private function init(evt:FlexEvent):void {
			
			navegacaoTab.addEventListener(IndexChangedEvent.CHANGE, onTabChange);
			
			obterAbaCadastro().servicoSubTiposBem = servicoSubTiposBem;
			obterAbaCadastro().servicoDependenciasSubTipoBem = servicoDependenciasSubTipoBem;
			
			obterAbaCadastro().cmbSubTipoBem.addEventListener(ListEvent.CHANGE, ativarAbas);
			obterAbaCadastro().cmbSituacaoBem.addEventListener(ListEvent.CHANGE, ativarAbaOnus);
			
			iterarAbas(function(aba:IAba):void{
				aba.init();
			});
			
			carregarDefinicoes();
			
			//Adiciona o evento ao trocar aba.			
			this.abaDocumentos.enabled = pessoaProxy.utilizaGedGft;
		}
		
		public override function dispose():void {
			super.dispose();
			obterAbaDocumento().dispose();
			navegacaoTab.removeEventListener(Event.CHANGE, onTabChange);
			navegacaoTab.removeAllChildren();
			navegacaoTab = null;
		}
		
		public function configurarDestinosServicos(destino:DestinoVO):void{
			
			this.destino = destino;
			servicoConsulta.configurarDestino(destino);
			servicoDefinicao.configurarDestino(destino);
			servicoSalvar.configurarDestino(destino);
			servicoExcluir.configurarDestino(destino);
			servicoSubTiposBem.configurarDestino(destino);
			servicoDependenciasSubTipoBem.configurarDestino(destino);
		}	
		
		private function onTabChange(event : Event) : void {
			iterarAbas(function(aba:IAba):void {
				if (aba is IAbaCrud) {
					IAbaCrud(aba).configurarBarraBotoesCrud();
				}
			});
			
			if (this.navegacaoTab.selectedIndex == ABA_DOCUMENTOS) {
				
				//Obtém as chaves de negócio
				var listaChavesNegocio:ArrayCollection = criarListaChavesNegocio();
				
				//Adiciona as chaves de negócio à instância do componente.
				this.abaDocumentos.chavesNegocio = listaChavesNegocio;
				
				//Atualiza as chaves de negócio dos arquivos que ainda não foram enviados.
				this.abaDocumentos.atualizarChavesNegocio(listaChavesNegocio);
			}
		}
		
		private function iterarAbas(funcao:Function): void {
			
			var abas:Array = navegacaoTab.getChildren();
			
			for each (var aba : IAba in abas) {
				funcao(aba);
			}
		}
		
		public function carregarRegistro(bem:Object):void
		{
			MostraCursor.setBusyCursor("Obtendo definições ...", 
				Application.application, MostraCursor.CURSOR_PROGRESSO);
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			
			dto.dados.bem = bem;
			
			servicoConsulta.getOperation(OPERACAO_OBTER_BEM).send(dto);
		}
		
		private function retornoCarregarRegistro(evt:ResultEvent):void {
			
			bem = evt.result.dados.bem;			 
			registroBkp = ObjectUtil.copy(bem) as BemVO;
			
			if (bem.subTipo.tipoBem.codigo == CODIGO_TIPO_BEM_IMOVEL) {
				atualizarCombo(obterAbaImovel().unidadeMedida, bem.subTipo.unidadesMedida);
				
				if (bem.subTipo.unidadesMedida != null && bem.subTipo.unidadesMedida.length > 0)
					this.obterAbaImovel().unidadeMedida.validarObrigatorio = true;
				else
					this.obterAbaImovel().unidadeMedida.validarObrigatorio = false;
			}
			
			atualizarCombo(obterAbaCadastro().cmbSubTipoBem, evt.result.dados.subTiposBem);
			atualizarCombo(obterAbaCadastro().cmbSituacaoBem, evt.result.dados.situacoesBem);
			
			preencherCampos();
			navegacaoTab.selectedIndex = 0;
			
			_novo = false;
			
			//Adiciona os documentos ao componente.
			this.abaDocumentos.carregarDocumentos(UploadDocGedUtil.criarListaDocumentos(bem.documentosComprobatorios));
			
			//Verifica se o registro está bloqueado para alteração.
			_isRegistroBloqueado = evt.result.dados.isRegistroBloqueadoAlteracao;
			
			this.dispatchEvent(new Event(REGISTRO_CARREGADO));	
		}
		
		public function preencherCampos():void {
			var abaDocumentos:DisplayObject = navegacaoTab.getChildByName("abaDocumentos");
			
			iterarAbas(function(aba:IAba):void{
				if(abaDocumentos == Container(aba)){
					Container(aba).enabled = pessoaProxy.utilizaGedGft;
				}else{
					aba.preencherCampos(bem);
					Container(aba).enabled = aba.ativarAba(bem.subTipo);
				}
			});
			
			obterAbaOnus().enabled = IAba(obterAbaOnus()).ativarAba(obterAbaCadastro().cmbSituacaoBem.selectedItem);
		}
		
		public function carregarDefinicoes(obj:Object=null):void {
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.codTipoPessoa = pessoaProxy.codTipoPessoa;  
			
			servicoDefinicao.getOperation(OPERACAO_OBTER_DEFINICOES).send(dto);
		}
		
		private function retornoObterDefinicoes(evt:ResultEvent):void {
			
			iterarAbas(function(aba:IAba):void{
				aba.carregarDefinicoes(evt);
			});
			
			definicoesComponenteGED = evt.result.dados.definicoesComponenteGED;
		}
		
		private function retornoSalvar(evt:ResultEvent):void {
			
			bem = evt.result.dados.bem;
			
			MostraCursor.removeBusyCursor();
			
			this.dispatchEvent(new Event(Modulo.REGISTRO_GRAVADO));	
		}
		
		public function novoRegistro():void
		{
			_novo = true;
			bem = new BemVO();
			registroBkp = new BemVO();
			limparFormIncluir();
		}
		
		public function gravarRegistro():void {
			
			/*if(!validarCampos())
				return;
			
			executarSeValido(gravarDados);*/
		}
		
		public function atualizarCamposRegistro():void{
			
			if (_novo) {
				if (isBemImovel()) {
					bem = new BemImovelVO();
				} else {
					bem = new BemVO();
				}
			}
			
			iterarAbas(function(aba:IAba):void{
				if (!Container(aba).enabled)
					aba.limparFormIncluir();
				aba.atualizarCamposRegistro(bem);
			}
			);
			
			bem.pessoaCompartilhamento = 
				ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(
					TelaPlataformaAtendimentoCAPESBase.getPessoaPlataforma());
			bem.documentosComprobatorios = this.abaDocumentos.obterDocumentosComprobatorios();
		}
		
		private function isBemImovel():Boolean {
			return  obterAbaCadastro() != null 
				&& obterAbaCadastro().cmbSubTipoBem != null 
				&& obterAbaCadastro().cmbSubTipoBem.selectedItem != null 
				&& obterAbaCadastro().cmbSubTipoBem.selectedItem.codTipoBem == CODIGO_TIPO_BEM_IMOVEL;
		}
		
		private function gravarDados():void {
			
			//Verifica as chaves de negócio dos documentos do componente GED com  as chaves atuais da tela.
			if(!this.abaDocumentos.validarDocumentosNaoEnviados()) {
				Alerta.show(UploadDocGedUtil.VALIDACAO_DOCUMENTOS_NAO_ENVIADOS, "ERRO!", Alerta.ALERTA_ERRO);
				return;
			}
			
			//Verifica se há algum documento que já foi enviado ao ged e está com as chaves de negócio diferentes das atuais.
			if(!this.abaDocumentos.verificarChavesNegocio(criarListaChavesNegocio())){
				Alerta.show(UploadDocGedUtil.VALIDACAO_DOCUMENTOS_DIVERGENTES, "ERRO!", Alerta.ALERTA_ERRO);
				return;
			}
			
			if(pessoaProxy.utilizaGedGft && this.abaDocumentos.obterDocumentosComprobatorios().length <= 0){
				Alerta.show(UploadDocGedUtil.VALIDACAO_DOCUMENTOS_NAO_SELECIONADOS, "ATENÇÃO", Alerta.ALERTA_PERGUNTA, null, onConfirmaGravarRegistro);
			}else{
				onConfirmaGravarRegistro();
			}
			
		}
		
		private function onConfirmaGravarRegistro(evt:Event = null):void {
			atualizarCamposRegistro();
			
			MostraCursor.setBusyCursor("Gravando dados ...", Application.application,
				MostraCursor.CURSOR_PROGRESSO);
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.bem = bem;
			
			if (_novo)
				servicoSalvar.getOperation(OPERACAO_INCLUIR_DADOS).send(dto);
			else
				servicoSalvar.getOperation(OPERACAO_ALTERAR_DADOS).send(dto);
		}
		
		public function excluirRegistro(bem:Object):void {
			/*MostraCursor.setBusyCursor("Excluindo Registro...", Application.application, 
				MostraCursor.CURSOR_EXCLUIR);
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.bem = bem;
			
			servicoExcluir.getOperation(OPERACAO_EXCLUIR_BEM).send(dto);*/
		}
		
		public function retornoExcluir(evt:ResultEvent):void {
			
			MostraCursor.removeBusyCursor();			
			this.dispatchEvent(new Event(REGISTRO_EXCLUIDO));		
		}
		
		public function retornoExcluirErro(evt:FaultEvent):void {
			MostraCursor.removeBusyCursor();
			//this.dispatchEvent(new Event(REGISTRO_EXCLUIDO));
		}	
		
		public function restaurarRegistro():void{
			bem = (ObjectUtil.copy(registroBkp) as BemVO);
		}
		
		public function verificarAlteracoes():Boolean {
			
			var retorno:Boolean = true;
			
			iterarAbas(function(aba:IAba):void{
				if (!aba.verificarAlteracoes(bem))
					retorno = false;
			});
			
			return retorno;
		}
		
		private function ativarAbas(evt:ListEvent):void {
			var subTipoBem:SubTipoBemVO = obterAbaCadastro().cmbSubTipoBem.selectedItem as SubTipoBemVO;
			
			iterarAbas(function(aba:IAba):void{
				var abaDocumentos:DisplayObject = navegacaoTab.getChildByName("abaDocumentos");
				if(abaDocumentos == Container(aba)){
					Container(aba).enabled = pessoaProxy.utilizaGedGft;
				}else{
					Container(aba).enabled = aba.ativarAba(subTipoBem);				
				}
			});
		}
		
		private function ativarAbaOnus(evt:ListEvent):void {
			obterAbaOnus().enabled = IAba(obterAbaOnus()).ativarAba(obterAbaCadastro().cmbSituacaoBem.selectedItem);
		}
		
		public function limparFormIncluir(event:FlexEvent=null):void{
			
			iterarAbas(function(aba:IAba):void {
				aba.limparFormIncluir();
			});
			
			bem.idBem = 0;
			registroBkp.idBem = 0;
			
			navegacaoTab.selectedIndex = 0;
		}
		[Deprecated]
		public function getEntidadeVigente():VigenteVO {
			return null;
		}
		
		
		/**
		 * Metodo sobrescrito para validar apenas os conponentes filhos de uma aba ativa.
		 *  
		 */
		override public function realizarValidacao():ResultadoValidacaoDTO {
			var resultado: ResultadoValidacaoDTO = new ResultadoValidacaoDTO();
			
			if (deveValidar) {
				
				var validaveisPaiEnabled:ColecaoValidaveis = new ColecaoValidaveis();
				
				for each (var validavel:IValidavel in colecaoValidaveis) { 
					// Valida apenas os componentes que são filhos de uma aba ativa
					if (validavel is UIComponent) {
						var componente:UIComponent = UIComponent(validavel);
						
						if (componente.parent != null) {// ABA
							if (Container(componente.parent).enabled) {
								validaveisPaiEnabled.addItem(validavel);
							}
						}		
					}
				}
				
				resultado = validaveisPaiEnabled.realizarValidacao();
				
				if (resultado.valido && validacoesAdicionais != null) {
					var resultados: Array = Validator.validateAll(validacoesAdicionais.toArray());
					var resultadoAdicional: ResultadoValidacaoDTO; 
					
					if (resultados.length != 0) {
						for each (var item: Object in resultados) {
							resultadoAdicional = converterResultado(item as ValidationResultEvent);
							if (resultadoAdicional != null) {
								resultado = resultado.merge(resultadoAdicional);
							}		
						} 
					}
				}
			}
			
			return resultado;
		}
		
		private function validarCampos():Boolean {
			var retorno:Boolean = obterAbaCadastro().validarCampos();
			if (!retorno) {
				navegacaoTab.selectedIndex = 0;
			}
			
			return retorno;
		}
		
		public function obterAbaImovel():abaImovelView {
			return navegacaoTab.getChildByName("abaImovel") as abaImovelView;
		}
		
		public function obterAbaCadastro():abaCadastroBemView {
			return navegacaoTab.getChildByName("abaCadastroBem") as abaCadastroBemView;
		}
		
		public function obterAbaDocumento():abaUploadGed{
			return navegacaoTab.getChildByName("abaDocumentos") as abaUploadGed;
		}
		
		public function obterAbaOnus():abaOnusView {
			return navegacaoTab.getChildByName("abaOnus") as abaOnusView;
		}
		
		public function atualizarCombo(combo:Combo, lista:ListCollectionView):void {
			
			if (combo.dataProvider == null)
				combo.dataProvider = new ArrayCollection();
			
			if (lista == null)
				lista = new ArrayCollection();
			
			ListCollectionView(combo.dataProvider).removeAll();
			adicionarItemOpcional(combo);
			cloneArrayCollection(ListCollectionView(combo.dataProvider), lista);
		}
		
		/**
		 * Adiciona o item opcional ao início da lista, se este ainda não existir
		 */
		private function adicionarItemOpcional(combo: Combo): void {
			if (combo.dataProvider is IList) {
				var lista: IList = (combo.dataProvider as IList);
				if (lista.length == 0 || (lista.getItemAt(0) != null && 
					!lista.getItemAt(0).hasOwnProperty("idItemOpcionalCombo"))) {
					lista.addItemAt(criarItemOpcional(combo),0);
				}
			}
		}
		
		
		/**
		 * Adiciona o item opcional ao início da lista, se este ainda não existir
		 */		
		protected function criarItemOpcional(combo: Combo):Object {
			var objeto:Object = new Object();
			
			objeto[combo.labelField] = "---";
			objeto.idItemOpcionalCombo = -1;
			
			return objeto;
		}
		
		private function cloneArrayCollection(objDestino:ListCollectionView, objOrigem:ListCollectionView):void {
			
			for each (var elemento:Object in objOrigem) {
				objDestino.addItem(elemento);
			}
		} 
		
		/*=============*/
		/*  GED / GFT  */
		/*=============*/
		/**
		 * Método para criar as lista de chave de negócio.
		 */
		private function criarListaChavesNegocio():ArrayCollection {
			var listaValoresChaves:ArrayCollection = new ArrayCollection();
			var objChaveDoc:Object = null;
			
			//Criar os valores das chaves na ordem que são adicionados na fachada.
			var valoresChavesNegocio:ArrayCollection = criarValoresChavesNegocio();
			var definicaoGED:Object = null;
			
			for(var i:uint = 0; i < definicoesComponenteGED.length; i++){
				definicaoGED = definicoesComponenteGED[i];
				
				//Adiciona a chave da pessoa (PF/PJ)
				objChaveDoc = new Object();
				objChaveDoc.siglaTipoDocumento = definicaoGED.siglaTipoDocumento;
				objChaveDoc.siglaChaveDocumento = definicaoGED.obterCodigoTipoPessoaSelecionada;
				objChaveDoc.valorChave = pessoaProxy.cpfCnpj;
				listaValoresChaves.addItem(objChaveDoc);
				
				//Percorre a lista para preencher as outras chaves.
				for (var x:uint = 0; x < definicaoGED.chavesNegocio.length; x++){
					objChaveDoc = new Object();
					
					objChaveDoc.siglaTipoDocumento = definicaoGED.siglaTipoDocumento;
					objChaveDoc.siglaChaveDocumento = definicaoGED.chavesNegocio[x];
					objChaveDoc.valorChave = valoresChavesNegocio[x];
					listaValoresChaves.addItem(objChaveDoc);
				}
			}
			
			return listaValoresChaves;
		}
		
		/**
		 * Método para adicionar os valores das chaves de negócio à um array,
		 * de acordo com o esperado pelas chaves de negócio.
		 */
		private function criarValoresChavesNegocio():ArrayCollection {
			var valoresChavesNegocio:ArrayCollection = new ArrayCollection();
			
			valoresChavesNegocio.addItem(pessoaProxy.codCompartilhamentoCadastro);
			
			var abaCadastroBem:abaCadastroBemView = obterAbaCadastro();
			if(abaCadastroBem.cmbSubTipoBem.selectedItem != null
				&& !isNaN(abaCadastroBem.cmbSubTipoBem.selectedItem.codigo) 
				&& abaCadastroBem.cmbSubTipoBem.selectedItem.codigo != 0){
				valoresChavesNegocio.addItem(abaCadastroBem.cmbSubTipoBem.selectedItem.codigo);
			}else{
				valoresChavesNegocio.addItem(undefined);
			}
			
			return valoresChavesNegocio;
		}
		
		public function isRegistroBloqueadoAlteracao():Boolean{
			return _isRegistroBloqueado; 
		}
	}
}