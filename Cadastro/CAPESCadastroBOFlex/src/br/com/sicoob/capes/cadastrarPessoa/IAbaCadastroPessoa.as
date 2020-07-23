package br.com.sicoob.capes.cadastrarPessoa
{
	import br.com.bancoob.vo.DestinoVO;
	import br.com.sicoob.capes.comum.vo.entidades.PessoaCompartilhamentoVO;
	
	import mx.rpc.events.ResultEvent;
	
	public interface IAbaCadastroPessoa
	{
		function preencherCampos(pessoa:PessoaCompartilhamentoVO):void;
		function configurarDestinosServicos(destino:DestinoVO):void;
		function verificarAlteracoes(registroBkp:PessoaCompartilhamentoVO):Boolean ;
		function retornoCarregarDefinicoes(event:ResultEvent): void;
		function atualizarCamposRegistro(registro:PessoaCompartilhamentoVO): PessoaCompartilhamentoVO;
	}
}