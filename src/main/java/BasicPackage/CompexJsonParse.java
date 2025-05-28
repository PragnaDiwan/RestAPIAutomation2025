package BasicPackage;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class CompexJsonParse {
	
	
	public static void main(String [] args)
	{
		JsonPath js=new JsonPath(Payload.complexJson());   //1. Print No of courses returned by API

			int count =js.getInt("courses.size()");	
				System.out.println(count);								
				

			int purchaseamt=js.getInt("dashboard.purchaseAmount");		//2.Print Purchase Amount											
			System.out.println(purchaseamt);										
			
			
			String title=js.getString("courses[0].title"); //3. Print Title of the first course
			System.out.println(title);										

			for(int i=0;i<count;i++)	
			{
				   String alltitle=js.getString("courses["+i+"].title") ;
					System.out.println(alltitle);										
                     //4. Print All course titles and their respective Prices
			}


			for(int i=0;i<count;i++)
			{
				String RPATitle=js.getString("courses["+i+"].title");
				if(RPATitle.equalsIgnoreCase("RPA"))
				{
					int copiesRPA=js.getInt("courses["+i+"].copies");
					System.out.println(copiesRPA);
					break;
				}
				
			}//5. Print no of copies sold by RPA Course
			
			 int sumprice=0;
			for(int i=0;i<count;i++)
			{  
				int getcopies=js.getInt("courses["+i+"].copies");
				int getprice=js.getInt("courses["+i+"].price");
				int getpurchaseprice=getcopies*getprice;
				System.out.println("Purchase price is:"+getpurchaseprice);

				 sumprice=sumprice+getpurchaseprice;
			}
			System.out.println("Purchase price is:"+sumprice);
			
			int purchaseprice=js.getInt("dashboard.purchaseAmount");
			
			if(sumprice==purchaseprice)
			{
				System.out.println("Purchase price is match:"+sumprice);

			}
			
			//6. Verify if Sum of all Course prices matches with Purchase Amount
																			
							

	}

}
