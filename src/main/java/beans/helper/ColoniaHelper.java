/*
 * Manage bean con alcance request
 * se utilizara para crear el bean de colonia obteniendo la info de una DB
 */
package beans.helper;

import beans.model.Colonia;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

@RequestScoped // declara el alcance del manage bean
@Named // para ser reconocido como manage bean
public class ColoniaHelper {
    
    
    public List<Colonia> getColonias(){
        
        List<Colonia> colonias = new ArrayList<>();
        
        int coloniaId = 1 ;
        Colonia colonia =  new Colonia (coloniaId++, "Napoles", 3810); //agregamos el objeto colonia al Arraylist de colonias
        colonias.add(colonia);
        
       colonia =  new Colonia (coloniaId++, "Polanco", 11530); //agregamos otro objeto colonia al Arraylist de colonias
        colonias.add(colonia);
        
        colonia =  new Colonia (coloniaId++, "Del Valle Centro", 3100); //agregamos otro objeto colonia al Arraylist de colonias
        colonias.add(colonia);
        
        return colonias;
        
    }
    
    //metodo que encuentra Id de la colonias por nombre recibido
    
    public int getColoniIdPorNombre (String nombreColonia){ //recibe el nombre de colonia para localizar el Id de la colonia
        
        int coloniaId= 0;// guarda el Id del colonia despues de pasar por el array
        
        List<Colonia> colonias = this.getColonias();// el objeto trae el array de colonia
        
        for(Colonia colonia: colonias){ // iteramos el array
            
            if(colonia.getNombreColonia().equals(nombreColonia)){
                coloniaId = colonia.getColoniaId(); // asigna el valor del elemento iterado si coincide con el nombre propocionado de nombreColonia
                break;
            }
        }
        
        return coloniaId; //regresa el Id encontrado
    }
    
    //metodo que encuentra Id de la colonias por CP recibido
    
     public int getColoniIdPorCP (int CodigoPostal){ //recibe el nombre de colonia para localizar el Id de la colonia
        
        int coloniaId= 0;// guarda el Id del colonia despues de pasar por el array
        
        List<Colonia> colonias = this.getColonias();// el objeto trae el array de colonia por medio del metodo getcolonias
        
        for(Colonia colonia: colonias){ // iteramos el array
            
            if(colonia.getCodigoPostal()== CodigoPostal){ //el equals se cambia a == al estar comparando enteros y no string
                coloniaId = colonia.getColoniaId(); // asigna el valor del elemento iterado si coincide con el nombre propocionado de nombreColonia
                break;
            }
        }
        
        return coloniaId; //regresa el Id encontrado
    }
     
     //Se encarga de crear los objetosde tipo colonia en elementos de get Iteam para evitar hacerlos en index
     
     public List<SelectItem> getSelectItem(){
         
         List<SelectItem> selectItems = new ArrayList<>();
         List<Colonia> colonias = this.getColonias();
         
         for(Colonia colonia: colonias){
         
         SelectItem selectItem = new SelectItem(colonia.getColoniaId(), colonia.getNombreColonia());
         selectItems.add(selectItem);
         
     }
         return selectItems;
     } 
     
}
