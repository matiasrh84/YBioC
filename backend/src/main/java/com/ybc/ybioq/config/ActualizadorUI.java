package com.ybc.ybioq.config;

import com.ybc.ybioq.model.Actualizacion;
import com.ybc.ybioq.service.ActualizadorService;
import com.ybc.ybioq.view.DialogoSiNo;

public class ActualizadorUI {

    private final ActualizadorService actualizadorService;
    private final ActualizadorDescarga actualizadorDescarga;

    public ActualizadorUI(ActualizadorService actualizadorService, ActualizadorDescarga actualizadorDescarga) {
        this.actualizadorService = actualizadorService;
        this.actualizadorDescarga = actualizadorDescarga;
    }

    public void verificarYNotificarActualizacion() {
        Actualizacion actualizacion = actualizadorService.verificarActualizacion();
        if (actualizadorService.hayNuevaVersion(actualizacion)) {
            DialogoSiNo dialogo = new DialogoSiNo(null, true);
            dialogo.lblTitulo.setText("Nueva actualización disponible");
            dialogo.lblCuerpo.setText("¿Desea descargar e instalar la nueva versión?");
            dialogo.setVisible(true);

            if (dialogo.isRespuestaPositiva()) {
                actualizadorDescarga.descargarEInstalar(actualizacion.getDownloadUrl());
            }
        }
    }
}
