package beans.backing;

import beans.helper.ColoniaHelper;
import beans.model.Candidato;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Named // etiqueta par ser reconocido el manage bean por JSF
@RequestScoped // determina el alcance
public class VacanteForm {

    //inyeccion del objeto candidato del manage bean anterior
    @Inject // dependencia de CDI para poder ser usado desde paquete de model   
    private Candidato candidato;
    private boolean comentarioEnviado;//por default es false

    @Inject // dependencia de CDI para poder ser usado desde paquete de model   
    private ColoniaHelper coloniaHelper;

    Logger log = LogManager.getRootLogger();

    public VacanteForm() {
        log.info("Creando el objeto VacanteForm");

    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    //redireccionamiento a otra paguina depndiendo el nombre de candidato ingresado
    public String enviar() {
        if (this.candidato.getNombre().equals("Juan")) { // valida si el nombre ingresado en index es juan para redireccionar

            if (this.candidato.getApellido().equals("Perez")) { //validar si la conbinacion con apellido existe

                String msg = "Gracias, pero Juan Perez ya trabaja con nosotros";
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
                FacesContext facesContext = FacesContext.getCurrentInstance();
                String componentId = null; // indica que el mensaje sera global no ira ligado a algun componente
                facesContext.addMessage(componentId, facesMessage);
                return "index";

            }

            log.info("Entrando al caso de exito");
            return "exito"; // exito refiere a una xhtml, no es necesario mencionar la extencion
        } else {
            log.info("Entrando al caso de fallo");
            return "fallo"; // fallo refiere a una xhtml, no es necesario mencionar la extencion
        }
    }

    public void codigoPostalListener(ValueChangeEvent valueChangeEvent) {

        FacesContext faceContext = FacesContext.getCurrentInstance();
        UIViewRoot uiViewRoot = faceContext.getViewRoot(); //busca un componente dentro del formulario

        //asignamos un ID a cada elemento que queramos acceder de JSF
        int nuevoCodigoPostal = ((Long) valueChangeEvent.getNewValue()).intValue(); // Asegura que regrese un int

        //ya noes necesario el If dado que en todos lso casos de CP corresponden a Cuidad de mexico
        UIInput coloniaIdInputText = (UIInput) uiViewRoot.findComponent("vacanteForm:coloniaId");//busca el componente colonia en el formulario

        int coloniaId = this.coloniaHelper.getColoniIdPorCP(nuevoCodigoPostal); // se obtiene el Id por medio del metodo
        coloniaIdInputText.setValue(coloniaId);
        coloniaIdInputText.setSubmittedValue(coloniaId);

        UIInput ciudadInputText = (UIInput) uiViewRoot.findComponent("vacanteForm:ciudad");//busca el componente ciudad en el formulario
        String nuevaCiudad = "Ciudad de Mexico";
        ciudadInputText.setValue(nuevaCiudad);
        ciudadInputText.setSubmittedValue(nuevaCiudad);

        faceContext.renderResponse(); // envia los cambios al formulario

    }

//metodo para ocultar o mostrar comentario
    public void ocultarComentario(ActionEvent actionEvent) { //este metodo se usa para mostrar el h:panelGroup de index

        this.comentarioEnviado = !this.comentarioEnviado; // invierte el valor que contiene comentario enviado

    }

    public boolean isComentarioEnviado() {
        return comentarioEnviado;
    }

    public void setComentarioEnviado(boolean comentarioEnviado) {
        this.comentarioEnviado = comentarioEnviado;
    }

    public ColoniaHelper getColoniaHelper() {
        return coloniaHelper;
    }

    public void setColoniaHelper(ColoniaHelper coloniaHelper) {
        this.coloniaHelper = coloniaHelper;
    }

}
