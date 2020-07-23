package br.com.sicoob.capes.destinoExportacao {
	
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.collections.ListCollectionView;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Botao;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.cadastro.BarraBotoesFormularioCadastroView;
	import br.com.bancoob.dto.RequisicaoDTO;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.tipos.Booleano;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.enums.CodificacaoArquivoEnum;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.DestinoExportacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoInformacaoVO;

    public class DestinoExportacaoEdicao extends DestinoExportacaoEdicaoView {

		private static const CLASSE_SERVICO: String = "br.com.sicoob.capes.cadastro.fachada.DestinoExportacaoFachada";
		private static const DESTINO_CAPES : String = "destinoCAPES";
		public static const CHAVE_MAPA:String = "destinoExportacao";
		
		private var servicoDefinicoes:ServicoJava;
		
		public function DestinoExportacaoEdicao() {
			super();
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoInformacao", TipoInformacaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.DestinoExportacao", DestinoExportacaoVO);
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
			campoDescricao.text = "";
			campoDiretorio.text = "";
			comboCodificacao.selectedIndex = 0;
			componenteListaTiposInformacao.limpar();
		}
		
		protected override function preencherCampos():void {
			var registro:DestinoExportacaoVO = DestinoExportacaoVO(this.objeto);
			
			this.campoDescricao.text = registro.descricao;
			this.campoDiretorio.text = registro.diretorio;
			this.situacao.selectedValue = registro.ativo.valor;
			preencherComboCodificacao(registro.codificacaoArquivo);
			
			if(MODO_VISUALIZACAO == modo) {
				componenteListaTiposInformacao.enabled = false
			} else {
				componenteListaTiposInformacao.enabled = true;
			}
		}
		
		protected override function montarDto():RequisicaoDTO {
			var vo:DestinoExportacaoVO = new DestinoExportacaoVO();
			
			if (MODO_EDICAO == modo) {
				vo.codigo = this.objeto.codigo;
			}
			
			vo.descricao = this.campoDescricao.text;
			vo.diretorio = this.campoDiretorio.text;
			vo.codificacaoArquivo = (this.comboCodificacao.selectedItem as CodificacaoArquivoEnum).codigo;
			vo.ativo = new Booleano(this.situacao.selectedValue);
			
			var tipoInformacaoSelecionados:ArrayCollection = this.componenteListaTiposInformacao.obterItensSelecionados();
			if (tipoInformacaoSelecionados != null && tipoInformacaoSelecionados.length > 0) {
				vo.tiposInformacao = tipoInformacaoSelecionados;
			}
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados[CHAVE_MAPA] = vo;
			
			return dto as RequisicaoDTO;
		}
		
		protected override function houveAlteracoes():Boolean {
			var alterado:Boolean = false;
			
			if (MODO_INCLUSAO == modo) {
				alterado = alterado || this.campoDescricao.text != "";
				alterado = alterado || this.campoDiretorio.text != "";
				alterado = alterado || this.situacao.selectedValue != null;
				alterado = alterado || this.comboCodificacao.selectedItem != null;
			} else if (MODO_EDICAO == modo) {
				var destino:DestinoExportacaoVO = DestinoExportacaoVO(this.objeto);
				alterado = alterado || this.campoDescricao.text != destino.descricao;
				alterado = alterado || this.campoDiretorio.text != destino.diretorio;
				alterado = alterado || new Boolean(this.situacao.selectedValue) != destino.ativo.valor;
				alterado = alterado || this.comboCodificacao.selectedItem != null ? (comboCodificacao.selectedItem as CodificacaoArquivoEnum).codigo != destino.codificacaoArquivo : true;
			}
			
			return alterado;
		}
		
		private function obterDefinicoes():void {
			MostraCursor.setBusyCursor("Obtendo definições...", this, MostraCursor.CURSOR_PESQUISAR);
			servicoDefinicoes.obterDefinicoes(new RequisicaoReqDTO());
		}
		
		private function retornoObterDefinicoes(evento:ResultEvent):void {
			this.comboCodificacao.dataProvider = evento.result.dados.listaTiposCodificacao;
			componenteListaTiposInformacao.adicionarListaRegistrosDisponiveis(evento.result.dados.tiposInformacao);
			
			var registro:DestinoExportacaoVO = DestinoExportacaoVO(this.objeto);
			if(registro != null) {
				preencherComboCodificacao(registro.codificacaoArquivo);
				componenteListaTiposInformacao.adicionarListaRegistrosSelecionados(registro.tiposInformacao);
			}
		}
		
		private function preencherTiposInformacao(lista:ListCollectionView, selecionados:ListCollectionView):void {
			componenteListaTiposInformacao.limpar();
			
			if (MODO_EDICAO == modo || MODO_VISUALIZACAO == modo) {
				if (selecionados != null && selecionados.length > 0) {
					var tiposInformacaoSelecionados:ArrayCollection = new ArrayCollection();
					for(var i:Number = 0; i < lista.length; i++){
						for(var j:Number = 0; j < selecionados.length; j++){
							if(!lista[i].informacaoObrigatoriaExportacao && lista[i].codigo == selecionados[j].codigo){
								tiposInformacaoSelecionados.addItem(lista[i]);
								break;
							}
						}
					}
				}
			}
			componenteListaTiposInformacao.adicionarListaRegistrosSelecionados(tiposInformacaoSelecionados);
			componenteListaTiposInformacao.validateNow();
		}
		
		private function preencherComboCodificacao(valor:String):void {
			for each(var codificacao:Object in comboCodificacao.dataProvider){
				if(codificacao.codigo == valor){
					comboCodificacao.selectedItem = codificacao;
					return;
				}
			}
			comboCodificacao.selectedIndex = 0;
		}
    }
}