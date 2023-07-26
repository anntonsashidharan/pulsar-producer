import lombok.Data;

import java.io.Serializable;

@Data
public class Employer implements Serializable {
    private String employerName;
    private long employerNumber;
}
