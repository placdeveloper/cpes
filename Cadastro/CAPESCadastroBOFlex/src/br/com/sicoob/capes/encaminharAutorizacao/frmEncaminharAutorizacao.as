package br.com.sicoob.capes.encaminharAutorizacao{

//	import br.com.bancoob.dto.RequisicaoReqDTO;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.ParametrosAssistenteAtendimento;
	import br.com.bancoob.sisbr.componentes.PlataformaAtendimento.ITelaBasePlataformaAtendimento;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.app.Application;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.EncaminharAutorizacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
	import br.com.sicoob.capes.corporativo.componentes.plataformaatendimento.ProcuraClientePlataformaCAPES;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCustomizadoCAPES;

	public class frmEncaminharAutorizacao extends frmEncaminharAutorizacaoView implements ITelaBasePlataformaAtendimento {

		static public const CLASSE_SERVICO: String = "br.com.sicoob.capes.cadastro.fachada.AutorizarFachada";
		/**
		 *	Serviços 
		 */
		public var servicoConsulta:ServicoJava;
		public var servicoEncaminhar:ServicoJava;
		public var servicoExcluir:ServicoJava;
		
		/**
		 *  Operações 
		 */
		static private const OPERACAO_OBTER_APTO_ENCAMINHAR: String = "obterDadosSelecaoAptoEncaminhar";
		static private const OPERACAO_ENCAMINHAR_AUTORIZACOES: String = "encaminharAutorizacoes";
		static private const OPERACAO_EXCLUIR_AUTORIZACAO: String = "excluirAutorizacaoEncaminhada";
		
		private static const MENSAGEM_OPERACAO_OBTER_DADOS:String = "Obtendo dados...";
		private static const MENSAGEM_OPERACAO_ENCAMINHAR_AUTORIZACOES:String = "Encaminhando Autorizações...";
		private static const MENSAGEM_OPERACAO_EXCLUIR_AUTORIZACAO:String = "Excluindo a autorização...";

		//--------------------------------------------------------------------------
	    //  Propriedades
	    //--------------------------------------------------------------------------

	    /**
	     *  Construtor.
	     */
		public function frmEncaminharAutorizacao()
		{
			super();
			
			servicoConsulta = ServicoJavaUtil.getServico(CLASSE_SERVICO, MENSAGEM_OPERACAO_OBTER_DADOS, 
				ResultEvent.RESULT, retornoCarregarRegistro);
			servicoEncaminhar = ServicoJavaUtil.getServico(CLASSE_SERVICO, MENSAGEM_OPERACAO_ENCAMINHAR_AUTORIZACOES, 
				ResultEvent.RESULT, retornoEncaminharAutorizacoes);
			servicoExcluir = ServicoJavaUtil.getServico(CLASSE_SERVICO, MENSAGEM_OPERACAO_EXCLUIR_AUTORIZACAO, 
				ResultEvent.RESULT, retornoExcluirAutorizacao);

			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		//--------------------------------------------------------------------------
	    //  Métodos
	    //--------------------------------------------------------------------------
		private function init(evt:FlexEvent):void 
		{
			pesquisar();
		}
		
		public function configurarDestino(destino:DestinoVO):void {
			servicoConsulta.configurarDestino(destino);	
			servicoEncaminhar.configurarDestino(destino);
			servicoExcluir.configurarDestino(destino);
		}
		
		public function encaminharautorizacao():void{
			MostraCursor.setBusyCursor(MENSAGEM_OPERACAO_ENCAMINHAR_AUTORIZACOES, Application.application);
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO(); 
			var pessoaCompartilhamento:PessoaCompartilhamentoVO = TelaPlataformaAtendimentoCustomizadoCAPES.getPessoaSelecionada();
			dto.dados.pessoa = pessoaCompartilhamento.pessoa;
			
			servicoEncaminhar.getOperation(OPERACAO_ENCAMINHAR_AUTORIZACOES).send(dto);
		}

		private function retornoCarregarRegistro(evt:ResultEvent):void {
			this.gc.source = evt.result.dados.autorizacoes;			
			this.gc.refresh();
			trace("atualizando o GC..");
			MostraCursor.removeBusyCursor();
		}
		
		private function retornoEncaminharAutorizacoes(evt:ResultEvent):void{
			this.gc.source = evt.result.dados.autorizacoes;			
			this.gc.refresh();
			
			var autorizacoesEncaminhadas:Boolean = evt.result.dados.autorizacoesEncaminhadas;
			
			if(autorizacoesEncaminhadas){
				Alerta.show("Atualizações da sua instituição foram encaminhadas com sucesso.", "Confirmação", Alerta.ALERTA_SUCESSO, null);				
			}else{
				Alerta.show("As autorizações não foram encaminhadas pois houve alteração do Responsável," +
					" favor encaminhar novamente.", "Confirmação", Alerta.ALERTA_INFORMACAO, null);
			}
			
			MostraCursor.removeBusyCursor();
		}
		
		private function obterRegistroSelecionado():EncaminharAutorizacaoVO {
			return this.gridDados.selectedItem as EncaminharAutorizacaoVO;
		}
		
		public function excluirAutorizacao():void {
			var autorizacao: EncaminharAutorizacaoVO = obterRegistroSelecionado();
			
			if(autorizacao != null) {
				if(ProcuraClientePlataformaCAPES.getPessoaSelecionada().idInstituicao == autorizacao.idInstituicaoOrigem){
					MostraCursor.setBusyCursor(MENSAGEM_OPERACAO_EXCLUIR_AUTORIZACAO, Application.application);
					var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
					dto.dados.autorizacao = autorizacao;
					dto.dados.pessoa = TelaPlataformaAtendimentoCustomizadoCAPES.getPessoaSelecionada().pessoa;
					servicoExcluir.getOperation(OPERACAO_EXCLUIR_AUTORIZACAO).send(dto);
				} else {
					Alerta.show("Permitido apenas para autorizações com origem da sua instituição", "Aviso", Alerta.ALERTA_ERRO);
				}
			}else {
				Alerta.show("Por favor, selecione uma autorização para exclusão.", "Aviso", Alerta.ALERTA_INFORMACAO);
			}
		}
		
		private function pesquisar() : void {
			MostraCursor.setBusyCursor(MENSAGEM_OPERACAO_OBTER_DADOS, Application.application);
			var pessoaCompartilahmentoVO:PessoaCompartilhamentoVO = TelaPlataformaAtendimentoCustomizadoCAPES.getPessoaSelecionada();
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO(); 
			dto.dados.pessoa = pessoaCompartilahmentoVO.pessoa;
			servicoConsulta.getOperation(OPERACAO_OBTER_APTO_ENCAMINHAR).send(dto);
		}
		
		private function retornoExcluirAutorizacao(evt:ResultEvent):void{
			this.gc.source = evt.result.dados.autorizacoes;			
			this.gc.refresh();
			
			MostraCursor.removeBusyCursor();
		}
		
	    //--------------------------------------------------------------------------
	    //  Métodos das interfaces
	    //--------------------------------------------------------------------------
		public function trocarTelaEntrar(params:ParametrosAssistenteAtendimento=null):void{};
		public function trocarTelaSair(params:ParametrosAssistenteAtendimento=null):void{};	
	}
}