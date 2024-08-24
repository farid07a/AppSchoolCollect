/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domaine;

/**
 *
 * @author farid
 */
public class Groupe {
    
    private int id;
    private String name_group;

    public Groupe(int id, String name_group) {
        this.id = id;
        this.name_group = name_group;
    }

    public Groupe() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_group() {
        return name_group;
    }

    public void setName_group(String name_group) {
        this.name_group = name_group;
    }

    @Override
    public String toString() {
        return "Groupe{" + "id=" + id + ", name_group=" + name_group + '}';
    }
    
    
    
}
