package com.bonitasoft.bbva.interfaces;

import org.bonitasoft.engine.bpm.comment.ArchivedComment;
import org.bonitasoft.engine.bpm.comment.Comment;
import org.bonitasoft.engine.bpm.process.ArchivedProcessInstance;
import org.bonitasoft.engine.bpm.process.ProcessInstance;

import java.util.List;

/**
 * Created by pablo on 18/08/2017.
 */
public interface CasosInterface {
    List<Object> casosAbiertosPorUsuario(long user_id) throws Exception;

    List<Object> casosPorUsuarioYDefinicionProceso(long user_id, long process_definition_id) throws Exception;

    List<Object> casosIniciadosPorUsuario(long user_id) throws Exception;

    List<Object> casosPorClaveBusquedaYUsuario(long user_id, String busqueda) throws Exception;

    List<Object> casosArchivadosPorUsuario(long user_id) throws Exception;

    List<Object> casosArchivadosPorUsuarioYDefinicionProceso(long user_id, long process_definition_id) throws Exception;

    List<Object> casosArchivadosIniciadosPorUsuario(long user_id) throws Exception;

    List<Object> comentariosPorCaso(long case_id) throws Exception;

    List<Object> comentariosArchivadosPorCaso(long case_id) throws Exception;
}
