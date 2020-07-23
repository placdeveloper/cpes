package br.com.sicoob.capes.alterarTributacao{

//	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.ParametrosAssistenteAtendimento;
	import br.com.bancoob.sisbr.componentes.PlataformaAtendimento.ITelaBasePlataformaAtendimento;
	import br.com.bancoob.sisbr.componentes.pti.ObjetoEvent;
	import br.com.bancoob.tipos.Booleano;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.sicoob.capes.comum.enums.SituacaoRegistroEnum;
	import br.com.sicoob.capes.comum.enums.TipoPessoaEnum;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.TributacaoVO;
	import br.com.sicoob.capes.comum.vo.VigenteVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaVO;
	import br.com.sicoob.capes.corporativo.componentes.plataformaatendimento.ProcuraClientePlataformaCAPES;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	import br.com.sicoob.capes.utils.ConversaoPessoaUtil;
	import br.com.sicoob.capes.utils.UploadDocGedUtil;
	import br.com.sicoob.capes.utils.autorizacao.DetalhamentoGedGft;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCustomizadoCAPES;
	
	import flash.display.DisplayObject;
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.events.IndexChangedEvent;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.ObjectUtil;

	public class frmEditarTributacao extends frmEditarTributacaoView implements ITelaBasePlataformaAtendimento {

		private var tributacaoVigenteBkp:TributacaoVO = new TributacaoVO();
		private var tributacaoAlteracaoBkp:TributacaoVO = new TributacaoVO();

		[Bindable]
		private var tributacaoVO:TributacaoVO = new TributacaoVO();
		
		private var tributacaoVigente:TributacaoVO = new TributacaoVO();
		private var tributacaoAlteracao:TributacaoVO = new TributacaoVO();
		
		private var _vigente:Boolean = true;

		//Informações da Pessoa
		private var pessoa:PessoaVO = null;

		/**
		 *	Serviços 
		 */
		public var servicoConsulta:ServicoJava;
		public var servicoSalvar:ServicoJava;
		public var servicoExcluir:ServicoJava;
		public var servicoValidarCadastro:ServicoJava;
		
		/**
		 *  Operações 
		 */
		static private const OPERACAO_OBTER_DEFINICOES: String = "obterDefinicoes";
		static private const OPERACAO_INCLUIR_DADOS: String = "incluirDados";
		static private const OPERACAO_ALTERAR_DADOS: String = "alterarDados";
		static private const OPERACAO_EXCLUIR_TRIBUTACAO: String = "excluirDados";
		static private const OPERACAO_OBTER_TRIBUTACAO: String = "obterDados";

		static private const CLASSE_SERVICO: String = 
			"br.com.sicoob.capes.cadastro.fachada.TributacaoFachada";
		
		public static const VERIFICAR_BOTOES_AUTORIZACAO:String = "verificarBotoesAltorizacao";
		
		//Guarda as informações da pessoa selecionada na plataforma
		private var pessoaSelecionada:PessoaPlataformaVO;
		
		private const ABA_DOCUMENTOS:Number = 1;
		
		//Objeto que guardará as informações para serem usadas no componente GED.
		private var definicoesComponenteGED:Object = new Object;
		
		private static const TITULO_JANELA:String = "DETALHAMENTO GED / GFT";

		//--------------------------------------------------------------------------
	    //  Propriedades
	    //--------------------------------------------------------------------------

	    /**
	     *  Construtor.
	     */
		public function frmEditarTributacao()
		{
			super();
			
			pessoaSelecionada = ProcuraClientePlataformaCAPES.getPessoaSelecionada();

			servicoConsulta = ServicoJavaUtil.getServico(CLASSE_SERVICO);
			servicoConsulta.addEventListener(ResultEvent.RESULT, retornoCarregarRegistro);
			servicoConsulta.addEventListener(FaultEvent.FAULT, erroRetornoCarregarRegistro);

			servicoSalvar = ServicoJavaUtil.getServico(CLASSE_SERVICO);
			servicoSalvar.addEventListener(ResultEvent.RESULT, retornoSalvar);
			
			servicoValidarCadastro = ServicoJavaUtil.getServico(CLASSE_SERVICO);
			servicoValidarCadastro.addEventListener(ResultEvent.RESULT, retornoValidar);

			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		//--------------------------------------------------------------------------
	    //  Métodos
	    //--------------------------------------------------------------------------
		private function init(evt:FlexEvent):void 
		{
			//Adiciona o evento ao trocar aba.
			this.navegacao.addEventListener(IndexChangedEvent.CHANGE, aoTrocarAba);
			this.abaDocumentos.enabled = pessoaSelecionada.utilizaGedGft && TipoPessoaEnum.PESSOA_JURIDICA.codigo == pessoaSelecionada.codTipoPessoa;
		}
		
		public override function dispose():void {
			super.dispose();
			abaDocumentos.dispose();
			navegacao.removeEventListener(Event.CHANGE, aoTrocarAba);
			navegacao.removeAllChildren();
			navegacao = null;
		}
		
		public function carregarRegistro(tributacao:Object=null):void
		{

			MostraCursor.setBusyCursor("Obtendo definições ...", 
				Application.application, MostraCursor.CURSOR_PROGRESSO);

			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			var objTributacao:TributacaoVO = new TributacaoVO();
			
			var pessoaCompartilhamento:PessoaCompartilhamentoVO = ConversaoPessoaUtil.
				converterVOParaPessoaCompartilhamento(TelaPlataformaAtendimentoCustomizadoCAPES.getPessoaPlataforma());
			
			objTributacao.pessoaCompartilhamento = pessoaCompartilhamento; 
			objTributacao.idPessoaCompartilhamento = pessoaCompartilhamento.idPessoaCompartilhamento;
			
			//Envia o tipo de pessoa para ser utilizado nas chaves de negócio.
			dto.dados.idTipoPessoa = pessoaSelecionada.codTipoPessoa;
			dto.dados.tributacao = objTributacao;

			servicoConsulta.getOperation(OPERACAO_OBTER_TRIBUTACAO).send(dto);
		}

		private function retornoCarregarRegistro(evt:ResultEvent):void {
			tributacaoVigente = evt.result.dados.tributacaoVigente;
			tributacaoAlteracao = evt.result.dados.tributacaoAlteracao;
			
			tributacaoVO = tributacaoVigente;
			
			tributacaoVigenteBkp = ObjectUtil.copy(tributacaoVigente) as TributacaoVO;
			tributacaoAlteracaoBkp = ObjectUtil.copy(tributacaoAlteracao) as TributacaoVO;
			
			//Atribui ao objeto as informações que serão utilizadas no componente GED,
			//configuradas na fachada.
			definicoesComponenteGED = evt.result.dados.definicoesComponenteGED;
			
			if (tributacaoVO == null && tributacaoAlteracao == null)
				novoRegistro();
			else {
				_novo = false;
				preencherCampos();
			}
			
			if(tributacaoVO != null){
				//Adiciona os documentos da tributação ao componente.
				this.abaDocumentos.carregarDocumentos(UploadDocGedUtil.criarListaDocumentos(tributacaoVO.documentosComprobatorios));
			}
			
			this.dispatchEvent(new Event(REGISTRO_CARREGADO));	
			
			verificarBotoesAutorizacao(tributacaoAlteracao != null ? tributacaoAlteracao.situacaoAprovacao : SituacaoRegistroEnum.VIGENTE, exibirBotaoDetalhamentoGedGft());
		}
		
		private function erroRetornoCarregarRegistro(evento:FaultEvent):void {
			this.abaDocumentos.enabled = false;
		}
		
		private function validarCadastro():void {
			servicoValidarCadastro.getOperation("validarCadastro").send(obterDTO());
		}
		
		private function retornoValidar(evt:ResultEvent):void {
			
		}
		
		private function obterDTO():RequisicaoReqDTO{
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			var objTributacao:TributacaoVO = new TributacaoVO();
			
			var pessoaCompartilhamento:PessoaCompartilhamentoVO = ConversaoPessoaUtil.
				converterVOParaPessoaCompartilhamento(TelaPlataformaAtendimentoCustomizadoCAPES.getPessoaPlataforma());
			
			objTributacao.pessoaCompartilhamento = pessoaCompartilhamento; 
			objTributacao.idPessoaCompartilhamento = pessoaCompartilhamento.idPessoaCompartilhamento;
			
			dto.dados.tributacao = objTributacao;
			
			return dto;
		}

		public function preencherCampos():void {

			if (tributacaoVO != null) {
				
				if (tributacaoVO.cobrarIR == null) {
					irrfSim.selected = false;
					irrfNao.selected = false;				
				} else if (tributacaoVO.cobrarIR.valor) {
					irrfSim.selected = true;
					irrfNao.selected = false;
				} else {
					irrfSim.selected = false;
					irrfNao.selected = true;
				}

				if (tributacaoVO.cobrarIOF == null) {
					iofSim.selected = false;
					iofNao.selected = false;					
				} else if (tributacaoVO.cobrarIOF.valor) {
					iofSim.selected = true;
					iofNao.selected = false;
				} else {
					iofSim.selected = false;
					iofNao.selected = true;
				}

//				if (tributacaoVO.cobrarCPMF == null) {
//					cpmfSim.selected = false;
//					cpmfNao.selected = false;					
//				} else if (tributacaoVO.cobrarCPMF.valor) {
//					cpmfSim.selected = true;
//					cpmfNao.selected = false;
//				} else {
//					cpmfSim.selected = false;
//					cpmfNao.selected = true;
//				}
			}
		}

		public function carregarDefinicoes(obj:Object=null):void {
			
		}
		
		public function retornoDefinicoes(evt:ResultEvent):void {
			
		}

		private function retornoSalvar(evt:ResultEvent):void {
			
			tributacaoVigente = evt.result.dados.tributacaoVigente;
			tributacaoAlteracao = evt.result.dados.tributacaoAlteracao;
			
			tributacaoVO = tributacaoVigente;
			
			preencherCampos();
			
			MostraCursor.removeBusyCursor();
			
			_novo = false;
			
			tributacaoVigenteBkp = ObjectUtil.copy(tributacaoVigente) as TributacaoVO;
			tributacaoAlteracaoBkp = ObjectUtil.copy(tributacaoAlteracao) as TributacaoVO;
			
			verificarBotoesAutorizacao(tributacaoAlteracao != null ? tributacaoAlteracao.situacaoAprovacao : SituacaoRegistroEnum.VIGENTE, exibirBotaoDetalhamentoGedGft());
		}

		public function novoRegistro():void
		{
			tributacaoVO = new TributacaoVO();
			tributacaoVigenteBkp = new TributacaoVO();
			tributacaoAlteracaoBkp = new TributacaoVO();
			_novo = true;
		}

		public function gravarRegistro():void {
			
			if(!validarCampos())
				return;
			
			if(pessoaSelecionada.utilizaGedGft && TipoPessoaEnum.PESSOA_JURIDICA.codigo == pessoaSelecionada.codTipoPessoa){
				
				//Verifica as chaves de negócio dos documentos do componente GED com  as chaves atuais da tela.
				if(!this.abaDocumentos.validarDocumentosNaoEnviados()) {
					Alerta.show(UploadDocGedUtil.VALIDACAO_DOCUMENTOS_NAO_ENVIADOS, "ERRO!", Alerta.ALERTA_ERRO);
					return;
				}
				
				//Verifica se há algum documento que já foi enviado ao ged e está com as chaves de negócio diferentes das atuais.
				if(!this.abaDocumentos.verificarChavesNegocio(criarListaChavesNegocio())){
					Alerta.show(UploadDocGedUtil.VALIDACAO_DOCUMENTOS_DIVERGENTES, "ERRO!", Alerta.ALERTA_ERRO);
					return;
				}
				
				//Exige confirmação para salvar a certidão sem documentos.
				if(this.abaDocumentos.obterDocumentosComprobatorios().length <= 0){
					Alerta.show(UploadDocGedUtil.VALIDACAO_DOCUMENTOS_NAO_SELECIONADOS, "ATENÇÃO", Alerta.ALERTA_PERGUNTA, null, onConfirmaGravarRegistro);
					return;
				}
			}
			
			onConfirmaGravarRegistro();
		}
		
		private function onConfirmaGravarRegistro(evt:Event = null):void{
			validarCadastro();
			atualizarCamposRegistro();
			executarSeValido(gravarDados);
		}

		public function atualizarCamposRegistro():void{
 
			if (irrfSim.selected) {
				tributacaoVO.cobrarIR = new Booleano(true);
			} else if (irrfNao.selected) {
				tributacaoVO.cobrarIR =  new Booleano(false);
			}
			
			if (iofSim.selected) {
				tributacaoVO.cobrarIOF = new Booleano(true);
			} else if (iofNao.selected) {
				tributacaoVO.cobrarIOF =  new Booleano(false);
			}

//			if (cpmfSim.selected) {
				tributacaoVO.cobrarCPMF = new Booleano(true);
//			} else if (cpmfNao.selected) {
//				tributacaoVO.cobrarCPMF =  new Booleano(false);
//			}
			
			var pessoaCompartilhamento:PessoaCompartilhamentoVO = ConversaoPessoaUtil.
				converterVOParaPessoaCompartilhamento(TelaPlataformaAtendimentoCustomizadoCAPES.getPessoaPlataforma());
			
			tributacaoVO.pessoaCompartilhamento = pessoaCompartilhamento; 
			tributacaoVO.idPessoaCompartilhamento = pessoaCompartilhamento.idPessoaCompartilhamento;
			
			if(TipoPessoaEnum.PESSOA_JURIDICA.codigo == pessoaSelecionada.codTipoPessoa){
				tributacaoVO.documentosComprobatorios = this.abaDocumentos.obterDocumentosComprobatorios();
			}
			
		}

		private function gravarDados():void {
			MostraCursor.setBusyCursor("Gravando dados ...", Application.application,
			MostraCursor.CURSOR_PROGRESSO);

			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.tributacao = tributacaoVO;
			dto.dados.idTipoPessoa = pessoaSelecionada.codTipoPessoa;

			if (_novo)
				servicoSalvar.getOperation(OPERACAO_INCLUIR_DADOS).send(dto);
			else
				servicoSalvar.getOperation(OPERACAO_ALTERAR_DADOS).send(dto);
		}

		/*public function excluirRegistro(tributacaoVO:Object = null):void {
			Alerta.show("Confirma a operação?", "Confirmação", Alerta.ALERTA_PERGUNTA, null, exluirTributacaoConfirmado);
		}
		
		private function exluirTributacaoConfirmado(evt:Event):void {
			MostraCursor.setBusyCursor("Excluindo Registro!", Application.application, MostraCursor.CURSOR_EXCLUIR);
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.tributacao = tributacaoAlteracao;
			
			servicoExcluir.getOperation(OPERACAO_EXCLUIR_TRIBUTACAO).send(dto);
		}*/
		
		public function restaurarRegistro():void{
			if(_vigente){
				tributacaoVO = (ObjectUtil.copy(tributacaoVigenteBkp) as TributacaoVO);
			}else{
				tributacaoVO = (ObjectUtil.copy(tributacaoAlteracaoBkp) as TributacaoVO);
			}
		}

		public function verificarAlteracoes():Boolean {
			
			if ((irrfSim.selected && !this.tributacaoVigenteBkp.cobrarIR.valor) 
				|| (irrfNao.selected && this.tributacaoVigenteBkp.cobrarIR.valor)) {
				return false;
			}

			if ((this.iofSim.selected && !this.tributacaoVigenteBkp.cobrarIOF.valor)
				|| (this.iofNao.selected && this.tributacaoVigenteBkp.cobrarIOF.valor)) {
				return false;
			}

//			if ((this.cpmfSim.selected && !this.tributacaoVigenteBkp.cobrarCPMF.valor)
//				|| (this.cpmfNao.selected && this.tributacaoVigenteBkp.cobrarCPMF.valor)) {
//				return false;
//			}

			return true;
		}
		
		/**
		 * Dispara um evento com a situação do registro
		 * para a visualização dos botões de autorizacao
		 **/
		private function verificarBotoesAutorizacao(situacao:SituacaoRegistroEnum, exibirBotaoGedGft:Boolean):void{
			var objeto:Object = new Object();
			objeto.situacao = situacao;
			objeto.exibir = exibirBotaoGedGft;
			
			var evento:Event = new ObjetoEvent(VERIFICAR_BOTOES_AUTORIZACAO, objeto);
			this.dispatchEvent(evento);
		}
		
		public function setarTributacaoVigente(vigente:Boolean):void{
			_vigente = vigente;
			tributacaoVO = vigente == true ? tributacaoVigente : tributacaoAlteracao;
			verificarBotoesAutorizacao(SituacaoRegistroEnum.VIGENTE, exibirBotaoDetalhamentoGedGft());
			preencherCampos();
			this.abaDocumentos.carregarDocumentos(UploadDocGedUtil.criarListaDocumentos(tributacaoVO.documentosComprobatorios));
		}

		private function validarCampos():Boolean {
			if (grupoIrrf.selectedValue == null) {
				Alerta.show("O campo IRRF é obrigatório!", "Erro", Alerta.ALERTA_ERRO);
				return false;
			}
			
			if (grupoIof.selectedValue == null) {
				Alerta.show("O campo IOF é obrigatório!", "Erro", Alerta.ALERTA_ERRO);
				return false;
			}
			
//			if (grupoCpmf.selectedValue == null) {
//				Alerta.show("O campo CPMF é obrigatório!", "Erro", Alerta.ALERTA_ERRO);
//				return false;
//			}	
			
			return true;
		}
		
		/**
		 * Método para adicionar os valores às chaves de negócio
		 * ao componente de upload do GED. 
		 */
		private function aoTrocarAba(event:IndexChangedEvent):void {
			if (this.navegacao.selectedIndex == ABA_DOCUMENTOS) {
				
				//Obtém as chaves de negócio
				var listaChavesNegocio:ArrayCollection = criarListaChavesNegocio();
				
				//Adiciona as chaves de negócio à instância do componente.
				this.abaDocumentos.chavesNegocio = listaChavesNegocio;
				
				//Atualiza as chaves de negócio dos arquivos que ainda não foram enviados.
				this.abaDocumentos.atualizarChavesNegocio(listaChavesNegocio);
			}
		}
		
		/**
		 * Método para criar as lista de chave de negócio.
		 */
		private function criarListaChavesNegocio():ArrayCollection {
			var listaValoresChaves:ArrayCollection = new ArrayCollection();
			var objChaveDoc:Object = new Object();
			
			//Adiciona a chave da pessoa (PF/PJ)
			objChaveDoc.siglaTipoDocumento = definicoesComponenteGED[0].siglaTipoDocumento;
			objChaveDoc.siglaChaveDocumento = definicoesComponenteGED[0].obterCodigoTipoPessoaSelecionada;
			objChaveDoc.valorChave = pessoaSelecionada.cpfCnpj;
			listaValoresChaves.addItem(objChaveDoc);
			
			//Adiciona o grupo de compartilhamento
			objChaveDoc = new Object();
			objChaveDoc.siglaTipoDocumento = definicoesComponenteGED[0].siglaTipoDocumento;
			objChaveDoc.siglaChaveDocumento = definicoesComponenteGED[0].chavesNegocio[0];
			objChaveDoc.valorChave = pessoaSelecionada.codCompartilhamentoCadastro;
			listaValoresChaves.addItem(objChaveDoc);
			
			//Adiciona a chave da pessoa (PF/PJ)
			objChaveDoc = new Object();
			objChaveDoc.siglaTipoDocumento = definicoesComponenteGED[1].siglaTipoDocumento;
			objChaveDoc.siglaChaveDocumento = definicoesComponenteGED[1].obterCodigoTipoPessoaSelecionada;
			objChaveDoc.valorChave = pessoaSelecionada.cpfCnpj;
			listaValoresChaves.addItem(objChaveDoc);
			
			//Adiciona o grupo de compartilhamento
			objChaveDoc = new Object();
			objChaveDoc.siglaTipoDocumento = definicoesComponenteGED[1].siglaTipoDocumento;
			objChaveDoc.siglaChaveDocumento = definicoesComponenteGED[1].chavesNegocio[0];
			objChaveDoc.valorChave = pessoaSelecionada.codCompartilhamentoCadastro;
			listaValoresChaves.addItem(objChaveDoc);
			
			return listaValoresChaves;
		}
		
		/**
		 * Método para adicionar os valores das chaves de negócio à um array,
		 * de acordo com o esperado pelas chaves de negócio.
		 */
		private function criarValoresChavesNegocio():ArrayCollection {
			var valoresChavesNegocio:ArrayCollection = new ArrayCollection();
			valoresChavesNegocio.addItem(pessoaSelecionada.codCompartilhamentoCadastro);
			return valoresChavesNegocio;
		}
		
		public function exibirDetalhamentoGedGft():void{
			var popUpDetalhamentoGedGft:DetalhamentoGedGft = new DetalhamentoGedGft();
			popUpDetalhamentoGedGft.objeto = tributacaoVO;
			popUpDetalhamentoGedGft.idTipoPessoaSelecionada = pessoaSelecionada.codTipoPessoa;
			
			var janela:Janela = new Janela();
			janela.title = TITULO_JANELA;
			janela.addChild(popUpDetalhamentoGedGft);
			janela.abrir(DisplayObject(Application.application), true);
		}

		public function limparFormIncluir(event:FlexEvent=null):void{

		}

		public function setPessoa(pessoaVO:PessoaVO=null):void {
			this.pessoa = pessoaVO;		
		}
		
		public function getEntidadeVigente():VigenteVO {
			return null;
		}
		
		private function exibirBotaoDetalhamentoGedGft():Boolean {
			if(pessoaSelecionada.utilizaGedGft){
				if(tributacaoVO != null && (SituacaoRegistroEnum.VIGENTE == tributacaoVO.situacaoAprovacao || SituacaoRegistroEnum.BLOQUEADO == tributacaoVO.situacaoAprovacao)){
					return true;
				}
			}
			return false;
		}
		
	    //--------------------------------------------------------------------------
	    //  Métodos das interfaces
	    //--------------------------------------------------------------------------
		public function trocarTelaEntrar(params:ParametrosAssistenteAtendimento=null):void{};
		public function trocarTelaSair(params:ParametrosAssistenteAtendimento=null):void{};	
	}
}