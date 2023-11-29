package employees;

import lombok.*;

import java.nio.file.Files;
import java.nio.file.Path;

@Data
@AllArgsConstructor
public class Employee {

    @NonNull
    String name;

    int yearOfBirth;

    @SneakyThrows
    public void readNameFromFile(Path path) {
        name = Files.readString(path);
    }
}
