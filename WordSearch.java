package wordgame;
public class WordSearch {
	public static void main(String[] args) { 
		boolean play;
		int arguments = args.length;
                int cols=3;
		if(arguments==0) {
			boolean Typed = true;
		}
                //Command Line Arguments
		else if(args[0].equalsIgnoreCase("-help")) {
			StdOut.println("Options: ");
			StdOut.println("\"-board FILENAME\": Specifies the game board");
			StdOut.println("\"-dict FILENAME\":  Specifies the dictionary");
			StdOut.println("\"-cols NUMCOLS\": Specifies the number of columns chosen");
			return;
		}
		else if(args[0].equalsIgnoreCase("-dict")||args[0].equalsIgnoreCase("-board")||args[0].equalsIgnoreCase("-cols")) {
			if(arguments==2) {
				if (args[0].equalsIgnoreCase("-dict")) {
					String Dictionary = args[1];
					boolean Typed = true;
				}
				else if (args[0].equalsIgnoreCase("-board")) {
					String GameBoard = args[1];
				}
				else if (args[0].equalsIgnoreCase("-cols")) {
					boolean Typed = true;
					cols = Integer.parseInt(args[1]);
				}
			}
			else if(arguments==4) {
				if( (args[0].equalsIgnoreCase("-dict"))&&(args[2].equalsIgnoreCase("-board")))
				{
					String Dictionary = args[1];
					String GameBoard = args[3];
				}
				else if( (args[0].equalsIgnoreCase("-dict"))&&(args[2].equalsIgnoreCase("-cols"))) {
					String Dictionary = args[1];
					cols = Integer.parseInt(args[3]);
					boolean Typed = true;
				}
				else if( (args[0].equalsIgnoreCase("-board"))&&(args[2].equalsIgnoreCase("-cols"))) {
					String GameBoard = args[1];
					cols = Integer.parseInt(args[3]);
				}
			}
			else if(arguments==6) {
				if( (args[0].equalsIgnoreCase("-dict"))&&(args[2].equalsIgnoreCase("-board"))&&(args[4].equalsIgnoreCase("-cols")))
				{
					String Dictionary = args[1];
					String GameBoard = args[3];
					cols = Integer.parseInt(args[5]);
				}

			}
		}
		while(play=true) {
			if(!play)
				break;
		String word;
		StdOut.println("Welcome, player! Ready for the puzzle? Alternatively, run with -help for command-line options.");			
		//Dictionary to Bag
		//Stopwatch t1 = new Stopwatch();
                In FileReader = new In ("dict8.txt");
                //In FileReader = new In ("dict10.txt"); //Alternate dictionary file
		Bag<String> DictBag = new Bag<>();	
                while(!FileReader.isEmpty()){
	    	 word=FileReader.readLine(); 
	    	 if(word.length()>=4)
	    	 DictBag.add(word);    		      
	        }
	       // StdOut.println("Transferring " + DictBag.size() + " words from Dictionary file to DictBag:  " + (double)((int)((double)t1.elapsedTime()*1000))/1000 + " seconds");
                FileReader.close();	    
	    
	    //Dictionary Bag to Dictionary TST
	    TST<Integer> DictTST = new TST<>();
	    int value=0;
	   // Stopwatch t2 = new Stopwatch();
	    for(String key: DictBag) {
	    	DictTST.put(key,value);
	    	value++;
            }
	   // StdOut.println("Transferring words from DictBag to DictTST:  " + (double)((int)((double)t2.elapsedTime()*1000))/1000 + " seconds");
	
	    //Game Board to Array
               StdOut.println("Gameboard: ");
	       //In GameBoardReader = new In("data2.txt"); //Game board without wildcard characters (7*7)
               In GameBoardReader = new In("datawcs.txt");  //Game board with wildcard characters (7*7)
               //In GameBoardReader = new In("data1.txt");  //Game board with wildcard characters (5*5)
                word=GameBoardReader.readLine();
	    	int int1=0;
	    	int int2=0;
	    	int size = Integer.parseInt(word);
                char [][]Gamechar= new char[size][size]; 
		    while(!GameBoardReader.isEmpty()) {
		    	word=GameBoardReader.readLine(); 
                        StdOut.println(word);
		    	   for (int i = 0; i < word.length(); i++) {
		    		   char rows[]=word.toCharArray();
		    		   if(i%2==0) {
		    			   Gamechar[int2][int1]=rows[i];
	    				   char temp=Gamechar[int2][int1];
	    				   temp=Character.toLowerCase(temp);
	    				   Gamechar[int2][int1]=temp;
	    				   int1++;
		    			   if(int1%size==0) {
		    				  int1=0; int2++;
		    				   
		    			   }
		    		   }
		    	   }
		      }
	   
           //Array to Bag of Possible Words on the Game board
	   Bag<String> PossibleWords = new Bag<>();
	   boolean exists;
	   String wordbuild;
	   int index1; int index2;
	  // Stopwatch t3 = new Stopwatch();
	   for(int i=0;i<size;i++)
		   for(int j=0;j<size;j++) { // 8 possible directions
                           //Right
			   index1=i; index2=j;
			   wordbuild="";
			   while(index1<size) {
	 			   String newstr= String.valueOf(Gamechar[index1][index2]);
	 			   wordbuild = wordbuild.concat(newstr);
	 			   exists = false;
 				   for(String a:PossibleWords)
 				   if(wordbuild.equalsIgnoreCase(a)) 
 					    exists=true;
	 			   if(!exists&&wordbuild.length()>=4)
	 				   PossibleWords.add(wordbuild);
 				   index1++;
			   } 
			   //Left			   
			   index1=i; index2=j;
			   wordbuild="";
			   while(index1>=0) {
	 			   String newstr= String.valueOf(Gamechar[index1][index2]);
	 			   wordbuild = wordbuild.concat(newstr);
	 			   exists = false;
 				   for(String a:PossibleWords)
 				   if(wordbuild.equalsIgnoreCase(a)) 
 					    exists=true;
	 			   if(!exists&&wordbuild.length()>=4)
                                            PossibleWords.add(wordbuild);
                                       index1--;
			   } 
                           //Down
			   index1=i; index2=j;
			   wordbuild="";
			   while(index2<size) {
	 			   String newstr= String.valueOf(Gamechar[index1][index2]);
	 			   wordbuild = wordbuild.concat(newstr);
	 			   exists = false;
 				   for(String a:PossibleWords)
 				   if(wordbuild.equalsIgnoreCase(a)) 
 					    exists=true;
	 			   if(!exists&&wordbuild.length()>=4)
	 				   PossibleWords.add(wordbuild);
 				   index2++;
			   } 
			   //Up
			   index1=i; index2=j;
			   wordbuild="";
			   while(index2>=0) {
	 			   String newstr= String.valueOf(Gamechar[index1][index2]);
	 			   wordbuild = wordbuild.concat(newstr);
	 			   exists = false;
 				   for(String a:PossibleWords)
 				   if(wordbuild.equalsIgnoreCase(a)) 
 					    exists=true;
	 			   if(!exists&&wordbuild.length()>=4)
	 				   PossibleWords.add(wordbuild);
 				   index2--;
			   } 
			   //Down-right
			   index1=i; index2=j;
			   wordbuild="";
			   while(index1<size&&index2<size) {
	 			   String newstr= String.valueOf(Gamechar[index1][index2]);
	 			   wordbuild = wordbuild.concat(newstr);
	 			   exists = false;
 				   for(String a:PossibleWords)
 				   if(wordbuild.equalsIgnoreCase(a)) 
 					    exists=true;
	 			   if(!exists&&wordbuild.length()>=4)
	 				   PossibleWords.add(wordbuild);
 				   index1++; index2++;
			   }
                            //Down-left
			   index1=i; index2=j;
			   wordbuild="";
			   while(index1>=0&&index2<size) {
	 			   String newstr= String.valueOf(Gamechar[index1][index2]);
	 			   wordbuild = wordbuild.concat(newstr);
	 			   exists = false;
 				   for(String a:PossibleWords)
 				   if(wordbuild.equalsIgnoreCase(a)) 
 					    exists=true;
	 			   if(!exists&&wordbuild.length()>=4)
	 				   PossibleWords.add(wordbuild);
 				   index1--; index2++;
			   } 
                           //Up-right
			   index1=i; index2=j;
			   wordbuild="";
			   while(index1<size&&index2>=0) {
	 			   String newstr= String.valueOf(Gamechar[index1][index2]);
	 			   wordbuild = wordbuild.concat(newstr);
	 			   exists = false;
 				   for(String v:PossibleWords)
 				   if(wordbuild.equalsIgnoreCase(v)) 
 					    exists=true;
	 			   if(!exists&&wordbuild.length()>=4)
	 				   PossibleWords.add(wordbuild);
 				   index1++; index2--;
			   }
			   //Up-left
			   index1=i; index2=j;
			   wordbuild="";
			   while(index1>=0&&index2>=0) {
	 			   String newstr= String.valueOf(Gamechar[index1][index2]);
	 			   wordbuild = wordbuild.concat(newstr);
	 			   exists = false;
 				   for(String v:PossibleWords)
 				   if(wordbuild.equalsIgnoreCase(v)) 
 					    exists=true;
	 			   if(!exists&&wordbuild.length()>=4)
	 				   PossibleWords.add(wordbuild);
 				   index1--; index2--;
			   }	   			   
		   }
	   //StdOut.println("Finding all words from gameboard: " + (double)((int)((double)t3.elapsedTime()*1000))/1000 + " seconds");
         
	   //Game TST
	   int value2=0;
	   TST<Integer> GameTST = new TST<>();
	    for(String kk : PossibleWords) {	    	
	    	kk=kk.replace('*', '.');
	    	if(DictTST.keys().toString().isEmpty()==false) {
	    		for(String finalwords:DictTST.wildcardMatch(kk))
	    		{GameTST.put(finalwords, value2);
	    		 value2++;
	    		}
	    	}
       }
            //User-input
        StdOut.println("Let's see how many 4-letter or more words you can find. Press a non-letter and then press ENTER to quit.");
        Bag<String> FromConsole = new Bag<>();
        String In;       
        boolean quit = true;      
        while(quit) {
        	In = StdIn.readString();
        	quit = In.matches("[a-zA-Z]*");
        	if(!quit)
        		break;
                else { 		
                boolean input = false;
                for( String temp : GameTST.keys()) {
 	 			   exists = false;
  				   for(String v:FromConsole)
  				   if(v.equalsIgnoreCase(In)) 
  					    exists=true;
  				   if(temp.equalsIgnoreCase(In)) {
  					   if(!exists) {
                                            input = true;
                                            FromConsole.add(In);
  					   }
  					   else
  						   input = true;
  				   }           		
                }
                In=In.toUpperCase();
                if(input)
                	StdOut.println("Yes, \"" + In + "\" is in the dictionary and on the game board. ");
                else
                	StdOut.println("No, \"" + In + "\" is not both in the dictionary and on the game board. ");
        	}
        }

        //Words on board that are in the dictionary
        StdOut.println("List of words on the game board:");
	    value2=0;
	    for (String key:GameTST.keys()) {
	    	key=key.toUpperCase();
           StdOut.print(key+" ");
           value2++;
           if(value2%cols==0)
        	   StdOut.println();
	    }
	    
	//Words found by user
        StdOut.println();
        StdOut.println("List of words that you found:");
        value2=0;
        for(String wordcon : FromConsole) {
        	wordcon=wordcon.toUpperCase();
        	StdOut.print(wordcon+" ");
            value2++;
            if(value2%cols==0)
         	   StdOut.println();
        }
        StdOut.println();
        
        //Accuracy
        int s1 = FromConsole.size();
        int s2 = GameTST.size();
        double score = (s1/s2)*100;
        int outof = (int)score;
        int outof2 = (int)((score+0.001)*100)%100;
        if(outof2==0)
        	StdOut.println("You found "+ s1 +" out of "+ s2 +" words, or "+ outof + "." + outof2 + outof2 +" percent.");
        else
        	StdOut.println("You found "+ s1 +" out of "+ s2 +" words, or "+ outof + "." + outof2 +" percent.");
        StdOut.print("Play again? [Y/N] ");
        String playAgain = StdIn.readString();
        if(playAgain.equalsIgnoreCase("Y"))
        	play = true;
        else 
        	play = false;
	}
    }
}