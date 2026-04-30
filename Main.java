import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        char[][] seats = SeatingChart.readFile("seating1.txt");

        Menu.start(seats);

        SeatingChart.writeFile("output.txt", seats);
    }
}
