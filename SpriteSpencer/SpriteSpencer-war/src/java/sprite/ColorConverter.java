/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprite;

import java.awt.Color;
import java.util.Scanner;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**Class Name: ColorConverter
 * @author : Spencer Stroud
 * Purpose: This class converts the color object to string and string to color object
 */
@FacesConverter(forClass=Color.class)
public class ColorConverter implements Converter {
    /**This method converts a formatted string into a color object
     * 
     * @param context, the Java Faces Context
     * @param component, the component associated with the string
     * @param value, the string to convert into object
     * @return newColor, a converted color object
     */
    @Override
    public Color getAsObject(FacesContext context,UIComponent component,String value){
    Scanner scan = new Scanner(value);
        int red = scan.nextInt();
        int green=scan.nextInt();
        int blue=scan.nextInt();
        Color newColor = new Color(red,green,blue);
        return newColor;
}
    /**This method converts a object into a formatted string
     * @param context, the Java Faces Context
     * @param component, the component associated with the object
     * @param value, the object to convert into string
     * @return formatted string of colors
     */
    @Override
    public String getAsString(FacesContext context,UIComponent component,Object value){
    Color color=(Color) value;
    return color.getRed()+" "+color.getGreen()+" "+color.getBlue();
    }
    
}
