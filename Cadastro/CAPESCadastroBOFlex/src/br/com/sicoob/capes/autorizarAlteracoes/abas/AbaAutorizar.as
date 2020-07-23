package br.com.sicoob.capes.autorizarAlteracoes.abas
{
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.sicoob.capes.comum.vo.entidades.AutorizacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.InstituicaoVO;
	
	import mx.rpc.events.ResultEvent;
	
	public class AbaAutorizar extends AbaBase {
		public function AbaAutorizar() {
			super();
		}
		
//		protected override function init(evento:FlexEvent):void {
//			super.init(evento);
//			pesquisaRealizada = true;
//			PainelListaAutorizacoesView(this.painelLista).pesquisar(1);
//		}
		
		public override function configurarDtoConsulta(dto : ConsultaDto) : void {
			trace("Configurando o dto de consulta");
			super.configurarDtoConsulta(dto);
			if (this.painelFiltro.numeroCooperativa.valor > 0) {
				var filtro : AutorizacaoVO = AutorizacaoVO(dto.filtro);
				if (filtro.instituicaoOrigem == null) {
					filtro.instituicaoOrigem = new InstituicaoVO();
				}
				filtro.instituicaoOrigem.numero = this.painelFiltro.numeroCooperativa.text;
			}
		}
		
		protected override function get metodoPesquisa():String {
			return "obterDadosSelecaoAutorizar";
		}
		
		public override function retornoPesquisa(event:ResultEvent):void {
			trace("retorno pesquisa");
			super.retornoPesquisa(event);
		}
		
	}
}