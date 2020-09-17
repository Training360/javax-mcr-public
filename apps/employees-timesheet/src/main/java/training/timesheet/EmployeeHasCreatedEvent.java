package training.timesheet;

import lombok.Data;

@Data
public class EmployeeHasCreatedEvent {

    private long id;

    private String name;

}
