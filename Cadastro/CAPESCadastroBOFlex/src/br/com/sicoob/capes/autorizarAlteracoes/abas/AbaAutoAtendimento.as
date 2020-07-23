package br.com.sicoob.capes.autorizarAlteracoes.abas
{
	import mx.collections.ArrayCollection;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.sicoob.capes.autorizarAlteracoes.PainelFiltroAutorizacaoView;
	import br.com.sicoob.capes.comum.enums.TipoAutorizacaoEnum;
	import br.com.sicoob.capes.comum.vo.entidades.AutorizacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.InstituicaoVO;
	
	public class AbaAutoAtendimento extends AbaBase {
		public function AbaAutoAtendimento() {
			super();
		}
		
		public override function configurarDtoConsulta(dto : ConsultaDto) : void {
			
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
			return "obterDadosSelecaoAutorizarAutoAtendimento";
		}

		public override function preencherDefinicoes(event : ResultEvent) : void {
			trace("preenchendo as definições");
			var filtro : PainelFiltroAutorizacaoView = PainelFiltroAutorizacaoView(
				this.painelLista.painelFiltro);
			
			var lista:ArrayCollection = new ArrayCollection();
			for each (var item:Object in event.result.dados.listaTipoAutorizacao) 
			{
				var itemEnum:TipoAutorizacaoEnum =  TipoAutorizacaoEnum(item.tipoAutorizacao);
				if(TipoAutorizacaoEnum.ENDERECO.equals(itemEnum) 
					|| TipoAutorizacaoEnum.FONTE_RENDA.equals(itemEnum)
				){
					lista.addItem(item);
				}
			}
			
			filtro.cmbTipoAutorizacao.dataProvider = lista;
		}
		
		
	}
}