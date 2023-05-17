package fitrecommend.fitquest.api;

import fitrecommend.fitquest.domain.Gym;
import fitrecommend.fitquest.domain.GymType;
import fitrecommend.fitquest.domain.Progress;
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
    public ResponseEntity<List<GymResponseDto>> getGym(){
        List<Gym> gyms = gymJPARepository.findAll();
        List<GymResponseDto> gymDtos = new ArrayList<>();

        for (Gym gym : gyms) {
            GymResponseDto gymDto = new GymResponseDto();
            gymDto.setId(gym.getId());
            gymDto.setName(gym.getName());
            gymDto.setGymType(gym.getType());
            gymDto.setUrl(gym.getUrl());

            gymDtos.add(gymDto);
        }
        return ResponseEntity.ok(gymDtos);
    }



    @Data
    public class GymResponseDto{
        private Long id;
        private String name;
        private GymType gymType;
        private String url;
    }

}
