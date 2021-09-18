package uz.pdp.model;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDto implements Serializable {

    @NotNull
    private String street;

    @NotNull
    private Long homeNumber;
}
