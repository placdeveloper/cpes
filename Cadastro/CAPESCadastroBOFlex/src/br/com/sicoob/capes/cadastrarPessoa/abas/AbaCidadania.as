package br.com.sicoob.capes.cadastrarPessoa.abas
{
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.sisbr.componentes.plataformas.BarraInferiorPlataformas;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastrarPessoa.IAbaCadastroPessoa;
	import br.com.sicoob.capes.cadastrarPessoa.janela.SelecaoCidadania;
	import br.com.sicoob.capes.comum.vo.entidades.CidadaniaVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaFisicaVO;
	import br.com.sicoob.capes.comum.vo.entidades.pk.CidadaniaPK;
	
	import flash.display.DisplayObject;
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;

	public class AbaCidadania extends AbaCidadaniaView implements IAbaCadastroPessoa
	{
		
		public function AbaCidadania()
		{
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE,init);
		}
		
		private function init(event:Event):void {
			btnSelecaoNacionalidade.addEventListener(MouseEvent.CLICK, pesquisarCidadania);
			btnRemoverNacionalidade.addEventListener(MouseEvent.CLICK, removerCidadania);
			
			btnSelecaoEnderecoFiscal.addEventListener(MouseEvent.CLICK, pesquisarEnderecoFiscal);
			btnRemoverEnderecoFiscal.addEventListener(MouseEvent.CLICK, removerEnderecoFiscal);
		}
		
		protected function removerEnderecoFiscal(event:MouseEvent):void {
			if(listaEndFiscais.selectedItem != null) {
				(listaEndFiscais.dataProvider as ArrayCollection).removeItemAt(listaEndFiscais.selectedIndex);
			}
		}
		
		protected function pesquisarEnderecoFiscal(event:MouseEvent):void {
			var janelaValidacaoCadastral:Janela = new Janela();
			var selecaoCidadania:SelecaoCidadania = new SelecaoCidadania(true, this.listaEndFiscais);
			janelaValidacaoCadastral.title = "INCLUIR ENDEREÇO FISCAL";
			janelaValidacaoCadastral.icone = "br/com/bancoob/imagens/icos/apply.png";
			janelaValidacaoCadastral.removeAllChildren();
			janelaValidacaoCadastral.addChild(DisplayObject(selecaoCidadania));
			this.parentDocument.addEventListener(BarraInferiorPlataformas.FECHAR_PLATAFORMA, selecaoCidadania.fechar);
			this.parentDocument.addEventListener(BarraInferiorPlataformas.VOLTAR_SISBR, selecaoCidadania.fechar);
			janelaValidacaoCadastral.abrir(DisplayObject(Application.application), false, true);
		}
		
		protected function removerCidadania(event:MouseEvent):void {
			if(listaCidadania.selectedItem != null) {
				(listaCidadania.dataProvider as ArrayCollection).removeItemAt(listaCidadania.selectedIndex);
			}
		}
		
		protected function pesquisarCidadania(event:MouseEvent):void {
			var janelaValidacaoCadastral:Janela = new Janela();
			var selecaoCidadania:SelecaoCidadania = new SelecaoCidadania(false, this.listaCidadania);
			janelaValidacaoCadastral.title = "INCLUIR NACIONALIDADE";
			janelaValidacaoCadastral.icone = "br/com/bancoob/imagens/icos/apply.png";
			janelaValidacaoCadastral.removeAllChildren();
			janelaValidacaoCadastral.addChild(DisplayObject(selecaoCidadania));
			this.parentDocument.addEventListener(BarraInferiorPlataformas.FECHAR_PLATAFORMA, selecaoCidadania.fechar);
			this.parentDocument.addEventListener(BarraInferiorPlataformas.VOLTAR_SISBR, selecaoCidadania.fechar);
			janelaValidacaoCadastral.abrir(DisplayObject(Application.application), false, true);
		}
		
		public function preencherCampos(pessoa:PessoaCompartilhamentoVO):void {
			var pessoaFisica:PessoaFisicaVO = pessoa as PessoaFisicaVO;
			
			listaCidadania.dataProvider = pessoaFisica.listaCidadania;
			listaEndFiscais.dataProvider = pessoaFisica.listaEnderecoFiscal;
		}
		
		public function configurarDestinosServicos(destino:DestinoVO):void {
			this.destino = destino;
		}
		
		public function verificarAlteracoes(registroBkp:PessoaCompartilhamentoVO):Boolean {
			return true;
		}
		
		public function retornoCarregarDefinicoes(event:ResultEvent): void {
		}
		
		public function atualizarCamposRegistro(pessoa:PessoaCompartilhamentoVO): PessoaCompartilhamentoVO {
			var registro: PessoaFisicaVO = PessoaFisicaVO(pessoa);
			registro.listaCidadania = listaCidadania.dataProvider as ArrayCollection;
			registro.listaEnderecoFiscal = listaEndFiscais.dataProvider as ArrayCollection;
			return registro;
		}
		
	}
}