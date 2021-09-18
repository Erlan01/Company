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
public class WorkerDto implements Serializable {

    @NotNull
    private String name;

    @NotNull
    private String phoneNumber;

    @NotNull
    private Long addressId;

    @NotNull
    private Long departmentId;
}
