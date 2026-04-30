import java.util.*;

	public class Menu {

	    public static void start(char[][] seats) {
	        Scanner sc = new Scanner(System.in);
	        int choice;

	        do {
	            SeatingChart.display(seats);

	            System.out.println("\n1. Book a seat");
	            System.out.println("2. Cancel a reservation");
	            System.out.println("3. Find N adjacent seats");
	            System.out.println("4. Move a reservation");
	            System.out.println("5. Save and Exit");
	            System.out.print("Enter choice: ");

	            choice = sc.nextInt();

	            switch (choice) {
	                case 1:
	                    book(sc, seats);
	                    break;
	                case 2:
	                    cancel(sc, seats);
	                    break;
	                case 3:
	                    adjacent(sc, seats);
	                    break;
	                case 4:
	                    move(sc, seats);
	                    break;
	                case 5:
	                    System.out.println("Saving...");
	                    break;
	                default:
	                    System.out.println("Invalid choice.");
	            }

	        } while (choice != 5);

	        sc.close();
	    }

	    private static void book(Scanner sc, char[][] arr) {
	        System.out.print("Row: ");
	        int r = sc.next().toUpperCase().charAt(0) - 'A';

	        System.out.print("Column: ");
	        int c = sc.nextInt() - 1;

	        SeatingChart.bookSeat(arr, r, c);
	    }

	    private static void cancel(Scanner sc, char[][] arr) {
	        System.out.print("Row: ");
	        int r = sc.next().toUpperCase().charAt(0) - 'A';

	        System.out.print("Column: ");
	        int c = sc.nextInt() - 1;

	        SeatingChart.cancelSeat(arr, r, c);
	    }

	    private static void adjacent(Scanner sc, char[][] arr) {
	        System.out.print("Enter N: ");
	        int n = sc.nextInt();

	        int[] pos = SeatingChart.findAdjacent(arr, n);

	        if (pos != null) {
	            System.out.println("Found at row " + (char)(pos[0] + 'A') +
	                               " col " + (pos[1] + 1));
	            SeatingChart.bookAdjacent(arr, pos[0], pos[1], n);
	        } else {
	            System.out.println("No adjacent seats.");
	        }
	    }

	    private static void move(Scanner sc, char[][] arr) {
	        System.out.print("From row: ");
	        int r1 = sc.next().toUpperCase().charAt(0) - 'A';

	        System.out.print("From col: ");
	        int c1 = sc.nextInt() - 1;

	        System.out.print("To row: ");
	        int r2 = sc.next().toUpperCase().charAt(0) - 'A';

	        System.out.print("To col: ");
	        int c2 = sc.nextInt() - 1;

	        SeatingChart.moveSeat(arr, r1, c1, r2, c2);
	    }
	}

