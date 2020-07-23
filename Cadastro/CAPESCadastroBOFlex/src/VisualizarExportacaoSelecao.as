package {
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.sisbr.portal.spool.ISpoolTemporarios;
	import br.com.bancoob.sisbr.portal.spool.SpoolTemporariosFactory;
	import br.com.bancoob.sisbr.portal.spool.eventos.SpoolTemporariosEvento;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.ArquivoExportacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.DestinoExportacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoInformacaoVO;
	import br.com.sicoob.capes.visualizarExportacao.VisualizarExportacaoView;
	
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.events.FlexEvent;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	public class VisualizarExportacaoSelecao extends VisualizarExportacaoView {
		
		private static const CLASSE_SERVICO: String = "br.com.sicoob.capes.cadastro.fachada.VisualizarExportacaoFachada";
		private static const DESTINO_CAPES : String = "destinoCAPES";
		private var servicoDefinicoes:ServicoJava;
		private var servicoVisualizar:ServicoJava;
		
		public function VisualizarExportacaoSelecao() {
			super();
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoInformacao", TipoInformacaoVO);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.DestinoExportacao", DestinoExportacaoVO);
			registerClassAlias("br.com.sicoob.capes.cadastro.negocio.vo.ArquivoExportacaoVO", ArquivoExportacaoVO);
			addEventListener(FlexEvent.CREATION_COMPLETE, inicializar);
		}
		
		private function inicializar(event:FlexEvent): void {
			servicoDefinicoes = ServicoJavaUtil.getServico(CLASSE_SERVICO, "Obtendo definições...");
			servicoDefinicoes.addEventListener(ResultEvent.RESULT, retorno_obter_definicoes);
			
			servicoVisualizar = ServicoJavaUtil.getServico(CLASSE_SERVICO, "Obtendo arquivo para visualização...");
			servicoVisualizar.addEventListener(ResultEvent.RESULT, retorno_gerar);
			servicoVisualizar.addEventListener(FaultEvent.FAULT, retorno_gerar_erro);
			
			botaoGerar.addEventListener(MouseEvent.CLICK, acao_botao_gerar)
			botaoLimpar.addEventListener(MouseEvent.CLICK, acao_botao_limpar);
			botaoFechar.addEventListener(MouseEvent.CLICK, acao_botao_fechar);
			
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], destino_recuperado);
		}
		
		private function destino_recuperado(destino:DestinoVO):void {
			servicoDefinicoes.configurarDestino(destino);
			servicoVisualizar.configurarDestino(destino);
			obterDefinicoes();
		}
		
		private function obterDefinicoes():void {
			MostraCursor.setBusyCursor("Obtendo definições...", this, MostraCursor.CURSOR_PESQUISAR);
			var requisicaoDto:RequisicaoReqDTO = new RequisicaoReqDTO();
			servicoDefinicoes.obterDefinicoes(requisicaoDto);
		}
		
		private function retorno_obter_definicoes(evento:ResultEvent):void {
			comboExportacao.dataProvider = evento.result.dados.listaDestinos;
			acao_botao_limpar();
			MostraCursor.removeBusyCursor();
		}
		
		private function acao_botao_gerar(evento:MouseEvent = null):void {
			if(!campoDataMovimento.validar()){
				Alerta.show("Campo de preenchimento obrigatório!", "ERRO DE VALIDAÇÃO", Alerta.ALERTA_INFORMACAO);
				return;
			}
			
			executarSeValido(gerar);
		}
		
		private function gerar():void {
			MostraCursor.setBusyCursor("Obtendo o arquivo para visualização...", this, MostraCursor.CURSOR_PESQUISAR);
			
			var requisicaoDto:RequisicaoReqDTO = new RequisicaoReqDTO();
			requisicaoDto.dados.destino = comboExportacao.selectedItem as DestinoExportacaoVO;
			requisicaoDto.dados.dataMovimento = campoDataMovimento.selectedDate;
			servicoVisualizar.obterArquivoExportacao(requisicaoDto);
		}
		
		private function retorno_gerar(evento:ResultEvent):void {
			var arquivo:ArquivoExportacaoVO = evento.result.dados.arquivo;
			
			if(arquivo != null) {
				try	{
					var spool:ISpoolTemporarios = SpoolTemporariosFactory.getSpool("arquivosTemporariosCAPES");
					spool.gravarItem(arquivo.bytes, arquivo.nome, true);
					spool.addEventListener(SpoolTemporariosEvento.ARQUIVO_GRAVADO, arquivo_gravado);
				} catch(error:Error){
					Alerta.show("Erro ao gerar o arquivo de exportação: " + error.message, "Erro", Alerta.ALERTA_ERRO);
				}
			}
			
			MostraCursor.removeBusyCursor();
		}
		
		private function retorno_gerar_erro(evento:FaultEvent):void {
			MostraCursor.removeBusyCursor();
		}
		
		private function arquivo_gravado(evento:SpoolTemporariosEvento): void {
			Alerta.show("Arquivo de exportação gerado com sucesso no diretório: " + evento.diretorio, "Sucesso", Alerta.ALERTA_INFORMACAO);
		}
		
		private function acao_botao_limpar(evento:MouseEvent = null):void {
			comboExportacao.selectedIndex = 0;
			campoDataMovimento.selectedDate = null;
		}
		
		private function acao_botao_fechar(evento:MouseEvent = null):void {
			pegaJanela().fecharJanela();
		}
	}
}