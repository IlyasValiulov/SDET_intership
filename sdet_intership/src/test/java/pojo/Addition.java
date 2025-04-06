package pojo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Addition {
    @Builder.Default
    private String additional_info = "Дополнительные сведения";

    @Builder.Default
    private int additional_number = 123;

    private int id;
}
