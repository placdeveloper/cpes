package br.com.sicoob.capes.corporativo.componentes.procurarBens.janelas{
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.util.IUpLoadDocGed;
	import br.com.bancoob.componentes.util.UpLoadDocGedFactory;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.enums.TipoClassificacaoBemEnum;
	import br.com.sicoob.capes.comum.enums.TipoPessoaEnum;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoBemImovelVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoBemMovelVO;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.IAba;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.bemImovel.abas.DadosBasicosImovel;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.bemMovel.abas.DadosBasicosMovel;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.vo.BemProprietarioVO;
	import br.com.sicoob.capes.corporativo.componentes.procurarBens.vo.BemVO;
	
	import flash.display.DisplayObject;
	
	import mx.collections.ArrayCollection;
	import mx.collections.ListCollectionView;
	import mx.rpc.events.ResultEvent;
	import mx.utils.ObjectUtil;
	
	/**
	 * Aba de documentos do bem.
	 * 
	 * @author Bruno.Carneiro
	 */
	public class AbaDocumentos extends AbaDocumentosView implements IAba {
		
		private var upLoadDocGed:IUpLoadDocGed;
		private var CTA_SIGLA_SISTEMA:String = "PLATAFORMAATENDIMENTO";
		
		public static const VALIDACAO_DOCUMENTOS_NAO_ENVIADOS:String = "Existem documentos que ainda não foram enviados, favor enviá-los antes de prosseguir!";
		public static const VALIDACAO_DOCUMENTOS_DIVERGENTES :String = "Existem documentos com as chaves de negócio diferentes das atuais. Por favor, verifique!";
		public static const VALIDACAO_DOCUMENTOS_NAO_SELECIONADOS:String = "Nenhum documento foi selecionado, deseja continuar assim mesmo?";
		
		//Objeto que guardará as informações para serem usadas no componente GED.
		private var definicoesComponenteGED:ArrayCollection = new ArrayCollection();
		
		/**
		 * @inheritDoc
		 */
		public function inicializar():void {
			upLoadDocGed = UpLoadDocGedFactory.getInstance().createComponent();
			upLoadDocGed.siglaAssunto = TipoPessoaEnum.PESSOA_FISICA.siglaAssuntoGED;
			upLoadDocGed.siglaClasseDocumento = "BEMNOVO";
			upLoadDocGed.validarChavesObrigatorias = true;
			upLoadDocGed.permiteSomenteUmDocumento = true;
			upLoadDocGed.validarObrigatorio = true;
			upLoadDocGed.idModulo = 1;
			upLoadDocGed.idSistema = 2;
			upLoadDocGed.siglaSistema = CTA_SIGLA_SISTEMA;
			
			canvasUploadGed.addChild(upLoadDocGed as DisplayObject);
		}
    	
		/**
		 * @inheritDoc
		 */
    	public function obterDefinicoes(evento:ResultEvent):void {
			definicoesComponenteGED = evento.result.dados.definicoesComponenteGED;
    	}
		
		/**
		 * @inheritDoc
		 */
		public function atualizarCamposRegistro(bem:BemVO):void {
			//Adiciona ao bem, os documentos que foram enviados ao GED.
			//bem.documentosComprobatorios = obterDocumentosComprobatorios();
    	}

		/**
		 * @inheritDoc
		 */
    	public function preencherCampos(bem:BemVO):void {
			//carregarDocumentos(criarListaDocumentos(bem.documentosComprobatorios));
    	}

		/**
		 * @inheritDoc
		 */
    	public function limpar():void {
			limparDocumentos();
    	}

		/**
		 * @inheritDoc
		 */
		public function abaTrocada():void {
			var proprietarios:ArrayCollection = obterAbaProprietarios().gridProprietarios.dataProvider as ArrayCollection;
			var proprietario:BemProprietarioVO = (proprietarios != null && proprietarios.length > 0) ? proprietarios.getItemAt(0) as BemProprietarioVO : null;
			
			if(proprietario != null && TipoPessoaEnum.PESSOA_FISICA.codigo != proprietario.codigoTipoPessoa) {
				upLoadDocGed.siglaAssunto = TipoPessoaEnum.PESSOA_JURIDICA.siglaAssuntoGED;
			} else {
				upLoadDocGed.siglaAssunto = TipoPessoaEnum.PESSOA_FISICA.siglaAssuntoGED;
			}
			
			//Obtém as chaves de negócio
			var listaChavesNegocio:ArrayCollection = criarListaChavesNegocio();
			
			//Adiciona as chaves de negócio à instância do componente.
			chavesNegocio = listaChavesNegocio;
			
			//Atualiza as chaves de negócio dos arquivos que ainda não foram enviados.
			atualizarChavesNegocio(listaChavesNegocio);
		}

		/**
		 * @inheritDoc
		 */
    	public function verificarAlteracoes(bem:BemVO = null):Boolean {
			return true;
    	}
		
		/**
		 *  @inheritDoc
		 */
		public function verificarPreenchimento():Boolean {
			return true;			
		}
		
		/**
		 * @inheritDoc
		 */
		public function configurarDestino(destino:DestinoVO):void {
			
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
			
		}
		
		/**
		 * @inheritDoc
		 */
		public function validar():Boolean {
			if(this.enabled) {
				//Verifica as chaves de negócio dos documentos do componente GED com  as chaves atuais da tela.
				if(!validarDocumentosNaoEnviados()) {
					Alerta.show(VALIDACAO_DOCUMENTOS_NAO_ENVIADOS, "ERRO!", Alerta.ALERTA_ERRO);
					return false;
				}
				
				//Verifica se há algum documento que já foi enviado ao ged e está com as chaves de negócio diferentes das atuais.
				if(!verificarChavesNegocio(criarListaChavesNegocio())){
					Alerta.show(VALIDACAO_DOCUMENTOS_DIVERGENTES, "ERRO!", Alerta.ALERTA_ERRO);
					return false;
				}
				
				/*if(obterDocumentosComprobatorios().length <= 0){
					Alerta.show(VALIDACAO_DOCUMENTOS_NAO_SELECIONADOS, "ATENÇÃO", Alerta.ALERTA_INFORMACAO);
				}*/
			}
			return true;
		}
		
		//==================================================//
		//					GED / GFT  						//
		//==================================================//
		
		/**
		 * Método para criar as lista de chave de negócio.
		 */
		public function criarListaChavesNegocio():ArrayCollection {
			var listaValoresChaves:ArrayCollection = new ArrayCollection();
			var objChaveDoc:Object = null;
			
			//Criar os valores das chaves na ordem que são adicionados na fachada.
			var valoresChavesNegocio:ArrayCollection = criarValoresChavesNegocio();
			var definicaoGED:Object = null;
			
			var proprietarios:ArrayCollection = obterAbaProprietarios().gridProprietarios.dataProvider as ArrayCollection;
			var proprietario:BemProprietarioVO = (proprietarios != null && proprietarios.length > 0) ? proprietarios.getItemAt(0) as BemProprietarioVO : null;
			
			for(var i:uint = 0; i < definicoesComponenteGED.length; i++){
				definicaoGED = definicoesComponenteGED[i];
				definicaoGED.idTipoPessoa = proprietario != null ? proprietario.codigoTipoPessoa : NaN;
				
				//Adiciona a chave da pessoa (PF/PJ)
				objChaveDoc = new Object();
				objChaveDoc.siglaTipoDocumento = definicaoGED.siglaTipoDocumento;
				objChaveDoc.siglaChaveDocumento = proprietario != null ? 0 == proprietario.codigoTipoPessoa ? "1" : "2" : "";
				objChaveDoc.valorChave = proprietario != null ? proprietario.cpfCnpj : "";
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
			
			//FIXME: bruno.carneiro. Verificar o grupo de compartilhamento utilizado.
			valoresChavesNegocio.addItem(1);
			
			var abaDadosBasicos:IAba = obterAbaDadosBasicos();
			var codigoTipoClassificacaoBem:Number;
			var codigoTipoBem:Number;
			
			if(abaDadosBasicos is DadosBasicosImovel) {
				var abaDadosBasicosImovel:DadosBasicosImovel = abaDadosBasicos as DadosBasicosImovel;
				var tipoBemImovel:TipoBemImovelVO = abaDadosBasicosImovel.comboTipoBem.selectedItem as TipoBemImovelVO;
				codigoTipoClassificacaoBem = TipoClassificacaoBemEnum.BEM_IMOVEL.codigo;
				codigoTipoBem = tipoBemImovel != null ? tipoBemImovel.codigo : undefined;
			}else {
				var abaDadosBasicosMovel:DadosBasicosMovel = abaDadosBasicos as DadosBasicosMovel;
				var tipoBemMovel:TipoBemMovelVO = abaDadosBasicosMovel.comboTipoBem.selectedItem as TipoBemMovelVO;
				codigoTipoClassificacaoBem = TipoClassificacaoBemEnum.BEM_MOVEL.codigo;
				codigoTipoBem = tipoBemMovel != null ? tipoBemMovel.codigo : undefined;
			}
			
			valoresChavesNegocio.addItem(codigoTipoClassificacaoBem);
			valoresChavesNegocio.addItem(codigoTipoBem);
			
			return valoresChavesNegocio;
		}
		
		/**
		 * Obtém a aba de dados básicos.
		 */
		private function obterAbaDadosBasicos():IAba {
			return this.parent.getChildByName("abaDadosBasicos") as IAba;
		}
		
		/**
		 * Obtém a aba de proprietários.
		 */
		private function obterAbaProprietarios():AbaProprietarios {
			return this.parent.getChildByName("abaProprietarios") as AbaProprietarios;
		}
		
		/**
		 * Método para setar as chaves de negócio no componente de upload do GED.
		 */
		public function set chavesNegocio(valores:ArrayCollection):void {
			upLoadDocGed.valoresChaves = valores;
		}
		
		/**
		 * Método para atualizar as chaves de negócio dos documentos que ainda não foram enviados.
		 */
		public function atualizarChavesNegocio(listaChaves:ArrayCollection):void {
			upLoadDocGed.atualizarChavesNegocio(listaChaves);
		}
		
		/**
		 * Método para limpar os documentos do componente GED.
		 */
		public function limparDocumentos():void {
			upLoadDocGed.limpar();
		}
		
		/**
		 * Método que verifica se todos os documentos foram enviados com sucesso.
		 */ 
		public function validarDocumentosNaoEnviados():Boolean{
			return upLoadDocGed.validar();
		}
		
		/**
		 * Método para verificar se as chaves de negócio dos documentos já enviados estão
		 * corretas
		 **/
		public function verificarChavesNegocio(listaChaves:ArrayCollection):Boolean {
			return upLoadDocGed.verificarChavesNegocio(listaChaves);
		}
		
		/**
		 * Método para carregar os documentos na combo do componente. 
		 **/
		public function carregarDocumentos(listaIds:ArrayCollection):void {
			upLoadDocGed.carregarDocumentos(ObjectUtil.copy(listaIds) as ArrayCollection);
		}
		
		/**
		 * Método para obter os ids dos documentos que foram enviados ao GED.
		 **/
		public function obterDocumentosComprobatorios():ArrayCollection{
			var documentosComprobatorios:ArrayCollection = new ArrayCollection();
			var arquivosEnviados:ArrayCollection = upLoadDocGed.obterArquivosEnviados();
			
			for(var i:uint = 0; i < arquivosEnviados.length; i++){
				var arquivo:Object = ObjectUtil.copy(arquivosEnviados[i]);
				documentosComprobatorios.addItem(arquivo.idDocumento);
			}
			return documentosComprobatorios;
		}
		
		/**
		 * Método para criar uma lista de ids de documentos para ser enviada
		 * ao componente de upload GED para carregar os documentos já atrelados ao registro.
		 */
		private static function criarListaDocumentos(listaDocs:ListCollectionView):ArrayCollection {
			var listaIds:ArrayCollection = new ArrayCollection();
			
			if(listaDocs != null){
				for(var i:uint = 0; i < listaDocs.length; i++){
					listaIds.addItem(listaDocs[i].idDocumento);
				}
			}
			
			return listaIds;
		}
		
	}
}