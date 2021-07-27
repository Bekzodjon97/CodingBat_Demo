package uz.pdp.CodingBatDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.CodingBatDemo.entity.Language;
import uz.pdp.CodingBatDemo.entity.User;
import uz.pdp.CodingBatDemo.payload.ApiResponse;
import uz.pdp.CodingBatDemo.payload.LanguageDto;
import uz.pdp.CodingBatDemo.payload.UserDto;
import uz.pdp.CodingBatDemo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public ResponseEntity<List<User>> get(){
        List<User> userList = userRepository.findAll();
        return ResponseEntity.ok(userList);
    }

    public ResponseEntity<User> getById(String email){
        Optional<User> optionalUser = userRepository.findById(email);
        return optionalUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.ok(new User()));

    }

    public ApiResponse create(UserDto userDto){
        boolean existsByEmail = userRepository.existsByEmail(userDto.getEmail());
        if (existsByEmail)
            return new ApiResponse("Bunday email mavjud", false);
        User user=new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
        return new ApiResponse("User saqlandi", true);

    }

    public ApiResponse delete(String email){
        Optional<User> optionalUser = userRepository.findById(email);
        if (!optionalUser.isPresent())
            return new ApiResponse("Bunday User mavjud emas", false);
        userRepository.deleteById(email);
        return new ApiResponse("User o'chirildi", true);
    }

    public ApiResponse edit(String email, UserDto userDto){
        Optional<User> optionalUser = userRepository.findById(email);
        if (!optionalUser.isPresent())
            return new ApiResponse("Bunday User topilmadi",false);
        boolean existsByEmail = userRepository.existsByEmail(userDto.getEmail());
        if (existsByEmail)
            return new ApiResponse("Bunday email mavjud", false);
        User user=optionalUser.get();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
        return new ApiResponse("User o'zgartirildi", true);
    }
}
