package br.com.sicoob.capes.cadastrarRelacionamentoPessoa{
	
	public class RelacionamentoPessoaUtils {
	
		public function RelacionamentoPessoaUtils(){
		}
				
		public static function getDataPrimeiraHoraDoDia(data:Date, offset : Number = 0): Date {
			var d : Date = null;
			if (data != null) {
				d = new Date(data.getTime() + offset);
				d = new Date(d.fullYear, d.month, d.date);
			}
			return d 
		}
		
		public static function getDataUltimaHoraDoDia(data:Date, offset : Number = 0): Date {
			var d : Date = null;
			if (data != null) {
				d = new Date(data.getTime() + offset);
				d = new Date(d.fullYear, d.month, d.date, 23, 59, 59, 999);
			}
			return d;
		}

	}
}