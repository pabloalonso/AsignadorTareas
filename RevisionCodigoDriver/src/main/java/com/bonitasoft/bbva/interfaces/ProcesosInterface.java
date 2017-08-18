package com.bonitasoft.bbva.interfaces;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by pablo on 18/08/2017.
 */
public interface ProcesosInterface {
    List<Object> procesosQueElUsuarioPuedeIniciar(long user_id) throws Exception;

    Object iniciarProceso(long process_definition_id, Map<String, Serializable> contract) throws Exception;
}
