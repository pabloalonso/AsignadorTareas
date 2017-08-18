package com.bonitasoft.bbva.impl;

import com.bonitasoft.bbva.interfaces.UsuariosInterface;
import org.bonitasoft.engine.api.IdentityAPI;

/**
 * Created by pablo on 18/08/2017.
 */
public class UsuariosImpl implements UsuariosInterface {
    private IdentityAPI identityAPI;
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

    public Object usuario(long user_id) throws Exception {
        identityAPI.getUser(user_id);

        // tambien se puede recuperar la informacion adicional
        identityAPI.getUserContactData(user_id, true); // Datos personales
        identityAPI.getUserContactData(user_id, false); // Datos profesionales
        identityAPI.getCustomUserInfo(user_id, contador, numResultados); //Datos personalizados

        return null;
    }
}
