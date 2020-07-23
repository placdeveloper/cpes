package br.com.sicoob.capes.cadastrarContato
{
	import flash.events.Event;
	import flash.events.FocusEvent;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.events.IndexChangedEvent;
	import mx.events.ListEvent;
	import mx.formatters.DateFormatter;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.ObjectUtil;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.ParametrosAssistenteAtendimento;
	import br.com.bancoob.sisbr.componentes.pti.ObjetoEvent;
	import br.com.bancoob.sisbr.eventos.EventAssistenteAtendimento;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.componentes.pesquisaendereco.TelaPesquisaEndereco;
	import br.com.sicoob.capes.comum.util.CadastroUnicoUtil;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.VigenteVO;
	import br.com.sicoob.capes.comum.vo.entidades.EnderecoVO;
	import br.com.sicoob.capes.comum.vo.entidades.LocalidadeVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoEnderecoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoLogradouroVO;
	import br.com.sicoob.capes.corporativo.componentes.plataformaatendimento.ProcuraClientePlataformaCAPES;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	import br.com.sicoob.capes.utils.ConversaoPessoaUtil;
	import br.com.sicoob.capes.utils.UploadDocGedUtil;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCAPESBase;
	
	public class frmEditarEndereco extends frmEditarEnderecoView implements IEdicaoContato {
		
		private static const CLASSE_SERVICO: String = "br.com.sicoob.capes.cadastro.fachada.EnderecoFachada";
		
		private var servicoEdicao:ServicoJava = ServicoJavaUtil.getServico(CLASSE_SERVICO);
		private var servicoConsulta:ServicoJava = ServicoJavaUtil.getServico(CLASSE_SERVICO);
		private var servicoDefinicao:ServicoJava = ServicoJavaUtil.getServico(CLASSE_SERVICO);
		private var servicoExcluir:ServicoJava = ServicoJavaUtil.getServico(CLASSE_SERVICO);
		
		private var janelaConsultaEndereco:Janela;
		
		[Bindable]
		private var registro:EnderecoVO = new EnderecoVO();
		private var registroBkp:EnderecoVO = new EnderecoVO();
		
		private const ABA_DOCUMENTOS:Number = 1;
		
		//Objeto que guardará as informações para serem usadas no componente GED.
		private var definicoesComponenteGED:Object = new Object;
		
		//Guarda as informações da pessoa selecionada na plataforma
		private var pessoaSelecionada:PessoaPlataformaVO;
		
		private var _isRegistroBloqueado:Boolean = false;
		private var pilotoHabilitado: Boolean;
		private const ENDERECO_RESIDENCIA: Number = 0;
		private const ENDERECO_COMERCIAL: Number = 1;
		private const FISICA: Number = 0;
		private var listaDocumetosGed: ArrayCollection = new ArrayCollection();
		
		/**
		 *  Construtor.
		 */
		public function frmEditarEndereco(){
			super();
			
			servicoDefinicao.addEventListener(ResultEvent.RESULT, retornoObterDefinicoes);
			servicoConsulta.addEventListener(ResultEvent.RESULT, retornoCarregarRegistro);
			servicoEdicao.addEventListener(ResultEvent.RESULT, retornoEdicao);
			servicoExcluir.addEventListener(ResultEvent.RESULT, retornoExcluir);
			servicoExcluir.addEventListener(FaultEvent.FAULT, retornoExcluirErro);
			
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		private function init(evt:FlexEvent):void {
			pessoaSelecionada = ProcuraClientePlataformaCAPES.getPessoaSelecionada();
			this.cmbTipoEndereco.dataProvider = new ArrayCollection();	
			carregarDefinicoes();
			cmbTipoLogradouro.addEventListener(ListEvent.CHANGE, preencherEndereco);
			logradouro.addEventListener(FocusEvent.FOCUS_OUT, preencherEndereco);
			checkNumero.addEventListener(Event.CHANGE, bloquearCampoNumero);
			
			componentePesquisaEndereco.configurarDestino(this.destino);
			componentePesquisaEndereco.addEventListener(TelaPesquisaEndereco.EVENTO_REGISTRO_SELECIONADO, preencherCamposEnderecoConsultado);
			
			//Adiciona o evento ao trocar aba.
			this.navegacao.addEventListener(IndexChangedEvent.CHANGE, aoTrocarAba);
			this.abaDocumentos.enabled = pessoaSelecionada.utilizaGedGft;
			
			PortalModel.portal.obterDefinicoesDestino("servicosJava", onDestinoPortalRecuperado);
		}
		
		private function onDestinoPortalRecuperado(destino:DestinoVO):void {
			cmpPesquisaMunicipio.configurarDestino(destino);
		}
		
		public override function dispose():void {
			super.dispose();
			if(initialized) {
				if(abaDocumentos != null) {
					abaDocumentos.dispose();
				}
				navegacao.removeEventListener(Event.CHANGE, aoTrocarAba);
				navegacao.removeAllChildren();
				navegacao = null;
			}
		}
		
		private function preencherCamposEnderecoConsultado(evento:ObjetoEvent):void {
			var localidade:br.com.sicoob.capes.comum.vo.LocalidadeVO = evento.objeto as br.com.sicoob.capes.comum.vo.LocalidadeVO;
			
			if(localidade != null) {
				selecionarComboTipoLogradouro(localidade.idTipoLogradouro);
				
				if (localidade.nomeLogradouro != null) {
					this.logradouro.text = localidade.nomeLogradouro.toUpperCase();
				} else {
					this.logradouro.text = "";
				}
				
				if (localidade.nomeBairro != null) {
					this.bairro.text = localidade.nomeBairro.toUpperCase();
				} else {
					this.bairro.text = "";
				}
				
				if (localidade.descComplementoLogradouro != null &&  localidade.descComplementoLogradouro != "NULL") {
					this.complemento.text = localidade.descComplementoLogradouro.toUpperCase();
				} else {
					this.complemento.text = "";
				}
				
				if (localidade.idLocalidadePai && localidade.idLocalidadePai != 0) {
					this.cmpPesquisaMunicipio.txtValor.text = String(localidade.idLocalidadePai);
				} else {
					this.cmpPesquisaMunicipio.txtValor.text = String(localidade.idLocalidade);
				}
				
				this.cmpPesquisaMunicipio.pesquisar();
				
				preencherEndereco(null);
			}
		}
		
		private function selecionarComboTipoLogradouro(idTipoLogradouro:Number):void {
			for each (var o:Object in this.cmbTipoLogradouro.dataProvider) {
				if(o.idTipoLogradouro == idTipoLogradouro) {
					this.cmbTipoLogradouro.selectedItem = o;
					break;
				}
			}
		}
		
		public function configurarDestinosServicos(destinoVO:DestinoVO):void {
			this.destino = destinoVO;
			servicoDefinicao.configurarDestino(destinoVO);
			servicoEdicao.configurarDestino(destinoVO);
			servicoConsulta.configurarDestino(destinoVO);
			servicoExcluir.configurarDestino(destinoVO);
		}	
		
		public function carregarDefinicoes(obj:Object=null):void {
			MostraCursor.setBusyCursor("Obtendo definições ...", 
				Application.application, MostraCursor.CURSOR_PROGRESSO);
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			//Envia o tipo de pessoa para ser utilizado nas chaves de negócio.
			dto.dados.idTipoPessoa = pessoaSelecionada.codTipoPessoa;
			
			servicoDefinicao.getOperation(ContatoPessoaSelecao.OPERACAO_OBTER_DEFINICOES).send(dto);
		}
		
		public function carregarRegistro(obj:Object): void {
			MostraCursor.setBusyCursor("Obtendo definições ...", Application.application, MostraCursor.CURSOR_PROGRESSO);
			
			var endereco: EnderecoVO = new EnderecoVO();
			endereco.idEndereco = obj.idEndereco;
			endereco.idInstituicaoAtualizacao = obj.idInstituicaoAtualizacao;
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.endereco = endereco;
			servicoConsulta.getOperation(ContatoPessoaSelecao.OPERACAO_OBTER).send(dto);
		}		
		
		public function excluirRegistro(endereco:Object):void {
			MostraCursor.setBusyCursor("Excluindo Registros!", Application.application, MostraCursor.CURSOR_EXCLUIR);
			
			//Verifica se o endereço é de correspondência
			if(endereco.padraoCorrespondencia){
				Alerta.show("Exclus\u00e3o n\u00e3o permitida, o endere\u00e7o \u00e9 de correspond\u00eancia em alguma institui\u00e7\u00e3o.", "AVISO!");
				return;
			}
			
			var vo:EnderecoVO = new EnderecoVO();
			vo.idEndereco = endereco.idEndereco;
			vo.dataHoraInicio = endereco.dataHoraInicio;
			vo.pessoaCompartilhamento = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(ProcuraClientePlataformaCAPES.getPessoaSelecionada());
			vo.situacaoAprovacao = endereco.situacaoAprovacao;
			
			if(endereco.idInstituicaoAtualizacao != null && endereco.idInstituicaoAtualizacao > 0){
				vo.idInstituicaoAtualizacao = endereco.idInstituicaoAtualizacao;
			}
			
			if(endereco.codigoSituacaoAprovacao != null && endereco.codigoSituacaoAprovacao > 0){
				vo.codigoSituacaoAprovacao = endereco.codigoSituacaoAprovacao;
			}
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.endereco = vo;
			dto.dados.pessoa = TelaPlataformaAtendimentoCAPESBase.getPessoaSelecionada();
			servicoExcluir.getOperation(ContatoPessoaSelecao.OPERACAO_EXCLUIR).send(dto);
		}
		
		private function convertDataToString(data: Date):String{
			var fmt:DateFormatter = new DateFormatter();
			fmt.formatString = "DD/MM/YYYY";
			return fmt.format(data);
		}
		
		public function preencherCampos():void {
			var tipoEndereco: Object = null;
			var tipoLogradouro: Object = null;
			var localidade:LocalidadeVO = registroBkp.localidade;
			
			if(registroBkp.tipoEndereco != null) {
				tipoEndereco = registroBkp.tipoEndereco.codigo;
			}
			if(registroBkp.tipoLogradouro != null) {
				tipoLogradouro = registroBkp.tipoLogradouro.idTipoLogradouro;
			}
			
			componentePesquisaEndereco.campoCEP.text = registroBkp.cep;
			logradouro.text = registroBkp.descricao;
			numero.text = registroBkp.numero;
			complemento.text = registroBkp.complemento;
			bairro.text = registroBkp.bairro;
			cmbTipoEndereco.procuraItemPorNome(tipoEndereco, "codigo"); 
			cmbTipoLogradouro.procuraItemPorNome(tipoLogradouro, "idTipoLogradouro");
			dataHoraInicio.text = registroBkp.dataHoraInicio != null ? convertDataToString(registroBkp.dataHoraInicio.data) : null;
			usuarioAlteracao.text = registroBkp.idUsuarioAprovacao;
			
			if(localidade != null && !isNaN(localidade.idLocalidade)) {
				this.cmpPesquisaMunicipio.txtValor.text = String(localidade.idLocalidade);
				this.cmpPesquisaMunicipio.pesquisar();
			} else {
				this.cmpPesquisaMunicipio.txtValor.text = "";
				this.cmpPesquisaMunicipio.lblDescricao.text = "";
			}
			
			preencherEndereco();
			verificarNumero();
			
		}
		
		private function carregarDocumentosGed(): void {
			this.listaDocumetosGed = new ArrayCollection();
			if (!pilotoHabilitado || this.registro == null || this.registro.idRegistroControlado == null) {
				return;
			}
			UploadDocGedUtil.recuperarDocumentosGED(this.destino, this.registro.idRegistroControlado, 
				pessoaSelecionada.codTipoPessoa, retornoListaGed);
		}
		
		private function habilitaCamposExtras(bol: Boolean):void{
			rotuloDataHoraInicio.visible = rotuloDataHoraInicio.includeInLayout = !bol;
			rotuloUsuarioAlteracao.visible = rotuloUsuarioAlteracao.includeInLayout = !bol;
			dataHoraInicio.visible = dataHoraInicio.includeInLayout = !bol;
			usuarioAlteracao.visible = usuarioAlteracao.includeInLayout = !bol;
		}
		
		private function preencherEndereco(evt:Event = null): void {
			var tipo:TipoLogradouroVO = cmbTipoLogradouro.selectedItem as TipoLogradouroVO;
			var descricao:String = '';
			
			if(tipo != null) {
				descricao = tipo.nome + ' '; 
			}
			
			descricao = descricao + logradouro.text
			endereco.text = descricao.toUpperCase();
		}
		
		public function novoRegistro():void {
			_novo = true;
			registro = new EnderecoVO();
			registroBkp = new EnderecoVO();
			
			habilitaCamposExtras(_novo);
			preencherCampos();
			
			limparGridDocumentos();
			
			carregarDocumentosGed();
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
			var isDocumentos:Boolean = isDocumentoSelecionado();
			var obrigatorio: Boolean = validarObrigatoriedadeDocumento(isDocumentos);
			
			if (obrigatorio) {
				Alerta.show(UploadDocGedUtil.VALIDACAO_DOCUMENTOS_OBRIGATORIOS + " comprovante de endereço.", "ERRO!", Alerta.ALERTA_ERRO);
				return;
			}
			
			if (isDocumentos) {
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
		
		public function atualizarCamposRegistro():void{
			
			if (_novo) {
				registro = new EnderecoVO();
			}
			
			var localidade: LocalidadeVO = null;
			
			if(!CadastroUnicoUtil.isVazio(cmpPesquisaMunicipio.txtValor.text)) {
				localidade = new LocalidadeVO();
				localidade.idLocalidade = Number(cmpPesquisaMunicipio.txtValor.text);
			}			
			
			registro.tipoEndereco = cmbTipoEndereco.selectedItem as TipoEnderecoVO;
			registro.cep = componentePesquisaEndereco.campoCEP.text;
			registro.tipoLogradouro = cmbTipoLogradouro.selectedItem as TipoLogradouroVO; 
			registro.descricao = logradouro.text;
			registro.numero = numero.text;
			registro.complemento = complemento.text;
			registro.bairro = bairro.text;
			registro.pessoaCompartilhamento = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(ProcuraClientePlataformaCAPES.getPessoaSelecionada());

			registro.localidade = localidade;
			
			//Adiciona ao endereço, os documentos que foram enviados ao GED.
			registro.documentosComprobatorios = this.abaDocumentos.obterDocumentosComprobatorios();
		}
		
		//--------------------------------------------------------------------------
		//  Métodos de auxiliares.
		//--------------------------------------------------------------------------	
		private function gravarDados(): void {
			
			MostraCursor.setBusyCursor("Gravando Registro!", Application.application,
				MostraCursor.CURSOR_GRAVAR);
			
			var dto: RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.endereco = registro;
			dto.dados.pessoa = TelaPlataformaAtendimentoCAPESBase.getPessoaSelecionada();
			
			if (_novo) {
				servicoEdicao.getOperation(ContatoPessoaSelecao.OPERACAO_INCLUIR).send(dto);
			} else {
				servicoEdicao.getOperation(ContatoPessoaSelecao.OPERACAO_ALTERAR).send(dto);
			}
			
		}
		
		public function restaurarRegistro():void{
			registro = (ObjectUtil.copy(registroBkp) as EnderecoVO);
		}
		
		public function verificarAlteracoes():Boolean {
			return (cmbTipoEndereco.selectedItem != null && cmbTipoEndereco.selectedItem.codigo == registroBkp.tipoEndereco.codigo)
				&& (cmbTipoLogradouro.selectedItem != null && cmbTipoLogradouro.selectedItem.idTipoLogradouro == registroBkp.tipoLogradouro.idTipoLogradouro)
				&& componentePesquisaEndereco.campoCEP.text == registroBkp.cep
				&& logradouro.text == registroBkp.descricao
				&& numero.text == registroBkp.numero
				&& complemento.text == registroBkp.complemento 
				&& bairro.text == registroBkp.bairro
				&& Number(cmpPesquisaMunicipio.txtValor.text) == registroBkp.localidade.idLocalidade;
		}		
		
		//--------------------------------------------------------------------------
		//  Métodos de callback.
		//--------------------------------------------------------------------------				
		private function retornoObterDefinicoes(event: ResultEvent): void {
			MostraCursor.removeBusyCursor();
			if(event.result.dados.tiposEndereco != null) {
				cmbTipoEndereco.dataProvider = event.result.dados.tiposEndereco;	
				cmbTipoEndereco.validateNow();
			}
			if(event.result.dados.tiposLogradouro != null) {
				cmbTipoLogradouro.dataProvider = event.result.dados.tiposLogradouro;	
				cmbTipoLogradouro.validateNow();
			}
			preencherCampos();
			//Atribui ao objeto as informações que serão utilizadas no componente GED, configuradas na fachada.
			definicoesComponenteGED = event.result.dados.definicoesComponenteGED;
			pilotoHabilitado = event.result.dados.pilotoHabilitado;
			
			carregarDocumentosGed();
		}
		
		public function retornoListaGed(event:ResultEvent):void {
			this.listaDocumetosGed = event.result.dados.listaDocumentosGED as ArrayCollection;
		}				
		
		private function retornoCarregarRegistro(evt:ResultEvent):void {
			registro = evt.result.dados.endereco;
			registroBkp = ObjectUtil.copy(registro) as EnderecoVO;			
			
			//Adiciona os documentos da certidão ao componente.
			this.abaDocumentos.carregarDocumentos(UploadDocGedUtil.criarListaDocumentos(registro.documentosComprobatorios));
			
			//Verifica se o registro está bloqueado para alteração.
			_isRegistroBloqueado = evt.result.dados.isRegistroBloqueadoAlteracao;
			_novo = false;
			
			limparGridDocumentos();
			habilitaCamposExtras(_novo);
			preencherCampos();
			
			this.dispatchEvent(new Event(REGISTRO_CARREGADO));	
		}
		
		private function retornoEdicao(evt: ResultEvent): void {
			registro = evt.result.dados.endereco; 
			MostraCursor.removeBusyCursor();
			this.dispatchEvent(new EventAssistenteAtendimento(EventAssistenteAtendimento.EVENTO_CONFIRMAR_MUDANCA_TELA));
			this.dispatchEvent(new Event(Modulo.REGISTRO_GRAVADO));	
			//work around para remover o foco do botão salvar, 
			//fazia com que ao apertar enter depois de salvar o registro ele era incluido novamente.
			logradouro.setFocus();
		}				
		
		public function retornoExcluir(evt:ResultEvent):void {
			registro = evt.result.dados.endereco;
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
		
		public function isRegistroBloqueadoAlteracao():Boolean{
			return _isRegistroBloqueado;
		}
		
		/** Interface ITelaBasePlataformaAtendimento*/
		public function trocarTelaEntrar(params:ParametrosAssistenteAtendimento=null):void{};
		public function trocarTelaSair(params:ParametrosAssistenteAtendimento=null):void{};
		
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
			
			if(!isNaN(cmbTipoEndereco.selectedIndex) && cmbTipoEndereco.selectedIndex != 0){
				valoresChavesNegocio.addItem(cmbTipoEndereco.selectedItem.codigo);
			}else{
				valoresChavesNegocio.addItem(undefined);
			}
			
			if(!isNaN(componentePesquisaEndereco.campoCEP.valor) && componentePesquisaEndereco.campoCEP.valor != 0){
				valoresChavesNegocio.addItem(componentePesquisaEndereco.campoCEP.valor);
			}else{
				valoresChavesNegocio.addItem(undefined);
			}
			
			return valoresChavesNegocio;
		}
		
		private function limparGridDocumentos():void{
			this.abaDocumentos.limparDocumentos();
			this.navegacao.selectedIndex = 0;
		}
		
		private function verificarNumero(evento:Event = null): void {
			if(numero.text == "S/N"){
				checkNumero.selected = true;
			}else {
				checkNumero.selected = false;
			}
			
			bloquearCampoNumero();
		}
		
		private function bloquearCampoNumero(evento:Event = null):void {
			if(checkNumero.selected) {
				numero.text = "S/N";
				numero.enabled = false;
			}else {
				numero.text = "";
				numero.text = registro.numero;
				numero.enabled = true;
			}
		}
		private function validarObrigatoriedadeDocumento(isDocumentos:Boolean):Boolean {
			if (!pilotoHabilitado || !isDocumentos) {
				return false;
			}
			var status: Boolean = false;
			
			if (this.listaDocumetosGed.length <= 0 && isDocumentos) {
				status = validarTipoEndereco();
			}
			return status;
		}
		
		private function validarTipoEndereco():Boolean {
			if (cmbTipoEndereco == null || cmbTipoEndereco.selectedItem == null || tipoEndereco == null) {
				return false;
			}
			var tipoEndereco: TipoEnderecoVO = TipoEnderecoVO(cmbTipoEndereco.selectedItem);
			var tipoEnderecoSelecionado: Number = tipoEndereco.codigo;
			var sucesso: Boolean = false;
			
			if (pessoaSelecionada.codTipoPessoa == FISICA) {
				sucesso = (ENDERECO_RESIDENCIA == tipoEnderecoSelecionado);//0
			} else {
				sucesso = (ENDERECO_COMERCIAL == tipoEnderecoSelecionado);//1
			}
			return sucesso;
		}
		
		private function isDocumentoSelecionado():Boolean {
			return pessoaSelecionada.utilizaGedGft && this.abaDocumentos.obterDocumentosComprobatorios().length <= 0;
		}
	}
}