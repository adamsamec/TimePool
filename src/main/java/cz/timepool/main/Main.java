//
//package cz.timepool.main;
//
//import cz.timepool.bo.User;
//import cz.timepool.dto.UserDto;
//import org.springframework.transaction.annotation.Transactional;
///**
// *
// * @author Lukas Lowinger
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {
//    "/WEB-INF/context/applicationContext.xml"})
//@TransactionConfiguration(defaultRollback=true, transactionManager="txManager")
//@Transactional //extend the transactions to whole tests in order to rollback the tests
//class Main {
//    public static void main(String[] args) {
//        
//        String name = "UserName" + System.currentTimeMillis();
//        String surname = "sur" + System.currentTimeMillis();
//        String pass = "passwd" + System.currentTimeMillis();
//        String email = "email" + System.currentTimeMillis();
//        String desctiption = "desc" + System.currentTimeMillis();
//        
//        Long id = userService.addUser(name,surname,email,pass,desctiption);
//        UserDto userDto = userService.getUserById(id);
//    }
//}
