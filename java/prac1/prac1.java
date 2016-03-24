import java.io.*;
import java.util.*;


public class prac1 {

    List<Integer> load_data() {

	String filename = "IntegerArray.txt";
	List<Integer> ints = new ArrayList<Integer>();
	
	try {
	    FileInputStream fstream = new FileInputStream(filename);
	
	    DataInputStream in = new DataInputStream(fstream);
	    BufferedReader br = new BufferedReader(new InputStreamReader(in));
	    String num;
	    
	    while ((num = br.readLine()) != null) {
		ints.add(Integer.parseInt(num));
	    }

	    in.close();

	    return ints;
	    
	} catch (Exception e) {
	    System.err.println("Error: " + e.getLocalizedMessage());
	    return null;
	}
    }
    
    List<List<Integer>> split(List<Integer> xs) {
	List<List<Integer>> xss = new ArrayList<List<Integer>>();
	
	int n = xs.length();
	if (n == 1) {
	    List<Integer> empty = new ArrayList<Integer>();
	    xss = xss.add(xs);
	    xss = xss.add(empty);
	    return xss;
	} else {
	    int half = (int) Math.round(n/2);
	    List<Integer> A = xs.sublist(0, half);
	    List<Integer> B = xs.sublist(half, n);
	    xss = xss.add(A);
	    xss = xss.add(B);
	    return xss;
	}
    }

    List<Integer> merge(List<Integer> B, List<Integer> C) {
	int len_b = B.length();
	int len_c = C.length();
	List<Integer> D = new ArrayList<Integer>();

	int i = 0;
	int j = 0;

	for (int k = 0; k < (len_b + len_c); k++) {
	    if (i == len_b) {
		D.add(C.get(j));
		j += 1;
	    } else if (j == len_c) {
		D.add(B.get(i));
		i += 1;
	    } else if (B.get(i) > C.get(j)) {
		D.add(C.get(j));
		j += 1;
	    } else if (B.get(i) < C.get(j)) {
		D.add(B.get(i));
		i += 1;
	    }
	}

	return D;
    }

    List<Integer> merge_count_inv(List<Integer> B, List<Integer> C) {
	int len_b = B.length();
	int len_c = C.length();
	List<Integer> D = new ArrayList<Integer>();

	int i = 0;
	int j = 0;

	num_inv = 0;
	for (int k = 0; k < (len_b + len_c); k++) {
	    if (i == len_b) {
		D.add(C.get(j));
		j += 1;
	    } else if (j == len_c) {
		D.add(B.get(i));
		i += 1;
	    } else if (B.get(i) > C.get(j)) {
		D.add(C.get(j));
		j += 1;
		num_inv = num_inv + (len_b - i);
	    } else if (B.get(i) < C.get(j)) {
		D.add(B.get(i));
		i += 1;
	    }
	}

	return D;
    }


    void sort(List<Integer> xs) {
	if (xs.length() == 0) {
	    List<Integer> empty = new ArrayList<Integer>();
	    return empty;
	} else if (xs.length() == 1) {
	    return xs;
	} else {
	    List<List<Integer>> AB = split(xs);
	    return merge(sort(AB.get(0)), sort(AB.get(1)));
	}
    }

    void sort_and_count(List<Integer> A, int n) {
	if (n == 1) {
	    return 0;
	} else {
	    List<List<Integer>> BC = split(A);
	    int X = sort_and_count(BC.get(0), BC.get(0).length());
	    int Y = sort_and_count(BC.get(1), BC.get(0).length());
	    int Z = count_split_inv(A, n);
	    return X + Y + Z;
	}
    }

    int count_split_inv(List<Integer> A, int n) {
	List<List<Integer>> BC = split(A);
	List<Integer> B_sorted = sort(BC.get(0));
	List<Integer> C_sorted = sort(BC.get(1));
	return merger_count_inv(B_sorted, C_sorted);
    }

    public static void main(String [] args) {
	prac1 pr = new prac1();
	List<Integer> ints = pr.load_data();
	int ans = pr.sort_and_count(ints, ints.length());
	System.out.println(ans);
    }
}


