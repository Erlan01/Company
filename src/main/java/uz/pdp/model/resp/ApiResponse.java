package uz.pdp.model.resp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private String message = "";

    private boolean success;

    private T data;

    public ApiResponse(T data) {
        this.data = data;
        this.success = true;
        this.message = "";
    }

    public ApiResponse(T data, boolean success) {
        this.data = data;
        this.message = "";
        this.success = success;
    }

    public ApiResponse(String message) {
        this.data = null;
        this.success = false;
        this.message = message;
    }
}
