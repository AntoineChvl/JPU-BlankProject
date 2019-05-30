package com.entity.mobileelements;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import entity.EntityTest;

/**
 * The diamond test class.
 * @author Antoine Chauvel
 * @version 1.0
 *
 */
public class DiamondTest extends EntityTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Instantiates a new diamond.
	 *
	 */
	@Before
	public void setUp() throws Exception {
		this.entity = new Diamond(4,4);
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * The testDiamond method.
	 * Checks the basic sprite name, character and strategy of the diamond.
	 */
	@Test
	public void testDiamond() {
		super.testEntityBasicCharacteristics();
		final String expectedSpriteName = "Diamond.png";
		final char expectedCharName = 'x';
		final String expectedStrategy = "BasicFalling";
		assertEquals(expectedCharName, this.entity.getSprite().getConsoleImage());
		assertEquals(expectedSpriteName, this.entity.getSprite().getImageName());
		assertEquals(expectedStrategy, this.entity.getStrategy().returnStrategy());
	}
}
