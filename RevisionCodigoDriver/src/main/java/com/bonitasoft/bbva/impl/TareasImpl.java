package com.bonitasoft.bbva.impl;

import com.bonitasoft.bbva.interfaces.TareasInterface;
import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.bpm.flownode.ActivityInstanceCriterion;
import org.bonitasoft.engine.bpm.flownode.ArchivedHumanTaskInstanceSearchDescriptor;
import org.bonitasoft.engine.bpm.flownode.HumanTaskInstanceSearchDescriptor;
import org.bonitasoft.engine.search.Order;
import org.bonitasoft.engine.search.SearchOptionsBuilder;

import java.util.List;

/**
 * El objetivo de esta clase es ilustrar los metodos del API Engine de Bonita BPM que deben utilizarse para obtener dicha información
 */
public class TareasImpl implements TareasInterface {
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

    public List<List<Object>> tareasDisponiblesPorUsuario(long user_id) throws Exception {

        processAPI.getPendingHumanTaskInstances(user_id,contador,numResultados, ActivityInstanceCriterion.DEFAULT);
        return null;
    }

    public List<List<Object>> tareasAsignadasPorUsuario(long user_id) throws Exception {

        processAPI.getAssignedHumanTaskInstances(user_id,contador,numResultados, ActivityInstanceCriterion.DEFAULT);
        return null;
    }

    public List<List<Object>> tareasRealizadasPorUsuario(long user_id) throws Exception {
        SearchOptionsBuilder builder = new SearchOptionsBuilder(contador, numResultados);
        builder.filter(ArchivedHumanTaskInstanceSearchDescriptor.ASSIGNEE_ID, user_id);
        processAPI.searchArchivedHumanTasks(builder.done()).getResult();
        return null;
    }

    public List<List<Object>> tareasAbiertasFiltradasPorNombreProcesoYUsuario(long user_id, String process_name) throws Exception {
        //SOLO DEVOLVERA LA ULTIMA VERSION DESPLEGADA (SI SE REQUIERE SE PUEDEN FILTRAR POR VARIOS ID)
        Long processDefinitionId = processAPI.getLatestProcessDefinitionId(process_name);
        SearchOptionsBuilder builder = new SearchOptionsBuilder(contador, numResultados);
        //ATENCION SI HAY SUBPROCESOS
        builder.filter(HumanTaskInstanceSearchDescriptor.PROCESS_DEFINITION_ID, processDefinitionId);
        processAPI.searchPendingTasksForUser(user_id, builder.done()).getResult();
        return null;
    }

    public List<List<Object>> tareasCerradasFiltradasPorNombreProcesoYUsuario(long user_id, String process_name) throws Exception {
        //SOLO DEVOLVERA LA ULTIMA VERSION DESPLEGADA (SI SE REQUIERE SE PUEDEN FILTRAR POR VARIOS ID)
        Long processDefinitionId = processAPI.getLatestProcessDefinitionId(process_name);
        SearchOptionsBuilder builder = new SearchOptionsBuilder(contador, numResultados);
        //SOLO DEVOLVERA LAS TAREAS EN LAS QUE EL USUARIO ESTE ASIGNADO
        builder.filter(ArchivedHumanTaskInstanceSearchDescriptor.ASSIGNEE_ID, user_id);
        //ATENCION SI HAY SUBPROCESOS
        builder.and().filter(ArchivedHumanTaskInstanceSearchDescriptor.PROCESS_DEFINITION_ID, processDefinitionId);
        processAPI.searchArchivedHumanTasks(builder.done()).getResult();
        return null;
    }


    public List<List<Object>> tareasCerradasFiltradasPorCaso(long user_id, long case_id) throws Exception {
        SearchOptionsBuilder builder = new SearchOptionsBuilder(contador, numResultados);
        builder.filter(ArchivedHumanTaskInstanceSearchDescriptor.ASSIGNEE_ID, user_id);
        builder.and().filter(ArchivedHumanTaskInstanceSearchDescriptor.ROOT_PROCESS_INSTANCE_ID, case_id);
        processAPI.searchArchivedHumanTasks(builder.done()).getResult();
        return null;
    }

    public List<List<Object>> tareasFiltradasPorCaso(long user_id, long case_id) throws Exception {
        SearchOptionsBuilder builder = new SearchOptionsBuilder(contador, numResultados);
        builder.filter(HumanTaskInstanceSearchDescriptor.PROCESS_INSTANCE_ID, case_id);
        processAPI.searchPendingTasksForUser(user_id, builder.done()).getResult();
        return null;
    }

    public List<List<Object>> tareasAbiertasFiltradasPorNombreTareaYUsuario(long user_id, String task_name) throws Exception {
        SearchOptionsBuilder builder = new SearchOptionsBuilder(contador, numResultados);
        //SOLO DEVOLVERA LAS TAREAS EN LAS QUE EL USUARIO ESTE ASIGNADO
        builder.filter(HumanTaskInstanceSearchDescriptor.NAME, task_name);
        processAPI.searchPendingTasksForUser(user_id, builder.done()).getResult();
        return null;
    }

    public List<List<Object>> tareasCerradasFiltradasPorNombreTareaYUsuario(long user_id, String task_name) throws Exception {
        SearchOptionsBuilder builder = new SearchOptionsBuilder(contador, numResultados);
        //SOLO DEVOLVERA LAS TAREAS EN LAS QUE EL USUARIO ESTE ASIGNADO
        builder.filter(ArchivedHumanTaskInstanceSearchDescriptor.ASSIGNEE_ID, user_id);
        builder.and().filter(ArchivedHumanTaskInstanceSearchDescriptor.NAME, task_name);
        processAPI.searchArchivedHumanTasks(builder.done()).getResult();
        return null;
    }

    public void liberarTarea(long task_id) throws Exception {
        processAPI.releaseUserTask(task_id);
    }

    public void tomarTarea(long task_id, long userId) throws Exception {
        processAPI.assignUserTask(task_id,userId);
    }

    public List<List<Object>> tareasAsignadasPorCasoYUsuario(long user_id, long case_id) throws Exception {
        SearchOptionsBuilder builder = new SearchOptionsBuilder(contador, numResultados);
        builder.filter(HumanTaskInstanceSearchDescriptor.PROCESS_INSTANCE_ID, case_id);
        //SI SOLO SE QUIEREN LAS ASIGNADAS CREAR UNA BUSQUEDA DE CERO
        processAPI.searchAssignedAndPendingHumanTasks(user_id, builder.done()).getResult();
        return null;
    }
}
