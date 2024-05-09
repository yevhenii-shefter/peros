package clear.solutions.testtask.service;

import clear.solutions.testtask.entity.User;
import clear.solutions.testtask.entity.UserDto;
import clear.solutions.testtask.exception.IncorrectAgeException;
import clear.solutions.testtask.exception.IncorrectDateRangeException;
import clear.solutions.testtask.exception.UserNotFoundException;
import clear.solutions.testtask.mapper.UserMapper;
import clear.solutions.testtask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private long id = 0;

    @Value("${min.age}")
    private Integer minAge;

    // JUNit + Mockito

    public String createNewUser(UserDto userDto) {
        if (Year.now().getValue() - userDto.getBirthday().getYear() <= minAge)
            throw new IncorrectAgeException(String.format("You need to be older than %d years old", minAge));
        User user = userMapper.toUser(userDto);
        userRepository.getUsers().put(++id, user);

        return String.format("User with id '%d' was successfully created", id);
    }

    public String editUser(Long id, UserDto userDto) {
        isUserExist(id);
        User user = userMapper.toUser(userDto);
        userRepository.getUsers().put(id, user);

        return String.format("User with id '%d' was successfully updated", id);
    }

    public String deleteUser(Long id) {
        isUserExist(id);
        userRepository.getUsers().remove(id);

        return String.format("User with id '%d' was successfully deleted", id);
    }

    public List<UserDto> getAllUsersByBirthdayRange(String from, String to) {
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);
        if (!fromDate.isBefore(toDate))
            throw new IncorrectDateRangeException("'From' should be less than 'to'");

        List<UserDto> userDtos = new ArrayList<>();
        for (User user : userRepository.getUsers().values()) {
            if (fromDate.isBefore(user.getBirthday()) && user.getBirthday().isBefore(toDate))
                userDtos.add(userMapper.toUserDto(user));
        }

        return userDtos;
    }

    private void isUserExist(Long id) {
        if (!userRepository.getUsers().containsKey(id))
            throw new UserNotFoundException(String.format("User with id '%d' was not found", id));
    }

}