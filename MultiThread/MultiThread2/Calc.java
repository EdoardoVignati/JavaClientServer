import java.io.*;
import java.net.Socket;


public class Calc extends Thread {
    Socket s = null;
    Double x, y = null;
    Character operator = null;
    String operators = "+*-/";

    public Calc(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        InputStream inStream;
        OutputStream outStream;

        try {
            inStream = s.getInputStream();
            outStream = s.getOutputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
            DataOutputStream out = new DataOutputStream(outStream);

            /* Continue asking for x */
            while (this.x == null) {
                out.writeBytes("Please insert first operand (double):\n");
                out.flush();
                this.x = this.isNumeric(in.readLine());
                System.out.println("First input: " + this.x);
            }


            /* Continue asking for y */
            while (this.y == null) {
                out.writeBytes("Please insert second operand (double):\n");
                out.flush();
                this.y = this.isNumeric(in.readLine());
                System.out.println("Second input: " + this.y);

            }

            /* Continue asking for operator */
            while (this.operator == null) {
                out.writeBytes("Please insert an operator ['+','-','*','/']:\n");
                out.flush();
                String op = in.readLine().charAt(0) + "";
                System.out.println("Operator input: " + op);
                if (operators.indexOf(op) != -1) {
                    this.operator = op.charAt(0);
                    break;
                }
            }

            String result = String.valueOf(this.doOperation());
            out.flush();
            out.writeBytes(result + "\n");
            System.out.println("Server sent " + result + " over the socket");

        } catch (IOException e) {
            System.out.println("Error in communication");
        } finally {
            try {
                s.close();
                System.out.println("Socket with client closed");
            } catch (IOException e) {
                System.out.println("Error closing connection with client");
            }
        }


    }

    private Double doOperation() {

        switch (this.operator) {
            case '+':
                return (this.x + this.y);
            case '-':
                return (this.x - this.y);
            case '/':
                return (this.x / this.y);
            case '*':
                return (this.x * this.y);
        }

        return null;
    }

    public Double isNumeric(String num) {
        try {
            return Double.parseDouble(num);
        } catch (Exception e) {
            System.out.println("Not Double number");
            return null;
        }
    }

}

