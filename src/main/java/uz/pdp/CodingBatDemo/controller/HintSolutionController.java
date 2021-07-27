package uz.pdp.CodingBatDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.CodingBatDemo.entity.HintSolution;
import uz.pdp.CodingBatDemo.entity.Language;
import uz.pdp.CodingBatDemo.payload.ApiResponse;
import uz.pdp.CodingBatDemo.payload.HintSolutionDto;
import uz.pdp.CodingBatDemo.payload.LanguageDto;
import uz.pdp.CodingBatDemo.service.HintSolutionService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/hintSolution")
public class HintSolutionController {
    @Autowired
    HintSolutionService hintSolutionService;


    /**
     * get all
     * @return ResponseEntity<List<Language>>
     */
    @GetMapping
    public ResponseEntity<List<HintSolution>> get(){
        return hintSolutionService.get();
    }

    /**
     * get by id
     * @param id
     * @return ResponseEntity<Language>
     */
    @GetMapping("/{id}")
    public ResponseEntity<HintSolution> getById(@PathVariable Integer id){
        return hintSolutionService.getById(id);
    }

    /**
     * create
     * @param hintSolutionDto
     * @return ResponseEntity<ApiResponse>
     */
    @PostMapping
    public ResponseEntity<ApiResponse> create(@Valid @RequestBody HintSolutionDto hintSolutionDto){
        ApiResponse apiResponse = hintSolutionService.create(hintSolutionDto);
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
        ApiResponse apiResponse = hintSolutionService.delete(id);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(200).body(apiResponse);
        return ResponseEntity.status(409).body(apiResponse);
    }

    /**
     * update
     * @param id
     * @param hintSolutionDto
     * @return ResponseEntity<ApiResponse>
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Integer id, @Valid @RequestBody HintSolutionDto hintSolutionDto){
        ApiResponse apiResponse = hintSolutionService.edit(id, hintSolutionDto);
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
