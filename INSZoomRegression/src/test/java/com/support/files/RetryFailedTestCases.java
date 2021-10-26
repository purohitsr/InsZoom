package com.support.files;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTestCases implements IRetryAnalyzer{
private int retryCount =0;
private int maxRetryCount=1;
    @Override
    public boolean retry(ITestResult result) {
	if(retryCount<maxRetryCount){
	    retryCount++;
	    return true;
	}
	return false;
    }

}
