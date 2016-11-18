package ageba;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args){
		System.out.print("Enter a string : ");
		Scanner input = new Scanner(System.in);
		String stringInput = input.next();
		input.close();
		String tempString = "";
		List<String> digitString = new ArrayList<String>();
		List<String> operatorString = new ArrayList<String>();
		List<String> stringExp = new ArrayList<String>();
		int i;
		
		try{
			for(i = 0 ; i < stringInput.length() ; i++){
				if(Character.isDigit(stringInput.charAt(i))){
					tempString += stringInput.charAt(i)+"";					
					if(i == stringInput.length() - 1){
						if(tempString != ""){
							stringExp.add(tempString);
							digitString.add(tempString);
							tempString = "";
						}						
					}
				}
				
				else if(stringInput.charAt(i) == '+' || stringInput.charAt(i) == '-' || stringInput.charAt(i) == '*' 
						|| stringInput.charAt(i) == '/' ||stringInput.charAt(i) == '%' || stringInput.charAt(i) == '(' 
						|| stringInput.charAt(i) == ')'){
					if(tempString != ""){
						digitString.add(tempString);
						stringExp.add(tempString);
						tempString = "";
					}
					operatorString.add(stringInput.charAt(i)+"");
					stringExp.add(stringInput.charAt(i)+"");
				}				
			}
			System.out.println("Digit : " + digitString);
			System.out.println("Operator : " + operatorString);
			
			if((digitString.size() + operatorString.size()) % 2 != 1 || digitString.size() <= operatorString.size()){
				throw new IndexOutOfBoundsException();
			}					
							
			System.out.println("Before Calcualate Exp String : " + stringExp);
			float tempFloat = 0.0f;
				
			for(i=1 ; i < stringExp.size() ; i++){
				tempString = stringExp.get(i);
				//System.out.println(stringExp.get(i));
//				System.out.println(i + " : " + tempString);
				if(tempString.equals("*")){
					tempFloat = Float.parseFloat(stringExp.get(i - 1)) * Float.parseFloat(stringExp.get(i+1));
					stringExp.set(i-1, Float.toString(tempFloat));
//					System.out.println("Do *");
					stringExp.remove(i);
					stringExp.remove(i);
					i--;
					//i -= 2;
				}
				else if(tempString.equals("/")){
					tempFloat = Float.parseFloat(stringExp.get(i - 1)) / Float.parseFloat(stringExp.get(i+1));
					stringExp.set(i-1, Float.toString(tempFloat));
//					System.out.println("Do /");
					stringExp.remove(i);
					stringExp.remove(i);
					i--;
					//i -= 2;
				}
				else if(tempString.equals("%")){
					tempFloat = Float.parseFloat(stringExp.get(i - 1)) % Float.parseFloat(stringExp.get(i+1));
					stringExp.set(i-1, Float.toString(tempFloat));
//					System.out.println("Do %");
					stringExp.remove(i);
					stringExp.remove(i);
					i--;
					///i -= 2;
				}				
			}
			
			System.out.println("Middle : " + stringExp);
			
			for(i=1 ; i < stringExp.size() ; i++){
				tempString = stringExp.get(i);
				if(tempString.equals("+")){
					tempFloat = Float.parseFloat(stringExp.get(i - 1)) + Float.parseFloat(stringExp.get(i + 1));
					stringExp.set(i-1, Float.toString(tempFloat));
					stringExp.remove(i);
					stringExp.remove(i);
					i--;
				}
				else if(tempString.equals("-")){
					tempFloat = Float.parseFloat(stringExp.get(i - 1)) - Float.parseFloat(stringExp.get(i + 1));
					stringExp.set(i-1, Float.toString(tempFloat));
					stringExp.remove(i);
					stringExp.remove(i);
					i--;
				}
			}
			//}
			//End Experiments string
			System.out.println("After Calcualate Exp String : " + stringExp.get(0));
			
		}
		catch(IndexOutOfBoundsException e){
			System.out.print("You enter wrong string, Try again !!!");
		}					
	}
}
