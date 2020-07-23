package br.com.sicoob.capes.comum.vo {

    import br.com.bancoob.vo.BancoobVO;
    
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;

    [Bindable]
    public class StatusTransferenciaGrupoEconomicoVOBase extends BancoobVO {
		
		private var _nomeGrupoEconomico:String;
		private var _quantidade:Number;
		
		public function get nomeGrupoEconomico():String {
			return _nomeGrupoEconomico;
		}
		
		public function set nomeGrupoEconomico(value:String):void {
			_nomeGrupoEconomico = value;
		}
		
		public function get quantidade():Number {
			return _quantidade;
		}
		
		public function set quantidade(value:Number):void {
			_quantidade = value;
		}
		
		public function readExternal(input:IDataInput):void {
			_nomeGrupoEconomico = input.readObject() as String;
			_quantidade = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
        }

		public function writeExternal(output:IDataOutput):void {
			output.writeObject(_nomeGrupoEconomico);
			output.writeObject(_quantidade);
        }
    }
}