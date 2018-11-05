
public class StrCompression {

	public static void main(String[] args) {
		
		StrCompression s = new StrCompression();
		
		// test cases
		System.out.println(s.compressStr("aabcccccaaa").equals("a2b1c5a3"));
		System.out.println(s.compressStr("abc").equals("abc"));
		System.out.println(s.compressStr("aabcccccaaa").equals("a2b1c5a3"));
		System.out.println(s.compressStr("aabcccccaaa").equals("a2b1c5a3"));
		System.out.println(s.compressStr("aabcccccaaa").equals("a2b1c5a3"));
		System.out.println(s.compressStr("aabcccccaaa").equals("a2b1c5a3"));
		System.out.println(s.compressStr("aabcccccaaa").equals("a2b1c5a3"));
		System.out.println(s.compressStr("aabcccccaaa").equals("a2b1c5a3"));
		System.out.println(s.compressStr("aabcccccaaa").equals("a2b1c5a3"));
		System.out.println(s.compressStr("aabcccccaaa").equals("a2b1c5a3"));
	}

	public String compressStr(String str){
		String compressed = "";
		StringBuilder sb = new StringBuilder();
		
		char[] chars = str.toCharArray();
		int freq = 1;
		int start = 0;
		
		for (int i = 0; i < chars.length-1; i++){
			if (chars[i] == chars[i+1]) freq++;
			else{	// current != next char
				sb.append(chars[start]);
				sb.append(freq);
				
				// reset values for next iteration
				start = i+1;
				freq = 1;
			}
		}
		// append last
		sb.append(chars[start]);
		sb.append(freq);
		
		compressed = sb.toString();
		
		// return original input if compressed string is larger
		if (compressed.length() > str.length()) return str;
		
		return compressed;
	}
}
