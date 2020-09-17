package training.timesheet.gateway;

import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.stereotype.Component;
import training.timesheet.CreateEmployeeCommand;
import training.timesheet.EmployeesService;

@GrpcService
@AllArgsConstructor
public class TimesheetService extends TimesheetServiceGrpc.TimesheetServiceImplBase {

    private EmployeesService employeesService;

    @Override
    public void createEmployee(CreateEmployeeRequest request, StreamObserver<CreateEmployeeResponse> responseObserver) {
        var employee = employeesService.createEmployee(
                new CreateEmployeeCommand(request.getName()));
        responseObserver.onNext(CreateEmployeeResponse.newBuilder().setId(employee.getId()).setName(employee.getName()).build());
        responseObserver.onCompleted();
    }
}
