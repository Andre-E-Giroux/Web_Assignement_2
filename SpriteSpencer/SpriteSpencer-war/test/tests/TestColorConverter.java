/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.awt.Color;
import javax.faces.component.UIColumn;
import javax.faces.context.FacesContextWrapper;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sprite.ColorConverter;

/**
 * Test class to test the  ColorConverter class
 * @author Andre
 */
public class TestColorConverter {
    
    public TestColorConverter() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**Tests the color converter method getAsObject()
     * Uses a string of RGB value to create and get a color object with the RGB values 
     */
    @Test
    public void testColorConverterObject() 
    {
        ColorConverter converter = new ColorConverter();
        
        // Color to match
        Color testingColor = new Color(1,2,3);
        
        // Color Converter to match testingColor
        //FacesContext adn UIColumn are stand ins
        Color returnedColor = (Color)converter.getAsObject(new FacesContextWrapper(){}, new UIColumn(){}, "1 2 3");
        
        assertEquals(testingColor,returnedColor);
    }
    
    /**Tests the color converter method getAsString()
     * Uses a a color object as a parameter to create and return the objects RGB values as a string 
     */
    @Test
    public void testColorConverterString()
    {
        ColorConverter converter = new ColorConverter();
        
        // Color to match
        Color testingColor = new Color(1,2,3);
        
        // Color Converter to match testingColor
        //FacesContext adn UIColumn are stand ins
        String returnedColor = converter.getAsString(new FacesContextWrapper(){}, new UIColumn(){}, testingColor);
        
        assertEquals(returnedColor, "1 2 3");
    }
    
    
    
    
    
    
}
