package com.bonitasoft.bbva.impl;

import com.bonitasoft.bbva.interfaces.ProcesosInterface;
import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.bpm.process.ProcessInstance;
import org.bonitasoft.engine.search.SearchOptionsBuilder;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * El objetivo de esta clase es ilustrar los metodos del API Engine de Bonita BPM que deben utilizarse para obtener dicha información
 */
public class ProcesosImpl implements ProcesosInterface{

    private ProcessAPI processAPI;

    /*
        Debera utilizarse un bucle que itere para recuperar todos los resultados. Como ejemplo:

            int count = 0;
            final int maxResults = 200;
            boolean more = true;
            List<HumanTaskInstance> ht = new ArrayList<HumanTaskInstance>();
            List<HumanTaskInstance> temp_ht;
            while(more) {
                temp_ht = pAPI.getPendingHumanTaskInstances(user_id, count, maxResults, ActivityInstanceCriterion.LAST_UPDATE_DESC);
                ht.addAll(temp_ht);
                if(temp_ht.size() < maxResults){
                    more = false;
                    count = count + maxResults;
                }
            }


     */
    private int numResultados;
    private int contador;

    public List<Object> procesosQueElUsuarioPuedeIniciar(long user_id) throws Exception {
        SearchOptionsBuilder builder = new SearchOptionsBuilder(contador, numResultados);
        processAPI.searchProcessDeploymentInfosCanBeStartedBy(user_id, builder.done()).getResult();
        return null;
    }

    public Object iniciarProceso(long process_definition_id, Map<String, Serializable> contract) throws Exception {
        processAPI.startProcessWithInputs(process_definition_id, contract);
        return null;
    }
}
