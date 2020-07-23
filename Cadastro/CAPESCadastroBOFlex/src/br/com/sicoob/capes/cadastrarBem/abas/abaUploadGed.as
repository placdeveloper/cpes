package br.com.sicoob.capes.cadastrarBem.abas{
	
	import br.com.bancoob.componentes.util.IUpLoadDocGed;
	import br.com.bancoob.componentes.util.UpLoadDocGedFactory;
	import br.com.sicoob.capes.cadastrarBem.IAbaForm;
	import br.com.sicoob.capes.utils.autorizacao.abas.abaUploadGedView;
	import br.com.sicoob.capes.comum.vo.entidades.DocumentoComprobatorioVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoDocumentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.bemantigo.BemVO;
	import br.com.sicoob.capes.utils.UploadDocGedUtil;
	
	import flash.display.DisplayObject;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.ObjectUtil;
	
	public class abaUploadGed extends abaUploadGedView implements IAbaForm {
		
		private var upLoadDocGed:IUpLoadDocGed;
		
		private static const SERVICO_JAVA: String = "servicosJavaCapes";
		private var CTA_SIGLA_SISTEMA:String = "PLATAFORMAATENDIMENTO";
		
		/**
		 * Método construtor.
		 **/
		public function abaUploadGed() {
			upLoadDocGed = UpLoadDocGedFactory.getInstance().createComponent();
			this.addEventListener(FlexEvent.CREATION_COMPLETE, initGed);
		}
		
		/**
		 * Método de inicialização.
		 **/
		private function initGed(event: FlexEvent) : void {
			// Informações para o carregamento da combo do componente de upload GED.
			upLoadDocGed.siglaAssunto = UploadDocGedUtil.obterSiglaAssuntoPessoaSelecionada();
			upLoadDocGed.siglaClasseDocumento = "BEM";//TODO TipoAutorizacaoEnum.BEM.name;
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
		
		public function carregarDocumentos(listaIds:ArrayCollection):void {
			upLoadDocGed.carregarDocumentos(ObjectUtil.copy(listaIds) as ArrayCollection);
		}
		
		/**
		 * Método para obter os documentos comprobatórios, enviados ao GED.
		 **/
		public function obterDocumentosComprobatorios():ArrayCollection{
			var documentosComprobatorios:ArrayCollection = new ArrayCollection();
			var arquivosEnviados:ArrayCollection = upLoadDocGed.obterArquivosEnviados();
			
			for(var i:uint = 0; i < arquivosEnviados.length; i++){
				var arquivo:Object = ObjectUtil.copy(arquivosEnviados[i]);
				
				var tipoDocumento:TipoDocumentoVO = new TipoDocumentoVO;
				tipoDocumento.idTipoDocumento = arquivo.idTipoDocumento;
				
				var documentoComprobatorio:DocumentoComprobatorioVO = new DocumentoComprobatorioVO;
				documentoComprobatorio.idDocumento = arquivo.idDocumento;
				documentoComprobatorio.tipoDocumento = tipoDocumento;
				
				documentosComprobatorios.addItem(documentoComprobatorio);
			}
			return documentosComprobatorios;
		}
		
		public function preencherCampos(objeto:BemVO):void{
			
		}
		
		public function carregarDefinicoes(evt:ResultEvent):void{
			
		}
		
		public function atualizarCamposRegistro(objeto:BemVO):void{
			
		}
		
		public function verificarAlteracoes(bem:BemVO):Boolean{
			return false;
		}
		
		public function ativarAba(object:Object):Boolean{
			return true;
		}
		
		public function limparFormIncluir():void{
			limparDocumentos();
		}
		
		public function init():void{
			
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