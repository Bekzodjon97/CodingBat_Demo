package uz.pdp.CodingBatDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.CodingBatDemo.entity.Chapter;
import uz.pdp.CodingBatDemo.entity.Example;
import uz.pdp.CodingBatDemo.payload.ApiResponse;
import uz.pdp.CodingBatDemo.payload.ChapterDto;
import uz.pdp.CodingBatDemo.payload.ExampleDto;
import uz.pdp.CodingBatDemo.service.ExampleService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/example")
public class ExampleController {
    @Autowired
    ExampleService exampleService;


    /**
     * get all
     * @return ResponseEntity<List<Language>>
     */
    @GetMapping
    public ResponseEntity<List<Example>> get(){
        return exampleService.get();
    }

    /**
     * get by id
     * @param id
     * @return ResponseEntity<Language>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Example> getById(@PathVariable Integer id){
        return exampleService.getById(id);
    }

    /**
     * create
     * @param exampleDto
     * @return ResponseEntity<ApiResponse>
     */
    @PostMapping
    public ResponseEntity<ApiResponse> create(@Valid @RequestBody ExampleDto exampleDto){
        ApiResponse apiResponse = exampleService.create(exampleDto);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(200).body(apiResponse);
        return ResponseEntity.status(409).body(apiResponse);
    }

    /**
     * delete
     * @param id
     * @return ResponseEntity<ApiResponse>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id){
        ApiResponse apiResponse = exampleService.delete(id);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(200).body(apiResponse);
        return ResponseEntity.status(409).body(apiResponse);
    }

    /**
     * update
     * @param id
     * @param exampleDto
     * @return ResponseEntity<ApiResponse>
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Integer id, @Valid @RequestBody ExampleDto exampleDto){
        ApiResponse apiResponse = exampleService.edit(id, exampleDto);
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
