import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Experience {
    private Employer employer;
    private String designation;
    private Date fromDate;
    private Date toDate;
}
