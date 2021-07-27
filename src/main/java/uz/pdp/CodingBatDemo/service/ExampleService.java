package uz.pdp.CodingBatDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.CodingBatDemo.entity.Chapter;
import uz.pdp.CodingBatDemo.entity.Example;
import uz.pdp.CodingBatDemo.entity.Exercise;
import uz.pdp.CodingBatDemo.entity.Language;
import uz.pdp.CodingBatDemo.payload.ApiResponse;
import uz.pdp.CodingBatDemo.payload.ChapterDto;
import uz.pdp.CodingBatDemo.payload.ExampleDto;
import uz.pdp.CodingBatDemo.payload.ExerciseDto;
import uz.pdp.CodingBatDemo.repository.ExampleRepository;
import uz.pdp.CodingBatDemo.repository.ExerciseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExampleService {
    @Autowired
    ExampleRepository exampleRepository;
    @Autowired
    ExerciseRepository exerciseRepository;

    public ResponseEntity<List<Example>> get(){
        List<Example> exampleList = exampleRepository.findAll();
        return ResponseEntity.ok(exampleList);
    }

    public ResponseEntity<Example> getById(Integer id){
        Optional<Example> optionalExample = exampleRepository.findById(id);
        return optionalExample.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.ok(new Example()));

    }

    public ApiResponse create(ExampleDto exampleDto){
        boolean existsByText = exampleRepository.existsByText(exampleDto.getText());
        if (existsByText)
            return new ApiResponse("Bunday Example mavjud", false);
        Optional<Exercise> optionalExercise = exerciseRepository.findById(exampleDto.getExerciseId());
        if (!optionalExercise.isPresent())
            return new ApiResponse("Bunday Exercise mavjud emas", false);
        Example example=new Example();
        example.setText(exampleDto.getText());
        example.setExercise(optionalExercise.get());
        exampleRepository.save(example);
        return new ApiResponse("Example saqlandi", true);

    }

    public ApiResponse delete(Integer id){
        Optional<Example> optionalExample = exampleRepository.findById(id);
        if (!optionalExample.isPresent())
            return new ApiResponse("Bunday Example mavjud emas", false);
        exampleRepository.deleteById(id);
        return new ApiResponse("Example o'chirildi", true);
    }

    public ApiResponse edit(Integer id, ExampleDto exampleDto){
        Optional<Example> optionalExample = exampleRepository.findById(id);
        if (!optionalExample.isPresent())
            return new ApiResponse("Bunday example mavjud emas", false);
        boolean existsByText = exampleRepository.existsByText(exampleDto.getText());
        if (existsByText)
            return new ApiResponse("Kiritilayotgan Example mavjud", false);
        Optional<Exercise> optionalExercise = exerciseRepository.findById(exampleDto.getExerciseId());
        if (!optionalExercise.isPresent())
            return new ApiResponse("Bunday Exercise mavjud emas", false);
        Example example=optionalExample.get();
        example.setText(exampleDto.getText());
        example.setExercise(optionalExercise.get());
        exampleRepository.save(example);
        return new ApiResponse("Exercise o'zgartirildi", true);
    }
}
