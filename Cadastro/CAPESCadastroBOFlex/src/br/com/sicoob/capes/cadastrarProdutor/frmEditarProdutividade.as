package br.com.sicoob.capes.cadastrarProdutor
{
	import br.com.bancoob.componentes.Modulo;
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.componentes.eventos.SelecaoEvent;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.sisbr.ParametrosAssistenteAtendimento;
	import br.com.bancoob.sisbr.eventos.EventAssistenteAtendimento;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.tipos.IDateTime;
	import br.com.bancoob.util.FormataNumero;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.CadastroUnicoUtil;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.VigenteVO;
	import br.com.sicoob.capes.comum.vo.entidades.EmpreendimentoVO;
	import br.com.sicoob.capes.comum.vo.entidades.ProdutividadeVO;
	import br.com.sicoob.capes.comum.vo.entidades.ProdutorVO;
	import br.com.sicoob.capes.comum.vo.entidades.bem.BemImovelVO;
	import br.com.sicoob.capes.utils.plataformaatendimento.IEdicaoPlataformaAtendimentoCAPES;
	import br.com.sicoob.capes.utils.plataformaatendimento.TelaPlataformaAtendimentoCAPESBase;
	
	import flash.events.Event;
	import flash.events.FocusEvent;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.events.FlexEvent;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.ObjectUtil;
	
	public class frmEditarProdutividade extends frmEditarProdutividadeView implements IEdicaoPlataformaAtendimentoCAPES {
	
		static private const OPERACAO_OBTER_DEFINICOES: String = "obterDefinicoes";
		static private const OPERACAO_INCLUIR_DADOS: String = "incluirDados";
		static private const OPERACAO_ALTERAR_DADOS: String = "alterarDados";
		static private const OPERACAO_EXCLUIR_DADOS: String = "excluirDados";
		static private const OPERACAO_OBTER_DADOS: String = "obterDados";
		static private const OPERACAO_FINALIZAR_EXPLORACAO: String = "finalizarExploracao";
		static private const OPERACAO_OBTER_EMPREENDIMENTO: String = "obterEmpreendimento";
			
		public var servicoEdicao:ServicoJava = ServicoJavaUtil.getServico(ProdutorSelecao.CLASSE_SERVICO_PRODUTIVIDADE);
		public var servicoConsulta:ServicoJava = ServicoJavaUtil.getServico(ProdutorSelecao.CLASSE_SERVICO_PRODUTIVIDADE);
		public var servicoDefinicao:ServicoJava = ServicoJavaUtil.getServico(ProdutorSelecao.CLASSE_SERVICO_PRODUTIVIDADE);
		public var servicoExcluir:ServicoJava = ServicoJavaUtil.getServico(ProdutorSelecao.CLASSE_SERVICO_PRODUTIVIDADE);			
		public var servicoEmpreendimento:ServicoJava = ServicoJavaUtil.getServico(ProdutorSelecao.CLASSE_SERVICO_PRODUTIVIDADE);
		
		private var registro:ProdutividadeVO = new ProdutividadeVO();
		private var registroBkp:ProdutividadeVO = null;	
		
		private var _produtor:ProdutorVO;
		
		public function frmEditarProdutividade() {
			super();
			
			servicoDefinicao.addEventListener(ResultEvent.RESULT, retornoObterDefinicoes);
			servicoConsulta.addEventListener(ResultEvent.RESULT, retornoCarregarRegistro);
			servicoEdicao.addEventListener(ResultEvent.RESULT, retornoEdicao);
			
			servicoExcluir.addEventListener(ResultEvent.RESULT, retornoExcluir);
			servicoExcluir.addEventListener(FaultEvent.FAULT, retornoExcluirErro);
			
			servicoEmpreendimento.addEventListener(ResultEvent.RESULT, retornoObterEmpreendimento);
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		public function init(evt:FlexEvent):void {
			this.cboBemImovel.dataProvider = new ArrayCollection();	
			carregarDefinicoes();
			txtAnoInicio.addEventListener(FocusEvent.FOCUS_OUT, preencherAnoFim);
			txtProducao.addEventListener(FocusEvent.FOCUS_OUT, calcularRendaBruta);
			txtPrecoMedio.addEventListener(FocusEvent.FOCUS_OUT, calcularRendaBruta);
			txtRendaBruta.addEventListener(FocusEvent.FOCUS_OUT, calcularRendaLiquida);
			txtValorPercentualRebate.addEventListener(FocusEvent.FOCUS_OUT, calcularRendaLiquida);
			
			PortalModel.portal.obterDefinicoesDestino("servicosJava", onDestinoPortalRecuperado);
		}

		public function configurarDestinosServicos(destinoVO:DestinoVO):void{
			this.destino = destinoVO;
			servicoConsulta.configurarDestino(destinoVO);
			servicoDefinicao.configurarDestino(destinoVO);
			servicoEdicao.configurarDestino(destinoVO);
			servicoExcluir.configurarDestino(destinoVO);
			servicoEmpreendimento.configurarDestino(destinoVO);
		}
		
		private function onDestinoPortalRecuperado(destino:DestinoVO):void {
			procurarEmpreendimento.configurarDestino(destino);
			procurarEmpreendimento.addEventListener(SelecaoEvent.OBJETO_SELECIONADO, obterEmpreendimento);
		}

		public function gravarRegistro():void {
			atualizarCamposRegistro();
			executarSeValido(gravarDados);
		}
		
		public function atualizarCamposRegistro():void{
			
			if (_novo) {
				registro = new ProdutividadeVO();
				registro.pessoaCompartilhamento = TelaPlataformaAtendimentoCAPESBase.getPessoaSelecionada();
			}
			
			var empreendimento: EmpreendimentoVO = null;
			
			if(!CadastroUnicoUtil.isVazio(procurarEmpreendimento.txtValor.text)) {
				empreendimento = new EmpreendimentoVO();
				empreendimento.codigo = Number(procurarEmpreendimento.txtValor.text);
			}
			
			var bemImovel:BemImovelVO = cboBemImovel.selectedItem as BemImovelVO;
			if(bemImovel != null) {
				registro.bemImovel = bemImovel;
				registro.idBemImovel = bemImovel.idBem;
			}
			
			registro.anoInicioSafra = txtAnoInicio.valor;
			registro.anoFimSafra = txtAnoFim.valor;
			registro.empreendimento = empreendimento;
			registro.descricao = txtDescricao.text;
			registro.producao = txtProducao.valor;
			registro.quantidadeOuArea = txtAreaQuantidade.valor;
			registro.percentualRebate = txtValorPercentualRebate.valor;
			registro.valorPrecoMedio = txtPrecoMedio.valor;
			registro.valorRendaBruta = txtRendaBruta.valor;
			
			registro.produtor = _produtor;
		}		

		public function restaurarRegistro():void{
			registro = (ObjectUtil.copy(registroBkp) as ProdutividadeVO);
		}
		
		public function verificarAlteracoes():Boolean {
			
			var idBem:Number = 0;
			var idBemSelecionado:Number = 0;
			
			if(registroBkp.bemImovel != null) {
				idBem = registroBkp.idBemImovel;
			}
			
			if(cboBemImovel.selectedItem != null) {
				idBemSelecionado = cboBemImovel.selectedItem.idBem;
			}
			
			return txtAnoInicio.valor == registroBkp.anoInicioSafra
				&& txtAnoFim.valor == registroBkp.anoFimSafra
				&& procurarEmpreendimento.txtValor.text == String(registroBkp.empreendimento.codigo)
				&& idBemSelecionado == idBem
				&& txtDescricao.text == registroBkp.descricao
				&& txtProducao.valor == registroBkp.producao
				&& txtAreaQuantidade.valor == registroBkp.quantidadeOuArea
				&& txtValorPercentualRebate.valor == registroBkp.percentualRebate
				&& txtPrecoMedio.valor == registroBkp.valorPrecoMedio
				&& txtRendaBruta.valor == registroBkp.valorRendaBruta;
		}			

		public function novoRegistro():void {
			_novo = true;
			registro = new ProdutividadeVO();
			registroBkp = new ProdutividadeVO();
			
			preencherCampos();
		}
		
		public function preencherCampos():void {
			
			var empreendimento: EmpreendimentoVO = registroBkp.empreendimento;
			var bemImovel: Object = null;
			var codigoEmpreendimento: String = "";
			var anoInicio: String  = "";
			var anoFim: String = "";
			
			if(empreendimento != null) {
				codigoEmpreendimento = String(empreendimento.codigo);
			}
			if(!isNaN(registroBkp.anoInicioSafra)) {
				anoInicio = String(registroBkp.anoInicioSafra);
			}
			if(!isNaN(registroBkp.anoFimSafra)) {
				anoFim = String(registroBkp.anoFimSafra);
			}
			if(registroBkp.bemImovel != null) {
				bemImovel = registroBkp.idBemImovel;
			}			
			
			txtAnoInicio.text = anoInicio;
			txtAnoFim.text = anoFim;
			txtDescricao.text = registroBkp.descricao;
			txtProducao.valor = registroBkp.producao;
			txtAreaQuantidade.valor = registroBkp.quantidadeOuArea;
			txtValorPercentualRebate.valor = registroBkp.percentualRebate;
			txtPrecoMedio.valor = registroBkp.valorPrecoMedio;
			txtRendaBruta.valor = registroBkp.valorRendaBruta;			
			cboBemImovel.procuraItemPorNome(bemImovel, "idBem");
			this.procurarEmpreendimento.txtValor.text = codigoEmpreendimento;
			if (codigoEmpreendimento.length > 0) {
				this.procurarEmpreendimento.pesquisar();
			}

			calcularRendaLiquida();
		}		

		public function carregarDefinicoes(obj:Object=null):void {
			MostraCursor.setBusyCursor("Obtendo definições ...", 
			Application.application, MostraCursor.CURSOR_PROGRESSO);
					
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.pessoa = TelaPlataformaAtendimentoCAPESBase.getPessoaSelecionada();					
			servicoDefinicao.getOperation(OPERACAO_OBTER_DEFINICOES).send(dto);
		}
	    //--------------------------------------------------------------------------
	    //  Métodos de edição do registro.
	    //--------------------------------------------------------------------------				
		public function excluirRegistro(objeto:Object):void {
			MostraCursor.setBusyCursor("Excluindo Registro!", Application.application, 
					MostraCursor.CURSOR_EXCLUIR);
					
			var vo:ProdutividadeVO = new ProdutividadeVO();
			vo.idProdutividade = objeto.idProdutividade;
								
			var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.produtividade = vo;
			servicoExcluir.getOperation(OPERACAO_EXCLUIR_DADOS).send(dto);
		}
		
		public function carregarRegistro(obj:Object): void {
			
			if(obj != null){
				_novo = false;
				MostraCursor.setBusyCursor("Carregando Registro ...", 
						Application.application, MostraCursor.CURSOR_PESQUISAR);

				var vo: ProdutividadeVO = new ProdutividadeVO();
				vo.idProdutividade = obj.idProdutividade;
				
				var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
				dto.dados.produtividade = vo;
				servicoConsulta.getOperation(OPERACAO_OBTER_DADOS).send(dto);
			}
		}
		
		private function gravarDados():void {
			
			MostraCursor.setBusyCursor("Gravando Registro!", Application.application,
					MostraCursor.CURSOR_GRAVAR);

			var dto: RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.produtividade = registro;
			
			if (_novo) {
				servicoEdicao.getOperation(OPERACAO_INCLUIR_DADOS).send(dto);
			} else {
				servicoEdicao.getOperation(OPERACAO_ALTERAR_DADOS).send(dto);
			}
		}		
		
		public function finalizarExploracao(idProdutividade: Number, percentual: Number, 
				houveFrustracao: Boolean, dataOcorrencia: IDateTime):void {
			
			MostraCursor.setBusyCursor("Finalizando Exploração!", Application.application,
					MostraCursor.CURSOR_GRAVAR);

			var dto: RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.idProdutividade = idProdutividade;
			dto.dados.houveFrustracao = houveFrustracao;
			dto.dados.percentualFrustracao = String(percentual);
			dto.dados.dataOcorrencia = dataOcorrencia;
			
			servicoEdicao.getOperation(OPERACAO_FINALIZAR_EXPLORACAO).send(dto);
		}
				
	    //--------------------------------------------------------------------------
	    //  Métodos auxiliares.
	    //--------------------------------------------------------------------------				
	    private function preencherAnoFim(evt: FocusEvent=null): void {
	    	if(!isNaN(txtAnoInicio.valor)) {
	    		txtAnoFim.text = String(txtAnoInicio.valor + 1);
	    	}
	    }
	    
		private function calcularRendaBruta(evt: FocusEvent=null): void {
			
			if(!isNaN(txtProducao.valor) && !isNaN(txtPrecoMedio.valor)) {
				txtRendaBruta.valor = txtProducao.valor * txtPrecoMedio.valor;
			}
			
			calcularRendaLiquida();
		}
		
		private function calcularRendaLiquida(evt: FocusEvent=null): void {
			
			if(!isNaN(txtRendaBruta.valor) && !isNaN(txtValorPercentualRebate.valor)) {
				var rendaBruta: Number = txtRendaBruta.valor;
				var rendaLiquida: Number = rendaBruta - (rendaBruta * txtValorPercentualRebate.valor/100); 
				txtRendaLiquida.text = FormataNumero.formata(rendaLiquida);  
			}
		}		
		
		private function obterEmpreendimento(evt: SelecaoEvent) : void {
			
			if(evt.objeto != null){
				
				MostraCursor.setBusyCursor("Carregando Registro ...", 
						Application.application, MostraCursor.CURSOR_PROGRESSO);
				
				var dto:RequisicaoReqDTO = new RequisicaoReqDTO();
				dto.dados.codigoEmpreendimento = evt.objeto["CODEMPREENDIMENTO"];	
				servicoEmpreendimento.getOperation(OPERACAO_OBTER_EMPREENDIMENTO).send(dto);
			}
		}
		
	    //--------------------------------------------------------------------------
	    //  Métodos de callback.
	    //--------------------------------------------------------------------------				
		private function retornoObterDefinicoes(event: ResultEvent): void {
			
			MostraCursor.removeBusyCursor();
			cboBemImovel.dataProvider = event.result.dados.imoveis;
		}					
					
		private function retornoCarregarRegistro(evt:ResultEvent):void {
			registro = evt.result.dados.produtividade;
			registroBkp = ObjectUtil.copy(registro) as ProdutividadeVO;
			_novo = false;
			this.dispatchEvent(new Event(REGISTRO_CARREGADO));	
		}
							
		private function retornoEdicao(evt: ResultEvent): void {

			MostraCursor.removeBusyCursor();
	  		this.dispatchEvent(new EventAssistenteAtendimento(EventAssistenteAtendimento.EVENTO_CONFIRMAR_MUDANCA_TELA));
			this.dispatchEvent(new Event(Modulo.REGISTRO_GRAVADO));	
		}				

		private function retornoExcluir(evt:ResultEvent):void {
			
			MostraCursor.removeBusyCursor();
			this.dispatchEvent(new Event(REGISTRO_EXCLUIDO));		
		}
		
		public function retornoExcluirErro(evt:FaultEvent):void {
			 
			MostraCursor.removeBusyCursor();
			this.dispatchEvent(new Event(REGISTRO_EXCLUIDO));							
		}	
		
		private function retornoObterEmpreendimento(evt: ResultEvent): void {

			var empreendimento: EmpreendimentoVO = evt.result.dados.empreendimento
			
			if(empreendimento != null) {
				if(empreendimento.unidadePrevisao != null) {
					lblUnidadeProducao.text = empreendimento.unidadePrevisao.descricao;
					lblUnidadePrecoMedio.text = empreendimento.unidadePrevisao.descricao;
				}
				if(empreendimento.unidadeArea != null) {
					lblUnidadeAreaQuantidade.text = empreendimento.unidadeArea.descricao;
				}
				txtAreaQuantidade.validarObrigatorio = empreendimento.exigeArea.valor;
				cboBemImovel.validarObrigatorio = empreendimento.exigeImovel.valor;
			}
			MostraCursor.removeBusyCursor();
		}
		
		/** Interface ITelaBasePlataformaAtendimento*/
		public function trocarTelaEntrar(params:ParametrosAssistenteAtendimento=null):void{};
		public function trocarTelaSair(params:ParametrosAssistenteAtendimento=null):void{};
		
		[Deprecated]
		public function getEntidadeVigente():VigenteVO {
			return registro;
		}
		
		public function isRegistroBloqueadoAlteracao():Boolean {
			return false;
		}
		
		public function setProdutor(valor:ProdutorVO):void {
			_produtor = valor;
		}
		
		public function limparLabelEmpreendimento():void {
			procurarEmpreendimento.lblDescricao.text = "";
		}
		
		public function isRegistroNovo(valor:Boolean):void{
			_novo = valor;
		}
		
	}
}