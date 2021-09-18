package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto implements Serializable {

    @NotNull
    private String corpName;

    @NotNull
    private String directorName;

    @NotNull
    private Long addressId;
}
