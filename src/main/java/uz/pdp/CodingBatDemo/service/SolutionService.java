package uz.pdp.CodingBatDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.CodingBatDemo.entity.Exercise;
import uz.pdp.CodingBatDemo.entity.Language;
import uz.pdp.CodingBatDemo.entity.Solution;
import uz.pdp.CodingBatDemo.entity.User;
import uz.pdp.CodingBatDemo.payload.ApiResponse;
import uz.pdp.CodingBatDemo.payload.LanguageDto;
import uz.pdp.CodingBatDemo.payload.SolutionDto;
import uz.pdp.CodingBatDemo.repository.ExerciseRepository;
import uz.pdp.CodingBatDemo.repository.SolutionRepository;
import uz.pdp.CodingBatDemo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SolutionService {
    @Autowired
    SolutionRepository solutionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ExerciseRepository exerciseRepository;

    public ResponseEntity<List<Solution>> get(){
        List<Solution> solutionList = solutionRepository.findAll();
        return ResponseEntity.ok(solutionList);
    }

    public ResponseEntity<Solution> getById(Integer id){
        Optional<Solution> optionalSolution = solutionRepository.findById(id);
        return optionalSolution.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.ok(new Solution()));

    }

    public ApiResponse create(SolutionDto solutionDto){
        Optional<User> optionalUser = userRepository.findById(solutionDto.getUserEmail());
        if (!optionalUser.isPresent())
            return new ApiResponse("Bunday user mavjud emas", false);
        Optional<Exercise> optionalExercise = exerciseRepository.findById(solutionDto.getExerciseId());
        if (!optionalExercise.isPresent())
            return new ApiResponse("Bunday exersice mavjud emas", false);
        Solution solution=new Solution();
        solution.setText(solutionDto.getText());
        solution.setExercise(optionalExercise.get());
        solution.setUser(optionalUser.get());
        solutionRepository.save(solution);
        return new ApiResponse("Solution saqlandi", true);

    }

    public ApiResponse delete(Integer id){
        Optional<Solution> optionalSolution = solutionRepository.findById(id);
        if (!optionalSolution.isPresent())
            return new ApiResponse("Bunday solution mavjud emas", false);
        solutionRepository.deleteById(id);
        return new ApiResponse("Solution o'chirildi", true);
    }

    public ApiResponse edit(Integer id, SolutionDto solutionDto){
        Optional<Solution> optionalSolution = solutionRepository.findById(id);
        if (!optionalSolution.isPresent())
            return new ApiResponse("Bunday solution mavjud emas", false);
        Optional<User> optionalUser = userRepository.findById(solutionDto.getUserEmail());
        if (!optionalUser.isPresent())
            return new ApiResponse("Bunday user mavjud emas", false);
        Optional<Exercise> optionalExercise = exerciseRepository.findById(solutionDto.getExerciseId());
        if (!optionalExercise.isPresent())
            return new ApiResponse("Bunday exersice mavjud emas", false);
        Solution solution=optionalSolution.get();
        solution.setText(solutionDto.getText());
        solution.setExercise(optionalExercise.get());
        solution.setUser(optionalUser.get());
        solutionRepository.save(solution);
        return new ApiResponse("Solution o'zgartirildi", true);
    }
}
