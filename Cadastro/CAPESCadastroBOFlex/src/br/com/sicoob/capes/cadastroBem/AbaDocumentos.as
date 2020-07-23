package br.com.sicoob.capes.cadastroBem{
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastroBem.bemImovel.abas.DadosBasicosImovel;
	import br.com.sicoob.capes.cadastroBem.bemMovel.abas.DadosBasicosMovel;
	import br.com.sicoob.capes.comum.enums.TipoClassificacaoBemEnum;
	import br.com.sicoob.capes.comum.vo.entidades.bem.BemVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoBemImovelVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.TipoBemMovelVO;
	import br.com.sicoob.capes.corporativo.componentes.plataformaatendimento.ProcuraClientePlataformaCAPES;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	import br.com.sicoob.capes.utils.UploadDocGedUtil;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.events.ResultEvent;
	
	/**
	 * Aba de documentos do bem.
	 * 
	 * @author Bruno.Carneiro
	 */
	public class AbaDocumentos extends AbaDocumentosView implements IAba {
		
		private var pessoaSelecionada:PessoaPlataformaVO = ProcuraClientePlataformaCAPES.getPessoaSelecionada();
		
		//Objeto que guardará as informações para serem usadas no componente GED.
		private var definicoesComponenteGED:ArrayCollection = new ArrayCollection();
		
		/**
		 * @inheritDoc
		 */
		public function inicializar():void {
			
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
			bem.documentosComprobatorios = abaDocumentos.obterDocumentosComprobatorios();
    	}

		/**
		 * @inheritDoc
		 */
    	public function preencherCampos(bem:BemVO):void {
			abaDocumentos.carregarDocumentos(UploadDocGedUtil.criarListaDocumentos(bem.documentosComprobatorios));
    	}

		/**
		 * @inheritDoc
		 */
    	public function limpar():void {
			abaDocumentos.limparDocumentos();
    	}

		/**
		 * @inheritDoc
		 */
		public function abaTrocada():void {
			//Obtém as chaves de negócio
			var listaChavesNegocio:ArrayCollection = criarListaChavesNegocio();
			
			//Adiciona as chaves de negócio à instância do componente.
			abaDocumentos.chavesNegocio = listaChavesNegocio;
			
			//Atualiza as chaves de negócio dos arquivos que ainda não foram enviados.
			abaDocumentos.atualizarChavesNegocio(listaChavesNegocio);
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
			if(this.enabled && pessoaSelecionada.utilizaGedGft) {
				//Verifica as chaves de negócio dos documentos do componente GED com  as chaves atuais da tela.
				if(!abaDocumentos.validarDocumentosNaoEnviados()) {
					Alerta.show(UploadDocGedUtil.VALIDACAO_DOCUMENTOS_NAO_ENVIADOS, "ERRO!", Alerta.ALERTA_ERRO);
					return false;
				}
				
				//Verifica se há algum documento que já foi enviado ao ged e está com as chaves de negócio diferentes das atuais.
				if(!abaDocumentos.verificarChavesNegocio(criarListaChavesNegocio())){
					Alerta.show(UploadDocGedUtil.VALIDACAO_DOCUMENTOS_DIVERGENTES, "ERRO!", Alerta.ALERTA_ERRO);
					return false;
				}
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
			
			for(var i:uint = 0; i < definicoesComponenteGED.length; i++){
				definicaoGED = definicoesComponenteGED[i];
				
				//Adiciona a chave da pessoa (PF/PJ)
				objChaveDoc = new Object();
				objChaveDoc.siglaTipoDocumento = definicaoGED.siglaTipoDocumento;
				objChaveDoc.siglaChaveDocumento = definicaoGED.obterCodigoTipoPessoaSelecionada;
				objChaveDoc.valorChave = pessoaSelecionada.cpfCnpj;
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
			
			valoresChavesNegocio.addItem(pessoaSelecionada.codCompartilhamentoCadastro);
			
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
	}
}