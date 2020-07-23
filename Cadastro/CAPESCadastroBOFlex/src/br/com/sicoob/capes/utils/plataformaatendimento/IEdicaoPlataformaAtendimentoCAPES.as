package br.com.sicoob.capes.utils.plataformaatendimento
{
	import br.com.bancoob.sisbr.componentes.PlataformaAtendimento.IEdicaoPlataformaAtendimento;
	import br.com.sicoob.capes.comum.vo.VigenteVO;
	
	
	public interface IEdicaoPlataformaAtendimentoCAPES extends IEdicaoPlataformaAtendimento
	{
		[Deprecated]
		function getEntidadeVigente():VigenteVO;
		
		function isRegistroBloqueadoAlteracao():Boolean;
	}
}