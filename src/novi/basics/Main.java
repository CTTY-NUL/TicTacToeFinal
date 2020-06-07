package novi.basics;

import java.util.Scanner;

public class Main {

    //ik moet dingen hier declareren als ik ze ook in andere methods wil gebruiken!
    public static char[] board = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static void main(String[] args) {
        //TODO zou leuker zijn als dit voor alle board sizes werkte
        //TODO speler objecten zoveel mogelijk gebruiken in tic tac toe, ik gebruik nu nog player1name en player2name
        //TODO welke andere classes kan ik aan tic tac toe toevoegen
        //TODO playerid ipv playertoken gebruiken


        // -- vanaf hier gaan we het spel opnieuw spelen met andere spelers (nadat op het eind keuze 2 gekozen is)
        // de 1e speler om zijn naam vragen


        // -- vanaf hier gaan we het spel opnieuw spelen met dezelfde spelers (nadat op het eind keuze 1 gekozen is)
        // speelbord opslaan (1 - 9)
        // uitleg: omdat we altijd met een bord starten met 9 getallen, kunnen we het Tic Tac Toe bord ook direct een waarde geven
        // zie demo project code voor de andere manier met de for loop

        //final char[] board = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        // speelbord tonen
        printBoard(board);


        int user_wants_to_exit = 0;
        //int user_input_is_valid = 0;
        int switch_players = 0;
        int drawCount = 0; // opslaan hoeveel keer er gelijk spel is geweest
        String activePlayerName;
        int keuze;
        int did_someone_win;

        while (user_wants_to_exit == 0) {

            Scanner playerInput = new Scanner(System.in);
            //voor testen makkelijker als ze defaultnamen hebben
            String player1Name = "Alice";
            String player2Name = "Bob";
//          System.out.println("Player 1, what is your name?");
//          String player1Name = playerInput.next();
            Player player1 = new Player(1, player1Name, 'X');
//          System.out.println("Player 2, what is your name?");
//          String player2Name = playerInput.next();
            Player player2 = new Player(2, player2Name, 'O');

            char activePlayerToken = 'X'; // token van de actieve speler opslaan
            int activePlayerId = 1;

            while (switch_players == 0) {
                System.out.println("9 beurten beginenn");
                for (int turn = 1; turn < 10; turn++) {
                    System.out.println("Het is beurt nummer " + turn);


                    keuze = ask_player_for_move(activePlayerId); // hier speler 1 of 2

                    board[keuze - 1] = activePlayerToken;
                    printBoard(board);
                    //if (turn > 2) { //met minder dan 3 beuren heeft t weinig zin dit te checken
                    did_someone_win = check_if_someone_won(board);
                    //}

                    if (did_someone_win == 1) { //ik zou ook een while loop kunnen maken van turn en dan telkens turn++

                        turn = 9;
                    } else if (did_someone_win == 0 && turn == 9) {
                        //het is gelijkspel
                        drawCount++;
                        System.out.println("het is gelijkspel maar eigenlijk zijn jullie beiden verliezers!");
                    }


                    //van activeplayertoken wisselen
                    if (activePlayerId == 1) {
                        activePlayerId = 2;
                        activePlayerToken = 'O';
                    } else {
                        activePlayerId = 1;
                        activePlayerToken = 'X';
                    }

                }
                System.out.println("hier mogelijkheid bieden andere spelers te nemen");
            }

        }
    }


    public static void printBoard(char[] board) {
        //TODO vervangen door mijn elegantere versie
        for (int fieldIndex = 0; fieldIndex < board.length; fieldIndex++) {
            System.out.print(board[fieldIndex] + " ");
            // als we het tweede veld geprint hebben of het vijfde veld geprint hebben
            // dan gaan we naar de volgende regel
            if (fieldIndex == 2 || fieldIndex == 5) {
                System.out.println();
            }
        }
        System.out.println();
    }


    public static int check_if_someone_won(char[] board) {

        for (int i = 1; i < 4; i++) {
            //horizontaal checken
            if (board[i * 3 - 3] == board[i * 3 - 2] && board[i * 3 - 2] == board[i * 3 - 1]) {
                System.out.println("WHOOHOOOOOOO er heeft iemand horizontaal gewoonnen!!!");
                return 1;
            }
            //verticaal checken
            if (board[i - 1] == board[i + 2] && board[i + 2] == board[i + 5]) {
                System.out.println("fuck yeah verticale win");
                return 1;
            }
        }
        //TODO diagonalen checken, zowel linksonder naar rechtsboven als linksboven naar rechtsonder

        return 0;
    }


    public static int ask_player_for_move(int speler) {  //speler 0 of speler 1? of 1 of 2?

        Scanner scan = new Scanner(System.in);  // Create a Scanner object

        Boolean validInput = false;
        int inputInt = 0;
        do {

            System.out.print("Speler " + speler + " please enter a whole number: ");
            if (scan.hasNextInt()) {
                inputInt = scan.nextInt();

                try {
                    if (board[inputInt - 1] != 'O' && board[inputInt - 1] != 'X') {
                        validInput = true;
                    } else {
                        System.out.println("This space is already occupied!");
                        validInput = false;
                    }
                } catch (Exception e) {
                    System.out.println("Please enter a whole number only 1-9");
                    validInput = false;
                }

            } else
                System.out.println("You have entered incorrect input! Please enter a whole number 1-9");
                scan.nextLine();
            }
        while (validInput == false);

        return inputInt;
    }


}