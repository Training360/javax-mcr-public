package training.timesheet.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import training.timesheet.gateway.CreateEmployeeRequest;
import training.timesheet.gateway.CreateEmployeeResponse;
import training.timesheet.gateway.TimesheetServiceGrpc;

public class ClientMain {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        TimesheetServiceGrpc.TimesheetServiceBlockingStub stub
                = TimesheetServiceGrpc.newBlockingStub(channel);

        CreateEmployeeResponse createEmployeeResponse = stub.createEmployee(CreateEmployeeRequest.newBuilder()
                .setName("John Doe 2")
                .build());

        System.out.println(createEmployeeResponse.getId());

        channel.shutdown();
    }
}
