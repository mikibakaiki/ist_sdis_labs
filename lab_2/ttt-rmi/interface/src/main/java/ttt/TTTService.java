package ttt;

import java.rmi.Remote;
import java.rmi.RemoteException;

/** Tic Tac Toe game remote interface. */
public interface TTTService extends Remote {
    boolean play(int row, int column, int player) throws RemoteException;

    String currentBoard() throws RemoteException;

    int checkWinner() throws RemoteException;

}
