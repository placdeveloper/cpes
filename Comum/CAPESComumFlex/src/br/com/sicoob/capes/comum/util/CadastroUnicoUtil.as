package br.com.sicoob.capes.comum.util
{
	import br.com.bancoob.util.StringUtils;
	import br.com.sicoob.capes.comum.vo.entidades.EstadoCivilVO;
	
	public class CadastroUnicoUtil
	{
		
		public static const ESTADO_CIVIL_CASADO : int = 2;
		
		public static const SEXO_MASCULINO : String = 'M';
		public static const SEXO_FEMININO : String = 'F';
		public static const SEXO_NAO_INFORMADO : String = 'N';
		
		public static const NACIONALIDADE_BRASILEIRA: int = 10;
		
		public function CadastroUnicoUtil(){}


		public static function isVazio(string: String) : Boolean {
     	
     		var isVazio: Boolean = string == null;
     		if(!isVazio) {
     			var stringTrim: String =  StringUtils.trim(string);
     			isVazio = stringTrim == "" || StringUtils.isWhitespace(stringTrim);
     		}
			
			return isVazio;
 		}		
 		
 		public static function isCasado(estadoCivil: EstadoCivilVO): Boolean {
 			return estadoCivil != null && estadoCivil.codigo == ESTADO_CIVIL_CASADO;
 		}
 		
// 		OBS: Utilizar ConversaoPessoaUtil.converterVOParaPessoaCompartilhamento
// 		public static function converterProxyParaPessoaCompartilhamento(proxy : PessoaPlataformaVO = null) : PessoaCompartilhamentoVO {
//		}

		public static function calcularRendaBrutaAnual(rendaBrutaMensal: Number, dataValidadeRenda : Date): Number {
			
			var rendaAnual: Number = 0;
			
			if(dataValidadeRenda == null && !isNaN(rendaBrutaMensal)) {
				rendaAnual = rendaBrutaMensal * 12;
			}
			
			return rendaAnual;
		}

	}
}