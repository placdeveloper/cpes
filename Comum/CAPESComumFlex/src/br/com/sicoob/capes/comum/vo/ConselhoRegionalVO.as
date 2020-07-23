package br.com.sicoob.capes.comum.vo
{
	import br.com.bancoob.vo.BancoobVO;
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.sicoob.capes.cadastro.vo.informacoesprofissionais.ConselhoRegionalVO",
		ConselhoRegionalVO);
	
	public class ConselhoRegionalVO extends BancoobVO
	{
	
		private var _codigo:Number;
		private var _descricao:String;
		
		public function set codigo(value:Number):void {
			_codigo = value;
		}
		public function get codigo():Number {
			return _codigo;
		}
		
		public function set descricao(value:String):void {
			_descricao = value;
		}
		public function get descricao():String {
			return _descricao;
		}
	}
}