package com.amazonaws.sns.samples.mobilepush;

/*
 * Copyright 2014 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.sns.samples.tools.AmazonSNSClientWrapper;
import com.amazonaws.sns.samples.tools.SampleMessageGenerator.Platform;

public class SNSMobilePush {

	private AmazonSNSClientWrapper snsClientWrapper;

	public SNSMobilePush(AmazonSNS snsClient) {
		this.snsClientWrapper = new AmazonSNSClientWrapper(snsClient);
	}

	public static final Map<Platform, Map<String, MessageAttributeValue>> attributesMap = new HashMap<Platform, Map<String, MessageAttributeValue>>();
	static {
		attributesMap.put(Platform.ADM, null);
		attributesMap.put(Platform.GCM, null);
		attributesMap.put(Platform.APNS, null);
		attributesMap.put(Platform.APNS_SANDBOX, null);
		attributesMap.put(Platform.BAIDU, addBaiduNotificationAttributes());
		attributesMap.put(Platform.WNS, addWNSNotificationAttributes());
		attributesMap.put(Platform.MPNS, addMPNSNotificationAttributes());
	}

	public static void main(String[] args) throws IOException {
		/*
		 * TODO: Be sure to fill in your AWS access credentials in the
		 * AwsCredentials.properties file before you try to run this sample.
		 * http://aws.amazon.com/security-credentials
		 */
		AmazonSNS sns = new AmazonSNSClient(new PropertiesCredentials(
				SNSMobilePush.class
						.getResourceAsStream("AwsCredentials.properties")));

		sns.setEndpoint("https://sns.us-west-2.amazonaws.com");
		System.out.println("===========================================\n");
		System.out.println("Getting Started with Amazon SNS");
		System.out.println("===========================================\n");
		try {
			SNSMobilePush sample = new SNSMobilePush(sns);
			/* TODO: Uncomment the services you wish to use. */
			// sample.demoAndroidAppNotification();
			// sample.demoKindleAppNotification();
			// sample.demoAppleAppNotification();
			sample.demoAppleSandboxAppNotification();
			// sample.demoBaiduAppNotification();
			// sample.demoWNSAppNotification();
			// sample.demoMPNSAppNotification();
		} catch (AmazonServiceException ase) {
			System.out
					.println("Caught an AmazonServiceException, which means your request made it "
							+ "to Amazon SNS, but was rejected with an error response for some reason.");
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			System.out
					.println("Caught an AmazonClientException, which means the client encountered "
							+ "a serious internal problem while trying to communicate with SNS, such as not "
							+ "being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}
	}

	public void demoAndroidAppNotification() {
		// TODO: Please fill in following values for your application. You can
		// also change the notification payload as per your preferences using
		// the method
		// com.amazonaws.sns.samples.tools.SampleMessageGenerator.getSampleAndroidMessage()
		String serverAPIKey = "";
		String applicationName = "";
		String registrationId = "";
		snsClientWrapper.demoNotification(Platform.GCM, "", serverAPIKey,
				registrationId, applicationName, attributesMap);
	}

	public void demoKindleAppNotification() {
		// TODO: Please fill in following values for your application. You can
		// also change the notification payload as per your preferences using
		// the method
		// com.amazonaws.sns.samples.tools.SampleMessageGenerator.getSampleKindleMessage()
		String clientId = "";
		String clientSecret = "";
		String applicationName = "";

		String registrationId = "";
		snsClientWrapper.demoNotification(Platform.ADM, clientId, clientSecret,
				registrationId, applicationName, attributesMap);
	}

	public void demoAppleAppNotification() {
		// TODO: Please fill in following values for your application. You can
		// also change the notification payload as per your preferences using
		// the method
		// com.amazonaws.sns.samples.tools.SampleMessageGenerator.getSampleAppleMessage()
		String certificate = "-----BEGIN CERTIFICATE-----\r\n" + 
				"MIIGXjCCBUagAwIBAgIIWxviHdMYcWowDQYJKoZIhvcNAQELBQAwgZYxCzAJBgNV\r\n" + 
				"BAYTAlVTMRMwEQYDVQQKDApBcHBsZSBJbmMuMSwwKgYDVQQLDCNBcHBsZSBXb3Js\r\n" + 
				"ZHdpZGUgRGV2ZWxvcGVyIFJlbGF0aW9uczFEMEIGA1UEAww7QXBwbGUgV29ybGR3\r\n" + 
				"aWRlIERldmVsb3BlciBSZWxhdGlvbnMgQ2VydGlmaWNhdGlvbiBBdXRob3JpdHkw\r\n" + 
				"HhcNMTcwODEyMDQyNDM1WhcNMTgwOTExMDQyNDM1WjCBrzEuMCwGCgmSJomT8ixk\r\n" + 
				"AQEMHmNvbS5hYXJuYVNvbHV0aW9ucy5zZWd1ZXN1ZG9rdTE8MDoGA1UEAwwzQXBw\r\n" + 
				"bGUgUHVzaCBTZXJ2aWNlczogY29tLmFhcm5hU29sdXRpb25zLnNlZ3Vlc3Vkb2t1\r\n" + 
				"MRMwEQYDVQQLDAo2WFIyVkY5OTNQMR0wGwYDVQQKDBRQaGFuaW5kcmEgcHVyaWdo\r\n" + 
				"YWxsYTELMAkGA1UEBhMCVVMwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIB\r\n" + 
				"AQCk2KClAbJxxFjnKPp8lB2iGoNR3wDiHU6w/2p2AkLFS6+mZceKH5QqoSoS3Cn2\r\n" + 
				"9T9x2o1himdBRo/9Wt9A9RTDFo4v/axLT77q10nGUNB262MYoqRmwh7//pW5LEnV\r\n" + 
				"hwkn+Pyz8dLJVEDA3QNTGadJrsBh2pTS2O3nqD82zTr5JL1Y92E6Fhi7duptIC6I\r\n" + 
				"AVVxiG1eGdONiPLWt7GEGR2g/u9q6RT2BNf76jzFJr9ViOkoIksXwsN1D4KfeKnr\r\n" + 
				"P2RBBRRgBTwXCI3IcBtyKPjbx90v9VDIwf1qRQ0rsHIkuWJNfY6etcNKXot/wVoK\r\n" + 
				"KoGpzgeAPIN+4qNVhwDVj6jrAgMBAAGjggKTMIICjzAdBgNVHQ4EFgQUgP8ndOW8\r\n" + 
				"p+ArTkHOmyTcC04xp7AwDAYDVR0TAQH/BAIwADAfBgNVHSMEGDAWgBSIJxcJqbYY\r\n" + 
				"YIvs67r2R1nFUlSjtzCCARwGA1UdIASCARMwggEPMIIBCwYJKoZIhvdjZAUBMIH9\r\n" + 
				"MIHDBggrBgEFBQcCAjCBtgyBs1JlbGlhbmNlIG9uIHRoaXMgY2VydGlmaWNhdGUg\r\n" + 
				"YnkgYW55IHBhcnR5IGFzc3VtZXMgYWNjZXB0YW5jZSBvZiB0aGUgdGhlbiBhcHBs\r\n" + 
				"aWNhYmxlIHN0YW5kYXJkIHRlcm1zIGFuZCBjb25kaXRpb25zIG9mIHVzZSwgY2Vy\r\n" + 
				"dGlmaWNhdGUgcG9saWN5IGFuZCBjZXJ0aWZpY2F0aW9uIHByYWN0aWNlIHN0YXRl\r\n" + 
				"bWVudHMuMDUGCCsGAQUFBwIBFilodHRwOi8vd3d3LmFwcGxlLmNvbS9jZXJ0aWZp\r\n" + 
				"Y2F0ZWF1dGhvcml0eTAwBgNVHR8EKTAnMCWgI6Ahhh9odHRwOi8vY3JsLmFwcGxl\r\n" + 
				"LmNvbS93d2RyY2EuY3JsMA4GA1UdDwEB/wQEAwIHgDATBgNVHSUEDDAKBggrBgEF\r\n" + 
				"BQcDAjAQBgoqhkiG92NkBgMBBAIFADAQBgoqhkiG92NkBgMCBAIFADCBowYKKoZI\r\n" + 
				"hvdjZAYDBgSBlDCBkQweY29tLmFhcm5hU29sdXRpb25zLnNlZ3Vlc3Vkb2t1MAUM\r\n" + 
				"A2FwcAwjY29tLmFhcm5hU29sdXRpb25zLnNlZ3Vlc3Vkb2t1LnZvaXAwBgwEdm9p\r\n" + 
				"cAwrY29tLmFhcm5hU29sdXRpb25zLnNlZ3Vlc3Vkb2t1LmNvbXBsaWNhdGlvbjAO\r\n" + 
				"DAxjb21wbGljYXRpb24wDQYJKoZIhvcNAQELBQADggEBAC4STsiKffmR/ohY0xf7\r\n" + 
				"Rc/ceKVNJs/u9afiCDv2Pw0+uvh85P4bimr9hT6dvnOlJ7WYiC9BG8MEJAIBS1nu\r\n" + 
				"Tweu+YygRlNhUMPhfBuLanlkPMDbCFlFvUHSEta4y6GNXv/SeoU8qRwziRlE0ZwX\r\n" + 
				"pe3z8GaYvZ4Y7POYAWfVnSkqfc0gcfSfYk4XfGLitXRdM1manKHc6CsQYg/UWuX8\r\n" + 
				"SfsRwNvOGJoJjDEt0bMWVG/f4VYAPd79wVHrZ8DObb9mMJ9TWJoGPg5z4TvKtwq6\r\n" + 
				"GR2yLw809EDk4raX5hPJ+jGlje7YgEtazQ4fRfRYjzUGoN0/mRWncGKy7ISaeSIe\r\n" + 
				"bqc=\r\n" + 
				"-----END CERTIFICATE-----\r\n" + 
				""; // This should be in pem format with \n at the
									// end of each line.
		String privateKey = "-----BEGIN RSA PRIVATE KEY-----\r\n" + 
				"MIIEowIBAAKCAQEApNigpQGyccRY5yj6fJQdohqDUd8A4h1OsP9qdgJCxUuvpmXH\r\n" + 
				"ih+UKqEqEtwp9vU/cdqNYYpnQUaP/VrfQPUUwxaOL/2sS0++6tdJxlDQdutjGKKk\r\n" + 
				"ZsIe//6VuSxJ1YcJJ/j8s/HSyVRAwN0DUxmnSa7AYdqU0tjt56g/Ns06+SS9WPdh\r\n" + 
				"OhYYu3bqbSAuiAFVcYhtXhnTjYjy1rexhBkdoP7vaukU9gTX++o8xSa/VYjpKCJL\r\n" + 
				"F8LDdQ+Cn3ip6z9kQQUUYAU8FwiNyHAbcij428fdL/VQyMH9akUNK7ByJLliTX2O\r\n" + 
				"nrXDSl6Lf8FaCiqBqc4HgDyDfuKjVYcA1Y+o6wIDAQABAoIBABY3wEcdGHDu9tCJ\r\n" + 
				"HGGoEOplSmfBd7wCGTYP5shq+5bd4Zm6xA8gvHaWbDZIiDtpyJhOzZlk7w/EM+oA\r\n" + 
				"0nBN1/OHWbisJwmrgN7kD2R0RfSROzT5ztCuv0Dfjkx75KOr5JcHnv+dyzwpZ+TW\r\n" + 
				"pPkRTxPGQtWtgTt902rSqVp4hMdJbfyyjkFqbQcTs3xASgcvhNfZVSK9mfx8fwlc\r\n" + 
				"dzwbCHbKavxVBIoc8DnnmzSskJGqmtwbEWazxOM1obfZOJTjAWCqGfsVr36mcadC\r\n" + 
				"IXCj3d09/9E+dRKW1/Du8cmv6MtKrkZPgf4UhdIRC5lrG5hqYnFduWBHFqilp7Cj\r\n" + 
				"snzdPekCgYEA0a1Xpiwd2HHE8wAdEg/nAnMaHy17tRD7pKmlkMsvrCYi01DfK4CS\r\n" + 
				"mDLV1LzTJaWNTgwmTMf8SD6iS1UDIlEW/a5FehuCAjWHWdcnb5EXGR0U6eGYsdhs\r\n" + 
				"uiaMyC+ogoni7Bo7666vTAQZiHxdk6XpAOF6JtSWBh2EvdbrMlz9EAcCgYEAyUPL\r\n" + 
				"O/9QX4uM+Aksx5EC/h+Nbsr0o5IDiabW7cizDl2mLio18I49wKUeHLqEFJ+S6CcH\r\n" + 
				"VvmTxjgfHGkQ64Azo1vIxXIcfw09m4ibOLXgnluc3T5bC/CJ0jYZ+gSlwluVsvJ8\r\n" + 
				"x8kz//dxGnm0JGx8xpuh2Oc5bnZP1I93Dao6Hv0CgYAu6ZCtvjsjPTkqgUD/1I0E\r\n" + 
				"EEqs5RlDhrw2uDox7jQJWTL7gqGb8xVZdi4/fcvkJWk7KB69mRUJ+PGI10lvOSNi\r\n" + 
				"uMYUrujko6NX53qMTLMVTFtshKwMRnb1I0DmXTtfkYgy+R+k+J2B7Xzk+6ZYLYu3\r\n" + 
				"Rbt1yqSSU7JqYxaWWxXHqwKBgQC/wDZWICaMESGqgttF+3O1d4xBPV4cpRV3xRvK\r\n" + 
				"ikeF7VVRcGfZmztcWjQ5PL5wA6RsSBrnE3tmA7woTjWPmaIZBm5fJxvdNFk44Mq6\r\n" + 
				"GhY56aCm7SzDUFWOkHrpSBpA9Q7H4d1eXuSK/02j4sXtWer7MfXd+FYwVsoyAslz\r\n" + 
				"nHYpZQKBgEYW2/Omit+WQEbG4eINvmZ7/YzISfb5Z1L+BUTsg/1WXIEVDK2dCZLU\r\n" + 
				"UP2N8vTyLmS4ugYVOPuz5BZa5AcFZvZjTCh9XTqZArBPtbQVEF1aLjyrSbZZ7S6j\r\n" + 
				"E68MbkHTPLU5u6hKrCx9IRXvcwKtLT6SWebbSRm+t2gxIjN41Vej\r\n" + 
				"-----END RSA PRIVATE KEY-----"; // This should be in pem format with \n at the
								// end of each line.
		String applicationName = "SegueSudokuPushNotification";
		String deviceToken = "55575CBF272E9A309724EA815400CDE0426FD90ECCCD6B0C1C34DDD9BCFCE975"; // This is 64 hex characters.
		snsClientWrapper.demoNotification(Platform.APNS, certificate,
				privateKey, deviceToken, applicationName, attributesMap);
	}

	public void demoAppleSandboxAppNotification() {
		// TODO: Please fill in following values for your application. You can
		// also change the notification payload as per your preferences using
		// the method
		// com.amazonaws.sns.samples.tools.SampleMessageGenerator.getSampleAppleMessage()
		String certificate = "-----BEGIN CERTIFICATE-----\r\n" + 
				"MIIGXjCCBUagAwIBAgIIWxviHdMYcWowDQYJKoZIhvcNAQELBQAwgZYxCzAJBgNV\r\n" + 
				"BAYTAlVTMRMwEQYDVQQKDApBcHBsZSBJbmMuMSwwKgYDVQQLDCNBcHBsZSBXb3Js\r\n" + 
				"ZHdpZGUgRGV2ZWxvcGVyIFJlbGF0aW9uczFEMEIGA1UEAww7QXBwbGUgV29ybGR3\r\n" + 
				"aWRlIERldmVsb3BlciBSZWxhdGlvbnMgQ2VydGlmaWNhdGlvbiBBdXRob3JpdHkw\r\n" + 
				"HhcNMTcwODEyMDQyNDM1WhcNMTgwOTExMDQyNDM1WjCBrzEuMCwGCgmSJomT8ixk\r\n" + 
				"AQEMHmNvbS5hYXJuYVNvbHV0aW9ucy5zZWd1ZXN1ZG9rdTE8MDoGA1UEAwwzQXBw\r\n" + 
				"bGUgUHVzaCBTZXJ2aWNlczogY29tLmFhcm5hU29sdXRpb25zLnNlZ3Vlc3Vkb2t1\r\n" + 
				"MRMwEQYDVQQLDAo2WFIyVkY5OTNQMR0wGwYDVQQKDBRQaGFuaW5kcmEgcHVyaWdo\r\n" + 
				"YWxsYTELMAkGA1UEBhMCVVMwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIB\r\n" + 
				"AQCk2KClAbJxxFjnKPp8lB2iGoNR3wDiHU6w/2p2AkLFS6+mZceKH5QqoSoS3Cn2\r\n" + 
				"9T9x2o1himdBRo/9Wt9A9RTDFo4v/axLT77q10nGUNB262MYoqRmwh7//pW5LEnV\r\n" + 
				"hwkn+Pyz8dLJVEDA3QNTGadJrsBh2pTS2O3nqD82zTr5JL1Y92E6Fhi7duptIC6I\r\n" + 
				"AVVxiG1eGdONiPLWt7GEGR2g/u9q6RT2BNf76jzFJr9ViOkoIksXwsN1D4KfeKnr\r\n" + 
				"P2RBBRRgBTwXCI3IcBtyKPjbx90v9VDIwf1qRQ0rsHIkuWJNfY6etcNKXot/wVoK\r\n" + 
				"KoGpzgeAPIN+4qNVhwDVj6jrAgMBAAGjggKTMIICjzAdBgNVHQ4EFgQUgP8ndOW8\r\n" + 
				"p+ArTkHOmyTcC04xp7AwDAYDVR0TAQH/BAIwADAfBgNVHSMEGDAWgBSIJxcJqbYY\r\n" + 
				"YIvs67r2R1nFUlSjtzCCARwGA1UdIASCARMwggEPMIIBCwYJKoZIhvdjZAUBMIH9\r\n" + 
				"MIHDBggrBgEFBQcCAjCBtgyBs1JlbGlhbmNlIG9uIHRoaXMgY2VydGlmaWNhdGUg\r\n" + 
				"YnkgYW55IHBhcnR5IGFzc3VtZXMgYWNjZXB0YW5jZSBvZiB0aGUgdGhlbiBhcHBs\r\n" + 
				"aWNhYmxlIHN0YW5kYXJkIHRlcm1zIGFuZCBjb25kaXRpb25zIG9mIHVzZSwgY2Vy\r\n" + 
				"dGlmaWNhdGUgcG9saWN5IGFuZCBjZXJ0aWZpY2F0aW9uIHByYWN0aWNlIHN0YXRl\r\n" + 
				"bWVudHMuMDUGCCsGAQUFBwIBFilodHRwOi8vd3d3LmFwcGxlLmNvbS9jZXJ0aWZp\r\n" + 
				"Y2F0ZWF1dGhvcml0eTAwBgNVHR8EKTAnMCWgI6Ahhh9odHRwOi8vY3JsLmFwcGxl\r\n" + 
				"LmNvbS93d2RyY2EuY3JsMA4GA1UdDwEB/wQEAwIHgDATBgNVHSUEDDAKBggrBgEF\r\n" + 
				"BQcDAjAQBgoqhkiG92NkBgMBBAIFADAQBgoqhkiG92NkBgMCBAIFADCBowYKKoZI\r\n" + 
				"hvdjZAYDBgSBlDCBkQweY29tLmFhcm5hU29sdXRpb25zLnNlZ3Vlc3Vkb2t1MAUM\r\n" + 
				"A2FwcAwjY29tLmFhcm5hU29sdXRpb25zLnNlZ3Vlc3Vkb2t1LnZvaXAwBgwEdm9p\r\n" + 
				"cAwrY29tLmFhcm5hU29sdXRpb25zLnNlZ3Vlc3Vkb2t1LmNvbXBsaWNhdGlvbjAO\r\n" + 
				"DAxjb21wbGljYXRpb24wDQYJKoZIhvcNAQELBQADggEBAC4STsiKffmR/ohY0xf7\r\n" + 
				"Rc/ceKVNJs/u9afiCDv2Pw0+uvh85P4bimr9hT6dvnOlJ7WYiC9BG8MEJAIBS1nu\r\n" + 
				"Tweu+YygRlNhUMPhfBuLanlkPMDbCFlFvUHSEta4y6GNXv/SeoU8qRwziRlE0ZwX\r\n" + 
				"pe3z8GaYvZ4Y7POYAWfVnSkqfc0gcfSfYk4XfGLitXRdM1manKHc6CsQYg/UWuX8\r\n" + 
				"SfsRwNvOGJoJjDEt0bMWVG/f4VYAPd79wVHrZ8DObb9mMJ9TWJoGPg5z4TvKtwq6\r\n" + 
				"GR2yLw809EDk4raX5hPJ+jGlje7YgEtazQ4fRfRYjzUGoN0/mRWncGKy7ISaeSIe\r\n" + 
				"bqc=\r\n" + 
				"-----END CERTIFICATE-----\r\n" + 
				""; // This should be in pem format with \n at the
									// end of each line.
		String privateKey = "-----BEGIN RSA PRIVATE KEY-----\r\n" + 
				"MIIEowIBAAKCAQEApNigpQGyccRY5yj6fJQdohqDUd8A4h1OsP9qdgJCxUuvpmXH\r\n" + 
				"ih+UKqEqEtwp9vU/cdqNYYpnQUaP/VrfQPUUwxaOL/2sS0++6tdJxlDQdutjGKKk\r\n" + 
				"ZsIe//6VuSxJ1YcJJ/j8s/HSyVRAwN0DUxmnSa7AYdqU0tjt56g/Ns06+SS9WPdh\r\n" + 
				"OhYYu3bqbSAuiAFVcYhtXhnTjYjy1rexhBkdoP7vaukU9gTX++o8xSa/VYjpKCJL\r\n" + 
				"F8LDdQ+Cn3ip6z9kQQUUYAU8FwiNyHAbcij428fdL/VQyMH9akUNK7ByJLliTX2O\r\n" + 
				"nrXDSl6Lf8FaCiqBqc4HgDyDfuKjVYcA1Y+o6wIDAQABAoIBABY3wEcdGHDu9tCJ\r\n" + 
				"HGGoEOplSmfBd7wCGTYP5shq+5bd4Zm6xA8gvHaWbDZIiDtpyJhOzZlk7w/EM+oA\r\n" + 
				"0nBN1/OHWbisJwmrgN7kD2R0RfSROzT5ztCuv0Dfjkx75KOr5JcHnv+dyzwpZ+TW\r\n" + 
				"pPkRTxPGQtWtgTt902rSqVp4hMdJbfyyjkFqbQcTs3xASgcvhNfZVSK9mfx8fwlc\r\n" + 
				"dzwbCHbKavxVBIoc8DnnmzSskJGqmtwbEWazxOM1obfZOJTjAWCqGfsVr36mcadC\r\n" + 
				"IXCj3d09/9E+dRKW1/Du8cmv6MtKrkZPgf4UhdIRC5lrG5hqYnFduWBHFqilp7Cj\r\n" + 
				"snzdPekCgYEA0a1Xpiwd2HHE8wAdEg/nAnMaHy17tRD7pKmlkMsvrCYi01DfK4CS\r\n" + 
				"mDLV1LzTJaWNTgwmTMf8SD6iS1UDIlEW/a5FehuCAjWHWdcnb5EXGR0U6eGYsdhs\r\n" + 
				"uiaMyC+ogoni7Bo7666vTAQZiHxdk6XpAOF6JtSWBh2EvdbrMlz9EAcCgYEAyUPL\r\n" + 
				"O/9QX4uM+Aksx5EC/h+Nbsr0o5IDiabW7cizDl2mLio18I49wKUeHLqEFJ+S6CcH\r\n" + 
				"VvmTxjgfHGkQ64Azo1vIxXIcfw09m4ibOLXgnluc3T5bC/CJ0jYZ+gSlwluVsvJ8\r\n" + 
				"x8kz//dxGnm0JGx8xpuh2Oc5bnZP1I93Dao6Hv0CgYAu6ZCtvjsjPTkqgUD/1I0E\r\n" + 
				"EEqs5RlDhrw2uDox7jQJWTL7gqGb8xVZdi4/fcvkJWk7KB69mRUJ+PGI10lvOSNi\r\n" + 
				"uMYUrujko6NX53qMTLMVTFtshKwMRnb1I0DmXTtfkYgy+R+k+J2B7Xzk+6ZYLYu3\r\n" + 
				"Rbt1yqSSU7JqYxaWWxXHqwKBgQC/wDZWICaMESGqgttF+3O1d4xBPV4cpRV3xRvK\r\n" + 
				"ikeF7VVRcGfZmztcWjQ5PL5wA6RsSBrnE3tmA7woTjWPmaIZBm5fJxvdNFk44Mq6\r\n" + 
				"GhY56aCm7SzDUFWOkHrpSBpA9Q7H4d1eXuSK/02j4sXtWer7MfXd+FYwVsoyAslz\r\n" + 
				"nHYpZQKBgEYW2/Omit+WQEbG4eINvmZ7/YzISfb5Z1L+BUTsg/1WXIEVDK2dCZLU\r\n" + 
				"UP2N8vTyLmS4ugYVOPuz5BZa5AcFZvZjTCh9XTqZArBPtbQVEF1aLjyrSbZZ7S6j\r\n" + 
				"E68MbkHTPLU5u6hKrCx9IRXvcwKtLT6SWebbSRm+t2gxIjN41Vej\r\n" + 
				"-----END RSA PRIVATE KEY-----"; // This should be in pem format with \n at the
								// end of each line.
		String applicationName = "SegueSudokuDevPushNotification";
		String deviceToken = "7D8867CA1E8CAE598848381088B8C1F10200A2BC17946464808CAF19B136FF39"; // This is 64 hex characters.
		snsClientWrapper.demoNotification(Platform.APNS_SANDBOX, certificate,
				privateKey, deviceToken, applicationName, attributesMap);
	}

	public void demoBaiduAppNotification() {
		/*
		 * TODO: Please fill in the following values for your application. If
		 * you wish to change the properties of your Baidu notification, you can
		 * do so by modifying the attribute values in the method
		 * addBaiduNotificationAttributes() . You can also change the
		 * notification payload as per your preferences using the method
		 * com.amazonaws
		 * .sns.samples.tools.SampleMessageGenerator.getSampleBaiduMessage()
		 */
		String userId = "";
		String channelId = "";
		String apiKey = "";
		String secretKey = "";
		String applicationName = "";
		snsClientWrapper.demoNotification(Platform.BAIDU, apiKey, secretKey,
				channelId + "|" + userId, applicationName, attributesMap);
	}

	public void demoWNSAppNotification() {
		/*
		 * TODO: Please fill in the following values for your application. If
		 * you wish to change the properties of your WNS notification, you can
		 * do so by modifying the attribute values in the method
		 * addWNSNotificationAttributes() . You can also change the notification
		 * payload as per your preferences using the method
		 * com.amazonaws.sns.samples
		 * .tools.SampleMessageGenerator.getSampleWNSMessage()
		 */
		String notificationChannelURI = "";
		String packageSecurityIdentifier = "";
		String secretKey = "";
		String applicationName = "";
		snsClientWrapper.demoNotification(Platform.WNS,
				packageSecurityIdentifier, secretKey, notificationChannelURI,
				applicationName, attributesMap);
	}

	public void demoMPNSAppNotification() {
		/*
		 * TODO: Please fill in the following values for your application. If
		 * you wish to change the properties of your MPNS notification, you can
		 * do so by modifying the attribute values in the method
		 * addMPNSNotificationAttributes() . You can also change the
		 * notification payload as per your preferences using the method
		 * com.amazonaws
		 * .sns.samples.tools.SampleMessageGenerator.getSampleMPNSMessage ()
		 */
		String notificationChannelURI = "";
		String applicationName = "";
		snsClientWrapper.demoNotification(Platform.MPNS, "", "",
				notificationChannelURI, applicationName, attributesMap);
	}

	private static Map<String, MessageAttributeValue> addBaiduNotificationAttributes() {
		Map<String, MessageAttributeValue> notificationAttributes = new HashMap<String, MessageAttributeValue>();
		notificationAttributes.put("AWS.SNS.MOBILE.BAIDU.DeployStatus",
				new MessageAttributeValue().withDataType("String")
						.withStringValue("1"));
		notificationAttributes.put("AWS.SNS.MOBILE.BAIDU.MessageKey",
				new MessageAttributeValue().withDataType("String")
						.withStringValue("default-channel-msg-key"));
		notificationAttributes.put("AWS.SNS.MOBILE.BAIDU.MessageType",
				new MessageAttributeValue().withDataType("String")
						.withStringValue("0"));
		return notificationAttributes;
	}

	private static Map<String, MessageAttributeValue> addWNSNotificationAttributes() {
		Map<String, MessageAttributeValue> notificationAttributes = new HashMap<String, MessageAttributeValue>();
		notificationAttributes.put("AWS.SNS.MOBILE.WNS.CachePolicy",
				new MessageAttributeValue().withDataType("String")
						.withStringValue("cache"));
		notificationAttributes.put("AWS.SNS.MOBILE.WNS.Type",
				new MessageAttributeValue().withDataType("String")
						.withStringValue("wns/badge"));
		return notificationAttributes;
	}

	private static Map<String, MessageAttributeValue> addMPNSNotificationAttributes() {
		Map<String, MessageAttributeValue> notificationAttributes = new HashMap<String, MessageAttributeValue>();
		notificationAttributes.put("AWS.SNS.MOBILE.MPNS.Type",
				new MessageAttributeValue().withDataType("String")
						.withStringValue("token")); // This attribute is required.
		notificationAttributes.put("AWS.SNS.MOBILE.MPNS.NotificationClass",
				new MessageAttributeValue().withDataType("String")
						.withStringValue("realtime")); // This attribute is required.
														
		return notificationAttributes;
	}
}
