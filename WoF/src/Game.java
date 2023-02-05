import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Game {
	Game() throws IOException{
		//find countries text and read
				Path countriesPath = Paths.get("countries.txt");											
				Scanner countries = new Scanner(countriesPath);
				
				//counting countries and create new stack
				int cnCount = (int)Files.lines(countriesPath).count();
				Stack S1 = new Stack(cnCount);
				
				Stack tempS = new Stack(cnCount);
				//read all countries and add S1
				while(countries.hasNext()) {
					for(int i=0;i<cnCount;i++) {
						String line = countries.nextLine();
						S1.push(line);
						
					}	
					if(S1.isFull()) {
						break;
					}
				}
				//creating new temp stack and adding S1 to temp stack.
				while(!(S1.isEmpty())) {
					//System.out.println(S1.peek());		
					tempS.push(S1.pop());
				}
				while(!(tempS.isEmpty())) {
							
					S1.push(tempS.pop());
				}
				
				while(!S1.isEmpty())
		        {
		            // pop out the first element	
					String temp=(String) S1.pop();      	
		            // while temporary stack is not empty and
		            // top of stack is greater than temp											
					while(!tempS.isEmpty()&&((int)((String)tempS.peek()).compareToIgnoreCase(temp) > 0)){   			  
					  S1.push(tempS.pop());
					}	
					tempS.push(temp);
		        }
				//add S1 and emptying temp stack.
				while(!(tempS.isEmpty())) {
					S1.push(((String)tempS.peek()).toUpperCase(Locale.ENGLISH));
					tempS.pop();			
				}
				
				//stack for english letters.
				Stack S2=new Stack(26);		
				for(int i=90;i>=65;i--) {
					//turning integer to ascii and add S2.
					char letter=(char) i;
					S2.push(letter);
				}
				
				//-------------------------------------------------------------------------------------------------------
				//generating random number for countries.
				Random rand = new Random();
				int rndForS1 = rand.nextInt(cnCount)+1;
				System.out.println("Randomly generated number: "+rndForS1);
				
				//finding the country.
				for(int i=0;i<rndForS1-1;i++) {
					tempS.push(S1.pop());
				}		
				//System.out.println(S1.peek());
				//creating queue for country guessing.
				Queue Q1=new Queue(cnCount*5);
				Queue Q2=new Queue(cnCount*5);
						
				//guessing country letters in Q1
				for(int i=0;i<((String) S1.peek()).length();i++) {
					Q1.enqueue(((String) S1.peek()).charAt(i));							
				}
				
				//creating guess letters 
				for(int i=0;i<((String) S1.peek()).length();i++) {
					if(((String) S1.peek()).charAt(i)>='A'&&((String) S1.peek()).charAt(i)<='Z'){
						Q2.enqueue('-');		
					}
					else {
						Q2.enqueue(' ');	
					}	
				}
				//showing on the screen and emptying the queue.
				while(!Q2.isEmpty()) {
					System.out.print(Q2.peek());
					Q2.dequeue();
				}
				for(int i=0;i<((String) S1.peek()).length();i++) {
					if(((String) S1.peek()).charAt(i)>='A'&&((String) S1.peek()).charAt(i)<='Z'){
						Q2.enqueue('-');
						//tempQ.enqueue('-');
					}
					else {
						Q2.enqueue(' ');
						//tempQ.enqueue(' ');
					}	
				}

				Stack tempS2=new Stack(cnCount);
				int rndForS2;
				boolean flag=true;
				int totalScore=0;
				int rndWheel;
				int wheel=0;
				//**********************************************************************
				//finding the letters for countries.
				while(flag&&!S2.isEmpty()) {	
					//random letters.
					rndForS2 = rand.nextInt(S2.size())+1;	
					for(int i=0;i<rndForS2-1;i++) {
						tempS2.push(S2.pop());
					}
					//random wheels.
					rndWheel = rand.nextInt(8);		
					System.out.println("\n"+(char)S2.peek());
					if(rndWheel==0) {
						wheel=10;
						System.out.println("Wheel: "+wheel);
					}
					else if(rndWheel==1) {
						wheel=50;
						System.out.println("Wheel: "+wheel);	
					}
					else if(rndWheel==2) {
						wheel=100;
						System.out.println("Wheel: "+wheel);
					}
					else if(rndWheel==3) {
						wheel=250;
						System.out.println("Wheel: "+wheel);
					}
					else if(rndWheel==4) {
						wheel=500;
						System.out.println("Wheel: "+wheel);
					}
					else if(rndWheel==5) {
						wheel=1000;
						System.out.println("Wheel: "+wheel);
					}
					else if(rndWheel==6) {
						System.out.println("Wheel: Double Money");
					}
					else if(rndWheel==7) {
						wheel=0;
						System.out.println("Wheel: Bankrupt");
					}
					
					
					for(int i=0;i<((String) S1.peek()).length();i++) {
						//if guessing letter is equal to random letter, add wheel to score.
						if((char) Q1.peek()==(char)S2.peek()) {
							if(rndWheel==0) {
								wheel=10;
								totalScore+=wheel;
							}
							else if(rndWheel==1) {
								wheel=50;	
								totalScore+=wheel;
							}
							else if(rndWheel==2) {
								wheel=100;
								totalScore+=wheel;
							}
							else if(rndWheel==3) {
								wheel=250;
								totalScore+=wheel;
							}
							else if(rndWheel==4) {
								wheel=500;
								totalScore+=wheel;
							}
							else if(rndWheel==5) {
								wheel=1000;
								totalScore+=wheel;
							}
							else if(rndWheel==6) {
								totalScore+=totalScore;
							}
							else if(rndWheel==7) {
								wheel=0;
								totalScore=0;
							}
						
							Q2.enqueue((char) Q1.peek());
						}
						//if guessing word has space, add q2 space.
						else if((char) Q2.peek()==' ') {
							Q2.enqueue(' ');
						}
						else if((char) Q2.peek()!='-') {
							Q2.enqueue(Q2.peek());
						}
						else {
							Q2.enqueue('-');
						}
						Q2.dequeue();	
						//System.out.print(Q1.peek());	
						Q1.dequeue();			
					}
					
					for(int i=0;i<((String) S1.peek()).length();i++) {
						Q1.enqueue(Q2.peek());		
						Q2.enqueue(Q2.dequeue());
					}
					//if all letter match with the guessing letter, game ends.
					int count=0;
					for(int i=0;i<((String) S1.peek()).length();i++) {
						System.out.print(Q1.peek());
						if((char)Q1.peek()==((String) S1.peek()).charAt(i)) {
							count++;
							if(count==((String) S1.peek()).length()) {
								System.out.println("\nCongrats!");						
								flag=false;
								break;
							}
						}
						Q1.dequeue();
					}
					//printing money on the screen.
					System.out.println("\nMoney: "+totalScore);
					
					
					for(int i=0;i<((String) S1.peek()).length();i++) {
						Q1.enqueue(((String) S1.peek()).charAt(i));							
					}
								
					S2.pop();
				
					while(flag==true&&!(tempS2.isEmpty())) {
						S2.push((String.valueOf( tempS2.peek())).charAt(0));
						tempS2.pop();			
					}
					
				System.out.println();
				while(flag==true&&!S2.isEmpty()) {
					System.out.print(S2.peek());
					tempS2.push(S2.peek());
					S2.pop();
					
				}
				
				while(flag==true&&!tempS2.isEmpty()) {
					S2.push(tempS2.peek());
					tempS2.pop();
					}										
				}
				highScore(totalScore);
				countries.close();
				
			}
			
			public static void highScore(int inputScore) throws IOException {
				//find score text in the computer.
				Path scorePath = Paths.get("h.txt");											
				Scanner scores = new Scanner(scorePath);	
				int scCount = (int)Files.lines(scorePath).count();
				
				String[][] arrScore = new String[scCount][2];	
				
				//read scores and add array.
				while (scores.hasNextLine()) {						
					for(int i=0;i<arrScore.length;i++) {
						String[] line = scores.nextLine().trim().split(" ");
						for (int j=0; j<line.length; j++) {
							arrScore[i][j] =line[j];					               							
						}
					}
				}
				//System.out.println(Arrays.deepToString(arrScore));
				
				Stack S3 = new Stack(scCount+1);
				Stack S4 = new Stack(scCount+1);
				S3.push("You");
				S4.push(String.valueOf(inputScore));
				Stack tempS3 = new Stack(scCount+1);
				Stack tempS4 = new Stack(scCount+1);
				//add scores to stacks.
				for(int i=0;i<scCount;i++) {
					S3.push(arrScore[i][0]);
				}
				for(int i=0;i<scCount;i++) {
					S4.push(arrScore[i][1]);
				}
				while(!(S4.isEmpty())) {
					//System.out.print(S4.peek());		
					tempS4.push(S4.pop());
				}
				while(!(tempS4.isEmpty())) {
							
					S4.push(tempS4.pop());
				}
				
				while(!(S3.isEmpty())) {
					//System.out.print(S3.peek());		
					tempS3.push(S3.pop());
				}
				while(!(tempS3.isEmpty())) {
							
					S3.push(tempS3.pop());
				}
			
				while(!S4.isEmpty())
		        {
					String temp=(String) S4.pop();
					String temp2=(String) S3.pop();   
					//sort stack for high score table.
		            while(!tempS4.isEmpty() &&Integer.parseInt(((String) tempS4.peek())) < Integer.parseInt(temp))
		            {             
		            S4.push(tempS4.pop());
		            S3.push(tempS3.pop());
		            }
		              
		            tempS4.push(temp);
		            tempS3.push(temp2);
		        }
				while(!(tempS4.isEmpty())) {
					S4.push(Integer.parseInt(((String) tempS4.peek())));
					//System.out.print(S4.peek());
					tempS4.pop();			
				}
				while(!(tempS3.isEmpty())) {
					S3.push((String)tempS3.peek());
					//System.out.print(S3.peek());
					tempS3.pop();			
				}
				System.out.println("High score table");
				//writing the scores in the text.
				Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("h.txt"))); 
				for(int i=0;i<10;i++) {
				System.out.print((String)S3.peek()+" "+String.valueOf(S4.peek())+"\n");
				writer.write((String)S3.pop()+" "+String.valueOf(S4.pop())+"\n");
				}
				writer.close();
				scores.close();
			}
	

}
