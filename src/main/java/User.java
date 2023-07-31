import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class User implements Serializable {
    private Employer employer;
    private String name;
    private Date joiningDate;
    private String address;
    private int age;
    private List<Experience> experiences;
    private List<String> hobbies;
    private List<Integer> numbers;
}
