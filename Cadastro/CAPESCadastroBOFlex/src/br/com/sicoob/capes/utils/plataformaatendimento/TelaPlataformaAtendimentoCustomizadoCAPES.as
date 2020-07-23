package br.com.sicoob.capes.utils.plataformaatendimento
{                                                                                                                                    
	import br.com.bancoob.componentes.MostraCursor;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.bancoob.negocio.vo.AplicacaoVO;
	import br.com.bancoob.sisbr.IValidarAbertura;
	import br.com.bancoob.sisbr.ProcuraClientePlataforma;
	import br.com.bancoob.sisbr.componentes.PlataformaAtendimento.TelaPlataformaAtendimentoCustomizado;
	import br.com.bancoob.sisbr.eventos.EventValidacaoAbertura;
	import br.com.bancoob.sisbr.portal.PortalModel;
	import br.com.bancoob.util.servico.ServicoJava;
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
	import br.com.sicoob.capes.corporativo.componentes.plataformaatendimento.ConfiguracoesCAPES;
	import br.com.sicoob.capes.corporativo.componentes.plataformaatendimento.ProcuraClientePlataformaCAPES;
	import br.com.sicoob.capes.corporativo.vo.PessoaPlataformaVO;
	import br.com.sicoob.capes.utils.ConversaoPessoaUtil;
	
	import mx.core.Application;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	public class TelaPlataformaAtendimentoCustomizadoCAPES extends TelaPlataformaAtendimentoCustomizado implements IValidarAbertura {
		
		private var servicoPessoa:ServicoJava = new ServicoJava();                                                                   
		private var servicoProdutosBancoob:ServicoJava = new ServicoJava();                                                          
		private var destinoVO:DestinoVO;                                                                                             
		protected static var pessoa:PessoaPlataformaVO;                                                                                     
		
		private static const PRODUTOS_BANCOOB : String = "produtosBancoob";                                                          
		public static const CLASSE_SERVICO: String =                                                                                 
			"br.com.sicoob.capes.corporativo.fachada.ProcurarPessoaFachada";                                     
		public static const CLASSE_SERVICO_PRODUTOS_BANCOOB: String =                                                                
			"br.com.sicoob.capes.cadastro.fachada.ProdutosBancoobFachada";                                    
		private var cuc:Boolean = false;                                                                                             
		private var _produtosBancoob:Boolean = false;                                                                                
		
		public function TelaPlataformaAtendimentoCustomizadoCAPES() {                                                                  
			super();                                                                                                                 
			include '../RegistroClasses.as';
			
			servicoPessoa.source = CLASSE_SERVICO;                                                                                   
			servicoPessoa.showBusyCursor = true;                                                                                     
			servicoPessoa.bloquearOperacao = true;        	                                                                         
			servicoPessoa.mensagemEspera = "Carregando dados...";                                                                    
			servicoPessoa.addEventListener(ResultEvent.RESULT, pesquisarPessoa_onResult);                                            
			servicoPessoa.addEventListener(FaultEvent.FAULT, validarAbertura_onError);                                               
			
			this.servicoProdutosBancoob.source = CLASSE_SERVICO_PRODUTOS_BANCOOB;                                                    
			this.servicoProdutosBancoob.showBusyCursor = true;                                                                       
			this.servicoProdutosBancoob.bloquearOperacao = true;        	                                                         
			this.servicoProdutosBancoob.mensagemEspera = "Validando pessoa selecionada...";                                          
			this.servicoProdutosBancoob.addEventListener(ResultEvent.RESULT, validarAbertura_onResult);                              
			this.servicoProdutosBancoob.addEventListener(FaultEvent.FAULT, validarAbertura_onError);                                 
			
			var app:AplicacaoVO = PortalModel.obterAplicativoSelecionado();                                                          
			cuc = (app.atributos.hasOwnProperty("cuc") && app.atributos["cuc"] == "1");                                              
			
			//			this.addEventListener(FlexEvent.CREATION_COMPLETE, onCreationComplete);                                                  
		}                                                                                                                            
		
		//		protected function onCreationComplete(event:FlexEvent):void {                                                                
		//			listaBotoes.botOk.removeEventListener(MouseEvent.CLICK, gravarClicado);		                                             
		//			listaBotoes.botOk.addEventListener(MouseEvent.CLICK, btGravarClicado);		                                             
		//		}                                                                                                                            
		//		                                                                                                                             
		//		private function btGravarClicado(event:MouseEvent):void {                                                                    
		//			if (!_novo && verificarAlteracoes()) {                                                                                   
		//				Alerta.show(TelaPlataformaAtendimentoCAPESBase.MENSAGEM_ALTERACAO_CORPO,                                               
		//					TelaPlataformaAtendimentoCAPESBase.MENSAGEM_ALTERACAO_CABECALHO, Alerta.ALERTA_INFORMACAO);                        
		//			} else {                                                                                                                 
		//				gravarClicado(event);                                                                                                
		//			}                                                                                                                        
		//		}                                                                                                                            
		//		                                                                                                                             
		//		protected function verificarAlteracoes():Boolean {                                                                           
		//			return true;                                                                                                             
		//		}                                                                                                                            
		
		public function get produtosBancoob():Boolean {                                                                              
			return _produtosBancoob;                                                                                                 
		}                                                                                                                            
		
		//--------------------------------------------------------------------------                                                 
		//  Métodos da interface.                                                                                                    
		//--------------------------------------------------------------------------	                                             
		public function validarAbertura(params:Object=null):void {                                                                   
			this._produtosBancoob = (this.parametros != null)                                                                        
				&& (this.parametros.indexOf(PRODUTOS_BANCOOB) >= 0);                                                             
			inicializarServicos();			                                                                                         
		}                                                                                                                            
		
		public function validarAbertura_onResult(evt:ResultEvent):void {                                                             
			this.dispatchEvent(new EventValidacaoAbertura(EventValidacaoAbertura.EVENTO_VALIDACAO_OK));	 			                 
		}
		
		public function validarAbertura_onError(evt:FaultEvent):void {	                                                             
			this.dispatchEvent(new EventValidacaoAbertura(EventValidacaoAbertura.EVENTO_VALIDACAO_ERRO));                            
		}
		
		private function pesquisarPessoa_onResult(event:ResultEvent):void {                                                          
			pessoaRecuperada(event.result.dados["pessoa"]);                                                                          
		}                                                                                                                            
		
		private function pessoaRecuperada(pessoaRecuperada:PessoaPlataformaVO = null):void {                                                
			pessoa = pessoaRecuperada;                                                                                               
			validarAberturaProdutosBancoob();                                                                                        
		}

		/**
		 * Método para ser sobreescrito nos módulos.
		 * Deverá remover os listeners e objetos que podem ficar na memória.
		 **/
		public override function dispose():void {
			super.dispose();
			servicoPessoa = null;
			servicoProdutosBancoob = null;                                                       
			destinoVO = null;
			pessoa = null;
		}
		
		//--------------------------------------------------------------------------                                                 
		//  Configuração de destino dos serviços.                                                                                    
		//--------------------------------------------------------------------------		                                         
		private function inicializarServicos():void {                                                                                
			destinoVO = ConfiguracoesCAPES.destinoCAPES;                                                                             
			configurarDestinos();                                                                                                    
			consultarPessoa();                                                                                                       
		}                                                                                                                            
		
		private function  configurarDestinos():void{                                                                                 
			this.servicoProdutosBancoob.configurarDestino(destinoVO);                                                                
			servicoPessoa.configurarDestino(destinoVO);                                                                              
			configurarDestinosServicos(destinoVO);				                                                                     
		}                                                                                                                            
		
		/** Função que deve ser sobrescrita para carregar os serviços desejados. */                                                  
		protected function configurarDestinosServicos(destinoVO:DestinoVO):void{                                                     
		}		                                                                                                                     
		
		//--------------------------------------------------------------------------                                                 
		//  Consultar cliente migrado.                                                                                               
		//--------------------------------------------------------------------------			                                     
		private function consultarPessoa(): void {                                                                                   
			
			if(cuc) {                                                                                                                
				pessoaRecuperada(ProcuraClientePlataformaCAPES.getPessoaSelecionada());                                                     
			} else {                                                                                                                 
				MostraCursor.setBusyCursor("Carregando Registros!",                                                                  
					Application.application, MostraCursor.CURSOR_PESQUISAR);                                                     
				var dto: RequisicaoReqDTO = new RequisicaoReqDTO();                                                                  
				var objPessoa: Object = ProcuraClientePlataforma.getObjCliente()                                                     
				dto.dados.numPessoa = objPessoa.NUMPESSOA;                                                                           
				servicoPessoa.getOperation("obterPessoaCadastroUnico").send(dto);                                                    
			}	                                                                                                                     
		}                                                                                                                            
		
		private function validarAberturaProdutosBancoob(): void {                                                                    
			
			if (this.produtosBancoob) {                                                                                              
				var dto : RequisicaoReqDTO = new RequisicaoReqDTO();                                                                 
				dto.dados.pessoa = ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(pessoa);                             
				servicoProdutosBancoob.getOperation("validarAberturaProdutosBancoob").send(dto);                                     
			} else {                                                                                                                 
				validarAbertura_onResult(null);                                                                                      
			}                                                                                                                        
		}                                                                                                                            
		
		//--------------------------------------------------------------------------                                                 
		//  Tratamento da seleção de cliente no CAPES.                                                                               
		//--------------------------------------------------------------------------					                             
		public static function getPessoaPlataforma(): PessoaPlataformaVO {                                                                       
			return pessoa;                                                                                                           
		}                                                                                                                            
		
		public static function getPessoaSelecionada():PessoaCompartilhamentoVO {                                                     
			return ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento(pessoa);                                             
		}			                                                                                                                 
	}                                                                                                                                
}                                                                                                                                    

