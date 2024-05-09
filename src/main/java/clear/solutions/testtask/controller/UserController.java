package clear.solutions.testtask.controller;

import clear.solutions.testtask.entity.UserDto;
import clear.solutions.testtask.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    @PostMapping("/create")
    public ResponseEntity<String> createNewUser(@RequestBody @Valid UserDto userDto) {
        return ResponseEntity.ok(userService.createNewUser(userDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> editUser(@PathVariable("id") Long id,
                                           @RequestBody @Valid UserDto userDto) {
        return ResponseEntity.ok(userService.editUser(id, userDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsersByBirthdayRange(@RequestParam("from") String from,
                                                                    @RequestParam("to") String to) {
        return ResponseEntity.ok(userService.getAllUsersByBirthdayRange(from, to));
    }

}