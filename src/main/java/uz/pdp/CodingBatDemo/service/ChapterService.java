package uz.pdp.CodingBatDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.CodingBatDemo.entity.Chapter;
import uz.pdp.CodingBatDemo.entity.Language;
import uz.pdp.CodingBatDemo.payload.ApiResponse;
import uz.pdp.CodingBatDemo.payload.ChapterDto;
import uz.pdp.CodingBatDemo.payload.LanguageDto;
import uz.pdp.CodingBatDemo.repository.ChapterRepository;
import uz.pdp.CodingBatDemo.repository.LanguageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ChapterService {
    @Autowired
    ChapterRepository chapterRepository;
    @Autowired
    LanguageRepository languageRepository;

    public ResponseEntity<List<Chapter>> get(){
        List<Chapter> chapterList = chapterRepository.findAll();
        return ResponseEntity.ok(chapterList);
    }

    public ResponseEntity<Chapter> getById(Integer id){
        Optional<Chapter> optionalChapter = chapterRepository.findById(id);
        return optionalChapter.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.ok(new Chapter()));

    }

    public ApiResponse create(ChapterDto chapterDto){
        boolean existsByNameAndLanguageId = chapterRepository.existsByNameAndLanguageId(chapterDto.getName(),chapterDto.getLanguageId());
        if (existsByNameAndLanguageId)
            return new ApiResponse("Bunday chapter mavjud", false);
        Optional<Language> optionalLanguage = languageRepository.findById(chapterDto.getLanguageId());
        if (!optionalLanguage.isPresent())
            return new ApiResponse("Bunday til mavjud emas", false);
        Chapter chapter=new Chapter();
        chapter.setName(chapterDto.getName());
        chapter.setDescription(chapterDto.getDescription());
        chapter.setLanguage(optionalLanguage.get());
        chapterRepository.save(chapter);
        return new ApiResponse("Chapter saqlandi", true);

    }

    public ApiResponse delete(Integer id){
        Optional<Chapter> optionalChapter = chapterRepository.findById(id);
        if (!optionalChapter.isPresent())
            return new ApiResponse("Bunday chapter mavjud emas", false);
        chapterRepository.deleteById(id);
        return new ApiResponse("Chapter o'chirildi", true);
    }

    public ApiResponse edit(Integer id, ChapterDto chapterDto){
        Optional<Chapter> optionalChapter = chapterRepository.findById(id);
        if (!optionalChapter.isPresent())
            return new ApiResponse("Bunday chapter topilmadi",false);
        boolean existsByNameAndLanguageId = chapterRepository.existsByNameAndLanguageId(chapterDto.getName(),chapterDto.getLanguageId());
        if (existsByNameAndLanguageId)
            return new ApiResponse("Bunday chapter mavjud", false);
        Optional<Language> optionalLanguage = languageRepository.findById(chapterDto.getLanguageId());
        if (!optionalLanguage.isPresent())
            return new ApiResponse("Bunday til mavjud emas", false);
        Chapter chapter=optionalChapter.get();
        chapter.setName(chapterDto.getName());
        chapter.setDescription(chapterDto.getDescription());
        chapter.setLanguage(optionalLanguage.get());
        chapterRepository.save(chapter);
        return new ApiResponse("Chapter o'zgartirildi", true);
    }
}
