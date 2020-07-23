package br.com.sicoob.capes.cadastroEmpreendimento
{
	import br.com.bancoob.componentes.eventos.EventoCadastro;
	import br.com.bancoob.dto.RequisicaoDTO;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.tipos.Booleano;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.vo.entidades.EmpreendimentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.FinalidadeEmpreendimentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.UnidadeMedidaVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	
	import flash.events.Event;
	
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;

	public class EmpreendimentoEdicao extends EmpreendimentoEdicaoView
	{
		static public const CLASSE_SERVICO: String = 
			"br.com.sicoob.capes.cadastro.fachada.EmpreendimentoFachada";
		static private const OPERACAO_OBTER_DEFINICOES: String = "obterDefinicoes";
		public var servico: ServicoJava;
		private static const DESTINO_CAPES:String = "destinoCAPES";
		
		public function EmpreendimentoEdicao() {
			super();
			servico = ServicoJavaUtil.getServico(CLASSE_SERVICO);
			servico.addEventListener(ResultEvent.RESULT, retornoObterDefinicoes);
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		protected override function init(evento:FlexEvent):void {
			super.init(evento);
			
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], destino_recuperado);
		}
		
		private function destino_recuperado(destino:DestinoVO):void {
			servico.configurarDestino(destino);
			ServicoJava(this.servicoInclusao).configurarDestino(destino);
			ServicoJava(this.servicoEdicao).configurarDestino(destino);
			
			servico.getOperation(OPERACAO_OBTER_DEFINICOES).send(new RequisicaoReqDTO());
		} 
		
		private function retornoObterDefinicoes(event: ResultEvent): void {
			finalidade.dataProvider = event.result.dados.listaFinalidades;
			unidadeArea.dataProvider = event.result.dados.listaUnidade;
			unidadePrevisao.dataProvider = event.result.dados.listaUnidade;

			if (initialized && (MODO_INCLUSAO == modo)) {
				this.codigo.valor = event.result.dados.codigo;
			}
			
			preencherCampos();
		}
		
		protected override function limparFormIncluir() : void {
			this.codigo.text = "";
			this.descricao.text = "";
			this.finalidade.selectedIndex = 0;
			this.unidadeArea.selectedIndex = 0;
			this.unidadePrevisao.selectedIndex = 0;
			this.codigoBacen.text = "";
			
			this.imovelSim.selected = true;
			this.imovelNao.selected = false;
			
			this.areaSim.selected = true;
			this.areaNao.selected = false;
			
			this.ativo.selected = false;
		}
		
		override protected function montarDto(): RequisicaoDTO {
			var vo: EmpreendimentoVO = new EmpreendimentoVO();
			vo.codigo = this.codigo.valor;
			vo.descricao = this.descricao.text;
			
			vo.finalidade = FinalidadeEmpreendimentoVO(this.finalidade.selectedItem);
			vo.unidadeArea = UnidadeMedidaVO(this.unidadeArea.selectedItem);
			vo.unidadePrevisao = UnidadeMedidaVO(this.unidadePrevisao.selectedItem);
			vo.codigoBacen = this.codigoBacen.text;
			
			vo.exigeImovel = new Booleano(this.imovelSim.selected);
			vo.exigeArea = new Booleano(this.areaSim.selected);
			
			vo.ativo = new Booleano(this.ativo.selected);
			
			// Monta o DTO, com o VO, para retorno
			var dto: RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.empreendimento = vo;
			return dto;
		}
		
		protected override function configurarEstadoComponente(componente : UIComponent, 
															   habilitar : Boolean) : void {
			
			super.configurarEstadoComponente(componente, habilitar);
			if (componente.id == "codigo") {
				this.codigo.enabled = false;
			}
			
			if (MODO_EDICAO == modo) {
				labelCodigoBacen.text = "Código Bacen :";
				codigoBacen.validarObrigatorio = false;
			}
		}		
		
		override protected function preencherCampos():void {
			var vo: EmpreendimentoVO = EmpreendimentoVO(objeto);
			if (vo != null) {
				this.codigo.valor = vo.codigo;
				this.descricao.text = vo.descricao;
				
				this.finalidade.procuraItemPorNome(vo.finalidade.codigo, "codigo");
				this.unidadeArea.procuraItemPorNome(vo.unidadeArea.codigo, "codigo");
				this.unidadePrevisao.procuraItemPorNome(vo.unidadePrevisao.codigo, "codigo");
				this.codigoBacen.text = vo.codigoBacen;
				
				if (vo.exigeImovel.valor) {
					this.imovelSim.selected = true;
					this.imovelNao.selected = false;
				} else {
					this.imovelNao.selected = true;
					this.imovelSim.selected = false;
				}
				
				if (vo.exigeArea.valor) {
					this.areaSim.selected = true;
					this.areaNao.selected = false;
				} else {
					this.areaNao.selected = true;
					this.areaSim.selected = false;
				}
				
				this.ativo.selected = vo.ativo.valor;
			}
			
		}		
		
	}
}