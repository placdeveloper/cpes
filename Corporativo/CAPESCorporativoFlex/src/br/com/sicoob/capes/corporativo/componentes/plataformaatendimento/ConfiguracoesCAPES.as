package br.com.sicoob.capes.corporativo.componentes.plataformaatendimento
{
	import br.com.bancoob.vo.DestinoVO;

	public class ConfiguracoesCAPES
	{
		private static var _destinoCAPES:DestinoVO;
		
		public static function get destinoCAPES():DestinoVO
		{
			return _destinoCAPES;
		}
		
		public static function set destinoCAPES(value:DestinoVO):void
		{
			_destinoCAPES = value;
		}
	}
}