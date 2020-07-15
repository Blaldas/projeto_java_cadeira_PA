package recursos;

import java.io.InputStream;
import java.util.HashMap;

import javafx.scene.image.Image;
import tppaMD_interface.Constantes;




public class Imagens implements Constantes{
    
static HashMap<String, Image> tab = new HashMap<>();; 
	
    public static Image loadImage(String name) {
        try {
        	
        	Image image = tab.get(name);
        	if (image != null)
        		return image;
        	
            image = new Image(Imagens.class.getResourceAsStream("/recursos/imagens/"+name));
            tab.put(name, image);
            return image;
            
        } catch (Exception e) {
            return null;
        }
    }
}
