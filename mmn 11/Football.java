import java.util.Scanner;
public class Football //class Football, by Elia Sulimanov, 14/10/2018.
{ // the program calculating the final score of 3 football games
    public static void main(String [] args)
    {
        final int WINING_POINTS = 3;
        final int WINING_DIFFRENCE_BONUS = 2;
        final int LOSEING_POINTS = 4;
        final int TIE_POINTS = 2;
        final int LAST_GAME_WINING_BONUS_MULTI = 2;
        final int LAST_GAME_DIFFRENCE_BONUS = 3;
        final int TIE_SUM = 3; //for bonus condition
        final int BONUS_POINTS_ADDITION = 5;
        
        boolean bonus1 = false, bonus2 = false, bonus3 = false;
        int totalScore = 0, tie = 0, won = 0; //counters initialize

        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter results for 3 games");
        System.out.println("The first game:");

        int homeTeam = scan.nextInt(); //get game scores
        int awayTeam = scan.nextInt();

        if(homeTeam > awayTeam) 
        {
            totalScore += WINING_POINTS;
            won++;
            if(homeTeam - awayTeam > WINING_DIFFRENCE_BONUS)
                bonus1 = true; //bonus1 is to check if game 1 won big time
        }
        else 
        {
            if(homeTeam < awayTeam) 
            {
                totalScore -= LOSEING_POINTS;
            }
            else 
            {
                totalScore += TIE_POINTS;
                tie++;
            }
        }

        System.out.println("The second game:");

        homeTeam = scan.nextInt();
        awayTeam = scan.nextInt();

        if(homeTeam > awayTeam) 
        {
            totalScore += WINING_POINTS;
            won++;
            if(homeTeam - awayTeam > WINING_DIFFRENCE_BONUS)
                bonus2 = true; //bonus2 is to check if game 2 won big time
        }
        else 
        {
            if(homeTeam < awayTeam) 
            {
                totalScore -= LOSEING_POINTS;
            }
            else 
            {
                totalScore += TIE_POINTS;
                tie++;
            }
        }

        System.out.println("The third game:");

        homeTeam = scan.nextInt();
        awayTeam = scan.nextInt();

        if(homeTeam > awayTeam) 
        {
            totalScore += WINING_POINTS + LAST_GAME_WINING_BONUS_MULTI * homeTeam;
            won++;
            if(homeTeam-awayTeam > LAST_GAME_DIFFRENCE_BONUS)
                bonus3 = true; //bonus3 is to check if game 3 won big time
        }
        else 
        {
            if(homeTeam < awayTeam) 
            {
                totalScore -= LOSEING_POINTS;
            }
            else 
            {
                totalScore += TIE_POINTS;
                tie++;
            }
        }

        if((bonus1 == true && bonus2 == true) || bonus3 == true || (tie == TIE_SUM)) //checking for bonus conditions
        {
            totalScore += BONUS_POINTS_ADDITION;
        }

        System.out.println("Number of games which the university team won: " + won);
        System.out.println("Number of games with tie result: " + tie);
        System.out.println("Final score of the university team: " + totalScore);
    } //end of method main
} //end of class Football
