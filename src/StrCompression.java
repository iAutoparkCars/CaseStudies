
public class StrCompression {

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		sb.append(4);
		System.out.println(sb.toString());
		
		StrCompression s = new StrCompression();
		//s.compress("aabcccccaaa");
		
	}

	public String compressStr(String str){
		String result = "";
		StringBuilder sb = new StringBuilder();
		
		
		
		result = sb.toString();
		
		// return original input if compressed string is larger
		if (result.length() > str.length()) return str;
		
		return result;
	}
	
	public String compress(String str){
		char[] chars = str.toCharArray();
		
        int counter = 0;
        for (int i = 0; i < chars.length; i++){
            // last element
            if (i == chars.length-1){
                chars[counter] = chars[i]; counter++;
                continue;
            }

            char key = chars[i];
            int freq = 1;

            // current is different than the next OR current is last char in array
            if (key != chars[i+1]){
                chars[counter] = key; counter++;
                chars[counter] = (char)(freq+'0'); counter++;
                continue;
            }

            int j = i+1;
            while(j < chars.length && key == chars[j]){
                freq++;
                j++;
            }

            for (char ch : (key + Integer.toString(freq)).toCharArray() ){
                chars[counter] = ch;
                counter++;
            }

            // rest values for next iteration, if you do i=j, then i will go i++ and it'll skip
            // because of the for loop
            freq = 1;
            i = j-1;
        }
		
        System.out.println(getString(counter, chars));
		return getString(counter, chars);
	}
	
	public String getString(int max, char[] chars){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < max; i++)
			sb.append(chars[i]);
		return sb.toString();
	}
}
