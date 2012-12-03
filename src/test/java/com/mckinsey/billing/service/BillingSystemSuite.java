package com.mckinsey.billing.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.mckinsey.billing.discount.OrderBasedDiscountTest;
import com.mckinsey.billing.discount.UserBasedDiscountTest;

/**
 * Suite class containing the list of all the Test classes that needs to be
 * executed to validate the different test cases.
 * 
 * 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( { BillingServiceImplTest.class, OrderBasedDiscountTest.class, UserBasedDiscountTest.class })
public class BillingSystemSuite {
}
