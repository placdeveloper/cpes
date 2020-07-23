package br.com.sicoob.capes.cadastrarNacionalidade {
	import br.com.bancoob.dto.RequisicaoDTO;
	import br.com.bancoob.dto.RequisicaoReqDTO;
	import br.com.sicoob.capes.comum.vo.entidades.NacionalidadeVO;
	import br.com.sicoob.capes.comum.componentes.dominios.DominioEdicao;
	
	public class NacionalidadeEdicao extends DominioEdicao {
				
		protected override function obterClasseServico() : String {
			return "br.com.sicoob.capes.cadastro.fachada.NacionalidadeFachada";
		}
		
		protected override function instanciarVO() : Object {
			return new NacionalidadeVO();
		}
		
		protected override function obterChaveMapa() : String {
			return "nacionalidade";
		}
		
		protected override function instanciarDTO() : RequisicaoDTO {
			return new RequisicaoReqDTO();
		}
		
		protected override function obterSubtitulo() : String {
			return "Tipo de Endereço";
		}
		
	}
}