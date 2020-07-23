package br.com.sicoob.capes.grupoTipoAnotacao {
	
	import br.com.bancoob.componentes.Botao;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.cadastro.BarraBotoesFormularioCadastroView;
	import br.com.bancoob.dto.RequisicaoDTO;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.GrupoTipoAnotacaoVO;
	
	import mx.collections.ArrayCollection;
	import mx.collections.ListCollectionView;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;

    public class GrupoTipoAnotacaoEdicao extends GrupoTipoAnotacaoEdicaoView {

		private static const CLASSE_SERVICO: String = "br.com.sicoob.capes.cadastro.fachada.GrupoTipoAnotacaoFachada";
		private static const DESTINO_CAPES : String = "destinoCAPES";
		public static const CHAVE_MAPA:String = "grupoTipoAnotacao";
		
		private var servicoDefinicoes:ServicoJava;
		
		private var listaTiposAnotacao:ArrayCollection = new ArrayCollection();
		
		public function GrupoTipoAnotacaoEdicao() {
			super();
        }
		
		protected override function init(event:FlexEvent):void {
			super.init(event);
			
			servicoDefinicoes = ServicoJavaUtil.getServico(CLASSE_SERVICO, "Obtendo definições...", ResultEvent.RESULT, retornoObterDefinicoes);
			servicoInclusao = ServicoJavaUtil.getServico(CLASSE_SERVICO, "Realizando a operação...");
			servicoEdicao = ServicoJavaUtil.getServico(CLASSE_SERVICO, "Realizando a operação...");
			
			var botaoAjuda:Botao = BarraBotoesFormularioCadastroView(this.barraBotoes).btnAjuda;
			botaoAjuda.visible = botaoAjuda.includeInLayout = false;
			
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], destino_recuperado);
		}
		
		public function destino_recuperado(destino:DestinoVO):void {
			ServicoJava(this.servicoInclusao).configurarDestino(destino);
			ServicoJava(this.servicoEdicao).configurarDestino(destino);
			servicoDefinicoes.configurarDestino(destino);
			
			obterDefinicoes();
		}
		
		protected override function limparFormIncluir() : void {
			campoNome.text = "";
			componenteListaTiposAnotacao.limpar();
		}
		
		protected override function preencherCampos():void {
			var registro:GrupoTipoAnotacaoVO = GrupoTipoAnotacaoVO(this.objeto);
			if(registro != null) {
				this.campoNome.text = registro.nome;
				componenteListaTiposAnotacao.adicionarListaRegistrosSelecionados(registro.tiposAnotacao);
			}
		}
		
		protected override function montarDto():RequisicaoDTO {
			var vo:GrupoTipoAnotacaoVO = new GrupoTipoAnotacaoVO();
			
			if (MODO_EDICAO == modo) {
				vo.idGrupoTipoAnotacao = this.objeto.idGrupoTipoAnotacao;
			}
			
			vo.nome = this.campoNome.text;
			vo.tiposAnotacao = ListCollectionView(componenteListaTiposAnotacao.obterItensSelecionados());
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados[CHAVE_MAPA] = vo;
			
			return dto as RequisicaoDTO;
		}
		
		protected override function houveAlteracoes():Boolean {
			var alterado:Boolean = false;
			
			if (MODO_INCLUSAO == modo) {
				alterado = alterado || this.campoNome.text != "";
			} else if (MODO_EDICAO == modo) {
				var destino:GrupoTipoAnotacaoVO = GrupoTipoAnotacaoVO(this.objeto);
				alterado = alterado || this.campoNome.text != destino.nome;
			}
			
			return alterado;
		}
		
		private function obterDefinicoes():void {
			MostraCursor.setBusyCursor("Obtendo definições...", this, MostraCursor.CURSOR_PESQUISAR);
			servicoDefinicoes.obterDefinicoes(new RequisicaoReqDTO());
		}
		
		private function retornoObterDefinicoes(evento:ResultEvent):void {
			componenteListaTiposAnotacao.adicionarListaRegistrosDisponiveis(evento.result.dados.tiposAnotacao);
			preencherCampos();
		}
		
		/**
		 * @inheritDoc
		 */
		protected override function habilitarFormulario():void {
			super.habilitarFormulario();
			bloquearCampoModoVisualizacao(false);
		}
		
		/**
		 * @inheritDoc
		 */
		protected override function desabilitarFormulario():void {
			super.desabilitarFormulario();
			bloquearCampoModoVisualizacao(true);
		}
		
		private function bloquearCampoModoVisualizacao(bloquear:Boolean):void {
			componenteListaTiposAnotacao.enabled = !bloquear;
		}
		
    }
}