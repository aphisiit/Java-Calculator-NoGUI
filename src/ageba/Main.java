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
		int i,br = 0;
		
		try{
			for(i = 0 ; i < stringInput.length() ; i++){
				if(Character.isDigit(stringInput.charAt(i)) || stringInput.charAt(i) == '.'){
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
						|| stringInput.charAt(i) == '/' ||stringInput.charAt(i) == '%'){
					if(tempString != ""){
						digitString.add(tempString);
						stringExp.add(tempString);
						tempString = "";
					}
					operatorString.add(stringInput.charAt(i)+"");
					stringExp.add(stringInput.charAt(i)+"");
				}	
				else if(stringInput.charAt(i) == '(' || stringInput.charAt(i) == ')'){
					if(tempString != ""){
						digitString.add(tempString);
						stringExp.add(tempString);
						tempString = "";
					}
					br++;
					stringExp.add(stringInput.charAt(i)+"");
				}
			}
			
			if((digitString.size() + operatorString.size()) % 2 != 1 || digitString.size() <= operatorString.size()
					|| (br % 2) != 0 ){
					throw new IndexOutOfBoundsException();
			}					

			
			System.out.println("Digit : " + digitString);
			System.out.println("Operator : " + operatorString);
			System.out.println("Expression : " + stringExp);
			
//						
			List<String> tempList;
			@SuppressWarnings("unused")
			float tempFloat;
			while(stringExp.lastIndexOf("(") >= 0){
				
				System.out.println("START While");
				tempList = stringExp.subList(stringExp.lastIndexOf("(") + 1, stringExp.indexOf(")"));
				tempFloat = doMultiDivide(tempList);				
//				System.out.println("tempList = " + tempList);
//				System.out.println("StringExp = " + stringExp);
//				System.out.println("End While");
				
				//stringExp.set(startRB, Float.toString(tempFloat));
				System.out.println("tempList = " + tempList);
				System.out.println("StringExp = " + stringExp);
				
				if(stringExp.lastIndexOf("(") >= 0)
					stringExp.remove(stringExp.lastIndexOf("("));
				if(stringExp.indexOf(")") >= 0)
					stringExp.remove(stringExp.indexOf(")"));
				
				System.out.println("END While");
			}
						
			System.out.println("Expression : " + stringExp);
			System.out.println("Expression : " + stringExp.get(0));
			
			// ==========================> Print Result <=============================
			
			System.out.println("Result ==> " + doMultiDivide(stringExp));
						
		}
		catch(Exception e){
			System.out.print("Invalid number format");
		}							
	}
	
	public static float doMultiDivide(List<String> stringExp){
		
		//float sum = 0f;
		float tempFloat = Float.parseFloat(stringExp.get(0));
		
		Object tempString;
		for(int i=1 ; i < stringExp.size() ; i++){
			tempString = stringExp.get(i);
			if(tempString.equals("*")){
				tempFloat = Float.parseFloat(stringExp.get(i - 1)) * Float.parseFloat(stringExp.get(i+1));
				stringExp.set(i-1, Float.toString(tempFloat));
				stringExp.remove(i);
				stringExp.remove(i);
				i--;
			}
			else if(tempString.equals("/")){
				tempFloat = Float.parseFloat(stringExp.get(i - 1)) / Float.parseFloat(stringExp.get(i+1));
				stringExp.set(i-1, Float.toString(tempFloat));
				stringExp.remove(i);
				stringExp.remove(i);
				i--;
			}
			else if(tempString.equals("%")){
				tempFloat = Float.parseFloat(stringExp.get(i - 1)) % Float.parseFloat(stringExp.get(i+1));
				stringExp.set(i-1, Float.toString(tempFloat));
				stringExp.remove(i);
				stringExp.remove(i);
				i--;
			}				
		}
		
		//System.out.println("Middle : " + stringExp);
		
		for(int i=1 ; i < stringExp.size() ; i++){
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
		
		return tempFloat;
	}
}
