import mpi.*;

public class ArraySumMPI {
    static final int N = 10;
    static int[] a = {1,2,3,4,5,6,7,8,9,10};

    public static void main(String[] args) throws Exception {
        MPI.Init(args);
        int pid = MPI.COMM_WORLD.Rank();
        int np = MPI.COMM_WORLD.Size();

        int elementsPerProcess = N / np;
        int[] recvData = new int[N];
        int sum = 0;

        if (pid == 0) {
            // Master process
            for (int i = 1; i < np; i++) {
                int start = i * elementsPerProcess;
                int length = (i == np - 1) ? N - start : elementsPerProcess;
                MPI.COMM_WORLD.Send(new int[]{length}, 0, 1, MPI.INT, i, 0);
                MPI.COMM_WORLD.Send(a, start, length, MPI.INT, i, 1);
            }

            // Sum own part
            for (int i = 0; i < elementsPerProcess; i++)
                sum += a[i];

            // Receive partial sums
            int[] temp = new int[1];
            for (int i = 1; i < np; i++) {
                MPI.COMM_WORLD.Recv(temp, 0, 1, MPI.INT, i, 2);
                sum += temp[0];
            }

            System.out.println("Sum of array is: " + sum);
        } else {
            // Slave processes
            int[] length = new int[1];
            MPI.COMM_WORLD.Recv(length, 0, 1, MPI.INT, 0, 0);
            int[] part = new int[length[0]];
            MPI.COMM_WORLD.Recv(part, 0, length[0], MPI.INT, 0, 1);

            int partialSum = 0;
            for (int i = 0; i < length[0]; i++)
                partialSum += part[i];

            MPI.COMM_WORLD.Send(new int[]{partialSum}, 0, 1, MPI.INT, 0, 2);
        }

        MPI.Finalize();
    }
}
