/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.bookstore.bo;

import cz.cvut.kbss.bookstore.provider.HashProvider;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * Entity, which represents user of the system
 * @author mickapa1
 */
@Entity
@Table(name = "users") //user je SQL klicove slovo, nejde ho pouzit po pojmenovani tabulky
@Configurable(preConstruction=true)
public class User extends AbstractBusinessObject {

    @Column(nullable = false)
    private String userName; //max 255 chars
    @Column(length = 40, nullable = false) //40 je hash od SHA1
    private String salt;
    @Column(length = 40, nullable = false) //40 je hash od SHA1
    private String password;
    private int age; //vse, co neni oanotovano a neni transient, je @Column
    @OneToMany(mappedBy="owner", cascade=CascadeType.REMOVE)
    private List<Book> books;
    @Autowired
    private transient HashProvider hashProvider; //transient fields are not persisted

    public HashProvider getHashProvider() {
        return hashProvider;
    }

    public void setHashProvider(HashProvider hashProvider) {
        this.hashProvider = hashProvider;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
    /**
     * Add a book to the list of users book, if not present
     * @param book book to be added
     */
    public void addBook(Book book){
        if(this.books == null){
            books = new ArrayList<Book>();
        }
        if(!this.books.contains(book)){
            books.add(book);
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Negative age");
        }
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.salt = hashProvider.computeHash(System.nanoTime() + "");
        this.password = hashProvider.computeHash(password + salt);
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
