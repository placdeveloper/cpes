package br.com.sicoob.capes.comum.componentes.instituicao.vo {
	import br.com.bancoob.vo.BancoobVO;
	
	import flash.net.registerClassAlias;
	import flash.utils.IDataInput;
	import flash.utils.IDataOutput;
	import flash.utils.IExternalizable;

	registerClassAlias("br.com.sicoob.capes.cadastro.negocio.vo.UnidadeVO", UnidadeVO);
	public class UnidadeVO extends BancoobVO implements IExternalizable {
		
		private var _codigo:Number;
		private var _descricao:String;
		
		public function get codigo():Number {
			return _codigo;
		}
		
		public function set codigo(valor:Number):void {
			_codigo = valor;
		}
		
		public function get descricao():String {
			return _descricao;
		}
		
		public function set descricao(valor:String):void {
			_descricao = valor;
		}
		
		public function readExternal(input:IDataInput):void {
			_codigo = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_descricao = input.readObject() as String;
		}
		
		public function writeExternal(output:IDataOutput):void {
			output.writeObject(_codigo);
			output.writeObject(_descricao);
		}
	}
}