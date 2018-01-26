package com.aarnaSolutions.bdd.runner;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dbunit.dataset.ITable;
import org.junit.Assert;

import com.cucumber.listener.Reporter;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Cabip001Steps extends BatchCucumberTester {

  Integer accountNo;
  Integer prevBillRefNo;
  Integer prevBillRefResets;

  @Given("^an account eligible for billing exists in the database$")
  public void accountInEBEDueToBeBilled() throws Throwable {
    BigDecimal test_account = (BigDecimal) (databaseTester.getConnection().createQueryTable(
        "extn_bill_extract",
        "select account_no from sky.extn_bill_extract").getValue(0, "account_no"));
    this.accountNo = test_account.intValue();

    // Store the prev bill ref no currently in CMF
    ITable cmfBeforeTest =
        databaseTester.getConnection().createQueryTable(
            "cmf",
            "select prev_bill_refno, prev_bill_ref_resets from arbor.cmf where account_no = "
                + this.accountNo);
    assertEquals(1, cmfBeforeTest.getRowCount());

    this.prevBillRefNo = Integer.parseInt(cmfBeforeTest.getValue(0, "prev_bill_refno").toString());
    this.prevBillRefResets = Integer.parseInt(cmfBeforeTest.getValue(0, "prev_bill_ref_resets").toString());
    
    Reporter.addStepLog("Latest invoice number before executing CABIP001: "
        + this.prevBillRefNo.toString() + " - " + this.prevBillRefResets.toString());

  }
  
  @When("^BIP process is executed$")
  public void runBIP() {
	  Reporter.addStepLog("BIP is executed - assume");
  }

  @Then("^expected file (.*) should be generated based on (.*) file$")
  public void expectedInvoiceGenerated(String expectedFileName, String configFileName) throws Throwable {

    String expectedFilePath = "src/test/resources/"+expectedFileName;
    
    Map<String, List<String>> tableFieldMapping = getTableFieldMappingFromConfigFile(configFileName);
    List<String> headerList = new ArrayList<String>();
    List<ITable> iTables = new ArrayList<ITable>();
    
    getHeaderAndData(headerList, iTables, tableFieldMapping);
    
    createFile(headerList, iTables, expectedFilePath);
    
    /*Reporter.addStepLog("New invoice generated: "
        + cmfAfterTest.getValue(0, "prev_bill_refno").toString());*/
     
  }
  
  @Then("^actual file (.*) should be generated based on (.*) file$")
  public void actualInvoiceGenerated(String actualFileName, String configFileName) throws Throwable {

    String actualFilePath = "src/test/resources/" + actualFileName;
    
    Map<String, List<String>> tableFieldMapping = getTableFieldMappingFromConfigFile(configFileName);
    List<String> headerList = new ArrayList<String>();
    List<ITable> iTables = new ArrayList<ITable>();
    
    getHeaderAndData(headerList, iTables, tableFieldMapping);
    
    createFile(headerList, iTables, actualFilePath);
    
    /*Reporter.addStepLog("New invoice generated: "
        + cmfAfterTest.getValue(0, "prev_bill_refno").toString());*/
    
  }
  
  @Given("^baseline and post upgrade are executed succesfully creating (.*) and (.*) files$")
  public void givenFilesForCompare(String expectedFile, String actualFile) {
    File expected = new File("src/test/resources/"+expectedFile);
    Assert.assertTrue(expected.exists());
    
    File actual = new File("src/test/resources/"+actualFile);
    Assert.assertTrue(actual.exists());
  }
  
  @Then("^compare the (.*) and (.*) files and verify the results$")
  public void compareFiles(String expectedFile, String actualFile) {
    compare("src/test/resources/"+expectedFile, "src/test/resources/"+actualFile);
  }
  
  /**
   * Get the table and fields to be used for verification using given config file
   * @return
   * @throws FileNotFoundException
   * @throws IOException
   */
  private Map<String, List<String>> getTableFieldMappingFromConfigFile(String configFileName) throws FileNotFoundException, IOException {
    File config_file = new File("src/test/resources/"+configFileName);
    BufferedReader reader = new BufferedReader(new FileReader(config_file));
    
    String read_line = null;
    Map<String, List<String>> tableFieldMapping = new HashMap<String, List<String>>();
    List<String> fieldsList = new ArrayList<String>();
    while ((read_line = reader.readLine()) != null) {
      String[] fields = read_line.split(",");
      if (tableFieldMapping.containsKey(fields[0])) {
        fieldsList.add(fields[1]);
        tableFieldMapping.put(fields[0], fieldsList);
      } else {
        fieldsList = new ArrayList<String>();
        fieldsList.add(fields[1]);
        tableFieldMapping.put(fields[0], fieldsList);
      }
    }
    
    return tableFieldMapping;
  }
  
  /**
   * Get the header and data to be added to a file.
   * @param headerList
   * @param iTables
   * @param tableFieldMapping
   * @throws Exception
   */
  private void getHeaderAndData(List<String> headerList, List<ITable> iTables, Map<String, List<String>> tableFieldMapping) throws Exception {
    String table = null;
    ITable cmfAfterTest = null;
    
    for (String key : tableFieldMapping.keySet() ) {
      table = key;
      
      List<String> fields = tableFieldMapping.get(table);
      String headers = fields.get(0).trim();
      
      for (int i=1; i<fields.size(); i++) {
        headers = headers + "," + fields.get(i).trim();
      }
      
      // Check that the prev bill ref no is updated in CMF
      if (table.equals("CMF")) {
        cmfAfterTest =
            databaseTester.getConnection().createQueryTable(
                table,
                "select " + headers + " from " + table +" where account_no = "
                    + this.accountNo);
        
        iTables.add(cmfAfterTest);
        
        assertEquals(1, cmfAfterTest.getRowCount());
        Assert.assertNotEquals(this.prevBillRefNo, cmfAfterTest.getValue(0, "prev_bill_refno")
            .toString());
      }
      
      ITable billInvoice = null;
      if (table.equals("BILL_INVOICE")) {
        billInvoice =
            databaseTester.getConnection().createQueryTable(
                table,
                "select " + headers + " from " + table +" where bill_ref_no = "
                    + this.prevBillRefNo + " and bill_ref_resets = " 
                    + this.prevBillRefResets);
        iTables.add(billInvoice);
      }
      headerList.add(headers);
    }
  }
  
  /**
   * Creates the csv file with given headers and data with the specified file name.
   * @param headers
   * @param iTables
   * @param filePath
   * @throws Exception
   */
  private void createFile(List<String> headers, List<ITable> iTables, String filePath) throws Exception {
    File fc = null;
    BufferedWriter writer = null;

    try{
          
          fc = new File(filePath);
          
          if (fc.exists())
              fc.delete();

          // Create specified file
          fc.createNewFile();

          writer = new BufferedWriter(new FileWriter(fc, true));
          String header = headers.get(0);
          for (int i = 1; i < headers.size(); i++) {
            header = header + "," + headers.get(i);
          }
          writer.write(header.trim());
          writer.newLine();

          StringBuffer line = new StringBuffer();
          
          for(int i = 0; i < iTables.size(); i++) {
            if (iTables.get(i).getTableMetaData().getTableName().equals("CMF")) {
              line.append("," + String.valueOf(iTables.get(i).getValue(0, "account_no")) + "," + String.valueOf(iTables.get(i).getValue(0, "prev_bill_refno")) + "," + String.valueOf(iTables.get(i).getValue(0, "prev_bill_ref_resets")));
            } else if (iTables.get(i).getTableMetaData().getTableName().equals("BILL_INVOICE")) {
              line.append("," + String.valueOf(iTables.get(i).getValue(0, "bill_sequence_num")));
            }
          }
          
          if (line != null)
          {
            writer.write(line.toString());
            writer.newLine();
          }

      } finally {
        if (writer != null)
        {
          writer.close();
          writer = null;
        }
          
      }
  }
  
  /**
   * Compare the given expected and actual files.
   * @param expectedFilePath
   * @param actualFilePath
   */
  private void compare(String expectedFilePath, String actualFilePath) {
    List<String[]> expectedFieldList = getDataFromFile(expectedFilePath);
    List<String[]> actualFieldList = getDataFromFile(actualFilePath);
    
    //verify number of rows are equal 
    Assert.assertEquals(expectedFieldList.size(),  actualFieldList.size());
    
    //verify number of columns referring to fields in table are same
    Assert.assertEquals(expectedFieldList.get(0).length,  actualFieldList.get(0).length);
    int numOfcolumns = expectedFieldList.get(0).length;
    
    //verify headers match
    for (int column = 0; column < numOfcolumns; column++) {
      Assert.assertTrue(expectedFieldList.get(0)[column].equals(actualFieldList.get(0)[column]));
    }
    
    for (int i = 1; i < expectedFieldList.size(); i++) { //verify values for each row in expected list
      for (int j = 1; j < actualFieldList.size(); j++) { // verify values for each row in actual list
        for (int column = 1; column < numOfcolumns + 1; column++) {
          if (expectedFieldList.get(0)[column-1].equals("account_no")) {
            System.out.println("column 0:" + expectedFieldList.get(i)[column]);
            Assert.assertTrue(expectedFieldList.get(i)[column].equals(actualFieldList.get(j)[column]));
          } else if (expectedFieldList.get(0)[column-1].equals("prev_bill_refno")) {
            System.out.println("column 1:" + expectedFieldList.get(i)[column]);
            Assert.assertFalse(expectedFieldList.get(i)[column].equals(actualFieldList.get(j)[column]));
            Assert.assertTrue(Integer.valueOf(expectedFieldList.get(i)[column]) < Integer.valueOf(actualFieldList.get(j)[column]));
          } else if (expectedFieldList.get(0)[column-1].equals("prev_bill_ref_resets")) {
            Assert.assertTrue(expectedFieldList.get(i)[column].equals(actualFieldList.get(j)[column]));
          } else if (expectedFieldList.get(0)[column-1].equals("bill_sequence_num")) {
            int previous_bill_seq_num = Integer.valueOf(expectedFieldList.get(i)[column].trim());
            int current_bill_seq_num = Integer.valueOf(actualFieldList.get(j)[column].trim());
            Assert.assertEquals(previous_bill_seq_num + 1, current_bill_seq_num);
          }
        }

      }
    }
  }
  
  /**
   * Reads the data from given file. 
   * @param filePath
   * @return the list containing file data
   */
  private List<String[]> getDataFromFile(String filePath) {
    String dataLine = null;
    List<String[]> fileList1 = new ArrayList<String[]>();
    try {
      BufferedReader reader1 = new BufferedReader(new FileReader(filePath));
      while( (dataLine=reader1.readLine()) != null ) {
        fileList1.add(dataLine.split(","));
      }
      reader1.close();

    }catch(Exception e){
      e.printStackTrace();
    }
    
    return fileList1;
  }


}
