import java.util.*;
import java.io.*;

class Room
{
int roomno;
 Room E,W,N,S;
}

class mazeclass
{
public static void main(String args[]) throws IOException

{
	
	//reads the count of lines in a text filename
     int num = countLines("CreateMaze.txt");
	 int i = 1;
	  Room head;
	 BufferedReader br;
	 PrintWriter w ;
	 
	  Room[] room=new  Room[num+2];
	 for( int k =0;k<num+2;k++)
		 room[k] =new  Room();
	 int[] east=new int[num+2];
	 int[] west=new int[num+2];
	 int[] north=new int[num+2];
	 int[] south=new int[num+2];
	
	 Scanner scanner = new Scanner(new File("CreateMaze.txt"));
	 while( scanner.hasNext() )
	 {
		room[i].roomno = scanner.nextInt() ;
		east[i] = scanner.nextInt() ;
		west[i] = scanner.nextInt() ;
		north[i] = scanner.nextInt() ;
	    south[i] = scanner.nextInt() ;
		i++;
		
	 }

	 
	  for( i=1;i<=num;i++ )
	 { 
 
     if(east[i]!=0)
     room[i].E=room[  east[i] ];
	 else
	 room[i].E=null;
	 if(west[i]!=0)    
	 room[i].W=room[  west[i] ];
	 else
	 room[i].W=null;
	 if(north[i]!=0)    
	 room[i].N=room[  north[i] ];
	 else
	 room[i].N=null;
	 if(south[i]!=0)
     room[i].S=room[  south[i] ];
	 else
	 room[i].S=null;
System.out.println(room[i].roomno+"--"+east[i]+"--"+west[i]+"--"+north[i]+"--"+south[i]);
	 } 
	 
	 int[] temp1; 
	 String[] temp;
   
	
	 br = new BufferedReader( new FileReader ( "TraverseMaze.txt" ) ) ;
	
	 w = new PrintWriter(  "Destinations.txt" , "UTF-8" );
	
	 String line;
	 while( (line = br.readLine()) != null )
	 {
		
		 temp = line.split("\\s+");		
		 temp1 = new int[temp.length];
		 for( int k =0;k<temp.length;k++ )
			 temp1[k] = Integer.parseInt( temp[k] );
         
		 head =  room[ temp1[0] ];
		
		 int t = traverse( head , temp1) ;
		 w.println( t );
		 w.flush();
	 }
	 
	
	 
	

	
	 
	 
	 
}

	 static int traverse(  Room root, int dir[] )
	 {
		 for( int i  = 1; i < dir.length; i++ )
		 {
		 switch( dir[i] )
		 {
			 case 0 : 	 root = ( root.E==null ? root : root.E); break; 
			 case 1 : 	 root = ( root.W == null ? root : root.W ); break; 
			 case 2 : 	 root =( root.N == null ? root : root.N );  break; 
			 case 3 : 	 root = ( root.S == null ? root : root.S ); break; 
		 }
		 }
		 //System.out.println( root.roomno );		

		 return root.roomno;
	 }


	 static int countLines(String filename) throws IOException {
     InputStream is = new BufferedInputStream(new FileInputStream(filename));
     try {
        byte[] c = new byte[1024];
        int count = 0;
        int readChars = 0;
        boolean empty = true;
        while ((readChars = is.read(c)) != -1) {
            empty = false;
            for (int i = 0; i < readChars; ++i) {
                if (c[i] == '\n') {
                    ++count;
                }
            }
        }
        return (count == 0 && !empty) ? 1 : count;
    } finally {
        is.close();
    }
}
}
