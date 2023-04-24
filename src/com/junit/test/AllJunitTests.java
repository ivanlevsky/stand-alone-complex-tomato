package com.junit.test;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;
import org.junit.platform.suite.api.Suite;

/***
 * //select classes: @SelectClasses({StringUtilsTest.class, CryptoUtilsTest.class})
 *  annotation equals below
 * //select package: @SelectPackages("com.junit.test")
 * //exclude class endwith "WindowsOsUtilsTest": @ExcludeClassNamePatterns({"^.*WindowsOsUtilsTest?$"})
 */
// @RunWith(JUnitPlatform.class)  -> junit4
@Suite
@SuiteDisplayName("JUnit Platform Suite Demo")
@SelectPackages("com.junit.test")
public class AllJunitTests {

}
