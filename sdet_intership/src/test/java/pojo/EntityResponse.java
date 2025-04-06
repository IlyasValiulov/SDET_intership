package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntityResponse {
    @JsonProperty("entity")
    private List<Entity> entities;
    private int page;
    private int perPage;
}
