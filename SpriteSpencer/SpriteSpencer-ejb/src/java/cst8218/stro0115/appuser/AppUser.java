/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.stro0115.appuser;

import java.io.Serializable;
import java.util.HashMap;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.CDI;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.security.enterprise.identitystore.PasswordHash;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**AppUser class holds the attributes and methods for an application user
 *
 * @author Spencer Stroud
 */
@Entity
@Table(name = "APPUSER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AppUser.findAll", query = "SELECT a FROM AppUser a"),
    @NamedQuery(name = "AppUser.findById", query = "SELECT a FROM AppUser a WHERE a.id = :id"),
    @NamedQuery(name = "AppUser.findByGroupname", query = "SELECT a FROM AppUser a WHERE a.groupname = :groupname"),
    @NamedQuery(name = "AppUser.findByPassword", query = "SELECT a FROM AppUser a WHERE a.password = :password"),
    @NamedQuery(name = "AppUser.findByUserid", query = "SELECT a FROM AppUser a WHERE a.userid = :userid")})
public class AppUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "GROUPNAME")
    private String groupname;
    @Size(max = 255)
    @Column(name = "PASSWORD")
    private String password;
    @Size(max = 255)
    @Column(name = "USERID")
    private String userid;

    /**
     * Constructor for the app user
     */
    public AppUser() {
    }

    /**
     * Constructor for the app user with an ID parameter
     * @param id of the created id
     */
    public AppUser(Long id) {
        this.id = id;
    }

    /**
     * Get the app user object's ID
     * @return int: UserApp ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the app user object's ID
     * @param id int: id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get UserApp object's groupName
     * @return user's group name
     */
    public String getGroupname() {
        return groupname;
    }

    /**
     * Set the app user object's groupName
     * @param groupname string: user's group name
     */
    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    /**
     * Get UserApp object's password
     * @return user's password
     */
    public String getPassword(){
        return "";   
    }
    
     /**
     * Set the app user object's password
     * @param password string: user's new password
     */
    public void setPassword(String password){
                // initialize a PasswordHash object which will generate password hashes
     Instance<? extends PasswordHash> instance = CDI.current().select(Pbkdf2PasswordHash.class);
     PasswordHash passwordHash = instance.get();
     passwordHash.initialize(new HashMap<String,String>());  // todo: are the defaults good enough?
     // now we can generate a password entry for a given password
     this.password = passwordHash.generate(password.toCharArray());

    }
    
    /**
     * Get UserApp object's userId (Username)
     * @return user's userId
     */
    public String getUserid() {
        return userid;
    }

    /**
     * Set the app user object's userId (username)
     * @param userid string: user's new userId
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * Get the hash code
     * @return return the id's hashCode if id is not null, returns 0 if id is null
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Check if the object passed is equal to the AppUser object
     * @param object object to check if it's id matches this App User's id
     * @return true if the IDs match, returns false if the object's id does not equal the App User
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AppUser)) {
            return false;
        }
        AppUser other = (AppUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @return returns the AppUser object as a string
     */
    @Override
    public String toString() {
        return "appuser.AppUser[ id=" + id + " ]";
    }
    
}
