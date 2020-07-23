package br.com.sicoob.capes.cadastrarNovaPessoa.janelas
{
	import flash.events.MouseEvent;
	
	import mx.events.FlexEvent;
	import mx.managers.IFocusManagerContainer;

	public class AlertaCompartilhamento extends AlertaCompartilhamentoView implements IFocusManagerContainer
	{	
		private var _funcaoOk:Function;
			
	    //--------------------------------------------------------------------------
	    //  Propriedades
	    //--------------------------------------------------------------------------
	    /**
	     *  Construtor.
	     */
		public function AlertaCompartilhamento(funcaoOk:Function):void{

			super();
			
			_funcaoOk = funcaoOk;
			this.addEventListener(FlexEvent.CREATION_COMPLETE, init); //após criação configura componente
		}
		
		//configura dados do alerta
		private function init(evt:FlexEvent):void{
			
			txtMsg.text = "O compartilhamento de cadastro somente pode ser realizado de acordo com as regras " + 
					"dispostas na Política Institucional de Cadastro e no MIG – Cadastro, e efetuado apenas pelas " + 
					"entidades que se relacionam com o cadastrado e pelas áreas sistêmicas de gestão corporativa de " + 
					"produtos e serviços, modelagem de risco, auditoria e controle. \nO acesso aos dados cadastrais é " + 
					"monitorado pelo Gestor Centralizado do Cadastro e o uso indevido do compartilhamento sujeita a " + 
					"entidade infratora às sanções regulamentares. \nEm caso de dúvida, consultar, previamente, o " + 
					"Gestor Centralizado do Cadastro. \n\nDeseja continuar?";
					
			//executa função passada como listener paga click no botão
			btSim.addEventListener(MouseEvent.CLICK, _funcaoOk);
						
			//adiciona listener para remover aletar ao clicar em algum botão
			btSim.addEventListener(MouseEvent.CLICK, fechar);
			btNao.addEventListener(MouseEvent.CLICK, fechar);
		}		
		
	    private function fechar(evt:MouseEvent=null):void {
			this.fecharJanela();			
		}		
		
	}
}