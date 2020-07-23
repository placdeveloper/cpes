package br.com.sicoob.capes.cadastrarFonteRenda
{
	import flash.events.Event;
	import flash.events.FocusEvent;
	import flash.events.MouseEvent;
	
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
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.Constantes;
	import br.com.bancoob.sisbr.eventos.EventAssistenteAtendimento;
	import br.com.bancoob.tipos.Booleano;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.CadastroUnicoUtil;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.FonteRendaProxy;
	import br.com.sicoob.capes.comum.vo.VigenteVO;
	import br.com.sicoob.capes.comum.vo.entidades.FonteRendaVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoEmpresaVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoFonteRendaVO;
	import br.com.sicoob.capes.comum.vo.entidades.interfaces.Aprovavel;
	import br.com.sicoob.capes.corporativo.componentes.plataformaatendimento.ProcuraClientePlataformaCAPES;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	import br.com.sicoob.capes.utils.ConversaoPessoaUtil;
	import br.com.sicoob.capes.utils.UploadDocGedUtil;
	import br.com.sicoob.capes.utils.plataformaatendimento.IEdicaoPlataformaAtendimentoCAPES;
	
	public class frmEditarFonteRenda extends frmEditarFonteRendaView implements IEdicaoPlataformaAtendimentoCAPES {
	
		/**
		 *	Serviços 
		 */
		public var servicoConsulta:ServicoJava;
		public var servicoSalvar:ServicoJava;
		public var servicoExcluir:ServicoJava;
		public var servicoDefinicao:ServicoJava;
		
		/**
		 *  Operações 
		 */
		static private const OPERACAO_OBTER_DEFINICOES: String = "obterDefinicoes";
		static private const OPERACAO_INCLUIR_DADOS: String = "incluirDados";
		static private const OPERACAO_ALTERAR_DADOS: String = "alterarDados";
		static private const OPERACAO_EXCLUIR_DADOS: String = "excluirDados";
		static private const OPERACAO_OBTER_DADOS: String = "obterDados";
			
		private const ABA_DOCUMENTOS:Number = 1;
		
		private var registro:FonteRendaVO = new FonteRendaVO();
		private var registroBkp:FonteRendaVO = null;	
		private var ehPessoaJuridica: Boolean = false;
		private var _isRegistroBloqueado:Boolean = false;
		private var definicoesComponenteGED:ArrayCollection = null;
		private var listaTipoEmpresa:ArrayCollection = new ArrayCollection();
		
		private var pessoaProxy : PessoaPlataformaVO = ProcuraClientePlataformaCAPES.getPessoaSelecionada();
		private var pilotoHabilitado: Boolean;
		private const MESADA: Number = 13;
		private const NAO_POSSUI_RENDA: Number = 12;
		private var listaDocumetosGed: ArrayCollection = new ArrayCollection();
		
		public function frmEditarFonteRenda() {
			super();
			
			servicoConsulta = ServicoJavaUtil.getServico(FonteRendaSelecao.CLASSE_SERVICO);
			servicoConsulta.addEventListener(ResultEvent.RESULT, retornoCarregarRegistro);

			servicoDefinicao = ServicoJavaUtil.getServico(FonteRendaSelecao.CLASSE_SERVICO);
			servicoDefinicao.addEventListener(ResultEvent.RESULT, retornoObterDefinicoes);

			servicoSalvar = ServicoJavaUtil.getServico(FonteRendaSelecao.CLASSE_SERVICO);
			servicoSalvar.addEventListener(ResultEvent.RESULT, retornoSalvar);		

			servicoExcluir = ServicoJavaUtil.getServico(FonteRendaSelecao.CLASSE_SERVICO);
			servicoExcluir.addEventListener(ResultEvent.RESULT, retornoExcluir);			
			servicoExcluir.addEventListener(FaultEvent.FAULT, retornoExcluirErro);
			
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
			
		}

		//--------------------------------------------------------------------------
	    //  Métodos
	    //--------------------------------------------------------------------------
		private function init(evt:FlexEvent):void {
			
			dataCadastro.enabled = false;
			dataRendaTemporaria.selectedDate=null;
			dataRendaTemporaria.enabled=false;
			chkProvisorio.addEventListener(MouseEvent.CLICK, rendaProvisoriaSelecionada);
			txtReceitaBrutaMensal.addEventListener(FocusEvent.FOCUS_OUT, calcularRendaBrutaAnual);			
			dataRendaTemporaria.addEventListener(Event.CHANGE, calcularRendaBrutaAnual);
			cmbTipoRenda.addEventListener(Event.CHANGE, validarValorObrigatorio);
			
			//Adiciona o evento ao trocar aba.
			this.navegacao.addEventListener(IndexChangedEvent.CHANGE, aoTrocarAba);
			this.abaDocumentos.enabled = pessoaProxy.utilizaGedGft;
		}
		
		public override function dispose():void {
			super.dispose();
			abaDocumentos.dispose();
			navegacao.removeEventListener(Event.CHANGE, aoTrocarAba);
			navegacao.removeAllChildren();
			navegacao = null;
		}
		
		public function carregarDefinicoes(obj:Object=null):void {

			MostraCursor.setBusyCursor("Carregando Definições!", Application.application);
				
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.codTipoPessoa = (obj as PessoaPlataformaVO).codTipoPessoa;  
			servicoDefinicao.getOperation(OPERACAO_OBTER_DEFINICOES).send(dto);
		}
		
		private function configurarTipoPessoa():void  {

			var codigoTipoPessoa: Number = pessoaProxy.codTipoPessoa;
			ehPessoaJuridica = codigoTipoPessoa == Constantes.COD_TIPO_PES_JURIDICA;
			if(ehPessoaJuridica){
				rotuloEmpregador.visible = rotuloEmpregador.includeInLayout = !ehPessoaJuridica; 
				empregador.visible = empregador.includeInLayout = !ehPessoaJuridica;
			}else{
				removeDoLayout(ehPessoaJuridica);
			}
		}
		
		private function removeDoLayout(bol:Boolean):void{
			rotuloSimples.visible = rotuloSimples.includeInLayout = bol;
			chkSimplNacional.visible = chkSimplNacional.includeInLayout = bol;
			
			rotuloAtivoSuperior.visible = rotuloAtivoSuperior.includeInLayout = bol;
			chkAtivoSuperior.visible = chkAtivoSuperior.includeInLayout = bol;
		}
			
		public function restaurarRegistro():void {
			registro = (ObjectUtil.copy(registroBkp) as FonteRendaVO);
		}
		
		
		public function novoRegistro():void {
			carregarDefinicoes(pessoaProxy);
			_novo = true;
			registro = new FonteRendaVO();
			registroBkp = new FonteRendaVO();

			habilitaCamposExtras(_novo);
			limpaCampos();
			preencherCampos();
			limparGridDocumentos();
			
		}
		
		private function habilitaCamposExtras(bol: Boolean):void{
			rotuloDataHoraInicio.visible = rotuloDataHoraInicio.includeInLayout = !bol;
			rotuloUsuarioAlteracao.visible = rotuloUsuarioAlteracao.includeInLayout = !bol;
			dataCadastro.visible = dataCadastro.includeInLayout = !bol;
			usuarioAlteracao.visible = usuarioAlteracao.includeInLayout = !bol;
		}
		
		private function habilitaChkSimplesNacional(valorAnual: Number):void{
			for each (var voTemp:TipoEmpresaVO in listaTipoEmpresa){
				if(voTemp.isSimplesNacional.valor){
					if((valorAnual >= voTemp.valorLimiteInferior) && (valorAnual <= voTemp.valorLimiteSuperior)){
						habilitaEdicaoChkSimplesNacional();
						break;
					}
				}
				if(!voTemp.isSimplesNacional.valor){
					if((valorAnual >= voTemp.valorLimiteInferior) && (valorAnual <= voTemp.valorLimiteSuperior)){
						desabilitaEdicaoChkSimplesNacional();
						continue;
					}
				}				
			}
		}
		
		private function habilitaChkAtivoSuperior(valorAnual: Number):void{
			for each (var voTemp:TipoEmpresaVO in listaTipoEmpresa){
				if(voTemp.possuiAtivoSuperior.valor){
					if((valorAnual >= voTemp.valorLimiteInferior) && (valorAnual <= voTemp.valorLimiteSuperior)){
						habilitaEdicaoChkAtivoSuperior();
						break;
					}else{
						desabilitaEdicaoChkAtivoSuperior();
						continue;
					}
				}
			}
		}
		
		private function habilitaEdicaoChkSimplesNacional():void{
			chkSimplNacional.enabled = rotuloSimples.enabled = true;
			chkSimplNacional.selected = false;
		}
		
		private function desabilitaEdicaoChkSimplesNacional():void{
			chkSimplNacional.enabled = rotuloSimples.enabled = false;
			chkSimplNacional.selected = false;
		}
		
		private function habilitaEdicaoChkAtivoSuperior():void{
			chkAtivoSuperior.enabled = rotuloAtivoSuperior.enabled = true;
		}
		
		private function desabilitaEdicaoChkAtivoSuperior():void{
			chkAtivoSuperior.enabled = rotuloAtivoSuperior.enabled = false;
			chkAtivoSuperior.selected = false;
		}

		public function carregarRegistro(obj:Object):void {

			if(obj != null){
				_novo = false;
				MostraCursor.setBusyCursor("Carregando Registro ...", 
						Application.application, MostraCursor.CURSOR_PESQUISAR);
				carregarDefinicoes(pessoaProxy);			
				var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
				dto.dados.fonteRenda = obterFonteRenda(obj as FonteRendaProxy); 
				servicoConsulta.getOperation(OPERACAO_OBTER_DADOS).send(dto);
			}
		}
		
		private function limpaCampos():void{
			resetaValores();
		}

		public function preencherCampos():void {

			configurarTipoPessoa();			
			var tipoRenda: Object = null;
			var dataValidade: Date = null;
			var fixaVariavel: Boolean = !ehPessoaJuridica;
			
			if(registroBkp.dataValidadeRenda != null) {
				dataValidade = registroBkp.dataValidadeRenda.data;					
			}
			if(registroBkp.tipoFonteRenda != null) {
				tipoRenda = registroBkp.tipoFonteRenda.codigo;
			}
			
			if(isNaN(registroBkp.valorReceitaBrutaMensal)){
				resetaValores();
			}
			
			usuarioAlteracao.text = registroBkp.idUsuarioAprovacao;
			dataCadastro.selectedDate = registroBkp.dataHoraInicio != null ? registroBkp.dataHoraInicio.data : null;
			dataCadastro.enabled = false;
			txtDescRenda.text = registroBkp.descFonteRenda;
			txtReceitaBrutaMensal.valor = registroBkp.valorReceitaBrutaMensal;
			
			dataRendaTemporaria.selectedDate = dataValidade;
			cmbTipoRenda.procuraItemPorNome(tipoRenda, "codigo");
			cmbTipoRenda.dispatchEvent(new Event(Event.CHANGE));			
			
			if(registroBkp.pessoaEmpregador != null && registroBkp.pessoaEmpregador.pessoa != null) {
				empregador.txtCodigo.text = registroBkp.pessoaEmpregador.pessoa.cpfCnpj;
				empregador.procurarCodigo();
			} else {
				empregador.limpar();
			}
			
			if(_novo){
				desabilitaEdicaoChkSimplesNacional();
				desabilitaEdicaoChkAtivoSuperior();
			}else{
				fixaVariavel = registroBkp.bolRendaFixa.valor;
				this.rdbFixaVariavel.selectedValue = fixaVariavel;
				setaRendaAnual(registroBkp.valorReceitaBrutaMensal);
				calcularRendaBrutaAnual();
				this.chkSimplNacional.selected = registroBkp.bolSimplesNacional.valor;
				this.chkAtivoSuperior.selected = registroBkp.bolPossuiAtivo.valor;
			}
			
			verificarRendaTemporaria(registro.dataValidadeRenda != null);
			this.chkProvisorio.enabled = _novo;
		}
		
		private function setaRendaAnual(valorRendaMensal:Number):void{
			txtRendaAnual.valor = CadastroUnicoUtil.calcularRendaBrutaAnual(valorRendaMensal, dataRendaTemporaria.selectedDate as Date);
		}
		
		public function gravarRegistro():void {
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
			var obrigatorio: Boolean = validarObrigatoriedadeDocumento(isDocumento);
			
			if (obrigatorio) {
				Alerta.show(UploadDocGedUtil.VALIDACAO_DOCUMENTOS_OBRIGATORIOS + "comprovante de renda.", "ERRO!", Alerta.ALERTA_ERRO);
				return;
			}
			if (isDocumento) {
				Alerta.show(UploadDocGedUtil.VALIDACAO_DOCUMENTOS_NAO_SELECIONADOS, "ATENÇÃO", Alerta.ALERTA_PERGUNTA, null, onConfirmaGravarRegistro);
			}
			else {
				onConfirmaGravarRegistro();
			}
		}
		
		private function onConfirmaGravarRegistro(evt:Event = null):void {
			atualizarCamposRegistro();
			executarSeValido(gravarDados);
		}
		
		public function atualizarCamposRegistro():void {
		
			if (_novo) {
				registro = new FonteRendaVO();
			}
			
			if (cmbTipoRenda.selectedIndex != 0) {				
				registro.tipoFonteRenda = TipoFonteRendaVO(cmbTipoRenda.selectedItem);
			}
			
			registro.pessoaCompartilhamento = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(pessoaProxy);			
			registro.dataValidadeRenda = DateTimeBase.getDateTimeEntity(dataRendaTemporaria.selectedDate);
			registro.pessoaEmpregador = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(empregador.registro as PessoaPlataformaVO);
			registro.descFonteRenda = txtDescRenda.text;
			
			if(txtReceitaBrutaMensal.enabled) {
				registro.valorReceitaBrutaMensal = txtReceitaBrutaMensal.valor;
			}else {
				registro.valorReceitaBrutaMensal = new Number(0);
			}
			
			registro.bolRendaFixa =  new Booleano(this.rdbFixaVariavel.selectedValue);	
			registro.bolSimplesNacional =  new Booleano(this.chkSimplNacional.selected);
			registro.bolPossuiAtivo =  new Booleano(this.chkAtivoSuperior.selected);
			registro.documentosComprobatorios = this.abaDocumentos.obterDocumentosComprobatorios();
		}		
		
		private function gravarDados():void {
					
			MostraCursor.setBusyCursor("Gravando Registro!", Application.application);

			var dto:RequisicaoReqDTO = new RequisicaoReqDTO(); 
			dto.dados.fonteRenda = registro;
							 
			if (_novo) {
				servicoSalvar.getOperation(OPERACAO_INCLUIR_DADOS).send(dto);
			} else {
				servicoSalvar.getOperation(OPERACAO_ALTERAR_DADOS).send(dto);
			}	
		}
		
 		public function excluirRegistro(obj:Object):void {		
			
			MostraCursor.setBusyCursor("Excluindo Registros!", Application.application, 
					MostraCursor.CURSOR_EXCLUIR);
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.fonteRenda = obterFonteRenda(obj as FonteRendaProxy); 
			
			servicoExcluir.getOperation(OPERACAO_EXCLUIR_DADOS).send(dto);	
		}

		public function verificarAlteracoes():Boolean {

			var cpfCnpjEmpregador: String = "";
			var descFonteRenda: String = "";
			
			if(registroBkp.pessoaEmpregador != null
					&& registroBkp.pessoaEmpregador.pessoa != null 
					&& registroBkp.pessoaEmpregador.pessoa.cpfCnpj != null){
				cpfCnpjEmpregador = registroBkp.pessoaEmpregador.pessoa.cpfCnpj;
			} 
			
			if(registroBkp.descFonteRenda != null){
				descFonteRenda = registroBkp.descFonteRenda;
			}
							
			return (cmbTipoRenda.selectedItem != null ? cmbTipoRenda.selectedItem.codigo : null) == registroBkp.tipoFonteRenda.codigo
				&& rdbFixaVariavel.selectedValue == registroBkp.bolRendaFixa.valor
				&& chkSimplNacional.selected == registroBkp.bolSimplesNacional.valor
				&& chkAtivoSuperior.selected == registroBkp.bolPossuiAtivo.valor
				&& empregador.txtCodigo.text == cpfCnpjEmpregador
				&& txtReceitaBrutaMensal.valor == registroBkp.valorReceitaBrutaMensal
				&& txtDescRenda.text == descFonteRenda; 
		}
		
		private function rendaProvisoriaSelecionada(evt: MouseEvent): void {
			verificarRendaTemporaria(chkProvisorio.selected);
		}
		
		private function calcularRendaBrutaAnual(evt: Event=null): void {
			setaRendaAnual(txtReceitaBrutaMensal.valor);
			if(txtRendaAnual.valor >= 0){
				habilitaChkSimplesNacional(txtRendaAnual.valor);
				habilitaChkAtivoSuperior(txtRendaAnual.valor);
			}else{
				desabilitaEdicaoChkSimplesNacional();
				desabilitaEdicaoChkAtivoSuperior();
			}
		}
		
		private function verificarRendaTemporaria(habilitar: Boolean): void {
			
			dataRendaTemporaria.enabled = habilitar;
			dataRendaTemporaria.validarObrigatorio = habilitar;
			if(!habilitar) {
				dataRendaTemporaria.selectedDate = null;
			}
			chkProvisorio.selected = habilitar;
		}
		
		//--------------------------------------------------------------------------
		// Métodos de retorno
		//--------------------------------------------------------------------------
		private function retornoObterDefinicoes(evt:ResultEvent):void {			

			MostraCursor.removeBusyCursor();
			cmbTipoRenda.dataProvider = evt.result.dados.listaTiposFonteRenda;
			cmbTipoRenda.validateNow();
			listaTipoEmpresa.addAll(evt.result.dados.listaTipoEmpresa);
			
			definicoesComponenteGED = evt.result.dados.definicoesComponenteGED;
			pilotoHabilitado = evt.result.dados.pilotoHabilitado;
			
			carregarDocumentosGed();
			
		}
		
		private function carregarDocumentosGed(): void {
			this.listaDocumetosGed = new ArrayCollection();
			if (!pilotoHabilitado || this.registro == null || this.registro.idRegistroControlado == null) {
				return;
			}
			UploadDocGedUtil.recuperarDocumentosGED(this.destino, this.registro.idRegistroControlado, 
				pessoaProxy.codTipoPessoa, retornoListaGed);
		}
		
		public function retornoListaGed(event:ResultEvent):void {
			this.listaDocumetosGed = event.result.dados.listaDocumentosGED as ArrayCollection;
		}
		
		private function retornoCarregarRegistro(evt:ResultEvent):void {
			registro = evt.result.dados["fonteRenda"];
			
			//Adiciona os documentos ao componente.
			this.abaDocumentos.carregarDocumentos(UploadDocGedUtil.criarListaDocumentos(registro.documentosComprobatorios));
			
			//Verifica se o registro está bloqueado para alteração.
			_isRegistroBloqueado = evt.result.dados.isRegistroBloqueadoAlteracao;
			
			registroBkp = ObjectUtil.copy(registro) as FonteRendaVO;
			_novo = false;
			habilitaCamposExtras(_novo);
			preencherCampos();
			limparGridDocumentos();
			
			carregarDocumentosGed();
			
			this.dispatchEvent(new Event(REGISTRO_CARREGADO));
		}
		
		private function retornoSalvar(evt:ResultEvent):void {
			registro = evt.result.dados["fonteRenda"];
			MostraCursor.removeBusyCursor();
			
			var mensagemValidacao:String = evt.result.dados.mensagemValidacao;
			if(mensagemValidacao != null && mensagemValidacao != "") {
				Alerta.show(mensagemValidacao, "Validação", Alerta.ALERTA_INFORMACAO);
			}
			
	  		this.dispatchEvent(new EventAssistenteAtendimento(EventAssistenteAtendimento.EVENTO_CONFIRMAR_MUDANCA_TELA));
			this.dispatchEvent(new Event(Modulo.REGISTRO_GRAVADO));			
		}

		private function retornoExcluir(evt:ResultEvent):void {
			
			registro = evt.result.dados["fonteRenda"];
			MostraCursor.removeBusyCursor();
			this.dispatchEvent(new Event(REGISTRO_EXCLUIDO));  
		}
		
		public function retornoExcluirErro(evt:FaultEvent):void {
			 
			MostraCursor.removeBusyCursor();			
			this.dispatchEvent(new Event(REGISTRO_EXCLUIDO));							
		}			
		
		[Deprecated]
		public function getEntidadeVigente():VigenteVO {
			return registro;
		}
		
		public function getEntidadeAprovavel():Aprovavel {
			return null;//registro;
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
			var objChaveDoc:Object = null;
			
			//Criar os valores das chaves na ordem que são adicionados na fachada.
			var valoresChavesNegocio:ArrayCollection = criarValoresChavesNegocio();
			var definicaoGED:Object = null;
			
			if (definicoesComponenteGED!=null){
				for(var i:uint = 0; i < definicoesComponenteGED.length; i++){
					definicaoGED = definicoesComponenteGED[i];
					
					//Adiciona a chave da pessoa (PF/PJ)
					objChaveDoc = new Object();
					objChaveDoc.siglaTipoDocumento = definicaoGED.siglaTipoDocumento;
					objChaveDoc.siglaChaveDocumento = definicaoGED.obterCodigoTipoPessoaSelecionada;
					objChaveDoc.valorChave = pessoaProxy.cpfCnpj;
					listaValoresChaves.addItem(objChaveDoc);
					
					//Percorre a lista para preencher as outras chaves.
					for (var x:uint = 0; x < definicaoGED.chavesNegocio.length; x++){
						objChaveDoc = new Object();
						
						objChaveDoc.siglaTipoDocumento = definicaoGED.siglaTipoDocumento;
						objChaveDoc.siglaChaveDocumento = definicaoGED.chavesNegocio[x];
						objChaveDoc.valorChave = valoresChavesNegocio[x];
						listaValoresChaves.addItem(objChaveDoc);
					}
				}
			}
			return listaValoresChaves;
		}
		
		/**
		 * Método para adicionar os valores das chaves de negócio à um array,
		 * de acordo com o esperado pelas chaves de negócio.
		 */
		private function criarValoresChavesNegocio():ArrayCollection {
			var valoresChavesNegocio:ArrayCollection = new ArrayCollection();
			
			valoresChavesNegocio.addItem(pessoaProxy.codCompartilhamentoCadastro);
			
			if(!isNaN(cmbTipoRenda.selectedIndex) && cmbTipoRenda.selectedIndex != 0){
				valoresChavesNegocio.addItem(cmbTipoRenda.selectedItem.codigo);
			}else{
				valoresChavesNegocio.addItem(undefined);
			}
									
			return valoresChavesNegocio;
		}
		
		public function isRegistroBloqueadoAlteracao():Boolean{
			return _isRegistroBloqueado; 
		}
		
		private function limparGridDocumentos():void{
			this.abaDocumentos.limparDocumentos();
			this.navegacao.selectedIndex = 0;
		}
		
		public function configurarDestinosServicos(destinoVO:DestinoVO):void{
			this.destino = destinoVO;
			servicoConsulta.configurarDestino(destinoVO);
			servicoConsulta.configurarDestino(destinoVO);
			servicoDefinicao.configurarDestino(destinoVO);
			servicoSalvar.configurarDestino(destinoVO);
			servicoExcluir.configurarDestino(destinoVO);
		}
		
		private function obterFonteRenda(fonteRendaProxy:FonteRendaProxy): FonteRendaVO {
			var fonteRenda:FonteRendaVO = new FonteRendaVO();
			
			fonteRenda.bolRendaFixa = new Booleano(fonteRendaProxy.bolRendaFixa);
			fonteRenda.bolSimplesNacional = new Booleano(fonteRendaProxy.bolSimplesNacional);
			fonteRenda.codigoSituacaoAprovacao = fonteRendaProxy.codigoSituacaoAprovacao;
			fonteRenda.dataHoraInicio = fonteRendaProxy.dataHoraInicio;
			fonteRenda.dataValidadeRenda = fonteRendaProxy.dataValidadeRenda;
			fonteRenda.descFonteRenda = fonteRendaProxy.descFonteRenda;
			fonteRenda.documentosComprobatorios = this.abaDocumentos.obterDocumentosComprobatorios();
			fonteRenda.idFonteRenda = fonteRendaProxy.idFonteRenda;
			
			if(fonteRendaProxy.idInstituicaoAtualizacao != 0){
				fonteRenda.idInstituicaoAtualizacao = fonteRendaProxy.idInstituicaoAtualizacao;
			}
			
			fonteRenda.idRegistroControlado = fonteRendaProxy.idRegistroControlado;
			fonteRenda.pessoaCompartilhamento = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(pessoaProxy);
			fonteRenda.situacaoAprovacao = fonteRendaProxy.situacaoAprovacao;
			fonteRenda.valorReceitaBrutaMensal = fonteRendaProxy.valorReceitaBrutaMensal;
			
			return fonteRenda;
		}
		
		private function resetaValores():void{
			txtReceitaBrutaMensal.valor = NaN;
			txtRendaAnual.valor = NaN;
		}
		
		private function validarValorObrigatorio(evento:Event):void {
			var tipoRenda:TipoFonteRendaVO = cmbTipoRenda.selectedItem as TipoFonteRendaVO;
			
			if(tipoRenda != null){
				var obrigatorio:Boolean = tipoRenda.valorObrigatorio.valor;
				txtReceitaBrutaMensal.enabled = obrigatorio;
				txtReceitaBrutaMensal.validarObrigatorio = obrigatorio;
				if(!obrigatorio){
					resetaValores();
					desabilitaEdicaoChkAtivoSuperior();
					desabilitaEdicaoChkSimplesNacional();
				}
			}else{
				txtReceitaBrutaMensal.enabled = true;
				txtReceitaBrutaMensal.validarObrigatorio = true;
			}
		}
		
		private function validarObrigatoriedadeDocumento(isDocumento:Boolean):Boolean {
			if (!pilotoHabilitado || !isDocumento) {
				return false;
			}
			var status: Boolean = false;
			
			if (this.listaDocumetosGed.length <= 0 && isDocumento) {
				status = validarTipoRenda();
			}
			return status;
		}
		
		private function validarTipoRenda():Boolean {
			if (cmbTipoRenda == null || cmbTipoRenda.selectedItem == null) {
				if(pessoaProxy.codTipoPessoa == 1) {
					return true;
				}
				return false;
			}
			var tipoRenda:TipoFonteRendaVO = TipoFonteRendaVO(cmbTipoRenda.selectedItem);
			var fonteRenda: Number = tipoRenda.codigo;
			var validar: Boolean = true;
			
			if (pessoaProxy.codTipoPessoa == 0) {
				if (MESADA == fonteRenda || NAO_POSSUI_RENDA == fonteRenda) {
					validar = false;
				}
			}
			return validar;
		}
		
		private function isDocumentoSelecionado():Boolean {
			return pessoaProxy.utilizaGedGft && this.abaDocumentos.obterDocumentosComprobatorios().length <= 0;
		}
	}
}