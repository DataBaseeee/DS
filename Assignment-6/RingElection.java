import java.util.*;

public class RingElection {
    static int num_pr;               // Number of processes
    static int[] isActive;          // Status of each process (1 = active, 0 = failed)
    static int old_cord;            // Failed coordinator
    static int initiator;           // Initiator of the election

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of processes
        System.out.print("Enter the number of processes: ");
        num_pr = sc.nextInt();
        isActive = new int[num_pr + 1];

        // Mark all processes as active initially
        for (int i = 1; i <= num_pr; i++) {
            isActive[i] = 1;
        }

        // Simulate failure of the current leader (highest ID)
        old_cord = num_pr;
        isActive[old_cord] = 0;

        System.out.println("Process " + old_cord + " (the coordinator) has failed.");

        // Input initiator
        System.out.print("Enter the process that initiates the election: ");
        initiator = sc.nextInt();

        // Optionally simulate another failed process
        System.out.print("Enter another process to fail (0 if none): ");
        int failed_process = sc.nextInt();
        if (failed_process != 0) {
            isActive[failed_process] = 0;
            System.out.println("Process " + failed_process + " has failed.");
        }

        // Start the election
        int newCoordinator = startElection(initiator);

        System.out.println("\n>>> Process " + newCoordinator + " is elected as the new coordinator.\n");

        // Send coordinator message to all active processes
        for (int i = 1; i <= num_pr; i++) {
            if (i != newCoordinator && isActive[i] == 1) {
                System.out.println("Coordinator message (" + newCoordinator + ") sent to Process " + i);
            }
        }

        sc.close();
    }

    public static int startElection(int initiator) {
        System.out.println("\nElection started by Process " + initiator);

        List<Integer> electionList = new ArrayList<>();
        int current = initiator;

        do {
            if (isActive[current] == 1) {
                electionList.add(current);
                System.out.println("Process " + current + " adds itself to the election message.");
            }
            // Move to next process in ring
            current = (current % num_pr) + 1;
        } while (current != initiator);

        System.out.print("Election message circulated with process IDs: ");
        System.out.println(electionList);

        // New coordinator is the process with the highest ID
        return Collections.max(electionList);
    }
}
