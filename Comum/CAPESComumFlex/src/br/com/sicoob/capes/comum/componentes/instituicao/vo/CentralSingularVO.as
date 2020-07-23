package br.com.sicoob.capes.comum.componentes.instituicao.vo {
	import br.com.bancoob.vo.BancoobVO;
	
	import flash.net.registerClassAlias;
	import flash.utils.IDataInput;
	import flash.utils.IDataOutput;
	import flash.utils.IExternalizable;

	registerClassAlias("br.com.sicoob.capes.cadastro.negocio.vo.CentralSingularVO", CentralSingularVO);
	public class CentralSingularVO extends BancoobVO {
		
		private var _idInstituicao:Number;
		private var _numeroCooperativa:Number;
		
		public function get idInstituicao():Number {
			return _idInstituicao;
		}
		
		public function set idInstituicao(valor:Number):void {
			_idInstituicao = valor;
		}
		
		public function get numeroCooperativa():Number {
			return _numeroCooperativa;
		}
		
		public function set numeroCooperativa(valor:Number):void {
			_numeroCooperativa = valor;
		}
		
	}
}