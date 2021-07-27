package uz.pdp.CodingBatDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.CodingBatDemo.entity.Chapter;
import uz.pdp.CodingBatDemo.entity.Language;
import uz.pdp.CodingBatDemo.payload.ApiResponse;
import uz.pdp.CodingBatDemo.payload.ChapterDto;
import uz.pdp.CodingBatDemo.payload.LanguageDto;
import uz.pdp.CodingBatDemo.service.ChapterService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chapter")
public class ChapterController {
    @Autowired
    ChapterService chapterService;


    /**
     * get all
     * @return ResponseEntity<List<Language>>
     */
    @GetMapping
    public ResponseEntity<List<Chapter>> get(){
        return chapterService.get();
    }

    /**
     * get by id
     * @param id
     * @return ResponseEntity<Language>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Chapter> getById(@PathVariable Integer id){
        return chapterService.getById(id);
    }

    /**
     * create
     * @param chapterDto
     * @return ResponseEntity<ApiResponse>
     */
    @PostMapping
    public ResponseEntity<ApiResponse> create(@Valid @RequestBody ChapterDto chapterDto){
        ApiResponse apiResponse = chapterService.create(chapterDto);
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
        ApiResponse apiResponse = chapterService.delete(id);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(200).body(apiResponse);
        return ResponseEntity.status(409).body(apiResponse);
    }

    /**
     * update
     * @param id
     * @param chapterDto
     * @return ResponseEntity<ApiResponse>
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Integer id, @Valid @RequestBody ChapterDto chapterDto){
        ApiResponse apiResponse = chapterService.edit(id, chapterDto);
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
