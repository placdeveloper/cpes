package br.com.sicoob.capes.autorizarAlteracoes.abas {
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.sicoob.capes.comum.vo.entidades.AutorizacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.InstituicaoVO;
	
	import mx.events.FlexEvent;
	
	public class AbaDevolvidas extends AbaBase implements IAbaAutorizacao{
		
		public function AbaDevolvidas(){
			super();
		}
		
		protected override function init(evento:FlexEvent):void {
			super.init(evento);
		}
		
		public override function configurarDtoConsulta(dto:ConsultaDto):void {
			
			super.configurarDtoConsulta(dto);
			if (this.painelFiltro.numeroCooperativa.valor > 0) {
				var filtro : AutorizacaoVO = AutorizacaoVO(dto.filtro);
				if (filtro.instituicaoDestino == null) {
					filtro.instituicaoDestino = new InstituicaoVO();
				}
				filtro.instituicaoDestino.numero = this.painelFiltro.numeroCooperativa.text;
			}
		}
		
		protected override function get metodoPesquisa():String {
			return "obterDadosSelecaoDevolvidas";
		}
		
	}
}