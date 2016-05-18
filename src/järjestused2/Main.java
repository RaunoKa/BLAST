package järjestused2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws IOException {
		String järjestust = "500"; // 10, 50, 100, 200, 500
		String baas = "blastn/tulemus"+järjestust+".txt";
		//String võrreldav = "masked/tulemus"+järjestust+".txt";
		String võrreldav = "megablast/tulemus"+järjestust+".txt";
		
		//SISSE LUGEMINE
		ArrayList<String> üks = loe(baas);
		System.out.println();
		ArrayList<String> kaks = loe(võrreldav);
		
		
		int erinevaid = 0;
		for(int i = 0; i < üks.size(); i++){
			if(!üks.get(i).equals(kaks.get(i))){
				erinevaid++;
				System.out.println(i+1+"-s päringujärjestus");
				System.out.println(üks.get(i));
				System.out.println(kaks.get(i));
				System.out.println();
			}
		}
		
		System.out.println(erinevaid +" järjestusel "+ üks.size() + "-st on erinev parima skooriga järjestus");
		
	}
	
	static ArrayList<String> loe(String fail) throws IOException{
		ArrayList<String> a = new ArrayList<String>();
		
		BufferedReader br = new BufferedReader(new FileReader(fail));
		 
		String line = null;
		int loe_rida = 0;
		
		while ((line = br.readLine()) != null) {
			if (loe_rida == 1){
				String[] tmp = line.split("\\.\\.\\.");
				
				try{
					if(tmp[0].startsWith(" ")){ //vanas megablast tulemustes on lisatühikud ja nimetus on 1 märk lühem
						tmp[0] = tmp[0].trim();
					}else
						tmp[0] = tmp[0].substring(0, tmp[0].length()-1);
						
					a.add(tmp[0]); 
				}catch(Exception e){
					
				}
				
				loe_rida = 0;
			}else if(loe_rida >1){
				loe_rida -= 1;
			}else{
				if(line.startsWith("Sequences")){
					//loe n-1 rea pärast; 2 = ülejärgmine rida (seal on esimene parim tulemus, 3 = teine tulemus jne...)
					loe_rida = 2; 
				}
					
				
			}
			
		} 
		br.close();
		
		return a;
	}

}
