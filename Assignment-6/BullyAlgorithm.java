import java.util.*;

public class BullyAlgorithm {
    static int num_pr;
    static int old_cord;
    static int new_cord;
    static int curr_elec;
    static int[] isActive;
    static int failed_process;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of processes:");
        num_pr = sc.nextInt();
        isActive = new int[num_pr + 1];

        for (int i = 1; i <= num_pr; i++) {
            isActive[i] = 1; // All processes active initially
        }

        old_cord = num_pr;
        isActive[old_cord] = 0; // Leader failed

        System.out.println("Enter the process that initiates the election:");
        curr_elec = sc.nextInt();

        System.out.println("Enter another process that fails (not the leader), else enter 0:");
        failed_process = sc.nextInt();
        if (failed_process != 0) isActive[failed_process] = 0;

        System.out.println("The process that failed is: " + old_cord + "\n");

        new_cord = election_process(curr_elec);

        System.out.println("Finally, process " + new_cord + " became the new leader.\n");

        for (int i = 1; i <= num_pr; i++) {
            if (isActive[i] == 1 && i != new_cord) {
                System.out.println("Process " + new_cord + " sends Coordinator(" + new_cord + ") message to process " + i);
            }
        }
    }

    public static int election_process(int curr_elec) {
        int highest = curr_elec;
        for (int i = curr_elec + 1; i <= num_pr; i++) {
            if (isActive[i] == 1) {
                System.out.println("Process " + curr_elec + " sends Election to process " + i);
                System.out.println("Process " + i + " sends OK to process " + curr_elec);
                highest = i;
            }
        }

        if (highest == curr_elec) {
            System.out.println("No higher active process. So, process " + curr_elec + " becomes the leader.");
        }

        return highest;
    }
}
