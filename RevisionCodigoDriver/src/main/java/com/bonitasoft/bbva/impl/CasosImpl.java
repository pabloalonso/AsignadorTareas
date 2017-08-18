package com.bonitasoft.bbva.impl;

import com.bonitasoft.bbva.interfaces.CasosInterface;
import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.bpm.comment.ArchivedCommentsSearchDescriptor;
import org.bonitasoft.engine.bpm.comment.SearchCommentsDescriptor;
import org.bonitasoft.engine.bpm.process.ArchivedProcessInstancesSearchDescriptor;
import org.bonitasoft.engine.bpm.process.ProcessInstanceSearchDescriptor;
import org.bonitasoft.engine.search.SearchOptionsBuilder;

import java.util.List;


/**
 * El objetivo de esta clase es ilustrar los metodos del API Engine de Bonita BPM que deben utilizarse para obtener dicha información
 */
public class CasosImpl implements CasosInterface {
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


    public List<Object> casosAbiertosPorUsuario(long user_id) throws Exception {
        SearchOptionsBuilder builder = new SearchOptionsBuilder(contador, numResultados);
        processAPI.searchOpenProcessInstancesInvolvingUser(user_id, builder.done()).getResult();
        return null;
    }

    public List<Object> casosPorUsuarioYDefinicionProceso(long user_id, long process_definition_id) throws Exception {
        SearchOptionsBuilder builder = new SearchOptionsBuilder(contador, numResultados);
        builder.filter(ProcessInstanceSearchDescriptor.PROCESS_DEFINITION_ID, process_definition_id);
        processAPI.searchOpenProcessInstancesInvolvingUser(user_id, builder.done()).getResult();
        return null;
    }

    public List<Object> casosIniciadosPorUsuario(long user_id) throws Exception {
        SearchOptionsBuilder builder = new SearchOptionsBuilder(contador, numResultados);
        builder.filter(ProcessInstanceSearchDescriptor.STARTED_BY, user_id);
        processAPI.searchOpenProcessInstancesInvolvingUser(user_id, builder.done()).getResult();
        return null;
    }

    public List<Object> casosPorClaveBusquedaYUsuario(long user_id, String busqueda) throws Exception {
        SearchOptionsBuilder builder = new SearchOptionsBuilder(contador, numResultados);
        builder.searchTerm(busqueda);
        processAPI.searchOpenProcessInstancesInvolvingUser(user_id, builder.done()).getResult();
        return null;
    }

    public List<Object> casosArchivadosPorUsuario(long user_id) throws Exception {
        SearchOptionsBuilder builder = new SearchOptionsBuilder(contador, numResultados);
        processAPI.searchArchivedProcessInstancesInvolvingUser(user_id, builder.done()).getResult();
        return null;
    }

    public List<Object> casosArchivadosPorUsuarioYDefinicionProceso(long user_id, long process_definition_id) throws Exception {
        SearchOptionsBuilder builder = new SearchOptionsBuilder(contador, numResultados);
        builder.filter(ArchivedProcessInstancesSearchDescriptor.PROCESS_DEFINITION_ID, process_definition_id);
        processAPI.searchArchivedProcessInstancesInvolvingUser(user_id, builder.done()).getResult();
        return null;
    }

    public List<Object> casosArchivadosIniciadosPorUsuario(long user_id) throws Exception {
        SearchOptionsBuilder builder = new SearchOptionsBuilder(contador, numResultados);
        builder.filter(ArchivedProcessInstancesSearchDescriptor.STARTED_BY, user_id);
        processAPI.searchArchivedProcessInstancesInvolvingUser(user_id, builder.done()).getResult();
        return null;
    }

    public List<Object> comentariosPorCaso(long case_id) throws Exception {
        /*
        Metodo deprecado
        processAPI.getComments(case_id)
         */
        SearchOptionsBuilder builder = new SearchOptionsBuilder(contador, numResultados);
        builder.filter(SearchCommentsDescriptor.PROCESS_INSTANCE_ID, case_id);
        processAPI.searchComments(builder.done());
        return null;
    }

    public List<Object> comentariosArchivadosPorCaso(long case_id) throws Exception {
        SearchOptionsBuilder builder = new SearchOptionsBuilder(contador, numResultados);
        builder.filter(ArchivedCommentsSearchDescriptor.PROCESS_INSTANCE_ID, case_id);
        processAPI.searchArchivedComments(builder.done());
        return null;
    }
}
