package edu.towson.cis.cosc442.project2.vendingmachine;

import static org.junit.Assert.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class VendingMachineTest.
 */
public class VendingMachineTest {

	/** The vm. */
	private VendingMachine vm;
	
	/** The vmi. */
	private VendingMachineItem vmi;
	
	/** list of Vendingmachine items implemented as an ArrayList **/
	
	private ArrayList<VendingMachineItem> item_list;
	
	private String [] codes = {"A", "B","C","D"};


	 
	/* The following is a code segment 
	
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		// create a new vending machine object 
		vm = new VendingMachine();
		 
		item_list = new ArrayList<VendingMachineItem>();
		 
		// just for fun, lets make an array of 5 items with random prices
		int item_count = 5;
		Random r = new Random();
		DecimalFormat f = new DecimalFormat("##.00");

		for(int i = 0; i<item_count; i++) {
			// random price from 1 - 8 
			double randomPrice =  1 + (8 * r.nextDouble());
			double roundedPrice = Math.round(randomPrice);
			// make a random name for the testing item 
			String itemName = "Testing Item "+ Integer.toString(i);
			// add the item to the list 
			item_list.add(new VendingMachineItem(itemName, roundedPrice));
		}
		assertEquals(item_list.size(),item_count);
		
	}

	
	/**
	 * Tear down.
	 *
	 * @throws Exception the exception
	 */
	@After
	public void tearDown() throws Exception {
		vm = null;
		vmi = null;
	}

	/**
	 * Tests the addition of an item to the vending machine.
	 */
	@Test 
	public void addItemTest(){
	
		// loop through and add to the arraylist
		for(int i = 0; i<item_list.size()-1; i++) {
			vm.addItem(item_list.get(i), codes[i]);
		}
		
		// try to add a new item and catch the exception.. for each slot!
		for(int i = 0; i<4; i++) {
			boolean thrown = false;
			try {
				vm.addItem(item_list.get(i), codes[i]);
			}catch(VendingMachineException e) {
				// error caught 
				thrown = true;
			}
			// the exception was thrown
			assertTrue(thrown);
		}
	}
	
	@Test 
	public void removeItemTest() throws Exception {
		// add the items to the vending machine
		addItemTest();
		
		/* this is going to test removal of all items in the vending machine 
		 * from each slot */
	
		for(int i =0; i<codes.length; i++) {
			VendingMachineItem testItem = vm.removeItem(codes[i]);
			// verify that it matches the ArrayList item at the given index.
			assertEquals(testItem, item_list.get(i));
		}
		
		
		//try to remove an item and catch the exception.. for each slot!
				for(int i = 0; i<4; i++) {
					boolean thrown = false;
					try {
						vm.removeItem(codes[i]);
					}catch(VendingMachineException e) {
						// error caught 
						String expectedMessage = "Slot "+codes[i]+" is empty -- cannot remove item";
						// check that the exception message is correct.. 
						assertEquals(e.getMessage(), expectedMessage);
						thrown = true;
					}
					// the exception was thrown
					assertTrue(thrown);
				}
	}
	
	/** Tests the getItem function in the VendingMachine class **/
	@Test 
	public void getItem() {
		// add the items to the vending machine
		addItemTest();
		/* this loop does pretty much the same this as everything else */
		testItemEquality();
		// now test that it throws an exception 
		String invalid = "E";
		boolean thrown = false;
		try {
			vm.getItem(invalid);
		}catch(VendingMachineException e) {
			thrown =  true;
		}
		assertTrue(thrown);
			
	}


	private void testItemEquality() {
		for(int i =0; i<codes.length; i++) {
			VendingMachineItem testItem = vm.getItem(codes[i]);
			// verify that it matches the ArrayList item at the given index.
			assertEquals(testItem, item_list.get(i));
		}
	}
	
	
	@Test 
	public void testInsertMoney() {
		// using the vending machine object in setup 
		double amount = 3.50;
		vm.insertMoney(amount);
		// now we're going to test the balance
		
		boolean thrown = false;
		try {
			vm.insertMoney(0);
		}catch(VendingMachineException e ) {
			thrown = true;
		}
		assertTrue(thrown);
		
		
		boolean thrown_2 = false;
		try {
			vm.insertMoney(-.001);
		}catch(VendingMachineException e ) {
			thrown_2 = true;
		}
		assertTrue(thrown_2);
		
		// now test against the good value that was inserted
		// using checkBalance
		assertEquals(vm.getBalance(), amount, .001);
	}
	
	
	@Test 
	public void testMakePurchase() {
		// okay, now we're going to purchase things
		addItemTest(); // add items to the vending machine 
		
		// loop through the ArrayList to get the needed amount of money
		double required_amount = 0;
		for(int i = 0; i<item_list.size()-1; i++) {
			required_amount+=item_list.get(i).getPrice();
		}
		
		double expected_amount = required_amount; 

		vm.insertMoney(required_amount);
		assertEquals(vm.getBalance(), required_amount, .001);

		/* now we should be able to buy everything with the 
		 * current balance
		 */ 
		
		// all codes should be filled, so purchase each one 
		int i = 0;
		for(String code : codes) {
			if(vm.makePurchase(code)) {
				/* subtract the expected balance if the purchase
				   is successful */
				expected_amount -= item_list.get(i).getPrice();
			}
			i++;
		}
		assertEquals(expected_amount, 0, .001);
		// now check that the balance is equal to the expected amount
		assertEquals(vm.getBalance(), expected_amount, .001);
		System.out.println(item_list.get(4).getPrice());
		System.out.println(vm.getBalance());
		
		// now try to remove the last item 
		//assertTrue(vm.makePurchase(code))
	}
	
	@Test 
	public void testReturnChange() {
		addItemTest();
		
		double required_amount = 0;
		for(int i = 0; i<item_list.size(); i++) {
			required_amount+=item_list.get(i).getPrice();
		}
		
		double expected_amount = required_amount; 

		vm.insertMoney(required_amount);
		assertEquals(vm.getBalance(), required_amount, .001);

		/* now we should be able to buy everything with the 
		 * current balance
		 */ 
		
		// all codes should be filled, so purchase each one 
		int i = 0;
		for(String code : codes) {
			if(vm.makePurchase(code)) {
				/* subtract the expected balance if the purchase
				   is successful */
				expected_amount -= item_list.get(i).getPrice();
			}
			i++;
		}
	
	
	// get the change and make sure it's equal to the expected amount
	  assertEquals(expected_amount,vm.returnChange(),.001);
	}
}
