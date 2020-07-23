package br.com.sicoob.capes.cadastrarNovaPessoa.paineis
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.componentes.pti.ObjetoEvent;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoPessoaVO;
	
	public class PainelValidacaoCpfCnpj extends PainelValidacaoCpfCnpjView {
		
		private static const OPERACAO_VALIDAR_DOCUMENTO: String = "validarCpfCnpj";
		public var servicoValidacao:ServicoJava = ServicoJavaUtil.getServico(
				CadastroNovaPessoa.CLASSE_SERVICO);

		private var pessoaCompartilhamento: PessoaCompartilhamentoVO; 
		private var pessoa: PessoaVO;
		private var _numeroCooperativa:Number;
		private var _cadastroFacaParte:Boolean = false;
		
		//**************
		// Construtor:
		//**************
		public function PainelValidacaoCpfCnpj() {
			
			servicoValidacao.addEventListener(ResultEvent.RESULT, retornoValidarCpfCnpj);
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}

		//**************
		// Eventos:
		//**************				
		protected function init(event: FlexEvent): void {
			this.btValidar.addEventListener(MouseEvent.CLICK, validar);
		}
		
		private function validar(evt:Event = null): void{
			executarSeValido(validarCpfCnpj);
		}					
		
		private function validarCpfCnpj(obj:Object=null): void {
					
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.cpfCnpj = txtCpfCnpj.text;
			dto.dados.tipoPessoa = cboTipoPessoa.selectedItem as TipoPessoaVO;
			dto.dados.numeroCooperativa = _numeroCooperativa;

			MostraCursor.setBusyCursor("Validando documento ...", 
					Application.application, MostraCursor.CURSOR_PROGRESSO);
			servicoValidacao.getOperation(OPERACAO_VALIDAR_DOCUMENTO).send(dto);
		}
		
		private function retornoValidarCpfCnpj(event:ResultEvent): void {
			MostraCursor.removeBusyCursor();
			this._cadastroFacaParte = event.result.dados.cadastroFacaParte;
			this.pessoaCompartilhamento = event.result.dados.pessoa as PessoaCompartilhamentoVO;
			this.pessoa = null;
			if(this.pessoaCompartilhamento != null) {
				this.pessoa = this.pessoaCompartilhamento.pessoa; 
			}
			this.dispatchEvent(new ObjetoEvent(Modulo.REGISTRO_CARREGADO, event.result.dados.dadosRFB));
		}		
		
		public function obterPessoaCompartilhamento(): PessoaCompartilhamentoVO {
			return this.pessoaCompartilhamento;
		}
		
		public function obterCadastroFacaParte():Boolean {
			return this._cadastroFacaParte;
		}
		
		public function obterPessoa():PessoaVO {
			if(this.pessoa == null) {
				this.pessoa = new PessoaVO();
				this.pessoa.cpfCnpj = txtCpfCnpj.text;
				this.pessoa.tipoPessoa = cboTipoPessoa.selectedItem as TipoPessoaVO;
			}
			
			return this.pessoa;
		}
		
		public function limpar(): void {
			this.txtCpfCnpj.text = "";
			this.cboTipoPessoa.selectedIndex = 0;
		}
		
		public function habilitarCampos(habilita: Boolean): void {
			this.txtCpfCnpj.enabled = habilita;
			this.cboTipoPessoa.enabled = habilita;
			this.btValidar.enabled = habilita;
		}
		
		public function set numeroCooperativa(valor:Number):void {
			this._numeroCooperativa = valor;
		}
	}
}