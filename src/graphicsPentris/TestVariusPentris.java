package graphicsPentris;
import java.util.Scanner;



public class TestVariusPentris {
	

	public static void main(String[] args) throws InterruptedException {
		

		

		PentrisGameBoard pentrisBoard = new PentrisGameBoard(6, 12);
		pentrisBoard.startGameTimer();
		Scanner scanner= new Scanner(System.in);

		int movekey=  scanner.nextInt();
		
        while (!pentrisBoard.xAxisMovement()){
        	if(movekey==6){
        	pentrisBoard.moveShapeRightStep();
        	pentrisBoard.printPentrisBoard();
        	movekey=scanner.nextInt();
        
        	
        	}
        	if(movekey==4){
        		pentrisBoard.moveShapeLeftStep();
            	pentrisBoard.printPentrisBoard();
            	movekey=scanner.nextInt();
        		
        	}
        	if(movekey==2){
        		pentrisBoard.moveShapeDownStep();
            	pentrisBoard.printPentrisBoard();
            	movekey=scanner.nextInt();
        		
        	}
        	if(movekey==5){
        		pentrisBoard.rotateShape90DegreesMechanism();
        	
            	pentrisBoard.printPentrisBoard();
            	movekey=scanner.nextInt();
        		
        	}
        	
        	movekey=scanner.nextInt();
            	
        	
            Thread.sleep(15);
        }


	}
}
	