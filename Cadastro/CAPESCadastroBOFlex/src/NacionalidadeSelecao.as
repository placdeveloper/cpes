package {
	import br.com.bancoob.componentes.cadastro.FormularioCadastro;
	import br.com.sicoob.capes.cadastrarNacionalidade.NacionalidadeEdicao;
	import br.com.sicoob.capes.comum.componentes.dominios.DominioSelecao;
	import br.com.sicoob.capes.comum.vo.entidades.NacionalidadeVO;
	
	import flash.net.registerClassAlias;
	
	public class NacionalidadeSelecao extends DominioSelecao {
		
		public function NacionalidadeSelecao() {
			super();
			include 'br/com/sicoob/capes/utils/RegistroClasses.as';
			registerClassAlias("br.com.sicoob.capes.negocio.entidades.Nacionalidade", NacionalidadeVO);
			botaoRelatorio.visible = false;
		}
		
		protected override function obterFormularioCadastro():FormularioCadastro {
			return new NacionalidadeEdicao();
		}
		
		protected override function obterClasseServico() : String {
			return "br.com.sicoob.capes.cadastro.fachada.NacionalidadeFachada";
		}
		
	}
}