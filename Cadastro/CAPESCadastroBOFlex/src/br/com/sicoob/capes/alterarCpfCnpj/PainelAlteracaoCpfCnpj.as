package br.com.sicoob.capes.alterarCpfCnpj
{
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.Constantes;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaVO;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	public class PainelAlteracaoCpfCnpj extends PainelAlteracaoCpfCnpjView {

		static private const OPERACAO_INCLUIR_DADOS: String = "incluirDados";
		public var servicoEdicao:ServicoJava;
		private var pessoa: PessoaVO; 
		
		//**************
		// Construtores:
		//**************
		
		public function PainelAlteracaoCpfCnpj() {
			servicoEdicao = ServicoJavaUtil.getServico(AlteracaoDocumentoPessoa.CLASSE_SERVICO);
			servicoEdicao.addEventListener(ResultEvent.RESULT, retornoAlterar);
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}

		public function exibir(pessoa:PessoaVO):void {
			this.pessoa = pessoa;
			
			if(pessoa != null) {
				this.visible = true;
				var codigoTipoPessoa: Number = pessoa.tipoPessoa.codTipoPessoa;
				rdbTipoCliente.selectedValue = codigoTipoPessoa;
				
				var ehPessoFisica:Boolean = isPessoaFisica(pessoa);
				this.cpfNovo.visible = ehPessoFisica 
				this.cpfNovo.validarObrigatorio = ehPessoFisica;
				this.cnpjNovo.visible = !ehPessoFisica;
				this.cnpjNovo.validarObrigatorio = !ehPessoFisica;
			} else {
				limpar();
				this.visible = false;
			}
		}
		
		//**************
		// Eventos:
		//**************				
		protected function init(event: FlexEvent): void {
			btAlterar.addEventListener(MouseEvent.CLICK, alterarClicado);
		}
		
		private function alterarClicado(evt:Event = null): void{
			executarSeValido(confirmarAlterar);
		}	
		
		private function confirmarAlterar():void {
			Alerta.show("Confirma a operação?", "Confirmação", Alerta.ALERTA_PERGUNTA, 
					null, alterarConfirmado);
		}
		
		private function alterarConfirmado(evt:Event):void {
			MostraCursor.setBusyCursor("Alterando CPF/CNPJ.", 
					Application.application, MostraCursor.CURSOR_GRAVAR);
			
			var cpfCnpj:String = "";
			if(isPessoaFisica(this.pessoa)){
				cpfCnpj = cpfNovo.text;
			} else {
				cpfCnpj = cnpjNovo.text;
			}
			
			var dto: RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.pessoa = this.pessoa;
			dto.dados.cpfCnpjNovo = cpfCnpj;
			dto.dados.motivo = this.motivo.text;
			dto.dados.solicitante = this.solicitante.text;
            servicoEdicao.getOperation(OPERACAO_INCLUIR_DADOS).send(dto);
		}
		
		private function retornoAlterar(event: ResultEvent): void {
			MostraCursor.removeBusyCursor();
			this.dispatchEvent(new Event(Modulo.REGISTRO_GRAVADO));
			limpar();	
		}				

		public function limpar():void {
			rdbTipoCliente.selectedValue = "";
			cpfNovo.text = "";
			cnpjNovo.text = "";
			solicitante.text = "";
			motivo.text = "";
			pessoa = null;
		}

		private function isPessoaFisica(pessoa:PessoaVO): Boolean {
			var codigoTipoPessoa: Number = pessoa.tipoPessoa.codTipoPessoa;
			return codigoTipoPessoa == Constantes.COD_TIPO_PES_FISICA;
		}
		
		public function configurarDestinos(destino:DestinoVO):void {
			servicoEdicao.configurarDestino(destino);
		}
	}
}