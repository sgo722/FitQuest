package fitrecommend.fitquest.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String name;

    private String content;

    private LocalDateTime created;

    @OneToMany(mappedBy = "post")
    private List<Comment> comment= new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<Like> like = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<Image> image = new ArrayList<>();
}
