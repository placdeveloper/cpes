package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.cadastro.persistencia.dao.TipoBeneficiarioSicorDAO;
import br.com.sicoob.capes.negocio.entidades.TipoBeneficiarioSicor;

public class TipoBeneficiarioSicorDAOImpl extends CAPESCadastroCrudDao<TipoBeneficiarioSicor> implements TipoBeneficiarioSicorDAO {
	
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_TIPO_BENEFICIARIO_SICOR_POR_FILTRO";

	public TipoBeneficiarioSicorDAOImpl() {
		super(TipoBeneficiarioSicor.class, NOME_COMANDO_PESQUISAR);
	}

}