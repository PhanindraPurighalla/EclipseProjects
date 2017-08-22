package com.amazonaws.sns.samples.mobilepush;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent.DynamodbStreamRecord;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;

public class DDBEventProcessor implements RequestHandler<DynamodbEvent, String> {

	public String handleRequest(DynamodbEvent ddbEvent, Context context) {
		for (DynamodbStreamRecord record : ddbEvent.getRecords()) {

			System.out.println("===========================================\n");
			System.out.println("DynamoDB Stream Record:\n");
			System.out.println(record.getEventID());
			System.out.println(record.getEventName());
			System.out.println(record.getDynamodb().toString());

		}

		try {
			System.out.println("===========================================\n");
			System.out.println("Now invoking sendPushNotification()");
			sendPushNotification();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "Successfully processed " + ddbEvent.getRecords().size() + " records.";
	}

	public List<DeviceToken> fetchAllDeviceTokens() {
		
		List<DeviceToken> deviceTokens = new ArrayList<DeviceToken>();
		
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
		ScanResult result = null;
		 
	    do{
	        ScanRequest req = new ScanRequest();
	        req.setTableName("DeviceTokens");
	 
	        if(result != null){
	            req.setExclusiveStartKey(result.getLastEvaluatedKey());
	        }
	         
	        result = client.scan(req);
	 
	        List<Map<String, AttributeValue>> rows = result.getItems();
	 
	        for(Map<String, AttributeValue> map : rows){
	            try{
	            		DeviceToken deviceToken = new DeviceToken();
	            		deviceToken.setDeviceID(map.get("DeviceID").getS());
	            		deviceToken.setDeviceToken(map.get("DeviceToken").getS());
	            		deviceToken.setUserType(map.get("UserType").getS());
	                
	            	    deviceTokens.add(deviceToken);
	            } catch (NumberFormatException e){
	                System.out.println(e.getMessage());
	            }
	        }
	    } while(result.getLastEvaluatedKey() != null);
	     
	    return deviceTokens;
	}
	
	public void sendPushNotification() throws IOException {
		/*
		 * TODO: Be sure to fill in your AWS access credentials in the
		 * AwsCredentials.properties file before you try to run this sample.
		 * http://aws.amazon.com/security-credentials
		 */
		AmazonSNS sns = new AmazonSNSClient(
				new PropertiesCredentials(SNSMobilePush.class.getResourceAsStream("AwsCredentials.properties")));

		sns.setEndpoint("https://sns.us-west-2.amazonaws.com");
		System.out.println("===========================================\n");
		System.out.println("Getting Started with Amazon SNS to publish to iOS App");
		System.out.println("===========================================\n");
		try {
			SNSMobilePush sample = new SNSMobilePush(sns);
			/* TODO: Uncomment the services you wish to use. */
			// sample.demoAndroidAppNotification();
			// sample.demoKindleAppNotification();
			sample.demoAppleAppNotification();
			// sample.demoAppleSandboxAppNotification();
			// sample.demoBaiduAppNotification();
			// sample.demoWNSAppNotification();
			// sample.demoMPNSAppNotification();
		} catch (AmazonServiceException ase) {
			System.out.println("Caught an AmazonServiceException, which means your request made it "
					+ "to Amazon SNS, but was rejected with an error response for some reason.");
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			System.out.println("Caught an AmazonClientException, which means the client encountered "
					+ "a serious internal problem while trying to communicate with SNS, such as not "
					+ "being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}
	}

}