package minor.server;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class CardDistributer {

	public Set getCardNumber() {
		Random r = new Random();
		
		Set s1 = new LinkedHashSet();
		int  size = 0;
		while (size != 43) {
			int num = r.nextInt(44);
			s1.add(num);
			size = s1.size();
		}
		System.out.println("set1=" +s1);

		return s1;
	}


}
