package br.com.sicoob.capes.alterarPessoaInstituicao{

	import flash.display.DisplayObject;
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	import mx.collections.ListCollectionView;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.ParametrosAssistenteAtendimento;
	import br.com.bancoob.sisbr.componentes.PlataformaAtendimento.ITelaBasePlataformaAtendimento;
	import br.com.bancoob.sisbr.componentes.plataformas.BarraInferiorPlataformas;
	import br.com.bancoob.tipos.Booleano;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.TributacaoVO;
	import br.com.sicoob.capes.comum.vo.UnidadeInstituicaoVO;
	import br.com.sicoob.capes.comum.vo.VigenteVO;
	import br.com.sicoob.capes.comum.vo.entidades.FuncionarioVO;
	import br.com.sicoob.capes.comum.vo.entidades.GrupoEconomicoVO;
	import br.com.sicoob.capes.comum.vo.entidades.NucleoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PerfilTarifarioVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaInstituicaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaVO;
	import br.com.sicoob.capes.corporativo.componentes.validacaoCadastral.ValidacaoCadastral;
	import br.com.sicoob.capes.utils.ConversaoPessoaUtil;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCustomizadoCAPES;
	
	public class frmEditarPessoaInstituicao extends frmEditarPessoaInstituicaoView implements ITelaBasePlataformaAtendimento {

		/**
		 *  Operações 
		 */
		private static const OPERACAO_OBTER_DEFINICOES: String = "obterDefinicoes";
		private static const OPERACAO_INCLUIR_DADOS: String = "incluirDados";
		private static const OPERACAO_ALTERAR_DADOS: String = "alterarDados";
		private static const OPERACAO_EXCLUIR: String = "excluirDados";
		private static const OPERACAO_OBTER: String = "obterDados";
		
		private var pessoaInstituicaoVOBkp:PessoaInstituicaoVO = new PessoaInstituicaoVO();

		[Bindable]
		private var pessoaInstituicaoVO:PessoaInstituicaoVO = new PessoaInstituicaoVO();

		//Informações da Pessoa
		private var pessoa:PessoaVO = null;

		/**
		 *	Serviços 
		 */
		private var servicoConsulta:ServicoJava;
		private var servicoIncluir:ServicoJava;
		private var servicoAlterar:ServicoJava;
		private var servicoValidarCadastro:ServicoJava;
		
		private var _produtosBancoob : Boolean = false;
		private var _habilitaNivelRisco: Boolean = false;

		static private const CLASSE_SERVICO: String = "br.com.sicoob.capes.cadastro.fachada.PessoaInstituicaoFachada";

		//--------------------------------------------------------------------------
	    //  Propriedades
	    //--------------------------------------------------------------------------

	    /**
	     *  Construtor.
	     */
		public function frmEditarPessoaInstituicao(){
			super();

			servicoConsulta = ServicoJavaUtil.getServico(CLASSE_SERVICO, "Obtendo dados...", ResultEvent.RESULT, retornoCarregarRegistro);
			servicoIncluir = ServicoJavaUtil.getServico(CLASSE_SERVICO, "Incluindo dados...", ResultEvent.RESULT, retornoSalvar);
			servicoAlterar = ServicoJavaUtil.getServico(CLASSE_SERVICO, "Alterando dados...", ResultEvent.RESULT, retornoSalvar);
			servicoValidarCadastro = ServicoJavaUtil.getServico(CLASSE_SERVICO, "Validando o cadastro da pessoa...", ResultEvent.RESULT, retornoValidarSucesso);
			
			servicoValidarCadastro.addEventListener(FaultEvent.FAULT, retornoValidarErro);
			servicoValidarCadastro.tratamentoErroPadrao = false;
			
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		//--------------------------------------------------------------------------
	    //  Métodos
	    //--------------------------------------------------------------------------
		private function init(evt:FlexEvent):void{
			this.responsavel.labelFunction = labelFunctionFuncionario;
		}
		
		public function configurarDestino(destino:DestinoVO):void {
			this.servicoAlterar.configurarDestino(destino);
			this.servicoConsulta.configurarDestino(destino);
			this.servicoIncluir.configurarDestino(destino);
			this.servicoValidarCadastro.configurarDestino(destino);
		}
		
		public function carregarRegistro(pessoaInstituicao:Object=null):void{
			MostraCursor.setBusyCursor("Obtendo dados...", Application.application, MostraCursor.CURSOR_PROGRESSO);

			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();

			var pessoaCompartilhamento:PessoaCompartilhamentoVO = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(TelaPlataformaAtendimentoCustomizadoCAPES.getPessoaPlataforma());
			
			dto.dados.pessoaCompartilhamento = pessoaCompartilhamento;
			dto.dados.pessoa = pessoaCompartilhamento.pessoa;
			dto.dados.produtosBancoob = this.produtosBancoob;
			servicoConsulta.getOperation(OPERACAO_OBTER).send(dto);
		}

		private function retornoCarregarRegistro(evt:ResultEvent):void {
			nucleo.dataProvider = evt.result.dados.nucleos;
			unidadeIntitucional.dataProvider = evt.result.dados.unidades; 
			responsavel.dataProvider = evt.result.dados.funcionarios;
			perfilTarifario.dataProvider = evt.result.dados.perfisTarifarios;
			nivelRisco.dataProvider = obterNiveisRiscos();
			_habilitaNivelRisco = evt.result.dados.habilitaNivelRisco;

			pessoaInstituicaoVO = evt.result.dados.pessoaInstituicao;
			pessoaInstituicaoVOBkp = copiarPessoaInstituicao(pessoaInstituicaoVO);
			
			categoriaCadastro.text = evt.result.dados.categoriasCadastro;  

			_novo = (pessoaInstituicaoVO == null);
			this.moduloRisco.visible = this.moduloRisco.includeInLayout = _novo && _habilitaNivelRisco;
			this.nivelRisco.validarObrigatorio = _novo && _habilitaNivelRisco;
			this.motivo.validarObrigatorio = _novo && _habilitaNivelRisco;
			if (_novo) {
				novoRegistro();
			} else {
				preencherCampos();
			}
			
			preencherCamposAdicionais(evt);
			habilitarCamposAlteracao();
			
			this.dispatchEvent(new Event(REGISTRO_CARREGADO));
			
			MostraCursor.removeBusyCursor();
		}
		
		private function habilitarCamposAlteracao():void {
			rotuloDataHoraInicio.visible = rotuloDataHoraInicio.includeInLayout = !_novo;
			rotuloUsuarioAlteracao.visible = rotuloUsuarioAlteracao.includeInLayout = !_novo;
			dataHoraInicio.visible = dataHoraInicio.includeInLayout = !_novo;
			usuarioAlteracao.visible = usuarioAlteracao.includeInLayout = !_novo;
			if(!_novo){
				dataCadastro.visible = dataCadastro.includeInLayout = !_novo;
			}else{
				dataCadastro.visible = _novo;
			}
			
		}
	
		private function copiarPessoaInstituicao(origem : PessoaInstituicaoVO = null) : PessoaInstituicaoVO {
			var pi : PessoaInstituicaoVO = new PessoaInstituicaoVO();
			if (origem != null) {
				pi.dataEnquadramentoRisco = origem.dataEnquadramentoRisco;
				pi.dataCadastro = origem.dataCadastro;
				pi.dataHoraInicio = origem.dataHoraInicio;
				pi.emiteAvisoLancamento = origem.emiteAvisoLancamento;
				pi.funcionario = origem.funcionario;
				pi.gravarHistorico = origem.gravarHistorico;
				pi.idUsuarioAprovacao = origem.idUsuarioAprovacao;
				pi.gruposEconomicos = origem.gruposEconomicos;
				pi.idInstituicao = origem.idInstituicao;
				pi.idPessoaInstituicao = origem.idPessoaInstituicao;
				pi.idUnidadeInst = origem.idUnidadeInst;
				pi.motivoRisco = origem.motivoRisco;
				pi.nivelRisco = origem.nivelRisco;
				pi.nucleo = origem.nucleo;
		        pi.parecerGerencia = origem.parecerGerencia;
		        pi.perfilTarifario = origem.perfilTarifario;
		        pi.pessoa = origem.pessoa;
				pi.trocarGrupo = origem.trocarGrupo;
			}
			return pi;
		}
	
		private function labelFunctionFuncionario(row:Object):String{
			var retorno:String = "";
			
			if(row != null){
				var obj:FuncionarioVO = row as FuncionarioVO;
				if(obj != null && obj.pessoa != null && obj.pessoa.pessoaCompartilhamento != null && obj.pessoa.pessoaCompartilhamento.nomePessoa != null){
					retorno = obj.pessoa.pessoaCompartilhamento.nomePessoa;
				}
			}
			return retorno;
		}

		public function preencherCampos():void {
			this.dataCadastro.selectedDate = pessoaInstituicaoVO.dataCadastro != null ? pessoaInstituicaoVO.dataCadastro.data : null;
			this.dataHoraInicio.selectedDate = pessoaInstituicaoVO.dataHoraInicio != null ? pessoaInstituicaoVO.dataHoraInicio.data : null;
			this.usuarioAlteracao.text = pessoaInstituicaoVO.idUsuarioAprovacao;
			this.unidadeIntitucional.procuraItemPorNome(pessoaInstituicaoVO.idUnidadeInst, "id");
			
			this.nucleo.selectedIndex = 0;
			if (pessoaInstituicaoVO.nucleo != null) {
				this.nucleo.procuraItemPorNome(pessoaInstituicaoVO.nucleo.idNucleo,"idNucleo");
			}
			
			this.responsavel.selectedIndex = 0;
			if (pessoaInstituicaoVO.funcionario != null) {
				this.responsavel.procuraItemPorNome(pessoaInstituicaoVO.funcionario.idFuncionario, "idFuncionario");
			}
			
			this.perfilTarifario.selectedIndex = 0;
			if (pessoaInstituicaoVO.perfilTarifario != null) {
				this.perfilTarifario.procuraItemPorNome(pessoaInstituicaoVO.perfilTarifario.chaveComposta, "chaveComposta");
			}

			this.emiteAviso.selected = false;
			if (pessoaInstituicaoVO.emiteAvisoLancamento != null) {
				this.emiteAviso.selected = pessoaInstituicaoVO.emiteAvisoLancamento.valor;
			}
			this.parecer.text = pessoaInstituicaoVO.parecerGerencia;
		}
		
    	public function obterNiveisRiscos():ListCollectionView {
    		var optionA:Object = new Object();
    		var optionAA:Object = new Object();
			var optionB:Object = new Object();
			var optionC:Object = new Object();
			var optionD:Object = new Object();
			var optionE:Object = new Object();
			var optionF:Object = new Object();
			var optionG:Object = new Object();
			var optionH:Object = new Object();
		
    		optionA.codigo = "A";
    		optionA.descricao = "A";
    		
    		optionAA.codigo = "AA";
    		optionAA.descricao = "AA";

    		optionB.codigo = "B";
    		optionB.descricao = "B";

    		optionC.codigo = "C";
    		optionC.descricao = "C";

    		optionD.codigo = "D";
    		optionD.descricao = "D";

    		optionE.codigo = "E";
    		optionE.descricao = "E";

    		optionF.codigo = "F";
    		optionF.descricao = "F";

    		optionG.codigo = "G";
    		optionG.descricao = "G";

    		optionH.codigo = "H";
    		optionH.descricao = "H";
    		
    		var niveis:ArrayCollection = new ArrayCollection();
    		niveis.addItem(optionA);
    		niveis.addItem(optionAA);
    		niveis.addItem(optionB);
    		niveis.addItem(optionC);
    		niveis.addItem(optionD);
    		niveis.addItem(optionE);
    		niveis.addItem(optionF);
    		niveis.addItem(optionG);
    		niveis.addItem(optionH);
    		
    		return niveis;
    	}
		
		public function preencherCamposAdicionais(evt:ResultEvent):void {
			var tributacao:TributacaoVO = evt.result.dados.tributacao;
			preencherTributacao(tributacao);
			preencherGruposEconomicos(evt.result.dados.gruposEconomicos as ListCollectionView);
		}
		
		public function preencherTributacao(tributacao:TributacaoVO):void {
			if(tributacao != null){
				rotuloIRRF.text = tributacao.cobrarIR.valor ? "Sim" : "Não";
				rotuloIOF.text = tributacao.cobrarIOF.valor ? "Sim" : "Não";
			}
		}
		
		public function preencherGruposEconomicos(listaGrupos:ListCollectionView): void {
			var texto:String = "";
			for each(var grupo:Object in listaGrupos) {
				if(grupo is GrupoEconomicoVO) {
					texto = grupo.descricao + ", ";
				} else {
					texto = grupo.nome + ", ";
				}
			}
			rotuloGrupoEconomico.text = texto.substring(0, texto.lastIndexOf(", "));
		}
		
		public function carregarDefinicoes(obj:Object=null):void {
			servicoConsulta.getOperation(OPERACAO_OBTER_DEFINICOES).send();
		}
		
		private function retornoSalvar(evt:ResultEvent):void {
			pessoaInstituicaoVO = evt.result.dados.pessoaInstituicao;
			_novo = false;
			pessoaInstituicaoVOBkp = copiarPessoaInstituicao(pessoaInstituicaoVO);
			
			this.moduloRisco.visible = false;
			this.moduloRisco.height = 0;
			this.nivelRisco.validarObrigatorio = false;
			
			var tributacao:TributacaoVO = evt.result.dados.tributacao;
			preencherTributacao(tributacao);
			
			MostraCursor.removeBusyCursor();
		}
		
		private function exibirRestricoes(evento:Event = null):void {
			var janelaValidacaoCadastral:Janela = new Janela();
			var validacaoCadastral:ValidacaoCadastral = new ValidacaoCadastral('R', false);
			
			janelaValidacaoCadastral.title = "VALIDAÇÃO CADASTRAL";
			janelaValidacaoCadastral.icone = "br/com/bancoob/imagens/icos/apply.png";
			janelaValidacaoCadastral.removeAllChildren();
			janelaValidacaoCadastral.addChild(DisplayObject(validacaoCadastral));
			this.parentDocument.addEventListener(BarraInferiorPlataformas.FECHAR_PLATAFORMA, validacaoCadastral.fechar);
			this.parentDocument.addEventListener(BarraInferiorPlataformas.VOLTAR_SISBR, validacaoCadastral.fechar);
			janelaValidacaoCadastral.abrir(DisplayObject(Application.application), false, true);
		}

		public function novoRegistro():void {
			pessoaInstituicaoVO = new PessoaInstituicaoVO();
			pessoaInstituicaoVOBkp = new PessoaInstituicaoVO();
			pessoaInstituicaoVOBkp.dataCadastro = DateTimeBase.getDateTimeEntity(new Date());
			pessoaInstituicaoVOBkp.dataHoraInicio = DateTimeBase.getDateTimeEntity(new Date());
			pessoaInstituicaoVOBkp.nucleo = new NucleoVO();
			pessoaInstituicaoVOBkp.funcionario = new FuncionarioVO();
			pessoaInstituicaoVOBkp.perfilTarifario = new PerfilTarifarioVO();
			pessoaInstituicaoVOBkp.emiteAvisoLancamento = new Booleano(false);
			_novo = true;
		}

		public function gravarRegistro():void {
			if(!validarCampos()) {
				return;
			}

			executarSeValido(gravarDados);
		}

		public function atualizarCamposRegistro():void{
			pessoaInstituicaoVO.nucleo = this.nucleo.selectedItem as NucleoVO;
			
			if (_novo) {
				pessoaInstituicaoVO.idInstituicao = UnidadeInstituicaoVO(this.unidadeIntitucional.selectedItem).idInstituicao;
				pessoaInstituicaoVO.dataCadastro = DateTimeBase.getDateTimeEntity(this.dataCadastro.selectedDate);
			}
			
			pessoaInstituicaoVO.dataHoraInicio = DateTimeBase.getDateTimeEntity(this.dataHoraInicio.selectedDate);
			pessoaInstituicaoVO.idUnidadeInst = UnidadeInstituicaoVO(this.unidadeIntitucional.selectedItem).id;
			pessoaInstituicaoVO.funcionario = this.responsavel.selectedItem as FuncionarioVO;
			pessoaInstituicaoVO.perfilTarifario = this.perfilTarifario.selectedItem as PerfilTarifarioVO;
			pessoaInstituicaoVO.emiteAvisoLancamento = new Booleano(this.emiteAviso.selected);
			pessoaInstituicaoVO.pessoa = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(TelaPlataformaAtendimentoCustomizadoCAPES.getPessoaPlataforma()).pessoa;
			pessoaInstituicaoVO.parecerGerencia = this.parecer.text;
			
			if (this.moduloRisco.visible) {
				pessoaInstituicaoVO.nivelRisco = this.nivelRisco.selectedItem.codigo;
				pessoaInstituicaoVO.motivoRisco = this.motivo.text;
				if (this.dataEnquadramento.selectedDate != null) {
					pessoaInstituicaoVO.dataEnquadramentoRisco = DateTimeBase.getDateTimeEntity(this.dataEnquadramento.selectedDate);
				}
			}
		}

		private function gravarDados():void {
			MostraCursor.setBusyCursor("Gravando dados ...", Application.application, MostraCursor.CURSOR_PROGRESSO);
			
			atualizarCamposRegistro();
			
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.pessoaInstituicao = pessoaInstituicaoVO;
			dto.dados.produtosBancoob = this.produtosBancoob;
			verificarCadastroPessoa();
		}

		public function excluirRegistro(tributacaoVO:Object):void {

		}
		
		public function restaurarRegistro():void{
			pessoaInstituicaoVO = copiarPessoaInstituicao(pessoaInstituicaoVOBkp);
		}

		public function verificarAlteracoes():Boolean {
			if (this.unidadeIntitucional.selectedItem.id != this.pessoaInstituicaoVOBkp.idUnidadeInst)
				return false;
			
			if (this.responsavel.selectedItem.idFuncionario != this.pessoaInstituicaoVOBkp.funcionario.idFuncionario)
				return false;
			
			if (this.perfilTarifario.selectedItem.pk.codPerfilTarifario != this.pessoaInstituicaoVOBkp.perfilTarifario.pk.codPerfilTarifario)
				return false;
			
			if (this.emiteAviso.selected != this.pessoaInstituicaoVOBkp.emiteAvisoLancamento.valor)
				return false;
			
			if (this.parecer.text != this.pessoaInstituicaoVOBkp.parecerGerencia)
				return false;
			
			return true;
		}

		private function validarCampos():Boolean {
			return true;
		}

		public function limparForm():void {
			restaurarRegistro();
			preencherCampos();
			this.nivelRisco.selectedIndex = 0;
			this.motivo.text = "";
		}

		public function setPessoa(pessoaVO:PessoaVO=null):void {
			this.pessoa = pessoaVO;		
		}
		
		public function getEntidadeVigente():VigenteVO {
			return null;
		}
		
		private function verificarCadastroPessoa(evento:Event=null): void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.pessoaInstituicao = pessoaInstituicaoVO;
			dto.dados.produtosBancoob = this.produtosBancoob;
			
			servicoValidarCadastro.getOperation("verificarCadastroPessoa").send(dto);
		}
		
		private function retornoValidarSucesso(evt:ResultEvent):void {
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.pessoaInstituicao = pessoaInstituicaoVO;
			dto.dados.produtosBancoob = this.produtosBancoob;
			
			if (_novo){
				servicoIncluir.getOperation(OPERACAO_INCLUIR_DADOS).send(dto);
			}else {
				servicoAlterar.getOperation(OPERACAO_ALTERAR_DADOS).send(dto);
			}
		}
		
		private function retornoValidarErro(evt:FaultEvent):void {
			if(evt.fault){
				Alerta.show(evt.fault.faultString + "\nDeseja visualizar as restrições cadastrais?", "Pessoa com restrições", Alerta.ALERTA_PERGUNTA, null, exibirRestricoes);
			}
			carregarRegistro();
		}
		
		public function set produtosBancoob(value:Boolean):void {
			_produtosBancoob = value;
		}
		public function get produtosBancoob():Boolean {
			return _produtosBancoob;
		}
		
	    //--------------------------------------------------------------------------
	    //  Métodos das interfaces
	    //--------------------------------------------------------------------------
		public function trocarTelaEntrar(params:ParametrosAssistenteAtendimento=null):void{};
		public function trocarTelaSair(params:ParametrosAssistenteAtendimento=null):void{};	
	}
}