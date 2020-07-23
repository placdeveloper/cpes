package br.com.sicoob.capes.comum.vo.entidades.pk {

	import br.com.bancoob.negocio.entidades.BancoobChavePrimaria;
	
	import flash.net.registerClassAlias;
	import flash.utils.IDataInput;
	import flash.utils.IDataOutput;

	registerClassAlias("br.com.sicoob.capes.negocio.entidades.pk.CooperativaAnotacaoPK", CooperativaAnotacaoPK);
    public class CooperativaAnotacaoPK extends BancoobChavePrimaria {
		
		private var _codTipoAnotacao:Number;
		private var _idInstituicao:Number;
		
		public function get codTipoAnotacao():Number {
			return _codTipoAnotacao;
		}
		
		public function set codTipoAnotacao(value:Number):void {
			_codTipoAnotacao = value;
		}
		
		public function get idInstituicao():Number {
			return _idInstituicao;
		}
		
		public function set idInstituicao(value:Number):void {
			_idInstituicao = value;
		}
		
		override public function readExternal(input:IDataInput):void {
			_codTipoAnotacao = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
			_idInstituicao = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
		}
		
		override public function writeExternal(output:IDataOutput):void {
			output.writeObject(_codTipoAnotacao);
			output.writeObject(_idInstituicao);
		}
	}
}