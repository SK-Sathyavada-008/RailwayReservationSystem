package RailwayReservationSystem;




//node class

class Node{

	User data;

	Node link;

	Node(User u){

		this.data = u;

		this.link = null;

	}

}



public class DSA_Applications {

	

	// users linked list 

	Node head = null;

	public void insertNode(User u) {

		Node t = new Node(u);

		if(head == null) {

			head = t;

		} else {

			Node temp = head;

			while(temp.link != null) {

				temp = temp.link;

			}

			temp.link = t;

		}

	}

	

	// linear search 

	public int linearSearch(String[] a, String key) {

		int n = a.length;

		for(int i = 0; i<n; i++) {

			if(a[i] != null && a[i].equalsIgnoreCase(key)) {

				return i;

			}

		}

		return -1;

	}

	

	// binary search 

	public int binarySearch(int[]a, int key) {

		int si = 0;

		int ei = a.length - 1;

		while(si <= ei) {

			int mi = si + (ei - si) / 2;

			if(a[mi] == key) {

				return mi;

			} else if(a[mi] < key) {

				si = mi + 1;   

			} else {

				ei = mi - 1; 

			}

		}

		return -1;

	}

	

	// bubble sort 

	public String[] bubbleSort(String[]a) {

		int n = a.length;

		for(int i = 0; i<n-1; i++) {

			for(int j = i+1; j<n; j++) {

				if(a[i].compareTo(a[j]) > 0) {

					String temp = a[i];

					a[i] = a[j];

					a[j] = temp;

				}

			}

		}

		return a;

	}

	

	// insertion sort 

	public String[] insertionSort(String[]a) {

		int n = a.length;

		for(int i = 1; i<n; i++) {

			String temp = a[i];

			int j = i - 1;

			while(j >= 0 && a[j].compareToIgnoreCase(temp) > 0) {

				a[j+1] = a[j];

				j--;

			}

			a[j+1] = temp;

		}

		return a;

	}



	//quick sort

	public String[] quicksort(String[]a, int si, int ei) {

		if(si < ei) {   

			int pi = partition(a, si, ei);

			quicksort(a, si, pi - 1);

			quicksort(a, pi+1, ei);

		}

		return a;

	}	

	public int partition(String[]a, int si, int ei) {

		String pivot = a[si];

		int i = si;

		int j = ei;



		while(i < j) {

			while(i <= ei && a[i].compareToIgnoreCase(pivot) <= 0) i++;

			while(a[j].compareToIgnoreCase(pivot) > 0) j--;

			if(i < j) swap(a, i, j);

		}

		swap(a, si, j);

		return j;

	}

	public void swap(String[]a, int i, int j) {

		String temp = a[i];

		a[i] = a[j];

		a[j] = temp;

	}

	

	

}