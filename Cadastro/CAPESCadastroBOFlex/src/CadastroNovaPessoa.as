package 
{  
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.containers.Janela;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.componentes.pti.ObjetoEvent;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.tipos.Booleano;
	import br.com.bancoob.util.ICadastro;
	import br.com.bancoob.util.StringUtils;
	import br.com.bancoob.util.eventos.EventData;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.cadastrarNovaPessoa.CadastroNovaPessoaView;
	import br.com.sicoob.capes.cadastrarNovaPessoa.janelas.AlertaCompartilhamento;
	import br.com.sicoob.capes.comum.util.CadastroUnicoUtil;
	import br.com.sicoob.capes.comum.util.FormatadorUtil;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.DadosCNPJVO;
	import br.com.sicoob.capes.comum.vo.DadosCPFVO;
	import br.com.sicoob.capes.comum.vo.DadosReceitaFederalVO;
	import br.com.sicoob.capes.comum.vo.entidades.AtividadeEconomicaVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaFisicaVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaJuridicaVO;
	import br.com.sicoob.capes.comum.vo.entidades.TipoPessoaVO;
	
	import flash.display.Sprite;
	import flash.events.Event;
	import flash.events.FocusEvent;
	import flash.events.MouseEvent;
	
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.events.FaultEvent;

	public class CadastroNovaPessoa extends CadastroNovaPessoaView implements ICadastro {
		
		//--------------------------------------------------------------------------
	    //  atributos
	    //--------------------------------------------------------------------------
		public static const CLASSE_SERVICO: String = 
				"br.com.sicoob.capes.cadastro.fachada.PessoaFachada";
		private static const OPERACAO_OBTER_DEFINICOES: String = "obterDefinicoes";
		private static const OPERACAO_INICIAR_RELACIONAMENTO: String = "iniciarRelacionamento";
		private static const OPERACAO_INCLUIR_DADOS: String = "incluirDados";
		
		private var servicoDefinicao:ServicoJava = ServicoJavaUtil.getServico(CLASSE_SERVICO);
		private var servicoRelacionamento:ServicoJava = ServicoJavaUtil.getServico(CLASSE_SERVICO);
		private var servicoInclusao:ServicoJava = ServicoJavaUtil.getServico(CLASSE_SERVICO);

		private var registro:PessoaCompartilhamentoVO;
		private var pessoaIncluida:Object;
						 
		private var alertaCompartilhamento:AlertaCompartilhamento = new AlertaCompartilhamento(certezaConfirmado);
		private var janelaAlerta:Janela = null;
		
		private var dadosRFB:DadosReceitaFederalVO = null;
		
		private var _numeroCooperativa:Number;
		private var _unidadeInstituicao:Number;
		
		private var mutexOkClicado:Boolean = true;
		
		public function CadastroNovaPessoa() {

			super();
			include 'br/com/sicoob/capes/utils/RegistroClasses.as';
			registerClassAlias("br.com.sicoob.capes.comum.negocio.vo.DadosReceitaFederalVO", DadosReceitaFederalVO);
			registerClassAlias("br.com.sicoob.capes.comum.negocio.vo.DadosCPFVO",	DadosCPFVO);
			registerClassAlias("br.com.sicoob.capes.comum.negocio.vo.DadosCNPJVO", DadosCNPJVO);
			this.addEventListener(FlexEvent.CREATION_COMPLETE,init);
		}
		
		private function init(evt:FlexEvent):void{

			servicoDefinicao.addEventListener(ResultEvent.RESULT, retornoObterDefinicoes);
			servicoRelacionamento.addEventListener(ResultEvent.RESULT, onInclusaoFinalizada);
			servicoInclusao.addEventListener(ResultEvent.RESULT, onInclusaoFinalizada);
			servicoInclusao.addEventListener(FaultEvent.FAULT, retornoErroGeral);
			
			painelValidacao.cboTipoPessoa.addEventListener(ListEvent.CHANGE, selecionarTipoPessoa);
			painelValidacao.addEventListener(Modulo.REGISTRO_CARREGADO, pessoaConsultada);
			
			txtNomePessoa.addEventListener(FocusEvent.FOCUS_OUT, carregarNomeCompleto);
			
			// Adiciona evento nos botões							
			botOK.addEventListener(MouseEvent.CLICK, okClicado);
			botCancelar.addEventListener(MouseEvent.CLICK, limparCampos);
			
			inicializarServicos();
			
			painelValidacao.numeroCooperativa = _numeroCooperativa;
		} 	
		
		//--------------------------------------------------------------------------
	    //  Tratamento dos botões
	    //--------------------------------------------------------------------------
		private function okClicado(evt:MouseEvent):void
		{
				executarSeValido(gravarDados);
		}
		
		private function iniciarRelacionamentoConfirmado(evt:Event):void {
			
			if(janelaAlerta == null){
				janelaAlerta = new Janela();
				janelaAlerta.addChild(alertaCompartilhamento);
				janelaAlerta.title = "ATENÇÃO";
				janelaAlerta.icone = "br/com/bancoob/imagens/icos/aviso_16.png";	
			}
			
 			janelaAlerta.abrir(Sprite(Application.application),true,true);			
		}
			
		private function certezaConfirmado(evt:Event): void {
			MostraCursor.setBusyCursor("Gravando dados ...", 
					Application.application, MostraCursor.CURSOR_PROGRESSO);
					
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.pessoa = painelValidacao.obterPessoaCompartilhamento();
			dto.dados.numeroCooperativa = _numeroCooperativa;
			dto.dados.unidadeInstituicao = _unidadeInstituicao;
			servicoRelacionamento.getOperation(OPERACAO_INICIAR_RELACIONAMENTO).send(dto);
		}
		
		//--------------------------------------------------------------------------
	    //  Listeners
	    //--------------------------------------------------------------------------			    		
		private function pessoaConsultada(event:ObjetoEvent): void {

			var pessoaCompartilhamento: PessoaCompartilhamentoVO = painelValidacao.obterPessoaCompartilhamento();
			this.dadosRFB = event.objeto as DadosReceitaFederalVO;
			
			var habilitarNomes:Boolean = (this.dadosRFB == null);
			this.lblNomeCompleto.enabled = this.lblNomePessoa.enabled = habilitarNomes;
			this.txtNomeCompleto.enabled = this.txtNomePessoa.enabled = habilitarNomes;
			
			var existeOutroRelacionamento:Boolean = 
					pessoaCompartilhamento != null && pessoaCompartilhamento.transicoes.length > 0;
			
			this.carregarNomes(pessoaCompartilhamento);
			
			if(existeOutroRelacionamento) {
				Alerta.show("A pessoa já consta na base única. Deseja iniciar relacionamento?", 
					"Confirmação", Alerta.ALERTA_PERGUNTA, null, iniciarRelacionamentoConfirmado);
			}
			
			if(!existeOutroRelacionamento && painelValidacao.obterCadastroFacaParte()) {
				Alerta.show("Sua cooperativa está configurada para que novos cadastros de pessoas físicas não sejam realizados pela Plataforma de Atendimento. Por favor utilize o módulo Plataforma de Adesão Digital.", "Atenção!", Alerta.ALERTA_OK);
				return;
			}
			
			botOK.enabled = !existeOutroRelacionamento;
			painelValidacao.habilitarCampos(!botOK.enabled)
			txtNomePessoa.setFocus();
		}
		
		private function onInclusaoFinalizada(event:ResultEvent):void {
			pessoaIncluida = event.result.dados.pessoa;
			Alerta.show("Pessoa incluída com sucesso!", "Sucesso", Alerta.ALERTA_SUCESSO, null,	dispararEventoInclusao);
			onOffMutexOkClicado();
		}		

		private function dispararEventoInclusao(event:Event = null):void {
			
			dispatchEvent(new EventData("pessoaIncluida", pessoaIncluida, true));
			novoRegistro();
		}
		
		private function retornoObterDefinicoes(event:ResultEvent): void {
			
			MostraCursor.removeBusyCursor();

			painelValidacao.cboTipoPessoa.dataProvider = event.result.dados.tiposPessoa;	
			painelValidacao.cboTipoPessoa.selectedIndex = 0;
			//painelValidacao.cboTipoPessoa.setFocus();
			
			selecionarTipoPessoa();
		}

		private function carregarNomeCompleto(evt: FocusEvent=null): void {
			
			if(this.txtNomeCompleto.text == '') {
				this.txtNomeCompleto.text = this.txtNomePessoa.text; 
			}
		}	
		
	    //--------------------------------------------------------------------------
	    //  Métodos auxiliares.
	    //--------------------------------------------------------------------------
		private function selecionarTipoPessoa(evt:ListEvent=null):void {
			
			var tipoPessoa: TipoPessoaVO = painelValidacao.cboTipoPessoa.selectedItem as TipoPessoaVO;
			 
			if (tipoPessoa == null || isTipoPessoaFisicaSelecionado()){
				painelValidacao.lblCpfCnpj.text = "CPF:"
				painelValidacao.txtCpfCnpj.inputMask = "###.###.###-##"
				lblNomePessoa.text = "Nome:"
				lblNomeCompleto.text = "Nome Completo:"
				lblApelidoo.text = "Nome de Tratamento:"
			} else {
				painelValidacao.lblCpfCnpj.text = "CNPJ:"
				painelValidacao.txtCpfCnpj.inputMask = "##.###.###/####-##"
				lblNomePessoa.text = "Razão Social:"
				lblNomeCompleto.text = "Razão Social Completa:"
				lblApelidoo.text = "Nome Fantasia:"
			}	
		}	    
	    
	    private function isTipoPessoaFisicaSelecionado(): Boolean {
			var tipoPessoa: TipoPessoaVO = painelValidacao.cboTipoPessoa.selectedItem as TipoPessoaVO; 
			return tipoPessoa.codTipoPessoa == FormatadorUtil.TIPO_PESSOA_FISICA;	    
	    }
	    
		private function carregarNomes(pessoaCompartilhamento:PessoaCompartilhamentoVO=null):void {
			
			var codigoPerfil: Object = null;
			
			if(pessoaCompartilhamento != null) {
				txtApelido.text = pessoaCompartilhamento.nomeApelido;
				txtNomePessoa.text = pessoaCompartilhamento.nomePessoa;
				txtNomeCompleto.text = pessoaCompartilhamento.nomeCompleto;
			} else if (this.dadosRFB != null) {
				if ((this.dadosRFB is DadosCNPJVO) && (DadosCNPJVO(this.dadosRFB).nomeFantasia != null)) {
					txtApelido.text = StringUtils.left(DadosCNPJVO(this.dadosRFB).nomeFantasia, txtApelido.maxChars);
				} else {
					txtApelido.text = "";
				}
				txtNomePessoa.text = StringUtils.left(this.dadosRFB.nome, txtNomePessoa.maxChars);
				txtNomeCompleto.text = this.dadosRFB.nome;
			} else {
				txtApelido.text = "";
				txtNomePessoa.text = "";
				txtNomeCompleto.text = "";
			}
		}
		
		private function gravarDados():void {
			if(mutexOkClicado){
				onOffMutexOkClicado();
				MostraCursor.setBusyCursor("Gravando Registro!", 
						Application.application, MostraCursor.CURSOR_GRAVAR);			
	
				if(isTipoPessoaFisicaSelecionado()) {
					var atividade: AtividadeEconomicaVO = new AtividadeEconomicaVO();
					atividade.codigo = 55;
					
					var pf:PessoaFisicaVO = new PessoaFisicaVO();
					pf.menorEmancipado = new Booleano();
					pf.uniaoEstavel = new Booleano();
					pf.quantidadeDependentes = 0;
					pf.tipoSexo = CadastroUnicoUtil.SEXO_NAO_INFORMADO;
					registro = pf;
					registro.atividadeEconomica = atividade;
				} else {
					
					var pj:PessoaJuridicaVO = new PessoaJuridicaVO();
					pj.valorCapitalSocial = 0;
					pj.tipoEmpresa = null;
					registro = pj;
				}
				
				registro.autorizaConsultaBacen = new Booleano(false);
				registro.gravarHistorico = new Booleano(true);
				registro.nomePessoa = txtNomePessoa.text;
				registro.nomeCompleto = txtNomeCompleto.text;
				registro.nomeApelido = txtApelido.text;
				registro.pessoa = painelValidacao.obterPessoa();
	
				var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
				dto.dados.pessoa = registro;
				dto.dados.dadosRFB = dadosRFB;
				dto.dados.numeroCooperativa = _numeroCooperativa;
				dto.dados.unidadeInstituicao = _unidadeInstituicao;
				servicoInclusao.getOperation(OPERACAO_INCLUIR_DADOS).send(dto);
			}
		}		
		
		public function limparCampos(event:Event = null): void {
			
			painelValidacao.limpar();
			painelValidacao.cboTipoPessoa.setFocus();			
			
			this.dadosRFB = null;
			this.carregarNomes();
			selecionarTipoPessoa();
			
			this.botOK.enabled = false;				
			painelValidacao.habilitarCampos(!botOK.enabled)
		}
		
	    //--------------------------------------------------------------------------
	    //  Métodos da interface ICadastro.
	    //--------------------------------------------------------------------------
 		public function carregarDefinicoes(obj:Object=null):void {
			   
			MostraCursor.setBusyCursor("Obtendo definições ...", 
			Application.application, MostraCursor.CURSOR_PROGRESSO);
					
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			servicoDefinicao.getOperation(OPERACAO_OBTER_DEFINICOES).send(dto);
		}
		
		public function novoRegistro():void {				
			
			_novo = true;
			registro = new PessoaCompartilhamentoVO();
			pessoaIncluida = null;
			mutexOkClicado = true;
			limparCampos();
		}
		
		public function excluirRegistro(obj:Object):void {}
		public function carregarRegistro(obj:Object):void {}
		public function preencherCampos():void {}
		
		public function set numeroCooperativa(valor:Number):void {
			_numeroCooperativa = valor;
		}
		
		public function set unidadeInstituicao(valor:Number):void {
			_unidadeInstituicao = valor;
		}
				
	    //--------------------------------------------------------------------------
	    //  Configuração de destino dos serviços.
	    //--------------------------------------------------------------------------		
		private function inicializarServicos():void {
			PortalModel.portal.obterDefinicoesDestino("servicosJavaCapes", onDestinoRecuperado);
		}  		

		private function onDestinoRecuperado(destino:DestinoVO):void {
			servicoDefinicao.configurarDestino(destino);
			servicoRelacionamento.configurarDestino(destino);
			servicoInclusao.configurarDestino(destino);
			painelValidacao.servicoValidacao.configurarDestino(destino);
			carregarDefinicoes();
		}
		
		private function onOffMutexOkClicado():void{
			if(mutexOkClicado){
				mutexOkClicado = false;
			}else{
				mutexOkClicado = true;
			}
		}
		
		private function retornoErroGeral(evt:FaultEvent):void {
			onOffMutexOkClicado();
		}
	}
}
