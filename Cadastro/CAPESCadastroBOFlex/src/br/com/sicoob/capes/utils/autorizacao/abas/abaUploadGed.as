package br.com.sicoob.capes.utils.autorizacao.abas {
	
	import flash.display.DisplayObject;
	import flash.filesystem.FileMode;
	import flash.filesystem.FileStream;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.utils.ObjectUtil;
	
	import br.com.bancoob.componentes.util.IUpLoadDocGed;
	import br.com.bancoob.componentes.util.UpLoadDocGedFactory;
	import br.com.bancoob.sisbr.util.log.SisbrLogger;
	import br.com.sicoob.capes.comum.vo.entidades.DocumentoComprobatorioVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoDocumentoVO;
	import br.com.sicoob.capes.utils.UploadDocGedUtil;
	
	public class abaUploadGed extends abaUploadGedView {
		
		private var upLoadDocGed:IUpLoadDocGed;
		
		public var _siglaClasseDocumento:String;
		private var CTA_SIGLA_SISTEMA:String = "PLATAFORMAATENDIMENTO";
		
		/**
		 * Método construtor.
		 **/
		public function abaUploadGed() {
			upLoadDocGed = UpLoadDocGedFactory.getInstance().createComponent();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		/**
		 * Método de inicialização.
		 **/
		private function init(event: FlexEvent) : void {
			// Informações para o carregamento da combo do componente de upload GED.
			upLoadDocGed.siglaAssunto = UploadDocGedUtil.obterSiglaAssuntoPessoaSelecionada();
			upLoadDocGed.siglaClasseDocumento = _siglaClasseDocumento;
			upLoadDocGed.validarChavesObrigatorias = true;
			upLoadDocGed.permiteSomenteUmDocumento = true;
			upLoadDocGed.validarObrigatorio = true;
			upLoadDocGed.idModulo = 1;
			upLoadDocGed.idSistema = 2;
			upLoadDocGed.siglaSistema = CTA_SIGLA_SISTEMA;
			
			canvasUploadGed.addChild(upLoadDocGed as DisplayObject);
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
		 * Método para obter os documentos comprobatórios, que foram enviados ao GED.
		 **/
		public function obterDocumentosComprobatorios():ArrayCollection{
			var documentosComprobatorios:ArrayCollection = new ArrayCollection();
			var arquivosEnviados:ArrayCollection = upLoadDocGed.obterArquivosEnviados();
			
			for(var i:uint = 0; i < arquivosEnviados.length; i++){
				try{
					var arquivo:Object = ObjectUtil.copy(arquivosEnviados[i]);
				
					var tipoDocumento:TipoDocumentoVO = new TipoDocumentoVO;
					tipoDocumento.idTipoDocumento = arquivo.idTipoDocumento;
					
					var documentoComprobatorio:DocumentoComprobatorioVO = new DocumentoComprobatorioVO();
					documentoComprobatorio.idDocumento = arquivo.idDocumento;
					documentoComprobatorio.tipoDocumento = tipoDocumento;
					
					documentosComprobatorios.addItem(documentoComprobatorio);
				} catch (erro:*) {
					//Enviar para a Monitoração.
					SisbrLogger
						.getLogger("abaUploadGed")
						.error("CAPES: Erro obter os documentos comprobatórios, que foram enviados ao GED. Erro(" + erro.getStackTrace() +")");
				}
			}
			return documentosComprobatorios;
		}
		
		public override function dispose():void {
			super.dispose();
			if(upLoadDocGed != null) {
				upLoadDocGed.dispose();
				upLoadDocGed = null;
			}
		}
	}
}