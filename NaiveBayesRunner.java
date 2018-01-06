package CS3;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class NaiveBayesRunner {
	public static void main(String args[]) throws IOException {
		Scanner file = new Scanner(
				new File(
						"/users/Documents/workspace/NewJavaTest/src/Training.dat"));
		Scanner fileD = new Scanner(
				new File(
						"/users/Documents/workspace/NewJavaTest/src/decission.dat"));
		String dString;
		String InputString;
		String keyword0 = "yes";
		String keyword1 = "no";
		
		int diabeticYes_Count = 0;
		int diabeticNo_Count  = 0;
		
		int dbYes_obese_Count  = 0; int dbYes_overweight_Count = 0; int dbYes_normalweight_Count = 0;int dbYes_underweight_Count = 0;
		int dbNO_obese_Count = 0;int dbNO_overweight_Count = 0;int dbNO_normalweight_Count = 0;int dbNO_underweight_Count = 0;
		
		int dbYes_BShigh_Count = 0;int dbYes_BSnormal_Count = 0;int dbYes_BSlow_Count = 0;
		int dbNO_BShigh_Count = 0;int dbNO_BSnormal_Count = 0;int dbNO_BSlow_Count = 0;
		
		int dbYes_AChigh_Count = 0;int dbYes_ACmoderate_Count = 0;int dbYes_ACpoor_Count = 0;
		int dbNO_AChigh_Count = 0;int dbNO_ACmoderate_Count = 0;int dbNO_ACpoor_Count = 0;
		
		float Pdiabetic_Yes = 0;
		float Pdiabetic_No = 0;
		
		float Pdiabetic_Yes_obese = 0;float Pdiabetic_Yes_overweight = 0;float Pdiabetic_Yes_normalweight = 0;float Pdiabetic_Yes_underweight =0;
		float Pdiabetic_NO_obese = 0;float Pdiabetic_NO_overweight = 0;float Pdiabetic_NO_normalweight = 0;float Pdiabetic_NO_underweight =0;
		
		float Pdiabetic_Yes_BShigh = 0;float Pdiabetic_Yes_BSnormal = 0;float Pdiabetic_Yes_BSlow = 0;
		float Pdiabetic_NO_BShigh = 0;float Pdiabetic_NO_BSnormal = 0;float Pdiabetic_NO_BSlow = 0;
		
		float Pdiabetic_Yes_AChigh = 0;float Pdiabetic_Yes_ACmoderate = 0;float Pdiabetic_Yes_ACpoor = 0;
		float Pdiabetic_NO_AChigh = 0;float Pdiabetic_NO_ACmoderate = 0;float Pdiabetic_NO_ACpoor = 0;
		
		NaiveBayesClassifer NB = new NaiveBayesClassifer ();
		// Read each record to find weather it is classified as diabetic or not. counting the number of records as diabetic classifiers yes or no
		//counting the number of  attribute classifiers with in diabetic yes or no classifier
		while (file.hasNextLine()) {
			InputString = file.nextLine();
			Boolean Yes_found = Arrays.asList(InputString.split(",")).contains(keyword0);
			if(Yes_found){
				diabeticYes_Count ++;
				String WeightClassfier = NB.findWeightClasffier(InputString);
				switch (WeightClassfier) {
				  case "obese" : dbYes_obese_Count ++;
				    break;
				  case "over" :   dbYes_overweight_Count ++;
				    break;
				  case "normal" : dbYes_normalweight_Count ++;
				    break;
				  case "under" :   dbYes_underweight_Count ++;
				    break;
				  
				
				}
				String BlodSugarClasffier = NB.findBlodSugarClasffier(InputString);
				switch (BlodSugarClasffier) {
				  case "BS_high" : dbYes_BShigh_Count ++;
				    break;
				  case "BS_normal" :   dbYes_BSnormal_Count ++;
				    break;
				  case "BS_low" : dbYes_BSlow_Count ++;
				    break;
				}
				String ActivityClasffier = NB.findActivityClasffier(InputString);
				switch (ActivityClasffier) {
				  case "AC_high" : dbYes_AChigh_Count ++;
				    break;
				  case "AC_moderate" :   dbYes_ACmoderate_Count ++;
				    break;
				  case "AC_poor" : dbYes_ACpoor_Count ++;
				    break;
				}
				
				
			}else {
				Boolean No_found = Arrays.asList(InputString.split(",")).contains(keyword1);
				if(No_found){
					diabeticNo_Count ++; 
					String WeightType = NB.findWeightClasffier(InputString);
					switch (WeightType) {
					  case "obese" : dbNO_obese_Count ++;
					    break;
					  case "over" :   dbNO_overweight_Count ++;
					    break;
					  case "normal" : dbNO_normalweight_Count ++;
					    break;
					  case "under" :   dbNO_underweight_Count ++;
					    break;
				    }
					String BlodSugarClasffier = NB.findBlodSugarClasffier(InputString);
					switch (BlodSugarClasffier) {
					  case "BS_high" : dbNO_BShigh_Count ++;
					    break;
					  case "BS_normal" :   dbNO_BSnormal_Count ++;
					    break;
					  case "BS_low" : dbNO_BSlow_Count ++;
					    break;
					}
					String ActivityClasffier = NB.findActivityClasffier(InputString);
					switch (ActivityClasffier) {
					  case "AC_high" : dbNO_AChigh_Count ++;
					    break;
					  case "AC_moderate" :   dbNO_ACmoderate_Count ++;
					    break;
					  case "AC_poor" : dbNO_ACpoor_Count ++;
					    break;
					}
					
			    }
				
			}
			
			//System.out.println(InputString);
			
		}
		file.close();
		int parameter_1 = 0;
		int parameter_2 = 0;
		int parameter_3 = 0;
		//Computing probabilities for main classifiers as well as attribute classifiers
		Pdiabetic_Yes = NB.computeProbability_for_Clasffier(diabeticYes_Count,(diabeticYes_Count + diabeticNo_Count),parameter_3);
		Pdiabetic_No = NB.computeProbability_for_Clasffier(diabeticNo_Count,(diabeticYes_Count + diabeticNo_Count),parameter_3);
		
		Pdiabetic_Yes_obese = NB.computeProbability_for_Clasffier(dbYes_obese_Count,diabeticYes_Count ,parameter_3);
		Pdiabetic_Yes_overweight = NB.computeProbability_for_Clasffier(dbYes_overweight_Count,diabeticYes_Count ,parameter_3);
		Pdiabetic_Yes_normalweight = NB.computeProbability_for_Clasffier(dbYes_normalweight_Count,diabeticYes_Count ,parameter_3);
		Pdiabetic_Yes_underweight = NB.computeProbability_for_Clasffier(dbYes_underweight_Count,diabeticYes_Count ,parameter_3);
	    Pdiabetic_NO_obese = NB.computeProbability_for_Clasffier(dbNO_obese_Count,diabeticNo_Count ,parameter_3);
		Pdiabetic_NO_overweight = NB.computeProbability_for_Clasffier(dbNO_overweight_Count,diabeticNo_Count ,parameter_3);
		Pdiabetic_NO_normalweight = NB.computeProbability_for_Clasffier(dbNO_normalweight_Count,diabeticNo_Count ,parameter_3);
		Pdiabetic_NO_underweight = NB.computeProbability_for_Clasffier(dbNO_underweight_Count,diabeticNo_Count ,parameter_3);

		Pdiabetic_Yes_BShigh = NB.computeProbability_for_Clasffier(dbYes_BShigh_Count,diabeticYes_Count ,parameter_3);
		Pdiabetic_Yes_BSnormal = NB.computeProbability_for_Clasffier(dbYes_BSnormal_Count,diabeticYes_Count ,parameter_3);
		Pdiabetic_Yes_BSlow = NB.computeProbability_for_Clasffier(dbYes_BSlow_Count,diabeticYes_Count ,parameter_3);
	    Pdiabetic_NO_BShigh = NB.computeProbability_for_Clasffier(dbNO_BShigh_Count,diabeticYes_Count ,parameter_3);
		Pdiabetic_NO_BSnormal = NB.computeProbability_for_Clasffier(dbNO_BSnormal_Count,diabeticYes_Count ,parameter_3);
		Pdiabetic_NO_BSlow = NB.computeProbability_for_Clasffier(dbNO_BSlow_Count,diabeticYes_Count ,parameter_3);
		
		Pdiabetic_Yes_AChigh = NB.computeProbability_for_Clasffier(dbYes_AChigh_Count,diabeticYes_Count ,parameter_3);
		Pdiabetic_Yes_ACmoderate = NB.computeProbability_for_Clasffier(dbYes_ACmoderate_Count,diabeticYes_Count ,parameter_3);
		Pdiabetic_Yes_ACpoor = NB.computeProbability_for_Clasffier(dbYes_ACpoor_Count,diabeticYes_Count ,parameter_3);
		Pdiabetic_NO_AChigh = NB.computeProbability_for_Clasffier(dbNO_AChigh_Count,diabeticYes_Count ,parameter_3);
		Pdiabetic_NO_ACmoderate = NB.computeProbability_for_Clasffier(dbNO_ACmoderate_Count,diabeticYes_Count ,parameter_3);
		Pdiabetic_NO_ACpoor = NB.computeProbability_for_Clasffier(dbNO_ACpoor_Count,diabeticYes_Count ,parameter_3);
		
		System.out.println("diabeticYes_Count: " + diabeticYes_Count);
		System.out.println("diabeticNo_Count: " + diabeticNo_Count);
		System.out.println("dbYes_obese_Count :" + dbYes_obese_Count);
		System.out.println ("dbYes_overweight_Count : " + dbYes_overweight_Count);
		System.out.println ("dbYes_normalweight_Count : " + dbYes_normalweight_Count);
		System.out.println ("dbYes_underweight_Count : " + dbYes_underweight_Count);
		System.out.println ("dbNO_obese_Count : " + dbNO_obese_Count);
		System.out.println ("dbNO_overweight_Count : " + dbNO_overweight_Count);
		System.out.println ("dbNO_normalweight_Count : " + dbNO_normalweight_Count);
		System.out.println ("dbNO_underweight_Count : " + dbNO_underweight_Count);
		
		System.out.println("Pdiabetic_Yes_obese :"  + Pdiabetic_Yes_obese);
	    System.out.println("	Pdiabetic_Yes_underweight :"	 + Pdiabetic_Yes_underweight);
	    System.out.println("	Pdiabetic_NO_obese :" + Pdiabetic_NO_obese);
	    
	    System.out.println("Pdiabetic_Yes_BShigh : " + Pdiabetic_Yes_BShigh);
	    System.out.println("Pdiabetic_NO_BShigh : " + Pdiabetic_NO_BShigh);
	    
	    System.out.println("Pdiabetic_Yes_AChigh : " + Pdiabetic_Yes_AChigh);
	    System.out.println("Pdiabetic_NO_AChigh : " + Pdiabetic_NO_AChigh);
	    // Using bayes  formulae compute the probability of each diabetic classifier (yes or no) for the given attributes
	    // which ever has greater probability will be as output of greater likelihood of the scenarion in near future.
	    while (fileD.hasNextLine()) {
	    	    float pWeight_Yes = 0;
	    	    float pWeight_NO = 0;
	    	    float pBS_Yes = 0;
	    	    float pBS_NO = 0;
	    	    float pAC_Yes = 0;
	    	    float pAC_No = 0;
	    
			dString = fileD.nextLine();
			String dWeightClassfier = NB.findWeightClasffier(dString);
			String dBlodSugarClasffier = NB.findBlodSugarClasffier(dString);
			String dActivityClasffier = NB.findActivityClasffier(dString);
			switch (dWeightClassfier) {
			  case "obese" : pWeight_Yes = Pdiabetic_Yes_obese; pWeight_NO = Pdiabetic_NO_obese;
			    break;
			  case "over" :   pWeight_Yes = Pdiabetic_Yes_overweight; pWeight_NO = Pdiabetic_NO_overweight;
			    break;
			  case "normal" : pWeight_Yes = Pdiabetic_Yes_normalweight; pWeight_NO = Pdiabetic_NO_normalweight;
			    break;
			  case "under" :   pWeight_Yes = Pdiabetic_Yes_underweight; pWeight_NO = Pdiabetic_NO_underweight;
			    break;
		    }
			switch (dBlodSugarClasffier) {
			  case "BS_high" :pBS_Yes = Pdiabetic_Yes_BShigh;pBS_NO = Pdiabetic_NO_BShigh;
			    break;
			  case "BS_normal" :   pBS_Yes = Pdiabetic_Yes_BSnormal;pBS_NO = Pdiabetic_NO_BSnormal;
			    break;
			  case "BS_low" : pBS_Yes = Pdiabetic_Yes_BSlow;pBS_NO = Pdiabetic_NO_BSlow;
			    break;
			}
			switch (dActivityClasffier) {
			  case "AC_high" : pAC_Yes = Pdiabetic_Yes_AChigh;pAC_No = Pdiabetic_NO_AChigh;
			    break;
			  case "AC_moderate" :   pAC_Yes = Pdiabetic_Yes_ACmoderate;pAC_No = Pdiabetic_NO_ACmoderate;
			    break;
			  case "AC_poor" : pAC_Yes = Pdiabetic_Yes_ACpoor;pAC_No = Pdiabetic_NO_ACpoor;
			    break;
			}
		 float yes_dnumber =  pWeight_Yes * pBS_Yes * pAC_Yes * Pdiabetic_Yes;
		 float no_dnumber  =  pWeight_NO * pBS_NO * pAC_No * Pdiabetic_No;
		 
		 if (yes_dnumber > no_dnumber) {
			 System.out.println("Input record :" + dString + " has HIGH likelihood of developing diabetes in the near future" );
		 } else {
			 System.out.println("Input record :" + dString + " has LOW likelihood of developing in the diabetes in near future" );
		 }
		 System.out.println("pWeight_Yes : " + pWeight_Yes);
		 System.out.println("pBS_Yes : " + pBS_Yes);
		 System.out.println("pAC_Yes : " + pAC_Yes);
		 System.out.println("Pdiabetic_Yes : " + Pdiabetic_Yes);
		 System.out.println("yes_dnumber : " + yes_dnumber);
		 
		 System.out.println(" " );
		 System.out.println("pWeight_NO : " + pWeight_NO);
		 System.out.println("pBS_NO : " + pBS_NO);
		 System.out.println("pAC_No : " + pAC_No);
		 System.out.println("no_dnumber : " + no_dnumber);
		 
	}
	    fileD.close();
	}
}
