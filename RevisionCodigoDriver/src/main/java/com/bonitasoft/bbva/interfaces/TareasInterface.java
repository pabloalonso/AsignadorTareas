package com.bonitasoft.bbva.interfaces;

import org.bonitasoft.engine.bpm.process.ProcessInstanceNotFoundException;
import org.bonitasoft.engine.exception.*;
import org.bonitasoft.engine.platform.LoginException;
import org.bonitasoft.engine.platform.LogoutException;
import org.bonitasoft.engine.session.SessionNotFoundException;

import java.io.IOException;
import java.util.List;

/**
 * Created by pablo on 18/08/2017.
 */
public interface TareasInterface {
    List<List<Object>> tareasDisponiblesPorUsuario(long user_id) throws Exception;

    List<List<Object>> tareasAsignadasPorUsuario(long user_id) throws Exception;

    List<List<Object>> tareasRealizadasPorUsuario(long user_id) throws Exception;

    List<List<Object>> tareasAbiertasFiltradasPorNombreProcesoYUsuario(long user_id, String process_name) throws Exception;

    List<List<Object>> tareasCerradasFiltradasPorNombreProcesoYUsuario(long user_id, String process_name) throws Exception;

    List<List<Object>> tareasCerradasFiltradasPorCaso(long user_id, long case_id) throws Exception;

    List<List<Object>> tareasFiltradasPorCaso(long user_id, long case_id) throws Exception;

    List<List<Object>> tareasAbiertasFiltradasPorNombreTareaYUsuario(long user_id, String task_name) throws Exception;

    List<List<Object>> tareasCerradasFiltradasPorNombreTareaYUsuario(long user_id, String task_name) throws Exception;

    void liberarTarea(long task_id) throws Exception;

    void tomarTarea(long task_id, long userId) throws Exception;

    List<List<Object>> tareasAsignadasPorCasoYUsuario(long user_id, long case_id) throws Exception;
}
