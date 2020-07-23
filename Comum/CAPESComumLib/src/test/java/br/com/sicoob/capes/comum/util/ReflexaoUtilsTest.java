/**
 *
 */
package br.com.sicoob.capes.comum.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

import javax.ejb.Stateless;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.theories.DataPoint;

import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.sicoob.capes.comum.negocio.annotation.IdEntidadeAprovavel;

/**
 * A Classe ReflexaoUtilsTest
 * 
 * @author Rodrigo.Chaves
 *
 */
public class ReflexaoUtilsTest {

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.util.ReflexaoUtils#getValorAtributo(java.lang.Object, java.lang.String)}
	 */
	@Test
	public final void testGetValorAtributoObjectString() {

		Assert.assertEquals(A.VALOR_ATRIBUTO_STRING, ReflexaoUtils.getValorAtributo(new A(), "atributoString"));
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.util.ReflexaoUtils#getValorAtributo(java.lang.Object, java.lang.reflect.Field)}
	 */
	@Test
	public final void testGetValorAtributoObjectField() {

		Field field = ReflexaoUtils.getField(A.class, "atributoString");
		Assert.assertNotNull(field);
		Assert.assertEquals(A.VALOR_ATRIBUTO_STRING, ReflexaoUtils.getValorAtributo(new A(), field));
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.util.ReflexaoUtils#setPropriedade(java.lang.Object, java.lang.String, java.lang.Object)}
	 * .
	 */
	@Test
	public final void testSetPropriedade() {

		A obj = new A();
		ReflexaoUtils.setPropriedade(obj, "atributoString", "teste");
		Assert.assertEquals("teste", obj.getAtributoString());
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.util.ReflexaoUtils#getMetodoGet(java.lang.Class, java.lang.String)}
	 */
	@Test
	public final void testGetMetodoGet() {

		Assert.assertNotNull(ReflexaoUtils.getMetodoGet(A.class, "atributoString"));
		try {
			Assert.assertNull(ReflexaoUtils.getMetodoGet(A.class, "atributoInacessivel"));
		} catch (BancoobRuntimeException e) {
			Assert.assertNotNull(e);
		}
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.util.ReflexaoUtils#getMetodoSet(java.lang.Class, java.lang.String, java.lang.Class)}
	 */
	@Test
	public final void testGetMetodoSet() {

		Assert.assertNotNull(ReflexaoUtils.getMetodoSet(A.class, "atributoString", String.class));
		try {
			Assert.assertNull(ReflexaoUtils.getMetodoSet(A.class, "atributoInacessivel", double.class));
		} catch (BancoobRuntimeException e) {
			Assert.assertNotNull(e);
		}
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.util.ReflexaoUtils#instanciar(java.lang.Class)}
	 * .
	 */
	@Test
	public final void testInstanciar() {

		Assert.assertNotNull(ReflexaoUtils.instanciar(A.class));
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.util.ReflexaoUtils#copiarPropriedade(java.lang.Object, java.lang.Object, java.lang.String)}
	 * .
	 */
	@Test
	public final void testCopiarPropriedade() {

		A source = new A();
		source.setAtributoString("teste");
		A dest = new A();
		ReflexaoUtils.copiarPropriedade(dest, source, "atributoString");
		Assert.assertEquals(source.getAtributoString(), dest.getAtributoString());
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.util.ReflexaoUtils#isPropriedadeIgual(java.lang.Object, java.lang.Object, java.lang.String)}
	 * .
	 */
	@Test
	public final void testIsPropriedadeIgual() {

		A a = new A();
		a.setAtributoString("teste");
		A b = new A();
		Assert.assertTrue(ReflexaoUtils.isPropriedadeIgual(a, b, "atributoInt"));
		Assert.assertFalse(ReflexaoUtils.isPropriedadeIgual(a, b, "atributoString"));
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.util.ReflexaoUtils#getField(java.lang.Class, java.lang.String)}
	 * .
	 */
	@Test
	public final void testGetField() {

		Assert.assertNotNull(ReflexaoUtils.getField(A.class, "atributoString"));
		try {
			ReflexaoUtils.getField(A.class, "teste");
			Assert.fail("Era esperada uma excecao");
		} catch (BancoobRuntimeException e) {
			Assert.assertNotNull(e);
		}
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.util.ReflexaoUtils#getFields(java.lang.Class)}
	 * .
	 */
	@Test
	public final void testGetFields() {

		Assert.assertEquals(4, ReflexaoUtils.getFields(A.class).size());
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.util.ReflexaoUtils#possuiMetodoSet(java.lang.Class, java.lang.String, java.lang.Class)}
	 * .
	 */
	@Test
	public final void testPossuiMetodoSet() {

		Assert.assertTrue(ReflexaoUtils.possuiMetodoSet(A.class, "atributoString", String.class));
		Assert.assertTrue(ReflexaoUtils.possuiMetodoSet(A.class, "atributoBoolean", boolean.class));
		Assert.assertFalse(ReflexaoUtils.possuiMetodoSet(A.class, "atributoInacessivel", double.class));
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.util.ReflexaoUtils#possuiMetodoGet(java.lang.Class, java.lang.String)}
	 * .
	 */
	@Test
	public final void testPossuiMetodoGetClassString() {

		Assert.assertTrue(ReflexaoUtils.possuiMetodoGet(A.class, "atributoString"));
		Assert.assertTrue(ReflexaoUtils.possuiMetodoGet(A.class, "atributoBoolean"));
		Assert.assertFalse(ReflexaoUtils.possuiMetodoGet(A.class, "atributoInacessivel"));
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.util.ReflexaoUtils#isTipoBasico(java.lang.reflect.Field)}
	 * .
	 *
	 * @throws Throwable
	 * @throws SecurityException
	 */
	@Test
	public final void testIsTipoBasico() throws Exception {

		Field field = ReflexaoUtils.getField(A.class, "atributoString");
		Assert.assertTrue(ReflexaoUtils.isTipoBasico(field));
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.util.ReflexaoUtils#getPacote(java.lang.Class)}
	 */
	@Test
	public final void testGetPacote() {

		Assert.assertEquals(this.getClass().getPackage(), ReflexaoUtils.getPacote(A.class));
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.util.ReflexaoUtils#isFinal(java.lang.reflect.Field)}
	 */
	@Test
	public final void testIsFinal() {

		Assert.assertTrue(ReflexaoUtils.isFinal(ReflexaoUtils.getField(A.class, "VALOR_ATRIBUTO_STRING")));
		Assert.assertFalse(ReflexaoUtils.isFinal(ReflexaoUtils.getField(A.class, "atributoString")));
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.util.ReflexaoUtils#isStatic(java.lang.reflect.Field)}
	 */
	@Test
	public final void testIsStatic() {

		Assert.assertTrue(ReflexaoUtils.isStatic(ReflexaoUtils.getField(A.class, "VALOR_ATRIBUTO_STRING")));
		Assert.assertFalse(ReflexaoUtils.isStatic(ReflexaoUtils.getField(A.class, "atributoString")));
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.util.ReflexaoUtils#getAnnotation(java.lang.Class, java.lang.Object)}
	 */
	@Test
	public final void testGetAnnotation() {

		Assert.assertNotNull(ReflexaoUtils.getAnnotation(FixMethodOrder.class, new A()));
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.util.ReflexaoUtils#hashCode(java.lang.Object, java.lang.String[])}
	 */
	@Test
	public final void testHashCode() {

		A a = new A();
		A b = new A();
		
		Assert.assertEquals(ReflexaoUtils.hashCode(a), ReflexaoUtils.hashCode(b));
		
		b.setAtributoString("Teste");
		
		Assert.assertNotEquals(ReflexaoUtils.hashCode(a), ReflexaoUtils.hashCode(b));
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.util.ReflexaoUtils#equals(java.lang.Object, java.lang.Object, java.lang.String[])}
	 * .
	 */
	@Test
	public final void testEquals() {

		A a = new A();
		A b = new A();
		
		Assert.assertTrue(ReflexaoUtils.equals(a, b));
		Assert.assertTrue(ReflexaoUtils.equals(a, b, (String[])null));
		
		b.setAtributoString("Teste");
		
		Assert.assertFalse(ReflexaoUtils.equals(a, b));
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.util.ReflexaoUtils#extrairDados(java.lang.Object, java.lang.String)}
	 * .
	 */
	@Test
	public final void testExtrairDados() {

		Assert.assertEquals(A.VALOR_ATRIBUTO_STRING, ReflexaoUtils.extrairDados(new A(), "atributoString"));
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.util.ReflexaoUtils#construirObjetoPorClasse(java.lang.Class)}
	 */
	@Test
	public final void testConstruirObjetoPorClasseClass() {

		Assert.assertNotNull(ReflexaoUtils.construirObjetoPorClasse(A.class));
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.util.ReflexaoUtils#construirObjetoPorClasse(java.lang.Class, java.lang.String)}
	 */
	@Test
	public final void testConstruirObjetoPorClasseClassString() {

		Assert.assertNotNull(ReflexaoUtils.construirObjetoPorClasse(Object.class, A.class.getName()));
	}

	/**
	 * Test method for {@link
	 * br.com.sicoob.capes.comum.util.ReflexaoUtils#possuiMetodo(java.lang.
	 * Object, java.lang.String, java.lang.Class<?>[])}
	 */
	@Test
	public final void testPossuiMetodo() {

		Assert.assertFalse(ReflexaoUtils.possuiMetodo(new A(), "equals", null));
		Assert.assertTrue(ReflexaoUtils.possuiMetodo(new A(), "equals", new Class<?>[] { Object.class }));
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.util.ReflexaoUtils#possuiMetodoGet(java.lang.Object, java.lang.String)}
	 */
	@Test
	public final void testPossuiMetodoGetObjectString() {

		Assert.assertTrue(ReflexaoUtils.possuiMetodoGet(new A(), "atributoString"));
		Assert.assertTrue(ReflexaoUtils.possuiMetodoGet(new A(), "atributoBoolean"));
		Assert.assertFalse(ReflexaoUtils.possuiMetodoGet(new A(), "atributoInacessivel"));
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.util.ReflexaoUtils#objetoPossuiMetodoGetIndexado(java.lang.Object, java.lang.String)}
	 * .
	 */
	@Test
	public final void testObjetoPossuiMetodoGetIndexado() {

		Assert.assertFalse(ReflexaoUtils.objetoPossuiMetodoGetIndexado(new A(), "atributoString"));
	}

	/**
	 * Test method for {@link
	 * br.com.sicoob.capes.comum.util.ReflexaoUtils#invocarMetodo(java.lang.
	 * Object, java.lang.String, java.lang.Class<?>[], java.lang.Object[])}.
	 */
	@Test
	public final void testInvocarMetodoObjectStringClassArrayObjectArray() {

		A a = new A();
		Assert.assertNull(ReflexaoUtils.invocarMetodo(a, "setAtributoString", new Class<?>[] { String.class },
				new Object[] { "teste" }));
		Assert.assertEquals("teste", a.getAtributoString());
		Assert.assertEquals(0, ReflexaoUtils.invocarMetodo(a, "getAtributoInt", null, null));
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.util.ReflexaoUtils#invocarMetodo(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])}
	 */
	@Test
	public final void testInvocarMetodoObjectMethodObjectArray() {

		A a = new A();
		Method atributoString = ReflexaoUtils.getMetodoSet(A.class, "atributoString", String.class);
		Method getAtributoInt = ReflexaoUtils.getMetodoGet(A.class, "atributoInt");
		try {
			Assert.assertNull(ReflexaoUtils.invocarMetodo(a, atributoString, new Object[] { "teste" }));
			Assert.assertEquals("teste", a.getAtributoString());
			Assert.assertEquals(0, ReflexaoUtils.invocarMetodo(a, getAtributoInt));
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.util.ReflexaoUtils#invocarMetodoGet(java.lang.Object, java.lang.String)}
	 * .
	 */
	@Test
	public final void testInvocarMetodoGet() {

		Assert.assertEquals(A.VALOR_ATRIBUTO_STRING, ReflexaoUtils.invocarMetodoGet(new A(), "atributoString"));
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.util.ReflexaoUtils#getNomeMetodoGet(java.lang.String)}
	 */
	@Test
	public final void testGetNomeMetodoGet() {

		Assert.assertEquals("getAtributoString", ReflexaoUtils.getNomeMetodoGet("atributoString"));
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.util.ReflexaoUtils#getAnnotationMetodoSobrescrito(java.lang.Class, java.lang.reflect.Method, java.lang.Object[])}
	 * .
	 */
	@Test
	public final void testGetAnnotationMetodoSobrescrito() {

		Method getAtributoString = ReflexaoUtils.getMetodoGet(B.class, "atributoString");
		Assert.assertNotNull(ReflexaoUtils.getAnnotationMetodoSobrescrito(Ignore.class, getAtributoString, new Object[]{}));

		Method getAtributoInt = ReflexaoUtils.getMetodoGet(B.class, "atributoInt");
		Assert.assertNotNull(ReflexaoUtils.getAnnotationMetodoSobrescrito(Ignore.class, getAtributoInt, new Object[]{}));

		Method isAtributoBoolean = ReflexaoUtils.getMetodoGet(B.class, "atributoBoolean");
		Assert.assertNull(ReflexaoUtils.getAnnotationMetodoSobrescrito(Ignore.class, isAtributoBoolean, new Object[]{}));
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.util.ReflexaoUtils#getAnnotationsSuperClasses(java.lang.Class, java.lang.Class)}
	 */
	@Test
	public final void testGetAnnotationsSuperClasses() {

		Assert.assertEquals(1, ReflexaoUtils.getAnnotationsSuperClasses(FixMethodOrder.class, B.class).size());
		Assert.assertEquals(1, ReflexaoUtils.getAnnotationsSuperClasses(Ignore.class, B.class).size());
		Assert.assertEquals(0, ReflexaoUtils.getAnnotationsSuperClasses(Ignore.class, A.class).size());
		Assert.assertEquals(0, ReflexaoUtils.getAnnotationsSuperClasses(Stateless.class, B.class).size());
		Assert.assertEquals(2, ReflexaoUtils.getAnnotationsSuperClasses(DataPoint.class, B.class).size());
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.util.ReflexaoUtils#obterMetodoPorParametros(java.lang.String, java.lang.Object[], java.lang.Class)}
	 * .
	 */
	@Test
	public final void testObterMetodoPorParametros() {

		Assert.assertNotNull(ReflexaoUtils.obterMetodoPorParametros("fazAlgumaCoisa", new Object[] { (int) 5 }, A.class));
		Assert.assertNull(ReflexaoUtils.obterMetodoPorParametros("fazAlgumaCoisa", new Object[] { true }, A.class));
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.util.ReflexaoUtils#obterIndexParametroMetodoSobrescrito(java.lang.reflect.Method, java.lang.Object[], java.lang.Class)}
	 */
	@Test
	public final void testObterIndexParametroMetodoSobrescrito() {

		Object[] actualParameters = new Object[] { "teste", 1 };
		Method method = ReflexaoUtils.obterMetodoPorParametros("fazAlgumaCoisa", actualParameters, A.class);
		Assert.assertEquals(Integer.valueOf(1),
				ReflexaoUtils.obterIndexParametroMetodoSobrescrito(method, actualParameters, IdEntidadeAprovavel.class));

		method = ReflexaoUtils.obterMetodoPorParametros("fazAlgumaCoisa", actualParameters, B.class);
		Assert.assertEquals(Integer.valueOf(1),
				ReflexaoUtils.obterIndexParametroMetodoSobrescrito(method, actualParameters, IdEntidadeAprovavel.class));
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.util.ReflexaoUtils#obterIndexParametroAnotado(java.lang.reflect.Method, java.lang.Class)}
	 */
	@Test
	public final void testObterIndexParametroAnotado() {

		Method method = ReflexaoUtils.obterMetodoPorParametros("fazAlgumaCoisa", new Object[] { "teste", 1 }, A.class);
		Assert.assertEquals(Integer.valueOf(1),
				ReflexaoUtils.obterIndexParametroAnotado(method, IdEntidadeAprovavel.class));

		method = ReflexaoUtils.obterMetodoPorParametros("fazAlgumaCoisa", new Object[] { "teste", 1 }, B.class);
		Assert.assertNull(ReflexaoUtils.obterIndexParametroAnotado(method, IdEntidadeAprovavel.class));
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.util.ReflexaoUtils#getPropertyDescriptors(java.lang.Class)}
	 * .
	 */
	@Test
	public final void testGetPropertyDescriptors() {

		PropertyDescriptor[] descriptors = ReflexaoUtils.getPropertyDescriptors(A.class);
		Assert.assertNotNull(descriptors);
		Assert.assertEquals(5, descriptors.length);
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.util.ReflexaoUtils#getPropertyDescriptor(java.lang.Object, java.lang.String)}
	 * .
	 */
	@Test
	public final void testGetPropertyDescriptor() {

		PropertyDescriptor descriptor = ReflexaoUtils.getPropertyDescriptor(new A(), "atributoString");
		Assert.assertNotNull(descriptor);
		Assert.assertEquals("atributoString", descriptor.getName());
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.util.ReflexaoUtils#copiarPropriedades(java.lang.Object, java.lang.Object, java.lang.String[])}
	 * .
	 */
	@Test
	public final void testCopiarPropriedades() {

		A source = new A();
		source.setAtributoString("teste");
		source.setAtributoInt(1);
		source.setAtributoBoolean(true);

		A dest = new A();
		ReflexaoUtils.copiarPropriedades(dest, source);

		Assert.assertEquals(source, dest);
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.util.ReflexaoUtils#obterTipoGenerico(java.lang.Class, int)}
	 * .
	 */
	@Test
	public final void testObterParametroGenericoClassInt() {

		Assert.assertNotNull(ReflexaoUtils.obterParametroGenerico(D.class, 0));
		try {
			ReflexaoUtils.obterParametroGenerico(A.class, 0);
			Assert.fail("Era esperada uma exceção por \"" + A.class.getName() + "\" não ser do tipo \""
					+ ParameterizedType.class.getName() + "\"");
		} catch(ClassCastException e) {
			Assert.assertNotNull(e);
		}
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.util.ReflexaoUtils#obterTipoGenerico(java.lang.Class)}
	 * .
	 */
	@Test
	public final void testObterParametroGenericoClass() {

		Assert.assertNotNull(ReflexaoUtils.obterParametroGenerico(D.class));
		try {
			ReflexaoUtils.obterParametroGenerico(A.class);
			Assert.fail("Era esperada uma exceção por \"" + A.class.getName() + "\" não ser do tipo \""
					+ ParameterizedType.class.getName() + "\"");
		} catch(ClassCastException e) {
			Assert.assertNotNull(e);
		}
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.util.ReflexaoUtils#getValorAtributoAninhado(java.lang.Object, java.lang.String)}
	 * .
	 */
	@Test
	public final void testGetValorAtributoAninhado() {

		A a = new A();
		Assert.assertNull(ReflexaoUtils.getValorAtributoAninhado(a, "atributoAninhado.atributoGenerico"));
		
		a.setAtributoAninhado(new D());
		Assert.assertNotNull(ReflexaoUtils.getValorAtributoAninhado(a, "atributoAninhado.atributoGenerico"));
	}

	/**
	 * A Classe A.
	 */
	@SuppressWarnings("unused")
	@FixMethodOrder
	@DataPoint
	public static class A {
		
		/** A constante VALOR_ATRIBUTO_STRING. */
		protected static final String VALOR_ATRIBUTO_STRING = "valor atributo String";
		
		/** O atributo atributoString. */
		private String atributoString = VALOR_ATRIBUTO_STRING;
		
		/** O atributo atributoInt. */
		private int atributoInt;
		
		/** O atributo atributoBoolean. */
		private boolean atributoBoolean;
		
		/** O atributo atributoInacessivel. */
		private double atributoInacessivel;
		
		/** O atributo atributoAninhado. */
		private D atributoAninhado;
		
		/**
		 * Recupera o valor de atributoString.
		 *
		 * @return o valor de atributoString
		 */
		public String getAtributoString() {
			
			return this.atributoString;
		}
		
		/**
		 * Define o valor de atributoString.
		 *
		 * @param atributoString o novo valor de atributoString
		 */
		public void setAtributoString(String atributoString) {
			
			this.atributoString = atributoString;
		}
		
		/**
		 * Recupera o valor de atributoInt.
		 *
		 * @return o valor de atributoInt
		 */
		@Ignore
		public int getAtributoInt() {
			
			return this.atributoInt;
		}
		
		/**
		 * Define o valor de atributoInt.
		 *
		 * @param atributoInteiro o novo valor de atributoInt
		 */
		public void setAtributoInt(int atributoInteiro) {
			
			this.atributoInt = atributoInteiro;
		}
		
		/**
		 * Verifica se eh atributo boolean.
		 *
		 * @return {@code true}, se for atributo boolean
		 */
		public boolean isAtributoBoolean() {
			
			return this.atributoBoolean;
		}
		
		/**
		 * Define o valor de atributoBoolean.
		 *
		 * @param atributoBoolean o novo valor de atributoBoolean
		 */
		public void setAtributoBoolean(boolean atributoBoolean) {
			
			this.atributoBoolean = atributoBoolean;
		}
		
		/**
		 * Recupera o valor de atributoAninhado.
		 *
		 * @return o valor de atributoAninhado
		 */
		public D getAtributoAninhado() {
			
			return atributoAninhado;
		}
		
		/**
		 * Define o valor de atributoAninhado.
		 *
		 * @param atributoAninhado o novo valor de atributoAninhado
		 */
		public void setAtributoAninhado(D atributoAninhado) {
			
			this.atributoAninhado = atributoAninhado;
		}
		
		/**
		 * O método Faz alguma coisa.
		 *
		 * @param valorInt o valor de valor int
		 */
		public void fazAlgumaCoisa(Integer valorInt) {
			
			setAtributoInt(valorInt);
		}
		
		/**
		 * O método Faz alguma coisa.
		 *
		 * @param valorString o valor de valor string
		 * @param valorInt o valor de valor int
		 */
		public void fazAlgumaCoisa(String valorString, @IdEntidadeAprovavel Integer valorInt) {
			
			setAtributoString(valorString);
			setAtributoInt(valorInt);
		}
		
		/**
	 * {@inheritDoc}
	 */
		@Override
		public int hashCode() {
			
			final int prime = 31;
			int result = 1;
			result = prime * result + (this.atributoBoolean ? 1231 : 1237);
			result = prime * result + this.atributoInt;
			result = prime * result + ((this.atributoString == null) ? 0 : this.atributoString.hashCode());
			return result;
		}
		
		/**
	 * {@inheritDoc}
	 */
		@Override
		public boolean equals(Object obj) {
			
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			A other = (A) obj;
			if (this.atributoBoolean != other.atributoBoolean) {
				return false;
			}
			if (this.atributoInt != other.atributoInt) {
				return false;
			}
			if (this.atributoString == null) {
				if (other.atributoString != null) {
					return false;
				}
			} else if (!this.atributoString.equals(other.atributoString)) {
				return false;
			}
			return true;
		}
		
	}
	
	/**
	 * A Classe B.
	 */
	@Ignore
	@DataPoint
	public static class B extends A {
		
		/**
	 * {@inheritDoc}
	 */
		@Override
		@Ignore
		public String getAtributoString() {
			
			return super.getAtributoString();
		}
		
		/**
	 * {@inheritDoc}
	 */
		@Override
		public int getAtributoInt() {
			
			return super.getAtributoInt();
		}
		
		/**
	 * {@inheritDoc}
	 */
		@Override
		public boolean isAtributoBoolean() {
			
			return super.isAtributoBoolean();
		}
		
		/**
	 * {@inheritDoc}
	 */
		@Override
		public void fazAlgumaCoisa(String valorString, Integer valorInt) {
		
			super.fazAlgumaCoisa(valorString, valorInt);
		}
	}
	
	/**
	 * A Classe C.
	 *
	 * @param <N> o tipo generico
	 */
	public static class C<N extends Number> {
		
		/** O atributo atributoGenerico. */
		private N atributoGenerico;
		
		/**
		 * Recupera o valor de atributoGenerico.
		 *
		 * @return o valor de atributoGenerico
		 */
		public N getAtributoGenerico() {
			
			return this.atributoGenerico;
		}
		
		/**
		 * Define o valor de atributoGenerico.
		 *
		 * @param atributoGenerico o novo valor de atributoGenerico
		 */
		public void setAtributoGenerico(N atributoGenerico) {
			
			this.atributoGenerico = atributoGenerico;
		}
		
	}
	
	/**
	 * A Classe D.
	 */
	public static class D extends C<Long> {
		
		/**
		 * Instancia um novo D.
		 */
		public D() {
			setAtributoGenerico(0L);
		}
	}
}
