package ttt;

import java.util.Scanner;
import java.rmi.*;


public class Game {
	Scanner keyboardSc;
	int winner = 0;
	int player = 1;
	
	TTTService tttRemote = null;


	public Game() {
		keyboardSc = new Scanner(System.in);
		
		try {
			tttRemote = (TTTService) Naming.lookup("//localhost:8000/TTTService");

		} catch (NotBoundException e) {
			System.out.println("Error getting the service: " + e.getMessage());

		} catch (RemoteException e) {
			System.out.println("Error getting the service: " + e.getMessage());
		}

	}

	public int readPlay() {
		int play;
		do {
			System.out.printf(
					"\nPlayer %d, please enter the number of the square "
							+ "where you want to place your %c (or 0 to refresh the board): \n",
					player, (player == 1) ? 'X' : 'O');
			play = keyboardSc.nextInt();
		} while (play > 9 || play < 0);
		return play;
	}

	public void playGame() {
		int play;
		boolean playAccepted;
		try {
			do {
				player = ++player % 2;
				do {
					System.out.println(tttRemote.currentBoard());
					
					play = readPlay();
					
					if (play != 0) {
						playAccepted = tttRemote.play(--play / 3, play % 3, player);
						if (!playAccepted)
							System.out.println("Invalid play! Try again.");
					} else
						playAccepted = false;
				} while (!playAccepted);
				winner = tttRemote.checkWinner();
			} while (winner == -1);
			System.out.println(tttRemote.currentBoard());
		} catch (RemoteException e) {
			System.out.println("TTTGame: " + e.getMessage());

		}
		
	}

	public void congratulate() {
		if (winner == 2)
			System.out.printf("\nHow boring, it is a draw\n");
		else
			System.out.printf("\nCongratulations, player %d, YOU ARE THE WINNER!\n", winner);
	}

	public static void main(String[] args) {
		try {
			Game g = new Game();
			g.playGame();
			g.congratulate();
		} catch (RemoteException e) {
            System.out.println("TTTGame: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Lookup: " + e.getMessage());
        }
		
	}
}
