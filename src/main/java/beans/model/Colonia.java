/*
 * componentes select item
 * separa el componen te colonia, no estara en el managebean de index, si no estara dentro de un modelo
 * esta clase sera un modelo para construr los objetos colonia, el cual las colonias se obtendran de manera dinamica desde una DB por medio de Clase ColoniaHelper
 */
package beans.model;

public class Colonia {
    
    private int coloniaId;
    private String nombreColonia;
    private int codigoPostal;

    public Colonia() { //constructor vacio concepto de Java Bean
    }

    
    
    public Colonia(int coloniaId, String nombreColonia, int codigoPostal) { //constructor con los 3 valores
        this.coloniaId = coloniaId;
        this.nombreColonia = nombreColonia;
        this.codigoPostal = codigoPostal;
    }

    public int getColoniaId() {
        return coloniaId;
    }

    public void setColoniaId(int coloniaId) {
        this.coloniaId = coloniaId;
    }

    public String getNombreColonia() {
        return nombreColonia;
    }

    public void setNombreColonia(String nombreColonia) {
        this.nombreColonia = nombreColonia;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
    
    
    
    
}
