package br.com.sicoob.capes.corporativo.componentes.validacaoCadastral {
	
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.DateTimeEntity;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.componentes.pti.ObjetoEvent;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.FormatUtil;
	import br.com.bancoob.util.FormataData;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.corporativo.componentes.plataformaatendimento.ProcuraClientePlataformaCAPES;
	import br.com.sicoob.capes.corporativo.componentes.validacaoCadastral.vo.ValidacaoCadastralVO;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	public class ValidacaoCadastral extends ValidacaoCadastralView {
		
		private static const EVENTO_DESTINO_RECUPERADO:String = "destinoRecuperado";
		private static const DESTINO_CAPES:String = "servicosJavaCapes";
		
		private static const CLASSE_SERVICO:String = "br.com.sicoob.capes.cadastro.fachada.ValidacaoCadastroFachada";
		private static const CLASSE_SERVICO_RELATORIO:String = "br.com.sicoob.capes.relatorio.negocio.fachada.RelatorioValidacaoCadastralFachada";
		
		private static const OPERACAO_OBTER_DEFINICOES:String = "obterDefinicoesComponente";
		private static const OPERACAO_REVALIDAR_CADASTRO:String = "revalidarCadastro";
		
		private static const MENSAGEM_OBTER_DEFINICOES:String = "Obtendo definições...";
		private static const MENSAGEM_REVALIDAR_CADASTRO:String = "Revalidando o cadastro do cliente...";
		private static const MENSAGEM_ESPERA_VALIDACAO_CADASTRAL:String = "Obtendo dados atualizados...";
		
		private static const EVENTO_ABRIR:String = "";
		
		private static const METODO_RELATORIO:String = "pesquisar";
		
		public static const TIPO_RELATORIO_ANALITICO:int = 0;
		public static const TIPO_RELATORIO_SINTETICO:int = 1;
		
		public static const NOME_RELATORIO_ANALITICO:String = "RELATORIO_VALIDACAO_CADASTRAL_ANALITICO";
		public static const NOME_RELATORIO_SINTETICO:String = "RELATORIO_VALIDACAO_CADASTRAL_SINTETICO";
		
		private var servicoDefinicoes:ServicoJava = new ServicoJava();
		private var servicoValidacao:ServicoJava = new ServicoJava();
		
		private var _errosValidacao:ArrayCollection = new ArrayCollection();
		private var _dataUltimaAtualizacao:DateTimeEntity;
		private var _dataUltimaValidacao:DateTimeEntity;
		
		private var pessoaSelecionada:PessoaPlataformaVO;
		private var tipoRegra:String = null;
		private var perfilCadastro:String = null;
		private var habilitaFechamentoAutomatico:Boolean;
		private var habilitaBotaoRevalidar:Boolean = true;
		private var considerarOrdemPerfilCadastro:Boolean;
		
		public function ValidacaoCadastral(tipoRegra:String=null, habilitaFechamentoAutomatico:Boolean = true, considerarOrdemPerfilCadastro:Boolean = true, habilitaBotaoRevalidar:Boolean = true) {
			super();
			
			registerClassAlias("br.com.sicoob.capes.cadastro.negocio.vo.ValidacaoCadastralVO", ValidacaoCadastralVO);
			
			this.tipoRegra = tipoRegra;
			this.habilitaFechamentoAutomatico = habilitaFechamentoAutomatico;
			this.considerarOrdemPerfilCadastro = considerarOrdemPerfilCadastro;
			this.habilitaBotaoRevalidar = habilitaBotaoRevalidar;
				
			servicoDefinicoes.source = CLASSE_SERVICO;
			servicoDefinicoes.mensagemEspera = MENSAGEM_OBTER_DEFINICOES;
			servicoDefinicoes.bloquearOperacao = true;
			servicoDefinicoes.addEventListener(ResultEvent.RESULT, retorno_obter_definicoes);
			
			servicoValidacao.source = CLASSE_SERVICO;
			servicoValidacao.mensagemEspera = MENSAGEM_REVALIDAR_CADASTRO;
			servicoValidacao.bloquearOperacao = true;
			servicoValidacao.addEventListener(ResultEvent.RESULT, retorno_validar_cadastro);
			
			this.addEventListener(FlexEvent.CREATION_COMPLETE, iniciar);
		}
		
		private function iniciar(evt:FlexEvent):void {
			exibirJanela(false);
			
			botaoFechar.addEventListener(MouseEvent.CLICK, fechar);
			botaoImprimir.addEventListener(MouseEvent.CLICK, imprimir);
			botaoRevalidar.addEventListener(MouseEvent.CLICK, revalidar);
			if(!habilitaBotaoRevalidar) {
				botaoRevalidar.visible = false;
			}
			
			pessoaSelecionada = ProcuraClientePlataformaCAPES.getPessoaSelecionada();
			
			inicializarServicos();
		}
		
		private function obterDefinicoes():void {
			MostraCursor.setBusyCursor(MENSAGEM_OBTER_DEFINICOES, Application.application, MostraCursor.CURSOR_PESQUISAR);
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.idInstituicao = pessoaSelecionada.idInstituicao;	
			//dto.dados.idPessoa = pessoaSelecionada.idPessoa;
			dto.dados.idPessoaCompartilhamento = pessoaSelecionada.idCompartilhamento;
			dto.dados.tipoRegra = tipoRegra;
			dto.dados.perfilCadastro = perfilCadastro;
			dto.dados.considerarOrdemPerfilCadastro = considerarOrdemPerfilCadastro;
			
			servicoDefinicoes.getOperation(OPERACAO_OBTER_DEFINICOES).send(dto);
		}
		
		public function fechar(evento:Event = null):void {
			pegaJanela().fecharJanela();
		}
		
		private function imprimir(evento:MouseEvent = null):void {
			var dto:ParametroDTO = new ParametroDTO();
			
			dto.dados.tipo = TIPO_RELATORIO_ANALITICO;
			dto.dados.idInstituicao = pessoaSelecionada.idInstituicao;
			dto.dados.cpfCnpj = pessoaSelecionada.cpfCnpj;
			
			RelatorioUtil.create().emitirRelatorio("capes/relatorio/RelatorioValidacaoCadastralServicoRemote", dto, ValidacaoCadastral.NOME_RELATORIO_ANALITICO, destino, "Emitindo Relatório...");
		}
		
		private function revalidar(evento:MouseEvent = null):void {
			MostraCursor.setBusyCursor(MENSAGEM_REVALIDAR_CADASTRO, Application.application, MostraCursor.CURSOR_PESQUISAR);
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.listaFalhas = _errosValidacao;
			dto.dados.idPessoa = pessoaSelecionada.idPessoa;
			dto.dados.idInstituicao = pessoaSelecionada.idInstituicao;
			
			servicoValidacao.getOperation(OPERACAO_REVALIDAR_CADASTRO).send(dto);
		}
		
		private function retorno_obter_definicoes(evento:ResultEvent):void {
			var listaFalhas:ArrayCollection = evento.result.dados.listaFalhas;
			_dataUltimaAtualizacao = evento.result.dados.dataUltimaAtualizacao;
			_dataUltimaValidacao = evento.result.dados.dataUltimaValidacao;
			
			_errosValidacao.removeAll();
			
			if(habilitaFechamentoAutomatico && (listaFalhas == null || listaFalhas.length <= 0)){
				fechar();
			} else {
				_errosValidacao.addAll(listaFalhas);
				exibirCampos();
				verificarExibicaoBotaoImprimir();
				exibirJanela(true);
			}
			
			MostraCursor.removeBusyCursor();
		}
		
		private function retorno_validar_cadastro(evento:ResultEvent):void {
			MostraCursor.removeBusyCursor();
			
			obterDefinicoes();
		}
		
		private function exibirCampos():void {
			labelCpfCnpj.text = FormatUtil.formataCPFCNPJ(pessoaSelecionada.cpfCnpj);
			labelDataUltimaAtualizacao.text = FormataData.formataDataHora(_dataUltimaAtualizacao != null ? _dataUltimaAtualizacao.data : null);
			labelDataUltimaValidacao.text = FormataData.formataDataHora(_dataUltimaValidacao != null ? _dataUltimaValidacao.data : null);
			
			tabela.dataProvider = _errosValidacao;
		}
		
		private function exibirJanela(valor:Boolean):void {
			pegaJanela().visible = 
				pegaJanela().includeInLayout = valor;
		}
		
		private function verificarExibicaoBotaoImprimir(): void {
			if(pessoaSelecionada != null && (isNaN(pessoaSelecionada.idPessoaInstituicao) || pessoaSelecionada.idPessoaInstituicao == 0)) {
				botaoImprimir.visible = 
					botaoImprimir.includeInLayout = false;
			}else {
				botaoImprimir.visible =
					botaoImprimir.includeInLayout = true;
			}
		}
		
	    //--------------------------------------------------------------------------
	    //  Configuração de destino dos serviços.
	    //--------------------------------------------------------------------------

		private function inicializarServicos():void {
			if(this.destino == null || destino.tipo == DestinoVO.CANAL_NET) {
				PortalModel.portal.obterDefinicoesDestino(DESTINO_CAPES, configurarDestino);
			} else {
				configurarDestino();
			}
		}
		
		private function configurarDestino(destino:DestinoVO = null):void {
			if (destino != null) {
				this.destino = destino;
				dispatchEvent(new ObjetoEvent(EVENTO_DESTINO_RECUPERADO, destino));
			}
			servicoDefinicoes.configurarDestino(this.destino);
			servicoValidacao.configurarDestino(this.destino);
			
			obterDefinicoes();
		}
	}
}