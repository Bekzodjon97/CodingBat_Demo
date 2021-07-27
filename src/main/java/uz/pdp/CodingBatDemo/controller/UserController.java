package uz.pdp.CodingBatDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.CodingBatDemo.entity.Language;
import uz.pdp.CodingBatDemo.entity.User;
import uz.pdp.CodingBatDemo.payload.ApiResponse;
import uz.pdp.CodingBatDemo.payload.LanguageDto;
import uz.pdp.CodingBatDemo.payload.UserDto;
import uz.pdp.CodingBatDemo.service.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;


    /**
     * get all
     * @return ResponseEntity<List<Language>>
     */
    @GetMapping
    public ResponseEntity<List<User>> get(){
        return userService.get();
    }

    /**
     * get by id
     * @param email
     * @return ResponseEntity<Language>
     */
    @GetMapping("/{email}")
    public ResponseEntity<User> getById(@PathVariable String email){
        return userService.getById(email);
    }

    /**
     * create
     * @param userDto
     * @return ResponseEntity<ApiResponse>
     */
    @PostMapping
    public ResponseEntity<ApiResponse> create(@Valid @RequestBody UserDto userDto){
        ApiResponse apiResponse = userService.create(userDto);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(200).body(apiResponse);
        return ResponseEntity.status(409).body(apiResponse);
    }

    /**
     * delete
     * @param email
     * @return ResponseEntity<ApiResponse>
     */
    @DeleteMapping("/{email}")
    public ResponseEntity<ApiResponse> delete(@PathVariable String email){
        ApiResponse apiResponse = userService.delete(email);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(200).body(apiResponse);
        return ResponseEntity.status(409).body(apiResponse);
    }

    /**
     * update
     * @param email
     * @param userDto
     * @return ResponseEntity<ApiResponse>
     */
    @PutMapping("/{email}")
    public ResponseEntity<ApiResponse> update(@PathVariable String email, @Valid @RequestBody UserDto userDto){
        ApiResponse apiResponse = userService.edit(email, userDto);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(200).body(apiResponse);
        return ResponseEntity.status(409).body(apiResponse);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
