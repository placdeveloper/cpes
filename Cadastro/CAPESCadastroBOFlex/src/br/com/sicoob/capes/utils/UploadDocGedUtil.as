package br.com.sicoob.capes.utils {
	
	import mx.collections.ArrayCollection;
	import mx.collections.ListCollectionView;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.Constantes;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.enums.TipoPessoaEnum;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.corporativo.componentes.plataformaatendimento.ProcuraClientePlataformaCAPES;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	
	public class UploadDocGedUtil {
		
		private static const CLASSE_SERVICO:String = "br.com.sicoob.capes.cadastro.fachada.AlteracaoDocumentoPessoaFachada";
		public static const VALIDACAO_DOCUMENTOS_NAO_ENVIADOS : String = "Existem documentos que ainda não foram enviados, favor enviá-los antes de prosseguir!";
		public static const VALIDACAO_DOCUMENTOS_DIVERGENTES  : String = "Existem documentos com as chaves de negócio diferentes das atuais. Por favor, verifique!";
		public static const VALIDACAO_DOCUMENTOS_NAO_SELECIONADOS : String = "Nenhum documento foi selecionado, deseja continuar assim mesmo?";
		public static const VALIDACAO_DOCUMENTOS_OBRIGATORIOS: String = "Para dar continuidade a atualização anexe um ";
		
		/**
		 * Método construtor.
		 **/
		public function UploadDocGedUtil() {
		}
		
		/**
		 * Método para retornar a sigla do assunto, dependendo
		 * do tipo de pessoa selecionada (PF/PJ).
		 */
		public static function obterSiglaAssuntoPessoaSelecionada():String {
			var pessoaSelecionada:PessoaPlataformaVO = ProcuraClientePlataformaCAPES.getPessoaSelecionada();
			var siglaAssunto:String = '';
			
			if(Constantes.COD_TIPO_PES_FISICA == pessoaSelecionada.codTipoPessoa){
				siglaAssunto = TipoPessoaEnum.PESSOA_FISICA.siglaAssuntoGED;
			}else if(Constantes.COD_TIPO_PES_JURIDICA == pessoaSelecionada.codTipoPessoa){
				siglaAssunto = TipoPessoaEnum.PESSOA_JURIDICA.siglaAssuntoGED;
			}
			
			return siglaAssunto;
		}
		
		/**
		 * Método para criar uma lista de ids de documentos para ser enviada
		 * ao componente de upload GED para carregar os documentos já atrelados ao registro.
		 */
		public static function criarListaDocumentos(listaDocs:ListCollectionView):ArrayCollection {
			var listaIds:ArrayCollection = new ArrayCollection();
			
			if(listaDocs != null){
				for(var i:uint = 0; i < listaDocs.length; i++){
					listaIds.addItem(listaDocs[i].idDocumento);
				}
			}
			
			return listaIds;
		}
		
		public static function recuperarDocumentosGED(destino:DestinoVO, idRegistroControlado:String, idTipoPessoaSelecionada:Number, funcaoRetorno:Function):void {
			var servico:ServicoJava = ServicoJavaUtil.getServico(CLASSE_SERVICO);
			servico.configurarDestino(destino);
			servico.addEventListener(ResultEvent.RESULT, funcaoRetorno);	
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO;
			dto.dados.idRegistroControlado = idRegistroControlado;
			dto.dados.idTipoPessoaSelecionada =  idTipoPessoaSelecionada;
			servico.recuperarDocumentosGED(dto);
		}
	}
}