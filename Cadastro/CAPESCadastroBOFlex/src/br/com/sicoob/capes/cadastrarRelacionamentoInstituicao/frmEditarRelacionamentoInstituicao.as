package br.com.sicoob.capes.cadastrarRelacionamentoInstituicao {
	
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.ParametrosAssistenteAtendimento;
	import br.com.bancoob.sisbr.componentes.PlataformaAtendimento.ITelaBasePlataformaAtendimento;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.capes.cadastrarRelacionamentoInstituicao.dto.RelacionamentoInstituicaoDTO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
	
	import flash.events.Event;
	
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	public class frmEditarRelacionamentoInstituicao extends frmEditarRelacionamentoInstituicaoView implements ITelaBasePlataformaAtendimento {
		
		/** Serviços */
		public var servicoSalvar:ServicoJava;
		
		/** Operações */
		public static const OPERACAO_ALTERAR_GESTOR: String = "alterarGestorCadastro";
		public static const OPERACAO_REPLICAR_CADASTRO: String = "replicarCadastroBancoob";
		public static const OPERACAO_ATUALIZAR_ASS_FOTO: String = "atualizarAssinaturaFotoBancoob";

		//--------------------------------------------------------------------------
	    //  Propriedades
	    //--------------------------------------------------------------------------
		private static var relacionamento:RelacionamentoInstituicaoDTO;
		private static var pessoa:PessoaCompartilhamentoVO;

	    /**
	     *  Construtor.
	     */
		public function frmEditarRelacionamentoInstituicao() {
			super();

			servicoSalvar = ServicoJavaUtil.getServico(RelacionamentoInstituicaoSelecao.CLASSE_SERVICO);
			servicoSalvar.addEventListener(ResultEvent.RESULT, retornoSalvar);		

			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		//--------------------------------------------------------------------------
	    //  Métodos auxiliares
	    //--------------------------------------------------------------------------
		private function init(evt:FlexEvent):void {
		}	

		public function carregarRegistro(vo: RelacionamentoInstituicaoDTO, pessoaVO:PessoaCompartilhamentoVO):void {
			relacionamento = vo;
			nomeInstituicao.text = vo.nomeInstituicao;
			pessoa = pessoaVO;
		}

		public function alterarGestor():void {
			executarSeValido(gravarRegistro);
		}

		public function gravarRegistro():void {

			MostraCursor.setBusyCursor("Alterando Gestor...", Application.application, 
					MostraCursor.CURSOR_GRAVAR);
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.relacionamento = relacionamento;
			dto.dados.pessoa = pessoa;
			servicoSalvar.getOperation(OPERACAO_ALTERAR_GESTOR).send(dto);			
		}

		public function replicarCadastroBancoob(pessoa:PessoaCompartilhamentoVO):void {

			MostraCursor.setBusyCursor("Replicando Cadastro...", Application.application, 
					MostraCursor.CURSOR_GRAVAR);

			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.pessoa = pessoa;
			servicoSalvar.getOperation(OPERACAO_REPLICAR_CADASTRO).send(dto);			
		}

		public function atualizarAssinaturaFotosBancoob(pessoa:PessoaCompartilhamentoVO):void {

			MostraCursor.setBusyCursor("Atualizando Assinatura/Foto Cadastro...", Application.application, 
					MostraCursor.CURSOR_GRAVAR);
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.pessoa = pessoa;
			servicoSalvar.getOperation(OPERACAO_ATUALIZAR_ASS_FOTO).send(dto);			
		}
				
		private function retornoSalvar(evt:ResultEvent):void {
			MostraCursor.removeBusyCursor();
			this.dispatchEvent(new Event(Modulo.REGISTRO_GRAVADO));	
		}
		
		public function verificarAlteracoes():Boolean {
			return true;
		}
					
	    //--------------------------------------------------------------------------
	    //  Métodos das interfaces
	    //--------------------------------------------------------------------------
		public function trocarTelaEntrar(params:ParametrosAssistenteAtendimento=null):void{};
		public function trocarTelaSair(params:ParametrosAssistenteAtendimento=null):void{};	
		
	}
}