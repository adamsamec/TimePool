/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.bookstore.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Represents book in the system. Every book must have attached a responsible person
 * (physical owner of the book)
 * @author mickapa1
 */
@Entity
public class Book extends AbstractBusinessObject {
    @Column(nullable = false)
    private String title,asdf;

    @ManyToOne
    private User owner;

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
        owner.addBook(this);
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
