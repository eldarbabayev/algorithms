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
	
	int n = xs.size();
	if (n == 1) {
	    List<Integer> empty = Collections.emptyList();
	    xss.add(xs);
	    xss.add(empty);
	    return xss;
	} else {
	    int half = (int) Math.round(n/2.0);
	    List<Integer> A = xs.subList(0, half);
	    List<Integer> B = xs.subList(half, n);
	    xss.add(A);
	    xss.add(B);
	    return xss;
	}
    }

    List<Integer> merge(List<Integer> B, List<Integer> C) {
	int len_b = B.size();
	int len_c = C.size();
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

    long merge_count_inv(List<Integer> B, List<Integer> C) {
	int len_b = B.size();
	int len_c = C.size();
	List<Integer> D = new ArrayList<Integer>();
	int i = 0;
	int j = 0;

	int num_inv = 0;
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
	return num_inv;
    }


    List<Integer> sort(List<Integer> xs) {
	if (xs.size() == 0) {
	    List<Integer> empty = Collections.emptyList();
	    return empty;
	} else if (xs.size() == 1) {
	    return xs;
	} else {
	    List<List<Integer>> AB = split(xs);
	    return merge(sort(AB.get(0)), sort(AB.get(1)));
	}
    }

    long sort_and_count(List<Integer> A, int n) {
	if (n == 1) {
	    return 0;
	} else {
	    List<List<Integer>> BC = split(A);
	    long X = sort_and_count(BC.get(0), BC.get(0).size());
	    long Y = sort_and_count(BC.get(1), BC.get(0).size());
	    long Z = count_split_inv(A, n);
	    return X + Y + Z;
	}
    }

    long count_split_inv(List<Integer> A, int n) {
	List<List<Integer>> BC = split(A);
	List<Integer> B_sorted = sort(BC.get(0));
	List<Integer> C_sorted = sort(BC.get(1));
	return merge_count_inv(B_sorted, C_sorted);
    }

    public static void main(String [] args) {
	prac1 pr = new prac1();
	List<Integer> ints = pr.load_data();
	List<Integer> small = new ArrayList<>();
	small.add(9);
	small.add(12);
	small.add(3);
	small.add(1);
	small.add(6);
	small.add(8);
	small.add(2);
	small.add(5);
	small.add(14);
	small.add(13);
	small.add(11);
	small.add(7);
	small.add(10);
	small.add(4);
	small.add(0);
	System.out.println(ints);
	long ans = pr.sort_and_count(ints, ints.size());
	System.out.println(ans);
    }
}


