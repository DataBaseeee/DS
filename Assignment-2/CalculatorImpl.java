import CalculatorApp.*;
import org.omg.CORBA.*;

public class CalculatorImpl extends CalculatorPOA {
    private ORB orb;

    public void setORB(ORB orb_val) {
        orb = orb_val;
    }

    public float add(float a, float b) {
        return a + b;
    }

    public float subtract(float a, float b) {
        return a - b;
    }

    public float multiply(float a, float b) {
        return a * b;
    }

    public float divide(float a, float b) {
        if (b == 0) return 0; // Basic divide-by-zero handling
        return a / b;
    }
}
