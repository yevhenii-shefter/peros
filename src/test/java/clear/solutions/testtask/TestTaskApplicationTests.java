package clear.solutions.testtask;

import clear.solutions.testtask.entity.UserDto;
import clear.solutions.testtask.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class TestTaskApplicationTests {

    @Test
    void contextLoads() {}

    @Autowired
    UserService userService;

    @Test
    void whenUserIsValid_thenEverythingWorks() {
        UserDto user = new UserDto();
        user.setFirstName("Tom");
        user.setLastName("Smith");
        user.setEmail("email@email");
        user.setBirthday(LocalDate.parse("2002-03-03"));
        assertEquals(String.format("User with id '%d' was successfully created", 1),
                userService.createNewUser(user));
    }

    @Test
    void whenUserIsNotValid_thenEverythingDoesntWork() {
        UserDto user = new UserDto();
        user.setFirstName("jdlskjgla");
        user.setLastName("");
        user.setEmail("email@email");
        user.setBirthday(LocalDate.parse("2008-03-03"));
        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.createNewUser(user);
        });
    }

}
