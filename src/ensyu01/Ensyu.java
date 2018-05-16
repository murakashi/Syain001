package ensyu01;

import java.util.ArrayList;

public class Ensyu {

	public static void main(String[] args) {

		ArrayList<String> arr = new ArrayList<String>();

		arr.add("テスト1");
		arr.add("テスト2");
		arr.add("テスト3");

		for(int i=0;i<arr.size();i++) {
			System.out.println(arr.get(i));
		}

		/*拡張for文
		for(String s : arr) {
			System.out.println(s);
		}*/



	}
}
