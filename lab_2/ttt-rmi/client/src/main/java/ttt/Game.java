package ttt;

import java.util.Scanner;
import java.rmi.*;


public class Game {
	Scanner keyboardSc;
	int winner = 0;
	int player = 1;

	public Game() {
		keyboardSc = new Scanner(System.in);
	}

	public int readPlay() {
		int play;
		do {
			System.out.printf(
					"\nPlayer %d, please enter the number of the square "
							+ "where you want to place your %c (or 0 to refresh the board): \n",
					player, (player == 1) ? 'X' : 'O');
			play = keyboardSc.nextInt();
			if (play == 444) {
				return play;
			}
		} while (play > 9 || play < 0);
		return play;
	}

	public void playGame(TTTService tttRemote) {
		int play;
		boolean playAccepted;
		try {
			do {
				player = ++player % 2;
				do {
					System.out.println(tttRemote.currentBoard());

					play = readPlay();
					if (play == 444) {
						tttRemote.passaVez();
						playAccepted = true;
					}
					else if (play != 0) {
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
			TTTService tttRemote = null;
			tttRemote = (TTTService) Naming.lookup("//localhost:8000/TTT");

			Game g = new Game();
			g.playGame(tttRemote);
			g.congratulate();
		} catch (UnknownHostException e) {
			System.out.println("Unknown host: " + e.getMessage());
		} catch (ConnectException e) {
			System.out.println("Connection error: " + e.getMessage());
		} catch (UnmarshalException e) {
			System.out.println("Error converting byte-stream to data: " + e.getMessage());
		} catch (MarshalException e) {
			System.out.println("Error converting data to byte-stream: " + e.getMessage());
		} catch (RemoteException e) {
            System.out.println("TTTGame: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
	}
}
