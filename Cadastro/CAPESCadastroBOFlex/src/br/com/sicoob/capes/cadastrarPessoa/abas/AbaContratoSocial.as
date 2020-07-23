package br.com.sicoob.capes.cadastrarPessoa.abas
{
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastrarPessoa.IAbaCadastroPessoa;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaJuridicaVO;
	
	import flash.events.Event;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	public class AbaContratoSocial extends AbaContratoSocialView implements IAbaCadastroPessoa
	{
		public function AbaContratoSocial()
		{
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE,init);
		}
		
		private function init(event:Event):void{}
				
		public function preencherCampos(pessoa:PessoaCompartilhamentoVO):void {
			
			var registro:PessoaJuridicaVO = PessoaJuridicaVO(pessoa);
			this.txtContratoSocial.text = registro.contratoSocial;
		}
		
		public function configurarDestinosServicos(destino:DestinoVO):void {
			this.destino = destino;
		}
		
		public function verificarAlteracoes(registroBkp:PessoaCompartilhamentoVO):Boolean {
			var registro:PessoaJuridicaVO = PessoaJuridicaVO(registroBkp);
			return this.txtContratoSocial.text == registro.contratoSocial;
		}
		
		public function retornoCarregarDefinicoes(event:ResultEvent): void{}
		
		public function atualizarCamposRegistro(pessoa:PessoaCompartilhamentoVO): PessoaCompartilhamentoVO {
			
			var registro:PessoaJuridicaVO = PessoaJuridicaVO(pessoa);
			
			registro.contratoSocial = txtContratoSocial.text;
			return registro;
		}
	}
}