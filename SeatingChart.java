import java.io.*;
import java.util.*;

public class SeatingChart {
	    public static final char AVAILABLE = '\u25FB';
	    public static final char BOOKED = '\u25FC';
	    public static final char NOT_SEAT = '-';

	    // File input
	    public static char[][] readFile(String filename) throws IOException {
	        BufferedReader br = new BufferedReader(new FileReader(filename));

	        String[] first = br.readLine().split(" ");
	        int rows = Integer.parseInt(first[0]);
	        int cols = Integer.parseInt(first[1]);

	        char[][] arr = new char[rows][cols];

	        for (int i = 0; i < rows; i++) {
	            String line = br.readLine();
	            for (int j = 0; j < cols; j++) {
	                arr[i][j] = line.charAt(j);
	            }
	        }

	        br.close();
	        return arr;
	    }

	    // File output
	    public static void writeFile(String filename, char[][] arr) throws IOException {
	        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));

	        bw.write(arr.length + " " + arr[0].length);
	        bw.newLine();

	        for (char[] row : arr) {
	            for (char c : row) {
	                bw.write(c);
	            }
	            bw.newLine();
	        }

	        bw.close();
	    }

	    // Display output
	    public static void display(char[][] arr) {
	        int rows = arr.length;
	        int cols = arr[0].length;

	        System.out.print("   ");
	        for (int i = 1; i <= cols; i++) {
	            if (i >= 10) System.out.print(i / 10);
	            else System.out.print(" ");
	        }
	        System.out.println();

	        System.out.print("   ");
	        for (int i = 1; i <= cols; i++) {
	            System.out.print(i % 10);
	        }
	        System.out.println();

	        int booked = 0, available = 0;

	        for (int i = 0; i < rows; i++) {
	            System.out.print((char) ('A' + i) + "  ");

	            for (int j = 0; j < cols; j++) {
	                System.out.print(arr[i][j]);

	                if (arr[i][j] == BOOKED) booked++;
	                if (arr[i][j] == AVAILABLE) available++;
	            }
	            System.out.println();
	        }

	        int total = booked + available;

	        System.out.println("\nBooked seats: " + booked + "/" + total);
	        System.out.println("Available seats: " + available + "/" + total);
	    }

	    // book seat
	    public static void bookSeat(char[][] arr, int r, int c) {
	        if (!valid(arr, r, c)) {
	            System.out.println("Invalid seat.");
	            return;
	        }

	        if (arr[r][c] == AVAILABLE) {
	            arr[r][c] = BOOKED;
	        } else {
	            System.out.println("Seat cannot be booked.");
	        }
	    }

	    // cancel seat
	    public static void cancelSeat(char[][] arr, int r, int c) {
	        if (!valid(arr, r, c)) {
	            System.out.println("Invalid seat.");
	            return;
	        }

	        if (arr[r][c] == BOOKED) {
	            arr[r][c] = AVAILABLE;
	        } else {
	            System.out.println("Seat is not booked.");
	        }
	    }

	    // move seat
	    public static void moveSeat(char[][] arr, int r1, int c1, int r2, int c2) {
	        if (!valid(arr, r1, c1) || !valid(arr, r2, c2)) {
	            System.out.println("Invalid seat.");
	            return;
	        }

	        if (arr[r1][c1] == BOOKED && arr[r2][c2] == AVAILABLE) {
	            arr[r1][c1] = AVAILABLE;
	            arr[r2][c2] = BOOKED;
	        } else {
	            System.out.println("Move not possible.");
	        }
	    }

	    //Adjacent
	    public static int[] findAdjacent(char[][] arr, int n) {
	        for (int i = 0; i < arr.length; i++) {
	            int count = 0;

	            for (int j = 0; j < arr[0].length; j++) {
	                if (arr[i][j] == AVAILABLE) {
	                    count++;
	                    if (count == n) {
	                        return new int[]{i, j - n + 1};
	                    }
	                } else {
	                    count = 0;
	                }
	            }
	        }
	        return null;
	    }

	    public static void bookAdjacent(char[][] arr, int r, int c, int n) {
	        for (int i = 0; i < n; i++) {
	            bookSeat(arr, r, c + i);
	        }
	    }

	    
	    public static boolean valid(char[][] arr, int r, int c) {
	        return r >= 0 && r < arr.length &&
	               c >= 0 && c < arr[0].length &&
	               arr[r][c] != NOT_SEAT;
	    }
	}