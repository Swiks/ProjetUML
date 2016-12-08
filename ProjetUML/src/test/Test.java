package test;

public class Test {

	public static void main(String[] args) {
		String s = "a	b	".replaceAll("\t", " ").trim();
		System.out.println(s);
		System.out.println(s.equals("a b"));
	}

}
