package br.com.sicoob.capes.comum.util
{
	import mx.collections.ArrayCollection;

	public final class DateUtil
	{
		
		public static const JANEIRO:int = 0; 
		public static const FEVEREIRO:int = 1; 
		public static const MARCO:int = 2; 
		public static const ABRIL:int = 3; 
		public static const MAIO:int = 4; 
		public static const JUNHO:int = 5; 
		public static const JULHO:int = 6; 
		public static const AGOSTO:int = 7; 
		public static const SETEMBRO:int = 8; 
		public static const OUTUBRO:int = 9; 
		public static const NOVEMBRO:int = 10; 
		public static const DEZEMBRO:int = 11; 
		
		public static const SEG:int = 1000;
		public static const MIN:int = SEG * 60;
		public static const HOUR:int = MIN * 60;
		public static const DAY:int = HOUR * 24;
		
		public static function plusDays(plus:int = 0, dataFim:Date = null):Date
		{
			if(dataFim == null)
			{
				dataFim = new Date();
			}
			dataFim.setTime(dataFim.getTime() + (DAY * plus));
			return dataFim;
		}
		
		public static function minusDays(minus:int = 0, dataFim:Date = null):Date
		{
			if(dataFim == null)
			{
				dataFim = new Date();
			}
			dataFim.setTime(dataFim.getTime() - (DAY * minus));
			return dataFim;
		}
		
		public static function lastMonth(minus:int = 1, allMonthDays:int = 1 ):Array
		{
			var meses:Array = new Array();
			var dataAtual:Date = new Date();
			if(minus <=  ConstantesUtil.ZERO)
			{
				return meses;				
			}
			if(allMonthDays <=  ConstantesUtil.UM || allMonthDays > 28)
			{
				allMonthDays = 1;				
			}
			
			var anoAtual:Number = dataAtual.fullYearUTC;
			var mesAtual:Number = dataAtual.monthUTC;
			
			for (var index:int = ConstantesUtil.ZERO; minus > ConstantesUtil.ZERO ; minus--)
			{
				var newDate:Date = new Date(anoAtual, mesAtual--, allMonthDays);
				
				if(mesAtual < JANEIRO)
				{
					mesAtual = DEZEMBRO;
					anoAtual--;
				}
				meses[index++] = newDate;
			}
			return meses;
		}
		
		public static function minusMonth(minus:int = 1):Date
		{
			var dataAtual:Date = new Date();
			if(minus <=  ConstantesUtil.ZERO)
			{
				return dataAtual;				
			}
			
			var anoAtual:Number = dataAtual.fullYearUTC;
			var mesAtual:Number = dataAtual.monthUTC;
			var newDate:Date = new Date();
			for (; minus > ConstantesUtil.ZERO ; minus--)
			{
				newDate = new Date(anoAtual, mesAtual--, 1);
				
				if(mesAtual < JANEIRO)
				{
					mesAtual = DEZEMBRO;
					anoAtual--;
				}
			}
			return newDate;
		}
		
		
		public static function minusYear(minus:int = 1, dataAtual:Date = null):Date
		{
			if(dataAtual == null)
			{
				dataAtual = new Date();
			}
			
			if(minus <=  ConstantesUtil.ZERO)
			{
				return dataAtual;				
			}
			
			var mesAtual:Number = dataAtual.monthUTC;
			var diaAtual:Number = dataAtual.day;
			
			return new Date(dataAtual.getFullYear() - minus, mesAtual, diaAtual);;
		}
		
		public static function calcularIdadeCompleta(niver:Date, hoje:Date = null):Object
		{
			if (!hoje)
			{
				hoje = new Date();
			}
			
			var idade:Object;
			
			var dias:Number;
			var meses:Number;
			var anos:Number;
			
			// Já fez aniversário
			if (hoje.getMonth() > niver.getMonth())
			{
				anos = hoje.getFullYear() - niver.getFullYear();
				
				if (hoje.getDate() < niver.getDate())
				{
					/* remove 1 mês, porque no mês corrente ainda
					não ultrapassou o dia da data de aniversário */
					meses = hoje.getMonth() - niver.getMonth() - 1;
					
					// a soma dos dias ultrapassados após o dia da data de aniversário
					dias = hoje.getDate() + (31 - niver.getDate());
				}
				else
				{
					meses = hoje.getMonth() - niver.getMonth();
					dias = hoje.getDate() - niver.getDate();
				}
			}
			else if (hoje.getMonth() < niver.getMonth())
			{
				// remove 1 ano porque ainda não fez aniversário
				anos = hoje.getFullYear() - niver.getFullYear() - 1;
				
				if (hoje.getDate() < niver.getDate())
				{
					meses = hoje.getMonth();
					
					// a soma dos dias ultrapassados após o dia da data de aniversário
					dias = hoje.getDate() + (31 - niver.getDate());
				}
				else
				{
					// adiciona 1 mês porque já passou do dia da data de aniversário
					meses = hoje.getMonth() + 1;
					dias = hoje.getDate() - niver.getDate();
				}
			}
			else if (hoje.getMonth() == niver.getMonth())
			{
				if (hoje.getDate() < niver.getDate())
				{
					// remove 1 ano porque ainda não fez aniversário
					anos = hoje.getFullYear() - niver.getFullYear() - 1;
					meses = hoje.getMonth() + 1;
					// a soma dos dias ultrapassados após o dia da data de aniversário
					dias = hoje.getDate() + (31 - niver.getDate());
				}
				else
				{
					anos = hoje.getFullYear() - niver.getFullYear();
					meses = hoje.getMonth() - niver.getMonth();
					dias = hoje.getDate() - niver.getDate();
				}
			}
			idade = {anos:anos, meses:meses, dias:dias};
			return idade;
		}
		
		public static function contarDias(date:Date):ArrayCollection
		{
			var arrayDias:ArrayCollection = new ArrayCollection(); 
			
			for(var i:Number = 1; i <= date.getDate(); i++)
			{
				arrayDias.addItem(i);	
			}
			return arrayDias;
		}
		
		public static function contarAnos(date:Date):ArrayCollection
		{
			var arrayAnos:ArrayCollection = new ArrayCollection(); 
			
			for(var i:Number = date.getFullYear() - 5; i <= date.getFullYear(); i++)
			{
				arrayAnos.addItem(i);	
			}
			return arrayAnos;
		}
	}
}