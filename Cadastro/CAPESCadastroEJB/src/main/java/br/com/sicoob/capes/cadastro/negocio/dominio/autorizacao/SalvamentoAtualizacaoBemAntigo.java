// 22/02/2013 - 08:40:56
package br.com.sicoob.capes.cadastro.negocio.dominio.autorizacao;

import java.util.ArrayList;
import java.util.List;

import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.bemantigo.Bem;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemOnus;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemPosse;
import br.com.sicoob.capes.negocio.entidades.bemantigo.BemRegistro;
import br.com.sicoob.capes.negocio.entidades.bemantigo.Ibem;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;


/**
 * 
 * @author Marcelo.Onofre
 */
public class SalvamentoAtualizacaoBemAntigo extends SalvamentoAtualizacaoGenerico {

	/**
	 * Copia o objeto {@code aprovavel}
	 * 
	 * @param aprovavel
	 *            O objeto a ser copiado
	 * @param instAtualizacao
	 *            a instituição para preenchimento do
	 *            {@link Aprovavel#setIdInstituicaoAtualizacao(Integer)}
	 * @param classeAprovavel
	 *            A classe do objeto {@code aprovavel}
	 * @return o novo objeto
	 */
	@Override
	protected Aprovavel criarNovoRegistro(final Aprovavel aprovavel, final Instituicao instAtualizacao,
			final Class<Aprovavel> classeAprovavel) {
		
		Bem novoRegistro = (Bem)super.criarNovoRegistro(aprovavel, instAtualizacao, classeAprovavel);
		
		//atualizando a referência do novo registro criado a lsiatas existentes. Tratamento feito
		//quando se utiliza o hibernate para realização de Cascade
		List<BemOnus> bensOnus = novoRegistro.getBensOnus();
		List<BemPosse> bensPosse = novoRegistro.getBensPosse();
		List<BemRegistro> bensRegistro = novoRegistro.getBensRegistro();
		
		atualizar(bensOnus, novoRegistro, "bensOnus");
		atualizar(bensPosse, novoRegistro, "bensPosse");
		atualizar(bensRegistro, novoRegistro, "bensRegistro");
		
		return novoRegistro;
	}

	/**
	 * O método Atualizar.
	 *
	 * @param lista o valor de lista
	 * @param novoRegistro o valor de novo registro
	 * @param propriedadeSet o valor de propriedade set
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void atualizar(List<? extends Ibem> lista, Bem novoRegistro, String propriedadeSet) {
		
		List novaLista = new ArrayList();
		
		if (lista != null
				&& !lista.isEmpty()) {
			for (Ibem iBem : lista) {
				Ibem novoRegistroIbem = ReflexaoUtils.construirObjetoPorClasse(iBem.getClass());
				ReflexaoUtils.copiarPropriedades(novoRegistroIbem, iBem, "id", "idBemOnus", "idBemRegistro", "idBemPosse", "dataHoraInicio");
				novoRegistroIbem.setBem(novoRegistro);
				novaLista.add(novoRegistroIbem);
			}			
			ReflexaoUtils.setPropriedade(novoRegistro, propriedadeSet, novaLista);
		}
		
	}	

}
