package ttt;

import java.rmi.*;
import java.rmi.registry.*;

public class TTTServer {
    public static void main(String args[]){
        int registryPort = 8000;
        System.out.println("Main OK");
        try {
            TTT ttt = new TTT();
            System.out.println("After create");

            Registry reg = LocateRegistry.createRegistry(registryPort);
            reg.rebind("TTT", ttt);

            System.out.println("TTT server ready");

            System.out.println("Awaiting connections");
            System.out.println("Press enter to shutdown");
            System.in.read();
            System.exit(0);

        } catch(Exception e) {
            System.out.println("TTT server main " + e.getMessage());
        }
    }
}