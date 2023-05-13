package fitrecommend.fitquest.domain.report;

import fitrecommend.fitquest.domain.Progress;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("G")
@Getter
@Setter
public class LocationGym extends Report{
    private LocalDateTime starttime;

    @Enumerated(EnumType.STRING)
    private Progress progress;
}
