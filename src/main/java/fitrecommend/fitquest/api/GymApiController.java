package fitrecommend.fitquest.api;

import fitrecommend.fitquest.domain.Gym;
import fitrecommend.fitquest.repository.GymJPARepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class GymApiController {

    private final GymJPARepository gymJPARepository;

    @GetMapping("/gym/all")
    public ResponseEntity<List<GymDto>> getGym(){
        List<Gym> gyms = gymJPARepository.findAll();
        List<GymDto> gymDtos = new ArrayList<>();

        for (Gym gym : gyms) {
            GymDto gymDTO = new GymDto();
            gymDTO.setId(gym.getId());
            gymDTO.setName(gym.getName());
            gymDtos.add(gymDTO);
        }
        return ResponseEntity.ok(gymDtos);
    }

    @Data
    public class GymDto{
        private String name;
        private Long id;
    }

}
