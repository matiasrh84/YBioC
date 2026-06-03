package com.ybc.ybioq.fx.session;

import com.ybc.ybioq.fx.client.dto.OrdenDto;
import com.ybc.ybioq.fx.client.dto.PacienteDto;
import com.ybc.ybioq.fx.client.dto.PersonaDto;
import com.ybc.ybioq.fx.client.dto.UsuarioSession;
import org.springframework.stereotype.Component;

@Component
public class SessionContext {

    private UsuarioSession usuario;
    private PacienteDto pacientePreseleccionado;
    private PersonaDto personaParaAlta;
    private OrdenDto ordenParaInformar;

    public void iniciar(UsuarioSession usuario) {
        this.usuario = usuario;
    }

    public void cerrar() {
        this.usuario = null;
    }

    public UsuarioSession getUsuario() {
        return usuario;
    }

    public Integer getIdUsuario() {
        return usuario != null ? usuario.id() : null;
    }

    public boolean isAutenticado() {
        return usuario != null;
    }

    public void setPacientePreseleccionado(PacienteDto paciente) {
        this.pacientePreseleccionado = paciente;
    }

    public PacienteDto getPacientePreseleccionado() {
        return pacientePreseleccionado;
    }

    public void limpiarPacientePreseleccionado() {
        this.pacientePreseleccionado = null;
    }

    public void setPersonaParaAlta(PersonaDto persona) { this.personaParaAlta = persona; }
    public PersonaDto getPersonaParaAlta()              { return personaParaAlta; }
    public void limpiarPersonaParaAlta()                { this.personaParaAlta = null; }

    public void setOrdenParaInformar(OrdenDto orden)    { this.ordenParaInformar = orden; }
    public OrdenDto getOrdenParaInformar()              { return ordenParaInformar; }
    public void limpiarOrdenParaInformar()              { this.ordenParaInformar = null; }

    private OrdenDto ordenParaModificar;
    public void setOrdenParaModificar(OrdenDto orden)   { this.ordenParaModificar = orden; }
    public OrdenDto getOrdenParaModificar()             { return ordenParaModificar; }
    public void limpiarOrdenParaModificar()             { this.ordenParaModificar = null; }
}
