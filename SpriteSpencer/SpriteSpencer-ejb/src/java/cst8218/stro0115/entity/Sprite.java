
package cst8218.stro0115.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.Random;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**Class Name: Sprite
 * @author : Spencer Stroud
 * Purpose: This class contains the sprite object and its related methods
 */
@Entity
@XmlRootElement
public class Sprite implements Serializable {

    private static final long serialVersionUID = 1L;

    public final static Random random = new Random();

    final static int SIZE = 10;
    final static int MAX_SPEED = 5;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column
    private Integer panelWidth;
    @Column
    private Integer panelHeight;
    @Column
    private Integer x;
    @Column
    private Integer y;
    @Column
    private Integer dx;
    @Column
    private Integer dy;
    @Column
    private Color color = Color.BLUE;

    public Sprite() {
    }

    public Sprite(int height, int width) {
        this.panelWidth = width;
        this.panelHeight = height;
        x = random.nextInt(width);
        y = random.nextInt(height);
        dx = random.nextInt(2 * MAX_SPEED) - MAX_SPEED;
        dy = random.nextInt(2 * MAX_SPEED) - MAX_SPEED;
    }

    public Sprite(int height, int width, Color color) {
        this(height, width);
        this.color = color;
    }
    /**This method returns the panel width
    * 
    * @return panelWidth
    */
    public Integer getPanelWidth() {
        return panelWidth;
    }
    /**This method sets the panel width
     * @param panelWidth, the width to set to
    */
    public void setPanelWidth(Integer panelWidth) {
        this.panelWidth = panelWidth;
    }
    /**This method returns the panel height
    * 
     * @return panelHeight
    */
    public Integer getPanelHeight() {
        return panelHeight;
    }
    /**This method sets the panel height
    * 
    * @param panelHeight, the height to set to 
    */
    public void setPanelHeight(Integer panelHeight) {
        this.panelHeight = panelHeight;
    }
    /**This method returns the x-value
     * 
    * @return x
     */
    public Integer getX() {
        return x;
    }

    /**This method sets the x value to a positive value
     * 
     * @param x the x value to set 
     */
    public void setX(Integer x) {
        x=Math.abs(x);
        this.x = x;
    }

    public Integer getY() {
        return y;
    }
    /**This method sets the y value to a positive value
    * 
    * @param y the y value to set 
    */
    public void setY(Integer y) {
        y=Math.abs(y);
        this.y = y;
    }
    /**This method returns the Dx value
     * 
     * @return dx
     */
    public Integer getDx() {
        return dx;
    }
    /**This Method sets the Dx value
     * 
     * @param dx, the dx to set to
     */
    public void setDx(Integer dx) {
        this.dx = dx;
    }
    /**This method returns the dy value
     * 
     * @return dy 
     */
    public Integer getDy() {
        return dy;
    }
    /**This method sets the dy-value
     * 
     * @param dy, the dy to set to 
     */
    public void setDy(Integer dy) {
        this.dy = dy;
    }

    public Color getColor() {
        return color;
    }
    
    

   
    public void setColor(Color color) {
        this.color=color;
    }
    
    
    /**This method overwrites the current sprite with any non-null values from the input sprite
     * 
     * @param newSprite the sprite to overwrite from
     */
    public void overwrite(Sprite newSprite){
        if(newSprite.getColor()!=null){
           this.color=newSprite.getColor();
        }
        if(newSprite.getDx()!=null){
            this.dx=newSprite.getDx();
        }
        if(newSprite.getDy()!=null){
            this.dy=newSprite.getDy();
        }
        if(newSprite.getX()!=null){
            this.x=newSprite.getX();
        }
        if(newSprite.getY()!=null){
            this.y=newSprite.getY();
        }
        if(newSprite.getPanelHeight()!=null){
         this.panelHeight=newSprite.getPanelHeight();
        }
        if(newSprite.getPanelWidth()!=null){
         this.panelWidth=newSprite.getPanelWidth();
        }
    }
  

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, SIZE, SIZE);
    }

    public void move() {

        // check for bounce and make the ball bounce if necessary
        //
        if (x < 0 && dx < 0) {
            //bounce off the left wall 
            x = 0;
            dx = -dx;
        }
        if (y < 0 && dy < 0) {
            //bounce off the top wall
            y = 0;
            dy = -dy;
        }
        if (x > panelWidth - SIZE && dx > 0) {
            //bounce off the right wall
            x = panelWidth - SIZE;
            dx = -dx;
        }
        if (y > panelHeight - SIZE && dy > 0) {
            //bounce off the bottom wall
            y = panelHeight - SIZE;
            dy = -dy;
        }

        //make the ball move
        x += dx;
        y += dy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sprite)) {
            return false;
        }
        Sprite other = (Sprite) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Sprite[ id=" + id + " ]";
    }

}
