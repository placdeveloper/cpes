package {
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.componentes.eventos.EventNavegacao;
	import br.com.bancoob.componentes.paginacao.BarraPaginacao;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.sisbr.relatorios.dto.ParametroDTO;
	import br.com.bancoob.sisbr.relatorios.util.RelatorioUtil;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastroGrupoCompartilhamento.CadastroGrupoCompartilhamentoView;
	import br.com.sicoob.capes.cadastroGrupoCompartilhamento.cadastrarCompartilhamentoCadastro.CompartilhamentoCadastroEdicao;
	import br.com.sicoob.capes.cadastroGrupoCompartilhamento.cadastrarGrupoCompartilhamento.EditarGrupoCompartilhamento;
	import br.com.sicoob.capes.cadastroGrupoCompartilhamento.cadastrarGrupoCompartilhamento.GrupoCompartilhamentoEdicao;
	
	public class CadastroGrupoCompartilhamento extends CadastroGrupoCompartilhamentoView {
		
		private var servico:ServicoJava = new ServicoJava();
		private var formEdicaoCompartCadast:CompartilhamentoCadastroEdicao = new CompartilhamentoCadastroEdicao();
		private var formEdicaoGrupoCompart:GrupoCompartilhamentoEdicao = new GrupoCompartilhamentoEdicao();
		private var formAlterarGrupoCompart:EditarGrupoCompartilhamento = new EditarGrupoCompartilhamento();
		private var janelaEdicaoCompartCadast:Janela = new Janela();
		private var janelaEdicaoGrupoCompart:Janela = new Janela();
		private var janelaAlterarGrupoCompart:Janela = new Janela();
		
		private var _listaGrupos:ArrayCollection = new ArrayCollection();
		private var nomeGrupo:String;

		private var TITULO_JANELA:String = "Alterar o vínculo da instituição";
		private static const DESTINO_CAPES : String = "destinoCAPES";
		
		public function CadastroGrupoCompartilhamento() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE,onCreateComplete);
			include 'br/com/sicoob/capes/utils/RegistroClasses.as';
		}

		private function onCreateComplete(event:FlexEvent): void {
			servico.source = "br.com.sicoob.capes.cadastro.fachada.GrupoCompartilhamentoFachada";
			
			servico.obterDefinicoes.addEventListener(ResultEvent.RESULT, obterDefinicoesOnResult);
			servico.listarInstuicoesPorCompartilhamento.addEventListener(ResultEvent.RESULT, pesquisarPorGrupoOnResult);
			servico.excluirGrupoCompartilhamento.addEventListener(ResultEvent.RESULT, excluirGrupoCompartilhamentoOnResult);
			servico.excluirGrupos.addEventListener(ResultEvent.RESULT, excluirGrupoOnResult);
			
			barraPaginacao.addEventListener(BarraPaginacao.EVENT_NAVEGACAO, navegar);
			formEdicaoCompartCadast.addEventListener(formEdicaoCompartCadast.RECARREGAR_COMBO, recarregarComboGrupos);
			
			btnExcluir.addEventListener(MouseEvent.CLICK, btnExcluirOnClick);
			btnFechar.addEventListener(MouseEvent.CLICK, fechar);
			btnImprimir.addEventListener(MouseEvent.CLICK, gerarRel);
			
			btnNovoGrupo.addEventListener(MouseEvent.CLICK, novoGrupoOnClick);
			btnAlterarGrupo.addEventListener(MouseEvent.CLICK, alterarGrupoOnClick);
			btnExcluirGrupo.addEventListener(MouseEvent.CLICK, excluirGrupoOnClick);
			
			btnIncluirInsti.addEventListener(MouseEvent.CLICK, abrirEdicaoGrupoCompart);
			btnAlterarInsti.addEventListener(MouseEvent.CLICK, abrirAlteracaoGrupoCompartilhamento);			
			
			btFiltrar.addEventListener(MouseEvent.CLICK, pesquisarPorGrupo);
			btLimpar.addEventListener(MouseEvent.CLICK, limparFiltro);
			
			janelaEdicaoCompartCadast.addChild(formEdicaoCompartCadast);
			janelaEdicaoCompartCadast.addEventListener("fecharJanela", janelaEdicaoCompartCadastOnClose);
						
			janelaEdicaoGrupoCompart.addChild(formEdicaoGrupoCompart);
			janelaEdicaoGrupoCompart.addEventListener("fecharJanela", janelaEdicaoGrupoCompartOnClose);
			
			formAlterarGrupoCompart.addEventListener(formAlterarGrupoCompart.RECARREGAR_LISTA, recarregarLista);
			janelaAlterarGrupoCompart.addChild(formAlterarGrupoCompart);
			
			cmbGrupo.addEventListener(ListEvent.CHANGE, verificarBotoes);
			
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], onDestinoCAPESRecuperado);
		}
		
		private function onDestinoCAPESRecuperado(destino:DestinoVO):void {
			servico.configurarDestino(destino);
			formEdicaoCompartCadast.configurarDestino(destino);
			formEdicaoGrupoCompart.configurarDestino(destino);
			this.destino = destino;
			obterDefinicoes();
		}
		
		protected function janelaEdicaoCompartCadastOnClose(event:Event):void {
			if(formEdicaoCompartCadast.isHouveAlteracao){
				obterDefinicoes();
			}
		}
		
		protected function janelaEdicaoGrupoCompartOnClose(event:Event):void {
			if(formEdicaoGrupoCompart.isHouveAlteracao){
				obterDefinicoes();
			}
		}
		
		protected function novoGrupoOnClick(event:MouseEvent):void {
			abrirEdicaoCompartCadast(true);
		}
		
		protected function alterarGrupoOnClick(event:MouseEvent):void {
			abrirEdicaoCompartCadast(false);
		}
		
		private function obterDefinicoes():void {
			var requisicaoDto:RequisicaoReqDTO = new RequisicaoReqDTO();			
			MostraCursor.setBusyCursor("Buscando Grupos", this, MostraCursor.CURSOR_PESQUISAR);
			servico.obterDefinicoes(requisicaoDto);
		}
		
		private function obterDefinicoesOnResult(event:ResultEvent):void {
			_listaGrupos = event.result.dados.listaGrupoCompartilhamentoCadastro;
			
			var array:ArrayCollection = new ArrayCollection();
			array.source = _listaGrupos.source;
			cmbGrupo.dataProvider = array;
			MostraCursor.removeBusyCursor();
			
			this.grdDados.dataProvider = null;
		}
		
		private function navegar(evt:EventNavegacao):void{
			pesquisar(evt.pagina);
		}

		private function pesquisarPorGrupo(event:MouseEvent = null):void {
			MostraCursor.setBusyCursor("Buscando Instituições", this, MostraCursor.CURSOR_PESQUISAR);
			pesquisar(1);
		}
		
		private function pesquisar(pagina:int):void{
			var requisicaoDto:RequisicaoReqDTO = new RequisicaoReqDTO();
			requisicaoDto.dados.codCompartilhamentoCadastro = cmbGrupo.selectedItem != null ? cmbGrupo.selectedItem.codigo : NaN;
			requisicaoDto.dados.codInstituicao = idUnidadeInst.valor != 0 ? idUnidadeInst.valor : NaN;
			requisicaoDto.dados.pagina = pagina;
			
			servico.listarInstuicoesPorCompartilhamento(requisicaoDto);
		}
		
		private function pesquisarPorGrupoOnResult(event:ResultEvent):void {
			grdDados.dataProvider = event.result.dados.lista;
			barraPaginacao.pagina = event.result.paginaAtual;
			barraPaginacao.totalPaginas = event.result.qtdPaginas;
			MostraCursor.removeBusyCursor();
		}

		private function abrirEdicaoCompartCadast(isIncluir:Boolean = false):void {
			formEdicaoCompartCadast.limpar();
			
			if(!isIncluir){
				if(cmbGrupo.selectedItem == null){
					Alerta.show("Selecione um grupo para alterar.", "Aviso!");
					return;
				}
				
				formEdicaoCompartCadast.itemEdicao = cmbGrupo.selectedItem;
			}
			
			janelaEdicaoCompartCadast.abrir(this, true);
			formEdicaoCompartCadast.isIncluir = isIncluir;
			
			if(isIncluir){
				formEdicaoCompartCadast.pesquisarProximoCodigo();
			}else{
				formEdicaoCompartCadast.preencherCampos();
			}
			
		}

		private function abrirEdicaoGrupoCompart(isIncluir:Boolean=false):void{
			if(cmbGrupo.selectedItem == null){
				Alerta.show("Selecione um grupo para para vincular as instituições.", "Aviso!");
				return;
			}
			
			janelaEdicaoGrupoCompart.abrir(this, true);
			formEdicaoGrupoCompart.grupoSelecionado = cmbGrupo.selectedItem;
			var titulo:String = "Vincular Instituições ao Grupo de Compartilhamento " + cmbGrupo.selectedItem.descricao;
			janelaEdicaoGrupoCompart.barraTitulo.lbTitulo.text = titulo;
		}

		protected function btnExcluirOnClick(event:MouseEvent):void	{	
			var numeroInstituicao:String;
			//Pergunta Excluir
			if(grdDados.selectedItem){
				nomeGrupo = grdDados.selectedItem.descricao;
				numeroInstituicao = grdDados.selectedItem.numero;
				//Excluir Instituições
				Alerta.show("Deseja Excluir a instituição " + numeroInstituicao + " do Grupo " + nomeGrupo + "?","Desvincular Instituição do Grupo?", Alerta.ALERTA_PERGUNTA, null, excluirGrupoCompartilhamento);
			}
		}
		
		private function excluirGrupoOnClick(event:Event = null):void {
			nomeGrupo = cmbGrupo.selectedItem.descricao;
			//Excluir Grupo
			Alerta.show("Deseja excluir o Grupo "+ nomeGrupo +"?", "Excluir Grupo " +nomeGrupo+ "?", Alerta.ALERTA_PERGUNTA, null, excluirGrupo);
		}

		private function excluirGrupoCompartilhamento(event:Event=null):void {
			MostraCursor.setBusyCursor("Excluindo Grupo Compartilhamento ", this, MostraCursor.CURSOR_EXCLUIR);
			var requisicaoDto:RequisicaoReqDTO = new RequisicaoReqDTO();
			requisicaoDto.dados.idGrupoCompartilhamento = grdDados.selectedItem.idGrupoCompartilhamento;
			servico.excluirGrupoCompartilhamento(requisicaoDto);
		}
		
		private function excluirGrupo(event:Event=null):void {	
			MostraCursor.setBusyCursor("Excluindo Grupo " + nomeGrupo, this, MostraCursor.CURSOR_EXCLUIR);
			var requisicaoDto:RequisicaoReqDTO = new RequisicaoReqDTO();
			var codigo:int = cmbGrupo.selectedItem.codigo
			requisicaoDto.dados.codCompartilhamentoCadastro = codigo;
			servico.excluirGrupos(requisicaoDto);
		}
		
		private function excluirGrupoOnResult(event:ResultEvent):void {
			MostraCursor.removeBusyCursor();
			obterDefinicoes();
		}
		
		private function excluirGrupoCompartilhamentoOnResult(event:ResultEvent):void {
			MostraCursor.removeBusyCursor();
			obterDefinicoes();
		}
		
		private function gerarRel(event:MouseEvent):void {
			if(cmbGrupo.selectedItem != null) {
				var dto:ParametroDTO = new ParametroDTO();
				dto.dados.codCompartilhamentoCadastro = cmbGrupo.selectedItem.codigo;
				RelatorioUtil.create().emitirRelatorio("capes/relatorio/RelatorioGrupoCompartilhamentoServicoRemote", dto, "relatorioGrupoCompartilhamento", destino);	
			} else {
				Alerta.show("Para gerar o relatório, selecione um grupo de compartilhamento.", "Relatório de grupo de compartilhamento", Alerta.ALERTA_ERRO);
			}
		}
		
		private function fechar(event:Event=null):void{
			fecharJanela();
		}
		
		private function recarregarComboGrupos(evento:Event):void{
			obterDefinicoes();
		}
		
		private function limparFiltro(event:MouseEvent = null):void {
			cmbGrupo.selectedIndex = 0;
			idUnidadeInst.text = "";
			
			verificarBotoes();
		}
		
		private function verificarBotoes(event:Event = null):void {
			if(cmbGrupo.selectedItem == null){
				setarBotoes(false);
			}else {
				setarBotoes(true);
			}
		}
		
		private function setarBotoes(valor:Boolean):void{
			btnAlterarGrupo.enabled = valor;
			btnIncluirInsti.enabled = valor;
			btnExcluirGrupo.enabled = valor;
			btnImprimir.enabled = valor;
		}
		
		private function abrirAlteracaoGrupoCompartilhamento(evento:Event = null):void{
			formAlterarGrupoCompart.atualizarListaGrupo(_listaGrupos);
			
			var objeto:Object = grdDados.selectedItem;
			formAlterarGrupoCompart.setarObjeto(objeto);
			
			formAlterarGrupoCompart.atualizarCampos();
			
			janelaAlterarGrupoCompart.title = TITULO_JANELA;
			janelaAlterarGrupoCompart.abrir(this, true);
		}
		
		private function recarregarLista(evento:Event = null):void {
			pesquisar(1);
		}
		
	}
}