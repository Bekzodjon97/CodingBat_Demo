package uz.pdp.CodingBatDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.CodingBatDemo.entity.HintSolution;
import uz.pdp.CodingBatDemo.entity.Language;
import uz.pdp.CodingBatDemo.payload.ApiResponse;
import uz.pdp.CodingBatDemo.payload.HintSolutionDto;
import uz.pdp.CodingBatDemo.payload.LanguageDto;
import uz.pdp.CodingBatDemo.repository.HintSolutionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class HintSolutionService {
    @Autowired
    HintSolutionRepository hintSolutionRepository;

    public ResponseEntity<List<HintSolution>> get(){
        List<HintSolution> hintSolutionList = hintSolutionRepository.findAll();
        return ResponseEntity.ok(hintSolutionList);
    }

    public ResponseEntity<HintSolution> getById(Integer id){
        Optional<HintSolution> optionalHintSolution = hintSolutionRepository.findById(id);
        return optionalHintSolution.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.ok(new HintSolution()));

    }

    public ApiResponse create(HintSolutionDto hintSolutionDto){
        boolean existsByText = hintSolutionRepository.existsByText(hintSolutionDto.getText());
        if (existsByText)
            return new ApiResponse("Bunday HintSolution mavjud", false);
        HintSolution hintSolution=new HintSolution();
        hintSolution.setText(hintSolutionDto.getText());
        hintSolutionRepository.save(hintSolution);
        return new ApiResponse("HintSolution saqlandi", true);

    }

    public ApiResponse delete(Integer id){
        Optional<HintSolution> optionalHintSolution = hintSolutionRepository.findById(id);
        if (!optionalHintSolution.isPresent())
            return new ApiResponse("Bunday HintSolution mavjud emas", false);
        hintSolutionRepository.deleteById(id);
        return new ApiResponse("HintSolution o'chirildi", true);
    }

    public ApiResponse edit(Integer id, HintSolutionDto hintSolutionDto){
        Optional<HintSolution> optionalHintSolution = hintSolutionRepository.findById(id);
        if (!optionalHintSolution.isPresent())
            return new ApiResponse("Bunday HintSolution mavjud emas", false);
        boolean existsByText = hintSolutionRepository.existsByText(hintSolutionDto.getText());
        if (existsByText)
            return new ApiResponse("Bunday HintSolution mavjud", false);
        HintSolution hintSolution=optionalHintSolution.get();
        hintSolution.setText(hintSolutionDto.getText());
        hintSolutionRepository.save(hintSolution);
        return new ApiResponse("HintSolution o'zgartirildi", true);
    }
}
