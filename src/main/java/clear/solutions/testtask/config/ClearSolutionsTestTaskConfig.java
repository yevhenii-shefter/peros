package clear.solutions.testtask.config;

import clear.solutions.testtask.mapper.UserMapper;
import clear.solutions.testtask.repository.UserRepository;
import clear.solutions.testtask.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClearSolutionsTestTaskConfig {

    @Bean
    public UserRepository userRepository() {
        return new UserRepository();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public UserMapper userMapper(ModelMapper modelMapper) {
        return new UserMapper(modelMapper);
    }

    @Bean
    public UserService userService(UserMapper userMapper, UserRepository userRepository) {
        return new UserService(userMapper, userRepository);
    }

}