package pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Entity {
    private Addition addition;

    private int id;

    private List<Integer> important_numbers;

    private String title;

    private boolean verified;

    @JsonCreator
    public Entity(
            @JsonProperty("addition") Addition addition,
            @JsonProperty("id") int id,
            @JsonProperty("important_numbers") List<Integer> important_numbers,
            @JsonProperty("title") String title,
            @JsonProperty("verified") boolean verified) {
        this.addition = addition;
        this.id = id;
        this.important_numbers = important_numbers;
        this.title = title;
        this.verified = verified;
    }

    public Entity() {}
}
