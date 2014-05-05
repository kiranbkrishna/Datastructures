package com.kiran.datastructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SortedLinkedList {
	class Node {
		private Object key;
		private int value;
		private Node next;
		public Node(Object k, int v) {
			this.key = k;
			this.value = v;
		}
	}

	private Node head = null;
	private boolean noDuplicates = true;

	private void add(Object key, int value) {
		System.out.println("add key == " + key);
		Node temp = head;
		if (temp == null) {
			Node n = new Node(key, value);
			n.next = null;
			head = n;
		}
		else {
			Node temp2 = head;
			while (temp != null && temp.value > value) {
//				System.out.println("temp.key " + temp.key );
//				System.out.println("key = " + key 
//						+ "\n --" + temp.key.equals(key));
				temp2 = temp; 
				temp = temp.next;
			}
			Node n = new Node(key, value);
			if (temp2.value > value) {
				// insert after
				n.next = temp;
				temp2.next = n;
			}
			else {
				// insert before
				if (temp2 == head) {
					n.next = head;
					head = n;
				}
				else {
					Node finder1 = head, finder2 = null;
					while(finder1 != temp2) {
						finder2 = finder1;
						finder1 = finder1.next;
					}
					n.next = finder1;
					finder2.next = n;
				}
			}
		}
		if (noDuplicates) {
			Node first = null, second = null;
			Node temp1 = head;
			Node temp2 = null;
			while (temp1 != null) {
				if (temp1.key.equals(key)) {
					System.out.println("*");
					if (first == null) 
						first = temp;
					else  {
						second = temp;
						break;
					}
				}
				temp2 = temp1;
				temp1 = temp1.next;
			}
			
			if (first != null && second != null) {
					System.out.println("duplicates == " + first + "\n" + second);
					System.out.println("duplicates == " + first.value + "\n" + second.value);
					if (first.value >= second.value) {
						remove(second);
					}
					else {
						remove(first);
					}
			} 
			else {
				System.out.println("no duplicates");
			}
		}
	}

	private void remove(Node node) {
		System.out.println( "deleting element with data " + node.value);
		Node temp = head;
		Node temp2 = null;
		if (node == head) {
			head = null;
			return;
		}
			
		while (temp != null) {
			if (node == temp) {
				temp2.next = temp.next;
				System.out.println( "deleting element with data " + temp.value);
				return;
			}
			temp2 = temp;
			temp = temp.next;
		}
	}
	
	private void setDuplicatesAllowed(boolean b) {
		this.noDuplicates = b;
	}
	

	private void printList() {
		Node temp = head;
		while (temp != null) {
			System.out.print("" + temp.key + ": " + temp.value );
			if (temp.next != null)
				System.out.print(" --> ");
			temp = temp.next;
		}
	}

	public static void main(String[] args) {
		SortedLinkedList sl = new SortedLinkedList();
		while (true) {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("Enter key :");
				String key = br.readLine();
				System.out.println("Enter integer: ");
				String value = br.readLine();
				Integer valueInt = Integer.parseInt(value);
				sl.add(key, valueInt.intValue());
				System.out.println("Current list");
				sl.printList();
				System.out.println("\n");
				
			} catch (Exception ioe) {
				System.out.println("Somehitng went wrong. Try again.");
			}
		}
	}
}
