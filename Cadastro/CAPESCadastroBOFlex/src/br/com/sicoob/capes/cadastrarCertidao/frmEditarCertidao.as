package br.com.sicoob.capes.cadastrarCertidao{

	import flash.events.Event;
	import flash.events.FocusEvent;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.events.IndexChangedEvent;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.ObjectUtil;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.eventos.SelecaoEvent;
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.util.FormataData;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.VigenteVO;
	import br.com.sicoob.capes.comum.vo.entidades.CertidaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoCertidaoVO;
	import br.com.sicoob.capes.corporativo.componentes.plataformaatendimento.ProcuraClientePlataformaCAPES;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	import br.com.sicoob.capes.utils.ConversaoPessoaUtil;
	import br.com.sicoob.capes.utils.UploadDocGedUtil;
	import br.com.sicoob.capes.utils.plataformaatendimento.IEdicaoPlataformaAtendimentoCAPES;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCAPESBase;
	
	public class frmEditarCertidao extends frmEditarCertidaoView implements IEdicaoPlataformaAtendimentoCAPES {
		
		private var certidaoVOBkp:CertidaoVO = new CertidaoVO();
				
		[Bindable]
		private var certidao:CertidaoVO = new CertidaoVO();
		
		//Informações da Pessoa
		private var pessoa:PessoaVO = null;
		private var prazo:Number = -1;
		private var tipoPrazo:Number = -1;
		
		//Guarda as informações da pessoa selecionada na plataforma
		private var pessoaSelecionada:PessoaPlataformaVO;
		
		private var primeiraConsulta:Boolean = false;
	
		/**
		 *	Serviços 
		 */
		private var servicoConsulta:ServicoJava;
		private var servicoSalvar:ServicoJava;
		private var servicoExcluir:ServicoJava;
		private var servicoDefinicao:ServicoJava;
		
		/**
		 *  Operações 
		 */
		static private const OPERACAO_OBTER_DEFINICOES:String = "obterDefinicoes";
		static private const OPERACAO_INCLUIR_DADOS:String = "incluirDados";
		static private const OPERACAO_ALTERAR_DADOS:String = "alterarDados";
		static private const OPERACAO_EXCLUIR_MENSAGEM:String = "excluirDados";
		static private const OPERACAO_OBTER_MENSAGEM:String = "obterDados";

		static private const CLASSE_SERVICO: String = 
			"br.com.sicoob.capes.cadastro.fachada.CertidaoFachada";
		
		private const ABA_DOCUMENTOS:Number = 1;
		
		//Objeto que guardará as informações para serem usadas no componente GED.
		private var definicoesComponenteGED:Object = new Object;
		
		private var _isRegistroBloqueado:Boolean = false;
		
		private var pilotoHabilitado: Boolean;
		private static const PESSOA_FISICA: Number = 0;
		
		private var listaDocumetosGed: ArrayCollection = new ArrayCollection();
		
		//--------------------------------------------------------------------------
	    //  Propriedades
	    //--------------------------------------------------------------------------

	    /**
	     *  Construtor.
	     */
		public function frmEditarCertidao(){
			super();

			servicoDefinicao = ServicoJavaUtil.getServico(CLASSE_SERVICO);
			servicoDefinicao.addEventListener(ResultEvent.RESULT, retornoDefinicoes);

			servicoConsulta = ServicoJavaUtil.getServico(CLASSE_SERVICO);
			servicoConsulta.addEventListener(ResultEvent.RESULT, retornoCarregarRegistro);

			servicoSalvar = ServicoJavaUtil.getServico(CLASSE_SERVICO);
			servicoSalvar.addEventListener(ResultEvent.RESULT, retornoSalvar);		

			servicoExcluir = ServicoJavaUtil.getServico(CLASSE_SERVICO);
			servicoExcluir.addEventListener(ResultEvent.RESULT, retornoExcluir);	
			servicoExcluir.addEventListener(FaultEvent.FAULT, retornoExcluirErro);

			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		//--------------------------------------------------------------------------
	    //  Métodos
	    //--------------------------------------------------------------------------
		private function init(evt:FlexEvent):void {
			pessoaSelecionada = ProcuraClientePlataformaCAPES.getPessoaSelecionada();
			
			var anoAtual : Number = new Date().fullYear;
			carregarDefinicoes();
			anoBase.valorMaximo = anoAtual - 1;
			anoBase.addEventListener(FocusEvent.FOCUS_OUT, atualizarAnoBaseVencimento);
			dtDataEmissao.addEventListener(FocusEvent.FOCUS_OUT, alterarDataVencimento);
			procurarGeral.addEventListener(SelecaoEvent.OBJETO_SELECIONADO, retornoTipoCertidaoSelecionado);
			
			//Adiciona o evento ao trocar aba.
			this.navegacao.addEventListener(IndexChangedEvent.CHANGE, aoTrocarAba);
			this.abaDocumentos.enabled = pessoaSelecionada.utilizaGedGft;
			
			configurarProcurarGeral();
		}
		
		public override function dispose():void {
			super.dispose();
			abaDocumentos.dispose();
			navegacao.removeEventListener(Event.CHANGE, aoTrocarAba);
			navegacao.removeAllChildren();
			navegacao = null;
		}
		
		private function retornoTipoCertidaoSelecionado(evnt:SelecaoEvent):void {
			var textoAnoBase:String = null; 
			var textoCrea:String = null; 
			
			anoBase.enabled = false;
			crea.enabled = false;
			dtDataVencimentoDocumento.enabled = false;

			textoAnoBase = anoBase.text;
			textoCrea = crea.text;
			
			anoBase.text = "";
			crea.text = "";
			
			var novaData:Date = null; 

			if (dtDataEmissao.selectedDate == null) {
				dtDataEmissao.selectedDate = new Date();
			}
			
			novaData = dtDataEmissao.selectedDate;
			
			if (evnt.objeto != null) {
				
				var tipoCertidao:TipoCertidaoVO = evnt.objeto as TipoCertidaoVO;
				prazo = tipoCertidao.prazoValidade;
				tipoPrazo = tipoCertidao.tipoPrazo.codigo;
				
				if (tipoPrazo == 0) {
					dtDataVencimentoDocumento.enabled = true;
				} else if (tipoPrazo == 1) { // Adiciona Dias.
					novaData["date"] += prazo;
					dtDataVencimentoDocumento.selectedDate = novaData;
				} else if (tipoPrazo == 2) { // Adiciona meses
					novaData["month"] += prazo;
					dtDataVencimentoDocumento.selectedDate = novaData;
				} else if (tipoPrazo == 3) {
					dtDataVencimentoDocumento.selectedDate = null;
				} else if (tipoPrazo == 4) {
					anoBase.enabled = true;
					crea.enabled = true;
					anoBase.text = textoAnoBase;
					crea.text = textoCrea;
					atualizarAnoBaseVencimento();
				}
				//Mantis 5496 - Quando a certidão for 14 - DECLARAÇÃO DE APTIDÃO AO PRONAF, não deixar inserir mais que 25 caracteres no campo Número Certidão
				if(tipoCertidao.codigo == 14) {
					txtNumCertidao.maxChars = 25;
					if(txtNumCertidao.text.length > 25) {
						txtNumCertidao.text = '';
					}
				} else {
					txtNumCertidao.maxChars = 30;
				}
			} else {
				dtDataVencimentoDocumento.selectedDate = null;
				prazo = -1;
				tipoPrazo = -1;
			}
			
			carregarDocumentosGed();
			
		}
		private function carregarDocumentosGed(): void {
			this.listaDocumetosGed = new ArrayCollection();
			if (!pilotoHabilitado || this.certidao == null || this.certidao.idRegistroControlado == null) {
				return;
			}
			
			UploadDocGedUtil.recuperarDocumentosGED(this.destino, this.certidao.idRegistroControlado, 
				pessoaSelecionada.codTipoPessoa, retornoListaGed);
		}
		private function habilitaCamposExtras(bol: Boolean):void{
			rotuloDataHoraInicio.visible = rotuloDataHoraInicio.includeInLayout = !bol;
			rotuloUsuarioAlteracao.visible = rotuloUsuarioAlteracao.includeInLayout = !bol;
			dtDataCadastro.visible = dtDataCadastro.includeInLayout = !bol;
			usuarioAlteracao.visible = usuarioAlteracao.includeInLayout = !bol;
		}
	
		private function alterarDataVencimento(event:FocusEvent):void {
			var textoAnoBase:String = null; 
			var textoCrea:String = null; 
			
			anoBase.enabled = false;
			crea.enabled = false;
			dtDataVencimentoDocumento.enabled = false;

			textoAnoBase = anoBase.text;
			textoCrea = crea.text;
			
			anoBase.text = "";
			crea.text = "";

			var novaData:Date = null; 
			
			if (dtDataEmissao.selectedDate == null) {
				dtDataEmissao.selectedDate = new Date();
			}
			
			novaData = dtDataEmissao.selectedDate;
			
			if (prazo >= 0 && tipoPrazo >= 0) {

				if (tipoPrazo == 0) {
					dtDataVencimentoDocumento.enabled = true;
				} else if (tipoPrazo == 1) { // Adiciona Dias.
					novaData["date"] += prazo;
					dtDataVencimentoDocumento.selectedDate = novaData;
				} else if (tipoPrazo == 2) { // Adiciona meses
					novaData["month"] += prazo;
					dtDataVencimentoDocumento.selectedDate = novaData;
				} else if (tipoPrazo == 3) {
					dtDataVencimentoDocumento.selectedDate = null;
				} else if (tipoPrazo == 4) {
					anoBase.enabled = true;
					crea.enabled = true;
					anoBase.text = textoAnoBase;
					crea.text = textoCrea;
					atualizarAnoBaseVencimento();
				}
			}
		}
		
		private function atualizarAnoBaseVencimento(event : FocusEvent = null):void {
			var novaData:Date = null;
			if ((anoBase.valor >= anoBase.valorMinimo) && (anoBase.valor <= anoBase.valorMaximo)) { 
				novaData = new Date();
	
				// preenche com a data 01/03...
				novaData["date"] = 1; 
				novaData["month"] = 2; // Março
				novaData["fullYear"] = (anoBase.valor + 2);

				// ... depois diminui um dia, para chegar ao último dia de fevereiro (29, se bissexto)				
				novaData["date"] -= 1;
				 
			}
			dtDataVencimentoDocumento.selectedDate = novaData;
		}

		public function carregarRegistro(certidao:Object):void {

			MostraCursor.setBusyCursor("Obtendo definições ...", 
				Application.application, MostraCursor.CURSOR_PROGRESSO);

			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.certidao = certidao;
			
			servicoConsulta.getOperation(OPERACAO_OBTER_MENSAGEM).send(dto);
		}

		private function retornoCarregarRegistro(evt:ResultEvent):void {
			certidao = evt.result.dados.certidao;
			certidaoVOBkp = ObjectUtil.copy(certidao) as CertidaoVO;
			
			//Adiciona os documentos da certidão ao componente.
			this.abaDocumentos.carregarDocumentos(UploadDocGedUtil.criarListaDocumentos(certidao.documentosComprobatorios));
			
			//Verifica se o registro está bloqueado para alteração.
			_isRegistroBloqueado = evt.result.dados.isRegistroBloqueadoAlteracao;

			primeiraConsulta = true;
			_novo = false;
			habilitaCamposExtras(_novo);
			preencherCampos();			
			
			limparGridDocumentos();
			carregarDocumentosGed();
			
			this.dispatchEvent(new Event(REGISTRO_CARREGADO));
		}

		public function preencherCampos():void {
			dtDataVencimentoDocumento.enabled = false;
			anoBase.enabled = false;	
			crea.enabled = false;
			
			if (certidao.tipoCertidao != null) {
				procurarGeral.textoCodigo.valor = certidao.tipoCertidao.codigo;
				procurarGeral.pesquisar();
			} else {
				procurarGeral.limpar();
			}

			if (certidao.dataEmissao != null) {
				dtDataEmissao.selectedDate = certidao.dataEmissao.data;
				txtHoraEmissao.text = FormataData.formataHora(certidao.dataEmissao.data, "JJNNSS");
			} else {
				txtHoraEmissao.text = "";
			}
			
			dtDataCadastro.selectedDate = certidao.dataHoraInicio != null ? certidao.dataHoraInicio.data : null;
			txtNumCertidao.text = certidao.numero;
			usuarioAlteracao.text = certidao.idUsuarioAprovacao;

			if (certidao.dataVencimento != null)
				dtDataVencimentoDocumento.selectedDate = certidao.dataVencimento.data;
			else
				dtDataVencimentoDocumento.selectedDate = null;

			txtDescObservacao.text = certidao.observacao;

			if (certidao.tipoCertidao != null  
				&& certidao.tipoCertidao.tipoPrazo != null ) {
				if (certidao.tipoCertidao.tipoPrazo.codigo == 0) {
					dtDataVencimentoDocumento.enabled = true;
				} else if (certidao.tipoCertidao.tipoPrazo.codigo == 4) {
					anoBase.enabled = true;
					crea.enabled = true;
	
					crea.text = certidao.numeroCrea;
					if ((certidao.dataVencimento != null) 
							&& (certidao.dataVencimento.data != null)) {
						anoBase.valor = certidao.dataVencimento.data.fullYear - 2;
					}
				}
			} 		
		}

		public function carregarDefinicoes(obj:Object=null):void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			//Envia o tipo de pessoa para ser utilizado nas chaves de negócio.
			dto.dados.idTipoPessoa = pessoaSelecionada.codTipoPessoa;
			
			servicoDefinicao.getOperation(OPERACAO_OBTER_DEFINICOES).send(dto);
		}
		
		private function retornoDefinicoes(evt:ResultEvent):void {
			var date:Date = evt.result.dados.dataHoraServidor;
			txtHoraEmissao.text = FormataData.formataHora(date, "JJNNSS");
			dtDataEmissao.selectedDate = date;
			
			//Atribui ao objeto as informações que serão utilizadas no componente GED,
			//configuradas na fachada.
			definicoesComponenteGED = evt.result.dados.definicoesComponenteGED;
			
			pilotoHabilitado = evt.result.dados.pilotoHabilitado;
			
		}
		
		private function retornoSalvar(evt:ResultEvent):void {
			certidao = evt.result.dados.certidao;
			MostraCursor.removeBusyCursor();
			this.dispatchEvent(new Event(Modulo.REGISTRO_GRAVADO));	
		}

		public function novoRegistro():void	{
			certidao = new CertidaoVO();
			certidaoVOBkp = new CertidaoVO();
			_novo = true;
			habilitaCamposExtras(_novo);
			limparFormIncluir();
			
			limparGridDocumentos();
			
			txtHoraEmissao.text = "000000";
		}

		public function gravarRegistro():void {
			if(!validarCampos())
				return;
			
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
			
			var isDocumento:Boolean = isDocumentoSelecionado();
			
			if (pilotoHabilitado) {
				if (this.listaDocumetosGed.length <= 0 && isDocumento) {
					Alerta.show(UploadDocGedUtil.VALIDACAO_DOCUMENTOS_OBRIGATORIOS + "documento de certidão.", "ERRO!", Alerta.ALERTA_ERRO);
					return;
				} 
			}
			
			//Exige confirmação para salvar a certidão sem documentos.
			if (isDocumento) {
				Alerta.show(UploadDocGedUtil.VALIDACAO_DOCUMENTOS_NAO_SELECIONADOS, "ATENÇÃO", Alerta.ALERTA_PERGUNTA, null, onConfirmaGravarRegistro);
			}
			
			else {
				onConfirmaGravarRegistro();
			}
		}
		
		/**
		 * Atualiza os campos do formulário e grava a solicitação.
		 **/
		private function onConfirmaGravarRegistro(evt:Event = null):void {
			atualizarCamposRegistro();
			executarSeValido(gravarDados);
		}

		public function atualizarCamposRegistro():void{
			certidao.pessoa = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(
					TelaPlataformaAtendimentoCAPESBase.getPessoaPlataforma());
			certidao.tipoCertidao = new TipoCertidaoVO();
			certidao.tipoCertidao.codigo = procurarGeral.obterRegistro() != null ? procurarGeral.obterRegistro().codigo : null;

			if (dtDataEmissao.selectedDate != null) 
            	certidao.dataEmissao = DateTimeBase.getDateTimeEntity(txtHoraEmissao.montaDataHora(dtDataEmissao.selectedDate)); 

			certidao.numero = txtNumCertidao.text;
			certidao.dataVencimento = DateTimeBase.getDateTimeEntity(dtDataVencimentoDocumento.selectedDate);
			certidao.observacao = txtDescObservacao.text;
			certidao.numeroCrea = crea.text;

			//Adiciona à certidão, os documentos que foram enviados ao GED.
			certidao.documentosComprobatorios = this.abaDocumentos.obterDocumentosComprobatorios();
		}

		private function gravarDados():void {

			MostraCursor.setBusyCursor("Gravando dados ...", Application.application,
			MostraCursor.CURSOR_PROGRESSO);

			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.certidao = certidao;

			if (_novo)
				servicoSalvar.getOperation(OPERACAO_INCLUIR_DADOS).send(dto);
			else
				servicoSalvar.getOperation(OPERACAO_ALTERAR_DADOS).send(dto);
		}

		public function excluirRegistro(certidao:Object):void {
			MostraCursor.setBusyCursor("Excluindo Registro...", Application.application, 
				MostraCursor.CURSOR_EXCLUIR);

			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();

			dto.dados.certidao = certidao;

			servicoExcluir.getOperation(OPERACAO_EXCLUIR_MENSAGEM).send(dto);
		}
		
		private function retornoExcluir(evt:ResultEvent):void {
			
			certidao = evt.result.dados.certidao;
			MostraCursor.removeBusyCursor();
			this.dispatchEvent(new Event(REGISTRO_EXCLUIDO));		
		}
		
		private function retornoExcluirErro(evt:FaultEvent):void {
			 
			MostraCursor.removeBusyCursor();
			//this.dispatchEvent(new Event(REGISTRO_EXCLUIDO));
		}	
		
		
		public function restaurarRegistro():void{
			certidao = (ObjectUtil.copy(certidaoVOBkp) as CertidaoVO);
		}

		public function verificarAlteracoes():Boolean {
			if(certidaoVOBkp.tipoCertidao.codigo != (procurarGeral.obterRegistro() != null ? procurarGeral.obterRegistro().codigo : null))
				return false;	

			if(certidaoVOBkp.dataEmissao != null 
				&& FormataData.formataData(certidaoVOBkp.dataEmissao.data) 
				!= FormataData.formataData(dtDataEmissao.selectedDate)) 
				return false;						

			if(certidaoVOBkp.dataEmissao.data.hours != txtHoraEmissao.hora)
				return false;

			if(certidaoVOBkp.dataEmissao.data.minutes != txtHoraEmissao.minuto)
				return false;

			if(certidaoVOBkp.dataEmissao.data.seconds != txtHoraEmissao.segundo)
				return false;					

			if(certidaoVOBkp.numero != null && certidaoVOBkp.numero != txtNumCertidao.text)
				return false;

			if(certidaoVOBkp.dataVencimento != null 
				&& FormataData.formataData(certidaoVOBkp.dataVencimento.data) 
				!= FormataData.formataData(dtDataVencimentoDocumento.selectedDate))
				return false;						

			if(certidaoVOBkp.observacao != null && certidaoVOBkp.observacao != txtDescObservacao.text)
				return false;

			if(certidaoVOBkp.numeroCrea != crea.text)
				return false;

			return true;
		}

		private function validarCampos():Boolean {
			
			if ((dtDataEmissao.selectedDate != null) 
				&& (dtDataVencimentoDocumento.selectedDate != null) 
				&& dtDataVencimentoDocumento.selectedDate < dtDataEmissao.selectedDate) {
				Alerta.show("A Data de vencimento não pode ser anterior a data de emissão!", "Erro", Alerta.ALERTA_ERRO, dtDataEmissao);
				return false;
			}
			
			return true;
		}

		private function limparFormIncluir(event:FlexEvent=null):void{
			anoBase.enabled = false;
			crea.enabled = false;
			dtDataVencimentoDocumento.enabled = false;
			
			dtDataCadastro.selectedDate = new Date();
			procurarGeral.limpar();
			txtHoraEmissao.text = "      ";
			txtNumCertidao.text = "";
			dtDataVencimentoDocumento.selectedDate = null;
			txtDescObservacao.text = "";
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
			
			//Criar os valores das chaves na ordem que são adicionados na fachada.
			var valoresChavesNegocio:ArrayCollection = criarValoresChavesNegocio();
			
			//Adiciona a chave da pessoa (PF/PJ)
			objChaveDoc.siglaTipoDocumento = definicoesComponenteGED.siglaTipoDocumento;
			objChaveDoc.siglaChaveDocumento = definicoesComponenteGED.obterCodigoTipoPessoaSelecionada;
			objChaveDoc.valorChave = pessoaSelecionada.cpfCnpj;
			listaValoresChaves.addItem(objChaveDoc);
			
			//Percorre a lista para preencher as outras chaves.
			for (var i:uint = 0; i < definicoesComponenteGED.chavesNegocio.length; i++){
				objChaveDoc = new Object();
				
				objChaveDoc.siglaTipoDocumento = definicoesComponenteGED.siglaTipoDocumento;
				objChaveDoc.siglaChaveDocumento = definicoesComponenteGED.chavesNegocio[i];
				objChaveDoc.valorChave = valoresChavesNegocio[i];
				listaValoresChaves.addItem(objChaveDoc);
			}
			
			return listaValoresChaves;
		}
		
		/**
		 * Método para adicionar os valores das chaves de negócio à um array,
		 * de acordo com o esperado pelas chaves de negócio.
		 */
		private function criarValoresChavesNegocio():ArrayCollection {
			var valoresChavesNegocio:ArrayCollection = new ArrayCollection();
			
			valoresChavesNegocio.addItem(pessoaSelecionada.codCompartilhamentoCadastro);
			
			if(procurarGeral.obterRegistro() != null && !isNaN(procurarGeral.obterRegistro().codigo) && procurarGeral.obterRegistro().codigo != 0){
				valoresChavesNegocio.addItem(procurarGeral.obterRegistro().codigo);
			}else{
				valoresChavesNegocio.addItem(undefined);
			}
			
			if(txtNumCertidao.text && txtNumCertidao.text != ""){
				valoresChavesNegocio.addItem(txtNumCertidao.text);
			}else{
				valoresChavesNegocio.addItem(undefined);
			}
			
			return valoresChavesNegocio;
		}
		
		public function configurarDestinosServicos(destinoVO:DestinoVO):void{
			this.destino = destinoVO;
			servicoConsulta.configurarDestino(destinoVO);
			servicoDefinicao.configurarDestino(destinoVO);
			servicoSalvar.configurarDestino(destinoVO);
			servicoExcluir.configurarDestino(destinoVO);
		}
		
		private function limparGridDocumentos():void{
			this.abaDocumentos.limparDocumentos();
			this.navegacao.selectedIndex = 0;
		}
		
		private function configurarProcurarGeral():void {
			procurarGeral.configurarDestino(this.destino);
		}
		
		private function isDocumentoSelecionado():Boolean {
			return pessoaSelecionada.utilizaGedGft && 
				this.abaDocumentos.obterDocumentosComprobatorios().length <= 0;
		}
		
		public function retornoListaGed(event:ResultEvent):void {
			this.listaDocumetosGed = event.result.dados.listaDocumentosGED as ArrayCollection;
		}
		
		public function setPessoa(pessoaVO:PessoaVO=null):void {
			this.pessoa = pessoaVO;		
		}
		
		[Deprecated]
		public function getEntidadeVigente():VigenteVO {
			return null;
		}
		
		public function isRegistroBloqueadoAlteracao():Boolean{
			return _isRegistroBloqueado;
		}
		
	}
}