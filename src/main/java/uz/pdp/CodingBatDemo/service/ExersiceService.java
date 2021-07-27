package uz.pdp.CodingBatDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.CodingBatDemo.entity.Chapter;
import uz.pdp.CodingBatDemo.entity.Exercise;
import uz.pdp.CodingBatDemo.entity.Language;
import uz.pdp.CodingBatDemo.payload.ApiResponse;
import uz.pdp.CodingBatDemo.payload.ExerciseDto;
import uz.pdp.CodingBatDemo.payload.LanguageDto;
import uz.pdp.CodingBatDemo.repository.ChapterRepository;
import uz.pdp.CodingBatDemo.repository.ExerciseRepository;
import uz.pdp.CodingBatDemo.repository.HintSolutionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExersiceService {
    @Autowired
    ExerciseRepository exerciseRepository;
    @Autowired
    ChapterRepository chapterRepository;
    @Autowired
    HintSolutionRepository hintSolutionRepository;


    public ResponseEntity<List<Exercise>> get(){
        List<Exercise> exerciseList = exerciseRepository.findAll();
        return ResponseEntity.ok(exerciseList);
    }

    public ResponseEntity<Exercise> getById(Integer id){
        Optional<Exercise> optionalExercise = exerciseRepository.findById(id);
        return optionalExercise.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.ok(new Exercise()));

    }

    public ApiResponse create(ExerciseDto exerciseDto){
        Optional<Chapter> optionalChapter = chapterRepository.findById(exerciseDto.getChapterid());
        if (!optionalChapter.isPresent())
            return new ApiResponse("Bunday chapter mavjud emas", false);
        Exercise exercise=new Exercise();
        exercise.setName(exerciseDto.getName());
        exercise.setQuestion(exerciseDto.getQuestion());
        exercise.setMethod(exerciseDto.getMethod());
        exercise.setHasStar(exerciseDto.isHasStar());
        exercise.setHintSolution(hintSolutionRepository.getOne(exerciseDto.getHintSolutionId()));
        exercise.setChapter(optionalChapter.get());
        exerciseRepository.save(exercise);
        return new ApiResponse("Exercise saqlandi", true);

    }

    public ApiResponse delete(Integer id){
        Optional<Exercise> optionalExercise = exerciseRepository.findById(id);
        if (!optionalExercise.isPresent())
            return new ApiResponse("Bunday misol mavjud emas", false);
        exerciseRepository.deleteById(id);
        return new ApiResponse("Misol o'chirildi", true);
    }

    public ApiResponse edit(Integer id, ExerciseDto exerciseDto){
        Optional<Exercise> optionalExercise = exerciseRepository.findById(id);
        if (!optionalExercise.isPresent())
            return new ApiResponse("Exercise mavjud emas",false);
        Optional<Chapter> optionalChapter = chapterRepository.findById(exerciseDto.getChapterid());
        if (!optionalChapter.isPresent())
            return new ApiResponse("Bunday chapter mavjud emas", false);
        Exercise exercise=optionalExercise.get();
        exercise.setName(exerciseDto.getName());
        exercise.setQuestion(exerciseDto.getQuestion());
        exercise.setMethod(exerciseDto.getMethod());
        exercise.setHasStar(exerciseDto.isHasStar());
        exercise.setHintSolution(hintSolutionRepository.getOne(exerciseDto.getHintSolutionId()));
        exercise.setChapter(optionalChapter.get());
        exerciseRepository.save(exercise);
        return new ApiResponse("Exercise o'zgartirildi", true);
    }
}
