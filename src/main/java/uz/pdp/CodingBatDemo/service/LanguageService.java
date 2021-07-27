package uz.pdp.CodingBatDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.CodingBatDemo.entity.Language;
import uz.pdp.CodingBatDemo.payload.ApiResponse;
import uz.pdp.CodingBatDemo.payload.LanguageDto;
import uz.pdp.CodingBatDemo.repository.LanguageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    @Autowired
    LanguageRepository languageRepository;

    public ResponseEntity<List<Language>> get(){
        List<Language> languageList = languageRepository.findAll();
        return ResponseEntity.ok(languageList);
    }

    public ResponseEntity<Language> getById(Integer id){
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        return optionalLanguage.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.ok(new Language()));

    }

    public ApiResponse create(LanguageDto languageDto){
        boolean existsByName = languageRepository.existsByName(languageDto.getName());
        if (existsByName)
            return new ApiResponse("Bunday til mavjud", false);
        Language language=new Language();
        language.setName(languageDto.getName());
        languageRepository.save(language);
        return new ApiResponse("Til saqlandi", true);

    }

    public ApiResponse delete(Integer id){
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (!optionalLanguage.isPresent())
            return new ApiResponse("Bunday til mavjud emas", false);
        languageRepository.deleteById(id);
        return new ApiResponse("Til o'chirildi", true);
    }

    public ApiResponse edit(Integer id, LanguageDto languageDto){
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (!optionalLanguage.isPresent())
            return new ApiResponse("Bunday til topilmadi",false);
        boolean existsByName = languageRepository.existsByName(languageDto.getName());
        if (existsByName)
            return new ApiResponse("Kiritilgan til mavjud", false);
        Language language=optionalLanguage.get();
        language.setName(languageDto.getName());
        languageRepository.save(language);
        return new ApiResponse("Til o'zgartirildi", true);
    }
}
