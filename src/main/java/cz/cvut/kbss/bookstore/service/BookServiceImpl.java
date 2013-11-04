/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.bookstore.service;

import cz.cvut.kbss.bookstore.bo.Book;
import cz.cvut.kbss.bookstore.bo.User;
import cz.timepool.dto.BookDto;
import cz.cvut.kbss.bookstore.helper.HibernateTools;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author mickapa1
 */
@Component
public class BookServiceImpl extends AbstractDataAccessService implements BookService{

    @Override
    public List<BookDto> getAllBooks(){
        List<Book> books = genericDao.getAll(Book.class);
        List<BookDto> bookDtos = new ArrayList<BookDto>();
        
        for(Book b : books){
            bookDtos.add(new BookDto(b.getId(), b.getTitle(), b.getId()));
        }
        return bookDtos;        
    }
    @Override
    public List<BookDto> getUsersBooks(Long userId){
        List<Book> books = genericDao.getByProperty("owner", genericDao.loadById(userId, User.class), Book.class);
        List<BookDto> bookDtos = new ArrayList<BookDto>();
        
        for(Book b : books){
            bookDtos.add(new BookDto(b.getId(), b.getTitle(), HibernateTools.getIdentifier(b.getOwner())));
        }
        return bookDtos;        
    }

    @Override
    public Long addBook(String title, Long owner) {
        Book newBook = new Book();
        newBook.setTitle(title);
        newBook.setOwner(genericDao.loadById(owner, User.class));
        
        return genericDao.saveOrUpdate(newBook).getId();
    }


    @Override
    public void deleteBook(Long bookId) {
        genericDao.removeById(bookId, Book.class);
    }
}
