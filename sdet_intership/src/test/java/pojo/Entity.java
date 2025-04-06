package pojo;

import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Entity {
    @Builder.Default
    private Addition addition = new Addition("Дополнительные сведения", 123, 1);

    private int id;

    @Builder.Default
    private List<Integer> important_numbers = new ArrayList<>(Arrays.asList(42, 87, 15));

    @Builder.Default
    private String title = "Заголовок сущности";

    @Builder.Default
    private boolean verified = true;
}
