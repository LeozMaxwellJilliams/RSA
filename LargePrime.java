import java.util.*;
import java.io.*;
import java.math.*;


public class LargePrime {

	public static void main(String[] args) {
		
		Scanner kb = new Scanner(System.in);
		
		System.out.println("Would you like to encrypt or decrypt a message?");
		
		String ans = kb.nextLine();
		
		if (ans.equals("encrypt")){
			
			BigInteger p = new BigInteger(1024, 10, new Random());
			BigInteger q = new BigInteger(1024, 10, new Random());
			BigInteger n = p.multiply(q);
			BigInteger phi = p.subtract(new BigInteger("1")).multiply(q.subtract(new BigInteger("1")));
			
			BigInteger e = new BigInteger(10, new Random());
			while (true){
				if (e.gcd(phi).equals(new BigInteger("1"))){
					break;
				}
				else{
					e = new BigInteger(10, new Random());
				}
			}
			
			BigInteger d = e.modInverse(phi);
			
			System.out.println("What message (number) would you like to encrypt?");
			
			BigInteger m = new BigInteger(kb.nextLine());
			
			BigInteger c = m.modPow(e, n);
			
			
			try {
		        BufferedWriter out = new BufferedWriter(new FileWriter("encrypted.txt"));
		        	out.write(n.toString()+"\n");
		        	out.write(e.toString()+"\n");
		        	out.write(d.toString()+"\n");
		        	out.write(c.toString());
		            out.close();
		        } 
			catch (IOException ex) {System.out.println(ex);}
		}
		
		if (ans.equals("decrypt")){
			System.out.println("What file contains the encrypted message?");
			
			Scanner infile = null;
	    	
	    	try{ //catch errors in file IO
	    		infile = new Scanner(new File(kb.nextLine()));

	    	}
	    	catch(IOException ex){
	    		System.out.println(ex);
	    	}
	    	
	    	String n = infile.nextLine();
	    	String e = infile.nextLine();
	    	String d = infile.nextLine();
	    	String c = infile.nextLine();
	    	
	    	BigInteger M = new BigInteger(c).modPow(new BigInteger(d), new BigInteger(n));
	    	
	    	System.out.println(M.toString());
	    		
		}
		

	}

}
