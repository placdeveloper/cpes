package {
	import br.com.bancoob.componentes.painellista.PainelListaBanco;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.dto.RequisicaoDTO;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastrarTipoCertidao.TipoCertidaoEdicao;
	import br.com.sicoob.capes.cadastrarTipoCertidao.TipoCertidaoSelecaoView;
	import br.com.sicoob.capes.comum.vo.entidades.OrgaoEmissorCertidaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.SubTipoCertidaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoAbrangenciaCertidaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoCertidaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoObjetoCertidaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoPrazoCertidaoVO;
	
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;

	public class TipoCertidaoSelecao extends TipoCertidaoSelecaoView {

		static private const OPERACAO_OBTER_DADOS_SELECAO: String = "obterDadosSelecao";
		private static const DESTINO_CAPES:String = "destinoCAPES";

		public function TipoCertidaoSelecao() {
			super();
			include 'br/com/sicoob/capes/utils/RegistroClasses.as';
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.SubTipoCertidao",SubTipoCertidaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoAbrangenciaCertidao",TipoAbrangenciaCertidaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoCertidao",TipoCertidaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.OrgaoEmissorCertidao", OrgaoEmissorCertidaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoObjetoCertidao",TipoObjetoCertidaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoPrazoCertidao",TipoPrazoCertidaoVO);
		}
		
		protected override function init(evento:FlexEvent):void {
			super.init(evento);
			
			PainelListaBanco(this.painelLista).funcaoCriacaoDto = instanciarDTOConsulta;
			
			// remoção do botão de exclusão
			this.barraBotoesListaCadastro.exibirBotaoExcluir = false;
			
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], destino_recuperado);
		}
		
		private function destino_recuperado(destino:DestinoVO):void {
			PainelListaBanco(this.painelLista).servicoPesquisa.configurarDestino(destino);
			ServicoJava(this.servicoRecuperacaoInformacoes).configurarDestino(destino);
			
			// Realiza a pesquisa inicial
			PainelListaBanco(this.painelLista).pesquisar(1);
		}		
		
		protected override function montarDtoExclusao(item:Object) : RequisicaoDTO {
			var vo: TipoCertidaoVO = new TipoCertidaoVO();
			vo.codigo = item.codigo;
			
			// Monta o DTO, com o VO, para retorno
			var dto: RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.tipoCertidao = vo;
			return dto;
		}

		private function instanciarDTOConsulta(): ConsultaDto {
			return new ConsultaDto();
		}
		
		protected override function botaoIncluirPressionado():void {
			this.formularioCadastro = new TipoCertidaoEdicao();
			super.botaoIncluirPressionado();
		}
	}
}