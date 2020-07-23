package br.com.sicoob.capes.cadastrarFuncionario {
	import br.com.bancoob.componentes.Alerta;
	import br.com.bancoob.componentes.Botao;
	import br.com.bancoob.componentes.cadastro.BarraBotoesFormularioCadastroView;
	import br.com.bancoob.dto.DateTimeBase;
	import br.com.bancoob.dto.RequisicaoDTO;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.componentes.procurarCliente.procurarPessoa;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.util.ServicoJavaUtil;
	import br.com.sicoob.capes.comum.vo.entidades.FuncaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.FuncionarioVO;
	import br.com.sicoob.capes.comum.vo.entidades.InstituicaoVO;
	import br.com.sicoob.capes.comum.vo.entidades.NucleoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaVO;
	
	import flash.events.Event;
	import flash.utils.flash_proxy;
	
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.ObjectUtil;
	
	public class FuncionarioEdicao extends FuncionarioEdicaoView {
		
		private var servico: ServicoJava;
		private var classeServico : String = "br.com.sicoob.capes.cadastro.fachada.FuncionarioFachada";
		private static const DESTINO_CAPES:String = "destinoCAPES";
		
		public function FuncionarioEdicao() {
			super();
			this.servico = ServicoJavaUtil.getServico(classeServico, 
					"Obtendo definições...", ResultEvent.RESULT, retornoObterDefinicoes);
			addEventListener(FlexEvent.CREATION_COMPLETE, init);
		}
		
		protected override function init(evento : FlexEvent) : void {
			super.init(evento);
			this.servicoEdicao = ServicoJavaUtil.getServico(classeServico, "Salvando dados...");
			this.servicoInclusao = ServicoJavaUtil.getServico(classeServico, "Salvando dados...");
			procurarPessoa.txtCodigo.maxChars = 11;
			procurarPessoa.addEventListener(br.com.bancoob.sisbr.componentes.procurarCliente.procurarPessoa.ITEM_SELECIONADO, pessoaSelecionada);
			dataAdmissao.addEventListener(Event.CHANGE,dataMinima);
			validacoesAdicionais.addItem(new FuncionarioValidator(procurarPessoa,dataAdmissao, dataDesligamento));
			
			var botaoAjuda:Botao = BarraBotoesFormularioCadastroView(this.barraBotoes).btnAjuda;
			botaoAjuda.visible = botaoAjuda.includeInLayout = false;
			
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();
			PortalModel.portal.obterDefinicoesDestino(app.atributos[DESTINO_CAPES], destino_recuperado);
		}
		
		private function destino_recuperado(destino:DestinoVO):void {
			this.servico.configurarDestino(destino);
			servicoEdicao.configurarDestino(destino);
			servicoInclusao.configurarDestino(destino);
			
			servico.getOperation("obterDefinicoes").send(new RequisicaoReqDTO());
		}
		
		protected override function houveAlteracoes():Boolean {
			var objetoAntigo : Object = objeto;
			var alterado : Boolean = false;
			if (objetoAntigo == null) {
				alterado = ((this.procurarPessoa.txtCodigo.text != "") || (this.matricula.text != "") || (this.funcao.selectedIndex > 0)
								|| (this.lotacao.selectedIndex > 0) || (this.nucleo.selectedIndex > 0) || (this.dataAdmissao.selectedDate != null)
									|| (this.dataDesligamento.selectedDate != null)) 
			} else {
				alterado = (		this.procurarPessoa.txtCodigo.text != objetoAntigo.pessoa.cpfCnpj 
								||  this.matricula.text != objeto.matricula 
								|| (this.funcao.selectedItem != null && this.funcao.selectedItem.idFuncao != objetoAntigo.funcao.idFuncao)
								|| (this.lotacao.selectedItem != null && this.lotacao.selectedItem.idInstituicao != objetoAntigo.instituicao.idInstituicao) 
								|| (this.nucleo.selectedItem != null && this.nucleo.selectedItem.idNucleo != objetoAntigo.nucleo.idNucleo)
							)
			}
			return alterado;
		}

		protected override function limparFormIncluir() : void {
			this.procurarPessoa.txtCodigo.text = null;
			this.procurarPessoa.procurarCodigo();
			this.matricula.text = "";
			this.funcao.selectedIndex = 0;
			this.lotacao.selectedIndex = 0;
			this.nucleo.selectedIndex = 0;
			this.dataAdmissao.selectedDate = null;
			this.dataDesligamento.selectedDate = null;
		}		
		
		protected override function preencherCampos():void {
			var objeto:FuncionarioVO = this.objeto as FuncionarioVO;
			this.procurarPessoa.txtCodigo.text = objeto.pessoa.cpfCnpj;
			this.procurarPessoa.procurarCodigo();
			this.matricula.text = objeto.matricula;
			this.funcao.procuraItemPorNome(objeto.funcao.idFuncao,"idFuncao");
			this.lotacao.procuraItemPorNome(objeto.instituicao.idUnidadeInst, "idUnidadeInst");
			this.nucleo.procuraItemPorNome(objeto.nucleo.idNucleo, "idNucleo");
			this.dataAdmissao.selectedDate = objeto.dataAdmissao.data;
			this.dataDesligamento.dataMinima = this.dataAdmissao.selectedDate;
			if(objeto.dataDesligamento != null){
				this.dataDesligamento.selectedDate = objeto.dataDesligamento.data;
			} else {
				this.dataDesligamento.selectedDate = null;
			}
		}
		
		protected override function montarDto():RequisicaoDTO {
			var vo : FuncionarioVO = new FuncionarioVO();
			vo.pessoa = new PessoaVO();
			vo.pessoa.idPessoa = procurarPessoa.codigo;
			vo.matricula = this.matricula.text;
			vo.funcao = this.funcao.selectedItem as FuncaoVO;
			vo.instituicao = this.lotacao.selectedItem as InstituicaoVO;
			vo.nucleo = this.nucleo.selectedItem as NucleoVO;
			vo.dataAdmissao = DateTimeBase.getDateTimeEntity(this.dataAdmissao.selectedDate);
			if(this.dataDesligamento.selectedDate != null){
				vo.dataDesligamento = DateTimeBase.getDateTimeEntity(this.dataDesligamento.selectedDate);
			}
			if(modo != MODO_INCLUSAO){
				vo.idFuncionario = this.objeto.idFuncionario;
			}
			var dto : RequisicaoReqDTO = new RequisicaoReqDTO();
			dto.dados.funcionario = vo;
			return dto as RequisicaoDTO;
		}
		
		protected override function configurarEstadoComponente(componente : UIComponent, 
				habilitar : Boolean) : void {
			this.procurarPessoa.enabled = (MODO_INCLUSAO == modo);
			super.configurarEstadoComponente(componente, habilitar);
		}
		
		private function retornoObterDefinicoes(event: ResultEvent): void { 
			this.funcao.dataProvider = event.result.dados.funcoes;
			this.lotacao.dataProvider = event.result.dados.pacs;
			this.nucleo.dataProvider = event.result.dados.nucleos;
			this.lotacao.labelFunction = cmbPaclblFunction;
			if ((initialized)  && (modo != MODO_INCLUSAO)) {
	       		preencherCampos();
	      	}  
			if ((initialized)  && (modo == MODO_INCLUSAO)) {
				limparFormIncluir();
			}  
		}
		
		private function cmbPaclblFunction(item: Object):String{
			return item.idUnidadeInst + " - " + item.nomeUnidade;
		}
		
		private function pessoaSelecionada(event: Event): void { 
			if(procurarPessoa.txtCodigo.text != "" && procurarPessoa.codigo == 0){
				Alerta.show("Pessoa não encontrada.","Atenção",Alerta.ALERTA_OK);
			}
		}
		
		private function dataMinima(event: Event): void {
			dataDesligamento.dataMinima = dataAdmissao.selectedDate;
		} 
	}
}