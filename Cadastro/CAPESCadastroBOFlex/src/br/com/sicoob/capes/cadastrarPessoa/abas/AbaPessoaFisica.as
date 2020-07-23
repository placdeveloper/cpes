package br.com.sicoob.capes.cadastrarPessoa.abas {
	import flash.events.Event;
	import flash.events.FocusEvent;
	
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.ObjectUtil;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.eventos.SelecaoEvent;
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.tipos.Booleano;
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.util.DateUtils;
	import br.com.bancoob.util.FormataNumero;
	import br.com.bancoob.util.validadores.CNPJ;
	import br.com.bancoob.util.validadores.CPF;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastrarPessoa.IAbaCadastroPessoa;
	import br.com.sicoob.capes.comum.enums.EstadoCivilEnum;
	import br.com.sicoob.capes.comum.util.CadastroUnicoUtil;
	import br.com.sicoob.capes.comum.vo.entidades.EstadoCivilVO;
	import br.com.sicoob.capes.comum.vo.entidades.GrauInstrucaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.NacionalidadeVO;
	import br.com.sicoob.capes.comum.vo.entidades.OcupacaoProfissionalVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaFisicaVO;
	import br.com.sicoob.capes.comum.vo.entidades.RegimeCasamentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoDocumentoIdentificacaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.UFVO;
	import br.com.sicoob.capes.comum.vo.entidades.VinculoEmpregaticioVO;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	import br.com.sicoob.capes.utils.ConversaoPessoaUtil;
	
	public class AbaPessoaFisica extends AbaPessoaFisicaView implements IAbaCadastroPessoa {

		public static const milisegundosPorDia:Number = 1000 * 60 * 60 * 24;
		public static const milisegundosPorAno:Number = milisegundosPorDia * 365.25;
		private var registroBkp:PessoaFisicaVO = null;
		private var isGestorCadastro:Boolean = false;
		private var isIntegracaoSRFLigada:Boolean = false;
		
		private static const textoParentescoNaoInformado:String = "NÃO INFORMADO NO DOCUMENTO DE IDENTIFICAÇÃO";
		
		public function AbaPessoaFisica()
		{
			super();
			this.addEventListener(FlexEvent.CREATION_COMPLETE,init);
		}

		private function init(event:Event):void{
			procurarNacionalidade.configurarDestino(destino);
			procurarPessoa.configurarDestino(destino);
			procurarPessoa.addEventListener(FocusEvent.FOCUS_OUT, validarConjugeInvalido);
			procurarProfissao.configurarDestino(destino);
			procurarNacionalidade.addEventListener(SelecaoEvent.OBJETO_SELECIONADO, verificarNacionalidade);
			txtDataNascimento.addEventListener(FocusEvent.FOCUS_OUT, verificarEmancipacao);
			cboEstadoCivil.addEventListener(ListEvent.CHANGE, verificarRegimeCasamento);
			checkNomeMaeNaoInformado.addEventListener(Event.CHANGE, atribuirNomeMaeNaoInformado);
			checkNomePaiNaoInformado.addEventListener(Event.CHANGE, atribuirNomePaiNaoInformado);
			cboVinculoEmpregaticio.addEventListener(ListEvent.CHANGE, atribuirLabelVinculoEmpregaticio);
			
			desabilitarCamposData();
			
			PortalModel.portal.obterDefinicoesDestino("servicosJava", onDestinoPortalRecuperado);
		}
		
		private function onDestinoPortalRecuperado(destino:DestinoVO):void {
			procurarNaturalidade.configurarDestino(destino);
		}
		
		private function atribuirNomeMaeNaoInformado(event:Event=null):void {	
			if(checkNomeMaeNaoInformado.selected) {
				txtNomeMae.text = textoParentescoNaoInformado;
				txtNomeMae.enabled = false;
				if(checkNomePaiNaoInformado.selected) {
					checkNomePaiNaoInformado.selected = false;
					txtNomePai.text = "";
					txtNomePai.enabled = true;
				}
			} else {
				txtNomeMae.text = "";
				txtNomeMae.enabled = true;
			}
		}
		
		private function atribuirNomePaiNaoInformado(event:Event=null):void {	
			if(checkNomePaiNaoInformado.selected) {
				txtNomePai.text = textoParentescoNaoInformado;
				txtNomePai.enabled = false;
				if(checkNomeMaeNaoInformado.selected) {
					checkNomeMaeNaoInformado.selected = false;
					txtNomeMae.text = "";
					txtNomeMae.enabled = true;
				}
			} else {
				txtNomePai.text = "";
				txtNomePai.enabled = true;
			}
		}
		
		public function preencherCampos(pessoa:PessoaCompartilhamentoVO):void {
			this.cboUFOrgaoExpedidor.validateNow();
			var registro: PessoaFisicaVO = PessoaFisicaVO(pessoa);
			
			var dataEmissaoDocumento :Date = null;
			var dataNascimento :Date = null;
			var tipoDocumento: Object = null; 
			var grau: Object = null; 
			var vinculo: Object = null; 
			var estadoCivil: Object = null; 
			var regimeCasamento: Object = null; 
			var ocupacao: OcupacaoProfissionalVO = registro.ocupacaoProfissional;
			var idNaturalidade: Number = registro.idNaturalidade;
			var nacionalidade: NacionalidadeVO = registro.nacionalidade; 
			
			if(registro.dataEmissaoDocumento != null) {
				dataEmissaoDocumento = registro.dataEmissaoDocumento.data;
			}
			if(registro.dataNascimento != null) {
				dataNascimento = registro.dataNascimento.data;
			}
			if(registro.tipoDocumento != null) {
				tipoDocumento = registro.tipoDocumento.codigo;
			}
			if(registro.grauInstrucao != null) {
				grau = registro.grauInstrucao.codigo;
			}
			if(registro.vinculoEmpregaticio != null) {
				vinculo = registro.vinculoEmpregaticio.codigo;
			}
			if(registro.estadoCivil != null) {
				estadoCivil = registro.estadoCivil.codigo;
			}
			if(registro.regimeCasamento != null) {
				regimeCasamento = registro.regimeCasamento.codigo;
			}
			
			if(registro.conjuge != null){
				this.procurarPessoa.txtCodigo.text = registro.conjuge.pessoa.cpfCnpj;
				this.procurarPessoa.procurarCodigo();
			}
			
			this.txtNumeroDocumento.text = registro.numeroDocumento;
			this.txtOrgaoExpedidor.text = registro.orgaoExpedidorDocumento;
			this.txtDataEmissaoDocumento.selectedDate = dataEmissaoDocumento; 
			this.txtDataNascimento.selectedDate = dataNascimento; 
			this.chkEmancipado.selected = registro.menorEmancipado.valor;
			this.rdbSexo.selectedValue = registro.tipoSexo;
			this.txtNomePai.text = registro.nomePai;
			if(registro.nomePai == textoParentescoNaoInformado) {
				txtNomePai.enabled = false;
				checkNomePaiNaoInformado.selected = true;
			}
			this.txtNomeMae.text = registro.nomeMae;
			if(registro.nomeMae == textoParentescoNaoInformado) {
				txtNomeMae.enabled = false;
				checkNomeMaeNaoInformado.selected = true;
			}
			this.txtQuantidadeDependentes.valor = registro.quantidadeDependentes;
			this.cboUFOrgaoExpedidor.procuraItemPorNome(registro.ufOrgaoExpedidorDocumento, "siglaUF");
			this.cboTipoDocumento.procuraItemPorNome(tipoDocumento, "codigo");
			this.cboGrauInstrucao.procuraItemPorNome(grau, "codigo");	
			this.cboVinculoEmpregaticio.procuraItemPorNome(vinculo, "codigo");	
			atribuirLabelVinculoEmpregaticio();
			this.cboEstadoCivil.procuraItemPorNome(estadoCivil, "codigo");
			this.cboRegimeCasamento.procuraItemPorNome(regimeCasamento, "codigo");

			if(ocupacao != null) {
				this.procurarProfissao.textoCodigo.text = String(ocupacao.idOcupacaoProfissional);
				this.procurarProfissao.pesquisar();
			}else{
				this.procurarProfissao.textoCodigo.text = null;
				this.procurarProfissao.textoDescricao.text = "";
			}

			if(nacionalidade != null) {
				this.procurarNacionalidade.textoCodigo.text = String(nacionalidade.codigo);
				this.procurarNacionalidade.pesquisar();
			}
			
			if(isNacionalidadeBrasileira()){
				if(!isNaN(idNaturalidade)) {
					this.procurarNaturalidade.txtValor.valor = idNaturalidade;
					this.procurarNaturalidade.pesquisar();
				}
			} else {
				this.txtNaturalidade.text = registro.descNaturalidade;
			}
			
			verificarNacionalidade();
			verificarEmancipacao();
			registroBkp = ObjectUtil.copy(registro) as PessoaFisicaVO;
			verificarRegimeCasamento();
			desabilitarCamposData();
			this.validateNow();
		}
		
		public function atualizarCamposRegistro(pessoa:PessoaCompartilhamentoVO): PessoaCompartilhamentoVO {
			
			var registro: PessoaFisicaVO = PessoaFisicaVO(pessoa);
			var nacionalidade: NacionalidadeVO = null;
			var ocupacao: OcupacaoProfissionalVO = null;
			var dataEmissaoDocumento: IDateTime = null;
			var dataNascimento: IDateTime = null;
			var uf: String = null;
			var idNaturalidade: Number = NaN;
			var sexo:String  = CadastroUnicoUtil.SEXO_NAO_INFORMADO;
			
			if(procurarNacionalidade.obterRegistro() != null) {
				nacionalidade = procurarNacionalidade.obterRegistro() as NacionalidadeVO;
			}
			
			if(procurarProfissao.obterRegistro() != null) {
				ocupacao = procurarProfissao.obterRegistro() as OcupacaoProfissionalVO;
			}
			
			if(this.rdbSexo.selectedValue != null) {
				sexo = String(this.rdbSexo.selectedValue);
			}
			
			registro.estadoCivil = cboEstadoCivil.selectedItem as EstadoCivilVO;
			registro.grauInstrucao = cboGrauInstrucao.selectedItem as GrauInstrucaoVO;
        	registro.menorEmancipado = new Booleano(chkEmancipado.selected);
			registro.nomeMae = txtNomeMae.text;
			registro.nomePai = txtNomePai.text;
			registro.numeroDocumento = txtNumeroDocumento.text;
			registro.orgaoExpedidorDocumento = txtOrgaoExpedidor.text;
			registro.regimeCasamento = cboRegimeCasamento.selectedItem as RegimeCasamentoVO;
			registro.tipoDocumento = cboTipoDocumento.selectedItem as TipoDocumentoIdentificacaoVO;
			registro.tipoSexo = sexo;
			registro.vinculoEmpregaticio = cboVinculoEmpregaticio.selectedItem as VinculoEmpregaticioVO;
			registro.nacionalidade = nacionalidade;
			registro.ocupacaoProfissional = ocupacao;
			registro.quantidadeDependentes = txtQuantidadeDependentes.valor;
			registro.conjuge = this.procurarPessoa.registro != null ? ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(PessoaPlataformaVO(this.procurarPessoa.registro)) : null;			
			
			if(txtDataEmissaoDocumento.selectedDate != null) {
				dataEmissaoDocumento = DateTimeBase.getDateTimeEntity(txtDataEmissaoDocumento.selectedDate);
			}	
			if(txtDataNascimento.selectedDate != null) {
				dataNascimento = DateTimeBase.getDateTimeEntity(txtDataNascimento.selectedDate);
			}
			if(!CadastroUnicoUtil.isVazio(procurarNaturalidade.txtValor.text)) {
				idNaturalidade = Number(procurarNaturalidade.txtValor.text);
			}
			if(cboUFOrgaoExpedidor.selectedItem != null) {
				uf = (cboUFOrgaoExpedidor.selectedItem as UFVO).siglaUF;
			}
			
			if(!isNacionalidadeBrasileira()){
				if(txtNaturalidade.text != null && txtNaturalidade.text != ""){
					registro.descNaturalidade = txtNaturalidade.text;
				} else {
					registro.descNaturalidade = null;
				}
			}
			
			registro.idNaturalidade = idNaturalidade;
			registro.ufOrgaoExpedidorDocumento = uf;
			registro.dataEmissaoDocumento = dataEmissaoDocumento;
			registro.dataNascimento = dataNascimento;
			
			//TODO: essa validação será retirada quando o campo união estável da pessoa física for removido.
			if(registro.estadoCivil != null){
				if(EstadoCivilEnum.CASADO.codigo == registro.estadoCivil.codigo || EstadoCivilEnum.UNIAO_ESTAVEL.codigo == registro.estadoCivil.codigo) {
					registro.uniaoEstavel = new Booleano(true);
				}else {
					registro.uniaoEstavel = new Booleano(false)
				}
			}else {
				registro.uniaoEstavel = new Booleano(false)
			}
			
			validarObrigatoriedadeCondicional();
			
			return registro;
		}		
		
		public function retornoCarregarDefinicoes(event:ResultEvent): void {
			isGestorCadastro = event.result.dados.gestorCadastro;
			isIntegracaoSRFLigada = event.result.dados.integracaoReceita;
			cboEstadoCivil.dataProvider = event.result.dados.estadosCivis;
			cboGrauInstrucao.dataProvider = event.result.dados.graus;
			cboRegimeCasamento.dataProvider = event.result.dados.regimesCasamento;
			cboTipoDocumento.dataProvider = event.result.dados.tiposDocumento;
			cboVinculoEmpregaticio.dataProvider = event.result.dados.vinculos;
			cboUFOrgaoExpedidor.dataProvider = event.result.dados.ufs;
		}
		
		private function desabilitarCamposData() :void {
			this.txtDataNascimento.enabled = (isGestorCadastro || !(isIntegracaoSRFLigada));
		}
		
		public function verificarAlteracoes(registroBkp:PessoaCompartilhamentoVO):Boolean {
			var registro: PessoaFisicaVO = PessoaFisicaVO(registroBkp);

			var sexo:String = CadastroUnicoUtil.SEXO_NAO_INFORMADO;
			var codigoProfissao: String = "";
			var codigoNacionalidade: String = "";
			var dataEmissaoDocumento: Date = null;
			var dataNascimento: Date = null;

			if(this.rdbSexo.selectedValue != null) {
				sexo = String(this.rdbSexo.selectedValue);
			}
			
			if(registro.ocupacaoProfissional != null) {
				codigoProfissao = new String(registro.ocupacaoProfissional.idOcupacaoProfissional);
			}			
			if(registro.nacionalidade != null) {
				codigoNacionalidade = new String(registro.nacionalidade.codigo);
			}			
			
			if(registro.dataEmissaoDocumento != null) {
				dataEmissaoDocumento = registro.dataEmissaoDocumento.data;
			}
			if(registro.dataNascimento != null) {
				dataNascimento = registro.dataNascimento.data;
			}
						
			return txtNumeroDocumento.text == registro.numeroDocumento
				&& txtOrgaoExpedidor.text == registro.orgaoExpedidorDocumento
				&& DateUtils.equals(txtDataEmissaoDocumento.selectedDate, dataEmissaoDocumento)
				&& DateUtils.equals(txtDataNascimento.selectedDate, dataNascimento)
				&& chkEmancipado.selected == registro.menorEmancipado.valor
				&& sexo == registro.tipoSexo
				&& txtNomePai.text == registro.nomePai
				&& txtNomeMae.text == registro.nomeMae
				&& txtQuantidadeDependentes.valor == registro.quantidadeDependentes				
				&& compararCombos(registro)
				&& procurarProfissao.textoCodigo.text == codigoProfissao
				&& procurarNacionalidade.textoCodigo.text == codigoNacionalidade
				&& compararNaturalidade(registro);
		}	
		
		private function compararNaturalidade(registro: PessoaFisicaVO): Boolean {
			
			var codigoNaturalidade:String = "";
			if(!isNaN(registro.idNaturalidade)) {
				codigoNaturalidade = new String(registro.idNaturalidade);
			}
			
			var naturalidade:String = "";
			if(registro.descNaturalidade != null) {
				naturalidade = registro.descNaturalidade;
			}

			return procurarNaturalidade.txtValor.text == codigoNaturalidade 
				&& txtNaturalidade.text == naturalidade;
		}
		
		private function compararCombos(registro: PessoaFisicaVO): Boolean {
			var codigoTipoDocumento: Object = null;
			var codigoGrau: Object = null;
			var codigoVinculoEmpregaticio: Object = null;
			var codigoEstadoCivil: Object = null;
			var codigoRegimeCasamento: Object = null;
			if(registro.tipoDocumento != null) {
				codigoTipoDocumento = registro.tipoDocumento.codigo;		
			}
			if(registro.grauInstrucao != null) {
				codigoGrau = registro.grauInstrucao.codigo;		
			}
			if(registro.vinculoEmpregaticio != null) {
				codigoVinculoEmpregaticio = registro.vinculoEmpregaticio.codigo;		
			}
			if(registro.estadoCivil != null) {
				codigoEstadoCivil = registro.estadoCivil.codigo;		
			}
			if(registro.regimeCasamento != null) {
				codigoRegimeCasamento = registro.regimeCasamento.codigo;
			}

			// Itens de combo
			var codigoTipoDocumentoCombo: Object = null; 			
			var codigoGrauCombo: Object = null; 
			var codigoVinculoEmpregaticioCombo: Object = null;
			var codigoEstadoCivilCombo: Object = null;
			var codigoRegimeCasamentoCombo: Object = null;
			var ufCombo:Object = null;
			if(cboTipoDocumento.selectedItem != null) {
				codigoTipoDocumentoCombo = cboTipoDocumento.selectedItem.codigo;
			}	
			if(cboGrauInstrucao.selectedItem != null) {
				codigoGrauCombo = cboGrauInstrucao.selectedItem.codigo;
			}		
			if(cboVinculoEmpregaticio.selectedItem != null) {
				codigoVinculoEmpregaticioCombo = cboVinculoEmpregaticio.selectedItem.codigo;
			}		
			if(cboEstadoCivil.selectedItem != null) {
				codigoEstadoCivilCombo = cboEstadoCivil.selectedItem.codigo;
			}				
			if(cboRegimeCasamento.selectedItem != null) {
				codigoRegimeCasamentoCombo = cboRegimeCasamento.selectedItem.codigo;
			}			
			if(cboUFOrgaoExpedidor.selectedItem != null) {
				ufCombo = cboUFOrgaoExpedidor.selectedItem.siglaUF;
			}			
			
			return ufCombo == registro.ufOrgaoExpedidorDocumento
				&& codigoTipoDocumentoCombo == codigoTipoDocumento
				&& codigoGrauCombo == codigoGrau
				&& codigoVinculoEmpregaticioCombo == codigoVinculoEmpregaticio	
				&& codigoEstadoCivilCombo == codigoEstadoCivil
				&& codigoRegimeCasamentoCombo == codigoRegimeCasamento;	
		}
		
	    //--------------------------------------------------------------------------
	    //  Configuração de destino dos serviços.
	    //--------------------------------------------------------------------------			
		public function configurarDestinosServicos(destino:DestinoVO):void{
			this.destino = destino;
		}			
		
	    //--------------------------------------------------------------------------
	    //  Métodos auxiliares.
	    //--------------------------------------------------------------------------
		private function verificarEmancipacao(evt: Event=null): void {
			var dataNascimento:IDateTime = DateTimeBase.getDateTime(txtDataNascimento.selectedDate);
			if (isMenorIdade(dataNascimento)){
				chkEmancipado.enabled = true;
				chkEmancipado.focusEnabled;
			}else{
				chkEmancipado.enabled = false;
				chkEmancipado.selected = false;	
			}
		}
		
		private function verificarNacionalidade(evt: Event=null): void {
			
			var isBrasileiro: Boolean = isNacionalidadeBrasileira();
			var isNacionalidadeVazia:Boolean = (procurarNacionalidade.textoCodigo.text == "");
			
			
			if (isNacionalidadeVazia) { 
				txtNaturalidade.text = "";
				procurarNaturalidade.limpar();				
			} else if(isBrasileiro) {
				txtNaturalidade.text = "";
			} else {
				procurarNaturalidade.limpar();				
			}
			
			txtNaturalidade.enabled = !isBrasileiro && !isNacionalidadeVazia;			
			procurarNaturalidade.enabled = isBrasileiro;
		}
		
		private function isNacionalidadeBrasileira():Boolean {
			return procurarNacionalidade.textoCodigo.valor == CadastroUnicoUtil.NACIONALIDADE_BRASILEIRA;
		}
		
		private function isMenorIdade(dataNascimento:IDateTime):Boolean{
			
			var dtAtual:IDateTime;
			var dia:int;
			var idade:Number = 0;
			
			if (dataNascimento != null ) {
				dtAtual = DateTimeBase.getDateTime(new Date());
				idade = ((dtAtual.data.getTime() - dataNascimento.data.getTime())+1)/milisegundosPorAno;
			}
			
			return idade < 18;
		}
		
		private function isCasado(): Boolean {
			if(cboEstadoCivil.selectedItem != null){
				return cboEstadoCivil.selectedItem.codigo == EstadoCivilEnum.CASADO.codigo
			}else{
				return false;
			}
		}
		
		private function isUniaoEstavel(): Boolean {
			if(cboEstadoCivil.selectedItem != null){
				return cboEstadoCivil.selectedItem.codigo == EstadoCivilEnum.UNIAO_ESTAVEL.codigo
			}else{
				return false;
			}
		}
		
		private function validarConjugeInvalido(event:Event=null): void {
			var cpf: String = this.procurarPessoa.txtCodigo.text;
			
			if (cpf.length > 0) {
				var valido: Boolean = CPF.validarCPF(cpf);
				
				if (!valido) {
					Alerta.show("CPF do Cônjuge/Companheiro(a) Inválido.", "ATENÇÃO", Alerta.ALERTA_INFORMACAO);
					procurarPessoa.exibeMensagemErroRetorno = true;
					procurarPessoa.limpar();
				}
			}
		}
	
		private function verificarRegimeCasamento(event:Event=null):void 
		{	
			var ehCasado:Boolean = isCasado();
			var ehCasadoOuUniao:Boolean = ehCasado || isUniaoEstavel();
			cboRegimeCasamento.enabled = ehCasadoOuUniao;
			cboRegimeCasamento.validarObrigatorio = ehCasadoOuUniao;
			//habilita ou não preenchimento cônjuge.
			//procurarPessoa.enabled = ehCasadoOuUniao;
			procurarPessoa.validarObrigatorio = ehCasadoOuUniao;
			procurarPessoa.enabled = ehCasadoOuUniao;
			 
			if(!ehCasadoOuUniao) {
				cboRegimeCasamento.selectedIndex = 0;
				procurarPessoa.limpar();
			}else if((registroBkp.conjuge != null) && (registroBkp.estadoCivil != null)){
				procurarPessoa.txtCodigo.text = registroBkp.conjuge.pessoa.cpfCnpj;
				procurarPessoa.procurarCodigo();
				procurarPessoa.enabled = !ehCasadoOuUniao;
				if(registroBkp.regimeCasamento != null) {
					this.cboRegimeCasamento.procuraItemPorNome(registroBkp.regimeCasamento.codigo, "codigo");
				}
				
			}
		}
		
		private function atribuirLabelVinculoEmpregaticio(event:Event=null):void 
		{	
			if(cboVinculoEmpregaticio.selectedItem != null) {
				lblValorMinimoRenda.text = "Renda mínima exigida para categoria R$ " + FormataNumero.formata(Number(cboVinculoEmpregaticio.selectedItem.valorRendaMinimaObrigatoria));
			} else {
				lblValorMinimoRenda.text = "";
			}
		}
		
	    //--------------------------------------------------------------------------
	    //  Validações obrigatórias dependentes.
	    //--------------------------------------------------------------------------	
	    private function validarObrigatoriedadeCondicional(): void {
	    	
	    	validarObrigatoriedadeIdentificacao();
	    	txtDataNascimento.validarObrigatorio = chkEmancipado.selected;
	    }
	    	
		private function validarObrigatoriedadeIdentificacao(): void {
			
			var algumPreenchido: Boolean = cboTipoDocumento.selectedIndex > 0 
					|| cboUFOrgaoExpedidor.selectedIndex > 0
					|| !CadastroUnicoUtil.isVazio(txtNumeroDocumento.text)
					|| !CadastroUnicoUtil.isVazio(txtOrgaoExpedidor.text)
					|| txtDataEmissaoDocumento.selectedDate != null;
					
			cboTipoDocumento.validarObrigatorio = algumPreenchido;
			cboUFOrgaoExpedidor.validarObrigatorio = algumPreenchido;
			txtNumeroDocumento.validarObrigatorio = algumPreenchido;
			txtOrgaoExpedidor.validarObrigatorio = algumPreenchido;
			txtDataEmissaoDocumento.validarObrigatorio = algumPreenchido;
			
			/*if(cboEstadoCivil.selectedIndex > 0){
				cboRegimeCasamento.validarObrigatorio = (cboEstadoCivil.selectedItem.codigo 
					== EstadoCivilEnum.CASADO.codigo);
			}*/
		}		
				
	}
}