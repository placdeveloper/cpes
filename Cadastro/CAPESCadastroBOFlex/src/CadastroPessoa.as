package
{
	import flash.display.DisplayObject;
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.net.registerClassAlias;
	import flash.utils.ByteArray;
	
	import mx.core.Application;
	import mx.core.Container;
	import mx.events.FlexEvent;
	import mx.managers.IFocusManagerContainer;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.Base64Decoder;
	import mx.utils.ObjectUtil;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.Configuracoes;
	import br.com.bancoob.sisbr.ParametrosAssistenteAtendimento;
	import br.com.bancoob.sisbr.ProcuraClientePlataforma;
	import br.com.bancoob.sisbr.componentes.plataformas.BarraInferiorPlataformas;
	import br.com.bancoob.sisbr.componentes.pti.ObjetoEvent;
	import br.com.bancoob.sisbr.eventos.EventAssistenteAtendimento;
	import br.com.bancoob.sisbr.eventos.EventValidacaoAbertura;
	import br.com.bancoob.util.Servicos;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastrarPessoa.BotoesOpcoesPessoa;
	import br.com.sicoob.capes.cadastrarPessoa.frmEditarPessoa;
	import br.com.sicoob.capes.cadastrarPessoa.frmImagensPessoaCUC;
	import br.com.sicoob.capes.cadastrarPessoa.dto.PessoaLegadoReqDTO;
	import br.com.sicoob.capes.cadastrarPessoa.relatorios.BotaoRelatorios;
	import br.com.sicoob.capes.comum.enums.SituacaoRegistroEnum;
	import br.com.sicoob.capes.comum.enums.TipoPessoaEnum;
	import br.com.sicoob.capes.comum.util.FormatadorUtil;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.CamposFichaCadastralVO;
	import br.com.sicoob.capes.comum.vo.PessoaLegadoVO;
	import br.com.sicoob.capes.comum.vo.entidades.CidadaniaVO;
	import br.com.sicoob.capes.comum.vo.entidades.EnderecoFiscalVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoFormaConstituicaoEsferaAdministrativaVO;
	import br.com.sicoob.capes.comum.vo.entidades.UFVO;
	import br.com.sicoob.capes.comum.vo.entidades.pk.CidadaniaPK;
	import br.com.sicoob.capes.comum.vo.entidades.pk.EnderecoFiscalPK;
	import br.com.sicoob.capes.comum.vo.entidades.pk.TipoFormaConstituicaoEsferaAdministrativaPK;
	import br.com.sicoob.capes.corporativo.componentes.plataformaatendimento.ProcuraClientePlataformaCAPES;
	import br.com.sicoob.capes.corporativo.componentes.validacaoCadastral.ValidacaoCadastral;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	import br.com.sicoob.capes.utils.ConversaoPessoaUtil;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCustomizadoCAPES;
	
	public class CadastroPessoa extends TelaPlataformaAtendimentoCustomizadoCAPES {
		
		public static const CLASSE_SERVICO: String = 
				"br.com.sicoob.capes.cadastro.fachada.PessoaFachada";
		private static const OPERACAO_VALIDAR_TRANSICAO_CONJUGE : String = "validarTransicaoConjuge";
		private static const OPERACAO_COMPARTILHAR_CONJUGE : String = "compartilharConjuge";

		private var botoesOpcoes:BotoesOpcoesPessoa = new BotoesOpcoesPessoa();
		private var telaEdicao:frmEditarPessoa = new frmEditarPessoa();
		
		private var telaImagens:frmImagensPessoaCUC;
		private var janImagens:Janela = new Janela();
		private var servicos:Servicos = new Servicos();
		
		private var servicoValidarTransicaoConjuge:ServicoJava;
		private var servicoCompartilharConjuge:ServicoJava;
		
		private var pessoa:PessoaPlataformaVO = ProcuraClientePlataformaCAPES.getPessoaSelecionada();		

		public function CadastroPessoa() {
			super();
			
			servicoValidarTransicaoConjuge = ServicoJavaUtil.getServico(CLASSE_SERVICO, 
				"Verificando o cadastro do cônjuge ...", ResultEvent.RESULT, onResultTransicaoPessoaRelacionada);
			servicoCompartilharConjuge = ServicoJavaUtil.getServico(CLASSE_SERVICO, 
				"Compartilhando cônjuge na base da cooperativa ...", ResultEvent.RESULT, onResultCompartilhamentoPessoaRelacionada);
			
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.UF", UFVO);
			registerClassAlias("br.com.sicoob.capes.comum.negocio.vo.CamposFichaCadastralVO", CamposFichaCadastralVO);
			registerClassAlias("br.com.sicoob.capes.comum.negocio.enums.SituacaoRegistroEnum", SituacaoRegistroEnum);
			
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.pk.TipoFormaConstituicaoEsferaAdministrativaPK", TipoFormaConstituicaoEsferaAdministrativaPK);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.TipoFormaConstituicaoEsferaAdministrativa", TipoFormaConstituicaoEsferaAdministrativaVO);
			
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.pk.CidadaniaPK", CidadaniaPK);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Cidadania", CidadaniaVO);
			
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.pk.EnderecoFiscalPK", EnderecoFiscalPK);
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.EnderecoFiscal", EnderecoFiscalVO);
			
			this.creationPolicy = "all";
			this.addEventListener("carregou", init);
		}

		private function init(event:Event):void{
			carregarTextos();
			
			validarTransicaoConjuge();
		}
		
		private function continuaInit():void{
			telaEdicao.addEventListener(Modulo.REGISTRO_CARREGADO, registroCarregado);
			telaEdicao.addEventListener(Modulo.REGISTRO_GRAVADO, registroGravado);
			telaEdicao.addEventListener(telaEdicao.VERIFICAR_BOTOES_AUTORIZACAO, verificarBotoesAutorizacao);
			
			this.incluirBotoesAdicionais();
			this.adicionaTela(telaEdicao);
		}
		
		public override function dispose():void {
			super.dispose();
			this.removeEventListener("carregou", init);
			
			if(telaEdicao != null) {
				telaEdicao.dispose();
			}
			
			botoesOpcoes 	= null;
			telaEdicao 		= null;
			telaImagens 	= null;
			janImagens 		= null;
			servicos 		= null;
		}
		
		private function carregarTextos(): void {
	
			if(FormatadorUtil.TIPO_PESSOA_FISICA == this.pessoa.codTipoPessoa) {
				this.textoAcao 		= "PESSOA FÍSICA";
				this.textoModulo 	= "PESSOA FÍSICA";
				this.iconModulo 	= "br/com/bancoob/imagens/icos/User.png";
			} else {
				this.textoAcao 		= "PESSOA JURÍDICA";
				this.textoModulo 	= "PESSOA JURÍDICA";
				this.iconModulo 	= "br/com/bancoob/imagens/icos/office-building.png";
			}
			
		}
		
		private function verificarBotoesAutorizacao(evento:ObjetoEvent):void{
			botoesOpcoes.botoesAutorizacao.situacao = evento.objeto.situacao as SituacaoRegistroEnum;
			botoesOpcoes.botoesAutorizacao.bt_GED_GFT.visible = 
				botoesOpcoes.botoesAutorizacao.bt_GED_GFT.includeInLayout = evento.objeto.exibir as Boolean;
		}
		
		private function verificarExibirBotaoGedGft(evento:ObjetoEvent):void{
			botoesOpcoes.botoesAutorizacao.bt_GED_GFT.visible = 
				botoesOpcoes.botoesAutorizacao.bt_GED_GFT.includeInLayout = evento.objeto as Boolean;
		}

	    //--------------------------------------------------------------------------
	    //  Tratamento dos botÃµes.
	    //--------------------------------------------------------------------------
		override protected function excluirClicado(event:MouseEvent=null):void {
			Alerta.show("Tem certeza que deseja excluir este registro?", "Confirmação", 
					Alerta.ALERTA_PERGUNTA, null, exclusaoConfirmada);
		}	
		
		private function exclusaoConfirmada(evt:Event):void {
			telaEdicao.excluirRegistro(this.pessoa);
		}			
		
		override protected function cancelarClicado(event:MouseEvent=null):void	{
			telaEdicao.restaurarRegistro();
			telaEdicao.preencherCampos();
		}

		override protected function gravarClicado(event:MouseEvent=null):void {
			telaEdicao.gravarRegistro();
		}

		protected function imagensClicado(event:MouseEvent=null):void {			
			obterImagensPessoa(ProcuraClientePlataforma.getObjCliente());	
		}
		
		protected function btAutorizacaoClicado(event:MouseEvent=null):void {			
			this.telaEdicao.setarRegistroAutorizacao();
		}
		
		private function obterImagensPessoa(obj:Object):void
		{
 			MostraCursor.setBusyCursor("Carregando Registros ...", Application.application, MostraCursor.CURSOR_PESQUISAR);
			 												
			var vo:PessoaLegadoVO = new PessoaLegadoVO();
			vo.NumPessoa = obj["NUMPESSOA"];
			
			var req:PessoaLegadoReqDTO = new PessoaLegadoReqDTO();    
			req.DadosPessoa = vo;
			
				
			servicos.invocarServico(Configuracoes.pacoteServicos + ".sisbr.Pessoa",
									obterImagensPessoa_onResult, obterImagensPessoa_onError).
									obterImagensPessoa(req);

		}
				
		private function obterImagensPessoa_onResult(evt:ResultEvent):void
		{
			var base64Dec:Base64Decoder = new Base64Decoder();
        	var byteArrayAssinatura:ByteArray;
        	var byteArrayFoto:ByteArray;
			var registroImagem:PessoaLegadoVO = null;        	

			/*
			if(evt.result.dados["ImgErro"] != "") { 
				Alerta.show(evt.result.dados["ImgErro"], "Erro"); 
				return;
			}
			*/
			
			if(evt.result.dados["Imagens"].AssinaturaPessoa != null && String(evt.result.dados["Imagens"].AssinaturaPessoa).length>0){
				base64Dec.decode(String(evt.result.dados["Imagens"].AssinaturaPessoa));
				byteArrayAssinatura = base64Dec.toByteArray();					
			}
			
			if(evt.result.dados["Imagens"].FotoPessoa != null && String(evt.result.dados["Imagens"].FotoPessoa).length>0){
				base64Dec.decode(String(evt.result.dados["Imagens"].FotoPessoa));
				byteArrayFoto = base64Dec.toByteArray();					
			}

			registroImagem = evt.result.dados["Imagens"];
			
			if (telaImagens == null){
				telaImagens = new frmImagensPessoaCUC;				
				janImagens.title = "IMAGENS";
				janImagens.icone = "br/com/bancoob/imagens/icos/pictures.png";
				janImagens.addChild(DisplayObject(telaImagens));
				janImagens.addEventListener(FlexEvent.CREATION_COMPLETE,janImagensonComplete);
				janImagens.addEventListener(Janela.FECHAR_JANELA,janImagensonClose);				
				janImagens.width = 900;
				janImagens.height = 600;
			}
			
			telaImagens.byteArrayAssinatura = byteArrayAssinatura;
			telaImagens.byteArrayFoto = byteArrayFoto;
			telaImagens.registroImg = ObjectUtil.copy(registroImagem) as PessoaLegadoVO;
			telaImagens.registroBkpImagem = ObjectUtil.copy(registroImagem) as PessoaLegadoVO;			
			janImagens.abrir(DisplayObject(Application.application), true, true);
						
			MostraCursor.removeBusyCursor();
			
		}
		
		protected function janImagensonClose(event:Event):void
		{
			if ((telaImagens.registroImg.acaoImagemFoto == null) && (telaImagens.registroImg.acaoImagemAssinatura == null)){
				telaImagens.cancelarImagens();
			}				
		}
		
		protected function janImagensonComplete(event:FlexEvent):void
		{
			janImagens.setFocus();
			telaImagens.loadAssinaturaPessoa();
			telaImagens.loadFotoPessoa();					
		}
		
		private function obterImagensPessoa_onError(evt:FaultEvent):void
		{
			Alerta.show(evt.fault.faultString, "Erro");
			MostraCursor.removeBusyCursor(); 			
		}		
				
	    //--------------------------------------------------------------------------
	    //  Listeners
	    //--------------------------------------------------------------------------
		protected function registroCarregado(evt:Event):void
		{			
			exibirTelaEdicao();
		}		
		
		override protected function registroGravado(event:Event):void {
			super.registroGravado(event);
			
			verificarOperacaoPendente();
		}
				
	    //--------------------------------------------------------------------------
	    //  MÃ©todos auxiliares
	    //--------------------------------------------------------------------------
		private function exibirBotoesLista(edicao:Boolean=false):void {

			listaBotoes.mostraBotao(listaBotoes.botVer, false);
			listaBotoes.mostraBotao(listaBotoes.botAlterar, false);
			listaBotoes.mostraBotao(listaBotoes.botNovo, false);
			
			listaBotoes.mostraBotao(listaBotoes.botOk, true);			
			listaBotoes.mostraBotao(listaBotoes.botCancelar, true);
			
			listaBotoes.mostraBotao(listaBotoes.botExcluir, false);
			listaBotoes.mostraBotao(listaBotoes.botVoltar, !edicao);
			
			exibirBotoesAdicionais(edicao);
		}		
		
		private function incluirBotoesAdicionais(): void {
			this.setBotoesAdicionais(botoesOpcoes);
			botoesOpcoes.btImagens.addEventListener(MouseEvent.CLICK, imagensClicado);
			
			botoesOpcoes.botoesAutorizacao.bt_GED_GFT.addEventListener(MouseEvent.CLICK, btVisualizarDocumentoClicado);
			
			botoesOpcoes.botoesAutorizacao.bt_A_ENCAMINHAR.addEventListener(MouseEvent.CLICK, btAutorizacaoClicado);
			botoesOpcoes.botoesAutorizacao.bt_BLOQUEADO.addEventListener(MouseEvent.CLICK, btAutorizacaoClicado);
			botoesOpcoes.botoesAutorizacao.bt_DEVOLVIDO.addEventListener(MouseEvent.CLICK, btAutorizacaoClicado);
			botoesOpcoes.botoesAutorizacao.bt_EM_AUTORIZACAO.addEventListener(MouseEvent.CLICK, btAutorizacaoClicado);
			
			botoesOpcoes.btnRenovacao.addEventListener(MouseEvent.CLICK, renovacaoClicado);
			
			botoesOpcoes.btRelatorios.addEventListener(MouseEvent.CLICK, botaoRelatorioClicado);
			
			botoesOpcoes.btValidacaoCadastral.addEventListener(MouseEvent.CLICK, botaoValidacaoCadastralClicado);
		}		
		
		protected function renovacaoClicado(event:MouseEvent=null):void {
			Alerta.show("Confirmar a Renovação Cadastral para esta pessoa?", "Confirmar Renovação", Alerta.ALERTA_PERGUNTA, null, renovarCadastro);
		}
		
		private function renovarCadastro(evt:Event):void {
			telaEdicao.renovarCadastro();
		}		
		
		private function exibirBotoesAdicionais(edicao:Boolean): void {
			botoesOpcoes.btRelatorios.visible = edicao;
			botoesOpcoes.btImagens.visible = edicao;
		}
		
	    //--------------------------------------------------------------------------
	    //  ConfiguraÃ§Ã£o de destino dos serviÃ§os.
	    //--------------------------------------------------------------------------		
		protected override function configurarDestinosServicos(destinoVO:DestinoVO):void{
			this.destino = destinoVO;
			servicoValidarTransicaoConjuge.configurarDestino(destinoVO);
			servicoCompartilharConjuge.configurarDestino(destinoVO);
			telaEdicao.configurarDestinosServicos(destinoVO);
			
		}			
		
	    //--------------------------------------------------------------------------
	    //  Controle de exibiÃ§Ã£o de telas.
	    //--------------------------------------------------------------------------
		private function exibirTelaEdicao():void {

			_novo = false;			
			super.selecionaTela(telaEdicao);
			exibirBotoesLista(true);

			this.validateNow();
			telaEdicao.validateNow();
			
			
			if(isRegistroEmAprovacao()) {
				desabilitarControles(telaEdicao as Container);
			} else {
				habilitarControles(telaEdicao as Container);					
			}		
			
			telaEdicao.preencherCampos();
			verificarRegistroEmAlteracao();
			systemManager.activate(Application.application as IFocusManagerContainer);	
		}
		
		protected function verificarRegistroEmAlteracao():void
		{
			if (isRegistroEmAprovacao()) {
				Alerta.show("O registro encontra-se em alteração, favor aguardar a finalização do fluxo de autorização.","Aviso");
			}
		}
		
		protected function verificarOperacaoPendente():void {
			if (isRegistroEmAprovacao()) {
				Alerta.show("Operação enviada para autorização do responsável, acesse o módulo \"Cliente\" opção \"Autorização\" para acompanhar.","Aviso");			
			}
		} 
		
		private function isRegistroEmAprovacao():Boolean {
			return telaEdicao.isRegistroBloqueadoAlteracao();
		}
		
		private function botaoRelatorioClicado(evento:Event):void {
			var janela:Janela = new Janela();
			
			var botaoRelatorios:BotaoRelatorios = new BotaoRelatorios();
			botaoRelatorios.configurarDestino(this.destino);
			botaoRelatorios.registro = telaEdicao.registro;
			
			janela.title = "Relatórios";
			janela.addChild(botaoRelatorios); 
			janela.abrir(DisplayObject(Application.application), true);
		}
		
		private function botaoValidacaoCadastralClicado(evento:Event):void {
			var janelaValidacaoCadastral:Janela = new Janela();
			var validacaoCadastral:ValidacaoCadastral = new ValidacaoCadastral(null, false);
			
			janelaValidacaoCadastral.title = "VALIDAÇÃO CADASTRAL";
			janelaValidacaoCadastral.icone = "br/com/bancoob/imagens/icos/apply.png";
			janelaValidacaoCadastral.removeAllChildren();
			janelaValidacaoCadastral.addChild(DisplayObject(validacaoCadastral));
			
			this.parentDocument.addEventListener(BarraInferiorPlataformas.FECHAR_PLATAFORMA, validacaoCadastral.fechar);
			this.parentDocument.addEventListener(BarraInferiorPlataformas.VOLTAR_SISBR, validacaoCadastral.fechar);
			janelaValidacaoCadastral.abrir(DisplayObject(Application.application), false, true);
		}
					
	    //--------------------------------------------------------------------------
	    //  MÃ©todos para o assistente de atendimento
	    //--------------------------------------------------------------------------
		override public function trocarTelaSair(params:ParametrosAssistenteAtendimento=null):void {
			if(listaBotoes.botOk.enabled && !telaEdicao.verificarAlteracoes()){
				telaEdicao.gravarRegistro();
			}else{
				this.dispatchEvent(new EventAssistenteAtendimento(EventAssistenteAtendimento.EVENTO_CONFIRMAR_MUDANCA_TELA));
			}			
		}	
		
		protected function btVisualizarDocumentoClicado(event:MouseEvent=null):void {
			this.telaEdicao.exibirDetalhamentoGedGft();
		}
		
		private function validarTransicaoConjuge():void {			
			if(TipoPessoaEnum.PESSOA_FISICA.codigo == this.pessoa.codTipoPessoa 
				&& !isNaN(this.pessoa.idPessoa) && this.pessoa.idPessoa != 0){
				var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
				dto.dados.pessoaTela = getPessoaSelecionada();
				dto.dados.produtosBancoob = this.produtosBancoob;
				servicoValidarTransicaoConjuge.getOperation(OPERACAO_VALIDAR_TRANSICAO_CONJUGE).send(dto);
			}else{
				continuaInit();
			}
		}
		
		private function onResultTransicaoPessoaRelacionada(evento : ResultEvent) : void {
			var existeTransicao:Boolean = evento.result.dados.existeTransicao as Boolean;
			if(!existeTransicao){
				compartilhaPessoaRelacionada();
			} else {
				//Continua o processo da tela
				continuaInit();
			}
		}
		
		private function compartilhaPessoaRelacionada():void{
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.pessoaTela = getPessoaSelecionada();
			dto.dados.produtosBancoob = this.produtosBancoob;
			servicoCompartilharConjuge.getOperation(OPERACAO_COMPARTILHAR_CONJUGE).send(dto);
		}
		
		private function onResultCompartilhamentoPessoaRelacionada(evento:ResultEvent):void {
			// TODO exibir mensagem cadastro compartilhado com sucesso.
			var isCompSucesso:Boolean = evento.result.dados.isCompSucesso as Boolean;
			if(isCompSucesso){
				//Continua o processo da tela
				continuaInit();
			}
		}
		
		public static function getPessoaSelecionada():PessoaCompartilhamentoVO {                                                     
			return ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(pessoa);                                             
		}
		
		public function voltaTela(evento:Event):void {	                                                             
			this.dispatchEvent(new EventValidacaoAbertura(EventValidacaoAbertura.EVENTO_VALIDACAO_ERRO));                            
		}
		
		/*protected function verificarAlteracoes():Boolean {
			return this.telaEdicao.verificarAlteracoes();
		}*/
	}
}