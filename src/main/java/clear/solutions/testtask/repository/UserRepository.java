package clear.solutions.testtask.repository;

import clear.solutions.testtask.entity.User;
import lombok.Getter;
import java.util.Map;
import java.util.TreeMap;

// class which emulate repository for acting with db
@Getter
public class UserRepository {

    private final Map<Long, User> users;


    public UserRepository() {
        users = new TreeMap<>();
    }

}