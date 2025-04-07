package pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Addition {
    private String additional_info;

    private int additional_number;

    private int id;

    @JsonCreator
    public Addition(
            @JsonProperty("additional_info") String additional_info,
            @JsonProperty("id") int id,
            @JsonProperty("additional_number") int additional_number) {
        this.additional_info = additional_info;
        this.id = id;
        this.additional_number = additional_number;
    }

    public Addition() {}
}
