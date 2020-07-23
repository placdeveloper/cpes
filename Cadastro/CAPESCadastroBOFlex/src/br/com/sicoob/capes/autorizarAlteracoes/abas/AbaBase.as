package br.com.sicoob.capes.autorizarAlteracoes.abas
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.collections.ListCollectionView;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Botao;
	import br.com.bancoob.componentes.cadastro.BarraBotoesListaCadastroView;
	import br.com.bancoob.componentes.cadastro.FormularioCadastro;
	import br.com.bancoob.componentes.input.Texto;
	import br.com.bancoob.componentes.painellista.PainelListaBanco;
	import br.com.bancoob.dto.ConsultaDto;
	import br.com.bancoob.dto.RequisicaoDTO;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.StringUtils;
	import br.com.bancoob.util.reflexao.ReflectionUtils;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.autorizarAlteracoes.AutorizacaoEdicao;
	import br.com.sicoob.capes.autorizarAlteracoes.PainelFiltroAutorizacaoView;
	import br.com.sicoob.capes.autorizarAlteracoes.PainelListaAutorizacoesView;
	import br.com.sicoob.capes.comum.enums.TipoAutorizacaoEnum;
	import br.com.sicoob.capes.comum.vo.entidades.AutorizacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.InstituicaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaVO;
	
	public class AbaBase extends AbaBaseView implements IAbaAutorizacao {
		
		
		protected var _pesquisaRealizada:Boolean = false;
		protected var _painelFiltro : PainelFiltroAutorizacaoView;
		
		public function AbaBase() {
			super();
		}
		
		//-----------------		
		// Callbacks
		//-----------------		
				
		protected override function init(evento : FlexEvent): void {
			
			super.init(evento);
			
			var painelListaBanco : PainelListaAutorizacoesView = PainelListaAutorizacoesView(this.painelLista);
			
			this.painelFiltro = PainelFiltroAutorizacaoView(painelListaBanco.painelFiltro);
			
			this.barraBotoesListaCadastro.exibirBotaoAlterar = false;
			this.barraBotoesListaCadastro.exibirBotaoExcluir = false;
			this.barraBotoesListaCadastro.exibirBotaoIncluir = false;
			this.barraBotoesListaCadastro.exibirBotaoLimpar = false;
			this.barraBotoesListaCadastro.exibirBotaoAjuda = false;
			
			this.painelFiltro.btLimpar.addEventListener(MouseEvent.CLICK, onBotaoLimparPressionado);
			
			var botaoVisualizar : Botao = (this.barraBotoesListaCadastro as BarraBotoesListaCadastroView).btnVisualizar;
			
			painelListaBanco.funcaoCriacaoDto = instanciarDtoConsulta;
			painelListaBanco.funcaoConfiguracaoDto = configurarDtoConsulta;
			painelListaBanco.metodoPesquisa = metodoPesquisa;
			painelListaBanco.callback = retornoPesquisa;
			
			this.addEventListener(Event.CHANGE, onAbaSelecionada);
			
			super.funcaoDuploClique = botaoVisualizarPressionado;
		}
		
		public function preencherDefinicoes(event : ResultEvent) : void {
			trace("preenchendo as definições");
			var filtro : PainelFiltroAutorizacaoView = PainelFiltroAutorizacaoView(
					this.painelLista.painelFiltro);
			filtro.cmbTipoAutorizacao.dataProvider = event.result.dados.listaTipoAutorizacao;
		}

		private function onAbaSelecionada(event:Event):void {
			if (!this.pesquisaRealizada) {
				pesquisar();
			}
		}
		
		private function onBotaoLimparPressionado(event:MouseEvent):void {
			botaoLimparPressionado();
			
			painelFiltro.codigoAutorizacao.text = "";
			painelFiltro.cpfCnpj.text = "";
			painelFiltro.numeroCooperativa.text = "";
			painelFiltro.idUnidadeInst.text = "";
			painelFiltro.cmbTipoAutorizacao.selectedIndex = 0;
		}
		
		//------------------------
		//Métodos auxiliares
		//------------------------
		
		public function instanciarDtoConsulta():ConsultaDto {
			trace("Instanciando o consultaDTO");
			return new ConsultaDto();
		}

		public function configurarDtoConsulta(dto: ConsultaDto) : void {
			trace("Configurando o consultaDTO");
			var filtro: AutorizacaoVO = new AutorizacaoVO();
			if (this.painelFiltro.codigoAutorizacao.valor > 0) {
				filtro.idAutorizacao = this.painelFiltro.codigoAutorizacao.valor;
			}
			if (this.painelFiltro.cpfCnpj.text != "") {
				filtro.pessoa = new PessoaVO();
				filtro.pessoa.cpfCnpj = substituirCaracteresEspeciais(this.painelFiltro.cpfCnpj.text);
			}
			if (this.painelFiltro.idUnidadeInst.text != "") {
				filtro.instituicaoDestino = new InstituicaoVO();
				filtro.instituicaoDestino.idUnidadeInst = this.painelFiltro.idUnidadeInst.valor;
			}
			if (this.painelFiltro.cmbTipoAutorizacao.selectedIndex > 0) {
			    filtro.tipoAutorizacao = TipoAutorizacaoEnum(this.painelFiltro.cmbTipoAutorizacao.selectedItem.tipoAutorizacao);
			}
			dto.filtro = filtro;
			
			trace("DTO de consulta configurado");
		}	
		
		private function substituirCaracteresEspeciais(valor:String):String {
			valor = StringUtils.replace(valor, ".", "");
			valor = StringUtils.replace(valor, ",", "");
			valor = StringUtils.replace(valor, "-", "");
			valor = StringUtils.replace(valor, "/", "");
			
			return valor;
		}
		
		//----------------------	
		// Métodos herdados
		//----------------------	
		
		protected override function montarDtoRecuperacao(objeto : Object) : RequisicaoDTO {
			var dto : RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.item = objeto;
			return dto;
		}
		
		protected override function informacoesRecuperadas(evento:ResultEvent):void {
			if (formularioCadastro.modo == FormularioCadastro.MODO_VISUALIZACAO) {
				exibirJanelaVisualizacao(ReflectionUtils.recuperarPropriedade(evento.result, nomePropriedadeItem));
			} else if (formularioCadastro.modo == FormularioCadastro.MODO_EDICAO) {
				exibirJanelaAlteracao(ReflectionUtils.recuperarPropriedade(evento.result, nomePropriedadeItem));
			}
		}
		
		protected override function exibirJanelaVisualizacao(objeto : Object):void {
			var form : AutorizacaoEdicao = AutorizacaoEdicao(this.formularioCadastro);
			form.camposTela = objeto.camposTela;
			form.listaProcedimento = ListCollectionView(objeto.atividades);
			form.historico = objeto.historico;
			form.documentosVigentes = objeto.documentosVigentes;
			super.exibirJanelaVisualizacao(recuperarItemSelecionado());
		}

		public function configurarDestino(destino:DestinoVO):void {
			
			ServicoJava(this.servicoRecuperacaoInformacoes).configurarDestino(destino);
			ServicoJava(formularioCadastro.servicoEdicao).configurarDestino(destino);
			
			var lista : PainelListaBanco = PainelListaBanco(this.painelLista);
			ServicoJava(lista.servicoPesquisa).configurarDestino(destino);
		}
		
		public function retornoPesquisa(event : ResultEvent) : void {
			trace("retornou");
			PainelListaBanco(this.painelLista).tabelaPaginada.montarConteudo(event.result);
		}
		
		//---------------------------
		// get/set
		//---------------------------
		
		protected function set painelFiltro(valor : PainelFiltroAutorizacaoView) : void {
			this._painelFiltro = valor;
		}
		protected function get painelFiltro() : PainelFiltroAutorizacaoView {
			return this._painelFiltro;
		}
		
		protected function get metodoPesquisa():String {
			return null;
		}
		
		public function pesquisar(pagina:int = 1):void {
			this.pesquisaRealizada = true;
			PainelListaBanco(this.painelLista).pesquisar(pagina);			
		}
		
		public function get pesquisaRealizada():Boolean {
			return _pesquisaRealizada;
		}
		public function set pesquisaRealizada(value:Boolean):void {
			_pesquisaRealizada = value;
		}
	}
}