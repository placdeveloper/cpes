package br.com.sicoob.capes.cadastrarInformacaoProfissional
{
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.componentes.procurarCliente.procurarPessoa;
	import br.com.bancoob.sisbr.eventos.EventAssistenteAtendimento;
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.ConselhoRegionalVO;
	import br.com.sicoob.capes.comum.vo.InformacaoProfissionalVO;
	import br.com.sicoob.capes.comum.vo.TipoAfastamentoVO;
	import br.com.sicoob.capes.comum.vo.TipoFuncionarioVO;
	import br.com.sicoob.capes.comum.vo.VigenteVO;
	import br.com.sicoob.capes.comum.vo.entidades.UFVO;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	import br.com.sicoob.capes.utils.plataformaatendimento.IEdicaoPlataformaAtendimentoCAPES;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCAPESBase;
	
	import flash.events.Event;
	
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.ObjectUtil;

	public class frmEditarInfoProfissional extends frmEditarInfoProfissionalView 
		implements IEdicaoPlataformaAtendimentoCAPES {	
		
		/**
		 *  Operações 
		 */
		static private const OPERACAO_OBTER_DEFINICOES: String = "obterDefinicoes";
		static private const OPERACAO_INCLUIR_DADOS: String = "incluirDados";
		static private const OPERACAO_ALTERAR_DADOS: String = "alterarDados";
		static private const OPERACAO_EXCLUIR_DADOS: String = "excluirDados";
		static private const OPERACAO_OBTER_DADOS: String = "obterDados";
		static private const OPERACAO_OBTER_CONTATOS: String = "obterContatos";
		
		private var registro:InformacaoProfissionalVO = new InformacaoProfissionalVO();
		private var registroBkp:InformacaoProfissionalVO = null;			
		
		public var servicoConsulta:ServicoJava;
		public var servicoSalvar:ServicoJava;
		public var servicoExcluir:ServicoJava;
		public var servicoDefinicao:ServicoJava;
		public var servicoTelefone:ServicoJava;
		
		private var _produtosBancoob : Boolean = false;
		
		public function frmEditarInfoProfissional()
		{
			super();

			servicoConsulta = ServicoJavaUtil.getServico(InformacaoProfissionalSelecao.CLASSE_SERVICO);
			servicoConsulta.addEventListener(ResultEvent.RESULT, retornoCarregarRegistro);
			
			servicoDefinicao = ServicoJavaUtil.getServico(InformacaoProfissionalSelecao.CLASSE_SERVICO);
			servicoDefinicao.addEventListener(ResultEvent.RESULT, retornoObterDefinicoes);
			
			servicoSalvar = ServicoJavaUtil.getServico(InformacaoProfissionalSelecao.CLASSE_SERVICO);
			servicoSalvar.addEventListener(ResultEvent.RESULT, retornoSalvar);		
			
			servicoExcluir = ServicoJavaUtil.getServico(InformacaoProfissionalSelecao.CLASSE_SERVICO);
			servicoExcluir.addEventListener(ResultEvent.RESULT, retornoExcluir);			
			servicoExcluir.addEventListener(FaultEvent.FAULT, retornoExcluirErro);
			
			servicoTelefone = ServicoJavaUtil.getServico(InformacaoProfissionalSelecao.CLASSE_SERVICO);
			servicoTelefone.addEventListener(ResultEvent.RESULT, retornoCarregarContatos);
			
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);			
		}
		
		private function init(evt:FlexEvent=null):void {
			empregador.addEventListener(procurarPessoa.ITEM_SELECIONADO, carregarContatos);
			this.empregador.possuiRelacionamentoBancoob = produtosBancoob;
			desabilitarDataCadastro();
		}		
		
		public function set produtosBancoob(value:Boolean):void {
			_produtosBancoob = value;
		}
		public function get produtosBancoob():Boolean {
			return _produtosBancoob;
		}		
		
		private function desabilitarDataCadastro() :void {
			this.txtDataCadastro.enabled = false;
		}		
		
		public function configurarDestinosServicos(destinoVO:DestinoVO):void{
			servicoConsulta.configurarDestino(destinoVO);
			servicoSalvar.configurarDestino(destinoVO);
			servicoExcluir.configurarDestino(destinoVO);
			servicoDefinicao.configurarDestino(destinoVO);
			servicoTelefone.configurarDestino(destinoVO);
		}			
			
		public function gravarRegistro():void {
			atualizarCamposRegistro();
			executarSeValido(gravarDados);
		}
		
		public function atualizarCamposRegistro():void {
			
			if (_novo) {
				registro = new InformacaoProfissionalVO;
			}
			
			var conselho: Number = NaN;
			var tipoAfastamento: Number = NaN;
			var tipoFuncionario: Number = NaN;
			var uf: String = null;
			var dataAdmissao: IDateTime = null;
			var dataDesligamento: IDateTime = null;
		
			if(cboConselho.selectedItem != null) {
				conselho = (cboConselho.selectedItem as ConselhoRegionalVO).codigo;
			}			
			if(cboTipoAfastamento.selectedItem != null) {
				tipoAfastamento = (cboTipoAfastamento.selectedItem as TipoAfastamentoVO).codigo;
			}			
			if(cboTipoFuncionario.selectedItem != null) {
				tipoFuncionario = (cboTipoFuncionario.selectedItem as TipoFuncionarioVO).codigo;
			}			
			if(cboUF.selectedItem != null) {
				uf = (cboUF.selectedItem as UFVO).siglaUF;
			}			
			if(txtDataAdmissao.selectedDate != null) {
				dataAdmissao = DateTimeBase.getDateTime(txtDataAdmissao.selectedDate);
			}	
			if(txtDataDesligamento.selectedDate != null) {
				dataDesligamento = DateTimeBase.getDateTime(txtDataDesligamento.selectedDate);
			}
			
			if(empregador.registro != null) {
				var pessoaEmpregador:PessoaPlataformaVO = (empregador.registro as PessoaPlataformaVO);
				registro.idPessoaEmpregador = pessoaEmpregador.idPessoa;
			}			
			
			registro.idPessoa = TelaPlataformaAtendimentoCAPESBase.getPessoaPlataforma().idPessoa;
			registro.cargo = txtCargo.text;
			registro.codigoConselhoRegional = conselho;
			registro.codigoTipoAfastamento = tipoAfastamento;
			registro.codigoTipoFuncionario = tipoFuncionario;
			registro.codSituacao = rdbSituacao.selectedValue as Number;
			registro.dataAdmissao = dataAdmissao;
			registro.dataDesligamento = dataDesligamento;
			registro.diaMesFerias = txtPeriodoFerias.text;
			registro.numeroInscricaoConselho = txtNumeroInscricao.text;
			registro.numMatricula = txtMatricula.text;
			registro.ufConselho = uf;
		}	
		
		public function restaurarRegistro():void {
			registro = (ObjectUtil.copy(registroBkp) as InformacaoProfissionalVO);
		}
		
		public function verificarAlteracoes():Boolean {
			
//			var cpfCnpjEmpregador: String = "";
//			var descFonteRenda: String = "";
//			
//			if(registroBkp.pessoaEmpregador != null
//				&& registroBkp.pessoaEmpregador.pessoa != null 
//				&& registroBkp.pessoaEmpregador.pessoa.cpfCnpj != null){
//				cpfCnpjEmpregador = registroBkp.pessoaEmpregador.pessoa.cpfCnpj;
//			} 
//			
//			if(registroBkp.descFonteRenda != null){
//				descFonteRenda = registroBkp.descFonteRenda;
//			}
//			
//			return cmbTipoRenda.selectedItem.codigo == registroBkp.tipoFonteRenda.codigo
//				&& rdbFixaVariavel.selectedValue == registroBkp.bolRendaFixa.valor
//				&& empregador.txtCodigo.text == cpfCnpjEmpregador
//				&& txtReceitaBrutaMensal.valor == registroBkp.valorReceitaBrutaMensal
//				&& txtDescRenda.text == descFonteRenda;
			
			return true;
		}
		
		private function gravarDados():void {
			
			MostraCursor.setBusyCursor("Gravando Registro!", Application.application);
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO(); 
			dto.dados.informacao = registro;
			dto.dados.produtosBancoob = this.produtosBancoob;
			
			if (_novo) {
				servicoSalvar.getOperation(OPERACAO_INCLUIR_DADOS).send(dto);
			} else {
				servicoSalvar.getOperation(OPERACAO_ALTERAR_DADOS).send(dto);
			}	
		}		
		
		//--------------------------------------------------------------------------
		// ICadastro
		//--------------------------------------------------------------------------
		public function carregarDefinicoes(obj:Object=null):void {
			
			MostraCursor.setBusyCursor("Carregando Definições!", Application.application);
			servicoDefinicao.getOperation(OPERACAO_OBTER_DEFINICOES).send();
		}		

		public function novoRegistro():void {
			_novo = true;
			registro = new InformacaoProfissionalVO();
			registroBkp = new InformacaoProfissionalVO();
			
			habilitaCamposExtras(_novo);
			preencherCampos();
		}
		
		private function habilitaCamposExtras(bol: Boolean):void{
			rotuloDataHoraInicio.visible = rotuloDataHoraInicio.includeInLayout = !bol;
			rotuloUsuarioAlteracao.visible = rotuloUsuarioAlteracao.includeInLayout = !bol;
			txtDataCadastro.visible = txtDataCadastro.includeInLayout = !bol;
			usuarioAlteracao.visible = usuarioAlteracao.includeInLayout = !bol;
		}
		
		public function excluirRegistro(obj:Object):void {		
			
			MostraCursor.setBusyCursor("Excluindo Registros!", Application.application, 
				MostraCursor.CURSOR_EXCLUIR);
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.informacao = obj; 
			
			servicoExcluir.getOperation(OPERACAO_EXCLUIR_DADOS).send(dto);	
		}
		
		public function carregarRegistro(obj:Object):void {
			
			if(obj != null){
				MostraCursor.setBusyCursor("Carregando Registro ...", 
					Application.application, MostraCursor.CURSOR_PESQUISAR);
				
				var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
				dto.dados.informacao = obj;
				servicoConsulta.getOperation(OPERACAO_OBTER_DADOS).send(dto);
			}
		}
		
		private function limpaCamposEmpregador():void{
			empregador.limpar();
			gridTelefone.dataProvider = null;
		}
		
		public function preencherCampos():void {
			
			var dataAdmissao: Date = null;
			var dataDesligamento: Date = null;
			
			if(registroBkp.dataAdmissao != null) {
				dataAdmissao = registroBkp.dataAdmissao.data;					
			}				
			if(registroBkp.dataDesligamento != null) {
				dataDesligamento = registroBkp.dataDesligamento.data;					
			}
			
			txtDataCadastro.selectedDate = registroBkp.dataHoraInicio != null ? registroBkp.dataHoraInicio.data : null;
			usuarioAlteracao.text = registroBkp.idUsuarioAprovacao;
			txtDataCadastro.enabled = false;			
			if(registroBkp.cnpjPessoaEmpregador != null) {
				empregador.txtCodigo.text = registroBkp.cnpjPessoaEmpregador;
				empregador.procurarCodigo();
				empregador.enabled = false;
			} else {
				limpaCamposEmpregador();
			}
			
			this.txtMatricula.text = registroBkp.numMatricula;
			this.txtCargo.text = registroBkp.cargo;
			this.cboTipoFuncionario.procuraItemPorNome(registroBkp.codigoTipoFuncionario, "codigo");	
			this.txtDataAdmissao.selectedDate = dataAdmissao;
			if(!_novo){
				this.rdbSituacao.selectedValue = registroBkp.codSituacao;
			}
			this.txtPeriodoFerias.text = registroBkp.diaMesFerias;
			this.cboTipoAfastamento.procuraItemPorNome(registroBkp.codigoTipoAfastamento, "codigo");
			this.txtDataDesligamento.selectedDate = dataDesligamento;
			this.cboConselho.procuraItemPorNome(registroBkp.codigoConselhoRegional, "codigo");
			this.cboUF.procuraItemPorNome(registroBkp.ufConselho, "siglaUF");
			this.txtNumeroInscricao.text = registroBkp.numeroInscricaoConselho;
			
			desabilitarDataCadastro();
		}		
		
		//--------------------------------------------------------------------------
		// Métodos de retorno
		//--------------------------------------------------------------------------
		private function carregarContatos(evt:Event=null):void {
			
			MostraCursor.setBusyCursor("Carregando Contatos...", Application.application);
			
			var pessoa:PessoaPlataformaVO = (empregador.registro as PessoaPlataformaVO);
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.idPessoa = pessoa.idPessoa;
			dto.dados.idInstituicao = pessoa.idInstituicao;
			
			servicoTelefone.getOperation(OPERACAO_OBTER_CONTATOS).send(dto);
		}	
		
		private function retornoObterDefinicoes(evt:ResultEvent):void {			
			
			MostraCursor.removeBusyCursor();
			
			cboConselho.dataProvider = evt.result.dados.conselhos;
			cboTipoAfastamento.dataProvider = evt.result.dados.tiposAfastamento;
			cboTipoFuncionario.dataProvider = evt.result.dados.tiposFuncionario;
		}
		
		private function retornoCarregarRegistro(evt:ResultEvent):void {
			registro = evt.result.dados["informacao"];
			registroBkp = ObjectUtil.copy(registro) as InformacaoProfissionalVO;
			preencherCampos();
			_novo = false;
			habilitaCamposExtras(_novo);
			this.dispatchEvent(new Event(REGISTRO_CARREGADO));
		}
		
		private function retornoSalvar(evt:ResultEvent):void {
			registro = evt.result.dados["informacao"];
			this.dispatchEvent(new EventAssistenteAtendimento(EventAssistenteAtendimento.EVENTO_CONFIRMAR_MUDANCA_TELA));
			this.dispatchEvent(new Event(Modulo.REGISTRO_GRAVADO));			
		}
		
		private function retornoExcluir(evt:ResultEvent):void {
			registro = evt.result.dados["informacao"];
			this.dispatchEvent(new Event(REGISTRO_EXCLUIDO));  
		}
		
		public function retornoExcluirErro(evt:FaultEvent):void {
			MostraCursor.removeBusyCursor();
		}			
		
		private function retornoCarregarContatos(evt:ResultEvent):void {
			
			MostraCursor.removeBusyCursor();
			gridTelefone.dataProvider = evt.result.dados["telefones"];
			this.dispatchEvent(new Event(Modulo.LISTA_CARREGADA));							
		}			
		
		[Deprecated]
		public function getEntidadeVigente():VigenteVO {
			return null;
		}
		
		public function isRegistroBloqueadoAlteracao():Boolean {
			return false;
		}
	}
}