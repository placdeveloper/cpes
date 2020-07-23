@FilterDefs({
    @FilterDef(name="periodoHistorico",
          defaultCondition = "( (timestamp(VARCHAR_FORMAT(datahorainicio, 'YYYY-MM-DD')) >= timestamp(VARCHAR_FORMAT(:data, 'YYYY-MM-DD')) and timestamp(VARCHAR_FORMAT(datahorainicio, 'YYYY-MM-DD')) <=  timestamp(VARCHAR_FORMAT(:dataFim, 'YYYY-MM-DD'))) or"
				        	+" (timestamp(VARCHAR_FORMAT(datahorafim, 'YYYY-MM-DD')) >= timestamp(VARCHAR_FORMAT(:data, 'YYYY-MM-DD'))    and timestamp(VARCHAR_FORMAT(datahorafim, 'YYYY-MM-DD')) <= timestamp(VARCHAR_FORMAT(:dataFim, 'YYYY-MM-DD'))) or "
				        	+" (timestamp(VARCHAR_FORMAT(datahorainicio, 'YYYY-MM-DD')) <= timestamp(VARCHAR_FORMAT(:data, 'YYYY-MM-DD')) and timestamp(VARCHAR_FORMAT(datahorafim, 'YYYY-MM-DD')) >= timestamp(VARCHAR_FORMAT(:dataFim, 'YYYY-MM-DD'))) ) ",  
                 parameters={@ParamDef(type="date",name="data"), @ParamDef(type="date",name="dataFim")}),
    
    @FilterDef(name="periodoHistoricoDatasIguais",
    defaultCondition = " (timestamp(VARCHAR_FORMAT(:data, 'YYYY-MM-DD')) >= timestamp(VARCHAR_FORMAT(datahorainicio, 'YYYY-MM-DD')) and timestamp(VARCHAR_FORMAT(:dataFim, 'YYYY-MM-DD')) <= timestamp(VARCHAR_FORMAT(datahorafim, 'YYYY-MM-DD'))) "
    		+ "and datahorafim is not null", 
    parameters={@ParamDef(type="date",name="data"), @ParamDef(type="date",name="dataFim")}),
    
    @FilterDef(name="dataVigente",
    defaultCondition = " timestamp(VARCHAR_FORMAT(:dataFim, 'YYYY-MM-DD') ||'-23.59.59') >= datahorainicio  and datahorainicio is not null ", 
    parameters={@ParamDef(type="date",name="data"), @ParamDef(type="date",name="dataFim")}),
    
    @FilterDef(name="dataVigenteDatasIguais",
    defaultCondition = " timestamp(VARCHAR_FORMAT(:data, 'YYYY-MM-DD') ||'-23.59.59') >= datahorainicio  and datahorainicio is not null", 
    parameters={@ParamDef(type="date",name="data"), @ParamDef(type="date",name="dataFim")}),
    
    @FilterDef(name="imprimirFichaCadastralPrevia",
          defaultCondition = " datahorainicio is null "),
    
    @FilterDef(name="imprimirFichaCadastral",
    	  defaultCondition = " datahorainicio is not null "),
    
    //Filtros Ficha Antiga
    @FilterDef(name="periodoHistoricoAntiga",
    	defaultCondition = " timestamp(VARCHAR_FORMAT(:data, 'YYYY-MM-DD') ||'-23.59.59') >= datahorainicio and :data <= datahorafim", 
    parameters={@ParamDef(type="date",name="data")}),
    @FilterDef(name="dataVigenteAntiga",
    	defaultCondition = " timestamp(VARCHAR_FORMAT(:data, 'YYYY-MM-DD') ||'-23.59.59') >= datahorainicio  and datahorainicio is not null", 
    parameters={@ParamDef(type="date",name="data")}),
    
})
package br.com.sicoob.capes.negocio.entidades.vigente;

import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;