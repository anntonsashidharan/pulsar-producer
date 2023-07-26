import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private Employer employer;
    private String name;
    private Date joiningDate;
    private String address;
    private int age;
}
