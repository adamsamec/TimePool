
import cz.timepool.service.UserService;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * @author Lukas Lowinger
 */
public class UserServiceImplTest extends AbstractServiceTest {

    @Autowired
    private UserService userService;

    public UserServiceImplTest() {
        super();
    }

    @Test
    public void testAddAndRetrieveBook() {

        Long userId = addUser();

        String title = "Bob a Bobek, kralici z klobouku";

        Long bookId = bookService.addBook(title, userId);
        List<BookDto> books = bookService.getUsersBooks(userId);
        assertEquals(1, books.size());

        BookDto book = books.get(0);

        assertEquals(title, book.getTitle());
        assertEquals(userId, book.getOwner());
        assertEquals(bookId, book.getId());
    }

    @Test
    public void testAddAndRemoveBook() {
        Long userId = addUser();
        
        String title = "Bob a Bobek, kralici z klobouku";
        Long bookId = bookService.addBook(title, userId);
        assertEquals(1, bookService.getAllBooks().size());
        bookService.deleteBook(bookId);
        assertEquals(0, bookService.getAllBooks().size());
    }
    
    @Test
    public void testBookDeletedWhenUserRemoved(){
        Long userId = addUser();
        
        String title = "Bob a Bobek, kralici z klobouku";
        bookService.addBook(title, userId);
        assertEquals(1, bookService.getAllBooks().size());
        
        userService.deleteUser(userId);    
        assertEquals(0, bookService.getAllBooks().size());
        
    }

    private long addUser() {
        String name = "UserName" + System.currentTimeMillis();
        String surname = "sur" + System.currentTimeMillis();
        String pass = "passwd" + System.currentTimeMillis();
        String email = "email" + System.currentTimeMillis();
        String desctiption = "desc" + System.currentTimeMillis();

        return userService.addUser(name,surname,email,pass,desctiption);
    }
}
