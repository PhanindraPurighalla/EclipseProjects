package com.amazonaws.sns.samples.mobilepush;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="DeviceTokens")
public class DeviceToken {
    
    private String deviceID;
    private String deviceToken;
    private String userType;
    private String someProp;
    
    @DynamoDBHashKey(attributeName="DeviceID")  
    public String getDeviceID() { return deviceID;}
    public void setDeviceID(String deviceID) {this.deviceID = deviceID;}
    
    @DynamoDBAttribute(attributeName="DeviceToken")  
    public String getDeviceToken() {return deviceToken; }
    public void setDeviceToken(String deviceToken) { this.deviceToken = deviceToken; }
    
    @DynamoDBAttribute(attributeName="UserType")  
    public String getUserType() { return userType; }
    public void setUserType(String userType) { this.userType = userType; }
    
    @DynamoDBIgnore
    public String getSomeProp() { return someProp;}
    public void setSomeProp(String someProp) {this.someProp = someProp;}
}
