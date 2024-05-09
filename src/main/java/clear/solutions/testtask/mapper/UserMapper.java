package clear.solutions.testtask.mapper;

import clear.solutions.testtask.entity.User;
import clear.solutions.testtask.entity.UserDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;


    public User toUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public UserDto toUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

}