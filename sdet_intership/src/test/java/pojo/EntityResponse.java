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
public class EntityResponse {
    @JsonProperty("entity")
    private List<Entity> entities;

    private int page;

    private int perPage;

    @JsonCreator
    public EntityResponse(
            @JsonProperty("entity") List<Entity> entities,
            @JsonProperty("page") int page,
            @JsonProperty("perPage") int perPage) {
        this.entities = entities;
        this.page = page;
        this.perPage = perPage;
    }

    public EntityResponse() {}
}
