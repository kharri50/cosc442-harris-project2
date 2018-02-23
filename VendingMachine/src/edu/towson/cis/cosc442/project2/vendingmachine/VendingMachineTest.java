package edu.towson.cis.cosc442.project2.vendingmachine;

import static org.junit.Assert.*;

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
	
	/* The following is a code segment 
	
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		 vm = new VendingMachine();
		 vmi = new VendingMachineItem("Test Item", 5.00);
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
		/* basically what we're doing here is adding 16 items 
		 * and if that works, then adding works. Ya know?
		 */
		ArrayList<VendingMachineItem> item_list = new ArrayList<VendingMachineItem>();
		
		// just for fun, lets make an array of 4 items with random prices
		int item_count = 5;
		Random r = new Random();
		for(int i = 0; i<item_count; i++) {
			// random price from 1 - 8 
			double randomPrice =  1 + (8 * r.nextDouble());
			// make a random name for the testing item 
			String itemName = "Testing Item "+ Integer.toString(i);
			// add the item to the arraylist 
			item_list.add(new VendingMachineItem(itemName, randomPrice));
		}
		assertEquals(item_list.size(),item_count);

		String [] codes = {"A", "B","C","D"};

		// loop through and add to the arraylist
		for(int i = 0; i<item_count-1; i++) {
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
	public void removeItemTest() {
		
	}
	
	
}
