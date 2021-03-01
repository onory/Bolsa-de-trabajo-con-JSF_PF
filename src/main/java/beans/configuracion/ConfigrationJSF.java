/*
 * FacesConfig - indica que se va a trabajar con JSF
 */

package beans.configuracion;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import static javax.faces.annotation.FacesConfig.Version.JSF_2_3;

@FacesConfig(
        //esto activa el CDI build-in para los beans que se van agregar
        //Activa CDI build-in beans
        version = JSF_2_3
)
@ApplicationScoped
public class ConfigrationJSF {
    
}
