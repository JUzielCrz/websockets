package com.exercises.websockets.service

import com.exercises.websockets.websocket.ProgresoHandler
import org.springframework.stereotype.Service

@Service
class ProcesoLargoService {

    private final ProgresoHandler progresoHandler

    ProcesoLargoService(ProgresoHandler handler) {
        this.progresoHandler = handler
    }

    void iniciarProcesoLargo() {
        new Thread({
            (1..10).each { i ->
                try {
                    Thread.sleep(1000)
                    progresoHandler.enviarMensaje("Avance: ${i * 10}%")
                } catch (InterruptedException e) {
                    e.printStackTrace()
                }
            }
            progresoHandler.enviarMensaje("Proceso terminado")
        }).start()
    }
}