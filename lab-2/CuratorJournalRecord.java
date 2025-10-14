import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CuratorJournalRecord  {
    private String surname;
    private String name;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private Address address;

    public CuratorJournalRecord(String surname, String name, LocalDate dateOfBirth, String phoneNumber, Address address) {
        this.surname = surname;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = dateOfBirth.format(formatter);

        return "Студент: " + surname + " " + name + "\n" +
                "Дата народження: " + formattedDate + "\n" +
                "Номер телефону: " + phoneNumber + "\n" +
                "Адреса: " + address.toString() + "\n";
    }
}